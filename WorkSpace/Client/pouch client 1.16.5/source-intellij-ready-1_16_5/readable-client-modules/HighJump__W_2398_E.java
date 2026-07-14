/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.N_4263_v;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_433_S;
import lightning.product.g_1462_f;
import lightning.product.h_1015_G;
import lightning.product.i_2154_H;
import lightning.product.q_366_O;
import lightning.product.y_2603_k;

public class W_2398_E
extends X_3546_T {
    private final q_366_O v_4262_N = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "\u0428\u0430\u043b\u043a\u0435\u0440", "\u0428\u0430\u043b\u043a\u0435\u0440");
    private final I_686_h w_1484_f = new I_686_h("\u0412\u044b\u0441\u043e\u0442\u0430 \u043f\u0440\u044b\u0436\u043a\u0430", 2.0f, 1.0f, 3.0f, 0.05f, () -> this.v_4262_N.J_1907_R("\u0428\u0430\u043b\u043a\u0435\u0440"));

    public W_2398_E() {
        super("HighJump", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N, this.w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (W_2398_E.c_3005_b.Y_259_p == null || W_2398_E.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.w_1484_f()) {
            return;
        }
        if (this.v_4262_N.J_1907_R("\u0428\u0430\u043b\u043a\u0435\u0440")) {
            for (i_2154_H tile : W_2398_E.c_3005_b.Y_601_j.t_148_a) {
                boolean isOpen;
                if (!(tile instanceof a_433_S)) continue;
                a_433_S shulkerBox = (a_433_S)tile;
                double distanceX = W_2398_E.c_3005_b.Y_259_p.O_3598_v() - (double)((float)tile.x_607_J().getX() + 0.5f);
                double distanceZ = W_2398_E.c_3005_b.Y_259_p.l_2647_k() - (double)((float)tile.x_607_J().getZ() + 0.5f);
                double distance = Math.sqrt(distanceX * distanceX + distanceZ * distanceZ);
                double distanceY = Math.abs(W_2398_E.c_3005_b.Y_259_p.X_2960_b() - (double)((float)tile.x_607_J().getY() + 0.5f));
                double maxDistanceY = W_2398_E.c_3005_b.Y_259_p.I_4348_c().R_4764_Y > 1.0 ? 30.0 : 2.0;
                float progress = shulkerBox.n_1700_B(1.0f);
                boolean isOpening = shulkerBox.w_1484_f() == a_433_S.n_1700_B.J_1907_R;
                boolean bl = isOpen = shulkerBox.w_1484_f() == a_433_S.n_1700_B.R_4764_Y;
                if (!(distance <= 1.0) || !(distanceY <= maxDistanceY) || W_2398_E.c_3005_b.Y_259_p.U_1241_n != 0.0f || !isOpening && (!isOpen || !(progress > 0.0f))) continue;
                W_2398_E.c_3005_b.Y_259_p.h_1847_R(W_2398_E.c_3005_b.Y_259_p.I_4348_c().J_1907_R, ((Float)this.w_1484_f.J_1907_R()).floatValue(), W_2398_E.c_3005_b.Y_259_p.I_4348_c().G_564_y);
                if (W_2398_E.c_3005_b.Y_1740_V == null) break;
                W_2398_E.c_3005_b.Y_1740_V.closeScreen();
                break;
            }
        }
        if (this.v_4262_N.J_1907_R("Holy Boat")) {
            for (N_4263_v entity : W_2398_E.c_3005_b.Y_601_j.J_1907_R()) {
                if (!(entity instanceof g_1462_f) || !((double)W_2398_E.c_3005_b.Y_259_p.R_4764_Y(entity) < 2.0) || !(W_2398_E.c_3005_b.Y_259_p.R_4764_Y(entity) < 2.0f)) continue;
                W_2398_E.c_3005_b.P_4830_p.T_69_K.n_1700_B(false);
                if (!W_2398_E.c_3005_b.Y_259_p.M_1641_O()) continue;
                W_2398_E.c_3005_b.Y_259_p.h_1847_R(0.0, 2.12, 0.0);
            }
        }
    }
}

