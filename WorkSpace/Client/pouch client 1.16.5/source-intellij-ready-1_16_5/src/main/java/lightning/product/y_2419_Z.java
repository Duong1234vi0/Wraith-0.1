/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.Stream;
import lightning.product.HeightmapDecorator;
import lightning.product.C_3925_O;
import lightning.product.CountNoiseDecorator;
import lightning.product.D_3325_s;
import lightning.product.F_1974_C;
import lightning.product.NoiseDependantDecoratorConfiguration;
import lightning.product.NopePlacementDecorator;
import lightning.product.LakeLavaPlacementDecorator;
import lightning.product.CountDecorator;
import lightning.product.CountWithExtraChanceDecorator;
import lightning.product.DecorationContext;
import lightning.product.HeightmapDoubleDecorator;
import lightning.product.CarvingMaskDecorator;
import lightning.product.P_1781_m;
import lightning.product.CountMultiLayerDecorator;
import lightning.product.DecoratedDecorator;
import lightning.product.NoiseCountFactorDecoratorConfiguration;
import lightning.product.EndIslandPlacementDecorator;
import lightning.product.S_4291_z;
import lightning.product.T_3881_k;
import lightning.product.NoiseBasedDecorator;
import lightning.product.V_3137_a;
import lightning.product.EndGatewayPlacementDecorator;
import lightning.product.c_1514_x;
import lightning.product.c_2685_I;
import lightning.product.MagmaDecorator;
import lightning.product.f_3410_P;
import lightning.product.GlowstoneDecorator;
import lightning.product.ChanceDecoratorConfiguration;
import lightning.product.CarvingMaskDecoratorConfiguration;
import lightning.product.ConfiguredDecorator;
import lightning.product.FrequencyWithExtraChanceDecoratorConfiguration;
import lightning.product.ChanceDecorator;
import lightning.product.DarkOakTreePlacementDecorator;
import lightning.product.DecoratedDecoratorConfiguration;
import lightning.product.FireDecorator;
import lightning.product.CountConfiguration;
import lightning.product.EmeraldPlacementDecorator;
import lightning.product.LakeWaterPlacementDecorator;
import lightning.product.DepthAverageConfigation;
import lightning.product.Spread32Decorator;
import lightning.product.y_4691_Y;
import lightning.product.RangeDecoratorConfiguration;
import lightning.product.z_915_d;

public abstract class y_2419_Z<DC extends P_1781_m> {
    public static final y_2419_Z<S_4291_z> n_1700_B = y_2419_Z.n_1700_B("nope", new NopePlacementDecorator(S_4291_z.n_1700_B));
    public static final y_2419_Z<ChanceDecoratorConfiguration> J_1907_R = y_2419_Z.n_1700_B("chance", new ChanceDecorator(ChanceDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<CountConfiguration> R_4764_Y = y_2419_Z.n_1700_B("count", new CountDecorator(CountConfiguration.n_1700_B));
    public static final y_2419_Z<NoiseDependantDecoratorConfiguration> G_564_y = y_2419_Z.n_1700_B("count_noise", new CountNoiseDecorator(NoiseDependantDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<NoiseCountFactorDecoratorConfiguration> P_1922_E = y_2419_Z.n_1700_B("count_noise_biased", new NoiseBasedDecorator(NoiseCountFactorDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<FrequencyWithExtraChanceDecoratorConfiguration> u_1723_Y = y_2419_Z.n_1700_B("count_extra", new CountWithExtraChanceDecorator(FrequencyWithExtraChanceDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<S_4291_z> v_4262_N = y_2419_Z.n_1700_B("square", new C_3925_O(S_4291_z.n_1700_B));
    public static final y_2419_Z<S_4291_z> w_1484_f = y_2419_Z.n_1700_B("heightmap", new HeightmapDecorator<S_4291_z>(S_4291_z.n_1700_B));
    public static final y_2419_Z<S_4291_z> t_148_a = y_2419_Z.n_1700_B("heightmap_spread_double", new HeightmapDoubleDecorator<S_4291_z>(S_4291_z.n_1700_B));
    public static final y_2419_Z<S_4291_z> s_956_w = y_2419_Z.n_1700_B("top_solid_heightmap", new D_3325_s(S_4291_z.n_1700_B));
    public static final y_2419_Z<S_4291_z> u_2550_I = y_2419_Z.n_1700_B("heightmap_world_surface", new z_915_d(S_4291_z.n_1700_B));
    public static final y_2419_Z<RangeDecoratorConfiguration> M_588_G = y_2419_Z.n_1700_B("range", new F_1974_C(RangeDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<RangeDecoratorConfiguration> P_4830_p = y_2419_Z.n_1700_B("range_biased", new y_4691_Y(RangeDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<RangeDecoratorConfiguration> h_1847_R = y_2419_Z.n_1700_B("range_very_biased", new T_3881_k(RangeDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<DepthAverageConfigation> Q_4569_t = y_2419_Z.n_1700_B("depth_average", new f_3410_P(DepthAverageConfigation.n_1700_B));
    public static final y_2419_Z<S_4291_z> M_182_A = y_2419_Z.n_1700_B("spread_32_above", new Spread32Decorator(S_4291_z.n_1700_B));
    public static final y_2419_Z<CarvingMaskDecoratorConfiguration> t_1786_h = y_2419_Z.n_1700_B("carving_mask", new CarvingMaskDecorator(CarvingMaskDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<CountConfiguration> multiplayerClientSuggestionProvider = y_2419_Z.n_1700_B("fire", new FireDecorator(CountConfiguration.n_1700_B));
    public static final y_2419_Z<S_4291_z> w_1457_N = y_2419_Z.n_1700_B("magma", new MagmaDecorator(S_4291_z.n_1700_B));
    public static final y_2419_Z<S_4291_z> Y_601_j = y_2419_Z.n_1700_B("emerald_ore", new EmeraldPlacementDecorator(S_4291_z.n_1700_B));
    public static final y_2419_Z<ChanceDecoratorConfiguration> Y_259_p = y_2419_Z.n_1700_B("lava_lake", new LakeLavaPlacementDecorator(ChanceDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<ChanceDecoratorConfiguration> Q_2552_b = y_2419_Z.n_1700_B("water_lake", new LakeWaterPlacementDecorator(ChanceDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<CountConfiguration> C_2741_M = y_2419_Z.n_1700_B("glowstone", new GlowstoneDecorator(CountConfiguration.n_1700_B));
    public static final y_2419_Z<S_4291_z> k_2293_S = y_2419_Z.n_1700_B("end_gateway", new EndGatewayPlacementDecorator(S_4291_z.n_1700_B));
    public static final y_2419_Z<S_4291_z> q_2307_F = y_2419_Z.n_1700_B("dark_oak_tree", new DarkOakTreePlacementDecorator(S_4291_z.n_1700_B));
    public static final y_2419_Z<S_4291_z> Z_875_P = y_2419_Z.n_1700_B("iceberg", new c_2685_I(S_4291_z.n_1700_B));
    public static final y_2419_Z<S_4291_z> c_3005_b = y_2419_Z.n_1700_B("end_island", new EndIslandPlacementDecorator(S_4291_z.n_1700_B));
    public static final y_2419_Z<DecoratedDecoratorConfiguration> H_2857_Y = y_2419_Z.n_1700_B("decorated", new DecoratedDecorator(DecoratedDecoratorConfiguration.n_1700_B));
    public static final y_2419_Z<CountConfiguration> A_4115_X = y_2419_Z.n_1700_B("count_multilayer", new CountMultiLayerDecorator(CountConfiguration.n_1700_B));
    private final Codec<ConfiguredDecorator<DC>> Y_1740_V;

    private static <T extends P_1781_m, G extends y_2419_Z<T>> G n_1700_B(String key, G placement) {
        return (G)V_3137_a.n_1700_B(V_3137_a.H_1083_k, key, placement);
    }

    public y_2419_Z(Codec<DC> codec) {
        this.Y_1740_V = codec.fieldOf("config").xmap(placementConfig -> new ConfiguredDecorator<P_1781_m>(this, (P_1781_m)placementConfig), ConfiguredDecorator::J_1907_R).codec();
    }

    public ConfiguredDecorator<DC> J_1907_R(DC config) {
        return new ConfiguredDecorator<DC>(this, config);
    }

    public Codec<ConfiguredDecorator<DC>> n_1700_B() {
        return this.Y_1740_V;
    }

    public abstract Stream<c_1514_x> n_1700_B(DecorationContext var1, Random var2, DC var3, c_1514_x var4);

    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode());
    }
}


