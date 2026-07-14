/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_4817_s;
import lightning.product.e_2866_D;

public class R_1815_U {
    public final float n_1700_B;
    public final float J_1907_R;
    public final boolean R_4764_Y;

    public R_1815_U(float widthIn, float heightIn, boolean fixedIn) {
        this.n_1700_B = widthIn;
        this.J_1907_R = heightIn;
        this.R_4764_Y = fixedIn;
    }

    public I_4817_s n_1700_B(e_2866_D p_242286_1_) {
        return this.n_1700_B(p_242286_1_.J_1907_R, p_242286_1_.R_4764_Y, p_242286_1_.G_564_y);
    }

    public I_4817_s n_1700_B(double p_242285_1_, double p_242285_3_, double p_242285_5_) {
        float f = this.n_1700_B / 2.0f;
        float f1 = this.J_1907_R;
        return new I_4817_s(p_242285_1_ - (double)f, p_242285_3_, p_242285_5_ - (double)f, p_242285_1_ + (double)f, p_242285_3_ + (double)f1, p_242285_5_ + (double)f);
    }

    public R_1815_U n_1700_B(float factor) {
        return this.n_1700_B(factor, factor);
    }

    public R_1815_U n_1700_B(float widthFactor, float heightFactor) {
        return !this.R_4764_Y && (widthFactor != 1.0f || heightFactor != 1.0f) ? R_1815_U.J_1907_R(this.n_1700_B * widthFactor, this.J_1907_R * heightFactor) : this;
    }

    public static R_1815_U J_1907_R(float widthIn, float heightIn) {
        return new R_1815_U(widthIn, heightIn, false);
    }

    public static R_1815_U R_4764_Y(float widthIn, float heightIn) {
        return new R_1815_U(widthIn, heightIn, true);
    }

    public String toString() {
        return "EntityDimensions w=" + this.n_1700_B + ", h=" + this.J_1907_R + ", fixed=" + this.R_4764_Y;
    }
}

