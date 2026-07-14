/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.HashMap;
import java.util.Map;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.g_221_o;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lombok.Generated;

public class P_2295_B
extends N_4006_T {
    private final ModeSetting n_1700_B;
    private final Map<String, Animation> J_1907_R;

    public P_2295_B(ModeSetting setting) {
        this.n_1700_B = setting;
        this.J_1907_R = new HashMap<String, Animation>();
        for (String mode : setting.G_564_y) {
            this.J_1907_R.put(mode, new Animation(mode.equals(setting.J_1907_R()) ? 1.0f : 0.0f, 10.0f, Easing.u_1723_Y));
        }
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        float offsetX = 0.0f;
        float offsetY = 0.0f;
        float totalHeight = 0.0f;
        int nameColorBase = q_3148_R.n_1700_B(K_1200_E.R_4764_Y);
        int nameColor = H_2506_c.n_1700_B(nameColorBase, q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha);
        l_3370_o.J_1907_R[12].n_1700_B(stack, this.n_1700_B.n_1700_B(), (double)(this.u_1723_Y() + 3.0f), (double)(this.v_4262_N() + 5.0f), nameColor);
        for (String text : this.n_1700_B.G_564_y) {
            float boxWidth = l_3370_o.R_4764_Y[12].n_1700_B(text) + 4.0f;
            float boxHeight = l_3370_o.R_4764_Y[12].h_1847_R() + 6.0f;
            if (offsetX + boxWidth >= this.w_1484_f() + 20.0f) {
                offsetX = 0.0f;
                offsetY += boxHeight + 1.0f;
            }
            Animation animation = this.J_1907_R.get(text);
            animation.n_1700_B(text.equals(this.n_1700_B.J_1907_R()) ? 1.0f : 0.0f);
            float progress = animation.n_1700_B();
            int currentBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_4830_p), q_3148_R.n_1700_B(K_1200_E.M_588_G), progress);
            int currentText = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), q_3148_R.n_1700_B(K_1200_E.R_4764_Y), progress);
            float bgAlpha = (float)H_2506_c.G_564_y(currentBg) / 255.0f * alpha;
            int finalBg = H_2506_c.n_1700_B(currentBg, bgAlpha);
            F_489_x.n_1700_B(this.u_1723_Y() + offsetX + 2.0f, this.v_4262_N() + 11.0f + offsetY, boxWidth, boxHeight - 0.5f, 1.5f, finalBg);
            float rectCenterX = this.u_1723_Y() + offsetX + boxWidth / 2.0f;
            float rectCenterY = this.v_4262_N() + 11.0f + offsetY + boxHeight / 2.0f + 0.5f;
            float textX = rectCenterX - l_3370_o.R_4764_Y[12].n_1700_B(text) / 2.0f;
            float textY = rectCenterY - l_3370_o.R_4764_Y[12].h_1847_R() / 2.0f + 0.5f;
            float txtAlpha = (float)H_2506_c.G_564_y(currentText) / 255.0f * alpha;
            int finalText = H_2506_c.n_1700_B(currentText, txtAlpha);
            l_3370_o.R_4764_Y[12].n_1700_B(stack, text, (double)(textX + 2.0f), (double)textY, finalText);
            offsetX += boxWidth + 1.0f;
            totalHeight = Math.max(totalHeight, offsetY + boxHeight);
        }
        this.G_564_y(totalHeight + l_3370_o.J_1907_R[12].h_1847_R() + 7.0f);
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        boolean changed = false;
        if (button == 0) {
            float offsetX = 0.0f;
            float offsetY = 0.0f;
            for (String text : this.n_1700_B.G_564_y) {
                float boxWidth = l_3370_o.R_4764_Y[12].n_1700_B(text) + 4.0f;
                float boxHeight = l_3370_o.R_4764_Y[12].h_1847_R() + 6.0f;
                if (offsetX + boxWidth >= this.w_1484_f() + 20.0f) {
                    offsetX = 0.0f;
                    offsetY += boxHeight + 1.0f;
                }
                if (F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + offsetX + 1.0f, this.v_4262_N() + 9.0f + offsetY, boxWidth + 1.0f, boxHeight + 2.0f)) {
                    this.n_1700_B.n_1700_B(text);
                    changed = true;
                    break;
                }
                offsetX += boxWidth + 1.0f;
            }
        } else if (button == 2 && F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y(), this.v_4262_N() - 1.0f, this.w_1484_f(), this.t_148_a())) {
            this.n_1700_B.n_1700_B(this.n_1700_B.P_1922_E);
            changed = true;
        }
        if (changed) {
            this.J_1907_R.forEach((mode, anim) -> anim.n_1700_B(mode.equals(this.n_1700_B.J_1907_R()) ? 1.0f : 0.0f));
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.R_4764_Y();
    }

    @Override
    public void J_1907_R() {
        this.J_1907_R.forEach((mode, anim) -> anim.J_1907_R(mode.equals(this.n_1700_B.J_1907_R()) ? 1.0f : 0.0f));
    }

    @Generated
    public ModeSetting R_4764_Y() {
        return this.n_1700_B;
    }
}

