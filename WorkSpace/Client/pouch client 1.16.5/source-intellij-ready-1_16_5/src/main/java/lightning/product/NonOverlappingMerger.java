/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.AbstractDoubleList
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 */
package lightning.product;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import lightning.product.IndexMerger;

public class NonOverlappingMerger
extends AbstractDoubleList
implements IndexMerger {
    private final DoubleList n_1700_B;
    private final DoubleList J_1907_R;
    private final boolean R_4764_Y;

    public NonOverlappingMerger(DoubleList list1, DoubleList list2, boolean p_i48187_3_) {
        this.n_1700_B = list1;
        this.J_1907_R = list2;
        this.R_4764_Y = p_i48187_3_;
    }

    public int size() {
        return this.n_1700_B.size() + this.J_1907_R.size();
    }

    @Override
    public boolean n_1700_B(IndexMerger.n_1700_B consumer) {
        return this.R_4764_Y ? this.J_1907_R((p_199636_1_, p_199636_2_, p_199636_3_) -> consumer.merge(p_199636_2_, p_199636_1_, p_199636_3_)) : this.J_1907_R(consumer);
    }

    private boolean J_1907_R(IndexMerger.n_1700_B p_199637_1_) {
        int i = this.n_1700_B.size() - 1;
        for (int j = 0; j < i; ++j) {
            if (p_199637_1_.merge(j, -1, j)) continue;
            return false;
        }
        if (!p_199637_1_.merge(i, -1, i)) {
            return false;
        }
        for (int k = 0; k < this.J_1907_R.size(); ++k) {
            if (p_199637_1_.merge(i, k, i + 1 + k)) continue;
            return false;
        }
        return true;
    }

    public double getDouble(int p_getDouble_1_) {
        return p_getDouble_1_ < this.n_1700_B.size() ? this.n_1700_B.getDouble(p_getDouble_1_) : this.J_1907_R.getDouble(p_getDouble_1_ - this.n_1700_B.size());
    }

    @Override
    public DoubleList n_1700_B() {
        return this;
    }
}


