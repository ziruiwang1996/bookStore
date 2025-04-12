package com.cse687.zirui.bookstore.order.messaging;
import com.cse687.zirui.bookstore.order.domain.command.*;
import com.cse687.zirui.bookstore.order.domain.event.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {
    private final RabbitTemplate rabbitTemplate;
    public static final String EXCHANGE = "order.exchange";

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishBuyBookCmd(BuyBook cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.BUY_BOOK.getKey(), cmd);
    }

    public void publishBookBoughtEvt(BookBought evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.BOOK_BOUGHT.getKey(), evt);
    }

    public void publishSellBookCmd(SellBook cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.SELL_BOOK.getKey(), cmd);
    }

    public void publishBookSoldEvt(BookSold evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.BOOK_SOLD.getKey(), evt);
    }

    public void publishReserveBookCmd(ReserveBook cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.RESERVE_BOOK.getKey(), cmd);
    }

    public void publishBookReservedEvt(BookReserved evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.BOOK_RESERVED.getKey(), evt);
    }

    public void publishCancelBookReserveCmd(CancelBookReserve cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.CANCEL_BOOK_RESERVE.getKey(), cmd);
    }

    public void publishBookReserveCancelledEvt(BookReserveCancelled evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.BOOK_RESERVE_CANCELLED.getKey(), evt);
    }

    public void publishStockBookCmd(StockBook cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.STOCK_BOOK.getKey(), cmd);
    }
}
