package com.cse687.zirui.bookstore.order.domain.command;

public record BuyBook(
        Long bookId,
        Long userId
) implements Command {}
