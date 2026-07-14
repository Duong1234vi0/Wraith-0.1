package ua.babka.baritone;

import ua.babka.baritone.IDatatype;
import ua.babka.baritone.IDatatypeContext;
import ua.babka.command.CommandException;

public interface IDatatypePost<T, O>
extends IDatatype {
    public T apply(IDatatypeContext var1, O var2) throws CommandException;
}

