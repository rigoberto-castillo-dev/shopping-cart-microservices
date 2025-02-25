package com.shoppingcart.orderservice.dto.response;

import com.shoppingcart.orderservice.dto.PaymentDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {
  GeneralResponseDTO generalResponseDto;
  PaymentDTO paymentDto;
}
