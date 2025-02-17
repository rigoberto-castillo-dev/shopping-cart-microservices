package com.shoppingcart.orderdetailservice.controller;

import com.shoppingcart.orderdetailservice.dto.OrderDetailDTO;
import com.shoppingcart.orderdetailservice.dto.OrderDetailRequestDTO;
import com.shoppingcart.orderdetailservice.dto.OrderDetailResponseDTO;
import com.shoppingcart.orderdetailservice.service.OrderDetailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingcart/order-details")
public class OrderDetailController {
    private final OrderDetailService service;

    public OrderDetailController(OrderDetailService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderDetailResponseDTO<List<OrderDetailDTO>>> saveOrderDetails(@RequestBody List<OrderDetailRequestDTO> request) {
        return service.saveOrderDetails(request);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailResponseDTO<List<OrderDetailDTO>>> getOrderDetails(@PathVariable Long orderId) {
        return service.getOrderDetails(orderId);
    }
}
