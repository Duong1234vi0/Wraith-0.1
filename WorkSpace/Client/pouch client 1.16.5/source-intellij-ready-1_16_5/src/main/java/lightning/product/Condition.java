/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Predicate;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;

@FunctionalInterface
public interface Condition {
    public static final Condition n_1700_B = container -> state -> true;
    public static final Condition J_1907_R = container -> state -> false;

    public Predicate<K_4074_S> getPredicate(Y_1835_y<T_2915_h, K_4074_S> var1);
}


