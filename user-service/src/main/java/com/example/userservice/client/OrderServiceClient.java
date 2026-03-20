package com.example.userservice.client;

import com.example.userservice.vo.ResponseOrder;
import com.example.userservice.error.FeignErrorDecoder4Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="order-service", configuration = FeignErrorDecoder4Order.class)
public interface OrderServiceClient {

    @GetMapping("/order-service/{userId}/orders-ng")
    List<ResponseOrder> getOrders(@PathVariable String userId);
}
