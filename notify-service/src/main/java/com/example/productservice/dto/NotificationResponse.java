package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private String orderId;
    private String userId;
    private Integer amount;
    private String message;
}
