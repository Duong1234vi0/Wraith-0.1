/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package mods.baritone.pathing.movement.movements;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import lightning.product.C_1985_D;
import lightning.product.K_4074_S;
import lightning.product.WoolCarpetBlock;
import lightning.product.T_2915_h;
import lightning.product.FenceGateBlock;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.AirBlock;
import lightning.product.n_1769_f;
import lightning.product.FallingBlock;
import lightning.product.y_3008_A;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.pathing.movement.MovementStatus;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.api.api.java.baritone.api.utils.RotationUtils;
import mods.baritone.api.api.java.baritone.api.utils.VecUtils;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.pathing.movement.CalculationContext;
import mods.baritone.pathing.movement.Movement;
import mods.baritone.pathing.movement.MovementHelper;
import mods.baritone.pathing.movement.MovementState;
import mods.baritone.utils.BlockStateInterface;

public class MovementPillar
extends Movement {
    public MovementPillar(IBaritone baritone, BetterBlockPos start, BetterBlockPos end) {
        super(baritone, start, end, new BetterBlockPos[]{start.up(2)}, start);
    }

    @Override
    public double calculateCost(CalculationContext context) {
        return MovementPillar.cost(context, this.src.x, this.src.y, this.src.z);
    }

    @Override
    protected Set<BetterBlockPos> calculateValidPositions() {
        return ImmutableSet.of((Object)this.src, (Object)this.dest);
    }

    public static double cost(CalculationContext context, int x, int y, int z) {
        K_4074_S fromState = context.get(x, y, z);
        T_2915_h from = fromState.J_1907_R();
        boolean ladder = from == a_3742_W.L_3570_A || from == a_3742_W.U_4087_m;
        K_4074_S fromDown = context.get(x, y - 1, z);
        if (!ladder) {
            if (fromDown.J_1907_R() == a_3742_W.L_3570_A || fromDown.J_1907_R() == a_3742_W.U_4087_m) {
                return 1000000.0;
            }
            if (fromDown.J_1907_R() instanceof y_3008_A && fromDown.R_4764_Y(y_3008_A.P_4830_p) == n_1769_f.J_1907_R) {
                return 1000000.0;
            }
        }
        if (from == a_3742_W.U_4087_m && !MovementPillar.hasAgainst(context, x, y, z)) {
            return 1000000.0;
        }
        K_4074_S toBreak = context.get(x, y + 2, z);
        T_2915_h toBreakBlock = toBreak.J_1907_R();
        if (toBreakBlock instanceof FenceGateBlock) {
            return 1000000.0;
        }
        K_4074_S srcUp = null;
        if (MovementHelper.isWater(toBreak) && MovementHelper.isWater(fromState) && MovementHelper.isWater(srcUp = context.get(x, y + 1, z))) {
            return 8.51063829787234;
        }
        double placeCost = 0.0;
        if (!ladder) {
            placeCost = context.costOfPlacingAt(x, y, z, fromState);
            if (placeCost >= 1000000.0) {
                return 1000000.0;
            }
            if (fromDown.J_1907_R() instanceof AirBlock) {
                placeCost += 0.1;
            }
        }
        if (MovementHelper.isLiquid(fromState) && !MovementHelper.canPlaceAgainst(context.bsi, x, y - 1, z, fromDown) || MovementHelper.isLiquid(fromDown) && context.assumeWalkOnWater) {
            return 1000000.0;
        }
        if ((from == a_3742_W.S_4035_N || from instanceof WoolCarpetBlock) && !fromDown.P_4830_p().R_4764_Y()) {
            return 1000000.0;
        }
        double hardness = MovementHelper.getMiningDurationTicks(context, x, y + 2, z, toBreak, true);
        if (hardness >= 1000000.0) {
            return 1000000.0;
        }
        if (hardness != 0.0) {
            if (toBreakBlock == a_3742_W.L_3570_A || toBreakBlock == a_3742_W.U_4087_m) {
                hardness = 0.0;
            } else {
                K_4074_S check = context.get(x, y + 3, z);
                if (check.J_1907_R() instanceof FallingBlock) {
                    if (srcUp == null) {
                        srcUp = context.get(x, y + 1, z);
                    }
                    if (!(toBreakBlock instanceof FallingBlock) || !(srcUp.J_1907_R() instanceof FallingBlock)) {
                        return 1000000.0;
                    }
                }
            }
        }
        if (ladder) {
            return 8.51063829787234 + hardness * 5.0;
        }
        return JUMP_ONE_BLOCK_COST + placeCost + context.jumpPenalty + hardness;
    }

    public static boolean hasAgainst(CalculationContext context, int x, int y, int z) {
        return MovementHelper.isBlockNormalCube(context.get(x + 1, y, z)) || MovementHelper.isBlockNormalCube(context.get(x - 1, y, z)) || MovementHelper.isBlockNormalCube(context.get(x, y, z + 1)) || MovementHelper.isBlockNormalCube(context.get(x, y, z - 1));
    }

    public static c_1514_x getAgainst(CalculationContext context, BetterBlockPos vine) {
        if (MovementHelper.isBlockNormalCube(context.get(vine.north()))) {
            return vine.north();
        }
        if (MovementHelper.isBlockNormalCube(context.get(vine.south()))) {
            return vine.south();
        }
        if (MovementHelper.isBlockNormalCube(context.get(vine.east()))) {
            return vine.east();
        }
        if (MovementHelper.isBlockNormalCube(context.get(vine.west()))) {
            return vine.west();
        }
        return null;
    }

    @Override
    public MovementState updateState(MovementState state) {
        boolean blockIsThere;
        super.updateState(state);
        if (state.getStatus() != MovementStatus.RUNNING) {
            return state;
        }
        if (this.ctx.playerFeet().y < this.src.y) {
            return state.setStatus(MovementStatus.UNREACHABLE);
        }
        K_4074_S fromDown = BlockStateInterface.get(this.ctx, this.src);
        if (MovementHelper.isWater(fromDown) && MovementHelper.isWater(this.ctx, this.dest)) {
            state.setTarget(new MovementState.MovementTarget(RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.getBlockPosCenter(this.dest), this.ctx.playerRotations()), false));
            e_2866_D destCenter = VecUtils.getBlockPosCenter(this.dest);
            if (Math.abs(this.ctx.player().s_4990_V().J_1907_R - destCenter.J_1907_R) > 0.2 || Math.abs(this.ctx.player().s_4990_V().G_564_y - destCenter.G_564_y) > 0.2) {
                state.setInput(Input.MOVE_FORWARD, true);
            }
            if (this.ctx.playerFeet().equals(this.dest)) {
                return state.setStatus(MovementStatus.SUCCESS);
            }
            return state;
        }
        boolean ladder = fromDown.J_1907_R() == a_3742_W.L_3570_A || fromDown.J_1907_R() == a_3742_W.U_4087_m;
        boolean vine = fromDown.J_1907_R() == a_3742_W.U_4087_m;
        Rotation rotation = RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.getBlockPosCenter(this.positionToPlace), this.ctx.playerRotations());
        if (!ladder) {
            state.setTarget(new MovementState.MovementTarget(this.ctx.playerRotations().withPitch(rotation.getPitch()), true));
        }
        boolean bl = blockIsThere = MovementHelper.canWalkOn(this.ctx, this.src) || ladder;
        if (ladder) {
            c_1514_x against;
            c_1514_x c_1514_x2 = against = vine ? MovementPillar.getAgainst(new CalculationContext(this.baritone), this.src) : this.src.offset(fromDown.R_4764_Y(C_1985_D.P_4830_p).u_1723_Y());
            if (against == null) {
                this.logDirect("Unable to climb vines. Consider disabling allowVines.");
                return state.setStatus(MovementStatus.UNREACHABLE);
            }
            if (this.ctx.playerFeet().equals(against.up()) || this.ctx.playerFeet().equals(this.dest)) {
                return state.setStatus(MovementStatus.SUCCESS);
            }
            if (MovementHelper.isBottomSlab(BlockStateInterface.get(this.ctx, this.src.down()))) {
                state.setInput(Input.JUMP, true);
            }
            MovementHelper.moveTowards(this.ctx, state, against);
            return state;
        }
        if (!((Baritone)this.baritone).getInventoryBehavior().selectThrowawayForLocation(true, this.src.x, this.src.y, this.src.z)) {
            return state.setStatus(MovementStatus.UNREACHABLE);
        }
        state.setInput(Input.SNEAK, this.ctx.player().s_4990_V().R_4764_Y > (double)this.dest.getY() || this.ctx.player().s_4990_V().R_4764_Y < (double)this.src.getY() + 0.2);
        double diffX = this.ctx.player().s_4990_V().J_1907_R - ((double)this.dest.getX() + 0.5);
        double diffZ = this.ctx.player().s_4990_V().G_564_y - ((double)this.dest.getZ() + 0.5);
        double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        double flatMotion = Math.sqrt(this.ctx.player().I_4348_c().J_1907_R * this.ctx.player().I_4348_c().J_1907_R + this.ctx.player().I_4348_c().G_564_y * this.ctx.player().I_4348_c().G_564_y);
        if (dist > 0.17) {
            state.setInput(Input.MOVE_FORWARD, true);
            state.setTarget(new MovementState.MovementTarget(rotation, true));
        } else if (flatMotion < 0.05) {
            state.setInput(Input.JUMP, this.ctx.player().s_4990_V().R_4764_Y < (double)this.dest.getY());
        }
        if (!blockIsThere) {
            K_4074_S frState = BlockStateInterface.get(this.ctx, this.src);
            T_2915_h fr = frState.J_1907_R();
            if (!(fr instanceof AirBlock) && !frState.R_4764_Y().P_1922_E()) {
                RotationUtils.reachable(this.ctx, (c_1514_x)this.src, this.ctx.playerController().getBlockReachDistance()).map(rot -> new MovementState.MovementTarget((Rotation)rot, true)).ifPresent(state::setTarget);
                state.setInput(Input.JUMP, false);
                state.setInput(Input.CLICK_LEFT, true);
                blockIsThere = false;
            } else if (this.ctx.player().Z_875_P() && (this.ctx.isLookingAt(this.src.down()) || this.ctx.isLookingAt(this.src)) && this.ctx.player().s_4990_V().R_4764_Y > (double)this.dest.getY() + 0.1) {
                state.setInput(Input.CLICK_RIGHT, true);
            }
        }
        if (this.ctx.playerFeet().equals(this.dest) && blockIsThere) {
            return state.setStatus(MovementStatus.SUCCESS);
        }
        return state;
    }

    @Override
    protected boolean prepared(MovementState state) {
        T_2915_h block;
        if ((this.ctx.playerFeet().equals(this.src) || this.ctx.playerFeet().equals(this.src.down())) && ((block = BlockStateInterface.getBlock(this.ctx, this.src.down())) == a_3742_W.L_3570_A || block == a_3742_W.U_4087_m)) {
            state.setInput(Input.SNEAK, true);
        }
        if (MovementHelper.isWater(this.ctx, this.dest.up())) {
            return true;
        }
        return super.prepared(state);
    }
}


