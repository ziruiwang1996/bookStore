package com.cse687.zirui.bookstore.auth.repository;
import com.cse687.zirui.bookstore.auth.domain.model.Customer;
import com.cse687.zirui.bookstore.auth.domain.model.Guest;
import com.cse687.zirui.bookstore.auth.domain.model.Member;
import com.cse687.zirui.bookstore.order.domain.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findById(Long userId);

    @Query("SELECT m FROM customer m WHERE TYPE(m) = Member AND m.email = :email")
    Optional<Member> findMemberByEmail(@Param("email") String email);

    @Query("SELECT m FROM customer m WHERE TYPE(m) = Guest AND m.email = :email")
    Optional<Guest> findGuestByEmail(@Param("email") String email);
}