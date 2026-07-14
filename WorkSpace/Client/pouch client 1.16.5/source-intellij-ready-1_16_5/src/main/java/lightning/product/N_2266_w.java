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
import lightning.product.j_1654_T;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lightning.product.KeyBindSetting;
import lombok.Generated;

public class N_2266_w
extends N_4006_T {
    final KeyBindSetting n_1700_B;
    public boolean J_1907_R;

    public N_2266_w(KeyBindSetting setting) {
        this.n_1700_B = setting;
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        String bind = this.J_1907_R ? "..." : ((Integer)this.n_1700_B.J_1907_R() != -1 ? j_1654_T.n_1700_B((Integer)this.n_1700_B.J_1907_R()) : "None");
        float boxWidth = l_3370_o.R_4764_Y[13].n_1700_B(bind) + 5.0f;
        float boxHeight = 11.0f;
        float textWidth = Math.max(20.0f, this.w_1484_f() - boxWidth - 11.0f);
        String displayName = this.u_2550_I() != null ? V_537_k.n_1700_B(this.u_2550_I().getClass().getSimpleName(), this.n_1700_B.n_1700_B()) : this.n_1700_B.n_1700_B();
        l_3370_o.R_4764_Y[14].n_1700_B(stack, displayName, this.u_1723_Y() + 6.0f, this.v_4262_N() + 2.0f, textWidth, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha), F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 6.0f, this.v_4262_N(), textWidth, l_3370_o.R_4764_Y[14].h_1847_R() + 2.0f), this.M_588_G());
        float x = this.u_1723_Y() + this.w_1484_f() - boxWidth - 5.0f;
        float y = this.v_4262_N() + this.t_148_a() - boxHeight - 2.0f;
        F_489_x.J_1907_R(x, y - 4.5f, boxWidth, boxHeight, 3.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        float rectCenterX = x + boxWidth / 2.0f;
        float rectCenterY = y - 4.0f + boxHeight / 2.0f;
        float textX = rectCenterX - l_3370_o.R_4764_Y[13].n_1700_B(bind) / 2.0f;
        int textY = (int)(rectCenterY - l_3370_o.R_4764_Y[13].h_1847_R() / 2.0f + 1.0f);
        l_3370_o.R_4764_Y[13].n_1700_B(stack, bind, (double)textX, (double)textY, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha));
        this.G_564_y(15.0f);
    }

    @Override
    public void n_1700_B(int keyCode, int scanCode, int modifiers) {
        if (this.J_1907_R) {
            if (keyCode == 261 || keyCode == 256) {
                this.n_1700_B.n_1700_B(-1);
                this.J_1907_R = false;
                return;
            }
            this.n_1700_B.n_1700_B(keyCode);
            this.J_1907_R = false;
        }
        super.n_1700_B(keyCode, scanCode, modifiers);
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        float boxWidth = l_3370_o.R_4764_Y[13].n_1700_B((Integer)this.n_1700_B.J_1907_R() != -1 ? j_1654_T.n_1700_B((Integer)this.n_1700_B.J_1907_R()) : "None") + 5.0f;
        float boxHeight = 11.0f;
        float boxX = this.u_1723_Y() + this.w_1484_f() - boxWidth - 5.0f;
        float boxY = this.v_4262_N() + this.t_148_a() - boxHeight - 6.0f;
        if (this.J_1907_R) {
            if (button == 0 || button == 1) {
                return;
            }
            if (button >= 1) {
                this.n_1700_B.n_1700_B(button);
                this.J_1907_R = false;
                return;
            }
        }
        if (F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y(), this.v_4262_N(), this.w_1484_f(), this.t_148_a())) {
            if (button == 0) {
                if (mouseX >= boxX && mouseX <= boxX + boxWidth && mouseY >= boxY && mouseY <= boxY + boxHeight) {
                    this.J_1907_R = true;
                }
            } else if (button == 2) {
                this.n_1700_B.n_1700_B(-1);
                this.J_1907_R = false;
            }
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.R_4764_Y();
    }

    @Generated
    public void n_1700_B(boolean activated) {
        this.J_1907_R = activated;
    }
}

