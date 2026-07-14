/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.exception;

import mods.baritone.api.api.java.baritone.api.command.exception.CommandErrorMessageException;

public class CommandInvalidStateException
extends CommandErrorMessageException {
    public CommandInvalidStateException(String reason) {
        super(reason);
    }
}

