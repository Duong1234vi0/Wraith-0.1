package ua.babka.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ua.babka.Babka;
import ua.babka.command.impl.BindCommand;
import ua.babka.command.impl.BotCommand;
import ua.babka.command.impl.CfgCommand;
import ua.babka.command.impl.Command;
import ua.babka.command.impl.FriendCommand;
import ua.babka.command.impl.GpsCommand;
import ua.babka.command.impl.HelpCommand;
import ua.babka.command.impl.ICommand;
import ua.babka.command.impl.MacroCommand;
import ua.babka.command.impl.NeuroCommand;
import ua.babka.command.impl.RotationCommand;
import ua.babka.command.impl.StaffCommand;
import ua.babka.command.impl.TargetCommand;
import ua.babka.command.impl.TestCommand;
import ua.babka.command.impl.VClipCommand;

public final class DefaultCommands {
    public static List<ICommand> createAll() {
        ArrayList<Command> commands = new ArrayList<Command>(Arrays.asList(new CfgCommand(), new RotationCommand(), new HelpCommand(Babka.getInstance()), new MacroCommand(Babka.getInstance()), new BindCommand(Babka.getInstance()), new FriendCommand(Babka.getInstance()), new TestCommand(Babka.getInstance()), new StaffCommand(Babka.getInstance()), new VClipCommand(), new GpsCommand(), new BotCommand(), new TargetCommand(), new NeuroCommand()));
        return Collections.unmodifiableList(commands);
    }
}

