package com.cse687.zirui.bookstore.cart.domain.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    private Long userId; // one cart per user
    @ElementCollection
    private List<Long> items = new ArrayList<>();

    public Cart() {}

    public Cart(Long userId) {
        this.userId = userId;
    }

    public void add(Long itemId) {
        items.add(itemId);
    }

    public void remove(Long itemId) {
        items.remove(itemId);
    }

    public boolean hasItem(Long itemId) {
        return items.contains(itemId);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<Long> getItems() {
        return items;
    }
}
