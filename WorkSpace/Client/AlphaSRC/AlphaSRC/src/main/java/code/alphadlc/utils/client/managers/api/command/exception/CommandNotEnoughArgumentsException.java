/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.exception;

import code.alphadlc.utils.client.managers.api.command.exception.CommandErrorMessageException;

public class CommandNotEnoughArgumentsException
extends CommandErrorMessageException {
    public CommandNotEnoughArgumentsException(int minArgs) {
        super(String.format("Not enough arguments (expected at least %d)", minArgs));
    }
}
