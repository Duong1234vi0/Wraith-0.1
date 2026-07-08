/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3545
 */
package code.alphadlc.utils.client.managers.api.command.manager;

import code.alphadlc.utils.client.managers.api.command.ICommand;
import code.alphadlc.utils.client.managers.api.command.argument.ICommandArgument;
import code.alphadlc.utils.client.managers.api.command.registry.Registry;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_3545;

public interface ICommandManager {
    public Registry<ICommand> getRegistry();

    public ICommand getCommand(String var1);

    public boolean execute(String var1);

    public boolean execute(class_3545<String, List<ICommandArgument>> var1);

    public Stream<String> tabComplete(class_3545<String, List<ICommandArgument>> var1);

    public Stream<String> tabComplete(String var1);
}
