package com.cse687.zirui.bookstore.order.service;
import com.cse687.zirui.bookstore.order.domain.command.*;
import com.cse687.zirui.bookstore.order.domain.event.*;
import com.cse687.zirui.bookstore.order.domain.model.Book;
import com.cse687.zirui.bookstore.order.domain.model.EBook;
import com.cse687.zirui.bookstore.order.domain.model.PaperBook;
import com.cse687.zirui.bookstore.order.messaging.OrderProducer;
import com.cse687.zirui.bookstore.order.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Objects;

@Service
public class OrderService {
    private final BookRepository bookRepo;
    private final OrderProducer producer;

    @Autowired
    public OrderService(BookRepository bookRepo, OrderProducer producer) {
        this.bookRepo = bookRepo;
        this.producer = producer;
    }

    public void addBookByISBN(String isbn, boolean paperBook, float price, String condition) {
        Map<String, String> bookInfo = BookFetcher.getBook(isbn);
        Book book;
        if (paperBook) {
            book = new PaperBook(
                    isbn,
                    bookInfo.get("authors"),
                    bookInfo.get("title"),
                    bookInfo.get("publisher"),
                    price,
                    condition
            );
        } else {
            book = new EBook(
                    isbn,
                    bookInfo.get("authors"),
                    bookInfo.get("title"),
                    bookInfo.get("publisher"),
                    price
            );
        }
        bookRepo.save(book);
    }

    public void buyBook(BuyBook cmd) {
        bookRepo.findById(cmd.bookId()).ifPresent(book -> {
            if (book.getStateCode().equals("AVAILABLE")) {
                BookBought evt = new BookBought(cmd.bookId(), cmd.userId());
                producer.publishBookBoughtEvt(evt);
            } else if (book.getStateCode().equals("RESERVED")) {
                if (Objects.equals(cmd.userId(), book.getReservedBy())) {
                    BookBought evt = new BookBought(cmd.bookId(), cmd.userId());
                    producer.publishBookBoughtEvt(evt);
                } else {
                    throw new IllegalStateException("Book RESERVED");
                }
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
                cmd.userId(),
                cmd.isbn(),
                cmd.condition(),
                cmd.paperBook(),
                cmd.price()
        );
        if (cmd.bookId() != null) {
            Book book = bookRepo.findById(cmd.bookId())
                    .orElseThrow(() -> new IllegalArgumentException("Book with ID " + cmd.bookId() + " not found"));
            switch (book.getStateCode()) {
                case "AVAILABLE", "RESERVED" -> throw new IllegalStateException("Book in inventory");
                case "SOLD" -> producer.publishBookSoldEvt(evt);
                default -> throw new IllegalStateException("Unexpected book state: " + book.getStateCode());
            }
        } else if (cmd.isbn() != null) {
            producer.publishBookSoldEvt(evt);
        } else {
            throw new IllegalArgumentException("Must provide either book ID or ISBN");
        }
    }

    public void bookSold(BookSold evt) {
        if (evt.bookId() != null) {
            Book book = bookRepo.findById(evt.bookId())
                    .orElseThrow(() -> new IllegalArgumentException("Book with ID " + evt.bookId() + " not found"));
            book.sell();
            if (book instanceof PaperBook pb) {
                pb.setCondition(evt.condition());
                pb.updatePrice();
            }
            bookRepo.save(book);
        } else if (evt.isbn() != null) {
            addBookByISBN(evt.isbn(), evt.paperBook(), evt.price(), evt.condition());
        } else {
            throw new IllegalArgumentException("Must provide either book ID or ISBN");
        }
    }

    public void reserveBook(ReserveBook cmd) {
        Book book = bookRepo.findById(cmd.bookId())
                .orElseThrow(() -> new IllegalArgumentException("Book with ID " + cmd.bookId() + " not found"));
        if (book.getStateCode().equals("AVAILABLE")) {
            BookReserved evt = new BookReserved(cmd.bookId(), cmd.userId());
            producer.publishBookReservedEvt(evt);
        } else {
            throw new IllegalStateException("Book NOT AVAILABLE or RESERVED");
        }
    }

    public void bookReserved(BookReserved evt) {
        Book book = bookRepo.findById(evt.bookId())
                .orElseThrow(() -> new IllegalArgumentException("Book with ID " + evt.bookId() + " not found"));
        book.reserve(evt.userId());
        bookRepo.save(book);
    }

    public void cancelReserve(CancelBookReserve cmd) {
        Book book = bookRepo.findById(cmd.bookId())
                .orElseThrow(() -> new IllegalArgumentException("Book with ID " + cmd.bookId() + " not found"));
        if (book.getStateCode().equals("RESERVED")) {
            if (Objects.equals(cmd.userId(), book.getReservedBy())) {
                BookReserveCancelled evt = new BookReserveCancelled(cmd.bookId(), cmd.userId());
                producer.publishBookReserveCancelledEvt(evt);
            } else {
                throw new IllegalStateException("Book RESERVED by another user");
            }
        } else {
            throw new IllegalStateException("Book NOT RESERVED");
        }
    }

    public void reserveCancelled(Long bookId) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book with ID " + bookId + " not found"));
        book.cancelReserve();
        bookRepo.save(book);
    }

    public void stockBook(StockBook cmd) {
        if (cmd.isbn() != null) {
            addBookByISBN(cmd.isbn(), cmd.paperBook(), cmd.price(), cmd.condition());
        }
    }
}
