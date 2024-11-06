package com.cse687.zirui.bookstore.domain.command;

public class BuyUsedBook implements Command {
    private final Long bookId;
    private final String customerEmail;

    public BuyUsedBook(Long bookId, String email) {
        this.bookId = bookId;
        this.customerEmail = email;
    }

    public Long getBookId() { return bookId; }

    public String getCustomerEmail() { return customerEmail; }
}
