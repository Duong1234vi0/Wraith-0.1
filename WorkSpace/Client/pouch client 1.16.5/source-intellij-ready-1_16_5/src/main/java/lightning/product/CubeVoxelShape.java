/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 */
package lightning.product;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import lightning.product.DiscreteVoxelShape;
import lightning.product.b_257_Y;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.CubePointRange;

public final class CubeVoxelShape
extends s_1395_c {
    protected CubeVoxelShape(DiscreteVoxelShape part) {
        super(part);
    }

    @Override
    protected DoubleList n_1700_B(b_257_Y.n_1700_B axis) {
        return new CubePointRange(this.n_1700_B.R_4764_Y(axis));
    }

    @Override
    protected int n_1700_B(b_257_Y.n_1700_B axis, double position) {
        int i = this.n_1700_B.R_4764_Y(axis);
        return u_530_F.n_1700_B(u_530_F.R_4764_Y(position * (double)i), -1, i);
    }
}


