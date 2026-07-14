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
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.V_537_k;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lightning.product.AttackAura;
import lightning.product.u_530_F;
import lombok.Generated;

public class h_3858_e
extends N_4006_T {
    private final NumberSetting n_1700_B;
    private boolean J_1907_R;
    private float R_4764_Y;
    private int G_564_y = -1;
    private int P_1922_E = -1;

    public h_3858_e(NumberSetting setting) {
        this.n_1700_B = setting;
    }

    @Override
    public void J_1907_R() {
        this.R_4764_Y = (this.w_1484_f() - 17.0f) * (((Float)this.n_1700_B.J_1907_R()).floatValue() - this.n_1700_B.G_564_y) / (this.n_1700_B.P_1922_E - this.n_1700_B.G_564_y);
        this.G_564_y = -1;
        this.P_1922_E = -1;
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        this.G_564_y(24.0f);
        String formattedValue = this.n_1700_B.w_1484_f();
        float valueWidth = l_3370_o.R_4764_Y[12].n_1700_B(formattedValue);
        float valueX = this.u_1723_Y() + this.w_1484_f() - valueWidth - 6.0f;
        float textWidth = this.w_1484_f() - valueWidth - 15.5f;
        String displayName = this.u_2550_I() != null ? V_537_k.n_1700_B(this.u_2550_I().getClass().getSimpleName(), this.n_1700_B.n_1700_B()) : this.n_1700_B.n_1700_B();
        l_3370_o.R_4764_Y[14].n_1700_B(stack, displayName, this.u_1723_Y() + 6.0f, this.v_4262_N() + 2.0f, textWidth, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha), F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 6.0f, this.v_4262_N(), textWidth, l_3370_o.R_4764_Y[14].h_1847_R() + 2.0f), this.M_588_G());
        float targetWidth = (this.w_1484_f() - 17.0f) * (((Float)this.n_1700_B.J_1907_R()).floatValue() - this.n_1700_B.G_564_y) / (this.n_1700_B.P_1922_E - this.n_1700_B.G_564_y);
        this.R_4764_Y = F_747_P.G_564_y(this.R_4764_Y, targetWidth, 20.0f);
        F_489_x.n_1700_B(valueX - 3.0f, this.v_4262_N() - 1.0f, valueWidth + 3.5f, 10.0f, 2.0f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.t_148_a), q_3148_R.J_1907_R(K_1200_E.t_148_a) / 255.0f * alpha));
        F_489_x.J_1907_R(valueX - 3.0f, this.v_4262_N() - 1.0f, valueWidth + 3.5f, 10.0f, 2.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        float rectCenterX = valueX + valueWidth / 2.0f - 1.0f;
        float rectCenterY = this.v_4262_N() + 4.5f;
        float textX = rectCenterX - l_3370_o.R_4764_Y[12].n_1700_B(formattedValue) / 2.0f;
        float textY = rectCenterY - l_3370_o.R_4764_Y[12].h_1847_R() / 2.0f + 0.5f;
        l_3370_o.R_4764_Y[12].n_1700_B(stack, formattedValue, (double)textX, (double)textY, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha));
        F_489_x.J_1907_R(this.u_1723_Y() + 5.0f, this.v_4262_N() + 12.0f, this.w_1484_f() - 10.5f, 4.0f, 1.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        int sliderTargetColor = this.R_4764_Y(q_3148_R.n_1700_B(K_1200_E.v_4262_N));
        int sliderCircleTargetColor = this.R_4764_Y(q_3148_R.n_1700_B(K_1200_E.w_1484_f));
        this.G_564_y = this.n_1700_B(this.G_564_y, sliderTargetColor);
        this.P_1922_E = this.n_1700_B(this.P_1922_E, sliderCircleTargetColor);
        float sliderAlpha = q_3148_R.J_1907_R(K_1200_E.v_4262_N) / 255.0f * alpha;
        float sliderCircleAlpha = q_3148_R.J_1907_R(K_1200_E.w_1484_f) / 255.0f * alpha;
        F_489_x.n_1700_B(this.u_1723_Y() + 5.0f, this.v_4262_N() + 12.0f, this.R_4764_Y + 2.0f, 4.0f, 0.6f, H_2506_c.n_1700_B(this.G_564_y, sliderAlpha));
        F_489_x.n_1700_B(this.u_1723_Y() + 4.5f + this.R_4764_Y, this.v_4262_N() + 10.0f, 8.0f, 8.0f, 3.0f, H_2506_c.n_1700_B(this.P_1922_E, sliderCircleAlpha));
        if (this.J_1907_R) {
            this.n_1700_B.n_1700_B(Float.valueOf(F_747_P.R_4764_Y(u_530_F.n_1700_B((mouseX - this.u_1723_Y() - 7.0f) / (this.w_1484_f() - 17.0f) * (this.n_1700_B.P_1922_E - this.n_1700_B.G_564_y) + this.n_1700_B.G_564_y, this.n_1700_B.G_564_y, this.n_1700_B.P_1922_E), this.n_1700_B.u_1723_Y)));
        }
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        if (button == 0 && F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 5.0f, this.v_4262_N() + 10.0f, this.w_1484_f() - 10.5f, 6.0f)) {
            this.J_1907_R = true;
            this.n_1700_B.n_1700_B(Float.valueOf(F_747_P.R_4764_Y(u_530_F.n_1700_B((mouseX - this.u_1723_Y() - 7.0f) / (this.w_1484_f() - 17.0f) * (this.n_1700_B.P_1922_E - this.n_1700_B.G_564_y) + this.n_1700_B.G_564_y, this.n_1700_B.G_564_y, this.n_1700_B.P_1922_E), this.n_1700_B.u_1723_Y)));
        } else if (button == 2 && F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y(), this.v_4262_N(), this.w_1484_f(), this.t_148_a())) {
            this.n_1700_B.n_1700_B(Float.valueOf(this.n_1700_B.v_4262_N));
        }
    }

    @Override
    public void n_1700_B(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.J_1907_R = false;
        }
    }

    @Override
    public void J_1907_R(float mouseX, float mouseY, int button) {
        if (button == 0) {
            if (this.J_1907_R) {
                this.n_1700_B.n_1700_B(Float.valueOf(F_747_P.R_4764_Y(u_530_F.n_1700_B((mouseX - this.u_1723_Y() - 7.0f) / (this.w_1484_f() - 17.0f) * (this.n_1700_B.P_1922_E - this.n_1700_B.G_564_y) + this.n_1700_B.G_564_y, this.n_1700_B.G_564_y, this.n_1700_B.P_1922_E), this.n_1700_B.u_1723_Y)));
            }
            this.J_1907_R = false;
        }
    }

    @Override
    public boolean n_1700_B(double mouseX, double mouseY, double delta) {
        if (k_2603_m.hasControlDown() && this.n_1700_B((float)mouseX, (float)mouseY)) {
            float step = this.n_1700_B.u_1723_Y;
            float rawValue = ((Float)this.n_1700_B.J_1907_R()).floatValue() + ((float)delta > 0.0f ? step : -step);
            float roundedValue = F_747_P.R_4764_Y(rawValue, step);
            float clampedValue = u_530_F.n_1700_B(roundedValue, this.n_1700_B.G_564_y, this.n_1700_B.P_1922_E);
            this.n_1700_B.n_1700_B(Float.valueOf(clampedValue));
            return true;
        }
        return false;
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.R_4764_Y();
    }

    private int R_4764_Y(int fallback) {
        if (!(this.u_2550_I() instanceof AttackAura)) {
            return fallback;
        }
        if (!"\u0420\u0430\u0434\u0438\u0443\u0441 \u0430\u0442\u0430\u043a\u0438".equals(this.n_1700_B.n_1700_B())) {
            return fallback;
        }
        AttackAura aura = (AttackAura)this.u_2550_I();
        if (aura.Q_4569_t().J_1907_R("Grim")) {
            return fallback;
        }
        float value = ((Float)this.n_1700_B.J_1907_R()).floatValue();
        if (value >= 3.1f && value <= 3.2f) {
            return H_2506_c.n_1700_B(255, 220, 60);
        }
        if (value > 3.2f) {
            return H_2506_c.n_1700_B(255, 64, 64);
        }
        return fallback;
    }

    private int n_1700_B(int currentColor, int targetColor) {
        if (currentColor == -1) {
            return targetColor;
        }
        return H_2506_c.n_1700_B(currentColor, targetColor, 0.2f);
    }

    @Generated
    public void n_1700_B(boolean drag) {
        this.J_1907_R = drag;
    }

    @Generated
    public void P_1922_E(float anim) {
        this.R_4764_Y = anim;
    }

    @Generated
    public void n_1700_B(int animatedSliderColor) {
        this.G_564_y = animatedSliderColor;
    }

    @Generated
    public void J_1907_R(int animatedSliderCircleColor) {
        this.P_1922_E = animatedSliderCircleColor;
    }
}


