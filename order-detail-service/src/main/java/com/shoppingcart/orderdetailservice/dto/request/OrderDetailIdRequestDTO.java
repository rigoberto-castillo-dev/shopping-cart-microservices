package com.shoppingcart.orderdetailservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailIdRequestDTO {
    @NotNull(message = "ID cannot be null")
    private Long orderId;
}
