/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 */
package lightning.product;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import lightning.product.IndexMerger;

public class IdenticalMerger
implements IndexMerger {
    private final DoubleList n_1700_B;

    public IdenticalMerger(DoubleList list) {
        this.n_1700_B = list;
    }

    @Override
    public boolean n_1700_B(IndexMerger.n_1700_B consumer) {
        for (int i = 0; i <= this.n_1700_B.size(); ++i) {
            if (consumer.merge(i, i, i)) continue;
            return false;
        }
        return true;
    }

    @Override
    public DoubleList n_1700_B() {
        return this.n_1700_B;
    }
}


