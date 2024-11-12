package com.cse687.zirui.bookstore.domain.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "book")
public class Book {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id; //unique
    @Column(name = "isbn")
    private String ISBN;
    private String authors;
    private String title;
    private String edition;
    private float price;
    @Enumerated(EnumType.STRING)
    private BookState state;
    private String publisher;
    @JsonProperty("condition")
    @Column(name = "book_condition")
    @Enumerated(EnumType.STRING)
    private BookCondition condition;

    public Book() {}

    public Book(String isbn, String authors, String title, String edition, String publisher, float price, int state, int condition) {
        this.ISBN = isbn;
        this.authors = authors;
        this.title = title;
        this.edition = edition;
        this.publisher = publisher;
        this.price = price;
        if (state == 1) {
            this.state = BookState.AVAILABLE;
        } else {
            this.state = BookState.SOLD;
        }
        if (condition == 1) {
            this.condition = BookCondition.NEW;
        } else {
            this.condition = BookCondition.USED;
        }
    }

    public void flipStatus() {
        if (state == BookState.AVAILABLE) {
            state = BookState.SOLD;
        } else if (state == BookState.SOLD) {
            state = BookState.AVAILABLE;
        }
    }

    public void changeCondition() {
        if (condition == BookCondition.NEW) {
            condition = BookCondition.USED;
        }
    }

    public void depreciation() { price *= 0.9F; }

    public Long getId() { return id; }

    public String getISBN() { return ISBN; }

    public String getAuthors() { return authors; }

    public String getTitle() { return title; }

    public String getEdition() { return edition; }

    public String getPublisher() { return publisher; }

    public float getPrice() { return price; }

    public boolean equals(Object o) {
        if (this == o) { return true;}
        if (!(o instanceof Book that)) { return false; }
        return Objects.equals(this.id, that.id);
    }

    public boolean matches(Book bk) {
        if (!ISBN.isEmpty() && !bk.getISBN().isEmpty() && !ISBN.equals(bk.getISBN())) {
            return false;
        }
        if (!authors.isEmpty() && !bk.getAuthors().isEmpty() && !authors.toLowerCase().contains(bk.getAuthors().toLowerCase())) {
            return false;
        }
        if (!title.isEmpty() && !bk.getTitle().isEmpty() && !title.toLowerCase().contains(bk.getTitle().toLowerCase())) {
            return false;
        }
        if (!edition.isEmpty() && !bk.getEdition().isEmpty() && !edition.toLowerCase().contains(bk.getEdition().toLowerCase())) {
            return false;
        }
        if (!publisher.isEmpty() && !bk.getEdition().isEmpty() && !publisher.toLowerCase().contains(bk.getPublisher().toLowerCase())) {
            return false;
        }
        return true;
    }

    public boolean matches(String query) {
        String[] queryParts = query.split(",.:;");
        boolean ifBookMatch = true;
        for (String queryPart : queryParts) {
            boolean ifPartMatch = false;
            if (!ISBN.isEmpty() && ISBN.toLowerCase().contains(queryPart.toLowerCase())) {
                ifPartMatch = true;
            }
            if (!authors.isEmpty() && authors.toLowerCase().contains(queryPart.toLowerCase())) {
                ifPartMatch = true;
            }
            if (!title.isEmpty() && title.toLowerCase().contains(queryPart.toLowerCase())) {
                ifPartMatch = true;
            }
            if (!edition.isEmpty() && edition.toLowerCase().contains(queryPart.toLowerCase())) {
                ifPartMatch = true;
            }
            if (!publisher.isEmpty() && publisher.toLowerCase().contains(queryPart.toLowerCase())) {
                ifPartMatch = true;
            }
            if (!ifPartMatch) {
                ifBookMatch = false;
                break;
            }
        }
        return ifBookMatch;
    }

    public int hashCode() {
        return Math.toIntExact(id) + ISBN.hashCode() + authors.hashCode() + title.hashCode() + edition.hashCode() + publisher.hashCode() + (int) price;
    }

    @Override
    public String toString() {
        return String.format("(ID: %d; ISBN: %s; Authors: %s; Title: %s; Edition: %s; Publisher: %s; Condition: %s; Price: %.2f; State: %s)",
                id,
                ISBN == null ? "" : ISBN,
                authors == null ? "" : authors,
                title == null ? "" : title,
                edition == null ? "" : edition,
                publisher == null ? "" : publisher,
                condition == null ? "" : condition.toString(),
                price,
                state == null ? "" : state.toString()
        );
    }
}

enum BookCondition {USED, NEW}