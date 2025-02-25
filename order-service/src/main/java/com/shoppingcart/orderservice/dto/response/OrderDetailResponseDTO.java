package com.shoppingcart.orderservice.dto.response;

import com.shoppingcart.orderservice.dto.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponseDTO {
    private GeneralResponseDTO generalResponseDto;
    private List<OrderDetailDTO> orderDetailDto;
}
