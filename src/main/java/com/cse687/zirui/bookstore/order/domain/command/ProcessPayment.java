package com.cse687.zirui.bookstore.order.domain.command;
import java.util.List;

public record ProcessPayment(
        Long orderId,
        Long userId,
        float amount,
        List<Long> items
) implements Command {}