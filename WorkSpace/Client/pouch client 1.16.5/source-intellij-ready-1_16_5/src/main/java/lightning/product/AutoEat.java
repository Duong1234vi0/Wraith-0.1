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

public class AutoEat
extends Module {
    private final BooleanSetting neIspolzovatSAttackauraEnabled = new BooleanSetting("\u041d\u0435 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u0441 AttackAura", false);
    private final NumberSetting kolVoGolodaSetting = new NumberSetting("\u041a\u043e\u043b-\u0432\u043e \u0433\u043e\u043b\u043e\u0434\u0430", 18.0f, 1.0f, 20.0f, 0.5f);
    private final BooleanSetting estZolotyeYablokiEnabled = new BooleanSetting("\u0415\u0441\u0442\u044c \u0437\u043e\u043b\u043e\u0442\u044b\u0435 \u044f\u0431\u043b\u043e\u043a\u0438", true);
    private final NumberSetting zdoroveDlyaGaSetting = new NumberSetting("\u0417\u0434\u043e\u0440\u043e\u0432\u044c\u0435 \u0434\u043b\u044f \u0413\u0410", 14.0f, 1.0f, 20.0f, 0.5f, this.estZolotyeYablokiEnabled::isEnabled);
    private final BooleanSetting prioritetGaEnabled = new BooleanSetting("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442 \u0413\u0410", true, this.estZolotyeYablokiEnabled::isEnabled);
    private boolean M_588_G = false;
    private boolean P_4830_p = false;
    private x_1688_C h_1847_R = null;

    public AutoEat() {
        super("AutoEat", ModuleCategory.G_564_y);
        this.addSettings(this.neIspolzovatSAttackauraEnabled, this.kolVoGolodaSetting, this.estZolotyeYablokiEnabled, this.zdoroveDlyaGaSetting, this.prioritetGaEnabled);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        AttackAura aura;
        Module attackAura;
        if (AutoEat.c_3005_b.Y_259_p == null || AutoEat.c_3005_b.Y_601_j == null) {
            this.h_1847_R();
            return;
        }
        if (AutoEat.c_3005_b.Y_1740_V != null && !(AutoEat.c_3005_b.Y_1740_V instanceof Q_1939_l)) {
            this.h_1847_R();
            return;
        }
        if (this.neIspolzovatSAttackauraEnabled.isEnabled().booleanValue() && (attackAura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AttackAura.class)) != null && attackAura.w_1484_f() && (aura = (AttackAura)attackAura).h_1847_R() != null) {
            this.h_1847_R();
            return;
        }
        Z_1993_T offHandStack = AutoEat.c_3005_b.Y_259_p.S_4035_N();
        Z_1993_T mainHandStack = AutoEat.c_3005_b.Y_259_p.A_2714_y();
        boolean offHandGApple = this.n_1700_B(offHandStack);
        boolean mainHandGApple = this.n_1700_B(mainHandStack);
        boolean hasGApple = offHandGApple || mainHandGApple;
        boolean offHandFood = this.J_1907_R(offHandStack) && !offHandGApple;
        boolean mainHandFood = this.J_1907_R(mainHandStack) && !mainHandGApple;
        boolean hasFood = offHandFood || mainHandFood;
        float currentHealth = AutoEat.c_3005_b.Y_259_p.g_46_E() + AutoEat.c_3005_b.Y_259_p.U_3823_u();
        int currentHunger = AutoEat.c_3005_b.Y_259_p.P_2295_B().n_1700_B();
        if (this.P_4830_p) {
            if (currentHealth > ((Float)this.zdoroveDlyaGaSetting.getValue()).floatValue()) {
                this.h_1847_R();
            } else {
                if (AutoEat.c_3005_b.Y_259_p.Y_601_j()) {
                    return;
                }
                this.h_1847_R();
            }
        }
        if (this.estZolotyeYablokiEnabled.isEnabled().booleanValue() && hasGApple && currentHealth <= ((Float)this.zdoroveDlyaGaSetting.getValue()).floatValue()) {
            x_1688_C handToUse = offHandGApple ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
            this.n_1700_B(handToUse, true);
            return;
        }
        if (hasFood && currentHunger <= ((Float)this.kolVoGolodaSetting.getValue()).intValue()) {
            if (this.prioritetGaEnabled.isEnabled().booleanValue() && this.estZolotyeYablokiEnabled.isEnabled().booleanValue() && hasGApple && currentHealth <= ((Float)this.zdoroveDlyaGaSetting.getValue()).floatValue() + 4.0f) {
                return;
            }
            x_1688_C handToUse = offHandFood ? x_1688_C.J_1907_R : x_1688_C.n_1700_B;
            this.n_1700_B(handToUse, false);
            return;
        }
        if (this.M_588_G && !this.P_4830_p && AutoEat.c_3005_b.Y_259_p.Y_601_j()) {
            if (currentHunger >= 20) {
                this.h_1847_R();
            }
            return;
        }
        if (this.M_588_G) {
            this.h_1847_R();
        }
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == Items.p_863_D || stack.J_1907_R() == Items.E_4612_l;
    }

    private boolean J_1907_R(Z_1993_T stack) {
        return !stack.n_1700_B() && stack.J_1907_R().Y_259_p();
    }

    private void n_1700_B(x_1688_C hand, boolean isGApple) {
        if (this.M_588_G && this.h_1847_R == hand && this.P_4830_p == isGApple) {
            if (!AutoEat.c_3005_b.Y_259_p.Y_601_j()) {
                AutoEat.c_3005_b.w_1457_N.processRightClick(AutoEat.c_3005_b.Y_259_p, AutoEat.c_3005_b.Y_601_j, hand);
                AutoEat.c_3005_b.P_4830_p.e_1992_r.n_1700_B(true);
            }
            return;
        }
        if (this.M_588_G) {
            this.h_1847_R();
        }
        this.M_588_G = true;
        this.P_4830_p = isGApple;
        this.h_1847_R = hand;
        AutoEat.c_3005_b.w_1457_N.processRightClick(AutoEat.c_3005_b.Y_259_p, AutoEat.c_3005_b.Y_601_j, hand);
        AutoEat.c_3005_b.P_4830_p.e_1992_r.n_1700_B(true);
    }

    private void h_1847_R() {
        if (this.M_588_G) {
            if (AutoEat.c_3005_b.Y_259_p != null && AutoEat.c_3005_b.Y_259_p.Y_601_j()) {
                AutoEat.c_3005_b.w_1457_N.onStoppedUsingItem(AutoEat.c_3005_b.Y_259_p);
            }
            AutoEat.c_3005_b.P_4830_p.e_1992_r.n_1700_B(false);
            this.M_588_G = false;
            this.P_4830_p = false;
            this.h_1847_R = null;
        }
    }

    @Override
    public void onDisable() {
        this.h_1847_R();
        super.onDisable();
    }
}



