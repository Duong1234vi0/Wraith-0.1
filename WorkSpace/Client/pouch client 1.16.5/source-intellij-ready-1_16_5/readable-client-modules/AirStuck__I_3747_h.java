/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.E_3343_g;
import lightning.product.I_686_h;
import lightning.product.N_3268_u;
import lightning.product.Q_2753_H;
import lightning.product.S_4035_N;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.m_2262_U;
import lightning.product.p_1977_n;
import lightning.product.y_2603_k;
import lombok.Generated;

public class I_3747_h
extends X_3546_T {
    private final p_1977_n v_4262_N = new p_1977_n("\u0418\u0437\u043c\u0435\u043d\u044f\u0442\u044c \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044e \u043a\u0438\u043b\u043b\u0430\u0443\u0440\u044b", false);
    private final I_686_h w_1484_f = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f \u043a\u0438\u043b\u043b\u0430\u0443\u0440\u044b", 3.5f, 2.0f, 6.0f, 0.1f, this.v_4262_N::t_148_a);
    private final p_1977_n t_148_a = new p_1977_n("\u041b\u043e\u0432\u0438\u0442\u044c \u043c\u043e\u043c\u0435\u043d\u0442", true);
    private double s_956_w = Double.NaN;
    private boolean u_2550_I = false;

    public I_3747_h() {
        super("AirStuck", y_2603_k.J_1907_R);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        if (this.t_148_a.t_148_a().booleanValue()) {
            this.s_956_w = I_3747_h.c_3005_b.Y_259_p != null && !I_3747_h.c_3005_b.Y_259_p.M_1641_O() ? I_3747_h.c_3005_b.Y_259_p.X_2960_b() : Double.NaN;
            this.u_2550_I = false;
        } else {
            this.u_2550_I = true;
        }
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.u_2550_I = false;
    }

    public float h_1847_R() {
        if (this.w_1484_f() && this.v_4262_N.t_148_a().booleanValue()) {
            return ((Float)this.w_1484_f.J_1907_R()).floatValue();
        }
        return -1.0f;
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        if (this.u_2550_I && e.G_564_y() instanceof N_3268_u) {
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(E_3343_g e) {
        this.R_4764_Y();
    }

    @Y_1740_V
    public void n_1700_B(m_2262_U e) {
        if (I_3747_h.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.t_148_a.t_148_a().booleanValue()) {
            double currentY = I_3747_h.c_3005_b.Y_259_p.X_2960_b();
            if (I_3747_h.c_3005_b.Y_259_p.M_1641_O()) {
                this.s_956_w = Double.NaN;
                this.u_2550_I = false;
            } else if (!this.u_2550_I) {
                if (Double.isNaN(this.s_956_w)) {
                    this.s_956_w = currentY;
                } else if (currentY > this.s_956_w) {
                    this.s_956_w = currentY;
                } else if (currentY < this.s_956_w) {
                    this.u_2550_I = true;
                }
            }
        }
        if (this.u_2550_I) {
            e.J_1907_R(0.0);
            e.n_1700_B(true);
        }
    }

    @Y_1740_V
    public void n_1700_B(S_4035_N e) {
        if (this.u_2550_I && e.J_1907_R() == I_3747_h.c_3005_b.Y_259_p) {
            e.n_1700_B(true);
        }
    }

    @Generated
    public p_1977_n Q_4569_t() {
        return this.v_4262_N;
    }

    @Generated
    public I_686_h M_182_A() {
        return this.w_1484_f;
    }

    @Generated
    public p_1977_n t_1786_h() {
        return this.t_148_a;
    }

    @Generated
    public double N_4405_n() {
        return this.s_956_w;
    }

    @Generated
    public boolean w_1457_N() {
        return this.u_2550_I;
    }
}

