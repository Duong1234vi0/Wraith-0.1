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
import mods.baritone.api.api.java.baritone.api.command.datatypes.ForBlockOptionalMeta;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeCoordinate;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeGoal;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.BlockOptionalMeta;

public class GotoCommand
extends Command {
    protected GotoCommand(IBaritone baritone) {
        super(baritone, "goto");
    }

    @Override
    public void execute(String label, IArgConsumer args) throws CommandException {
        if (args.peekDatatypeOrNull(RelativeCoordinate.INSTANCE) != null) {
            args.requireMax(3);
            BetterBlockPos origin = this.ctx.playerFeet();
            Goal goal = (Goal)args.getDatatypePost(RelativeGoal.INSTANCE, origin);
            this.logDirect(String.format("Going to: %s", goal.toString()));
            this.baritone.getCustomGoalProcess().setGoalAndPath(goal);
            return;
        }
        args.requireMax(1);
        BlockOptionalMeta destination = (BlockOptionalMeta)args.getDatatypeFor(ForBlockOptionalMeta.INSTANCE);
        this.baritone.getGetToBlockProcess().getToBlock(destination);
    }

    @Override
    public Stream<String> tabComplete(String label, IArgConsumer args) throws CommandException {
        args.requireMax(1);
        return args.tabCompleteDatatype(ForBlockOptionalMeta.INSTANCE);
    }

    @Override
    public String getShortDesc() {
        return "Go to a coordinate or block";
    }

    @Override
    public List<String> getLongDesc() {
        return Arrays.asList("The goto command tells Baritone to head towards a given goal or block.", "", "Wherever a coordinate is expected, you can use ~ just like in regular Minecraft commands. Or, you can just use regular numbers.", "", "Usage:", "> goto <block> - Go to a block, wherever it is in the world", "> goto <y> - Go to a Y level", "> goto <x> <z> - Go to an X,Z position", "> goto <x> <y> <z> - Go to an X,Y,Z position");
    }
}

