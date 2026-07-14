/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.base.Splitter
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;
import lightning.product.Condition;
import lightning.product.v_3760_Q;

public class KeyValueCondition
implements Condition {
    private static final Splitter R_4764_Y = Splitter.on((char)'|').omitEmptyStrings();
    private final String G_564_y;
    private final String P_1922_E;

    public KeyValueCondition(String keyIn, String valueIn) {
        this.G_564_y = keyIn;
        this.P_1922_E = valueIn;
    }

    @Override
    public Predicate<K_4074_S> getPredicate(Y_1835_y<T_2915_h, K_4074_S> p_getPredicate_1_) {
        Predicate<K_4074_S> predicate;
        List list;
        boolean flag;
        v_3760_Q<?> property = p_getPredicate_1_.n_1700_B(this.G_564_y);
        if (property == null) {
            throw new RuntimeException(String.format("Unknown property '%s' on '%s'", this.G_564_y, p_getPredicate_1_.R_4764_Y().toString()));
        }
        String s = this.P_1922_E;
        boolean bl = flag = !s.isEmpty() && s.charAt(0) == '!';
        if (flag) {
            s = s.substring(1);
        }
        if ((list = R_4764_Y.splitToList((CharSequence)s)).isEmpty()) {
            throw new RuntimeException(String.format("Empty value '%s' for property '%s' on '%s'", this.P_1922_E, this.G_564_y, p_getPredicate_1_.R_4764_Y().toString()));
        }
        if (list.size() == 1) {
            predicate = this.n_1700_B(p_getPredicate_1_, property, s);
        } else {
            List list1 = list.stream().map(value -> this.n_1700_B(p_getPredicate_1_, property, (String)value)).collect(Collectors.toList());
            predicate = state -> list1.stream().anyMatch(pred -> pred.test(state));
        }
        return flag ? predicate.negate() : predicate;
    }

    private Predicate<K_4074_S> n_1700_B(Y_1835_y<T_2915_h, K_4074_S> container, v_3760_Q<?> property, String value) {
        Optional<?> optional = property.J_1907_R(value);
        if (!optional.isPresent()) {
            throw new RuntimeException(String.format("Unknown value '%s' for property '%s' on '%s' in '%s'", value, this.G_564_y, container.R_4764_Y().toString(), this.P_1922_E));
        }
        return state -> state.R_4764_Y(property).equals(optional.get());
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("key", (Object)this.G_564_y).add("value", (Object)this.P_1922_E).toString();
    }
}


