/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1410_T;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.Z_2491_A;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.l_3370_o;
import lightning.product.o_4117_e;
import lightning.product.x_282_a;

public class A_1306_N
extends o_4117_e {
    private static final float w_1484_f = 8.0f;
    private static final float t_148_a = 4.0f;
    private static final float s_956_w = 16.0f;
    private final g_2336_b u_2550_I;
    private final Integer M_588_G;

    public A_1306_N(g_2336_b image, x_282_a message) {
        super(message);
        this.u_2550_I = image;
        this.M_588_G = null;
    }

    public A_1306_N(g_2336_b image, x_282_a message, int overrideColor) {
        super(message);
        this.u_2550_I = image;
        this.M_588_G = overrideColor;
    }

    @Override
    public void n_1700_B(float x, float y, g_221_o matrixStack) {
        this.n_1700_B(y);
        boolean expired = this.n_1700_B();
        this.P_1922_E.n_1700_B(expired ? 0.0f : 1.0f);
        this.G_564_y.n_1700_B(this.u_1723_Y && expired ? y - 2.0f : y);
        float animatedY = this.G_564_y.n_1700_B();
        float animatedAlpha = this.P_1922_E.n_1700_B();
        float textWidth = l_3370_o.G_564_y[12].n_1700_B(this.n_1700_B.getString());
        float iconWidth = 16.0f;
        float textRectWidth = textWidth + 5.0f;
        int glow = (Integer)D_1410_T.s_956_w.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        F_489_x.n_1700_B(x - 10.0f, animatedY - 10.0f, iconWidth + 20.0f, 33.0f, 3.0f, glow, glow, glow, glow, glowAlpha * animatedAlpha, 10.0f);
        F_489_x.n_1700_B(x + iconWidth + 1.0f - 10.0f, animatedY - 10.0f, textRectWidth + 20.0f, 33.0f, 3.0f, glow, glow, glow, glow, glowAlpha * animatedAlpha, 10.0f);
        int bgColorValue = (Integer)D_1410_T.v_4262_N.J_1907_R();
        F_489_x.n_1700_B(x, animatedY, iconWidth, 13.0f, new Z_2491_A(3.0f, 3.0f, 1.0f, 1.0f), bgColorValue, animatedAlpha);
        F_489_x.n_1700_B(x + iconWidth + 1.0f, animatedY, textRectWidth, 13.0f, new Z_2491_A(1.0f, 1.0f, 3.0f, 3.0f), bgColorValue, animatedAlpha);
        float offsetX = x + 4.0f;
        float offsetY = animatedY + 2.5f;
        if (this.M_588_G != null && this.u_2550_I.J_1907_R().contains("potion")) {
            int colored = H_2506_c.n_1700_B((int)this.M_588_G, animatedAlpha);
            F_489_x.n_1700_B(colored, offsetX, offsetY, 8.0f, 8.0f);
        }
        F_489_x.n_1700_B(this.u_2550_I, offsetX, offsetY, 8.0f, 8.0f, H_2506_c.n_1700_B(-1, animatedAlpha));
        int textColorValue = (Integer)D_1410_T.w_1484_f.J_1907_R();
        l_3370_o.G_564_y[12].n_1700_B(matrixStack, this.n_1700_B, (double)((float)((int)x) + iconWidth + 3.0f), (double)(animatedY + l_3370_o.G_564_y[12].h_1847_R() + 1.5f), H_2506_c.n_1700_B(textColorValue, animatedAlpha));
    }
}

