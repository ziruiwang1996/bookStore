package com.cse687.zirui.bookstore.domain.event;

public class NewBookBought implements Event {
    private final String isbn;
    private final Long customerId;
    private final float price;

    public NewBookBought(String isbn, Long customerid, float price) {
        this.isbn = isbn;
        this.customerId = customerid;
        this.price = price;
    }

    public String getISBN() { return isbn; }

    public Long getCustomerId() { return customerId; }

    public float getPrice() { return price; }
}