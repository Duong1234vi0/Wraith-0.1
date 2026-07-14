/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Function;
import lightning.product.TickList;
import lightning.product.V_4824_J;
import lightning.product.c_1514_x;

public class WorldGenTickList<T>
implements TickList<T> {
    private final Function<c_1514_x, TickList<T>> n_1700_B;

    public WorldGenTickList(Function<c_1514_x, TickList<T>> tickListProviderIn) {
        this.n_1700_B = tickListProviderIn;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, T itemIn) {
        return this.n_1700_B.apply(pos).n_1700_B(pos, itemIn);
    }

    @Override
    public void n_1700_B(c_1514_x pos, T itemIn, int scheduledTime, V_4824_J priority) {
        this.n_1700_B.apply(pos).n_1700_B(pos, itemIn, scheduledTime, priority);
    }

    @Override
    public boolean J_1907_R(c_1514_x pos, T obj) {
        return false;
    }
}


