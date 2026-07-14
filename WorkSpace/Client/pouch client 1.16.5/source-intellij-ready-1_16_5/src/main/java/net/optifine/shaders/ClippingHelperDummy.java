/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders;

import lightning.product.D_1098_v;
import lightning.product.E_4918_z;
import lightning.product.I_4817_s;

public class ClippingHelperDummy
extends E_4918_z {
    public ClippingHelperDummy() {
        super(new D_1098_v(), new D_1098_v());
    }

    @Override
    public boolean isBoundingBoxInFrustum(I_4817_s aabbIn) {
        return true;
    }
}

