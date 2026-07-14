/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.function.Supplier;
import lightning.product.SurfaceBuilder;
import lightning.product.K_4074_S;
import lightning.product.V_3137_a;
import lightning.product.Z_927_M;
import lightning.product.ChunkAccess;
import lightning.product.k_594_Q;
import lightning.product.n_4684_C;

public class ConfiguredSurfaceBuilder<SC extends Z_927_M> {
    public static final Codec<ConfiguredSurfaceBuilder<?>> n_1700_B = V_3137_a.U_1241_n.dispatch(p_237169_0_ -> p_237169_0_.R_4764_Y, SurfaceBuilder::G_564_y);
    public static final Codec<Supplier<ConfiguredSurfaceBuilder<?>>> J_1907_R = n_4684_C.n_1700_B(V_3137_a.D_60_a, n_1700_B);
    public final SurfaceBuilder<SC> R_4764_Y;
    public final SC G_564_y;

    public ConfiguredSurfaceBuilder(SurfaceBuilder<SC> builder, SC config) {
        this.R_4764_Y = builder;
        this.G_564_y = config;
    }

    public void n_1700_B(Random random, ChunkAccess chunkIn, k_594_Q biomeIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed) {
        this.R_4764_Y.n_1700_B(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, this.G_564_y);
    }

    public void n_1700_B(long seed) {
        this.R_4764_Y.n_1700_B(seed);
    }

    public SC n_1700_B() {
        return this.G_564_y;
    }
}


