package com.cse687.zirui.bookstore.services.order.event;

public record OutOfStock(Long bookId) implements Event {}