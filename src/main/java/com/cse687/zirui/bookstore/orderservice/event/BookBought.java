package com.cse687.zirui.bookstore.orderservice.event;
import com.cse687.zirui.bookstore.orderservice.model.Book;

public record BookBought(Long bookId,
                         Long userId)
        implements Event {}
