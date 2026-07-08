/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 */
package code.alphadlc.utils.client.managers.api.command.exception;

import code.alphadlc.utils.client.managers.api.command.ICommand;
import code.alphadlc.utils.client.managers.api.command.argument.ICommandArgument;
import code.alphadlc.utils.display.interfaces.QuickLogger;
import java.util.List;
import net.minecraft.class_124;

public interface ICommandException
extends QuickLogger {
    public String getMessage();

    default public void handle(ICommand command, List<ICommandArgument> args) {
        this.logDirect(this.getMessage(), class_124.field_1061);
    }
}
