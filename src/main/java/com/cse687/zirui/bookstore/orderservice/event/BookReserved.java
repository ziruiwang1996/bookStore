package com.cse687.zirui.bookstore.orderservice.event;

public record BookReserved(Long customerId, Long bookId) implements Event {
}
