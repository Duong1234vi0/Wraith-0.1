/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;
import lightning.product.C_2701_A;
import lightning.product.C_3240_x;
import lightning.product.H_3330_w;
import lightning.product.I_2695_V;
import lightning.product.M_712_N;
import lightning.product.Z_1993_T;
import lightning.product.Z_3926_G;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.m_2494_X;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class P_430_o
extends C_2701_A {
    private final MinecraftClient n_1700_B;
    private final Z_3926_G J_1907_R;
    private final I_2695_V R_4764_Y;
    private final int G_564_y;
    private final A_2629_w P_1922_E;
    private final M_712_N u_1723_Y;
    private final Z_1993_T v_4262_N;
    private final x_282_a w_1484_f;
    private final m_2494_X t_148_a;
    private final Map<A_2629_w, m_2494_X> s_956_w = Maps.newLinkedHashMap();
    private double u_2550_I;
    private double M_588_G;
    private int P_4830_p = Integer.MAX_VALUE;
    private int h_1847_R = Integer.MAX_VALUE;
    private int Q_4569_t = Integer.MIN_VALUE;
    private int M_182_A = Integer.MIN_VALUE;
    private float t_1786_h;
    private boolean multiplayerClientSuggestionProvider;

    public P_430_o(MinecraftClient minecraft, Z_3926_G screen, I_2695_V type, int index, A_2629_w advancement, M_712_N displayInfo) {
        this.n_1700_B = minecraft;
        this.J_1907_R = screen;
        this.R_4764_Y = type;
        this.G_564_y = index;
        this.P_1922_E = advancement;
        this.u_1723_Y = displayInfo;
        this.v_4262_N = displayInfo.R_4764_Y();
        this.w_1484_f = displayInfo.n_1700_B();
        this.t_148_a = new m_2494_X(this, minecraft, advancement, displayInfo);
        this.n_1700_B(this.t_148_a, advancement);
    }

    public A_2629_w n_1700_B() {
        return this.P_1922_E;
    }

    public x_282_a J_1907_R() {
        return this.w_1484_f;
    }

    public void n_1700_B(g_221_o matrixStack, int offsetX, int offsetY, boolean isSelected) {
        this.R_4764_Y.n_1700_B(matrixStack, this, offsetX, offsetY, isSelected, this.G_564_y);
    }

    public void n_1700_B(int offsetX, int offsetY, H_3330_w renderer) {
        this.R_4764_Y.n_1700_B(offsetX, offsetY, this.G_564_y, renderer, this.v_4262_N);
    }

    public void n_1700_B(g_221_o matrixStack) {
        if (!this.multiplayerClientSuggestionProvider) {
            this.u_2550_I = 117 - (this.Q_4569_t + this.P_4830_p) / 2;
            this.M_588_G = 56 - (this.M_182_A + this.h_1847_R) / 2;
            this.multiplayerClientSuggestionProvider = true;
        }
        c_4037_x.v_4276_D();
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.R_4764_Y(0.0f, 0.0f, 950.0f);
        c_4037_x.n_1700_B(false, false, false, false);
        P_430_o.fill(matrixStack, 4680, 2260, -4680, -2260, -16777216);
        c_4037_x.n_1700_B(true, true, true, true);
        c_4037_x.R_4764_Y(0.0f, 0.0f, -950.0f);
        c_4037_x.J_1907_R(518);
        P_430_o.fill(matrixStack, 234, 113, 0, 0, -16777216);
        c_4037_x.J_1907_R(515);
        g_2336_b resourcelocation = this.u_1723_Y.G_564_y();
        if (resourcelocation != null) {
            this.n_1700_B.G_624_v().n_1700_B(resourcelocation);
        } else {
            this.n_1700_B.G_624_v().n_1700_B(C_3240_x.n_1700_B);
        }
        int i = u_530_F.R_4764_Y(this.u_2550_I);
        int j = u_530_F.R_4764_Y(this.M_588_G);
        int k = i % 16;
        int l = j % 16;
        for (int i1 = -1; i1 <= 15; ++i1) {
            for (int j1 = -1; j1 <= 8; ++j1) {
                P_430_o.blit(matrixStack, k + 16 * i1, l + 16 * j1, 0.0f, 0.0f, 16, 16, 16, 16);
            }
        }
        this.t_148_a.n_1700_B(matrixStack, i, j, true);
        this.t_148_a.n_1700_B(matrixStack, i, j, false);
        this.t_148_a.n_1700_B(matrixStack, i, j);
        c_4037_x.J_1907_R(518);
        c_4037_x.R_4764_Y(0.0f, 0.0f, -950.0f);
        c_4037_x.n_1700_B(false, false, false, false);
        P_430_o.fill(matrixStack, 4680, 2260, -4680, -2260, -16777216);
        c_4037_x.n_1700_B(true, true, true, true);
        c_4037_x.R_4764_Y(0.0f, 0.0f, 950.0f);
        c_4037_x.J_1907_R(515);
        c_4037_x.d_2461_k();
    }

    public void n_1700_B(g_221_o matrixStack, int mouseX, int mouseY, int width, int height) {
        c_4037_x.v_4276_D();
        c_4037_x.R_4764_Y(0.0f, 0.0f, 200.0f);
        P_430_o.fill(matrixStack, 0, 0, 234, 113, u_530_F.G_564_y(this.t_1786_h * 255.0f) << 24);
        boolean flag = false;
        int i = u_530_F.R_4764_Y(this.u_2550_I);
        int j = u_530_F.R_4764_Y(this.M_588_G);
        if (mouseX > 0 && mouseX < 234 && mouseY > 0 && mouseY < 113) {
            for (m_2494_X advancemententrygui : this.s_956_w.values()) {
                if (!advancemententrygui.n_1700_B(i, j, mouseX, mouseY)) continue;
                flag = true;
                advancemententrygui.n_1700_B(matrixStack, i, j, this.t_1786_h, width, height);
                break;
            }
        }
        c_4037_x.d_2461_k();
        this.t_1786_h = flag ? u_530_F.n_1700_B(this.t_1786_h + 0.02f, 0.0f, 0.3f) : u_530_F.n_1700_B(this.t_1786_h - 0.04f, 0.0f, 1.0f);
    }

    public boolean n_1700_B(int offsetX, int offsetY, double mouseX, double mouseY) {
        return this.R_4764_Y.n_1700_B(offsetX, offsetY, this.G_564_y, mouseX, mouseY);
    }

    @Nullable
    public static P_430_o n_1700_B(MinecraftClient minecraft, Z_3926_G screen, int tabIndex, A_2629_w advancement) {
        if (advancement.R_4764_Y() == null) {
            return null;
        }
        for (I_2695_V advancementtabtype : I_2695_V.values()) {
            if (tabIndex < advancementtabtype.n_1700_B()) {
                return new P_430_o(minecraft, screen, advancementtabtype, tabIndex, advancement, advancement.R_4764_Y());
            }
            tabIndex -= advancementtabtype.n_1700_B();
        }
        return null;
    }

    public void n_1700_B(double dragX, double dragY) {
        if (this.Q_4569_t - this.P_4830_p > 234) {
            this.u_2550_I = u_530_F.n_1700_B(this.u_2550_I + dragX, (double)(-(this.Q_4569_t - 234)), 0.0);
        }
        if (this.M_182_A - this.h_1847_R > 113) {
            this.M_588_G = u_530_F.n_1700_B(this.M_588_G + dragY, (double)(-(this.M_182_A - 113)), 0.0);
        }
    }

    public void n_1700_B(A_2629_w advancement) {
        if (advancement.R_4764_Y() != null) {
            m_2494_X advancemententrygui = new m_2494_X(this, this.n_1700_B, advancement, advancement.R_4764_Y());
            this.n_1700_B(advancemententrygui, advancement);
        }
    }

    private void n_1700_B(m_2494_X gui, A_2629_w advancement) {
        this.s_956_w.put(advancement, gui);
        int i = gui.R_4764_Y();
        int j = i + 28;
        int k = gui.J_1907_R();
        int l = k + 27;
        this.P_4830_p = Math.min(this.P_4830_p, i);
        this.Q_4569_t = Math.max(this.Q_4569_t, j);
        this.h_1847_R = Math.min(this.h_1847_R, k);
        this.M_182_A = Math.max(this.M_182_A, l);
        for (m_2494_X advancemententrygui : this.s_956_w.values()) {
            advancemententrygui.n_1700_B();
        }
    }

    @Nullable
    public m_2494_X J_1907_R(A_2629_w advancement) {
        return this.s_956_w.get(advancement);
    }

    public Z_3926_G R_4764_Y() {
        return this.J_1907_R;
    }
}



