package com.cse687.zirui.bookstore.orderservice.model.bookstate;
import com.cse687.zirui.bookstore.orderservice.model.Book;

public class AvailableState implements BookState {
    private Book book;

    public AvailableState(Book book) {
        this.book = book;
    }

    @Override
    public void userSell() { throw new IllegalStateException("Book in inventory"); }

    @Override
    public void userBuy() { this.book.setState(book.getSoldState()); }

    @Override
    public void reserve() { this.book.setState(book.getReservedState()); }

    @Override
    public void cancelReserve() { throw new IllegalStateException("No reservation to cancel"); }

    @Override
    public String getCode() {
        return "AVAILABLE";
    }
}
