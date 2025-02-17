package com.shoppingcart.orderservice.feign;

import com.shoppingcart.orderservice.dto.PaymentRequestDTO;
import com.shoppingcart.orderservice.dto.PaymentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://localhost:8083")
public interface PaymentClient {
    @PostMapping("shoppingcart/payments")
    PaymentResponseDTO processPayment(@RequestBody PaymentRequestDTO request);
}
