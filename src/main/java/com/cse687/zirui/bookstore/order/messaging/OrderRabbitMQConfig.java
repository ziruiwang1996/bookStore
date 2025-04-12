package com.cse687.zirui.bookstore.order.messaging;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRabbitMQConfig {
    public static final String EXCHANGE = "order.exchange";

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(EXCHANGE);
    }

    // === QUEUES & BINDINGS ===
    @Bean
    public Queue buyBookQueue() {
        return new Queue(RoutingKey.BUY_BOOK.getKey(), true);
    }
    @Bean
    public Binding bindBuyBook(Queue buyBookQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(buyBookQueue).to(orderExchange).with(RoutingKey.BUY_BOOK.getKey());
    }

    @Bean
    public Queue sellBookQueue() {
        return new Queue(RoutingKey.SELL_BOOK.getKey(), true);
    }
    @Bean
    public Binding bindSellBook(Queue sellBookQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(sellBookQueue).to(orderExchange).with(RoutingKey.SELL_BOOK.getKey());
    }

    @Bean
    public Queue reserveBookQueue() {
        return new Queue(RoutingKey.RESERVE_BOOK.getKey(), true);
    }

    @Bean
    public Binding bindReserveBook(Queue reserveBookQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(reserveBookQueue).to(orderExchange).with(RoutingKey.RESERVE_BOOK.getKey());
    }

    @Bean
    public Queue cancelReserveQueue() {
        return new Queue(RoutingKey.CANCEL_BOOK_RESERVE.getKey(), true);
    }

    @Bean
    public Binding bindCancelReserve(Queue cancelReserveQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(cancelReserveQueue).to(orderExchange).with(RoutingKey.CANCEL_BOOK_RESERVE.getKey());
    }

    @Bean
    public Queue stockBookQueue() {
        return new Queue(RoutingKey.STOCK_BOOK.getKey(), true);
    }

    @Bean
    public Binding bindStockBook(Queue stockBookQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(stockBookQueue).to(orderExchange).with(RoutingKey.STOCK_BOOK.getKey());
    }

    @Bean
    public Queue bookBoughtQueue() {
        return new Queue(RoutingKey.BOOK_BOUGHT.getKey(), true);
    }

    @Bean
    public Binding bindBookBought(Queue bookBoughtQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(bookBoughtQueue).to(orderExchange).with(RoutingKey.BOOK_BOUGHT.getKey());
    }

    @Bean
    public Queue bookSoldQueue() {
        return new Queue(RoutingKey.BOOK_SOLD.getKey(), true);
    }

    @Bean
    public Binding bindBookSold(Queue bookSoldQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(bookSoldQueue).to(orderExchange).with(RoutingKey.BOOK_SOLD.getKey());
    }

    @Bean
    public Queue bookReservedQueue() {
        return new Queue(RoutingKey.BOOK_RESERVED.getKey(), true);
    }

    @Bean
    public Binding bindBookReserved(Queue bookReservedQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(bookReservedQueue).to(orderExchange).with(RoutingKey.BOOK_RESERVED.getKey());
    }

    @Bean
    public Queue reserveCancelledQueue() {
        return new Queue(RoutingKey.BOOK_RESERVE_CANCELLED.getKey(), true);
    }

    @Bean
    public Binding bindReserveCancelled(Queue reserveCancelledQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(reserveCancelledQueue).to(orderExchange).with(RoutingKey.BOOK_RESERVE_CANCELLED.getKey());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}