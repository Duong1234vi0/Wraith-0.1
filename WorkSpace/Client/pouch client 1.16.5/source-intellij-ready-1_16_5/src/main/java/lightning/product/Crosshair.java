/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.NumberSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.d_3244_b;
import lightning.product.FreeCam;
import lightning.product.g_221_o;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.ModuleCategory;

public class Crosshair
extends Module {
    private final NumberSetting zazorSetting = new NumberSetting("\u0417\u0430\u0437\u043e\u0440", 2.0f, 0.0f, 6.0f, 0.5f);
    private final NumberSetting dlinaSetting = new NumberSetting("\u0414\u043b\u0438\u043d\u0430", 3.0f, 2.0f, 5.0f, 0.5f);
    private final NumberSetting tolschinaSetting = new NumberSetting("\u0422\u043e\u043b\u0449\u0438\u043d\u0430", 2.0f, 1.0f, 6.0f, 1.0f);
    private final BooleanSetting menyatCvetPriNavedeniiEnabled = new BooleanSetting("\u041c\u0435\u043d\u044f\u0442\u044c \u0446\u0432\u0435\u0442 \u043f\u0440\u0438 \u043d\u0430\u0432\u0435\u0434\u0435\u043d\u0438\u0438", false);
    private final BooleanSetting dinamicheskiyRazryvEnabled = new BooleanSetting("\u0414\u0438\u043d\u0430\u043c\u0438\u0447\u0435\u0441\u043a\u0438\u0439 \u0440\u0430\u0437\u0440\u044b\u0432", false);
    private final BooleanSetting obvodkaEnabled = new BooleanSetting("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", true);
    private final BooleanSetting tochkaVCentreEnabled = new BooleanSetting("\u0422\u043e\u0447\u043a\u0430 \u0432 \u0446\u0435\u043d\u0442\u0440\u0435", true);

    public Crosshair() {
        super("Crosshair", ModuleCategory.R_4764_Y);
        this.addSettings(this.zazorSetting, this.dlinaSetting, this.tolschinaSetting, this.menyatCvetPriNavedeniiEnabled, this.dinamicheskiyRazryvEnabled, this.obvodkaEnabled, this.tochkaVCentreEnabled);
    }

    @Y_1740_V
    public void n_1700_B(d_3244_b e) {
        if (Crosshair.c_3005_b.P_4830_p.P_4830_p().n_1700_B() || ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(FreeCam.class).w_1484_f()) {
            float cx = (float)c_3005_b.RealmsServerPing().Q_4569_t() / 2.0f;
            float cy = (float)c_3005_b.RealmsServerPing().M_182_A() / 2.0f;
            float cooldown = 1.0f - Crosshair.c_3005_b.Y_259_p.k_2293_S(e.R_4764_Y());
            this.n_1700_B(e.J_1907_R(), cx, cy, cooldown);
            e.n_1700_B(true);
        }
    }

    private void n_1700_B(g_221_o stack, float centerX, float centerY, float cooldown) {
        int color;
        float gapValue = ((Float)this.zazorSetting.getValue()).floatValue();
        float thicknessValue = ((Float)this.tolschinaSetting.getValue()).floatValue();
        float lengthValue = ((Float)this.dlinaSetting.getValue()).floatValue();
        float actualGap = this.dinamicheskiyRazryvEnabled.isEnabled() != false ? gapValue + 8.0f * cooldown : gapValue;
        float outline = 1.0f;
        int n = color = this.menyatCvetPriNavedeniiEnabled.isEnabled() != false && Crosshair.c_3005_b.q_2307_F != null ? H_2506_c.n_1700_B(255, 64, 64) : -1;
        if (this.obvodkaEnabled.isEnabled().booleanValue()) {
            F_489_x.n_1700_B(stack, centerX + actualGap - outline / 2.0f, centerY - thicknessValue / 2.0f - outline / 2.0f, lengthValue + outline, thicknessValue + outline, H_2506_c.n_1700_B(0, 0, 0));
            F_489_x.n_1700_B(stack, centerX - actualGap - lengthValue - outline / 2.0f, centerY - thicknessValue / 2.0f - outline / 2.0f, lengthValue + outline, thicknessValue + outline, H_2506_c.n_1700_B(0, 0, 0));
            F_489_x.n_1700_B(stack, centerX - thicknessValue / 2.0f - outline / 2.0f, centerY - actualGap - lengthValue - outline / 2.0f, thicknessValue + outline, lengthValue + outline, H_2506_c.n_1700_B(0, 0, 0));
            F_489_x.n_1700_B(stack, centerX - thicknessValue / 2.0f - outline / 2.0f, centerY + actualGap - outline / 2.0f, thicknessValue + outline, lengthValue + outline, H_2506_c.n_1700_B(0, 0, 0));
            F_489_x.n_1700_B(stack, centerX + actualGap, centerY - thicknessValue / 2.0f, lengthValue, thicknessValue, color);
            F_489_x.n_1700_B(stack, centerX - actualGap - lengthValue, centerY - thicknessValue / 2.0f, lengthValue, thicknessValue, color);
            F_489_x.n_1700_B(stack, centerX - thicknessValue / 2.0f, centerY - actualGap - lengthValue, thicknessValue, lengthValue, color);
            F_489_x.n_1700_B(stack, centerX - thicknessValue / 2.0f, centerY + actualGap, thicknessValue, lengthValue, color);
        } else {
            F_489_x.n_1700_B(stack, centerX + actualGap, centerY - thicknessValue / 2.0f, lengthValue, thicknessValue, color);
            F_489_x.n_1700_B(stack, centerX - actualGap - lengthValue, centerY - thicknessValue / 2.0f, lengthValue, thicknessValue, color);
            F_489_x.n_1700_B(stack, centerX - thicknessValue / 2.0f, centerY - actualGap - lengthValue, thicknessValue, lengthValue, color);
            F_489_x.n_1700_B(stack, centerX - thicknessValue / 2.0f, centerY + actualGap, thicknessValue, lengthValue, color);
        }
        if (this.tochkaVCentreEnabled.isEnabled().booleanValue() && actualGap > 0.0f) {
            float dotSize = thicknessValue;
            float dx = centerX - dotSize / 2.0f;
            float dy = centerY - dotSize / 2.0f;
            if (this.obvodkaEnabled.isEnabled().booleanValue()) {
                F_489_x.n_1700_B(stack, dx - outline / 2.0f, dy - outline / 2.0f, dotSize + outline, dotSize + outline, H_2506_c.n_1700_B(0, 0, 0));
                F_489_x.n_1700_B(stack, dx, dy, dotSize, dotSize, color);
            } else {
                F_489_x.n_1700_B(stack, dx, dy, dotSize, dotSize, color);
            }
        }
    }
}



