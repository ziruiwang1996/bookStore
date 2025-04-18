package com.cse687.zirui.bookstore.payment.messaging;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRabbitMQConfig {
    public static final String EXCHANGE = "payment.exchange";

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue processPaymentQueue() {
        return new Queue("payment.process", true);
    }

    @Bean
    public Queue paymentSucceededQueue() {
        return new Queue("payment.succeeded", true);
    }

    @Bean
    public Queue paymentFailedQueue() {
        return new Queue("payment.failed", true);
    }

    @Bean
    public Binding bindProcessPayment(Queue processPaymentQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(processPaymentQueue).to(paymentExchange).with("payment.process");
    }

    @Bean
    public Binding bindPaymentSucceeded(Queue paymentSucceededQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentSucceededQueue).to(paymentExchange).with("payment.succeeded");
    }

    @Bean
    public Binding bindPaymentFailed(Queue paymentFailedQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentFailedQueue).to(paymentExchange).with("payment.failed");
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