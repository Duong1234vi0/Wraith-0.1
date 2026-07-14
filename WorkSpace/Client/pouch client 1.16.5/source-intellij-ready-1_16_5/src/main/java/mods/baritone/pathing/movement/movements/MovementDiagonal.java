/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package mods.baritone.pathing.movement.movements;

import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.V_772_m;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
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
import mods.baritone.utils.pathing.MutableMoveResult;

public class MovementDiagonal
extends Movement {
    private static final double SQRT_2 = Math.sqrt(2.0);

    public MovementDiagonal(IBaritone baritone, BetterBlockPos start, b_257_Y dir1, b_257_Y dir2, int dy) {
        this(baritone, start, start.offset(dir1), start.offset(dir2), dir2, dy);
    }

    private MovementDiagonal(IBaritone baritone, BetterBlockPos start, BetterBlockPos dir1, BetterBlockPos dir2, b_257_Y drr2, int dy) {
        this(baritone, start, dir1.offset(drr2).up(dy), dir1, dir2);
    }

    private MovementDiagonal(IBaritone baritone, BetterBlockPos start, BetterBlockPos end, BetterBlockPos dir1, BetterBlockPos dir2) {
        super(baritone, start, end, new BetterBlockPos[]{dir1, dir1.up(), dir2, dir2.up(), end, end.up()});
    }

    @Override
    protected boolean safeToCancel(MovementState state) {
        V_772_m player = this.ctx.player();
        double offset = 0.25;
        double x = player.s_4990_V().J_1907_R;
        double y = player.s_4990_V().R_4764_Y - 1.0;
        double z = player.s_4990_V().G_564_y;
        if (this.ctx.playerFeet().equals(this.src)) {
            return true;
        }
        if (MovementHelper.canWalkOn(this.ctx, new c_1514_x(this.src.x, this.src.y - 1, this.dest.z)) && MovementHelper.canWalkOn(this.ctx, new c_1514_x(this.dest.x, this.src.y - 1, this.src.z))) {
            return true;
        }
        if (this.ctx.playerFeet().equals(new BetterBlockPos(this.src.x, this.src.y, this.dest.z)) || this.ctx.playerFeet().equals(new BetterBlockPos(this.dest.x, this.src.y, this.src.z))) {
            return MovementHelper.canWalkOn(this.ctx, new BetterBlockPos(x + offset, y, z + offset)) || MovementHelper.canWalkOn(this.ctx, new BetterBlockPos(x + offset, y, z - offset)) || MovementHelper.canWalkOn(this.ctx, new BetterBlockPos(x - offset, y, z + offset)) || MovementHelper.canWalkOn(this.ctx, new BetterBlockPos(x - offset, y, z - offset));
        }
        return true;
    }

    @Override
    public double calculateCost(CalculationContext context) {
        MutableMoveResult result = new MutableMoveResult();
        MovementDiagonal.cost(context, this.src.x, this.src.y, this.src.z, this.dest.x, this.dest.z, result);
        if (result.y != this.dest.y) {
            return 1000000.0;
        }
        return result.cost;
    }

    @Override
    protected Set<BetterBlockPos> calculateValidPositions() {
        BetterBlockPos diagA = new BetterBlockPos(this.src.x, this.src.y, this.dest.z);
        BetterBlockPos diagB = new BetterBlockPos(this.dest.x, this.src.y, this.src.z);
        if (this.dest.y < this.src.y) {
            return ImmutableSet.of((Object)this.src, (Object)this.dest.up(), (Object)diagA, (Object)diagB, (Object)this.dest, (Object)diagA.down(), (Object[])new BetterBlockPos[]{diagB.down()});
        }
        if (this.dest.y > this.src.y) {
            return ImmutableSet.of((Object)this.src, (Object)this.src.up(), (Object)diagA, (Object)diagB, (Object)this.dest, (Object)diagA.up(), (Object[])new BetterBlockPos[]{diagB.up()});
        }
        return ImmutableSet.of((Object)this.src, (Object)this.dest, (Object)diagA, (Object)diagB);
    }

    public static void cost(CalculationContext context, int x, int y, int z, int destX, int destZ, MutableMoveResult res) {
        K_4074_S cuttingOver1;
        K_4074_S fromDown;
        K_4074_S destWalkOn;
        if (!MovementHelper.canWalkThrough(context, destX, y + 1, destZ)) {
            return;
        }
        K_4074_S destInto = context.get(destX, y, destZ);
        boolean ascend = false;
        boolean descend = false;
        boolean frostWalker = false;
        if (!MovementHelper.canWalkThrough(context, destX, y, destZ, destInto)) {
            ascend = true;
            if (!(context.allowDiagonalAscend && MovementHelper.canWalkThrough(context, x, y + 2, z) && MovementHelper.canWalkOn(context, destX, y, destZ, destInto) && MovementHelper.canWalkThrough(context, destX, y + 2, destZ))) {
                return;
            }
            destWalkOn = destInto;
            fromDown = context.get(x, y - 1, z);
        } else {
            destWalkOn = context.get(destX, y - 1, destZ);
            fromDown = context.get(x, y - 1, z);
            boolean standingOnABlock = MovementHelper.mustBeSolidToWalkOn(context, x, y - 1, z, fromDown);
            boolean bl = frostWalker = standingOnABlock && MovementHelper.canUseFrostWalker(context, destWalkOn);
            if (!frostWalker && !MovementHelper.canWalkOn(context, destX, y - 1, destZ, destWalkOn)) {
                descend = true;
                if (!(context.allowDiagonalDescend && MovementHelper.canWalkOn(context, destX, y - 2, destZ) && MovementHelper.canWalkThrough(context, destX, y - 1, destZ, destWalkOn))) {
                    return;
                }
            }
            frostWalker &= !context.assumeWalkOnWater;
        }
        double multiplier = 4.63284688441047;
        if (destWalkOn.J_1907_R() == a_3742_W.C_415_h) {
            multiplier += 2.316423442205235;
        } else if (!frostWalker && destWalkOn.J_1907_R() == a_3742_W.c_3005_b) {
            multiplier += context.walkOnWaterOnePenalty * SQRT_2;
        }
        T_2915_h fromDownBlock = fromDown.J_1907_R();
        if (fromDownBlock == a_3742_W.L_3570_A || fromDownBlock == a_3742_W.U_4087_m) {
            return;
        }
        if (fromDownBlock == a_3742_W.C_415_h) {
            multiplier += 2.316423442205235;
        }
        if ((cuttingOver1 = context.get(x, y - 1, destZ)).J_1907_R() == a_3742_W.LevitationControl || MovementHelper.isLava(cuttingOver1)) {
            return;
        }
        K_4074_S cuttingOver2 = context.get(destX, y - 1, z);
        if (cuttingOver2.J_1907_R() == a_3742_W.LevitationControl || MovementHelper.isLava(cuttingOver2)) {
            return;
        }
        boolean water = false;
        K_4074_S startState = context.get(x, y, z);
        T_2915_h startIn = startState.J_1907_R();
        if (MovementHelper.isWater(startState) || MovementHelper.isWater(destInto)) {
            if (ascend) {
                return;
            }
            multiplier = context.waterWalkSpeed;
            water = true;
        }
        K_4074_S pb0 = context.get(x, y, destZ);
        K_4074_S pb2 = context.get(destX, y, z);
        if (ascend) {
            boolean ATop = MovementHelper.canWalkThrough(context, x, y + 2, destZ);
            boolean AMid = MovementHelper.canWalkThrough(context, x, y + 1, destZ);
            boolean ALow = MovementHelper.canWalkThrough(context, x, y, destZ, pb0);
            boolean BTop = MovementHelper.canWalkThrough(context, destX, y + 2, z);
            boolean BMid = MovementHelper.canWalkThrough(context, destX, y + 1, z);
            boolean BLow = MovementHelper.canWalkThrough(context, destX, y, z, pb2);
            if ((!ATop || !AMid || !ALow) && (!BTop || !BMid || !BLow) || MovementHelper.avoidWalkingInto(pb0) || MovementHelper.avoidWalkingInto(pb2) || ATop && AMid && MovementHelper.canWalkOn(context, x, y, destZ, pb0) || BTop && BMid && MovementHelper.canWalkOn(context, destX, y, z, pb2) || !ATop && AMid && ALow || !BTop && BMid && BLow) {
                return;
            }
            res.cost = multiplier * SQRT_2 + JUMP_ONE_BLOCK_COST;
            res.x = destX;
            res.z = destZ;
            res.y = y + 1;
            return;
        }
        double optionA = MovementHelper.getMiningDurationTicks(context, x, y, destZ, pb0, false);
        double optionB = MovementHelper.getMiningDurationTicks(context, destX, y, z, pb2, false);
        if (optionA != 0.0 && optionB != 0.0) {
            return;
        }
        K_4074_S pb1 = context.get(x, y + 1, destZ);
        if ((optionA += MovementHelper.getMiningDurationTicks(context, x, y + 1, destZ, pb1, true)) != 0.0 && optionB != 0.0) {
            return;
        }
        K_4074_S pb3 = context.get(destX, y + 1, z);
        if (optionA == 0.0 && (MovementHelper.avoidWalkingInto(pb2) && pb2.J_1907_R() != a_3742_W.c_3005_b || MovementHelper.avoidWalkingInto(pb3))) {
            return;
        }
        if (optionA != 0.0 && (optionB += MovementHelper.getMiningDurationTicks(context, destX, y + 1, z, pb3, true)) != 0.0) {
            return;
        }
        if (optionB == 0.0 && (MovementHelper.avoidWalkingInto(pb0) && pb0.J_1907_R() != a_3742_W.c_3005_b || MovementHelper.avoidWalkingInto(pb1))) {
            return;
        }
        if (optionA != 0.0 || optionB != 0.0) {
            multiplier *= SQRT_2 - 0.001;
            if (startIn == a_3742_W.L_3570_A || startIn == a_3742_W.U_4087_m) {
                return;
            }
        } else if (context.canSprint && !water) {
            multiplier *= 0.7692444761225944;
        }
        res.cost = multiplier * SQRT_2;
        if (descend) {
            res.cost += Math.max(FALL_N_BLOCKS_COST[1], 0.9265693768820937);
            res.y = y - 1;
        } else {
            res.y = y;
        }
        res.x = destX;
        res.z = destZ;
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
        if (!(this.playerInValidPosition() || MovementHelper.isLiquid(this.ctx, this.src) && this.getValidPositions().contains(this.ctx.playerFeet().up()))) {
            return state.setStatus(MovementStatus.UNREACHABLE);
        }
        if (this.dest.y > this.src.y && this.ctx.player().s_4990_V().R_4764_Y < (double)this.src.y + 0.1 && this.ctx.player().D_60_a) {
            state.setInput(Input.JUMP, true);
        }
        if (this.sprint()) {
            state.setInput(Input.SPRINT, true);
        }
        MovementHelper.moveTowards(this.ctx, state, this.dest);
        return state;
    }

    private boolean sprint() {
        if (MovementHelper.isLiquid(this.ctx, this.ctx.playerFeet()) && !((Boolean)Baritone.settings().sprintInWater.value).booleanValue()) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            if (MovementHelper.canWalkThrough(this.ctx, this.positionsToBreak[i])) continue;
            return false;
        }
        return true;
    }

    @Override
    protected boolean prepared(MovementState state) {
        return true;
    }

    @Override
    public List<c_1514_x> toBreak(BlockStateInterface bsi) {
        if (this.toBreakCached != null) {
            return this.toBreakCached;
        }
        ArrayList<c_1514_x> result = new ArrayList<c_1514_x>();
        for (int i = 4; i < 6; ++i) {
            if (MovementHelper.canWalkThrough(bsi, this.positionsToBreak[i].x, this.positionsToBreak[i].y, this.positionsToBreak[i].z)) continue;
            result.add(this.positionsToBreak[i]);
        }
        this.toBreakCached = result;
        return result;
    }

    @Override
    public List<c_1514_x> toWalkInto(BlockStateInterface bsi) {
        if (this.toWalkIntoCached == null) {
            this.toWalkIntoCached = new ArrayList();
        }
        ArrayList<BetterBlockPos> result = new ArrayList<BetterBlockPos>();
        for (int i = 0; i < 4; ++i) {
            if (MovementHelper.canWalkThrough(bsi, this.positionsToBreak[i].x, this.positionsToBreak[i].y, this.positionsToBreak[i].z)) continue;
            result.add(this.positionsToBreak[i]);
        }
        this.toWalkIntoCached = result;
        return this.toWalkIntoCached;
    }
}


