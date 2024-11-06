package com.cse687.zirui.bookstore.adapter;
import com.cse687.zirui.bookstore.domain.model.Book;
import com.cse687.zirui.bookstore.domain.model.BookStatus;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByStatus(BookStatus status);
}
