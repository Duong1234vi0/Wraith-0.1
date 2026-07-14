/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.exception;

import mods.baritone.api.api.java.baritone.api.command.argument.ICommandArgument;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidArgumentException;

public class CommandInvalidTypeException
extends CommandInvalidArgumentException {
    public CommandInvalidTypeException(ICommandArgument arg, String expected) {
        super(arg, String.format("Expected %s", expected));
    }

    public CommandInvalidTypeException(ICommandArgument arg, String expected, Throwable cause) {
        super(arg, String.format("Expected %s", expected), cause);
    }

    public CommandInvalidTypeException(ICommandArgument arg, String expected, String got) {
        super(arg, String.format("Expected %s, but got %s instead", expected, got));
    }

    public CommandInvalidTypeException(ICommandArgument arg, String expected, String got, Throwable cause) {
        super(arg, String.format("Expected %s, but got %s instead", expected, got), cause);
    }
}

