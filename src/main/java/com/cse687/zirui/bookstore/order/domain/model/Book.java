package com.cse687.zirui.bookstore.order.domain.model;
import com.cse687.zirui.bookstore.order.domain.model.bookstate.AvailableState;
import com.cse687.zirui.bookstore.order.domain.model.bookstate.BookState;
import com.cse687.zirui.bookstore.order.domain.model.bookstate.ReservedState;
import com.cse687.zirui.bookstore.order.domain.model.bookstate.SoldState;
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
    protected float price;
    protected String stateCode;
    protected Long reservedBy;

    @Transient
    protected BookState soldState;
    @Transient
    protected BookState availableState;
    @Transient
    protected BookState reservedState;
    @Transient
    protected BookState state;

    public Book() {}

    public Book(String isbn, String authors, String title, String publisher, float price) {
        this.isbn = isbn;
        this.authors = authors;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
        initStates();
    }

    private void initStates() {
        this.soldState = new SoldState(this);
        this.availableState = new AvailableState(this);
        this.reservedState = new ReservedState(this);
        switch (stateCode) {
            case "SOLD": this.state = this.soldState; break;
            case "RESERVED": this.state = this.reservedState; break;
            default: this.state = this.availableState;
        }
    }

    @PostLoad
    public void postLoadInit() {
        initStates();
    }

    public void setState(BookState state) {
        this.state = state;
        this.stateCode = state.getCode();
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
        this.state.userSell();
    }

    public void buy(){
        this.state.userBuy();
    }

    public void reserve(Long userId){
        this.state.reserve();
        this.reservedBy = userId;
    }

    public void cancelReserve(){
        this.state.cancelReserve();
        this.reservedBy = null;
    }

    public Long getId() { return id; }

    public String getISBN() { return isbn; }

    public float getPrice() { return price; }

    public String getStateCode() { return stateCode; }

    public Long getReservedBy() { return reservedBy; }

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
