/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.PathNavigation;
import lightning.product.DebugPackets;
import lightning.product.D_3856_V;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.Y_559_r;
import lightning.product.Z_530_i;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.j_3341_s;
import lightning.product.k_1804_C;
import lightning.product.u_530_F;
import lightning.product.z_3539_x;

public class c_1972_S
extends PathNavigation {
    private boolean n_1700_B;

    public c_1972_S(Z_530_i entitylivingIn, b_4507_u worldIn) {
        super(entitylivingIn, worldIn);
    }

    @Override
    protected D_3856_V n_1700_B(int p_179679_1_) {
        this.n_1700_B = this.J_1907_R instanceof Y_559_r;
        this.M_182_A = new k_1804_C(this.n_1700_B);
        return new D_3856_V(this.M_182_A, p_179679_1_);
    }

    @Override
    protected boolean J_1907_R() {
        return this.n_1700_B || this.Q_4569_t();
    }

    @Override
    protected e_2866_D R_4764_Y() {
        return new e_2866_D(this.J_1907_R.O_3598_v(), this.J_1907_R.P_1922_E(0.5), this.J_1907_R.l_2647_k());
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
    protected void u_2550_I() {
        if (this.G_564_y != null) {
            e_2866_D vector3d = this.R_4764_Y();
            float f = this.J_1907_R.C_415_h();
            float f1 = f > 0.75f ? f / 2.0f : 0.75f - f / 2.0f;
            e_2866_D vector3d1 = this.J_1907_R.I_4348_c();
            if (Math.abs(vector3d1.J_1907_R) > 0.2 || Math.abs(vector3d1.G_564_y) > 0.2) {
                f1 = (float)((double)f1 * vector3d1.u_1723_Y() * 6.0);
            }
            int i = 6;
            e_2866_D vector3d2 = e_2866_D.R_4764_Y(this.G_564_y.v_4262_N());
            if (Math.abs(this.J_1907_R.O_3598_v() - vector3d2.J_1907_R) < (double)f1 && Math.abs(this.J_1907_R.l_2647_k() - vector3d2.G_564_y) < (double)f1 && Math.abs(this.J_1907_R.X_2960_b() - vector3d2.R_4764_Y) < (double)(f1 * 2.0f)) {
                this.G_564_y.n_1700_B();
            }
            for (int j = Math.min(this.G_564_y.u_1723_Y() + 6, this.G_564_y.P_1922_E() - 1); j > this.G_564_y.u_1723_Y(); --j) {
                vector3d2 = this.G_564_y.n_1700_B(this.J_1907_R, j);
                if (vector3d2.v_4262_N(vector3d) > 36.0 || !this.n_1700_B(vector3d, vector3d2, 0, 0, 0)) continue;
                this.G_564_y.R_4764_Y(j);
                break;
            }
            this.n_1700_B(vector3d);
        }
    }

    @Override
    protected void n_1700_B(e_2866_D positionVec3) {
        if (this.u_1723_Y - this.v_4262_N > 100) {
            if (positionVec3.v_4262_N(this.w_1484_f) < 2.25) {
                this.h_1847_R();
            }
            this.v_4262_N = this.u_1723_Y;
            this.w_1484_f = positionVec3;
        }
        if (this.G_564_y != null && !this.G_564_y.R_4764_Y()) {
            c_1514_x vector3i = this.G_564_y.v_4262_N();
            if (vector3i.equals(this.t_148_a)) {
                this.s_956_w += j_3341_s.J_1907_R() - this.u_2550_I;
            } else {
                this.t_148_a = vector3i;
                double d0 = positionVec3.u_1723_Y(e_2866_D.n_1700_B(this.t_148_a));
                double d = this.M_588_G = this.J_1907_R.l_2995_s() > 0.0f ? d0 / (double)this.J_1907_R.l_2995_s() * 100.0 : 0.0;
            }
            if (this.M_588_G > 0.0 && (double)this.s_956_w > this.M_588_G * 2.0) {
                this.t_148_a = z_3539_x.NULL_VECTOR;
                this.s_956_w = 0L;
                this.M_588_G = 0.0;
                this.h_1847_R();
            }
            this.u_2550_I = j_3341_s.J_1907_R();
        }
    }

    @Override
    protected boolean n_1700_B(e_2866_D posVec31, e_2866_D posVec32, int sizeX, int sizeY, int sizeZ) {
        e_2866_D vector3d = new e_2866_D(posVec32.J_1907_R, posVec32.R_4764_Y + (double)this.J_1907_R.v_165_F() * 0.5, posVec32.G_564_y);
        return this.R_4764_Y.n_1700_B(new ClipContext(posVec31, vector3d, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, this.J_1907_R)).R_4764_Y() == HitResult.n_1700_B.n_1700_B;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos) {
        return !this.R_4764_Y.getBlockState(pos).t_148_a(this.R_4764_Y, pos);
    }

    @Override
    public void R_4764_Y(boolean canSwim) {
    }
}


