/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Q_1939_l;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.h_1015_G;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.Items;
import lightning.product.AttackAura;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;

public class AutoSoup
extends Module {
    private final NumberSetting zdoroveSetting = new NumberSetting("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435", 16.0f, 1.0f, 20.0f, 0.5f);
    private final BooleanSetting neIspolzovatSAttackauraEnabled = new BooleanSetting("\u041d\u0435 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0441 AttackAura", false);
    private final BooleanSetting tolkoVInventareEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", false);
    private boolean s_956_w = false;
    private x_1688_C u_2550_I = null;

    public AutoSoup() {
        super("AutoSoup", ModuleCategory.G_564_y);
        this.addSettings(this.zdoroveSetting, this.neIspolzovatSAttackauraEnabled, this.tolkoVInventareEnabled);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        AttackAura aura;
        Module attackAura;
        if (AutoSoup.c_3005_b.Y_259_p == null || AutoSoup.c_3005_b.Y_601_j == null) {
            this.h_1847_R();
            return;
        }
        if (this.tolkoVInventareEnabled.isEnabled().booleanValue() && (AutoSoup.c_3005_b.Y_1740_V == null || !(AutoSoup.c_3005_b.Y_1740_V instanceof Q_1939_l))) {
            this.h_1847_R();
            return;
        }
        if (AutoSoup.c_3005_b.Y_1740_V != null && !(AutoSoup.c_3005_b.Y_1740_V instanceof Q_1939_l)) {
            this.h_1847_R();
            return;
        }
        if (this.neIspolzovatSAttackauraEnabled.isEnabled().booleanValue() && (attackAura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AttackAura.class)) != null && attackAura.w_1484_f() && (aura = (AttackAura)attackAura).h_1847_R() != null) {
            this.h_1847_R();
            return;
        }
        float currentHealth = AutoSoup.c_3005_b.Y_259_p.g_46_E() + AutoSoup.c_3005_b.Y_259_p.U_3823_u();
        if (this.s_956_w) {
            if (currentHealth >= 20.0f) {
                this.h_1847_R();
            } else {
                if (AutoSoup.c_3005_b.Y_259_p.Y_601_j()) {
                    return;
                }
                this.h_1847_R();
            }
        }
        if (currentHealth <= ((Float)this.zdoroveSetting.getValue()).floatValue()) {
            Z_1993_T offHandStack = AutoSoup.c_3005_b.Y_259_p.S_4035_N();
            Z_1993_T mainHandStack = AutoSoup.c_3005_b.Y_259_p.A_2714_y();
            boolean offHandSoup = this.n_1700_B(offHandStack);
            boolean mainHandSoup = this.n_1700_B(mainHandStack);
            if (offHandSoup || mainHandSoup) {
                x_1688_C handToUse = offHandSoup ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
                this.n_1700_B(handToUse);
                return;
            }
            for (int i = 0; i < 9; ++i) {
                Z_1993_T stack = AutoSoup.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (!this.n_1700_B(stack)) continue;
                AutoSoup.c_3005_b.Y_259_p.l_1268_F.G_564_y = i;
                this.n_1700_B(x_1688_C.n_1700_B);
                return;
            }
        }
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == Items.MinecraftAccess || stack.J_1907_R() == Items.N_3347_G || stack.J_1907_R() == Items.MyceliumBlock;
    }

    private void n_1700_B(x_1688_C hand) {
        if (this.s_956_w && this.u_2550_I == hand) {
            if (!AutoSoup.c_3005_b.Y_259_p.Y_601_j()) {
                AutoSoup.c_3005_b.w_1457_N.processRightClick(AutoSoup.c_3005_b.Y_259_p, AutoSoup.c_3005_b.Y_601_j, hand);
                AutoSoup.c_3005_b.P_4830_p.e_1992_r.n_1700_B(true);
            }
            return;
        }
        if (this.s_956_w) {
            this.h_1847_R();
        }
        this.s_956_w = true;
        this.u_2550_I = hand;
        AutoSoup.c_3005_b.w_1457_N.processRightClick(AutoSoup.c_3005_b.Y_259_p, AutoSoup.c_3005_b.Y_601_j, hand);
        AutoSoup.c_3005_b.P_4830_p.e_1992_r.n_1700_B(true);
    }

    private void h_1847_R() {
        if (this.s_956_w) {
            if (AutoSoup.c_3005_b.Y_259_p != null && AutoSoup.c_3005_b.Y_259_p.Y_601_j()) {
                AutoSoup.c_3005_b.w_1457_N.onStoppedUsingItem(AutoSoup.c_3005_b.Y_259_p);
            }
            AutoSoup.c_3005_b.P_4830_p.e_1992_r.n_1700_B(false);
            this.s_956_w = false;
            this.u_2550_I = null;
        }
    }

    @Override
    public void onDisable() {
        this.h_1847_R();
        super.onDisable();
    }
}



