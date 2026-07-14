/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Iterators
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.Lifecycle
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectList
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenCustomHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.M_3100_a;
import lightning.product.V_3137_a;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.WritableRegistry;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class v_1758_J<T>
extends WritableRegistry<T> {
    protected static final Logger RealmsClientOutdatedScreen = LogManager.getLogger();
    private final ObjectList<T> RealmsConfirmScreen = new ObjectArrayList(256);
    private final Object2IntMap<T> RealmsCreateRealmScreen = new Object2IntOpenCustomHashMap(j_3341_s.u_2550_I());
    private final BiMap<g_2336_b, T> C_290_v;
    private final BiMap<f_2392_k<T>, T> w_728_N;
    private final Map<T, Lifecycle> J_4256_G;
    private Lifecycle RealmsLongConfirmationScreen;
    protected Object[] W_3464_O;
    private int RealmsLongRunningMcoTaskScreen;

    public v_1758_J(f_2392_k<? extends V_3137_a<T>> registryKey, Lifecycle lifecycle) {
        super(registryKey, lifecycle);
        this.RealmsCreateRealmScreen.defaultReturnValue(-1);
        this.C_290_v = HashBiMap.create();
        this.w_728_N = HashBiMap.create();
        this.J_4256_G = Maps.newIdentityHashMap();
        this.RealmsLongConfirmationScreen = lifecycle;
    }

    public static <T> MapCodec<n_1700_B<T>> n_1700_B(f_2392_k<? extends V_3137_a<T>> registryKey, MapCodec<T> mapCodec) {
        return RecordCodecBuilder.mapCodec(builder -> builder.group((App)g_2336_b.n_1700_B.xmap(f_2392_k.J_1907_R(registryKey), f_2392_k::n_1700_B).fieldOf("name").forGetter(entry -> entry.n_1700_B), (App)Codec.INT.fieldOf("id").forGetter(entry -> entry.J_1907_R), (App)mapCodec.forGetter(entry -> entry.R_4764_Y)).apply((Applicative)builder, n_1700_B::new));
    }

    @Override
    public <V extends T> V n_1700_B(int id, f_2392_k<T> name, V instance, Lifecycle lifecycle) {
        return this.n_1700_B(id, name, instance, lifecycle, true);
    }

    private <V extends T> V n_1700_B(int index, f_2392_k<T> registryKey, V value, Lifecycle lifecycle, boolean logDuplicateKeys) {
        Validate.notNull(registryKey);
        Validate.notNull(value);
        this.RealmsConfirmScreen.size(Math.max(this.RealmsConfirmScreen.size(), index + 1));
        this.RealmsConfirmScreen.set(index, value);
        this.RealmsCreateRealmScreen.put(value, index);
        this.W_3464_O = null;
        if (logDuplicateKeys && this.w_728_N.containsKey(registryKey)) {
            RealmsClientOutdatedScreen.debug("Adding duplicate key '{}' to registry", registryKey);
        }
        if (this.C_290_v.containsValue(value)) {
            RealmsClientOutdatedScreen.error("Adding duplicate value '{}' to registry", value);
        }
        this.C_290_v.put((Object)registryKey.n_1700_B(), value);
        this.w_728_N.put(registryKey, value);
        this.J_4256_G.put(value, lifecycle);
        this.RealmsLongConfirmationScreen = this.RealmsLongConfirmationScreen.add(lifecycle);
        if (this.RealmsLongRunningMcoTaskScreen <= index) {
            this.RealmsLongRunningMcoTaskScreen = index + 1;
        }
        return value;
    }

    @Override
    public <V extends T> V n_1700_B(f_2392_k<T> name, V instance, Lifecycle lifecycle) {
        return this.n_1700_B(this.RealmsLongRunningMcoTaskScreen, name, instance, lifecycle);
    }

    @Override
    public <V extends T> V n_1700_B(OptionalInt index, f_2392_k<T> registryKey, V value, Lifecycle lifecycle) {
        int i;
        Validate.notNull(registryKey);
        Validate.notNull(value);
        Object t = this.w_728_N.get(registryKey);
        if (t == null) {
            i = index.isPresent() ? index.getAsInt() : this.RealmsLongRunningMcoTaskScreen;
        } else {
            i = this.RealmsCreateRealmScreen.getInt(t);
            if (index.isPresent() && index.getAsInt() != i) {
                throw new IllegalStateException("ID mismatch");
            }
            this.RealmsCreateRealmScreen.removeInt(t);
            this.J_4256_G.remove(t);
        }
        return this.n_1700_B(i, registryKey, value, lifecycle, false);
    }

    @Override
    @Nullable
    public g_2336_b J_1907_R(T value) {
        return (g_2336_b)this.C_290_v.inverse().get(value);
    }

    @Override
    public Optional<f_2392_k<T>> R_4764_Y(T value) {
        return Optional.ofNullable((f_2392_k)this.w_728_N.inverse().get(value));
    }

    @Override
    public int n_1700_B(@Nullable T value) {
        return this.RealmsCreateRealmScreen.getInt(value);
    }

    @Override
    @Nullable
    public T n_1700_B(@Nullable f_2392_k<T> key) {
        return (T)this.w_728_N.get(key);
    }

    @Override
    @Nullable
    public T n_1700_B(int value) {
        return (T)(value >= 0 && value < this.RealmsConfirmScreen.size() ? this.RealmsConfirmScreen.get(value) : null);
    }

    @Override
    public Lifecycle G_564_y(T object) {
        return this.J_4256_G.get(object);
    }

    @Override
    public Lifecycle R_4764_Y() {
        return this.RealmsLongConfirmationScreen;
    }

    @Override
    public Iterator<T> iterator() {
        return Iterators.filter((Iterator)this.RealmsConfirmScreen.iterator(), Objects::nonNull);
    }

    @Override
    @Nullable
    public T n_1700_B(@Nullable g_2336_b name) {
        return (T)this.C_290_v.get((Object)name);
    }

    @Override
    public Set<g_2336_b> G_564_y() {
        return Collections.unmodifiableSet(this.C_290_v.keySet());
    }

    @Override
    public Set<Map.Entry<f_2392_k<T>, T>> P_1922_E() {
        return Collections.unmodifiableMap(this.w_728_N).entrySet();
    }

    @Nullable
    public T n_1700_B(Random random) {
        if (this.W_3464_O == null) {
            Set collection = this.C_290_v.values();
            if (collection.isEmpty()) {
                return null;
            }
            this.W_3464_O = collection.toArray(new Object[collection.size()]);
        }
        return (T)j_3341_s.n_1700_B(this.W_3464_O, random);
    }

    @Override
    public boolean R_4764_Y(g_2336_b name) {
        return this.C_290_v.containsKey((Object)name);
    }

    public static <T> Codec<v_1758_J<T>> n_1700_B(f_2392_k<? extends V_3137_a<T>> registryKey, Lifecycle lifecycle, Codec<T> codec) {
        return v_1758_J.n_1700_B(registryKey, codec.fieldOf("element")).codec().listOf().xmap(entries -> {
            v_1758_J simpleregistry = new v_1758_J(registryKey, lifecycle);
            for (n_1700_B entry : entries) {
                simpleregistry.n_1700_B(entry.J_1907_R, entry.n_1700_B, entry.R_4764_Y, lifecycle);
            }
            return simpleregistry;
        }, registry -> {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (Object t : registry) {
                builder.add(new n_1700_B(registry.R_4764_Y(t).get(), registry.n_1700_B(t), t));
            }
            return builder.build();
        });
    }

    public static <T> Codec<v_1758_J<T>> J_1907_R(f_2392_k<? extends V_3137_a<T>> registryKey, Lifecycle lifecycle, Codec<T> mapCodec) {
        return M_3100_a.n_1700_B(registryKey, lifecycle, mapCodec);
    }

    public static <T> Codec<v_1758_J<T>> R_4764_Y(f_2392_k<? extends V_3137_a<T>> registryKey, Lifecycle lifecycle, Codec<T> mapCodec) {
        return Codec.unboundedMap((Codec)g_2336_b.n_1700_B.xmap(f_2392_k.J_1907_R(registryKey), f_2392_k::n_1700_B), mapCodec).xmap(registryMap -> {
            v_1758_J simpleregistry = new v_1758_J(registryKey, lifecycle);
            registryMap.forEach((? super K key, ? super V value) -> simpleregistry.n_1700_B((f_2392_k)key, (Object)value, lifecycle));
            return simpleregistry;
        }, registry -> ImmutableMap.copyOf(registry.w_728_N));
    }

    public static class n_1700_B<T> {
        public final f_2392_k<T> n_1700_B;
        public final int J_1907_R;
        public final T R_4764_Y;

        public n_1700_B(f_2392_k<T> name, int index, T value) {
            this.n_1700_B = name;
            this.J_1907_R = index;
            this.R_4764_Y = value;
        }
    }
}


