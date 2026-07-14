/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.h_1015_G;
import lightning.product.BooleanSetting;
import lightning.product.q_3115_L;
import lightning.product.Items;
import lightning.product.ModuleCategory;

public class FastPlace
extends Module {
    private final BooleanSetting tolkoBezPvpRezhimaEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0431\u0435\u0437 \u043f\u0432\u043f \u0440\u0435\u0436\u0438\u043c\u0430", false);
    private final NumberSetting zaderzhkaSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 0.0f, 0.0f, 4.0f, 1.0f);
    private final BooleanSetting puzyrkovOpytaEnabled = new BooleanSetting("\u041f\u0443\u0437\u044b\u0440\u044c\u043a\u043e\u0432 \u043e\u043f\u044b\u0442\u0430", false);
    private final BooleanSetting blokovEnabled = new BooleanSetting("\u0411\u043b\u043e\u043a\u043e\u0432", false);
    private final BooleanSetting yaicEnabled = new BooleanSetting("\u042f\u0438\u0446", false);
    private final BooleanSetting snezhkovEnabled = new BooleanSetting("\u0421\u043d\u0435\u0436\u043a\u043e\u0432", false);
    private final BooleanSetting enderPerlovEnabled = new BooleanSetting("\u042d\u043d\u0434\u0435\u0440-\u043f\u0435\u0440\u043b\u043e\u0432", false);
    private final BooleanSetting vzryvnyhZeliyEnabled = new BooleanSetting("\u0412\u0437\u0440\u044b\u0432\u043d\u044b\u0445 \u0437\u0435\u043b\u0438\u0439", false);
    private final BooleanSetting osedayuschihZeliyEnabled = new BooleanSetting("\u041e\u0441\u0435\u0434\u0430\u044e\u0449\u0438\u0445 \u0437\u0435\u043b\u0438\u0439", false);

    public FastPlace() {
        super("FastPlace", ModuleCategory.G_564_y);
        this.addSettings(this.tolkoBezPvpRezhimaEnabled, this.zaderzhkaSetting, this.blokovEnabled, this.puzyrkovOpytaEnabled, this.yaicEnabled, this.snezhkovEnabled, this.enderPerlovEnabled, this.vzryvnyhZeliyEnabled, this.osedayuschihZeliyEnabled);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (FastPlace.c_3005_b.Y_259_p == null || FastPlace.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.tolkoBezPvpRezhimaEnabled.isEnabled().booleanValue() && q_3115_L.n_1700_B()) {
            return;
        }
        Z_1993_T mainHand = FastPlace.c_3005_b.Y_259_p.A_2714_y();
        Z_1993_T offHand = FastPlace.c_3005_b.Y_259_p.S_4035_N();
        boolean shouldApplyFastPlace = false;
        if (this.blokovEnabled.isEnabled().booleanValue() && (this.n_1700_B(mainHand) || this.n_1700_B(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.yaicEnabled.isEnabled().booleanValue() && (this.J_1907_R(mainHand) || this.J_1907_R(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.snezhkovEnabled.isEnabled().booleanValue() && (this.R_4764_Y(mainHand) || this.R_4764_Y(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.enderPerlovEnabled.isEnabled().booleanValue() && (this.G_564_y(mainHand) || this.G_564_y(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.puzyrkovOpytaEnabled.isEnabled().booleanValue() && (this.P_1922_E(mainHand) || this.P_1922_E(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.vzryvnyhZeliyEnabled.isEnabled().booleanValue() && (this.u_1723_Y(mainHand) || this.u_1723_Y(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (this.osedayuschihZeliyEnabled.isEnabled().booleanValue() && (this.v_4262_N(mainHand) || this.v_4262_N(offHand))) {
            shouldApplyFastPlace = true;
        }
        if (shouldApplyFastPlace) {
            FastPlace.c_3005_b.c_3005_b = ((Float)this.zaderzhkaSetting.getValue()).intValue();
        }
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R().getClass().getName().contains("BlockItem");
    }

    private boolean J_1907_R(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == Items.s_4405_m;
    }

    private boolean R_4764_Y(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == Items.i_770_g;
    }

    private boolean G_564_y(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == Items.v_2746_S;
    }

    private boolean P_1922_E(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R() == Items.s_3084_y;
    }

    private boolean u_1723_Y(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R().getClass().getName().contains("SplashPotionItem");
    }

    private boolean v_4262_N(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        return stack.J_1907_R().getClass().getName().contains("LingeringPotionItem");
    }
}



