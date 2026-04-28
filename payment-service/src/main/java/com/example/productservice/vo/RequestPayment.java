package com.example.productservice.vo;

import lombok.Data;

@Data
public class RequestPayment {
    private String orderId;
    private String userId;
    private Integer totalPrice;
    private String method;

    public RequestPayment(String orderId, String userId, Integer totalPrice, String method) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.method = method;
    }
}
