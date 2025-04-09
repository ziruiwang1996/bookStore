package com.cse687.zirui.bookstore.orderservice.service;
import com.cse687.zirui.bookstore.orderservice.command.BuyBook;
import com.cse687.zirui.bookstore.orderservice.command.SellBook;
import com.cse687.zirui.bookstore.orderservice.event.BookBought;
import com.cse687.zirui.bookstore.orderservice.event.BookSold;
import com.cse687.zirui.bookstore.orderservice.event.OutOfStock;
import com.cse687.zirui.bookstore.orderservice.messaging.Producer;
import com.cse687.zirui.bookstore.orderservice.model.Book;
import com.cse687.zirui.bookstore.orderservice.model.EBook;
import com.cse687.zirui.bookstore.orderservice.model.PaperBook;
import com.cse687.zirui.bookstore.orderservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    private final BookRepository bookRepo;
    private Producer producer;

    @Autowired
    public OrderService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void buyBook(BuyBook cmd) {
        bookRepo.findById(cmd.bookId()).ifPresent(book -> {
            if (book.getStateCode().equals("AVAILABLE")) {
                BookBought evt = new BookBought(cmd.bookId(), cmd.userId());
                producer.publishBookBoughtEvent(evt);
            } else {
                throw new IllegalStateException("Book NOT AVAILABLE");
            }
        });
    }

    public void bookBought(Long bookId) {
        bookRepo.findById(bookId).ifPresent(book -> {
            book.buy();
            bookRepo.save(book);
        });
    }

    public void sellBook(SellBook cmd) {
        BookSold evt = new BookSold(
                cmd.bookId(),
                cmd.isbn(),
                cmd.userId(),
                cmd.condition(),
                cmd.paperBook(),
                cmd.price()
        );
        if (cmd.bookId() != null) {
            Book book = bookRepo.findById(cmd.bookId())
                    .orElseThrow(() -> new IllegalArgumentException("Book with ID " + cmd.bookId() + " not found"));
            switch (book.getStateCode()) {
                case "AVAILABLE", "RESERVED" -> throw new IllegalStateException("Book is already in the database and not sold");
                case "SOLD" -> producer.publishBookSoldEvent(evt);
                default -> throw new IllegalStateException("Unexpected book state: " + book.getStateCode());
            }
        } else if (cmd.isbn() != null) {
            producer.publishBookSoldEvent(evt);
        } else {
            throw new IllegalArgumentException("Must provide either book ID or ISBN");
        }
    }

    public void bookSold(BookSold evt) {
        if (evt.bookId() != null) {
            bookRepo.findById(evt.bookId()).ifPresent(book -> {
                book.sell();
                if (book instanceof PaperBook pb) {
                    pb.setCondition(evt.condition());
                    pb.updatePrice();
                }
                bookRepo.save(book);
            });
        } else if (evt.isbn() != null) {
            Map<String, String> bookInfo = BookFetcher.getBook(evt.isbn());
            Book book;
            if (evt.paperBook()) {
                book = new PaperBook(
                        evt.isbn(),
                        bookInfo.get("authors"),
                        bookInfo.get("titie"),
                        bookInfo.get("publisher"),
                        evt.price(),
                        evt.condition()
                );
            } else {
                book = new EBook(
                        evt.isbn(),
                        bookInfo.get("authors"),
                        bookInfo.get("titie"),
                        bookInfo.get("publisher"),
                        evt.price()
                );
            }
            bookRepo.save(book);
        }
    }
}
