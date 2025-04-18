package com.cse687.zirui.bookstore.cart.domain.command;

public record PlaceOrder(
    Long userId
) implements Command {}
