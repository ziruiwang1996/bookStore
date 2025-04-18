package com.cse687.zirui.bookstore.auth.domain.command;

public record AddCreditIfMember(
        Long userId,
        float amt
) implements Command {}