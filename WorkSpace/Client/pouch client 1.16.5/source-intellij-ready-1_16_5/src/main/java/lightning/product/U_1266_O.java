/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.Optional;
import lightning.product.v_3760_Q;

public class U_1266_O
extends v_3760_Q<Boolean> {
    private final ImmutableSet<Boolean> n_1700_B = ImmutableSet.of((Object)true, (Object)false);

    protected U_1266_O(String name) {
        super(name, Boolean.class);
    }

    @Override
    public Collection<Boolean> n_1700_B() {
        return this.n_1700_B;
    }

    public static U_1266_O n_1700_B(String name) {
        return new U_1266_O(name);
    }

    @Override
    public Optional<Boolean> J_1907_R(String value) {
        return !"true".equals(value) && !"false".equals(value) ? Optional.empty() : Optional.of(Boolean.valueOf(value));
    }

    @Override
    public String n_1700_B(Boolean value) {
        return value.toString();
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ instanceof U_1266_O && super.equals(p_equals_1_)) {
            U_1266_O booleanproperty = (U_1266_O)p_equals_1_;
            return this.n_1700_B.equals(booleanproperty.n_1700_B);
        }
        return false;
    }

    @Override
    public int J_1907_R() {
        return 31 * super.J_1907_R() + this.n_1700_B.hashCode();
    }
}

