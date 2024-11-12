package com.cse687.zirui.bookstore.service;
import com.cse687.zirui.bookstore.adapter.CustomerRepository;
import com.cse687.zirui.bookstore.domain.model.Customer;
import com.cse687.zirui.bookstore.domain.model.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepo;
    private Customer currentCustomer;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepo = customerRepository;
    }

    public void setCurrentCustomer(String email) {
        getCustomerByEmail(email).ifPresentOrElse(
                customer -> { currentCustomer = customer; },
                () -> {
                    currentCustomer = new Customer(email, 0);
                    customerRepo.save(currentCustomer);
                }
        );
    }

    public void removeCurrentCustomer() {
        currentCustomer = null;
    }

    public boolean ifCurrentCustomer(String email) {
        if (currentCustomer == null) { return false; }
        if (currentCustomer.getEmail().equals(email)) { return true; }
        return false;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepo.findById(id);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    @Transactional
    public void addOrderToCurCusHistory(Order order) {
        currentCustomer.addOrder(order);
        customerRepo.save(currentCustomer);
    }
}
