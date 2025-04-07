package com.cse687.zirui.bookstore.services.order.model.bookstate;
import com.cse687.zirui.bookstore.services.order.model.Book;

public class SoldState implements BookState {
    private Book book;

    public SoldState(Book book) {
        this.book = book;
    }

    @Override
    public void sell() {
        throw new UnsupportedOperationException("Book already sold");
    }

    @Override
    public void buy() {
        this.book.setState(book.getAvailableState());
    }

    @Override
    public void reserve() {
        throw new UnsupportedOperationException("Book is sold");
    }

    @Override
    public void cancelReserve() {
        throw new UnsupportedOperationException("Book is sold");
    }
}
