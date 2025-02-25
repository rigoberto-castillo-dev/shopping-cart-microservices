package com.shoppingcart.payment.service.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequestDTO {
    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotNull(message = "User is required")
    private Long userId;

    @Min(value = 1, message = "Amount must be at least 1")
    private double amount;
}
