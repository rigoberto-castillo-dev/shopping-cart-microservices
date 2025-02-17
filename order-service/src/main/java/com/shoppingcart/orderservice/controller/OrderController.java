package com.shoppingcart.orderservice.controller;


import com.shoppingcart.orderservice.dto.OrderDTO;
import com.shoppingcart.orderservice.dto.OrderRequestDTO;
import com.shoppingcart.orderservice.dto.OrderResponseDTO;
import com.shoppingcart.orderservice.model.Order;
import com.shoppingcart.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService service) {
        this.orderService = service;
    }

    // 🔹 Crear una nueva orden
    @PostMapping
    public ResponseEntity<OrderResponseDTO<OrderDTO>> createOrder(@RequestBody OrderRequestDTO request) {
        return orderService.createOrder(request);
    }

    // 🔹 Obtener una orden por ID con sus detalles
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO<OrderDTO>> getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }
}
