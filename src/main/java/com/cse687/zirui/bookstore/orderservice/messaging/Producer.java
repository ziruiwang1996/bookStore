package com.cse687.zirui.bookstore.orderservice.messaging;

import com.cse687.zirui.bookstore.orderservice.command.BuyBook;
import com.cse687.zirui.bookstore.orderservice.command.SellBook;
import com.cse687.zirui.bookstore.orderservice.event.BookBought;
import com.cse687.zirui.bookstore.orderservice.event.BookSold;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishBuyBookCommand(BuyBook cmd) {
        rabbitTemplate.convertAndSend("orderExchange","book.buy", cmd);
    }

    public void publishBookBoughtEvent(BookBought event) {
        rabbitTemplate.convertAndSend("orderExchange","book.bought", event);
    }

//    public void publishOutOfStockEvent(OutOfStock event) {
//        rabbitTemplate.convertAndSend("orderExchange","book.outOfStock", event);
//    } // move to notification services

    public void publishSellBookCommand(SellBook cmd) {
        rabbitTemplate.convertAndSend("orderExchange","order.sold", cmd);
    }

    public void publishBookSoldEvent(BookSold evt) {
        rabbitTemplate.convertAndSend("orderExchange","book.sold", evt);
    }
}
