/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.exception;

import mods.baritone.api.api.java.baritone.api.command.exception.CommandErrorMessageException;

public class CommandTooManyArgumentsException
extends CommandErrorMessageException {
    public CommandTooManyArgumentsException(int maxArgs) {
        super(String.format("Too many arguments (expected at most %d)", maxArgs));
    }
}

