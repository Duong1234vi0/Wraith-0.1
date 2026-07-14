/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatype;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeContext;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;

public interface IDatatypePost<T, O>
extends IDatatype {
    public T apply(IDatatypeContext var1, O var2) throws CommandException;
}

