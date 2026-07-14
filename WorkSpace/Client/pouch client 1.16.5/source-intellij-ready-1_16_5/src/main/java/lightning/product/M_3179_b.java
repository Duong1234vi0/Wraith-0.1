/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1436_R;

public class M_3179_b {
    private D_1436_R[] n_1700_B = new D_1436_R[128];
    private int J_1907_R;

    public D_1436_R n_1700_B(D_1436_R point) {
        if (point.G_564_y >= 0) {
            throw new IllegalStateException("OW KNOWS!");
        }
        if (this.J_1907_R == this.n_1700_B.length) {
            D_1436_R[] apathpoint = new D_1436_R[this.J_1907_R << 1];
            System.arraycopy(this.n_1700_B, 0, apathpoint, 0, this.J_1907_R);
            this.n_1700_B = apathpoint;
        }
        this.n_1700_B[this.J_1907_R] = point;
        point.G_564_y = this.J_1907_R;
        this.n_1700_B(this.J_1907_R++);
        return point;
    }

    public void n_1700_B() {
        this.J_1907_R = 0;
    }

    public D_1436_R J_1907_R() {
        D_1436_R pathpoint = this.n_1700_B[0];
        this.n_1700_B[0] = this.n_1700_B[--this.J_1907_R];
        this.n_1700_B[this.J_1907_R] = null;
        if (this.J_1907_R > 0) {
            this.J_1907_R(0);
        }
        pathpoint.G_564_y = -1;
        return pathpoint;
    }

    public void n_1700_B(D_1436_R point, float distance) {
        float f = point.v_4262_N;
        point.v_4262_N = distance;
        if (distance < f) {
            this.n_1700_B(point.G_564_y);
        } else {
            this.J_1907_R(point.G_564_y);
        }
    }

    private void n_1700_B(int index) {
        D_1436_R pathpoint = this.n_1700_B[index];
        float f = pathpoint.v_4262_N;
        while (index > 0) {
            int i = index - 1 >> 1;
            D_1436_R pathpoint1 = this.n_1700_B[i];
            if (!(f < pathpoint1.v_4262_N)) break;
            this.n_1700_B[index] = pathpoint1;
            pathpoint1.G_564_y = index;
            index = i;
        }
        this.n_1700_B[index] = pathpoint;
        pathpoint.G_564_y = index;
    }

    private void J_1907_R(int index) {
        D_1436_R pathpoint = this.n_1700_B[index];
        float f = pathpoint.v_4262_N;
        while (true) {
            float f2;
            D_1436_R pathpoint2;
            int i = 1 + (index << 1);
            int j = i + 1;
            if (i >= this.J_1907_R) break;
            D_1436_R pathpoint1 = this.n_1700_B[i];
            float f1 = pathpoint1.v_4262_N;
            if (j >= this.J_1907_R) {
                pathpoint2 = null;
                f2 = Float.POSITIVE_INFINITY;
            } else {
                pathpoint2 = this.n_1700_B[j];
                f2 = pathpoint2.v_4262_N;
            }
            if (f1 < f2) {
                if (!(f1 < f)) break;
                this.n_1700_B[index] = pathpoint1;
                pathpoint1.G_564_y = index;
                index = i;
                continue;
            }
            if (!(f2 < f)) break;
            this.n_1700_B[index] = pathpoint2;
            pathpoint2.G_564_y = index;
            index = j;
        }
        this.n_1700_B[index] = pathpoint;
        pathpoint.G_564_y = index;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R == 0;
    }
}

