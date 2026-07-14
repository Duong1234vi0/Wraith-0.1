/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class FrameTimer {
    private final long[] n_1700_B = new long[240];
    private int J_1907_R;
    private int R_4764_Y;
    private int G_564_y;

    public void n_1700_B(long runningTime) {
        this.n_1700_B[this.G_564_y] = runningTime;
        ++this.G_564_y;
        if (this.G_564_y == 240) {
            this.G_564_y = 0;
        }
        if (this.R_4764_Y < 240) {
            this.J_1907_R = 0;
            ++this.R_4764_Y;
        } else {
            this.J_1907_R = this.n_1700_B(this.G_564_y + 1);
        }
    }

    public int n_1700_B(long valueIn, int scale, int divisor) {
        double d0 = (double)valueIn / (double)(1000000000L / (long)divisor);
        return (int)(d0 * (double)scale);
    }

    public int n_1700_B() {
        return this.J_1907_R;
    }

    public int J_1907_R() {
        return this.G_564_y;
    }

    public int n_1700_B(int rawIndex) {
        return rawIndex % 240;
    }

    public long[] R_4764_Y() {
        return this.n_1700_B;
    }
}


