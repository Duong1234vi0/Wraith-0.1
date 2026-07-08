/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3545
 */
package code.alphadlc.commands.manager;

import code.alphadlc.AlphaDLC;
import code.alphadlc.commands.argument.ArgConsumer;
import code.alphadlc.commands.argument.CommandArguments;
import code.alphadlc.commands.defaults.DefaultCommands;
import code.alphadlc.utils.client.managers.api.command.ICommand;
import code.alphadlc.utils.client.managers.api.command.argument.ICommandArgument;
import code.alphadlc.utils.client.managers.api.command.exception.CommandException;
import code.alphadlc.utils.client.managers.api.command.exception.CommandUnhandledException;
import code.alphadlc.utils.client.managers.api.command.exception.ICommandException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import code.alphadlc.utils.client.managers.api.command.manager.ICommandManager;
import code.alphadlc.utils.client.managers.api.command.registry.Registry;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import net.minecraft.class_3545;

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
    public boolean execute(class_3545<String, List<ICommandArgument>> expanded) {
        ExecutionWrapper execution = this.from(expanded);
        if (execution != null) {
            execution.execute();
        }
        return execution != null;
    }

    @Override
    public Stream<String> tabComplete(class_3545<String, List<ICommandArgument>> expanded) {
        ExecutionWrapper execution = this.from(expanded);
        return execution == null ? Stream.empty() : execution.tabComplete();
    }

    @Override
    public Stream<String> tabComplete(String prefix) {
        class_3545<String, List<ICommandArgument>> pair = CommandRepository.expand(prefix, true);
        String label = (String)pair.method_15442();
        List args = (List)pair.method_15441();
        if (args.isEmpty()) {
            return new TabCompleteHelper().addCommands(AlphaDLC.getInstance().getCommandRepository()).filterPrefix(label).stream();
        }
        return this.tabComplete(pair);
    }

    private ExecutionWrapper from(class_3545<String, List<ICommandArgument>> expanded) {
        String label = (String)expanded.method_15442();
        ArgConsumer args = new ArgConsumer(this, (List)expanded.method_15441());
        ICommand command = this.getCommand(label);
        return command == null ? null : new ExecutionWrapper(command, label, args);
    }

    private static class_3545<String, List<ICommandArgument>> expand(String string, boolean preserveEmptyLast) {
        String label = string.split("\\s", 2)[0];
        List<ICommandArgument> args = CommandArguments.from(string.substring(label.length()), preserveEmptyLast);
        return new class_3545((Object)label, args);
    }

    public static class_3545<String, List<ICommandArgument>> expand(String string) {
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
            catch (Throwable t) {
                ICommandException exception = t instanceof ICommandException ? (ICommandException)((Object)t) : new CommandUnhandledException(t);
                exception.handle(this.command, this.args.getArgs());
            }
        }

        private Stream<String> tabComplete() {
            try {
                return this.command.tabComplete(this.label, this.args);
            }
            catch (CommandException commandException) {
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            return Stream.empty();
        }
    }
}
