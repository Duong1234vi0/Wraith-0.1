/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.R_3213_X;
import lightning.product.V_537_k;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lombok.Generated;

public class f_887_Z
extends N_4006_T {
    private final h_2367_h n_1700_B;
    private final R_3213_X J_1907_R;

    public f_887_Z(h_2367_h setting) {
        this.n_1700_B = setting;
        this.J_1907_R = new R_3213_X(setting);
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        String displayName = this.u_2550_I() != null ? V_537_k.n_1700_B(this.u_2550_I().getClass().getSimpleName(), this.n_1700_B.n_1700_B()) : this.n_1700_B.n_1700_B();
        l_3370_o.R_4764_Y[14].n_1700_B(stack, displayName, this.u_1723_Y() + 5.0f, this.v_4262_N() + 2.0f, 80.0f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha), F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 5.0f, this.v_4262_N(), 80.0f, l_3370_o.R_4764_Y[14].h_1847_R() + 2.0f), this.M_588_G());
        F_489_x.n_1700_B(this.u_1723_Y() + this.w_1484_f() - 11.0f, this.v_4262_N(), 8.0f, 8.0f, 3.0f, H_2506_c.n_1700_B((int)((Integer)this.n_1700_B.J_1907_R()), this.n_1700_B.w_1484_f() * alpha / 255.0f));
        F_489_x.J_1907_R(this.u_1723_Y() + this.w_1484_f() - 11.0f, this.v_4262_N(), 8.0f, 8.0f, 3.5f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        this.G_564_y(15.0f);
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.R_4764_Y();
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        if (button == 0 && F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y(), this.v_4262_N() - 1.0f, this.w_1484_f(), this.t_148_a() - 7.0f)) {
            this.J_1907_R.n_1700_B(!this.J_1907_R.G_564_y());
        } else if (button == 2 && F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y(), this.v_4262_N(), this.w_1484_f(), this.t_148_a())) {
            this.n_1700_B.n_1700_B(this.n_1700_B.G_564_y);
            this.J_1907_R.J_1907_R();
        } else if (this.J_1907_R.G_564_y()) {
            this.J_1907_R.n_1700_B(mouseX, mouseY);
        }
    }

    @Override
    public void J_1907_R(float mouseX, float mouseY, int button) {
        if (this.J_1907_R.G_564_y()) {
            this.J_1907_R.n_1700_B();
        }
    }

    @Generated
    public h_2367_h R_4764_Y() {
        return this.n_1700_B;
    }

    @Generated
    public R_3213_X G_564_y() {
        return this.J_1907_R;
    }
}

