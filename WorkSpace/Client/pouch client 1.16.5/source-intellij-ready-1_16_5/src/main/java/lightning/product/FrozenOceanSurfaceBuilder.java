/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import lightning.product.SurfaceBuilder;
import lightning.product.PerlinSimplexNoise;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.Z_927_M;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.WorldgenRandom;
import lightning.product.k_594_Q;
import lightning.product.Material;

public class FrozenOceanSurfaceBuilder
extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    protected static final K_4074_S n_1700_B = a_3742_W.ServerHelper.multiplayerClientSuggestionProvider();
    protected static final K_4074_S J_1907_R = a_3742_W.l_697_B.multiplayerClientSuggestionProvider();
    private static final K_4074_S R_4764_Y = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    private static final K_4074_S G_564_y = a_3742_W.t_4043_B.multiplayerClientSuggestionProvider();
    private static final K_4074_S P_1922_E = a_3742_W.O_1795_e.multiplayerClientSuggestionProvider();
    private PerlinSimplexNoise v_4276_D;
    private PerlinSimplexNoise d_2461_k;
    private long G_624_v;

    public FrozenOceanSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232126_1_) {
        super(p_i232126_1_);
    }

    @Override
    public void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        double d0 = 0.0;
        double d1 = 0.0;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        float f = biomeIn.n_1700_B(blockpos$mutable.n_1700_B(x, 63, z));
        double d2 = Math.min(Math.abs(noise), this.v_4276_D.n_1700_B((double)x * 0.1, (double)z * 0.1, false) * 15.0);
        if (d2 > 1.8) {
            double d3 = 0.09765625;
            d0 = d2 * d2 * 1.2;
            double d4 = Math.abs(this.d_2461_k.n_1700_B((double)x * 0.09765625, (double)z * 0.09765625, false));
            double d5 = Math.ceil(d4 * 40.0) + 14.0;
            if (d0 > d5) {
                d0 = d5;
            }
            if (f > 0.1f) {
                d0 -= 2.0;
            }
            if (d0 > 2.0) {
                d1 = (double)seaLevel - d0 - 7.0;
                d0 += (double)seaLevel;
            } else {
                d0 = 0.0;
            }
        }
        int l1 = x & 0xF;
        int i = z & 0xF;
        Z_927_M isurfacebuilderconfig = biomeIn.P_1922_E().P_1922_E();
        K_4074_S blockstate = isurfacebuilderconfig.J_1907_R();
        K_4074_S blockstate4 = isurfacebuilderconfig.n_1700_B();
        K_4074_S blockstate1 = blockstate;
        K_4074_S blockstate2 = blockstate4;
        int j = (int)(noise / 3.0 + 3.0 + random.nextDouble() * 0.25);
        int k = -1;
        int l = 0;
        int i1 = 2 + random.nextInt(4);
        int j1 = seaLevel + 18 + random.nextInt(10);
        for (int k1 = Math.max(startHeight, (int)d0 + 1); k1 >= 0; --k1) {
            blockpos$mutable.n_1700_B(l1, k1, i);
            if (chunkIn.getBlockState(blockpos$mutable).v_4262_N() && k1 < (int)d0 && random.nextDouble() > 0.01) {
                chunkIn.setBlockState(blockpos$mutable, n_1700_B, false);
            } else if (chunkIn.getBlockState(blockpos$mutable).R_4764_Y() == Material.s_956_w && k1 > (int)d1 && k1 < seaLevel && d1 != 0.0 && random.nextDouble() > 0.15) {
                chunkIn.setBlockState(blockpos$mutable, n_1700_B, false);
            }
            K_4074_S blockstate3 = chunkIn.getBlockState(blockpos$mutable);
            if (blockstate3.v_4262_N()) {
                k = -1;
                continue;
            }
            if (!blockstate3.n_1700_B(defaultBlock.J_1907_R())) {
                if (!blockstate3.n_1700_B(a_3742_W.ServerHelper) || l > i1 || k1 <= j1) continue;
                chunkIn.setBlockState(blockpos$mutable, J_1907_R, false);
                ++l;
                continue;
            }
            if (k == -1) {
                if (j <= 0) {
                    blockstate2 = R_4764_Y;
                    blockstate1 = defaultBlock;
                } else if (k1 >= seaLevel - 4 && k1 <= seaLevel + 1) {
                    blockstate2 = blockstate4;
                    blockstate1 = blockstate;
                }
                if (k1 < seaLevel && (blockstate2 == null || blockstate2.v_4262_N())) {
                    blockstate2 = biomeIn.n_1700_B(blockpos$mutable.n_1700_B(x, k1, z)) < 0.15f ? P_1922_E : defaultFluid;
                }
                k = j;
                if (k1 >= seaLevel - 1) {
                    chunkIn.setBlockState(blockpos$mutable, blockstate2, false);
                    continue;
                }
                if (k1 < seaLevel - 7 - j) {
                    blockstate2 = R_4764_Y;
                    blockstate1 = defaultBlock;
                    chunkIn.setBlockState(blockpos$mutable, G_564_y, false);
                    continue;
                }
                chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                continue;
            }
            if (k <= 0) continue;
            chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
            if (--k != 0 || !blockstate1.n_1700_B(a_3742_W.A_4115_X) || j <= 1) continue;
            k = random.nextInt(4) + Math.max(0, k1 - 63);
            blockstate1 = blockstate1.n_1700_B(a_3742_W.Y_1740_V) ? a_3742_W.BlockFly.multiplayerClientSuggestionProvider() : a_3742_W.h_4320_q.multiplayerClientSuggestionProvider();
        }
    }

    @Override
    public void n_1700_B(long seed) {
        if (this.G_624_v != seed || this.v_4276_D == null || this.d_2461_k == null) {
            WorldgenRandom sharedseedrandom = new WorldgenRandom(seed);
            this.v_4276_D = new PerlinSimplexNoise(sharedseedrandom, IntStream.rangeClosed(-3, 0));
            this.d_2461_k = new PerlinSimplexNoise(sharedseedrandom, (List<Integer>)ImmutableList.of((Object)0));
        }
        this.G_624_v = seed;
    }
}



