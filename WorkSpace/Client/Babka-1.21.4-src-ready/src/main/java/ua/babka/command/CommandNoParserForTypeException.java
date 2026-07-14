package ua.babka.command;

import ua.babka.command.CommandUnhandledException;

public class CommandNoParserForTypeException
extends CommandUnhandledException {
    public CommandNoParserForTypeException(Class<?> klass) {
        super(String.format("Could not find a handler for type %s", klass.getSimpleName()));
    }
}

