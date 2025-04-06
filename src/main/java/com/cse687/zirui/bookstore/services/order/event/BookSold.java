package com.cse687.zirui.bookstore.services.order.event;

public record BookSold(Long customerId, Long bookId) implements Event {
}
