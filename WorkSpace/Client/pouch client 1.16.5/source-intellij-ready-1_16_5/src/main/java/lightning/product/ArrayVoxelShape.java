/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.DoubleArrayList
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 */
package lightning.product;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import java.util.Arrays;
import lightning.product.DiscreteVoxelShape;
import lightning.product.b_257_Y;
import lightning.product.j_3341_s;
import lightning.product.s_1395_c;

public final class ArrayVoxelShape
extends s_1395_c {
    private final DoubleList J_1907_R;
    private final DoubleList R_4764_Y;
    private final DoubleList G_564_y;

    protected ArrayVoxelShape(DiscreteVoxelShape shapePartIn, double[] xPointsIn, double[] yPointsIn, double[] zPointsIn) {
        this(shapePartIn, (DoubleList)DoubleArrayList.wrap((double[])Arrays.copyOf(xPointsIn, shapePartIn.J_1907_R() + 1)), (DoubleList)DoubleArrayList.wrap((double[])Arrays.copyOf(yPointsIn, shapePartIn.R_4764_Y() + 1)), (DoubleList)DoubleArrayList.wrap((double[])Arrays.copyOf(zPointsIn, shapePartIn.G_564_y() + 1)));
    }

    ArrayVoxelShape(DiscreteVoxelShape shapePartIn, DoubleList xPointsIn, DoubleList yPointsIn, DoubleList zPointsIn) {
        super(shapePartIn);
        int i = shapePartIn.J_1907_R() + 1;
        int j = shapePartIn.R_4764_Y() + 1;
        int k = shapePartIn.G_564_y() + 1;
        if (i != xPointsIn.size() || j != yPointsIn.size() || k != zPointsIn.size()) {
            throw j_3341_s.R_4764_Y(new IllegalArgumentException("Lengths of point arrays must be consistent with the size of the VoxelShape."));
        }
        this.J_1907_R = xPointsIn;
        this.R_4764_Y = yPointsIn;
        this.G_564_y = zPointsIn;
    }

    @Override
    protected DoubleList n_1700_B(b_257_Y.n_1700_B axis) {
        switch (axis) {
            case n_1700_B: {
                return this.J_1907_R;
            }
            case J_1907_R: {
                return this.R_4764_Y;
            }
            case R_4764_Y: {
                return this.G_564_y;
            }
        }
        throw new IllegalArgumentException();
    }
}


