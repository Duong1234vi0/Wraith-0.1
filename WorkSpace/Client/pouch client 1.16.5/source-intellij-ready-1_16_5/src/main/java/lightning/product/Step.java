/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.lang.reflect.Field;
import lightning.product.NumberSetting;
import lightning.product.N_3268_u;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_1015_G;
import lightning.product.m_2262_U;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.u_2124_A;
import lightning.product.ModuleCategory;

public class Step
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "Vanilla", "Vanilla", "NCP");
    private final NumberSetting vysotaSetting = new NumberSetting("\u0412\u044b\u0441\u043e\u0442\u0430", 2.0f, 0.0f, 12.0f, 0.5f);
    private final BooleanSetting taymerEnabled = new BooleanSetting("\u0422\u0430\u0439\u043c\u0435\u0440", true, () -> this.rezhimMode.isMode("NCP"));
    private final BooleanSetting reversestepEnabled = new BooleanSetting("ReverseStep", false);
    private final NumberSetting vysotaReversestepSetting = new NumberSetting("\u0412\u044b\u0441\u043e\u0442\u0430 ReverseStep", 3.0f, 0.0f, 12.0f, 0.5f, () -> this.reversestepEnabled.isEnabled());
    private boolean M_588_G = false;
    private float P_4830_p = 0.6f;

    public Step() {
        super("Step", ModuleCategory.J_1907_R);
        this.addSettings(this.rezhimMode, this.vysotaSetting, this.taymerEnabled, this.reversestepEnabled, this.vysotaReversestepSetting);
    }

    @Override
    public void onEnable() {
        if (Step.c_3005_b.Y_259_p != null) {
            this.P_4830_p = Step.c_3005_b.Y_259_p.RealmsServerPing;
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (Step.c_3005_b.Y_259_p != null) {
            Step.c_3005_b.Y_259_p.RealmsServerPing = this.P_4830_p;
        }
        this.n_1700_B(1.0f);
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (Step.c_3005_b.Y_259_p == null || Step.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.M_588_G) {
            this.n_1700_B(1.0f);
            this.M_588_G = false;
        }
        Step.c_3005_b.Y_259_p.RealmsServerPing = ((Float)this.vysotaSetting.getValue()).floatValue();
        if (!this.reversestepEnabled.isEnabled().booleanValue()) {
            return;
        }
        if (Step.c_3005_b.Y_259_p.y_2772_m() || Step.c_3005_b.Y_259_p.k_578_l() || Step.c_3005_b.Y_259_p.e_() || Step.c_3005_b.Y_259_p.W_3464_O() || Step.c_3005_b.Y_259_p.RowButton() || Step.c_3005_b.P_4830_p.Ping.G_564_y() || Step.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
            return;
        }
        if (Step.c_3005_b.Y_259_p.M_1641_O() && this.J_1907_R(((Float)this.vysotaReversestepSetting.getValue()).doubleValue())) {
            Step.c_3005_b.Y_259_p.h_1847_R(Step.c_3005_b.Y_259_p.I_4348_c().J_1907_R, -((Float)this.vysotaReversestepSetting.getValue()).doubleValue(), Step.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        }
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U event) {
        if (Step.c_3005_b.Y_259_p == null || Step.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.rezhimMode.isMode("NCP")) {
            return;
        }
        double yDelta = Step.c_3005_b.Y_259_p.X_2960_b() - Step.c_3005_b.Y_259_p.A_1038_p;
        if (yDelta <= 0.75 || yDelta > ((Float)this.vysotaSetting.getValue()).doubleValue()) {
            return;
        }
        double[] offsets = this.n_1700_B(yDelta);
        if (offsets == null || offsets.length <= 1) {
            return;
        }
        if (this.taymerEnabled.isEnabled().booleanValue()) {
            this.n_1700_B(1.0f / (float)offsets.length);
            this.M_588_G = true;
        }
        for (double offset : offsets) {
            Step.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.n_1700_B(Step.c_3005_b.Y_259_p.r_715_M, Step.c_3005_b.Y_259_p.A_1038_p + offset, Step.c_3005_b.Y_259_p.i_1637_u, false));
        }
    }

    private double[] n_1700_B(double h) {
        switch ((int)(h * 10000.0)) {
            case 7500: 
            case 10000: {
                return new double[]{0.42, 0.753};
            }
            case 8125: 
            case 8750: {
                return new double[]{0.39, 0.7};
            }
            case 15000: {
                return new double[]{0.42, 0.75, 1.0, 1.16, 1.23, 1.2};
            }
            case 20000: {
                return new double[]{0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43};
            }
            case 25000: {
                return new double[]{0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907};
            }
        }
        return null;
    }

    private boolean J_1907_R(double maxDrop) {
        for (double i = 0.5; i <= maxDrop + 0.5; i += 0.25) {
            if (Step.c_3005_b.Y_601_j.a_(Step.c_3005_b.Y_259_p, Step.c_3005_b.Y_259_p.i_601_W().offset(0.0, -i, 0.0))) continue;
            return true;
        }
        return false;
    }

    private void n_1700_B(float multiplier) {
        try {
            Field timerField = c_3005_b.getClass().getDeclaredField("timer");
            timerField.setAccessible(true);
            u_2124_A timer = (u_2124_A)timerField.get(c_3005_b);
            Field tickLen = u_2124_A.class.getDeclaredField("G_564_y");
            tickLen.setAccessible(true);
            tickLen.setFloat(timer, 50.0f / multiplier);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}



