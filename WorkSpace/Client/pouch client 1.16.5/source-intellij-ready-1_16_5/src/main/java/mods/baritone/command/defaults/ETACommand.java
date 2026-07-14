/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.command.defaults;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.behavior.IPathingBehavior;
import mods.baritone.api.api.java.baritone.api.command.Command;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandInvalidStateException;
import mods.baritone.api.api.java.baritone.api.pathing.calc.IPathingControlManager;
import mods.baritone.api.api.java.baritone.api.process.IBaritoneProcess;

public class ETACommand
extends Command {
    public ETACommand(IBaritone baritone) {
        super(baritone, "eta");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(0);
        IPathingControlManager pathingControlManager = this.baritone.getPathingControlManager();
        IBaritoneProcess process = pathingControlManager.mostRecentInControl().orElse(null);
        if (process == null) {
            throw new CommandInvalidStateException("No process in control");
        }
        IPathingBehavior pathingBehavior = this.baritone.getPathingBehavior();
        double ticksRemainingInSegment = pathingBehavior.ticksRemainingInSegment().orElse(Double.NaN);
        double ticksRemainingInGoal = pathingBehavior.estimatedTicksToGoal().orElse(Double.NaN);
        this.logDirect(String.format("Next segment: %.1fs (%.0f ticks)\nGoal: %.1fs (%.0f ticks)", ticksRemainingInSegment / 20.0, ticksRemainingInSegment, ticksRemainingInGoal / 20.0, ticksRemainingInGoal));
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "View the current ETA";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The ETA command provides information about the estimated time until the next segment.", "and the goal", "", "Be aware that the ETA to your goal is really unprecise", "", "Usage:", "> eta - View ETA, if present");
    }
}

