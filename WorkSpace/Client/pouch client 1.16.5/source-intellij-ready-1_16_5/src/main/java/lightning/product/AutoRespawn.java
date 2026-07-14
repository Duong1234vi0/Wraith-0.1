/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.k_2603_m;
import lightning.product.l_3609_d;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.AttackAura;
import lightning.product.ModuleCategory;

public class AutoRespawn
extends Module {
    private final BooleanSetting otklyuchatAttackauraEnabled = new BooleanSetting("\u041e\u0442\u043a\u043b\u044e\u0447\u0430\u0442\u044c AttackAura", true);
    private final BooleanSetting ignorirovatHardkorEnabled = new BooleanSetting("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u0445\u0430\u0440\u0434\u043a\u043e\u0440", false);

    public AutoRespawn() {
        super("AutoRespawn", ModuleCategory.G_564_y);
        this.addSettings(this.otklyuchatAttackauraEnabled, this.ignorirovatHardkorEnabled);
    }

    @Y_1740_V
    public void n_1700_B(l_3609_d e) {
        if (AutoRespawn.c_3005_b.Y_601_j == null || AutoRespawn.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!this.ignorirovatHardkorEnabled.isEnabled().booleanValue() && AutoRespawn.c_3005_b.Y_601_j.Y_259_p().n_1700_B()) {
            return;
        }
        if (this.otklyuchatAttackauraEnabled.isEnabled().booleanValue() && ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AttackAura.class).w_1484_f()) {
            ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AttackAura.class).R_4764_Y();
        }
        AutoRespawn.c_3005_b.Y_259_p.G_564_y();
        c_3005_b.n_1700_B((k_2603_m)null);
    }
}


