package com.cse687.zirui.bookstore.orderservice.command;

public record ReserveBook(
        Long bookId,
        Long userId)
        implements Command {}
