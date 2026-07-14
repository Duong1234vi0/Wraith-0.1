/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.m_2262_U;
import lightning.product.p_1977_n;
import lightning.product.q_366_O;
import lightning.product.u_925_K;
import lightning.product.y_2603_k;

public class N_3408_T
extends X_3546_T {
    public final q_366_O v_4262_N = new q_366_O("\u0422\u0438\u043f", "\u0421\u043a\u043e\u043b\u044c\u0436\u0435\u043d\u0438\u0435", "\u0421\u043a\u043e\u043b\u044c\u0436\u0435\u043d\u0438\u0435", "\u041f\u0440\u044b\u0436\u043a\u0438", "\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0435", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "ReallyWorld Dragon");
    public final I_686_h w_1484_f = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 1.5f, 0.1f, 10.0f, 0.1f, () -> !this.v_4262_N.J_1907_R("\u041f\u0440\u044b\u0436\u043a\u0438") && !this.v_4262_N.J_1907_R("ReallyWorld Dragon"));
    public final I_686_h t_148_a = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u043f\u043e Y", 1.5f, 0.1f, 10.0f, 0.1f, () -> !this.v_4262_N.J_1907_R("\u041f\u0440\u044b\u0436\u043a\u0438") && !this.v_4262_N.J_1907_R("ReallyWorld Dragon"));
    public final p_1977_n s_956_w = new p_1977_n("\u041b\u0435\u0442\u0435\u0442\u044c \u0432\u043f\u0435\u0440\u0435\u0434", false, () -> this.v_4262_N.J_1907_R("ReallyWorld Dragon"));

    public N_3408_T() {
        super("Flight", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w);
    }

    @Override
    public void J_1907_R() {
        if (this.s_956_w.t_148_a().booleanValue() && this.v_4262_N.J_1907_R("ReallyWorld Dragon")) {
            N_3408_T.c_3005_b.P_4830_p.O_508_d.n_1700_B(false);
        }
        super.J_1907_R();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        boolean isSneaking = N_3408_T.c_3005_b.P_4830_p.p_178_J.G_564_y();
        boolean isJumping = N_3408_T.c_3005_b.P_4830_p.T_69_K.G_564_y();
        float motionSpeed = ((Float)this.t_148_a.J_1907_R()).floatValue();
        switch ((String)this.v_4262_N.J_1907_R()) {
            case "\u0421\u043a\u043e\u043b\u044c\u0436\u0435\u043d\u0438\u0435": {
                N_3408_T.c_3005_b.Y_259_p.h_1847_R(0.0, -0.005f, 0.0);
                if (isSneaking) {
                    N_3408_T.c_3005_b.Y_259_p.T_69_K.R_4764_Y = -motionSpeed;
                } else if (isJumping) {
                    N_3408_T.c_3005_b.Y_259_p.T_69_K.R_4764_Y = motionSpeed;
                }
                if (N_3408_T.c_3005_b.Y_259_p.M_1641_O()) {
                    N_3408_T.c_3005_b.Y_259_p.e_837_t();
                }
                u_925_K.n_1700_B((double)((Float)this.w_1484_f.J_1907_R()).floatValue());
                break;
            }
            case "\u041e\u0431\u044b\u0447\u043d\u044b\u0439": {
                N_3408_T.c_3005_b.Y_259_p.h_1847_R(0.0, 0.0, 0.0);
                if (isSneaking) {
                    N_3408_T.c_3005_b.Y_259_p.T_69_K.R_4764_Y = -motionSpeed;
                } else if (isJumping) {
                    N_3408_T.c_3005_b.Y_259_p.T_69_K.R_4764_Y = motionSpeed;
                }
                if (N_3408_T.c_3005_b.Y_259_p.M_1641_O()) {
                    N_3408_T.c_3005_b.Y_259_p.e_837_t();
                }
                u_925_K.n_1700_B((double)((Float)this.w_1484_f.J_1907_R()).floatValue());
                break;
            }
            case "\u0414\u0432\u0438\u0436\u0435\u043d\u0438\u0435": {
                if (isSneaking) {
                    N_3408_T.c_3005_b.Y_259_p.T_69_K.R_4764_Y = -motionSpeed;
                } else if (isJumping) {
                    N_3408_T.c_3005_b.Y_259_p.T_69_K.R_4764_Y = motionSpeed;
                }
                u_925_K.n_1700_B((double)((Float)this.w_1484_f.J_1907_R()).floatValue());
                break;
            }
            case "\u041f\u0440\u044b\u0436\u043a\u0438": {
                if (!isJumping) break;
                N_3408_T.c_3005_b.Y_259_p.e_837_t();
                break;
            }
            case "ReallyWorld Dragon": {
                boolean vertical;
                if (!N_3408_T.c_3005_b.Y_259_p.C_415_h.J_1907_R) break;
                if (this.s_956_w.t_148_a().booleanValue()) {
                    N_3408_T.c_3005_b.P_4830_p.O_508_d.n_1700_B(true);
                    N_3408_T.c_3005_b.Y_259_p.G_564_y.moveForward = 1.0f;
                }
                N_3408_T.c_3005_b.Y_259_p.h_1847_R(0.0, 0.0, 0.0);
                boolean moving = u_925_K.n_1700_B();
                boolean bl = vertical = isSneaking || isJumping;
                if (moving) {
                    u_925_K.n_1700_B(vertical ? 0.7 : 1.05);
                }
                if (isSneaking) {
                    N_3408_T.c_3005_b.Y_259_p.T_69_K.R_4764_Y = -0.74;
                    break;
                }
                if (isJumping) {
                    N_3408_T.c_3005_b.Y_259_p.T_69_K.R_4764_Y = 0.74;
                    break;
                }
                if (moving) break;
                N_3408_T.c_3005_b.Y_259_p.T_69_K.R_4764_Y = 0.0;
                break;
            }
        }
    }
}

