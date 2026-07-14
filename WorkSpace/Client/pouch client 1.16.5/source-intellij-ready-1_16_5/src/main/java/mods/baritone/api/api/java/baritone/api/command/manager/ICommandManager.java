/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.manager;

import java.util.List;
import java.util.stream.Stream;
import lightning.product.Tuple;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.ICommand;
import mods.baritone.api.api.java.baritone.api.command.argument.ICommandArgument;
import mods.baritone.api.api.java.baritone.api.command.registry.Registry;

public interface ICommandManager {
    public IBaritone getBaritone();

    public Registry<ICommand> getRegistry();

    public ICommand getCommand(String var1);

    public boolean execute(String var1);

    public boolean execute(Tuple<String, List<ICommandArgument>> var1);

    public Stream<String> tabComplete(Tuple<String, List<ICommandArgument>> var1);

    public Stream<String> tabComplete(String var1);
}


