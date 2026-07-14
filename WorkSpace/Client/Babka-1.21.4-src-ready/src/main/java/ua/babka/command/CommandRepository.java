package ua.babka.command;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.util.Pair;
import ua.babka.Babka;
import ua.babka.command.CommandArguments;
import ua.babka.command.CommandException;
import ua.babka.command.CommandUnhandledException;
import ua.babka.command.ICommandManager;
import ua.babka.command.Registry;
import ua.babka.command.TabCompleteHelper;
import ua.babka.command.argument.ArgConsumer;
import ua.babka.command.argument.ICommandArgument;
import ua.babka.command.exception.ICommandException;
import ua.babka.command.impl.DefaultCommands;
import ua.babka.command.impl.ICommand;

public class CommandRepository
implements ICommandManager {
    private final Registry<ICommand> registry = new Registry();

    public CommandRepository() {
        DefaultCommands.createAll().forEach(this.registry::register);
    }

    @Override
    public Registry<ICommand> getRegistry() {
        return this.registry;
    }

    @Override
    public ICommand getCommand(String name) {
        for (ICommand command : this.registry.entries) {
            if (!command.getNames().contains(name.toLowerCase(Locale.US))) continue;
            return command;
        }
        return null;
    }

    @Override
    public boolean execute(String string) {
        return this.execute(CommandRepository.expand(string));
    }

    @Override
    public boolean execute(Pair<String, List<ICommandArgument>> expanded) {
        ExecutionWrapper execution = this.from(expanded);
        if (execution != null) {
            execution.execute();
        }
        return execution != null;
    }

    @Override
    public Stream<String> tabComplete(Pair<String, List<ICommandArgument>> expanded) {
        ExecutionWrapper execution = this.from(expanded);
        return execution == null ? Stream.empty() : execution.tabComplete();
    }

    @Override
    public Stream<String> tabComplete(String prefix) {
        Pair<String, List<ICommandArgument>> pair = CommandRepository.expand(prefix, true);
        String label = (String)pair.getLeft();
        List args = (List)pair.getRight();
        if (args.isEmpty()) {
            return new TabCompleteHelper().addCommands(Babka.getInstance().getCommandRepository()).filterPrefix(label).stream();
        }
        return this.tabComplete(pair);
    }

    private ExecutionWrapper from(Pair<String, List<ICommandArgument>> expanded) {
        String label = (String)expanded.getLeft();
        ArgConsumer args = new ArgConsumer(this, (List)expanded.getRight());
        ICommand command = this.getCommand(label);
        return command == null ? null : new ExecutionWrapper(command, label, args);
    }

    private static Pair<String, List<ICommandArgument>> expand(String string, boolean preserveEmptyLast) {
        String label = string.split("\\s", 2)[0];
        List<ICommandArgument> args = CommandArguments.from(string.substring(label.length()), preserveEmptyLast);
        return new Pair((Object)label, args);
    }

    public static Pair<String, List<ICommandArgument>> expand(String string) {
        return CommandRepository.expand(string, false);
    }

    private static final class ExecutionWrapper {
        private ICommand command;
        private String label;
        private ArgConsumer args;

        private ExecutionWrapper(ICommand command, String label, ArgConsumer args) {
            this.command = command;
            this.label = label;
            this.args = args;
        }

        private void execute() {
            try {
                this.command.execute(this.label, this.args);
            }
            catch (Throwable t2) {
                ICommandException exception = t2 instanceof ICommandException ? (ICommandException)((Object)t2) : new CommandUnhandledException(t2);
                exception.handle(this.command, this.args.getArgs());
            }
        }

        private Stream<String> tabComplete() {
            try {
                return this.command.tabComplete(this.label, this.args);
            }
            catch (CommandException commandException) {
            }
            catch (Throwable t2) {
                t2.printStackTrace();
            }
            return Stream.empty();
        }
    }
}

