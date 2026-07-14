/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.SurfaceBuilder;
import lightning.product.K_4074_S;
import lightning.product.SurfaceBuilderBaseConfiguration;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.k_594_Q;

public class SwampSurfaceBuilder
extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    public SwampSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232137_1_) {
        super(p_i232137_1_);
    }

    @Override
    public void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        double d0 = k_594_Q.u_1723_Y.n_1700_B((double)x * 0.25, (double)z * 0.25, false);
        if (d0 > 0.0) {
            int i = x & 0xF;
            int j = z & 0xF;
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (int k = startHeight; k >= 0; --k) {
                blockpos$mutable.n_1700_B(i, k, j);
                if (chunkIn.getBlockState(blockpos$mutable).v_4262_N()) continue;
                if (k != 62 || chunkIn.getBlockState(blockpos$mutable).n_1700_B(defaultFluid.J_1907_R())) break;
                chunkIn.setBlockState(blockpos$mutable, defaultFluid, false);
                break;
            }
        }
        SurfaceBuilder.Q_2552_b.n_1700_B(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, config);
    }
}


