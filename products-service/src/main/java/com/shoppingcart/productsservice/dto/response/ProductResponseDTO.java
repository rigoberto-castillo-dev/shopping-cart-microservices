package com.shoppingcart.productsservice.dto.response;

import com.shoppingcart.productsservice.dto.ProductDTO;
import com.shoppingcart.productsservice.model.Product;
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
