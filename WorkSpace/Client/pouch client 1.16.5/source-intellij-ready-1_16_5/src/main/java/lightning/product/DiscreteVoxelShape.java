/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BitSetDiscreteVoxelShape;
import lightning.product.b_257_Y;
import lightning.product.p_602_A;

public abstract class DiscreteVoxelShape {
    private static final b_257_Y.n_1700_B[] G_564_y = b_257_Y.n_1700_B.values();
    protected final int n_1700_B;
    protected final int J_1907_R;
    protected final int R_4764_Y;

    protected DiscreteVoxelShape(int xIn, int yIn, int zIn) {
        this.n_1700_B = xIn;
        this.J_1907_R = yIn;
        this.R_4764_Y = zIn;
    }

    public boolean n_1700_B(p_602_A axis, int x, int y, int z) {
        return this.R_4764_Y(axis.n_1700_B(x, y, z, b_257_Y.n_1700_B.n_1700_B), axis.n_1700_B(x, y, z, b_257_Y.n_1700_B.J_1907_R), axis.n_1700_B(x, y, z, b_257_Y.n_1700_B.R_4764_Y));
    }

    public boolean R_4764_Y(int x, int y, int z) {
        if (x >= 0 && y >= 0 && z >= 0) {
            return x < this.n_1700_B && y < this.J_1907_R && z < this.R_4764_Y ? this.J_1907_R(x, y, z) : false;
        }
        return false;
    }

    public boolean J_1907_R(p_602_A rotationIn, int x, int y, int z) {
        return this.J_1907_R(rotationIn.n_1700_B(x, y, z, b_257_Y.n_1700_B.n_1700_B), rotationIn.n_1700_B(x, y, z, b_257_Y.n_1700_B.J_1907_R), rotationIn.n_1700_B(x, y, z, b_257_Y.n_1700_B.R_4764_Y));
    }

    public abstract boolean J_1907_R(int var1, int var2, int var3);

    public abstract void n_1700_B(int var1, int var2, int var3, boolean var4, boolean var5);

    public boolean n_1700_B() {
        for (b_257_Y.n_1700_B direction$axis : G_564_y) {
            if (this.n_1700_B(direction$axis) < this.J_1907_R(direction$axis)) continue;
            return true;
        }
        return false;
    }

    public abstract int n_1700_B(b_257_Y.n_1700_B var1);

    public abstract int J_1907_R(b_257_Y.n_1700_B var1);

    public int n_1700_B(b_257_Y.n_1700_B axis, int p_197836_2_, int p_197836_3_) {
        if (p_197836_2_ >= 0 && p_197836_3_ >= 0) {
            b_257_Y.n_1700_B direction$axis = p_602_A.J_1907_R.n_1700_B(axis);
            b_257_Y.n_1700_B direction$axis1 = p_602_A.R_4764_Y.n_1700_B(axis);
            if (p_197836_2_ < this.R_4764_Y(direction$axis) && p_197836_3_ < this.R_4764_Y(direction$axis1)) {
                int i = this.R_4764_Y(axis);
                p_602_A axisrotation = p_602_A.n_1700_B(b_257_Y.n_1700_B.n_1700_B, axis);
                for (int j = i - 1; j >= 0; --j) {
                    if (!this.J_1907_R(axisrotation, j, p_197836_2_, p_197836_3_)) continue;
                    return j + 1;
                }
                return 0;
            }
            return 0;
        }
        return 0;
    }

    public int R_4764_Y(b_257_Y.n_1700_B axis) {
        return axis.n_1700_B(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
    }

    public int J_1907_R() {
        return this.R_4764_Y(b_257_Y.n_1700_B.n_1700_B);
    }

    public int R_4764_Y() {
        return this.R_4764_Y(b_257_Y.n_1700_B.J_1907_R);
    }

    public int G_564_y() {
        return this.R_4764_Y(b_257_Y.n_1700_B.R_4764_Y);
    }

    public void n_1700_B(J_1907_R consumer, boolean combine) {
        this.n_1700_B(consumer, p_602_A.n_1700_B, combine);
        this.n_1700_B(consumer, p_602_A.J_1907_R, combine);
        this.n_1700_B(consumer, p_602_A.R_4764_Y, combine);
    }

    private void n_1700_B(J_1907_R lineConsumer, p_602_A axis, boolean p_197832_3_) {
        p_602_A axisrotation = axis.n_1700_B();
        int j = this.R_4764_Y(axisrotation.n_1700_B(b_257_Y.n_1700_B.n_1700_B));
        int k = this.R_4764_Y(axisrotation.n_1700_B(b_257_Y.n_1700_B.J_1907_R));
        int l = this.R_4764_Y(axisrotation.n_1700_B(b_257_Y.n_1700_B.R_4764_Y));
        for (int i1 = 0; i1 <= j; ++i1) {
            for (int j1 = 0; j1 <= k; ++j1) {
                int i = -1;
                for (int k1 = 0; k1 <= l; ++k1) {
                    int l1 = 0;
                    int i2 = 0;
                    for (int j2 = 0; j2 <= 1; ++j2) {
                        for (int k2 = 0; k2 <= 1; ++k2) {
                            if (!this.n_1700_B(axisrotation, i1 + j2 - 1, j1 + k2 - 1, k1)) continue;
                            ++l1;
                            i2 ^= j2 ^ k2;
                        }
                    }
                    if (l1 == 1 || l1 == 3 || l1 == 2 && !(i2 & true)) {
                        if (p_197832_3_) {
                            if (i != -1) continue;
                            i = k1;
                            continue;
                        }
                        lineConsumer.consume(axisrotation.n_1700_B(i1, j1, k1, b_257_Y.n_1700_B.n_1700_B), axisrotation.n_1700_B(i1, j1, k1, b_257_Y.n_1700_B.J_1907_R), axisrotation.n_1700_B(i1, j1, k1, b_257_Y.n_1700_B.R_4764_Y), axisrotation.n_1700_B(i1, j1, k1 + 1, b_257_Y.n_1700_B.n_1700_B), axisrotation.n_1700_B(i1, j1, k1 + 1, b_257_Y.n_1700_B.J_1907_R), axisrotation.n_1700_B(i1, j1, k1 + 1, b_257_Y.n_1700_B.R_4764_Y));
                        continue;
                    }
                    if (i == -1) continue;
                    lineConsumer.consume(axisrotation.n_1700_B(i1, j1, i, b_257_Y.n_1700_B.n_1700_B), axisrotation.n_1700_B(i1, j1, i, b_257_Y.n_1700_B.J_1907_R), axisrotation.n_1700_B(i1, j1, i, b_257_Y.n_1700_B.R_4764_Y), axisrotation.n_1700_B(i1, j1, k1, b_257_Y.n_1700_B.n_1700_B), axisrotation.n_1700_B(i1, j1, k1, b_257_Y.n_1700_B.J_1907_R), axisrotation.n_1700_B(i1, j1, k1, b_257_Y.n_1700_B.R_4764_Y));
                    i = -1;
                }
            }
        }
    }

    protected boolean n_1700_B(int fromZ, int toZ, int x, int y) {
        for (int i = fromZ; i < toZ; ++i) {
            if (this.R_4764_Y(x, y, i)) continue;
            return false;
        }
        return true;
    }

    protected void n_1700_B(int fromZ, int toZ, int x, int y, boolean filled) {
        for (int i = fromZ; i < toZ; ++i) {
            this.n_1700_B(x, y, i, false, filled);
        }
    }

    protected boolean n_1700_B(int fromX, int toX, int fromZ, int toZ, int x) {
        for (int i = fromX; i < toX; ++i) {
            if (this.n_1700_B(fromZ, toZ, i, x)) continue;
            return false;
        }
        return true;
    }

    public void J_1907_R(J_1907_R consumer, boolean combine) {
        BitSetDiscreteVoxelShape voxelshapepart = new BitSetDiscreteVoxelShape(this);
        for (int i = 0; i <= this.n_1700_B; ++i) {
            for (int j = 0; j <= this.J_1907_R; ++j) {
                int k = -1;
                for (int l = 0; l <= this.R_4764_Y; ++l) {
                    if (voxelshapepart.R_4764_Y(i, j, l)) {
                        if (combine) {
                            if (k != -1) continue;
                            k = l;
                            continue;
                        }
                        consumer.consume(i, j, l, i + 1, j + 1, l + 1);
                        continue;
                    }
                    if (k == -1) continue;
                    int i1 = i;
                    int j1 = i;
                    int k1 = j;
                    int l1 = j;
                    ((DiscreteVoxelShape)voxelshapepart).n_1700_B(k, l, i, j, false);
                    while (((DiscreteVoxelShape)voxelshapepart).n_1700_B(k, l, i1 - 1, k1)) {
                        ((DiscreteVoxelShape)voxelshapepart).n_1700_B(k, l, i1 - 1, k1, false);
                        --i1;
                    }
                    while (((DiscreteVoxelShape)voxelshapepart).n_1700_B(k, l, j1 + 1, k1)) {
                        ((DiscreteVoxelShape)voxelshapepart).n_1700_B(k, l, j1 + 1, k1, false);
                        ++j1;
                    }
                    while (voxelshapepart.n_1700_B(i1, j1 + 1, k, l, k1 - 1)) {
                        for (int i2 = i1; i2 <= j1; ++i2) {
                            ((DiscreteVoxelShape)voxelshapepart).n_1700_B(k, l, i2, k1 - 1, false);
                        }
                        --k1;
                    }
                    while (voxelshapepart.n_1700_B(i1, j1 + 1, k, l, l1 + 1)) {
                        for (int j2 = i1; j2 <= j1; ++j2) {
                            ((DiscreteVoxelShape)voxelshapepart).n_1700_B(k, l, j2, l1 + 1, false);
                        }
                        ++l1;
                    }
                    consumer.consume(i1, k1, k, j1 + 1, l1 + 1, l);
                    k = -1;
                }
            }
        }
    }

    public void n_1700_B(n_1700_B faceConsumer) {
        this.n_1700_B(faceConsumer, p_602_A.n_1700_B);
        this.n_1700_B(faceConsumer, p_602_A.J_1907_R);
        this.n_1700_B(faceConsumer, p_602_A.R_4764_Y);
    }

    private void n_1700_B(n_1700_B faceConsumer, p_602_A axisRotationIn) {
        p_602_A axisrotation = axisRotationIn.n_1700_B();
        b_257_Y.n_1700_B direction$axis = axisrotation.n_1700_B(b_257_Y.n_1700_B.R_4764_Y);
        int i = this.R_4764_Y(axisrotation.n_1700_B(b_257_Y.n_1700_B.n_1700_B));
        int j = this.R_4764_Y(axisrotation.n_1700_B(b_257_Y.n_1700_B.J_1907_R));
        int k = this.R_4764_Y(direction$axis);
        b_257_Y direction = b_257_Y.n_1700_B(direction$axis, b_257_Y.J_1907_R.J_1907_R);
        b_257_Y direction1 = b_257_Y.n_1700_B(direction$axis, b_257_Y.J_1907_R.n_1700_B);
        for (int l = 0; l < i; ++l) {
            for (int i1 = 0; i1 < j; ++i1) {
                boolean flag = false;
                for (int j1 = 0; j1 <= k; ++j1) {
                    boolean flag1;
                    boolean bl = flag1 = j1 != k && this.J_1907_R(axisrotation, l, i1, j1);
                    if (!flag && flag1) {
                        faceConsumer.consume(direction, axisrotation.n_1700_B(l, i1, j1, b_257_Y.n_1700_B.n_1700_B), axisrotation.n_1700_B(l, i1, j1, b_257_Y.n_1700_B.J_1907_R), axisrotation.n_1700_B(l, i1, j1, b_257_Y.n_1700_B.R_4764_Y));
                    }
                    if (flag && !flag1) {
                        faceConsumer.consume(direction1, axisrotation.n_1700_B(l, i1, j1 - 1, b_257_Y.n_1700_B.n_1700_B), axisrotation.n_1700_B(l, i1, j1 - 1, b_257_Y.n_1700_B.J_1907_R), axisrotation.n_1700_B(l, i1, j1 - 1, b_257_Y.n_1700_B.R_4764_Y));
                    }
                    flag = flag1;
                }
            }
        }
    }

    public static interface J_1907_R {
        public void consume(int var1, int var2, int var3, int var4, int var5, int var6);
    }

    public static interface n_1700_B {
        public void consume(b_257_Y var1, int var2, int var3, int var4);
    }
}


