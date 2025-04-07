package com.cse687.zirui.bookstore.services.order.command;

public record ReStock(String isbn, double price) implements Command {
}
