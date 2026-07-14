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
import lightning.product.ChunkAccess;
import lightning.product.k_594_Q;

public class MountainSurfaceBuilder
extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    public MountainSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232129_1_) {
        super(p_i232129_1_);
    }

    @Override
    public void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        if (noise > 1.0) {
            SurfaceBuilder.Q_2552_b.n_1700_B(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.t_148_a);
        } else {
            SurfaceBuilder.Q_2552_b.n_1700_B(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.w_1484_f);
        }
    }
}


