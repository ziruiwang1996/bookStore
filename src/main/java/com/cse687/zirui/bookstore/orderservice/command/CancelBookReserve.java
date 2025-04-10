package com.cse687.zirui.bookstore.orderservice.command;

public record CancelBookReserve(
        Long bookId,
        Long userId)
        implements Command{}
