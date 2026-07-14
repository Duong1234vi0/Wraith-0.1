/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BiomeManager;
import lightning.product.IdMap;
import lightning.product.Y_1387_d;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.u_530_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class c_1108_W
implements BiomeManager.n_1700_B {
    private static final Logger G_564_y = LogManager.getLogger();
    private static final int P_1922_E = (int)Math.round(Math.log(16.0) / Math.log(2.0)) - 2;
    private static final int u_1723_Y = (int)Math.round(Math.log(256.0) / Math.log(2.0)) - 2;
    public static final int n_1700_B = 1 << P_1922_E + P_1922_E + u_1723_Y;
    public static final int J_1907_R = (1 << P_1922_E) - 1;
    public static final int R_4764_Y = (1 << u_1723_Y) - 1;
    private final IdMap<k_594_Q> v_4262_N;
    private final k_594_Q[] w_1484_f;

    public c_1108_W(IdMap<k_594_Q> biomeRegistry, k_594_Q[] biomes) {
        this.v_4262_N = biomeRegistry;
        this.w_1484_f = biomes;
    }

    private c_1108_W(IdMap<k_594_Q> biomeRegistry) {
        this(biomeRegistry, new k_594_Q[n_1700_B]);
    }

    public c_1108_W(IdMap<k_594_Q> biomeRegistry, int[] biomes) {
        this(biomeRegistry);
        for (int i = 0; i < this.w_1484_f.length; ++i) {
            int j = biomes[i];
            k_594_Q biome = biomeRegistry.n_1700_B(j);
            if (biome == null) {
                G_564_y.warn("Received invalid biome id: " + j);
                this.w_1484_f[i] = biomeRegistry.n_1700_B(0);
                continue;
            }
            this.w_1484_f[i] = biome;
        }
    }

    public c_1108_W(IdMap<k_594_Q> biomeRegistry, Y_1387_d chunkPos, BiomeSource provider) {
        this(biomeRegistry);
        int i = chunkPos.J_1907_R() >> 2;
        int j = chunkPos.R_4764_Y() >> 2;
        for (int k = 0; k < this.w_1484_f.length; ++k) {
            int l = k & J_1907_R;
            int i1 = k >> P_1922_E + P_1922_E & R_4764_Y;
            int j1 = k >> P_1922_E & J_1907_R;
            this.w_1484_f[k] = provider.G_564_y(i + l, i1, j + j1);
        }
    }

    public c_1108_W(IdMap<k_594_Q> biomeRegistry, Y_1387_d chunkPos, BiomeSource provider, @Nullable int[] biomes) {
        this(biomeRegistry);
        int i = chunkPos.J_1907_R() >> 2;
        int j = chunkPos.R_4764_Y() >> 2;
        if (biomes != null) {
            for (int k = 0; k < biomes.length; ++k) {
                this.w_1484_f[k] = biomeRegistry.n_1700_B(biomes[k]);
                if (this.w_1484_f[k] != null) continue;
                int l = k & J_1907_R;
                int i1 = k >> P_1922_E + P_1922_E & R_4764_Y;
                int j1 = k >> P_1922_E & J_1907_R;
                this.w_1484_f[k] = provider.G_564_y(i + l, i1, j + j1);
            }
        } else {
            for (int k1 = 0; k1 < this.w_1484_f.length; ++k1) {
                int l1 = k1 & J_1907_R;
                int i2 = k1 >> P_1922_E + P_1922_E & R_4764_Y;
                int j2 = k1 >> P_1922_E & J_1907_R;
                this.w_1484_f[k1] = provider.G_564_y(i + l1, i2, j + j2);
            }
        }
    }

    public int[] n_1700_B() {
        int[] aint = new int[this.w_1484_f.length];
        for (int i = 0; i < this.w_1484_f.length; ++i) {
            aint[i] = this.v_4262_N.n_1700_B(this.w_1484_f[i]);
        }
        return aint;
    }

    @Override
    public k_594_Q G_564_y(int x, int y, int z) {
        int i = x & J_1907_R;
        int j = u_530_F.n_1700_B(y, 0, R_4764_Y);
        int k = z & J_1907_R;
        return this.w_1484_f[j << P_1922_E + P_1922_E | k << P_1922_E | i];
    }
}


