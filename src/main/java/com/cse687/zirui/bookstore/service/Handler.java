//package com.cse687.zirui.bookstore.service;
//import com.cse687.zirui.bookstore.domain.command.BuyNewBook;
//import com.cse687.zirui.bookstore.domain.command.BuyUsedBook;
//import com.cse687.zirui.bookstore.domain.command.SellBook;
//import com.cse687.zirui.bookstore.domain.event.*;
//import com.cse687.zirui.bookstore.domain.model.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.Date;
//
//@Service
//public class Handler {
//    private final CustomerService customerServ;
//    private final BookService bookServ;
//    private final OrderService orderServ;
//
//    @Autowired
//    public Handler(CustomerService customerServ, BookService bookServ, OrderService orderServ) {
//        this.customerServ = customerServ;
//        this.bookServ = bookServ;
//        this.orderServ = orderServ;
//    }
//
//    public void LogIn(String email) {
//        customerServ.setCurrentCustomer(email);
//    }
//
//    public void LogOut() {
//        customerServ.removeCurrentCustomer();
//    }
//
//    public void buyNewBook(BuyNewBook cmd) {
//        if (customerServ.getCurrentCustomer()==null) {
//            LogIn(cmd.getCustomerEmail());
//        }
//        bookServ.buyNewBook(cmd.getIsbn(), customerServ.getCurrentCustomer().getId(), cmd.getPrice());
//    }
//
//    public void buyUsedBook(BuyUsedBook cmd) {
//        if (customerServ.getCurrentCustomer()==null) {
//            LogIn(cmd.getCustomerEmail());
//        }
//        bookServ.buyUsedBook(cmd.getBookId(), customerServ.getCurrentCustomer().getId());
//    }
//
//    public void NewBookBought(NewBookBought evt) {
//        Long bookId = bookServ.newBookBought(evt.getISBN(), evt.getPrice());
//        Order order = new Order(evt.getCustomerId(), bookId, evt.getPrice(), new Date(), 1);
//        orderServ.addNewOrder(order);
//        // add orderId to user
//        customerServ.addOrderToCurCusHistory(order);
//    }
//
//    public void UsedBookBought(UsedBookBought evt) {
//        float amount = bookServ.usedBookBought(evt.getBookId());
//        Order order = new Order(evt.getCustomerId(), evt.getBookId(), amount, new Date(), 1);
//        orderServ.addNewOrder(order);
//        customerServ.addOrderToCurCusHistory(order);
//    }
//
//    public void sellBook(SellBook cmd) {
//        if (customerServ.getCurrentCustomer()==null) {
//            LogIn(cmd.getCustomerEmail());
//        }
//        bookServ.sellBook(cmd.getBookId(), customerServ.getCurrentCustomer().getId());
//    }
//
//    public void BookSold(BookSold evt) {
//        float amount = bookServ.bookSold(evt.getBookId());
//        Order order = new Order(evt.getCustomerId(), evt.getBookId(), amount, new Date(), 0);
//        orderServ.addNewOrder(order);
//        customerServ.addOrderToCurCusHistory(order);
//    }
//
//    public void DuplicateBookFound(DuplicateBookFound evt) {
//    }
//
//    public void OutOfStock(OutOfStock evt) {
//    }
//}
