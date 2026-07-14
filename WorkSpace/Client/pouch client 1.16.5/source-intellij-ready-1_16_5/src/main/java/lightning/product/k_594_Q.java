/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.AmbientParticleSettings;
import lightning.product.E_4700_p;
import lightning.product.AmbientMoodSettings;
import lightning.product.StructureFeature;
import lightning.product.FoliageColor;
import lightning.product.Fluids;
import lightning.product.PerlinSimplexNoise;
import lightning.product.J_3017_d;
import lightning.product.K_3381_i;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.BoundingBox;
import lightning.product.GrassColor;
import lightning.product.T_1316_M;
import lightning.product.T_3975_o;
import lightning.product.V_3137_a;
import lightning.product.BuiltinRegistries;
import lightning.product.SoundEvent;
import lightning.product.MobSpawnSettings;
import lightning.product.Y_1387_d;
import lightning.product.a_3742_W;
import lightning.product.ConfiguredFeature;
import lightning.product.Music;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SectionPos;
import lightning.product.BiomeGenerationSettings;
import lightning.product.ChunkAccess;
import lightning.product.ConfiguredSurfaceBuilder;
import lightning.product.WorldgenRandom;
import lightning.product.g_2336_b;
import lightning.product.Feature;
import lightning.product.AmbientAdditionsSettings;
import lightning.product.j_3341_s;
import lightning.product.BiomeSpecialEffects;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.n_4684_C;
import lightning.product.s_3834_w;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class k_594_Q {
    public static final Logger n_1700_B = LogManager.getLogger();
    public static final Codec<k_594_Q> J_1907_R = RecordCodecBuilder.create(builder -> builder.group((App)lightning.product.k_594_Q$G_564_y.n_1700_B.forGetter(biome -> biome.s_956_w), (App)lightning.product.k_594_Q$R_4764_Y.multiplayerClientSuggestionProvider.fieldOf("category").forGetter(biome -> biome.Q_4569_t), (App)Codec.FLOAT.fieldOf("depth").forGetter(biome -> Float.valueOf(biome.P_4830_p)), (App)Codec.FLOAT.fieldOf("scale").forGetter(biome -> Float.valueOf(biome.h_1847_R)), (App)BiomeSpecialEffects.n_1700_B.fieldOf("effects").forGetter(biome -> biome.M_182_A), (App)BiomeGenerationSettings.R_4764_Y.forGetter(biome -> biome.u_2550_I), (App)MobSpawnSettings.R_4764_Y.forGetter(biome -> biome.M_588_G)).apply((Applicative)builder, k_594_Q::new));
    public static final Codec<k_594_Q> R_4764_Y = RecordCodecBuilder.create(builder -> builder.group((App)lightning.product.k_594_Q$G_564_y.n_1700_B.forGetter(biome -> biome.s_956_w), (App)lightning.product.k_594_Q$R_4764_Y.multiplayerClientSuggestionProvider.fieldOf("category").forGetter(biome -> biome.Q_4569_t), (App)Codec.FLOAT.fieldOf("depth").forGetter(biome -> Float.valueOf(biome.P_4830_p)), (App)Codec.FLOAT.fieldOf("scale").forGetter(biome -> Float.valueOf(biome.h_1847_R)), (App)BiomeSpecialEffects.n_1700_B.fieldOf("effects").forGetter(biome -> biome.M_182_A)).apply((Applicative)builder, (climate, category, depth, scale, ambience) -> new k_594_Q((G_564_y)climate, (R_4764_Y)category, depth.floatValue(), scale.floatValue(), (BiomeSpecialEffects)ambience, BiomeGenerationSettings.J_1907_R, MobSpawnSettings.J_1907_R)));
    public static final Codec<Supplier<k_594_Q>> G_564_y = n_4684_C.n_1700_B(V_3137_a.PlayerInfo, J_1907_R);
    public static final Codec<List<Supplier<k_594_Q>>> P_1922_E = n_4684_C.J_1907_R(V_3137_a.PlayerInfo, J_1907_R);
    private final Map<Integer, List<StructureFeature<?>>> v_4262_N = V_3137_a.M_1641_O.u_1723_Y().collect(Collectors.groupingBy(structure -> structure.G_564_y().ordinal()));
    private static final PerlinSimplexNoise w_1484_f = new PerlinSimplexNoise(new WorldgenRandom(1234L), (List<Integer>)ImmutableList.of((Object)0));
    private static final PerlinSimplexNoise t_148_a = new PerlinSimplexNoise(new WorldgenRandom(3456L), (List<Integer>)ImmutableList.of((Object)-2, (Object)-1, (Object)0));
    public static final PerlinSimplexNoise u_1723_Y = new PerlinSimplexNoise(new WorldgenRandom(2345L), (List<Integer>)ImmutableList.of((Object)0));
    private final G_564_y s_956_w;
    private final BiomeGenerationSettings u_2550_I;
    private final MobSpawnSettings M_588_G;
    private final float P_4830_p;
    private final float h_1847_R;
    private final R_4764_Y Q_4569_t;
    private final BiomeSpecialEffects M_182_A;
    private final ThreadLocal<Long2FloatLinkedOpenHashMap> t_1786_h = ThreadLocal.withInitial(() -> j_3341_s.n_1700_B(() -> {
        Long2FloatLinkedOpenHashMap long2floatlinkedopenhashmap = new Long2FloatLinkedOpenHashMap(1024, 0.25f){

            protected void rehash(int p_rehash_1_) {
            }
        };
        long2floatlinkedopenhashmap.defaultReturnValue(Float.NaN);
        return long2floatlinkedopenhashmap;
    }));

    private k_594_Q(G_564_y climate, R_4764_Y category, float depth, float scale, BiomeSpecialEffects effects, BiomeGenerationSettings biomeGenerationSettings, MobSpawnSettings mobSpawnInfo) {
        this.s_956_w = climate;
        this.u_2550_I = biomeGenerationSettings;
        this.M_588_G = mobSpawnInfo;
        this.Q_4569_t = category;
        this.P_4830_p = depth;
        this.h_1847_R = scale;
        this.M_182_A = effects;
    }

    public int n_1700_B() {
        return this.M_182_A.G_564_y();
    }

    public MobSpawnSettings J_1907_R() {
        return this.M_588_G;
    }

    public P_1922_E R_4764_Y() {
        return this.s_956_w.J_1907_R;
    }

    public boolean G_564_y() {
        return this.t_148_a() > 0.85f;
    }

    private float J_1907_R(c_1514_x pos) {
        float f = this.s_956_w.G_564_y.n_1700_B(pos, this.u_2550_I());
        if (pos.getY() > 64) {
            float f1 = (float)(w_1484_f.n_1700_B((float)pos.getX() / 8.0f, (float)pos.getZ() / 8.0f, false) * 4.0);
            return f - (f1 + (float)pos.getY() - 64.0f) * 0.05f / 30.0f;
        }
        return f;
    }

    public final float n_1700_B(c_1514_x pos) {
        long i = pos.toLong();
        Long2FloatLinkedOpenHashMap long2floatlinkedopenhashmap = this.t_1786_h.get();
        float f = long2floatlinkedopenhashmap.get(i);
        if (!Float.isNaN(f)) {
            return f;
        }
        float f1 = this.J_1907_R(pos);
        if (long2floatlinkedopenhashmap.size() == 1024) {
            long2floatlinkedopenhashmap.removeFirstFloat();
        }
        long2floatlinkedopenhashmap.put(i, f1);
        return f1;
    }

    public boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
        return this.n_1700_B(worldIn, pos, true);
    }

    public boolean n_1700_B(T_1316_M worldIn, c_1514_x water, boolean mustBeAtEdge) {
        if (this.n_1700_B(water) >= 0.15f) {
            return false;
        }
        if (water.getY() >= 0 && water.getY() < 256 && worldIn.getLightFor(K_4719_o.J_1907_R, water) < 10) {
            K_4074_S blockstate = worldIn.getBlockState(water);
            FluidState fluidstate = worldIn.getFluidState(water);
            if (fluidstate.n_1700_B() == Fluids.R_4764_Y && blockstate.J_1907_R() instanceof s_3834_w) {
                boolean flag;
                if (!mustBeAtEdge) {
                    return true;
                }
                boolean bl = flag = worldIn.s_956_w(water.west()) && worldIn.s_956_w(water.east()) && worldIn.s_956_w(water.north()) && worldIn.s_956_w(water.south());
                if (!flag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean J_1907_R(T_1316_M worldIn, c_1514_x pos) {
        K_4074_S blockstate;
        if (this.n_1700_B(pos) >= 0.15f) {
            return false;
        }
        return pos.getY() >= 0 && pos.getY() < 256 && worldIn.getLightFor(K_4719_o.J_1907_R, pos) < 10 && (blockstate = worldIn.getBlockState(pos)).v_4262_N() && a_3742_W.X_290_I.multiplayerClientSuggestionProvider().n_1700_B(worldIn, pos);
    }

    public BiomeGenerationSettings P_1922_E() {
        return this.u_2550_I;
    }

    public void n_1700_B(J_3017_d structureManager, z_1753_f chunkGenerator, K_3381_i worldGenRegion, long seed, WorldgenRandom rand, c_1514_x pos) {
        List<List<Supplier<ConfiguredFeature<?, ?>>>> list = this.u_2550_I.R_4764_Y();
        int i = T_3975_o.J_1907_R.values().length;
        for (int j = 0; j < i; ++j) {
            int k = 0;
            if (structureManager.n_1700_B()) {
                for (StructureFeature f_661_m : this.v_4262_N.getOrDefault(j, Collections.emptyList())) {
                    rand.J_1907_R(seed, k, j);
                    int l = pos.getX() >> 4;
                    int i1 = pos.getZ() >> 4;
                    int j1 = l << 4;
                    int k1 = i1 << 4;
                    try {
                        structureManager.n_1700_B(SectionPos.n_1700_B(pos), f_661_m).forEach(structureStart -> structureStart.n_1700_B(worldGenRegion, structureManager, chunkGenerator, rand, new BoundingBox(j1, k1, j1 + 15, k1 + 15), new Y_1387_d(l, i1)));
                    }
                    catch (Exception exception) {
                        n_3236_c crashreport = n_3236_c.n_1700_B(exception, "Feature placement");
                        crashreport.n_1700_B("Feature").n_1700_B("Id", V_3137_a.M_1641_O.J_1907_R(f_661_m)).n_1700_B("Description", () -> structure.toString());
                        throw new ReportedException(crashreport);
                    }
                    ++k;
                }
            }
            if (list.size() <= j) continue;
            for (Supplier supplier : list.get(j)) {
                ConfiguredFeature configuredfeature = (ConfiguredFeature)supplier.get();
                rand.J_1907_R(seed, k, j);
                try {
                    configuredfeature.n_1700_B(worldGenRegion, chunkGenerator, rand, pos);
                }
                catch (Exception exception1) {
                    n_3236_c crashreport1 = n_3236_c.n_1700_B(exception1, "Feature placement");
                    crashreport1.n_1700_B("Feature").n_1700_B("Id", V_3137_a.RealmsServerPing.J_1907_R((Feature<?>)configuredfeature.P_1922_E)).n_1700_B("ClientConfig", configuredfeature.u_1723_Y).n_1700_B("Description", () -> configuredfeature.P_1922_E.toString());
                    throw new ReportedException(crashreport1);
                }
                ++k;
            }
        }
    }

    public int u_1723_Y() {
        return this.M_182_A.n_1700_B();
    }

    public int n_1700_B(double posX, double posZ) {
        int i = this.M_182_A.u_1723_Y().orElseGet(this::Y_259_p);
        return this.M_182_A.v_4262_N().n_1700_B(posX, posZ, i);
    }

    private int Y_259_p() {
        double d0 = u_530_F.n_1700_B(this.s_956_w.R_4764_Y, 0.0f, 1.0f);
        double d1 = u_530_F.n_1700_B(this.s_956_w.P_1922_E, 0.0f, 1.0f);
        return GrassColor.n_1700_B(d0, d1);
    }

    public int v_4262_N() {
        return this.M_182_A.P_1922_E().orElseGet(this::Q_2552_b);
    }

    private int Q_2552_b() {
        double d0 = u_530_F.n_1700_B(this.s_956_w.R_4764_Y, 0.0f, 1.0f);
        double d1 = u_530_F.n_1700_B(this.s_956_w.P_1922_E, 0.0f, 1.0f);
        return FoliageColor.n_1700_B(d0, d1);
    }

    public void n_1700_B(Random random, ChunkAccess chunkIn, int x, int z, int startHeight, double noise, K_4074_S defaultBlock, K_4074_S defaultFluid, int seaLevel, long seed) {
        ConfiguredSurfaceBuilder<?> configuredsurfacebuilder = this.u_2550_I.G_564_y().get();
        configuredsurfacebuilder.n_1700_B(seed);
        configuredsurfacebuilder.n_1700_B(random, chunkIn, this, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed);
    }

    public final float w_1484_f() {
        return this.P_4830_p;
    }

    public final float t_148_a() {
        return this.s_956_w.P_1922_E;
    }

    public final float s_956_w() {
        return this.h_1847_R;
    }

    public final float u_2550_I() {
        return this.s_956_w.R_4764_Y;
    }

    public BiomeSpecialEffects M_588_G() {
        return this.M_182_A;
    }

    public final int P_4830_p() {
        return this.M_182_A.J_1907_R();
    }

    public final int h_1847_R() {
        return this.M_182_A.R_4764_Y();
    }

    public Optional<AmbientParticleSettings> Q_4569_t() {
        return this.M_182_A.w_1484_f();
    }

    public Optional<SoundEvent> M_182_A() {
        return this.M_182_A.t_148_a();
    }

    public Optional<AmbientMoodSettings> t_1786_h() {
        return this.M_182_A.s_956_w();
    }

    public Optional<AmbientAdditionsSettings> multiplayerClientSuggestionProvider() {
        return this.M_182_A.u_2550_I();
    }

    public Optional<Music> w_1457_N() {
        return this.M_182_A.M_588_G();
    }

    public final R_4764_Y Y_601_j() {
        return this.Q_4569_t;
    }

    public String toString() {
        g_2336_b resourcelocation = BuiltinRegistries.t_148_a.J_1907_R(this);
        return resourcelocation == null ? super.toString() : resourcelocation.toString();
    }

    static class G_564_y {
        public static final MapCodec<G_564_y> n_1700_B = RecordCodecBuilder.mapCodec(builder -> builder.group((App)lightning.product.k_594_Q$P_1922_E.G_564_y.fieldOf("precipitation").forGetter(precipitation -> precipitation.J_1907_R), (App)Codec.FLOAT.fieldOf("temperature").forGetter(climate -> Float.valueOf(climate.R_4764_Y)), (App)lightning.product.k_594_Q$u_1723_Y.R_4764_Y.optionalFieldOf("temperature_modifier", (Object)lightning.product.k_594_Q$u_1723_Y.n_1700_B).forGetter(climate -> climate.G_564_y), (App)Codec.FLOAT.fieldOf("downfall").forGetter(climate -> Float.valueOf(climate.P_1922_E))).apply((Applicative)builder, G_564_y::new));
        private final P_1922_E J_1907_R;
        private final float R_4764_Y;
        private final u_1723_Y G_564_y;
        private final float P_1922_E;

        private G_564_y(P_1922_E precipitation, float temperature, u_1723_Y temperatureModifier, float downfall) {
            this.J_1907_R = precipitation;
            this.R_4764_Y = temperature;
            this.G_564_y = temperatureModifier;
            this.P_1922_E = downfall;
        }
    }

    public static final class R_4764_Y
    extends Enum<R_4764_Y>
    implements E_4700_p {
        public static final /* enum */ R_4764_Y n_1700_B = new R_4764_Y("none");
        public static final /* enum */ R_4764_Y J_1907_R = new R_4764_Y("taiga");
        public static final /* enum */ R_4764_Y R_4764_Y = new R_4764_Y("extreme_hills");
        public static final /* enum */ R_4764_Y G_564_y = new R_4764_Y("jungle");
        public static final /* enum */ R_4764_Y P_1922_E = new R_4764_Y("mesa");
        public static final /* enum */ R_4764_Y u_1723_Y = new R_4764_Y("plains");
        public static final /* enum */ R_4764_Y v_4262_N = new R_4764_Y("savanna");
        public static final /* enum */ R_4764_Y w_1484_f = new R_4764_Y("icy");
        public static final /* enum */ R_4764_Y t_148_a = new R_4764_Y("the_end");
        public static final /* enum */ R_4764_Y s_956_w = new R_4764_Y("beach");
        public static final /* enum */ R_4764_Y u_2550_I = new R_4764_Y("forest");
        public static final /* enum */ R_4764_Y M_588_G = new R_4764_Y("ocean");
        public static final /* enum */ R_4764_Y P_4830_p = new R_4764_Y("desert");
        public static final /* enum */ R_4764_Y h_1847_R = new R_4764_Y("river");
        public static final /* enum */ R_4764_Y Q_4569_t = new R_4764_Y("swamp");
        public static final /* enum */ R_4764_Y M_182_A = new R_4764_Y("mushroom");
        public static final /* enum */ R_4764_Y t_1786_h = new R_4764_Y("nether");
        public static final Codec<R_4764_Y> multiplayerClientSuggestionProvider;
        private static final Map<String, R_4764_Y> w_1457_N;
        private final String Y_601_j;
        private static final /* synthetic */ R_4764_Y[] Y_259_p;

        public static R_4764_Y[] values() {
            return (R_4764_Y[])Y_259_p.clone();
        }

        public static R_4764_Y valueOf(String name) {
            return Enum.valueOf(R_4764_Y.class, name);
        }

        private R_4764_Y(String name) {
            this.Y_601_j = name;
        }

        public String J_1907_R() {
            return this.Y_601_j;
        }

        public static R_4764_Y n_1700_B(String name) {
            return w_1457_N.get(name);
        }

        @Override
        public String n_1700_B() {
            return this.Y_601_j;
        }

        private static /* synthetic */ R_4764_Y[] R_4764_Y() {
            return new R_4764_Y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A, t_1786_h};
        }

        static {
            Y_259_p = lightning.product.k_594_Q$R_4764_Y.R_4764_Y();
            multiplayerClientSuggestionProvider = E_4700_p.n_1700_B(R_4764_Y::values, R_4764_Y::n_1700_B);
            w_1457_N = Arrays.stream(lightning.product.k_594_Q$R_4764_Y.values()).collect(Collectors.toMap(R_4764_Y::J_1907_R, category -> category));
        }
    }

    public static final class P_1922_E
    extends Enum<P_1922_E>
    implements E_4700_p {
        public static final /* enum */ P_1922_E n_1700_B = new P_1922_E("none");
        public static final /* enum */ P_1922_E J_1907_R = new P_1922_E("rain");
        public static final /* enum */ P_1922_E R_4764_Y = new P_1922_E("snow");
        public static final Codec<P_1922_E> G_564_y;
        private static final Map<String, P_1922_E> P_1922_E;
        private final String u_1723_Y;
        private static final /* synthetic */ P_1922_E[] v_4262_N;

        public static P_1922_E[] values() {
            return (P_1922_E[])v_4262_N.clone();
        }

        public static P_1922_E valueOf(String name) {
            return Enum.valueOf(P_1922_E.class, name);
        }

        private P_1922_E(String name) {
            this.u_1723_Y = name;
        }

        public String J_1907_R() {
            return this.u_1723_Y;
        }

        public static P_1922_E n_1700_B(String name) {
            return P_1922_E.get(name);
        }

        @Override
        public String n_1700_B() {
            return this.u_1723_Y;
        }

        private static /* synthetic */ P_1922_E[] R_4764_Y() {
            return new P_1922_E[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            v_4262_N = lightning.product.k_594_Q$P_1922_E.R_4764_Y();
            G_564_y = E_4700_p.n_1700_B(P_1922_E::values, P_1922_E::n_1700_B);
            P_1922_E = Arrays.stream(lightning.product.k_594_Q$P_1922_E.values()).collect(Collectors.toMap(P_1922_E::J_1907_R, precipitation -> precipitation));
        }
    }

    public static abstract sealed class u_1723_Y
    extends Enum<u_1723_Y>
    implements E_4700_p {
        public static final /* enum */ u_1723_Y n_1700_B = new u_1723_Y("none"){

            @Override
            public float n_1700_B(c_1514_x pos, float temperature) {
                return temperature;
            }
        };
        public static final /* enum */ u_1723_Y J_1907_R = new u_1723_Y("frozen"){

            @Override
            public float n_1700_B(c_1514_x pos, float temperature) {
                double d3;
                double d1;
                double d0 = t_148_a.n_1700_B((double)pos.getX() * 0.05, (double)pos.getZ() * 0.05, false) * 7.0;
                double d2 = d0 + (d1 = k_594_Q.u_1723_Y.n_1700_B((double)pos.getX() * 0.2, (double)pos.getZ() * 0.2, false));
                if (d2 < 0.3 && (d3 = k_594_Q.u_1723_Y.n_1700_B((double)pos.getX() * 0.09, (double)pos.getZ() * 0.09, false)) < 0.8) {
                    return 0.2f;
                }
                return temperature;
            }
        };
        private final String G_564_y;
        public static final Codec<u_1723_Y> R_4764_Y;
        private static final Map<String, u_1723_Y> P_1922_E;
        private static final /* synthetic */ u_1723_Y[] u_1723_Y;

        public static u_1723_Y[] values() {
            return (u_1723_Y[])u_1723_Y.clone();
        }

        public static u_1723_Y valueOf(String name) {
            return Enum.valueOf(u_1723_Y.class, name);
        }

        public abstract float n_1700_B(c_1514_x var1, float var2);

        private u_1723_Y(String name) {
            this.G_564_y = name;
        }

        public String J_1907_R() {
            return this.G_564_y;
        }

        @Override
        public String n_1700_B() {
            return this.G_564_y;
        }

        public static u_1723_Y n_1700_B(String name) {
            return P_1922_E.get(name);
        }

        private static /* synthetic */ u_1723_Y[] R_4764_Y() {
            return new u_1723_Y[]{n_1700_B, J_1907_R};
        }

        static {
            u_1723_Y = lightning.product.k_594_Q$u_1723_Y.R_4764_Y();
            R_4764_Y = E_4700_p.n_1700_B(u_1723_Y::values, u_1723_Y::n_1700_B);
            P_1922_E = Arrays.stream(lightning.product.k_594_Q$u_1723_Y.values()).collect(Collectors.toMap(u_1723_Y::J_1907_R, temperatureModifier -> temperatureModifier));
        }
    }

    public static class J_1907_R {
        @Nullable
        private P_1922_E n_1700_B;
        @Nullable
        private R_4764_Y J_1907_R;
        @Nullable
        private Float R_4764_Y;
        @Nullable
        private Float G_564_y;
        @Nullable
        private Float P_1922_E;
        private u_1723_Y u_1723_Y = lightning.product.k_594_Q$u_1723_Y.n_1700_B;
        @Nullable
        private Float v_4262_N;
        @Nullable
        private BiomeSpecialEffects w_1484_f;
        @Nullable
        private MobSpawnSettings t_148_a;
        @Nullable
        private BiomeGenerationSettings s_956_w;

        public J_1907_R n_1700_B(P_1922_E precipitationIn) {
            this.n_1700_B = precipitationIn;
            return this;
        }

        public J_1907_R n_1700_B(R_4764_Y biomeCategory) {
            this.J_1907_R = biomeCategory;
            return this;
        }

        public J_1907_R n_1700_B(float depthIn) {
            this.R_4764_Y = Float.valueOf(depthIn);
            return this;
        }

        public J_1907_R J_1907_R(float scaleIn) {
            this.G_564_y = Float.valueOf(scaleIn);
            return this;
        }

        public J_1907_R R_4764_Y(float temperatureIn) {
            this.P_1922_E = Float.valueOf(temperatureIn);
            return this;
        }

        public J_1907_R G_564_y(float downfallIn) {
            this.v_4262_N = Float.valueOf(downfallIn);
            return this;
        }

        public J_1907_R n_1700_B(BiomeSpecialEffects effects) {
            this.w_1484_f = effects;
            return this;
        }

        public J_1907_R n_1700_B(MobSpawnSettings mobSpawnSettings) {
            this.t_148_a = mobSpawnSettings;
            return this;
        }

        public J_1907_R n_1700_B(BiomeGenerationSettings generationSettings) {
            this.s_956_w = generationSettings;
            return this;
        }

        public J_1907_R n_1700_B(u_1723_Y temperatureSettings) {
            this.u_1723_Y = temperatureSettings;
            return this;
        }

        public k_594_Q n_1700_B() {
            if (this.n_1700_B != null && this.J_1907_R != null && this.R_4764_Y != null && this.G_564_y != null && this.P_1922_E != null && this.v_4262_N != null && this.w_1484_f != null && this.t_148_a != null && this.s_956_w != null) {
                return new k_594_Q(new G_564_y(this.n_1700_B, this.P_1922_E.floatValue(), this.u_1723_Y, this.v_4262_N.floatValue()), this.J_1907_R, this.R_4764_Y.floatValue(), this.G_564_y.floatValue(), this.w_1484_f, this.s_956_w, this.t_148_a);
            }
            throw new IllegalStateException("You are missing parameters to build a proper biome\n" + String.valueOf(this));
        }

        public String toString() {
            return "BiomeBuilder{\nprecipitation=" + String.valueOf(this.n_1700_B) + ",\nbiomeCategory=" + String.valueOf(this.J_1907_R) + ",\ndepth=" + this.R_4764_Y + ",\nscale=" + this.G_564_y + ",\ntemperature=" + this.P_1922_E + ",\ntemperatureModifier=" + String.valueOf(this.u_1723_Y) + ",\ndownfall=" + this.v_4262_N + ",\nspecialEffects=" + String.valueOf(this.w_1484_f) + ",\nmobSpawnSettings=" + String.valueOf(this.t_148_a) + ",\ngenerationSettings=" + String.valueOf(this.s_956_w) + ",\n}";
        }
    }

    public static class n_1700_B {
        public static final Codec<n_1700_B> n_1700_B = RecordCodecBuilder.create(builder -> builder.group((App)Codec.floatRange((float)-2.0f, (float)2.0f).fieldOf("temperature").forGetter(attributes -> Float.valueOf(attributes.J_1907_R)), (App)Codec.floatRange((float)-2.0f, (float)2.0f).fieldOf("humidity").forGetter(attributes -> Float.valueOf(attributes.R_4764_Y)), (App)Codec.floatRange((float)-2.0f, (float)2.0f).fieldOf("altitude").forGetter(attributes -> Float.valueOf(attributes.G_564_y)), (App)Codec.floatRange((float)-2.0f, (float)2.0f).fieldOf("weirdness").forGetter(attributes -> Float.valueOf(attributes.P_1922_E)), (App)Codec.floatRange((float)0.0f, (float)1.0f).fieldOf("offset").forGetter(attributes -> Float.valueOf(attributes.u_1723_Y))).apply((Applicative)builder, n_1700_B::new));
        private final float J_1907_R;
        private final float R_4764_Y;
        private final float G_564_y;
        private final float P_1922_E;
        private final float u_1723_Y;

        public n_1700_B(float temperature, float humidity, float altitude, float weirdness, float offset) {
            this.J_1907_R = temperature;
            this.R_4764_Y = humidity;
            this.G_564_y = altitude;
            this.P_1922_E = weirdness;
            this.u_1723_Y = offset;
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
                n_1700_B biome$attributes = (n_1700_B)p_equals_1_;
                if (Float.compare(biome$attributes.J_1907_R, this.J_1907_R) != 0) {
                    return false;
                }
                if (Float.compare(biome$attributes.R_4764_Y, this.R_4764_Y) != 0) {
                    return false;
                }
                if (Float.compare(biome$attributes.G_564_y, this.G_564_y) != 0) {
                    return false;
                }
                return Float.compare(biome$attributes.P_1922_E, this.P_1922_E) == 0;
            }
            return false;
        }

        public int hashCode() {
            int i = this.J_1907_R != 0.0f ? Float.floatToIntBits(this.J_1907_R) : 0;
            i = 31 * i + (this.R_4764_Y != 0.0f ? Float.floatToIntBits(this.R_4764_Y) : 0);
            i = 31 * i + (this.G_564_y != 0.0f ? Float.floatToIntBits(this.G_564_y) : 0);
            return 31 * i + (this.P_1922_E != 0.0f ? Float.floatToIntBits(this.P_1922_E) : 0);
        }

        public float n_1700_B(n_1700_B attributes) {
            return (this.J_1907_R - attributes.J_1907_R) * (this.J_1907_R - attributes.J_1907_R) + (this.R_4764_Y - attributes.R_4764_Y) * (this.R_4764_Y - attributes.R_4764_Y) + (this.G_564_y - attributes.G_564_y) * (this.G_564_y - attributes.G_564_y) + (this.P_1922_E - attributes.P_1922_E) * (this.P_1922_E - attributes.P_1922_E) + (this.u_1723_Y - attributes.u_1723_Y) * (this.u_1723_Y - attributes.u_1723_Y);
        }
    }
}


