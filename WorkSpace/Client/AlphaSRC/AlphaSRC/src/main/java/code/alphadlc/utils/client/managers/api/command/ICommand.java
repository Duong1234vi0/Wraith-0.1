/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.utils.client.managers.api.command;

import code.alphadlc.utils.client.managers.api.command.argument.IArgConsumer;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.display.interfaces.QuickLogger;
import java.util.List;
import java.util.stream.Stream;

public interface ICommand
extends QuickLogger {
    public void execute(String var1, IArgConsumer var2) throws CommandException;

    public Stream<String> tabComplete(String var1, IArgConsumer var2) throws CommandException;

    public String getShortDesc();

    public List<String> getLongDesc();

    public List<String> getNames();

    default public boolean hiddenFromHelp() {
        return false;
    }
}
