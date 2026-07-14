/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Streams
 */
package lightning.product;

import com.google.common.collect.Streams;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;
import lightning.product.Condition;

public class o_1429_S
implements Condition {
    private final Iterable<? extends Condition> R_4764_Y;

    public o_1429_S(Iterable<? extends Condition> conditionsIn) {
        this.R_4764_Y = conditionsIn;
    }

    @Override
    public Predicate<K_4074_S> getPredicate(Y_1835_y<T_2915_h, K_4074_S> p_getPredicate_1_) {
        List list = Streams.stream(this.R_4764_Y).map(condition -> condition.getPredicate(p_getPredicate_1_)).collect(Collectors.toList());
        return state -> list.stream().anyMatch(predicate -> predicate.test(state));
    }
}


