package ua.babka.command;

import ua.babka.command.CommandErrorMessageException;

public class CommandNotEnoughArgumentsException
extends CommandErrorMessageException {
    public CommandNotEnoughArgumentsException(int minArgs) {
        super(String.format("Недостаточно аргументов, нужен %d аргумент", minArgs));
    }
}

