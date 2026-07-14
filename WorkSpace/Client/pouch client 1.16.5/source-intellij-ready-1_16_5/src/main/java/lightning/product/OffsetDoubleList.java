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

public class OffsetDoubleList
extends AbstractDoubleList {
    private final DoubleList n_1700_B;
    private final double J_1907_R;

    public OffsetDoubleList(DoubleList delegate, double offset) {
        this.n_1700_B = delegate;
        this.J_1907_R = offset;
    }

    public double getDouble(int p_getDouble_1_) {
        return this.n_1700_B.getDouble(p_getDouble_1_) + this.J_1907_R;
    }

    public int size() {
        return this.n_1700_B.size();
    }
}


