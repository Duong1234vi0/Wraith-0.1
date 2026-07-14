/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.HashMap;
import java.util.Map;
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
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;

public class c_1732_c
extends N_4006_T {
    private final ModeSetting n_1700_B;
    private final Map<String, Animation> J_1907_R;
    private final Map<String, Animation> R_4764_Y;

    public c_1732_c(ModeSetting setting) {
        this.n_1700_B = setting;
        this.J_1907_R = new HashMap<String, Animation>();
        this.R_4764_Y = new HashMap<String, Animation>();
        for (String mode : setting.G_564_y) {
            this.J_1907_R.put(mode, new Animation(mode.equals(setting.J_1907_R()) ? 1.0f : 0.0f, 8.0f, Easing.t_148_a));
            this.R_4764_Y.put(mode, new Animation(0.0f, 10.0f, Easing.u_1723_Y));
        }
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        float offsetX = 0.0f;
        float offsetY = 0.0f;
        float totalHeight = 0.0f;
        String headerText = this.u_2550_I() != null ? V_537_k.n_1700_B(this.u_2550_I().getClass().getSimpleName(), this.n_1700_B.n_1700_B()) : this.n_1700_B.n_1700_B();
        l_3370_o.R_4764_Y[14].n_1700_B(stack, headerText, (double)(this.u_1723_Y() + 5.0f), (double)this.v_4262_N(), H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha));
        offsetY = 8.0f;
        for (String option : this.n_1700_B.G_564_y) {
            String text = this.u_2550_I() != null ? V_537_k.n_1700_B(this.u_2550_I().getClass().getSimpleName(), this.n_1700_B.n_1700_B(), option) : option;
            float boxWidth = l_3370_o.R_4764_Y[11].n_1700_B(text) + 6.0f;
            float boxHeight = l_3370_o.R_4764_Y[11].h_1847_R() + 6.75f;
            if (offsetX + boxWidth >= this.w_1484_f() - 10.0f) {
                offsetX = 0.0f;
                offsetY += boxHeight + 1.0f;
            }
            Animation selectAnim = this.J_1907_R.computeIfAbsent(option, k -> new Animation(option.equals(this.n_1700_B.J_1907_R()) ? 1.0f : 0.0f, 8.0f, Easing.t_148_a));
            selectAnim.n_1700_B(option.equals(this.n_1700_B.J_1907_R()) ? 1.0f : 0.0f);
            float selectValue = selectAnim.n_1700_B();
            boolean isHovered = F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 5.0f + offsetX, this.v_4262_N() + offsetY, boxWidth, boxHeight);
            Animation hoverAnim = this.R_4764_Y.computeIfAbsent(option, k -> new Animation(0.0f, 10.0f, Easing.u_1723_Y));
            hoverAnim.n_1700_B(isHovered ? 1.0f : 0.0f);
            float hoverValue = hoverAnim.n_1700_B();
            int inactiveBgColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.multiplayerClientSuggestionProvider), 0.02f * alpha);
            int inactiveTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.G_564_y), 0.48f * alpha);
            int hoveredBgColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.s_956_w), 0.5f * alpha);
            int hoveredTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.C_2741_M), alpha);
            int activeBgColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.s_956_w), alpha);
            int activeTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.C_2741_M), alpha);
            int baseWithHoverBg = H_2506_c.n_1700_B(inactiveBgColor, hoveredBgColor, hoverValue * (1.0f - selectValue));
            int baseWithHoverText = H_2506_c.n_1700_B(inactiveTextColor, hoveredTextColor, hoverValue * (1.0f - selectValue));
            int bgColor = H_2506_c.n_1700_B(baseWithHoverBg, activeBgColor, selectValue);
            int textColor = H_2506_c.n_1700_B(baseWithHoverText, activeTextColor, selectValue);
            F_489_x.n_1700_B(this.u_1723_Y() + 5.0f + offsetX, this.v_4262_N() + offsetY, boxWidth, boxHeight, 2.0f, bgColor);
            float outlineAlpha = q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha * (1.0f - selectValue);
            F_489_x.J_1907_R(this.u_1723_Y() + 5.0f + offsetX, this.v_4262_N() + offsetY, boxWidth, boxHeight, 2.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), outlineAlpha);
            float rectCenterX = this.u_1723_Y() + 5.0f + offsetX + boxWidth / 2.0f;
            float rectCenterY = this.v_4262_N() + offsetY + boxHeight / 2.0f;
            float textX = rectCenterX - l_3370_o.R_4764_Y[11].n_1700_B(text) / 2.0f;
            float textY = rectCenterY - l_3370_o.R_4764_Y[11].h_1847_R() / 2.0f + 1.0f;
            l_3370_o.R_4764_Y[11].n_1700_B(stack, text, (double)textX, (double)textY, textColor);
            offsetX += boxWidth + 1.0f;
            totalHeight = Math.max(totalHeight, offsetY + boxHeight);
        }
        this.G_564_y(totalHeight + 6.0f);
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        if (button == 0) {
            float offsetX = 0.0f;
            float offsetY = 8.0f;
            for (String option : this.n_1700_B.G_564_y) {
                String text = this.u_2550_I() != null ? V_537_k.n_1700_B(this.u_2550_I().getClass().getSimpleName(), this.n_1700_B.n_1700_B(), option) : option;
                float boxWidth = l_3370_o.R_4764_Y[11].n_1700_B(text) + 6.0f;
                float boxHeight = l_3370_o.R_4764_Y[11].h_1847_R() + 6.75f;
                if (offsetX + boxWidth >= this.w_1484_f() - 10.0f) {
                    offsetX = 0.0f;
                    offsetY += boxHeight + 1.0f;
                }
                if (F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 5.0f + offsetX, this.v_4262_N() + offsetY, boxWidth, boxHeight)) {
                    this.n_1700_B.n_1700_B(option);
                    break;
                }
                offsetX += boxWidth + 1.0f;
            }
        } else if (button == 2 && F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y(), this.v_4262_N(), this.w_1484_f(), this.t_148_a())) {
            this.n_1700_B.n_1700_B(this.n_1700_B.P_1922_E);
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.R_4764_Y();
    }

    @Override
    public void J_1907_R() {
        for (String option : this.n_1700_B.G_564_y) {
            Animation hoverAnim;
            Animation anim = this.J_1907_R.get(option);
            if (anim != null) {
                anim.J_1907_R(option.equals(this.n_1700_B.J_1907_R()) ? 1.0f : 0.0f);
            }
            if ((hoverAnim = this.R_4764_Y.get(option)) == null) continue;
            hoverAnim.J_1907_R(0.0f);
        }
    }
}


