/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.g_221_o;
import lightning.product.SoundEventRegistration;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;

public class P_3676_m
extends N_4006_T {
    private final SoundEventRegistration n_1700_B;
    private final String J_1907_R;
    private final String R_4764_Y;
    private float G_564_y;
    private float P_1922_E;
    private final Animation u_1723_Y = new Animation(0.0f, 10.0f, Easing.u_1723_Y);

    public P_3676_m(SoundEventRegistration setting, String textOff, String textOn) {
        this.n_1700_B = setting;
        this.J_1907_R = textOn;
        this.R_4764_Y = textOff;
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        this.G_564_y = this.w_1484_f() - 8.0f;
        this.P_1922_E = 13.0f;
        float toggleX = this.u_1723_Y() + 4.0f;
        float toggleY = this.v_4262_N() - 2.0f;
        boolean isHovered = F_747_P.n_1700_B(mouseX, mouseY, toggleX, toggleY, this.G_564_y, this.P_1922_E);
        this.u_1723_Y.n_1700_B(isHovered ? 1.0f : 0.0f);
        int interpolatedBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), this.u_1723_Y.n_1700_B());
        float bgAlpha = (float)H_2506_c.G_564_y(interpolatedBg) / 255.0f * alpha;
        int finalBg = H_2506_c.n_1700_B(interpolatedBg, bgAlpha);
        F_489_x.n_1700_B(toggleX, toggleY, this.G_564_y, this.P_1922_E, 1.5f, finalBg);
        F_489_x.J_1907_R(toggleX, toggleY, this.G_564_y, this.P_1922_E, 2.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        String displayText = (Boolean)this.n_1700_B.J_1907_R() != false ? this.J_1907_R : this.R_4764_Y;
        float textWidth = l_3370_o.R_4764_Y[12].n_1700_B(displayText);
        float textX = this.u_1723_Y() + this.w_1484_f() / 2.0f - textWidth / 2.0f;
        float textY = this.v_4262_N() + (this.t_148_a() - 15.5f);
        int interpolatedText = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), this.u_1723_Y.n_1700_B());
        float txtAlpha = (float)H_2506_c.G_564_y(interpolatedText) / 255.0f * alpha;
        int finalText = H_2506_c.n_1700_B(interpolatedText, txtAlpha);
        l_3370_o.R_4764_Y[12].n_1700_B(stack, displayText, (double)textX, (double)textY, finalText);
        this.G_564_y(19.0f);
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        if (F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 3.0f, this.v_4262_N() - 4.0f, this.G_564_y + 1.0f, this.P_1922_E + 2.0f)) {
            if (button == 0) {
                this.n_1700_B.n_1700_B(Boolean.valueOf((Boolean)this.n_1700_B.J_1907_R() == false));
            } else if (button == 2) {
                this.n_1700_B.n_1700_B(Boolean.valueOf(this.n_1700_B.G_564_y));
            }
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.R_4764_Y();
    }
}


