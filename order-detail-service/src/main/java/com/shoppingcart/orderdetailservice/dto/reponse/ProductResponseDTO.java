package com.shoppingcart.orderdetailservice.dto.reponse;

import com.shoppingcart.orderdetailservice.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private GeneralResponseDTO generalResponseDto;
    private List<ProductDTO> productsDto;
}
