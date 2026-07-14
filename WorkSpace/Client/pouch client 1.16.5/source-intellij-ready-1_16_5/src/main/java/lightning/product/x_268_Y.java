/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.math.DoubleMath
 *  com.google.common.math.IntMath
 *  it.unimi.dsi.fastutil.doubles.DoubleArrayList
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;
import lightning.product.BlockGetter;
import lightning.product.ArrayVoxelShape;
import lightning.product.BitSetDiscreteVoxelShape;
import lightning.product.IndexMerger;
import lightning.product.I_4817_s;
import lightning.product.IdenticalMerger;
import lightning.product.K_4074_S;
import lightning.product.DiscreteVoxelShape;
import lightning.product.IndirectMerger;
import lightning.product.T_1316_M;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.DiscreteCubeMerger;
import lightning.product.NonOverlappingMerger;
import lightning.product.j_3341_s;
import lightning.product.CubeVoxelShape;
import lightning.product.p_602_A;
import lightning.product.SliceShape;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.CubePointRange;
import lightning.product.BooleanOp;

public final class x_268_Y {
    private static final s_1395_c J_1907_R = j_3341_s.n_1700_B(() -> {
        BitSetDiscreteVoxelShape voxelshapepart = new BitSetDiscreteVoxelShape(1, 1, 1);
        ((DiscreteVoxelShape)voxelshapepart).n_1700_B(0, 0, 0, true, true);
        return new CubeVoxelShape(voxelshapepart);
    });
    public static final s_1395_c n_1700_B = x_268_Y.n_1700_B(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    private static final s_1395_c R_4764_Y = new ArrayVoxelShape((DiscreteVoxelShape)new BitSetDiscreteVoxelShape(0, 0, 0), (DoubleList)new DoubleArrayList(new double[]{0.0}), (DoubleList)new DoubleArrayList(new double[]{0.0}), (DoubleList)new DoubleArrayList(new double[]{0.0}));

    public static s_1395_c n_1700_B() {
        return R_4764_Y;
    }

    public static s_1395_c J_1907_R() {
        return J_1907_R;
    }

    public static s_1395_c n_1700_B(double x1, double y1, double z1, double x2, double y2, double z2) {
        return x_268_Y.n_1700_B(new I_4817_s(x1, y1, z1, x2, y2, z2));
    }

    public static s_1395_c n_1700_B(I_4817_s aabb) {
        int i = x_268_Y.n_1700_B(aabb.minX, aabb.maxX);
        int j = x_268_Y.n_1700_B(aabb.minY, aabb.maxY);
        int k = x_268_Y.n_1700_B(aabb.minZ, aabb.maxZ);
        if (i >= 0 && j >= 0 && k >= 0) {
            if (i == 0 && j == 0 && k == 0) {
                return aabb.contains(0.5, 0.5, 0.5) ? x_268_Y.J_1907_R() : x_268_Y.n_1700_B();
            }
            int l = 1 << i;
            int i1 = 1 << j;
            int j1 = 1 << k;
            int k1 = (int)Math.round(aabb.minX * (double)l);
            int l1 = (int)Math.round(aabb.maxX * (double)l);
            int i2 = (int)Math.round(aabb.minY * (double)i1);
            int j2 = (int)Math.round(aabb.maxY * (double)i1);
            int k2 = (int)Math.round(aabb.minZ * (double)j1);
            int l2 = (int)Math.round(aabb.maxZ * (double)j1);
            BitSetDiscreteVoxelShape bitsetvoxelshapepart = new BitSetDiscreteVoxelShape(l, i1, j1, k1, i2, k2, l1, j2, l2);
            for (long i3 = (long)k1; i3 < (long)l1; ++i3) {
                for (long j3 = (long)i2; j3 < (long)j2; ++j3) {
                    for (long k3 = (long)k2; k3 < (long)l2; ++k3) {
                        bitsetvoxelshapepart.n_1700_B((int)i3, (int)j3, (int)k3, false, true);
                    }
                }
            }
            return new CubeVoxelShape(bitsetvoxelshapepart);
        }
        return new ArrayVoxelShape(x_268_Y.J_1907_R.n_1700_B, new double[]{aabb.minX, aabb.maxX}, new double[]{aabb.minY, aabb.maxY}, new double[]{aabb.minZ, aabb.maxZ});
    }

    private static int n_1700_B(double p_197885_0_, double p_197885_2_) {
        if (!(p_197885_0_ < -1.0E-7) && !(p_197885_2_ > 1.0000001)) {
            for (int i = 0; i <= 3; ++i) {
                boolean flag1;
                double d0 = p_197885_0_ * (double)(1 << i);
                double d1 = p_197885_2_ * (double)(1 << i);
                boolean flag = Math.abs(d0 - Math.floor(d0)) < 1.0E-7;
                boolean bl = flag1 = Math.abs(d1 - Math.floor(d1)) < 1.0E-7;
                if (!flag || !flag1) continue;
                return i;
            }
            return -1;
        }
        return -1;
    }

    protected static long n_1700_B(int aa, int bb) {
        return (long)aa * (long)(bb / IntMath.gcd((int)aa, (int)bb));
    }

    public static s_1395_c n_1700_B(s_1395_c shape1, s_1395_c shape2) {
        return x_268_Y.n_1700_B(shape1, shape2, BooleanOp.Q_4569_t);
    }

    public static s_1395_c n_1700_B(s_1395_c p_216384_0_, s_1395_c ... p_216384_1_) {
        return Arrays.stream(p_216384_1_).reduce(p_216384_0_, x_268_Y::n_1700_B);
    }

    public static s_1395_c n_1700_B(s_1395_c shape1, s_1395_c shape2, BooleanOp function) {
        return x_268_Y.J_1907_R(shape1, shape2, function).R_4764_Y();
    }

    public static s_1395_c J_1907_R(s_1395_c shape1, s_1395_c shape2, BooleanOp function) {
        if (function.apply(false, false)) {
            throw j_3341_s.R_4764_Y(new IllegalArgumentException());
        }
        if (shape1 == shape2) {
            return function.apply(true, true) ? shape1 : x_268_Y.n_1700_B();
        }
        boolean flag = function.apply(true, false);
        boolean flag1 = function.apply(false, true);
        if (shape1.J_1907_R()) {
            return flag1 ? shape2 : x_268_Y.n_1700_B();
        }
        if (shape2.J_1907_R()) {
            return flag ? shape1 : x_268_Y.n_1700_B();
        }
        IndexMerger idoublelistmerger = x_268_Y.n_1700_B(1, shape1.n_1700_B(b_257_Y.n_1700_B.n_1700_B), shape2.n_1700_B(b_257_Y.n_1700_B.n_1700_B), flag, flag1);
        IndexMerger idoublelistmerger1 = x_268_Y.n_1700_B(idoublelistmerger.n_1700_B().size() - 1, shape1.n_1700_B(b_257_Y.n_1700_B.J_1907_R), shape2.n_1700_B(b_257_Y.n_1700_B.J_1907_R), flag, flag1);
        IndexMerger idoublelistmerger2 = x_268_Y.n_1700_B((idoublelistmerger.n_1700_B().size() - 1) * (idoublelistmerger1.n_1700_B().size() - 1), shape1.n_1700_B(b_257_Y.n_1700_B.R_4764_Y), shape2.n_1700_B(b_257_Y.n_1700_B.R_4764_Y), flag, flag1);
        BitSetDiscreteVoxelShape bitsetvoxelshapepart = BitSetDiscreteVoxelShape.n_1700_B(shape1.n_1700_B, shape2.n_1700_B, idoublelistmerger, idoublelistmerger1, idoublelistmerger2, function);
        return idoublelistmerger instanceof DiscreteCubeMerger && idoublelistmerger1 instanceof DiscreteCubeMerger && idoublelistmerger2 instanceof DiscreteCubeMerger ? new CubeVoxelShape(bitsetvoxelshapepart) : new ArrayVoxelShape((DiscreteVoxelShape)bitsetvoxelshapepart, idoublelistmerger.n_1700_B(), idoublelistmerger1.n_1700_B(), idoublelistmerger2.n_1700_B());
    }

    public static boolean R_4764_Y(s_1395_c shape1, s_1395_c shape2, BooleanOp function) {
        if (function.apply(false, false)) {
            throw j_3341_s.R_4764_Y(new IllegalArgumentException());
        }
        if (shape1 == shape2) {
            return function.apply(true, true);
        }
        if (shape1.J_1907_R()) {
            return function.apply(false, !shape2.J_1907_R());
        }
        if (shape2.J_1907_R()) {
            return function.apply(!shape1.J_1907_R(), false);
        }
        boolean flag = function.apply(true, false);
        boolean flag1 = function.apply(false, true);
        for (b_257_Y.n_1700_B direction$axis : p_602_A.G_564_y) {
            if (shape1.R_4764_Y(direction$axis) < shape2.J_1907_R(direction$axis) - 1.0E-7) {
                return flag || flag1;
            }
            if (!(shape2.R_4764_Y(direction$axis) < shape1.J_1907_R(direction$axis) - 1.0E-7)) continue;
            return flag || flag1;
        }
        IndexMerger idoublelistmerger = x_268_Y.n_1700_B(1, shape1.n_1700_B(b_257_Y.n_1700_B.n_1700_B), shape2.n_1700_B(b_257_Y.n_1700_B.n_1700_B), flag, flag1);
        IndexMerger idoublelistmerger1 = x_268_Y.n_1700_B(idoublelistmerger.n_1700_B().size() - 1, shape1.n_1700_B(b_257_Y.n_1700_B.J_1907_R), shape2.n_1700_B(b_257_Y.n_1700_B.J_1907_R), flag, flag1);
        IndexMerger idoublelistmerger2 = x_268_Y.n_1700_B((idoublelistmerger.n_1700_B().size() - 1) * (idoublelistmerger1.n_1700_B().size() - 1), shape1.n_1700_B(b_257_Y.n_1700_B.R_4764_Y), shape2.n_1700_B(b_257_Y.n_1700_B.R_4764_Y), flag, flag1);
        return x_268_Y.n_1700_B(idoublelistmerger, idoublelistmerger1, idoublelistmerger2, shape1.n_1700_B, shape2.n_1700_B, function);
    }

    private static boolean n_1700_B(IndexMerger p_197874_0_, IndexMerger p_197874_1_, IndexMerger p_197874_2_, DiscreteVoxelShape p_197874_3_, DiscreteVoxelShape p_197874_4_, BooleanOp p_197874_5_) {
        return !p_197874_0_.n_1700_B((p_199861_5_, p_199861_6_, p_199861_7_) -> p_197874_1_.n_1700_B((p_199860_6_, p_199860_7_, p_199860_8_) -> p_197874_2_.n_1700_B((p_199862_7_, p_199862_8_, p_199862_9_) -> !p_197874_5_.apply(p_197874_3_.R_4764_Y(p_199861_5_, p_199860_6_, p_199862_7_), p_197874_4_.R_4764_Y(p_199861_6_, p_199860_7_, p_199862_8_)))));
    }

    public static double n_1700_B(b_257_Y.n_1700_B movementAxis, I_4817_s collisionBox, Stream<s_1395_c> possibleHits, double desiredOffset) {
        Iterator iterator = possibleHits.iterator();
        while (iterator.hasNext()) {
            if (Math.abs(desiredOffset) < 1.0E-7) {
                return 0.0;
            }
            desiredOffset = ((s_1395_c)iterator.next()).n_1700_B(movementAxis, collisionBox, desiredOffset);
        }
        return desiredOffset;
    }

    public static double n_1700_B(b_257_Y.n_1700_B movementAxis, I_4817_s collisionBox, T_1316_M worldReader, double desiredOffset, CollisionContext selectionContext, Stream<s_1395_c> possibleHits) {
        return x_268_Y.n_1700_B(collisionBox, worldReader, desiredOffset, selectionContext, p_602_A.n_1700_B(movementAxis, b_257_Y.n_1700_B.R_4764_Y), possibleHits);
    }

    private static double n_1700_B(I_4817_s collisionBox, T_1316_M worldReader, double desiredOffset, CollisionContext selectionContext, p_602_A rotationAxis, Stream<s_1395_c> possibleHits) {
        if (!(collisionBox.getXSize() < 1.0E-6 || collisionBox.getYSize() < 1.0E-6 || collisionBox.getZSize() < 1.0E-6)) {
            if (Math.abs(desiredOffset) < 1.0E-7) {
                return 0.0;
            }
            p_602_A axisrotation = rotationAxis.n_1700_B();
            b_257_Y.n_1700_B direction$axis = axisrotation.n_1700_B(b_257_Y.n_1700_B.n_1700_B);
            b_257_Y.n_1700_B direction$axis1 = axisrotation.n_1700_B(b_257_Y.n_1700_B.J_1907_R);
            b_257_Y.n_1700_B direction$axis2 = axisrotation.n_1700_B(b_257_Y.n_1700_B.R_4764_Y);
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            int i = u_530_F.R_4764_Y(collisionBox.getMin(direction$axis) - 1.0E-7) - 1;
            int j = u_530_F.R_4764_Y(collisionBox.getMax(direction$axis) + 1.0E-7) + 1;
            int k = u_530_F.R_4764_Y(collisionBox.getMin(direction$axis1) - 1.0E-7) - 1;
            int l = u_530_F.R_4764_Y(collisionBox.getMax(direction$axis1) + 1.0E-7) + 1;
            double d0 = collisionBox.getMin(direction$axis2) - 1.0E-7;
            double d1 = collisionBox.getMax(direction$axis2) + 1.0E-7;
            boolean flag = desiredOffset > 0.0;
            int i1 = flag ? u_530_F.R_4764_Y(collisionBox.getMax(direction$axis2) - 1.0E-7) - 1 : u_530_F.R_4764_Y(collisionBox.getMin(direction$axis2) + 1.0E-7) + 1;
            int j1 = x_268_Y.n_1700_B(desiredOffset, d0, d1);
            int k1 = flag ? 1 : -1;
            int l1 = i1;
            while (!(flag ? l1 > j1 : l1 < j1)) {
                for (int i2 = i; i2 <= j; ++i2) {
                    for (int j2 = k; j2 <= l; ++j2) {
                        int k2 = 0;
                        if (i2 == i || i2 == j) {
                            ++k2;
                        }
                        if (j2 == k || j2 == l) {
                            ++k2;
                        }
                        if (l1 == i1 || l1 == j1) {
                            ++k2;
                        }
                        if (k2 >= 3) continue;
                        blockpos$mutable.n_1700_B(axisrotation, i2, j2, l1);
                        K_4074_S blockstate = worldReader.getBlockState(blockpos$mutable);
                        if (k2 == 1 && !blockstate.G_564_y() || k2 == 2 && !blockstate.n_1700_B(a_3742_W.O_2151_c)) continue;
                        desiredOffset = blockstate.R_4764_Y((BlockGetter)worldReader, (c_1514_x)blockpos$mutable, selectionContext).n_1700_B(direction$axis2, collisionBox.offset(-blockpos$mutable.getX(), -blockpos$mutable.getY(), -blockpos$mutable.getZ()), desiredOffset);
                        if (Math.abs(desiredOffset) < 1.0E-7) {
                            return 0.0;
                        }
                        j1 = x_268_Y.n_1700_B(desiredOffset, d0, d1);
                    }
                }
                l1 += k1;
            }
            double[] adouble = new double[]{desiredOffset};
            possibleHits.forEach(p_216388_3_ -> {
                adouble[0] = p_216388_3_.n_1700_B(direction$axis2, collisionBox, adouble[0]);
            });
            return adouble[0];
        }
        return desiredOffset;
    }

    private static int n_1700_B(double desiredOffset, double min, double max) {
        return desiredOffset > 0.0 ? u_530_F.R_4764_Y(max + desiredOffset) + 1 : u_530_F.R_4764_Y(min + desiredOffset) - 1;
    }

    public static boolean n_1700_B(s_1395_c shape, s_1395_c adjacentShape, b_257_Y side) {
        if (shape == x_268_Y.J_1907_R() && adjacentShape == x_268_Y.J_1907_R()) {
            return true;
        }
        if (adjacentShape.J_1907_R()) {
            return false;
        }
        b_257_Y.n_1700_B direction$axis = side.h_1847_R();
        b_257_Y.J_1907_R direction$axisdirection = side.P_1922_E();
        s_1395_c voxelshape = direction$axisdirection == b_257_Y.J_1907_R.n_1700_B ? shape : adjacentShape;
        s_1395_c voxelshape1 = direction$axisdirection == b_257_Y.J_1907_R.n_1700_B ? adjacentShape : shape;
        BooleanOp ibooleanfunction = direction$axisdirection == b_257_Y.J_1907_R.n_1700_B ? BooleanOp.P_1922_E : BooleanOp.R_4764_Y;
        return DoubleMath.fuzzyEquals((double)voxelshape.R_4764_Y(direction$axis), (double)1.0, (double)1.0E-7) && DoubleMath.fuzzyEquals((double)voxelshape1.J_1907_R(direction$axis), (double)0.0, (double)1.0E-7) && !x_268_Y.R_4764_Y(new SliceShape(voxelshape, direction$axis, voxelshape.n_1700_B.R_4764_Y(direction$axis) - 1), new SliceShape(voxelshape1, direction$axis, 0), ibooleanfunction);
    }

    public static s_1395_c n_1700_B(s_1395_c voxelShapeIn, b_257_Y directionIn) {
        int i;
        boolean flag;
        if (voxelShapeIn == x_268_Y.J_1907_R()) {
            return x_268_Y.J_1907_R();
        }
        b_257_Y.n_1700_B direction$axis = directionIn.h_1847_R();
        if (directionIn.P_1922_E() == b_257_Y.J_1907_R.n_1700_B) {
            flag = DoubleMath.fuzzyEquals((double)voxelShapeIn.R_4764_Y(direction$axis), (double)1.0, (double)1.0E-7);
            i = voxelShapeIn.n_1700_B.R_4764_Y(direction$axis) - 1;
        } else {
            flag = DoubleMath.fuzzyEquals((double)voxelShapeIn.J_1907_R(direction$axis), (double)0.0, (double)1.0E-7);
            i = 0;
        }
        return !flag ? x_268_Y.n_1700_B() : new SliceShape(voxelShapeIn, direction$axis, i);
    }

    public static boolean J_1907_R(s_1395_c shape, s_1395_c adjacentShape, b_257_Y side) {
        if (shape != x_268_Y.J_1907_R() && adjacentShape != x_268_Y.J_1907_R()) {
            s_1395_c voxelshape1;
            b_257_Y.n_1700_B direction$axis = side.h_1847_R();
            b_257_Y.J_1907_R direction$axisdirection = side.P_1922_E();
            s_1395_c voxelshape = direction$axisdirection == b_257_Y.J_1907_R.n_1700_B ? shape : adjacentShape;
            s_1395_c s_1395_c2 = voxelshape1 = direction$axisdirection == b_257_Y.J_1907_R.n_1700_B ? adjacentShape : shape;
            if (!DoubleMath.fuzzyEquals((double)voxelshape.R_4764_Y(direction$axis), (double)1.0, (double)1.0E-7)) {
                voxelshape = x_268_Y.n_1700_B();
            }
            if (!DoubleMath.fuzzyEquals((double)voxelshape1.J_1907_R(direction$axis), (double)0.0, (double)1.0E-7)) {
                voxelshape1 = x_268_Y.n_1700_B();
            }
            return !x_268_Y.R_4764_Y(x_268_Y.J_1907_R(), x_268_Y.J_1907_R((s_1395_c)new SliceShape(voxelshape, direction$axis, voxelshape.n_1700_B.R_4764_Y(direction$axis) - 1), (s_1395_c)new SliceShape(voxelshape1, direction$axis, 0), BooleanOp.Q_4569_t), BooleanOp.P_1922_E);
        }
        return true;
    }

    public static boolean J_1907_R(s_1395_c voxelShape1, s_1395_c voxelShape2) {
        if (voxelShape1 != x_268_Y.J_1907_R() && voxelShape2 != x_268_Y.J_1907_R()) {
            if (voxelShape1.J_1907_R() && voxelShape2.J_1907_R()) {
                return false;
            }
            return !x_268_Y.R_4764_Y(x_268_Y.J_1907_R(), x_268_Y.J_1907_R(voxelShape1, voxelShape2, BooleanOp.Q_4569_t), BooleanOp.P_1922_E);
        }
        return true;
    }

    @VisibleForTesting
    protected static IndexMerger n_1700_B(int p_199410_0_, DoubleList list1, DoubleList list2, boolean p_199410_3_, boolean p_199410_4_) {
        long k;
        int i = list1.size() - 1;
        int j = list2.size() - 1;
        if (list1 instanceof CubePointRange && list2 instanceof CubePointRange && (long)p_199410_0_ * (k = x_268_Y.n_1700_B(i, j)) <= 256L) {
            return new DiscreteCubeMerger(i, j);
        }
        if (list1.getDouble(i) < list2.getDouble(0) - 1.0E-7) {
            return new NonOverlappingMerger(list1, list2, false);
        }
        if (list2.getDouble(j) < list1.getDouble(0) - 1.0E-7) {
            return new NonOverlappingMerger(list2, list1, true);
        }
        if (i == j && Objects.equals(list1, list2)) {
            if (list1 instanceof IdenticalMerger) {
                return (IndexMerger)list1;
            }
            return list2 instanceof IdenticalMerger ? (IndexMerger)list2 : new IdenticalMerger(list1);
        }
        return new IndirectMerger(list1, list2, p_199410_3_, p_199410_4_);
    }

    public static interface n_1700_B {
        public void consume(double var1, double var3, double var5, double var7, double var9, double var11);
    }
}


