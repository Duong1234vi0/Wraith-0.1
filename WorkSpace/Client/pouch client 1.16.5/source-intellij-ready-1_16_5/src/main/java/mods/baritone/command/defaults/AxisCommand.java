/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalAxis;

public class AxisCommand
extends Command {
    public AxisCommand(IBaritone baritone) {
        super(baritone, "axis", "highway");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(0);
        GoalAxis goal = new GoalAxis();
        this.baritone.getCustomGoalProcess().setGoal(goal);
        this.logDirect(String.format("Goal: %s", ((Object)goal).toString()));
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Set a goal to the axes";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The axis command sets a goal that tells Baritone to head towards the nearest axis. That is, X=0 or Z=0.", "", "Usage:", "> axis");
    }
}

