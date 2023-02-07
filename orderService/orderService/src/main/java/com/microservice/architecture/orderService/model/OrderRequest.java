package com.microservice.architecture.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long productId;
    private Long totalAmount;
    private Long quantity;
    private Payment payment;
}
