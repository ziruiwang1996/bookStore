package com.cse687.zirui.bookstore.orderservice.event;

public record BookSwapped(Long bookId1, Long bookId2) implements Event {
}
