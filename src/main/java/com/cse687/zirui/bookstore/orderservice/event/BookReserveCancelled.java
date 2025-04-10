package com.cse687.zirui.bookstore.orderservice.event;

public record BookReserveCancelled(
        Long bookId,
        Long userId)
        implements Event {}
