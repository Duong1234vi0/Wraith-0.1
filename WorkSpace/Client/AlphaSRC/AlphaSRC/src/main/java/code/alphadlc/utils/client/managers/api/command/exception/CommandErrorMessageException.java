/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.exception;

import code.alphadlc.utils.client.managers.api.command.exception.CommandException;

public abstract class CommandErrorMessageException
extends CommandException {
    protected CommandErrorMessageException(String reason) {
        super(reason);
    }

    protected CommandErrorMessageException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
