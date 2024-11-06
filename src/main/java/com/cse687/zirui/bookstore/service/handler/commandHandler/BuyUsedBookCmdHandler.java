package com.cse687.zirui.bookstore.service.handler.commandHandler;
import com.cse687.zirui.bookstore.domain.command.BuyUsedBook;
import com.cse687.zirui.bookstore.service.BookService;
import com.cse687.zirui.bookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BuyUsedBookCmdHandler implements CommandHandler<BuyUsedBook> {
    private final CustomerService customerServ;
    private final BookService bookServ;

    @Autowired
    public BuyUsedBookCmdHandler(CustomerService customerServ, BookService bookServ) {
        this.customerServ = customerServ;
        this.bookServ = bookServ;
    }

    @EventListener
    public void handle(BuyUsedBook command) {
        if (customerServ.getCurrentCustomer()==null) {
            customerServ.setCurrentCustomer(command.getCustomerEmail());
        }
        bookServ.buyUsedBook(command.getBookId(), customerServ.getCurrentCustomer().getId());
    }
}
