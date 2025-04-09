package com.cse687.zirui.bookstore.orderservice.messaging;
import com.cse687.zirui.bookstore.orderservice.command.BuyBook;
import com.cse687.zirui.bookstore.orderservice.command.SellBook;
import com.cse687.zirui.bookstore.orderservice.event.BookBought;
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

    @RabbitListener(queues = "orderCmdQueue")
    public void handleBuyBookCommand(BuyBook cmd) {
        orderServ.buyBook(cmd);
    }

    @RabbitListener(queues = "orderEvtQueue")
    public void handleBookBoughtEvent(BookBought event) {
        orderServ.bookBought(event.bookId());
    }

//    @RabbitListener(queues = "orderEvtQueue")
//    public void handleOutOfStockEvent(OutOfStock event) {
//        //handled out of order situation
//    }

    @RabbitListener(queues = "orderCmdQueue")
    public void handleSellBookCommand(SellBook cmd) {
        orderServ.sellBook(cmd);
    }

    @RabbitListener(queues = "")
    public void handleBookSoldEvent(BookSold event) {
        orderServ.bookBought(event.bookId());
    }
}
