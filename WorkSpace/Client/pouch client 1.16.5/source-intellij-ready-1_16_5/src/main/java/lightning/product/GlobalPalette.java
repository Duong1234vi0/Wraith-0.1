/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Predicate;
import lightning.product.b_2585_i;
import lightning.product.Palette;
import lightning.product.q_2896_o;
import lightning.product.w_424_u;

public class GlobalPalette<T>
implements Palette<T> {
    private final w_424_u<T> n_1700_B;
    private final T J_1907_R;

    public GlobalPalette(w_424_u<T> p_i48965_1_, T p_i48965_2_) {
        this.n_1700_B = p_i48965_1_;
        this.J_1907_R = p_i48965_2_;
    }

    @Override
    public int n_1700_B(T state) {
        int i = this.n_1700_B.n_1700_B(state);
        return i == -1 ? 0 : i;
    }

    @Override
    public boolean n_1700_B(Predicate<T> p_230341_1_) {
        return true;
    }

    @Override
    public T n_1700_B(int indexKey) {
        T t = this.n_1700_B.n_1700_B(indexKey);
        return t == null ? this.J_1907_R : t;
    }

    @Override
    public void n_1700_B(b_2585_i buf) {
    }

    @Override
    public void J_1907_R(b_2585_i buf) {
    }

    @Override
    public int n_1700_B() {
        return b_2585_i.n_1700_B(0);
    }

    @Override
    public void n_1700_B(q_2896_o nbt) {
    }
}


