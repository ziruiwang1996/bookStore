package com.cse687.zirui.bookstore.services.order.model.bookstate;
import com.cse687.zirui.bookstore.services.order.model.Book;

public class ReservedState implements BookState {
    private Book book;

    public ReservedState(Book book) {
        this.book = book;
    }

    @Override
    public void sell() {
        this.book.setState(book.getSoldState());
    }

    @Override
    public void buy() {
        throw new UnsupportedOperationException("Book already available and is reserved");
    }

    @Override
    public void reserve() {
        throw new UnsupportedOperationException("Book already reserved");
    }

    @Override
    public void cancelReserve() {
        this.book.setState(book.getAvailableState());
    }
}
