package ua.babka.command;

import java.util.List;
import ua.babka.command.CommandException;
import ua.babka.command.argument.ICommandArgument;
import ua.babka.command.impl.ICommand;
import ua.babka.util.QuickLogger;

public class CommandNotFoundException
extends CommandException
implements QuickLogger {
    public final String command;

    public CommandNotFoundException(String command) {
        super(String.format("Команда не найдена: %s", command));
        this.command = command;
    }

    @Override
    public void handle(ICommand command, List<ICommandArgument> args) {
        this.logDirect(this.getMessage());
    }
}

