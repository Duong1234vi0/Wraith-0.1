/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.TickList;
import lightning.product.V_4824_J;
import lightning.product.c_1514_x;

public class EmptyTickList<T>
implements TickList<T> {
    private static final EmptyTickList<Object> n_1700_B = new EmptyTickList();

    public static <T> EmptyTickList<T> n_1700_B() {
        return n_1700_B;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, T itemIn) {
        return false;
    }

    @Override
    public void n_1700_B(c_1514_x pos, T itemIn, int scheduledTime) {
    }

    @Override
    public void n_1700_B(c_1514_x pos, T itemIn, int scheduledTime, V_4824_J priority) {
    }

    @Override
    public boolean J_1907_R(c_1514_x pos, T obj) {
        return false;
    }
}


