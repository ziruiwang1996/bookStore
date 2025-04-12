package com.cse687.zirui.bookstore.auth.service;
import com.cse687.zirui.bookstore.auth.domain.command.*;
import com.cse687.zirui.bookstore.auth.messaging.AuthProducer;
import com.cse687.zirui.bookstore.auth.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final CustomerRepository customerRepo;
    private AuthProducer producer;

    @Autowired
    public AuthService(CustomerRepository customerRepo, AuthProducer producer) {
        this.customerRepo = customerRepo;
        this.producer = producer;
    }

    public void logIn(LogIn cmd) {
    }

    public void logOut(LogOut cmd) {

    }

    public void register(RegisterAccount cmd) {
    }


    public void update(UpdateMemberAccount cmd) {
    }

    public void delete(DeleteAccount cmd) {
    }
}
