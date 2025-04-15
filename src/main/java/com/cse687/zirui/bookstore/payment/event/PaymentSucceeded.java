package com.cse687.zirui.bookstore.payment.event;

public record PaymentSucceeded(
        Long orderId,
        Long paymentId,
        Long userId,
        float amount
) implements Event {}