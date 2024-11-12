package com.cse687.zirui.bookstore.adapter;
import com.cse687.zirui.bookstore.domain.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {}
