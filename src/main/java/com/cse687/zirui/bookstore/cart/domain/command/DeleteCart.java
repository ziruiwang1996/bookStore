package com.cse687.zirui.bookstore.cart.domain.command;

public record DeleteCart(
        Long userId
) implements Command {}
