/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_3871_I;
import lightning.product.D_4792_h;
import net.optifine.SmartAnimations;
import net.optifine.render.VertexBuilderWrapper;

public class SpriteCoordinateExpander
extends VertexBuilderWrapper
implements D_4792_h {
    private final D_4792_h n_1700_B;
    private final B_3871_I J_1907_R;

    public SpriteCoordinateExpander(D_4792_h bufferIn, B_3871_I spriteIn) {
        super(bufferIn);
        if (SmartAnimations.isActive()) {
            SmartAnimations.spriteRendered(spriteIn);
        }
        this.n_1700_B = bufferIn;
        this.J_1907_R = spriteIn;
    }

    @Override
    public D_4792_h pos(double x, double y, double z) {
        return this.n_1700_B.pos(x, y, z);
    }

    @Override
    public D_4792_h color(int red, int green, int blue, int alpha) {
        return this.n_1700_B.color(red, green, blue, alpha);
    }

    @Override
    public D_4792_h tex(float u, float v) {
        return this.n_1700_B.tex(this.J_1907_R.n_1700_B((double)(u * 16.0f)), this.J_1907_R.J_1907_R((double)(v * 16.0f)));
    }

    @Override
    public D_4792_h overlay(int u, int v) {
        return this.n_1700_B.overlay(u, v);
    }

    @Override
    public D_4792_h lightmap(int u, int v) {
        return this.n_1700_B.lightmap(u, v);
    }

    @Override
    public D_4792_h normal(float x, float y, float z) {
        return this.n_1700_B.normal(x, y, z);
    }

    @Override
    public void endVertex() {
        this.n_1700_B.endVertex();
    }

    @Override
    public void n_1700_B(float x, float y, float z, float red, float green, float blue, float alpha, float texU, float texV, int overlayUV, int lightmapUV, float normalX, float normalY, float normalZ) {
        this.n_1700_B.n_1700_B(x, y, z, red, green, blue, alpha, this.J_1907_R.n_1700_B((double)(texU * 16.0f)), this.J_1907_R.J_1907_R((double)(texV * 16.0f)), overlayUV, lightmapUV, normalX, normalY, normalZ);
    }
}


