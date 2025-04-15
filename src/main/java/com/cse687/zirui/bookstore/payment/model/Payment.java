package com.cse687.zirui.bookstore.payment.model;

public class Payment {
    private Long paymentId;
    private Long orderId;
    private Long userId;
    private float amount;
    private PaymentStatus status;
}