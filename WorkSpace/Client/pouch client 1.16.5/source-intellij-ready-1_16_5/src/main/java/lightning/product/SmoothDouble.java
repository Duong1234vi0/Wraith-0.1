/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.u_530_F;

public class SmoothDouble {
    private double n_1700_B;
    private double J_1907_R;
    private double R_4764_Y;

    public double n_1700_B(double p_199102_1_, double p_199102_3_) {
        this.n_1700_B += p_199102_1_;
        double d0 = this.n_1700_B - this.J_1907_R;
        double d1 = u_530_F.G_564_y(0.5, this.R_4764_Y, d0);
        double d2 = Math.signum(d0);
        if (d2 * d0 > d2 * this.R_4764_Y) {
            d0 = d1;
        }
        this.R_4764_Y = d1;
        this.J_1907_R += d0 * p_199102_3_;
        return d0 * p_199102_3_;
    }

    public void n_1700_B() {
        this.n_1700_B = 0.0;
        this.J_1907_R = 0.0;
        this.R_4764_Y = 0.0;
    }
}


