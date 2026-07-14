/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.pathing.movement;

import java.util.List;
import java.util.Optional;
import lightning.product.EndPortalBlock;
import lightning.product.E_872_n;
import lightning.product.F_4312_i;
import lightning.product.BlockHitResult;
import lightning.product.InfestedBlock;
import lightning.product.HitResult;
import lightning.product.Fluids;
import lightning.product.K_2390_Z;
import lightning.product.HorizontalDirectionalBlock;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.WoolCarpetBlock;
import lightning.product.S_1431_H;
import lightning.product.BaseFireBlock;
import lightning.product.T_2915_h;
import lightning.product.AbstractSkullBlock;
import lightning.product.U_1266_O;
import lightning.product.U_4243_e;
import lightning.product.V_2454_J;
import lightning.product.SkullBlock;
import lightning.product.Y_3462_U;
import lightning.product.FenceGateBlock;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.WaterlilyBlock;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.AirBlock;
import lightning.product.m_2244_y;
import lightning.product.n_1769_f;
import lightning.product.WaterFluid;
import lightning.product.s_3401_U;
import lightning.product.s_3834_w;
import lightning.product.Fluid;
import lightning.product.Material;
import lightning.product.t_3546_P;
import lightning.product.u_863_c;
import lightning.product.FallingBlock;
import lightning.product.x_2838_H;
import lightning.product.SnowLayerBlock;
import lightning.product.y_3008_A;
import lightning.product.z_2909_G;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.pathing.movement.ActionCosts;
import mods.baritone.api.api.java.baritone.api.pathing.movement.MovementStatus;
import mods.baritone.api.api.java.baritone.api.utils.BetterBlockPos;
import mods.baritone.api.api.java.baritone.api.utils.Helper;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.baritone.api.api.java.baritone.api.utils.RayTraceUtils;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.api.api.java.baritone.api.utils.RotationUtils;
import mods.baritone.api.api.java.baritone.api.utils.VecUtils;
import mods.baritone.api.api.java.baritone.api.utils.input.Input;
import mods.baritone.pathing.movement.CalculationContext;
import mods.baritone.pathing.movement.Movement;
import mods.baritone.pathing.movement.MovementState;
import mods.baritone.pathing.precompute.Ternary;
import mods.baritone.utils.BlockStateInterface;
import mods.baritone.utils.ToolSet;

public interface MovementHelper
extends ActionCosts,
Helper {
    public static boolean avoidBreaking(BlockStateInterface bsi, int x, int y, int z, K_4074_S state) {
        if (!bsi.worldBorder.canPlaceAt(x, z)) {
            return true;
        }
        T_2915_h b = state.J_1907_R();
        return ((List)Baritone.settings().blocksToDisallowBreaking.value).contains(b) || b == a_3742_W.O_1795_e || b instanceof InfestedBlock || MovementHelper.avoidAdjacentBreaking(bsi, x, y + 1, z, true) || MovementHelper.avoidAdjacentBreaking(bsi, x + 1, y, z, false) || MovementHelper.avoidAdjacentBreaking(bsi, x - 1, y, z, false) || MovementHelper.avoidAdjacentBreaking(bsi, x, y, z + 1, false) || MovementHelper.avoidAdjacentBreaking(bsi, x, y, z - 1, false);
    }

    public static boolean avoidAdjacentBreaking(BlockStateInterface bsi, int x, int y, int z, boolean directlyAbove) {
        K_4074_S state = bsi.get0(x, y, z);
        T_2915_h block = state.J_1907_R();
        if (!directlyAbove && block instanceof FallingBlock && ((Boolean)Baritone.settings().avoidUpdatingFallingBlocks.value).booleanValue() && FallingBlock.t_148_a(bsi.get0(x, y - 1, z))) {
            return true;
        }
        if (block instanceof s_3834_w) {
            if (directlyAbove || ((Boolean)Baritone.settings().strictLiquidCheck.value).booleanValue()) {
                return true;
            }
            int level = state.R_4764_Y(s_3834_w.P_4830_p);
            if (level == 0) {
                return true;
            }
            return !(bsi.get0(x, y - 1, z).J_1907_R() instanceof s_3834_w);
        }
        return !state.P_4830_p().R_4764_Y();
    }

    public static boolean canWalkThrough(IPlayerContext ctx, BetterBlockPos pos) {
        return MovementHelper.canWalkThrough(new BlockStateInterface(ctx), pos.x, pos.y, pos.z);
    }

    public static boolean canWalkThrough(BlockStateInterface bsi, int x, int y, int z) {
        return MovementHelper.canWalkThrough(bsi, x, y, z, bsi.get0(x, y, z));
    }

    public static boolean canWalkThrough(CalculationContext context, int x, int y, int z, K_4074_S state) {
        return context.precomputedData.canWalkThrough(context.bsi, x, y, z, state);
    }

    public static boolean canWalkThrough(CalculationContext context, int x, int y, int z) {
        return context.precomputedData.canWalkThrough(context.bsi, x, y, z, context.get(x, y, z));
    }

    public static boolean canWalkThrough(BlockStateInterface bsi, int x, int y, int z, K_4074_S state) {
        Ternary canWalkThrough = MovementHelper.canWalkThroughBlockState(state);
        if (canWalkThrough == Ternary.YES) {
            return true;
        }
        if (canWalkThrough == Ternary.NO) {
            return false;
        }
        return MovementHelper.canWalkThroughPosition(bsi, x, y, z, state);
    }

    public static Ternary canWalkThroughBlockState(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        if (block instanceof AirBlock) {
            return Ternary.YES;
        }
        if (block instanceof BaseFireBlock || block == a_3742_W.I_3637_j || block == a_3742_W.y_1700_S || block == a_3742_W.M_2562_s || block == a_3742_W.U_3823_u || block instanceof AbstractSkullBlock || block == a_3742_W.S_4325_V || block instanceof Y_3462_U || block instanceof y_3008_A || block instanceof x_2838_H || block == a_3742_W.B_1335_M || block == a_3742_W.BaritoneSettings || block == a_3742_W.s_4405_m) {
            return Ternary.NO;
        }
        if (((List)Baritone.settings().blocksToAvoid.value).contains(block)) {
            return Ternary.NO;
        }
        if (block instanceof S_1431_H || block instanceof FenceGateBlock) {
            if (block == a_3742_W.h_2739_B) {
                return Ternary.NO;
            }
            return Ternary.YES;
        }
        if (block instanceof WoolCarpetBlock) {
            return Ternary.MAYBE;
        }
        if (block instanceof SnowLayerBlock) {
            return Ternary.MAYBE;
        }
        FluidState fluidState = state.P_4830_p();
        if (!fluidState.R_4764_Y()) {
            if (fluidState.n_1700_B().G_564_y(fluidState) != 8) {
                return Ternary.NO;
            }
            return Ternary.MAYBE;
        }
        if (block instanceof K_2390_Z) {
            return Ternary.NO;
        }
        try {
            if (state.n_1700_B(null, null, t_3546_P.n_1700_B)) {
                return Ternary.YES;
            }
            return Ternary.NO;
        }
        catch (Throwable exception) {
            System.out.println("The block " + state.J_1907_R().M_588_G().getString() + " requires a special case due to the exception " + exception.getMessage());
            return Ternary.MAYBE;
        }
    }

    public static boolean canWalkThroughPosition(BlockStateInterface bsi, int x, int y, int z, K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        if (block instanceof WoolCarpetBlock) {
            return MovementHelper.canWalkOn(bsi, x, y - 1, z);
        }
        if (block instanceof SnowLayerBlock) {
            if (!bsi.worldContainsLoadedChunk(x, z)) {
                return true;
            }
            if (state.R_4764_Y(SnowLayerBlock.P_4830_p) >= 3) {
                return false;
            }
            return MovementHelper.canWalkOn(bsi, x, y - 1, z);
        }
        FluidState fluidState = state.P_4830_p();
        if (!fluidState.R_4764_Y()) {
            if (MovementHelper.isFlowing(x, y, z, state, bsi)) {
                return false;
            }
            if (((Boolean)Baritone.settings().assumeWalkOnWater.value).booleanValue()) {
                return false;
            }
            K_4074_S up = bsi.get0(x, y + 1, z);
            if (!up.P_4830_p().R_4764_Y() || up.J_1907_R() instanceof WaterlilyBlock) {
                return false;
            }
            return fluidState.n_1700_B() instanceof WaterFluid;
        }
        return state.n_1700_B(bsi.access, c_1514_x.ZERO, t_3546_P.n_1700_B);
    }

    public static Ternary fullyPassableBlockState(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        if (block instanceof AirBlock) {
            return Ternary.YES;
        }
        if (block instanceof BaseFireBlock || block == a_3742_W.I_3637_j || block == a_3742_W.y_1700_S || block == a_3742_W.U_4087_m || block == a_3742_W.L_3570_A || block == a_3742_W.U_3823_u || block instanceof S_1431_H || block instanceof FenceGateBlock || block instanceof SnowLayerBlock || !state.P_4830_p().R_4764_Y() || block instanceof x_2838_H || block instanceof EndPortalBlock || block instanceof SkullBlock || block instanceof Y_3462_U) {
            return Ternary.NO;
        }
        try {
            if (state.n_1700_B(null, null, t_3546_P.n_1700_B)) {
                return Ternary.YES;
            }
            return Ternary.NO;
        }
        catch (Throwable exception) {
            System.out.println("The block " + state.J_1907_R().M_588_G().getString() + " requires a special case due to the exception " + exception.getMessage());
            return Ternary.MAYBE;
        }
    }

    public static boolean fullyPassable(CalculationContext context, int x, int y, int z) {
        return MovementHelper.fullyPassable(context, x, y, z, context.get(x, y, z));
    }

    public static boolean fullyPassable(CalculationContext context, int x, int y, int z, K_4074_S state) {
        return context.precomputedData.fullyPassable(context.bsi, x, y, z, state);
    }

    public static boolean fullyPassable(IPlayerContext ctx, c_1514_x pos) {
        K_4074_S state = ctx.world().getBlockState(pos);
        Ternary fullyPassable = MovementHelper.fullyPassableBlockState(state);
        if (fullyPassable == Ternary.YES) {
            return true;
        }
        if (fullyPassable == Ternary.NO) {
            return false;
        }
        return MovementHelper.fullyPassablePosition(new BlockStateInterface(ctx), pos.getX(), pos.getY(), pos.getZ(), state);
    }

    public static boolean fullyPassablePosition(BlockStateInterface bsi, int x, int y, int z, K_4074_S state) {
        return state.n_1700_B(bsi.access, (c_1514_x)bsi.isPassableBlockPos.n_1700_B(x, y, z), t_3546_P.n_1700_B);
    }

    public static boolean isReplaceable(int x, int y, int z, K_4074_S state, BlockStateInterface bsi) {
        T_2915_h block = state.J_1907_R();
        if (block instanceof AirBlock) {
            return true;
        }
        if (block instanceof SnowLayerBlock) {
            if (!bsi.worldContainsLoadedChunk(x, z)) {
                return true;
            }
            return state.R_4764_Y(SnowLayerBlock.P_4830_p) == 1;
        }
        if (block == a_3742_W.PotionTracker || block == a_3742_W.Party) {
            return true;
        }
        return state.R_4764_Y().P_1922_E();
    }

    @Deprecated
    public static boolean isReplacable(int x, int y, int z, K_4074_S state, BlockStateInterface bsi) {
        return MovementHelper.isReplaceable(x, y, z, state, bsi);
    }

    public static boolean isDoorPassable(IPlayerContext ctx, c_1514_x doorPos, c_1514_x playerPos) {
        if (playerPos.equals(doorPos)) {
            return false;
        }
        K_4074_S state = BlockStateInterface.get(ctx, doorPos);
        if (!(state.J_1907_R() instanceof S_1431_H)) {
            return true;
        }
        return MovementHelper.isHorizontalBlockPassable(doorPos, state, playerPos, S_1431_H.h_1847_R);
    }

    public static boolean isGatePassable(IPlayerContext ctx, c_1514_x gatePos, c_1514_x playerPos) {
        if (playerPos.equals(gatePos)) {
            return false;
        }
        K_4074_S state = BlockStateInterface.get(ctx, gatePos);
        if (!(state.J_1907_R() instanceof FenceGateBlock)) {
            return true;
        }
        return state.R_4764_Y(FenceGateBlock.P_4830_p);
    }

    public static boolean isHorizontalBlockPassable(c_1514_x blockPos, K_4074_S blockState, c_1514_x playerPos, U_1266_O propertyOpen) {
        b_257_Y.n_1700_B playerFacing;
        if (playerPos.equals(blockPos)) {
            return false;
        }
        b_257_Y.n_1700_B facing = blockState.R_4764_Y(HorizontalDirectionalBlock.w_612_n).h_1847_R();
        boolean open = blockState.R_4764_Y(propertyOpen);
        if (playerPos.north().equals(blockPos) || playerPos.south().equals(blockPos)) {
            playerFacing = b_257_Y.n_1700_B.R_4764_Y;
        } else if (playerPos.east().equals(blockPos) || playerPos.west().equals(blockPos)) {
            playerFacing = b_257_Y.n_1700_B.n_1700_B;
        } else {
            return true;
        }
        return facing == playerFacing == open;
    }

    public static boolean avoidWalkingInto(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        return !state.P_4830_p().R_4764_Y() || block == a_3742_W.LevitationControl || block == a_3742_W.d_3244_b || block == a_3742_W.s_4405_m || block instanceof BaseFireBlock || block == a_3742_W.M_2562_s || block == a_3742_W.y_1700_S || block == a_3742_W.S_4325_V;
    }

    public static boolean canWalkOn(BlockStateInterface bsi, int x, int y, int z, K_4074_S state) {
        Ternary canWalkOn = MovementHelper.canWalkOnBlockState(state);
        if (canWalkOn == Ternary.YES) {
            return true;
        }
        if (canWalkOn == Ternary.NO) {
            return false;
        }
        return MovementHelper.canWalkOnPosition(bsi, x, y, z, state);
    }

    public static Ternary canWalkOnBlockState(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        if (MovementHelper.isBlockNormalCube(state) && block != a_3742_W.LevitationControl && block != a_3742_W.S_4325_V && block != a_3742_W.B_1335_M) {
            return Ternary.YES;
        }
        if (block == a_3742_W.L_3570_A || block == a_3742_W.U_4087_m && ((Boolean)Baritone.settings().allowVines.value).booleanValue()) {
            return Ternary.YES;
        }
        if (block == a_3742_W.Z_735_d || block == a_3742_W.InvManager) {
            return Ternary.YES;
        }
        if (block == a_3742_W.k_2348_i || block == a_3742_W.L_1362_X || block == a_3742_W.NumberSetting) {
            return Ternary.YES;
        }
        if (block == a_3742_W.e_1992_r || block instanceof F_4312_i) {
            return Ternary.YES;
        }
        if (block instanceof z_2909_G) {
            return Ternary.YES;
        }
        if (MovementHelper.isWater(state)) {
            return Ternary.MAYBE;
        }
        if (MovementHelper.isLava(state) && ((Boolean)Baritone.settings().assumeWalkOnLava.value).booleanValue()) {
            return Ternary.MAYBE;
        }
        if (block instanceof y_3008_A) {
            if (!((Boolean)Baritone.settings().allowWalkOnBottomSlab.value).booleanValue()) {
                if (state.R_4764_Y(y_3008_A.P_4830_p) != n_1769_f.J_1907_R) {
                    return Ternary.YES;
                }
                return Ternary.NO;
            }
            return Ternary.YES;
        }
        return Ternary.NO;
    }

    public static boolean canWalkOnPosition(BlockStateInterface bsi, int x, int y, int z, K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        if (MovementHelper.isWater(state)) {
            K_4074_S upState = bsi.get0(x, y + 1, z);
            T_2915_h up = upState.J_1907_R();
            if (up == a_3742_W.S_4035_N || up instanceof WoolCarpetBlock) {
                return true;
            }
            if (MovementHelper.isFlowing(x, y, z, state, bsi) || upState.P_4830_p().n_1700_B() == Fluids.J_1907_R) {
                return MovementHelper.isWater(upState) && (Boolean)Baritone.settings().assumeWalkOnWater.value == false;
            }
            return MovementHelper.isWater(upState) ^ (Boolean)Baritone.settings().assumeWalkOnWater.value;
        }
        return MovementHelper.isLava(state) && !MovementHelper.isFlowing(x, y, z, state, bsi) && (Boolean)Baritone.settings().assumeWalkOnLava.value != false;
    }

    public static boolean canWalkOn(CalculationContext context, int x, int y, int z, K_4074_S state) {
        return context.precomputedData.canWalkOn(context.bsi, x, y, z, state);
    }

    public static boolean canWalkOn(CalculationContext context, int x, int y, int z) {
        return MovementHelper.canWalkOn(context, x, y, z, context.get(x, y, z));
    }

    public static boolean canWalkOn(IPlayerContext ctx, BetterBlockPos pos, K_4074_S state) {
        return MovementHelper.canWalkOn(new BlockStateInterface(ctx), pos.x, pos.y, pos.z, state);
    }

    public static boolean canWalkOn(IPlayerContext ctx, c_1514_x pos) {
        return MovementHelper.canWalkOn(new BlockStateInterface(ctx), pos.getX(), pos.getY(), pos.getZ());
    }

    public static boolean canWalkOn(IPlayerContext ctx, BetterBlockPos pos) {
        return MovementHelper.canWalkOn(new BlockStateInterface(ctx), pos.x, pos.y, pos.z);
    }

    public static boolean canWalkOn(BlockStateInterface bsi, int x, int y, int z) {
        return MovementHelper.canWalkOn(bsi, x, y, z, bsi.get0(x, y, z));
    }

    public static boolean canUseFrostWalker(CalculationContext context, K_4074_S state) {
        return context.frostWalker != 0 && state.R_4764_Y() == Material.s_956_w && state.R_4764_Y(s_3834_w.P_4830_p) == 0;
    }

    public static boolean canUseFrostWalker(IPlayerContext ctx, c_1514_x pos) {
        K_4074_S state = BlockStateInterface.get(ctx, pos);
        return K_4096_w.t_148_a(ctx.player()) && state.R_4764_Y() == Material.s_956_w && state.R_4764_Y(s_3834_w.P_4830_p) == 0;
    }

    public static boolean mustBeSolidToWalkOn(CalculationContext context, int x, int y, int z, K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        if (block == a_3742_W.L_3570_A || block == a_3742_W.U_4087_m) {
            return false;
        }
        if (!state.P_4830_p().R_4764_Y()) {
            if (block instanceof y_3008_A) {
                if (state.R_4764_Y(y_3008_A.P_4830_p) != n_1769_f.J_1907_R) {
                    return true;
                }
            } else if (block instanceof z_2909_G) {
                if (state.R_4764_Y(z_2909_G.h_1847_R) == m_2244_y.n_1700_B) {
                    return true;
                }
                u_863_c shape = state.R_4764_Y(z_2909_G.Q_4569_t);
                if (shape == u_863_c.J_1907_R || shape == u_863_c.R_4764_Y) {
                    return true;
                }
            } else if (block instanceof x_2838_H ? state.R_4764_Y(x_2838_H.P_4830_p) == false && state.R_4764_Y(x_2838_H.h_1847_R) == m_2244_y.n_1700_B : block == a_3742_W.i_770_g) {
                return true;
            }
            if (context.assumeWalkOnWater) {
                return false;
            }
            T_2915_h blockAbove = context.getBlock(x, y + 1, z);
            if (blockAbove instanceof s_3834_w) {
                return false;
            }
        }
        return true;
    }

    public static boolean canPlaceAgainst(BlockStateInterface bsi, int x, int y, int z) {
        return MovementHelper.canPlaceAgainst(bsi, x, y, z, bsi.get0(x, y, z));
    }

    public static boolean canPlaceAgainst(BlockStateInterface bsi, c_1514_x pos) {
        return MovementHelper.canPlaceAgainst(bsi, pos.getX(), pos.getY(), pos.getZ());
    }

    public static boolean canPlaceAgainst(IPlayerContext ctx, c_1514_x pos) {
        return MovementHelper.canPlaceAgainst(new BlockStateInterface(ctx), pos);
    }

    public static boolean canPlaceAgainst(BlockStateInterface bsi, int x, int y, int z, K_4074_S state) {
        if (!bsi.worldBorder.canPlaceAt(x, z)) {
            return false;
        }
        return MovementHelper.isBlockNormalCube(state) || state.J_1907_R() == a_3742_W.e_1992_r || state.J_1907_R() instanceof F_4312_i;
    }

    public static double getMiningDurationTicks(CalculationContext context, int x, int y, int z, boolean includeFalling) {
        return MovementHelper.getMiningDurationTicks(context, x, y, z, context.get(x, y, z), includeFalling);
    }

    public static double getMiningDurationTicks(CalculationContext context, int x, int y, int z, K_4074_S state, boolean includeFalling) {
        T_2915_h block = state.J_1907_R();
        if (!MovementHelper.canWalkThrough(context, x, y, z, state)) {
            K_4074_S above;
            if (!state.P_4830_p().R_4764_Y()) {
                return 1000000.0;
            }
            double mult = context.breakCostMultiplierAt(x, y, z, state);
            if (mult >= 1000000.0) {
                return 1000000.0;
            }
            if (MovementHelper.avoidBreaking(context.bsi, x, y, z, state)) {
                return 1000000.0;
            }
            double strVsBlock = context.toolSet.getStrVsBlock(state);
            if (strVsBlock <= 0.0) {
                return 1000000.0;
            }
            double result = 1.0 / strVsBlock;
            result += context.breakBlockAdditionalCost;
            result *= mult;
            if (includeFalling && (above = context.get(x, y + 1, z)).J_1907_R() instanceof FallingBlock) {
                result += MovementHelper.getMiningDurationTicks(context, x, y + 1, z, above, true);
            }
            return result;
        }
        return 0.0;
    }

    public static boolean isBottomSlab(K_4074_S state) {
        return state.J_1907_R() instanceof y_3008_A && state.R_4764_Y(y_3008_A.P_4830_p) == n_1769_f.J_1907_R;
    }

    public static void switchToBestToolFor(IPlayerContext ctx, K_4074_S b) {
        MovementHelper.switchToBestToolFor(ctx, b, new ToolSet(ctx.player()), (Boolean)BaritoneAPI.getSettings().preferSilkTouch.value);
    }

    public static void switchToBestToolFor(IPlayerContext ctx, K_4074_S b, ToolSet ts, boolean preferSilkTouch) {
        if (((Boolean)Baritone.settings().autoTool.value).booleanValue() && !((Boolean)Baritone.settings().assumeExternalAutoTool.value).booleanValue()) {
            ctx.player().l_1268_F.G_564_y = ts.getBestSlot(b.J_1907_R(), preferSilkTouch);
        }
    }

    public static void moveTowards(IPlayerContext ctx, MovementState state, c_1514_x pos) {
        state.setTarget(new MovementState.MovementTarget(RotationUtils.calcRotationFromVec3d(ctx.playerHead(), VecUtils.getBlockPosCenter(pos), ctx.playerRotations()).withPitch(ctx.playerRotations().getPitch()), false)).setInput(Input.MOVE_FORWARD, true);
    }

    public static boolean isWater(K_4074_S state) {
        Fluid f = state.P_4830_p().n_1700_B();
        return f == Fluids.R_4764_Y || f == Fluids.J_1907_R;
    }

    public static boolean isWater(IPlayerContext ctx, c_1514_x bp) {
        return MovementHelper.isWater(BlockStateInterface.get(ctx, bp));
    }

    public static boolean isLava(K_4074_S state) {
        Fluid f = state.P_4830_p().n_1700_B();
        return f == Fluids.P_1922_E || f == Fluids.G_564_y;
    }

    public static boolean isLiquid(IPlayerContext ctx, c_1514_x p) {
        return MovementHelper.isLiquid(BlockStateInterface.get(ctx, p));
    }

    public static boolean isLiquid(K_4074_S blockState) {
        return !blockState.P_4830_p().R_4764_Y();
    }

    public static boolean possiblyFlowing(K_4074_S state) {
        FluidState fluidState = state.P_4830_p();
        return fluidState.n_1700_B() instanceof U_4243_e && fluidState.n_1700_B().G_564_y(fluidState) != 8;
    }

    public static boolean isFlowing(int x, int y, int z, K_4074_S state, BlockStateInterface bsi) {
        FluidState fluidState = state.P_4830_p();
        if (!(fluidState.n_1700_B() instanceof U_4243_e)) {
            return false;
        }
        if (fluidState.n_1700_B().G_564_y(fluidState) != 8) {
            return true;
        }
        return MovementHelper.possiblyFlowing(bsi.get0(x + 1, y, z)) || MovementHelper.possiblyFlowing(bsi.get0(x - 1, y, z)) || MovementHelper.possiblyFlowing(bsi.get0(x, y, z + 1)) || MovementHelper.possiblyFlowing(bsi.get0(x, y, z - 1));
    }

    public static boolean isBlockNormalCube(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        if (block instanceof E_872_n || block instanceof s_3401_U || block instanceof V_2454_J || block instanceof Y_3462_U) {
            return false;
        }
        try {
            return T_2915_h.n_1700_B(state.u_2550_I(null, null));
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static PlaceResult attemptToPlaceABlock(MovementState state, IBaritone baritone, c_1514_x placeAt, boolean preferDown, boolean wouldSneak) {
        IPlayerContext ctx = baritone.getPlayerContext();
        Optional<Rotation> direct = RotationUtils.reachable(ctx, placeAt, wouldSneak);
        boolean found = false;
        if (direct.isPresent()) {
            state.setTarget(new MovementState.MovementTarget(direct.get(), true));
            found = true;
        }
        for (int i = 0; i < 5; ++i) {
            c_1514_x against1 = placeAt.offset(Movement.HORIZONTALS_BUT_ALSO_DOWN_____SO_EVERY_DIRECTION_EXCEPT_UP[i]);
            if (!MovementHelper.canPlaceAgainst(ctx, against1)) continue;
            if (!((Baritone)baritone).getInventoryBehavior().selectThrowawayForLocation(false, placeAt.getX(), placeAt.getY(), placeAt.getZ())) {
                Helper.HELPER.logDebug("bb pls get me some blocks. dirt, netherrack, cobble");
                state.setStatus(MovementStatus.UNREACHABLE);
                return PlaceResult.NO_OPTION;
            }
            double faceX = ((double)(placeAt.getX() + against1.getX()) + 1.0) * 0.5;
            double faceY = ((double)(placeAt.getY() + against1.getY()) + 0.5) * 0.5;
            double faceZ = ((double)(placeAt.getZ() + against1.getZ()) + 1.0) * 0.5;
            Rotation place = RotationUtils.calcRotationFromVec3d(wouldSneak ? RayTraceUtils.inferSneakingEyePosition(ctx.player()) : ctx.playerHead(), new e_2866_D(faceX, faceY, faceZ), ctx.playerRotations());
            Rotation actual = baritone.getLookBehavior().getAimProcessor().peekRotation(place);
            HitResult res = RayTraceUtils.rayTraceTowards(ctx.player(), actual, ctx.playerController().getBlockReachDistance(), wouldSneak);
            if (res == null || res.R_4764_Y() != HitResult.n_1700_B.J_1907_R || !((BlockHitResult)res).n_1700_B().equals(against1) || !((BlockHitResult)res).n_1700_B().offset(((BlockHitResult)res).J_1907_R()).equals(placeAt)) continue;
            state.setTarget(new MovementState.MovementTarget(place, true));
            found = true;
            if (!preferDown) break;
        }
        if (ctx.getSelectedBlock().isPresent()) {
            c_1514_x selectedBlock = ctx.getSelectedBlock().get();
            b_257_Y side = ((BlockHitResult)ctx.objectMouseOver()).J_1907_R();
            if (selectedBlock.equals(placeAt) || MovementHelper.canPlaceAgainst(ctx, selectedBlock) && selectedBlock.offset(side).equals(placeAt)) {
                if (wouldSneak) {
                    state.setInput(Input.SNEAK, true);
                }
                ((Baritone)baritone).getInventoryBehavior().selectThrowawayForLocation(true, placeAt.getX(), placeAt.getY(), placeAt.getZ());
                return PlaceResult.READY_TO_PLACE;
            }
        }
        if (found) {
            if (wouldSneak) {
                state.setInput(Input.SNEAK, true);
            }
            ((Baritone)baritone).getInventoryBehavior().selectThrowawayForLocation(true, placeAt.getX(), placeAt.getY(), placeAt.getZ());
            return PlaceResult.ATTEMPTING;
        }
        return PlaceResult.NO_OPTION;
    }

    public static boolean isTransparent(T_2915_h b) {
        return b instanceof AirBlock || b == a_3742_W.H_2857_Y || b == a_3742_W.c_3005_b;
    }

    public static boolean isClimbable(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        return block == a_3742_W.L_3570_A || block == a_3742_W.U_4087_m || block instanceof V_2454_J;
    }

    public static boolean slowsMovement(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        return block == a_3742_W.y_1700_S || block == a_3742_W.B_1335_M || block == a_3742_W.s_4405_m;
    }

    public static boolean speedsMovement(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        return block == a_3742_W.O_1795_e || block == a_3742_W.ServerHelper || block == a_3742_W.G_4691_Q;
    }

    public static boolean reducesFallDamage(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        return block == a_3742_W.M_4609_z || block == a_3742_W.g_4841_c;
    }

    public static boolean isFullBlock(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        if (block instanceof y_3008_A || block instanceof z_2909_G || block instanceof x_2838_H) {
            return false;
        }
        return MovementHelper.isBlockNormalCube(state);
    }

    public static boolean increasesJumpHeight(K_4074_S state) {
        T_2915_h block = state.J_1907_R();
        return block == a_3742_W.g_4841_c;
    }

    public static enum PlaceResult {
        READY_TO_PLACE,
        ATTEMPTING,
        NO_OPTION;

    }
}



