/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.lang.invoke.LambdaMetafactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.D_4024_W;
import lightning.product.U_2871_b;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.x_282_a;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.cache.IWaypoint;
import mods.baritone.api.api.java.baritone.api.cache.IWaypointCollection;
import mods.baritone.api.api.java.baritone.api.cache.IWorldData;
import mods.baritone.api.api.java.baritone.api.cache.Waypoint;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.IBaritoneChatControl;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.ForWaypoints;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeBlockPos;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidStateException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidTypeException;
import mods.baritone.api.api.java.baritone.api.command.helpers.Paginator;
import mods.baritone.api.api.java.baritone.api.command.helpers.TabCompleteHelper;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public class WaypointsCommand
extends Command {
    private Map<IWorldData, List<IWaypoint>> deletedWaypoints = new HashMap<IWorldData, List<IWaypoint>>();

    public WaypointsCommand(IBaritone baritone) {
        super(baritone, "waypoints", "waypoint", "wp");
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        v0 = action = args.hasAny() != false ? Action.getByName(args.getString()) : Action.LIST;
        if (action == null) {
            throw new CommandInvalidTypeException(args.consumed(), "an action");
        }
        toComponent = (BiFunction<IWaypoint, Action, x_282_a>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, lambda$execute$0(java.lang.String mods.baritone.api.api.java.baritone.api.cache.IWaypoint mods.baritone.command.defaults.WaypointsCommand$Action ), (Lmods/baritone/api/api/java/baritone/api/cache/IWaypoint;Lmods/baritone/command/defaults/WaypointsCommand$Action;)Llightning/product/x_282_a;)((String)label);
        transform = (Function<IWaypoint, x_282_a>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$execute$1(java.util.function.BiFunction mods.baritone.command.defaults.WaypointsCommand$Action mods.baritone.api.api.java.baritone.api.cache.IWaypoint ), (Lmods/baritone/api/api/java/baritone/api/cache/IWaypoint;)Llightning/product/x_282_a;)(toComponent, (Action)action);
        if (action != Action.LIST) ** GOTO lbl18
        v1 = tag = args.hasAny() != false ? IWaypoint.Tag.getByName(args.peekString()) : null;
        if (tag != null) {
            args.get();
        }
        v2 = waypoints = tag != null ? ForWaypoints.getWaypointsByTag(this.baritone, tag) : ForWaypoints.getWaypoints(this.baritone);
        if (waypoints.length > 0) {
            args.requireMax(1);
            Paginator.paginate(args, waypoints, (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$execute$2(mods.baritone.api.api.java.baritone.api.cache.IWaypoint$Tag ), ()V)((WaypointsCommand)this, (IWaypoint.Tag)tag), transform, String.format("%s%s %s%s", new Object[]{IBaritoneChatControl.FORCE_COMMAND_PREFIX, label, action.names[0], tag != null ? " " + tag.getName() : ""}));
        } else {
            args.requireMax(0);
            throw new CommandInvalidStateException(tag != null ? "No waypoints found by that tag" : "No waypoints found");
lbl18:
            // 1 sources

            if (action == Action.SAVE) {
                v3 = tag = args.hasAny() != false ? IWaypoint.Tag.getByName(args.peekString()) : null;
                if (tag == null) {
                    tag = IWaypoint.Tag.USER;
                } else {
                    args.get();
                }
                name = args.hasExactlyOne() != false || args.hasExactly(4) != false ? args.getString() : "";
                pos = args.hasAny() != false ? (BetterBlockPos)args.getDatatypePost(RelativeBlockPos.INSTANCE, this.ctx.playerFeet()) : this.ctx.playerFeet();
                args.requireMax(0);
                waypoint = new Waypoint(name, tag, pos);
                ForWaypoints.waypoints(this.baritone).addWaypoint(waypoint);
                component = new U_2871_b("Waypoint added: ");
                component.n_1700_B(component.n_1700_B().n_1700_B(D_4024_W.w_1484_f));
                component.n_1700_B(toComponent.apply(waypoint, Action.INFO));
                this.logDirect(new x_282_a[]{component});
            } else if (action == Action.CLEAR) {
                args.requireMax(1);
                name = args.getString();
                tag = IWaypoint.Tag.getByName(name);
                if (tag == null) {
                    throw new CommandInvalidStateException("Invalid tag, \"" + name + "\"");
                }
                for (IWaypoint waypoint : waypoints = ForWaypoints.getWaypointsByTag(this.baritone, tag)) {
                    ForWaypoints.waypoints(this.baritone).removeWaypoint(waypoint);
                }
                this.deletedWaypoints.computeIfAbsent(this.baritone.getWorldProvider().getCurrentWorld(), (Function<IWorldData, List>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$execute$3(mods.baritone.api.api.java.baritone.api.cache.IWorldData ), (Lmods/baritone/api/api/java/baritone/api/cache/IWorldData;)Ljava/util/List;)()).addAll(Arrays.asList(waypoints));
                textComponent = new U_2871_b(String.format("Cleared %d waypoints, click to restore them", new Object[]{waypoints.length}));
                textComponent.n_1700_B(textComponent.n_1700_B().n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, String.format("%s%s restore @ %s", new Object[]{IBaritoneChatControl.FORCE_COMMAND_PREFIX, label, Stream.of(waypoints).map((Function<IWaypoint, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$execute$4(mods.baritone.api.api.java.baritone.api.cache.IWaypoint ), (Lmods/baritone/api/api/java/baritone/api/cache/IWaypoint;)Ljava/lang/String;)()).collect(Collectors.joining(" "))}))));
                this.logDirect(new x_282_a[]{textComponent});
            } else if (action == Action.RESTORE) {
                waypoints = new ArrayList<E>();
                deletedWaypoints = this.deletedWaypoints.getOrDefault(this.baritone.getWorldProvider().getCurrentWorld(), Collections.emptyList());
                if (args.peekString().equals("@")) {
                    args.get();
                    block5: while (args.hasAny()) {
                        timestamp = args.getAs(Long.class);
                        for (IWaypoint waypoint : deletedWaypoints) {
                            if (waypoint.getCreationTimestamp() != timestamp) continue;
                            waypoints.add(waypoint);
                            continue block5;
                        }
                    }
                } else {
                    args.requireExactly(1);
                    size = deletedWaypoints.size();
                    amount = Math.min(size, args.getAs(Integer.class));
                    waypoints = new ArrayList<T>(deletedWaypoints.subList(size - amount, size));
                }
                waypoints.forEach((Consumer<IWaypoint>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)V, addWaypoint(mods.baritone.api.api.java.baritone.api.cache.IWaypoint ), (Lmods/baritone/api/api/java/baritone/api/cache/IWaypoint;)V)((IWaypointCollection)ForWaypoints.waypoints(this.baritone)));
                deletedWaypoints.removeIf((Predicate<IWaypoint>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, contains(java.lang.Object ), (Lmods/baritone/api/api/java/baritone/api/cache/IWaypoint;)Z)(waypoints));
                this.logDirect(String.format("Restored %d waypoints", new Object[]{waypoints.size()}));
            } else {
                waypoints = (IWaypoint[])args.getDatatypeFor(ForWaypoints.INSTANCE);
                waypoint = null;
                if (args.hasAny() && args.peekString().equals("@")) {
                    args.requireExactly(2);
                    args.get();
                    timestamp = args.getAs(Long.class);
                    for (IWaypoint iWaypoint : waypoints) {
                        if (iWaypoint.getCreationTimestamp() != timestamp) continue;
                        waypoint = iWaypoint;
                        break;
                    }
                    if (waypoint == null) {
                        throw new CommandInvalidStateException("Timestamp was specified but no waypoint was found");
                    }
                } else {
                    switch (waypoints.length) {
                        case 0: {
                            throw new CommandInvalidStateException("No waypoints found");
                        }
                        case 1: {
                            waypoint = waypoints[0];
                            break;
                        }
                    }
                }
                if (waypoint == null) {
                    args.requireMax(1);
                    Paginator.paginate(args, waypoints, (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$execute$5(), ()V)((WaypointsCommand)this), transform, String.format("%s%s %s %s", new Object[]{IBaritoneChatControl.FORCE_COMMAND_PREFIX, label, action.names[0], args.consumedString()}));
                } else if (action == Action.INFO) {
                    this.logDirect(new x_282_a[]{transform.apply(waypoint)});
                    this.logDirect(String.format("Position: %s", new Object[]{waypoint.getLocation()}));
                    deleteComponent = new U_2871_b("Click to delete this waypoint");
                    deleteComponent.n_1700_B(deleteComponent.n_1700_B().n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, String.format("%s%s delete %s @ %d", new Object[]{IBaritoneChatControl.FORCE_COMMAND_PREFIX, label, waypoint.getTag().getName(), waypoint.getCreationTimestamp()}))));
                    goalComponent = new U_2871_b("Click to set goal to this waypoint");
                    goalComponent.n_1700_B(goalComponent.n_1700_B().n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, String.format("%s%s goal %s @ %d", new Object[]{IBaritoneChatControl.FORCE_COMMAND_PREFIX, label, waypoint.getTag().getName(), waypoint.getCreationTimestamp()}))));
                    recreateComponent = new U_2871_b("Click to show a command to recreate this waypoint");
                    recreateComponent.n_1700_B(recreateComponent.n_1700_B().n_1700_B(new i_2909_p(i_2909_p.n_1700_B.G_564_y, String.format("%s%s save %s %s %s %s %s", new Object[]{Baritone.settings().prefix.value, label, waypoint.getTag().getName(), waypoint.getName(), waypoint.getLocation().x, waypoint.getLocation().y, waypoint.getLocation().z}))));
                    backComponent = new U_2871_b("Click to return to the waypoints list");
                    backComponent.n_1700_B(backComponent.n_1700_B().n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, String.format("%s%s list", new Object[]{IBaritoneChatControl.FORCE_COMMAND_PREFIX, label}))));
                    this.logDirect(new x_282_a[]{deleteComponent});
                    this.logDirect(new x_282_a[]{goalComponent});
                    this.logDirect(new x_282_a[]{recreateComponent});
                    this.logDirect(new x_282_a[]{backComponent});
                } else if (action == Action.DELETE) {
                    ForWaypoints.waypoints(this.baritone).removeWaypoint(waypoint);
                    this.deletedWaypoints.computeIfAbsent(this.baritone.getWorldProvider().getCurrentWorld(), (Function<IWorldData, List>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$execute$6(mods.baritone.api.api.java.baritone.api.cache.IWorldData ), (Lmods/baritone/api/api/java/baritone/api/cache/IWorldData;)Ljava/util/List;)()).add(waypoint);
                    textComponent = new U_2871_b("That waypoint has successfully been deleted, click to restore it");
                    textComponent.n_1700_B(textComponent.n_1700_B().n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, String.format("%s%s restore @ %s", new Object[]{IBaritoneChatControl.FORCE_COMMAND_PREFIX, label, waypoint.getCreationTimestamp()}))));
                    this.logDirect(new x_282_a[]{textComponent});
                } else if (action == Action.GOAL) {
                    goal = new GoalBlock(waypoint.getLocation());
                    this.baritone.getCustomGoalProcess().setGoal(goal);
                    this.logDirect(String.format("Goal: %s", new Object[]{goal}));
                } else if (action == Action.GOTO) {
                    goal = new GoalBlock(waypoint.getLocation());
                    this.baritone.getCustomGoalProcess().setGoalAndPath(goal);
                    this.logDirect(String.format("Going to: %s", new Object[]{goal}));
                }
            }
        }
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny()) {
            if (args.hasExactlyOne()) {
                return new TabCompleteHelper().append(Action.getAllNames()).sortAlphabetically().filterPrefix(args.getString()).stream();
            }
            Action action = Action.getByName(args.getString());
            if (args.hasExactlyOne()) {
                if (action == Action.LIST || action == Action.SAVE || action == Action.CLEAR) {
                    return new TabCompleteHelper().append(IWaypoint.Tag.getAllNames()).sortAlphabetically().filterPrefix(args.getString()).stream();
                }
                if (action == Action.RESTORE) {
                    return Stream.empty();
                }
                return args.tabCompleteDatatype(ForWaypoints.INSTANCE);
            }
            if (args.has(3) && action == Action.SAVE) {
                args.get();
                args.get();
                return args.tabCompleteDatatype(RelativeBlockPos.INSTANCE);
            }
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Manage waypoints";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The waypoint command allows you to manage Baritone's waypoints.", "", "Waypoints can be used to mark positions for later. Waypoints are each given a tag and an optional name.", "", "Note that the info, delete, and goal commands let you specify a waypoint by tag. If there is more than one waypoint with a certain tag, then they will let you select which waypoint you mean.", "", "Missing arguments for the save command use the USER tag, creating an unnamed waypoint and your current position as defaults.", "", "Usage:", "> wp [l/list] - List all waypoints.", "> wp <l/list> <tag> - List all waypoints by tag.", "> wp <s/save> - Save an unnamed USER waypoint at your current position", "> wp <s/save> [tag] [name] [pos] - Save a waypoint with the specified tag, name and position.", "> wp <i/info/show> <tag/name> - Show info on a waypoint by tag or name.", "> wp <d/delete> <tag/name> - Delete a waypoint by tag or name.", "> wp <restore> <n> - Restore the last n deleted waypoints.", "> wp <c/clear> <tag> - Delete all waypoints with the specified tag.", "> wp <g/goal> <tag/name> - Set a goal to a waypoint by tag or name.", "> wp <goto> <tag/name> - Set a goal to a waypoint by tag or name and start pathing.");
    }

    private static /* synthetic */ List lambda$execute$6(IWorldData k) {
        return new ArrayList();
    }

    private /* synthetic */ void lambda$execute$5() {
        this.logDirect("Multiple waypoints were found:");
    }

    private static /* synthetic */ String lambda$execute$4(IWaypoint wp) {
        return Long.toString(wp.getCreationTimestamp());
    }

    private static /* synthetic */ List lambda$execute$3(IWorldData k) {
        return new ArrayList();
    }

    private /* synthetic */ void lambda$execute$2(IWaypoint.Tag tag) {
        this.logDirect(tag != null ? String.format("All waypoints by tag %s:", tag.name()) : "All waypoints:");
    }

    private static /* synthetic */ x_282_a lambda$execute$1(BiFunction toComponent, Action action, IWaypoint waypoint) {
        return (x_282_a)toComponent.apply(waypoint, action == Action.LIST ? Action.INFO : action);
    }

    private static /* synthetic */ x_282_a lambda$execute$0(String label, IWaypoint waypoint, Action _action) {
        U_2871_b component = new U_2871_b("");
        U_2871_b tagComponent = new U_2871_b(waypoint.getTag().name() + " ");
        tagComponent.n_1700_B(tagComponent.n_1700_B().n_1700_B(D_4024_W.w_1484_f));
        String name = waypoint.getName();
        U_2871_b nameComponent = new U_2871_b(!name.isEmpty() ? name : "<empty>");
        nameComponent.n_1700_B(nameComponent.n_1700_B().n_1700_B(!name.isEmpty() ? D_4024_W.w_1484_f : D_4024_W.t_148_a));
        U_2871_b timestamp = new U_2871_b(" @ " + String.valueOf(new Date(waypoint.getCreationTimestamp())));
        timestamp.n_1700_B(timestamp.n_1700_B().n_1700_B(D_4024_W.t_148_a));
        component.n_1700_B(tagComponent);
        component.n_1700_B(nameComponent);
        component.n_1700_B(timestamp);
        component.n_1700_B(component.n_1700_B().n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("Click to select"))).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, String.format("%s%s %s %s @ %d", IBaritoneChatControl.FORCE_COMMAND_PREFIX, label, _action.names[0], waypoint.getTag().getName(), waypoint.getCreationTimestamp()))));
        return component;
    }

    private static enum Action {
        LIST("list", "get", "l"),
        CLEAR("clear", "c"),
        SAVE("save", "s"),
        INFO("info", "show", "i"),
        DELETE("delete", "d"),
        RESTORE("restore"),
        GOAL("goal", "g"),
        GOTO("goto");

        private final String[] names;

        private Action(String ... names) {
            this.names = names;
        }

        public static Action getByName(String name) {
            for (Action action : Action.values()) {
                for (String alias : action.names) {
                    if (!alias.equalsIgnoreCase(name)) continue;
                    return action;
                }
            }
            return null;
        }

        public static String[] getAllNames() {
            HashSet<String> names = new HashSet<String>();
            for (Action action : Action.values()) {
                names.addAll(Arrays.asList(action.names));
            }
            return names.toArray(new String[0]);
        }
    }
}

