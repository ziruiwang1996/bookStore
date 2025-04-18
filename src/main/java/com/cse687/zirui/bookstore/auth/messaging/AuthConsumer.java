package com.cse687.zirui.bookstore.auth.messaging;
import com.cse687.zirui.bookstore.auth.domain.command.*;
import com.cse687.zirui.bookstore.auth.domain.event.*;
import com.cse687.zirui.bookstore.auth.service.AuthService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthConsumer {
    private final AuthService authServ;
    private static final String REGISTER_ACCOUNT = "auth.queue.registerAccount";
    private static final String DELETE_ACCOUNT = "auth.queue.deleteAccount";
    private static final String LOGGED_IN = "auth.queue.loggedIn";
    private static final String LOGGED_OUT = "auth.queue.loggedOut";
    private static final String ACCOUNT_REGISTERED = "auth.queue.accountRegistered";
    private static final String ACCOUNT_DELETED = "auth.queue.accountDeleted";
    private static final String ADD_CREDIT = "order.queue.addCredit"; // from order service

    @Autowired
    public AuthConsumer(AuthService authServ) {
        this.authServ = authServ;
    }

    @RabbitListener(queues = LOGGED_IN)
    public void handleLoggedInEvt(LoggedIn evt) {
        authServ.loggedIn(evt);
    }

    @RabbitListener(queues = LOGGED_OUT)
    public void handleLoggedOutEvt(LoggedOut evt) {
        authServ.loggedOut(evt);
    }

    @RabbitListener(queues = REGISTER_ACCOUNT)
    public void handleRegisterAccountCmd(RegisterAccount cmd) {
        authServ.register(cmd);
    }

    @RabbitListener(queues = ACCOUNT_REGISTERED)
    public void handleAccountRegisteredEvt(AccountRegistered evt) {
        authServ.registered(evt);
    }

    @RabbitListener(queues = DELETE_ACCOUNT)
    public void handleDeleteAccountCmd(DeleteAccount cmd) {
        authServ.delete(cmd);
    }

    @RabbitListener(queues = ACCOUNT_DELETED)
    public void handleAccountDeletedEvt(AccountDeleted evt){
        authServ.deleted(evt);
    }

    @RabbitListener(queues = ADD_CREDIT)
    public void handleAddCreditIfMemberCmd(AddCreditIfMember cmd){
        authServ.addCreditIfMember(cmd);
    }
}
