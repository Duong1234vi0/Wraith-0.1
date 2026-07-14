/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.ModuleCategory;

public class AirJump
extends Module {
    private boolean v_4262_N = false;

    public AirJump() {
        super("AirJump", ModuleCategory.J_1907_R);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        boolean isJumpPressed = AirJump.c_3005_b.P_4830_p.Ping.G_564_y();
        if (isJumpPressed && !this.v_4262_N) {
            AirJump.c_3005_b.Y_259_p.e_837_t();
        }
        this.v_4262_N = isJumpPressed;
    }
}



