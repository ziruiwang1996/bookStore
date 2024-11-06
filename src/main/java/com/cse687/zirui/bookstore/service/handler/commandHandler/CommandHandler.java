package com.cse687.zirui.bookstore.service.handler.commandHandler;
import com.cse687.zirui.bookstore.domain.command.Command;

public interface CommandHandler<T extends Command > {
    void handle(T command);
}
