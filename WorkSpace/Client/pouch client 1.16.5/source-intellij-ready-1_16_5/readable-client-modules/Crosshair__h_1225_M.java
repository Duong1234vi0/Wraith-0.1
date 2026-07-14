/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.d_3244_b;
import lightning.product.d_4412_Z;
import lightning.product.g_221_o;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.y_2603_k;

public class h_1225_M
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u0417\u0430\u0437\u043e\u0440", 2.0f, 0.0f, 6.0f, 0.5f);
    private final I_686_h w_1484_f = new I_686_h("\u0414\u043b\u0438\u043d\u0430", 3.0f, 2.0f, 5.0f, 0.5f);
    private final I_686_h t_148_a = new I_686_h("\u0422\u043e\u043b\u0449\u0438\u043d\u0430", 2.0f, 1.0f, 6.0f, 1.0f);
    private final p_1977_n s_956_w = new p_1977_n("\u041c\u0435\u043d\u044f\u0442\u044c \u0446\u0432\u0435\u0442 \u043f\u0440\u0438 \u043d\u0430\u0432\u0435\u0434\u0435\u043d\u0438\u0438", false);
    private final p_1977_n u_2550_I = new p_1977_n("\u0414\u0438\u043d\u0430\u043c\u0438\u0447\u0435\u0441\u043a\u0438\u0439 \u0440\u0430\u0437\u0440\u044b\u0432", false);
    private final p_1977_n M_588_G = new p_1977_n("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", true);
    private final p_1977_n P_4830_p = new p_1977_n("\u0422\u043e\u0447\u043a\u0430 \u0432 \u0446\u0435\u043d\u0442\u0440\u0435", true);

    public h_1225_M() {
        super("Crosshair", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p);
    }

    @Y_1740_V
    public void n_1700_B(d_3244_b e) {
        if (h_1225_M.c_3005_b.P_4830_p.P_4830_p().n_1700_B() || o_148_s.Y_601_j().J_1907_R().n_1700_B(d_4412_Z.class).w_1484_f()) {
            float cx = (float)c_3005_b.a_2085_x().Q_4569_t() / 2.0f;
            float cy = (float)c_3005_b.a_2085_x().M_182_A() / 2.0f;
            float cooldown = 1.0f - h_1225_M.c_3005_b.Y_259_p.k_2293_S(e.R_4764_Y());
            this.n_1700_B(e.J_1907_R(), cx, cy, cooldown);
            e.n_1700_B(true);
        }
    }

    private void n_1700_B(g_221_o stack, float centerX, float centerY, float cooldown) {
        int color;
        float gapValue = ((Float)this.v_4262_N.J_1907_R()).floatValue();
        float thicknessValue = ((Float)this.t_148_a.J_1907_R()).floatValue();
        float lengthValue = ((Float)this.w_1484_f.J_1907_R()).floatValue();
        float actualGap = this.u_2550_I.t_148_a() != false ? gapValue + 8.0f * cooldown : gapValue;
        float outline = 1.0f;
        int n = color = this.s_956_w.t_148_a() != false && h_1225_M.c_3005_b.q_2307_F != null ? H_2506_c.n_1700_B(255, 64, 64) : -1;
        if (this.M_588_G.t_148_a().booleanValue()) {
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
        if (this.P_4830_p.t_148_a().booleanValue() && actualGap > 0.0f) {
            float dotSize = thicknessValue;
            float dx = centerX - dotSize / 2.0f;
            float dy = centerY - dotSize / 2.0f;
            if (this.M_588_G.t_148_a().booleanValue()) {
                F_489_x.n_1700_B(stack, dx - outline / 2.0f, dy - outline / 2.0f, dotSize + outline, dotSize + outline, H_2506_c.n_1700_B(0, 0, 0));
                F_489_x.n_1700_B(stack, dx, dy, dotSize, dotSize, color);
            } else {
                F_489_x.n_1700_B(stack, dx, dy, dotSize, dotSize, color);
            }
        }
    }
}

