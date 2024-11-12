package com.cse687.zirui.bookstore.adapter;
import com.cse687.zirui.bookstore.domain.model.Book;
import com.cse687.zirui.bookstore.domain.model.BookState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByState(BookState state);
}
