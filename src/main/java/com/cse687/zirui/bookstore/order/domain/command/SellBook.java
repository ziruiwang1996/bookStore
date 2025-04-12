package com.cse687.zirui.bookstore.order.domain.command;

public record SellBook(
        Long bookId,
        Long userId,
        String isbn,
        String condition,
        float price,
        boolean paperBook
) implements Command {}
