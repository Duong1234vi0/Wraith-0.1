/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.D_1410_T;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.U_2871_b;
import lightning.product.Z_2491_A;
import lightning.product.g_221_o;
import lightning.product.l_3370_o;
import lightning.product.o_4117_e;
import lombok.Generated;

public class y_3417_N
extends o_4117_e {
    private final String w_1484_f;
    private final int t_148_a = 0;
    private final Integer s_956_w;

    public y_3417_N(String title, String message, int color) {
        super(new U_2871_b(message));
        this.w_1484_f = title;
        this.s_956_w = color;
    }

    @Override
    public void n_1700_B(float x, float y, g_221_o matrixStack) {
        this.n_1700_B(y);
        boolean expired = this.n_1700_B();
        this.P_1922_E.n_1700_B(expired ? 0.0f : 1.0f);
        this.G_564_y.n_1700_B(this.u_1723_Y && expired ? y - 2.0f : y);
        float animatedY = this.G_564_y.n_1700_B();
        float animatedAlpha = this.P_1922_E.n_1700_B();
        float width = l_3370_o.u_1723_Y[16].n_1700_B(this.w_1484_f) + l_3370_o.G_564_y[12].n_1700_B(this.n_1700_B.getString()) + 5.0f;
        float totalWidth = width + 11.5f;
        int iconColorValue = (Integer)D_1410_T.w_1484_f.J_1907_R();
        int iconColor = H_2506_c.n_1700_B(iconColorValue, animatedAlpha);
        int glow = (Integer)D_1410_T.s_956_w.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        F_489_x.n_1700_B(x + l_3370_o.u_1723_Y[16].n_1700_B(this.w_1484_f), animatedY - 10.0f, totalWidth - (l_3370_o.u_1723_Y[16].n_1700_B(this.w_1484_f) + 9.0f) + 20.0f, 33.0f, 3.0f, glow, glow, glow, glow, glowAlpha * animatedAlpha, 10.0f);
        F_489_x.n_1700_B(x - 10.0f, animatedY - 10.0f, l_3370_o.u_1723_Y[16].n_1700_B(this.w_1484_f) + 9.0f + 20.0f, 33.0f, 3.0f, glow, glow, glow, glow, glowAlpha * animatedAlpha, 10.0f);
        int bgColorValue = (Integer)D_1410_T.v_4262_N.J_1907_R();
        F_489_x.n_1700_B(x, animatedY, l_3370_o.u_1723_Y[16].n_1700_B(this.w_1484_f) + 9.0f, 13.0f, new Z_2491_A(3.0f, 3.0f, 1.0f, 1.0f), bgColorValue, animatedAlpha);
        F_489_x.n_1700_B(x + l_3370_o.u_1723_Y[16].n_1700_B(this.w_1484_f) + 10.0f, animatedY, totalWidth - (l_3370_o.u_1723_Y[16].n_1700_B(this.w_1484_f) + 9.0f), 13.0f, new Z_2491_A(1.0f, 1.0f, 3.0f, 3.0f), bgColorValue, animatedAlpha);
        float leftWidth = l_3370_o.u_1723_Y[16].n_1700_B(this.w_1484_f) + 9.0f;
        float glyphWidth = l_3370_o.u_1723_Y[16].n_1700_B(this.w_1484_f);
        float glyphDrawX = x + (leftWidth - glyphWidth) / 2.0f;
        float glyphDrawY = animatedY + (13.0f + l_3370_o.u_1723_Y[16].h_1847_R()) / 2.0f - 0.5f;
        l_3370_o.u_1723_Y[16].n_1700_B(matrixStack, this.w_1484_f, (double)glyphDrawX, (double)(animatedY + l_3370_o.G_564_y[16].h_1847_R() + 0.5f), iconColor);
        int textColorValue = (Integer)D_1410_T.w_1484_f.J_1907_R();
        l_3370_o.G_564_y[12].n_1700_B(matrixStack, this.n_1700_B.getString(), (double)((float)((int)x) + l_3370_o.u_1723_Y[16].n_1700_B(this.w_1484_f) + 13.0f), (double)(animatedY + l_3370_o.G_564_y[12].h_1847_R() + 1.5f), H_2506_c.n_1700_B(textColorValue, animatedAlpha));
    }

    @Generated
    public String s_956_w() {
        return this.w_1484_f;
    }

    @Generated
    public int u_2550_I() {
        return this.t_148_a;
    }

    @Generated
    public Integer M_588_G() {
        return this.s_956_w;
    }
}

