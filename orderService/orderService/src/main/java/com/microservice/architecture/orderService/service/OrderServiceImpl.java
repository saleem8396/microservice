package com.microservice.architecture.orderService.service;

import com.microservice.architecture.orderService.entity.Orders;
import com.microservice.architecture.orderService.external.client.PaymentService;
import com.microservice.architecture.orderService.external.client.ProductService;
import com.microservice.architecture.orderService.external.request.PaymentRequest;
import com.microservice.architecture.orderService.model.OrderRequest;
import com.microservice.architecture.orderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private PaymentService paymentService;
    @Override
    public Long placeOrder(OrderRequest orderRequest) {

        log.info("calling product service");
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        log.info("creating order entity object");
        Orders order= Orders.builder()
                .orderDate(Instant.now())
                .amount(orderRequest.getTotalAmount())
                .productId(orderRequest.getProductId())
                .orderStatus("CREATED")
                .quantity(orderRequest.getQuantity())
                .build();
        log.info("calling payment service");
        PaymentRequest paymentRequest=PaymentRequest.builder()
                .orderId(order.getId())
                .amount(orderRequest.getTotalAmount())
                .paymentMode(orderRequest.getPayment())
                .build();
        String orderStatus=null;
        try {
            paymentService.doPayment(paymentRequest);
            orderStatus="SUCCESS";
        }catch(Exception e){
            orderStatus="PAYMENT_FAILURE";
        }
        log.info("adding order status to order");
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        log.info("order saved in database");
        return order.getId();
    }
}
