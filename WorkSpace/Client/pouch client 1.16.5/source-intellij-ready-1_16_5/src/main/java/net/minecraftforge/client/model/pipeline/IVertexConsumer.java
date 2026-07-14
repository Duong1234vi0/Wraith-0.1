/*
 * Decompiled with CFR 0.152.
 */
package net.minecraftforge.client.model.pipeline;

import lightning.product.b_1213_w;
import lightning.product.b_257_Y;

public interface IVertexConsumer {
    public b_1213_w getVertexFormat();

    public void setQuadTint(int var1);

    public void setQuadOrientation(b_257_Y var1);

    public void setQuadColored();

    public void put(int var1, float ... var2);
}

