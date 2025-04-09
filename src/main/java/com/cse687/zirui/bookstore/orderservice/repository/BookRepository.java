package com.cse687.zirui.bookstore.orderservice.repository;
import com.cse687.zirui.bookstore.orderservice.model.Book;
import com.cse687.zirui.bookstore.orderservice.model.EBook;
import com.cse687.zirui.bookstore.orderservice.model.PaperBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findById(Long id);

    List<Book> findByStateCode(String stateCode);

//    @Query("SELECT p FROM PaperBook p WHERE p.bookState = :state")
//    List<PaperBook> findPaperBooksByBookState(@Param("state") BookState state);
//
//    @Query("SELECT e FROM EBook e WHERE e.bookState = :state")
//    List<EBook> findEBooksByBookState(@Param("state") BookState state);
}