package com.shoppingcart.orderservice.controller;


import com.shoppingcart.orderservice.dto.OrderDTO;
import com.shoppingcart.orderservice.dto.request.OrderRequestDTO;
import com.shoppingcart.orderservice.dto.response.OrderResponseDTO;
import com.shoppingcart.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService service) {
        this.orderService = service;
    }

    @PostMapping("/process-order")
    public ResponseEntity<OrderResponseDTO> processOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.processOrder(orderRequestDTO);
    }
}
