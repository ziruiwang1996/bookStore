package com.cse687.zirui.bookstore.orderservice.event;

public record BookReserved(
        Long bookId,
        Long customerId)
        implements Event {}
