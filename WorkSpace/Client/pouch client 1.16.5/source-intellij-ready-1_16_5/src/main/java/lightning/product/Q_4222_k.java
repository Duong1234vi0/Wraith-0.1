/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_1491_c;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.V_537_k;
import lightning.product.g_221_o;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;

public class Q_4222_k
extends N_4006_T {
    private final H_1491_c n_1700_B;
    private float J_1907_R;
    private float R_4764_Y;
    private final Animation G_564_y = new Animation(0.0f, 10.0f, Easing.u_1723_Y);

    public Q_4222_k(H_1491_c setting) {
        this.n_1700_B = setting;
        this.G_564_y(19.0f);
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        this.J_1907_R = 94.0f;
        this.R_4764_Y = 13.0f;
        float toggleX = this.u_1723_Y() + 4.0f;
        float toggleY = this.v_4262_N() - 2.0f;
        boolean isHovered = F_747_P.n_1700_B(mouseX, mouseY, toggleX, toggleY, this.J_1907_R, this.R_4764_Y);
        this.G_564_y.n_1700_B(isHovered ? 1.0f : 0.0f);
        int interpolatedBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), this.G_564_y.n_1700_B());
        float bgAlpha = (float)H_2506_c.G_564_y(interpolatedBg) / 255.0f * alpha;
        int finalBg = H_2506_c.n_1700_B(interpolatedBg, bgAlpha);
        F_489_x.n_1700_B(toggleX, toggleY, this.J_1907_R, this.R_4764_Y, 1.5f, finalBg);
        F_489_x.J_1907_R(toggleX, toggleY, this.J_1907_R, this.R_4764_Y, 2.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        String displayText = this.u_2550_I() != null ? V_537_k.n_1700_B(this.u_2550_I().getClass().getSimpleName(), this.n_1700_B.n_1700_B()) : this.n_1700_B.n_1700_B();
        float textWidth = l_3370_o.R_4764_Y[12].n_1700_B(displayText);
        float rectCenterX = toggleX + this.J_1907_R / 2.0f;
        int textX = (int)(rectCenterX - textWidth / 2.0f) + 1;
        float textY = this.v_4262_N() + (this.t_148_a() - 15.5f);
        int interpolatedText = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), this.G_564_y.n_1700_B());
        float txtAlpha = (float)H_2506_c.G_564_y(interpolatedText) / 255.0f * alpha;
        int finalText = H_2506_c.n_1700_B(interpolatedText, txtAlpha);
        l_3370_o.R_4764_Y[12].n_1700_B(stack, displayText, (double)textX, (double)textY, finalText);
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        if (F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 3.0f, this.v_4262_N() - 4.0f, this.J_1907_R + 1.0f, this.R_4764_Y + 2.0f) && button == 0) {
            this.n_1700_B.w_1484_f();
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.R_4764_Y();
    }
}

