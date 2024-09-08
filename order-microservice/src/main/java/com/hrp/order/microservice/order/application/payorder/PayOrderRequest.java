package com.hrp.order.microservice.order.application.payorder;

import com.hrp.order.microservice.payment.application.paymentorder.PaymentOrderRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayOrderRequest {
    private List<PaymentOrderRequest> paymentOrderRequests;
}
