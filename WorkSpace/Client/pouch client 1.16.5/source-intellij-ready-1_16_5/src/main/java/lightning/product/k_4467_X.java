/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.D_3746_J;
import lightning.product.FoliageColor;
import lightning.product.BiomeColors;
import lightning.product.K_4074_S;
import lightning.product.BlockColor;
import lightning.product.GrassColor;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.BlockAndTintGetter;
import lightning.product.Z_4734_t;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_3212_H;
import lightning.product.DoublePlantBlock;
import lightning.product.MaterialColor;
import lightning.product.v_3760_Q;
import lightning.product.w_424_u;

public class k_4467_X {
    private final w_424_u<BlockColor> n_1700_B = new w_424_u(32);
    private final Map<T_2915_h, Set<v_3760_Q<?>>> J_1907_R = Maps.newHashMap();

    public static k_4467_X n_1700_B() {
        k_4467_X blockcolors = new k_4467_X();
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> reader != null && pos != null ? BiomeColors.n_1700_B(reader, state.R_4764_Y(DoublePlantBlock.P_4830_p) == g_3212_H.n_1700_B ? pos.down() : pos) : -1, a_3742_W.PotionTracker, a_3742_W.Party);
        blockcolors.n_1700_B(DoublePlantBlock.P_4830_p, a_3742_W.PotionTracker, a_3742_W.Party);
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> reader != null && pos != null ? BiomeColors.n_1700_B(reader, pos) : GrassColor.n_1700_B(0.5, 1.0), a_3742_W.t_148_a, a_3742_W.RetryCallException, a_3742_W.u_744_e, a_3742_W.I_1790_n);
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> FoliageColor.n_1700_B(), a_3742_W.i_1637_u);
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> FoliageColor.J_1907_R(), a_3742_W.Ping);
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> reader != null && pos != null ? BiomeColors.J_1907_R(reader, pos) : FoliageColor.R_4764_Y(), a_3742_W.A_1038_p, a_3742_W.p_178_J, a_3742_W.RealmsClientConfig, a_3742_W.f_4016_n, a_3742_W.U_4087_m);
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> reader != null && pos != null ? BiomeColors.R_4764_Y(reader, pos) : -1, a_3742_W.c_3005_b, a_3742_W.S_4325_V, a_3742_W.m_1621_v);
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> Z_4734_t.J_1907_R(state.R_4764_Y(Z_4734_t.t_1786_h)), a_3742_W.P_5000_x);
        blockcolors.n_1700_B(Z_4734_t.t_1786_h, a_3742_W.P_5000_x);
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> reader != null && pos != null ? BiomeColors.n_1700_B(reader, pos) : -1, a_3742_W.l_3609_d);
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> 14731036, a_3742_W.J_2061_p, a_3742_W.i_789_Q);
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> {
            int i = state.R_4764_Y(D_3746_J.P_4830_p);
            int j = i * 32;
            int k = 255 - i * 8;
            int l = i * 4;
            return j << 16 | k << 8 | l;
        }, a_3742_W.n_4539_g, a_3742_W.L_1733_J);
        blockcolors.n_1700_B(D_3746_J.P_4830_p, a_3742_W.n_4539_g, a_3742_W.L_1733_J);
        blockcolors.n_1700_B((K_4074_S state, BlockAndTintGetter reader, c_1514_x pos, int color) -> reader != null && pos != null ? 2129968 : 7455580, a_3742_W.S_4035_N);
        return blockcolors;
    }

    public int n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x blockPosIn) {
        BlockColor iblockcolor = this.n_1700_B.n_1700_B(V_3137_a.q_4610_l.n_1700_B(state.J_1907_R()));
        if (iblockcolor != null) {
            return iblockcolor.getColor(state, null, null, 0);
        }
        MaterialColor materialcolor = state.G_564_y(worldIn, blockPosIn);
        return materialcolor != null ? materialcolor.i_1637_u : -1;
    }

    public int n_1700_B(K_4074_S blockStateIn, @Nullable BlockAndTintGetter lightReaderIn, @Nullable c_1514_x blockPosIn, int tintIndexIn) {
        BlockColor iblockcolor = this.n_1700_B.n_1700_B(V_3137_a.q_4610_l.n_1700_B(blockStateIn.J_1907_R()));
        return iblockcolor == null ? -1 : iblockcolor.getColor(blockStateIn, lightReaderIn, blockPosIn, tintIndexIn);
    }

    public void n_1700_B(BlockColor blockColor, T_2915_h ... blocksIn) {
        for (T_2915_h block : blocksIn) {
            this.n_1700_B.n_1700_B(blockColor, V_3137_a.q_4610_l.n_1700_B(block));
        }
    }

    private void n_1700_B(Set<v_3760_Q<?>> propertiesIn, T_2915_h ... blocksIn) {
        for (T_2915_h block : blocksIn) {
            this.J_1907_R.put(block, propertiesIn);
        }
    }

    private void n_1700_B(v_3760_Q<?> propertyIn, T_2915_h ... blocksIn) {
        this.n_1700_B((Set<v_3760_Q<?>>)ImmutableSet.of(propertyIn), blocksIn);
    }

    public Set<v_3760_Q<?>> n_1700_B(T_2915_h blockIn) {
        return (Set)this.J_1907_R.getOrDefault(blockIn, (Set<v_3760_Q<?>>)ImmutableSet.of());
    }
}



