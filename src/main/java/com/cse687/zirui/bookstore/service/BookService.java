package com.cse687.zirui.bookstore.service;
import com.cse687.zirui.bookstore.adapter.BookRepository;
import com.cse687.zirui.bookstore.domain.event.*;
import com.cse687.zirui.bookstore.domain.model.Book;
import com.cse687.zirui.bookstore.domain.model.BookStatus;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepo;
    private final MessageBus messageBus;
    private Map<Long, Book> availableBooks;
    private Map<Long, Book> unavailableBooks;

    @Autowired
    public BookService(BookRepository bookRepo, MessageBus messageBus) {
        this.bookRepo = bookRepo;
        this.messageBus = messageBus;
    }

    @PostConstruct
    public void init() {
        List<Book> available = bookRepo.findByStatus(BookStatus.AVAILABLE);
        List<Book> unavailable = bookRepo.findByStatus(BookStatus.SOLD);
        availableBooks = new HashMap<>();
        unavailableBooks = new HashMap<>();
        for (Book book : available) {
            availableBooks.put(book.getId(), book);
        }
        for (Book book : unavailable) {
            unavailableBooks.put(book.getId(), book);
        }
    }

    public List<Book> getAvailableBooks() {
        return new ArrayList<>(availableBooks.values());
    }

    public Optional<Book> searchByISBN(String isbn) {
        for (Book cur: availableBooks.values()) {
            if (cur.getISBN().equals(isbn)) {
                return Optional.of(cur);
            }
        }
        for (Book cur: unavailableBooks.values()) {
            if (cur.getISBN().equals(isbn)) {
                return Optional.of(cur);
            }
        }
        return Optional.empty();
    }

    public Book searchById(Long bookId) {
        Book bk1 = availableBooks.getOrDefault(bookId, null);
        if (bk1 != null) {
            return bk1;
        }
        Book bk2 = unavailableBooks.getOrDefault(bookId, null);
        if (bk2 != null) {
            return bk2;
        }
        return null;
    }

    public void buyNewBook(String isbn, Long customerId, float price) {
        messageBus.publishEvent(new NewBookBought(isbn, customerId, price));
    }

    @Transactional
    public Long newBookBought(String isbn, float price){
        Optional<Book> sameIsbnBook = searchByISBN(isbn);
        Book newBook;
        if (sameIsbnBook.isPresent()) {
            Book book = sameIsbnBook.get();
            newBook = new Book(
                    book.getISBN(),
                    book.getAuthors(),
                    book.getTitle(),
                    book.getEdition(),
                    price,
                    1
            );
        } else {
            newBook = new Book(isbn, "", "", "", price, 1);
        }
        bookRepo.save(newBook);
        availableBooks.put(newBook.getId(), newBook);
        return newBook.getId();
    }

    public void buyUsedBook(Long bookId, Long customerId) {
        if (bookId == null || customerId == null) return;
        if (!availableBooks.containsKey(bookId) && unavailableBooks.containsKey(bookId)) {
            messageBus.publishEvent(new UsedBookBought(bookId, customerId));
        } else {
            messageBus.publishEvent(new DuplicateBookFound(bookId));
        }
    }

    @Transactional
    public float usedBookBought(Long bookId) {
        Book book = unavailableBooks.get(bookId);
        book.depreciation();
        book.flipStatus();
        bookRepo.save(book);
        availableBooks.put(bookId, book);
        unavailableBooks.remove(bookId);
        return book.getPrice();
    }

    public void sellBook(Long bookId, Long customerId) {
        if (bookId == null || customerId == null) return;
        if (availableBooks.containsKey(bookId)) {
            messageBus.publishEvent(new BookSold(bookId, customerId));
        } else {
            messageBus.publishEvent(new OutOfStock(bookId));
        }
    }

    @Transactional
    public float bookSold(Long bookId) {
        Book book = availableBooks.get(bookId);
        book.flipStatus();
        bookRepo.save(book);
        unavailableBooks.put(bookId, book);
        availableBooks.remove(bookId);
        return book.getPrice();
    }
}