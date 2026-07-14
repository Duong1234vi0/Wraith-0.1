/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.datafixers.util.Either
 *  com.mojang.datafixers.util.Function3
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  it.unimi.dsi.fastutil.doubles.DoubleArrayList
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import lightning.product.NormalNoise;
import lightning.product.P_1103_o;
import lightning.product.biomeBiomes;
import lightning.product.V_3137_a;
import lightning.product.Biomes;
import lightning.product.WorldgenRandom;
import lightning.product.g_2336_b;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;

public class n_880_h
extends BiomeSource {
    private static final J_1907_R v_4262_N = new J_1907_R(-7, (List<Double>)ImmutableList.of((Object)1.0, (Object)1.0));
    public static final MapCodec<n_880_h> P_1922_E = RecordCodecBuilder.mapCodec(builder -> builder.group((App)Codec.LONG.fieldOf("seed").forGetter(netherProvider -> netherProvider.multiplayerClientSuggestionProvider), (App)RecordCodecBuilder.create(biomeAttributes -> biomeAttributes.group((App)k_594_Q.n_1700_B.n_1700_B.fieldOf("parameters").forGetter(Pair::getFirst), (App)k_594_Q.G_564_y.fieldOf("biome").forGetter(Pair::getSecond)).apply((Applicative)biomeAttributes, Pair::of)).listOf().fieldOf("biomes").forGetter(netherProvider -> netherProvider.M_182_A), (App)lightning.product.n_880_h$J_1907_R.n_1700_B.fieldOf("temperature_noise").forGetter(netherProvider -> netherProvider.w_1484_f), (App)lightning.product.n_880_h$J_1907_R.n_1700_B.fieldOf("humidity_noise").forGetter(netherProvider -> netherProvider.t_148_a), (App)lightning.product.n_880_h$J_1907_R.n_1700_B.fieldOf("altitude_noise").forGetter(netherProvider -> netherProvider.s_956_w), (App)lightning.product.n_880_h$J_1907_R.n_1700_B.fieldOf("weirdness_noise").forGetter(netherProvider -> netherProvider.u_2550_I)).apply((Applicative)builder, n_880_h::new));
    public static final Codec<n_880_h> u_1723_Y = Codec.mapEither(lightning.product.n_880_h$n_1700_B.n_1700_B, P_1922_E).xmap(either -> (n_880_h)either.map(n_1700_B::G_564_y, Function.identity()), netherProvider -> netherProvider.G_564_y().map(Either::left).orElseGet(() -> Either.right((Object)netherProvider))).codec();
    private final J_1907_R w_1484_f;
    private final J_1907_R t_148_a;
    private final J_1907_R s_956_w;
    private final J_1907_R u_2550_I;
    private final NormalNoise M_588_G;
    private final NormalNoise P_4830_p;
    private final NormalNoise h_1847_R;
    private final NormalNoise Q_4569_t;
    private final List<Pair<k_594_Q.n_1700_B, Supplier<k_594_Q>>> M_182_A;
    private final boolean t_1786_h;
    private final long multiplayerClientSuggestionProvider;
    private final Optional<Pair<V_3137_a<k_594_Q>, R_4764_Y>> w_1457_N;

    private n_880_h(long seed, List<Pair<k_594_Q.n_1700_B, Supplier<k_594_Q>>> biomeAttributes, Optional<Pair<V_3137_a<k_594_Q>, R_4764_Y>> netherProviderPreset) {
        this(seed, biomeAttributes, v_4262_N, v_4262_N, v_4262_N, v_4262_N, netherProviderPreset);
    }

    private n_880_h(long seed, List<Pair<k_594_Q.n_1700_B, Supplier<k_594_Q>>> biomeAttributes, J_1907_R temperatureNoise, J_1907_R humidityNoise, J_1907_R altitudeNoise, J_1907_R weirdnessNoise) {
        this(seed, biomeAttributes, temperatureNoise, humidityNoise, altitudeNoise, weirdnessNoise, Optional.empty());
    }

    private n_880_h(long seed, List<Pair<k_594_Q.n_1700_B, Supplier<k_594_Q>>> biomeAttributes, J_1907_R temperatureNoise, J_1907_R humidityNoise, J_1907_R altitudeNoise, J_1907_R weirdnessNoise, Optional<Pair<V_3137_a<k_594_Q>, R_4764_Y>> netherProviderPreset) {
        super(biomeAttributes.stream().map(Pair::getSecond));
        this.multiplayerClientSuggestionProvider = seed;
        this.w_1457_N = netherProviderPreset;
        this.w_1484_f = temperatureNoise;
        this.t_148_a = humidityNoise;
        this.s_956_w = altitudeNoise;
        this.u_2550_I = weirdnessNoise;
        this.M_588_G = NormalNoise.n_1700_B(new WorldgenRandom(seed), temperatureNoise.n_1700_B(), temperatureNoise.J_1907_R());
        this.P_4830_p = NormalNoise.n_1700_B(new WorldgenRandom(seed + 1L), humidityNoise.n_1700_B(), humidityNoise.J_1907_R());
        this.h_1847_R = NormalNoise.n_1700_B(new WorldgenRandom(seed + 2L), altitudeNoise.n_1700_B(), altitudeNoise.J_1907_R());
        this.Q_4569_t = NormalNoise.n_1700_B(new WorldgenRandom(seed + 3L), weirdnessNoise.n_1700_B(), weirdnessNoise.J_1907_R());
        this.M_182_A = biomeAttributes;
        this.t_1786_h = false;
    }

    @Override
    protected Codec<? extends BiomeSource> n_1700_B() {
        return u_1723_Y;
    }

    @Override
    public BiomeSource n_1700_B(long seed) {
        return new n_880_h(seed, this.M_182_A, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.w_1457_N);
    }

    private Optional<n_1700_B> G_564_y() {
        return this.w_1457_N.map(registryPresetPair -> new n_1700_B((R_4764_Y)registryPresetPair.getSecond(), (V_3137_a)registryPresetPair.getFirst(), this.multiplayerClientSuggestionProvider));
    }

    @Override
    public k_594_Q G_564_y(int x, int y, int z) {
        int i = this.t_1786_h ? y : 0;
        k_594_Q.n_1700_B biome$attributes = new k_594_Q.n_1700_B((float)this.M_588_G.n_1700_B(x, i, z), (float)this.P_4830_p.n_1700_B(x, i, z), (float)this.h_1847_R.n_1700_B(x, i, z), (float)this.Q_4569_t.n_1700_B(x, i, z), 0.0f);
        return this.M_182_A.stream().min(Comparator.comparing(attributeBiomePair -> Float.valueOf(((k_594_Q.n_1700_B)attributeBiomePair.getFirst()).n_1700_B(biome$attributes)))).map(Pair::getSecond).map(Supplier::get).orElse(Biomes.J_1907_R);
    }

    public boolean J_1907_R(long seed) {
        return this.multiplayerClientSuggestionProvider == seed && this.w_1457_N.isPresent() && Objects.equals(this.w_1457_N.get().getSecond(), lightning.product.n_880_h$R_4764_Y.n_1700_B);
    }

    static class J_1907_R {
        private final int J_1907_R;
        private final DoubleList R_4764_Y;
        public static final Codec<J_1907_R> n_1700_B = RecordCodecBuilder.create(builder -> builder.group((App)Codec.INT.fieldOf("firstOctave").forGetter(J_1907_R::n_1700_B), (App)Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(J_1907_R::J_1907_R)).apply((Applicative)builder, J_1907_R::new));

        public J_1907_R(int numOctaves, List<Double> amplitudes) {
            this.J_1907_R = numOctaves;
            this.R_4764_Y = new DoubleArrayList(amplitudes);
        }

        public int n_1700_B() {
            return this.J_1907_R;
        }

        public DoubleList J_1907_R() {
            return this.R_4764_Y;
        }
    }

    public static class R_4764_Y {
        private static final Map<g_2336_b, R_4764_Y> J_1907_R = Maps.newHashMap();
        public static final R_4764_Y n_1700_B = new R_4764_Y(new g_2336_b("nether"), (Function3<R_4764_Y, V_3137_a<k_594_Q>, Long, n_880_h>)((Function3)(preset, lookupRegistry, seed) -> new n_880_h((long)seed, (List<Pair<k_594_Q.n_1700_B, Supplier<k_594_Q>>>)ImmutableList.of((Object)Pair.of((Object)new k_594_Q.n_1700_B(0.0f, 0.0f, 0.0f, 0.0f, 0.0f), () -> lookupRegistry.R_4764_Y(biomeBiomes.t_148_a)), (Object)Pair.of((Object)new k_594_Q.n_1700_B(0.0f, -0.5f, 0.0f, 0.0f, 0.0f), () -> lookupRegistry.R_4764_Y(biomeBiomes.V_1225_t)), (Object)Pair.of((Object)new k_594_Q.n_1700_B(0.4f, 0.0f, 0.0f, 0.0f, 0.0f), () -> lookupRegistry.R_4764_Y(biomeBiomes.U_1241_n)), (Object)Pair.of((Object)new k_594_Q.n_1700_B(0.0f, 0.5f, 0.0f, 0.0f, 0.375f), () -> lookupRegistry.R_4764_Y(biomeBiomes.q_1982_R)), (Object)Pair.of((Object)new k_594_Q.n_1700_B(-0.5f, 0.0f, 0.0f, 0.0f, 0.175f), () -> lookupRegistry.R_4764_Y(biomeBiomes.dtoRealmsServerAddress))), Optional.of(Pair.of((Object)lookupRegistry, (Object)preset)))));
        private final g_2336_b R_4764_Y;
        private final Function3<R_4764_Y, V_3137_a<k_594_Q>, Long, n_880_h> G_564_y;

        public R_4764_Y(g_2336_b id, Function3<R_4764_Y, V_3137_a<k_594_Q>, Long, n_880_h> netherProviderFunction) {
            this.R_4764_Y = id;
            this.G_564_y = netherProviderFunction;
            J_1907_R.put(id, this);
        }

        public n_880_h n_1700_B(V_3137_a<k_594_Q> lookupRegistry, long seed) {
            return (n_880_h)this.G_564_y.apply((Object)this, lookupRegistry, (Object)seed);
        }
    }

    static final class n_1700_B {
        public static final MapCodec<n_1700_B> n_1700_B = RecordCodecBuilder.mapCodec(builder -> builder.group((App)g_2336_b.n_1700_B.flatXmap(id -> Optional.ofNullable(lightning.product.n_880_h$R_4764_Y.J_1907_R.get(id)).map(DataResult::success).orElseGet(() -> DataResult.error((String)("Unknown preset: " + String.valueOf(id)))), preset -> DataResult.success((Object)preset.R_4764_Y)).fieldOf("preset").stable().forGetter(n_1700_B::n_1700_B), (App)P_1103_o.n_1700_B(V_3137_a.PlayerInfo).forGetter(n_1700_B::J_1907_R), (App)Codec.LONG.fieldOf("seed").stable().forGetter(n_1700_B::R_4764_Y)).apply((Applicative)builder, builder.stable(n_1700_B::new)));
        private final R_4764_Y J_1907_R;
        private final V_3137_a<k_594_Q> R_4764_Y;
        private final long G_564_y;

        private n_1700_B(R_4764_Y preset, V_3137_a<k_594_Q> lookupRegistry, long seed) {
            this.J_1907_R = preset;
            this.R_4764_Y = lookupRegistry;
            this.G_564_y = seed;
        }

        public R_4764_Y n_1700_B() {
            return this.J_1907_R;
        }

        public V_3137_a<k_594_Q> J_1907_R() {
            return this.R_4764_Y;
        }

        public long R_4764_Y() {
            return this.G_564_y;
        }

        public n_880_h G_564_y() {
            return this.J_1907_R.n_1700_B(this.R_4764_Y, this.G_564_y);
        }
    }
}


