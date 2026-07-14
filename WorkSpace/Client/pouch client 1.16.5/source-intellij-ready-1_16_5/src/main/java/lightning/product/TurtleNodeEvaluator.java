/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.D_1436_R;
import lightning.product.BlockGetter;
import lightning.product.I_1869_h;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.Target;
import lightning.product.PathNavigationRegion;
import lightning.product.Z_530_i;
import lightning.product.Z_535_q;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.g_2711_h;
import lightning.product.BlockTags;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;

public class TurtleNodeEvaluator
extends Z_535_q {
    private float u_2550_I;
    private float M_588_G;

    @Override
    public void n_1700_B(PathNavigationRegion p_225578_1_, Z_530_i p_225578_2_) {
        super.n_1700_B(p_225578_1_, p_225578_2_);
        p_225578_2_.n_1700_B(I_1869_h.w_1484_f, 0.0f);
        this.u_2550_I = p_225578_2_.n_1700_B(I_1869_h.R_4764_Y);
        p_225578_2_.n_1700_B(I_1869_h.R_4764_Y, 6.0f);
        this.M_588_G = p_225578_2_.n_1700_B(I_1869_h.t_148_a);
        p_225578_2_.n_1700_B(I_1869_h.t_148_a, 4.0f);
    }

    @Override
    public void n_1700_B() {
        this.J_1907_R.n_1700_B(I_1869_h.R_4764_Y, this.u_2550_I);
        this.J_1907_R.n_1700_B(I_1869_h.t_148_a, this.M_588_G);
        super.n_1700_B();
    }

    @Override
    public D_1436_R J_1907_R() {
        return this.n_1700_B(u_530_F.R_4764_Y(this.J_1907_R.i_601_W().minX), u_530_F.R_4764_Y(this.J_1907_R.i_601_W().minY + 0.5), u_530_F.R_4764_Y(this.J_1907_R.i_601_W().minZ));
    }

    @Override
    public Target n_1700_B(double p_224768_1_, double p_224768_3_, double p_224768_5_) {
        return new Target(this.n_1700_B(u_530_F.R_4764_Y(p_224768_1_), u_530_F.R_4764_Y(p_224768_3_ + 0.5), u_530_F.R_4764_Y(p_224768_5_)));
    }

    @Override
    public int n_1700_B(D_1436_R[] p_222859_1_, D_1436_R p_222859_2_) {
        D_1436_R pathpoint9;
        D_1436_R pathpoint8;
        D_1436_R pathpoint7;
        D_1436_R pathpoint6;
        boolean flag3;
        int i = 0;
        boolean j = true;
        c_1514_x blockpos = new c_1514_x(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y);
        double d0 = this.J_1907_R(blockpos);
        D_1436_R pathpoint = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y + 1, 1, d0);
        D_1436_R pathpoint1 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y, 1, d0);
        D_1436_R pathpoint2 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y, 1, d0);
        D_1436_R pathpoint3 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y - 1, 1, d0);
        D_1436_R pathpoint4 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y, 0, d0);
        D_1436_R pathpoint5 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R - 1, p_222859_2_.R_4764_Y, 1, d0);
        if (pathpoint != null && !pathpoint.t_148_a) {
            p_222859_1_[i++] = pathpoint;
        }
        if (pathpoint1 != null && !pathpoint1.t_148_a) {
            p_222859_1_[i++] = pathpoint1;
        }
        if (pathpoint2 != null && !pathpoint2.t_148_a) {
            p_222859_1_[i++] = pathpoint2;
        }
        if (pathpoint3 != null && !pathpoint3.t_148_a) {
            p_222859_1_[i++] = pathpoint3;
        }
        if (pathpoint4 != null && !pathpoint4.t_148_a) {
            p_222859_1_[i++] = pathpoint4;
        }
        if (pathpoint5 != null && !pathpoint5.t_148_a) {
            p_222859_1_[i++] = pathpoint5;
        }
        boolean flag = pathpoint3 == null || pathpoint3.M_588_G == I_1869_h.J_1907_R || pathpoint3.u_2550_I != 0.0f;
        boolean flag1 = pathpoint == null || pathpoint.M_588_G == I_1869_h.J_1907_R || pathpoint.u_2550_I != 0.0f;
        boolean flag2 = pathpoint2 == null || pathpoint2.M_588_G == I_1869_h.J_1907_R || pathpoint2.u_2550_I != 0.0f;
        boolean bl = flag3 = pathpoint1 == null || pathpoint1.M_588_G == I_1869_h.J_1907_R || pathpoint1.u_2550_I != 0.0f;
        if (flag && flag3 && (pathpoint6 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y - 1, 1, d0)) != null && !pathpoint6.t_148_a) {
            p_222859_1_[i++] = pathpoint6;
        }
        if (flag && flag2 && (pathpoint7 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y - 1, 1, d0)) != null && !pathpoint7.t_148_a) {
            p_222859_1_[i++] = pathpoint7;
        }
        if (flag1 && flag3 && (pathpoint8 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y + 1, 1, d0)) != null && !pathpoint8.t_148_a) {
            p_222859_1_[i++] = pathpoint8;
        }
        if (flag1 && flag2 && (pathpoint9 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y + 1, 1, d0)) != null && !pathpoint9.t_148_a) {
            p_222859_1_[i++] = pathpoint9;
        }
        return i;
    }

    private double J_1907_R(c_1514_x p_203246_1_) {
        if (!this.J_1907_R.RowButton()) {
            c_1514_x blockpos = p_203246_1_.down();
            s_1395_c voxelshape = this.n_1700_B.getBlockState(blockpos).u_2550_I(this.n_1700_B, blockpos);
            return (double)blockpos.getY() + (voxelshape.J_1907_R() ? 0.0 : voxelshape.R_4764_Y(b_257_Y.n_1700_B.J_1907_R));
        }
        return (double)p_203246_1_.getY() + 0.5;
    }

    @Nullable
    private D_1436_R n_1700_B(int p_203245_1_, int p_203245_2_, int p_203245_3_, int p_203245_4_, double p_203245_5_) {
        D_1436_R pathpoint = null;
        c_1514_x blockpos = new c_1514_x(p_203245_1_, p_203245_2_, p_203245_3_);
        double d0 = this.J_1907_R(blockpos);
        if (d0 - p_203245_5_ > 1.125) {
            return null;
        }
        I_1869_h pathnodetype = this.n_1700_B(this.n_1700_B, p_203245_1_, p_203245_2_, p_203245_3_, this.J_1907_R, this.G_564_y, this.P_1922_E, this.u_1723_Y, false, false);
        float f = this.J_1907_R.n_1700_B(pathnodetype);
        double d1 = (double)this.J_1907_R.C_415_h() / 2.0;
        if (f >= 0.0f) {
            pathpoint = this.n_1700_B(p_203245_1_, p_203245_2_, p_203245_3_);
            pathpoint.M_588_G = pathnodetype;
            pathpoint.u_2550_I = Math.max(pathpoint.u_2550_I, f);
        }
        if (pathnodetype != I_1869_h.w_1484_f && pathnodetype != I_1869_h.R_4764_Y) {
            if (pathpoint == null && p_203245_4_ > 0 && pathnodetype != I_1869_h.u_1723_Y && pathnodetype != I_1869_h.u_2550_I && pathnodetype != I_1869_h.P_1922_E) {
                pathpoint = this.n_1700_B(p_203245_1_, p_203245_2_ + 1, p_203245_3_, p_203245_4_ - 1, p_203245_5_);
            }
            if (pathnodetype == I_1869_h.J_1907_R) {
                I_4817_s axisalignedbb = new I_4817_s((double)p_203245_1_ - d1 + 0.5, (double)p_203245_2_ + 0.001, (double)p_203245_3_ - d1 + 0.5, (double)p_203245_1_ + d1 + 0.5, (float)p_203245_2_ + this.J_1907_R.v_165_F(), (double)p_203245_3_ + d1 + 0.5);
                if (!this.J_1907_R.O_508_d.a_(this.J_1907_R, axisalignedbb)) {
                    return null;
                }
                I_1869_h pathnodetype1 = this.n_1700_B(this.n_1700_B, p_203245_1_, p_203245_2_ - 1, p_203245_3_, this.J_1907_R, this.G_564_y, this.P_1922_E, this.u_1723_Y, false, false);
                if (pathnodetype1 == I_1869_h.n_1700_B) {
                    pathpoint = this.n_1700_B(p_203245_1_, p_203245_2_, p_203245_3_);
                    pathpoint.M_588_G = I_1869_h.R_4764_Y;
                    pathpoint.u_2550_I = Math.max(pathpoint.u_2550_I, f);
                    return pathpoint;
                }
                if (pathnodetype1 == I_1869_h.w_1484_f) {
                    pathpoint = this.n_1700_B(p_203245_1_, p_203245_2_, p_203245_3_);
                    pathpoint.M_588_G = I_1869_h.w_1484_f;
                    pathpoint.u_2550_I = Math.max(pathpoint.u_2550_I, f);
                    return pathpoint;
                }
                int i = 0;
                while (p_203245_2_ > 0 && pathnodetype == I_1869_h.J_1907_R) {
                    --p_203245_2_;
                    if (i++ >= this.J_1907_R.n_3197_X()) {
                        return null;
                    }
                    pathnodetype = this.n_1700_B(this.n_1700_B, p_203245_1_, p_203245_2_, p_203245_3_, this.J_1907_R, this.G_564_y, this.P_1922_E, this.u_1723_Y, false, false);
                    f = this.J_1907_R.n_1700_B(pathnodetype);
                    if (pathnodetype != I_1869_h.J_1907_R && f >= 0.0f) {
                        pathpoint = this.n_1700_B(p_203245_1_, p_203245_2_, p_203245_3_);
                        pathpoint.M_588_G = pathnodetype;
                        pathpoint.u_2550_I = Math.max(pathpoint.u_2550_I, f);
                        break;
                    }
                    if (!(f < 0.0f)) continue;
                    return null;
                }
            }
            return pathpoint;
        }
        if (p_203245_2_ < this.J_1907_R.O_508_d.d_2461_k() - 10 && pathpoint != null) {
            pathpoint.u_2550_I += 1.0f;
        }
        return pathpoint;
    }

    @Override
    protected I_1869_h n_1700_B(BlockGetter p_215744_1_, boolean p_215744_2_, boolean p_215744_3_, c_1514_x p_215744_4_, I_1869_h p_215744_5_) {
        if (p_215744_5_ == I_1869_h.s_956_w && !(p_215744_1_.getBlockState(p_215744_4_).J_1907_R() instanceof g_2711_h) && !(p_215744_1_.getBlockState(p_215744_4_.down()).J_1907_R() instanceof g_2711_h)) {
            p_215744_5_ = I_1869_h.u_2550_I;
        }
        if (p_215744_5_ == I_1869_h.multiplayerClientSuggestionProvider || p_215744_5_ == I_1869_h.w_1457_N || p_215744_5_ == I_1869_h.Y_601_j) {
            p_215744_5_ = I_1869_h.n_1700_B;
        }
        if (p_215744_5_ == I_1869_h.Q_2552_b) {
            p_215744_5_ = I_1869_h.n_1700_B;
        }
        return p_215744_5_;
    }

    @Override
    public I_1869_h n_1700_B(BlockGetter blockaccessIn, int x, int y, int z) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        I_1869_h pathnodetype = TurtleNodeEvaluator.J_1907_R(blockaccessIn, blockpos$mutable.n_1700_B(x, y, z));
        if (pathnodetype == I_1869_h.w_1484_f) {
            for (b_257_Y direction : b_257_Y.values()) {
                I_1869_h pathnodetype2 = TurtleNodeEvaluator.J_1907_R(blockaccessIn, blockpos$mutable.n_1700_B(x, y, z).n_1700_B(direction));
                if (pathnodetype2 != I_1869_h.n_1700_B) continue;
                return I_1869_h.t_148_a;
            }
            return I_1869_h.w_1484_f;
        }
        if (pathnodetype == I_1869_h.J_1907_R && y >= 1) {
            K_4074_S blockstate = blockaccessIn.getBlockState(new c_1514_x(x, y - 1, z));
            I_1869_h pathnodetype1 = TurtleNodeEvaluator.J_1907_R(blockaccessIn, blockpos$mutable.n_1700_B(x, y - 1, z));
            pathnodetype = pathnodetype1 != I_1869_h.R_4764_Y && pathnodetype1 != I_1869_h.J_1907_R && pathnodetype1 != I_1869_h.v_4262_N ? I_1869_h.R_4764_Y : I_1869_h.J_1907_R;
            if (pathnodetype1 == I_1869_h.P_4830_p || blockstate.n_1700_B(a_3742_W.LevitationControl) || blockstate.n_1700_B(BlockTags.U_1241_n)) {
                pathnodetype = I_1869_h.P_4830_p;
            }
            if (pathnodetype1 == I_1869_h.Q_4569_t) {
                pathnodetype = I_1869_h.Q_4569_t;
            }
            if (pathnodetype1 == I_1869_h.t_1786_h) {
                pathnodetype = I_1869_h.t_1786_h;
            }
        }
        if (pathnodetype == I_1869_h.R_4764_Y) {
            pathnodetype = TurtleNodeEvaluator.n_1700_B(blockaccessIn, blockpos$mutable.n_1700_B(x, y, z), pathnodetype);
        }
        return pathnodetype;
    }
}



