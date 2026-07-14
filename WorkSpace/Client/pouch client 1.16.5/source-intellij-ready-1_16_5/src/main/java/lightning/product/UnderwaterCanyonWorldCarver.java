/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.serialization.Codec
 *  org.apache.commons.lang3.mutable.MutableBoolean
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;
import lightning.product.ProbabilityFeatureConfiguration;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.UnderwaterCaveWorldCarver;
import lightning.product.k_594_Q;
import lightning.product.CanyonWorldCarver;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class UnderwaterCanyonWorldCarver
extends CanyonWorldCarver {
    public UnderwaterCanyonWorldCarver(Codec<ProbabilityFeatureConfiguration> p_i231919_1_) {
        super(p_i231919_1_);
        this.s_956_w = ImmutableSet.of((Object)a_3742_W.J_1907_R, (Object)a_3742_W.R_4764_Y, (Object)a_3742_W.P_1922_E, (Object)a_3742_W.v_4262_N, (Object)a_3742_W.s_956_w, (Object)a_3742_W.u_2550_I, (Object[])new T_2915_h[]{a_3742_W.M_588_G, a_3742_W.t_148_a, a_3742_W.InventoryPlus, a_3742_W.I_2209_R, a_3742_W.h_3858_e, a_3742_W.l_4397_i, a_3742_W.t_4433_T, a_3742_W.AimAssist, a_3742_W.AntiBot, a_3742_W.AntiSurround, a_3742_W.s_4447_V, a_3742_W.AttackAura, a_3742_W.AutoAnchor, a_3742_W.AutoCrystal, a_3742_W.AutoExplosion, a_3742_W.AutoSwap, a_3742_W.AutoTotem, a_3742_W.AutoTrap, a_3742_W.s_4054_j, a_3742_W.h_4320_q, a_3742_W.BlockFly, a_3742_W.A_2714_y, a_3742_W.X_290_I, a_3742_W.A_4115_X, a_3742_W.t_4043_B, a_3742_W.c_3005_b, a_3742_W.H_2857_Y, a_3742_W.ClientBootstrap, a_3742_W.n_1700_B, a_3742_W.a_1344_X});
    }

    @Override
    protected boolean n_1700_B(ChunkAccess chunkIn, int chunkX, int chunkZ, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        return false;
    }

    @Override
    protected boolean n_1700_B(ChunkAccess p_230358_1_, Function<c_1514_x, k_594_Q> p_230358_2_, BitSet p_230358_3_, Random p_230358_4_, c_1514_x.n_1700_B p_230358_5_, c_1514_x.n_1700_B p_230358_6_, c_1514_x.n_1700_B p_230358_7_, int p_230358_8_, int p_230358_9_, int p_230358_10_, int p_230358_11_, int p_230358_12_, int p_230358_13_, int p_230358_14_, int p_230358_15_, MutableBoolean p_230358_16_) {
        return UnderwaterCaveWorldCarver.n_1700_B(this, p_230358_1_, p_230358_3_, p_230358_4_, p_230358_5_, p_230358_8_, p_230358_9_, p_230358_10_, p_230358_11_, p_230358_12_, p_230358_13_, p_230358_14_, p_230358_15_);
    }
}



