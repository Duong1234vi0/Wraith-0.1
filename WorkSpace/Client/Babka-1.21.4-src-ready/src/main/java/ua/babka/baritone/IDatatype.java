package ua.babka.baritone;

import java.util.stream.Stream;
import ua.babka.baritone.IDatatypeContext;
import ua.babka.command.CommandException;
import ua.babka.util.IMinecraft;

public interface IDatatype
extends IMinecraft {
    public Stream<String> tabComplete(IDatatypeContext var1) throws CommandException;
}

