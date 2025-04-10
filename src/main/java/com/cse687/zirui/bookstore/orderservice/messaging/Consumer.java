package com.cse687.zirui.bookstore.orderservice.messaging;
import com.cse687.zirui.bookstore.orderservice.command.*;
import com.cse687.zirui.bookstore.orderservice.event.BookBought;
import com.cse687.zirui.bookstore.orderservice.event.BookReserveCancelled;
import com.cse687.zirui.bookstore.orderservice.event.BookSold;
import com.cse687.zirui.bookstore.orderservice.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private final OrderService orderServ;

    @Autowired
    public Consumer(OrderService orderServ) {
        this.orderServ = orderServ;
    }

    @RabbitListener(queues = "")
    public void handleBuyBookCommand(BuyBook cmd) {
        orderServ.buyBook(cmd);
    }

    @RabbitListener(queues = "")
    public void handleBookBoughtEvent(BookBought event) {
        orderServ.bookBought(event.bookId());
    }

    @RabbitListener(queues = "")
    public void handleSellBookCommand(SellBook cmd) {
        orderServ.sellBook(cmd);
    }

    @RabbitListener(queues = "")
    public void handleBookSoldEvent(BookSold evt) {
        orderServ.bookSold(evt);
    }

    @RabbitListener(queues = "")
    public void handleReserveBookCommand(ReserveBook cmd) { orderServ.reserveBook(cmd);}

    @RabbitListener(queues = "")
    public void handleBookReservedEvent(BookBought evt) {orderServ.bookReserved(evt); }

    @RabbitListener(queues = "")
    public void handleCancelReserveBookCommand(CancelBookReserve cmd) {orderServ.cancelReserve(cmd); }

    @RabbitListener(queues = "")
    public void handleBookReserveCancelledEvent(BookReserveCancelled evt) {orderServ.reserveCancelled(evt.bookId()); }

    @RabbitListener(queues = "")
    public void handleStockBookCommand(StockBook cmd) {orderServ.stockBook(cmd);}
}


