package com.cse687.zirui.bookstore.cart.repository;
import com.cse687.zirui.bookstore.cart.domain.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
