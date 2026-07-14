/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.exception;

import java.util.List;
import mods.baritone.api.api.java.baritone.api.command.ICommand;
import mods.baritone.api.api.java.baritone.api.command.argument.ICommandArgument;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.utils.Helper;

public class CommandNotFoundException
extends CommandException {
    public final String command;

    public CommandNotFoundException(String command) {
        super(String.format("Command not found: %s", command));
        this.command = command;
    }

    @Override
    public void handle(ICommand command, List<ICommandArgument> args) {
        Helper.HELPER.logDirect(this.getMessage());
    }
}

