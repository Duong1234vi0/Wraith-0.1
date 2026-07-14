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
import lightning.product.V_537_k;
import lightning.product.g_221_o;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lombok.Generated;

public class p_3749_n
extends N_4006_T {
    private final BooleanSetting n_1700_B;
    private final Animation J_1907_R = new Animation(0.0f, 8.0f, Easing.t_148_a);
    private boolean R_4764_Y;

    public p_3749_n(BooleanSetting setting) {
        this.n_1700_B = setting;
        this.J_1907_R.J_1907_R(setting.t_148_a() != false ? 1.0f : 0.0f);
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        boolean locked = this.n_1700_B.w_1484_f();
        float effectiveAlpha = locked ? alpha * 0.55f : alpha;
        String displayName = this.u_2550_I() != null ? V_537_k.n_1700_B(this.u_2550_I().getClass().getSimpleName(), this.n_1700_B.n_1700_B()) : this.n_1700_B.n_1700_B();
        l_3370_o.R_4764_Y[14].n_1700_B(stack, displayName, this.u_1723_Y() + 6.0f, this.v_4262_N() + 2.0f, 80.0f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * effectiveAlpha), F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 6.0f, this.v_4262_N(), 80.0f, l_3370_o.R_4764_Y[14].h_1847_R() + 2.0f), this.M_588_G());
        this.G_564_y(15.0f);
        this.J_1907_R.n_1700_B(this.n_1700_B.t_148_a() != false ? 1.0f : 0.0f);
        float toggleY = this.v_4262_N() + this.t_148_a() - 10.0f;
        float progress = this.J_1907_R.n_1700_B();
        int currentBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), progress);
        float bgAlpha = (float)H_2506_c.G_564_y(currentBg) / 255.0f * effectiveAlpha;
        int finalBg = H_2506_c.n_1700_B(currentBg, bgAlpha);
        int knobColorNoAlpha = H_2506_c.n_1700_B(H_2506_c.n_1700_B(255, 255, 255, 175), -1, progress);
        int knobColor = H_2506_c.n_1700_B(knobColorNoAlpha, effectiveAlpha);
        float switchX = this.u_1723_Y() + this.w_1484_f() - 18.0f;
        float switchW = 15.0f;
        float switchH = 10.0f;
        float switchY = toggleY - 6.0f;
        F_489_x.n_1700_B(switchX, switchY, switchW, switchH, 4.0f, finalBg);
        F_489_x.J_1907_R(switchX, switchY, switchW, switchH, 4.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * effectiveAlpha);
        float offsetX = progress * 5.0f;
        float knobX = this.u_1723_Y() + this.w_1484_f() - 17.0f + offsetX;
        float knobY = toggleY - 5.5f;
        F_489_x.n_1700_B(knobX, knobY, 9.0f, 9.0f, 4.0f, knobColor);
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        if (this.n_1700_B.w_1484_f()) {
            return;
        }
        if (button == 0 && F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + this.w_1484_f() - 18.0f, this.v_4262_N() + (this.t_148_a() - 10.0f) - 6.5f, 15.0f, 11.0f)) {
            if (this.J_1907_R.G_564_y() && this.J_1907_R.n_1700_B() != this.J_1907_R.J_1907_R()) {
                return;
            }
            this.n_1700_B.n_1700_B((Boolean)(this.n_1700_B.t_148_a() == false ? 1 : 0));
        }
        if (button == 2) {
            if (this.J_1907_R.G_564_y() && this.J_1907_R.n_1700_B() != this.J_1907_R.J_1907_R()) {
                return;
            }
            this.n_1700_B.n_1700_B((Boolean)this.n_1700_B.G_564_y);
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public void n_1700_B(int keyCode, int scanCode, int modifiers) {
        super.n_1700_B(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.R_4764_Y();
    }

    @Generated
    public BooleanSetting R_4764_Y() {
        return this.n_1700_B;
    }

    @Generated
    public Animation G_564_y() {
        return this.J_1907_R;
    }

    @Generated
    public boolean P_4830_p() {
        return this.R_4764_Y;
    }

    @Generated
    public void n_1700_B(boolean bind) {
        this.R_4764_Y = bind;
    }
}

