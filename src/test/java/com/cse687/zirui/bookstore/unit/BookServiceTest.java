//package com.cse687.zirui.bookstore.unit;
//import com.cse687.zirui.bookstore.adapter.BookRepository;
//import com.cse687.zirui.bookstore.domain.model.Book;
//import com.cse687.zirui.bookstore.service.BookService;
//import com.cse687.zirui.bookstore.service.MessageBus;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//public class BookServiceTest {
//
//    @Mock
//    private BookRepository bookRepo;
//
//    @Mock
//    private MessageBus messageBus;
//
//    @InjectMocks
//    private BookService bookService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testInit() {
//        List<Book> available = List.of(new Book("123", "Author", "Title", "1st", 10.0f, 1));
//        List<Book> unavailable = List.of(new Book("456", "Author", "Title", "1st", 12.0f, 0));
//
//        when(bookRepo.findByStatus("AVAILABLE")).thenReturn(available);
//        when(bookRepo.findByStatus("SOLD")).thenReturn(unavailable);
//
//        bookService.init();
//
//        assertTrue(bookService.getAvailableBooks().containsKey(available.get(0).getId()));
//        assertTrue(bookService.getUnavailableBooks().containsKey(unavailable.get(0).getId()));
//    }
//
//
//    // Tests go here
//}