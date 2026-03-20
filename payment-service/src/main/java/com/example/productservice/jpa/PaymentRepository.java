package com.example.productservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {
    PaymentEntity findByPaymentId(String productId);
    PaymentEntity findByOrderId(String orderId);
}
