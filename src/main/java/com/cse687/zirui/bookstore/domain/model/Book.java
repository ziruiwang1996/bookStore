package com.cse687.zirui.bookstore.domain.model;
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
    private BookStatus status;

    public Book() {}

    public Book(String isbn, String authors, String title, String edition, float price, int state) {
        this.ISBN = isbn;
        this.authors = authors;
        this.title = title;
        this.edition = edition;
        this.price = price;
        if (state == 1) {
            this.status = BookStatus.AVAILABLE;
        } else {
            this.status = BookStatus.SOLD;
        }
    }

    public void flipStatus() {
        if (status == BookStatus.AVAILABLE) {
            status = BookStatus.SOLD;
        } else if (status == BookStatus.SOLD) {
            status = BookStatus.AVAILABLE;
        }
    }

    public void depreciation() {
        price *= 0.1F;
    }

    public Long getId() {
        return id;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public String getEdition() {
        return edition;
    }

    public float getPrice() {
        return price;
    }

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
        return true;
    }

    public int hashCode() {
        return Math.toIntExact(id) + ISBN.hashCode() + authors.hashCode() + title.hashCode() + edition.hashCode() + (int) price;
    }

    public String toString() {
        return "(ID: "
                .concat(String.valueOf(id)).concat("; ISBN: ")
                .concat(ISBN==null ? "" : ISBN).concat("; Authors: ")
                .concat(authors==null ? "" : authors).concat("; Title: ")
                .concat(title==null ? "" : title).concat("; Edition: ")
                .concat(edition==null ? "" : edition).concat("; Price: ")
                .concat(String.valueOf(price)).concat(";)");
    }
}