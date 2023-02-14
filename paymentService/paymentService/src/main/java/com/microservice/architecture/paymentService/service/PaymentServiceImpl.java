package com.microservice.architecture.paymentService.service;

import com.microservice.architecture.paymentService.entity.TransactionDetails;
import com.microservice.architecture.paymentService.model.PaymentRequest;
import com.microservice.architecture.paymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("creating entity");
        TransactionDetails transactionDetails= TransactionDetails.builder()
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .amount(paymentRequest.getAmount())
                .paymentDate(Instant.now())
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .build();
        transactionDetailsRepository.save(transactionDetails);
        log.info("entity saved in database");
        return transactionDetails.getId();
    }
}
