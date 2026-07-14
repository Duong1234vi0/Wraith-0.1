/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.D_4792_h;
import lightning.product.o_2576_A;
import net.optifine.util.MathUtils;

public class J_2133_L {
    private final o_2576_A J_1907_R;
    private final o_2576_A R_4764_Y;
    private final float G_564_y;
    private final float P_1922_E;
    private final float u_1723_Y;
    private final float v_4262_N;
    private final float w_1484_f;
    private final float t_148_a;
    private final float s_956_w;
    private final float u_2550_I;
    public static final D_1098_v n_1700_B = MathUtils.makeMatrixIdentity();

    public J_2133_L(o_2576_A normalType, o_2576_A seeThroughType, float u0, float u1, float v0, float v1, float minX, float maxX, float minY, float maxY) {
        this.J_1907_R = normalType;
        this.R_4764_Y = seeThroughType;
        this.G_564_y = u0;
        this.P_1922_E = u1;
        this.u_1723_Y = v0;
        this.v_4262_N = v1;
        this.w_1484_f = minX;
        this.t_148_a = maxX;
        this.s_956_w = minY;
        this.u_2550_I = maxY;
    }

    public void n_1700_B(boolean italicIn, float xIn, float yIn, D_1098_v matrixIn, D_4792_h bufferIn, float redIn, float greenIn, float blueIn, float alphaIn, int packedLight) {
        float f7;
        int i = 3;
        float f = xIn + this.w_1484_f;
        float f1 = xIn + this.t_148_a;
        float f2 = this.s_956_w - 3.0f;
        float f3 = this.u_2550_I - 3.0f;
        float f4 = yIn + f2;
        float f5 = yIn + f3;
        float f6 = italicIn ? 1.0f - 0.25f * f2 : 0.0f;
        float f8 = f7 = italicIn ? 1.0f - 0.25f * f3 : 0.0f;
        if (bufferIn instanceof D_3318_r && matrixIn == n_1700_B) {
            D_3318_r bufferbuilder = (D_3318_r)bufferIn;
            int j = (int)(redIn * 255.0f);
            int k = (int)(greenIn * 255.0f);
            int l = (int)(blueIn * 255.0f);
            int i1 = (int)(alphaIn * 255.0f);
            int j1 = packedLight & 0xFFFF;
            int k1 = packedLight >> 16 & 0xFFFF;
            bufferbuilder.n_1700_B(f + f6, f4, 0.0f, j, k, l, i1, this.G_564_y, this.u_1723_Y, j1, k1);
            bufferbuilder.n_1700_B(f + f7, f5, 0.0f, j, k, l, i1, this.G_564_y, this.v_4262_N, j1, k1);
            bufferbuilder.n_1700_B(f1 + f7, f5, 0.0f, j, k, l, i1, this.P_1922_E, this.v_4262_N, j1, k1);
            bufferbuilder.n_1700_B(f1 + f6, f4, 0.0f, j, k, l, i1, this.P_1922_E, this.u_1723_Y, j1, k1);
        } else {
            bufferIn.n_1700_B(matrixIn, f + f6, f4, 0.0f).n_1700_B(redIn, greenIn, blueIn, alphaIn).tex(this.G_564_y, this.u_1723_Y).J_1907_R(packedLight).endVertex();
            bufferIn.n_1700_B(matrixIn, f + f7, f5, 0.0f).n_1700_B(redIn, greenIn, blueIn, alphaIn).tex(this.G_564_y, this.v_4262_N).J_1907_R(packedLight).endVertex();
            bufferIn.n_1700_B(matrixIn, f1 + f7, f5, 0.0f).n_1700_B(redIn, greenIn, blueIn, alphaIn).tex(this.P_1922_E, this.v_4262_N).J_1907_R(packedLight).endVertex();
            bufferIn.n_1700_B(matrixIn, f1 + f6, f4, 0.0f).n_1700_B(redIn, greenIn, blueIn, alphaIn).tex(this.P_1922_E, this.u_1723_Y).J_1907_R(packedLight).endVertex();
        }
    }

    public void n_1700_B(n_1700_B effectIn, D_1098_v matrixIn, D_4792_h bufferIn, int packedLightIn) {
        bufferIn.n_1700_B(matrixIn, effectIn.n_1700_B, effectIn.J_1907_R, effectIn.P_1922_E).n_1700_B(effectIn.u_1723_Y, effectIn.v_4262_N, effectIn.w_1484_f, effectIn.t_148_a).tex(this.G_564_y, this.u_1723_Y).J_1907_R(packedLightIn).endVertex();
        bufferIn.n_1700_B(matrixIn, effectIn.R_4764_Y, effectIn.J_1907_R, effectIn.P_1922_E).n_1700_B(effectIn.u_1723_Y, effectIn.v_4262_N, effectIn.w_1484_f, effectIn.t_148_a).tex(this.G_564_y, this.v_4262_N).J_1907_R(packedLightIn).endVertex();
        bufferIn.n_1700_B(matrixIn, effectIn.R_4764_Y, effectIn.G_564_y, effectIn.P_1922_E).n_1700_B(effectIn.u_1723_Y, effectIn.v_4262_N, effectIn.w_1484_f, effectIn.t_148_a).tex(this.P_1922_E, this.v_4262_N).J_1907_R(packedLightIn).endVertex();
        bufferIn.n_1700_B(matrixIn, effectIn.n_1700_B, effectIn.G_564_y, effectIn.P_1922_E).n_1700_B(effectIn.u_1723_Y, effectIn.v_4262_N, effectIn.w_1484_f, effectIn.t_148_a).tex(this.P_1922_E, this.u_1723_Y).J_1907_R(packedLightIn).endVertex();
    }

    public o_2576_A n_1700_B(boolean seeThroughIn) {
        return seeThroughIn ? this.R_4764_Y : this.J_1907_R;
    }

    public static class n_1700_B {
        protected final float n_1700_B;
        protected final float J_1907_R;
        protected final float R_4764_Y;
        protected final float G_564_y;
        protected final float P_1922_E;
        protected final float u_1723_Y;
        protected final float v_4262_N;
        protected final float w_1484_f;
        protected final float t_148_a;

        public n_1700_B(float x0, float y0, float x1, float y1, float depth, float red, float green, float blue, float alpha) {
            this.n_1700_B = x0;
            this.J_1907_R = y0;
            this.R_4764_Y = x1;
            this.G_564_y = y1;
            this.P_1922_E = depth;
            this.u_1723_Y = red;
            this.v_4262_N = green;
            this.w_1484_f = blue;
            this.t_148_a = alpha;
        }
    }
}

