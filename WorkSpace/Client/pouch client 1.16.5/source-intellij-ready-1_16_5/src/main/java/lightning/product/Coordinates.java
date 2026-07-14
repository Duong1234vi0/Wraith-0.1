/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.P_3504_Q;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.y_2498_m;

public interface Coordinates {
    public e_2866_D n_1700_B(y_2498_m var1);

    public P_3504_Q J_1907_R(y_2498_m var1);

    default public c_1514_x R_4764_Y(y_2498_m source) {
        return new c_1514_x(this.n_1700_B(source));
    }

    public boolean n_1700_B();

    public boolean J_1907_R();

    public boolean R_4764_Y();
}


