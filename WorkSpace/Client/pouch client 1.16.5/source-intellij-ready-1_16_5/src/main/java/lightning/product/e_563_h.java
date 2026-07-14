/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicates
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lightning.product.E_4700_p;
import lightning.product.v_3760_Q;

public class e_563_h<T extends Enum<T>>
extends v_3760_Q<T> {
    private final ImmutableSet<T> n_1700_B;
    private final Map<String, T> J_1907_R = Maps.newHashMap();

    protected e_563_h(String name, Class<T> valueClass, Collection<T> allowedValues) {
        super(name, valueClass);
        this.n_1700_B = ImmutableSet.copyOf(allowedValues);
        for (Enum t : allowedValues) {
            String s = ((E_4700_p)((Object)t)).n_1700_B();
            if (this.J_1907_R.containsKey(s)) {
                throw new IllegalArgumentException("Multiple values have the same name '" + s + "'");
            }
            this.J_1907_R.put(s, t);
        }
    }

    @Override
    public Collection<T> n_1700_B() {
        return this.n_1700_B;
    }

    @Override
    public Optional<T> J_1907_R(String value) {
        return Optional.ofNullable((Enum)this.J_1907_R.get(value));
    }

    @Override
    public String n_1700_B(T value) {
        return ((E_4700_p)value).n_1700_B();
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ instanceof e_563_h && super.equals(p_equals_1_)) {
            e_563_h enumproperty = (e_563_h)p_equals_1_;
            return this.n_1700_B.equals(enumproperty.n_1700_B) && this.J_1907_R.equals(enumproperty.J_1907_R);
        }
        return false;
    }

    @Override
    public int J_1907_R() {
        int i = super.J_1907_R();
        i = 31 * i + this.n_1700_B.hashCode();
        return 31 * i + this.J_1907_R.hashCode();
    }

    public static <T extends Enum<T>> e_563_h<T> n_1700_B(String name, Class<T> clazz) {
        return e_563_h.n_1700_B(name, clazz, Predicates.alwaysTrue());
    }

    public static <T extends Enum<T>> e_563_h<T> n_1700_B(String name, Class<T> clazz, Predicate<T> filter) {
        return e_563_h.n_1700_B(name, clazz, Arrays.stream((Enum[])clazz.getEnumConstants()).filter(filter).collect(Collectors.toList()));
    }

    public static <T extends Enum<T>> e_563_h<T> n_1700_B(String name, Class<T> clazz, T ... values) {
        return e_563_h.n_1700_B(name, clazz, Lists.newArrayList((Object[])values));
    }

    public static <T extends Enum<T>> e_563_h<T> n_1700_B(String name, Class<T> clazz, Collection<T> values) {
        return new e_563_h<T>(name, clazz, values);
    }
}

