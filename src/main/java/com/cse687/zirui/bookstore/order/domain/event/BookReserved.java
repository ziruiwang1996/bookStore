package com.cse687.zirui.bookstore.order.domain.event;

public record BookReserved(
        Long bookId,
        Long userId
) implements Event {}
