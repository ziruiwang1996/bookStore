package com.cse687.zirui.bookstore.auth.domain.command;

public record DeleteCart(
        Long userId
) implements Command {}
