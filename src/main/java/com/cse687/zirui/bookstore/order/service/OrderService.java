package com.cse687.zirui.bookstore.order.service;
import com.cse687.zirui.bookstore.order.domain.command.*;
import com.cse687.zirui.bookstore.order.domain.event.*;
import com.cse687.zirui.bookstore.order.domain.model.*;
import com.cse687.zirui.bookstore.order.messaging.OrderProducer;
import com.cse687.zirui.bookstore.order.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
            if (book.getStateCode().equals("SOLD")) {
                throw new IllegalStateException("Book NOT AVAILABLE");
            }
            if (book.getStateCode().equals("RESERVED")) {
                if (!Objects.equals(cmd.userId(), book.getReservedBy())) {
                    throw new IllegalStateException("Book RESERVED");
                }
            }
        });
    }

    public void bookBought(BookBought evt) {
        bookRepo.findById(evt.bookId()).ifPresent(book -> {
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
        float actualPrice = evt.price();
        if (evt.bookId() == null && evt.isbn() == null) {
            throw new IllegalArgumentException("Must provide either book ID or ISBN");
        }
        if (evt.bookId() != null) {
            Book book = bookRepo.findById(evt.bookId())
                    .orElseThrow(() -> new IllegalArgumentException("Book with ID " + evt.bookId() + " not found"));
            book.sell();
            if (book instanceof PaperBook pb) {
                pb.setCondition(evt.condition());
                pb.updatePrice();
                actualPrice = pb.getPrice();
            }
            bookRepo.save(book);
        if (evt.isbn() != null) {
            addBookByISBN(evt.isbn(), evt.paperBook(), evt.price(), evt.condition());
        }
        producer.publishAddCreditIfMemberCmd(
                new AddCreditIfMember(evt.userId(), actualPrice)
        );
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
        producer.publishAddItemCmd(new AddItem(evt.bookId(), evt.userId()));
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

    public void reserveCancelled(BookReserveCancelled evt) {
        Book book = bookRepo.findById(evt.bookId())
                .orElseThrow(() -> new IllegalArgumentException("Book with ID " + evt.bookId() + " not found"));
        book.cancelReserve();
        bookRepo.save(book);
        producer.publishRemoveItemCmd(new RemoveItem(evt.bookId(), evt.userId()));
    }

    public void stockBook(StockBook cmd) {
        if (cmd.isbn() != null) {
            addBookByISBN(cmd.isbn(), cmd.paperBook(), cmd.price(), cmd.condition());
        }
    }



    public void orderPlaced(OrderPlaced evt) {
        Long userId = evt.userId();
        List<Long> books = evt.items();
        float total = 0;
        for (Long bookId : books) {
            try {
                BuyBook cmd = new BuyBook(bookId, userId);
                producer.publishBuyBookCmd(cmd);
                Book bk = bookRepo.findById(bookId).get();
                total += bk.getPrice();
            } catch (Exception e) {
                System.out.println("Book " + bookId + " purchase  failed: " + e.getMessage());
                books.remove(bookId);
            }
        }
        Long orderId = Objects.hash(books) + userId;
        producer.publishProcessPaymentCmd(
                new ProcessPayment(orderId, userId, total, books)
        );
    }


    public void paymentSucceeded(PaymentSucceeded evt) {
        for (Long bookId : evt.items()) {
            producer.publishBookBoughtEvt(
                    new BookBought(bookId, evt.userId())
            );
        }
        System.out.println("Order"+ evt.orderId() + "Payment Succeeded");
    }

    public void paymentFailed(PaymentFailed evt) {
        System.out.println("Order"+ evt.orderId() + " Payment Failed: " + evt.message());
    }
}
