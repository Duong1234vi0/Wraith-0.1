/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class RunningTrimmedMean {
    private final long[] n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;

    public RunningTrimmedMean(int sizeIn) {
        this.n_1700_B = new long[sizeIn];
    }

    public long n_1700_B(long valueIn) {
        if (this.J_1907_R < this.n_1700_B.length) {
            ++this.J_1907_R;
        }
        this.n_1700_B[this.R_4764_Y] = valueIn;
        this.R_4764_Y = (this.R_4764_Y + 1) % this.n_1700_B.length;
        long i = Long.MAX_VALUE;
        long j = Long.MIN_VALUE;
        long k = 0L;
        for (int l = 0; l < this.J_1907_R; ++l) {
            long i1 = this.n_1700_B[l];
            k += i1;
            i = Math.min(i, i1);
            j = Math.max(j, i1);
        }
        if (this.J_1907_R > 2) {
            return (k -= i + j) / (long)(this.J_1907_R - 2);
        }
        return k > 0L ? (long)this.J_1907_R / k : 0L;
    }
}


