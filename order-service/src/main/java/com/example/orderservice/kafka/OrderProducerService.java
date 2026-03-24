package com.example.orderservice.kafka;

import com.example.orderservice.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderProducerService {
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC_NAME = "example-orders-topic";  // 주문 이벤트를 보낼 토픽명

    @Autowired
    public OrderProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(OrderDto orderDto) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(orderDto);

            kafkaTemplate.send(TOPIC_NAME, json);

            log.info("Kafka Producer sent data from the Order microservice: {}", orderDto);
        } catch(JsonProcessingException ex) {
            log.error("Failed to serialize orderDTO", ex);
        }
    }
}
