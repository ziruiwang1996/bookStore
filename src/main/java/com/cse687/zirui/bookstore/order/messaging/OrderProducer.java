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
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.BUY_BOOK.getKey(), cmd);
    }

    public void publishBookBoughtEvt(BookBought evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.BOOK_BOUGHT.getKey(), evt);
    }

    public void publishSellBookCmd(SellBook cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.SELL_BOOK.getKey(), cmd);
    }

    public void publishBookSoldEvt(BookSold evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.BOOK_SOLD.getKey(), evt);
    }

    public void publishReserveBookCmd(ReserveBook cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.RESERVE_BOOK.getKey(), cmd);
    }

    public void publishBookReservedEvt(BookReserved evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.BOOK_RESERVED.getKey(), evt);
    }

    public void publishCancelBookReserveCmd(CancelBookReserve cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.CANCEL_BOOK_RESERVE.getKey(), cmd);
    }

    public void publishBookReserveCancelledEvt(BookReserveCancelled evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.BOOK_RESERVE_CANCELLED.getKey(), evt);
    }

    public void publishStockBookCmd(StockBook cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.STOCK_BOOK.getKey(), cmd);
    }


    public void publishAddItemCmd(AddItem cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.ADD_ITEM.getKey(), cmd);
    }

    public void publishRemoveItemCmd(RemoveItem cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.REMOVE_ITEM.getKey(), cmd);
    }




    public void publishProcessPaymentCmd(ProcessPayment cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.PROCESS_PAYMENT.getKey(), cmd);
    }

    public void publishAddCreditIfMemberCmd(AddCreditIfMember cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, OrderRoutingKey.ADD_CREDIT.getKey(), cmd);
    }
}
