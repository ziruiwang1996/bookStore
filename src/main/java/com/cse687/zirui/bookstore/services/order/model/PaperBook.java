package com.cse687.zirui.bookstore.services.order.model;
import com.cse687.zirui.bookstore.services.order.model.depreciateStrategy.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class PaperBook extends Book {
    @JsonProperty("condition")
    @Column(name = "book_condition")
    @Enumerated(EnumType.STRING)
    private BookCondition bookCondition;

    public PaperBook() {}

    public PaperBook(String isbn, String authors, String title, String publisher, double price, BookState state, BookCondition condition) {
        super(isbn, authors, title, publisher, price, state);
        setCondition(condition);
    }

    public void setCondition(BookCondition condition) {
        bookCondition = condition;
        setDiscountBehavior();
    }

    private void setDiscountBehavior() {
        if (bookCondition == BookCondition.FAIR) {
            depreciateStrategy = new FairDepreciate();
        } else if (bookCondition == BookCondition.GOOD) {
            depreciateStrategy = new GoodDepreciate();
        } else if (bookCondition == BookCondition.LIKE_NEW) {
            depreciateStrategy = new LikeNewDepreciate();
        }
    }

    @Override
    public void updateAvailability() {
        if (bookState == BookState.AVAILABLE) {
            bookState = BookState.SOLD_OUT;
        } else if (bookState == BookState.SOLD_OUT) {
            bookState = BookState.AVAILABLE;
        }
    }

    @Override
    public String toString() {
        return String.format("(ID: %d; ISBN: %s; Authors: %s; Title: %s; Edition: %s; Publisher: %s; Condition: %s; State: %s; Price: %.2f)",
                id,
                isbn == null ? "" : isbn,
                authors == null ? "" : authors,
                title == null ? "" : title,
                publisher == null ? "" : publisher,
                bookCondition == null ? "" : bookCondition.toString(),
                bookState == null ? "" : bookState.toString(),
                price
        );
    }
}