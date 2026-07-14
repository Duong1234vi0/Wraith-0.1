/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.MobEffects;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.AttackAura;
import lightning.product.ModuleCategory;

public class AutoJump
extends Module {
    public static MultiBooleanSetting prygatEsliOptions = new MultiBooleanSetting("\u041f\u0440\u044b\u0433\u0430\u0442\u044c \u0435\u0441\u043b\u0438", new BooleanSetting("\u0410\u043a\u0442\u0438\u0432\u043d\u0430 Attack Aura", false), new BooleanSetting("\u0410\u043a\u0442\u0438\u0432\u043d\u043e \u0437\u0435\u043b\u044c\u0435 \u0437\u0430\u043c\u0435\u0434\u043b\u0435\u043d\u0438\u044f", true));

    public AutoJump() {
        super("AutoJump", ModuleCategory.J_1907_R);
        this.addSettings(prygatEsliOptions);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        AttackAura aura;
        if (!AutoJump.c_3005_b.Y_259_p.M_1641_O() || AutoJump.c_3005_b.P_4830_p.Ping.G_564_y()) {
            return;
        }
        if (AutoJump.c_3005_b.Y_259_p.U_1241_n > 0.0f) {
            return;
        }
        boolean shouldJump = false;
        if (prygatEsliOptions.isOptionEnabled("\u0410\u043a\u0442\u0438\u0432\u043d\u0430 Attack Aura").booleanValue() && (aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B).w_1484_f() && aura.h_1847_R() != null) {
            shouldJump = true;
        }
        if (prygatEsliOptions.isOptionEnabled("\u0410\u043a\u0442\u0438\u0432\u043d\u043e \u0437\u0435\u043b\u044c\u0435 \u0437\u0430\u043c\u0435\u0434\u043b\u0435\u043d\u0438\u044f").booleanValue() && AutoJump.c_3005_b.Y_259_p.J_1907_R(MobEffects.J_1907_R)) {
            shouldJump = true;
        }
        if (shouldJump && AutoJump.c_3005_b.Y_259_p.M_1641_O()) {
            AutoJump.c_3005_b.Y_259_p.e_837_t();
        }
    }
}



