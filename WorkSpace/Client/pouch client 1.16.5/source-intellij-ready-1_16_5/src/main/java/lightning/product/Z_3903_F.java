/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.Lifecycle
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.io.File;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.function.Supplier;
import lightning.product.TheEndBiomeSource;
import lightning.product.G_156_T;
import lightning.product.LevelStem;
import lightning.product.K_2991_D;
import lightning.product.BiomeZoomer;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.b_4507_u;
import lightning.product.c_3833_Y;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.k_594_Q;
import lightning.product.WritableRegistry;
import lightning.product.n_395_H;
import lightning.product.n_4684_C;
import lightning.product.n_880_h;
import lightning.product.BlockTags;
import lightning.product.r_109_r;
import lightning.product.r_4097_j;
import lightning.product.u_530_F;
import lightning.product.v_1758_J;
import lightning.product.z_1753_f;

public class Z_3903_F {
    public static final g_2336_b n_1700_B = new g_2336_b("overworld");
    public static final g_2336_b J_1907_R = new g_2336_b("the_nether");
    public static final g_2336_b R_4764_Y = new g_2336_b("the_end");
    public static final Codec<Z_3903_F> G_564_y = RecordCodecBuilder.create(builder -> builder.group((App)Codec.LONG.optionalFieldOf("fixed_time").xmap(fixedTime -> fixedTime.map(OptionalLong::of).orElseGet(OptionalLong::empty), fixedTime -> fixedTime.isPresent() ? Optional.of(fixedTime.getAsLong()) : Optional.empty()).forGetter(type -> type.Q_4569_t), (App)Codec.BOOL.fieldOf("has_skylight").forGetter(Z_3903_F::J_1907_R), (App)Codec.BOOL.fieldOf("has_ceiling").forGetter(Z_3903_F::R_4764_Y), (App)Codec.BOOL.fieldOf("ultrawarm").forGetter(Z_3903_F::G_564_y), (App)Codec.BOOL.fieldOf("natural").forGetter(Z_3903_F::P_1922_E), (App)Codec.doubleRange((double)1.0E-5f, (double)3.0E7).fieldOf("coordinate_scale").forGetter(Z_3903_F::u_1723_Y), (App)Codec.BOOL.fieldOf("piglin_safe").forGetter(Z_3903_F::v_4262_N), (App)Codec.BOOL.fieldOf("bed_works").forGetter(Z_3903_F::w_1484_f), (App)Codec.BOOL.fieldOf("respawn_anchor_works").forGetter(Z_3903_F::t_148_a), (App)Codec.BOOL.fieldOf("has_raids").forGetter(Z_3903_F::s_956_w), (App)Codec.intRange((int)0, (int)256).fieldOf("logical_height").forGetter(Z_3903_F::u_2550_I), (App)g_2336_b.n_1700_B.fieldOf("infiniburn").forGetter(type -> type.H_2857_Y), (App)g_2336_b.n_1700_B.fieldOf("effects").orElse((Object)n_1700_B).forGetter(type -> type.A_4115_X), (App)Codec.FLOAT.fieldOf("ambient_light").forGetter(type -> Float.valueOf(type.Y_1740_V))).apply((Applicative)builder, Z_3903_F::new));
    public static final float[] P_1922_E = new float[]{1.0f, 0.75f, 0.5f, 0.25f, 0.0f, 0.25f, 0.5f, 0.75f};
    public static final f_2392_k<Z_3903_F> u_1723_Y = f_2392_k.n_1700_B(V_3137_a.d_2427_y, new g_2336_b("overworld"));
    public static final f_2392_k<Z_3903_F> v_4262_N = f_2392_k.n_1700_B(V_3137_a.d_2427_y, new g_2336_b("the_nether"));
    public static final f_2392_k<Z_3903_F> w_1484_f = f_2392_k.n_1700_B(V_3137_a.d_2427_y, new g_2336_b("the_end"));
    protected static final Z_3903_F t_148_a = new Z_3903_F(OptionalLong.empty(), true, false, false, true, 1.0, false, false, true, false, true, 256, c_3833_Y.n_1700_B, BlockTags.M_1641_O.J_1907_R(), n_1700_B, 0.0f);
    protected static final Z_3903_F s_956_w = new Z_3903_F(OptionalLong.of(18000L), false, true, true, false, 8.0, false, true, false, true, false, 128, K_2991_D.n_1700_B, BlockTags.RealmsWorldOptions.J_1907_R(), J_1907_R, 0.1f);
    protected static final Z_3903_F u_2550_I = new Z_3903_F(OptionalLong.of(6000L), false, false, false, false, 1.0, true, false, false, false, true, 256, K_2991_D.n_1700_B, BlockTags.RealmsWorldResetDto.J_1907_R(), R_4764_Y, 0.0f);
    public static final f_2392_k<Z_3903_F> M_588_G = f_2392_k.n_1700_B(V_3137_a.d_2427_y, new g_2336_b("overworld_caves"));
    protected static final Z_3903_F P_4830_p = new Z_3903_F(OptionalLong.empty(), true, true, false, true, 1.0, false, false, true, false, true, 256, c_3833_Y.n_1700_B, BlockTags.M_1641_O.J_1907_R(), n_1700_B, 0.0f);
    public static final Codec<Supplier<Z_3903_F>> h_1847_R = n_4684_C.n_1700_B(V_3137_a.d_2427_y, G_564_y);
    private final OptionalLong Q_4569_t;
    private final boolean M_182_A;
    private final boolean t_1786_h;
    private final boolean multiplayerClientSuggestionProvider;
    private final boolean w_1457_N;
    private final double Y_601_j;
    private final boolean Y_259_p;
    private final boolean Q_2552_b;
    private final boolean C_2741_M;
    private final boolean k_2293_S;
    private final boolean q_2307_F;
    private final int Z_875_P;
    private final BiomeZoomer c_3005_b;
    private final g_2336_b H_2857_Y;
    private final g_2336_b A_4115_X;
    private final float Y_1740_V;
    private final transient float[] t_4043_B;

    protected Z_3903_F(OptionalLong fixedTime, boolean hasSkyLight, boolean hasCeiling, boolean ultrawarm, boolean natural, double coordinateScale, boolean piglinSafe, boolean bedWorks, boolean respawnAnchorWorks, boolean hasRaids, int logicalHeight, g_2336_b infiniburn, g_2336_b effects, float ambientLight) {
        this(fixedTime, hasSkyLight, hasCeiling, ultrawarm, natural, coordinateScale, false, piglinSafe, bedWorks, respawnAnchorWorks, hasRaids, logicalHeight, K_2991_D.n_1700_B, infiniburn, effects, ambientLight);
    }

    protected Z_3903_F(OptionalLong fixedTime, boolean hasSkyLight, boolean hasCeiling, boolean ultrawarm, boolean natural, double coordinateScale, boolean hasDragonFight, boolean piglinSafe, boolean bedWorks, boolean respawnAnchorWorks, boolean hasRaids, int logicalHeight, BiomeZoomer magnifier, g_2336_b infiniburn, g_2336_b effects, float ambientLight) {
        this.Q_4569_t = fixedTime;
        this.M_182_A = hasSkyLight;
        this.t_1786_h = hasCeiling;
        this.multiplayerClientSuggestionProvider = ultrawarm;
        this.w_1457_N = natural;
        this.Y_601_j = coordinateScale;
        this.Y_259_p = hasDragonFight;
        this.Q_2552_b = piglinSafe;
        this.C_2741_M = bedWorks;
        this.k_2293_S = respawnAnchorWorks;
        this.q_2307_F = hasRaids;
        this.Z_875_P = logicalHeight;
        this.c_3005_b = magnifier;
        this.H_2857_Y = infiniburn;
        this.A_4115_X = effects;
        this.Y_1740_V = ambientLight;
        this.t_4043_B = Z_3903_F.n_1700_B(ambientLight);
    }

    private static float[] n_1700_B(float light) {
        float[] afloat = new float[16];
        for (int i = 0; i <= 15; ++i) {
            float f = (float)i / 15.0f;
            float f1 = f / (4.0f - 3.0f * f);
            afloat[i] = u_530_F.v_4262_N(light, f1, 1.0f);
        }
        return afloat;
    }

    @Deprecated
    public static DataResult<f_2392_k<b_4507_u>> n_1700_B(Dynamic<?> dynamic) {
        Optional optional = dynamic.asNumber().result();
        if (optional.isPresent()) {
            int i = ((Number)optional.get()).intValue();
            if (i == -1) {
                return DataResult.success(b_4507_u.v_4262_N);
            }
            if (i == 0) {
                return DataResult.success(b_4507_u.u_1723_Y);
            }
            if (i == 1) {
                return DataResult.success(b_4507_u.w_1484_f);
            }
        }
        return b_4507_u.P_1922_E.parse(dynamic);
    }

    public static r_4097_j.J_1907_R n_1700_B(r_4097_j.J_1907_R impl) {
        WritableRegistry<Z_3903_F> mutableregistry = impl.J_1907_R(V_3137_a.d_2427_y);
        mutableregistry.n_1700_B(u_1723_Y, t_148_a, Lifecycle.stable());
        mutableregistry.n_1700_B(M_588_G, P_4830_p, Lifecycle.stable());
        mutableregistry.n_1700_B(v_4262_N, s_956_w, Lifecycle.stable());
        mutableregistry.n_1700_B(w_1484_f, u_2550_I, Lifecycle.stable());
        return impl;
    }

    private static z_1753_f n_1700_B(V_3137_a<k_594_Q> lookUpRegistryBiome, V_3137_a<G_156_T> settingsRegistry, long seed) {
        return new n_395_H(new TheEndBiomeSource(lookUpRegistryBiome, seed), seed, () -> settingsRegistry.R_4764_Y(G_156_T.u_1723_Y));
    }

    private static z_1753_f J_1907_R(V_3137_a<k_594_Q> lookUpRegistryBiome, V_3137_a<G_156_T> lookUpRegistryDimensionType, long seed) {
        return new n_395_H(n_880_h.R_4764_Y.n_1700_B.n_1700_B(lookUpRegistryBiome, seed), seed, () -> lookUpRegistryDimensionType.R_4764_Y(G_156_T.P_1922_E));
    }

    public static v_1758_J<LevelStem> n_1700_B(V_3137_a<Z_3903_F> lookUpRegistryDimensionType, V_3137_a<k_594_Q> lookUpRegistryBiome, V_3137_a<G_156_T> lookUpRegistryDimensionSettings, long seed) {
        v_1758_J<LevelStem> simpleregistry = new v_1758_J<LevelStem>(V_3137_a.v_4276_D, Lifecycle.experimental());
        simpleregistry.n_1700_B(LevelStem.R_4764_Y, new LevelStem(() -> lookUpRegistryDimensionType.R_4764_Y(v_4262_N), Z_3903_F.J_1907_R(lookUpRegistryBiome, lookUpRegistryDimensionSettings, seed)), Lifecycle.stable());
        simpleregistry.n_1700_B(LevelStem.G_564_y, new LevelStem(() -> lookUpRegistryDimensionType.R_4764_Y(w_1484_f), Z_3903_F.n_1700_B(lookUpRegistryBiome, lookUpRegistryDimensionSettings, seed)), Lifecycle.stable());
        return simpleregistry;
    }

    public static double n_1700_B(Z_3903_F firstType, Z_3903_F secondType) {
        double d0 = firstType.u_1723_Y();
        double d1 = secondType.u_1723_Y();
        return d0 / d1;
    }

    @Deprecated
    public String n_1700_B() {
        return this.n_1700_B(u_2550_I) ? "_end" : "";
    }

    public static File n_1700_B(f_2392_k<b_4507_u> dimensionKey, File worldFolder) {
        if (dimensionKey == b_4507_u.u_1723_Y) {
            return worldFolder;
        }
        if (dimensionKey == b_4507_u.w_1484_f) {
            return new File(worldFolder, "DIM1");
        }
        return dimensionKey == b_4507_u.v_4262_N ? new File(worldFolder, "DIM-1") : new File(worldFolder, "dimensions/" + dimensionKey.n_1700_B().R_4764_Y() + "/" + dimensionKey.n_1700_B().J_1907_R());
    }

    public boolean J_1907_R() {
        return this.M_182_A;
    }

    public boolean R_4764_Y() {
        return this.t_1786_h;
    }

    public boolean G_564_y() {
        return this.multiplayerClientSuggestionProvider;
    }

    public boolean P_1922_E() {
        return this.w_1457_N;
    }

    public double u_1723_Y() {
        return this.Y_601_j;
    }

    public boolean v_4262_N() {
        return this.Q_2552_b;
    }

    public boolean w_1484_f() {
        return this.C_2741_M;
    }

    public boolean t_148_a() {
        return this.k_2293_S;
    }

    public boolean s_956_w() {
        return this.q_2307_F;
    }

    public int u_2550_I() {
        return this.Z_875_P;
    }

    public boolean M_588_G() {
        return this.Y_259_p;
    }

    public BiomeZoomer P_4830_p() {
        return this.c_3005_b;
    }

    public boolean h_1847_R() {
        return this.Q_4569_t.isPresent();
    }

    public float n_1700_B(long dayTime) {
        double d0 = u_530_F.v_4262_N((double)this.Q_4569_t.orElse(dayTime) / 24000.0 - 0.25);
        double d1 = 0.5 - Math.cos(d0 * Math.PI) / 2.0;
        return (float)(d0 * 2.0 + d1) / 3.0f;
    }

    public int J_1907_R(long dayTime) {
        return (int)(dayTime / 24000L % 8L + 8L) % 8;
    }

    public float n_1700_B(int lightIn) {
        return this.t_4043_B[lightIn];
    }

    public r_109_r<T_2915_h> Q_4569_t() {
        r_109_r<T_2915_h> itag = BlockTags.n_1700_B().n_1700_B(this.H_2857_Y);
        return itag != null ? itag : BlockTags.M_1641_O;
    }

    public g_2336_b M_182_A() {
        return this.A_4115_X;
    }

    public boolean n_1700_B(Z_3903_F type) {
        if (this == type) {
            return true;
        }
        return this.M_182_A == type.M_182_A && this.t_1786_h == type.t_1786_h && this.multiplayerClientSuggestionProvider == type.multiplayerClientSuggestionProvider && this.w_1457_N == type.w_1457_N && this.Y_601_j == type.Y_601_j && this.Y_259_p == type.Y_259_p && this.Q_2552_b == type.Q_2552_b && this.C_2741_M == type.C_2741_M && this.k_2293_S == type.k_2293_S && this.q_2307_F == type.q_2307_F && this.Z_875_P == type.Z_875_P && Float.compare(type.Y_1740_V, this.Y_1740_V) == 0 && this.Q_4569_t.equals(type.Q_4569_t) && this.c_3005_b.equals(type.c_3005_b) && this.H_2857_Y.equals(type.H_2857_Y) && this.A_4115_X.equals(type.A_4115_X);
    }
}


