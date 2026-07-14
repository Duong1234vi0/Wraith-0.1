/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.BitSet;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import lightning.product.K_4573_Z;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.i_4544_r;
import lightning.product.k_594_Q;
import lightning.product.n_4684_C;

public class ConfiguredWorldCarver<WC extends K_4573_Z> {
    public static final Codec<ConfiguredWorldCarver<?>> n_1700_B = V_3137_a.dtoRealmsServerAddress.dispatch(p_236236_0_ -> p_236236_0_.G_564_y, i_4544_r::R_4764_Y);
    public static final Codec<Supplier<ConfiguredWorldCarver<?>>> J_1907_R = n_4684_C.n_1700_B(V_3137_a.k_3961_g, n_1700_B);
    public static final Codec<List<Supplier<ConfiguredWorldCarver<?>>>> R_4764_Y = n_4684_C.J_1907_R(V_3137_a.k_3961_g, n_1700_B);
    private final i_4544_r<WC> G_564_y;
    private final WC P_1922_E;

    public ConfiguredWorldCarver(i_4544_r<WC> carver, WC config) {
        this.G_564_y = carver;
        this.P_1922_E = config;
    }

    public WC n_1700_B() {
        return this.P_1922_E;
    }

    public boolean n_1700_B(Random rand, int chunkX, int chunkZ) {
        return this.G_564_y.n_1700_B(rand, chunkX, chunkZ, this.P_1922_E);
    }

    public boolean n_1700_B(ChunkAccess chunk, Function<c_1514_x, k_594_Q> biomePos, Random rand, int seaLevel, int chunkXOffset, int chunkZOffset, int chunkX, int chunkZ, BitSet carvingMask) {
        return this.G_564_y.n_1700_B(chunk, biomePos, rand, seaLevel, chunkXOffset, chunkZOffset, chunkX, chunkZ, carvingMask, this.P_1922_E);
    }
}


