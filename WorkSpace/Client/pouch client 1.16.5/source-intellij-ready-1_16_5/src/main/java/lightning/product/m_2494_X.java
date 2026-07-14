/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;
import lightning.product.C_2701_A;
import lightning.product.C_3304_p;
import lightning.product.FormattedText;
import lightning.product.M_2751_j;
import lightning.product.M_712_N;
import lightning.product.P_430_o;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.f_1703_u;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.l_4033_W;
import lightning.product.u_530_F;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;

public class m_2494_X
extends C_2701_A {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/advancements/widgets.png");
    private static final int[] J_1907_R = new int[]{0, 10, -10, 25, -25};
    private final P_430_o R_4764_Y;
    private final A_2629_w G_564_y;
    private final M_712_N P_1922_E;
    private final FormattedCharSequence u_1723_Y;
    private final int v_4262_N;
    private final List<FormattedCharSequence> w_1484_f;
    private final MinecraftClient t_148_a;
    private m_2494_X s_956_w;
    private final List<m_2494_X> u_2550_I = Lists.newArrayList();
    private C_3304_p M_588_G;
    private final int P_4830_p;
    private final int h_1847_R;

    public m_2494_X(P_430_o guiAdvancementTab, MinecraftClient minecraft, A_2629_w advancement, M_712_N displayInfo) {
        this.R_4764_Y = guiAdvancementTab;
        this.G_564_y = advancement;
        this.P_1922_E = displayInfo;
        this.t_148_a = minecraft;
        this.u_1723_Y = l_4033_W.R_4764_Y().n_1700_B(minecraft.t_148_a.n_1700_B(displayInfo.n_1700_B(), 163));
        this.P_4830_p = u_530_F.G_564_y(displayInfo.u_1723_Y() * 28.0f);
        this.h_1847_R = u_530_F.G_564_y(displayInfo.v_4262_N() * 27.0f);
        int i = advancement.v_4262_N();
        int j = String.valueOf(i).length();
        int k = i > 1 ? minecraft.t_148_a.J_1907_R("  ") + minecraft.t_148_a.J_1907_R("0") * j * 2 + minecraft.t_148_a.J_1907_R("/") : 0;
        int l = 29 + minecraft.t_148_a.n_1700_B(this.u_1723_Y) + k;
        this.w_1484_f = l_4033_W.R_4764_Y().n_1700_B(this.n_1700_B(ComponentUtils.n_1700_B(displayInfo.J_1907_R().P_1922_E(), Z_1567_W.n_1700_B.n_1700_B(displayInfo.P_1922_E().R_4764_Y())), l));
        for (FormattedCharSequence ireorderingprocessor : this.w_1484_f) {
            l = Math.max(l, minecraft.t_148_a.n_1700_B(ireorderingprocessor));
        }
        this.v_4262_N = l + 3 + 5;
    }

    private static float n_1700_B(f_1703_u manager, List<FormattedText> text) {
        return (float)text.stream().mapToDouble(manager::n_1700_B).max().orElse(0.0);
    }

    private List<FormattedText> n_1700_B(x_282_a component, int maxWidth) {
        f_1703_u charactermanager = this.t_148_a.t_148_a.J_1907_R();
        List<FormattedText> list = null;
        float f = Float.MAX_VALUE;
        for (int i : J_1907_R) {
            List<FormattedText> list1 = charactermanager.J_1907_R(component, maxWidth - i, Z_1567_W.n_1700_B);
            float f1 = Math.abs(m_2494_X.n_1700_B(charactermanager, list1) - (float)maxWidth);
            if (f1 <= 10.0f) {
                return list1;
            }
            if (!(f1 < f)) continue;
            f = f1;
            list = list1;
        }
        return list;
    }

    @Nullable
    private m_2494_X n_1700_B(A_2629_w advancementIn) {
        while ((advancementIn = advancementIn.J_1907_R()) != null && advancementIn.R_4764_Y() == null) {
        }
        return advancementIn != null && advancementIn.R_4764_Y() != null ? this.R_4764_Y.J_1907_R(advancementIn) : null;
    }

    public void n_1700_B(g_221_o matrixStack, int x, int y, boolean dropShadow) {
        if (this.s_956_w != null) {
            int j1;
            int i = x + this.s_956_w.P_4830_p + 13;
            int j = x + this.s_956_w.P_4830_p + 26 + 4;
            int k = y + this.s_956_w.h_1847_R + 13;
            int l = x + this.P_4830_p + 13;
            int i1 = y + this.h_1847_R + 13;
            int n = j1 = dropShadow ? -16777216 : -1;
            if (dropShadow) {
                this.hLine(matrixStack, j, i, k - 1, j1);
                this.hLine(matrixStack, j + 1, i, k, j1);
                this.hLine(matrixStack, j, i, k + 1, j1);
                this.hLine(matrixStack, l, j - 1, i1 - 1, j1);
                this.hLine(matrixStack, l, j - 1, i1, j1);
                this.hLine(matrixStack, l, j - 1, i1 + 1, j1);
                this.vLine(matrixStack, j - 1, i1, k, j1);
                this.vLine(matrixStack, j + 1, i1, k, j1);
            } else {
                this.hLine(matrixStack, j, i, k, j1);
                this.hLine(matrixStack, l, j, i1, j1);
                this.vLine(matrixStack, j, i1, k, j1);
            }
        }
        for (m_2494_X advancemententrygui : this.u_2550_I) {
            advancemententrygui.n_1700_B(matrixStack, x, y, dropShadow);
        }
    }

    public void n_1700_B(g_221_o matrixStack, int x, int y) {
        if (!this.P_1922_E.s_956_w() || this.M_588_G != null && this.M_588_G.n_1700_B()) {
            float f = this.M_588_G == null ? 0.0f : this.M_588_G.R_4764_Y();
            M_2751_j advancementstate = f >= 1.0f ? M_2751_j.n_1700_B : M_2751_j.J_1907_R;
            this.t_148_a.G_624_v().n_1700_B(n_1700_B);
            this.blit(matrixStack, x + this.P_4830_p + 3, y + this.h_1847_R, this.P_1922_E.P_1922_E().J_1907_R(), 128 + advancementstate.n_1700_B() * 26, 26, 26);
            this.t_148_a.r_715_M().R_4764_Y(this.P_1922_E.R_4764_Y(), x + this.P_4830_p + 8, y + this.h_1847_R + 5);
        }
        for (m_2494_X advancemententrygui : this.u_2550_I) {
            advancemententrygui.n_1700_B(matrixStack, x, y);
        }
    }

    public void n_1700_B(C_3304_p advancementProgressIn) {
        this.M_588_G = advancementProgressIn;
    }

    public void n_1700_B(m_2494_X guiAdvancementIn) {
        this.u_2550_I.add(guiAdvancementIn);
    }

    public void n_1700_B(g_221_o matrixStack, int x, int y, float fade, int width, int height) {
        M_2751_j advancementstate2;
        M_2751_j advancementstate1;
        M_2751_j advancementstate;
        boolean flag = width + x + this.P_4830_p + this.v_4262_N + 26 >= this.R_4764_Y.R_4764_Y().width;
        String s = this.M_588_G == null ? null : this.M_588_G.G_564_y();
        int i = s == null ? 0 : this.t_148_a.t_148_a.J_1907_R(s);
        boolean flag1 = 113 - y - this.h_1847_R - 26 <= 6 + this.w_1484_f.size() * 9;
        float f = this.M_588_G == null ? 0.0f : this.M_588_G.R_4764_Y();
        int j = u_530_F.G_564_y(f * (float)this.v_4262_N);
        if (f >= 1.0f) {
            j = this.v_4262_N / 2;
            advancementstate = M_2751_j.n_1700_B;
            advancementstate1 = M_2751_j.n_1700_B;
            advancementstate2 = M_2751_j.n_1700_B;
        } else if (j < 2) {
            j = this.v_4262_N / 2;
            advancementstate = M_2751_j.J_1907_R;
            advancementstate1 = M_2751_j.J_1907_R;
            advancementstate2 = M_2751_j.J_1907_R;
        } else if (j > this.v_4262_N - 2) {
            j = this.v_4262_N / 2;
            advancementstate = M_2751_j.n_1700_B;
            advancementstate1 = M_2751_j.n_1700_B;
            advancementstate2 = M_2751_j.J_1907_R;
        } else {
            advancementstate = M_2751_j.n_1700_B;
            advancementstate1 = M_2751_j.J_1907_R;
            advancementstate2 = M_2751_j.J_1907_R;
        }
        int k = this.v_4262_N - j;
        this.t_148_a.G_624_v().n_1700_B(n_1700_B);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.Y_601_j();
        int l = y + this.h_1847_R;
        int i1 = flag ? x + this.P_4830_p - this.v_4262_N + 26 + 6 : x + this.P_4830_p;
        int j1 = 32 + this.w_1484_f.size() * 9;
        if (!this.w_1484_f.isEmpty()) {
            if (flag1) {
                this.n_1700_B(matrixStack, i1, l + 26 - j1, this.v_4262_N, j1, 10, 200, 26, 0, 52);
            } else {
                this.n_1700_B(matrixStack, i1, l, this.v_4262_N, j1, 10, 200, 26, 0, 52);
            }
        }
        this.blit(matrixStack, i1, l, 0, advancementstate.n_1700_B() * 26, j, 26);
        this.blit(matrixStack, i1 + j, l, 200 - k, advancementstate1.n_1700_B() * 26, k, 26);
        this.blit(matrixStack, x + this.P_4830_p + 3, y + this.h_1847_R, this.P_1922_E.P_1922_E().J_1907_R(), 128 + advancementstate2.n_1700_B() * 26, 26, 26);
        if (flag) {
            this.t_148_a.t_148_a.n_1700_B(matrixStack, this.u_1723_Y, (float)(i1 + 5), (float)(y + this.h_1847_R + 9), -1);
            if (s != null) {
                this.t_148_a.t_148_a.n_1700_B(matrixStack, s, (float)(x + this.P_4830_p - i), (float)(y + this.h_1847_R + 9), -1);
            }
        } else {
            this.t_148_a.t_148_a.n_1700_B(matrixStack, this.u_1723_Y, (float)(x + this.P_4830_p + 32), (float)(y + this.h_1847_R + 9), -1);
            if (s != null) {
                this.t_148_a.t_148_a.n_1700_B(matrixStack, s, (float)(x + this.P_4830_p + this.v_4262_N - i - 5), (float)(y + this.h_1847_R + 9), -1);
            }
        }
        if (flag1) {
            for (int k1 = 0; k1 < this.w_1484_f.size(); ++k1) {
                this.t_148_a.t_148_a.J_1907_R(matrixStack, this.w_1484_f.get(k1), (float)(i1 + 5), (float)(l + 26 - j1 + 7 + k1 * 9), -5592406);
            }
        } else {
            for (int l1 = 0; l1 < this.w_1484_f.size(); ++l1) {
                this.t_148_a.t_148_a.J_1907_R(matrixStack, this.w_1484_f.get(l1), (float)(i1 + 5), (float)(y + this.h_1847_R + 9 + 17 + l1 * 9), -5592406);
            }
        }
        this.t_148_a.r_715_M().R_4764_Y(this.P_1922_E.R_4764_Y(), x + this.P_4830_p + 8, y + this.h_1847_R + 5);
    }

    protected void n_1700_B(g_221_o matrixStack, int x, int y, int width, int height, int padding, int uWidth, int vHeight, int uOffset, int vOffset) {
        this.blit(matrixStack, x, y, uOffset, vOffset, padding, padding);
        this.n_1700_B(matrixStack, x + padding, y, width - padding - padding, padding, uOffset + padding, vOffset, uWidth - padding - padding, vHeight);
        this.blit(matrixStack, x + width - padding, y, uOffset + uWidth - padding, vOffset, padding, padding);
        this.blit(matrixStack, x, y + height - padding, uOffset, vOffset + vHeight - padding, padding, padding);
        this.n_1700_B(matrixStack, x + padding, y + height - padding, width - padding - padding, padding, uOffset + padding, vOffset + vHeight - padding, uWidth - padding - padding, vHeight);
        this.blit(matrixStack, x + width - padding, y + height - padding, uOffset + uWidth - padding, vOffset + vHeight - padding, padding, padding);
        this.n_1700_B(matrixStack, x, y + padding, padding, height - padding - padding, uOffset, vOffset + padding, uWidth, vHeight - padding - padding);
        this.n_1700_B(matrixStack, x + padding, y + padding, width - padding - padding, height - padding - padding, uOffset + padding, vOffset + padding, uWidth - padding - padding, vHeight - padding - padding);
        this.n_1700_B(matrixStack, x + width - padding, y + padding, padding, height - padding - padding, uOffset + uWidth - padding, vOffset + padding, uWidth, vHeight - padding - padding);
    }

    protected void n_1700_B(g_221_o matrixStack, int x, int y, int borderToU, int borderToV, int uOffset, int vOffset, int uWidth, int vHeight) {
        for (int i = 0; i < borderToU; i += uWidth) {
            int j = x + i;
            int k = Math.min(uWidth, borderToU - i);
            for (int l = 0; l < borderToV; l += vHeight) {
                int i1 = y + l;
                int j1 = Math.min(vHeight, borderToV - l);
                this.blit(matrixStack, j, i1, uOffset, vOffset, k, j1);
            }
        }
    }

    public boolean n_1700_B(int x, int y, int mouseX, int mouseY) {
        if (!this.P_1922_E.s_956_w() || this.M_588_G != null && this.M_588_G.n_1700_B()) {
            int i = x + this.P_4830_p;
            int j = i + 26;
            int k = y + this.h_1847_R;
            int l = k + 26;
            return mouseX >= i && mouseX <= j && mouseY >= k && mouseY <= l;
        }
        return false;
    }

    public void n_1700_B() {
        if (this.s_956_w == null && this.G_564_y.J_1907_R() != null) {
            this.s_956_w = this.n_1700_B(this.G_564_y);
            if (this.s_956_w != null) {
                this.s_956_w.n_1700_B(this);
            }
        }
    }

    public int J_1907_R() {
        return this.h_1847_R;
    }

    public int R_4764_Y() {
        return this.P_4830_p;
    }
}



