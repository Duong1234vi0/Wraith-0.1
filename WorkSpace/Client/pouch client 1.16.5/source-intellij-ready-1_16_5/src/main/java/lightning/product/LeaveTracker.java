/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_3457_f;
import lightning.product.N_4263_v;
import lightning.product.V_772_m;
import lightning.product.Module;
import lightning.product.X_4340_E;
import lightning.product.Y_1740_V;
import lightning.product.BooleanSetting;
import lightning.product.v_1900_v;
import lightning.product.ModuleCategory;

public class LeaveTracker
extends Module {
    private final BooleanSetting proveryatDistanciyuEnabled = new BooleanSetting("\u041f\u0440\u043e\u0432\u0435\u0440\u044f\u0442\u044c \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044e", true);

    public LeaveTracker() {
        super("LeaveTracker", ModuleCategory.G_564_y);
        this.addSettings(this.proveryatDistanciyuEnabled);
    }

    @Y_1740_V
    public void n_1700_B(I_3457_f event) {
        if (LeaveTracker.c_3005_b.Y_259_p == null || LeaveTracker.c_3005_b.Y_601_j == null) {
            return;
        }
        N_4263_v n_4263_v = event.J_1907_R();
        if (n_4263_v instanceof X_4340_E) {
            X_4340_E clientPlayer = (X_4340_E)n_4263_v;
            if (clientPlayer instanceof V_772_m) {
                return;
            }
            if (this.proveryatDistanciyuEnabled.isEnabled().booleanValue() && LeaveTracker.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)clientPlayer) < 100.0f) {
                return;
            }
            String name = clientPlayer.c_4037_x();
            if (name == null || name.contains("CIT-")) {
                return;
            }
            String x = String.format("%.1f", clientPlayer.O_3598_v());
            String y = String.format("%.1f", clientPlayer.X_2960_b());
            String z = String.format("%.1f", clientPlayer.l_2647_k());
            v_1900_v.n_1700_B("\u0418\u0433\u0440\u043e\u043a %s \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u043b\u0441\u044f \u043d\u0430 \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u044b x: %s y: %s z: %s", name, x, y, z);
        }
    }
}


