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
import mods.baritone.api.api.java.baritone.api.pathing.goals.GoalXZ;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;

public enum RelativeGoalXZ implements IDatatypePost<GoalXZ, BetterBlockPos>
{
    INSTANCE;


    @Override
    public GoalXZ apply(IDatatypeContext ctx, BetterBlockPos origin) throws CommandException {
        if (origin == null) {
            origin = BetterBlockPos.ORIGIN;
        }
        IArgConsumer consumer = ctx.getConsumer();
        return new GoalXZ(u_530_F.R_4764_Y((Double)consumer.getDatatypePost(RelativeCoordinate.INSTANCE, Double.valueOf(origin.x))), u_530_F.R_4764_Y((Double)consumer.getDatatypePost(RelativeCoordinate.INSTANCE, Double.valueOf(origin.z))));
    }

    @Override
    public Stream<String> tabComplete(IDatatypeContext ctx) {
        IArgConsumer consumer = ctx.getConsumer();
        if (consumer.hasAtMost(2)) {
            return consumer.tabCompleteDatatype(RelativeCoordinate.INSTANCE);
        }
        return Stream.empty();
    }
}

