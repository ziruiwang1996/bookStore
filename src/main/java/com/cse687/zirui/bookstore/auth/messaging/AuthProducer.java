package com.cse687.zirui.bookstore.auth.messaging;
import com.cse687.zirui.bookstore.auth.domain.command.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthProducer {
    private RabbitTemplate rabbitTemplate;

    public AuthProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishLogInCmd(LogIn cmd) {
        rabbitTemplate.convertAndSend("auth.exchange", "logIn", cmd);
    }

    public void publishLogOutCmd(LogOut cmd) {
        rabbitTemplate.convertAndSend("auth.exchange", "logIn", cmd);
    }

    public void publishRegisterAccountCmd(RegisterAccount cmd) {
        rabbitTemplate.convertAndSend("auth.exchange", "logIn", cmd);
    }

    public void publishUpdateMemberAccountCmd(UpdateMemberAccount cmd) {
        rabbitTemplate.convertAndSend("auth.exchange", "logIn", cmd);
    }

    public void publishDeleteAccountCmd(DeleteAccount cmd) {
        rabbitTemplate.convertAndSend("auth.exchange", "logIn", cmd);
    }

}