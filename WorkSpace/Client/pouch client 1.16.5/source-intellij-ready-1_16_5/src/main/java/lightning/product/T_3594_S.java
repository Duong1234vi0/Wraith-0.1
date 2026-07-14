/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4792_h;

public abstract class T_3594_S
implements D_4792_h {
    protected boolean n_1700_B = false;
    protected int J_1907_R = 255;
    protected int R_4764_Y = 255;
    protected int G_564_y = 255;
    protected int P_1922_E = 255;

    public void n_1700_B(int red, int green, int blue, int alpha) {
        this.J_1907_R = red;
        this.R_4764_Y = green;
        this.G_564_y = blue;
        this.P_1922_E = alpha;
        this.n_1700_B = true;
    }
}

