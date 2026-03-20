package com.example.userservice.vo;

import lombok.Data;

@Data
public class RequestPayment {
    private String orderId;
    private String userId;
    private Integer totalPrice;
    private String method;
}
