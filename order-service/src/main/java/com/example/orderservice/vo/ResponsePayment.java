package com.example.orderservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePayment {
    private String paymentId;
    private String method;
    private String status;
    private Integer totalPrice;
    private String userId;
    private String orderId;
    private Date createdAt;
}
