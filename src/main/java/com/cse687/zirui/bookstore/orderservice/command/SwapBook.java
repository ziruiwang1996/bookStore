package com.cse687.zirui.bookstore.orderservice.command;

public record SwapBook(Long bookId1, Long bookId2) implements Command {
}
