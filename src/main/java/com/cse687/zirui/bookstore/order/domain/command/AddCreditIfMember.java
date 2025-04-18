package com.cse687.zirui.bookstore.order.domain.command;

public record AddCreditIfMember(
        Long userId,
        float amt
) implements Command {}
