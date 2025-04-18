package com.cse687.zirui.bookstore.cart.domain.command;

public record AddItem(
        Long itemId,
        Long userId
) implements Command {}