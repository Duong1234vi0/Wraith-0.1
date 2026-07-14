/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MobEffects;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.ModuleCategory;

public class LevitationControl
extends Module {
    public LevitationControl() {
        super("LevitationControl", ModuleCategory.G_564_y);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (LevitationControl.c_3005_b.Y_259_p == null) {
            return;
        }
        if (LevitationControl.c_3005_b.Y_259_p.J_1907_R(MobEffects.q_2307_F)) {
            int amplifier = LevitationControl.c_3005_b.Y_259_p.R_4764_Y(MobEffects.q_2307_F).R_4764_Y();
            if (LevitationControl.c_3005_b.P_4830_p.Ping.G_564_y()) {
                LevitationControl.c_3005_b.Y_259_p.h_1847_R(LevitationControl.c_3005_b.Y_259_p.I_4348_c().J_1907_R, (0.05 * (double)(amplifier + 25) - LevitationControl.c_3005_b.Y_259_p.I_4348_c().R_4764_Y) * 0.2, LevitationControl.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            } else if (LevitationControl.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
                LevitationControl.c_3005_b.Y_259_p.h_1847_R(LevitationControl.c_3005_b.Y_259_p.I_4348_c().J_1907_R, -((0.05 * (double)(amplifier + 25) - LevitationControl.c_3005_b.Y_259_p.I_4348_c().R_4764_Y) * 0.2), LevitationControl.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            } else {
                LevitationControl.c_3005_b.Y_259_p.h_1847_R(LevitationControl.c_3005_b.Y_259_p.I_4348_c().J_1907_R, 0.0, LevitationControl.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            }
        }
    }
}



