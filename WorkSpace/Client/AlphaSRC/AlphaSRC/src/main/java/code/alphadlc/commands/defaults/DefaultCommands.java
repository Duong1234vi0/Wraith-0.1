/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.commands.defaults;

import code.alphadlc.AlphaDLC;
import code.alphadlc.commands.defaults.BindCommand;
import code.alphadlc.commands.defaults.BlockESPCommand;
import code.alphadlc.commands.defaults.ConfigCommand;
import code.alphadlc.commands.defaults.DragCommand;
import code.alphadlc.commands.defaults.FakePlayerCommand;
import code.alphadlc.commands.defaults.FriendCommand;
import code.alphadlc.commands.defaults.HelpCommand;
import code.alphadlc.commands.defaults.IrcCommand;
import code.alphadlc.commands.defaults.MacroCommand;
import code.alphadlc.commands.defaults.PanicCommand;
import code.alphadlc.commands.defaults.PrefixCommand;
import code.alphadlc.commands.defaults.RCTCommand;
import code.alphadlc.commands.defaults.ServerCommand;
import code.alphadlc.commands.defaults.StaffCommand;
import code.alphadlc.commands.defaults.TargetCommand;
import code.alphadlc.commands.defaults.ThemeCommand;
import code.alphadlc.commands.defaults.VClipCommand;
import code.alphadlc.commands.defaults.WayCommand;
import code.alphadlc.utils.client.managers.api.command.Command;
import code.alphadlc.utils.client.managers.api.command.ICommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class DefaultCommands {
    public static List<ICommand> createAll() {
        AlphaDLC main = AlphaDLC.getInstance();
        ArrayList<Command> commands = new ArrayList<Command>(Arrays.asList(new ConfigCommand(main), new ThemeCommand(main), new MacroCommand(main), new BindCommand(main), new WayCommand(main), new FriendCommand(), new PrefixCommand(), new TargetCommand(), new StaffCommand(), new BlockESPCommand(), new RCTCommand(main), new DragCommand(), new FakePlayerCommand(), new VClipCommand(), new ServerCommand(), new IrcCommand(), new HelpCommand(main), new PanicCommand()));
        return Collections.unmodifiableList(commands);
    }
}
