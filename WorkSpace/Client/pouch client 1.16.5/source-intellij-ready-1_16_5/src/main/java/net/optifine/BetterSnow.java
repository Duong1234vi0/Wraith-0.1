/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import lightning.product.FenceBlock;
import lightning.product.C_1985_D;
import lightning.product.E_3601_d;
import lightning.product.F_2203_T;
import lightning.product.BlockGetter;
import lightning.product.RedstoneTorchBlock;
import lightning.product.BushBlock;
import lightning.product.K_3256_W;
import lightning.product.K_4074_S;
import lightning.product.L_2467_I;
import lightning.product.Q_4220_D;
import lightning.product.S_3826_o;
import lightning.product.T_2915_h;
import lightning.product.V_1045_N;
import lightning.product.W_3538_l;
import lightning.product.SaplingBlock;
import lightning.product.BlockAndTintGetter;
import lightning.product.FenceGateBlock;
import lightning.product.a_3742_W;
import lightning.product.FlowerBlock;
import lightning.product.c_1514_x;
import lightning.product.c_1788_D;
import lightning.product.MushroomBlock;
import lightning.product.h_935_G;
import lightning.product.m_2244_y;
import lightning.product.n_1769_f;
import lightning.product.DoublePlantBlock;
import lightning.product.v_4620_e;
import lightning.product.SugarCaneBlock;
import lightning.product.y_3008_A;
import lightning.product.z_2909_G;
import net.optifine.Config;

public class BetterSnow {
    private static S_3826_o modelSnowLayer = null;

    public static void update() {
        modelSnowLayer = Config.getMinecraft().z_1333_t().J_1907_R().J_1907_R(a_3742_W.X_290_I.multiplayerClientSuggestionProvider());
    }

    public static S_3826_o getModelSnowLayer() {
        return modelSnowLayer;
    }

    public static K_4074_S getStateSnowLayer() {
        return a_3742_W.X_290_I.multiplayerClientSuggestionProvider();
    }

    public static boolean shouldRender(BlockAndTintGetter lightReader, K_4074_S blockState, c_1514_x blockPos) {
        if (!(lightReader instanceof BlockGetter)) {
            return false;
        }
        return !BetterSnow.checkBlock(lightReader, blockState, blockPos) ? false : BetterSnow.hasSnowNeighbours(lightReader, blockPos);
    }

    private static boolean hasSnowNeighbours(BlockGetter blockAccess, c_1514_x pos) {
        T_2915_h block = a_3742_W.X_290_I;
        if (blockAccess.getBlockState(pos.north()).J_1907_R() == block || blockAccess.getBlockState(pos.south()).J_1907_R() == block || blockAccess.getBlockState(pos.west()).J_1907_R() == block || blockAccess.getBlockState(pos.east()).J_1907_R() == block) {
            K_4074_S blockstate = blockAccess.getBlockState(pos.down());
            if (blockstate.t_148_a(blockAccess, pos)) {
                return true;
            }
            T_2915_h block1 = blockstate.J_1907_R();
            if (block1 instanceof z_2909_G) {
                return blockstate.R_4764_Y(z_2909_G.h_1847_R) == m_2244_y.n_1700_B;
            }
            if (block1 instanceof y_3008_A) {
                return blockstate.R_4764_Y(y_3008_A.P_4830_p) == n_1769_f.n_1700_B;
            }
        }
        return false;
    }

    private static boolean checkBlock(BlockGetter blockAccess, K_4074_S blockState, c_1514_x blockPos) {
        if (blockState.t_148_a(blockAccess, blockPos)) {
            return false;
        }
        T_2915_h block = blockState.J_1907_R();
        if (block == a_3742_W.l_697_B) {
            return false;
        }
        if (!(block instanceof BushBlock && (block instanceof DoublePlantBlock || block instanceof FlowerBlock || block instanceof MushroomBlock || block instanceof SaplingBlock || block instanceof E_3601_d))) {
            if (!(block instanceof FenceBlock || block instanceof FenceGateBlock || block instanceof h_935_G || block instanceof v_4620_e || block instanceof SugarCaneBlock || block instanceof W_3538_l)) {
                if (block instanceof RedstoneTorchBlock) {
                    return true;
                }
                if (block instanceof z_2909_G) {
                    return blockState.R_4764_Y(z_2909_G.h_1847_R) == m_2244_y.n_1700_B;
                }
                if (block instanceof y_3008_A) {
                    return blockState.R_4764_Y(y_3008_A.P_4830_p) == n_1769_f.n_1700_B;
                }
                if (block instanceof V_1045_N) {
                    return blockState.R_4764_Y(V_1045_N.RealmsServerPing) != F_2203_T.n_1700_B;
                }
                if (block instanceof c_1788_D) {
                    return true;
                }
                if (block instanceof C_1985_D) {
                    return true;
                }
                if (block instanceof K_3256_W) {
                    return true;
                }
                if (block instanceof L_2467_I) {
                    return true;
                }
                return block instanceof Q_4220_D;
            }
            return true;
        }
        return true;
    }
}


