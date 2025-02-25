package com.shoppingcart.orderservice.feign;

import com.shoppingcart.orderservice.config.FeignConfig;
import com.shoppingcart.orderservice.dto.request.OrderDetailRequestDTO;
import com.shoppingcart.orderservice.dto.response.OrderDetailResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order-detail-service", url = "${orderdetail.service.client.url}", configuration = FeignConfig.class)
public interface OrderDetailClient {
    @PostMapping("/order-details")
    OrderDetailResponseDTO saveOrderDetails(@RequestBody List<OrderDetailRequestDTO> request);
}
