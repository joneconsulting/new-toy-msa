package com.example.productservice.dto;

import lombok.Data;

@Data
public class PaymentDto { // implements Serializable {
    private String paymentId;
    private Integer totalPrice;
    private String method;
    private String status;

    private String orderId;
    private String userId;
}
