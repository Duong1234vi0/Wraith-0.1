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
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeGoalXZ;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalXZ;

public class ExploreCommand
extends Command {
    public ExploreCommand(IBaritone baritone) {
        super(baritone, "explore");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        if (args.hasAny()) {
            args.requireExactly(2);
        } else {
            args.requireMax(0);
        }
        GoalXZ goal = args.hasAny() ? (GoalXZ)args.getDatatypePost(RelativeGoalXZ.INSTANCE, this.ctx.playerFeet()) : new GoalXZ(this.ctx.playerFeet());
        this.baritone.getExploreProcess().explore(goal.getX(), goal.getZ());
        this.logDirect(String.format("Exploring from %s", goal.toString()));
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) {
        if (args.hasAtMost(2)) {
            return args.tabCompleteDatatype(RelativeGoalXZ.INSTANCE);
        }
        return Stream.empty();
    }

    @Override
    public String getShortDesc() {
        return "Explore things";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("Tell Baritone to explore randomly. If you used explorefilter before this, it will be applied.", "", "Usage:", "> explore - Explore from your current position.", "> explore <x> <z> - Explore from the specified X and Z position.");
    }
}

