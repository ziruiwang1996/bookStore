package com.cse687.zirui.bookstore.controller;
import com.cse687.zirui.bookstore.domain.model.Customer;
import com.cse687.zirui.bookstore.service.CustomerService;
import com.cse687.zirui.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerServ;
    private final OrderService orderServ;

    @Autowired
    public CustomerController(CustomerService customerServ, OrderService orderServ) {
        this.customerServ = customerServ;
        this.orderServ = orderServ;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String email) {
        try {
            if (customerServ.getCurrentCustomer()==null) {
                customerServ.setCurrentCustomer(email);
            } else if ( !customerServ.ifCurrentCustomer(email) ) {
                customerServ.setCurrentCustomer(email);
            }
            return ResponseEntity.ok(customerServ.getCurrentCustomer());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
