/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.K_4074_S;
import lightning.product.O_3671_t;
import lightning.product.R_1815_U;
import lightning.product.BlockUtil;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_1186_l;
import lightning.product.BlockTags;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;

public class Z_4149_q {
    private static final q_4293_E.R_4764_Y n_1700_B = (state, blockReader, pos) -> state.n_1700_B(a_3742_W.ClientBootstrap);
    private final LevelAccessor J_1907_R;
    private final b_257_Y.n_1700_B R_4764_Y;
    private final b_257_Y G_564_y;
    private int P_1922_E;
    @Nullable
    private c_1514_x u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;

    public static Optional<Z_4149_q> n_1700_B(LevelAccessor world, c_1514_x pos, b_257_Y.n_1700_B axis) {
        return Z_4149_q.n_1700_B(world, pos, (Z_4149_q size) -> size.n_1700_B() && size.P_1922_E == 0, axis);
    }

    public static Optional<Z_4149_q> n_1700_B(LevelAccessor world, c_1514_x pos, Predicate<Z_4149_q> sizePredicate, b_257_Y.n_1700_B axis) {
        Optional<Z_4149_q> optional = Optional.of(new Z_4149_q(world, pos, axis)).filter(sizePredicate);
        if (optional.isPresent()) {
            return optional;
        }
        b_257_Y.n_1700_B direction$axis = axis == b_257_Y.n_1700_B.n_1700_B ? b_257_Y.n_1700_B.R_4764_Y : b_257_Y.n_1700_B.n_1700_B;
        return Optional.of(new Z_4149_q(world, pos, direction$axis)).filter(sizePredicate);
    }

    public Z_4149_q(LevelAccessor worldIn, c_1514_x pos, b_257_Y.n_1700_B axisIn) {
        this.J_1907_R = worldIn;
        this.R_4764_Y = axisIn;
        this.G_564_y = axisIn == b_257_Y.n_1700_B.n_1700_B ? b_257_Y.P_1922_E : b_257_Y.G_564_y;
        this.u_1723_Y = this.n_1700_B(pos);
        if (this.u_1723_Y == null) {
            this.u_1723_Y = pos;
            this.w_1484_f = 1;
            this.v_4262_N = 1;
        } else {
            this.w_1484_f = this.G_564_y();
            if (this.w_1484_f > 0) {
                this.v_4262_N = this.P_1922_E();
            }
        }
    }

    @Nullable
    private c_1514_x n_1700_B(c_1514_x pos) {
        int i = Math.max(0, pos.getY() - 21);
        while (pos.getY() > i && Z_4149_q.n_1700_B(this.J_1907_R.getBlockState(pos.down()))) {
            pos = pos.down();
        }
        b_257_Y direction = this.G_564_y.u_1723_Y();
        int j = this.n_1700_B(pos, direction) - 1;
        return j < 0 ? null : pos.offset(direction, j);
    }

    private int G_564_y() {
        int i = this.n_1700_B(this.u_1723_Y, this.G_564_y);
        return i >= 2 && i <= 21 ? i : 0;
    }

    private int n_1700_B(c_1514_x pos, b_257_Y direction) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i = 0; i <= 21; ++i) {
            blockpos$mutable.n_1700_B(pos).n_1700_B(direction, i);
            K_4074_S blockstate = this.J_1907_R.getBlockState(blockpos$mutable);
            if (!Z_4149_q.n_1700_B(blockstate)) {
                if (!n_1700_B.test(blockstate, this.J_1907_R, blockpos$mutable)) break;
                return i;
            }
            K_4074_S blockstate1 = this.J_1907_R.getBlockState(blockpos$mutable.n_1700_B(b_257_Y.n_1700_B));
            if (!n_1700_B.test(blockstate1, this.J_1907_R, blockpos$mutable)) break;
        }
        return 0;
    }

    private int P_1922_E() {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        int i = this.n_1700_B(blockpos$mutable);
        return i >= 3 && i <= 21 && this.n_1700_B(blockpos$mutable, i) ? i : 0;
    }

    private boolean n_1700_B(c_1514_x.n_1700_B mutablePos, int upDisplacement) {
        for (int i = 0; i < this.w_1484_f; ++i) {
            c_1514_x.n_1700_B blockpos$mutable = mutablePos.n_1700_B(this.u_1723_Y).n_1700_B(b_257_Y.J_1907_R, upDisplacement).n_1700_B(this.G_564_y, i);
            if (n_1700_B.test(this.J_1907_R.getBlockState(blockpos$mutable), this.J_1907_R, blockpos$mutable)) continue;
            return false;
        }
        return true;
    }

    private int n_1700_B(c_1514_x.n_1700_B mutablePos) {
        for (int i = 0; i < 21; ++i) {
            mutablePos.n_1700_B(this.u_1723_Y).n_1700_B(b_257_Y.J_1907_R, i).n_1700_B(this.G_564_y, -1);
            if (!n_1700_B.test(this.J_1907_R.getBlockState(mutablePos), this.J_1907_R, mutablePos)) {
                return i;
            }
            mutablePos.n_1700_B(this.u_1723_Y).n_1700_B(b_257_Y.J_1907_R, i).n_1700_B(this.G_564_y, this.w_1484_f);
            if (!n_1700_B.test(this.J_1907_R.getBlockState(mutablePos), this.J_1907_R, mutablePos)) {
                return i;
            }
            for (int j = 0; j < this.w_1484_f; ++j) {
                mutablePos.n_1700_B(this.u_1723_Y).n_1700_B(b_257_Y.J_1907_R, i).n_1700_B(this.G_564_y, j);
                K_4074_S blockstate = this.J_1907_R.getBlockState(mutablePos);
                if (!Z_4149_q.n_1700_B(blockstate)) {
                    return i;
                }
                if (!blockstate.n_1700_B(a_3742_W.M_766_z)) continue;
                ++this.P_1922_E;
            }
        }
        return 21;
    }

    private static boolean n_1700_B(K_4074_S state) {
        return state.v_4262_N() || state.n_1700_B(BlockTags.j_276_v) || state.n_1700_B(a_3742_W.M_766_z);
    }

    public boolean n_1700_B() {
        return this.u_1723_Y != null && this.w_1484_f >= 2 && this.w_1484_f <= 21 && this.v_4262_N >= 3 && this.v_4262_N <= 21;
    }

    public void J_1907_R() {
        K_4074_S blockstate = (K_4074_S)a_3742_W.M_766_z.multiplayerClientSuggestionProvider().n_1700_B(O_3671_t.P_4830_p, this.R_4764_Y);
        c_1514_x.getAllInBoxMutable(this.u_1723_Y, this.u_1723_Y.offset(b_257_Y.J_1907_R, this.v_4262_N - 1).offset(this.G_564_y, this.w_1484_f - 1)).forEach(pos -> this.J_1907_R.n_1700_B((c_1514_x)pos, blockstate, 18));
    }

    public boolean R_4764_Y() {
        return this.n_1700_B() && this.P_1922_E == this.w_1484_f * this.v_4262_N;
    }

    public static e_2866_D n_1700_B(BlockUtil.J_1907_R result, b_257_Y.n_1700_B axis, e_2866_D positionVector, R_1815_U size) {
        double d4;
        double d2;
        double d0 = (double)result.J_1907_R - (double)size.n_1700_B;
        double d1 = (double)result.R_4764_Y - (double)size.J_1907_R;
        c_1514_x blockpos = result.n_1700_B;
        if (d0 > 0.0) {
            float f = (float)blockpos.func_243648_a(axis) + size.n_1700_B / 2.0f;
            d2 = u_530_F.n_1700_B(u_530_F.R_4764_Y(positionVector.n_1700_B(axis) - (double)f, 0.0, d0), 0.0, 1.0);
        } else {
            d2 = 0.5;
        }
        if (d1 > 0.0) {
            b_257_Y.n_1700_B direction$axis = b_257_Y.n_1700_B.J_1907_R;
            d4 = u_530_F.n_1700_B(u_530_F.R_4764_Y(positionVector.n_1700_B(direction$axis) - (double)blockpos.func_243648_a(direction$axis), 0.0, d1), 0.0, 1.0);
        } else {
            d4 = 0.0;
        }
        b_257_Y.n_1700_B direction$axis1 = axis == b_257_Y.n_1700_B.n_1700_B ? b_257_Y.n_1700_B.R_4764_Y : b_257_Y.n_1700_B.n_1700_B;
        double d3 = positionVector.n_1700_B(direction$axis1) - ((double)blockpos.func_243648_a(direction$axis1) + 0.5);
        return new e_2866_D(d2, d4, d3);
    }

    public static f_1186_l n_1700_B(e_3591_l world, BlockUtil.J_1907_R result, b_257_Y.n_1700_B axis, e_2866_D offsetVector, R_1815_U size, e_2866_D motion, float rotationYaw, float rotationPitch) {
        c_1514_x blockpos = result.n_1700_B;
        K_4074_S blockstate = world.getBlockState(blockpos);
        b_257_Y.n_1700_B direction$axis = blockstate.R_4764_Y(BlockStateProperties.t_4043_B);
        double d0 = result.J_1907_R;
        double d1 = result.R_4764_Y;
        int i = axis == direction$axis ? 0 : 90;
        e_2866_D vector3d = axis == direction$axis ? motion : new e_2866_D(motion.G_564_y, motion.R_4764_Y, -motion.J_1907_R);
        double d2 = (double)size.n_1700_B / 2.0 + (d0 - (double)size.n_1700_B) * offsetVector.n_1700_B();
        double d3 = (d1 - (double)size.J_1907_R) * offsetVector.J_1907_R();
        double d4 = 0.5 + offsetVector.R_4764_Y();
        boolean flag = direction$axis == b_257_Y.n_1700_B.n_1700_B;
        e_2866_D vector3d1 = new e_2866_D((double)blockpos.getX() + (flag ? d2 : d4), (double)blockpos.getY() + d3, (double)blockpos.getZ() + (flag ? d4 : d2));
        return new f_1186_l(vector3d1, vector3d, rotationYaw + (float)i, rotationPitch);
    }
}



