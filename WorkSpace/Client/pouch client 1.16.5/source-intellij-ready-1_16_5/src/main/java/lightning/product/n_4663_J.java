/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Y_1387_d;
import lightning.product.c_1289_c;

public abstract class n_4663_J
extends c_1289_c {
    protected n_4663_J(int levelCount, int expectedSet, int expectedMap) {
        super(levelCount, expectedSet, expectedMap);
    }

    @Override
    protected boolean n_1700_B(long pos) {
        return pos == Y_1387_d.n_1700_B;
    }

    @Override
    protected void n_1700_B(long pos, int level, boolean isDecreasing) {
        Y_1387_d chunkpos = new Y_1387_d(pos);
        int i = chunkpos.J_1907_R;
        int j = chunkpos.R_4764_Y;
        for (int k = -1; k <= 1; ++k) {
            for (int l = -1; l <= 1; ++l) {
                long i1 = Y_1387_d.n_1700_B(i + k, j + l);
                if (i1 == pos) continue;
                this.J_1907_R(pos, i1, level, isDecreasing);
            }
        }
    }

    @Override
    protected int n_1700_B(long pos, long excludedSourcePos, int level) {
        int i = level;
        Y_1387_d chunkpos = new Y_1387_d(pos);
        int j = chunkpos.J_1907_R;
        int k = chunkpos.R_4764_Y;
        for (int l = -1; l <= 1; ++l) {
            for (int i1 = -1; i1 <= 1; ++i1) {
                long j1 = Y_1387_d.n_1700_B(j + l, k + i1);
                if (j1 == pos) {
                    j1 = Y_1387_d.n_1700_B;
                }
                if (j1 == excludedSourcePos) continue;
                int k1 = this.J_1907_R(j1, pos, this.R_4764_Y(j1));
                if (i > k1) {
                    i = k1;
                }
                if (i != 0) continue;
                return i;
            }
        }
        return i;
    }

    @Override
    protected int J_1907_R(long startPos, long endPos, int startLevel) {
        return startPos == Y_1387_d.n_1700_B ? this.J_1907_R(endPos) : startLevel + 1;
    }

    protected abstract int J_1907_R(long var1);

    public void J_1907_R(long pos, int level, boolean isDecreasing) {
        this.n_1700_B(Y_1387_d.n_1700_B, pos, level, isDecreasing);
    }
}

