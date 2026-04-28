package com.example.productservice.controller;

import com.example.productservice.dto.NotificationResponse;
import com.example.productservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notify-service")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Notify Service";
    }

    @GetMapping("/notifications")
    public List<NotificationResponse> getNotifications() {
        return notificationService.getAllNotifications();
    }
}