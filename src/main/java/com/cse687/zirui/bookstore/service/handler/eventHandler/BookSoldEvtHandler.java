package com.cse687.zirui.bookstore.service.handler.eventHandler;
import com.cse687.zirui.bookstore.domain.event.BookSold;
import com.cse687.zirui.bookstore.domain.model.Order;
import com.cse687.zirui.bookstore.service.BookService;
import com.cse687.zirui.bookstore.service.CustomerService;
import com.cse687.zirui.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class BookSoldEvtHandler implements EventHandler<BookSold> {
    private final CustomerService customerServ;
    private final BookService bookServ;
    private final OrderService orderServ;

    @Autowired
    public BookSoldEvtHandler(CustomerService customerServ, BookService bookServ, OrderService orderServ) {
        this.customerServ = customerServ;
        this.bookServ = bookServ;
        this.orderServ = orderServ;
    }

    @EventListener
    public void handle(BookSold event) {
        float amount = bookServ.bookSold(event.getBookId());
        Order order = new Order(customerServ.getCurrentCustomer(), event.getBookId(), amount, new Date(), 0);
        orderServ.addNewOrder(order);
        customerServ.addOrderToCurCusHistory(order);
    }
}
