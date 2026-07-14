/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  lombok.Generated
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_1491_c;
import lightning.product.H_2506_c;
import lightning.product.I_2209_R;
import lightning.product.NumberSetting;
import lightning.product.K_1200_E;
import lightning.product.M_1336_P;
import lightning.product.N_2266_w;
import lightning.product.N_4006_T;
import lightning.product.MultiBooleanSetting;
import lightning.product.O_3016_i;
import lightning.product.P_3676_m;
import lightning.product.Q_4222_k;
import lightning.product.Module;
import lightning.product.b_2037_V;
import lightning.product.c_1732_c;
import lightning.product.f_887_Z;
import lightning.product.g_221_o;
import lightning.product.SoundEventRegistration;
import lightning.product.h_2367_h;
import lightning.product.h_3858_e;
import lightning.product.Setting;
import lightning.product.i_4833_u;
import lightning.product.j_1654_T;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.l_4397_i;
import lightning.product.BooleanSetting;
import lightning.product.p_3749_n;
import lightning.product.q_3148_R;
import lightning.product.KeyBindSetting;
import lightning.product.ModeSetting;
import lightning.product.t_4433_T;
import lombok.Generated;

public class s_3815_K
extends N_4006_T {
    private final Module J_1907_R;
    private final Animation R_4764_Y = new Animation(0.0f, 15.0f);
    private final Animation G_564_y = new Animation(0.0f, 12.0f);
    public boolean n_1700_B = false;
    private boolean P_1922_E;
    private final ObjectArrayList<N_4006_T> u_1723_Y = new ObjectArrayList();

    public s_3815_K(Module module) {
        this.J_1907_R = module;
        for (Setting<?> setting : module.u_2550_I()) {
            N_4006_T element = null;
            if (setting instanceof BooleanSetting) {
                BooleanSetting booleanSetting = (BooleanSetting)setting;
                element = new p_3749_n(booleanSetting);
            }
            if (setting instanceof NumberSetting) {
                NumberSetting sliderSetting = (NumberSetting)setting;
                element = new h_3858_e(sliderSetting);
            }
            if (setting instanceof KeyBindSetting) {
                KeyBindSetting bindSetting = (KeyBindSetting)setting;
                element = new N_2266_w(bindSetting);
            }
            if (setting instanceof ModeSetting) {
                ModeSetting modeSetting = (ModeSetting)setting;
                element = new c_1732_c(modeSetting);
            }
            if (setting instanceof MultiBooleanSetting) {
                MultiBooleanSetting multiBooleanSetting = (MultiBooleanSetting)setting;
                element = new I_2209_R(multiBooleanSetting);
            }
            if (setting instanceof h_2367_h) {
                h_2367_h colorSetting = (h_2367_h)setting;
                element = new f_887_Z(colorSetting);
            }
            if (setting instanceof O_3016_i) {
                O_3016_i stringSetting = (O_3016_i)setting;
                element = new l_4397_i(stringSetting);
            }
            if (setting instanceof b_2037_V) {
                b_2037_V textSetting = (b_2037_V)setting;
                element = new t_4433_T(textSetting);
            }
            if (setting instanceof SoundEventRegistration) {
                SoundEventRegistration buttonSetting = (SoundEventRegistration)setting;
                element = new P_3676_m(buttonSetting, buttonSetting.t_148_a(), buttonSetting.s_956_w());
            }
            if (setting instanceof H_1491_c) {
                H_1491_c clickSetting = (H_1491_c)setting;
                element = new Q_4222_k(clickSetting);
            }
            if (element == null) continue;
            element.n_1700_B(module);
            this.u_1723_Y.add((Object)element);
        }
        this.R_4764_Y.J_1907_R(0.0f);
        this.G_564_y.J_1907_R(0.0f);
        for (N_4006_T element : this.u_1723_Y) {
            element.R_4764_Y(this.w_1484_f());
        }
    }

    @Override
    public void n_1700_B(g_221_o stack, float mouseX, float mouseY, float alpha) {
        float textDrawY;
        float textDrawX;
        this.J_1907_R.M_588_G().n_1700_B(this.J_1907_R.w_1484_f() ? 1.0f : 0.0f);
        int baseColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.multiplayerClientSuggestionProvider), q_3148_R.n_1700_B(K_1200_E.t_1786_h), this.J_1907_R.M_588_G().n_1700_B());
        float baseAlpha = (float)H_2506_c.G_564_y(baseColor) / 255.0f * alpha;
        int bgColorWithAlpha = H_2506_c.n_1700_B(baseColor, baseAlpha);
        F_489_x.n_1700_B(this.u_1723_Y(), this.v_4262_N(), this.w_1484_f(), this.t_148_a(), 5.0f, bgColorWithAlpha);
        F_489_x.J_1907_R(this.u_1723_Y(), this.v_4262_N(), this.w_1484_f(), this.t_148_a(), 5.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        float textX = this.u_1723_Y() + 5.0f;
        int textY = (int)(this.v_4262_N() + (19.0f - l_3370_o.R_4764_Y[18].h_1847_R()) / 2.0f);
        int textCol = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha);
        l_3370_o.R_4764_Y[15].n_1700_B(stack, this.J_1907_R.G_564_y(), (double)textX, (double)(textY + 1), textCol);
        boolean hasSettings = this.u_1723_Y.stream().anyMatch(N_4006_T::n_1700_B);
        float iconX = this.u_1723_Y() + this.w_1484_f() - 10.0f;
        String bindLabel = this.J_1907_R.v_4262_N() != -100 ? j_1654_T.n_1700_B(this.J_1907_R.v_4262_N()) : "...";
        float bindTextWidth = l_3370_o.R_4764_Y[13].n_1700_B(bindLabel);
        float iconInsideW = l_3370_o.w_1484_f[12].n_1700_B("C");
        float iconInsideH = l_3370_o.w_1484_f[12].h_1847_R();
        float sepWidth = 1.0f;
        float padding = 4.0f;
        float rectH = 9.0f;
        float rectW = Math.max(iconInsideW + sepWidth + bindTextWidth + padding * 2.0f, 20.0f);
        float rectRight = hasSettings ? iconX - 6.0f : this.u_1723_Y() + this.w_1484_f() - 5.0f;
        float rectX = rectRight - rectW;
        float rectY = (float)textY + (l_3370_o.R_4764_Y[18].h_1847_R() - rectH) / 2.0f;
        int rectFill = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.t_1786_h), q_3148_R.J_1907_R(K_1200_E.t_1786_h) / 255.0f * alpha);
        int rectOutline = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) / 255.0f * alpha);
        F_489_x.n_1700_B(rectX, rectY, rectW, rectH, 2.0f, rectFill);
        F_489_x.J_1907_R(rectX, rectY, rectW, rectH, 2.0f, q_3148_R.n_1700_B(K_1200_E.h_1847_R), q_3148_R.J_1907_R(K_1200_E.h_1847_R) * alpha);
        float iconDrawX = rectX + padding / 2.0f;
        float iconDrawY = rectY + (rectH - iconInsideH) / 2.0f + 1.0f;
        l_3370_o.w_1484_f[12].n_1700_B(stack, "C", (double)(iconDrawX + 0.5f), (double)iconDrawY, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha));
        float sepX = iconDrawX + iconInsideW + 2.0f;
        F_489_x.n_1700_B(sepX, rectY + 1.0f, sepWidth, rectH - 2.0f, 0.5f, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.Q_4569_t), q_3148_R.J_1907_R(K_1200_E.Q_4569_t) / 255.0f * alpha));
        float availableTextSpace = rectX + rectW - (sepX + sepWidth) - 2.0f;
        if (bindLabel.equals("...")) {
            textDrawX = sepX + sepWidth + (availableTextSpace - l_3370_o.R_4764_Y[13].n_1700_B(bindLabel)) / 2.0f + 0.5f;
            textDrawY = rectY + (rectH - l_3370_o.R_4764_Y[13].h_1847_R()) / 2.0f - 0.5f;
        } else {
            textDrawX = sepX + sepWidth + 1.5f;
            textDrawY = rectY + (rectH - l_3370_o.R_4764_Y[12].h_1847_R()) / 2.0f + 0.5f;
        }
        l_3370_o.R_4764_Y[13].n_1700_B(stack, bindLabel, (double)textDrawX, (double)textDrawY, H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha));
        if (hasSettings) {
            int dotsCol = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.R_4764_Y), q_3148_R.J_1907_R(K_1200_E.R_4764_Y) / 255.0f * alpha);
            this.R_4764_Y.n_1700_B(this.n_1700_B ? 1.0f : 0.0f);
            float angle = 90.0f * this.R_4764_Y.n_1700_B();
            float iconY = (float)textY + 1.5f;
            float iconW = l_3370_o.w_1484_f[17].n_1700_B("B");
            float iconH = l_3370_o.w_1484_f[17].h_1847_R();
            stack.n_1700_B();
            stack.n_1700_B((double)(iconX + iconW / 2.0f), (double)(iconY + iconH / 2.0f), 0.0);
            stack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(angle));
            l_3370_o.w_1484_f[17].n_1700_B(stack, "B", (double)(-iconW / 2.0f), (double)(-iconH / 2.0f), dotsCol);
            stack.J_1907_R();
        }
        this.J_1907_R(stack, mouseX, mouseY, alpha);
        super.n_1700_B(stack, mouseX, mouseY, alpha);
    }

    public void J_1907_R(g_221_o stack, float mouseX, float mouseY, float parentAlpha) {
        this.R_4764_Y.n_1700_B(this.n_1700_B ? 1.0f : 0.0f);
        this.G_564_y.n_1700_B(this.n_1700_B ? 1.0f : 0.0f);
        if ((double)this.R_4764_Y.n_1700_B() > 0.01) {
            i_4833_u.n_1700_B(this.u_1723_Y(), this.v_4262_N(), this.w_1484_f(), this.t_148_a());
            float baseYOffset = -8.0f * (1.0f - this.R_4764_Y.n_1700_B());
            float baseY = this.v_4262_N() + 20.0f + baseYOffset;
            float currentYOffset = 0.0f;
            for (N_4006_T element : this.u_1723_Y) {
                float visibleValue = element.P_1922_E();
                if (!(visibleValue > 0.0f)) continue;
                float offsetY = -8.0f * (1.0f - visibleValue);
                float targetY = baseY + currentYOffset + offsetY;
                float currentY = baseY + (targetY - baseY) * this.R_4764_Y.n_1700_B();
                element.n_1700_B(Math.round(this.u_1723_Y()));
                element.J_1907_R(Math.round(currentY));
                element.R_4764_Y(this.w_1484_f());
                float alphaFade = this.G_564_y.n_1700_B() * visibleValue * parentAlpha;
                element.n_1700_B(stack, mouseX, mouseY, alphaFade);
                currentYOffset += element.t_148_a() * visibleValue;
            }
            i_4833_u.n_1700_B();
        }
    }

    @Override
    public void n_1700_B(int keyCode, int scanCode, int modifiers) {
        for (N_4006_T element : this.u_1723_Y) {
            if (!element.n_1700_B()) continue;
            element.n_1700_B(keyCode, scanCode, modifiers);
        }
        super.n_1700_B(keyCode, scanCode, modifiers);
    }

    @Override
    public void n_1700_B(char codePoint, int modifiers) {
        for (N_4006_T element : this.u_1723_Y) {
            if (!element.n_1700_B()) continue;
            element.n_1700_B(codePoint, modifiers);
        }
        super.n_1700_B(codePoint, modifiers);
    }

    @Override
    public void n_1700_B(float mouseX, float mouseY, int button) {
        if (this.J_1907_R(mouseX, mouseY) && this.n_1700_B(mouseX, mouseY, 15.0f)) {
            float bindRectY;
            float rectW;
            float textY = this.v_4262_N() + 4.0f;
            boolean hasSettings = this.u_1723_Y.stream().anyMatch(N_4006_T::n_1700_B);
            float iconX = this.u_1723_Y() + this.w_1484_f() - 10.0f;
            String bindLabel = this.J_1907_R.v_4262_N() != -100 ? j_1654_T.n_1700_B(this.J_1907_R.v_4262_N()) : "...";
            float bindTextWidth = l_3370_o.R_4764_Y[13].n_1700_B(bindLabel);
            float iconInsideW = l_3370_o.w_1484_f[12].n_1700_B("C");
            float sepWidth = 1.0f;
            float padding = 4.0f;
            float rectH = 9.0f;
            float rectRight = hasSettings ? iconX - 6.0f : this.u_1723_Y() + this.w_1484_f() - 5.0f;
            float bindRectX = rectRight - (rectW = Math.max(iconInsideW + sepWidth + bindTextWidth + padding * 2.0f, 20.0f));
            boolean isBindZone = F_747_P.n_1700_B(mouseX, mouseY, bindRectX - 2.0f, (bindRectY = textY + (l_3370_o.R_4764_Y[18].h_1847_R() - rectH) / 2.0f) - 2.0f, rectW + 4.0f, rectH);
            if (isBindZone) {
                return;
            }
            if (button == 0) {
                this.J_1907_R.R_4764_Y();
            } else if (button == 1 && this.u_1723_Y.stream().anyMatch(N_4006_T::n_1700_B)) {
                this.n_1700_B = !this.n_1700_B;
                for (N_4006_T element : this.u_1723_Y) {
                    element.R_4764_Y(this.w_1484_f());
                    element.J_1907_R();
                }
            }
        }
        if (this.n_1700_B && this.R_4764_Y.n_1700_B() > 0.99f && this.J_1907_R(mouseX, mouseY)) {
            for (int i = this.u_1723_Y.size() - 1; i >= 0; --i) {
                N_4006_T element = (N_4006_T)this.u_1723_Y.get(i);
                if (!element.n_1700_B() || element.P_1922_E() < 0.99f) continue;
                if (element instanceof N_2266_w) {
                    N_2266_w bindElement = (N_2266_w)element;
                    if (bindElement.J_1907_R) {
                        bindElement.n_1700_B(mouseX, mouseY, button);
                        break;
                    }
                }
                if (!element.n_1700_B(mouseX, mouseY)) continue;
                element.n_1700_B(mouseX, mouseY, button);
                break;
            }
        }
        super.n_1700_B(mouseX, mouseY, button);
    }

    @Override
    public boolean n_1700_B(double mouseX, double mouseY, double delta) {
        if (this.n_1700_B) {
            for (N_4006_T element : this.u_1723_Y) {
                if (!element.n_1700_B() || !element.n_1700_B(mouseX, mouseY, delta)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean J_1907_R(float mouseX, float mouseY) {
        if (this.s_956_w() == null) {
            return false;
        }
        float visibleTop = this.s_956_w().P_1922_E() + 28.0f;
        float visibleBottom = visibleTop + this.s_956_w().w_1484_f() - 28.0f;
        return mouseY >= visibleTop && mouseY <= visibleBottom && mouseX >= this.s_956_w().G_564_y() && mouseX <= this.s_956_w().G_564_y() + this.s_956_w().v_4262_N();
    }

    @Override
    public void J_1907_R(float mouseX, float mouseY, int button) {
        for (N_4006_T element : this.u_1723_Y) {
            element.J_1907_R(mouseX, mouseY, button);
        }
        super.J_1907_R(mouseX, mouseY, button);
    }

    @Generated
    public void n_1700_B(boolean open) {
        this.n_1700_B = open;
    }

    @Generated
    public void J_1907_R(boolean bind) {
        this.P_1922_E = bind;
    }

    @Override
    @Generated
    public Module u_2550_I() {
        return this.J_1907_R;
    }

    @Generated
    public Animation R_4764_Y() {
        return this.R_4764_Y;
    }

    @Generated
    public Animation G_564_y() {
        return this.G_564_y;
    }

    @Generated
    public boolean P_4830_p() {
        return this.n_1700_B;
    }

    @Generated
    public boolean h_1847_R() {
        return this.P_1922_E;
    }

    @Generated
    public ObjectArrayList<N_4006_T> Q_4569_t() {
        return this.u_1723_Y;
    }
}



