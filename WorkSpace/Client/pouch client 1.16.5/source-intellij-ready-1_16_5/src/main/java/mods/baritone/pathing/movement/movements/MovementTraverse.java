/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package mods.baritone.pathing.movement.movements;

import com.google.common.collect.ImmutableSet;
import java.util.Optional;
import java.util.Set;
import lightning.product.C_1985_D;
import lightning.product.K_4074_S;
import lightning.product.WoolCarpetBlock;
import lightning.product.S_1431_H;
import lightning.product.T_2915_h;
import lightning.product.FenceGateBlock;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.AirBlock;
import lightning.product.n_1769_f;
import lightning.product.y_3008_A;
import lightning.product.z_3539_x;
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
import mods.baritone.pathing.movement.movements.MovementPillar;
import mods.baritone.utils.BlockStateInterface;

public class MovementTraverse
extends Movement {
    private boolean wasTheBridgeBlockAlwaysThere = true;

    public MovementTraverse(IBaritone baritone, BetterBlockPos from, BetterBlockPos to) {
        super(baritone, from, to, new BetterBlockPos[]{to.up(), to}, to.down());
    }

    @Override
    public void reset() {
        super.reset();
        this.wasTheBridgeBlockAlwaysThere = true;
    }

    @Override
    public double calculateCost(CalculationContext context) {
        return MovementTraverse.cost(context, this.src.x, this.src.y, this.src.z, this.dest.x, this.dest.z);
    }

    @Override
    protected Set<BetterBlockPos> calculateValidPositions() {
        return ImmutableSet.of((Object)this.src, (Object)this.dest);
    }

    public static double cost(CalculationContext context, int x, int y, int z, int destX, int destZ) {
        boolean frostWalker;
        K_4074_S pb0 = context.get(destX, y + 1, destZ);
        K_4074_S pb1 = context.get(destX, y, destZ);
        K_4074_S destOn = context.get(destX, y - 1, destZ);
        K_4074_S srcDown = context.get(x, y - 1, z);
        T_2915_h srcDownBlock = srcDown.J_1907_R();
        boolean standingOnABlock = MovementHelper.mustBeSolidToWalkOn(context, x, y - 1, z, srcDown);
        boolean bl = frostWalker = standingOnABlock && !context.assumeWalkOnWater && MovementHelper.canUseFrostWalker(context, destOn);
        if (frostWalker || MovementHelper.canWalkOn(context, destX, y - 1, destZ, destOn)) {
            double WC = 4.63284688441047;
            boolean water = false;
            if (MovementHelper.isWater(pb0) || MovementHelper.isWater(pb1)) {
                WC = context.waterWalkSpeed;
                water = true;
            } else {
                if (destOn.J_1907_R() == a_3742_W.C_415_h) {
                    WC += 2.316423442205235;
                } else if (!frostWalker && destOn.J_1907_R() == a_3742_W.c_3005_b) {
                    WC += context.walkOnWaterOnePenalty;
                }
                if (srcDownBlock == a_3742_W.C_415_h) {
                    WC += 2.316423442205235;
                }
            }
            double hardness1 = MovementHelper.getMiningDurationTicks(context, destX, y, destZ, pb1, false);
            if (hardness1 >= 1000000.0) {
                return 1000000.0;
            }
            double hardness2 = MovementHelper.getMiningDurationTicks(context, destX, y + 1, destZ, pb0, true);
            if (hardness1 == 0.0 && hardness2 == 0.0) {
                if (!water && context.canSprint) {
                    WC *= 0.7692444761225944;
                }
                return WC;
            }
            if (srcDownBlock == a_3742_W.L_3570_A || srcDownBlock == a_3742_W.U_4087_m) {
                hardness1 *= 5.0;
                hardness2 *= 5.0;
            }
            return WC + hardness1 + hardness2;
        }
        if (srcDownBlock == a_3742_W.L_3570_A || srcDownBlock == a_3742_W.U_4087_m) {
            return 1000000.0;
        }
        if (MovementHelper.isReplaceable(destX, y - 1, destZ, destOn, context.bsi)) {
            boolean throughWater;
            boolean bl2 = throughWater = MovementHelper.isWater(pb0) || MovementHelper.isWater(pb1);
            if (MovementHelper.isWater(destOn) && throughWater) {
                return 1000000.0;
            }
            double placeCost = context.costOfPlacingAt(destX, y - 1, destZ, destOn);
            if (placeCost >= 1000000.0) {
                return 1000000.0;
            }
            double hardness1 = MovementHelper.getMiningDurationTicks(context, destX, y, destZ, pb1, false);
            if (hardness1 >= 1000000.0) {
                return 1000000.0;
            }
            double hardness2 = MovementHelper.getMiningDurationTicks(context, destX, y + 1, destZ, pb0, true);
            double WC = throughWater ? context.waterWalkSpeed : 4.63284688441047;
            for (int i = 0; i < 5; ++i) {
                int againstX = destX + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[i].t_148_a();
                int againstY = y - 1 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[i].s_956_w();
                int againstZ = destZ + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[i].u_2550_I();
                if (againstX == x && againstZ == z || !MovementHelper.canPlaceAgainst(context.bsi, againstX, againstY, againstZ)) continue;
                return WC + placeCost + hardness1 + hardness2;
            }
            if (srcDownBlock == a_3742_W.C_415_h || srcDownBlock instanceof y_3008_A && srcDown.R_4764_Y(y_3008_A.P_4830_p) != n_1769_f.R_4764_Y) {
                return 1000000.0;
            }
            if (!standingOnABlock) {
                return 1000000.0;
            }
            T_2915_h blockSrc = context.getBlock(x, y, z);
            if ((blockSrc == a_3742_W.S_4035_N || blockSrc instanceof WoolCarpetBlock) && !srcDown.P_4830_p().R_4764_Y()) {
                return 1000000.0;
            }
            return (WC *= 3.3207692307692307) + placeCost + hardness1 + hardness2;
        }
        return 1000000.0;
    }

    @Override
    public MovementState updateState(MovementState state) {
        double dist;
        boolean ladder;
        super.updateState(state);
        K_4074_S pb0 = BlockStateInterface.get(this.ctx, this.positionsToBreak[0]);
        K_4074_S pb1 = BlockStateInterface.get(this.ctx, this.positionsToBreak[1]);
        if (state.getStatus() != MovementStatus.RUNNING) {
            if (!((Boolean)Baritone.settings().walkWhileBreaking.value).booleanValue()) {
                return state;
            }
            if (state.getStatus() != MovementStatus.PREPPING) {
                return state;
            }
            if (MovementHelper.avoidWalkingInto(pb0)) {
                return state;
            }
            if (MovementHelper.avoidWalkingInto(pb1)) {
                return state;
            }
            double dist2 = Math.max(Math.abs(this.ctx.player().s_4990_V().J_1907_R - ((double)this.dest.getX() + 0.5)), Math.abs(this.ctx.player().s_4990_V().G_564_y - ((double)this.dest.getZ() + 0.5)));
            if (dist2 < 0.83) {
                return state;
            }
            if (!state.getTarget().getRotation().isPresent()) {
                return state;
            }
            float yawToDest = RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.calculateBlockCenter(this.ctx.world(), this.dest), this.ctx.playerRotations()).getYaw();
            float pitchToBreak = state.getTarget().getRotation().get().getPitch();
            if (MovementHelper.isBlockNormalCube(pb0) || pb0.J_1907_R() instanceof AirBlock && (MovementHelper.isBlockNormalCube(pb1) || pb1.J_1907_R() instanceof AirBlock)) {
                pitchToBreak = 26.0f;
            }
            return state.setTarget(new MovementState.MovementTarget(new Rotation(yawToDest, pitchToBreak), true)).setInput(Input.MOVE_FORWARD, true).setInput(Input.SPRINT, true);
        }
        state.setInput(Input.SNEAK, false);
        T_2915_h fd = BlockStateInterface.get(this.ctx, this.src.down()).J_1907_R();
        boolean bl = ladder = fd == a_3742_W.L_3570_A || fd == a_3742_W.U_4087_m;
        if (pb0.J_1907_R() instanceof S_1431_H || pb1.J_1907_R() instanceof S_1431_H) {
            boolean canOpen;
            boolean notPassable = pb0.J_1907_R() instanceof S_1431_H && !MovementHelper.isDoorPassable(this.ctx, this.src, this.dest) || pb1.J_1907_R() instanceof S_1431_H && !MovementHelper.isDoorPassable(this.ctx, this.dest, this.src);
            boolean bl2 = canOpen = !a_3742_W.h_2739_B.equals(pb0.J_1907_R()) && !a_3742_W.h_2739_B.equals(pb1.J_1907_R());
            if (notPassable && canOpen) {
                return state.setTarget(new MovementState.MovementTarget(RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.calculateBlockCenter(this.ctx.world(), this.positionsToBreak[0]), this.ctx.playerRotations()), true)).setInput(Input.CLICK_RIGHT, true);
            }
        }
        if (pb0.J_1907_R() instanceof FenceGateBlock || pb1.J_1907_R() instanceof FenceGateBlock) {
            Optional<Rotation> rotation;
            BetterBlockPos blocked;
            BetterBlockPos betterBlockPos = !MovementHelper.isGatePassable(this.ctx, this.positionsToBreak[0], this.src.up()) ? this.positionsToBreak[0] : (blocked = !MovementHelper.isGatePassable(this.ctx, this.positionsToBreak[1], this.src) ? this.positionsToBreak[1] : null);
            if (blocked != null && (rotation = RotationUtils.reachable(this.ctx, blocked)).isPresent()) {
                return state.setTarget(new MovementState.MovementTarget(rotation.get(), true)).setInput(Input.CLICK_RIGHT, true);
            }
        }
        boolean isTheBridgeBlockThere = MovementHelper.canWalkOn(this.ctx, this.positionToPlace) || ladder || MovementHelper.canUseFrostWalker(this.ctx, this.positionToPlace);
        BetterBlockPos feet = this.ctx.playerFeet();
        if (feet.getY() != this.dest.getY() && !ladder) {
            this.logDebug("Wrong Y coordinate");
            if (feet.getY() < this.dest.getY()) {
                return state.setInput(Input.JUMP, true);
            }
            return state;
        }
        if (isTheBridgeBlockThere) {
            if (((z_3539_x)feet).equals(this.dest)) {
                return state.setStatus(MovementStatus.SUCCESS);
            }
            if (((Boolean)Baritone.settings().overshootTraverse.value).booleanValue() && (((z_3539_x)feet).equals(this.dest.add(this.getDirection())) || ((z_3539_x)feet).equals(this.dest.add(this.getDirection()).add(this.getDirection())))) {
                return state.setStatus(MovementStatus.SUCCESS);
            }
            T_2915_h low = BlockStateInterface.get(this.ctx, this.src).J_1907_R();
            T_2915_h high = BlockStateInterface.get(this.ctx, this.src.up()).J_1907_R();
            if (this.ctx.player().s_4990_V().R_4764_Y > (double)this.src.y + 0.1 && !this.ctx.player().M_1641_O() && (low == a_3742_W.U_4087_m || low == a_3742_W.L_3570_A || high == a_3742_W.U_4087_m || high == a_3742_W.L_3570_A)) {
                return state;
            }
            c_1514_x into = this.dest.subtract(this.src).add(this.dest);
            K_4074_S intoBelow = BlockStateInterface.get(this.ctx, into);
            K_4074_S intoAbove = BlockStateInterface.get(this.ctx, into.up());
            if (!(!this.wasTheBridgeBlockAlwaysThere || MovementHelper.isLiquid(this.ctx, feet) && !((Boolean)Baritone.settings().sprintInWater.value).booleanValue() || MovementHelper.avoidWalkingInto(intoBelow) && !MovementHelper.isWater(intoBelow) || MovementHelper.avoidWalkingInto(intoAbove))) {
                state.setInput(Input.SPRINT, true);
            }
            K_4074_S destDown = BlockStateInterface.get(this.ctx, this.dest.down());
            BetterBlockPos against = this.positionsToBreak[0];
            if (feet.getY() != this.dest.getY() && ladder && (destDown.J_1907_R() == a_3742_W.U_4087_m || destDown.J_1907_R() == a_3742_W.L_3570_A)) {
                c_1514_x c_1514_x2 = against = destDown.J_1907_R() == a_3742_W.U_4087_m ? MovementPillar.getAgainst(new CalculationContext(this.baritone), this.dest.down()) : this.dest.offset(destDown.R_4764_Y(C_1985_D.P_4830_p).u_1723_Y());
                if (against == null) {
                    this.logDirect("Unable to climb vines. Consider disabling allowVines.");
                    return state.setStatus(MovementStatus.UNREACHABLE);
                }
            }
            MovementHelper.moveTowards(this.ctx, state, against);
            return state;
        }
        this.wasTheBridgeBlockAlwaysThere = false;
        T_2915_h standingOn = BlockStateInterface.get(this.ctx, ((c_1514_x)feet).down()).J_1907_R();
        if ((standingOn.equals(a_3742_W.C_415_h) || standingOn instanceof y_3008_A) && (dist = Math.max(Math.abs((double)this.dest.getX() + 0.5 - this.ctx.player().s_4990_V().J_1907_R), Math.abs((double)this.dest.getZ() + 0.5 - this.ctx.player().s_4990_V().G_564_y))) < 0.85) {
            MovementHelper.moveTowards(this.ctx, state, this.dest);
            return state.setInput(Input.MOVE_FORWARD, false).setInput(Input.MOVE_BACK, true);
        }
        double dist1 = Math.max(Math.abs(this.ctx.player().s_4990_V().J_1907_R - ((double)this.dest.getX() + 0.5)), Math.abs(this.ctx.player().s_4990_V().G_564_y - ((double)this.dest.getZ() + 0.5)));
        MovementHelper.PlaceResult p = MovementHelper.attemptToPlaceABlock(state, this.baritone, this.dest.down(), false, true);
        if ((p == MovementHelper.PlaceResult.READY_TO_PLACE || dist1 < 0.6) && !((Boolean)Baritone.settings().assumeSafeWalk.value).booleanValue()) {
            state.setInput(Input.SNEAK, true);
        }
        switch (p) {
            case READY_TO_PLACE: {
                if (this.ctx.player().Z_875_P() || ((Boolean)Baritone.settings().assumeSafeWalk.value).booleanValue()) {
                    state.setInput(Input.CLICK_RIGHT, true);
                }
                return state;
            }
            case ATTEMPTING: {
                if (dist1 > 0.83) {
                    float yaw = RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.getBlockPosCenter(this.dest), this.ctx.playerRotations()).getYaw();
                    if ((double)Math.abs(state.getTarget().rotation.getYaw() - yaw) < 0.1) {
                        return state.setInput(Input.MOVE_FORWARD, true);
                    }
                } else if (this.ctx.playerRotations().isReallyCloseTo(state.getTarget().rotation)) {
                    return state.setInput(Input.CLICK_LEFT, true);
                }
                return state;
            }
        }
        if (((z_3539_x)feet).equals(this.dest)) {
            double faceX = ((double)(this.dest.getX() + this.src.getX()) + 1.0) * 0.5;
            double faceY = ((double)(this.dest.getY() + this.src.getY()) - 1.0) * 0.5;
            double faceZ = ((double)(this.dest.getZ() + this.src.getZ()) + 1.0) * 0.5;
            BetterBlockPos goalLook = this.src.down();
            Rotation backToFace = RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), new e_2866_D(faceX, faceY, faceZ), this.ctx.playerRotations());
            float pitch = backToFace.getPitch();
            double dist2 = Math.max(Math.abs(this.ctx.player().s_4990_V().J_1907_R - faceX), Math.abs(this.ctx.player().s_4990_V().G_564_y - faceZ));
            if (dist2 < 0.29) {
                float yaw = RotationUtils.calcRotationFromVec3d(VecUtils.getBlockPosCenter(this.dest), this.ctx.playerHead(), this.ctx.playerRotations()).getYaw();
                state.setTarget(new MovementState.MovementTarget(new Rotation(yaw, pitch), true));
                state.setInput(Input.MOVE_BACK, true);
            } else {
                state.setTarget(new MovementState.MovementTarget(backToFace, true));
            }
            if (this.ctx.isLookingAt(goalLook)) {
                return state.setInput(Input.CLICK_RIGHT, true);
            }
            if (this.ctx.playerRotations().isReallyCloseTo(state.getTarget().rotation)) {
                state.setInput(Input.CLICK_LEFT, true);
            }
            return state;
        }
        MovementHelper.moveTowards(this.ctx, state, this.positionsToBreak[0]);
        return state;
    }

    @Override
    public boolean safeToCancel(MovementState state) {
        return state.getStatus() != MovementStatus.RUNNING || MovementHelper.canWalkOn(this.ctx, this.dest.down());
    }

    @Override
    protected boolean prepared(MovementState state) {
        T_2915_h block;
        if ((this.ctx.playerFeet().equals(this.src) || this.ctx.playerFeet().equals(this.src.down())) && ((block = BlockStateInterface.getBlock(this.ctx, this.src.down())) == a_3742_W.L_3570_A || block == a_3742_W.U_4087_m)) {
            state.setInput(Input.SNEAK, true);
        }
        return super.prepared(state);
    }
}


