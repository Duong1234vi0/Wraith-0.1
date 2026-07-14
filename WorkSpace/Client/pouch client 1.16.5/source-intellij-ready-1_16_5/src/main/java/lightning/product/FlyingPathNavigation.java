/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.PathNavigation;
import lightning.product.DebugPackets;
import lightning.product.D_3856_V;
import lightning.product.BlockGetter;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.b_1722_e;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.FlyNodeEvaluator;
import lightning.product.u_530_F;

public class FlyingPathNavigation
extends PathNavigation {
    public FlyingPathNavigation(Z_530_i entityIn, b_4507_u worldIn) {
        super(entityIn, worldIn);
    }

    @Override
    protected D_3856_V n_1700_B(int p_179679_1_) {
        this.M_182_A = new FlyNodeEvaluator();
        this.M_182_A.n_1700_B(true);
        return new D_3856_V(this.M_182_A, p_179679_1_);
    }

    @Override
    protected boolean J_1907_R() {
        return this.t_1786_h() && this.Q_4569_t() || !this.J_1907_R.y_2772_m();
    }

    @Override
    protected e_2866_D R_4764_Y() {
        return this.J_1907_R.s_4990_V();
    }

    @Override
    public b_1722_e n_1700_B(N_4263_v entityIn, int p_75494_2_) {
        return this.n_1700_B(entityIn.b_2312_j(), p_75494_2_);
    }

    @Override
    public void n_1700_B() {
        ++this.u_1723_Y;
        if (this.h_1847_R) {
            this.t_148_a();
        }
        if (!this.M_588_G()) {
            if (this.J_1907_R()) {
                this.u_2550_I();
            } else if (this.G_564_y != null && !this.G_564_y.R_4764_Y()) {
                e_2866_D vector3d = this.G_564_y.n_1700_B(this.J_1907_R);
                if (u_530_F.R_4764_Y(this.J_1907_R.O_3598_v()) == u_530_F.R_4764_Y(vector3d.J_1907_R) && u_530_F.R_4764_Y(this.J_1907_R.X_2960_b()) == u_530_F.R_4764_Y(vector3d.R_4764_Y) && u_530_F.R_4764_Y(this.J_1907_R.l_2647_k()) == u_530_F.R_4764_Y(vector3d.G_564_y)) {
                    this.G_564_y.n_1700_B();
                }
            }
            DebugPackets.n_1700_B(this.R_4764_Y, this.J_1907_R, this.G_564_y, this.P_4830_p);
            if (!this.M_588_G()) {
                e_2866_D vector3d1 = this.G_564_y.n_1700_B(this.J_1907_R);
                this.J_1907_R.A_4115_X().n_1700_B(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, this.P_1922_E);
            }
        }
    }

    @Override
    protected boolean n_1700_B(e_2866_D posVec31, e_2866_D posVec32, int sizeX, int sizeY, int sizeZ) {
        int i = u_530_F.R_4764_Y(posVec31.J_1907_R);
        int j = u_530_F.R_4764_Y(posVec31.R_4764_Y);
        int k = u_530_F.R_4764_Y(posVec31.G_564_y);
        double d0 = posVec32.J_1907_R - posVec31.J_1907_R;
        double d1 = posVec32.R_4764_Y - posVec31.R_4764_Y;
        double d2 = posVec32.G_564_y - posVec31.G_564_y;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        if (d3 < 1.0E-8) {
            return false;
        }
        double d4 = 1.0 / Math.sqrt(d3);
        double d5 = 1.0 / Math.abs(d0 *= d4);
        double d6 = 1.0 / Math.abs(d1 *= d4);
        double d7 = 1.0 / Math.abs(d2 *= d4);
        double d8 = (double)i - posVec31.J_1907_R;
        double d9 = (double)j - posVec31.R_4764_Y;
        double d10 = (double)k - posVec31.G_564_y;
        if (d0 >= 0.0) {
            d8 += 1.0;
        }
        if (d1 >= 0.0) {
            d9 += 1.0;
        }
        if (d2 >= 0.0) {
            d10 += 1.0;
        }
        d8 /= d0;
        d9 /= d1;
        d10 /= d2;
        int l = d0 < 0.0 ? -1 : 1;
        int i1 = d1 < 0.0 ? -1 : 1;
        int j1 = d2 < 0.0 ? -1 : 1;
        int k1 = u_530_F.R_4764_Y(posVec32.J_1907_R);
        int l1 = u_530_F.R_4764_Y(posVec32.R_4764_Y);
        int i2 = u_530_F.R_4764_Y(posVec32.G_564_y);
        int j2 = k1 - i;
        int k2 = l1 - j;
        int l2 = i2 - k;
        while (j2 * l > 0 || k2 * i1 > 0 || l2 * j1 > 0) {
            if (d8 < d10 && d8 <= d9) {
                d8 += d5;
                j2 = k1 - (i += l);
                continue;
            }
            if (d9 < d8 && d9 <= d10) {
                d9 += d6;
                k2 = l1 - (j += i1);
                continue;
            }
            d10 += d7;
            l2 = i2 - (k += j1);
        }
        return true;
    }

    public void n_1700_B(boolean canOpenDoorsIn) {
        this.M_182_A.J_1907_R(canOpenDoorsIn);
    }

    public void J_1907_R(boolean canEnterDoorsIn) {
        this.M_182_A.n_1700_B(canEnterDoorsIn);
    }

    @Override
    public boolean n_1700_B(c_1514_x pos) {
        return this.R_4764_Y.getBlockState(pos).n_1700_B((BlockGetter)this.R_4764_Y, pos, (N_4263_v)this.J_1907_R);
    }
}


