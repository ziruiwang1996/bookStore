package com.cse687.zirui.bookstore.auth.service;
import com.cse687.zirui.bookstore.auth.domain.command.*;
import com.cse687.zirui.bookstore.auth.domain.event.*;
import com.cse687.zirui.bookstore.auth.domain.model.*;
import com.cse687.zirui.bookstore.auth.messaging.AuthProducer;
import com.cse687.zirui.bookstore.auth.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Field;
import java.util.Optional;

@Service
public class AuthService {
    private final CustomerRepository customerRepo;
    private final AuthProducer producer;

    @Autowired
    public AuthService(CustomerRepository customerRepo, AuthProducer producer){
        this.customerRepo = customerRepo;
        this.producer = producer;
    }

    public String logIn(LogIn cmd) {
        Optional<Customer> customerOpt = customerRepo.findByEmail(cmd.email());
        if (customerOpt.isEmpty()) {
            Guest guest = new Guest(cmd.email());
            customerRepo.save(guest);
            producer.publishLoggedInEvt(new LoggedIn(guest.getId()));
            return JwtService.generateToken(guest.getId(), "GUEST");
        }
        Customer customer = customerOpt.get();
        if (customer instanceof Member member) {
            if (cmd.password() != null && cmd.password().equals(member.getPassword())) {
                producer.publishLoggedInEvt(new LoggedIn(member.getId()));
                return JwtService.generateToken(member.getId(), "MEMBER");
            } else {
                throw new IllegalArgumentException("Invalid credentials");
            }
        } else if (customer instanceof Guest guest) {
            producer.publishLoggedInEvt(new LoggedIn(guest.getId()));
            return JwtService.generateToken(guest.getId(), "GUEST");
        }
        throw new IllegalStateException("Unexpected user type");
    }

    public void loggedIn(LoggedIn evt) {
        System.out.println("User " + evt.userId() + ": logged in");
        producer.publishCreateCartCmd(
                new CreateCart(evt.userId())
        );
    }

    public void logOut(LogOut cmd) {
        producer.publishLoggedOutEvt(new LoggedOut(cmd.userId()));
    }

    public void loggedOut(LoggedOut evt) {
        System.out.println("User " + evt.userId() + ": logged out");
        // placeholder for future features
    }

    public void register(RegisterAccount cmd) {
        if (customerRepo.findMemberByEmail(cmd.email()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        if (!cmd.Password().equals(cmd.passwordConfirmation())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        producer.publishAccountRegisteredEvt(
                new AccountRegistered(cmd.email(), cmd.Password())
        );
    }

    private void upgradeGuestToMember(Guest guest, String password) {
        Long guestId = guest.getId();
        String guestEmail = guest.getEmail();
        customerRepo.deleteById(guestId);
        Member newMember = new Member(guestEmail, password, 0);
        try {
            Field idField = Customer.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(newMember, guestId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to assign ID to new Member", e);
        }
        customerRepo.save(newMember);
    }

    @Transactional
    public void registered(AccountRegistered evt) {
        customerRepo.findGuestByEmail(evt.email()).ifPresentOrElse(
                guest -> upgradeGuestToMember(guest, evt.Password()),
                () -> customerRepo.save(new Member(evt.email(), evt.Password(), 0))
        );
    }

    public void delete(DeleteAccount cmd) {
        if (customerRepo.findById(cmd.userId()).isPresent()) {
            producer.publishAccountDeletedEvt(
                    new AccountDeleted(cmd.userId())
            );
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public void deleted(AccountDeleted evt) {
        customerRepo.deleteById(evt.userId());
        producer.publishDeleteCartCmd(new DeleteCart(evt.userId()));
    }

    public void addCreditIfMember(AddCreditIfMember cmd) {
        customerRepo.findById(cmd.userId()).ifPresent(
                customer -> {
                    if (customer instanceof Member member) {
                        member.addCredit(cmd.amt());
                    }
                }
        );
    }
}