package com.cse687.zirui.bookstore.services.order.model;
import jakarta.persistence.Entity;

@Entity
public class EBook extends Book {
    private int quantity;

    public EBook() {}

    public EBook(String isbn, String authors, String title, String publisher, double price, BookState state) {
        super(isbn, authors, title, publisher, price, state);
    }

    public void decrementQuantity(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
            updateAvailability();
        } else {
            throw new IllegalStateException("Maximum order allowed: " + quantity);
        }
    }

    public void incrementQuantity(int amount) {
        quantity += amount;
    }

    @Override
    public void updateAvailability() {
        bookState = quantity == 0 ? BookState.SOLD_OUT : BookState.AVAILABLE;
    }

    @Override
    public String toString() {
        return String.format("(ID: %d; ISBN: %s; Authors: %s; Title: %s; Publisher: %s; State: %s; Price: %.2f)",
                id,
                isbn == null ? "" : isbn,
                authors == null ? "" : authors,
                title == null ? "" : title,
                publisher == null ? "" : publisher,
                bookState == null ? "" : bookState.toString(),
                price
        );
    }
}