package com.cse687.zirui.bookstore.payment.domain.command;
import java.util.List;

public record GenerateInvoice(
        Long orderId,
        Long paymentId,
        Long userId,
        List<Long> items,
        float amt
) implements Command {}
