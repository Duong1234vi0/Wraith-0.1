/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.J_2133_L;
import lightning.product.N_1972_P;
import lightning.product.ResourceManager;
import lightning.product.c_4477_a;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;
import lightning.product.o_2576_A;
import lightning.product.s_3940_w;

public class FontTexture
extends c_4477_a {
    private final g_2336_b n_1700_B;
    private final o_2576_A J_1907_R;
    private final o_2576_A R_4764_Y;
    private final boolean G_564_y;
    private final n_1700_B P_1922_E;

    public FontTexture(g_2336_b resourceLocationIn, boolean coloredIn) {
        this.n_1700_B = resourceLocationIn;
        this.G_564_y = coloredIn;
        this.P_1922_E = new n_1700_B(0, 0, 256, 256);
        N_1972_P.n_1700_B(coloredIn ? i_2518_W.J_1907_R.n_1700_B : i_2518_W.J_1907_R.P_1922_E, this.getGlTextureId(), 256, 256);
        this.J_1907_R = o_2576_A.M_182_A(resourceLocationIn);
        this.R_4764_Y = o_2576_A.t_1786_h(resourceLocationIn);
    }

    @Override
    public void loadTexture(ResourceManager manager) {
    }

    @Override
    public void close() {
        this.deleteGlTexture();
    }

    @Nullable
    public J_2133_L n_1700_B(s_3940_w glyphInfoIn) {
        if (glyphInfoIn.G_564_y() != this.G_564_y) {
            return null;
        }
        n_1700_B fonttexture$entry = this.P_1922_E.n_1700_B(glyphInfoIn);
        if (fonttexture$entry != null) {
            this.bindTexture();
            glyphInfoIn.n_1700_B(fonttexture$entry.n_1700_B, fonttexture$entry.J_1907_R);
            float f = 256.0f;
            float f1 = 256.0f;
            float f2 = 0.01f;
            return new J_2133_L(this.J_1907_R, this.R_4764_Y, ((float)fonttexture$entry.n_1700_B + 0.01f) / 256.0f, ((float)fonttexture$entry.n_1700_B - 0.01f + (float)glyphInfoIn.n_1700_B()) / 256.0f, ((float)fonttexture$entry.J_1907_R + 0.01f) / 256.0f, ((float)fonttexture$entry.J_1907_R - 0.01f + (float)glyphInfoIn.J_1907_R()) / 256.0f, glyphInfoIn.w_1484_f(), glyphInfoIn.t_148_a(), glyphInfoIn.s_956_w(), glyphInfoIn.u_2550_I());
        }
        return null;
    }

    public g_2336_b n_1700_B() {
        return this.n_1700_B;
    }

    static class n_1700_B {
        private final int n_1700_B;
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private n_1700_B P_1922_E;
        private n_1700_B u_1723_Y;
        private boolean v_4262_N;

        private n_1700_B(int p_i49711_1_, int p_i49711_2_, int p_i49711_3_, int p_i49711_4_) {
            this.n_1700_B = p_i49711_1_;
            this.J_1907_R = p_i49711_2_;
            this.R_4764_Y = p_i49711_3_;
            this.G_564_y = p_i49711_4_;
        }

        @Nullable
        n_1700_B n_1700_B(s_3940_w p_211224_1_) {
            if (this.P_1922_E != null && this.u_1723_Y != null) {
                n_1700_B fonttexture$entry = this.P_1922_E.n_1700_B(p_211224_1_);
                if (fonttexture$entry == null) {
                    fonttexture$entry = this.u_1723_Y.n_1700_B(p_211224_1_);
                }
                return fonttexture$entry;
            }
            if (this.v_4262_N) {
                return null;
            }
            int i = p_211224_1_.n_1700_B();
            int j = p_211224_1_.J_1907_R();
            if (i <= this.R_4764_Y && j <= this.G_564_y) {
                if (i == this.R_4764_Y && j == this.G_564_y) {
                    this.v_4262_N = true;
                    return this;
                }
                int k = this.R_4764_Y - i;
                int l = this.G_564_y - j;
                if (k > l) {
                    this.P_1922_E = new n_1700_B(this.n_1700_B, this.J_1907_R, i, this.G_564_y);
                    this.u_1723_Y = new n_1700_B(this.n_1700_B + i + 1, this.J_1907_R, this.R_4764_Y - i - 1, this.G_564_y);
                } else {
                    this.P_1922_E = new n_1700_B(this.n_1700_B, this.J_1907_R, this.R_4764_Y, j);
                    this.u_1723_Y = new n_1700_B(this.n_1700_B, this.J_1907_R + j + 1, this.R_4764_Y, this.G_564_y - j - 1);
                }
                return this.P_1922_E.n_1700_B(p_211224_1_);
            }
            return null;
        }
    }
}


