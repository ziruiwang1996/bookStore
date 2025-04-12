package com.cse687.zirui.bookstore.order.domain.command;

public record ReserveBook(
        Long bookId,
        Long userId
) implements Command {}
