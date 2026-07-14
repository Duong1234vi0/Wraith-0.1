package ua.babka.command;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.util.Pair;
import ua.babka.command.Registry;
import ua.babka.command.argument.ICommandArgument;
import ua.babka.command.impl.ICommand;

public interface ICommandManager {
    public Registry<ICommand> getRegistry();

    public ICommand getCommand(String var1);

    public boolean execute(String var1);

    public boolean execute(Pair<String, List<ICommandArgument>> var1);

    public Stream<String> tabComplete(Pair<String, List<ICommandArgument>> var1);

    public Stream<String> tabComplete(String var1);
}

