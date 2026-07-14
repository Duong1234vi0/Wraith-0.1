/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import lightning.product.v_3760_Q;

public class g_88_D
extends v_3760_Q<Integer> {
    private final ImmutableSet<Integer> n_1700_B;

    protected g_88_D(String name, int min, int max) {
        super(name, Integer.class);
        if (min < 0) {
            throw new IllegalArgumentException("Min value of " + name + " must be 0 or greater");
        }
        if (max <= min) {
            throw new IllegalArgumentException("Max value of " + name + " must be greater than min (" + min + ")");
        }
        HashSet set = Sets.newHashSet();
        for (int i = min; i <= max; ++i) {
            set.add(i);
        }
        this.n_1700_B = ImmutableSet.copyOf((Collection)set);
    }

    @Override
    public Collection<Integer> n_1700_B() {
        return this.n_1700_B;
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ instanceof g_88_D && super.equals(p_equals_1_)) {
            g_88_D integerproperty = (g_88_D)p_equals_1_;
            return this.n_1700_B.equals(integerproperty.n_1700_B);
        }
        return false;
    }

    @Override
    public int J_1907_R() {
        return 31 * super.J_1907_R() + this.n_1700_B.hashCode();
    }

    public static g_88_D n_1700_B(String name, int min, int max) {
        return new g_88_D(name, min, max);
    }

    @Override
    public Optional<Integer> J_1907_R(String value) {
        try {
            Integer integer = Integer.valueOf(value);
            return this.n_1700_B.contains((Object)integer) ? Optional.of(integer) : Optional.empty();
        }
        catch (NumberFormatException numberformatexception) {
            return Optional.empty();
        }
    }

    @Override
    public String n_1700_B(Integer value) {
        return value.toString();
    }
}

