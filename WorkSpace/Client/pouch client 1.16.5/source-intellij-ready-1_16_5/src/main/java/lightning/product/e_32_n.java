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
import lightning.product.Tag;
import lightning.product.b_2585_i;
import lightning.product.Palette;
import lightning.product.q_2896_o;
import lightning.product.t_252_P;
import lightning.product.w_424_u;

public class e_32_n<T>
implements Palette<T> {
    private final w_424_u<T> n_1700_B;
    private final t_252_P<T> J_1907_R;
    private final PaletteResize<T> R_4764_Y;
    private final Function<U_2912_j, T> G_564_y;
    private final Function<T, U_2912_j> P_1922_E;
    private final int u_1723_Y;

    public e_32_n(w_424_u<T> backingRegistry, int bitsIn, PaletteResize<T> paletteResizerIn, Function<U_2912_j, T> deserializerIn, Function<T, U_2912_j> p_i48964_5_) {
        this.n_1700_B = backingRegistry;
        this.u_1723_Y = bitsIn;
        this.R_4764_Y = paletteResizerIn;
        this.G_564_y = deserializerIn;
        this.P_1922_E = p_i48964_5_;
        this.J_1907_R = new t_252_P(1 << bitsIn);
    }

    @Override
    public int n_1700_B(T state) {
        int i = this.J_1907_R.n_1700_B(state);
        if (i == -1 && (i = this.J_1907_R.J_1907_R(state)) >= 1 << this.u_1723_Y) {
            i = this.R_4764_Y.onResize(this.u_1723_Y + 1, state);
        }
        return i;
    }

    @Override
    public boolean n_1700_B(Predicate<T> p_230341_1_) {
        for (int i = 0; i < this.J_1907_R(); ++i) {
            if (!p_230341_1_.test(this.J_1907_R.n_1700_B(i))) continue;
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public T n_1700_B(int indexKey) {
        return this.J_1907_R.n_1700_B(indexKey);
    }

    @Override
    public void n_1700_B(b_2585_i buf) {
        this.J_1907_R.n_1700_B();
        int i = buf.u_1723_Y();
        for (int j = 0; j < i; ++j) {
            this.J_1907_R.J_1907_R(this.n_1700_B.n_1700_B(buf.u_1723_Y()));
        }
    }

    @Override
    public void J_1907_R(b_2585_i buf) {
        int i = this.J_1907_R();
        buf.G_564_y(i);
        for (int j = 0; j < i; ++j) {
            buf.G_564_y(this.n_1700_B.n_1700_B(this.J_1907_R.n_1700_B(j)));
        }
    }

    @Override
    public int n_1700_B() {
        int i = b_2585_i.n_1700_B(this.J_1907_R());
        for (int j = 0; j < this.J_1907_R(); ++j) {
            i += b_2585_i.n_1700_B(this.n_1700_B.n_1700_B(this.J_1907_R.n_1700_B(j)));
        }
        return i;
    }

    public int J_1907_R() {
        return this.J_1907_R.J_1907_R();
    }

    @Override
    public void n_1700_B(q_2896_o nbt) {
        this.J_1907_R.n_1700_B();
        for (int i = 0; i < nbt.size(); ++i) {
            this.J_1907_R.J_1907_R(this.G_564_y.apply(nbt.n_1700_B(i)));
        }
    }

    public void J_1907_R(q_2896_o paletteList) {
        for (int i = 0; i < this.J_1907_R(); ++i) {
            paletteList.add((Tag)this.P_1922_E.apply(this.J_1907_R.n_1700_B(i)));
        }
    }
}


