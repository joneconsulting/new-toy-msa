package com.example.productservice.controller;

import com.example.productservice.dto.PaymentDto;
import com.example.productservice.jpa.PaymentEntity;
import com.example.productservice.service.PaymentService;
import com.example.productservice.vo.RequestPayment;
import com.example.productservice.vo.ResponsePayment;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment-service")
@Slf4j
public class PaymentController {
    Environment env;
    PaymentService paymentService;

    @Autowired
    public PaymentController(Environment env, PaymentService paymentService) {
        this.env = env;
        this.paymentService = paymentService;
    }

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in Payment Service on LOCAL PORT %s (SERVER PORT %s)",
                env.getProperty("local.server.port"),
                env.getProperty("server.port"));
    }

    @PostMapping("/payments")
    public ResponseEntity<ResponsePayment> createPayment(@RequestBody RequestPayment paymentDetails) {
        log.info("Before add payment data");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        PaymentDto paymentDto = mapper.map(paymentDetails, PaymentDto.class);

        /* jpa */
        PaymentDto createdPayment = paymentService.createPayment(paymentDto);
        ResponsePayment responsePayment = mapper.map(createdPayment, ResponsePayment.class);

        log.info("After added payment data");
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePayment);
    }

    @GetMapping("/payments/{orderId}")
    public ResponseEntity<ResponsePayment> getPayment(@PathVariable("orderId") String orderId) throws Exception {
        log.info("Before retrieve payment data");
        PaymentEntity paymentEntity = paymentService.getPaymentByOrderId(orderId);
        ResponsePayment returnValue = new ModelMapper().map(paymentEntity, ResponsePayment.class);

        log.info("Add retrieved payment data");

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
