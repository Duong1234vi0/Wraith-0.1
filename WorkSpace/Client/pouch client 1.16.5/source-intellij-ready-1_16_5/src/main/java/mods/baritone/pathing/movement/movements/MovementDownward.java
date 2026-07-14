/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package mods.baritone.pathing.movement.movements;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.pathing.movement.MovementStatus;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.pathing.movement.CalculationContext;
import mods.baritone.pathing.movement.Movement;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.pathing.movement.MovementState;

public class MovementDownward
extends Movement {
    private int numTicks = 0;

    public MovementDownward(IBaritone baritone, BetterBlockPos start, BetterBlockPos end) {
        super(baritone, start, end, new BetterBlockPos[]{end});
    }

    @Override
    public void reset() {
        super.reset();
        this.numTicks = 0;
    }

    @Override
    public double calculateCost(CalculationContext context) {
        return MovementDownward.cost(context, this.src.x, this.src.y, this.src.z);
    }

    @Override
    protected Set<BetterBlockPos> calculateValidPositions() {
        return ImmutableSet.of((Object)this.src, (Object)this.dest);
    }

    public static double cost(CalculationContext context, int x, int y, int z) {
        if (!context.allowDownward) {
            return 1000000.0;
        }
        if (!MovementHelper.canWalkOn(context, x, y - 2, z)) {
            return 1000000.0;
        }
        K_4074_S down = context.get(x, y - 1, z);
        T_2915_h downBlock = down.J_1907_R();
        if (downBlock == a_3742_W.L_3570_A || downBlock == a_3742_W.U_4087_m) {
            return 6.666666666666667;
        }
        return FALL_N_BLOCKS_COST[1] + MovementHelper.getMiningDurationTicks(context, x, y - 1, z, down, false);
    }

    @Override
    public MovementState updateState(MovementState state) {
        super.updateState(state);
        if (state.getStatus() != MovementStatus.RUNNING) {
            return state;
        }
        if (this.ctx.playerFeet().equals(this.dest)) {
            return state.setStatus(MovementStatus.SUCCESS);
        }
        if (!this.playerInValidPosition()) {
            return state.setStatus(MovementStatus.UNREACHABLE);
        }
        double diffX = this.ctx.player().s_4990_V().J_1907_R - ((double)this.dest.getX() + 0.5);
        double diffZ = this.ctx.player().s_4990_V().G_564_y - ((double)this.dest.getZ() + 0.5);
        double ab = Math.sqrt(diffX * diffX + diffZ * diffZ);
        if (this.numTicks++ < 10 && ab < 0.2) {
            return state;
        }
        MovementHelper.moveTowards(this.ctx, state, this.positionsToBreak[0]);
        return state;
    }
}

