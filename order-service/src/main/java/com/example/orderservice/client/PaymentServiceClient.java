package com.example.orderservice.client;

import com.example.orderservice.vo.RequestPayment;
import com.example.orderservice.vo.ResponsePayment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="payment-service")
public interface PaymentServiceClient {

    @PostMapping("/payment-service/payments")
    ResponsePayment createPayment(@RequestBody RequestPayment paymentDetails);

}
