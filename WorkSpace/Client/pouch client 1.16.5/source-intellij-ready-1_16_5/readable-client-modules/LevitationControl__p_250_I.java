/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.J_588_u;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.y_2603_k;

public class p_250_I
extends X_3546_T {
    public p_250_I() {
        super("LevitationControl", y_2603_k.G_564_y);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (p_250_I.c_3005_b.Y_259_p == null) {
            return;
        }
        if (p_250_I.c_3005_b.Y_259_p.J_1907_R(J_588_u.q_2307_F)) {
            int amplifier = p_250_I.c_3005_b.Y_259_p.R_4764_Y(J_588_u.q_2307_F).R_4764_Y();
            if (p_250_I.c_3005_b.P_4830_p.T_69_K.G_564_y()) {
                p_250_I.c_3005_b.Y_259_p.h_1847_R(p_250_I.c_3005_b.Y_259_p.I_4348_c().J_1907_R, (0.05 * (double)(amplifier + 25) - p_250_I.c_3005_b.Y_259_p.I_4348_c().R_4764_Y) * 0.2, p_250_I.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            } else if (p_250_I.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
                p_250_I.c_3005_b.Y_259_p.h_1847_R(p_250_I.c_3005_b.Y_259_p.I_4348_c().J_1907_R, -((0.05 * (double)(amplifier + 25) - p_250_I.c_3005_b.Y_259_p.I_4348_c().R_4764_Y) * 0.2), p_250_I.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            } else {
                p_250_I.c_3005_b.Y_259_p.h_1847_R(p_250_I.c_3005_b.Y_259_p.I_4348_c().J_1907_R, 0.0, p_250_I.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            }
        }
    }
}

