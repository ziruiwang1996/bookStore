package com.cse687.zirui.bookstore.shared.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange("orderExchange");
    }

    @Bean
    public Queue orderCommandQueue() {
        return new Queue("orderCmdQueue");
    }

    @Bean
    public Queue orderEventQueue() {
        return new Queue("orderEvtQueue");
    }

    @Bean
    public Binding bindOrderCmdQueue() {
        return BindingBuilder
                .bind(orderCommandQueue())
                .to(orderExchange())
                .with("order.cmd");
    }

    @Bean
    public Binding bindOrderEvtQueue() {
        return BindingBuilder
                .bind(orderEventQueue())
                .to(orderExchange())
                .with("order.evt");
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