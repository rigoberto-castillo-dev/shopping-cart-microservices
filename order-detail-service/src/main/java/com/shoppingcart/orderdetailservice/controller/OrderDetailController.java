package com.shoppingcart.orderdetailservice.controller;

import com.shoppingcart.orderdetailservice.dto.OrderDetailDTO;
import com.shoppingcart.orderdetailservice.dto.request.OrderDetailIdRequestDTO;
import com.shoppingcart.orderdetailservice.dto.request.OrderDetailRequestDTO;
import com.shoppingcart.orderdetailservice.dto.reponse.OrderDetailResponseDTO;
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
    public ResponseEntity<OrderDetailResponseDTO> saveOrderDetails(@RequestBody List<OrderDetailRequestDTO> request) {
        return service.saveOrderDetails(request);
    }

    @PostMapping("by-id")
    public ResponseEntity<OrderDetailResponseDTO> getOrderDetailsById(@Valid @RequestBody OrderDetailIdRequestDTO orderDetailIdRequestDTO) {
        return service.getOrderDetailsByOrderId(orderDetailIdRequestDTO);
    }
}
