package com.shoppingcart.orderdetailservice.dto.reponse;

import com.shoppingcart.orderdetailservice.dto.OrderDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponseDTO{
    private GeneralResponseDTO generalResponseDTO;
    private List<OrderDetailDTO> orderDetailDTO;
}
