/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.collect.ImmutableSet
 *  com.google.gson.JsonObject
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.JsonOps
 *  com.mojang.serialization.Lifecycle
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonObject;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Properties;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import lightning.product.G_156_T;
import lightning.product.LevelStem;
import lightning.product.OverworldBiomeSource;
import lightning.product.V_3137_a;
import lightning.product.W_2121_d;
import lightning.product.Z_3903_F;
import lightning.product.b_4507_u;
import lightning.product.f_2392_k;
import lightning.product.i_4431_W;
import lightning.product.k_594_Q;
import lightning.product.WritableRegistry;
import lightning.product.n_395_H;
import lightning.product.DebugLevelSource;
import lightning.product.r_4097_j;
import lightning.product.v_1758_J;
import lightning.product.z_1753_f;
import lightning.product.z_2376_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class j_419_j {
    public static final Codec<j_419_j> n_1700_B = RecordCodecBuilder.create(p_236214_0_ -> p_236214_0_.group((App)Codec.LONG.fieldOf("seed").stable().forGetter(j_419_j::n_1700_B), (App)Codec.BOOL.fieldOf("generate_features").orElse((Object)true).stable().forGetter(j_419_j::J_1907_R), (App)Codec.BOOL.fieldOf("bonus_chest").orElse((Object)false).stable().forGetter(j_419_j::R_4764_Y), (App)v_1758_J.J_1907_R(V_3137_a.v_4276_D, Lifecycle.stable(), LevelStem.n_1700_B).xmap(LevelStem::n_1700_B, Function.identity()).fieldOf("dimensions").forGetter(j_419_j::G_564_y), (App)Codec.STRING.optionalFieldOf("legacy_custom_options").stable().forGetter(p_236213_0_ -> p_236213_0_.v_4262_N)).apply((Applicative)p_236214_0_, p_236214_0_.stable(j_419_j::new))).comapFlatMap(j_419_j::P_4830_p, Function.identity());
    private static final Logger J_1907_R = LogManager.getLogger();
    private final long R_4764_Y;
    private final boolean G_564_y;
    private final boolean P_1922_E;
    private final v_1758_J<LevelStem> u_1723_Y;
    private final Optional<String> v_4262_N;

    private DataResult<j_419_j> P_4830_p() {
        LevelStem dimension = this.u_1723_Y.n_1700_B(LevelStem.J_1907_R);
        if (dimension == null) {
            return DataResult.error((String)"Overworld settings missing");
        }
        return this.h_1847_R() ? DataResult.success((Object)this, (Lifecycle)Lifecycle.stable()) : DataResult.success((Object)this);
    }

    private boolean h_1847_R() {
        return LevelStem.n_1700_B(this.R_4764_Y, this.u_1723_Y);
    }

    public j_419_j(long seed, boolean generateFeatures, boolean bonusChest, v_1758_J<LevelStem> p_i231914_5_) {
        this(seed, generateFeatures, bonusChest, p_i231914_5_, Optional.empty());
        LevelStem dimension = p_i231914_5_.n_1700_B(LevelStem.J_1907_R);
        if (dimension == null) {
            throw new IllegalStateException("Overworld settings missing");
        }
    }

    private j_419_j(long seed, boolean generateFeatures, boolean bonusChest, v_1758_J<LevelStem> p_i231915_5_, Optional<String> p_i231915_6_) {
        this.R_4764_Y = seed;
        this.G_564_y = generateFeatures;
        this.P_1922_E = bonusChest;
        this.u_1723_Y = p_i231915_5_;
        this.v_4262_N = p_i231915_6_;
    }

    public static j_419_j n_1700_B(r_4097_j p_242752_0_) {
        WritableRegistry<k_594_Q> registry = p_242752_0_.J_1907_R(V_3137_a.PlayerInfo);
        int i = "North Carolina".hashCode();
        WritableRegistry<Z_3903_F> registry1 = p_242752_0_.J_1907_R(V_3137_a.d_2427_y);
        WritableRegistry<G_156_T> registry2 = p_242752_0_.J_1907_R(V_3137_a.e_1992_r);
        return new j_419_j(i, true, true, j_419_j.n_1700_B(registry1, Z_3903_F.n_1700_B(registry1, registry, registry2, i), (z_1753_f)j_419_j.n_1700_B(registry, registry2, i)));
    }

    public static j_419_j n_1700_B(V_3137_a<Z_3903_F> p_242751_0_, V_3137_a<k_594_Q> p_242751_1_, V_3137_a<G_156_T> p_242751_2_) {
        long i = new Random().nextLong();
        return new j_419_j(i, true, false, j_419_j.n_1700_B(p_242751_0_, Z_3903_F.n_1700_B(p_242751_0_, p_242751_1_, p_242751_2_, i), (z_1753_f)j_419_j.n_1700_B(p_242751_1_, p_242751_2_, i)));
    }

    public static n_395_H n_1700_B(V_3137_a<k_594_Q> p_242750_0_, V_3137_a<G_156_T> p_242750_1_, long p_242750_2_) {
        return new n_395_H(new OverworldBiomeSource(p_242750_2_, false, false, p_242750_0_), p_242750_2_, () -> p_242750_1_.R_4764_Y(G_156_T.R_4764_Y));
    }

    public long n_1700_B() {
        return this.R_4764_Y;
    }

    public boolean J_1907_R() {
        return this.G_564_y;
    }

    public boolean R_4764_Y() {
        return this.P_1922_E;
    }

    public static v_1758_J<LevelStem> n_1700_B(V_3137_a<Z_3903_F> p_242749_0_, v_1758_J<LevelStem> p_242749_1_, z_1753_f p_242749_2_) {
        LevelStem dimension = p_242749_1_.n_1700_B(LevelStem.J_1907_R);
        Supplier<Z_3903_F> supplier = () -> dimension == null ? p_242749_0_.R_4764_Y(Z_3903_F.u_1723_Y) : dimension.J_1907_R();
        return j_419_j.n_1700_B(p_242749_1_, supplier, p_242749_2_);
    }

    public static v_1758_J<LevelStem> n_1700_B(v_1758_J<LevelStem> p_241520_0_, Supplier<Z_3903_F> p_241520_1_, z_1753_f p_241520_2_) {
        v_1758_J<LevelStem> simpleregistry = new v_1758_J<LevelStem>(V_3137_a.v_4276_D, Lifecycle.experimental());
        simpleregistry.n_1700_B(LevelStem.J_1907_R, new LevelStem(p_241520_1_, p_241520_2_), Lifecycle.stable());
        for (Map.Entry<f_2392_k<LevelStem>, LevelStem> entry : p_241520_0_.P_1922_E()) {
            f_2392_k<LevelStem> registrykey = entry.getKey();
            if (registrykey == LevelStem.J_1907_R) continue;
            simpleregistry.n_1700_B(registrykey, entry.getValue(), p_241520_0_.G_564_y(entry.getValue()));
        }
        return simpleregistry;
    }

    public v_1758_J<LevelStem> G_564_y() {
        return this.u_1723_Y;
    }

    public z_1753_f P_1922_E() {
        LevelStem dimension = this.u_1723_Y.n_1700_B(LevelStem.J_1907_R);
        if (dimension == null) {
            throw new IllegalStateException("Overworld settings missing");
        }
        return dimension.R_4764_Y();
    }

    public ImmutableSet<f_2392_k<b_4507_u>> u_1723_Y() {
        return (ImmutableSet)this.G_564_y().P_1922_E().stream().map(p_236218_0_ -> f_2392_k.n_1700_B(V_3137_a.z_1737_N, ((f_2392_k)p_236218_0_.getKey()).n_1700_B())).collect(ImmutableSet.toImmutableSet());
    }

    public boolean v_4262_N() {
        return this.P_1922_E() instanceof DebugLevelSource;
    }

    public boolean w_1484_f() {
        return this.P_1922_E() instanceof W_2121_d;
    }

    public boolean t_148_a() {
        return this.v_4262_N.isPresent();
    }

    public j_419_j s_956_w() {
        return new j_419_j(this.R_4764_Y, this.G_564_y, true, this.u_1723_Y, this.v_4262_N);
    }

    public j_419_j u_2550_I() {
        return new j_419_j(this.R_4764_Y, !this.G_564_y, this.P_1922_E, this.u_1723_Y);
    }

    public j_419_j M_588_G() {
        return new j_419_j(this.R_4764_Y, this.G_564_y, !this.P_1922_E, this.u_1723_Y);
    }

    public static j_419_j n_1700_B(r_4097_j p_242753_0_, Properties p_242753_1_) {
        String s = (String)MoreObjects.firstNonNull((Object)((String)p_242753_1_.get("generator-settings")), (Object)"");
        p_242753_1_.put("generator-settings", s);
        String s1 = (String)MoreObjects.firstNonNull((Object)((String)p_242753_1_.get("level-seed")), (Object)"");
        p_242753_1_.put("level-seed", s1);
        String s2 = (String)p_242753_1_.get("generate-structures");
        boolean flag = s2 == null || Boolean.parseBoolean(s2);
        p_242753_1_.put("generate-structures", Objects.toString(flag));
        String s3 = (String)p_242753_1_.get("level-type");
        String s4 = Optional.ofNullable(s3).map(p_236217_0_ -> p_236217_0_.toLowerCase(Locale.ROOT)).orElse("default");
        p_242753_1_.put("level-type", s4);
        long i = new Random().nextLong();
        if (!s1.isEmpty()) {
            try {
                long j = Long.parseLong(s1);
                if (j != 0L) {
                    i = j;
                }
            }
            catch (NumberFormatException numberformatexception) {
                i = s1.hashCode();
            }
        }
        WritableRegistry<Z_3903_F> registry2 = p_242753_0_.J_1907_R(V_3137_a.d_2427_y);
        WritableRegistry<k_594_Q> registry = p_242753_0_.J_1907_R(V_3137_a.PlayerInfo);
        WritableRegistry<G_156_T> registry1 = p_242753_0_.J_1907_R(V_3137_a.e_1992_r);
        v_1758_J<LevelStem> simpleregistry = Z_3903_F.n_1700_B(registry2, registry, registry1, i);
        int b0 = -1;
        switch (s4.hashCode()) {
            case -1100099890: {
                if (!s4.equals("largebiomes")) break;
                b0 = 3;
                break;
            }
            case 3145593: {
                if (!s4.equals("flat")) break;
                b0 = 0;
                break;
            }
            case 1045526590: {
                if (!s4.equals("debug_all_block_states")) break;
                b0 = 1;
                break;
            }
            case 1271599715: {
                if (!s4.equals("amplified")) break;
                b0 = 2;
            }
        }
        switch (b0) {
            case 0: {
                JsonObject jsonobject = !s.isEmpty() ? i_4431_W.n_1700_B(s) : new JsonObject();
                Dynamic dynamic = new Dynamic((DynamicOps)JsonOps.INSTANCE, (Object)jsonobject);
                return new j_419_j(i, flag, false, j_419_j.n_1700_B(registry2, simpleregistry, (z_1753_f)new W_2121_d(z_2376_a.n_1700_B.parse(dynamic).resultOrPartial(arg_0 -> ((Logger)J_1907_R).error(arg_0)).orElseGet(() -> z_2376_a.n_1700_B(registry)))));
            }
            case 1: {
                return new j_419_j(i, flag, false, j_419_j.n_1700_B(registry2, simpleregistry, (z_1753_f)new DebugLevelSource(registry)));
            }
            case 2: {
                return new j_419_j(i, flag, false, j_419_j.n_1700_B(registry2, simpleregistry, (z_1753_f)new n_395_H(new OverworldBiomeSource(i, false, false, registry), i, () -> registry1.R_4764_Y(G_156_T.G_564_y))));
            }
            case 3: {
                return new j_419_j(i, flag, false, j_419_j.n_1700_B(registry2, simpleregistry, (z_1753_f)new n_395_H(new OverworldBiomeSource(i, false, true, registry), i, () -> registry1.R_4764_Y(G_156_T.R_4764_Y))));
            }
        }
        return new j_419_j(i, flag, false, j_419_j.n_1700_B(registry2, simpleregistry, (z_1753_f)j_419_j.n_1700_B(registry, registry1, i)));
    }

    public j_419_j n_1700_B(boolean hardcore, OptionalLong worldSeed) {
        v_1758_J<LevelStem> simpleregistry;
        long i = worldSeed.orElse(this.R_4764_Y);
        if (worldSeed.isPresent()) {
            simpleregistry = new v_1758_J<LevelStem>(V_3137_a.v_4276_D, Lifecycle.experimental());
            long j = worldSeed.getAsLong();
            for (Map.Entry<f_2392_k<LevelStem>, LevelStem> entry : this.u_1723_Y.P_1922_E()) {
                f_2392_k<LevelStem> registrykey = entry.getKey();
                simpleregistry.n_1700_B(registrykey, new LevelStem(entry.getValue().n_1700_B(), entry.getValue().R_4764_Y().n_1700_B(j)), this.u_1723_Y.G_564_y(entry.getValue()));
            }
        } else {
            simpleregistry = this.u_1723_Y;
        }
        j_419_j dimensiongeneratorsettings = this.v_4262_N() ? new j_419_j(i, false, false, simpleregistry) : new j_419_j(i, this.J_1907_R(), this.R_4764_Y() && !hardcore, simpleregistry);
        return dimensiongeneratorsettings;
    }
}


