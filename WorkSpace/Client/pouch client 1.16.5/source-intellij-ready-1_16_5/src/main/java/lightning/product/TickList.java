/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.V_4824_J;
import lightning.product.c_1514_x;

public interface TickList<T> {
    public boolean n_1700_B(c_1514_x var1, T var2);

    default public void n_1700_B(c_1514_x pos, T itemIn, int scheduledTime) {
        this.n_1700_B(pos, itemIn, scheduledTime, V_4824_J.G_564_y);
    }

    public void n_1700_B(c_1514_x var1, T var2, int var3, V_4824_J var4);

    public boolean J_1907_R(c_1514_x var1, T var2);
}


