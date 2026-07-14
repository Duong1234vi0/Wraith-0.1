/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import lightning.product.D_4792_h;
import lightning.product.o_3091_w;

public class VertexBuilderDummy
implements D_4792_h {
    private o_3091_w.n_1700_B renderTypeBuffer = null;

    public VertexBuilderDummy(o_3091_w.n_1700_B renderTypeBuffer) {
        this.renderTypeBuffer = renderTypeBuffer;
    }

    @Override
    public o_3091_w.n_1700_B getRenderTypeBuffer() {
        return this.renderTypeBuffer;
    }

    @Override
    public D_4792_h pos(double x, double y, double z) {
        return this;
    }

    @Override
    public D_4792_h color(int red, int green, int blue, int alpha) {
        return this;
    }

    @Override
    public D_4792_h tex(float u, float v) {
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
    public void endVertex() {
    }
}

