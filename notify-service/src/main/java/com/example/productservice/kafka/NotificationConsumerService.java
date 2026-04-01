package com.example.productservice.kafka;

import com.example.productservice.jpa.NotificationEntity;
import com.example.productservice.jpa.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumerService {

    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order.events", groupId = "notify-service-group")
    public void consumeOrderCreated(String kafkaMessage) {
        log.info("Received Kafka message: {}", kafkaMessage);

        try {
            Map<String, Object> map = objectMapper.readValue(
                    kafkaMessage,
                    new TypeReference<Map<String, Object>>() {}
            );

            String eventType = (String) map.get("eventType");
            if (!"ORDER_CREATED".equals(eventType)) {
                log.info("Ignored eventType: {}", eventType);
                return;
            }

            String orderId = (String) map.get("orderId");
            String userId = (String) map.get("userId");
            Integer amount = (Integer) map.get("amount");

            NotificationEntity entity = NotificationEntity.builder()
                    .orderId(orderId)
                    .userId(userId)
                    .amount(amount)
                    .message("주문이 접수되었습니다.")
                    .build();

            notificationRepository.save(entity);

            log.info("Notification saved. orderId={}, userId={}", orderId, userId);

        } catch (JsonProcessingException ex) {
            log.error("Failed to parse kafka message: {}", kafkaMessage, ex);
        } catch (Exception ex) {
            log.error("Failed to save notification", ex);
        }
    }
}