//package com.cse687.zirui.bookstore.service.handler;
//import com.cse687.zirui.bookstore.domain.event.*;
//import com.cse687.zirui.bookstore.service.handler.eventHandler.*;
//import org.springframework.context.annotation.Configuration;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class EventHandlerRegistry {
//    private static final Map< Class<? extends Event>, EventHandler<? extends Event>> handlers = new HashMap<>();
//
//    public EventHandlerRegistry(BookSoldEvtHandler bookSoldEvtHandler,
//                                DuplicateBookFoundEvtHandler duplicateBookFoundEvtHandler,
//                                NewBookBoughtEvtHandler newBookBoughtEvtHandler,
//                                OutOfStockEvtHandler outOfStockEvtHandler,
//                                UsedBookBoughtEvtHandler usedBookBoughtEvtHandler)
//    {
//        handlers.put(BookSold.class, bookSoldEvtHandler);
//        handlers.put(DuplicateBookFound.class, duplicateBookFoundEvtHandler);
//        handlers.put(NewBookBought.class, newBookBoughtEvtHandler);
//        handlers.put(OutOfStock.class, outOfStockEvtHandler);
//        handlers.put(UsedBookBought.class, usedBookBoughtEvtHandler);
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T extends Event> EventHandler<T> getHandler(Class<T> eventClass) {
//        return (EventHandler<T>) handlers.get(eventClass);
//    }
//}
