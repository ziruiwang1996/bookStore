package com.cse687.zirui.bookstore.orderservice.model;
import com.cse687.zirui.bookstore.orderservice.model.bookstate.*;
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
    protected String stateCode;

    @Transient
    protected BookState soldState;
    @Transient
    protected BookState availableState;
    @Transient
    protected BookState reservedState;
    @Transient
    protected BookState state;

    public Book() {}

    public Book(String isbn, String authors, String title, String publisher, double price) {
        this.isbn = isbn;
        this.authors = authors;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
        this.soldState = new SoldState(this);
        this.availableState = new AvailableState(this);
        this.reservedState = new ReservedState(this);
        this.state = this.availableState;
    }

    public void setState(BookState state) {
        this.state = state;
        if (state instanceof SoldState) {
            this.stateCode = "SOLD";
        } else if (state instanceof AvailableState) {
            this.stateCode = "AVAILABLE";
        } else if (state instanceof ReservedState) {
            this.stateCode = "RESERVED";
        }
    }

    public BookState getSoldState() {
        return soldState;
    }

    public BookState getAvailableState() {
        return availableState;
    }

    public BookState getReservedState() {
        return reservedState;
    }

    public void sell(){
        this.state.sell();
    }

    public void buy(){
        this.state.buy();
    }

    public void reserve(){
        this.state.reserve();
    }

    public void cancelReserve(){
        this.state.cancelReserve();
    }

    public Long getId() { return id; }

    public String getISBN() { return isbn; }

    public double getPrice() { return price; }

    public String getStateCode() { return stateCode; }

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
