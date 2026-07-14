/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import lightning.product.MonsterRoomFeature;
import lightning.product.featureGlowstoneFeature;
import lightning.product.ProbabilityFeatureConfiguration;
import lightning.product.LakeFeature;
import lightning.product.NoSurfaceOreFeature;
import lightning.product.ReplaceSphereConfiguration;
import lightning.product.AbstractFlowerFeature;
import lightning.product.FossilFeature;
import lightning.product.ColumnFeatureConfiguration;
import lightning.product.DiskConfiguration;
import lightning.product.F_3233_i;
import lightning.product.CoralClawFeature;
import lightning.product.G_3858_B;
import lightning.product.featureReplaceBlockFeature;
import lightning.product.RandomFeatureConfiguration;
import lightning.product.SimpleBlockConfiguration;
import lightning.product.BlockPileFeature;
import lightning.product.K_4074_S;
import lightning.product.TreeFeature;
import lightning.product.HugeMushroomFeatureConfiguration;
import lightning.product.ReplaceBlobsFeature;
import lightning.product.DesertWellFeature;
import lightning.product.BambooFeature;
import lightning.product.EndGatewayFeature;
import lightning.product.SimpleBlockFeature;
import lightning.product.S_1806_m;
import lightning.product.WorldGenLevel;
import lightning.product.S_2843_Z;
import lightning.product.TreeConfiguration;
import lightning.product.T_2915_h;
import lightning.product.IcePatchFeature;
import lightning.product.SpringConfiguration;
import lightning.product.SpikeConfiguration;
import lightning.product.V_3137_a;
import lightning.product.SpringFeature;
import lightning.product.RandomBooleanFeatureConfiguration;
import lightning.product.NetherForestVegetationFeature;
import lightning.product.BlockPileConfiguration;
import lightning.product.DecoratedFeatureConfiguration;
import lightning.product.a_3742_W;
import lightning.product.ReplaceBlockFeature;
import lightning.product.ConfiguredFeature;
import lightning.product.b_2885_h;
import lightning.product.b_3673_R;
import lightning.product.RandomPatchFeature;
import lightning.product.b_618_G;
import lightning.product.FillLayerFeature;
import lightning.product.ReplaceBlockConfiguration;
import lightning.product.c_1514_x;
import lightning.product.HugeFungusFeature;
import lightning.product.d_1001_C;
import lightning.product.VoidStartPlatformFeature;
import lightning.product.RandomSelectorFeature;
import lightning.product.OreConfiguration;
import lightning.product.e_2415_Y;
import lightning.product.IceSpikeFeature;
import lightning.product.g_4836_I;
import lightning.product.EndIslandFeature;
import lightning.product.VinesFeature;
import lightning.product.DefaultFlowerFeature;
import lightning.product.l_3305_T;
import lightning.product.BonusChestFeature;
import lightning.product.BasaltColumnsFeature;
import lightning.product.o_2105_O;
import lightning.product.RandomPatchConfiguration;
import lightning.product.SeaPickleFeature;
import lightning.product.LevelSimulatedReader;
import lightning.product.q_4293_E;
import lightning.product.SpikeFeature;
import lightning.product.DeltaFeatureConfiguration;
import lightning.product.s_3543_h;
import lightning.product.FeatureConfiguration;
import lightning.product.KelpFeature;
import lightning.product.NoOpFeature;
import lightning.product.CountConfiguration;
import lightning.product.SnowAndFreezeFeature;
import lightning.product.BlockStateConfiguration;
import lightning.product.DecoratedFeature;
import lightning.product.DeltaFeature;
import lightning.product.featureIcePatchFeature;
import lightning.product.HugeBrownMushroomFeature;
import lightning.product.LayerConfiguration;
import lightning.product.ChorusPlantFeature;
import lightning.product.LevelWriter;
import lightning.product.SimpleRandomFeatureConfiguration;
import lightning.product.HugeFungusConfiguration;
import lightning.product.z_1753_f;
import lightning.product.EndGatewayConfiguration;

public abstract class Feature<FC extends FeatureConfiguration> {
    public static final Feature<o_2105_O> J_1907_R = Feature.n_1700_B("no_op", new NoOpFeature(o_2105_O.n_1700_B));
    public static final Feature<TreeConfiguration> R_4764_Y = Feature.n_1700_B("tree", new TreeFeature(TreeConfiguration.n_1700_B));
    public static final AbstractFlowerFeature<RandomPatchConfiguration> G_564_y = Feature.n_1700_B("flower", new DefaultFlowerFeature(RandomPatchConfiguration.n_1700_B));
    public static final AbstractFlowerFeature<RandomPatchConfiguration> P_1922_E = Feature.n_1700_B("no_bonemeal_flower", new DefaultFlowerFeature(RandomPatchConfiguration.n_1700_B));
    public static final Feature<RandomPatchConfiguration> u_1723_Y = Feature.n_1700_B("random_patch", new RandomPatchFeature(RandomPatchConfiguration.n_1700_B));
    public static final Feature<BlockPileConfiguration> v_4262_N = Feature.n_1700_B("block_pile", new BlockPileFeature(BlockPileConfiguration.n_1700_B));
    public static final Feature<SpringConfiguration> w_1484_f = Feature.n_1700_B("spring_feature", new SpringFeature(SpringConfiguration.n_1700_B));
    public static final Feature<o_2105_O> t_148_a = Feature.n_1700_B("chorus_plant", new ChorusPlantFeature(o_2105_O.n_1700_B));
    public static final Feature<ReplaceBlockConfiguration> s_956_w = Feature.n_1700_B("emerald_ore", new ReplaceBlockFeature(ReplaceBlockConfiguration.n_1700_B));
    public static final Feature<o_2105_O> u_2550_I = Feature.n_1700_B("void_start_platform", new VoidStartPlatformFeature(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> M_588_G = Feature.n_1700_B("desert_well", new DesertWellFeature(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> P_4830_p = Feature.n_1700_B("fossil", new FossilFeature(o_2105_O.n_1700_B));
    public static final Feature<HugeMushroomFeatureConfiguration> h_1847_R = Feature.n_1700_B("huge_red_mushroom", new d_1001_C(HugeMushroomFeatureConfiguration.n_1700_B));
    public static final Feature<HugeMushroomFeatureConfiguration> Q_4569_t = Feature.n_1700_B("huge_brown_mushroom", new HugeBrownMushroomFeature(HugeMushroomFeatureConfiguration.n_1700_B));
    public static final Feature<o_2105_O> M_182_A = Feature.n_1700_B("ice_spike", new IceSpikeFeature(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> t_1786_h = Feature.n_1700_B("glowstone_blob", new featureGlowstoneFeature(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> multiplayerClientSuggestionProvider = Feature.n_1700_B("freeze_top_layer", new SnowAndFreezeFeature(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> w_1457_N = Feature.n_1700_B("vines", new VinesFeature(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> Y_601_j = Feature.n_1700_B("monster_room", new MonsterRoomFeature(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> Y_259_p = Feature.n_1700_B("blue_ice", new F_3233_i(o_2105_O.n_1700_B));
    public static final Feature<BlockStateConfiguration> Q_2552_b = Feature.n_1700_B("iceberg", new b_3673_R(BlockStateConfiguration.n_1700_B));
    public static final Feature<BlockStateConfiguration> C_2741_M = Feature.n_1700_B("forest_rock", new S_2843_Z(BlockStateConfiguration.n_1700_B));
    public static final Feature<DiskConfiguration> k_2293_S = Feature.n_1700_B("disk", new featureReplaceBlockFeature(DiskConfiguration.n_1700_B));
    public static final Feature<DiskConfiguration> q_2307_F = Feature.n_1700_B("ice_patch", new IcePatchFeature(DiskConfiguration.n_1700_B));
    public static final Feature<BlockStateConfiguration> Z_875_P = Feature.n_1700_B("lake", new LakeFeature(BlockStateConfiguration.n_1700_B));
    public static final Feature<OreConfiguration> c_3005_b = Feature.n_1700_B("ore", new l_3305_T(OreConfiguration.n_1700_B));
    public static final Feature<SpikeConfiguration> H_2857_Y = Feature.n_1700_B("end_spike", new SpikeFeature(SpikeConfiguration.n_1700_B));
    public static final Feature<o_2105_O> A_4115_X = Feature.n_1700_B("end_island", new EndIslandFeature(o_2105_O.n_1700_B));
    public static final Feature<EndGatewayConfiguration> Y_1740_V = Feature.n_1700_B("end_gateway", new EndGatewayFeature(EndGatewayConfiguration.n_1700_B));
    public static final b_2885_h t_4043_B = Feature.n_1700_B("seagrass", new b_2885_h(ProbabilityFeatureConfiguration.n_1700_B));
    public static final Feature<o_2105_O> x_607_J = Feature.n_1700_B("kelp", new KelpFeature(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> e_4240_b = Feature.n_1700_B("coral_tree", new b_618_G(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> n_3318_d = Feature.n_1700_B("coral_mushroom", new s_3543_h(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> d_2427_y = Feature.n_1700_B("coral_claw", new CoralClawFeature(o_2105_O.n_1700_B));
    public static final Feature<CountConfiguration> z_1737_N = Feature.n_1700_B("sea_pickle", new SeaPickleFeature(CountConfiguration.n_1700_B));
    public static final Feature<SimpleBlockConfiguration> v_4276_D = Feature.n_1700_B("simple_block", new SimpleBlockFeature(SimpleBlockConfiguration.n_1700_B));
    public static final Feature<ProbabilityFeatureConfiguration> d_2461_k = Feature.n_1700_B("bamboo", new BambooFeature(ProbabilityFeatureConfiguration.n_1700_B));
    public static final Feature<HugeFungusConfiguration> G_624_v = Feature.n_1700_B("huge_fungus", new HugeFungusFeature(HugeFungusConfiguration.n_1700_B));
    public static final Feature<BlockPileConfiguration> T_2506_i = Feature.n_1700_B("nether_forest_vegetation", new NetherForestVegetationFeature(BlockPileConfiguration.n_1700_B));
    public static final Feature<o_2105_O> q_4610_l = Feature.n_1700_B("weeping_vines", new G_3858_B(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> z_4693_k = Feature.n_1700_B("twisting_vines", new S_1806_m(o_2105_O.n_1700_B));
    public static final Feature<ColumnFeatureConfiguration> g_221_o = Feature.n_1700_B("basalt_columns", new BasaltColumnsFeature(ColumnFeatureConfiguration.n_1700_B));
    public static final Feature<DeltaFeatureConfiguration> e_2887_G = Feature.n_1700_B("delta_feature", new DeltaFeature(DeltaFeatureConfiguration.n_1700_B));
    public static final Feature<ReplaceSphereConfiguration> B_1668_F = Feature.n_1700_B("netherrack_replace_blobs", new ReplaceBlobsFeature(ReplaceSphereConfiguration.n_1700_B));
    public static final Feature<LayerConfiguration> g_164_R = Feature.n_1700_B("fill_layer", new FillLayerFeature(LayerConfiguration.n_1700_B));
    public static final BonusChestFeature X_933_l = Feature.n_1700_B("bonus_chest", new BonusChestFeature(o_2105_O.n_1700_B));
    public static final Feature<o_2105_O> Z_976_R = Feature.n_1700_B("basalt_pillar", new g_4836_I(o_2105_O.n_1700_B));
    public static final Feature<OreConfiguration> H_1990_U = Feature.n_1700_B("no_surface_ore", new NoSurfaceOreFeature(OreConfiguration.n_1700_B));
    public static final Feature<RandomFeatureConfiguration> N_2525_X = Feature.n_1700_B("random_selector", new RandomSelectorFeature(RandomFeatureConfiguration.n_1700_B));
    public static final Feature<SimpleRandomFeatureConfiguration> c_4037_x = Feature.n_1700_B("simple_random_selector", new e_2415_Y(SimpleRandomFeatureConfiguration.n_1700_B));
    public static final Feature<RandomBooleanFeatureConfiguration> g_2268_R = Feature.n_1700_B("random_boolean_selector", new featureIcePatchFeature(RandomBooleanFeatureConfiguration.n_1700_B));
    public static final Feature<DecoratedFeatureConfiguration> T_3594_S = Feature.n_1700_B("decorated", new DecoratedFeature(DecoratedFeatureConfiguration.n_1700_B));
    private final Codec<ConfiguredFeature<FC, Feature<FC>>> n_1700_B;

    private static <C extends FeatureConfiguration, F extends Feature<C>> F n_1700_B(String key, F value) {
        return (F)V_3137_a.n_1700_B(V_3137_a.RealmsServerPing, key, value);
    }

    public Feature(Codec<FC> codec) {
        this.n_1700_B = codec.fieldOf("config").xmap(config -> new ConfiguredFeature<FeatureConfiguration, Feature>(this, (FeatureConfiguration)config), configured -> configured.u_1723_Y).codec();
    }

    public Codec<ConfiguredFeature<FC, Feature<FC>>> n_1700_B() {
        return this.n_1700_B;
    }

    public ConfiguredFeature<FC, ?> J_1907_R(FC config) {
        return new ConfiguredFeature<FC, Feature>(this, config);
    }

    protected void n_1700_B(LevelWriter world, c_1514_x pos, K_4074_S state) {
        world.n_1700_B(pos, state, 3);
    }

    public abstract boolean n_1700_B(WorldGenLevel var1, z_1753_f var2, Random var3, c_1514_x var4, FC var5);

    protected static boolean n_1700_B(T_2915_h blockIn) {
        return blockIn == a_3742_W.J_1907_R || blockIn == a_3742_W.R_4764_Y || blockIn == a_3742_W.P_1922_E || blockIn == a_3742_W.v_4262_N;
    }

    public static boolean J_1907_R(T_2915_h blockIn) {
        return blockIn == a_3742_W.s_956_w || blockIn == a_3742_W.t_148_a || blockIn == a_3742_W.M_588_G || blockIn == a_3742_W.u_2550_I || blockIn == a_3742_W.A_2714_y;
    }

    public static boolean n_1700_B(LevelSimulatedReader world, c_1514_x pos) {
        return world.n_1700_B(pos, state -> Feature.J_1907_R(state.J_1907_R()));
    }

    public static boolean J_1907_R(LevelSimulatedReader world, c_1514_x pos) {
        return world.n_1700_B(pos, q_4293_E.n_1700_B::v_4262_N);
    }
}


