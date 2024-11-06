package com.cse687.zirui.bookstore.service.handler.eventHandler;
import com.cse687.zirui.bookstore.domain.event.OutOfStock;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OutOfStockEvtHandler implements EventHandler<OutOfStock> {
    @EventListener
    public void handle(OutOfStock event) {
        // send notification to user
    }
}
