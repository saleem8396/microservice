package com.microservice.architecture.orderService.service;

import com.microservice.architecture.orderService.entity.Orders;
import com.microservice.architecture.orderService.external.client.ProductService;
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
    @Override
    public Long placeOrder(OrderRequest orderRequest) {

        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        Orders order= Orders.builder()
                .orderDate(Instant.now())
                .amount(orderRequest.getTotalAmount())
                .productId(orderRequest.getProductId())
                .orderStatus("CREATED")
                .quantity(orderRequest.getQuantity())
                .build();
        orderRepository.save(order);
        return order.getId();
    }
}
