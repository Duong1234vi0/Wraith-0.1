package ua.babka.command;

import com.google.common.eventbus.Subscribe;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.util.Pair;
import ua.babka.Babka;
import ua.babka.baritone.IBaritoneChatControl;
import ua.babka.command.CommandArguments;
import ua.babka.command.CommandNotEnoughArgumentsException;
import ua.babka.command.CommandNotFoundException;
import ua.babka.command.CommandRepository;
import ua.babka.command.ICommandManager;
import ua.babka.command.TabCompleteHelper;
import ua.babka.command.argument.ArgConsumer;
import ua.babka.command.argument.ICommandArgument;
import ua.babka.event.ChatEvent;
import ua.babka.event.TabCompleteEvent;

public class CommandDispatcher {
    private final ICommandManager manager = Babka.getInstance().getCommandRepository();

    public CommandDispatcher() {
        Babka.getInstance().getEventBus().register((Object)this);
    }

    @Subscribe
    public void onSendChatMessage(ChatEvent event) {
        String msg = event.getMessage();
        String prefix = ".";
        boolean forceRun = msg.startsWith(IBaritoneChatControl.FORCE_COMMAND_PREFIX);
        if (msg.startsWith(prefix) || forceRun) {
            event.setCancelled(true);
            String commandStr = msg.substring(forceRun ? IBaritoneChatControl.FORCE_COMMAND_PREFIX.length() : prefix.length());
            if (!this.runCommand(commandStr) && !commandStr.trim().isEmpty()) {
                new CommandNotFoundException((String)CommandRepository.expand(commandStr).getLeft()).handle(null, null);
            }
        }
    }

    public boolean runCommand(String msg) {
        if (msg.isEmpty()) {
            return this.runCommand("help");
        }
        Pair<String, List<ICommandArgument>> pair = CommandRepository.expand(msg);
        return this.manager.execute(pair);
    }

    @Subscribe
    public void onPreTabComplete(TabCompleteEvent event) {
        String commandPrefix;
        String prefix = event.getPrefix();
        if (!prefix.startsWith(commandPrefix = ".")) {
            return;
        }
        String msg = prefix.substring(commandPrefix.length());
        List<ICommandArgument> args = CommandArguments.from(msg, true);
        Stream<String> stream = this.tabComplete(msg);
        if (args.size() == 1) {
            stream = stream.map(x2 -> commandPrefix + x2);
        }
        event.completions = (String[])stream.toArray(String[]::new);
    }

    public Stream<String> tabComplete(String msg) {
        try {
            List<ICommandArgument> args = CommandArguments.from(msg, true);
            ArgConsumer argc = new ArgConsumer(this.manager, args);
            if (argc.hasAtMost(2) && argc.hasExactly(1)) {
                return new TabCompleteHelper().addCommands(this.manager).filterPrefix(argc.getString()).stream();
            }
            return this.manager.tabComplete(msg);
        }
        catch (CommandNotEnoughArgumentsException ignored) {
            return Stream.empty();
        }
    }
}

