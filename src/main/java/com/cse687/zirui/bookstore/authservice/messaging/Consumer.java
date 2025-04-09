package com.cse687.zirui.bookstore.authservice.messaging;

import com.cse687.zirui.bookstore.orderservice.event.BookBought;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {

//    @RabbitListener(queues = "orderEvtQueue")
//    public void handleBookBoughtEvent(BookBought event) {
//        orderServ.bookBought(event.bookId());
//    }
}
