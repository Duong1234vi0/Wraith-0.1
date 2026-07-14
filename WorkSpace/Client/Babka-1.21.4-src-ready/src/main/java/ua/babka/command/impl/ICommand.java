package ua.babka.command.impl;

import java.util.List;
import java.util.stream.Stream;
import ua.babka.command.CommandException;
import ua.babka.command.argument.IArgConsumer;
import ua.babka.util.QuickLogger;

public interface ICommand
extends QuickLogger {
    public void execute(String var1, IArgConsumer var2) throws CommandException;

    public Stream<String> tabComplete(String var1, IArgConsumer var2) throws CommandException;

    public String getShortDesc();

    public List<String> getLongDesc();

    public List<String> getNames();

    default public boolean hiddenFromHelp() {
        return false;
    }
}

