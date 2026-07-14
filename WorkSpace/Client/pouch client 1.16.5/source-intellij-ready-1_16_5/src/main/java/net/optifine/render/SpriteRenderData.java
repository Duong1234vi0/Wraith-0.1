/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import lightning.product.B_3871_I;

public class SpriteRenderData {
    private B_3871_I sprite;
    private int[] positions;
    private int[] counts;

    public SpriteRenderData(B_3871_I sprite, int[] positions, int[] counts) {
        this.sprite = sprite;
        this.positions = positions;
        this.counts = counts;
        if (positions.length != counts.length) {
            throw new IllegalArgumentException(positions.length + " != " + counts.length);
        }
    }

    public B_3871_I getSprite() {
        return this.sprite;
    }

    public int[] getPositions() {
        return this.positions;
    }

    public int[] getCounts() {
        return this.counts;
    }

    public String toString() {
        return String.valueOf(this.sprite.s_956_w()) + ", " + this.positions.length;
    }
}

