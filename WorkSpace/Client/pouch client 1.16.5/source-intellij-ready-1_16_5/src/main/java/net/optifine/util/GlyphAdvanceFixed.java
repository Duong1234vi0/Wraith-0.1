/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import lightning.product.y_3462_t;

public class GlyphAdvanceFixed
implements y_3462_t {
    private float advanceWidth;

    public GlyphAdvanceFixed(float advanceWidth) {
        this.advanceWidth = advanceWidth;
    }

    @Override
    public float getAdvance() {
        return this.advanceWidth;
    }
}

