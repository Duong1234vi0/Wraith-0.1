/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.Validate
 */
package lightning.product;

import lightning.product.u_530_F;
import org.apache.commons.lang3.Validate;

public class A_1434_p {
    private final long[] n_1700_B;
    private final int J_1907_R;
    private final long R_4764_Y;
    private final int G_564_y;

    public A_1434_p(int p_i231442_1_, int p_i231442_2_) {
        this(p_i231442_1_, p_i231442_2_, new long[u_530_F.R_4764_Y(p_i231442_2_ * p_i231442_1_, 64) / 64]);
    }

    public A_1434_p(int p_i231443_1_, int p_i231443_2_, long[] p_i231443_3_) {
        Validate.inclusiveBetween((long)1L, (long)32L, (long)p_i231443_1_);
        this.G_564_y = p_i231443_2_;
        this.J_1907_R = p_i231443_1_;
        this.n_1700_B = p_i231443_3_;
        this.R_4764_Y = (1L << p_i231443_1_) - 1L;
        int i = u_530_F.R_4764_Y(p_i231443_2_ * p_i231443_1_, 64) / 64;
        if (p_i231443_3_.length != i) {
            throw new IllegalArgumentException("Invalid length given for storage, got: " + p_i231443_3_.length + " but expected: " + i);
        }
    }

    public void n_1700_B(int p_233049_1_, int p_233049_2_) {
        Validate.inclusiveBetween((long)0L, (long)(this.G_564_y - 1), (long)p_233049_1_);
        Validate.inclusiveBetween((long)0L, (long)this.R_4764_Y, (long)p_233049_2_);
        int i = p_233049_1_ * this.J_1907_R;
        int j = i >> 6;
        int k = (p_233049_1_ + 1) * this.J_1907_R - 1 >> 6;
        int l = i ^ j << 6;
        this.n_1700_B[j] = this.n_1700_B[j] & (this.R_4764_Y << l ^ 0xFFFFFFFFFFFFFFFFL) | ((long)p_233049_2_ & this.R_4764_Y) << l;
        if (j != k) {
            int i1 = 64 - l;
            int j1 = this.J_1907_R - i1;
            this.n_1700_B[k] = this.n_1700_B[k] >>> j1 << j1 | ((long)p_233049_2_ & this.R_4764_Y) >> i1;
        }
    }

    public int n_1700_B(int p_233048_1_) {
        Validate.inclusiveBetween((long)0L, (long)(this.G_564_y - 1), (long)p_233048_1_);
        int i = p_233048_1_ * this.J_1907_R;
        int j = i >> 6;
        int k = (p_233048_1_ + 1) * this.J_1907_R - 1 >> 6;
        int l = i ^ j << 6;
        if (j == k) {
            return (int)(this.n_1700_B[j] >>> l & this.R_4764_Y);
        }
        int i1 = 64 - l;
        return (int)((this.n_1700_B[j] >>> l | this.n_1700_B[k] << i1) & this.R_4764_Y);
    }

    public long[] n_1700_B() {
        return this.n_1700_B;
    }

    public int J_1907_R() {
        return this.J_1907_R;
    }
}

