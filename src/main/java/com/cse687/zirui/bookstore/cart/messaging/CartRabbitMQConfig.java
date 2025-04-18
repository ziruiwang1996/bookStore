package com.cse687.zirui.bookstore.cart.messaging;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartRabbitMQConfig {
    public static final String EXCHANGE = "cart.exchange";

    @Bean
    public MessageConverter messageConverter() {return new Jackson2JsonMessageConverter();}

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange cartExchange() { return new DirectExchange(EXCHANGE); }

    @Bean
    public Queue addItemQueue() {
        return new Queue(CartQueueName.ADD_ITEM.getName(), true);
    }

    @Bean
    public Binding addItemBinding(Queue addItemQueue, DirectExchange cartExchange) {
        return BindingBuilder.bind(addItemQueue)
                .to(cartExchange).with(CartRoutingKey.ADD_ITEM.getKey());
    }

    @Bean
    public Queue itemAddedQueue() {
        return new Queue(CartQueueName.ITEM_ADDED.getName(), true);
    }

    @Bean
    public Binding itemAddedBinding(Queue itemAddedQueue, DirectExchange cartExchange) {
        return BindingBuilder.bind(itemAddedQueue)
                .to(cartExchange).with(CartRoutingKey.ITEM_ADDED.getKey());
    }

    @Bean
    public Queue removeItemQueue() {
        return new Queue(CartQueueName.REMOVE_ITEM.getName(), true);
    }

    @Bean
    public Binding removeItemBinding(Queue removeItemQueue, DirectExchange cartExchange) {
        return BindingBuilder.bind(removeItemQueue)
                .to(cartExchange).with(CartRoutingKey.REMOVE_ITEM.getKey());
    }

    @Bean
    public Queue itemRemovedQueue() {
        return new Queue(CartQueueName.ITEM_REMOVED.getName(), true);
    }

    @Bean
    public Binding itemRemovedBinding(Queue itemRemovedQueue, DirectExchange cartExchange) {
        return BindingBuilder.bind(itemRemovedQueue)
                .to(cartExchange).with(CartRoutingKey.ITEM_REMOVED.getKey());
    }

    @Bean
    public Queue placeOrderQueue() {
        return new Queue(CartQueueName.PLACE_ORDER.getName(), true);
    }

    @Bean
    public Binding placeOrderBinding(Queue placeOrderQueue, DirectExchange cartExchange) {
        return BindingBuilder.bind(placeOrderQueue)
                .to(cartExchange).with(CartRoutingKey.PLACE_ORDER.getKey());
    }

    @Bean
    public Queue orderPlacedQueue() {
        return new Queue(CartQueueName.ORDER_PLACED.getName(), true);
    }

    @Bean
    public Binding orderPlacedBinding(Queue orderPlacedQueue, DirectExchange cartExchange) {
        return BindingBuilder.bind(orderPlacedQueue)
                .to(cartExchange).with(CartRoutingKey.ORDER_PLACED.getKey());
    }
}
