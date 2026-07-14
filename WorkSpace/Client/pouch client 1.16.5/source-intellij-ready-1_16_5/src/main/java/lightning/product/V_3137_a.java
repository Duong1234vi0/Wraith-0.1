/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.Keyable
 *  com.mojang.serialization.Lifecycle
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.Lifecycle;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nullable;
import lightning.product.B_4977_Y;
import lightning.product.ChunkStatus;
import lightning.product.DefaultedRegistry;
import lightning.product.SensorType;
import lightning.product.SurfaceBuilder;
import lightning.product.StructureFeature;
import lightning.product.G_156_T;
import lightning.product.LevelStem;
import lightning.product.TreeDecoratorType;
import lightning.product.Attributes;
import lightning.product.LootItemFunctions;
import lightning.product.Fluids;
import lightning.product.SharedConstants;
import lightning.product.MobEffects;
import lightning.product.FeatureSizeType;
import lightning.product.K_1310_v;
import lightning.product.BlockPlacerType;
import lightning.product.TrunkPlacerType;
import lightning.product.MenuType;
import lightning.product.Attribute;
import lightning.product.VillagerProfession;
import lightning.product.ConfiguredWorldCarver;
import lightning.product.R_3043_n;
import lightning.product.Potions;
import lightning.product.IdMap;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.FoliagePlacerType;
import lightning.product.RuleTestType;
import lightning.product.BuiltinRegistries;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.X_2241_P;
import lightning.product.Z_3903_F;
import lightning.product.a_3742_W;
import lightning.product.ConfiguredFeature;
import lightning.product.b_4507_u;
import lightning.product.StructurePieceType;
import lightning.product.Enchantments;
import lightning.product.d_614_w;
import lightning.product.ConfiguredSurfaceBuilder;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.g_422_i;
import lightning.product.PosRuleTestType;
import lightning.product.Feature;
import lightning.product.BlockStateProviderType;
import lightning.product.RecipeType;
import lightning.product.Activity;
import lightning.product.i_4544_r;
import lightning.product.Schedule;
import lightning.product.StructurePoolElementType;
import lightning.product.ConfiguredStructureFeature;
import lightning.product.k_594_Q;
import lightning.product.WritableRegistry;
import lightning.product.BiomeSource;
import lightning.product.o_3000_u;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;
import lightning.product.q_2232_A;
import lightning.product.q_3277_O;
import lightning.product.Items;
import lightning.product.BlockEntityType;
import lightning.product.r_3979_x_0;
import lightning.product.LootPoolEntries;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.v_1758_J;
import lightning.product.Motive;
import lightning.product.MemoryModuleType;
import lightning.product.StructureProcessorType;
import lightning.product.LootItemConditions;
import lightning.product.ParticleType;
import lightning.product.y_2419_Z;
import lightning.product.y_528_b;
import lightning.product.z_1753_f;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class V_3137_a<T>
implements Codec<T>,
Keyable,
IdMap<T> {
    protected static final Logger n_1700_B = LogManager.getLogger();
    private static final Map<g_2336_b, Supplier<?>> RealmsClientOutdatedScreen = Maps.newLinkedHashMap();
    public static final g_2336_b J_1907_R = new g_2336_b("root");
    protected static final WritableRegistry<WritableRegistry<?>> R_4764_Y = new v_1758_J(V_3137_a.n_1700_B("root"), Lifecycle.experimental());
    public static final V_3137_a<? extends V_3137_a<?>> G_564_y = R_4764_Y;
    public static final f_2392_k<V_3137_a<SoundEvent>> P_1922_E = V_3137_a.n_1700_B("sound_event");
    public static final f_2392_k<V_3137_a<Fluid>> u_1723_Y = V_3137_a.n_1700_B("fluid");
    public static final f_2392_k<V_3137_a<g_422_i>> v_4262_N = V_3137_a.n_1700_B("mob_effect");
    public static final f_2392_k<V_3137_a<T_2915_h>> w_1484_f = V_3137_a.n_1700_B("block");
    public static final f_2392_k<V_3137_a<K_1310_v>> t_148_a = V_3137_a.n_1700_B("enchantment");
    public static final f_2392_k<V_3137_a<t_5_h<?>>> s_956_w = V_3137_a.n_1700_B("entity_type");
    public static final f_2392_k<V_3137_a<q_1613_l>> u_2550_I = V_3137_a.n_1700_B("item");
    public static final f_2392_k<V_3137_a<y_528_b>> M_588_G = V_3137_a.n_1700_B("potion");
    public static final f_2392_k<V_3137_a<ParticleType<?>>> P_4830_p = V_3137_a.n_1700_B("particle_type");
    public static final f_2392_k<V_3137_a<BlockEntityType<?>>> h_1847_R = V_3137_a.n_1700_B("block_entity_type");
    public static final f_2392_k<V_3137_a<Motive>> Q_4569_t = V_3137_a.n_1700_B("motive");
    public static final f_2392_k<V_3137_a<g_2336_b>> M_182_A = V_3137_a.n_1700_B("custom_stat");
    public static final f_2392_k<V_3137_a<ChunkStatus>> t_1786_h = V_3137_a.n_1700_B("chunk_status");
    public static final f_2392_k<V_3137_a<RuleTestType<?>>> multiplayerClientSuggestionProvider = V_3137_a.n_1700_B("rule_test");
    public static final f_2392_k<V_3137_a<PosRuleTestType<?>>> w_1457_N = V_3137_a.n_1700_B("pos_rule_test");
    public static final f_2392_k<V_3137_a<MenuType<?>>> Y_601_j = V_3137_a.n_1700_B("menu");
    public static final f_2392_k<V_3137_a<RecipeType<?>>> Y_259_p = V_3137_a.n_1700_B("recipe_type");
    public static final f_2392_k<V_3137_a<RecipeSerializer<?>>> Q_2552_b = V_3137_a.n_1700_B("recipe_serializer");
    public static final f_2392_k<V_3137_a<Attribute>> C_2741_M = V_3137_a.n_1700_B("attribute");
    public static final f_2392_k<V_3137_a<q_3277_O<?>>> k_2293_S = V_3137_a.n_1700_B("stat_type");
    public static final f_2392_k<V_3137_a<R_3043_n>> q_2307_F = V_3137_a.n_1700_B("villager_type");
    public static final f_2392_k<V_3137_a<VillagerProfession>> Z_875_P = V_3137_a.n_1700_B("villager_profession");
    public static final f_2392_k<V_3137_a<q_2232_A>> c_3005_b = V_3137_a.n_1700_B("point_of_interest_type");
    public static final f_2392_k<V_3137_a<MemoryModuleType<?>>> H_2857_Y = V_3137_a.n_1700_B("memory_module_type");
    public static final f_2392_k<V_3137_a<SensorType<?>>> A_4115_X = V_3137_a.n_1700_B("sensor_type");
    public static final f_2392_k<V_3137_a<Schedule>> Y_1740_V = V_3137_a.n_1700_B("schedule");
    public static final f_2392_k<V_3137_a<Activity>> t_4043_B = V_3137_a.n_1700_B("activity");
    public static final f_2392_k<V_3137_a<d_614_w>> x_607_J = V_3137_a.n_1700_B("loot_pool_entry_type");
    public static final f_2392_k<V_3137_a<B_4977_Y>> e_4240_b = V_3137_a.n_1700_B("loot_function_type");
    public static final f_2392_k<V_3137_a<o_3000_u>> n_3318_d = V_3137_a.n_1700_B("loot_condition_type");
    public static final f_2392_k<V_3137_a<Z_3903_F>> d_2427_y = V_3137_a.n_1700_B("dimension_type");
    public static final f_2392_k<V_3137_a<b_4507_u>> z_1737_N = V_3137_a.n_1700_B("dimension");
    public static final f_2392_k<V_3137_a<LevelStem>> v_4276_D = V_3137_a.n_1700_B("dimension");
    public static final V_3137_a<SoundEvent> d_2461_k = V_3137_a.n_1700_B(P_1922_E, () -> SoundEvents.DeathCoords);
    public static final DefaultedRegistry<Fluid> G_624_v = V_3137_a.n_1700_B(u_1723_Y, "empty", () -> Fluids.n_1700_B);
    public static final V_3137_a<g_422_i> T_2506_i = V_3137_a.n_1700_B(v_4262_N, () -> MobEffects.Z_875_P);
    public static final DefaultedRegistry<T_2915_h> q_4610_l = V_3137_a.n_1700_B(w_1484_f, "air", () -> a_3742_W.n_1700_B);
    public static final V_3137_a<K_1310_v> z_4693_k = V_3137_a.n_1700_B(t_148_a, () -> Enchantments.C_2741_M);
    public static final DefaultedRegistry<t_5_h<?>> g_221_o = V_3137_a.n_1700_B(s_956_w, "pig", () -> t_5_h.A_1038_p);
    public static final DefaultedRegistry<q_1613_l> e_2887_G = V_3137_a.n_1700_B(u_2550_I, "air", () -> Items.n_1700_B);
    public static final DefaultedRegistry<y_528_b> B_1668_F = V_3137_a.n_1700_B(M_588_G, "empty", () -> Potions.n_1700_B);
    public static final V_3137_a<ParticleType<?>> g_164_R = V_3137_a.n_1700_B(P_4830_p, () -> ParticleTypes.G_564_y);
    public static final V_3137_a<BlockEntityType<?>> X_933_l = V_3137_a.n_1700_B(h_1847_R, () -> BlockEntityType.n_1700_B);
    public static final DefaultedRegistry<Motive> Z_976_R = V_3137_a.n_1700_B(Q_4569_t, "kebab", () -> Motive.n_1700_B);
    public static final V_3137_a<g_2336_b> H_1990_U = V_3137_a.n_1700_B(M_182_A, () -> Stats.Y_1740_V);
    public static final DefaultedRegistry<ChunkStatus> N_2525_X = V_3137_a.n_1700_B(t_1786_h, "empty", () -> ChunkStatus.n_1700_B);
    public static final V_3137_a<RuleTestType<?>> c_4037_x = V_3137_a.n_1700_B(multiplayerClientSuggestionProvider, () -> RuleTestType.n_1700_B);
    public static final V_3137_a<PosRuleTestType<?>> g_2268_R = V_3137_a.n_1700_B(w_1457_N, () -> PosRuleTestType.n_1700_B);
    public static final V_3137_a<MenuType<?>> T_3594_S = V_3137_a.n_1700_B(Y_601_j, () -> MenuType.w_1484_f);
    public static final V_3137_a<RecipeType<?>> D_4792_h = V_3137_a.n_1700_B(Y_259_p, () -> RecipeType.n_1700_B);
    public static final V_3137_a<RecipeSerializer<?>> s_2632_s = V_3137_a.n_1700_B(Q_2552_b, () -> RecipeSerializer.J_1907_R);
    public static final V_3137_a<Attribute> l_1233_K = V_3137_a.n_1700_B(C_2741_M, () -> Attributes.u_2550_I);
    public static final V_3137_a<q_3277_O<?>> z_1333_t = V_3137_a.n_1700_B(k_2293_S, () -> Stats.R_4764_Y);
    public static final DefaultedRegistry<R_3043_n> O_508_d = V_3137_a.n_1700_B(q_2307_F, "plains", () -> R_3043_n.R_4764_Y);
    public static final DefaultedRegistry<VillagerProfession> r_715_M = V_3137_a.n_1700_B(Z_875_P, "none", () -> VillagerProfession.n_1700_B);
    public static final DefaultedRegistry<q_2232_A> A_1038_p = V_3137_a.n_1700_B(c_3005_b, "unemployed", () -> q_2232_A.R_4764_Y);
    public static final DefaultedRegistry<MemoryModuleType<?>> i_1637_u = V_3137_a.n_1700_B(H_2857_Y, "dummy", () -> MemoryModuleType.n_1700_B);
    public static final DefaultedRegistry<SensorType<?>> Ping = V_3137_a.n_1700_B(A_4115_X, "dummy", () -> SensorType.n_1700_B);
    public static final V_3137_a<Schedule> p_178_J = V_3137_a.n_1700_B(Y_1740_V, () -> Schedule.n_1700_B);
    public static final V_3137_a<Activity> RealmsClientConfig = V_3137_a.n_1700_B(t_4043_B, () -> Activity.J_1907_R);
    public static final V_3137_a<d_614_w> f_4016_n = V_3137_a.n_1700_B(x_607_J, () -> LootPoolEntries.n_1700_B);
    public static final V_3137_a<B_4977_Y> j_276_v = V_3137_a.n_1700_B(e_4240_b, () -> LootItemFunctions.J_1907_R);
    public static final V_3137_a<o_3000_u> UploadStatus = V_3137_a.n_1700_B(n_3318_d, () -> LootItemConditions.n_1700_B);
    public static final f_2392_k<V_3137_a<G_156_T>> e_1992_r = V_3137_a.n_1700_B("worldgen/noise_settings");
    public static final f_2392_k<V_3137_a<ConfiguredSurfaceBuilder<?>>> D_60_a = V_3137_a.n_1700_B("worldgen/configured_surface_builder");
    public static final f_2392_k<V_3137_a<ConfiguredWorldCarver<?>>> k_3961_g = V_3137_a.n_1700_B("worldgen/configured_carver");
    public static final f_2392_k<V_3137_a<ConfiguredFeature<?, ?>>> Ops = V_3137_a.n_1700_B("worldgen/configured_feature");
    public static final f_2392_k<V_3137_a<ConfiguredStructureFeature<?, ?>>> h_4320_q = V_3137_a.n_1700_B("worldgen/configured_structure_feature");
    public static final f_2392_k<V_3137_a<r_3979_x_0>> t_4219_U = V_3137_a.n_1700_B("worldgen/processor_list");
    public static final f_2392_k<V_3137_a<X_2241_P>> V_1446_Y = V_3137_a.n_1700_B("worldgen/template_pool");
    public static final f_2392_k<V_3137_a<k_594_Q>> PlayerInfo = V_3137_a.n_1700_B("worldgen/biome");
    public static final f_2392_k<V_3137_a<SurfaceBuilder<?>>> V_1225_t = V_3137_a.n_1700_B("worldgen/surface_builder");
    public static final V_3137_a<SurfaceBuilder<?>> U_1241_n = V_3137_a.n_1700_B(V_1225_t, () -> SurfaceBuilder.Q_2552_b);
    public static final f_2392_k<V_3137_a<i_4544_r<?>>> q_1982_R = V_3137_a.n_1700_B("worldgen/carver");
    public static final V_3137_a<i_4544_r<?>> dtoRealmsServerAddress = V_3137_a.n_1700_B(q_1982_R, () -> i_4544_r.n_1700_B);
    public static final f_2392_k<V_3137_a<Feature<?>>> w_612_n = V_3137_a.n_1700_B("worldgen/feature");
    public static final V_3137_a<Feature<?>> RealmsServerPing = V_3137_a.n_1700_B(w_612_n, () -> Feature.c_3005_b);
    public static final f_2392_k<V_3137_a<StructureFeature<?>>> j_1564_a = V_3137_a.n_1700_B("worldgen/structure_feature");
    public static final V_3137_a<StructureFeature<?>> M_1641_O = V_3137_a.n_1700_B(j_1564_a, () -> StructureFeature.R_4764_Y);
    public static final f_2392_k<V_3137_a<StructurePieceType>> RealmsWorldOptions = V_3137_a.n_1700_B("worldgen/structure_piece");
    public static final V_3137_a<StructurePieceType> RealmsWorldResetDto = V_3137_a.n_1700_B(RealmsWorldOptions, () -> StructurePieceType.R_4764_Y);
    public static final f_2392_k<V_3137_a<y_2419_Z<?>>> RegionPingResult = V_3137_a.n_1700_B("worldgen/decorator");
    public static final V_3137_a<y_2419_Z<?>> H_1083_k = V_3137_a.n_1700_B(RegionPingResult, () -> y_2419_Z.n_1700_B);
    public static final f_2392_k<V_3137_a<BlockStateProviderType<?>>> R_3908_n = V_3137_a.n_1700_B("worldgen/block_state_provider_type");
    public static final f_2392_k<V_3137_a<BlockPlacerType<?>>> ValueObject = V_3137_a.n_1700_B("worldgen/block_placer_type");
    public static final f_2392_k<V_3137_a<FoliagePlacerType<?>>> F_1410_V = V_3137_a.n_1700_B("worldgen/foliage_placer_type");
    public static final f_2392_k<V_3137_a<TrunkPlacerType<?>>> S_4022_R = V_3137_a.n_1700_B("worldgen/trunk_placer_type");
    public static final f_2392_k<V_3137_a<TreeDecoratorType<?>>> l_4537_E = V_3137_a.n_1700_B("worldgen/tree_decorator_type");
    public static final f_2392_k<V_3137_a<FeatureSizeType<?>>> F_2624_D = V_3137_a.n_1700_B("worldgen/feature_size_type");
    public static final f_2392_k<V_3137_a<Codec<? extends BiomeSource>>> RealmsDefaultUncaughtExceptionHandler = V_3137_a.n_1700_B("worldgen/biome_source");
    public static final f_2392_k<V_3137_a<Codec<? extends z_1753_f>>> y_1700_S = V_3137_a.n_1700_B("worldgen/chunk_generator");
    public static final f_2392_k<V_3137_a<StructureProcessorType<?>>> u_744_e = V_3137_a.n_1700_B("worldgen/structure_processor");
    public static final f_2392_k<V_3137_a<StructurePoolElementType<?>>> RetryCallException = V_3137_a.n_1700_B("worldgen/structure_pool_element");
    public static final V_3137_a<BlockStateProviderType<?>> r_3651_U = V_3137_a.n_1700_B(R_3908_n, () -> BlockStateProviderType.n_1700_B);
    public static final V_3137_a<BlockPlacerType<?>> RowButton = V_3137_a.n_1700_B(ValueObject, () -> BlockPlacerType.n_1700_B);
    public static final V_3137_a<FoliagePlacerType<?>> LongRunningTask = V_3137_a.n_1700_B(F_1410_V, () -> FoliagePlacerType.n_1700_B);
    public static final V_3137_a<TrunkPlacerType<?>> j_2266_I = V_3137_a.n_1700_B(S_4022_R, () -> TrunkPlacerType.n_1700_B);
    public static final V_3137_a<TreeDecoratorType<?>> S_980_j = V_3137_a.n_1700_B(l_4537_E, () -> TreeDecoratorType.J_1907_R);
    public static final V_3137_a<FeatureSizeType<?>> R_3077_Z = V_3137_a.n_1700_B(F_2624_D, () -> FeatureSizeType.n_1700_B);
    public static final V_3137_a<Codec<? extends BiomeSource>> RealmsScreenWithCallback = V_3137_a.n_1700_B(RealmsDefaultUncaughtExceptionHandler, Lifecycle.stable(), () -> BiomeSource.n_1700_B);
    public static final V_3137_a<Codec<? extends z_1753_f>> M_2677_i = V_3137_a.n_1700_B(y_1700_S, Lifecycle.stable(), () -> z_1753_f.n_1700_B);
    public static final V_3137_a<StructureProcessorType<?>> c_132_F = V_3137_a.n_1700_B(u_744_e, () -> StructureProcessorType.n_1700_B);
    public static final V_3137_a<StructurePoolElementType<?>> g_4106_L = V_3137_a.n_1700_B(RetryCallException, () -> StructurePoolElementType.G_564_y);
    private final f_2392_k<? extends V_3137_a<T>> W_3464_O;
    private final Lifecycle RealmsConfirmScreen;

    private static <T> f_2392_k<V_3137_a<T>> n_1700_B(String name) {
        return f_2392_k.n_1700_B(new g_2336_b(name));
    }

    public static <T extends WritableRegistry<?>> void n_1700_B(WritableRegistry<T> registry) {
        registry.forEach(registryElement -> {
            if (registryElement.G_564_y().isEmpty()) {
                n_1700_B.error("Registry '{}' was empty after loading", (Object)registry.J_1907_R(registryElement));
                if (SharedConstants.G_564_y) {
                    throw new IllegalStateException("Registry: '" + String.valueOf(registry.J_1907_R(registryElement)) + "' is empty, not allowed, fix me!");
                }
            }
            if (registryElement instanceof DefaultedRegistry) {
                g_2336_b resourcelocation = ((DefaultedRegistry)registryElement).n_1700_B();
                Validate.notNull(registryElement.n_1700_B(resourcelocation), (String)("Missing default of DefaultedMappedRegistry: " + String.valueOf(resourcelocation)), (Object[])new Object[0]);
            }
        });
    }

    private static <T> V_3137_a<T> n_1700_B(f_2392_k<? extends V_3137_a<T>> registryKey, Supplier<T> supplier) {
        return V_3137_a.n_1700_B(registryKey, Lifecycle.experimental(), supplier);
    }

    private static <T> DefaultedRegistry<T> n_1700_B(f_2392_k<? extends V_3137_a<T>> registryKey, String defaultedValueKey, Supplier<T> supplier) {
        return V_3137_a.n_1700_B(registryKey, defaultedValueKey, Lifecycle.experimental(), supplier);
    }

    private static <T> V_3137_a<T> n_1700_B(f_2392_k<? extends V_3137_a<T>> registryKey, Lifecycle lifecycle, Supplier<T> supplier) {
        return V_3137_a.n_1700_B(registryKey, new v_1758_J(registryKey, lifecycle), supplier, lifecycle);
    }

    private static <T> DefaultedRegistry<T> n_1700_B(f_2392_k<? extends V_3137_a<T>> registryKey, String defaultedValueKey, Lifecycle lifecycle, Supplier<T> supplier) {
        return V_3137_a.n_1700_B(registryKey, new DefaultedRegistry(defaultedValueKey, registryKey, lifecycle), supplier, lifecycle);
    }

    private static <T, R extends WritableRegistry<T>> R n_1700_B(f_2392_k<? extends V_3137_a<T>> registryKey, R instance, Supplier<T> objectSupplier, Lifecycle lifecycle) {
        g_2336_b resourcelocation = registryKey.n_1700_B();
        RealmsClientOutdatedScreen.put(resourcelocation, objectSupplier);
        WritableRegistry<WritableRegistry<?>> mutableregistry = R_4764_Y;
        return mutableregistry.n_1700_B(registryKey, instance, lifecycle);
    }

    protected V_3137_a(f_2392_k<? extends V_3137_a<T>> registryKey, Lifecycle lifecycle) {
        this.W_3464_O = registryKey;
        this.RealmsConfirmScreen = lifecycle;
    }

    public f_2392_k<? extends V_3137_a<T>> J_1907_R() {
        return this.W_3464_O;
    }

    public String toString() {
        return "Registry[" + String.valueOf(this.W_3464_O) + " (" + String.valueOf(this.RealmsConfirmScreen) + ")]";
    }

    public <U> DataResult<Pair<T, U>> decode(DynamicOps<U> p_decode_1_, U p_decode_2_) {
        return p_decode_1_.compressMaps() ? p_decode_1_.getNumberValue(p_decode_2_).flatMap(registryId -> {
            Object t = this.n_1700_B(registryId.intValue());
            return t == null ? DataResult.error((String)("Unknown registry id: " + String.valueOf(registryId))) : DataResult.success((Object)t, (Lifecycle)this.G_564_y(t));
        }).map(encodedValue -> Pair.of((Object)encodedValue, (Object)p_decode_1_.empty())) : g_2336_b.n_1700_B.decode(p_decode_1_, p_decode_2_).flatMap(encodedRegistryPair -> {
            T t = this.n_1700_B((g_2336_b)encodedRegistryPair.getFirst());
            return t == null ? DataResult.error((String)("Unknown registry key: " + String.valueOf(encodedRegistryPair.getFirst()))) : DataResult.success((Object)Pair.of(t, (Object)encodedRegistryPair.getSecond()), (Lifecycle)this.G_564_y(t));
        });
    }

    public <U> DataResult<U> encode(T p_encode_1_, DynamicOps<U> p_encode_2_, U p_encode_3_) {
        g_2336_b resourcelocation = this.J_1907_R(p_encode_1_);
        if (resourcelocation == null) {
            return DataResult.error((String)("Unknown registry element " + String.valueOf(p_encode_1_)));
        }
        return p_encode_2_.compressMaps() ? p_encode_2_.mergeToPrimitive(p_encode_3_, p_encode_2_.createInt(this.n_1700_B(p_encode_1_))).setLifecycle(this.RealmsConfirmScreen) : p_encode_2_.mergeToPrimitive(p_encode_3_, p_encode_2_.createString(resourcelocation.toString())).setLifecycle(this.RealmsConfirmScreen);
    }

    public <U> Stream<U> keys(DynamicOps<U> p_keys_1_) {
        return this.G_564_y().stream().map(registryID -> p_keys_1_.createString(registryID.toString()));
    }

    @Nullable
    public abstract g_2336_b J_1907_R(T var1);

    public abstract Optional<f_2392_k<T>> R_4764_Y(T var1);

    @Override
    public abstract int n_1700_B(@Nullable T var1);

    @Nullable
    public abstract T n_1700_B(@Nullable f_2392_k<T> var1);

    @Nullable
    public abstract T n_1700_B(@Nullable g_2336_b var1);

    protected abstract Lifecycle G_564_y(T var1);

    public abstract Lifecycle R_4764_Y();

    public Optional<T> J_1907_R(@Nullable g_2336_b id) {
        return Optional.ofNullable(this.n_1700_B(id));
    }

    public Optional<T> J_1907_R(@Nullable f_2392_k<T> registryKey) {
        return Optional.ofNullable(this.n_1700_B(registryKey));
    }

    public T R_4764_Y(f_2392_k<T> key) {
        T t = this.n_1700_B(key);
        if (t == null) {
            throw new IllegalStateException("Missing: " + String.valueOf(key));
        }
        return t;
    }

    public abstract Set<g_2336_b> G_564_y();

    public abstract Set<Map.Entry<f_2392_k<T>, T>> P_1922_E();

    public Stream<T> u_1723_Y() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    public abstract boolean R_4764_Y(g_2336_b var1);

    public static <T> T n_1700_B(V_3137_a<? super T> registry, String identifier, T value) {
        return V_3137_a.n_1700_B(registry, new g_2336_b(identifier), value);
    }

    public static <V, T extends V> T n_1700_B(V_3137_a<V> registry, g_2336_b identifier, T value) {
        return ((WritableRegistry)registry).n_1700_B(f_2392_k.n_1700_B(registry.W_3464_O, identifier), value, Lifecycle.stable());
    }

    public static <V, T extends V> T n_1700_B(V_3137_a<V> registry, int id, String identifier, T value) {
        return ((WritableRegistry)registry).n_1700_B(id, f_2392_k.n_1700_B(registry.W_3464_O, new g_2336_b(identifier)), value, Lifecycle.stable());
    }

    static {
        BuiltinRegistries.n_1700_B();
        RealmsClientOutdatedScreen.forEach((? super K registry, ? super V registrySupplier) -> {
            if (registrySupplier.get() == null) {
                n_1700_B.error("Unable to bootstrap registry '{}'", registry);
            }
        });
        V_3137_a.n_1700_B(R_4764_Y);
    }
}



