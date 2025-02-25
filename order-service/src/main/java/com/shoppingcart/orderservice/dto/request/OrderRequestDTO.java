package com.shoppingcart.orderservice.dto.request;

import com.shoppingcart.orderservice.dto.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private Long userId;
    private double totalAmount;
    private List<OrderDetailRequestDTO> orderDetailsRequestDto;
}
