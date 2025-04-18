package com.cse687.zirui.bookstore.order.domain.command;

public record RemoveItem(
        Long itemId,
        Long userId
) implements Command {}
