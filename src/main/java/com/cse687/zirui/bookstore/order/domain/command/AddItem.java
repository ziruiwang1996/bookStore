package com.cse687.zirui.bookstore.order.domain.command;

public record AddItem(
        Long itemId,
        Long userId
) implements Command {}