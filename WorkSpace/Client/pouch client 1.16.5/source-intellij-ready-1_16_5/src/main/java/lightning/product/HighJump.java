/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.N_4263_v;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_433_S;
import lightning.product.g_1462_f;
import lightning.product.h_1015_G;
import lightning.product.i_2154_H;
import lightning.product.ModeSetting;
import lightning.product.ModuleCategory;

public class HighJump
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "\u0428\u0430\u043b\u043a\u0435\u0440", "\u0428\u0430\u043b\u043a\u0435\u0440");
    private final NumberSetting vysotaPryzhkaSetting = new NumberSetting("\u0412\u044b\u0441\u043e\u0442\u0430 \u043f\u0440\u044b\u0436\u043a\u0430", 2.0f, 1.0f, 3.0f, 0.05f, () -> this.rezhimMode.isMode("\u0428\u0430\u043b\u043a\u0435\u0440"));

    public HighJump() {
        super("HighJump", ModuleCategory.J_1907_R);
        this.addSettings(this.rezhimMode, this.vysotaPryzhkaSetting);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (HighJump.c_3005_b.Y_259_p == null || HighJump.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.w_1484_f()) {
            return;
        }
        if (this.rezhimMode.isMode("\u0428\u0430\u043b\u043a\u0435\u0440")) {
            for (i_2154_H tile : HighJump.c_3005_b.Y_601_j.t_148_a) {
                boolean isOpen;
                if (!(tile instanceof a_433_S)) continue;
                a_433_S shulkerBox = (a_433_S)tile;
                double distanceX = HighJump.c_3005_b.Y_259_p.O_3598_v() - (double)((float)tile.x_607_J().getX() + 0.5f);
                double distanceZ = HighJump.c_3005_b.Y_259_p.l_2647_k() - (double)((float)tile.x_607_J().getZ() + 0.5f);
                double distance = Math.sqrt(distanceX * distanceX + distanceZ * distanceZ);
                double distanceY = Math.abs(HighJump.c_3005_b.Y_259_p.X_2960_b() - (double)((float)tile.x_607_J().getY() + 0.5f));
                double maxDistanceY = HighJump.c_3005_b.Y_259_p.I_4348_c().R_4764_Y > 1.0 ? 30.0 : 2.0;
                float progress = shulkerBox.n_1700_B(1.0f);
                boolean isOpening = shulkerBox.w_1484_f() == a_433_S.n_1700_B.J_1907_R;
                boolean bl = isOpen = shulkerBox.w_1484_f() == a_433_S.n_1700_B.R_4764_Y;
                if (!(distance <= 1.0) || !(distanceY <= maxDistanceY) || HighJump.c_3005_b.Y_259_p.U_1241_n != 0.0f || !isOpening && (!isOpen || !(progress > 0.0f))) continue;
                HighJump.c_3005_b.Y_259_p.h_1847_R(HighJump.c_3005_b.Y_259_p.I_4348_c().J_1907_R, ((Float)this.vysotaPryzhkaSetting.getValue()).floatValue(), HighJump.c_3005_b.Y_259_p.I_4348_c().G_564_y);
                if (HighJump.c_3005_b.Y_1740_V == null) break;
                HighJump.c_3005_b.Y_1740_V.closeScreen();
                break;
            }
        }
        if (this.rezhimMode.isMode("Holy Boat")) {
            for (N_4263_v entity : HighJump.c_3005_b.Y_601_j.J_1907_R()) {
                if (!(entity instanceof g_1462_f) || !((double)HighJump.c_3005_b.Y_259_p.R_4764_Y(entity) < 2.0) || !(HighJump.c_3005_b.Y_259_p.R_4764_Y(entity) < 2.0f)) continue;
                HighJump.c_3005_b.P_4830_p.Ping.n_1700_B(false);
                if (!HighJump.c_3005_b.Y_259_p.M_1641_O()) continue;
                HighJump.c_3005_b.Y_259_p.h_1847_R(0.0, 2.12, 0.0);
            }
        }
    }
}



