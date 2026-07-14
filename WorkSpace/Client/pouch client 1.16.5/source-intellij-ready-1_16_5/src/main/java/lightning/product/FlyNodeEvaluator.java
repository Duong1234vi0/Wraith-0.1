/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.HashSet;
import javax.annotation.Nullable;
import lightning.product.D_1436_R;
import lightning.product.BlockGetter;
import lightning.product.I_1869_h;
import lightning.product.K_4074_S;
import lightning.product.Target;
import lightning.product.T_2915_h;
import lightning.product.PathNavigationRegion;
import lightning.product.Z_530_i;
import lightning.product.Z_535_q;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.BlockTags;
import lightning.product.u_530_F;

public class FlyNodeEvaluator
extends Z_535_q {
    @Override
    public void n_1700_B(PathNavigationRegion p_225578_1_, Z_530_i p_225578_2_) {
        super.n_1700_B(p_225578_1_, p_225578_2_);
        this.s_956_w = p_225578_2_.n_1700_B(I_1869_h.w_1484_f);
    }

    @Override
    public void n_1700_B() {
        this.J_1907_R.n_1700_B(I_1869_h.w_1484_f, this.s_956_w);
        super.n_1700_B();
    }

    @Override
    public D_1436_R J_1907_R() {
        c_1514_x blockpos1;
        I_1869_h pathnodetype1;
        int i;
        if (this.P_1922_E() && this.J_1907_R.RowButton()) {
            i = u_530_F.R_4764_Y(this.J_1907_R.X_2960_b());
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(this.J_1907_R.O_3598_v(), (double)i, this.J_1907_R.l_2647_k());
            T_2915_h block = this.n_1700_B.getBlockState(blockpos$mutable).J_1907_R();
            while (block == a_3742_W.c_3005_b) {
                blockpos$mutable.n_1700_B(this.J_1907_R.O_3598_v(), (double)(++i), this.J_1907_R.l_2647_k());
                block = this.n_1700_B.getBlockState(blockpos$mutable).J_1907_R();
            }
        } else {
            i = u_530_F.R_4764_Y(this.J_1907_R.X_2960_b() + 0.5);
        }
        if (this.J_1907_R.n_1700_B(pathnodetype1 = this.n_1700_B(this.J_1907_R, (blockpos1 = this.J_1907_R.b_2312_j()).getX(), i, blockpos1.getZ())) < 0.0f) {
            HashSet set = Sets.newHashSet();
            set.add(new c_1514_x(this.J_1907_R.i_601_W().minX, (double)i, this.J_1907_R.i_601_W().minZ));
            set.add(new c_1514_x(this.J_1907_R.i_601_W().minX, (double)i, this.J_1907_R.i_601_W().maxZ));
            set.add(new c_1514_x(this.J_1907_R.i_601_W().maxX, (double)i, this.J_1907_R.i_601_W().minZ));
            set.add(new c_1514_x(this.J_1907_R.i_601_W().maxX, (double)i, this.J_1907_R.i_601_W().maxZ));
            for (c_1514_x blockpos : set) {
                I_1869_h pathnodetype = this.n_1700_B(this.J_1907_R, blockpos);
                if (!(this.J_1907_R.n_1700_B(pathnodetype) >= 0.0f)) continue;
                return super.n_1700_B(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            }
        }
        return super.n_1700_B(blockpos1.getX(), i, blockpos1.getZ());
    }

    @Override
    public Target n_1700_B(double p_224768_1_, double p_224768_3_, double p_224768_5_) {
        return new Target(super.n_1700_B(u_530_F.R_4764_Y(p_224768_1_), u_530_F.R_4764_Y(p_224768_3_), u_530_F.R_4764_Y(p_224768_5_)));
    }

    @Override
    public int n_1700_B(D_1436_R[] p_222859_1_, D_1436_R p_222859_2_) {
        D_1436_R pathpoint25;
        D_1436_R pathpoint24;
        D_1436_R pathpoint23;
        D_1436_R pathpoint22;
        D_1436_R pathpoint21;
        D_1436_R pathpoint20;
        D_1436_R pathpoint19;
        D_1436_R pathpoint18;
        D_1436_R pathpoint17;
        D_1436_R pathpoint16;
        D_1436_R pathpoint15;
        D_1436_R pathpoint14;
        D_1436_R pathpoint13;
        D_1436_R pathpoint12;
        D_1436_R pathpoint11;
        D_1436_R pathpoint10;
        D_1436_R pathpoint9;
        D_1436_R pathpoint8;
        D_1436_R pathpoint7;
        D_1436_R pathpoint6;
        D_1436_R pathpoint5;
        D_1436_R pathpoint4;
        D_1436_R pathpoint3;
        D_1436_R pathpoint2;
        D_1436_R pathpoint1;
        int i = 0;
        D_1436_R pathpoint = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y + 1);
        if (this.J_1907_R(pathpoint)) {
            p_222859_1_[i++] = pathpoint;
        }
        if (this.J_1907_R(pathpoint1 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y))) {
            p_222859_1_[i++] = pathpoint1;
        }
        if (this.J_1907_R(pathpoint2 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y))) {
            p_222859_1_[i++] = pathpoint2;
        }
        if (this.J_1907_R(pathpoint3 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y - 1))) {
            p_222859_1_[i++] = pathpoint3;
        }
        if (this.J_1907_R(pathpoint4 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y))) {
            p_222859_1_[i++] = pathpoint4;
        }
        if (this.J_1907_R(pathpoint5 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R - 1, p_222859_2_.R_4764_Y))) {
            p_222859_1_[i++] = pathpoint5;
        }
        if (this.J_1907_R(pathpoint6 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y + 1)) && this.n_1700_B(pathpoint) && this.n_1700_B(pathpoint4)) {
            p_222859_1_[i++] = pathpoint6;
        }
        if (this.J_1907_R(pathpoint7 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y)) && this.n_1700_B(pathpoint1) && this.n_1700_B(pathpoint4)) {
            p_222859_1_[i++] = pathpoint7;
        }
        if (this.J_1907_R(pathpoint8 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y)) && this.n_1700_B(pathpoint2) && this.n_1700_B(pathpoint4)) {
            p_222859_1_[i++] = pathpoint8;
        }
        if (this.J_1907_R(pathpoint9 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y - 1)) && this.n_1700_B(pathpoint3) && this.n_1700_B(pathpoint4)) {
            p_222859_1_[i++] = pathpoint9;
        }
        if (this.J_1907_R(pathpoint10 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R - 1, p_222859_2_.R_4764_Y + 1)) && this.n_1700_B(pathpoint) && this.n_1700_B(pathpoint5)) {
            p_222859_1_[i++] = pathpoint10;
        }
        if (this.J_1907_R(pathpoint11 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R - 1, p_222859_2_.R_4764_Y)) && this.n_1700_B(pathpoint1) && this.n_1700_B(pathpoint5)) {
            p_222859_1_[i++] = pathpoint11;
        }
        if (this.J_1907_R(pathpoint12 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R - 1, p_222859_2_.R_4764_Y)) && this.n_1700_B(pathpoint2) && this.n_1700_B(pathpoint5)) {
            p_222859_1_[i++] = pathpoint12;
        }
        if (this.J_1907_R(pathpoint13 = this.n_1700_B(p_222859_2_.n_1700_B, p_222859_2_.J_1907_R - 1, p_222859_2_.R_4764_Y - 1)) && this.n_1700_B(pathpoint3) && this.n_1700_B(pathpoint5)) {
            p_222859_1_[i++] = pathpoint13;
        }
        if (this.J_1907_R(pathpoint14 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y - 1)) && this.n_1700_B(pathpoint3) && this.n_1700_B(pathpoint2)) {
            p_222859_1_[i++] = pathpoint14;
        }
        if (this.J_1907_R(pathpoint15 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y + 1)) && this.n_1700_B(pathpoint) && this.n_1700_B(pathpoint2)) {
            p_222859_1_[i++] = pathpoint15;
        }
        if (this.J_1907_R(pathpoint16 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y - 1)) && this.n_1700_B(pathpoint3) && this.n_1700_B(pathpoint1)) {
            p_222859_1_[i++] = pathpoint16;
        }
        if (this.J_1907_R(pathpoint17 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R, p_222859_2_.R_4764_Y + 1)) && this.n_1700_B(pathpoint) && this.n_1700_B(pathpoint1)) {
            p_222859_1_[i++] = pathpoint17;
        }
        if (this.J_1907_R(pathpoint18 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y - 1)) && this.n_1700_B(pathpoint14) && this.n_1700_B(pathpoint3) && this.n_1700_B(pathpoint2) && this.n_1700_B(pathpoint4) && this.n_1700_B(pathpoint9) && this.n_1700_B(pathpoint8)) {
            p_222859_1_[i++] = pathpoint18;
        }
        if (this.J_1907_R(pathpoint19 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y + 1)) && this.n_1700_B(pathpoint15) && this.n_1700_B(pathpoint) && this.n_1700_B(pathpoint2) && this.n_1700_B(pathpoint4) && this.n_1700_B(pathpoint6) && this.n_1700_B(pathpoint8)) {
            p_222859_1_[i++] = pathpoint19;
        }
        if (this.J_1907_R(pathpoint20 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y - 1)) && this.n_1700_B(pathpoint16) && this.n_1700_B(pathpoint3) && this.n_1700_B(pathpoint1) & this.n_1700_B(pathpoint4) && this.n_1700_B(pathpoint9) && this.n_1700_B(pathpoint7)) {
            p_222859_1_[i++] = pathpoint20;
        }
        if (this.J_1907_R(pathpoint21 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R + 1, p_222859_2_.R_4764_Y + 1)) && this.n_1700_B(pathpoint17) && this.n_1700_B(pathpoint) && this.n_1700_B(pathpoint1) & this.n_1700_B(pathpoint4) && this.n_1700_B(pathpoint6) && this.n_1700_B(pathpoint7)) {
            p_222859_1_[i++] = pathpoint21;
        }
        if (this.J_1907_R(pathpoint22 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R - 1, p_222859_2_.R_4764_Y - 1)) && this.n_1700_B(pathpoint14) && this.n_1700_B(pathpoint3) && this.n_1700_B(pathpoint2) && this.n_1700_B(pathpoint5) && this.n_1700_B(pathpoint13) && this.n_1700_B(pathpoint12)) {
            p_222859_1_[i++] = pathpoint22;
        }
        if (this.J_1907_R(pathpoint23 = this.n_1700_B(p_222859_2_.n_1700_B + 1, p_222859_2_.J_1907_R - 1, p_222859_2_.R_4764_Y + 1)) && this.n_1700_B(pathpoint15) && this.n_1700_B(pathpoint) && this.n_1700_B(pathpoint2) && this.n_1700_B(pathpoint5) && this.n_1700_B(pathpoint10) && this.n_1700_B(pathpoint12)) {
            p_222859_1_[i++] = pathpoint23;
        }
        if (this.J_1907_R(pathpoint24 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R - 1, p_222859_2_.R_4764_Y - 1)) && this.n_1700_B(pathpoint16) && this.n_1700_B(pathpoint3) && this.n_1700_B(pathpoint1) && this.n_1700_B(pathpoint5) && this.n_1700_B(pathpoint13) && this.n_1700_B(pathpoint11)) {
            p_222859_1_[i++] = pathpoint24;
        }
        if (this.J_1907_R(pathpoint25 = this.n_1700_B(p_222859_2_.n_1700_B - 1, p_222859_2_.J_1907_R - 1, p_222859_2_.R_4764_Y + 1)) && this.n_1700_B(pathpoint17) && this.n_1700_B(pathpoint) && this.n_1700_B(pathpoint1) && this.n_1700_B(pathpoint5) && this.n_1700_B(pathpoint10) && this.n_1700_B(pathpoint11)) {
            p_222859_1_[i++] = pathpoint25;
        }
        return i;
    }

    private boolean n_1700_B(@Nullable D_1436_R p_227476_1_) {
        return p_227476_1_ != null && p_227476_1_.u_2550_I >= 0.0f;
    }

    private boolean J_1907_R(@Nullable D_1436_R p_227477_1_) {
        return p_227477_1_ != null && !p_227477_1_.t_148_a;
    }

    @Override
    @Nullable
    protected D_1436_R n_1700_B(int x, int y, int z) {
        D_1436_R pathpoint = null;
        I_1869_h pathnodetype = this.n_1700_B(this.J_1907_R, x, y, z);
        float f = this.J_1907_R.n_1700_B(pathnodetype);
        if (f >= 0.0f) {
            pathpoint = super.n_1700_B(x, y, z);
            pathpoint.M_588_G = pathnodetype;
            pathpoint.u_2550_I = Math.max(pathpoint.u_2550_I, f);
            if (pathnodetype == I_1869_h.R_4764_Y) {
                pathpoint.u_2550_I += 1.0f;
            }
        }
        return pathnodetype != I_1869_h.J_1907_R && pathnodetype != I_1869_h.R_4764_Y ? pathpoint : pathpoint;
    }

    @Override
    public I_1869_h n_1700_B(BlockGetter blockaccessIn, int x, int y, int z, Z_530_i entitylivingIn, int xSize, int ySize, int zSize, boolean canBreakDoorsIn, boolean canEnterDoorsIn) {
        EnumSet<I_1869_h> enumset = EnumSet.noneOf(I_1869_h.class);
        I_1869_h pathnodetype = I_1869_h.n_1700_B;
        c_1514_x blockpos = entitylivingIn.b_2312_j();
        pathnodetype = this.n_1700_B(blockaccessIn, x, y, z, xSize, ySize, zSize, canBreakDoorsIn, canEnterDoorsIn, enumset, pathnodetype, blockpos);
        if (enumset.contains((Object)I_1869_h.u_1723_Y)) {
            return I_1869_h.u_1723_Y;
        }
        I_1869_h pathnodetype1 = I_1869_h.n_1700_B;
        for (I_1869_h pathnodetype2 : enumset) {
            if (entitylivingIn.n_1700_B(pathnodetype2) < 0.0f) {
                return pathnodetype2;
            }
            if (!(entitylivingIn.n_1700_B(pathnodetype2) >= entitylivingIn.n_1700_B(pathnodetype1))) continue;
            pathnodetype1 = pathnodetype2;
        }
        return pathnodetype == I_1869_h.J_1907_R && entitylivingIn.n_1700_B(pathnodetype1) == 0.0f ? I_1869_h.J_1907_R : pathnodetype1;
    }

    @Override
    public I_1869_h n_1700_B(BlockGetter blockaccessIn, int x, int y, int z) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        I_1869_h pathnodetype = FlyNodeEvaluator.J_1907_R(blockaccessIn, blockpos$mutable.n_1700_B(x, y, z));
        if (pathnodetype == I_1869_h.J_1907_R && y >= 1) {
            K_4074_S blockstate = blockaccessIn.getBlockState(blockpos$mutable.n_1700_B(x, y - 1, z));
            I_1869_h pathnodetype1 = FlyNodeEvaluator.J_1907_R(blockaccessIn, blockpos$mutable.n_1700_B(x, y - 1, z));
            pathnodetype = pathnodetype1 != I_1869_h.P_4830_p && !blockstate.n_1700_B(a_3742_W.LevitationControl) && pathnodetype1 != I_1869_h.v_4262_N && !blockstate.n_1700_B(BlockTags.U_1241_n) ? (pathnodetype1 == I_1869_h.Q_4569_t ? I_1869_h.Q_4569_t : (pathnodetype1 == I_1869_h.t_1786_h ? I_1869_h.t_1786_h : (pathnodetype1 == I_1869_h.k_2293_S ? I_1869_h.k_2293_S : (pathnodetype1 == I_1869_h.u_1723_Y ? I_1869_h.u_1723_Y : (pathnodetype1 != I_1869_h.R_4764_Y && pathnodetype1 != I_1869_h.J_1907_R && pathnodetype1 != I_1869_h.w_1484_f ? I_1869_h.R_4764_Y : I_1869_h.J_1907_R))))) : I_1869_h.P_4830_p;
        }
        if (pathnodetype == I_1869_h.R_4764_Y || pathnodetype == I_1869_h.J_1907_R) {
            pathnodetype = FlyNodeEvaluator.n_1700_B(blockaccessIn, blockpos$mutable.n_1700_B(x, y, z), pathnodetype);
        }
        return pathnodetype;
    }

    private I_1869_h n_1700_B(Z_530_i p_192559_1_, c_1514_x p_192559_2_) {
        return this.n_1700_B(p_192559_1_, p_192559_2_.getX(), p_192559_2_.getY(), p_192559_2_.getZ());
    }

    private I_1869_h n_1700_B(Z_530_i p_192558_1_, int p_192558_2_, int p_192558_3_, int p_192558_4_) {
        return this.n_1700_B(this.n_1700_B, p_192558_2_, p_192558_3_, p_192558_4_, p_192558_1_, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.G_564_y(), this.R_4764_Y());
    }
}



