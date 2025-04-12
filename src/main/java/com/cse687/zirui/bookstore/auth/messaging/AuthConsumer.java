package com.cse687.zirui.bookstore.auth.messaging;
import com.cse687.zirui.bookstore.auth.domain.command.*;
import com.cse687.zirui.bookstore.auth.service.AuthService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthConsumer {
    private final AuthService authServ;

    @Autowired
    public AuthConsumer(AuthService authServ) {
        this.authServ = authServ;
    }

    @RabbitListener(queues = "authEvtQueue")
    public void handleLogInCmd(LogIn cmd) {
        authServ.logIn(cmd);
    }

    @RabbitListener(queues = "authEvtQueue")
    public void handleLogOutCmd(LogOut cmd) {
        authServ.logOut(cmd);
    }

    @RabbitListener(queues = "authEvtQueue")
    public void handleRegisterAccountCmd(RegisterAccount cmd) {
        authServ.register(cmd);
    }

    @RabbitListener(queues = "authEvtQueue")
    public void handleUpdateMemberAccountCmd(UpdateMemberAccount cmd) {
        authServ.update(cmd);
    }

    @RabbitListener(queues = "authEvtQueue")
    public void handleDeleteAccountCmd(DeleteAccount cmd) {
        authServ.delete(cmd);
    }

//    @RabbitListener(queues = "orderEvtQueue")
//    public void handleBookBoughtEvent(BookBought event) {
//        orderServ.bookBought(event.bookId());
//    }
}
