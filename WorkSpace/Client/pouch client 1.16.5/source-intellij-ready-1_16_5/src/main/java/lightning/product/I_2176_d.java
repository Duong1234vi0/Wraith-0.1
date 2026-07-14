/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Sets
 *  com.google.common.collect.Sets$SetView
 */
package lightning.product;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import lightning.product.I_2011_f;
import lightning.product.g_1866_m;
import lightning.product.q_4394_S;

public class I_2176_d {
    private final Set<I_2011_f<?>> n_1700_B;
    private final Set<I_2011_f<?>> J_1907_R;

    private I_2176_d(Set<I_2011_f<?>> required, Set<I_2011_f<?>> optional) {
        this.n_1700_B = ImmutableSet.copyOf(required);
        this.J_1907_R = ImmutableSet.copyOf((Collection)Sets.union(required, optional));
    }

    public Set<I_2011_f<?>> n_1700_B() {
        return this.n_1700_B;
    }

    public Set<I_2011_f<?>> J_1907_R() {
        return this.J_1907_R;
    }

    public String toString() {
        return "[" + Joiner.on((String)", ").join(this.J_1907_R.stream().map(p_216275_1_ -> (this.n_1700_B.contains(p_216275_1_) ? "!" : "") + String.valueOf(p_216275_1_.n_1700_B())).iterator()) + "]";
    }

    public void n_1700_B(g_1866_m p_227556_1_, q_4394_S p_227556_2_) {
        Set<I_2011_f<?>> set = p_227556_2_.n_1700_B();
        Sets.SetView set1 = Sets.difference(set, this.J_1907_R);
        if (!set1.isEmpty()) {
            p_227556_1_.n_1700_B("Parameters " + String.valueOf(set1) + " are not provided in this context");
        }
    }

    public static class n_1700_B {
        private final Set<I_2011_f<?>> n_1700_B = Sets.newIdentityHashSet();
        private final Set<I_2011_f<?>> J_1907_R = Sets.newIdentityHashSet();

        public n_1700_B n_1700_B(I_2011_f<?> parameter) {
            if (this.J_1907_R.contains(parameter)) {
                throw new IllegalArgumentException("Parameter " + String.valueOf(parameter.n_1700_B()) + " is already optional");
            }
            this.n_1700_B.add(parameter);
            return this;
        }

        public n_1700_B J_1907_R(I_2011_f<?> parameter) {
            if (this.n_1700_B.contains(parameter)) {
                throw new IllegalArgumentException("Parameter " + String.valueOf(parameter.n_1700_B()) + " is already required");
            }
            this.J_1907_R.add(parameter);
            return this;
        }

        public I_2176_d n_1700_B() {
            return new I_2176_d(this.n_1700_B, this.J_1907_R);
        }
    }
}

