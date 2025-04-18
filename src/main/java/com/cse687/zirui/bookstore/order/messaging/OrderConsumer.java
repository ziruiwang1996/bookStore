package com.cse687.zirui.bookstore.order.messaging;
import com.cse687.zirui.bookstore.order.domain.command.*;
import com.cse687.zirui.bookstore.order.domain.event.*;
import com.cse687.zirui.bookstore.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {
    private final OrderService orderServ;
    public static final String BUY_BOOK = "order.queue.buy";
    public static final String SELL_BOOK = "order.queue.sell";
    public static final String RESERVE_BOOK = "order.queue.reserve";
    public static final String CANCEL_BOOK_RESERVE = "order.queue.cancelReserve";
    public static final String STOCK_BOOK = "order.queue.stock";
    public static final String BOOK_BOUGHT = "order.queue.bought";
    public static final String BOOK_SOLD = "order.queue.sold";
    public static final String BOOK_RESERVED = "order.queue.reserved";
    public static final String BOOK_RESERVE_CANCELLED = "order.queue.reserveCancelled";

    public static final String ORDER_PLACED = "cart.queue.orderPlaced"; // from cart service
    public static final String PAYMENT_SUCCEEDED = "payment.queue.succeeded"; // from payment service
    public static final String PAYMENT_FAILED = "payment.queue.failed"; // from payment service

    @Autowired
    public OrderConsumer(OrderService orderServ) { this.orderServ = orderServ; }

    @RabbitListener(queues = BUY_BOOK)
    public void handleBuyBookCmd(BuyBook cmd) { orderServ.buyBook(cmd); }

    @RabbitListener(queues = BOOK_BOUGHT)
    public void handleBookBoughtEvt(BookBought evt) { orderServ.bookBought(evt); }

    @RabbitListener(queues = SELL_BOOK)
    public void handleSellBookCmd(SellBook cmd) { orderServ.sellBook(cmd); }

    @RabbitListener(queues = BOOK_SOLD)
    public void handleBookSoldEvt(BookSold evt) { orderServ.bookSold(evt); }

    @RabbitListener(queues = RESERVE_BOOK)
    public void handleReserveBookCmd(ReserveBook cmd) { orderServ.reserveBook(cmd); }

    @RabbitListener(queues = BOOK_RESERVED)
    public void handleBookReservedEvt(BookReserved evt) { orderServ.bookReserved(evt); }

    @RabbitListener(queues = CANCEL_BOOK_RESERVE)
    public void handleCancelBookReserveCmd(CancelBookReserve cmd) { orderServ.cancelReserve(cmd); }

    @RabbitListener(queues = BOOK_RESERVE_CANCELLED)
    public void handleBookReserveCancelledEvt(BookReserveCancelled evt) { orderServ.reserveCancelled(evt); }

    @RabbitListener(queues = STOCK_BOOK)
    public void handleStockBookCmd(StockBook cmd) { orderServ.stockBook(cmd);}

    @RabbitListener(queues = ORDER_PLACED)
    public void handleOrderPlacedEvt(OrderPlaced evt) {
        orderServ.orderPlaced(evt);
    }

    @RabbitListener(queues = PAYMENT_SUCCEEDED)
    public void handlePaymentSucceededEvt(PaymentSucceeded evt) { orderServ.paymentSucceeded(evt); }

    @RabbitListener(queues = PAYMENT_FAILED)
    public void handlePaymentFailedEvt(PaymentFailed evt) { orderServ.paymentFailed(evt); }
}


