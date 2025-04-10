package com.cse687.zirui.bookstore.orderservice.model.bookstate;
import com.cse687.zirui.bookstore.orderservice.model.Book;

import java.util.Objects;

public class ReservedState implements BookState {
    private Book book;

    public ReservedState(Book book) {
        this.book = book;
    }

    @Override
    public void userSell() { throw new IllegalStateException("Book in inventory"); }

    @Override
    public void userBuy() { this.book.setState(book.getSoldState()); }

    @Override
    public void reserve() {
        throw new IllegalStateException("Book already reserved");
    }

    @Override
    public void cancelReserve() { this.book.setState(book.getAvailableState()); }

    @Override
    public String getCode() {
        return "RESERVED";
    }
}
