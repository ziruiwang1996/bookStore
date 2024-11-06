package com.cse687.zirui.bookstore.domain.event;

public class DuplicateBookFound implements Event {
    private Long bookId;

    public DuplicateBookFound(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() { return bookId; }
}
