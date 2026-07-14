/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.i_2154_H;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;

public interface BlockGetter {
    @Nullable
    public i_2154_H getTileEntity(c_1514_x var1);

    public K_4074_S getBlockState(c_1514_x var1);

    public FluidState getFluidState(c_1514_x var1);

    default public int R_4764_Y(c_1514_x pos) {
        return this.getBlockState(pos).u_1723_Y();
    }

    default public int Z_875_P() {
        return 15;
    }

    default public int c_3005_b() {
        return 256;
    }

    default public Stream<K_4074_S> n_1700_B(I_4817_s p_234853_1_) {
        return c_1514_x.getAllInBox(p_234853_1_).map(this::getBlockState);
    }

    default public BlockHitResult n_1700_B(ClipContext context) {
        return BlockGetter.n_1700_B(context, (p_217297_1_, p_217297_2_) -> {
            K_4074_S blockstate = this.getBlockState((c_1514_x)p_217297_2_);
            FluidState fluidstate = this.getFluidState((c_1514_x)p_217297_2_);
            e_2866_D vector3d = p_217297_1_.J_1907_R();
            e_2866_D vector3d1 = p_217297_1_.n_1700_B();
            s_1395_c voxelshape = p_217297_1_.n_1700_B(blockstate, this, (c_1514_x)p_217297_2_);
            BlockHitResult blockraytraceresult = this.n_1700_B(vector3d, vector3d1, (c_1514_x)p_217297_2_, voxelshape, blockstate);
            s_1395_c voxelshape1 = p_217297_1_.n_1700_B(fluidstate, this, (c_1514_x)p_217297_2_);
            BlockHitResult blockraytraceresult1 = voxelshape1.n_1700_B(vector3d, vector3d1, (c_1514_x)p_217297_2_);
            double d0 = blockraytraceresult == null ? Double.MAX_VALUE : p_217297_1_.J_1907_R().v_4262_N(blockraytraceresult.P_1922_E());
            double d1 = blockraytraceresult1 == null ? Double.MAX_VALUE : p_217297_1_.J_1907_R().v_4262_N(blockraytraceresult1.P_1922_E());
            return d0 <= d1 ? blockraytraceresult : blockraytraceresult1;
        }, p_217302_0_ -> {
            e_2866_D vector3d = p_217302_0_.J_1907_R().G_564_y(p_217302_0_.n_1700_B());
            return BlockHitResult.n_1700_B(p_217302_0_.n_1700_B(), b_257_Y.n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y), new c_1514_x(p_217302_0_.n_1700_B()));
        });
    }

    @Nullable
    default public BlockHitResult n_1700_B(e_2866_D startVec, e_2866_D endVec, c_1514_x pos, s_1395_c shape, K_4074_S state) {
        BlockHitResult blockraytraceresult1;
        BlockHitResult blockraytraceresult = shape.n_1700_B(startVec, endVec, pos);
        if (blockraytraceresult != null && (blockraytraceresult1 = state.P_4830_p(this, pos).n_1700_B(startVec, endVec, pos)) != null && blockraytraceresult1.P_1922_E().G_564_y(startVec).v_4262_N() < blockraytraceresult.P_1922_E().G_564_y(startVec).v_4262_N()) {
            return blockraytraceresult.n_1700_B(blockraytraceresult1.J_1907_R());
        }
        return blockraytraceresult;
    }

    default public double n_1700_B(s_1395_c p_242402_1_, Supplier<s_1395_c> p_242402_2_) {
        if (!p_242402_1_.J_1907_R()) {
            return p_242402_1_.R_4764_Y(b_257_Y.n_1700_B.J_1907_R);
        }
        double d0 = p_242402_2_.get().R_4764_Y(b_257_Y.n_1700_B.J_1907_R);
        return d0 >= 1.0 ? d0 - 1.0 : Double.NEGATIVE_INFINITY;
    }

    default public double G_564_y(c_1514_x p_242403_1_) {
        return this.n_1700_B(this.getBlockState(p_242403_1_).u_2550_I(this, p_242403_1_), () -> {
            c_1514_x blockpos = p_242403_1_.down();
            return this.getBlockState(blockpos).u_2550_I(this, blockpos);
        });
    }

    public static <T> T n_1700_B(ClipContext context, BiFunction<ClipContext, c_1514_x, T> rayTracer, Function<ClipContext, T> missFactory) {
        int k;
        int j;
        e_2866_D vector3d1;
        e_2866_D vector3d = context.J_1907_R();
        if (vector3d.equals(vector3d1 = context.n_1700_B())) {
            return missFactory.apply(context);
        }
        double d0 = u_530_F.G_564_y(-1.0E-7, vector3d1.J_1907_R, vector3d.J_1907_R);
        double d1 = u_530_F.G_564_y(-1.0E-7, vector3d1.R_4764_Y, vector3d.R_4764_Y);
        double d2 = u_530_F.G_564_y(-1.0E-7, vector3d1.G_564_y, vector3d.G_564_y);
        double d3 = u_530_F.G_564_y(-1.0E-7, vector3d.J_1907_R, vector3d1.J_1907_R);
        double d4 = u_530_F.G_564_y(-1.0E-7, vector3d.R_4764_Y, vector3d1.R_4764_Y);
        double d5 = u_530_F.G_564_y(-1.0E-7, vector3d.G_564_y, vector3d1.G_564_y);
        int i = u_530_F.R_4764_Y(d3);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(i, j = u_530_F.R_4764_Y(d4), k = u_530_F.R_4764_Y(d5));
        T t = rayTracer.apply(context, blockpos$mutable);
        if (t != null) {
            return t;
        }
        double d6 = d0 - d3;
        double d7 = d1 - d4;
        double d8 = d2 - d5;
        int l = u_530_F.s_956_w(d6);
        int i1 = u_530_F.s_956_w(d7);
        int j1 = u_530_F.s_956_w(d8);
        double d9 = l == 0 ? Double.MAX_VALUE : (double)l / d6;
        double d10 = i1 == 0 ? Double.MAX_VALUE : (double)i1 / d7;
        double d11 = j1 == 0 ? Double.MAX_VALUE : (double)j1 / d8;
        double d12 = d9 * (l > 0 ? 1.0 - u_530_F.v_4262_N(d3) : u_530_F.v_4262_N(d3));
        double d13 = d10 * (i1 > 0 ? 1.0 - u_530_F.v_4262_N(d4) : u_530_F.v_4262_N(d4));
        double d14 = d11 * (j1 > 0 ? 1.0 - u_530_F.v_4262_N(d5) : u_530_F.v_4262_N(d5));
        while (d12 <= 1.0 || d13 <= 1.0 || d14 <= 1.0) {
            T t1;
            if (d12 < d13) {
                if (d12 < d14) {
                    i += l;
                    d12 += d9;
                } else {
                    k += j1;
                    d14 += d11;
                }
            } else if (d13 < d14) {
                j += i1;
                d13 += d10;
            } else {
                k += j1;
                d14 += d11;
            }
            if ((t1 = rayTracer.apply(context, blockpos$mutable.n_1700_B(i, j, k))) == null) continue;
            return t1;
        }
        return missFactory.apply(context);
    }
}


