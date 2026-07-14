/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Objects;
import java.util.function.Consumer;
import lightning.product.W_2163_m;
import lightning.product.v_4829_R;

public class TestFunction {
    private final String n_1700_B;
    private final String J_1907_R;
    private final String R_4764_Y;
    private final boolean G_564_y = false;
    private final Consumer<v_4829_R> P_1922_E = null;
    private final int u_1723_Y = 0;
    private final long v_4262_N = 0L;
    private final W_2163_m w_1484_f = null;

    public void n_1700_B(v_4829_R p_229658_1_) {
        this.P_1922_E.accept(p_229658_1_);
    }

    public String n_1700_B() {
        return this.J_1907_R;
    }

    public String J_1907_R() {
        return this.R_4764_Y;
    }

    public String toString() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        Objects.requireNonNull(this);
        return 0;
    }

    public boolean G_564_y() {
        Objects.requireNonNull(this);
        return false;
    }

    public String P_1922_E() {
        return this.n_1700_B;
    }

    public long u_1723_Y() {
        return this.v_4262_N;
    }

    public W_2163_m v_4262_N() {
        return this.w_1484_f;
    }

    private TestFunction() {
        this.n_1700_B = null;
        this.J_1907_R = null;
        this.R_4764_Y = null;
        throw new RuntimeException("Synthetic constructor added by MCP, do not call");
    }
}


