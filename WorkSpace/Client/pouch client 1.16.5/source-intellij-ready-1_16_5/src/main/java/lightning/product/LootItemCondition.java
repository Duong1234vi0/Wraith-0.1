/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Predicate;
import lightning.product.W_1923_h;
import lightning.product.f_3980_l;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.q_4394_S;

public interface LootItemCondition
extends Predicate<q_1704_m>,
q_4394_S {
    public o_3000_u J_1907_R();

    @FunctionalInterface
    public static interface n_1700_B {
        public LootItemCondition build();

        default public n_1700_B n_1700_B() {
            return W_1923_h.n_1700_B(this);
        }

        default public f_3980_l.n_1700_B n_1700_B(n_1700_B builderIn) {
            return f_3980_l.n_1700_B(this, builderIn);
        }
    }
}


