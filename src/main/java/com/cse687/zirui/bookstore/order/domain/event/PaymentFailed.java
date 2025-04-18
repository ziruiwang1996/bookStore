package com.cse687.zirui.bookstore.order.domain.event;

public record PaymentFailed(
        Long orderId,
        String message
) implements Event {}
