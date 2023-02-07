package com.microservice.architecture.orderService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/products")
public interface ProductService {

    @PutMapping("/reducequantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long id, @RequestParam Long quantity);
}
