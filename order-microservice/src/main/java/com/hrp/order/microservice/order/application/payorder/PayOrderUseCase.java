package com.hrp.order.microservice.order.application.payorder;

import com.hrp.order.microservice.common.annotation.UseCase;
import com.hrp.order.microservice.order.domain.Order;
import com.hrp.order.microservice.order.domain.OrderStatus;
import com.hrp.order.microservice.order.infrastructure.inputports.PayOrderInputPort;
import com.hrp.order.microservice.order.infrastructure.outputports.FindOrderOutputPort;
import com.hrp.order.microservice.order.infrastructure.outputports.PayOrderOutputPort;
import com.hrp.order.microservice.payment.application.paymentorder.PaymentOrderRequest;
import com.hrp.order.microservice.payment.infraestructure.inputports.PaymentInputport;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
@Transactional
public class PayOrderUseCase implements PayOrderInputPort {
    private final PayOrderOutputPort payOrderOutputPort;
    private final PaymentInputport paymentInputport;
    private final FindOrderOutputPort findOrderOutputPort;

    @Autowired
    public PayOrderUseCase(PayOrderOutputPort payOrderOutputPort, PaymentInputport paymentInputport, FindOrderOutputPort findOrderOutputPort) {
        this.payOrderOutputPort = payOrderOutputPort;
        this.paymentInputport = paymentInputport;
        this.findOrderOutputPort = findOrderOutputPort;
    }

    @Override
    public void payOrder(Long orderId, PayOrderRequest payOrderRequest) {
        //validate the order exists
        Order order = findOrderOutputPort.findOrder(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        //verify they not pay or cancelled the order
        if(!order.getStatus().equals(OrderStatus.PENDING)){
            throw new IllegalArgumentException("Order status is not PENDING");
        }
        //verify if the payment methos are complete
        if(order.getTotalAmount() > getTotalAmout(payOrderRequest.getPaymentOrderRequests())){
            throw new IllegalArgumentException("the total amount is insufficient");
        }
        // save the payments use case
        for (PaymentOrderRequest payment : payOrderRequest.getPaymentOrderRequests()) {
            savePayments(order, payment);
        }
        //change the status
        order.setStatus(OrderStatus.COMPLETED);
        //update the order
        order =  payOrderOutputPort.updateOrderWithPayments(order);
    }

    private Double getTotalAmout(List<PaymentOrderRequest> payments){
        return payments.stream()
                .mapToDouble(PaymentOrderRequest::getAmount)
                .sum();
    }

    private void savePayments(Order order, PaymentOrderRequest paymentCheckOutRequest){
        paymentInputport.savePayment(order, paymentCheckOutRequest);
    }
}
