/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.pathing.movement.movements;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lightning.product.C_1985_D;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.WaterFluid;
import lightning.product.Items;
import lightning.product.z_3539_x;
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
import mods.baritone.pathing.movement.movements.MovementDescend;
import mods.baritone.utils.pathing.MutableMoveResult;

public class MovementFall
extends Movement {
    private static final Z_1993_T STACK_BUCKET_WATER = new Z_1993_T(Items.W_2770_z);
    private static final Z_1993_T STACK_BUCKET_EMPTY = new Z_1993_T(Items.G_1539_D);

    public MovementFall(IBaritone baritone, BetterBlockPos src, BetterBlockPos dest) {
        super(baritone, src, dest, MovementFall.buildPositionsToBreak(src, dest));
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
        HashSet<BetterBlockPos> set = new HashSet<BetterBlockPos>();
        set.add(this.src);
        for (int y = this.src.y - this.dest.y; y >= 0; --y) {
            set.add(this.dest.up(y));
        }
        return set;
    }

    private boolean willPlaceBucket() {
        CalculationContext context = new CalculationContext(this.baritone);
        MutableMoveResult result = new MutableMoveResult();
        return MovementDescend.dynamicFallCost(context, this.src.x, this.src.y, this.src.z, this.dest.x, this.dest.z, 0.0, context.get(this.dest.x, this.src.y - 2, this.dest.z), result);
    }

    @Override
    public MovementState updateState(MovementState state) {
        z_3539_x avoid;
        super.updateState(state);
        if (state.getStatus() != MovementStatus.RUNNING) {
            return state;
        }
        BetterBlockPos playerFeet = this.ctx.playerFeet();
        Rotation toDest = RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), VecUtils.getBlockPosCenter(this.dest), this.ctx.playerRotations());
        Rotation targetRotation = null;
        K_4074_S destState = this.ctx.world().getBlockState(this.dest);
        T_2915_h destBlock = destState.J_1907_R();
        boolean isWater = destState.P_4830_p().n_1700_B() instanceof WaterFluid;
        if (!isWater && this.willPlaceBucket() && !((z_3539_x)playerFeet).equals(this.dest)) {
            if (!W_3491_f.J_1907_R(this.ctx.player().l_1268_F.J_1907_R(STACK_BUCKET_WATER)) || this.ctx.world().g_2268_R() == b_4507_u.v_4262_N) {
                return state.setStatus(MovementStatus.UNREACHABLE);
            }
            if (this.ctx.player().s_4990_V().R_4764_Y - (double)this.dest.getY() < this.ctx.playerController().getBlockReachDistance() && !this.ctx.player().M_1641_O()) {
                this.ctx.player().l_1268_F.G_564_y = this.ctx.player().l_1268_F.J_1907_R(STACK_BUCKET_WATER);
                targetRotation = new Rotation(toDest.getYaw(), 90.0f);
                if (this.ctx.isLookingAt(this.dest) || this.ctx.isLookingAt(this.dest.down())) {
                    state.setInput(Input.CLICK_RIGHT, true);
                }
            }
        }
        if (targetRotation != null) {
            state.setTarget(new MovementState.MovementTarget(targetRotation, true));
        } else {
            state.setTarget(new MovementState.MovementTarget(toDest, false));
        }
        if (((z_3539_x)playerFeet).equals(this.dest) && (this.ctx.player().s_4990_V().R_4764_Y - (double)playerFeet.getY() < 0.094 || isWater)) {
            if (isWater) {
                if (W_3491_f.J_1907_R(this.ctx.player().l_1268_F.J_1907_R(STACK_BUCKET_EMPTY))) {
                    this.ctx.player().l_1268_F.G_564_y = this.ctx.player().l_1268_F.J_1907_R(STACK_BUCKET_EMPTY);
                    if (this.ctx.player().I_4348_c().R_4764_Y >= 0.0) {
                        return state.setInput(Input.CLICK_RIGHT, true);
                    }
                    return state;
                }
                if (this.ctx.player().I_4348_c().R_4764_Y >= 0.0) {
                    return state.setStatus(MovementStatus.SUCCESS);
                }
            } else {
                return state.setStatus(MovementStatus.SUCCESS);
            }
        }
        e_2866_D destCenter = VecUtils.getBlockPosCenter(this.dest);
        if (Math.abs(this.ctx.player().s_4990_V().J_1907_R + this.ctx.player().I_4348_c().J_1907_R - destCenter.J_1907_R) > 0.1 || Math.abs(this.ctx.player().s_4990_V().G_564_y + this.ctx.player().I_4348_c().G_564_y - destCenter.G_564_y) > 0.1) {
            if (!this.ctx.player().M_1641_O() && Math.abs(this.ctx.player().I_4348_c().R_4764_Y) > 0.4) {
                state.setInput(Input.SNEAK, true);
            }
            state.setInput(Input.MOVE_FORWARD, true);
        }
        if ((avoid = (z_3539_x)Optional.ofNullable(this.avoid()).map(b_257_Y::M_182_A).orElse(null)) == null) {
            avoid = this.src.subtract(this.dest);
        } else {
            double dist = Math.abs((double)avoid.getX() * (destCenter.J_1907_R - (double)avoid.getX() / 2.0 - this.ctx.player().s_4990_V().J_1907_R)) + Math.abs((double)avoid.getZ() * (destCenter.G_564_y - (double)avoid.getZ() / 2.0 - this.ctx.player().s_4990_V().G_564_y));
            if (dist < 0.6) {
                state.setInput(Input.MOVE_FORWARD, true);
            } else if (!this.ctx.player().M_1641_O()) {
                state.setInput(Input.SNEAK, false);
            }
        }
        if (targetRotation == null) {
            e_2866_D destCenterOffset = new e_2866_D(destCenter.J_1907_R + 0.125 * (double)avoid.getX(), destCenter.R_4764_Y, destCenter.G_564_y + 0.125 * (double)avoid.getZ());
            state.setTarget(new MovementState.MovementTarget(RotationUtils.calcRotationFromVec3d(this.ctx.playerHead(), destCenterOffset, this.ctx.playerRotations()), false));
        }
        return state;
    }

    private b_257_Y avoid() {
        for (int i = 0; i < 15; ++i) {
            K_4074_S state = this.ctx.world().getBlockState(this.ctx.playerFeet().down(i));
            if (state.J_1907_R() != a_3742_W.L_3570_A) continue;
            return state.R_4764_Y(C_1985_D.P_4830_p);
        }
        return null;
    }

    @Override
    public boolean safeToCancel(MovementState state) {
        return this.ctx.playerFeet().equals(this.src) || state.getStatus() != MovementStatus.RUNNING;
    }

    private static BetterBlockPos[] buildPositionsToBreak(BetterBlockPos src, BetterBlockPos dest) {
        int diffX = src.getX() - dest.getX();
        int diffZ = src.getZ() - dest.getZ();
        int diffY = src.getY() - dest.getY();
        BetterBlockPos[] toBreak = new BetterBlockPos[diffY + 2];
        for (int i = 0; i < toBreak.length; ++i) {
            toBreak[i] = new BetterBlockPos(src.getX() - diffX, src.getY() + 1 - i, src.getZ() - diffZ);
        }
        return toBreak;
    }

    @Override
    protected boolean prepared(MovementState state) {
        if (state.getStatus() == MovementStatus.WAITING) {
            return true;
        }
        for (int i = 0; i < 4 && i < this.positionsToBreak.length; ++i) {
            if (MovementHelper.canWalkThrough(this.ctx, this.positionsToBreak[i])) continue;
            return super.prepared(state);
        }
        return true;
    }
}


