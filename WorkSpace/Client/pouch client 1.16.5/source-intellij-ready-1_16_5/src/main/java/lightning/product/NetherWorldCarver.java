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
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.k_594_Q;
import lightning.product.CaveWorldCarver;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class NetherWorldCarver
extends CaveWorldCarver {
    public NetherWorldCarver(Codec<ProbabilityFeatureConfiguration> p_i231918_1_) {
        super(p_i231918_1_, 128);
        this.s_956_w = ImmutableSet.of((Object)a_3742_W.J_1907_R, (Object)a_3742_W.R_4764_Y, (Object)a_3742_W.P_1922_E, (Object)a_3742_W.v_4262_N, (Object)a_3742_W.s_956_w, (Object)a_3742_W.u_2550_I, (Object[])new T_2915_h[]{a_3742_W.M_588_G, a_3742_W.t_148_a, a_3742_W.i_3196_G, a_3742_W.C_415_h, a_3742_W.v_165_F, a_3742_W.ServerFunctionManager, a_3742_W.ServerAdvancementManager, a_3742_W.LockSlot, a_3742_W.J_1008_m, a_3742_W.s_4990_V, a_3742_W.m_1964_F});
        this.u_2550_I = ImmutableSet.of((Object)Fluids.P_1922_E, (Object)Fluids.R_4764_Y);
    }

    @Override
    protected int n_1700_B() {
        return 10;
    }

    @Override
    protected float n_1700_B(Random p_230359_1_) {
        return (p_230359_1_.nextFloat() * 2.0f + p_230359_1_.nextFloat()) * 2.0f;
    }

    @Override
    protected double J_1907_R() {
        return 5.0;
    }

    @Override
    protected int J_1907_R(Random p_230361_1_) {
        return p_230361_1_.nextInt(this.M_588_G);
    }

    @Override
    protected boolean n_1700_B(ChunkAccess p_230358_1_, Function<c_1514_x, k_594_Q> p_230358_2_, BitSet p_230358_3_, Random p_230358_4_, c_1514_x.n_1700_B p_230358_5_, c_1514_x.n_1700_B p_230358_6_, c_1514_x.n_1700_B p_230358_7_, int p_230358_8_, int p_230358_9_, int p_230358_10_, int p_230358_11_, int p_230358_12_, int p_230358_13_, int p_230358_14_, int p_230358_15_, MutableBoolean p_230358_16_) {
        int i = p_230358_13_ | p_230358_15_ << 4 | p_230358_14_ << 8;
        if (p_230358_3_.get(i)) {
            return false;
        }
        p_230358_3_.set(i);
        p_230358_5_.n_1700_B(p_230358_11_, p_230358_14_, p_230358_12_);
        if (this.n_1700_B(p_230358_1_.getBlockState(p_230358_5_))) {
            K_4074_S blockstate = p_230358_14_ <= 31 ? t_148_a.v_4262_N() : v_4262_N;
            p_230358_1_.setBlockState(p_230358_5_, blockstate, false);
            return true;
        }
        return false;
    }
}



