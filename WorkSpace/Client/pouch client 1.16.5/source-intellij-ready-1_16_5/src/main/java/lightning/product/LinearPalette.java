/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.U_2912_j;
import lightning.product.PaletteResize;
import lightning.product.b_2585_i;
import lightning.product.Palette;
import lightning.product.q_2896_o;
import lightning.product.w_424_u;

public class LinearPalette<T>
implements Palette<T> {
    private final w_424_u<T> n_1700_B;
    private final T[] J_1907_R;
    private final PaletteResize<T> R_4764_Y;
    private final Function<U_2912_j, T> G_564_y;
    private final int P_1922_E;
    private int u_1723_Y;

    public LinearPalette(w_424_u<T> registryIn, int bitsIn, PaletteResize<T> resizeHandlerIn, Function<U_2912_j, T> deserializerIn) {
        this.n_1700_B = registryIn;
        this.J_1907_R = new Object[1 << bitsIn];
        this.P_1922_E = bitsIn;
        this.R_4764_Y = resizeHandlerIn;
        this.G_564_y = deserializerIn;
    }

    @Override
    public int n_1700_B(T state) {
        int j;
        for (int i = 0; i < this.u_1723_Y; ++i) {
            if (this.J_1907_R[i] != state) continue;
            return i;
        }
        if ((j = this.u_1723_Y++) < this.J_1907_R.length) {
            this.J_1907_R[j] = state;
            return j;
        }
        return this.R_4764_Y.onResize(this.P_1922_E + 1, state);
    }

    @Override
    public boolean n_1700_B(Predicate<T> p_230341_1_) {
        for (int i = 0; i < this.u_1723_Y; ++i) {
            if (!p_230341_1_.test(this.J_1907_R[i])) continue;
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public T n_1700_B(int indexKey) {
        return indexKey >= 0 && indexKey < this.u_1723_Y ? (T)this.J_1907_R[indexKey] : null;
    }

    @Override
    public void n_1700_B(b_2585_i buf) {
        this.u_1723_Y = buf.u_1723_Y();
        for (int i = 0; i < this.u_1723_Y; ++i) {
            this.J_1907_R[i] = this.n_1700_B.n_1700_B(buf.u_1723_Y());
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) {
        buf.G_564_y(this.u_1723_Y);
        for (int i = 0; i < this.u_1723_Y; ++i) {
            buf.G_564_y(this.n_1700_B.n_1700_B(this.J_1907_R[i]));
        }
    }

    @Override
    public int n_1700_B() {
        int i = b_2585_i.n_1700_B(this.J_1907_R());
        for (int j = 0; j < this.J_1907_R(); ++j) {
            i += b_2585_i.n_1700_B(this.n_1700_B.n_1700_B(this.J_1907_R[j]));
        }
        return i;
    }

    public int J_1907_R() {
        return this.u_1723_Y;
    }

    @Override
    public void n_1700_B(q_2896_o nbt) {
        for (int i = 0; i < nbt.size(); ++i) {
            this.J_1907_R[i] = this.G_564_y.apply(nbt.n_1700_B(i));
        }
        this.u_1723_Y = nbt.size();
    }
}


