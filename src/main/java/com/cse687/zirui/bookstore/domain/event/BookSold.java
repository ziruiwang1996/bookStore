package com.cse687.zirui.bookstore.domain.event;

public class BookSold implements Event {
    private final Long bookId;
    private final Long customerId;

    public BookSold(Long bookId, Long customerId) {
        this.bookId = bookId;
        this.customerId = customerId;
    }

    public Long getBookId() { return bookId; }

    public Long getCustomerId() { return customerId; }
}