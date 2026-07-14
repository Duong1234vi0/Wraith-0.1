/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.E_1407_D;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1567_W;
import lightning.product.c_973_a;
import lightning.product.h_1015_G;
import lightning.product.i_2909_p;
import lightning.product.v_1900_v;
import lightning.product.ModuleCategory;

public class DeathCoords
extends Module {
    public DeathCoords() {
        super("DeathCoords", ModuleCategory.P_1922_E);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (DeathCoords.c_3005_b.Y_259_p == null) {
            return;
        }
        if (DeathCoords.c_3005_b.Y_1740_V instanceof E_1407_D && DeathCoords.c_3005_b.Y_259_p.O_2151_c < 1) {
            MutableComponent message = new U_2871_b("\u041a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b \u0441\u043c\u0435\u0440\u0442\u0438: " + DeathCoords.c_3005_b.Y_259_p.b_2312_j().getX() + " " + DeathCoords.c_3005_b.Y_259_p.b_2312_j().getY() + " " + DeathCoords.c_3005_b.Y_259_p.b_2312_j().getZ()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f).n_1700_B(new i_2909_p(i_2909_p.n_1700_B.R_4764_Y, ".gps " + DeathCoords.c_3005_b.Y_259_p.b_2312_j().getX() + " " + DeathCoords.c_3005_b.Y_259_p.b_2312_j().getZ())).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("\u041d\u0430\u0436\u043c\u0438\u0442\u0435 \u0434\u043b\u044f \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0438\u044f GPS").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)))));
            v_1900_v.n_1700_B(message, new Object[0]);
        }
    }
}



