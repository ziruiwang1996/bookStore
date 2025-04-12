package com.cse687.zirui.bookstore.order.domain.event;

public record BookSold(
        Long bookId,
        Long userId,
        String isbn,
        String condition,
        boolean paperBook,
        float price
) implements Event {}
