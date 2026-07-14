/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Z_1993_T;
import lightning.product.Tier;
import lightning.product.q_1613_l;

public class TieredItem
extends q_1613_l {
    private final Tier n_1700_B;

    public TieredItem(Tier tierIn, q_1613_l.n_1700_B builder) {
        super(builder.J_1907_R(tierIn.n_1700_B()));
        this.n_1700_B = tierIn;
    }

    public Tier w_1484_f() {
        return this.n_1700_B;
    }

    @Override
    public int G_564_y() {
        return this.n_1700_B.P_1922_E();
    }

    @Override
    public boolean n_1700_B(Z_1993_T toRepair, Z_1993_T repair) {
        return this.n_1700_B.u_1723_Y().n_1700_B(repair) || super.n_1700_B(toRepair, repair);
    }
}


