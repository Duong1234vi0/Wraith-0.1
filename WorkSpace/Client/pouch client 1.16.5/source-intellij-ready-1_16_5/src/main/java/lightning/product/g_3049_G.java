/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;
import lightning.product.v_3760_Q;

public class g_3049_G
implements Predicate<K_4074_S> {
    public static final Predicate<K_4074_S> n_1700_B = state -> true;
    private final Y_1835_y<T_2915_h, K_4074_S> J_1907_R;
    private final Map<v_3760_Q<?>, Predicate<Object>> R_4764_Y = Maps.newHashMap();

    private g_3049_G(Y_1835_y<T_2915_h, K_4074_S> blockStateIn) {
        this.J_1907_R = blockStateIn;
    }

    public static g_3049_G n_1700_B(T_2915_h blockIn) {
        return new g_3049_G(blockIn.t_1786_h());
    }

    public boolean n_1700_B(@Nullable K_4074_S p_test_1_) {
        if (p_test_1_ != null && p_test_1_.J_1907_R().equals(this.J_1907_R.R_4764_Y())) {
            if (this.R_4764_Y.isEmpty()) {
                return true;
            }
            for (Map.Entry<v_3760_Q<?>, Predicate<Object>> entry : this.R_4764_Y.entrySet()) {
                if (this.n_1700_B(p_test_1_, entry.getKey(), entry.getValue())) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    protected <T extends Comparable<T>> boolean n_1700_B(K_4074_S blockState, v_3760_Q<T> property, Predicate<Object> predicate) {
        T t = blockState.R_4764_Y(property);
        return predicate.test(t);
    }

    public <V extends Comparable<V>> g_3049_G n_1700_B(v_3760_Q<V> property, Predicate<Object> is) {
        if (!this.J_1907_R.G_564_y().contains(property)) {
            throw new IllegalArgumentException(String.valueOf(this.J_1907_R) + " cannot support property " + String.valueOf(property));
        }
        this.R_4764_Y.put(property, is);
        return this;
    }

    @Override
    public /* synthetic */ boolean test(@Nullable Object object) {
        return this.n_1700_B((K_4074_S)object);
    }
}

