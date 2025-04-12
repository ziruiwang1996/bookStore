package com.cse687.zirui.bookstore.order.domain.command;

public record CancelBookReserve(
        Long bookId,
        Long userId
) implements Command{}
