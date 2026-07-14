/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.math.DoubleMath
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.math.DoubleMath;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.OffsetDoubleList;
import lightning.product.ArrayVoxelShape;
import lightning.product.BlockHitResult;
import lightning.product.I_4817_s;
import lightning.product.DiscreteVoxelShape;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.j_3341_s;
import lightning.product.p_602_A;
import lightning.product.SliceShape;
import lightning.product.u_530_F;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public abstract class s_1395_c {
    protected final DiscreteVoxelShape n_1700_B;
    @Nullable
    private s_1395_c[] J_1907_R;

    s_1395_c(DiscreteVoxelShape part) {
        this.n_1700_B = part;
    }

    public double J_1907_R(b_257_Y.n_1700_B axis) {
        int i = this.n_1700_B.n_1700_B(axis);
        return i >= this.n_1700_B.R_4764_Y(axis) ? Double.POSITIVE_INFINITY : this.n_1700_B(axis, i);
    }

    public double R_4764_Y(b_257_Y.n_1700_B axis) {
        int i = this.n_1700_B.J_1907_R(axis);
        return i <= 0 ? Double.NEGATIVE_INFINITY : this.n_1700_B(axis, i);
    }

    public I_4817_s n_1700_B() {
        if (this.J_1907_R()) {
            throw j_3341_s.R_4764_Y(new UnsupportedOperationException("No bounds for empty shape."));
        }
        return new I_4817_s(this.J_1907_R(b_257_Y.n_1700_B.n_1700_B), this.J_1907_R(b_257_Y.n_1700_B.J_1907_R), this.J_1907_R(b_257_Y.n_1700_B.R_4764_Y), this.R_4764_Y(b_257_Y.n_1700_B.n_1700_B), this.R_4764_Y(b_257_Y.n_1700_B.J_1907_R), this.R_4764_Y(b_257_Y.n_1700_B.R_4764_Y));
    }

    protected double n_1700_B(b_257_Y.n_1700_B axis, int index) {
        return this.n_1700_B(axis).getDouble(index);
    }

    protected abstract DoubleList n_1700_B(b_257_Y.n_1700_B var1);

    public boolean J_1907_R() {
        return this.n_1700_B.n_1700_B();
    }

    public s_1395_c n_1700_B(double xOffset, double yOffset, double zOffset) {
        return this.J_1907_R() ? x_268_Y.n_1700_B() : new ArrayVoxelShape(this.n_1700_B, (DoubleList)new OffsetDoubleList(this.n_1700_B(b_257_Y.n_1700_B.n_1700_B), xOffset), (DoubleList)new OffsetDoubleList(this.n_1700_B(b_257_Y.n_1700_B.J_1907_R), yOffset), (DoubleList)new OffsetDoubleList(this.n_1700_B(b_257_Y.n_1700_B.R_4764_Y), zOffset));
    }

    public s_1395_c R_4764_Y() {
        s_1395_c[] avoxelshape = new s_1395_c[]{x_268_Y.n_1700_B()};
        this.J_1907_R((double p_197763_1_, double p_197763_3_, double p_197763_5_, double p_197763_7_, double p_197763_9_, double p_197763_11_) -> {
            avoxelshape[0] = x_268_Y.J_1907_R(avoxelshape[0], x_268_Y.n_1700_B(p_197763_1_, p_197763_3_, p_197763_5_, p_197763_7_, p_197763_9_, p_197763_11_), BooleanOp.Q_4569_t);
        });
        return avoxelshape[0];
    }

    public void n_1700_B(x_268_Y.n_1700_B action) {
        this.n_1700_B.n_1700_B((int x1, int y1, int z1, int x2, int y2, int z2) -> action.consume(this.n_1700_B(b_257_Y.n_1700_B.n_1700_B, x1), this.n_1700_B(b_257_Y.n_1700_B.J_1907_R, y1), this.n_1700_B(b_257_Y.n_1700_B.R_4764_Y, z1), this.n_1700_B(b_257_Y.n_1700_B.n_1700_B, x2), this.n_1700_B(b_257_Y.n_1700_B.J_1907_R, y2), this.n_1700_B(b_257_Y.n_1700_B.R_4764_Y, z2)), true);
    }

    public void J_1907_R(x_268_Y.n_1700_B action) {
        DoubleList doublelist = this.n_1700_B(b_257_Y.n_1700_B.n_1700_B);
        DoubleList doublelist1 = this.n_1700_B(b_257_Y.n_1700_B.J_1907_R);
        DoubleList doublelist2 = this.n_1700_B(b_257_Y.n_1700_B.R_4764_Y);
        this.n_1700_B.J_1907_R((x1, y1, z1, x2, y2, z2) -> action.consume(doublelist.getDouble(x1), doublelist1.getDouble(y1), doublelist2.getDouble(z1), doublelist.getDouble(x2), doublelist1.getDouble(y2), doublelist2.getDouble(z2)), true);
    }

    public List<I_4817_s> G_564_y() {
        ArrayList list = Lists.newArrayList();
        this.J_1907_R((double x1, double y1, double z1, double x2, double y2, double z2) -> list.add(new I_4817_s(x1, y1, z1, x2, y2, z2)));
        return list;
    }

    public double n_1700_B(b_257_Y.n_1700_B p_197760_1_, double position1, double p_197760_4_) {
        int j;
        b_257_Y.n_1700_B direction$axis = p_602_A.J_1907_R.n_1700_B(p_197760_1_);
        b_257_Y.n_1700_B direction$axis1 = p_602_A.R_4764_Y.n_1700_B(p_197760_1_);
        int i = this.n_1700_B(direction$axis, position1);
        int k = this.n_1700_B.n_1700_B(p_197760_1_, i, j = this.n_1700_B(direction$axis1, p_197760_4_));
        return k <= 0 ? Double.NEGATIVE_INFINITY : this.n_1700_B(p_197760_1_, k);
    }

    protected int n_1700_B(b_257_Y.n_1700_B axis, double position) {
        return u_530_F.n_1700_B(0, this.n_1700_B.R_4764_Y(axis) + 1, index -> {
            if (index < 0) {
                return false;
            }
            if (index > this.n_1700_B.R_4764_Y(axis)) {
                return true;
            }
            return position < this.n_1700_B(axis, index);
        }) - 1;
    }

    protected boolean J_1907_R(double x, double y, double z) {
        return this.n_1700_B.R_4764_Y(this.n_1700_B(b_257_Y.n_1700_B.n_1700_B, x), this.n_1700_B(b_257_Y.n_1700_B.J_1907_R, y), this.n_1700_B(b_257_Y.n_1700_B.R_4764_Y, z));
    }

    @Nullable
    public BlockHitResult n_1700_B(e_2866_D startVec, e_2866_D endVec, c_1514_x pos) {
        if (this.J_1907_R()) {
            return null;
        }
        e_2866_D vector3d = endVec.G_564_y(startVec);
        if (vector3d.v_4262_N() < 1.0E-7) {
            return null;
        }
        e_2866_D vector3d1 = startVec.P_1922_E(vector3d.n_1700_B(0.001));
        return this.J_1907_R(vector3d1.J_1907_R - (double)pos.getX(), vector3d1.R_4764_Y - (double)pos.getY(), vector3d1.G_564_y - (double)pos.getZ()) ? new BlockHitResult(vector3d1, b_257_Y.n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y).u_1723_Y(), pos, true) : I_4817_s.rayTrace(this.G_564_y(), startVec, endVec, pos);
    }

    public s_1395_c n_1700_B(b_257_Y side) {
        if (!this.J_1907_R() && this != x_268_Y.J_1907_R()) {
            s_1395_c voxelshape1;
            if (this.J_1907_R != null) {
                s_1395_c voxelshape = this.J_1907_R[side.ordinal()];
                if (voxelshape != null) {
                    return voxelshape;
                }
            } else {
                this.J_1907_R = new s_1395_c[6];
            }
            this.J_1907_R[side.ordinal()] = voxelshape1 = this.J_1907_R(side);
            return voxelshape1;
        }
        return this;
    }

    private s_1395_c J_1907_R(b_257_Y side) {
        b_257_Y.n_1700_B direction$axis = side.h_1847_R();
        b_257_Y.J_1907_R direction$axisdirection = side.P_1922_E();
        DoubleList doublelist = this.n_1700_B(direction$axis);
        if (doublelist.size() == 2 && DoubleMath.fuzzyEquals((double)doublelist.getDouble(0), (double)0.0, (double)1.0E-7) && DoubleMath.fuzzyEquals((double)doublelist.getDouble(1), (double)1.0, (double)1.0E-7)) {
            return this;
        }
        int i = this.n_1700_B(direction$axis, direction$axisdirection == b_257_Y.J_1907_R.n_1700_B ? 0.9999999 : 1.0E-7);
        return new SliceShape(this, direction$axis, i);
    }

    public double n_1700_B(b_257_Y.n_1700_B movementAxis, I_4817_s collisionBox, double desiredOffset) {
        return this.n_1700_B(p_602_A.n_1700_B(movementAxis, b_257_Y.n_1700_B.n_1700_B), collisionBox, desiredOffset);
    }

    protected double n_1700_B(p_602_A movementAxis, I_4817_s collisionBox, double desiredOffset) {
        block11: {
            int j1;
            int l;
            double d1;
            b_257_Y.n_1700_B direction$axis;
            p_602_A axisrotation;
            block10: {
                if (this.J_1907_R()) {
                    return desiredOffset;
                }
                if (Math.abs(desiredOffset) < 1.0E-7) {
                    return 0.0;
                }
                axisrotation = movementAxis.n_1700_B();
                direction$axis = axisrotation.n_1700_B(b_257_Y.n_1700_B.n_1700_B);
                b_257_Y.n_1700_B direction$axis1 = axisrotation.n_1700_B(b_257_Y.n_1700_B.J_1907_R);
                b_257_Y.n_1700_B direction$axis2 = axisrotation.n_1700_B(b_257_Y.n_1700_B.R_4764_Y);
                double d0 = collisionBox.getMax(direction$axis);
                d1 = collisionBox.getMin(direction$axis);
                int i = this.n_1700_B(direction$axis, d1 + 1.0E-7);
                int j = this.n_1700_B(direction$axis, d0 - 1.0E-7);
                int k = Math.max(0, this.n_1700_B(direction$axis1, collisionBox.getMin(direction$axis1) + 1.0E-7));
                l = Math.min(this.n_1700_B.R_4764_Y(direction$axis1), this.n_1700_B(direction$axis1, collisionBox.getMax(direction$axis1) - 1.0E-7) + 1);
                int i1 = Math.max(0, this.n_1700_B(direction$axis2, collisionBox.getMin(direction$axis2) + 1.0E-7));
                j1 = Math.min(this.n_1700_B.R_4764_Y(direction$axis2), this.n_1700_B(direction$axis2, collisionBox.getMax(direction$axis2) - 1.0E-7) + 1);
                int k1 = this.n_1700_B.R_4764_Y(direction$axis);
                if (!(desiredOffset > 0.0)) break block10;
                for (int l1 = j + 1; l1 < k1; ++l1) {
                    for (int i2 = k; i2 < l; ++i2) {
                        for (int j2 = i1; j2 < j1; ++j2) {
                            if (!this.n_1700_B.n_1700_B(axisrotation, l1, i2, j2)) continue;
                            double d2 = this.n_1700_B(direction$axis, l1) - d0;
                            if (d2 >= -1.0E-7) {
                                desiredOffset = Math.min(desiredOffset, d2);
                            }
                            return desiredOffset;
                        }
                    }
                }
                break block11;
            }
            if (!(desiredOffset < 0.0)) break block11;
            for (int k2 = i - 1; k2 >= 0; --k2) {
                for (int l2 = k; l2 < l; ++l2) {
                    for (int i3 = i1; i3 < j1; ++i3) {
                        if (!this.n_1700_B.n_1700_B(axisrotation, k2, l2, i3)) continue;
                        double d3 = this.n_1700_B(direction$axis, k2 + 1) - d1;
                        if (d3 <= 1.0E-7) {
                            desiredOffset = Math.max(desiredOffset, d3);
                        }
                        return desiredOffset;
                    }
                }
            }
        }
        return desiredOffset;
    }

    public String toString() {
        return this.J_1907_R() ? "EMPTY" : "VoxelShape[" + String.valueOf(this.n_1700_B()) + "]";
    }
}


