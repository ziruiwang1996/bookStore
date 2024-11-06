package com.cse687.zirui.bookstore.domain.command;

public class BuyNewBook implements Command {
    private final String isbn;
    private final String customerEmail;
    private final float price;

    public BuyNewBook(String isbn, String email, float price) {
        this.isbn = isbn;
        this.customerEmail = email;
        this.price = price;
    }

    public String getIsbn() { return isbn; }

    public String getCustomerEmail() { return customerEmail; }

    public float getPrice() {return price; }
}
