/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.J_588_u;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.e_1174_E;
import lightning.product.h_1015_G;
import lightning.product.i_4434_b;
import lightning.product.k_2610_C;
import lightning.product.q_3206_W;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.u_925_K;
import lightning.product.y_2603_k;

public class J_617_O
extends X_3546_T {
    public q_366_O v_4262_N = new q_366_O("Mode", "MetaHvH", "MetaHvH", "Custom");
    private final I_686_h w_1484_f = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 0.2f, 0.2f, 1.05f, 0.01f, () -> this.v_4262_N.J_1907_R("Custom"));
    private final q_3206_W t_148_a = new q_3206_W("\u0411\u0438\u043d\u0434 \u0431\u0443\u0441\u0442\u0430", () -> this.v_4262_N.J_1907_R("MetaHvH"));
    private final I_686_h s_956_w = new I_686_h("\u0412\u0440\u0435\u043c\u044f \u0431\u0443\u0441\u0442\u0430 (\u0441\u0435\u043a)", 1.0f, 0.5f, 10.0f, 0.01f, () -> this.v_4262_N.J_1907_R("MetaHvH"));
    private final I_686_h u_2550_I = new I_686_h("\u041a\u0443\u043b\u0434\u0430\u0443\u043d \u0431\u0443\u0441\u0442\u0430 (\u0441\u0435\u043a)", 12.0f, 3.0f, 30.0f, 0.5f, () -> this.v_4262_N.J_1907_R("MetaHvH"));
    private final I_686_h M_588_G = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0431\u0443\u0441\u0442\u0430", 0.4f, 0.1f, 1.0f, 0.01f, () -> this.v_4262_N.J_1907_R("MetaHvH"));
    private static final float P_4830_p = 0.65f;
    private static final float h_1847_R = 0.65f;
    private static final float Q_4569_t = 0.8f;
    private int M_182_A = -1;
    private long t_1786_h = -1L;
    private long N_4405_n = -1L;
    private boolean w_1457_N = false;
    private boolean Y_601_j = false;

    public J_617_O() {
        super("Jesus", y_2603_k.J_1907_R);
        this.n_1700_B(this.t_148_a, this.v_4262_N, this.w_1484_f, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (!this.v_4262_N.J_1907_R("MetaHvH")) {
            return;
        }
        if (e.J_1907_R()) {
            return;
        }
        if (e.n_1700_B() == ((Integer)this.t_148_a.J_1907_R()).intValue() && !this.w_1457_N) {
            long currentTime = System.currentTimeMillis();
            float cooldownDurationMs = ((Float)this.u_2550_I.J_1907_R()).floatValue() * 1000.0f;
            if (this.N_4405_n == -1L || (float)(currentTime - this.N_4405_n) >= cooldownDurationMs) {
                this.M_182_A = e.n_1700_B();
                this.t_1786_h = currentTime;
                this.N_4405_n = currentTime;
                this.w_1457_N = true;
                this.Y_601_j = false;
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        boolean isMoving;
        float finalSpeed;
        if (J_617_O.c_3005_b.Y_259_p == null) {
            return;
        }
        if (!J_617_O.c_3005_b.Y_259_p.a_2180_A() && !J_617_O.c_3005_b.Y_259_p.W_3464_O()) {
            return;
        }
        k_2610_C speedEffect = J_617_O.c_3005_b.Y_259_p.R_4764_Y(J_588_u.n_1700_B);
        k_2610_C slowness = J_617_O.c_3005_b.Y_259_p.R_4764_Y(J_588_u.J_1907_R);
        Z_1993_T offhand = J_617_O.c_3005_b.Y_259_p.S_4035_N();
        String offhandName = offhand.N_4405_n().getString();
        float boostDurationMs = ((Float)this.s_956_w.J_1907_R()).floatValue() * 1000.0f;
        if (this.w_1457_N && (float)(System.currentTimeMillis() - this.t_1786_h) > boostDurationMs) {
            this.w_1457_N = false;
            this.M_182_A = -1;
        }
        float cooldownDurationMs = ((Float)this.u_2550_I.J_1907_R()).floatValue() * 1000.0f;
        if (!this.Y_601_j && this.N_4405_n != -1L && (float)(System.currentTimeMillis() - this.N_4405_n) >= cooldownDurationMs) {
            this.Y_601_j = true;
        }
        switch ((String)this.v_4262_N.J_1907_R()) {
            case "MetaHvH": {
                finalSpeed = this.n_1700_B(speedEffect);
                if (offhandName.contains("\u041b\u043e\u043c\u0442\u0438\u043a \u0414\u044b\u043d\u0438") && speedEffect != null && speedEffect.R_4764_Y() == 2) {
                    finalSpeed = 0.492775f;
                } else if (speedEffect != null) {
                    if (speedEffect.R_4764_Y() == 2) {
                        finalSpeed = 0.489325f;
                        if (offhandName.contains("\u0428\u0430\u0440 \u0410\u0438\u0434\u0430 2") || offhandName.contains("\u0422\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u0412\u0435\u043d\u043e\u043c\u0430") || offhandName.contains("\u0428\u0430\u0440 \u0413\u0435\u0440\u0430\u043a\u043b\u0430 2")) {
                            finalSpeed = 0.431f;
                        }
                    } else if (speedEffect.R_4764_Y() == 1) {
                        finalSpeed = 0.4255f;
                        if (offhandName.contains("\u0428\u0430\u0440 \u0410\u0438\u0434\u0430 2") || offhandName.contains("\u0422\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u0412\u0435\u043d\u043e\u043c\u0430") || offhandName.contains("\u0428\u0430\u0440 \u0413\u0435\u0440\u0430\u043a\u043b\u0430 2")) {
                            finalSpeed = 0.5f;
                        }
                    }
                } else {
                    finalSpeed = 0.28934002f;
                    if (offhandName.contains("\u0428\u0430\u0440 \u0410\u0438\u0434\u0430 2") || offhandName.contains("\u0422\u0430\u043b\u0438\u0441\u043c\u0430\u043d \u0412\u0435\u043d\u043e\u043c\u0430") || offhandName.contains("\u0428\u0430\u0440 \u0413\u0435\u0440\u0430\u043a\u043b\u0430 2")) {
                        finalSpeed = 0.365f;
                    }
                }
                if (slowness != null) {
                    finalSpeed *= 0.85f;
                }
                if (this.h_1847_R()) {
                    finalSpeed /= 1.2f;
                }
                if (!this.w_1457_N || this.M_182_A != (Integer)this.t_148_a.J_1907_R()) break;
                float boostValue = ((Float)this.M_588_G.J_1907_R()).floatValue();
                if (this.h_1847_R()) {
                    boostValue /= 1.35f;
                }
                finalSpeed += boostValue;
                break;
            }
            case "Custom": {
                finalSpeed = ((Float)this.w_1484_f.J_1907_R()).floatValue();
                if (slowness != null) {
                    finalSpeed *= 0.85f;
                }
                if (!this.h_1847_R()) break;
                finalSpeed /= 1.35f;
                break;
            }
            default: {
                finalSpeed = 0.65f;
            }
        }
        boolean bl = isMoving = J_617_O.c_3005_b.P_4830_p.O_508_d.G_564_y() || J_617_O.c_3005_b.P_4830_p.A_1038_p.G_564_y() || J_617_O.c_3005_b.P_4830_p.r_715_M.G_564_y() || J_617_O.c_3005_b.P_4830_p.i_1637_u.G_564_y();
        if (!isMoving) {
            J_617_O.c_3005_b.Y_259_p.T_69_K.J_1907_R = 0.0;
            J_617_O.c_3005_b.Y_259_p.T_69_K.G_564_y = 0.0;
        }
        J_617_O.c_3005_b.Y_259_p.T_69_K.R_4764_Y = J_617_O.c_3005_b.P_4830_p.T_69_K.G_564_y() ? 0.019 : 0.003;
        u_925_K.n_1700_B((double)finalSpeed);
    }

    private float n_1700_B(k_2610_C speedEffect) {
        if (speedEffect != null) {
            int amplifier = speedEffect.R_4764_Y();
            if (amplifier >= 3) {
                return 0.8f;
            }
            if (amplifier == 2) {
                return 0.65f;
            }
        }
        return 0.65f;
    }

    private boolean h_1847_R() {
        Z_1993_T headStack = J_617_O.c_3005_b.Y_259_p.J_1907_R(e_1174_E.u_1723_Y);
        return this.n_1700_B(headStack) || this.J_1907_R(headStack);
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack.J_1907_R() != q_4592_V.C_3560_B || !stack.h_1847_R()) {
            return false;
        }
        String tag = stack.Q_4569_t().toString();
        if (!tag.contains("AttributeModifiers")) {
            return false;
        }
        try {
            String headName = tag.split("AttributeModifiers")[1];
            return headName.startsWith(":[{Amount:3.0d,Slot:\"head\",AttributeName:\"minecraft:generic.armor\",Operation:0,UUID:[I;427683850,761809167,-1124274585,-962634053]");
        }
        catch (Exception e) {
            return false;
        }
    }

    private boolean J_1907_R(Z_1993_T stack) {
        if (stack.J_1907_R() != q_4592_V.C_3560_B || !stack.h_1847_R()) {
            return false;
        }
        String tag = stack.Q_4569_t().toString();
        if (!tag.contains("AttributeModifiers")) {
            return false;
        }
        try {
            String headName = tag.split("AttributeModifiers")[1];
            return headName.startsWith(":[{Amount:3.5d,Slot:\"head\",AttributeName:\"minecraft:generic.armor\",Operation:0,UUID:[I;-368453572,-1112977890,-1779712266,-159547550],Name:\"GENERIC_ARMOR\"},{Amount:2.5d,Slot:\"head\",AttributeName:\"minecraft:generic.armor_toughness\",Operation:0,UUID:[I;-177222828,1392200633,-1721737065,1359173546]");
        }
        catch (Exception e) {
            return false;
        }
    }
}

