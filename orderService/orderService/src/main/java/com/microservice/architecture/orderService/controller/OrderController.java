package com.microservice.architecture.orderService.controller;

import com.microservice.architecture.orderService.model.OrderRequest;
import com.microservice.architecture.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeorder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        Long id=orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
