/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AxeItem;
import lightning.product.K_4096_w;
import lightning.product.N_4263_v;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Enchantments;
import lightning.product.ElytraItem;
import lightning.product.h_1015_G;
import lightning.product.n_1494_c;
import lightning.product.o_12_W;
import lightning.product.BooleanSetting;
import lightning.product.Items;
import lightning.product.SwordItem;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class AutoPilot
extends Module {
    private final BooleanSetting sharEnabled = new BooleanSetting("\u0428\u0430\u0440", true);
    private final BooleanSetting elitraEnabled = new BooleanSetting("\u042d\u043b\u0438\u0442\u0440\u0430", true);
    private final BooleanSetting oskolokEnabled = new BooleanSetting("\u041e\u0441\u043a\u043e\u043b\u043e\u043a", true);
    private final BooleanSetting ostrotaViEnabled = new BooleanSetting("\u041e\u0441\u0442\u0440\u043e\u0442\u0430 VI", false);
    private final BooleanSetting antiPoletEnabled = new BooleanSetting("\u0410\u043d\u0442\u0438 \u043f\u043e\u043b\u0451\u0442", false);
    private final BooleanSetting auraEnabled = new BooleanSetting("\u0410\u0443\u0440\u0430", true);

    public AutoPilot() {
        super("AutoPilot", ModuleCategory.G_564_y);
        this.addSettings(this.sharEnabled, this.elitraEnabled, this.oskolokEnabled, this.ostrotaViEnabled, this.antiPoletEnabled, this.auraEnabled);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (AutoPilot.c_3005_b.Y_259_p == null || AutoPilot.c_3005_b.Y_601_j == null) {
            return;
        }
        for (N_4263_v entity : AutoPilot.c_3005_b.Y_601_j.J_1907_R()) {
            float[] r;
            if (!(entity instanceof n_1494_c)) continue;
            n_1494_c itemEntity = (n_1494_c)entity;
            Z_1993_T itemStack = itemEntity.P_1922_E();
            String displayName = itemStack.multiplayerClientSuggestionProvider().getString();
            if (this.sharEnabled.isEnabled().booleanValue() && itemStack.J_1907_R() instanceof o_12_W) {
                r = this.n_1700_B(entity);
                AutoPilot.c_3005_b.Y_259_p.p_178_J = r[0];
                AutoPilot.c_3005_b.Y_259_p.f_4016_n = r[1];
            }
            if (this.elitraEnabled.isEnabled().booleanValue() && itemStack.J_1907_R() instanceof ElytraItem) {
                r = this.n_1700_B(entity);
                AutoPilot.c_3005_b.Y_259_p.p_178_J = r[0];
                AutoPilot.c_3005_b.Y_259_p.f_4016_n = r[1];
            }
            if (this.oskolokEnabled.isEnabled().booleanValue() && itemStack.J_1907_R() == Items.b_3334_n && displayName.contains("\u041e\u0441\u043a\u043e\u043b\u043e\u043a")) {
                r = this.n_1700_B(entity);
                AutoPilot.c_3005_b.Y_259_p.p_178_J = r[0];
                AutoPilot.c_3005_b.Y_259_p.f_4016_n = r[1];
            }
            if (this.ostrotaViEnabled.isEnabled().booleanValue() && (itemStack.J_1907_R() instanceof SwordItem || itemStack.J_1907_R() instanceof AxeItem) && K_4096_w.n_1700_B(Enchantments.P_4830_p, itemStack) >= 6) {
                r = this.n_1700_B(entity);
                AutoPilot.c_3005_b.Y_259_p.p_178_J = r[0];
                AutoPilot.c_3005_b.Y_259_p.f_4016_n = r[1];
            }
            if (this.antiPoletEnabled.isEnabled().booleanValue() && itemStack.J_1907_R() == Items.FenceGateBlock && displayName.contains("\u0410\u043d\u0442\u0438 \u041f\u043e\u043b\u0451\u0442")) {
                r = this.n_1700_B(entity);
                AutoPilot.c_3005_b.Y_259_p.p_178_J = r[0];
                AutoPilot.c_3005_b.Y_259_p.f_4016_n = r[1];
            }
            if (!this.auraEnabled.isEnabled().booleanValue() || !(itemStack.J_1907_R() == Items.E_2115_e && displayName.contains("\u0410\u0443\u0440\u0430 \u041e\u0445\u043e\u0442\u043d\u0438\u043a\u0430") || itemStack.J_1907_R() == Items.i_4833_u && displayName.contains("\u0410\u0443\u0440\u0430 \u0422\u0432\u0451\u0440\u0434\u043e\u0441\u0442\u0438 \u0411\u0440\u043e\u043d\u0438") || itemStack.J_1907_R() == Items.MelonBlock && displayName.contains("\u0410\u0443\u0440\u0430 \u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u0438") || itemStack.J_1907_R() == Items.u_3578_p && displayName.contains("\u0410\u0443\u0440\u0430 \u0411\u043e\u0433\u0430\u0447\u0430") || itemStack.J_1907_R() == Items.ServerFunctionManager && displayName.contains("\u0410\u0443\u0440\u0430 \u0417\u0430\u0449\u0438\u0442\u044b \u041e\u0442 \u041f\u0430\u0434\u0435\u043d\u0438\u044f")) && (itemStack.J_1907_R() != Items.b_3334_n || !displayName.contains("\u0410\u0443\u0440\u0430 \u0417\u0430\u0449\u0438\u0442\u044b \u041e\u0442 \u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u043e\u0432"))) continue;
            r = this.n_1700_B(entity);
            AutoPilot.c_3005_b.Y_259_p.p_178_J = r[0];
            AutoPilot.c_3005_b.Y_259_p.f_4016_n = r[1];
        }
    }

    public float[] n_1700_B(N_4263_v entity) {
        double x = entity.O_3598_v() - AutoPilot.c_3005_b.Y_259_p.O_3598_v();
        double y = entity.X_2960_b() - AutoPilot.c_3005_b.Y_259_p.X_2960_b() - 1.0;
        double z = entity.l_2647_k() - AutoPilot.c_3005_b.Y_259_p.l_2647_k();
        double u = u_530_F.n_1700_B(x * x + z * z);
        float yaw = (float)(u_530_F.G_564_y(z, x) * 57.29577951308232 - 90.0);
        float pitch = (float)(-u_530_F.G_564_y(y, u) * 57.29577951308232);
        return new float[]{yaw, pitch};
    }
}



