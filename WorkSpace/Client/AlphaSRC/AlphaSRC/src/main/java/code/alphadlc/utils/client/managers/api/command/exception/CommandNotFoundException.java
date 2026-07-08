/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command.exception;

import code.alphadlc.utils.client.managers.api.command.ICommand;
import code.alphadlc.utils.client.managers.api.command.argument.ICommandArgument;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.display.interfaces.QuickLogger;
import java.util.List;

public class CommandNotFoundException
extends CommandException
implements QuickLogger {
    public final String command;

    public CommandNotFoundException(String command) {
        super(String.format("\u041a\u043e\u043c\u0430\u043d\u0434\u0430 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u0430: %s", command));
        this.command = command;
    }

    @Override
    public void handle(ICommand command, List<ICommandArgument> args) {
        this.logDirect(this.getMessage());
    }
}
