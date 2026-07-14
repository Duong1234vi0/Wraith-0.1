/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.exception;

import java.util.List;
import lightning.product.D_4024_W;
import mods.baritone.api.api.java.baritone.api.command.ICommand;
import mods.baritone.api.api.java.baritone.api.command.argument.ICommandArgument;
import mods.baritone.api.api.java.baritone.api.utils.Helper;

public interface ICommandException {
    public String getMessage();

    default public void handle(ICommand command, List<ICommandArgument> args) {
        Helper.HELPER.logDirect(this.getMessage(), D_4024_W.P_4830_p);
    }
}

