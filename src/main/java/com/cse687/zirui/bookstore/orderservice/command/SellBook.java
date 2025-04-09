package com.cse687.zirui.bookstore.orderservice.command;

public record SellBook(String isbn,
                       Long bookId,
                       Long userId,
                       String condition,
                       float price,
                       boolean paperBook)
        implements Command {}
