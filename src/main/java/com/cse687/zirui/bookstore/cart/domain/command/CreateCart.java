package com.cse687.zirui.bookstore.cart.domain.command;

public record CreateCart(
        Long userId
) implements Command {}
