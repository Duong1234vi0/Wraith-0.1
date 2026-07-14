/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.exception;

import mods.baritone.api.api.java.baritone.api.command.exception.ICommandException;

public abstract class CommandException
extends Exception
implements ICommandException {
    protected CommandException(String reason) {
        super(reason);
    }

    protected CommandException(String reason, Throwable cause) {
        super(reason, cause);
    }
}

