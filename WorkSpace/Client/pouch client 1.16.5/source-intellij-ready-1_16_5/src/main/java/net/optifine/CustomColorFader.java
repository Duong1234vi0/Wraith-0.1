/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import lightning.product.e_2866_D;
import net.optifine.Config;

public class CustomColorFader {
    private e_2866_D color = null;
    private long timeUpdate = System.currentTimeMillis();

    public e_2866_D getColor(double x, double y, double z) {
        if (this.color == null) {
            this.color = new e_2866_D(x, y, z);
            return this.color;
        }
        long i = System.currentTimeMillis();
        long j = i - this.timeUpdate;
        if (j == 0L) {
            return this.color;
        }
        this.timeUpdate = i;
        if (Math.abs(x - this.color.J_1907_R) < 0.004 && Math.abs(y - this.color.R_4764_Y) < 0.004 && Math.abs(z - this.color.G_564_y) < 0.004) {
            return this.color;
        }
        double d0 = (double)j * 0.001;
        d0 = Config.limit(d0, 0.0, 1.0);
        double d1 = x - this.color.J_1907_R;
        double d2 = y - this.color.R_4764_Y;
        double d3 = z - this.color.G_564_y;
        double d4 = this.color.J_1907_R + d1 * d0;
        double d5 = this.color.R_4764_Y + d2 * d0;
        double d6 = this.color.G_564_y + d3 * d0;
        this.color = new e_2866_D(d4, d5, d6);
        return this.color;
    }
}

