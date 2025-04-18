package com.cse687.zirui.bookstore.payment.domain.event;

public record PaymentFailed(
        Long orderId,
        String message
) implements Event {}