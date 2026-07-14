/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import java.util.stream.Stream;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeContext;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;

public interface IDatatype {
    public Stream<String> tabComplete(IDatatypeContext var1) throws CommandException;
}

