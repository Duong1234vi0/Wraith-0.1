/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.y_3462_t;

public interface s_3940_w
extends y_3462_t {
    public int n_1700_B();

    public int J_1907_R();

    public void n_1700_B(int var1, int var2);

    public boolean G_564_y();

    public float R_4764_Y();

    default public float w_1484_f() {
        return this.P_1922_E();
    }

    default public float t_148_a() {
        return this.w_1484_f() + (float)this.n_1700_B() / this.R_4764_Y();
    }

    default public float s_956_w() {
        return this.M_588_G();
    }

    default public float u_2550_I() {
        return this.s_956_w() + (float)this.J_1907_R() / this.R_4764_Y();
    }

    default public float M_588_G() {
        return 3.0f;
    }
}

