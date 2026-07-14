/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.AbstractDoubleList
 */
package lightning.product;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleList;

public class CubePointRange
extends AbstractDoubleList {
    private final int n_1700_B;

    CubePointRange(int p_i47689_1_) {
        this.n_1700_B = p_i47689_1_;
    }

    public double getDouble(int p_getDouble_1_) {
        return (double)p_getDouble_1_ / (double)this.n_1700_B;
    }

    public int size() {
        return this.n_1700_B + 1;
    }
}


