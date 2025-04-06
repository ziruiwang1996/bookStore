package com.cse687.zirui.bookstore.services.order.command;

public record Restock(String isbn, double price) implements Command {
}
