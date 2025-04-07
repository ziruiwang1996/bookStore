package com.cse687.zirui.bookstore.services.order.event;

public record OutOfStock(String isbn) implements Event {}