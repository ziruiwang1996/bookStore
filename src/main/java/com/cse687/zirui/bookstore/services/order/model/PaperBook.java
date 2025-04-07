package com.cse687.zirui.bookstore.services.order.model;
import com.cse687.zirui.bookstore.services.order.model.depreciatestrategy.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class PaperBook extends Book {
    @JsonProperty("condition")
    @Column(name = "book_condition")
    @Enumerated(EnumType.STRING)
    private BookCondition bookCondition;
    @Transient
    private DepreciateStrategy depreciateStrategy;

    public PaperBook() {}

    public PaperBook(String isbn, String authors, String title, String publisher, double price, BookCondition condition) {
        super(isbn, authors, title, publisher, price);
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

    public void updatePrice() {
        this.price *= depreciateStrategy.depreciationRate();
    }

    @Override
    public String toString() {
        return String.format("(ID: %d; ISBN: %s; Authors: %s; Title: %s; Publisher: %s; Condition: %s; Price: %.2f)",
                id,
                isbn == null ? "" : isbn,
                authors == null ? "" : authors,
                title == null ? "" : title,
                publisher == null ? "" : publisher,
                bookCondition == null ? "" : bookCondition.toString(),
                price
        );
    }
}