package com.cse687.zirui.bookstore.auth.messaging;
import com.cse687.zirui.bookstore.auth.domain.command.*;
import com.cse687.zirui.bookstore.auth.domain.event.*;
import com.cse687.zirui.bookstore.shared.RoutingKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthProducer {
    private final RabbitTemplate rabbitTemplate;
    public static final String EXCHANGE = "auth.exchange";

    public AuthProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishLoggedInEvt(LoggedIn evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.LOGGED_IN.getKey(), evt);
    }

    public void publishLoggedOutEvt(LoggedOut evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.LOGGED_OUT.getKey(), evt);
    }

    public void publishRegisterAccountCmd(RegisterAccount cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.REGISTER_ACCOUNT.getKey(), cmd);
    }

    public void publishAccountRegisteredEvt(AccountRegistered evt) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.ACCOUNT_REGISTERED.getKey(), evt);
    }

    public void publishDeleteAccountCmd(DeleteAccount cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.DELETE_ACCOUNT.getKey(), cmd);
    }

    public void publishAccountDeletedEvt(AccountDeleted evt){
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.ACCOUNT_DELETED.getKey(), evt);
    }

    public void publishCreateCartCmd(CreateCart cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.CREATE_CART.getKey(), cmd);
    }

    public void publishDeleteCartCmd(DeleteCart cmd) {
        rabbitTemplate.convertAndSend(EXCHANGE, RoutingKey.CREATE_CART.getKey(), cmd);
    }
}