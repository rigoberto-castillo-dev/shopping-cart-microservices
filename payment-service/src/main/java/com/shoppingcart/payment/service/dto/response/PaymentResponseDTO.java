package com.shoppingcart.payment.service.dto.response;

import com.shoppingcart.payment.service.dto.PaymentDTO;
import com.shoppingcart.payment.service.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResponseDTO {
    GeneralResponseDTO generalResponseDto;
    PaymentDTO paymentDto;
}
