package com.cse687.zirui.bookstore.orderservice.command;

public record StockBook(String isbn, double price) implements Command {
}
