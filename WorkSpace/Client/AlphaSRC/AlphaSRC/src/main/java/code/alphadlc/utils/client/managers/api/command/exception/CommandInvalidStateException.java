/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.exception;

import code.alphadlc.utils.client.managers.api.command.exception.CommandErrorMessageException;

public class CommandInvalidStateException
extends CommandErrorMessageException {
    public CommandInvalidStateException(String reason) {
        super(reason);
    }
}
