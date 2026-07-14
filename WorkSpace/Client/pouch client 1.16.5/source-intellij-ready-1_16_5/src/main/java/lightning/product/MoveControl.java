/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.PathNavigation;
import lightning.product.Attributes;
import lightning.product.I_1869_h;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Z_530_i;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.NodeEvaluator;
import lightning.product.BlockTags;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;

public class MoveControl {
    protected final Z_530_i n_1700_B;
    protected double J_1907_R;
    protected double R_4764_Y;
    protected double G_564_y;
    protected double P_1922_E;
    protected float u_1723_Y;
    protected float v_4262_N;
    protected n_1700_B w_1484_f = lightning.product.MoveControl$n_1700_B.n_1700_B;

    public MoveControl(Z_530_i mob) {
        this.n_1700_B = mob;
    }

    public boolean J_1907_R() {
        return this.w_1484_f == lightning.product.MoveControl$n_1700_B.J_1907_R;
    }

    public double R_4764_Y() {
        return this.P_1922_E;
    }

    public void n_1700_B(double x, double y, double z, double speedIn) {
        this.J_1907_R = x;
        this.R_4764_Y = y;
        this.G_564_y = z;
        this.P_1922_E = speedIn;
        if (this.w_1484_f != lightning.product.MoveControl$n_1700_B.G_564_y) {
            this.w_1484_f = lightning.product.MoveControl$n_1700_B.J_1907_R;
        }
    }

    public void n_1700_B(float forward, float strafe) {
        this.w_1484_f = lightning.product.MoveControl$n_1700_B.R_4764_Y;
        this.u_1723_Y = forward;
        this.v_4262_N = strafe;
        this.P_1922_E = 0.25;
    }

    public void n_1700_B() {
        if (this.w_1484_f == lightning.product.MoveControl$n_1700_B.R_4764_Y) {
            float f8;
            float f = (float)this.n_1700_B.J_1907_R(Attributes.G_564_y);
            float f1 = (float)this.P_1922_E * f;
            float f2 = this.u_1723_Y;
            float f3 = this.v_4262_N;
            float f4 = u_530_F.R_4764_Y(f2 * f2 + f3 * f3);
            if (f4 < 1.0f) {
                f4 = 1.0f;
            }
            f4 = f1 / f4;
            float f5 = u_530_F.n_1700_B(this.n_1700_B.p_178_J * ((float)Math.PI / 180));
            float f6 = u_530_F.J_1907_R(this.n_1700_B.p_178_J * ((float)Math.PI / 180));
            float f7 = (f2 *= f4) * f6 - (f3 *= f4) * f5;
            if (!this.J_1907_R(f7, f8 = f3 * f6 + f2 * f5)) {
                this.u_1723_Y = 1.0f;
                this.v_4262_N = 0.0f;
            }
            this.n_1700_B.w_1457_N(f1);
            this.n_1700_B.C_2741_M(this.u_1723_Y);
            this.n_1700_B.q_2307_F(this.v_4262_N);
            this.w_1484_f = lightning.product.MoveControl$n_1700_B.n_1700_B;
        } else if (this.w_1484_f == lightning.product.MoveControl$n_1700_B.J_1907_R) {
            this.w_1484_f = lightning.product.MoveControl$n_1700_B.n_1700_B;
            double d0 = this.J_1907_R - this.n_1700_B.O_3598_v();
            double d1 = this.G_564_y - this.n_1700_B.l_2647_k();
            double d2 = this.R_4764_Y - this.n_1700_B.X_2960_b();
            double d3 = d0 * d0 + d2 * d2 + d1 * d1;
            if (d3 < 2.500000277905201E-7) {
                this.n_1700_B.C_2741_M(0.0f);
                return;
            }
            float f9 = (float)(u_530_F.G_564_y(d1, d0) * 57.2957763671875) - 90.0f;
            this.n_1700_B.p_178_J = this.n_1700_B(this.n_1700_B.p_178_J, f9, 90.0f);
            this.n_1700_B.w_1457_N((float)(this.P_1922_E * this.n_1700_B.J_1907_R(Attributes.G_564_y)));
            c_1514_x blockpos = this.n_1700_B.b_2312_j();
            K_4074_S blockstate = this.n_1700_B.O_508_d.getBlockState(blockpos);
            T_2915_h block = blockstate.J_1907_R();
            s_1395_c voxelshape = blockstate.u_2550_I(this.n_1700_B.O_508_d, blockpos);
            if (d2 > (double)this.n_1700_B.RealmsServerPing && d0 * d0 + d1 * d1 < (double)Math.max(1.0f, this.n_1700_B.C_415_h()) || !voxelshape.J_1907_R() && this.n_1700_B.X_2960_b() < voxelshape.R_4764_Y(b_257_Y.n_1700_B.J_1907_R) + (double)blockpos.getY() && !block.n_1700_B(BlockTags.M_182_A) && !block.n_1700_B(BlockTags.G_624_v)) {
                this.n_1700_B.t_4043_B().n_1700_B();
                this.w_1484_f = lightning.product.MoveControl$n_1700_B.G_564_y;
            }
        } else if (this.w_1484_f == lightning.product.MoveControl$n_1700_B.G_564_y) {
            this.n_1700_B.w_1457_N((float)(this.P_1922_E * this.n_1700_B.J_1907_R(Attributes.G_564_y)));
            if (this.n_1700_B.M_1641_O()) {
                this.w_1484_f = lightning.product.MoveControl$n_1700_B.n_1700_B;
            }
        } else {
            this.n_1700_B.C_2741_M(0.0f);
        }
    }

    private boolean J_1907_R(float p_234024_1_, float p_234024_2_) {
        NodeEvaluator nodeprocessor;
        PathNavigation pathnavigator = this.n_1700_B.e_4240_b();
        return pathnavigator == null || (nodeprocessor = pathnavigator.M_182_A()) == null || nodeprocessor.n_1700_B(this.n_1700_B.O_508_d, u_530_F.R_4764_Y(this.n_1700_B.O_3598_v() + (double)p_234024_1_), u_530_F.R_4764_Y(this.n_1700_B.X_2960_b()), u_530_F.R_4764_Y(this.n_1700_B.l_2647_k() + (double)p_234024_2_)) == I_1869_h.R_4764_Y;
    }

    protected float n_1700_B(float sourceAngle, float targetAngle, float maximumChange) {
        float f1;
        float f = u_530_F.v_4262_N(targetAngle - sourceAngle);
        if (f > maximumChange) {
            f = maximumChange;
        }
        if (f < -maximumChange) {
            f = -maximumChange;
        }
        if ((f1 = sourceAngle + f) < 0.0f) {
            f1 += 360.0f;
        } else if (f1 > 360.0f) {
            f1 -= 360.0f;
        }
        return f1;
    }

    public double G_564_y() {
        return this.J_1907_R;
    }

    public double P_1922_E() {
        return this.R_4764_Y;
    }

    public double u_1723_Y() {
        return this.G_564_y;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.MoveControl$n_1700_B.n_1700_B();
        }
    }
}


