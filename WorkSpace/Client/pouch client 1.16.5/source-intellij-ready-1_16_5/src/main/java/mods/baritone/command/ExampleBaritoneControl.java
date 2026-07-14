/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import lightning.product.D_4024_W;
import lightning.product.Tuple;
import lightning.product.U_2871_b;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.Settings;
import mods.baritone.api.api.java.baritone.api.command.IBaritoneChatControl;
import mods.baritone.api.api.java.baritone.api.command.argument.ICommandArgument;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandNotEnoughArgumentsException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandNotFoundException;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;
import mods.baritone.api.api.java.baritone.api.command.manager.ICommandManager;
import mods.baritone.api.api.java.baritone.api.event.events.ChatEvent;
import mods.baritone.api.api.java.baritone.api.event.events.TabCompleteEvent;
import mods.baritone.api.api.java.baritone.api.utils.Helper;
import mods.baritone.api.api.java.baritone.api.utils.SettingsUtil;
import mods.baritone.behavior.Behavior;
import mods.baritone.command.argument.ArgConsumer;
import mods.baritone.command.argument.CommandArguments;
import mods.baritone.command.manager.CommandManager;

public class ExampleBaritoneControl
extends Behavior
implements Helper {
    private static final Settings settings = BaritoneAPI.getSettings();
    private final ICommandManager manager;

    public ExampleBaritoneControl(Baritone baritone) {
        super(baritone);
        this.manager = baritone.getCommandManager();
    }

    @Override
    public void onSendChatMessage(ChatEvent event) {
        String msg = event.getMessage();
        String prefix = (String)ExampleBaritoneControl.settings.prefix.value;
        boolean forceRun = msg.startsWith(IBaritoneChatControl.FORCE_COMMAND_PREFIX);
        if (((Boolean)ExampleBaritoneControl.settings.prefixControl.value).booleanValue() && msg.startsWith(prefix) || forceRun) {
            event.cancel();
            String commandStr = msg.substring(forceRun ? IBaritoneChatControl.FORCE_COMMAND_PREFIX.length() : prefix.length());
            if (!this.runCommand(commandStr) && !commandStr.trim().isEmpty()) {
                new CommandNotFoundException(CommandManager.expand(commandStr).n_1700_B()).handle(null, null);
            }
        } else if ((((Boolean)ExampleBaritoneControl.settings.chatControl.value).booleanValue() || ((Boolean)ExampleBaritoneControl.settings.chatControlAnyway.value).booleanValue()) && this.runCommand(msg)) {
            event.cancel();
        }
    }

    private void logRanCommand(String command, String rest) {
        if (((Boolean)ExampleBaritoneControl.settings.echoCommands.value).booleanValue()) {
            String msg = command + rest;
            String toDisplay = (Boolean)ExampleBaritoneControl.settings.censorRanCommands.value != false ? command + " ..." : msg;
            U_2871_b component = new U_2871_b(String.format("> %s", toDisplay));
            component.n_1700_B(component.n_1700_B().n_1700_B(D_4024_W.M_182_A).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("Click to rerun command"))).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, IBaritoneChatControl.FORCE_COMMAND_PREFIX + msg)));
            this.logDirect(component);
        }
    }

    public boolean runCommand(String msg) {
        if (msg.trim().equalsIgnoreCase("damn")) {
            this.logDirect("daniel");
            return false;
        }
        if (msg.trim().equalsIgnoreCase("orderpizza")) {
            try {
                this.ctx.minecraft().Y_1740_V.openLinkInvoker(new URI("https://www.dominos.com/en/pages/order/"));
            }
            catch (NullPointerException | URISyntaxException exception) {
                // empty catch block
            }
            return false;
        }
        if (msg.isEmpty()) {
            return this.runCommand("help");
        }
        Tuple<String, List<ICommandArgument>> pair = CommandManager.expand(msg);
        String command = pair.n_1700_B();
        String rest = msg.substring(pair.n_1700_B().length());
        ArgConsumer argc = new ArgConsumer(this.manager, pair.J_1907_R());
        if (!argc.hasAny()) {
            Settings.Setting<?> setting = ExampleBaritoneControl.settings.byLowerName.get(command.toLowerCase(Locale.US));
            if (setting != null) {
                this.logRanCommand(command, rest);
                if (setting.getValueClass() == Boolean.class) {
                    this.manager.execute(String.format("set toggle %s", setting.getName()));
                } else {
                    this.manager.execute(String.format("set %s", setting.getName()));
                }
                return true;
            }
        } else if (argc.hasExactlyOne()) {
            for (Settings.Setting<?> setting : ExampleBaritoneControl.settings.allSettings) {
                if (setting.isJavaOnly() || !setting.getName().equalsIgnoreCase(pair.n_1700_B())) continue;
                this.logRanCommand(command, rest);
                try {
                    this.manager.execute(String.format("set %s %s", setting.getName(), argc.getString()));
                }
                catch (CommandNotEnoughArgumentsException commandNotEnoughArgumentsException) {
                    // empty catch block
                }
                return true;
            }
        }
        if (this.manager.getCommand(pair.n_1700_B()) != null) {
            this.logRanCommand(command, rest);
        }
        return this.manager.execute(pair);
    }

    @Override
    public void onPreTabComplete(TabCompleteEvent event) {
        if (!((Boolean)ExampleBaritoneControl.settings.prefixControl.value).booleanValue()) {
            return;
        }
        String prefix = event.prefix;
        String commandPrefix = (String)ExampleBaritoneControl.settings.prefix.value;
        if (!prefix.startsWith(commandPrefix)) {
            return;
        }
        String msg = prefix.substring(commandPrefix.length());
        List<ICommandArgument> args = CommandArguments.from(msg, true);
        Stream<String> stream = this.tabComplete(msg);
        if (args.size() == 1) {
            stream = stream.map(x -> commandPrefix + x);
        }
        event.completions = (String[])stream.toArray(String[]::new);
    }

    public Stream<String> tabComplete(String msg) {
        try {
            List<ICommandArgument> args = CommandArguments.from(msg, true);
            ArgConsumer argc = new ArgConsumer(this.manager, args);
            if (argc.hasAtMost(2)) {
                if (argc.hasExactly(1)) {
                    return new TabCompleteHelper().addCommands(this.manager).addSettings().filterPrefix(argc.getString()).stream();
                }
                Settings.Setting<?> setting = ExampleBaritoneControl.settings.byLowerName.get(argc.getString().toLowerCase(Locale.US));
                if (setting != null && !setting.isJavaOnly()) {
                    if (setting.getValueClass() == Boolean.class) {
                        TabCompleteHelper helper = new TabCompleteHelper();
                        if (((Boolean)setting.value).booleanValue()) {
                            helper.append("true", "false");
                        } else {
                            helper.append("false", "true");
                        }
                        return helper.filterPrefix(argc.getString()).stream();
                    }
                    return Stream.of(SettingsUtil.settingValueToString(setting));
                }
            }
            return this.manager.tabComplete(msg);
        }
        catch (CommandNotEnoughArgumentsException ignored) {
            return Stream.empty();
        }
    }
}


