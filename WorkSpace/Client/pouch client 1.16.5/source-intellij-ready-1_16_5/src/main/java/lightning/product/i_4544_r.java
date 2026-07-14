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
import java.util.Set;
import java.util.function.Function;
import lightning.product.FluidTags;
import lightning.product.ProbabilityFeatureConfiguration;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.K_4573_Z;
import lightning.product.NetherWorldCarver;
import lightning.product.ConfiguredWorldCarver;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.ChunkAccess;
import lightning.product.UnderwaterCaveWorldCarver;
import lightning.product.k_594_Q;
import lightning.product.CanyonWorldCarver;
import lightning.product.UnderwaterCanyonWorldCarver;
import lightning.product.Fluid;
import lightning.product.u_530_F;
import lightning.product.CaveWorldCarver;
import org.apache.commons.lang3.mutable.MutableBoolean;

public abstract class i_4544_r<C extends K_4573_Z> {
    public static final i_4544_r<ProbabilityFeatureConfiguration> n_1700_B = i_4544_r.n_1700_B("cave", new CaveWorldCarver(ProbabilityFeatureConfiguration.n_1700_B, 256));
    public static final i_4544_r<ProbabilityFeatureConfiguration> J_1907_R = i_4544_r.n_1700_B("nether_cave", new NetherWorldCarver(ProbabilityFeatureConfiguration.n_1700_B));
    public static final i_4544_r<ProbabilityFeatureConfiguration> R_4764_Y = i_4544_r.n_1700_B("canyon", new CanyonWorldCarver(ProbabilityFeatureConfiguration.n_1700_B));
    public static final i_4544_r<ProbabilityFeatureConfiguration> G_564_y = i_4544_r.n_1700_B("underwater_canyon", new UnderwaterCanyonWorldCarver(ProbabilityFeatureConfiguration.n_1700_B));
    public static final i_4544_r<ProbabilityFeatureConfiguration> P_1922_E = i_4544_r.n_1700_B("underwater_cave", new UnderwaterCaveWorldCarver(ProbabilityFeatureConfiguration.n_1700_B));
    protected static final K_4074_S u_1723_Y = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    protected static final K_4074_S v_4262_N = a_3742_W.a_1344_X.multiplayerClientSuggestionProvider();
    protected static final FluidState w_1484_f = Fluids.R_4764_Y.w_1484_f();
    protected static final FluidState t_148_a = Fluids.P_1922_E.w_1484_f();
    protected Set<T_2915_h> s_956_w = ImmutableSet.of((Object)a_3742_W.J_1907_R, (Object)a_3742_W.R_4764_Y, (Object)a_3742_W.P_1922_E, (Object)a_3742_W.v_4262_N, (Object)a_3742_W.s_956_w, (Object)a_3742_W.u_2550_I, (Object[])new T_2915_h[]{a_3742_W.M_588_G, a_3742_W.t_148_a, a_3742_W.InventoryPlus, a_3742_W.I_2209_R, a_3742_W.h_3858_e, a_3742_W.l_4397_i, a_3742_W.t_4433_T, a_3742_W.AimAssist, a_3742_W.AntiBot, a_3742_W.AntiSurround, a_3742_W.s_4447_V, a_3742_W.AttackAura, a_3742_W.AutoAnchor, a_3742_W.AutoCrystal, a_3742_W.AutoExplosion, a_3742_W.AutoSwap, a_3742_W.AutoTotem, a_3742_W.AutoTrap, a_3742_W.s_4054_j, a_3742_W.h_4320_q, a_3742_W.BlockFly, a_3742_W.A_2714_y, a_3742_W.X_290_I, a_3742_W.ServerHelper});
    protected Set<Fluid> u_2550_I = ImmutableSet.of((Object)Fluids.R_4764_Y);
    private final Codec<ConfiguredWorldCarver<C>> P_4830_p;
    protected final int M_588_G;

    private static <C extends K_4573_Z, F extends i_4544_r<C>> F n_1700_B(String key, F carver) {
        return (F)V_3137_a.n_1700_B(V_3137_a.dtoRealmsServerAddress, key, carver);
    }

    public i_4544_r(Codec<C> p_i231921_1_, int p_i231921_2_) {
        this.M_588_G = p_i231921_2_;
        this.P_4830_p = p_i231921_1_.fieldOf("config").xmap(this::n_1700_B, ConfiguredWorldCarver::n_1700_B).codec();
    }

    public ConfiguredWorldCarver<C> n_1700_B(C p_242761_1_) {
        return new ConfiguredWorldCarver<C>(this, p_242761_1_);
    }

    public Codec<ConfiguredWorldCarver<C>> R_4764_Y() {
        return this.P_4830_p;
    }

    public int G_564_y() {
        return 4;
    }

    protected boolean n_1700_B(ChunkAccess chunk, Function<c_1514_x, k_594_Q> biomePos, long seed, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, double p_227208_14_, double p_227208_16_, BitSet carvingMask) {
        Random random = new Random(seed + (long)chunkX + (long)chunkZ);
        double d0 = chunkX * 16 + 8;
        double d1 = chunkZ * 16 + 8;
        if (!(randOffsetXCoord < d0 - 16.0 - p_227208_14_ * 2.0 || randOffsetZCoord < d1 - 16.0 - p_227208_14_ * 2.0 || randOffsetXCoord > d0 + 16.0 + p_227208_14_ * 2.0 || randOffsetZCoord > d1 + 16.0 + p_227208_14_ * 2.0)) {
            int j1;
            int i1;
            int l;
            int k;
            int j;
            int i = Math.max(u_530_F.R_4764_Y(randOffsetXCoord - p_227208_14_) - chunkX * 16 - 1, 0);
            if (this.n_1700_B(chunk, chunkX, chunkZ, i, j = Math.min(u_530_F.R_4764_Y(randOffsetXCoord + p_227208_14_) - chunkX * 16 + 1, 16), k = Math.max(u_530_F.R_4764_Y(startY - p_227208_16_) - 1, 1), l = Math.min(u_530_F.R_4764_Y(startY + p_227208_16_) + 1, this.M_588_G - 8), i1 = Math.max(u_530_F.R_4764_Y(randOffsetZCoord - p_227208_14_) - chunkZ * 16 - 1, 0), j1 = Math.min(u_530_F.R_4764_Y(randOffsetZCoord + p_227208_14_) - chunkZ * 16 + 1, 16))) {
                return false;
            }
            boolean flag = false;
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            c_1514_x.n_1700_B blockpos$mutable1 = new c_1514_x.n_1700_B();
            c_1514_x.n_1700_B blockpos$mutable2 = new c_1514_x.n_1700_B();
            for (int k1 = i; k1 < j; ++k1) {
                int l1 = k1 + chunkX * 16;
                double d2 = ((double)l1 + 0.5 - randOffsetXCoord) / p_227208_14_;
                for (int i2 = i1; i2 < j1; ++i2) {
                    int j2 = i2 + chunkZ * 16;
                    double d3 = ((double)j2 + 0.5 - randOffsetZCoord) / p_227208_14_;
                    if (d2 * d2 + d3 * d3 >= 1.0) continue;
                    MutableBoolean mutableboolean = new MutableBoolean(false);
                    for (int k2 = l; k2 > k; --k2) {
                        double d4 = ((double)k2 - 0.5 - startY) / p_227208_16_;
                        if (this.n_1700_B(d2, d4, d3, k2)) continue;
                        flag |= this.n_1700_B(chunk, biomePos, carvingMask, random, blockpos$mutable, blockpos$mutable1, blockpos$mutable2, seaLevel, chunkX, chunkZ, l1, j2, k1, k2, i2, mutableboolean);
                    }
                }
            }
            return flag;
        }
        return false;
    }

    protected boolean n_1700_B(ChunkAccess p_230358_1_, Function<c_1514_x, k_594_Q> p_230358_2_, BitSet p_230358_3_, Random p_230358_4_, c_1514_x.n_1700_B p_230358_5_, c_1514_x.n_1700_B p_230358_6_, c_1514_x.n_1700_B p_230358_7_, int p_230358_8_, int p_230358_9_, int p_230358_10_, int p_230358_11_, int p_230358_12_, int p_230358_13_, int p_230358_14_, int p_230358_15_, MutableBoolean p_230358_16_) {
        int i = p_230358_13_ | p_230358_15_ << 4 | p_230358_14_ << 8;
        if (p_230358_3_.get(i)) {
            return false;
        }
        p_230358_3_.set(i);
        p_230358_5_.n_1700_B(p_230358_11_, p_230358_14_, p_230358_12_);
        K_4074_S blockstate = p_230358_1_.getBlockState(p_230358_5_);
        K_4074_S blockstate1 = p_230358_1_.getBlockState(p_230358_6_.n_1700_B(p_230358_5_, b_257_Y.J_1907_R));
        if (blockstate.n_1700_B(a_3742_W.t_148_a) || blockstate.n_1700_B(a_3742_W.A_2714_y)) {
            p_230358_16_.setTrue();
        }
        if (!this.n_1700_B(blockstate, blockstate1)) {
            return false;
        }
        if (p_230358_14_ < 11) {
            p_230358_1_.setBlockState(p_230358_5_, t_148_a.v_4262_N(), false);
        } else {
            p_230358_1_.setBlockState(p_230358_5_, v_4262_N, false);
            if (p_230358_16_.isTrue()) {
                p_230358_7_.n_1700_B(p_230358_5_, b_257_Y.n_1700_B);
                if (p_230358_1_.getBlockState(p_230358_7_).n_1700_B(a_3742_W.s_956_w)) {
                    p_230358_1_.setBlockState(p_230358_7_, p_230358_2_.apply(p_230358_5_).P_1922_E().P_1922_E().n_1700_B(), false);
                }
            }
        }
        return true;
    }

    public abstract boolean n_1700_B(ChunkAccess var1, Function<c_1514_x, k_594_Q> var2, Random var3, int var4, int var5, int var6, int var7, int var8, BitSet var9, C var10);

    public abstract boolean n_1700_B(Random var1, int var2, int var3, C var4);

    protected boolean n_1700_B(K_4074_S p_222706_1_) {
        return this.s_956_w.contains(p_222706_1_.J_1907_R());
    }

    protected boolean n_1700_B(K_4074_S state, K_4074_S aboveState) {
        return this.n_1700_B(state) || (state.n_1700_B(a_3742_W.A_4115_X) || state.n_1700_B(a_3742_W.t_4043_B)) && !aboveState.P_4830_p().n_1700_B(FluidTags.J_1907_R);
    }

    protected boolean n_1700_B(ChunkAccess chunkIn, int chunkX, int chunkZ, int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = minX; i < maxX; ++i) {
            for (int j = minZ; j < maxZ; ++j) {
                for (int k = minY - 1; k <= maxY + 1; ++k) {
                    if (this.u_2550_I.contains(chunkIn.getFluidState(blockpos$mutable.n_1700_B(i + chunkX * 16, k, j + chunkZ * 16)).n_1700_B())) {
                        return true;
                    }
                    if (k == maxY + 1 || this.n_1700_B(minX, maxX, minZ, maxZ, i, j)) continue;
                    k = maxY;
                }
            }
        }
        return false;
    }

    private boolean n_1700_B(int minX, int maxX, int minZ, int maxZ, int x, int z) {
        return x == minX || x == maxX - 1 || z == minZ || z == maxZ - 1;
    }

    protected boolean n_1700_B(int p_222702_1_, int p_222702_2_, double p_222702_3_, double p_222702_5_, int p_222702_7_, int p_222702_8_, float p_222702_9_) {
        double d0 = p_222702_1_ * 16 + 8;
        double d2 = p_222702_3_ - d0;
        double d1 = p_222702_2_ * 16 + 8;
        double d3 = p_222702_5_ - d1;
        double d4 = p_222702_8_ - p_222702_7_;
        double d5 = p_222702_9_ + 2.0f + 16.0f;
        return d2 * d2 + d3 * d3 - d4 * d4 <= d5 * d5;
    }

    protected abstract boolean n_1700_B(double var1, double var3, double var5, int var7);
}



