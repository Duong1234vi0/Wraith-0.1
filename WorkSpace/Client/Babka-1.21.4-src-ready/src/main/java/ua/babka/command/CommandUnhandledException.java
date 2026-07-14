package ua.babka.command;

import java.util.List;
import ua.babka.command.argument.ICommandArgument;
import ua.babka.command.exception.ICommandException;
import ua.babka.command.impl.ICommand;
import ua.babka.util.QuickLogger;

public class CommandUnhandledException
extends RuntimeException
implements ICommandException,
QuickLogger {
    public CommandUnhandledException(String message) {
        super(message);
    }

    public CommandUnhandledException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle(ICommand command, List<ICommandArgument> args) {
    }
}

