/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import lightning.product.B_4977_Y;
import lightning.product.Z_1993_T;
import lightning.product.q_1704_m;
import lightning.product.q_4394_S;

public interface A_2178_U
extends BiFunction<Z_1993_T, q_1704_m, Z_1993_T>,
q_4394_S {
    public B_4977_Y J_1907_R();

    public static Consumer<Z_1993_T> n_1700_B(BiFunction<Z_1993_T, q_1704_m, Z_1993_T> p_215858_0_, Consumer<Z_1993_T> stackConsumer, q_1704_m context) {
        return stack -> stackConsumer.accept((Z_1993_T)p_215858_0_.apply((Z_1993_T)stack, context));
    }

    public static interface n_1700_B {
        public A_2178_U u_1723_Y();
    }
}

