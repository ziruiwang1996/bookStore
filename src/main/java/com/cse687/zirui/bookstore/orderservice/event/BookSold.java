package com.cse687.zirui.bookstore.orderservice.event;

public record BookSold(
        Long bookId,
        String isbn,
        Long userId,
        String condition,
        boolean paperBook,
        float price)
        implements Event {}
