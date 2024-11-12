package com.cse687.zirui.bookstore.controller;
import com.cse687.zirui.bookstore.domain.command.BuyNewBook;
import com.cse687.zirui.bookstore.domain.command.BuyUsedBook;
import com.cse687.zirui.bookstore.domain.command.SellBook;
import com.cse687.zirui.bookstore.domain.model.Book;
import com.cse687.zirui.bookstore.service.BookService;
import com.cse687.zirui.bookstore.service.MessageBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookServ;
    private final MessageBus messageBus;

    @Autowired
    public BookController(BookService bookService, MessageBus messageBus) {
        this.bookServ = bookService;
        this.messageBus = messageBus;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam(value = "query", required = false) String query) {
        try {
            if (query != null && !query.isEmpty()) {
                return ResponseEntity.ok(bookServ.searchBook(query));
            } else {
                return ResponseEntity.ok(bookServ.getAvailableBooks());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/sellnew")
    public ResponseEntity<String> userSellNewBook(@RequestBody BuyNewBook cmd) {
        try {
            messageBus.publishCommand(cmd);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed: " + e.getMessage());
        }
    }

    @PostMapping("/sellused")
    public ResponseEntity<String> userSellUsedBook(@RequestBody BuyUsedBook cmd) {
        try {
            messageBus.publishCommand(cmd);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed: " + e.getMessage());
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<String> userBuyBook(@RequestBody SellBook cmd) {
        try {
            messageBus.publishCommand(cmd);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed: " + e.getMessage());
        }
    }
}
