/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.T_2915_h;
import lightning.product.Z_927_M;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.BadlandsSurfaceBuilder;
import lightning.product.k_594_Q;

public class ErodedBadlandsSurfaceBuilder
extends BadlandsSurfaceBuilder {
    private static final K_4074_S v_4276_D = a_3742_W.I_2209_R.multiplayerClientSuggestionProvider();
    private static final K_4074_S d_2461_k = a_3742_W.h_3858_e.multiplayerClientSuggestionProvider();
    private static final K_4074_S G_624_v = a_3742_W.InventoryPlus.multiplayerClientSuggestionProvider();

    public ErodedBadlandsSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232125_1_) {
        super(p_i232125_1_);
    }

    @Override
    public void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        double d0 = 0.0;
        double d1 = Math.min(Math.abs(noise), this.R_4764_Y.n_1700_B((double)x * 0.25, (double)z * 0.25, false) * 15.0);
        if (d1 > 0.0) {
            double d2 = 0.001953125;
            d0 = d1 * d1 * 2.5;
            double d3 = Math.abs(this.G_564_y.n_1700_B((double)x * 0.001953125, (double)z * 0.001953125, false));
            double d4 = Math.ceil(d3 * 50.0) + 14.0;
            if (d0 > d4) {
                d0 = d4;
            }
            d0 += 64.0;
        }
        int i1 = x & 0xF;
        int i = z & 0xF;
        K_4074_S blockstate3 = v_4276_D;
        Z_927_M isurfacebuilderconfig = biomeIn.P_1922_E().P_1922_E();
        K_4074_S blockstate4 = isurfacebuilderconfig.J_1907_R();
        K_4074_S blockstate = isurfacebuilderconfig.n_1700_B();
        K_4074_S blockstate1 = blockstate4;
        int j = (int)(noise / 3.0 + 3.0 + random.nextDouble() * 0.25);
        boolean flag = Math.cos(noise / 3.0 * Math.PI) > 0.0;
        int k = -1;
        boolean flag1 = false;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int l = Math.max(startHeight, (int)d0 + 1); l >= 0; --l) {
            K_4074_S blockstate2;
            blockpos$mutable.n_1700_B(i1, l, i);
            if (chunkIn.getBlockState(blockpos$mutable).v_4262_N() && l < (int)d0) {
                chunkIn.setBlockState(blockpos$mutable, defaultBlock, false);
            }
            if ((blockstate2 = chunkIn.getBlockState(blockpos$mutable)).v_4262_N()) {
                k = -1;
                continue;
            }
            if (!blockstate2.n_1700_B(defaultBlock.J_1907_R())) continue;
            if (k == -1) {
                flag1 = false;
                if (j <= 0) {
                    blockstate3 = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
                    blockstate1 = defaultBlock;
                } else if (l >= seaLevel - 4 && l <= seaLevel + 1) {
                    blockstate3 = v_4276_D;
                    blockstate1 = blockstate4;
                }
                if (l < seaLevel && (blockstate3 == null || blockstate3.v_4262_N())) {
                    blockstate3 = defaultFluid;
                }
                k = j + Math.max(0, l - seaLevel);
                if (l >= seaLevel - 1) {
                    if (l <= seaLevel + 3 + j) {
                        chunkIn.setBlockState(blockpos$mutable, blockstate, false);
                        flag1 = true;
                        continue;
                    }
                    K_4074_S blockstate5 = l >= 64 && l <= 127 ? (flag ? G_624_v : this.n_1700_B(x, l, z)) : d_2461_k;
                    chunkIn.setBlockState(blockpos$mutable, blockstate5, false);
                    continue;
                }
                chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                T_2915_h block = blockstate1.J_1907_R();
                if (block != a_3742_W.I_2209_R && block != a_3742_W.h_3858_e && block != a_3742_W.l_4397_i && block != a_3742_W.t_4433_T && block != a_3742_W.AimAssist && block != a_3742_W.AntiBot && block != a_3742_W.AntiSurround && block != a_3742_W.s_4447_V && block != a_3742_W.AttackAura && block != a_3742_W.AutoAnchor && block != a_3742_W.AutoCrystal && block != a_3742_W.AutoExplosion && block != a_3742_W.AutoSwap && block != a_3742_W.AutoTotem && block != a_3742_W.AutoTrap && block != a_3742_W.s_4054_j) continue;
                chunkIn.setBlockState(blockpos$mutable, d_2461_k, false);
                continue;
            }
            if (k <= 0) continue;
            --k;
            if (flag1) {
                chunkIn.setBlockState(blockpos$mutable, d_2461_k, false);
                continue;
            }
            chunkIn.setBlockState(blockpos$mutable, this.n_1700_B(x, l, z), false);
        }
    }
}



