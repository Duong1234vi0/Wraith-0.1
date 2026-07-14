/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;

public interface IDatatypePostFunction<T, O> {
    public T apply(O var1) throws CommandException;
}

