package com.cse687.zirui.bookstore.service.handler.commandHandler;
import com.cse687.zirui.bookstore.domain.command.SellBook;
import com.cse687.zirui.bookstore.service.BookService;
import com.cse687.zirui.bookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SellBookCmdHandler implements CommandHandler<SellBook> {
    private final CustomerService customerServ;
    private final BookService bookServ;

    @Autowired
    public SellBookCmdHandler(CustomerService customerServ, BookService bookServ) {
        this.customerServ = customerServ;
        this.bookServ = bookServ;
    }

    @EventListener
    public void handle(SellBook command) {
        if (customerServ.getCurrentCustomer()==null) {
            customerServ.setCurrentCustomer(command.getCustomerEmail());
        } else if ( !customerServ.ifCurrentCustomer(command.getCustomerEmail()) ) {
            customerServ.setCurrentCustomer(command.getCustomerEmail());
        }
        bookServ.sellBook(command.getBookId(), customerServ.getCurrentCustomer().getId());
    }
}
