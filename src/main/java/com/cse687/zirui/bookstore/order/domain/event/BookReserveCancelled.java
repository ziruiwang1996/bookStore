package com.cse687.zirui.bookstore.order.domain.event;

public record BookReserveCancelled(
        Long bookId,
        Long userId
) implements Event {}
