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
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.FallingBlock;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.pathing.movement.MovementStatus;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.pathing.movement.CalculationContext;
import mods.baritone.pathing.movement.Movement;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.pathing.movement.MovementState;
import mods.baritone.utils.BlockStateInterface;

public class MovementAscend
extends Movement {
    private int ticksWithoutPlacement = 0;

    public MovementAscend(IBaritone baritone, BetterBlockPos src, BetterBlockPos dest) {
        super(baritone, src, dest, new BetterBlockPos[]{dest, src.up(2), dest.up()}, dest.down());
    }

    @Override
    public void reset() {
        super.reset();
        this.ticksWithoutPlacement = 0;
    }

    @Override
    public double calculateCost(CalculationContext context) {
        return MovementAscend.cost(context, this.src.x, this.src.y, this.src.z, this.dest.x, this.dest.z);
    }

    @Override
    protected Set<BetterBlockPos> calculateValidPositions() {
        BetterBlockPos prior = new BetterBlockPos(this.src.subtract(this.getDirection()).up());
        return ImmutableSet.of((Object)this.src, (Object)this.src.up(), (Object)this.dest, (Object)prior, (Object)prior.up());
    }

    public static double cost(CalculationContext context, int x, int y, int z, int destX, int destZ) {
        double walk;
        K_4074_S toPlace = context.get(destX, y, destZ);
        double additionalPlacementCost = 0.0;
        if (!MovementHelper.canWalkOn(context, destX, y, destZ, toPlace)) {
            additionalPlacementCost = context.costOfPlacingAt(destX, y, destZ, toPlace);
            if (additionalPlacementCost >= 1000000.0) {
                return 1000000.0;
            }
            if (!MovementHelper.isReplaceable(destX, y, destZ, toPlace, context.bsi)) {
                return 1000000.0;
            }
            boolean foundPlaceOption = false;
            for (int i = 0; i < 5; ++i) {
                int againstX = destX + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[i].t_148_a();
                int againstY = y + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[i].s_956_w();
                int againstZ = destZ + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[i].u_2550_I();
                if (againstX == x && againstZ == z || !MovementHelper.canPlaceAgainst(context.bsi, againstX, againstY, againstZ)) continue;
                foundPlaceOption = true;
                break;
            }
            if (!foundPlaceOption) {
                return 1000000.0;
            }
        }
        K_4074_S srcUp2 = context.get(x, y + 2, z);
        if (context.get(x, y + 3, z).J_1907_R() instanceof FallingBlock && (MovementHelper.canWalkThrough(context, x, y + 1, z) || !(srcUp2.J_1907_R() instanceof FallingBlock))) {
            return 1000000.0;
        }
        K_4074_S srcDown = context.get(x, y - 1, z);
        if (srcDown.J_1907_R() == a_3742_W.L_3570_A || srcDown.J_1907_R() == a_3742_W.U_4087_m) {
            return 1000000.0;
        }
        boolean jumpingFromBottomSlab = MovementHelper.isBottomSlab(srcDown);
        boolean jumpingToBottomSlab = MovementHelper.isBottomSlab(toPlace);
        if (jumpingFromBottomSlab && !jumpingToBottomSlab) {
            return 1000000.0;
        }
        if (jumpingToBottomSlab) {
            if (jumpingFromBottomSlab) {
                walk = Math.max(JUMP_ONE_BLOCK_COST, 4.63284688441047);
                walk += context.jumpPenalty;
            } else {
                walk = 4.63284688441047;
            }
        } else {
            walk = toPlace.J_1907_R() == a_3742_W.C_415_h ? 9.26569376882094 : Math.max(JUMP_ONE_BLOCK_COST, 4.63284688441047);
            walk += context.jumpPenalty;
        }
        double totalCost = walk + additionalPlacementCost;
        totalCost += MovementHelper.getMiningDurationTicks(context, x, y + 2, z, srcUp2, false);
        if (totalCost >= 1000000.0) {
            return 1000000.0;
        }
        if ((totalCost += MovementHelper.getMiningDurationTicks(context, destX, y + 1, destZ, false)) >= 1000000.0) {
            return 1000000.0;
        }
        return totalCost += MovementHelper.getMiningDurationTicks(context, destX, y + 2, destZ, true);
    }

    @Override
    public MovementState updateState(MovementState state) {
        if (this.ctx.playerFeet().y < this.src.y) {
            return state.setStatus(MovementStatus.UNREACHABLE);
        }
        super.updateState(state);
        if (state.getStatus() != MovementStatus.RUNNING) {
            return state;
        }
        if (this.ctx.playerFeet().equals(this.dest) || this.ctx.playerFeet().equals(this.dest.add(this.getDirection().down()))) {
            return state.setStatus(MovementStatus.SUCCESS);
        }
        K_4074_S jumpingOnto = BlockStateInterface.get(this.ctx, this.positionToPlace);
        if (!MovementHelper.canWalkOn(this.ctx, this.positionToPlace, jumpingOnto)) {
            ++this.ticksWithoutPlacement;
            if (MovementHelper.attemptToPlaceABlock(state, this.baritone, this.dest.down(), false, true) == MovementHelper.PlaceResult.READY_TO_PLACE) {
                state.setInput(Input.SNEAK, true);
                if (this.ctx.player().Z_875_P()) {
                    state.setInput(Input.CLICK_RIGHT, true);
                }
            }
            if (this.ticksWithoutPlacement > 10) {
                state.setInput(Input.MOVE_BACK, true);
            }
            return state;
        }
        MovementHelper.moveTowards(this.ctx, state, this.dest);
        if (MovementHelper.isBottomSlab(jumpingOnto) && !MovementHelper.isBottomSlab(BlockStateInterface.get(this.ctx, this.src.down()))) {
            return state;
        }
        if (((Boolean)Baritone.settings().assumeStep.value).booleanValue() || this.ctx.playerFeet().equals(this.src.up())) {
            return state;
        }
        int xAxis = Math.abs(this.src.getX() - this.dest.getX());
        int zAxis = Math.abs(this.src.getZ() - this.dest.getZ());
        double flatDistToNext = (double)xAxis * Math.abs((double)this.dest.getX() + 0.5 - this.ctx.player().s_4990_V().J_1907_R) + (double)zAxis * Math.abs((double)this.dest.getZ() + 0.5 - this.ctx.player().s_4990_V().G_564_y);
        double sideDist = (double)zAxis * Math.abs((double)this.dest.getX() + 0.5 - this.ctx.player().s_4990_V().J_1907_R) + (double)xAxis * Math.abs((double)this.dest.getZ() + 0.5 - this.ctx.player().s_4990_V().G_564_y);
        double lateralMotion = (double)xAxis * this.ctx.player().I_4348_c().G_564_y + (double)zAxis * this.ctx.player().I_4348_c().J_1907_R;
        if (Math.abs(lateralMotion) > 0.1) {
            return state;
        }
        if (this.headBonkClear()) {
            return state.setInput(Input.JUMP, true);
        }
        if (flatDistToNext > 1.2 || sideDist > 0.2) {
            return state;
        }
        return state.setInput(Input.JUMP, true);
    }

    public boolean headBonkClear() {
        BetterBlockPos startUp = this.src.up(2);
        for (int i = 0; i < 4; ++i) {
            BetterBlockPos check = startUp.offset(b_257_Y.J_1907_R(i));
            if (MovementHelper.canWalkThrough(this.ctx, check)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean safeToCancel(MovementState state) {
        return state.getStatus() != MovementStatus.RUNNING || this.ticksWithoutPlacement == 0;
    }
}


