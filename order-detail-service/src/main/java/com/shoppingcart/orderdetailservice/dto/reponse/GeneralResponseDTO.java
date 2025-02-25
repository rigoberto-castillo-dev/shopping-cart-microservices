package com.shoppingcart.orderdetailservice.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponseDTO {
    private String codeResponse;
    private String messageResponse;
}