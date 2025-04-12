package com.cse687.zirui.bookstore.order.domain.model.bookstate;
import com.cse687.zirui.bookstore.order.domain.model.Book;

public class SoldState implements BookState {
    private final Book book;

    public SoldState(Book book) {
        this.book = book;
    }

    @Override
    public void userBuy() {
        throw new IllegalStateException("Book sold");
    }

    @Override
    public void userSell() {
        this.book.setState(book.getAvailableState());
    }

    @Override
    public void reserve() {
        throw new IllegalStateException("Book sold");
    }

    @Override
    public void cancelReserve() {
        throw new IllegalStateException("Book sold");
    }

    @Override
    public String getCode() {
        return "SOLD";
    }
}

