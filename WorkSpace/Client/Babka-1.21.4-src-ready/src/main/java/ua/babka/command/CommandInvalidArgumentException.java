package ua.babka.command;

import ua.babka.command.CommandErrorMessageException;
import ua.babka.command.argument.ICommandArgument;

public abstract class CommandInvalidArgumentException
extends CommandErrorMessageException {
    public final ICommandArgument arg;

    protected CommandInvalidArgumentException(ICommandArgument arg, String message) {
        super(CommandInvalidArgumentException.formatMessage(arg, message));
        this.arg = arg;
    }

    protected CommandInvalidArgumentException(ICommandArgument arg, String message, Throwable cause) {
        super(CommandInvalidArgumentException.formatMessage(arg, message), cause);
        this.arg = arg;
    }

    private static String formatMessage(ICommandArgument arg, String message) {
        return String.format("Ошибка в %s аргументе: %s", arg.getIndex() == -1 ? "<unknown>" : Integer.toString(arg.getIndex() + 1), message);
    }
}

