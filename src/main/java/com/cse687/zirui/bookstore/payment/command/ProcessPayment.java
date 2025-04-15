package com.cse687.zirui.bookstore.payment.command;

public record ProcessPayment(
        Long orderId,
        Long userId,
        float amount
) implements Command {}
