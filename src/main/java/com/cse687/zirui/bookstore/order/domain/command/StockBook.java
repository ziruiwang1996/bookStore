package com.cse687.zirui.bookstore.order.domain.command;

public record StockBook(
        String isbn,
        String condition,
        boolean paperBook,
        float price
) implements Command {}
