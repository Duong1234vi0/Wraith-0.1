/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.QuadrupedModel;
import lightning.product.e_4189_z;

public class w_3245_r<T extends N_4263_v>
extends QuadrupedModel<T> {
    public w_3245_r() {
        super(12, 0.0f, false, 10.0f, 4.0f, 2.0f, 2.0f, 24);
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-4.0f, -4.0f, -6.0f, 8.0f, 8.0f, 6.0f, 0.0f);
        this.n_1700_B.n_1700_B(0.0f, 4.0f, -8.0f);
        this.n_1700_B.n_1700_B(22, 0).n_1700_B(-5.0f, -5.0f, -4.0f, 1.0f, 3.0f, 1.0f, 0.0f);
        this.n_1700_B.n_1700_B(22, 0).n_1700_B(4.0f, -5.0f, -4.0f, 1.0f, 3.0f, 1.0f, 0.0f);
        this.J_1907_R = new e_4189_z(this, 18, 4);
        this.J_1907_R.n_1700_B(-6.0f, -10.0f, -7.0f, 12.0f, 18.0f, 10.0f, 0.0f);
        this.J_1907_R.n_1700_B(0.0f, 5.0f, 2.0f);
        this.J_1907_R.n_1700_B(52, 0).n_1700_B(-2.0f, 2.0f, -8.0f, 4.0f, 6.0f, 1.0f);
        this.R_4764_Y.R_4764_Y -= 1.0f;
        this.G_564_y.R_4764_Y += 1.0f;
        this.R_4764_Y.P_1922_E += 0.0f;
        this.G_564_y.P_1922_E += 0.0f;
        this.P_1922_E.R_4764_Y -= 1.0f;
        this.u_1723_Y.R_4764_Y += 1.0f;
        this.P_1922_E.P_1922_E -= 1.0f;
        this.u_1723_Y.P_1922_E -= 1.0f;
    }

    public e_4189_z R_4764_Y() {
        return this.n_1700_B;
    }
}


