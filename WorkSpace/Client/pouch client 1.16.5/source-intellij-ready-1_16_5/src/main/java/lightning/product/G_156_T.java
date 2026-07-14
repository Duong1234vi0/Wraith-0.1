/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import lightning.product.StructureFeature;
import lightning.product.K_4074_S;
import lightning.product.StructureSettings;
import lightning.product.NoiseSlideSettings;
import lightning.product.NoiseSamplingSettings;
import lightning.product.NoiseSettings;
import lightning.product.V_3137_a;
import lightning.product.BuiltinRegistries;
import lightning.product.V_4739_Y;
import lightning.product.a_3742_W;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.n_4684_C;

public final class G_156_T {
    public static final Codec<G_156_T> n_1700_B = RecordCodecBuilder.create(p_236112_0_ -> p_236112_0_.group((App)StructureSettings.n_1700_B.fieldOf("structures").forGetter(G_156_T::n_1700_B), (App)NoiseSettings.n_1700_B.fieldOf("noise").forGetter(G_156_T::J_1907_R), (App)K_4074_S.J_1907_R.fieldOf("default_block").forGetter(G_156_T::R_4764_Y), (App)K_4074_S.J_1907_R.fieldOf("default_fluid").forGetter(G_156_T::G_564_y), (App)Codec.intRange((int)-20, (int)276).fieldOf("bedrock_roof_position").forGetter(G_156_T::P_1922_E), (App)Codec.intRange((int)-20, (int)276).fieldOf("bedrock_floor_position").forGetter(G_156_T::u_1723_Y), (App)Codec.intRange((int)0, (int)255).fieldOf("sea_level").forGetter(G_156_T::v_4262_N), (App)Codec.BOOL.fieldOf("disable_mob_generation").forGetter(G_156_T::w_1484_f)).apply((Applicative)p_236112_0_, G_156_T::new));
    public static final Codec<Supplier<G_156_T>> J_1907_R = n_4684_C.n_1700_B(V_3137_a.e_1992_r, n_1700_B);
    private final StructureSettings t_148_a;
    private final NoiseSettings s_956_w;
    private final K_4074_S u_2550_I;
    private final K_4074_S M_588_G;
    private final int P_4830_p;
    private final int h_1847_R;
    private final int Q_4569_t;
    private final boolean M_182_A;
    public static final f_2392_k<G_156_T> R_4764_Y = f_2392_k.n_1700_B(V_3137_a.e_1992_r, new g_2336_b("overworld"));
    public static final f_2392_k<G_156_T> G_564_y = f_2392_k.n_1700_B(V_3137_a.e_1992_r, new g_2336_b("amplified"));
    public static final f_2392_k<G_156_T> P_1922_E = f_2392_k.n_1700_B(V_3137_a.e_1992_r, new g_2336_b("nether"));
    public static final f_2392_k<G_156_T> u_1723_Y = f_2392_k.n_1700_B(V_3137_a.e_1992_r, new g_2336_b("end"));
    public static final f_2392_k<G_156_T> v_4262_N = f_2392_k.n_1700_B(V_3137_a.e_1992_r, new g_2336_b("caves"));
    public static final f_2392_k<G_156_T> w_1484_f = f_2392_k.n_1700_B(V_3137_a.e_1992_r, new g_2336_b("floating_islands"));
    private static final G_156_T t_1786_h = G_156_T.n_1700_B(R_4764_Y, G_156_T.n_1700_B(new StructureSettings(true), false, R_4764_Y.n_1700_B()));

    private G_156_T(StructureSettings structures, NoiseSettings noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int p_i231905_5_, int p_i231905_6_, int p_i231905_7_, boolean p_i231905_8_) {
        this.t_148_a = structures;
        this.s_956_w = noise;
        this.u_2550_I = defaultBlock;
        this.M_588_G = defaultFluid;
        this.P_4830_p = p_i231905_5_;
        this.h_1847_R = p_i231905_6_;
        this.Q_4569_t = p_i231905_7_;
        this.M_182_A = p_i231905_8_;
    }

    public StructureSettings n_1700_B() {
        return this.t_148_a;
    }

    public NoiseSettings J_1907_R() {
        return this.s_956_w;
    }

    public K_4074_S R_4764_Y() {
        return this.u_2550_I;
    }

    public K_4074_S G_564_y() {
        return this.M_588_G;
    }

    public int P_1922_E() {
        return this.P_4830_p;
    }

    public int u_1723_Y() {
        return this.h_1847_R;
    }

    public int v_4262_N() {
        return this.Q_4569_t;
    }

    @Deprecated
    protected boolean w_1484_f() {
        return this.M_182_A;
    }

    public boolean n_1700_B(f_2392_k<G_156_T> p_242744_1_) {
        return Objects.equals(this, BuiltinRegistries.s_956_w.n_1700_B(p_242744_1_));
    }

    private static G_156_T n_1700_B(f_2392_k<G_156_T> p_242745_0_, G_156_T p_242745_1_) {
        BuiltinRegistries.n_1700_B(BuiltinRegistries.s_956_w, p_242745_0_.n_1700_B(), p_242745_1_);
        return p_242745_1_;
    }

    public static G_156_T t_148_a() {
        return t_1786_h;
    }

    private static G_156_T n_1700_B(StructureSettings p_242742_0_, K_4074_S p_242742_1_, K_4074_S p_242742_2_, g_2336_b p_242742_3_, boolean p_242742_4_, boolean p_242742_5_) {
        return new G_156_T(p_242742_0_, new NoiseSettings(128, new NoiseSamplingSettings(2.0, 1.0, 80.0, 160.0), new NoiseSlideSettings(-3000, 64, -46), new NoiseSlideSettings(-30, 7, 1), 2, 1, 0.0, 0.0, true, false, p_242742_5_, false), p_242742_1_, p_242742_2_, -10, -10, 0, p_242742_4_);
    }

    private static G_156_T n_1700_B(StructureSettings p_242741_0_, K_4074_S p_242741_1_, K_4074_S p_242741_2_, g_2336_b p_242741_3_) {
        HashMap map = Maps.newHashMap(StructureSettings.J_1907_R);
        map.put(StructureFeature.w_1484_f, new V_4739_Y(25, 10, 34222645));
        return new G_156_T(new StructureSettings(Optional.ofNullable(p_242741_0_.J_1907_R()), map), new NoiseSettings(128, new NoiseSamplingSettings(1.0, 3.0, 80.0, 60.0), new NoiseSlideSettings(120, 3, 0), new NoiseSlideSettings(320, 4, -1), 1, 2, 0.0, 0.019921875, false, false, false, false), p_242741_1_, p_242741_2_, 0, 0, 32, false);
    }

    private static G_156_T n_1700_B(StructureSettings p_242743_0_, boolean p_242743_1_, g_2336_b p_242743_2_) {
        double d0 = 0.9999999814507745;
        return new G_156_T(p_242743_0_, new NoiseSettings(256, new NoiseSamplingSettings(0.9999999814507745, 0.9999999814507745, 80.0, 160.0), new NoiseSlideSettings(-10, 3, 0), new NoiseSlideSettings(-30, 0, 0), 1, 2, 1.0, -0.46875, true, true, false, p_242743_1_), a_3742_W.J_1907_R.multiplayerClientSuggestionProvider(), a_3742_W.c_3005_b.multiplayerClientSuggestionProvider(), -10, 0, 63, false);
    }

    static {
        G_156_T.n_1700_B(G_564_y, G_156_T.n_1700_B(new StructureSettings(true), true, G_564_y.n_1700_B()));
        G_156_T.n_1700_B(P_1922_E, G_156_T.n_1700_B(new StructureSettings(false), a_3742_W.i_3196_G.multiplayerClientSuggestionProvider(), a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider(), P_1922_E.n_1700_B()));
        G_156_T.n_1700_B(u_1723_Y, G_156_T.n_1700_B(new StructureSettings(false), a_3742_W.e_1231_S.multiplayerClientSuggestionProvider(), a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), u_1723_Y.n_1700_B(), true, true));
        G_156_T.n_1700_B(v_4262_N, G_156_T.n_1700_B(new StructureSettings(true), a_3742_W.J_1907_R.multiplayerClientSuggestionProvider(), a_3742_W.c_3005_b.multiplayerClientSuggestionProvider(), v_4262_N.n_1700_B()));
        G_156_T.n_1700_B(w_1484_f, G_156_T.n_1700_B(new StructureSettings(true), a_3742_W.J_1907_R.multiplayerClientSuggestionProvider(), a_3742_W.c_3005_b.multiplayerClientSuggestionProvider(), w_1484_f.n_1700_B(), false, false));
    }
}


