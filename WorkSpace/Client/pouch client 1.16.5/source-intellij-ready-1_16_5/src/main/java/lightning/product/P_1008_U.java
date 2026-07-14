/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ArrayTable
 *  com.google.common.collect.HashBasedTable
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Table
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.MapCodec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.v_3760_Q;

public abstract class P_1008_U<O, S> {
    private static final Function<Map.Entry<v_3760_Q<?>, Comparable<?>>, String> n_1700_B = new Function<Map.Entry<v_3760_Q<?>, Comparable<?>>, String>(){

        public String n_1700_B(@Nullable Map.Entry<v_3760_Q<?>, Comparable<?>> p_apply_1_) {
            if (p_apply_1_ == null) {
                return "<NULL>";
            }
            v_3760_Q<?> property = p_apply_1_.getKey();
            return property.P_1922_E() + "=" + this.n_1700_B(property, p_apply_1_.getValue());
        }

        private <T extends Comparable<T>> String n_1700_B(v_3760_Q<T> p_235905_1_, Comparable<?> p_235905_2_) {
            return p_235905_1_.n_1700_B(p_235905_2_);
        }

        @Override
        public /* synthetic */ Object apply(@Nullable Object object) {
            return this.n_1700_B((Map.Entry)object);
        }
    };
    protected final O R_4764_Y;
    private final ImmutableMap<v_3760_Q<?>, Comparable<?>> J_1907_R;
    private Table<v_3760_Q<?>, Comparable<?>, S> P_1922_E;
    protected final MapCodec<S> G_564_y;

    protected P_1008_U(O p_i231879_1_, ImmutableMap<v_3760_Q<?>, Comparable<?>> p_i231879_2_, MapCodec<S> p_i231879_3_) {
        this.R_4764_Y = p_i231879_1_;
        this.J_1907_R = p_i231879_2_;
        this.G_564_y = p_i231879_3_;
    }

    public <T extends Comparable<T>> S n_1700_B(v_3760_Q<T> p_235896_1_) {
        return this.n_1700_B(p_235896_1_, (Comparable)P_1008_U.n_1700_B(p_235896_1_.n_1700_B(), this.R_4764_Y(p_235896_1_)));
    }

    protected static <T> T n_1700_B(Collection<T> p_235898_0_, T p_235898_1_) {
        Iterator<T> iterator = p_235898_0_.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().equals(p_235898_1_)) continue;
            if (iterator.hasNext()) {
                return iterator.next();
            }
            return p_235898_0_.iterator().next();
        }
        return iterator.next();
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(this.R_4764_Y);
        if (!this.q_2307_F().isEmpty()) {
            stringbuilder.append('[');
            stringbuilder.append(this.q_2307_F().entrySet().stream().map(n_1700_B).collect(Collectors.joining(",")));
            stringbuilder.append(']');
        }
        return stringbuilder.toString();
    }

    public Collection<v_3760_Q> k_2293_S() {
        return Collections.unmodifiableCollection(this.J_1907_R.keySet());
    }

    public <T extends Comparable<T>> boolean J_1907_R(v_3760_Q<T> property) {
        return this.J_1907_R.containsKey(property);
    }

    public <T extends Comparable<T>> T R_4764_Y(v_3760_Q<T> property) {
        Comparable comparable = (Comparable)this.J_1907_R.get(property);
        if (comparable == null) {
            throw new IllegalArgumentException("Cannot get property " + String.valueOf(property) + " as it does not exist in " + String.valueOf(this.R_4764_Y));
        }
        return (T)((Comparable)property.u_1723_Y().cast(comparable));
    }

    public <T extends Comparable<T>> Optional<T> G_564_y(v_3760_Q<T> p_235903_1_) {
        Comparable comparable = (Comparable)this.J_1907_R.get(p_235903_1_);
        return comparable == null ? Optional.empty() : Optional.of((Comparable)p_235903_1_.u_1723_Y().cast(comparable));
    }

    public <T extends Comparable<T>, V extends T> S n_1700_B(v_3760_Q<T> property, V value) {
        Comparable comparable = (Comparable)this.J_1907_R.get(property);
        if (comparable == null) {
            throw new IllegalArgumentException("Cannot set property " + String.valueOf(property) + " as it does not exist in " + String.valueOf(this.R_4764_Y));
        }
        if (comparable == value) {
            return (S)this;
        }
        Object s = this.P_1922_E.get(property, value);
        if (s == null) {
            throw new IllegalArgumentException("Cannot set property " + String.valueOf(property) + " to " + String.valueOf(value) + " on " + String.valueOf(this.R_4764_Y) + ", it is not an allowed value");
        }
        return (S)s;
    }

    public void n_1700_B(Map<Map<v_3760_Q<?>, Comparable<?>>, S> p_235899_1_) {
        if (this.P_1922_E != null) {
            throw new IllegalStateException();
        }
        HashBasedTable table = HashBasedTable.create();
        for (Map.Entry entry : this.J_1907_R.entrySet()) {
            v_3760_Q property = (v_3760_Q)entry.getKey();
            for (Comparable comparable : property.n_1700_B()) {
                if (comparable == entry.getValue()) continue;
                table.put((Object)property, (Object)comparable, p_235899_1_.get(this.J_1907_R(property, comparable)));
            }
        }
        this.P_1922_E = table.isEmpty() ? table : ArrayTable.create((Table)table);
    }

    private Map<v_3760_Q<?>, Comparable<?>> J_1907_R(v_3760_Q<?> p_235902_1_, Comparable<?> p_235902_2_) {
        HashMap map = Maps.newHashMap(this.J_1907_R);
        map.put(p_235902_1_, p_235902_2_);
        return map;
    }

    public ImmutableMap<v_3760_Q<?>, Comparable<?>> q_2307_F() {
        return this.J_1907_R;
    }

    protected static <O, S extends P_1008_U<O, S>> Codec<S> n_1700_B(Codec<O> p_235897_0_, Function<O, S> p_235897_1_) {
        return p_235897_0_.dispatch("Name", p_235895_0_ -> p_235895_0_.R_4764_Y, p_235900_1_ -> {
            P_1008_U s = (P_1008_U)p_235897_1_.apply(p_235900_1_);
            return s.q_2307_F().isEmpty() ? Codec.unit((Object)s) : s.G_564_y.fieldOf("Properties").codec();
        });
    }
}

