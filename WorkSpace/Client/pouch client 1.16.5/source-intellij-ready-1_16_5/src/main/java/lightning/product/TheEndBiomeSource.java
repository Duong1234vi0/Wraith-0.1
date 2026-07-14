/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import lightning.product.SimplexNoise;
import lightning.product.P_1103_o;
import lightning.product.biomeBiomes;
import lightning.product.V_3137_a;
import lightning.product.WorldgenRandom;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.u_530_F;

public class TheEndBiomeSource
extends BiomeSource {
    public static final Codec<TheEndBiomeSource> P_1922_E = RecordCodecBuilder.create(builder -> builder.group((App)P_1103_o.n_1700_B(V_3137_a.PlayerInfo).forGetter(provider -> provider.v_4262_N), (App)Codec.LONG.fieldOf("seed").stable().forGetter(provider -> provider.w_1484_f)).apply((Applicative)builder, builder.stable(TheEndBiomeSource::new)));
    private final SimplexNoise u_1723_Y;
    private final V_3137_a<k_594_Q> v_4262_N;
    private final long w_1484_f;
    private final k_594_Q t_148_a;
    private final k_594_Q s_956_w;
    private final k_594_Q u_2550_I;
    private final k_594_Q M_588_G;
    private final k_594_Q P_4830_p;

    public TheEndBiomeSource(V_3137_a<k_594_Q> lookupRegistry, long seed) {
        this(lookupRegistry, seed, lookupRegistry.R_4764_Y(biomeBiomes.s_956_w), lookupRegistry.R_4764_Y(biomeBiomes.g_221_o), lookupRegistry.R_4764_Y(biomeBiomes.z_4693_k), lookupRegistry.R_4764_Y(biomeBiomes.q_4610_l), lookupRegistry.R_4764_Y(biomeBiomes.e_2887_G));
    }

    private TheEndBiomeSource(V_3137_a<k_594_Q> lookupRegistry, long seed, k_594_Q theEndBiome, k_594_Q endHighlandsBiome, k_594_Q endMidlandsBiome, k_594_Q smallEndIslandsBiome, k_594_Q endBarrensBiome) {
        super((List<k_594_Q>)ImmutableList.of((Object)theEndBiome, (Object)endHighlandsBiome, (Object)endMidlandsBiome, (Object)smallEndIslandsBiome, (Object)endBarrensBiome));
        this.v_4262_N = lookupRegistry;
        this.w_1484_f = seed;
        this.t_148_a = theEndBiome;
        this.s_956_w = endHighlandsBiome;
        this.u_2550_I = endMidlandsBiome;
        this.M_588_G = smallEndIslandsBiome;
        this.P_4830_p = endBarrensBiome;
        WorldgenRandom sharedseedrandom = new WorldgenRandom(seed);
        sharedseedrandom.n_1700_B(17292);
        this.u_1723_Y = new SimplexNoise(sharedseedrandom);
    }

    @Override
    protected Codec<? extends BiomeSource> n_1700_B() {
        return P_1922_E;
    }

    @Override
    public BiomeSource n_1700_B(long seed) {
        return new TheEndBiomeSource(this.v_4262_N, seed, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p);
    }

    @Override
    public k_594_Q G_564_y(int x, int y, int z) {
        int i = x >> 2;
        int j = z >> 2;
        if ((long)i * (long)i + (long)j * (long)j <= 4096L) {
            return this.t_148_a;
        }
        float f = TheEndBiomeSource.n_1700_B(this.u_1723_Y, i * 2 + 1, j * 2 + 1);
        if (f > 40.0f) {
            return this.s_956_w;
        }
        if (f >= 0.0f) {
            return this.u_2550_I;
        }
        return f < -20.0f ? this.M_588_G : this.P_4830_p;
    }

    public boolean J_1907_R(long seed) {
        return this.w_1484_f == seed;
    }

    public static float n_1700_B(SimplexNoise noiseGenerator, int x, int z) {
        int i = x / 2;
        int j = z / 2;
        int k = x % 2;
        int l = z % 2;
        float f = 100.0f - u_530_F.R_4764_Y((float)(x * x + z * z)) * 8.0f;
        f = u_530_F.n_1700_B(f, -100.0f, 80.0f);
        for (int i1 = -12; i1 <= 12; ++i1) {
            for (int j1 = -12; j1 <= 12; ++j1) {
                long k1 = i + i1;
                long l1 = j + j1;
                if (k1 * k1 + l1 * l1 <= 4096L || !(noiseGenerator.n_1700_B(k1, l1) < (double)-0.9f)) continue;
                float f1 = (u_530_F.P_1922_E(k1) * 3439.0f + u_530_F.P_1922_E(l1) * 147.0f) % 13.0f + 9.0f;
                float f2 = k - i1 * 2;
                float f3 = l - j1 * 2;
                float f4 = 100.0f - u_530_F.R_4764_Y(f2 * f2 + f3 * f3) * f1;
                f4 = u_530_F.n_1700_B(f4, -100.0f, 80.0f);
                f = Math.max(f, f4);
            }
        }
        return f;
    }
}


