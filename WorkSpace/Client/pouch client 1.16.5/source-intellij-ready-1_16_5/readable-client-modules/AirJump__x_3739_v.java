/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.y_2603_k;

public class x_3739_v
extends X_3546_T {
    private boolean v_4262_N = false;

    public x_3739_v() {
        super("AirJump", y_2603_k.J_1907_R);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        boolean isJumpPressed = x_3739_v.c_3005_b.P_4830_p.T_69_K.G_564_y();
        if (isJumpPressed && !this.v_4262_N) {
            x_3739_v.c_3005_b.Y_259_p.e_837_t();
        }
        this.v_4262_N = isJumpPressed;
    }
}

