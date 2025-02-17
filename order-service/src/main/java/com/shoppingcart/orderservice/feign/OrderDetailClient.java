package com.shoppingcart.orderservice.feign;

import com.shoppingcart.orderservice.config.FeignConfig;
import com.shoppingcart.orderservice.dto.OrderDetailDTO;
import com.shoppingcart.orderservice.dto.OrderDetailRequestDTO;
import com.shoppingcart.orderservice.dto.OrderDetailResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order-detail-service", url = "http://localhost:8082/shoppingcart", configuration = FeignConfig.class)
public interface OrderDetailClient {
    @GetMapping("/order-details/{orderId}")
    OrderDetailResponseDTO<List<OrderDetailDTO>> getOrderDetailsByOrderId(@PathVariable Long orderId);

    @PostMapping("/order-details")
    ResponseEntity<OrderDetailResponseDTO<List<OrderDetailDTO>>> saveOrderDetails(@RequestBody List<OrderDetailRequestDTO> request);
}
