package com.shoppingcart.orderservice.dto.response;

import com.shoppingcart.orderservice.dto.OrderDTO;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private GeneralResponseDTO generalResponseDto;
    List<OrderDTO> orderDto;
}
