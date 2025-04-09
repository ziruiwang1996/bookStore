package com.cse687.zirui.bookstore.orderservice.controller;
import com.cse687.zirui.bookstore.orderservice.command.*;
import com.cse687.zirui.bookstore.orderservice.messaging.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private Producer producer;

    @GetMapping("/buy")
    public ResponseEntity<?> buyBook(@RequestBody BuyBook cmd) {
        try {
            producer.publishBuyBookCommand(cmd);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/sell")
    public ResponseEntity<?> sellBook(@RequestBody SellBook cmd) {
        try {
            producer.publishSellBookCommand(cmd);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/swap")
    public ResponseEntity<?> swapBook(@RequestBody SwapBook cmd) {
        try {
            producer.punlishSwapBookCommand();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/reserve")
    public ResponseEntity<?> reserveBook(@RequestBody ReserveBook cmd) {
        try {
            producer.publishReserveBookCommand();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cancelreserve")
    public ResponseEntity<?> cancelReserveBook(@RequestBody CancelReserveBook cmd) {
        try {
            producer.publishCancelReserveBookCommand();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("restock")
    public ResponseEntity<?> stockBook(@RequestBody StockBook cmd) {
        try {
            producer.publishStockBookCommand();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
