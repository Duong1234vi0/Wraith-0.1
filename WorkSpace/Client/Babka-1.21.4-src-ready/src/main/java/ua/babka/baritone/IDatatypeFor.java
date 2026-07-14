package ua.babka.baritone;

import ua.babka.baritone.IDatatype;
import ua.babka.baritone.IDatatypeContext;
import ua.babka.command.CommandException;

public interface IDatatypeFor<T>
extends IDatatype {
    public T get(IDatatypeContext var1) throws CommandException;
}

