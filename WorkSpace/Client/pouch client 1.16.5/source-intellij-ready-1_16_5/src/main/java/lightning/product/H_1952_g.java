/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.K_1200_E;
import lightning.product.Module;
import lightning.product.Z_2491_A;
import lightning.product.g_221_o;
import lightning.product.i_4833_u;
import lightning.product.Animation;
import lightning.product.k_1608_N;
import lightning.product.l_3370_o;
import lightning.product.ClientBootstrap;
import lightning.product.p_1458_L;
import lightning.product.q_3148_R;
import lightning.product.s_3815_K;
import lightning.product.u_4724_w;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;
import lombok.Generated;

public class H_1952_g
implements k_1608_N {
    private ModuleCategory u_1723_Y;
    private String v_4262_N;
    protected float n_1700_B;
    protected float J_1907_R;
    float R_4764_Y = 0.0f;
    protected float G_564_y = 112.0f;
    protected float P_1922_E = 275.0f;
    private List<s_3815_K> w_1484_f = new ArrayList<s_3815_K>();
    private final List<s_3815_K> t_148_a = new ArrayList<s_3815_K>();
    private Animation s_956_w = new Animation(0.0f, 10.0f);
    private float u_2550_I;

    public H_1952_g() {
    }

    private void J_1907_R(ModuleCategory category) {
        this.u_1723_Y = category;
        this.n_1700_B();
    }

    public void n_1700_B() {
        this.t_148_a.clear();
        for (Module module : ClientBootstrap.Y_601_j().J_1907_R().u_1723_Y()) {
            boolean alreadyAdded;
            if (module.u_1723_Y() != this.u_1723_Y || (alreadyAdded = this.t_148_a.stream().anyMatch(element -> element.u_2550_I() == module))) continue;
            s_3815_K component = new s_3815_K(module);
            component.n_1700_B(this);
            this.t_148_a.add(component);
        }
    }

    public H_1952_g(ModuleCategory category) {
        this.J_1907_R(category);
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        this.s_956_w.n_1700_B(this.u_2550_I);
        float animatedScroll = this.s_956_w.n_1700_B();
        int currentBg = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.k_2293_S), q_3148_R.n_1700_B(K_1200_E.k_2293_S), q_3148_R.J_1907_R(K_1200_E.k_2293_S));
        float bgAlpha = (float)H_2506_c.G_564_y(currentBg) / 255.0f * alpha;
        int finalBg = H_2506_c.n_1700_B(currentBg, bgAlpha);
        F_489_x.n_1700_B(this.n_1700_B - 10.0f, this.J_1907_R - 10.0f, this.G_564_y + 20.0f, this.P_1922_E + 20.0f, 9.0f, q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.n_1700_B(K_1200_E.q_2307_F), q_3148_R.J_1907_R(K_1200_E.q_2307_F) / 255.0f * alpha, 10.0f);
        F_489_x.n_1700_B(this.n_1700_B, this.J_1907_R, this.G_564_y, this.P_1922_E, 9.0f, q_3148_R.n_1700_B(K_1200_E.n_1700_B), alpha);
        F_489_x.n_1700_B(this.n_1700_B, this.J_1907_R, this.G_564_y, 28.0f, new Z_2491_A(9.0f, 0.0f, 9.0f, 0.0f), finalBg);
        F_489_x.J_1907_R(this.n_1700_B, this.J_1907_R, this.G_564_y, this.P_1922_E, 9.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        this.n_1700_B(stack, alpha);
        this.n_1700_B(stack, mouseX, mouseY, alpha, animatedScroll);
    }

    private void n_1700_B(g_221_o stack, float parentAlpha) {
        String categoryName = this.u_1723_Y.name();
        float padding = 8.0f;
        float textWidth = l_3370_o.R_4764_Y[24].n_1700_B(categoryName);
        float textHeight = l_3370_o.J_1907_R[21].h_1847_R();
        int startX = (int)(this.n_1700_B + 8.0f);
        int textY = (int)(this.J_1907_R - textHeight / 2.0f + 13.0f);
        String iconToDraw = this.v_4262_N != null ? this.v_4262_N : (this.u_1723_Y != null ? (p_1458_L.values().length > this.u_1723_Y.ordinal() ? p_1458_L.values()[this.u_1723_Y.ordinal()].name() : "B") : "B");
        float iconWidth = l_3370_o.w_1484_f[26].n_1700_B(iconToDraw);
        int startXI = (int)(this.n_1700_B + this.G_564_y - 8.0f - iconWidth);
        int hdrCol = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.P_1922_E), q_3148_R.J_1907_R(K_1200_E.P_1922_E) / 255.0f * parentAlpha);
        l_3370_o.J_1907_R[21].n_1700_B(stack, categoryName, (double)startX, (double)(textY + 1), hdrCol);
        l_3370_o.w_1484_f[26].n_1700_B(stack, iconToDraw, (double)startXI, (double)(textY + 1), hdrCol);
    }

    private void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha, float animatedScroll) {
        float contentHeight = this.w_1484_f() - 34.5f;
        if (this.R_4764_Y > contentHeight) {
            this.u_2550_I = u_530_F.n_1700_B(this.u_2550_I, -this.R_4764_Y + contentHeight, 0.0f);
            animatedScroll = u_530_F.n_1700_B(animatedScroll, -this.R_4764_Y + contentHeight, 0.0f);
        } else {
            animatedScroll = 0.0f;
            this.u_2550_I = 0.0f;
        }
        float visibleTop = this.P_1922_E() + 28.0f;
        float visibleBottom = visibleTop + this.P_1922_E - 28.0f;
        i_4833_u.n_1700_B(this.G_564_y(), this.P_1922_E() + 28.0f, this.v_4262_N(), this.w_1484_f() - 32.0f);
        float offset = 0.0f;
        for (s_3815_K element : new ArrayList<s_3815_K>(this.w_1484_f)) {
            float moduleTop;
            float moduleBottom;
            element.n_1700_B(this.G_564_y() + 3.0f);
            element.J_1907_R(Math.round(this.P_1922_E() + 30.5f + offset + animatedScroll));
            element.R_4764_Y(this.v_4262_N() - 6.0f);
            element.G_564_y(19.0f);
            if (element.R_4764_Y().n_1700_B() > 0.0f) {
                float componentOffset = (float)element.Q_4569_t().stream().mapToDouble(sub -> sub.t_148_a() * sub.P_1922_E()).sum();
                float animatedHeight = 19.0f + componentOffset * element.R_4764_Y().n_1700_B();
                element.G_564_y(animatedHeight);
            }
            if ((moduleBottom = (moduleTop = element.v_4262_N()) + element.t_148_a()) > visibleTop && moduleTop < visibleBottom) {
                element.n_1700_B(stack, mouseX, mouseY, alpha);
            }
            offset += element.t_148_a() + 3.0f;
        }
        this.R_4764_Y = offset;
        i_4833_u.n_1700_B();
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        if (this.u_1723_Y == ModuleCategory.P_1922_E && button == 0) {
            float padding = 8.0f;
            float textHeight = l_3370_o.J_1907_R[21].h_1847_R();
            int textY = (int)(this.J_1907_R - textHeight / 2.0f + 13.0f);
            String iconToDraw = this.v_4262_N != null ? this.v_4262_N : (this.u_1723_Y != null ? (p_1458_L.values().length > this.u_1723_Y.ordinal() ? p_1458_L.values()[this.u_1723_Y.ordinal()].name() : "B") : "B");
            float iconWidth = l_3370_o.w_1484_f[26].n_1700_B(iconToDraw);
            int startXI = (int)(this.n_1700_B + this.G_564_y - 8.0f - iconWidth);
            float iconAreaX = startXI - 5;
            float iconAreaY = textY - 5;
            float iconAreaWidth = iconWidth + 10.0f;
            float iconAreaHeight = textHeight + 10.0f;
            if (F_747_P.n_1700_B(mouseX, mouseY, iconAreaX, iconAreaY, iconAreaWidth, iconAreaHeight)) {
                u_4724_w.n_1700_B = true;
                u_4724_w.J_1907_R.J_1907_R(0.0f);
                u_4724_w.R_4764_Y.n_1700_B();
                return;
            }
        }
        float visibleTop = this.P_1922_E() + 28.0f;
        float visibleBottom = visibleTop + this.w_1484_f() - 28.0f;
        if (F_747_P.n_1700_B(mouseX, mouseY, this.G_564_y(), visibleTop, this.v_4262_N(), this.w_1484_f() - 28.0f)) {
            for (s_3815_K element : this.w_1484_f) {
                float moduleTop = element.v_4262_N();
                float moduleBottom = moduleTop + element.t_148_a();
                if (!(moduleBottom > visibleTop) || !(moduleTop < visibleBottom) || !F_747_P.n_1700_B(mouseX, mouseY, element.u_1723_Y(), element.v_4262_N(), element.w_1484_f(), element.t_148_a())) continue;
                element.n_1700_B(mouseX, mouseY, button);
                return;
            }
        }
    }

    @Override
    public void J_1907_R(float mouseX, float mouseY, int button) {
        for (s_3815_K element : new ArrayList<s_3815_K>(this.w_1484_f)) {
            element.J_1907_R(mouseX, mouseY, button);
        }
    }

    @Override
    public void n_1700_B(int keyCode, int scanCode, int modifiers) {
        for (s_3815_K element : this.w_1484_f) {
            element.n_1700_B(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public void n_1700_B(char codePoint, int modifiers) {
        for (s_3815_K element : this.w_1484_f) {
            element.n_1700_B(codePoint, modifiers);
        }
    }

    @Override
    public boolean n_1700_B(double mouseX, double mouseY, double delta) {
        for (s_3815_K element : this.w_1484_f) {
            if (!element.n_1700_B(mouseX, mouseY, delta)) continue;
            return true;
        }
        if (F_747_P.n_1700_B((float)mouseX, (float)mouseY, this.G_564_y(), this.P_1922_E(), this.v_4262_N(), this.w_1484_f())) {
            float contentHeight = this.w_1484_f() - 34.5f;
            boolean canScroll = this.R_4764_Y > contentHeight;
            float previousScroll = this.u_2550_I;
            this.u_1723_Y((float)((double)this.M_588_G() + delta * 20.0));
            this.u_2550_I = u_530_F.n_1700_B(this.u_2550_I, -this.R_4764_Y + contentHeight, 0.0f);
            return canScroll && this.u_2550_I != previousScroll;
        }
        return false;
    }

    @Generated
    public ModuleCategory J_1907_R() {
        return this.u_1723_Y;
    }

    @Generated
    public String R_4764_Y() {
        return this.v_4262_N;
    }

    @Generated
    public float G_564_y() {
        return this.n_1700_B;
    }

    @Generated
    public float P_1922_E() {
        return this.J_1907_R;
    }

    @Generated
    public float u_1723_Y() {
        return this.R_4764_Y;
    }

    @Generated
    public float v_4262_N() {
        return this.G_564_y;
    }

    @Generated
    public float w_1484_f() {
        return this.P_1922_E;
    }

    @Generated
    public List<s_3815_K> t_148_a() {
        return this.w_1484_f;
    }

    @Generated
    public List<s_3815_K> s_956_w() {
        return this.t_148_a;
    }

    @Generated
    public Animation u_2550_I() {
        return this.s_956_w;
    }

    @Generated
    public float M_588_G() {
        return this.u_2550_I;
    }

    @Generated
    public void n_1700_B(ModuleCategory category) {
        this.u_1723_Y = category;
    }

    @Generated
    public void n_1700_B(String iconText) {
        this.v_4262_N = iconText;
    }

    @Generated
    public void n_1700_B(float x) {
        this.n_1700_B = x;
    }

    @Generated
    public void J_1907_R(float y) {
        this.J_1907_R = y;
    }

    @Generated
    public void R_4764_Y(float maxHeight) {
        this.R_4764_Y = maxHeight;
    }

    @Generated
    public void G_564_y(float width) {
        this.G_564_y = width;
    }

    @Generated
    public void P_1922_E(float height) {
        this.P_1922_E = height;
    }

    @Generated
    public void n_1700_B(List<s_3815_K> modules) {
        this.w_1484_f = modules;
    }

    @Generated
    public void n_1700_B(Animation scrollAnimation) {
        this.s_956_w = scrollAnimation;
    }

    @Generated
    public void u_1723_Y(float scroll) {
        this.u_2550_I = scroll;
    }
}


