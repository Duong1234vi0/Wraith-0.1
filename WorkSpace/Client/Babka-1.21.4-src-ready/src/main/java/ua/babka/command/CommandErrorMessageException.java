package ua.babka.command;

import ua.babka.command.CommandException;

public abstract class CommandErrorMessageException
extends CommandException {
    protected CommandErrorMessageException(String reason) {
        super(reason);
    }

    protected CommandErrorMessageException(String reason, Throwable cause) {
        super(reason, cause);
    }
}

