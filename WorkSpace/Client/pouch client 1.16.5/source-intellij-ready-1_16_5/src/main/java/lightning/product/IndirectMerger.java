/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.DoubleArrayList
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 */
package lightning.product;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import lightning.product.IndexMerger;

public final class IndirectMerger
implements IndexMerger {
    private final DoubleArrayList n_1700_B;
    private final IntArrayList J_1907_R;
    private final IntArrayList R_4764_Y;

    protected IndirectMerger(DoubleList list1In, DoubleList list2In, boolean p_i47685_3_, boolean p_i47685_4_) {
        int i = 0;
        int j = 0;
        double d0 = Double.NaN;
        int k = list1In.size();
        int l = list2In.size();
        int i1 = k + l;
        this.n_1700_B = new DoubleArrayList(i1);
        this.J_1907_R = new IntArrayList(i1);
        this.R_4764_Y = new IntArrayList(i1);
        while (true) {
            double d1;
            boolean flag1;
            boolean flag = i < k;
            boolean bl = flag1 = j < l;
            if (!flag && !flag1) {
                if (this.n_1700_B.isEmpty()) {
                    this.n_1700_B.add(Math.min(list1In.getDouble(k - 1), list2In.getDouble(l - 1)));
                }
                return;
            }
            boolean flag2 = flag && (!flag1 || list1In.getDouble(i) < list2In.getDouble(j) + 1.0E-7);
            double d = d1 = flag2 ? list1In.getDouble(i++) : list2In.getDouble(j++);
            if ((i == 0 || !flag) && !flag2 && !p_i47685_4_ || (j == 0 || !flag1) && flag2 && !p_i47685_3_) continue;
            if (!(d0 >= d1 - 1.0E-7)) {
                this.J_1907_R.add(i - 1);
                this.R_4764_Y.add(j - 1);
                this.n_1700_B.add(d1);
                d0 = d1;
                continue;
            }
            if (this.n_1700_B.isEmpty()) continue;
            this.J_1907_R.set(this.J_1907_R.size() - 1, i - 1);
            this.R_4764_Y.set(this.R_4764_Y.size() - 1, j - 1);
        }
    }

    @Override
    public boolean n_1700_B(IndexMerger.n_1700_B consumer) {
        for (int i = 0; i < this.n_1700_B.size() - 1; ++i) {
            if (consumer.merge(this.J_1907_R.getInt(i), this.R_4764_Y.getInt(i), i)) continue;
            return false;
        }
        return true;
    }

    @Override
    public DoubleList n_1700_B() {
        return this.n_1700_B;
    }
}


