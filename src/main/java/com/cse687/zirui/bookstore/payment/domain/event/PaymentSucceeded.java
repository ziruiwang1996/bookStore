package com.cse687.zirui.bookstore.payment.domain.event;
import java.util.List;

public record PaymentSucceeded(
        Long orderId,
        Long userId,
        List<Long> items
) implements Event {}