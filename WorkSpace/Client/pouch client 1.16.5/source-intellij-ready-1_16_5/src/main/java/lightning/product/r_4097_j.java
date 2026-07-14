/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.JsonOps
 *  com.mojang.serialization.Lifecycle
 *  com.mojang.serialization.codecs.UnboundedMapCodec
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.codecs.UnboundedMapCodec;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.F_877_l;
import lightning.product.G_156_T;
import lightning.product.ConfiguredWorldCarver;
import lightning.product.V_3137_a;
import lightning.product.BuiltinRegistries;
import lightning.product.X_2241_P;
import lightning.product.Z_3903_F;
import lightning.product.ConfiguredFeature;
import lightning.product.ConfiguredSurfaceBuilder;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.ConfiguredStructureFeature;
import lightning.product.k_594_Q;
import lightning.product.WritableRegistry;
import lightning.product.v_1758_J;
import lightning.product.StructureProcessorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class r_4097_j {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Map<f_2392_k<? extends V_3137_a<?>>, n_1700_B<?>> J_1907_R = (Map)j_3341_s.n_1700_B(() -> {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        r_4097_j.n_1700_B(builder, V_3137_a.d_2427_y, Z_3903_F.G_564_y, Z_3903_F.G_564_y);
        r_4097_j.n_1700_B(builder, V_3137_a.PlayerInfo, k_594_Q.J_1907_R, k_594_Q.R_4764_Y);
        r_4097_j.n_1700_B(builder, V_3137_a.D_60_a, ConfiguredSurfaceBuilder.n_1700_B);
        r_4097_j.n_1700_B(builder, V_3137_a.k_3961_g, ConfiguredWorldCarver.n_1700_B);
        r_4097_j.n_1700_B(builder, V_3137_a.Ops, ConfiguredFeature.n_1700_B);
        r_4097_j.n_1700_B(builder, V_3137_a.h_4320_q, ConfiguredStructureFeature.n_1700_B);
        r_4097_j.n_1700_B(builder, V_3137_a.t_4219_U, StructureProcessorType.M_588_G);
        r_4097_j.n_1700_B(builder, V_3137_a.V_1446_Y, X_2241_P.n_1700_B);
        r_4097_j.n_1700_B(builder, V_3137_a.e_1992_r, G_156_T.n_1700_B);
        return builder.build();
    });
    private static final J_1907_R R_4764_Y = j_3341_s.n_1700_B(() -> {
        J_1907_R dynamicregistries$impl = new J_1907_R();
        Z_3903_F.n_1700_B(dynamicregistries$impl);
        J_1907_R.keySet().stream().filter(registryKey -> !registryKey.equals(V_3137_a.d_2427_y)).forEach(registerKey -> r_4097_j.n_1700_B(dynamicregistries$impl, registerKey));
        return dynamicregistries$impl;
    });

    public abstract <E> Optional<WritableRegistry<E>> n_1700_B(f_2392_k<? extends V_3137_a<E>> var1);

    public <E> WritableRegistry<E> J_1907_R(f_2392_k<? extends V_3137_a<E>> registryKey) {
        return this.n_1700_B(registryKey).orElseThrow(() -> new IllegalStateException("Missing registry: " + String.valueOf(registryKey)));
    }

    public V_3137_a<Z_3903_F> n_1700_B() {
        return this.J_1907_R(V_3137_a.d_2427_y);
    }

    private static <E> void n_1700_B(ImmutableMap.Builder<f_2392_k<? extends V_3137_a<?>>, n_1700_B<?>> codecHolder, f_2392_k<? extends V_3137_a<E>> registryKey, Codec<E> codec) {
        codecHolder.put(registryKey, new n_1700_B<E>(registryKey, codec, null));
    }

    private static <E> void n_1700_B(ImmutableMap.Builder<f_2392_k<? extends V_3137_a<?>>, n_1700_B<?>> codecHolder, f_2392_k<? extends V_3137_a<E>> registryKey, Codec<E> codec, Codec<E> codec2) {
        codecHolder.put(registryKey, new n_1700_B<E>(registryKey, codec, codec2));
    }

    public static J_1907_R J_1907_R() {
        J_1907_R dynamicregistries$impl = new J_1907_R();
        F_877_l.n_1700_B.n_1700_B worldsettingsimport$iresourceaccess$registryaccess = new F_877_l.n_1700_B.n_1700_B();
        for (n_1700_B<?> codecholder : J_1907_R.values()) {
            r_4097_j.n_1700_B(dynamicregistries$impl, worldsettingsimport$iresourceaccess$registryaccess, codecholder);
        }
        F_877_l.n_1700_B(JsonOps.INSTANCE, worldsettingsimport$iresourceaccess$registryaccess, dynamicregistries$impl);
        return dynamicregistries$impl;
    }

    private static <E> void n_1700_B(J_1907_R dynamicRegistries, F_877_l.n_1700_B.n_1700_B registryAccess, n_1700_B<E> codecHolder) {
        f_2392_k<V_3137_a<E>> registrykey = codecHolder.n_1700_B();
        boolean flag = !registrykey.equals(V_3137_a.e_1992_r) && !registrykey.equals(V_3137_a.d_2427_y);
        WritableRegistry<E> registry = R_4764_Y.J_1907_R(registrykey);
        WritableRegistry<E> mutableregistry = dynamicRegistries.J_1907_R(registrykey);
        for (Map.Entry entry : registry.P_1922_E()) {
            Object e = entry.getValue();
            if (flag) {
                registryAccess.n_1700_B(R_4764_Y, entry.getKey(), codecHolder.J_1907_R(), registry.n_1700_B(e), e, registry.G_564_y(e));
                continue;
            }
            mutableregistry.n_1700_B(registry.n_1700_B(e), entry.getKey(), e, registry.G_564_y(e));
        }
    }

    private static <R extends V_3137_a<?>> void n_1700_B(J_1907_R dynamicRegistries, f_2392_k<R> key) {
        V_3137_a<V_3137_a<?>> registry = BuiltinRegistries.J_1907_R;
        V_3137_a<?> registry1 = registry.n_1700_B(key);
        if (registry1 == null) {
            throw new IllegalStateException("Missing builtin registry: " + String.valueOf(key));
        }
        r_4097_j.n_1700_B(dynamicRegistries, registry1);
    }

    private static <E> void n_1700_B(J_1907_R dynamicRegistries, V_3137_a<E> registry) {
        WritableRegistry<E> mutableregistry = dynamicRegistries.n_1700_B(registry.J_1907_R()).orElseThrow(() -> new IllegalStateException("Missing registry: " + String.valueOf(registry.J_1907_R())));
        for (Map.Entry<f_2392_k<E>, E> entry : registry.P_1922_E()) {
            E e = entry.getValue();
            mutableregistry.n_1700_B(registry.n_1700_B(e), entry.getKey(), e, registry.G_564_y(e));
        }
    }

    public static void n_1700_B(J_1907_R dynamicRegistries, F_877_l<?> settingsImport) {
        for (n_1700_B<?> codecholder : J_1907_R.values()) {
            r_4097_j.n_1700_B(settingsImport, dynamicRegistries, codecholder);
        }
    }

    private static <E> void n_1700_B(F_877_l<?> settingsImport, J_1907_R dynamicRegistries, n_1700_B<E> codecHolder) {
        f_2392_k registrykey = codecHolder.n_1700_B();
        v_1758_J simpleregistry = Optional.ofNullable(dynamicRegistries.J_1907_R.get(registrykey)).map(simpleRegistry -> simpleRegistry).orElseThrow(() -> new IllegalStateException("Missing registry: " + String.valueOf(registrykey)));
        DataResult<v_1758_J<E>> dataresult = settingsImport.n_1700_B(simpleregistry, codecHolder.n_1700_B(), codecHolder.J_1907_R());
        dataresult.error().ifPresent(result -> n_1700_B.error("Error loading registry data: {}", (Object)result.message()));
    }

    static final class n_1700_B<E> {
        private final f_2392_k<? extends V_3137_a<E>> n_1700_B;
        private final Codec<E> J_1907_R;
        @Nullable
        private final Codec<E> R_4764_Y;

        public n_1700_B(f_2392_k<? extends V_3137_a<E>> registryKey, Codec<E> registryCodec, @Nullable Codec<E> packetCodec) {
            this.n_1700_B = registryKey;
            this.J_1907_R = registryCodec;
            this.R_4764_Y = packetCodec;
        }

        public f_2392_k<? extends V_3137_a<E>> n_1700_B() {
            return this.n_1700_B;
        }

        public Codec<E> J_1907_R() {
            return this.J_1907_R;
        }

        @Nullable
        public Codec<E> R_4764_Y() {
            return this.R_4764_Y;
        }

        public boolean G_564_y() {
            return this.R_4764_Y != null;
        }
    }

    public static final class J_1907_R
    extends r_4097_j {
        public static final Codec<J_1907_R> n_1700_B = lightning.product.r_4097_j$J_1907_R.R_4764_Y();
        private final Map<? extends f_2392_k<? extends V_3137_a<?>>, ? extends v_1758_J<?>> J_1907_R;

        private static <E> Codec<J_1907_R> R_4764_Y() {
            Codec codec = g_2336_b.n_1700_B.xmap(f_2392_k::n_1700_B, f_2392_k::n_1700_B);
            Codec codec1 = codec.partialDispatch("type", simpleRegistry -> DataResult.success(simpleRegistry.J_1907_R()), registryKey -> lightning.product.r_4097_j$J_1907_R.R_4764_Y(registryKey).map(codec2 -> v_1758_J.n_1700_B(registryKey, Lifecycle.experimental(), codec2)));
            UnboundedMapCodec unboundedmapcodec = Codec.unboundedMap((Codec)codec, (Codec)codec1);
            return lightning.product.r_4097_j$J_1907_R.n_1700_B(unboundedmapcodec);
        }

        private static <K extends f_2392_k<? extends V_3137_a<?>>, V extends v_1758_J<?>> Codec<J_1907_R> n_1700_B(UnboundedMapCodec<K, V> unboundedCodec) {
            return unboundedCodec.xmap(J_1907_R::new, dynamicRegistries -> (Map)dynamicRegistries.J_1907_R.entrySet().stream().filter(entry -> J_1907_R.get(entry.getKey()).G_564_y()).collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, Map.Entry::getValue)));
        }

        private static <E> DataResult<? extends Codec<E>> R_4764_Y(f_2392_k<? extends V_3137_a<E>> registryKey) {
            return Optional.ofNullable(J_1907_R.get(registryKey)).map(codecHolder -> codecHolder.R_4764_Y()).map(DataResult::success).orElseGet(() -> DataResult.error((String)("Unknown or not serializable registry: " + String.valueOf(registryKey))));
        }

        public J_1907_R() {
            this(J_1907_R.keySet().stream().collect(Collectors.toMap(Function.identity(), J_1907_R::G_564_y)));
        }

        private J_1907_R(Map<? extends f_2392_k<? extends V_3137_a<?>>, ? extends v_1758_J<?>> keyToSimpleRegistryMap) {
            this.J_1907_R = keyToSimpleRegistryMap;
        }

        private static <E> v_1758_J<?> G_564_y(f_2392_k<? extends V_3137_a<?>> registerKey) {
            return new v_1758_J(registerKey, Lifecycle.stable());
        }

        @Override
        public <E> Optional<WritableRegistry<E>> n_1700_B(f_2392_k<? extends V_3137_a<E>> p_230521_1_) {
            return Optional.ofNullable((WritableRegistry)this.J_1907_R.get(p_230521_1_)).map(mutable -> mutable);
        }
    }
}


