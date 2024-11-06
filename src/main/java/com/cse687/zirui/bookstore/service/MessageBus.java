package com.cse687.zirui.bookstore.service;
import com.cse687.zirui.bookstore.domain.command.Command;
import com.cse687.zirui.bookstore.domain.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MessageBus {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public MessageBus(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEvent(Event evt) {
        eventPublisher.publishEvent(evt);
    }

    public void publishCommand(Command cmd) {
        eventPublisher.publishEvent(cmd);
    }
}
