package com.example.productservice.service;

import com.example.productservice.dto.PaymentDto;
import com.example.productservice.jpa.PaymentEntity;

public interface PaymentService {
    PaymentEntity getPaymentByPaymentId(String paymentId);
    PaymentEntity getPaymentByOrderId(String orderId);
    PaymentDto createPayment(PaymentDto paymentDto);
}
