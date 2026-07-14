/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.I_1170_F;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.o_3283_D;
import lightning.product.BlockTags;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.t_5_h;
import lightning.product.x_268_Y;
import lightning.product.x_2838_H;

public class G_652_w {
    public static int[][] n_1700_B(b_257_Y p_234632_0_) {
        b_257_Y direction = p_234632_0_.v_4262_N();
        b_257_Y direction1 = direction.u_1723_Y();
        b_257_Y direction2 = p_234632_0_.u_1723_Y();
        return new int[][]{{direction.t_148_a(), direction.u_2550_I()}, {direction1.t_148_a(), direction1.u_2550_I()}, {direction2.t_148_a() + direction.t_148_a(), direction2.u_2550_I() + direction.u_2550_I()}, {direction2.t_148_a() + direction1.t_148_a(), direction2.u_2550_I() + direction1.u_2550_I()}, {p_234632_0_.t_148_a() + direction.t_148_a(), p_234632_0_.u_2550_I() + direction.u_2550_I()}, {p_234632_0_.t_148_a() + direction1.t_148_a(), p_234632_0_.u_2550_I() + direction1.u_2550_I()}, {direction2.t_148_a(), direction2.u_2550_I()}, {p_234632_0_.t_148_a(), p_234632_0_.u_2550_I()}};
    }

    public static boolean n_1700_B(double p_234630_0_) {
        return !Double.isInfinite(p_234630_0_) && p_234630_0_ < 1.0;
    }

    public static boolean n_1700_B(o_3283_D p_234631_0_, r_4811_B p_234631_1_, I_4817_s p_234631_2_) {
        return p_234631_0_.J_1907_R(p_234631_1_, p_234631_2_).allMatch(s_1395_c::J_1907_R);
    }

    @Nullable
    public static e_2866_D n_1700_B(o_3283_D p_242381_0_, double p_242381_1_, double p_242381_3_, double p_242381_5_, r_4811_B p_242381_7_, I_1170_F p_242381_8_) {
        if (G_652_w.n_1700_B(p_242381_3_)) {
            e_2866_D vector3d = new e_2866_D(p_242381_1_, p_242381_3_, p_242381_5_);
            if (G_652_w.n_1700_B(p_242381_0_, p_242381_7_, p_242381_7_.u_1723_Y(p_242381_8_).offset(vector3d))) {
                return vector3d;
            }
        }
        return null;
    }

    public static s_1395_c n_1700_B(BlockGetter p_242380_0_, c_1514_x p_242380_1_) {
        K_4074_S blockstate = p_242380_0_.getBlockState(p_242380_1_);
        return !blockstate.n_1700_B(BlockTags.h_4320_q) && (!(blockstate.J_1907_R() instanceof x_2838_H) || blockstate.R_4764_Y(x_2838_H.P_4830_p) == false) ? blockstate.u_2550_I(p_242380_0_, p_242380_1_) : x_268_Y.n_1700_B();
    }

    public static double n_1700_B(c_1514_x p_242383_0_, int p_242383_1_, Function<c_1514_x, s_1395_c> p_242383_2_) {
        c_1514_x.n_1700_B blockpos$mutable = p_242383_0_.toMutable();
        for (int i = 0; i < p_242383_1_; ++i) {
            s_1395_c voxelshape = p_242383_2_.apply(blockpos$mutable);
            if (!voxelshape.J_1907_R()) {
                return (double)(p_242383_0_.getY() + i) + voxelshape.J_1907_R(b_257_Y.n_1700_B.J_1907_R);
            }
            blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
        }
        return Double.POSITIVE_INFINITY;
    }

    @Nullable
    public static e_2866_D n_1700_B(t_5_h<?> p_242379_0_, o_3283_D p_242379_1_, c_1514_x p_242379_2_, boolean p_242379_3_) {
        if (p_242379_3_ && p_242379_0_.n_1700_B(p_242379_1_.getBlockState(p_242379_2_))) {
            return null;
        }
        double d0 = p_242379_1_.n_1700_B(G_652_w.n_1700_B((BlockGetter)p_242379_1_, p_242379_2_), () -> G_652_w.n_1700_B((BlockGetter)p_242379_1_, p_242379_2_.down()));
        if (!G_652_w.n_1700_B(d0)) {
            return null;
        }
        if (p_242379_3_ && d0 <= 0.0 && p_242379_0_.n_1700_B(p_242379_1_.getBlockState(p_242379_2_.down()))) {
            return null;
        }
        e_2866_D vector3d = e_2866_D.n_1700_B(p_242379_2_, d0);
        return p_242379_1_.J_1907_R(null, p_242379_0_.u_2550_I().n_1700_B(vector3d)).allMatch(s_1395_c::J_1907_R) ? vector3d : null;
    }
}


