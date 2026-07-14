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
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;

public class ComeCommand
extends Command {
    public ComeCommand(IBaritone baritone) {
        super(baritone, "come");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        args.requireMax(0);
        this.baritone.getCustomGoalProcess().setGoalAndPath(new GoalBlock(this.ctx.viewerPos()));
        this.logDirect("Coming");
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Start heading towards your camera";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The come command tells Baritone to head towards your camera.", "", "This can be useful in hacked clients where freecam doesn't move your player position.", "", "Usage:", "> come");
    }
}

