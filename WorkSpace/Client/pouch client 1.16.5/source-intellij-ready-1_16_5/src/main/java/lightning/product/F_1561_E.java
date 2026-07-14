/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import lightning.product.D_3718_K;
import lightning.product.F_2904_S;
import lightning.product.G_156_T;
import lightning.product.G_212_i;
import lightning.product.OverworldBiomeSource;
import lightning.product.biomeBiomes;
import lightning.product.V_3137_a;
import lightning.product.V_4077_W;
import lightning.product.W_2121_d;
import lightning.product.Z_3903_F;
import lightning.product.j_419_j;
import lightning.product.k_2603_m;
import lightning.product.k_594_Q;
import lightning.product.WritableRegistry;
import lightning.product.n_395_H;
import lightning.product.o_1792_J;
import lightning.product.DebugLevelSource;
import lightning.product.r_4097_j;
import lightning.product.x_282_a;
import lightning.product.z_1753_f;
import lightning.product.z_2376_a;

public abstract class F_1561_E {
    public static final F_1561_E n_1700_B = new F_1561_E("default"){

        @Override
        protected z_1753_f n_1700_B(V_3137_a<k_594_Q> p_241869_1_, V_3137_a<G_156_T> p_241869_2_, long p_241869_3_) {
            return new n_395_H(new OverworldBiomeSource(p_241869_3_, false, false, p_241869_1_), p_241869_3_, () -> p_241869_2_.R_4764_Y(G_156_T.R_4764_Y));
        }
    };
    private static final F_1561_E P_1922_E = new F_1561_E("flat"){

        @Override
        protected z_1753_f n_1700_B(V_3137_a<k_594_Q> p_241869_1_, V_3137_a<G_156_T> p_241869_2_, long p_241869_3_) {
            return new W_2121_d(z_2376_a.n_1700_B(p_241869_1_));
        }
    };
    private static final F_1561_E u_1723_Y = new F_1561_E("large_biomes"){

        @Override
        protected z_1753_f n_1700_B(V_3137_a<k_594_Q> p_241869_1_, V_3137_a<G_156_T> p_241869_2_, long p_241869_3_) {
            return new n_395_H(new OverworldBiomeSource(p_241869_3_, false, true, p_241869_1_), p_241869_3_, () -> p_241869_2_.R_4764_Y(G_156_T.R_4764_Y));
        }
    };
    public static final F_1561_E J_1907_R = new F_1561_E("amplified"){

        @Override
        protected z_1753_f n_1700_B(V_3137_a<k_594_Q> p_241869_1_, V_3137_a<G_156_T> p_241869_2_, long p_241869_3_) {
            return new n_395_H(new OverworldBiomeSource(p_241869_3_, false, false, p_241869_1_), p_241869_3_, () -> p_241869_2_.R_4764_Y(G_156_T.G_564_y));
        }
    };
    private static final F_1561_E v_4262_N = new F_1561_E("single_biome_surface"){

        @Override
        protected z_1753_f n_1700_B(V_3137_a<k_594_Q> p_241869_1_, V_3137_a<G_156_T> p_241869_2_, long p_241869_3_) {
            return new n_395_H(new D_3718_K(p_241869_1_.R_4764_Y(biomeBiomes.J_1907_R)), p_241869_3_, () -> p_241869_2_.R_4764_Y(G_156_T.R_4764_Y));
        }
    };
    private static final F_1561_E w_1484_f = new F_1561_E("single_biome_caves"){

        @Override
        public j_419_j n_1700_B(r_4097_j.J_1907_R p_241220_1_, long p_241220_2_, boolean p_241220_4_, boolean p_241220_5_) {
            WritableRegistry<k_594_Q> registry = p_241220_1_.J_1907_R(V_3137_a.PlayerInfo);
            WritableRegistry<Z_3903_F> registry1 = p_241220_1_.J_1907_R(V_3137_a.d_2427_y);
            WritableRegistry<G_156_T> registry2 = p_241220_1_.J_1907_R(V_3137_a.e_1992_r);
            return new j_419_j(p_241220_2_, p_241220_4_, p_241220_5_, j_419_j.n_1700_B(Z_3903_F.n_1700_B(registry1, registry, registry2, p_241220_2_), () -> registry1.R_4764_Y(Z_3903_F.M_588_G), this.n_1700_B(registry, registry2, p_241220_2_)));
        }

        @Override
        protected z_1753_f n_1700_B(V_3137_a<k_594_Q> p_241869_1_, V_3137_a<G_156_T> p_241869_2_, long p_241869_3_) {
            return new n_395_H(new D_3718_K(p_241869_1_.R_4764_Y(biomeBiomes.J_1907_R)), p_241869_3_, () -> p_241869_2_.R_4764_Y(G_156_T.v_4262_N));
        }
    };
    private static final F_1561_E t_148_a = new F_1561_E("single_biome_floating_islands"){

        @Override
        protected z_1753_f n_1700_B(V_3137_a<k_594_Q> p_241869_1_, V_3137_a<G_156_T> p_241869_2_, long p_241869_3_) {
            return new n_395_H(new D_3718_K(p_241869_1_.R_4764_Y(biomeBiomes.J_1907_R)), p_241869_3_, () -> p_241869_2_.R_4764_Y(G_156_T.w_1484_f));
        }
    };
    private static final F_1561_E s_956_w = new F_1561_E("debug_all_block_states"){

        @Override
        protected z_1753_f n_1700_B(V_3137_a<k_594_Q> p_241869_1_, V_3137_a<G_156_T> p_241869_2_, long p_241869_3_) {
            return new DebugLevelSource(p_241869_1_);
        }
    };
    protected static final List<F_1561_E> R_4764_Y = Lists.newArrayList((Object[])new F_1561_E[]{n_1700_B, P_1922_E, u_1723_Y, J_1907_R, v_4262_N, w_1484_f, t_148_a, s_956_w});
    protected static final Map<Optional<F_1561_E>, n_1700_B> G_564_y = ImmutableMap.of(Optional.of(P_1922_E), (p_239089_0_, p_239089_1_) -> {
        z_1753_f chunkgenerator = p_239089_1_.P_1922_E();
        return new G_212_i(p_239089_0_, p_239083_2_ -> p_239089_0_.R_4764_Y.n_1700_B(new j_419_j(p_239089_1_.n_1700_B(), p_239089_1_.J_1907_R(), p_239089_1_.R_4764_Y(), j_419_j.n_1700_B(p_239089_0_.R_4764_Y.J_1907_R().J_1907_R(V_3137_a.d_2427_y), p_239089_1_.G_564_y(), (z_1753_f)new W_2121_d((z_2376_a)p_239083_2_)))), chunkgenerator instanceof W_2121_d ? ((W_2121_d)chunkgenerator).v_4262_N() : z_2376_a.n_1700_B(p_239089_0_.R_4764_Y.J_1907_R().J_1907_R(V_3137_a.PlayerInfo)));
    }, Optional.of(v_4262_N), (p_239087_0_, p_239087_1_) -> new V_4077_W(p_239087_0_, p_239087_0_.R_4764_Y.J_1907_R(), p_239088_2_ -> p_239087_0_.R_4764_Y.n_1700_B(F_1561_E.n_1700_B(p_239087_0_.R_4764_Y.J_1907_R(), p_239087_1_, v_4262_N, p_239088_2_)), F_1561_E.n_1700_B(p_239087_0_.R_4764_Y.J_1907_R(), p_239087_1_)), Optional.of(w_1484_f), (p_239085_0_, p_239085_1_) -> new V_4077_W(p_239085_0_, p_239085_0_.R_4764_Y.J_1907_R(), p_239086_2_ -> p_239085_0_.R_4764_Y.n_1700_B(F_1561_E.n_1700_B(p_239085_0_.R_4764_Y.J_1907_R(), p_239085_1_, w_1484_f, p_239086_2_)), F_1561_E.n_1700_B(p_239085_0_.R_4764_Y.J_1907_R(), p_239085_1_)), Optional.of(t_148_a), (p_239081_0_, p_239081_1_) -> new V_4077_W(p_239081_0_, p_239081_0_.R_4764_Y.J_1907_R(), p_239082_2_ -> p_239081_0_.R_4764_Y.n_1700_B(F_1561_E.n_1700_B(p_239081_0_.R_4764_Y.J_1907_R(), p_239081_1_, t_148_a, p_239082_2_)), F_1561_E.n_1700_B(p_239081_0_.R_4764_Y.J_1907_R(), p_239081_1_)));
    private final x_282_a u_2550_I;

    private F_1561_E(String p_i232324_1_) {
        this.u_2550_I = new F_2904_S("generator." + p_i232324_1_);
    }

    private static j_419_j n_1700_B(r_4097_j p_243452_0_, j_419_j p_243452_1_, F_1561_E p_243452_2_, k_594_Q p_243452_3_) {
        D_3718_K biomeprovider = new D_3718_K(p_243452_3_);
        WritableRegistry<Z_3903_F> registry = p_243452_0_.J_1907_R(V_3137_a.d_2427_y);
        WritableRegistry<G_156_T> registry1 = p_243452_0_.J_1907_R(V_3137_a.e_1992_r);
        Supplier<G_156_T> supplier = p_243452_2_ == w_1484_f ? () -> registry1.R_4764_Y(G_156_T.v_4262_N) : (p_243452_2_ == t_148_a ? () -> registry1.R_4764_Y(G_156_T.w_1484_f) : () -> registry1.R_4764_Y(G_156_T.R_4764_Y));
        return new j_419_j(p_243452_1_.n_1700_B(), p_243452_1_.J_1907_R(), p_243452_1_.R_4764_Y(), j_419_j.n_1700_B(registry, p_243452_1_.G_564_y(), (z_1753_f)new n_395_H(biomeprovider, p_243452_1_.n_1700_B(), supplier)));
    }

    private static k_594_Q n_1700_B(r_4097_j p_243451_0_, j_419_j p_243451_1_) {
        return p_243451_1_.P_1922_E().G_564_y().J_1907_R().stream().findFirst().orElse(p_243451_0_.J_1907_R(V_3137_a.PlayerInfo).R_4764_Y(biomeBiomes.J_1907_R));
    }

    public static Optional<F_1561_E> n_1700_B(j_419_j p_239079_0_) {
        z_1753_f chunkgenerator = p_239079_0_.P_1922_E();
        if (chunkgenerator instanceof W_2121_d) {
            return Optional.of(P_1922_E);
        }
        return chunkgenerator instanceof DebugLevelSource ? Optional.of(s_956_w) : Optional.empty();
    }

    public x_282_a n_1700_B() {
        return this.u_2550_I;
    }

    public j_419_j n_1700_B(r_4097_j.J_1907_R p_241220_1_, long p_241220_2_, boolean p_241220_4_, boolean p_241220_5_) {
        WritableRegistry<k_594_Q> registry = p_241220_1_.J_1907_R(V_3137_a.PlayerInfo);
        WritableRegistry<Z_3903_F> registry1 = p_241220_1_.J_1907_R(V_3137_a.d_2427_y);
        WritableRegistry<G_156_T> registry2 = p_241220_1_.J_1907_R(V_3137_a.e_1992_r);
        return new j_419_j(p_241220_2_, p_241220_4_, p_241220_5_, j_419_j.n_1700_B(registry1, Z_3903_F.n_1700_B(registry1, registry, registry2, p_241220_2_), this.n_1700_B(registry, registry2, p_241220_2_)));
    }

    protected abstract z_1753_f n_1700_B(V_3137_a<k_594_Q> var1, V_3137_a<G_156_T> var2, long var3);

    public static interface n_1700_B {
        public k_2603_m createEditScreen(o_1792_J var1, j_419_j var2);
    }
}


