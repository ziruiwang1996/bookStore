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
    public TopicExchange paymentExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue paymentSucceededQueue() {
        return new Queue(PaymentQueueName.PAYMENT_SUCCEEDED.getName(), true);
    }

    @Bean
    public Binding paymentSucceededBinding(Queue paymentSucceededQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentSucceededQueue)
                .to(paymentExchange).with(PaymentRoutingKey.PAYMENT_SUCCEEDED.getKey());
    }

    @Bean
    public Queue paymentFailedQueue() {
        return new Queue(PaymentQueueName.PAYMENT_FAILED.getName(), true);
    }

    @Bean
    public Binding paymentFailedBinding(Queue paymentFailedQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentFailedQueue)
                .to(paymentExchange).with(PaymentRoutingKey.PAYMENT_FAILED.getKey());
    }

    @Bean
    public Queue generateInvoiceQueue() {
        return new Queue(PaymentQueueName.GENERATE_INVOICE.getName(), true);
    }

    @Bean
    public Binding generateInvoiceBinding(Queue generateInvoiceQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(generateInvoiceQueue)
                .to(paymentExchange).with(PaymentRoutingKey.GENERATE_INVOICE.getKey());
    }
}