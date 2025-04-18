package com.cse687.zirui.bookstore.cart.controller;
import com.cse687.zirui.bookstore.cart.domain.command.*;
import com.cse687.zirui.bookstore.cart.messaging.CartProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cart")
public class CartController {
    private final CartProducer producer;

    @Autowired
    public CartController(CartProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AddItem cmd) {
        try {
            producer.publishAddItemCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(@RequestBody RemoveItem cmd) {
        try {
            producer.publishRemoveItemCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/place_order")
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrder cmd) {
        try {
            producer.publishPlaceOrderCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
