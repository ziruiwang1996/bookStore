package com.cse687.zirui.bookstore.auth.messaging;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthRabbitMQConfig {
    public static final String EXCHANGE = "auth.exchange";

    @Bean
    public MessageConverter messageConverter() {return new Jackson2JsonMessageConverter();}

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange authExchange() { return new DirectExchange(EXCHANGE); }

    @Bean
    public Queue loggedInQueue() {
        return new Queue(AuthQueueName.LOGGED_IN.getName(), true);
    }

    @Bean
    public Binding loggedInBinding(Queue loggedInQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(loggedInQueue)
                .to(authExchange).with(AuthRoutingKey.LOGGED_IN.getKey());
    }

    @Bean
    public Queue loggedOutQueue() {
        return new Queue(AuthQueueName.LOGGED_OUT.getName(), true); }

    @Bean
    public Binding loggedOutBinding(Queue loggedOutQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(loggedOutQueue)
                .to(authExchange).with(AuthRoutingKey.LOGGED_OUT.getKey());
    }

    @Bean
    public Queue registerQueue() {
        return new Queue(AuthQueueName.REGISTER_ACCOUNT.getName(), true); }

    @Bean
    public Binding registerBinding(Queue registerQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(registerQueue)
                .to(authExchange).with(AuthRoutingKey.REGISTER_ACCOUNT.getKey());
    }

    @Bean
    public Queue registeredQueue() {
        return new Queue(AuthQueueName.ACCOUNT_REGISTERED.getName(), true); }

    @Bean
    public Binding registeredBinding(Queue registeredQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(registeredQueue)
                .to(authExchange).with(AuthRoutingKey.ACCOUNT_REGISTERED.getKey());
    }

    @Bean
    public Queue deleteQueue() {
        return new Queue(AuthQueueName.DELETE_ACCOUNT.getName(), true); }

    @Bean
    public Binding deleteBinding(Queue deleteQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(deleteQueue)
                .to(authExchange).with(AuthRoutingKey.DELETE_ACCOUNT.getKey());
    }

    @Bean
    public Queue deletedQueue() {
        return new Queue(AuthQueueName.ACCOUNT_DELETED.getName(), true); }

    @Bean
    public Binding deletedBinding(Queue deletedQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(deletedQueue)
                .to(authExchange).with(AuthRoutingKey.ACCOUNT_DELETED.getKey());
    }

    @Bean
    public Queue createCartQueue() {
        return new Queue(AuthQueueName.CREATE_CART.getName(), true);
    }

    @Bean
    public Binding createCartBinding(Queue createCartQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(createCartQueue)
                .to(authExchange).with(AuthRoutingKey.CREATE_CART.getKey());
    }

    @Bean
    public Queue deleteCartQueue() {
        return new Queue(AuthQueueName.DELETE_CART.getName(), true);
    }

    @Bean
    public Binding deleteCartBinding(Queue deleteCartQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(deleteCartQueue)
                .to(authExchange).with(AuthRoutingKey.DELETE_CART.getKey());
    }
}