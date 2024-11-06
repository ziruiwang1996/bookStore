package com.cse687.zirui.bookstore.domain.event;

public class UsedBookBought implements Event {
    private final Long bookId;
    private final Long customerId;

    public UsedBookBought(Long bookid, Long customerid) {
        this.bookId = bookid;
        this.customerId = customerid;
    }

    public Long getBookId() { return bookId; }

    public Long getCustomerId() { return customerId; }
}