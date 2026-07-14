/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import lightning.product.InactiveProfiler;
import lightning.product.V_3322_x;
import lightning.product.ProfilerFiller;
import lightning.product.ProfileResults;
import lightning.product.q_1764_n;

public class ContinuousProfiler {
    private final LongSupplier n_1700_B;
    private final IntSupplier J_1907_R;
    private V_3322_x R_4764_Y = InactiveProfiler.n_1700_B;

    public ContinuousProfiler(LongSupplier p_i231483_1_, IntSupplier p_i231483_2_) {
        this.n_1700_B = p_i231483_1_;
        this.J_1907_R = p_i231483_2_;
    }

    public boolean n_1700_B() {
        return this.R_4764_Y != InactiveProfiler.n_1700_B;
    }

    public void J_1907_R() {
        this.R_4764_Y = InactiveProfiler.n_1700_B;
    }

    public void R_4764_Y() {
        this.R_4764_Y = new q_1764_n(this.n_1700_B, this.J_1907_R, true);
    }

    public ProfilerFiller G_564_y() {
        return this.R_4764_Y;
    }

    public ProfileResults P_1922_E() {
        return this.R_4764_Y.G_564_y();
    }
}


