package com.cse687.zirui.bookstore.orderservice.command;

public record BuyBook(Long bookId, Long userId) implements Command {
}
