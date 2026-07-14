/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;
import net.optifine.render.VertexBuilderWrapper;

public class z_1333_t {
    public static D_4792_h n_1700_B(D_4792_h vertexBuilder, D_4792_h delegateBuilder) {
        return new n_1700_B(vertexBuilder, delegateBuilder);
    }

    static class n_1700_B
    extends VertexBuilderWrapper
    implements D_4792_h {
        private final D_4792_h n_1700_B;
        private final D_4792_h J_1907_R;
        private boolean R_4764_Y;

        public n_1700_B(D_4792_h vertexBuilder, D_4792_h delegateBuilder) {
            super(delegateBuilder);
            if (vertexBuilder == delegateBuilder) {
                throw new IllegalArgumentException("Duplicate delegates");
            }
            this.n_1700_B = vertexBuilder;
            this.J_1907_R = delegateBuilder;
            this.J_1907_R();
        }

        @Override
        public D_4792_h pos(double x, double y, double z) {
            this.n_1700_B.pos(x, y, z);
            this.J_1907_R.pos(x, y, z);
            return this;
        }

        @Override
        public D_4792_h color(int red, int green, int blue, int alpha) {
            this.n_1700_B.color(red, green, blue, alpha);
            this.J_1907_R.color(red, green, blue, alpha);
            return this;
        }

        @Override
        public D_4792_h tex(float u, float v) {
            this.n_1700_B.tex(u, v);
            this.J_1907_R.tex(u, v);
            return this;
        }

        @Override
        public D_4792_h overlay(int u, int v) {
            this.n_1700_B.overlay(u, v);
            this.J_1907_R.overlay(u, v);
            return this;
        }

        @Override
        public D_4792_h lightmap(int u, int v) {
            this.n_1700_B.lightmap(u, v);
            this.J_1907_R.lightmap(u, v);
            return this;
        }

        @Override
        public D_4792_h normal(float x, float y, float z) {
            this.n_1700_B.normal(x, y, z);
            this.J_1907_R.normal(x, y, z);
            return this;
        }

        @Override
        public void n_1700_B(float x, float y, float z, float red, float green, float blue, float alpha, float texU, float texV, int overlayUV, int lightmapUV, float normalX, float normalY, float normalZ) {
            if (this.R_4764_Y) {
                this.n_1700_B.n_1700_B(x, y, z, red, green, blue, alpha, texU / 32.0f, texV / 32.0f, overlayUV, lightmapUV, normalX, normalY, normalZ);
            } else {
                this.n_1700_B.n_1700_B(x, y, z, red, green, blue, alpha, texU, texV, overlayUV, lightmapUV, normalX, normalY, normalZ);
            }
            this.J_1907_R.n_1700_B(x, y, z, red, green, blue, alpha, texU, texV, overlayUV, lightmapUV, normalX, normalY, normalZ);
        }

        @Override
        public void endVertex() {
            this.n_1700_B.endVertex();
            this.J_1907_R.endVertex();
        }

        @Override
        public void setRenderBlocks(boolean p_setRenderBlocks_1_) {
            super.setRenderBlocks(p_setRenderBlocks_1_);
            this.J_1907_R();
        }

        private void J_1907_R() {
            this.R_4764_Y = !this.n_1700_B.isMultiTexture() && this.J_1907_R.isMultiTexture();
        }

        @Override
        public D_4792_h n_1700_B() {
            return this.n_1700_B;
        }
    }
}

