/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import lightning.product.I_4817_s;

public interface ICamera {
    public void setCameraPosition(double var1, double var3, double var5);

    public boolean isBoundingBoxInFrustum(I_4817_s var1);

    public boolean isBoxInFrustumFully(double var1, double var3, double var5, double var7, double var9, double var11);
}

