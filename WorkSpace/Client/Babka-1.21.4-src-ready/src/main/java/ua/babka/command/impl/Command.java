package ua.babka.command.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import ua.babka.command.impl.ICommand;

public abstract class Command
implements ICommand {
    protected final List<String> names;

    protected Command(String ... names) {
        this.names = Stream.of(names).map(string -> string.toLowerCase(Locale.US)).toList();
    }

    @Override
    public final List<String> getNames() {
        return this.names;
    }
}

