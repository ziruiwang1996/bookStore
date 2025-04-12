package com.cse687.zirui.bookstore.order.domain.event;

public record BookBought(
        Long bookId,
        Long userId
) implements Event {}
