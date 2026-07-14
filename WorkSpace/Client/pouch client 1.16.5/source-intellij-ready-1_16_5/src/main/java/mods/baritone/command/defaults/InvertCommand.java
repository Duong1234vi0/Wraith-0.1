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
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidStateException;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalInverted;
import mods.baritone.api.api.java.baritone.api.process.ICustomGoalProcess;

public class InvertCommand
extends Command {
    public InvertCommand(IBaritone baritone) {
        super(baritone, "invert");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(0);
        ICustomGoalProcess customGoalProcess = this.baritone.getCustomGoalProcess();
        Goal goal = customGoalProcess.getGoal();
        if (goal == null) {
            throw new CommandInvalidStateException("No goal");
        }
        goal = goal instanceof GoalInverted ? ((GoalInverted)goal).origin : new GoalInverted(goal);
        customGoalProcess.setGoalAndPath(goal);
        this.logDirect(String.format("Goal: %s", goal.toString()));
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Run away from the current goal";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The invert command tells Baritone to head away from the current goal rather than towards it.", "", "Usage:", "> invert - Invert the current goal.");
    }
}

