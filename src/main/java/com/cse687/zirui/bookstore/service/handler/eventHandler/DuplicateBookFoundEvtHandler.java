package com.cse687.zirui.bookstore.service.handler.eventHandler;
import com.cse687.zirui.bookstore.domain.event.DuplicateBookFound;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DuplicateBookFoundEvtHandler implements EventHandler<DuplicateBookFound> {
    @EventListener
    public void handle(DuplicateBookFound event) {
        // send notification
        // remove book where it should not be
    }
}
