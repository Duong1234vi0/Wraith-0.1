/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.exception;

import java.util.List;
import lightning.product.D_4024_W;
import mods.baritone.api.api.java.baritone.api.command.ICommand;
import mods.baritone.api.api.java.baritone.api.command.argument.ICommandArgument;
import mods.baritone.api.api.java.baritone.api.command.exception.ICommandException;
import mods.baritone.api.api.java.baritone.api.utils.Helper;

public class CommandUnhandledException
extends RuntimeException
implements ICommandException {
    public CommandUnhandledException(String message) {
        super(message);
    }

    public CommandUnhandledException(Throwable cause) {
        super(cause);
    }

    @Override
    public void handle(ICommand command, List<ICommandArgument> args) {
        Helper.HELPER.logDirect("An unhandled exception occurred. The error is in your game's log, please report this at https://github.com/cabaletta/baritone/issues", D_4024_W.P_4830_p);
        this.printStackTrace();
    }
}

