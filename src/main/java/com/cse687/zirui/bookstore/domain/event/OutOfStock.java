package com.cse687.zirui.bookstore.domain.event;

public class OutOfStock implements Event {
    private final Long bookId;

    public OutOfStock(Long bookid) {
        this.bookId = bookid;
    }

    public Long getBookId() { return bookId; }
}