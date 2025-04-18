package com.cse687.zirui.bookstore.cart.service;
import com.cse687.zirui.bookstore.cart.domain.command.*;
import com.cse687.zirui.bookstore.cart.domain.event.*;
import com.cse687.zirui.bookstore.cart.domain.model.Cart;
import com.cse687.zirui.bookstore.cart.messaging.CartProducer;
import com.cse687.zirui.bookstore.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepo;
    private final CartProducer producer;

    @Autowired
    public CartService(CartRepository cartRepo, CartProducer producer) {
        this.cartRepo = cartRepo;
        this.producer = producer;
    }

    public void addItem(AddItem cmd) {
        Cart cart = cartRepo.findByUserId(cmd.userId())
                .orElseThrow(() -> new IllegalStateException("Cart not found"));
        if (!cart.hasItem(cmd.itemId())) {
            producer.publishItemAddedEvt(new ItemAdded(cmd.userId(), cmd.itemId()));
        }
    }

    public void itemAdded(ItemAdded evt) {
        cartRepo.findByUserId(evt.userId()).ifPresent(cart -> {
            cart.add(evt.itemId());
            cartRepo.save(cart);
        });
    }

    public void removeItem(RemoveItem cmd) {
        Cart cart = cartRepo.findByUserId(cmd.userId())
                .orElseThrow(() -> new IllegalStateException("Cart not found"));
        if (cart.hasItem(cmd.itemId())) {
            producer.publishItemRemovedEvt(new ItemRemoved(cmd.userId(), cmd.itemId()));
        }
    }

    public void itemRemoved(ItemRemoved evt) {
        cartRepo.findByUserId(evt.userId()).ifPresent(cart -> {
            cart.remove(evt.itemId());
            cartRepo.save(cart);
        });
    }

    public void createCart(CreateCart cmd) {
        if (cartRepo.findByUserId(cmd.userId()).isEmpty()) {
            Cart cart = new Cart(cmd.userId());
            cartRepo.save(cart);
        }
    }

    public void deleteCart(DeleteCart cmd) {
        if (cartRepo.findByUserId(cmd.userId()).isPresent()) {
            cartRepo.deleteById(cmd.userId());
        }
    }

    public void placeOrder(PlaceOrder cmd) {
        Cart cart = cartRepo.findByUserId(cmd.userId())
                .orElseThrow(() -> new IllegalStateException("Cart not found"));
        if (!cart.isEmpty()) {
            producer.publishOrderPlacedEvt(
                    new OrderPlaced(cmd.userId(), cart.getItems())
            );
        }
    }
}
