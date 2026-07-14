/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.c_1289_c;
import lightning.product.SectionPos;

public abstract class SectionTracker
extends c_1289_c {
    protected SectionTracker(int levelCount, int p_i50706_2_, int p_i50706_3_) {
        super(levelCount, p_i50706_2_, p_i50706_3_);
    }

    @Override
    protected boolean n_1700_B(long pos) {
        return pos == Long.MAX_VALUE;
    }

    @Override
    protected void n_1700_B(long pos, int level, boolean isDecreasing) {
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                for (int k = -1; k <= 1; ++k) {
                    long l = SectionPos.n_1700_B(pos, i, j, k);
                    if (l == pos) continue;
                    this.J_1907_R(pos, l, level, isDecreasing);
                }
            }
        }
    }

    @Override
    protected int n_1700_B(long pos, long excludedSourcePos, int level) {
        int i = level;
        for (int j = -1; j <= 1; ++j) {
            for (int k = -1; k <= 1; ++k) {
                for (int l = -1; l <= 1; ++l) {
                    long i1 = SectionPos.n_1700_B(pos, j, k, l);
                    if (i1 == pos) {
                        i1 = Long.MAX_VALUE;
                    }
                    if (i1 == excludedSourcePos) continue;
                    int j1 = this.J_1907_R(i1, pos, this.R_4764_Y(i1));
                    if (i > j1) {
                        i = j1;
                    }
                    if (i != 0) continue;
                    return i;
                }
            }
        }
        return i;
    }

    @Override
    protected int J_1907_R(long startPos, long endPos, int startLevel) {
        return startPos == Long.MAX_VALUE ? this.J_1907_R(endPos) : startLevel + 1;
    }

    protected abstract int J_1907_R(long var1);

    public void J_1907_R(long pos, int level, boolean isDecreasing) {
        this.n_1700_B(Long.MAX_VALUE, pos, level, isDecreasing);
    }
}


