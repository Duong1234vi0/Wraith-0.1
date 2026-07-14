/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.E_390_U;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_1952_g;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.N_4006_T;
import lightning.product.MinecraftAccess;
import lightning.product.c_1608_O;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.q_3148_R;
import lombok.Generated;

public class H_1475_K
extends N_4006_T
implements MinecraftAccess {
    private final c_1608_O n_1700_B;
    private final Animation J_1907_R = new Animation(0.0f, 8.0f, Easing.t_148_a);
    private final Animation R_4764_Y = new Animation(0.0f, 8.0f, Easing.t_148_a);
    private final Animation G_564_y = new Animation(0.0f, 20.0f);
    private boolean P_1922_E = false;

    public H_1475_K(c_1608_O setting) {
        this.n_1700_B = setting;
        this.J_1907_R.J_1907_R((Boolean)setting.J_1907_R() != false ? 1.0f : 0.0f);
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        float parentVis;
        super.n_1700_B(stack, mouseX, mouseY, alpha);
        this.G_564_y(20.0f);
        this.R_4764_Y.n_1700_B(this.P_1922_E ? 1.0f : 0.0f);
        int bgColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.multiplayerClientSuggestionProvider), q_3148_R.n_1700_B(K_1200_E.t_1786_h), this.R_4764_Y.n_1700_B());
        float bgAlpha = (float)H_2506_c.G_564_y(bgColor) / 255.0f * alpha;
        int finalBg = H_2506_c.n_1700_B(bgColor, bgAlpha);
        F_489_x.n_1700_B(this.u_1723_Y(), this.v_4262_N(), 106.0f, 15.0f, 2.5f, finalBg);
        F_489_x.J_1907_R(this.u_1723_Y(), this.v_4262_N(), 106.0f, 15.0f, 3.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        this.G_564_y.n_1700_B(0.5f);
        H_1952_g h_1952_g = this.s_956_w();
        if (h_1952_g instanceof E_390_U) {
            E_390_U ab = (E_390_U)h_1952_g;
            parentVis = ab.w_1457_N().n_1700_B();
        } else {
            parentVis = alpha;
        }
        float half = 8.0f;
        float centerX = this.u_1723_Y() - 1.5f + half;
        float centerY = this.v_4262_N() - 1.0f + half;
        F_489_x.n_1700_B(centerX, centerY, this.G_564_y.n_1700_B() * parentVis);
        F_489_x.n_1700_B(this.n_1700_B.t_148_a(), centerX - half, centerY - half, 1.0f);
        F_489_x.R_4764_Y();
        l_3370_o.R_4764_Y[14].n_1700_B(stack, this.n_1700_B.n_1700_B(), this.u_1723_Y() + 13.0f, this.v_4262_N() + 6.0f, 80.0f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha), F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 13.0f, this.v_4262_N(), 80.0f, l_3370_o.R_4764_Y[14].h_1847_R() + 2.0f), this.M_588_G());
        F_489_x.J_1907_R(this.u_1723_Y() + 94.0f, this.v_4262_N() + 3.0f, 9.0f, 9.0f, 2.5f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        this.J_1907_R.n_1700_B((Boolean)this.n_1700_B.J_1907_R() != false ? 1.0f : 0.0f);
        float iconX = this.u_1723_Y() + 96.5f;
        float iconY = this.v_4262_N() + 5.5f;
        float scale = this.J_1907_R.n_1700_B() < 0.5f ? 1.0f - this.J_1907_R.n_1700_B() * 2.0f : (this.J_1907_R.n_1700_B() - 0.5f) * 2.0f;
        float activeAlpha = q_3148_R.J_1907_R(K_1200_E.s_956_w) / 255.0f * this.J_1907_R.n_1700_B() * alpha;
        float inactiveAlpha = q_3148_R.J_1907_R(K_1200_E.u_2550_I) / 255.0f * (1.0f - this.J_1907_R.n_1700_B()) * alpha;
        F_489_x.n_1700_B(iconX + 3.0f, iconY + 3.0f, scale);
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/gui/check.png"), iconX - 1.0f, iconY - 1.0f, 6.0f, 6.0f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.s_956_w), activeAlpha));
        F_489_x.n_1700_B(new g_2336_b("Pouch/icons/gui/xmark.png"), iconX, iconY, 4.0f, 4.0f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.u_2550_I), inactiveAlpha));
        F_489_x.R_4764_Y();
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        if (button == 0 && F_747_P.n_1700_B(mouseX, mouseY, this.u_1723_Y() + 96.0f, this.v_4262_N() + 3.0f, 9.0f, 9.0f)) {
            if (this.J_1907_R.G_564_y() && this.J_1907_R.n_1700_B() != this.J_1907_R.J_1907_R()) {
                return;
            }
            this.n_1700_B.n_1700_B(Boolean.valueOf((Boolean)this.n_1700_B.J_1907_R() == false));
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public void J_1907_R() {
        this.R_4764_Y.J_1907_R(this.P_1922_E ? 1.0f : 0.0f);
        this.J_1907_R.J_1907_R((Boolean)this.n_1700_B.J_1907_R() != false ? 1.0f : 0.0f);
        this.G_564_y.J_1907_R(0.5f);
    }

    @Generated
    public void n_1700_B(boolean selected) {
        this.P_1922_E = selected;
    }

    @Generated
    public c_1608_O R_4764_Y() {
        return this.n_1700_B;
    }

    @Generated
    public Animation G_564_y() {
        return this.J_1907_R;
    }

    @Generated
    public Animation P_4830_p() {
        return this.R_4764_Y;
    }

    @Generated
    public Animation h_1847_R() {
        return this.G_564_y;
    }

    @Generated
    public boolean Q_4569_t() {
        return this.P_1922_E;
    }
}



