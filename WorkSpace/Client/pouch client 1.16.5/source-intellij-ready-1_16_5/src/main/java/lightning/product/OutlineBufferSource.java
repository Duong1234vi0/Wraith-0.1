/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Optional;
import lightning.product.D_3318_r;
import lightning.product.D_4792_h;
import lightning.product.T_3594_S;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.z_1333_t;

public class OutlineBufferSource
implements o_3091_w {
    private final o_3091_w.n_1700_B n_1700_B;
    private final o_3091_w.n_1700_B J_1907_R = o_3091_w.n_1700_B(new D_3318_r(256));
    private int R_4764_Y = 255;
    private int G_564_y = 255;
    private int P_1922_E = 255;
    private int u_1723_Y = 255;

    public OutlineBufferSource(o_3091_w.n_1700_B bufferIn) {
        this.n_1700_B = bufferIn;
    }

    @Override
    public D_4792_h getBuffer(o_2576_A p_getBuffer_1_) {
        if (p_getBuffer_1_.A_4115_X()) {
            D_4792_h ivertexbuilder2 = this.J_1907_R.getBuffer(p_getBuffer_1_);
            return new n_1700_B(ivertexbuilder2, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y);
        }
        D_4792_h ivertexbuilder = this.n_1700_B.getBuffer(p_getBuffer_1_);
        Optional<o_2576_A> optional = p_getBuffer_1_.H_2857_Y();
        if (optional.isPresent()) {
            D_4792_h ivertexbuilder1 = this.J_1907_R.getBuffer(optional.get());
            n_1700_B outlinelayerbuffer$coloredoutline = new n_1700_B(ivertexbuilder1, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y);
            return z_1333_t.n_1700_B(outlinelayerbuffer$coloredoutline, ivertexbuilder);
        }
        return ivertexbuilder;
    }

    public void n_1700_B(int redIn, int greenIn, int blueIn, int alphaIn) {
        this.R_4764_Y = redIn;
        this.G_564_y = greenIn;
        this.P_1922_E = blueIn;
        this.u_1723_Y = alphaIn;
    }

    public void J_1907_R() {
        this.J_1907_R.J_1907_R();
    }

    static class n_1700_B
    extends T_3594_S {
        private final D_4792_h t_148_a;
        private double s_956_w;
        private double u_2550_I;
        private double M_588_G;
        private float P_4830_p;
        private float h_1847_R;

        private n_1700_B(D_4792_h bufferIn, int red, int green, int blue, int alpha) {
            this.t_148_a = bufferIn;
            super.n_1700_B(red, green, blue, alpha);
        }

        @Override
        public void n_1700_B(int red, int green, int blue, int alpha) {
        }

        @Override
        public D_4792_h pos(double x, double y, double z) {
            this.s_956_w = x;
            this.u_2550_I = y;
            this.M_588_G = z;
            return this;
        }

        @Override
        public D_4792_h color(int red, int green, int blue, int alpha) {
            return this;
        }

        @Override
        public D_4792_h tex(float u, float v) {
            this.P_4830_p = u;
            this.h_1847_R = v;
            return this;
        }

        @Override
        public D_4792_h overlay(int u, int v) {
            return this;
        }

        @Override
        public D_4792_h lightmap(int u, int v) {
            return this;
        }

        @Override
        public D_4792_h normal(float x, float y, float z) {
            return this;
        }

        @Override
        public void n_1700_B(float x, float y, float z, float red, float green, float blue, float alpha, float texU, float texV, int overlayUV, int lightmapUV, float normalX, float normalY, float normalZ) {
            this.t_148_a.pos(x, y, z).color(this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E).tex(texU, texV).endVertex();
        }

        @Override
        public void endVertex() {
            this.t_148_a.pos(this.s_956_w, this.u_2550_I, this.M_588_G).color(this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E).tex(this.P_4830_p, this.h_1847_R).endVertex();
        }
    }
}


