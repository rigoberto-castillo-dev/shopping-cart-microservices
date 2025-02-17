package com.shoppingcart.payment.service.constants;

public class Constants {

    public static final String LOG_PROCESSING_PAYMENT = "Processing payment for Order ID: {} with amount: {}";
    public static final String LOG_INVALID_PAYMENT_AMOUNT = "Invalid payment amount: {} for Order ID: {}";
    public static final String LOG_PAYMENT_RESULT = "Payment simulation result: {}";
    public static final String LOG_PAYMENT_SUCCESS = "Payment successful for Order ID: {}";
    public static final String LOG_PAYMENT_FAILED = "Payment failed for Order ID: {}";
    public static final String RESPONSE_PAYMENT_SUCCESS = "Payment processed successfully";
    public static final String RESPONSE_PAYMENT_FAILED = "Payment failed";
    public static final String ERROR_INVALID_AMOUNT = "Payment amount must be at least 1";
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_INTERNAL_SERVER_ERROR = 500;
}
