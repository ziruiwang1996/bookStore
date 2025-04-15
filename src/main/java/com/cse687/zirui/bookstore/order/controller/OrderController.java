package com.cse687.zirui.bookstore.order.controller;
import com.cse687.zirui.bookstore.order.domain.command.*;
import com.cse687.zirui.bookstore.order.messaging.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private final OrderProducer producer;

    @Autowired
    public OrderController(OrderProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buyBook(@RequestBody BuyBook cmd) {
        try {
            producer.publishBuyBookCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sellBook(@RequestBody SellBook cmd) {
        try {
            producer.publishSellBookCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reserve")
    public ResponseEntity<?> reserveBook(@RequestBody ReserveBook cmd) {
        try {
            producer.publishReserveBookCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cancelreserve")
    public ResponseEntity<?> cancelReserveBook(@RequestBody CancelBookReserve cmd) {
        try {
            producer.publishCancelBookReserveCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("restock")
    public ResponseEntity<?> stockBook(@RequestBody StockBook cmd) {
        try {
            producer.publishStockBookCmd(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
