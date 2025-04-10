package com.cse687.zirui.bookstore.orderservice.command;

public record StockBook(
        String isbn,
        boolean paperBook,
        String condition,
        float price
) implements Command {
}
