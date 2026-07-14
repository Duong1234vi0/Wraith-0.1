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
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.FallingBlock;
import lightning.product.z_3539_x;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.pathing.movement.MovementStatus;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.RotationUtils;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.pathing.movement.CalculationContext;
import mods.baritone.pathing.movement.Movement;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.pathing.movement.MovementState;
import mods.baritone.utils.BlockStateInterface;
import mods.baritone.utils.pathing.MutableMoveResult;

public class MovementDescend
extends Movement {
    private int numTicks = 0;
    public boolean forceSafeMode = false;

    public MovementDescend(IBaritone baritone, BetterBlockPos start, BetterBlockPos end) {
        super(baritone, start, end, new BetterBlockPos[]{end.up(2), end.up(), end}, end.down());
    }

    @Override
    public void reset() {
        super.reset();
        this.numTicks = 0;
        this.forceSafeMode = false;
    }

    public void forceSafeMode() {
        this.forceSafeMode = true;
    }

    @Override
    public double calculateCost(CalculationContext context) {
        MutableMoveResult result = new MutableMoveResult();
        MovementDescend.cost(context, this.src.x, this.src.y, this.src.z, this.dest.x, this.dest.z, result);
        if (result.y != this.dest.y) {
            return 1000000.0;
        }
        return result.cost;
    }

    @Override
    protected Set<BetterBlockPos> calculateValidPositions() {
        return ImmutableSet.of((Object)this.src, (Object)this.dest.up(), (Object)this.dest);
    }

    public static void cost(CalculationContext context, int x, int y, int z, int destX, int destZ, MutableMoveResult res) {
        double totalCost = 0.0;
        K_4074_S destDown = context.get(destX, y - 1, destZ);
        if ((totalCost += MovementHelper.getMiningDurationTicks(context, destX, y - 1, destZ, destDown, false)) >= 1000000.0) {
            return;
        }
        if ((totalCost += MovementHelper.getMiningDurationTicks(context, destX, y, destZ, false)) >= 1000000.0) {
            return;
        }
        if ((totalCost += MovementHelper.getMiningDurationTicks(context, destX, y + 1, destZ, true)) >= 1000000.0) {
            return;
        }
        T_2915_h fromDown = context.get(x, y - 1, z).J_1907_R();
        if (fromDown == a_3742_W.L_3570_A || fromDown == a_3742_W.U_4087_m) {
            return;
        }
        K_4074_S below = context.get(destX, y - 2, destZ);
        if (!MovementHelper.canWalkOn(context, destX, y - 2, destZ, below)) {
            MovementDescend.dynamicFallCost(context, x, y, z, destX, destZ, totalCost, below, res);
            return;
        }
        if (destDown.J_1907_R() == a_3742_W.L_3570_A || destDown.J_1907_R() == a_3742_W.U_4087_m) {
            return;
        }
        if (MovementHelper.canUseFrostWalker(context, destDown)) {
            return;
        }
        double walk = 3.7062775075283763;
        if (fromDown == a_3742_W.C_415_h) {
            walk *= 2.0;
        }
        res.x = destX;
        res.y = y - 1;
        res.z = destZ;
        res.cost = totalCost += walk + Math.max(FALL_N_BLOCKS_COST[1], 0.9265693768820937);
    }

    public static boolean dynamicFallCost(CalculationContext context, int x, int y, int z, int destX, int destZ, double frontBreak, K_4074_S below, MutableMoveResult res) {
        if (frontBreak != 0.0 && context.get(destX, y + 2, destZ).J_1907_R() instanceof FallingBlock) {
            return false;
        }
        if (!MovementHelper.canWalkThrough(context, destX, y - 2, destZ, below)) {
            return false;
        }
        double costSoFar = 0.0;
        int effectiveStartHeight = y;
        int fallHeight = 3;
        int newY;
        while ((newY = y - fallHeight) >= 0) {
            K_4074_S ontoBlock = context.get(destX, newY, destZ);
            int unprotectedFallHeight = fallHeight - (y - effectiveStartHeight);
            double tentativeCost = 3.7062775075283763 + FALL_N_BLOCKS_COST[unprotectedFallHeight] + frontBreak + costSoFar;
            if (MovementHelper.isWater(ontoBlock)) {
                if (!MovementHelper.canWalkThrough(context, destX, newY, destZ, ontoBlock)) {
                    return false;
                }
                if (context.assumeWalkOnWater) {
                    return false;
                }
                if (MovementHelper.isFlowing(destX, newY, destZ, ontoBlock, context.bsi)) {
                    return false;
                }
                if (!MovementHelper.canWalkOn(context, destX, newY - 1, destZ)) {
                    return false;
                }
                res.x = destX;
                res.y = newY;
                res.z = destZ;
                res.cost = tentativeCost;
                return false;
            }
            if (unprotectedFallHeight <= 11 && (ontoBlock.J_1907_R() == a_3742_W.U_4087_m || ontoBlock.J_1907_R() == a_3742_W.L_3570_A)) {
                costSoFar += FALL_N_BLOCKS_COST[unprotectedFallHeight - 1];
                costSoFar += 6.666666666666667;
                effectiveStartHeight = newY;
            } else if (!MovementHelper.canWalkThrough(context, destX, newY, destZ, ontoBlock)) {
                if (!MovementHelper.canWalkOn(context, destX, newY, destZ, ontoBlock)) {
                    return false;
                }
                if (MovementHelper.isBottomSlab(ontoBlock)) {
                    return false;
                }
                if (unprotectedFallHeight <= context.maxFallHeightNoWater + 1) {
                    res.x = destX;
                    res.y = newY + 1;
                    res.z = destZ;
                    res.cost = tentativeCost;
                    return false;
                }
                if (context.hasWaterBucket && unprotectedFallHeight <= context.maxFallHeightBucket + 1) {
                    res.x = destX;
                    res.y = newY + 1;
                    res.z = destZ;
                    res.cost = tentativeCost + context.placeBucketCost();
                    return true;
                }
                return false;
            }
            ++fallHeight;
        }
        return false;
    }

    @Override
    public MovementState updateState(MovementState state) {
        super.updateState(state);
        if (state.getStatus() != MovementStatus.RUNNING) {
            return state;
        }
        BetterBlockPos playerFeet = this.ctx.playerFeet();
        c_1514_x fakeDest = new c_1514_x(this.dest.getX() * 2 - this.src.getX(), this.dest.getY(), this.dest.getZ() * 2 - this.src.getZ());
        if ((((z_3539_x)playerFeet).equals(this.dest) || ((z_3539_x)playerFeet).equals(fakeDest)) && (MovementHelper.isLiquid(this.ctx, this.dest) || this.ctx.player().s_4990_V().R_4764_Y - (double)this.dest.getY() < 0.5)) {
            return state.setStatus(MovementStatus.SUCCESS);
        }
        if (this.safeMode()) {
            double destX = ((double)this.src.getX() + 0.5) * 0.17 + ((double)this.dest.getX() + 0.5) * 0.83;
            double destZ = ((double)this.src.getZ() + 0.5) * 0.17 + ((double)this.dest.getZ() + 0.5) * 0.83;
            state.setTarget(new MovementState.MovementTarget(RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), new e_2866_D(destX, this.dest.getY(), destZ), this.ctx.playerRotations()).withPitch(this.ctx.playerRotations().getPitch()), false)).setInput(Input.MOVE_FORWARD, true);
            return state;
        }
        double diffX = this.ctx.player().s_4990_V().J_1907_R - ((double)this.dest.getX() + 0.5);
        double diffZ = this.ctx.player().s_4990_V().G_564_y - ((double)this.dest.getZ() + 0.5);
        double ab = Math.sqrt(diffX * diffX + diffZ * diffZ);
        double x = this.ctx.player().s_4990_V().J_1907_R - ((double)this.src.getX() + 0.5);
        double z = this.ctx.player().s_4990_V().G_564_y - ((double)this.src.getZ() + 0.5);
        double fromStart = Math.sqrt(x * x + z * z);
        if (!((z_3539_x)playerFeet).equals(this.dest) || ab > 0.25) {
            if (this.numTicks++ < 20 && fromStart < 1.25) {
                MovementHelper.moveTowards(this.ctx, state, fakeDest);
            } else {
                MovementHelper.moveTowards(this.ctx, state, this.dest);
            }
        }
        return state;
    }

    public boolean safeMode() {
        if (this.forceSafeMode) {
            return true;
        }
        c_1514_x into = this.dest.subtract(this.src.down()).add(this.dest);
        if (this.skipToAscend()) {
            return true;
        }
        for (int y = 0; y <= 2; ++y) {
            if (!MovementHelper.avoidWalkingInto(BlockStateInterface.get(this.ctx, into.up(y)))) continue;
            return true;
        }
        return false;
    }

    public boolean skipToAscend() {
        c_1514_x into = this.dest.subtract(this.src.down()).add(this.dest);
        return !MovementHelper.canWalkThrough(this.ctx, new BetterBlockPos(into)) && MovementHelper.canWalkThrough(this.ctx, new BetterBlockPos(into).up()) && MovementHelper.canWalkThrough(this.ctx, new BetterBlockPos(into).up(2));
    }
}


