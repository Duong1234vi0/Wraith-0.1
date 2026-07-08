/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.exception;

import code.alphadlc.utils.client.managers.api.command.exception.ICommandException;

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
