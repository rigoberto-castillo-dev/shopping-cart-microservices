package com.shoppingcart.orderservice.feign;

import com.shoppingcart.orderservice.config.FeignConfig;
import com.shoppingcart.orderservice.dto.request.PaymentRequestDTO;
import com.shoppingcart.orderservice.dto.response.PaymentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "${payment.service.client.url}", configuration = FeignConfig.class)
public interface PaymentClient {
    @PostMapping("/payments")
    PaymentResponseDTO processPayment(@RequestBody PaymentRequestDTO request);
}
