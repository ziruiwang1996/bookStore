package com.cse687.zirui.bookstore.payment.event;

public record PaymentFailed(
        Long orderId,
        Long userId,
        String reason
) implements Event {}