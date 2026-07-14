/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.Lifecycle
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import lightning.product.P_1103_o;
import lightning.product.biomeBiomes;
import lightning.product.V_3137_a;
import lightning.product.V_4170_D;
import lightning.product.f_2392_k;
import lightning.product.k_594_Q;
import lightning.product.n_1670_s;
import lightning.product.BiomeSource;

public class OverworldBiomeSource
extends BiomeSource {
    public static final Codec<OverworldBiomeSource> P_1922_E = RecordCodecBuilder.create(builder -> builder.group((App)Codec.LONG.fieldOf("seed").stable().forGetter(overworldProvider -> overworldProvider.w_1484_f), (App)Codec.BOOL.optionalFieldOf("legacy_biome_init_layer", (Object)false, Lifecycle.stable()).forGetter(overworldProvider -> overworldProvider.t_148_a), (App)Codec.BOOL.fieldOf("large_biomes").orElse((Object)false).stable().forGetter(overworldProvider -> overworldProvider.s_956_w), (App)P_1103_o.n_1700_B(V_3137_a.PlayerInfo).forGetter(overworldProvider -> overworldProvider.u_2550_I)).apply((Applicative)builder, builder.stable(OverworldBiomeSource::new)));
    private final n_1670_s u_1723_Y;
    private static final List<f_2392_k<k_594_Q>> v_4262_N = ImmutableList.of(biomeBiomes.n_1700_B, biomeBiomes.J_1907_R, biomeBiomes.R_4764_Y, biomeBiomes.G_564_y, biomeBiomes.P_1922_E, biomeBiomes.u_1723_Y, biomeBiomes.v_4262_N, biomeBiomes.w_1484_f, biomeBiomes.u_2550_I, biomeBiomes.M_588_G, biomeBiomes.P_4830_p, biomeBiomes.h_1847_R, (Object[])new f_2392_k[]{biomeBiomes.Q_4569_t, biomeBiomes.M_182_A, biomeBiomes.t_1786_h, biomeBiomes.multiplayerClientSuggestionProvider, biomeBiomes.w_1457_N, biomeBiomes.Y_601_j, biomeBiomes.Y_259_p, biomeBiomes.Q_2552_b, biomeBiomes.C_2741_M, biomeBiomes.k_2293_S, biomeBiomes.q_2307_F, biomeBiomes.Z_875_P, biomeBiomes.c_3005_b, biomeBiomes.H_2857_Y, biomeBiomes.A_4115_X, biomeBiomes.Y_1740_V, biomeBiomes.t_4043_B, biomeBiomes.x_607_J, biomeBiomes.e_4240_b, biomeBiomes.n_3318_d, biomeBiomes.d_2427_y, biomeBiomes.z_1737_N, biomeBiomes.v_4276_D, biomeBiomes.d_2461_k, biomeBiomes.G_624_v, biomeBiomes.T_2506_i, biomeBiomes.B_1668_F, biomeBiomes.g_164_R, biomeBiomes.X_933_l, biomeBiomes.Z_976_R, biomeBiomes.H_1990_U, biomeBiomes.N_2525_X, biomeBiomes.c_4037_x, biomeBiomes.T_3594_S, biomeBiomes.D_4792_h, biomeBiomes.s_2632_s, biomeBiomes.l_1233_K, biomeBiomes.z_1333_t, biomeBiomes.O_508_d, biomeBiomes.r_715_M, biomeBiomes.A_1038_p, biomeBiomes.i_1637_u, biomeBiomes.Ping, biomeBiomes.p_178_J, biomeBiomes.RealmsClientConfig, biomeBiomes.f_4016_n, biomeBiomes.j_276_v, biomeBiomes.UploadStatus, biomeBiomes.e_1992_r, biomeBiomes.D_60_a, biomeBiomes.k_3961_g, biomeBiomes.Ops, biomeBiomes.h_4320_q, biomeBiomes.t_4219_U});
    private final long w_1484_f;
    private final boolean t_148_a;
    private final boolean s_956_w;
    private final V_3137_a<k_594_Q> u_2550_I;

    public OverworldBiomeSource(long seed, boolean legacyBiomes, boolean largeBiomes, V_3137_a<k_594_Q> lookupRegistry) {
        super(v_4262_N.stream().map(key -> () -> (k_594_Q)lookupRegistry.R_4764_Y((f_2392_k<k_594_Q>)key)));
        this.w_1484_f = seed;
        this.t_148_a = legacyBiomes;
        this.s_956_w = largeBiomes;
        this.u_2550_I = lookupRegistry;
        this.u_1723_Y = V_4170_D.n_1700_B(seed, legacyBiomes, largeBiomes ? 6 : 4, 4);
    }

    @Override
    protected Codec<? extends BiomeSource> n_1700_B() {
        return P_1922_E;
    }

    @Override
    public BiomeSource n_1700_B(long seed) {
        return new OverworldBiomeSource(seed, this.t_148_a, this.s_956_w, this.u_2550_I);
    }

    @Override
    public k_594_Q G_564_y(int x, int y, int z) {
        return this.u_1723_Y.n_1700_B(this.u_2550_I, x, z);
    }
}


