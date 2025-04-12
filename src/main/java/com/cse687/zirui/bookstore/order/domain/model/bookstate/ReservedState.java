package com.cse687.zirui.bookstore.order.domain.model.bookstate;
import com.cse687.zirui.bookstore.order.domain.model.Book;

public class ReservedState implements BookState {
    private final Book book;

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
