//package com.cse687.zirui.bookstore.service.handler;
//import com.cse687.zirui.bookstore.domain.command.*;
//import com.cse687.zirui.bookstore.service.handler.commandHandler.*;
//import org.springframework.context.annotation.Configuration;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class CommandHandlerRegistry {
//    private static final Map< Class<? extends Command>, CommandHandler<? extends Command>> handlers = new HashMap<>();
//
//    public CommandHandlerRegistry(BuyNewBookCmdHandler buyNewBookCmdHandler,
//                                   BuyUsedBookCmdHandler buyUsedBookCmdHandler,
//                                   SellBookCmdHandler sellBookCmdHandler)
//    {
//        handlers.put(BuyNewBook.class, buyNewBookCmdHandler);
//        handlers.put(BuyUsedBook.class, buyUsedBookCmdHandler);
//        handlers.put(SellBook.class, sellBookCmdHandler);
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T extends Command> CommandHandler<T> getHandler(Class<T> commandClass) {
//        return (CommandHandler<T>) handlers.get(commandClass);
//    }
//}
