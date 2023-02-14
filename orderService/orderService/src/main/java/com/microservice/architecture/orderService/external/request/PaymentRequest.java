package com.microservice.architecture.orderService.external.request;

import com.microservice.architecture.orderService.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private Long orderId;
    private Long amount;
    private String referenceNumber;
    private Payment paymentMode;

}
