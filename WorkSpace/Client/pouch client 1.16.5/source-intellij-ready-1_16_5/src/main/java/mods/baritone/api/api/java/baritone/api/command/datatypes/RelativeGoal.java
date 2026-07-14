/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import java.util.stream.Stream;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeContext;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypePost;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeCoordinate;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeGoalBlock;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeGoalXZ;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeGoalYLevel;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.pathing.goals.Goal;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalBlock;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalXZ;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalYLevel;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public enum RelativeGoal implements IDatatypePost<Goal, BetterBlockPos>
{
    INSTANCE;


    @Override
    public Goal apply(IDatatypeContext ctx, BetterBlockPos origin) throws CommandException {
        IArgConsumer consumer;
        GoalBlock goalBlock;
        if (origin == null) {
            origin = BetterBlockPos.ORIGIN;
        }
        if ((goalBlock = (GoalBlock)(consumer = ctx.getConsumer()).peekDatatypePostOrNull(RelativeGoalBlock.INSTANCE, origin)) != null) {
            return goalBlock;
        }
        GoalXZ goalXZ = (GoalXZ)consumer.peekDatatypePostOrNull(RelativeGoalXZ.INSTANCE, origin);
        if (goalXZ != null) {
            return goalXZ;
        }
        GoalYLevel goalYLevel = (GoalYLevel)consumer.peekDatatypePostOrNull(RelativeGoalYLevel.INSTANCE, origin);
        if (goalYLevel != null) {
            return goalYLevel;
        }
        return new GoalBlock(origin);
    }

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) {
        return ctx.getConsumer().tabCompleteDatatype(RelativeCoordinate.INSTANCE);
    }
}

