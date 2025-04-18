package com.cse687.zirui.bookstore.order.domain.model;
import jakarta.persistence.Entity;

@Entity
public class EBook extends Book {
    public EBook() {}

    public EBook(String isbn, String authors, String title, String publisher, float price) {
        super(isbn, authors, title, publisher, price);
    }

    @Override
    public String toString() {
        return String.format("(ID: %d; ISBN: %s; Authors: %s; Title: %s; Publisher: %s; Price: %.2f)",
                id,
                isbn == null ? "" : isbn,
                authors == null ? "" : authors,
                title == null ? "" : title,
                publisher == null ? "" : publisher,
                price
        );
    }
}