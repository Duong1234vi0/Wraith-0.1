/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_3698_k;
import lightning.product.F_747_P;
import lightning.product.NumberSetting;
import lightning.product.M_2562_s;
import lightning.product.N_4263_v;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.Items;
import lightning.product.AttackAura;
import lightning.product.r_4811_B;
import lightning.product.u_1934_K;
import lightning.product.ModuleCategory;

public class ElytraMotion
extends Module {
    public final NumberSetting distanciyaRabotySetting = new NumberSetting("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u0440\u0430\u0431\u043e\u0442\u044b", 3.0f, 0.1f, 5.0f, 0.1f);
    private final BooleanSetting avtoFeyerverkEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e-\u0444\u0435\u0439\u0435\u0440\u0432\u0435\u0440\u043a", false);
    private final V_4557_X s_956_w = new V_4557_X();
    public boolean w_1484_f;

    public ElytraMotion() {
        super("ElytraMotion", ModuleCategory.J_1907_R);
        this.addSettings(this.distanciyaRabotySetting, this.avtoFeyerverkEnabled);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        F_3698_k elytraTarget;
        if (!ElytraMotion.c_3005_b.Y_259_p.k_578_l()) {
            this.w_1484_f = false;
            return;
        }
        AttackAura killAura = ClientBootstrap.Y_601_j().J_1907_R().J_1907_R();
        if (this.n_1700_B(killAura, elytraTarget = ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y())) {
            ElytraMotion.c_3005_b.P_4830_p.O_508_d.n_1700_B(false);
            this.w_1484_f = true;
        } else {
            ElytraMotion.c_3005_b.P_4830_p.O_508_d.n_1700_B(true);
            this.w_1484_f = false;
        }
        if (this.avtoFeyerverkEnabled.isEnabled().booleanValue() && killAura.h_1847_R() != null && F_747_P.J_1907_R(ElytraMotion.c_3005_b.Y_259_p) <= 30.0 && this.s_956_w.J_1907_R(500L)) {
            u_1934_K.w_1484_f(Items.FenceBlock);
            this.s_956_w.n_1700_B();
        }
    }

    @Y_1740_V
    public void n_1700_B(M_2562_s e) {
        if (this.w_1484_f) {
            e.R_4764_Y(new e_2866_D(0.0, 0.0, 0.0));
        }
    }

    public boolean n_1700_B(AttackAura killAura, F_3698_k elytraTarget) {
        r_4811_B target = killAura.h_1847_R();
        if (target == null) {
            return false;
        }
        boolean canTarget = elytraTarget != null && elytraTarget.h_1847_R() && ElytraMotion.c_3005_b.Y_259_p.k_578_l() && target.k_578_l();
        return !canTarget && target.R_4764_Y((N_4263_v)ElytraMotion.c_3005_b.Y_259_p) < ((Float)this.distanciyaRabotySetting.getValue()).floatValue();
    }

    @Override
    public void onDisable() {
        this.w_1484_f = false;
        super.onDisable();
    }
}



