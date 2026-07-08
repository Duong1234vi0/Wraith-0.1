/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3545
 */
package code.alphadlc.commands;

import code.alphadlc.AlphaDLC;
import code.alphadlc.commands.argument.ArgConsumer;
import code.alphadlc.commands.argument.CommandArguments;
import code.alphadlc.commands.manager.CommandRepository;
import code.alphadlc.events.chat.ChatEvent;
import code.alphadlc.events.chat.TabCompleteEvent;
import code.alphadlc.features.impl.misc.Panic;
import code.alphadlc.utils.client.managers.api.command.IBaritoneChatControl;
import code.alphadlc.utils.client.managers.api.command.argument.ICommandArgument;
import code.alphadlc.utils.client.managers.api.command.exception.CommandNotEnoughArgumentsException;
import code.alphadlc.utils.client.managers.api.command.exception.CommandNotFoundException;
import code.alphadlc.utils.client.managers.api.command.helpers.TabCompleteHelper;
import code.alphadlc.utils.client.managers.api.command.manager.ICommandManager;
import code.alphadlc.utils.client.managers.event.EventHandler;
import code.alphadlc.utils.client.managers.event.EventManager;
import code.alphadlc.utils.display.interfaces.QuickLogger;
import code.alphadlc.utils.interactions.path.BaritoneHelper;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_3545;

public class CommandDispatcher
implements QuickLogger {
    private final ICommandManager manager = AlphaDLC.getInstance().getCommandRepository();
    public static String prefix = ".";

    public CommandDispatcher(EventManager eventManager) {
        EventManager.register(this);
    }

    @EventHandler
    public void onChat(ChatEvent event) {
        String msg = event.getMessage();
        if (Panic.isPanicMode()) {
            if (msg.equals(Panic.getRecoveryCode())) {
                return;
            }
            return;
        }
        if (msg.startsWith("/")) {
            return;
        }
        if (BaritoneHelper.handleChatCommand(msg)) {
            event.cancel();
            return;
        }
        boolean forceRun = msg.startsWith(IBaritoneChatControl.FORCE_COMMAND_PREFIX);
        if (msg.startsWith(prefix) || forceRun) {
            event.cancel();
            String commandStr = msg.substring(forceRun ? IBaritoneChatControl.FORCE_COMMAND_PREFIX.length() : prefix.length());
            if (!this.runCommand(commandStr) && !commandStr.trim().isEmpty()) {
                new CommandNotFoundException((String)CommandRepository.expand(commandStr).method_15442()).handle(null, null);
            }
        }
    }

    public boolean runCommand(String msg) {
        if (msg.isEmpty()) {
            return this.runCommand("help");
        }
        class_3545<String, List<ICommandArgument>> pair = CommandRepository.expand(msg);
        String command = (String)pair.method_15442();
        String rest = msg.substring(((String)pair.method_15442()).length());
        ArgConsumer argc = new ArgConsumer(this.manager, (List)pair.method_15441());
        return this.manager.execute(pair);
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        if (Panic.isPanicMode()) {
            return;
        }
        String eventPrefix = event.prefix;
        if (!eventPrefix.startsWith(prefix)) {
            return;
        }
        String msg = eventPrefix.substring(prefix.length());
        List<ICommandArgument> args = CommandArguments.from(msg, true);
        Stream<String> stream = this.tabComplete(msg);
        if (args.size() == 1) {
            stream = stream.map(x -> prefix + x);
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
