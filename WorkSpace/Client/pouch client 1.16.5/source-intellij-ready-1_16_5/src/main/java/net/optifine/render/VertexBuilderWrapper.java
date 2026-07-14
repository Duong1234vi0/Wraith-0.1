/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import lightning.product.B_3871_I;
import lightning.product.D_4792_h;
import lightning.product.M_1336_P;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import net.optifine.render.VertexPosition;

public abstract class VertexBuilderWrapper
implements D_4792_h {
    private D_4792_h vertexBuilder;

    public VertexBuilderWrapper(D_4792_h vertexBuilder) {
        this.vertexBuilder = vertexBuilder;
    }

    public D_4792_h getVertexBuilder() {
        return this.vertexBuilder;
    }

    @Override
    public void putSprite(B_3871_I sprite) {
        this.vertexBuilder.putSprite(sprite);
    }

    @Override
    public void setSprite(B_3871_I sprite) {
        this.vertexBuilder.setSprite(sprite);
    }

    @Override
    public boolean isMultiTexture() {
        return this.vertexBuilder.isMultiTexture();
    }

    @Override
    public void setRenderType(o_2576_A renderType) {
        this.vertexBuilder.setRenderType(renderType);
    }

    @Override
    public o_2576_A getRenderType() {
        return this.vertexBuilder.getRenderType();
    }

    @Override
    public void setRenderBlocks(boolean renderBlocks) {
        this.vertexBuilder.setRenderBlocks(renderBlocks);
    }

    @Override
    public M_1336_P getTempVec3f(M_1336_P vec) {
        return this.vertexBuilder.getTempVec3f(vec);
    }

    @Override
    public M_1336_P getTempVec3f(float x, float y, float z) {
        return this.vertexBuilder.getTempVec3f(x, y, z);
    }

    @Override
    public float[] getTempFloat4(float f1, float f2, float f3, float f4) {
        return this.vertexBuilder.getTempFloat4(f1, f2, f3, f4);
    }

    @Override
    public int[] getTempInt4(int i1, int i2, int i3, int i4) {
        return this.vertexBuilder.getTempInt4(i1, i2, i3, i4);
    }

    @Override
    public o_3091_w.n_1700_B getRenderTypeBuffer() {
        return this.vertexBuilder.getRenderTypeBuffer();
    }

    @Override
    public void setQuadVertexPositions(VertexPosition[] vps) {
        this.vertexBuilder.setQuadVertexPositions(vps);
    }

    @Override
    public void setMidBlock(float mbx, float mby, float mbz) {
        this.vertexBuilder.setMidBlock(mbx, mby, mbz);
    }
}

