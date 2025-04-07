package com.cse687.zirui.bookstore.services.order.event;

public record BookReserved(Long customerId, Long bookId) implements Event {
}
