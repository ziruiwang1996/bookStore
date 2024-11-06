package com.cse687.zirui.bookstore.adapter;
import com.cse687.zirui.bookstore.domain.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
