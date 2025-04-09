package com.cse687.zirui.bookstore.authservice.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Producer {
    private RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

}