package com.cse687.zirui.bookstore.service.handler.eventHandler;
import com.cse687.zirui.bookstore.domain.event.NewBookBought;
import com.cse687.zirui.bookstore.domain.model.Order;
import com.cse687.zirui.bookstore.service.BookService;
import com.cse687.zirui.bookstore.service.CustomerService;
import com.cse687.zirui.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class NewBookBoughtEvtHandler implements EventHandler<NewBookBought> {
    private final CustomerService customerServ;
    private final BookService bookServ;
    private final OrderService orderServ;

    @Autowired
    public NewBookBoughtEvtHandler(CustomerService customerServ, BookService bookServ, OrderService orderServ) {
        this.customerServ = customerServ;
        this.bookServ = bookServ;
        this.orderServ = orderServ;
    }

    @EventListener
    public void handle(NewBookBought event) {
        Long bookId = bookServ.newBookBought(event.getISBN(), event.getPrice());
        Order order = new Order(customerServ.getCurrentCustomer(), bookId, event.getPrice(), new Date(), 1);
        orderServ.addNewOrder(order);
        customerServ.addOrderToCurCusHistory(order);
    }
}
