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
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue buyBookQueue() {
        return new Queue(OrderQueueName.BUY_BOOK.getName(), true);
    }

    @Bean
    public Binding buyBookBinding(Queue buyBookQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(buyBookQueue)
                .to(orderExchange).with(OrderRoutingKey.BUY_BOOK.getKey());
    }

    @Bean
    public Queue bookBoughtQueue() {
        return new Queue(OrderQueueName.BOOK_BOUGHT.getName(), true);
    }

    @Bean
    public Binding bookBoughtBinding(Queue bookBoughtQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(bookBoughtQueue)
                .to(orderExchange).with(OrderRoutingKey.BOOK_BOUGHT.getKey());
    }

    @Bean
    public Queue sellBookQueue() {
        return new Queue(OrderQueueName.SELL_BOOK.getName(), true);
    }

    @Bean
    public Binding sellBookBinding(Queue sellBookQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(sellBookQueue)
                .to(orderExchange).with(OrderRoutingKey.SELL_BOOK.getKey());
    }

    @Bean
    public Queue bookSoldQueue() {
        return new Queue(OrderQueueName.BOOK_SOLD.getName(), true);
    }

    @Bean
    public Binding bookSoldBinding(Queue bookSoldQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(bookSoldQueue)
                .to(orderExchange).with(OrderRoutingKey.BOOK_SOLD.getKey());
    }

    @Bean
    public Queue reserveBookQueue() {
        return new Queue(OrderQueueName.RESERVE_BOOK.getName(), true);
    }

    @Bean
    public Binding reserveBookBinding(Queue reserveBookQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(reserveBookQueue)
                .to(orderExchange).with(OrderRoutingKey.RESERVE_BOOK.getKey());
    }

    @Bean
    public Queue bookReservedQueue() {
        return new Queue(OrderQueueName.BOOK_RESERVED.getName(), true);
    }

    @Bean
    public Binding bookReservedBinding(Queue bookReservedQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(bookReservedQueue)
                .to(orderExchange).with(OrderRoutingKey.BOOK_RESERVED.getKey());
    }

    @Bean
    public Queue cancelReserveQueue() {
        return new Queue(OrderQueueName.CANCEL_BOOK_RESERVE.getName(), true);
    }

    @Bean
    public Binding cancelReserveBinding(Queue cancelReserveQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(cancelReserveQueue)
                .to(orderExchange).with(OrderRoutingKey.CANCEL_BOOK_RESERVE.getKey());
    }

    @Bean
    public Queue reserveCancelledQueue() {
        return new Queue(OrderQueueName.BOOK_RESERVE_CANCELLED.getName(), true);
    }

    @Bean
    public Binding bindReserveCancelled(Queue reserveCancelledQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(reserveCancelledQueue)
                .to(orderExchange).with(OrderRoutingKey.BOOK_RESERVE_CANCELLED.getKey());
    }

    @Bean
    public Queue stockBookQueue() {
        return new Queue(OrderQueueName.STOCK_BOOK.getName(), true);
    }

    @Bean
    public Binding stockBookBinding(Queue stockBookQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(stockBookQueue)
                .to(orderExchange).with(OrderRoutingKey.STOCK_BOOK.getKey());
    }

    @Bean
    public Queue addCartItemQueue() {
        return new Queue(OrderQueueName.ADD_ITEM.getName(), true);
    }

    @Bean
    public Binding addCartItemBinding(Queue addCartItemQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(addCartItemQueue)
                .to(orderExchange).with(OrderRoutingKey.ADD_ITEM.getKey());
    }

    @Bean
    public Queue removeCartItemQueue() {
        return new Queue(OrderQueueName.REMOVE_ITEM.getName(), true);
    }

    @Bean
    public Binding removeCartItemBinding(Queue removeCartItemQueue,DirectExchange orderExchange) {
        return BindingBuilder.bind(removeCartItemQueue)
                .to(orderExchange).with(OrderRoutingKey.REMOVE_ITEM.getKey());
    }

    @Bean
    public Queue processPaymentQueue() {
        return new Queue(OrderQueueName.PROCESS_PAYMENT.getName(), true);
    }

    @Bean
    public Binding processPaymentBinding(Queue processPaymentQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(processPaymentQueue)
                .to(orderExchange).with(OrderRoutingKey.PROCESS_PAYMENT.getKey());
    }

    @Bean
    public Queue addCreditQueue() {
        return new Queue(OrderQueueName.ADD_CREDIT.getName(), true);
    }

    @Bean
    public Binding addCreditBinding(Queue addCreditQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(addCreditQueue)
                .to(orderExchange).with(OrderRoutingKey.ADD_CREDIT.getKey());
    }
}