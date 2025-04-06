package com.cse687.zirui.bookstore.services.order.model;
import com.cse687.zirui.bookstore.services.order.model.depreciateStrategy.DepreciateStrategy;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String isbn;
    protected String authors;
    protected String title;
    protected String publisher;
    protected double price;
    @Enumerated(EnumType.STRING)
    protected BookState bookState;
    @Transient
    protected DepreciateStrategy depreciateStrategy;

    public Book() {}

    public Book(String isbn, String authors, String title, String publisher, double price, BookState state) {
        this.isbn = isbn;
        this.authors = authors;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
        this.bookState = state;
    }

    public Long getId() { return id; }

    public String getISBN() { return isbn; }

    public String getAuthors() { return authors; }

    public String getTitle() { return title; }

    public String getPublisher() { return publisher; }

    public double getPrice() { return price; }

    public void applyDepreciation() {
        if (depreciateStrategy != null) {
            price *= depreciateStrategy.depreciationRate();
        }
    }

    public abstract void updateAvailability();

    public boolean ifMatches(String query) {
        String[] queries = query.split(",.:;");
        boolean isMatch = true;
        for (String q : queries) {
            boolean isQueryMatch = false;
            if (!isbn.isEmpty() && isbn.toLowerCase().contains(q.toLowerCase())) {
                isQueryMatch = true;
            }
            if (!authors.isEmpty() && authors.toLowerCase().contains(q.toLowerCase())) {
                isQueryMatch = true;
            }
            if (!title.isEmpty() && title.toLowerCase().contains(q.toLowerCase())) {
                isQueryMatch = true;
            }
            if (!publisher.isEmpty() && publisher.toLowerCase().contains(q.toLowerCase())) {
                isQueryMatch = true;
            }
            if (!isQueryMatch) {
                isMatch = false;
                break;
            }
        }
        return isMatch;
    }

    public boolean equals(Object o) {
        if (this == o) { return true;}
        if (!(o instanceof Book that)) { return false; }
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, authors, title, publisher, price);
    }

    public abstract String toString();
}
