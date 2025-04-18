package com.cse687.zirui.bookstore.order.domain.model;
import com.cse687.zirui.bookstore.order.domain.model.depreciate.DepreciationCondition;
import com.cse687.zirui.bookstore.order.domain.model.depreciate.DepreciationStrategy;
import jakarta.persistence.*;

@Entity
public class PaperBook extends Book {
    @Enumerated(EnumType.STRING)
    private BookCondition bookCondition;
    @Transient
    private DepreciationStrategy depreciationStrategy;

    public PaperBook() {}

    public PaperBook(String isbn, String authors, String title, String publisher, float price, String condition) {
        super(isbn, authors, title, publisher, price);
        setCondition(condition);
    }

    public void setCondition(String condition) {
        switch (condition) {
            case "new":
                bookCondition = BookCondition.NEW;
                break;
            case "like_new":
                bookCondition = BookCondition.LIKE_NEW;
                break;
            case "good":
                bookCondition = BookCondition.GOOD;
                break;
            case "fair":
                bookCondition = BookCondition.FAIR;
                break;
            default:
                throw new IllegalArgumentException("Invalid book condition: " + condition);
        }
        setDiscountBehavior();
    }

    private void setDiscountBehavior() {
        if (bookCondition == BookCondition.FAIR) {
            depreciationStrategy = DepreciationCondition.FAIR;
        } else if (bookCondition == BookCondition.GOOD) {
            depreciationStrategy = DepreciationCondition.GOOD;
        } else if (bookCondition == BookCondition.LIKE_NEW) {
            depreciationStrategy = DepreciationCondition.LIKE_NEW;
        } else {
            depreciationStrategy = null;
        }
    }

    public void updatePrice() {
        this.price *= depreciationStrategy.depreciationRate();
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

enum BookCondition {NEW, LIKE_NEW, GOOD, FAIR}