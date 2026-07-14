/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.command.datatypes;

import java.util.stream.Stream;
import lightning.product.u_530_F;
import mods.baritone.api.api.java.baritone.api.command.argument.IArgConsumer;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypeContext;
import mods.baritone.api.api.java.baritone.api.command.datatypes.IDatatypePost;
import mods.baritone.api.api.java.baritone.api.command.datatypes.RelativeCoordinate;
import mods.baritone.api.api.java.baritone.api.command.exception.CommandException;
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalYLevel;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public enum RelativeGoalYLevel implements IDatatypePost<GoalYLevel, BetterBlockPos>
{
    INSTANCE;


    @Override
    public GoalYLevel apply(IDatatypeContext ctx, BetterBlockPos origin) throws CommandException {
        if (origin == null) {
            origin = BetterBlockPos.ORIGIN;
        }
        return new GoalYLevel(u_530_F.R_4764_Y((Double)ctx.getConsumer().getDatatypePost(RelativeCoordinate.INSTANCE, Double.valueOf(origin.y))));
    }

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) {
        IArgConsumer consumer = ctx.getConsumer();
        if (consumer.hasAtMost(1)) {
            return consumer.tabCompleteDatatype(RelativeCoordinate.INSTANCE);
        }
        return Stream.empty();
    }
}

