package com.example.orderservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDto implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String paymentStatus;
    private Date paymentDate;

    private String orderId;
    private String userId;
}
