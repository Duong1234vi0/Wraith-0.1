/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.serialization.Lifecycle
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.serialization.Lifecycle;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.G_156_T;
import lightning.product.Pools;
import lightning.product.Carvers;
import lightning.product.ConfiguredWorldCarver;
import lightning.product.Features;
import lightning.product.V_3137_a;
import lightning.product.X_2241_P;
import lightning.product.StructureFeatures;
import lightning.product.Biomes;
import lightning.product.ConfiguredFeature;
import lightning.product.ProcessorLists;
import lightning.product.ConfiguredSurfaceBuilder;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.ConfiguredStructureFeature;
import lightning.product.k_594_Q;
import lightning.product.WritableRegistry;
import lightning.product.r_3979_x_0;
import lightning.product.v_1758_J;
import lightning.product.SurfaceBuilders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BuiltinRegistries {
    protected static final Logger n_1700_B = LogManager.getLogger();
    private static final Map<g_2336_b, Supplier<?>> u_2550_I = Maps.newLinkedHashMap();
    private static final WritableRegistry<WritableRegistry<?>> M_588_G = new v_1758_J(f_2392_k.n_1700_B(new g_2336_b("root")), Lifecycle.experimental());
    public static final V_3137_a<? extends V_3137_a<?>> J_1907_R = M_588_G;
    public static final V_3137_a<ConfiguredSurfaceBuilder<?>> R_4764_Y = BuiltinRegistries.n_1700_B(V_3137_a.D_60_a, () -> SurfaceBuilders.M_182_A);
    public static final V_3137_a<ConfiguredWorldCarver<?>> G_564_y = BuiltinRegistries.n_1700_B(V_3137_a.k_3961_g, () -> Carvers.n_1700_B);
    public static final V_3137_a<ConfiguredFeature<?, ?>> P_1922_E = BuiltinRegistries.n_1700_B(V_3137_a.Ops, () -> Features.TextRenderingUtils);
    public static final V_3137_a<ConfiguredStructureFeature<?, ?>> u_1723_Y = BuiltinRegistries.n_1700_B(V_3137_a.h_4320_q, () -> StructureFeatures.J_1907_R);
    public static final V_3137_a<r_3979_x_0> v_4262_N = BuiltinRegistries.n_1700_B(V_3137_a.t_4219_U, () -> ProcessorLists.J_1907_R);
    public static final V_3137_a<X_2241_P> w_1484_f = BuiltinRegistries.n_1700_B(V_3137_a.V_1446_Y, Pools::n_1700_B);
    public static final V_3137_a<k_594_Q> t_148_a = BuiltinRegistries.n_1700_B(V_3137_a.PlayerInfo, () -> Biomes.n_1700_B);
    public static final V_3137_a<G_156_T> s_956_w = BuiltinRegistries.n_1700_B(V_3137_a.e_1992_r, G_156_T::t_148_a);

    private static <T> V_3137_a<T> n_1700_B(f_2392_k<? extends V_3137_a<T>> registryKey, Supplier<T> defaultSupplier) {
        return BuiltinRegistries.n_1700_B(registryKey, Lifecycle.stable(), defaultSupplier);
    }

    private static <T> V_3137_a<T> n_1700_B(f_2392_k<? extends V_3137_a<T>> registryKey, Lifecycle lifecycle, Supplier<T> defaultSupplier) {
        return BuiltinRegistries.n_1700_B(registryKey, new v_1758_J(registryKey, lifecycle), defaultSupplier, lifecycle);
    }

    private static <T, R extends WritableRegistry<T>> R n_1700_B(f_2392_k<? extends V_3137_a<T>> registryKey, R registry, Supplier<T> defaultSupplier, Lifecycle lifecycle) {
        g_2336_b resourcelocation = registryKey.n_1700_B();
        u_2550_I.put(resourcelocation, defaultSupplier);
        WritableRegistry<WritableRegistry<?>> mutableregistry = M_588_G;
        return mutableregistry.n_1700_B(registryKey, registry, lifecycle);
    }

    public static <T> T n_1700_B(V_3137_a<? super T> registry, String id, T value) {
        return BuiltinRegistries.n_1700_B(registry, new g_2336_b(id), value);
    }

    public static <V, T extends V> T n_1700_B(V_3137_a<V> registry, g_2336_b id, T value) {
        return ((WritableRegistry)registry).n_1700_B(f_2392_k.n_1700_B(registry.J_1907_R(), id), value, Lifecycle.stable());
    }

    public static <V, T extends V> T n_1700_B(V_3137_a<V> registry, int index, f_2392_k<V> registryKey, T value) {
        return ((WritableRegistry)registry).n_1700_B(index, registryKey, value, Lifecycle.stable());
    }

    public static void n_1700_B() {
    }

    static {
        u_2550_I.forEach((id, defaultSupplier) -> {
            if (defaultSupplier.get() == null) {
                n_1700_B.error("Unable to bootstrap registry '{}'", id);
            }
        });
        V_3137_a.n_1700_B(M_588_G);
    }
}


