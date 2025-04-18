package com.cse687.zirui.bookstore.auth.messaging;
import com.cse687.zirui.bookstore.shared.RoutingKey;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthRabbitMQConfig {
    public static final String EXCHANGE_NAME = "auth.exchange";

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
    public DirectExchange authExchange() { return new DirectExchange(EXCHANGE_NAME); }

    @Bean
    public Queue registerQueue() { return new Queue(RoutingKey.REGISTER_ACCOUNT.getKey(), true); }

    @Bean
    public Binding registerBinding(Queue registerQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(registerQueue).to(authExchange).with(RoutingKey.REGISTER_ACCOUNT.getKey());
    }

    @Bean
    public Queue deleteQueue() { return new Queue(RoutingKey.DELETE_ACCOUNT.getKey(), true); }

    @Bean
    public Binding deleteBinding(Queue deleteQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(deleteQueue).to(authExchange).with(RoutingKey.DELETE_ACCOUNT.getKey());
    }

    @Bean
    public Queue loggedInQueue() {return new Queue(RoutingKey.LOGGED_IN.getKey(), true); }

    @Bean
    public Binding loggedInBinding(Queue loggedInQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(loggedInQueue).to(authExchange).with(RoutingKey.LOGGED_IN.getKey());
    }

    @Bean
    public Queue loggedOutQueue() {return new Queue(RoutingKey.LOGGED_OUT.getKey(), true); }

    @Bean
    public Binding loggedOutBinding(Queue loggedOutQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(loggedOutQueue).to(authExchange).with(RoutingKey.LOGGED_OUT.getKey());
    }

    @Bean
    public Queue registeredQueue() { return new Queue(RoutingKey.ACCOUNT_REGISTERED.getKey(), true); }

    @Bean
    public Binding registeredBinding(Queue registeredQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(registeredQueue).to(authExchange).with(RoutingKey.ACCOUNT_REGISTERED.getKey());
    }

    @Bean
    public Queue deletedQueue() {return new Queue(RoutingKey.ACCOUNT_DELETED.getKey(), true); }

    @Bean
    public Binding deletedBinding(Queue deletedQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(deletedQueue).to(authExchange).with(RoutingKey.ACCOUNT_DELETED.getKey());
    }
}