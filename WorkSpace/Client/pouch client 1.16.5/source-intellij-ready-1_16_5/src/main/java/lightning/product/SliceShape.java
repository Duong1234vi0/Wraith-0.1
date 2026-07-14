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
import lightning.product.SubShape;
import lightning.product.s_1395_c;
import lightning.product.CubePointRange;

public class SliceShape
extends s_1395_c {
    private final s_1395_c J_1907_R;
    private final b_257_Y.n_1700_B R_4764_Y;
    private static final DoubleList G_564_y = new CubePointRange(1);

    public SliceShape(s_1395_c shapeIn, b_257_Y.n_1700_B axis, int p_i47682_3_) {
        super(SliceShape.n_1700_B(shapeIn.n_1700_B, axis, p_i47682_3_));
        this.J_1907_R = shapeIn;
        this.R_4764_Y = axis;
    }

    private static DiscreteVoxelShape n_1700_B(DiscreteVoxelShape shapePartIn, b_257_Y.n_1700_B axis, int p_197775_2_) {
        return new SubShape(shapePartIn, axis.n_1700_B(p_197775_2_, 0, 0), axis.n_1700_B(0, p_197775_2_, 0), axis.n_1700_B(0, 0, p_197775_2_), axis.n_1700_B(p_197775_2_ + 1, shapePartIn.n_1700_B, shapePartIn.n_1700_B), axis.n_1700_B(shapePartIn.J_1907_R, p_197775_2_ + 1, shapePartIn.J_1907_R), axis.n_1700_B(shapePartIn.R_4764_Y, shapePartIn.R_4764_Y, p_197775_2_ + 1));
    }

    @Override
    protected DoubleList n_1700_B(b_257_Y.n_1700_B axis) {
        return axis == this.R_4764_Y ? G_564_y : this.J_1907_R.n_1700_B(axis);
    }
}


