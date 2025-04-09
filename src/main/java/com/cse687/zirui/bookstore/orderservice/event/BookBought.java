package com.cse687.zirui.bookstore.orderservice.event;

public record BookBought(Long bookId,
                         Long userId)
        implements Event {}
