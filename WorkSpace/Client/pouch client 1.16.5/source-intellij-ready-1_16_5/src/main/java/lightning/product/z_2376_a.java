/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import lightning.product.StructureFeature;
import lightning.product.K_4074_S;
import lightning.product.P_1103_o;
import lightning.product.StructureSettings;
import lightning.product.T_3851_R;
import lightning.product.T_3975_o;
import lightning.product.biomeBiomes;
import lightning.product.Features;
import lightning.product.V_3137_a;
import lightning.product.V_4739_Y;
import lightning.product.StructureFeatures;
import lightning.product.a_3742_W;
import lightning.product.ConfiguredFeature;
import lightning.product.BiomeGenerationSettings;
import lightning.product.Feature;
import lightning.product.j_3341_s;
import lightning.product.ConfiguredStructureFeature;
import lightning.product.k_594_Q;
import lightning.product.LayerConfiguration;
import lightning.product.z_2963_s;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class z_2376_a {
    private static final Logger J_1907_R = LogManager.getLogger();
    public static final Codec<z_2376_a> n_1700_B = RecordCodecBuilder.create(p_236938_0_ -> p_236938_0_.group((App)P_1103_o.n_1700_B(V_3137_a.PlayerInfo).forGetter(p_242874_0_ -> p_242874_0_.G_564_y), (App)StructureSettings.n_1700_B.fieldOf("structures").forGetter(z_2376_a::G_564_y), (App)T_3851_R.n_1700_B.listOf().fieldOf("layers").forGetter(z_2376_a::u_1723_Y), (App)Codec.BOOL.fieldOf("lakes").orElse((Object)false).forGetter(p_241528_0_ -> p_241528_0_.u_2550_I), (App)Codec.BOOL.fieldOf("features").orElse((Object)false).forGetter(p_242871_0_ -> p_242871_0_.s_956_w), (App)k_594_Q.G_564_y.optionalFieldOf("biome").orElseGet(Optional::empty).forGetter(p_242868_0_ -> Optional.of(p_242868_0_.v_4262_N))).apply((Applicative)p_236938_0_, z_2376_a::new)).stable();
    private static final Map<StructureFeature<?>, ConfiguredStructureFeature<?, ?>> R_4764_Y = j_3341_s.n_1700_B(Maps.newHashMap(), p_236940_0_ -> {
        p_236940_0_.put(StructureFeature.R_4764_Y, StructureFeatures.J_1907_R);
        p_236940_0_.put(StructureFeature.t_1786_h, StructureFeatures.Y_601_j);
        p_236940_0_.put(StructureFeature.u_2550_I, StructureFeatures.u_2550_I);
        p_236940_0_.put(StructureFeature.s_956_w, StructureFeatures.s_956_w);
        p_236940_0_.put(StructureFeature.u_1723_Y, StructureFeatures.u_1723_Y);
        p_236940_0_.put(StructureFeature.P_1922_E, StructureFeatures.P_1922_E);
        p_236940_0_.put(StructureFeature.v_4262_N, StructureFeatures.v_4262_N);
        p_236940_0_.put(StructureFeature.P_4830_p, StructureFeatures.P_4830_p);
        p_236940_0_.put(StructureFeature.t_148_a, StructureFeatures.w_1484_f);
        p_236940_0_.put(StructureFeature.M_588_G, StructureFeatures.M_588_G);
        p_236940_0_.put(StructureFeature.Q_4569_t, StructureFeatures.t_1786_h);
        p_236940_0_.put(StructureFeature.G_564_y, StructureFeatures.G_564_y);
        p_236940_0_.put(StructureFeature.h_1847_R, StructureFeatures.Q_4569_t);
        p_236940_0_.put(StructureFeature.J_1907_R, StructureFeatures.n_1700_B);
        p_236940_0_.put(StructureFeature.w_1484_f, StructureFeatures.q_2307_F);
        p_236940_0_.put(StructureFeature.w_1457_N, StructureFeatures.w_1457_N);
    });
    private final V_3137_a<k_594_Q> G_564_y;
    private final StructureSettings P_1922_E;
    private final List<T_3851_R> u_1723_Y = Lists.newArrayList();
    private Supplier<k_594_Q> v_4262_N;
    private final K_4074_S[] w_1484_f = new K_4074_S[256];
    private boolean t_148_a;
    private boolean s_956_w = false;
    private boolean u_2550_I = false;

    public z_2376_a(V_3137_a<k_594_Q> p_i242012_1_, StructureSettings p_i242012_2_, List<T_3851_R> p_i242012_3_, boolean p_i242012_4_, boolean p_i242012_5_, Optional<Supplier<k_594_Q>> p_i242012_6_) {
        this(p_i242012_2_, p_i242012_1_);
        if (p_i242012_4_) {
            this.J_1907_R();
        }
        if (p_i242012_5_) {
            this.n_1700_B();
        }
        this.u_1723_Y.addAll(p_i242012_3_);
        this.w_1484_f();
        if (!p_i242012_6_.isPresent()) {
            J_1907_R.error("Unknown biome, defaulting to plains");
            this.v_4262_N = () -> p_i242012_1_.R_4764_Y(biomeBiomes.J_1907_R);
        } else {
            this.v_4262_N = p_i242012_6_.get();
        }
    }

    public z_2376_a(StructureSettings p_i242011_1_, V_3137_a<k_594_Q> p_i242011_2_) {
        this.G_564_y = p_i242011_2_;
        this.P_1922_E = p_i242011_1_;
        this.v_4262_N = () -> p_i242011_2_.R_4764_Y(biomeBiomes.J_1907_R);
    }

    public z_2376_a n_1700_B(StructureSettings p_236937_1_) {
        return this.n_1700_B(this.u_1723_Y, p_236937_1_);
    }

    public z_2376_a n_1700_B(List<T_3851_R> p_241527_1_, StructureSettings p_241527_2_) {
        z_2376_a flatgenerationsettings = new z_2376_a(p_241527_2_, this.G_564_y);
        for (T_3851_R flatlayerinfo : p_241527_1_) {
            flatgenerationsettings.u_1723_Y.add(new T_3851_R(flatlayerinfo.n_1700_B(), flatlayerinfo.J_1907_R().J_1907_R()));
            flatgenerationsettings.w_1484_f();
        }
        flatgenerationsettings.n_1700_B(this.v_4262_N);
        if (this.s_956_w) {
            flatgenerationsettings.n_1700_B();
        }
        if (this.u_2550_I) {
            flatgenerationsettings.J_1907_R();
        }
        return flatgenerationsettings;
    }

    public void n_1700_B() {
        this.s_956_w = true;
    }

    public void J_1907_R() {
        this.u_2550_I = true;
    }

    public k_594_Q R_4764_Y() {
        boolean flag;
        k_594_Q biome = this.P_1922_E();
        BiomeGenerationSettings biomegenerationsettings = biome.P_1922_E();
        BiomeGenerationSettings.n_1700_B biomegenerationsettings$builder = new BiomeGenerationSettings.n_1700_B().n_1700_B(biomegenerationsettings.G_564_y());
        if (this.u_2550_I) {
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.J_1907_R, Features.q_4610_l);
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.J_1907_R, Features.z_4693_k);
        }
        for (Map.Entry<StructureFeature<?>, V_4739_Y> entry : this.P_1922_E.n_1700_B().entrySet()) {
            biomegenerationsettings$builder.n_1700_B(biomegenerationsettings.n_1700_B(R_4764_Y.get(entry.getKey())));
        }
        boolean bl = flag = (!this.t_148_a || this.G_564_y.R_4764_Y(biome).equals(Optional.of(biomeBiomes.g_2268_R))) && this.s_956_w;
        if (flag) {
            List<List<Supplier<ConfiguredFeature<?, ?>>>> list = biomegenerationsettings.R_4764_Y();
            for (int i = 0; i < list.size(); ++i) {
                if (i == T_3975_o.J_1907_R.G_564_y.ordinal() || i == T_3975_o.J_1907_R.P_1922_E.ordinal()) continue;
                for (Supplier<ConfiguredFeature<?, ?>> supplier : list.get(i)) {
                    biomegenerationsettings$builder.n_1700_B(i, supplier);
                }
            }
        }
        K_4074_S[] ablockstate = this.v_4262_N();
        for (int j = 0; j < ablockstate.length; ++j) {
            K_4074_S blockstate = ablockstate[j];
            if (blockstate == null || z_2963_s.n_1700_B.P_1922_E.P_1922_E().test(blockstate)) continue;
            this.w_1484_f[j] = null;
            biomegenerationsettings$builder.n_1700_B(T_3975_o.J_1907_R.s_956_w, Feature.g_164_R.J_1907_R(new LayerConfiguration(j, blockstate)));
        }
        return new k_594_Q.J_1907_R().n_1700_B(biome.R_4764_Y()).n_1700_B(biome.Y_601_j()).n_1700_B(biome.w_1484_f()).J_1907_R(biome.s_956_w()).R_4764_Y(biome.u_2550_I()).G_564_y(biome.t_148_a()).n_1700_B(biome.M_588_G()).n_1700_B(biomegenerationsettings$builder.n_1700_B()).n_1700_B(biome.J_1907_R()).n_1700_B();
    }

    public StructureSettings G_564_y() {
        return this.P_1922_E;
    }

    public k_594_Q P_1922_E() {
        return this.v_4262_N.get();
    }

    public void n_1700_B(Supplier<k_594_Q> p_242870_1_) {
        this.v_4262_N = p_242870_1_;
    }

    public List<T_3851_R> u_1723_Y() {
        return this.u_1723_Y;
    }

    public K_4074_S[] v_4262_N() {
        return this.w_1484_f;
    }

    public void w_1484_f() {
        Arrays.fill(this.w_1484_f, 0, this.w_1484_f.length, null);
        int i = 0;
        for (T_3851_R flatlayerinfo : this.u_1723_Y) {
            flatlayerinfo.n_1700_B(i);
            i += flatlayerinfo.n_1700_B();
        }
        this.t_148_a = true;
        for (T_3851_R flatlayerinfo1 : this.u_1723_Y) {
            for (int j = flatlayerinfo1.R_4764_Y(); j < flatlayerinfo1.R_4764_Y() + flatlayerinfo1.n_1700_B(); ++j) {
                K_4074_S blockstate = flatlayerinfo1.J_1907_R();
                if (blockstate.n_1700_B(a_3742_W.n_1700_B)) continue;
                this.t_148_a = false;
                this.w_1484_f[j] = blockstate;
            }
        }
    }

    public static z_2376_a n_1700_B(V_3137_a<k_594_Q> p_242869_0_) {
        StructureSettings dimensionstructuressettings = new StructureSettings(Optional.of(StructureSettings.R_4764_Y), Maps.newHashMap((Map)ImmutableMap.of(StructureFeature.t_1786_h, (Object)((V_4739_Y)StructureSettings.J_1907_R.get(StructureFeature.t_1786_h)))));
        z_2376_a flatgenerationsettings = new z_2376_a(dimensionstructuressettings, p_242869_0_);
        flatgenerationsettings.v_4262_N = () -> p_242869_0_.R_4764_Y(biomeBiomes.J_1907_R);
        flatgenerationsettings.u_1723_Y().add(new T_3851_R(1, a_3742_W.Z_875_P));
        flatgenerationsettings.u_1723_Y().add(new T_3851_R(2, a_3742_W.s_956_w));
        flatgenerationsettings.u_1723_Y().add(new T_3851_R(1, a_3742_W.t_148_a));
        flatgenerationsettings.w_1484_f();
        return flatgenerationsettings;
    }
}


