package com.example.userservice.client;

import com.example.userservice.error.FeignErrorDecoder4Payment;
import com.example.userservice.vo.ResponsePayment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="payment-service", configuration = FeignErrorDecoder4Payment.class)
public interface PaymentServiceClient {

    @GetMapping("/payment-service/payments/{orderId}")
    ResponsePayment getPayment(@PathVariable("orderId") String orderId);

}
