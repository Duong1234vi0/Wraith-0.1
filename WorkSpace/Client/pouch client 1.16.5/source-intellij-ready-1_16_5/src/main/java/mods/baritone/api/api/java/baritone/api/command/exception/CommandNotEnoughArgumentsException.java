/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.exception;

import mods.baritone.api.api.java.baritone.api.command.exception.CommandErrorMessageException;

public class CommandNotEnoughArgumentsException
extends CommandErrorMessageException {
    public CommandNotEnoughArgumentsException(int minArgs) {
        super(String.format("Not enough arguments (expected at least %d)", minArgs));
    }
}

