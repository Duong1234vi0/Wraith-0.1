/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.b_2585_i;
import lightning.product.h_256_u;

public interface EntityDataSerializer<T> {
    public void n_1700_B(b_2585_i var1, T var2);

    public T J_1907_R(b_2585_i var1);

    default public h_256_u<T> n_1700_B(int id) {
        return new h_256_u(id, this);
    }

    public T n_1700_B(T var1);
}


