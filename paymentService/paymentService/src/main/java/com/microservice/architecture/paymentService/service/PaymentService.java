package com.microservice.architecture.paymentService.service;

import com.microservice.architecture.paymentService.model.PaymentRequest;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);
}
