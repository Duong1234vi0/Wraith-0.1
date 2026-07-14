/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.pathing.movement.movements;

import java.util.HashSet;
import java.util.Set;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.WaterFluid;
import lightning.product.z_2909_G;
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

public class MovementParkour
extends Movement {
    private static final BetterBlockPos[] EMPTY = new BetterBlockPos[0];
    private final b_257_Y direction;
    private final int dist;
    private final boolean ascend;

    private MovementParkour(IBaritone baritone, BetterBlockPos src, int dist, b_257_Y dir, boolean ascend) {
        super(baritone, src, src.offset(dir, dist).up(ascend ? 1 : 0), EMPTY, src.offset(dir, dist).down(ascend ? 0 : 1));
        this.direction = dir;
        this.dist = dist;
        this.ascend = ascend;
    }

    public static MovementParkour cost(CalculationContext context, BetterBlockPos src, b_257_Y direction) {
        MutableMoveResult res = new MutableMoveResult();
        MovementParkour.cost(context, src.x, src.y, src.z, direction, res);
        int dist = Math.abs(res.x - src.x) + Math.abs(res.z - src.z);
        return new MovementParkour(context.getBaritone(), src, dist, direction, res.y > src.y);
    }

    public static void cost(CalculationContext context, int x, int y, int z, b_257_Y dir, MutableMoveResult res) {
        int destZ;
        int destX;
        int zDiff;
        if (!context.allowParkour) {
            return;
        }
        if (y == 256 && !context.allowJumpAt256) {
            return;
        }
        int xDiff = dir.t_148_a();
        if (!MovementHelper.fullyPassable(context, x + xDiff, y, z + (zDiff = dir.u_2550_I()))) {
            return;
        }
        K_4074_S adj = context.get(x + xDiff, y - 1, z + zDiff);
        if (MovementHelper.canWalkOn(context, x + xDiff, y - 1, z + zDiff, adj)) {
            return;
        }
        if (MovementHelper.avoidWalkingInto(adj) && !(adj.P_4830_p().n_1700_B() instanceof WaterFluid)) {
            return;
        }
        if (!MovementHelper.fullyPassable(context, x + xDiff, y + 1, z + zDiff)) {
            return;
        }
        if (!MovementHelper.fullyPassable(context, x + xDiff, y + 2, z + zDiff)) {
            return;
        }
        if (!MovementHelper.fullyPassable(context, x, y + 2, z)) {
            return;
        }
        K_4074_S standingOn = context.get(x, y - 1, z);
        if (standingOn.J_1907_R() == a_3742_W.U_4087_m || standingOn.J_1907_R() == a_3742_W.L_3570_A || standingOn.J_1907_R() instanceof z_2909_G || MovementHelper.isBottomSlab(standingOn)) {
            return;
        }
        if (context.assumeWalkOnWater && !standingOn.P_4830_p().R_4764_Y()) {
            return;
        }
        if (!context.get(x, y, z).P_4830_p().R_4764_Y()) {
            return;
        }
        int maxJump = standingOn.J_1907_R() == a_3742_W.C_415_h ? 2 : (context.canSprint ? 4 : 3);
        int verifiedMaxJump = 1;
        int i = 2;
        while (i <= maxJump && MovementHelper.fullyPassable(context, destX = x + xDiff * i, y + 1, destZ = z + zDiff * i) && MovementHelper.fullyPassable(context, destX, y + 2, destZ)) {
            K_4074_S destInto = context.bsi.get0(destX, y, destZ);
            if (!MovementHelper.fullyPassable(context, destX, y, destZ, destInto)) {
                if (i > 3 || !context.allowParkourAscend || !context.canSprint || !MovementHelper.canWalkOn(context, destX, y, destZ, destInto) || !MovementParkour.checkOvershootSafety(context.bsi, destX + xDiff, y + 1, destZ + zDiff)) break;
                res.x = destX;
                res.y = y + 1;
                res.z = destZ;
                res.cost = (double)i * 3.563791874554526 + context.jumpPenalty;
                return;
            }
            K_4074_S landingOn = context.bsi.get0(destX, y - 1, destZ);
            if (landingOn.J_1907_R() != a_3742_W.Z_735_d && MovementHelper.canWalkOn(context, destX, y - 1, destZ, landingOn) || Math.min(16, context.frostWalker + 2) >= i && MovementHelper.canUseFrostWalker(context, landingOn)) {
                if (!MovementParkour.checkOvershootSafety(context.bsi, destX + xDiff, y, destZ + zDiff)) break;
                res.x = destX;
                res.y = y;
                res.z = destZ;
                res.cost = MovementParkour.costFromJumpDistance(i) + context.jumpPenalty;
                return;
            }
            if (!MovementHelper.fullyPassable(context, destX, y + 3, destZ)) break;
            verifiedMaxJump = i++;
        }
        if (!context.allowParkourPlace) {
            return;
        }
        for (i = verifiedMaxJump; i > 1; --i) {
            destX = x + i * xDiff;
            destZ = z + i * zDiff;
            K_4074_S toReplace = context.get(destX, y - 1, destZ);
            double placeCost = context.costOfPlacingAt(destX, y - 1, destZ, toReplace);
            if (placeCost >= 1000000.0 || !MovementHelper.isReplaceable(destX, y - 1, destZ, toReplace, context.bsi) || !MovementParkour.checkOvershootSafety(context.bsi, destX + xDiff, y, destZ + zDiff)) continue;
            for (int j = 0; j < 5; ++j) {
                int againstX = destX + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[j].t_148_a();
                int againstY = y - 1 + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[j].s_956_w();
                int againstZ = destZ + HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[j].u_2550_I();
                if (againstX == destX - xDiff && againstZ == destZ - zDiff || !MovementHelper.canPlaceAgainst(context.bsi, againstX, againstY, againstZ)) continue;
                res.x = destX;
                res.y = y;
                res.z = destZ;
                res.cost = MovementParkour.costFromJumpDistance(i) + placeCost + context.jumpPenalty;
                return;
            }
        }
    }

    private static boolean checkOvershootSafety(BlockStateInterface bsi, int x, int y, int z) {
        return !MovementHelper.avoidWalkingInto(bsi.get0(x, y, z)) && !MovementHelper.avoidWalkingInto(bsi.get0(x, y + 1, z));
    }

    private static double costFromJumpDistance(int dist) {
        switch (dist) {
            case 2: {
                return 9.26569376882094;
            }
            case 3: {
                return 13.89854065323141;
            }
            case 4: {
                return 14.255167498218103;
            }
        }
        throw new IllegalStateException("LOL " + dist);
    }

    @Override
    public double calculateCost(CalculationContext context) {
        MutableMoveResult res = new MutableMoveResult();
        MovementParkour.cost(context, this.src.x, this.src.y, this.src.z, this.direction, res);
        if (res.x != this.dest.x || res.y != this.dest.y || res.z != this.dest.z) {
            return 1000000.0;
        }
        return res.cost;
    }

    @Override
    protected Set<BetterBlockPos> calculateValidPositions() {
        HashSet<BetterBlockPos> set = new HashSet<BetterBlockPos>();
        for (int i = 0; i <= this.dist; ++i) {
            for (int y = 0; y < 2; ++y) {
                set.add(this.src.offset(this.direction, i).up(y));
            }
        }
        return set;
    }

    @Override
    public boolean safeToCancel(MovementState state) {
        return state.getStatus() != MovementStatus.RUNNING;
    }

    @Override
    public MovementState updateState(MovementState state) {
        super.updateState(state);
        if (state.getStatus() != MovementStatus.RUNNING) {
            return state;
        }
        if (this.ctx.playerFeet().y < this.src.y) {
            this.logDebug("sorry");
            return state.setStatus(MovementStatus.UNREACHABLE);
        }
        if (this.dist >= 4 || this.ascend) {
            state.setInput(Input.SPRINT, true);
        }
        MovementHelper.moveTowards(this.ctx, state, this.dest);
        if (this.ctx.playerFeet().equals(this.dest)) {
            T_2915_h d = BlockStateInterface.getBlock(this.ctx, this.dest);
            if (d == a_3742_W.U_4087_m || d == a_3742_W.L_3570_A) {
                return state.setStatus(MovementStatus.SUCCESS);
            }
            if (this.ctx.player().s_4990_V().R_4764_Y - (double)this.ctx.playerFeet().getY() < 0.094) {
                state.setStatus(MovementStatus.SUCCESS);
            }
        } else if (!this.ctx.playerFeet().equals(this.src)) {
            if (this.ctx.playerFeet().equals(this.src.offset(this.direction)) || this.ctx.player().s_4990_V().R_4764_Y - (double)this.src.y > 1.0E-4) {
                if (((Boolean)Baritone.settings().allowPlace.value).booleanValue() && ((Baritone)this.baritone).getInventoryBehavior().hasGenericThrowaway() && !MovementHelper.canWalkOn(this.ctx, this.dest.down()) && !this.ctx.player().M_1641_O() && MovementHelper.attemptToPlaceABlock(state, this.baritone, this.dest.down(), true, false) == MovementHelper.PlaceResult.READY_TO_PLACE) {
                    state.setInput(Input.CLICK_RIGHT, true);
                }
                if (this.dist == 3 && !this.ascend) {
                    double xDiff = (double)this.src.x + 0.5 - this.ctx.player().s_4990_V().J_1907_R;
                    double zDiff = (double)this.src.z + 0.5 - this.ctx.player().s_4990_V().G_564_y;
                    double distFromStart = Math.max(Math.abs(xDiff), Math.abs(zDiff));
                    if (distFromStart < 0.7) {
                        return state;
                    }
                }
                state.setInput(Input.JUMP, true);
            } else if (!this.ctx.playerFeet().equals(this.dest.offset(this.direction, -1))) {
                state.setInput(Input.SPRINT, false);
                if (this.ctx.playerFeet().equals(this.src.offset(this.direction, -1))) {
                    MovementHelper.moveTowards(this.ctx, state, this.src);
                } else {
                    MovementHelper.moveTowards(this.ctx, state, this.src.offset(this.direction, -1));
                }
            }
        }
        return state;
    }
}


