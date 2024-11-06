package com.cse687.zirui.bookstore.service.handler.eventHandler;
import com.cse687.zirui.bookstore.domain.event.Event;

public interface EventHandler <T extends Event>{
    void handle(T event);
}
