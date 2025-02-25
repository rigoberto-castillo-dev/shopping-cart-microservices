package com.shoppingcart.orderservice.service;

import com.shoppingcart.orderservice.constants.Constants;
import com.shoppingcart.orderservice.dto.*;
import com.shoppingcart.orderservice.dto.request.OrderDetailRequestDTO;
import com.shoppingcart.orderservice.dto.request.OrderRequestDTO;
import com.shoppingcart.orderservice.dto.request.PaymentRequestDTO;
import com.shoppingcart.orderservice.dto.response.GeneralResponseDTO;
import com.shoppingcart.orderservice.dto.response.OrderDetailResponseDTO;
import com.shoppingcart.orderservice.dto.response.OrderResponseDTO;
import com.shoppingcart.orderservice.dto.response.PaymentResponseDTO;
import com.shoppingcart.orderservice.feign.OrderDetailClient;
import com.shoppingcart.orderservice.feign.PaymentClient;
import com.shoppingcart.orderservice.model.Order;
import com.shoppingcart.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository repository;
    private final PaymentClient paymentClient;
    private final OrderDetailClient orderDetailClient;

    public OrderService(OrderRepository repository, PaymentClient paymentClient, OrderDetailClient orderDetailClient) {
        this.repository = repository;
        this.paymentClient = paymentClient;
        this.orderDetailClient = orderDetailClient;
    }

    @Transactional
    public ResponseEntity<OrderResponseDTO> processOrder(OrderRequestDTO orderRequestDto) {
        Order order = new Order();
        order.setUserId(orderRequestDto.getUserId());
        order.setTotalAmount(orderRequestDto.getTotalAmount());
        order.setPaid(false);
        order.setCreatedAt(LocalDateTime.now());
        repository.save(order);

        List<OrderDetailDTO> savedOrderDetails = new ArrayList<>();
        if (orderRequestDto.getOrderDetailsRequestDto() != null && !orderRequestDto.getOrderDetailsRequestDto().isEmpty()) {
            List<OrderDetailRequestDTO> orderDetailRequestDTO = orderRequestDto.getOrderDetailsRequestDto().stream()
                    .map(detail -> new OrderDetailRequestDTO(order.getId(), detail.getProductId(), detail.getQuantity()))
                    .collect(Collectors.toList());

            OrderDetailResponseDTO detailResponse = orderDetailClient.saveOrderDetails(orderDetailRequestDTO);

            if (detailResponse.getGeneralResponseDto().getCodeResponse().trim().equals(Constants.SUCCESS)) {
                savedOrderDetails = detailResponse.getOrderDetailDto();
            } else {
                log.error(Constants.ERROR_CONNECT_TO_ORDER_DETAIL_SERVICE);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new OrderResponseDTO(new GeneralResponseDTO(Constants.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECT_TO_ORDER_DETAIL_SERVICE), null));
            }
        }

        PaymentRequestDTO paymentRequest = new PaymentRequestDTO(order.getId(), orderRequestDto.getUserId(), orderRequestDto.getTotalAmount());
        PaymentResponseDTO paymentResponse = paymentClient.processPayment(paymentRequest);

        if (!paymentResponse.getGeneralResponseDto().getCodeResponse().trim().equals(Constants.SUCCESS)) {
            log.error(Constants.ERROR_CONNECT_TO_PAYMENT_SERVICE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new OrderResponseDTO(new GeneralResponseDTO(Constants.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECT_TO_PAYMENT_SERVICE), null));
        }

        order.setPaid(true);
        repository.save(order);

        OrderDTO orderDTO = new OrderDTO(order.getId(), orderRequestDto.getUserId(), paymentResponse.getPaymentDto().getAmount(), order.isPaid(), paymentResponse.getPaymentDto().getCliente(), paymentResponse.getPaymentDto().getPaymentMethod(), paymentResponse.getPaymentDto().getTimestamp(), savedOrderDetails);
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO(Constants.SUCCESS, Constants.ORDER_PROCESS);
        List<OrderDTO> orderDtoList = List.of(orderDTO);
        log.info(Constants.ORDER_PROCESS);
        return ResponseEntity.status(HttpStatus.OK).body(new OrderResponseDTO(generalResponseDTO, orderDtoList));
    }
}
