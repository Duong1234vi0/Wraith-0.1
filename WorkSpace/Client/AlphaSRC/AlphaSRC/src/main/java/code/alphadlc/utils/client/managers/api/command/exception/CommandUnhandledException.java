/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.exception;

import code.alphadlc.utils.client.managers.api.command.ICommand;
import code.alphadlc.utils.client.managers.api.command.argument.ICommandArgument;
import code.alphadlc.utils.client.managers.api.command.exception.ICommandException;
import code.alphadlc.utils.display.interfaces.QuickLogger;
import java.util.List;

public class CommandUnhandledException
extends RuntimeException
implements ICommandException,
QuickLogger {
    public CommandUnhandledException(String message) {
        super(message);
    }

    public CommandUnhandledException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle(ICommand command, List<ICommandArgument> args) {
    }
}
