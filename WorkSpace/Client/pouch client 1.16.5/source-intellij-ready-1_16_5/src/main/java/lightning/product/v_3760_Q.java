/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;
import lightning.product.P_1008_U;

public abstract class v_3760_Q<T extends Comparable<T>> {
    private final Class<T> n_1700_B;
    private final String J_1907_R;
    private Integer R_4764_Y;
    private final Codec<T> G_564_y = Codec.STRING.comapFlatMap(p_lambda$new$1_1_ -> this.J_1907_R((String)p_lambda$new$1_1_).map(DataResult::success).orElseGet(() -> DataResult.error((String)("Unable to read property: " + String.valueOf(this) + " with value: " + p_lambda$new$1_1_))), this::n_1700_B);
    private final Codec<n_1700_B<T>> P_1922_E = this.G_564_y.xmap(this::J_1907_R, n_1700_B::J_1907_R);

    protected v_3760_Q(String name, Class<T> valueClass) {
        this.n_1700_B = valueClass;
        this.J_1907_R = name;
    }

    public n_1700_B<T> J_1907_R(T p_241490_1_) {
        return new n_1700_B<T>(this, p_241490_1_);
    }

    public n_1700_B<T> n_1700_B(P_1008_U<?, ?> p_241489_1_) {
        return new n_1700_B(this, p_241489_1_.R_4764_Y(this));
    }

    public Stream<n_1700_B<T>> R_4764_Y() {
        return this.n_1700_B().stream().map(this::J_1907_R);
    }

    public Codec<n_1700_B<T>> G_564_y() {
        return this.P_1922_E;
    }

    public String P_1922_E() {
        return this.J_1907_R;
    }

    public Class<T> u_1723_Y() {
        return this.n_1700_B;
    }

    public abstract Collection<T> n_1700_B();

    public abstract String n_1700_B(T var1);

    public abstract Optional<T> J_1907_R(String var1);

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("name", (Object)this.J_1907_R).add("clazz", this.n_1700_B).add("values", this.n_1700_B()).toString();
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof v_3760_Q)) {
            return false;
        }
        v_3760_Q property = (v_3760_Q)p_equals_1_;
        return this.n_1700_B.equals(property.n_1700_B) && this.J_1907_R.equals(property.J_1907_R);
    }

    public final int hashCode() {
        if (this.R_4764_Y == null) {
            this.R_4764_Y = this.J_1907_R();
        }
        return this.R_4764_Y;
    }

    public int J_1907_R() {
        return 31 * this.n_1700_B.hashCode() + this.J_1907_R.hashCode();
    }

    public static final class n_1700_B<T extends Comparable<T>> {
        private final v_3760_Q<T> n_1700_B;
        private final T J_1907_R;

        private n_1700_B(v_3760_Q<T> p_i232540_1_, T p_i232540_2_) {
            if (!p_i232540_1_.n_1700_B().contains(p_i232540_2_)) {
                throw new IllegalArgumentException("Value " + String.valueOf(p_i232540_2_) + " does not belong to property " + String.valueOf(p_i232540_1_));
            }
            this.n_1700_B = p_i232540_1_;
            this.J_1907_R = p_i232540_2_;
        }

        public v_3760_Q<T> n_1700_B() {
            return this.n_1700_B;
        }

        public T J_1907_R() {
            return this.J_1907_R;
        }

        public String toString() {
            return this.n_1700_B.P_1922_E() + "=" + this.n_1700_B.n_1700_B(this.J_1907_R);
        }

        public boolean equals(Object p_equals_1_) {
            if (this == p_equals_1_) {
                return true;
            }
            if (!(p_equals_1_ instanceof n_1700_B)) {
                return false;
            }
            n_1700_B valuepair = (n_1700_B)p_equals_1_;
            return this.n_1700_B == valuepair.n_1700_B && this.J_1907_R.equals(valuepair.J_1907_R);
        }

        public int hashCode() {
            int i = this.n_1700_B.hashCode();
            return 31 * i + this.J_1907_R.hashCode();
        }
    }
}

