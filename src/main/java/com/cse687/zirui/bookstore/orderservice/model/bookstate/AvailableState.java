package com.cse687.zirui.bookstore.orderservice.model.bookstate;
import com.cse687.zirui.bookstore.orderservice.model.Book;

public class AvailableState implements BookState {
    private Book book;

    public AvailableState(Book book) {
        this.book = book;
    }

    @Override
    public void sell() {
        this.book.setState(book.getSoldState());
    }

    @Override
    public void buy() {
        throw new UnsupportedOperationException("Book already available");
    }

    @Override
    public void reserve() {
        this.book.setState(book.getReservedState());
    }

    @Override
    public void cancelReserve() {
        throw new UnsupportedOperationException("Cannot cancel reservation on available book");
    }
}
