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
import lightning.product.SurfaceBuilder;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.WorldgenRandom;
import lightning.product.PerlinNoise;
import lightning.product.k_594_Q;

public class NetherForestSurfaceBuilder
extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    private static final K_4074_S J_1907_R = a_3742_W.a_1344_X.multiplayerClientSuggestionProvider();
    protected long n_1700_B;
    private PerlinNoise R_4764_Y;

    public NetherForestSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232131_1_) {
        super(p_i232131_1_);
    }

    @Override
    public void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        int i = seaLevel;
        int j = x & 0xF;
        int k = z & 0xF;
        double d0 = this.R_4764_Y.n_1700_B((double)x * 0.1, seaLevel, (double)z * 0.1);
        boolean flag = d0 > 0.15 + random.nextDouble() * 0.35;
        double d1 = this.R_4764_Y.n_1700_B((double)x * 0.1, 109.0, (double)z * 0.1);
        boolean flag1 = d1 > 0.25 + random.nextDouble() * 0.9;
        int l = (int)(noise / 3.0 + 3.0 + random.nextDouble() * 0.25);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        int i1 = -1;
        K_4074_S blockstate = config.J_1907_R();
        for (int j1 = 127; j1 >= 0; --j1) {
            blockpos$mutable.n_1700_B(j, j1, k);
            K_4074_S blockstate1 = config.n_1700_B();
            K_4074_S blockstate2 = chunkIn.getBlockState(blockpos$mutable);
            if (blockstate2.v_4262_N()) {
                i1 = -1;
                continue;
            }
            if (!blockstate2.n_1700_B(defaultBlock.J_1907_R())) continue;
            if (i1 == -1) {
                boolean flag2 = false;
                if (l <= 0) {
                    flag2 = true;
                    blockstate = config.J_1907_R();
                }
                if (flag) {
                    blockstate1 = config.J_1907_R();
                } else if (flag1) {
                    blockstate1 = config.R_4764_Y();
                }
                if (j1 < i && flag2) {
                    blockstate1 = defaultFluid;
                }
                i1 = l;
                if (j1 >= i - 1) {
                    chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                    continue;
                }
                chunkIn.setBlockState(blockpos$mutable, blockstate, false);
                continue;
            }
            if (i1 <= 0) continue;
            --i1;
            chunkIn.setBlockState(blockpos$mutable, blockstate, false);
        }
    }

    @Override
    public void n_1700_B(long seed) {
        if (this.n_1700_B != seed || this.R_4764_Y == null) {
            this.R_4764_Y = new PerlinNoise(new WorldgenRandom(seed), (List<Integer>)ImmutableList.of((Object)0));
        }
        this.n_1700_B = seed;
    }
}


