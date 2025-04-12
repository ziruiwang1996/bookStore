package com.cse687.zirui.bookstore.order.repository;
import com.cse687.zirui.bookstore.order.domain.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findById(Long bookId);
}