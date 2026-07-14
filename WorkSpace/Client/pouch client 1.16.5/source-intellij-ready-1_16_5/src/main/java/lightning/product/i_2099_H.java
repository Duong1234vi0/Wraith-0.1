/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.PathNavigation;
import lightning.product.D_1436_R;
import lightning.product.D_3856_V;
import lightning.product.BlockGetter;
import lightning.product.I_1869_h;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.Z_530_i;
import lightning.product.Z_535_q;
import lightning.product.a_3742_W;
import lightning.product.b_1722_e;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.t_3546_P;
import lightning.product.u_530_F;

public class i_2099_H
extends PathNavigation {
    private boolean n_1700_B;

    public i_2099_H(Z_530_i entitylivingIn, b_4507_u worldIn) {
        super(entitylivingIn, worldIn);
    }

    @Override
    protected D_3856_V n_1700_B(int p_179679_1_) {
        this.M_182_A = new Z_535_q();
        this.M_182_A.n_1700_B(true);
        return new D_3856_V(this.M_182_A, p_179679_1_);
    }

    @Override
    protected boolean J_1907_R() {
        return this.J_1907_R.M_1641_O() || this.Q_4569_t() || this.J_1907_R.y_2772_m();
    }

    @Override
    protected e_2866_D R_4764_Y() {
        return new e_2866_D(this.J_1907_R.O_3598_v(), this.w_1457_N(), this.J_1907_R.l_2647_k());
    }

    @Override
    public b_1722_e n_1700_B(c_1514_x pos, int p_179680_2_) {
        if (this.R_4764_Y.getBlockState(pos).v_4262_N()) {
            c_1514_x blockpos = pos.down();
            while (blockpos.getY() > 0 && this.R_4764_Y.getBlockState(blockpos).v_4262_N()) {
                blockpos = blockpos.down();
            }
            if (blockpos.getY() > 0) {
                return super.n_1700_B(blockpos.up(), p_179680_2_);
            }
            while (blockpos.getY() < this.R_4764_Y.c_3005_b() && this.R_4764_Y.getBlockState(blockpos).v_4262_N()) {
                blockpos = blockpos.up();
            }
            pos = blockpos;
        }
        if (!this.R_4764_Y.getBlockState(pos).R_4764_Y().J_1907_R()) {
            return super.n_1700_B(pos, p_179680_2_);
        }
        c_1514_x blockpos1 = pos.up();
        while (blockpos1.getY() < this.R_4764_Y.c_3005_b() && this.R_4764_Y.getBlockState(blockpos1).R_4764_Y().J_1907_R()) {
            blockpos1 = blockpos1.up();
        }
        return super.n_1700_B(blockpos1, p_179680_2_);
    }

    @Override
    public b_1722_e n_1700_B(N_4263_v entityIn, int p_75494_2_) {
        return this.n_1700_B(entityIn.b_2312_j(), p_75494_2_);
    }

    private int w_1457_N() {
        if (this.J_1907_R.RowButton() && this.t_1786_h()) {
            int i = u_530_F.R_4764_Y(this.J_1907_R.X_2960_b());
            T_2915_h block = this.R_4764_Y.getBlockState(new c_1514_x(this.J_1907_R.O_3598_v(), (double)i, this.J_1907_R.l_2647_k())).J_1907_R();
            int j = 0;
            while (block == a_3742_W.c_3005_b) {
                block = this.R_4764_Y.getBlockState(new c_1514_x(this.J_1907_R.O_3598_v(), (double)(++i), this.J_1907_R.l_2647_k())).J_1907_R();
                if (++j <= 16) continue;
                return u_530_F.R_4764_Y(this.J_1907_R.X_2960_b());
            }
            return i;
        }
        return u_530_F.R_4764_Y(this.J_1907_R.X_2960_b() + 0.5);
    }

    @Override
    protected void G_564_y() {
        super.G_564_y();
        if (this.n_1700_B) {
            if (this.R_4764_Y.canSeeSky(new c_1514_x(this.J_1907_R.O_3598_v(), this.J_1907_R.X_2960_b() + 0.5, this.J_1907_R.l_2647_k()))) {
                return;
            }
            for (int i = 0; i < this.G_564_y.P_1922_E(); ++i) {
                D_1436_R pathpoint = this.G_564_y.n_1700_B(i);
                if (!this.R_4764_Y.canSeeSky(new c_1514_x(pathpoint.n_1700_B, pathpoint.J_1907_R, pathpoint.R_4764_Y))) continue;
                this.G_564_y.J_1907_R(i);
                return;
            }
        }
    }

    @Override
    protected boolean n_1700_B(e_2866_D posVec31, e_2866_D posVec32, int sizeX, int sizeY, int sizeZ) {
        int i = u_530_F.R_4764_Y(posVec31.J_1907_R);
        int j = u_530_F.R_4764_Y(posVec31.G_564_y);
        double d0 = posVec32.J_1907_R - posVec31.J_1907_R;
        double d1 = posVec32.G_564_y - posVec31.G_564_y;
        double d2 = d0 * d0 + d1 * d1;
        if (d2 < 1.0E-8) {
            return false;
        }
        double d3 = 1.0 / Math.sqrt(d2);
        if (!this.n_1700_B(i, u_530_F.R_4764_Y(posVec31.R_4764_Y), j, sizeX += 2, sizeY, sizeZ += 2, posVec31, d0 *= d3, d1 *= d3)) {
            return false;
        }
        sizeX -= 2;
        sizeZ -= 2;
        double d4 = 1.0 / Math.abs(d0);
        double d5 = 1.0 / Math.abs(d1);
        double d6 = (double)i - posVec31.J_1907_R;
        double d7 = (double)j - posVec31.G_564_y;
        if (d0 >= 0.0) {
            d6 += 1.0;
        }
        if (d1 >= 0.0) {
            d7 += 1.0;
        }
        d6 /= d0;
        d7 /= d1;
        int k = d0 < 0.0 ? -1 : 1;
        int l = d1 < 0.0 ? -1 : 1;
        int i1 = u_530_F.R_4764_Y(posVec32.J_1907_R);
        int j1 = u_530_F.R_4764_Y(posVec32.G_564_y);
        int k1 = i1 - i;
        int l1 = j1 - j;
        while (k1 * k > 0 || l1 * l > 0) {
            if (d6 < d7) {
                d6 += d4;
                k1 = i1 - (i += k);
            } else {
                d7 += d5;
                l1 = j1 - (j += l);
            }
            if (this.n_1700_B(i, u_530_F.R_4764_Y(posVec31.R_4764_Y), j, sizeX, sizeY, sizeZ, posVec31, d0, d1)) continue;
            return false;
        }
        return true;
    }

    private boolean n_1700_B(int x, int y, int z, int sizeX, int sizeY, int sizeZ, e_2866_D vec31, double p_179683_8_, double p_179683_10_) {
        int i = x - sizeX / 2;
        int j = z - sizeZ / 2;
        if (!this.J_1907_R(i, y, j, sizeX, sizeY, sizeZ, vec31, p_179683_8_, p_179683_10_)) {
            return false;
        }
        for (int k = i; k < i + sizeX; ++k) {
            for (int l = j; l < j + sizeZ; ++l) {
                double d0 = (double)k + 0.5 - vec31.J_1907_R;
                double d1 = (double)l + 0.5 - vec31.G_564_y;
                if (d0 * p_179683_8_ + d1 * p_179683_10_ < 0.0) continue;
                I_1869_h pathnodetype = this.M_182_A.n_1700_B(this.R_4764_Y, k, y - 1, l, this.J_1907_R, sizeX, sizeY, sizeZ, true, true);
                if (!this.n_1700_B(pathnodetype)) {
                    return false;
                }
                pathnodetype = this.M_182_A.n_1700_B(this.R_4764_Y, k, y, l, this.J_1907_R, sizeX, sizeY, sizeZ, true, true);
                float f = this.J_1907_R.n_1700_B(pathnodetype);
                if (f < 0.0f || f >= 8.0f) {
                    return false;
                }
                if (pathnodetype != I_1869_h.P_4830_p && pathnodetype != I_1869_h.M_588_G && pathnodetype != I_1869_h.t_1786_h) continue;
                return false;
            }
        }
        return true;
    }

    protected boolean n_1700_B(I_1869_h p_230287_1_) {
        if (p_230287_1_ == I_1869_h.w_1484_f) {
            return false;
        }
        if (p_230287_1_ == I_1869_h.v_4262_N) {
            return false;
        }
        return p_230287_1_ != I_1869_h.J_1907_R;
    }

    private boolean J_1907_R(int x, int y, int z, int sizeX, int sizeY, int sizeZ, e_2866_D p_179692_7_, double p_179692_8_, double p_179692_10_) {
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(new c_1514_x(x, y, z), new c_1514_x(x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1))) {
            double d1;
            double d0 = (double)blockpos.getX() + 0.5 - p_179692_7_.J_1907_R;
            if (d0 * p_179692_8_ + (d1 = (double)blockpos.getZ() + 0.5 - p_179692_7_.G_564_y) * p_179692_10_ < 0.0 || this.R_4764_Y.getBlockState(blockpos).n_1700_B((BlockGetter)this.R_4764_Y, blockpos, t_3546_P.n_1700_B)) continue;
            return false;
        }
        return true;
    }

    public void n_1700_B(boolean canBreakDoors) {
        this.M_182_A.J_1907_R(canBreakDoors);
    }

    public boolean P_1922_E() {
        return this.M_182_A.R_4764_Y();
    }

    public void J_1907_R(boolean avoidSun) {
        this.n_1700_B = avoidSun;
    }
}


