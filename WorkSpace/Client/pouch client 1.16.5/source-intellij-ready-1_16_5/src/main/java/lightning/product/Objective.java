/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.M_1462_J;
import lightning.product.U_2871_b;
import lightning.product.c_973_a;
import lightning.product.i_4895_l;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;

public class Objective {
    private final i_4895_l n_1700_B;
    private final String J_1907_R;
    private final M_1462_J R_4764_Y;
    private x_282_a G_564_y;
    private x_282_a P_1922_E;
    private M_1462_J.n_1700_B u_1723_Y;

    public Objective(i_4895_l p_i49788_1_, String p_i49788_2_, M_1462_J p_i49788_3_, x_282_a p_i49788_4_, M_1462_J.n_1700_B p_i49788_5_) {
        this.n_1700_B = p_i49788_1_;
        this.J_1907_R = p_i49788_2_;
        this.R_4764_Y = p_i49788_3_;
        this.G_564_y = p_i49788_4_;
        this.P_1922_E = this.v_4262_N();
        this.u_1723_Y = p_i49788_5_;
    }

    public i_4895_l n_1700_B() {
        return this.n_1700_B;
    }

    public String J_1907_R() {
        return this.J_1907_R;
    }

    public M_1462_J R_4764_Y() {
        return this.R_4764_Y;
    }

    public x_282_a G_564_y() {
        return this.G_564_y;
    }

    private x_282_a v_4262_N() {
        return ComponentUtils.n_1700_B(this.G_564_y.P_1922_E().n_1700_B(p_237497_1_ -> p_237497_1_.n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b(this.J_1907_R)))));
    }

    public x_282_a P_1922_E() {
        return this.P_1922_E;
    }

    public void n_1700_B(x_282_a p_199864_1_) {
        this.G_564_y = p_199864_1_;
        this.P_1922_E = this.v_4262_N();
        this.n_1700_B.G_564_y(this);
    }

    public M_1462_J.n_1700_B u_1723_Y() {
        return this.u_1723_Y;
    }

    public void n_1700_B(M_1462_J.n_1700_B p_199866_1_) {
        this.u_1723_Y = p_199866_1_;
        this.n_1700_B.G_564_y(this);
    }
}


