/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import lightning.product.SurfaceBuilder;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.WorldgenRandom;
import lightning.product.PerlinNoise;
import lightning.product.k_594_Q;

public abstract class I_1748_L
extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    private long n_1700_B;
    private ImmutableMap<K_4074_S, PerlinNoise> J_1907_R = ImmutableMap.of();
    private ImmutableMap<K_4074_S, PerlinNoise> R_4764_Y = ImmutableMap.of();
    private PerlinNoise G_564_y;

    public I_1748_L(Codec<SurfaceBuilderBaseConfiguration> p_i232130_1_) {
        super(p_i232130_1_);
    }

    @Override
    public void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        int i = seaLevel + 1;
        int j = x & 0xF;
        int k = z & 0xF;
        int l = (int)(noise / 3.0 + 3.0 + random.nextDouble() * 0.25);
        int i1 = (int)(noise / 3.0 + 3.0 + random.nextDouble() * 0.25);
        double d0 = 0.03125;
        boolean flag = this.G_564_y.n_1700_B((double)x * 0.03125, 109.0, (double)z * 0.03125) * 75.0 + random.nextDouble() > 0.0;
        K_4074_S blockstate = (K_4074_S)this.R_4764_Y.entrySet().stream().max(Comparator.comparing(p_237176_3_ -> ((PerlinNoise)p_237176_3_.getValue()).n_1700_B(x, seaLevel, z))).get().getKey();
        K_4074_S blockstate1 = (K_4074_S)this.J_1907_R.entrySet().stream().max(Comparator.comparing(p_237174_3_ -> ((PerlinNoise)p_237174_3_.getValue()).n_1700_B(x, seaLevel, z))).get().getKey();
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        K_4074_S blockstate2 = chunkIn.getBlockState(blockpos$mutable.n_1700_B(j, 128, k));
        for (int j1 = 127; j1 >= 0; --j1) {
            blockpos$mutable.n_1700_B(j, j1, k);
            K_4074_S blockstate3 = chunkIn.getBlockState(blockpos$mutable);
            if (blockstate2.n_1700_B(defaultBlock.J_1907_R()) && (blockstate3.v_4262_N() || blockstate3 == defaultFluid)) {
                for (int k1 = 0; k1 < l; ++k1) {
                    blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
                    if (!chunkIn.getBlockState(blockpos$mutable).n_1700_B(defaultBlock.J_1907_R())) break;
                    chunkIn.setBlockState(blockpos$mutable, blockstate, false);
                }
                blockpos$mutable.n_1700_B(j, j1, k);
            }
            if ((blockstate2.v_4262_N() || blockstate2 == defaultFluid) && blockstate3.n_1700_B(defaultBlock.J_1907_R())) {
                for (int l1 = 0; l1 < i1 && chunkIn.getBlockState(blockpos$mutable).n_1700_B(defaultBlock.J_1907_R()); ++l1) {
                    if (flag && j1 >= i - 4 && j1 <= i + 1) {
                        chunkIn.setBlockState(blockpos$mutable, this.R_4764_Y(), false);
                    } else {
                        chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                    }
                    blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
                }
            }
            blockstate2 = blockstate3;
        }
    }

    @Override
    public void n_1700_B(long seed) {
        if (this.n_1700_B != seed || this.G_564_y == null || this.J_1907_R.isEmpty() || this.R_4764_Y.isEmpty()) {
            this.J_1907_R = I_1748_L.n_1700_B(this.n_1700_B(), seed);
            this.R_4764_Y = I_1748_L.n_1700_B(this.J_1907_R(), seed + (long)this.J_1907_R.size());
            this.G_564_y = new PerlinNoise(new WorldgenRandom(seed + (long)this.J_1907_R.size() + (long)this.R_4764_Y.size()), (List<Integer>)ImmutableList.of((Object)0));
        }
        this.n_1700_B = seed;
    }

    private static ImmutableMap<K_4074_S, PerlinNoise> n_1700_B(ImmutableList<K_4074_S> p_237175_0_, long p_237175_1_) {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        for (K_4074_S blockstate : p_237175_0_) {
            builder.put((Object)blockstate, (Object)new PerlinNoise(new WorldgenRandom(p_237175_1_), (List<Integer>)ImmutableList.of((Object)-4)));
            ++p_237175_1_;
        }
        return builder.build();
    }

    protected abstract ImmutableList<K_4074_S> n_1700_B();

    protected abstract ImmutableList<K_4074_S> J_1907_R();

    protected abstract K_4074_S R_4764_Y();
}


