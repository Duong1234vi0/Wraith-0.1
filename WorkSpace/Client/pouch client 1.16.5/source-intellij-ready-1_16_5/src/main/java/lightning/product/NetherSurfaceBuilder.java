/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.IntStream;
import lightning.product.SurfaceBuilder;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.WorldgenRandom;
import lightning.product.PerlinNoise;
import lightning.product.k_594_Q;

public class NetherSurfaceBuilder
extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    private static final K_4074_S R_4764_Y = a_3742_W.a_1344_X.multiplayerClientSuggestionProvider();
    private static final K_4074_S G_564_y = a_3742_W.t_4043_B.multiplayerClientSuggestionProvider();
    private static final K_4074_S P_1922_E = a_3742_W.C_415_h.multiplayerClientSuggestionProvider();
    protected long n_1700_B;
    protected PerlinNoise J_1907_R;

    public NetherSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232132_1_) {
        super(p_i232132_1_);
    }

    @Override
    public void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        int i = seaLevel;
        int j = x & 0xF;
        int k = z & 0xF;
        double d0 = 0.03125;
        boolean flag = this.J_1907_R.n_1700_B((double)x * 0.03125, (double)z * 0.03125, 0.0) * 75.0 + random.nextDouble() > 0.0;
        boolean flag1 = this.J_1907_R.n_1700_B((double)x * 0.03125, 109.0, (double)z * 0.03125) * 75.0 + random.nextDouble() > 0.0;
        int l = (int)(noise / 3.0 + 3.0 + random.nextDouble() * 0.25);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        int i1 = -1;
        K_4074_S blockstate = config.n_1700_B();
        K_4074_S blockstate1 = config.J_1907_R();
        for (int j1 = 127; j1 >= 0; --j1) {
            blockpos$mutable.n_1700_B(j, j1, k);
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
                    blockstate1 = config.J_1907_R();
                } else if (j1 >= i - 4 && j1 <= i + 1) {
                    blockstate = config.n_1700_B();
                    blockstate1 = config.J_1907_R();
                    if (flag1) {
                        blockstate = G_564_y;
                        blockstate1 = config.J_1907_R();
                    }
                    if (flag) {
                        blockstate = P_1922_E;
                        blockstate1 = P_1922_E;
                    }
                }
                if (j1 < i && flag2) {
                    blockstate = defaultFluid;
                }
                i1 = l;
                if (j1 >= i - 1) {
                    chunkIn.setBlockState(blockpos$mutable, blockstate, false);
                    continue;
                }
                chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                continue;
            }
            if (i1 <= 0) continue;
            --i1;
            chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
        }
    }

    @Override
    public void n_1700_B(long seed) {
        if (this.n_1700_B != seed || this.J_1907_R == null) {
            this.J_1907_R = new PerlinNoise(new WorldgenRandom(seed), IntStream.rangeClosed(-3, 0));
        }
        this.n_1700_B = seed;
    }
}


