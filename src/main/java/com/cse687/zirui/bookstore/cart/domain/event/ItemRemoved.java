package com.cse687.zirui.bookstore.cart.domain.event;

public record ItemRemoved(
        Long userId,
        Long itemId
) implements Event {}
