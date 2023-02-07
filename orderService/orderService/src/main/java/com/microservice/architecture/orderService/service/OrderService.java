package com.microservice.architecture.orderService.service;

import com.microservice.architecture.orderService.model.OrderRequest;
import org.springframework.stereotype.Repository;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
