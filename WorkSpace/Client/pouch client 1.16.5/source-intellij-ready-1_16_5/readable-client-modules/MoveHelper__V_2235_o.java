/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.R_1828_C;
import lightning.product.T_2915_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_178_J;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.m_1679_b;
import lightning.product.m_2262_U;
import lightning.product.p_1977_n;
import lightning.product.t_2650_P;
import lightning.product.u_925_K;
import lightning.product.y_2603_k;
import lightning.product.y_3008_A;
import lightning.product.z_2909_G;

public class V_2235_o
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u041d\u0435\u0437\u0430\u043c\u0435\u0442\u043d\u044b\u0435 \u043f\u0440\u044b\u0436\u043a\u0438 \u043d\u0430 \u0441\u0442\u0443\u043f\u0435\u043d\u044c\u043a\u0430\u0445", true);
    private final p_1977_n w_1484_f = new p_1977_n("\u041f\u0440\u044b\u0433\u0430\u0442\u044c \u043d\u0430 \u043a\u0440\u0430\u044e \u0431\u043b\u043e\u043a\u0430", false);
    private final p_1977_n t_148_a = new p_1977_n("\u041f\u0440\u0438\u0441\u0435\u0434\u0430\u0442\u044c \u043d\u0430 \u043a\u0440\u0430\u044e \u0431\u043b\u043e\u043a\u0430", false);
    private final p_1977_n s_956_w = new p_1977_n("\u0410\u0432\u0442\u043e \u043f\u0440\u0438\u0441\u0435\u0434\u0430\u043d\u0438\u0435 \u0432 \u043d\u0438\u0437\u043a\u0438\u0445 \u043f\u0440\u043e\u0445\u043e\u0434\u0430\u0445", false);
    private final p_1977_n u_2550_I = new p_1977_n("\u0423\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u0435 \u043d\u0430 \u043b\u044c\u0434\u0443", false);
    private final p_1977_n M_588_G = new p_1977_n("\u041f\u0440\u044b\u0436\u043a\u0438 \u043d\u0430 \u0441\u043b\u0430\u0439\u043c \u0431\u043b\u043e\u043a\u0430\u0445", false);
    private boolean P_4830_p;

    public V_2235_o() {
        super("MoveHelper", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        c_1514_x belowPos;
        K_4074_S belowState;
        if (V_2235_o.c_3005_b.Y_259_p == null || V_2235_o.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.v_4262_N.t_148_a().booleanValue() && u_925_K.n_1700_B() && !V_2235_o.c_3005_b.Y_259_p.Z_875_P() && this.h_1847_R() && V_2235_o.c_3005_b.Y_259_p.M_1641_O() && !V_2235_o.c_3005_b.P_4830_p.T_69_K.G_564_y()) {
            V_2235_o.c_3005_b.Y_259_p.e_837_t();
        }
        if (this.w_1484_f.t_148_a().booleanValue() && u_925_K.n_1700_B(0.001f) && V_2235_o.c_3005_b.Y_259_p.M_1641_O()) {
            V_2235_o.c_3005_b.Y_259_p.e_837_t();
        }
        if (this.M_588_G.t_148_a().booleanValue() && V_2235_o.c_3005_b.Y_259_p.M_1641_O() && (belowState = V_2235_o.c_3005_b.Y_601_j.getBlockState(belowPos = new c_1514_x(V_2235_o.c_3005_b.Y_259_p.s_4990_V()).down())).J_1907_R() == a_3742_W.g_4841_c && u_925_K.n_1700_B()) {
            V_2235_o.c_3005_b.Y_259_p.e_837_t();
        }
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        T_2915_h block;
        K_4074_S belowState;
        c_1514_x belowPos;
        if (V_2235_o.c_3005_b.Y_259_p == null || V_2235_o.c_3005_b.Y_601_j == null) {
            return;
        }
        if (V_2235_o.c_3005_b.Y_259_p.M_1641_O()) {
            belowPos = new c_1514_x(V_2235_o.c_3005_b.Y_259_p.s_4990_V()).add(0, -1, 0);
            belowState = V_2235_o.c_3005_b.Y_601_j.getBlockState(belowPos);
            if (this.n_1700_B(belowState)) {
                if (!this.P_4830_p) {
                    this.P_4830_p = true;
                }
            } else if (this.P_4830_p) {
                this.P_4830_p = false;
            }
        } else if (this.P_4830_p) {
            this.P_4830_p = false;
        }
        if (this.u_2550_I.t_148_a().booleanValue() && V_2235_o.c_3005_b.Y_259_p.M_1641_O() && u_925_K.n_1700_B() && ((block = (belowState = V_2235_o.c_3005_b.Y_601_j.getBlockState(belowPos = new c_1514_x(V_2235_o.c_3005_b.Y_259_p.s_4990_V()).down())).J_1907_R()) == a_3742_W.O_1795_e || block == a_3742_W.n_94_R || block == a_3742_W.G_4691_Q)) {
            double speed = 0.3;
            if (block == a_3742_W.n_94_R) {
                speed = 0.35;
            } else if (block == a_3742_W.G_4691_Q) {
                speed = 0.4;
            }
            e_2866_D motion = V_2235_o.c_3005_b.Y_259_p.I_4348_c();
            double forward = V_2235_o.c_3005_b.Y_259_p.G_564_y.moveForward;
            double strafe = V_2235_o.c_3005_b.Y_259_p.G_564_y.moveStrafe;
            if (forward != 0.0 || strafe != 0.0) {
                double z;
                double yaw = Math.toRadians(V_2235_o.c_3005_b.Y_259_p.p_178_J);
                double x = -Math.sin(yaw) * forward + Math.cos(yaw) * strafe;
                double len = Math.sqrt(x * x + (z = Math.cos(yaw) * forward + Math.sin(yaw) * strafe) * z);
                if (len > 0.0) {
                    x /= len;
                    z /= len;
                }
                V_2235_o.c_3005_b.Y_259_p.h_1847_R(x * speed, motion.R_4764_Y, z * speed);
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(a_178_J event) {
        if (this.t_148_a.t_148_a().booleanValue() && !V_2235_o.c_3005_b.P_4830_p.p_178_J.G_564_y()) {
            event.u_1723_Y(this.P_4830_p);
        }
        if (this.s_956_w.t_148_a().booleanValue() && !V_2235_o.c_3005_b.P_4830_p.p_178_J.G_564_y() && this.Q_4569_t()) {
            event.u_1723_Y(true);
        }
    }

    private boolean h_1847_R() {
        e_2866_D playerPos = V_2235_o.c_3005_b.Y_259_p.s_4990_V();
        double yaw = Math.toRadians(V_2235_o.c_3005_b.Y_259_p.p_178_J);
        double x = -Math.sin(yaw);
        double z = Math.cos(yaw);
        c_1514_x playerBlockPos = new c_1514_x(playerPos.J_1907_R, playerPos.R_4764_Y, playerPos.G_564_y);
        c_1514_x frontPos = new c_1514_x(playerPos.J_1907_R + x, playerPos.R_4764_Y, playerPos.G_564_y + z);
        c_1514_x frontUpPos = frontPos.up();
        c_1514_x belowPos = playerBlockPos.down();
        boolean isOnStairsOrSlab = V_2235_o.c_3005_b.Y_601_j.getBlockState(belowPos).J_1907_R() instanceof z_2909_G || V_2235_o.c_3005_b.Y_601_j.getBlockState(belowPos).J_1907_R() instanceof y_3008_A;
        boolean isFrontClimbable = V_2235_o.c_3005_b.Y_601_j.getBlockState(frontPos).J_1907_R() instanceof z_2909_G || V_2235_o.c_3005_b.Y_601_j.getBlockState(frontPos).J_1907_R() instanceof y_3008_A || V_2235_o.c_3005_b.Y_601_j.getBlockState(frontUpPos).J_1907_R() instanceof z_2909_G || V_2235_o.c_3005_b.Y_601_j.getBlockState(frontUpPos).J_1907_R() instanceof y_3008_A;
        return isOnStairsOrSlab && isFrontClimbable;
    }

    private boolean n_1700_B(K_4074_S state) {
        return state.J_1907_R() instanceof m_1679_b || state.R_4764_Y() == t_2650_P.v_4262_N || state.R_4764_Y() == t_2650_P.P_1922_E || state.R_4764_Y() == t_2650_P.s_956_w || state.R_4764_Y() == t_2650_P.M_588_G || state.J_1907_R() == a_3742_W.X_290_I || state.J_1907_R() instanceof R_1828_C || state.J_1907_R() instanceof y_3008_A || state.u_2550_I(V_2235_o.c_3005_b.Y_601_j, new c_1514_x(V_2235_o.c_3005_b.Y_259_p.s_4990_V()).add(0, -1, 0)).J_1907_R();
    }

    private boolean Q_4569_t() {
        e_2866_D playerPos = V_2235_o.c_3005_b.Y_259_p.s_4990_V();
        c_1514_x headPos = new c_1514_x(playerPos.J_1907_R, playerPos.R_4764_Y + 1.5, playerPos.G_564_y);
        K_4074_S headState = V_2235_o.c_3005_b.Y_601_j.getBlockState(headPos);
        return !headState.v_4262_N() && !headState.u_2550_I(V_2235_o.c_3005_b.Y_601_j, headPos).J_1907_R();
    }

    private c_1514_x M_182_A() {
        e_2866_D playerPos = V_2235_o.c_3005_b.Y_259_p.s_4990_V();
        double yaw = Math.toRadians(V_2235_o.c_3005_b.Y_259_p.p_178_J);
        double x = -Math.sin(yaw);
        double z = Math.cos(yaw);
        return new c_1514_x(playerPos.J_1907_R + x, playerPos.R_4764_Y, playerPos.G_564_y + z);
    }
}

