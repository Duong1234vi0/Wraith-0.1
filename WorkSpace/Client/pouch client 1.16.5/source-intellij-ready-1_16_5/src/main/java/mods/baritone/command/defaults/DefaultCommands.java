/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.ICommand;
import mods.baritone.command.defaults.AxisCommand;
import mods.baritone.command.defaults.BlacklistCommand;
import mods.baritone.command.defaults.BuildCommand;
import mods.baritone.command.defaults.ClickCommand;
import mods.baritone.command.defaults.ComeCommand;
import mods.baritone.command.defaults.CommandAlias;
import mods.baritone.command.defaults.ETACommand;
import mods.baritone.command.defaults.ExecutionControlCommands;
import mods.baritone.command.defaults.ExploreCommand;
import mods.baritone.command.defaults.ExploreFilterCommand;
import mods.baritone.command.defaults.FarmCommand;
import mods.baritone.command.defaults.FindCommand;
import mods.baritone.command.defaults.FollowCommand;
import mods.baritone.command.defaults.ForceCancelCommand;
import mods.baritone.command.defaults.GcCommand;
import mods.baritone.command.defaults.GoalCommand;
import mods.baritone.command.defaults.GotoCommand;
import mods.baritone.command.defaults.HelpCommand;
import mods.baritone.command.defaults.InvertCommand;
import mods.baritone.command.defaults.LitematicaCommand;
import mods.baritone.command.defaults.MineCommand;
import mods.baritone.command.defaults.PathCommand;
import mods.baritone.command.defaults.ProcCommand;
import mods.baritone.command.defaults.ReloadAllCommand;
import mods.baritone.command.defaults.RenderCommand;
import mods.baritone.command.defaults.RepackCommand;
import mods.baritone.command.defaults.SaveAllCommand;
import mods.baritone.command.defaults.SelCommand;
import mods.baritone.command.defaults.SetCommand;
import mods.baritone.command.defaults.SurfaceCommand;
import mods.baritone.command.defaults.ThisWayCommand;
import mods.baritone.command.defaults.TunnelCommand;
import mods.baritone.command.defaults.VersionCommand;
import mods.baritone.command.defaults.WaypointsCommand;

public final class DefaultCommands {
    private DefaultCommands() {
    }

    public static List<ICommand> createAll(IBaritone baritone) {
        Objects.requireNonNull(baritone);
        ArrayList<Command> commands = new ArrayList<Command>(Arrays.asList(new HelpCommand(baritone), new SetCommand(baritone), new CommandAlias(baritone, Arrays.asList("modified", "mod", "baritone", "modifiedsettings"), "List modified settings", "set modified"), new CommandAlias(baritone, "reset", "Reset all settings or just one", "set reset"), new GoalCommand(baritone), new GotoCommand(baritone), new PathCommand(baritone), new ProcCommand(baritone), new ETACommand(baritone), new VersionCommand(baritone), new RepackCommand(baritone), new BuildCommand(baritone), new LitematicaCommand(baritone), new ComeCommand(baritone), new AxisCommand(baritone), new ForceCancelCommand(baritone), new GcCommand(baritone), new InvertCommand(baritone), new TunnelCommand(baritone), new RenderCommand(baritone), new FarmCommand(baritone), new FollowCommand(baritone), new ExploreFilterCommand(baritone), new ReloadAllCommand(baritone), new SaveAllCommand(baritone), new ExploreCommand(baritone), new BlacklistCommand(baritone), new FindCommand(baritone), new MineCommand(baritone), new ClickCommand(baritone), new SurfaceCommand(baritone), new ThisWayCommand(baritone), new WaypointsCommand(baritone), new CommandAlias(baritone, "sethome", "Sets your home waypoint", "waypoints save home"), new CommandAlias(baritone, "home", "Path to your home waypoint", "waypoints goto home"), new SelCommand(baritone)));
        ExecutionControlCommands prc = new ExecutionControlCommands(baritone);
        commands.add(prc.pauseCommand);
        commands.add(prc.resumeCommand);
        commands.add(prc.pausedCommand);
        commands.add(prc.cancelCommand);
        return Collections.unmodifiableList(commands);
    }
}

