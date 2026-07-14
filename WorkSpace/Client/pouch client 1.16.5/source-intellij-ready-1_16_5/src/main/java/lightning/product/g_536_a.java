/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.J_2538_C;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.g_3316_o;
import lightning.product.q_1613_l;
import lightning.product.x_282_a;

public class g_536_a
extends q_1613_l {
    private final J_2538_C n_1700_B;

    public g_536_a(J_2538_C pattern, q_1613_l.n_1700_B builder) {
        super(builder);
        this.n_1700_B = pattern;
    }

    public J_2538_C R_4764_Y() {
        return this.n_1700_B;
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        tooltip.add(this.P_1922_E().n_1700_B(D_4024_W.w_1484_f));
    }

    public MutableComponent P_1922_E() {
        return new F_2904_S(this.J_1907_R() + ".desc");
    }
}


