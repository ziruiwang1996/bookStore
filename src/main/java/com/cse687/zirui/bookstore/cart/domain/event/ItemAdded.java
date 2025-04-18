package com.cse687.zirui.bookstore.cart.domain.event;

public record ItemAdded(
        Long userId,
        Long itemId
) implements Event {}
