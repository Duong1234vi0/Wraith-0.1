/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.g_221_o;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lombok.Generated;

public class h_973_D
extends N_4006_T {
    private final BooleanSetting n_1700_B;
    private final Animation J_1907_R = new Animation(0.0f, 10.0f, Easing.u_1723_Y);

    public h_973_D(BooleanSetting setting) {
        this.n_1700_B = setting;
        this.J_1907_R.J_1907_R(setting.t_148_a() != false ? 1.0f : 0.0f);
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        this.J_1907_R.n_1700_B(this.n_1700_B.t_148_a() != false ? 1.0f : 0.0f);
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        int baseText = q_3148_R.n_1700_B(K_1200_E.R_4764_Y);
        float textA = q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha;
        int labelColor = H_2506_c.n_1700_B(baseText, textA);
        l_3370_o.J_1907_R[12].n_1700_B(stack, this.n_1700_B.n_1700_B(), (double)(this.u_1723_Y() + 3.0f), (double)(this.v_4262_N() + 4.5f), labelColor);
        float progress = this.J_1907_R.n_1700_B();
        int currentBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), progress);
        float bgAlpha = (float)H_2506_c.G_564_y(currentBg) / 255.0f * alpha;
        int finalBg = H_2506_c.n_1700_B(currentBg, bgAlpha);
        int knobColorNoAlpha = H_2506_c.n_1700_B(H_2506_c.n_1700_B(255, 255, 255, 175), -1, progress);
        int knobColor = H_2506_c.n_1700_B(knobColorNoAlpha, alpha);
        F_489_x.n_1700_B(this.u_1723_Y() + this.w_1484_f() - 18.0f, this.v_4262_N() + 2.0f, 15.0f, 8.0f, 3.0f, finalBg);
        float offsetX = progress * 7.0f;
        F_489_x.n_1700_B(this.u_1723_Y() + this.w_1484_f() - 17.0f + offsetX, this.v_4262_N() + 3.0f, 6.0f, 6.0f, 2.0f, knobColor);
        this.G_564_y(Math.max(10.0f, l_3370_o.J_1907_R[12].h_1847_R()));
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        boolean inBounds;
        boolean bl = inBounds = mouseX >= this.u_1723_Y() + this.w_1484_f() - 18.0f && mouseX <= this.u_1723_Y() + this.w_1484_f() - 4.0f && mouseY >= this.v_4262_N() + 1.5f && mouseY <= this.v_4262_N() + 9.5f;
        if (inBounds) {
            if (button == 0) {
                this.n_1700_B.n_1700_B((Boolean)(this.n_1700_B.t_148_a() == false ? 1 : 0));
                this.J_1907_R.n_1700_B(this.n_1700_B.t_148_a() != false ? 1.0f : 0.0f);
            } else if (button == 2) {
                this.n_1700_B.n_1700_B((Boolean)this.n_1700_B.G_564_y);
                this.J_1907_R.n_1700_B(this.n_1700_B.G_564_y ? 1.0f : 0.0f);
            }
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.R_4764_Y();
    }

    @Override
    public void J_1907_R() {
        this.J_1907_R.J_1907_R(this.n_1700_B.t_148_a() != false ? 1.0f : 0.0f);
    }

    @Generated
    public BooleanSetting R_4764_Y() {
        return this.n_1700_B;
    }
}

