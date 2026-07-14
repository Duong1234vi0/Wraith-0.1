/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.D_1436_R;
import lightning.product.N_4263_v;
import lightning.product.Z_1164_j;
import lightning.product.a_3913_L;
import lightning.product.b_1722_e;
import lightning.product.b_2971_b;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.DragonFireball;
import lightning.product.AbstractDragonPhaseInstance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class k_608_m
extends AbstractDragonPhaseInstance {
    private static final Logger J_1907_R = LogManager.getLogger();
    private int R_4764_Y;
    private b_1722_e G_564_y;
    private e_2866_D P_1922_E;
    private r_4811_B u_1723_Y;
    private boolean v_4262_N;

    public k_608_m(b_2971_b dragonIn) {
        super(dragonIn);
    }

    @Override
    public void J_1907_R() {
        if (this.u_1723_Y == null) {
            J_1907_R.warn("Skipping player strafe phase because no player was found");
            this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.n_1700_B);
        } else {
            double d12;
            if (this.G_564_y != null && this.G_564_y.R_4764_Y()) {
                double d0 = this.u_1723_Y.O_3598_v();
                double d1 = this.u_1723_Y.l_2647_k();
                double d2 = d0 - this.n_1700_B.O_3598_v();
                double d3 = d1 - this.n_1700_B.l_2647_k();
                double d4 = u_530_F.n_1700_B(d2 * d2 + d3 * d3);
                double d5 = Math.min((double)0.4f + d4 / 80.0 - 1.0, 10.0);
                this.P_1922_E = new e_2866_D(d0, this.u_1723_Y.X_2960_b() + d5, d1);
            }
            double d = d12 = this.P_1922_E == null ? 0.0 : this.P_1922_E.R_4764_Y(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k());
            if (d12 < 100.0 || d12 > 22500.0) {
                this.s_956_w();
            }
            double d13 = 64.0;
            if (this.u_1723_Y.G_564_y((N_4263_v)this.n_1700_B) < 4096.0) {
                if (this.n_1700_B.c_3005_b(this.u_1723_Y)) {
                    ++this.R_4764_Y;
                    e_2866_D vector3d1 = new e_2866_D(this.u_1723_Y.O_3598_v() - this.n_1700_B.O_3598_v(), 0.0, this.u_1723_Y.l_2647_k() - this.n_1700_B.l_2647_k()).G_564_y();
                    e_2866_D vector3d = new e_2866_D(u_530_F.n_1700_B(this.n_1700_B.p_178_J * ((float)Math.PI / 180)), 0.0, -u_530_F.J_1907_R(this.n_1700_B.p_178_J * ((float)Math.PI / 180))).G_564_y();
                    float f1 = (float)vector3d.J_1907_R(vector3d1);
                    float f = (float)(Math.acos(f1) * 57.2957763671875);
                    f += 0.5f;
                    if (this.R_4764_Y >= 5 && f >= 0.0f && f < 10.0f) {
                        double d14 = 1.0;
                        e_2866_D vector3d2 = this.n_1700_B.t_148_a(1.0f);
                        double d6 = this.n_1700_B.h_1847_R.O_3598_v() - vector3d2.J_1907_R * 1.0;
                        double d7 = this.n_1700_B.h_1847_R.P_1922_E(0.5) + 0.5;
                        double d8 = this.n_1700_B.h_1847_R.l_2647_k() - vector3d2.G_564_y * 1.0;
                        double d9 = this.u_1723_Y.O_3598_v() - d6;
                        double d10 = this.u_1723_Y.P_1922_E(0.5) - d7;
                        double d11 = this.u_1723_Y.l_2647_k() - d8;
                        if (!this.n_1700_B.y_1700_S()) {
                            this.n_1700_B.O_508_d.n_1700_B((a_3913_L)null, 1017, this.n_1700_B.b_2312_j(), 0);
                        }
                        DragonFireball dragonfireballentity = new DragonFireball(this.n_1700_B.O_508_d, this.n_1700_B, d9, d10, d11);
                        dragonfireballentity.J_1907_R(d6, d7, d8, 0.0f, 0.0f);
                        this.n_1700_B.O_508_d.a_(dragonfireballentity);
                        this.R_4764_Y = 0;
                        if (this.G_564_y != null) {
                            while (!this.G_564_y.R_4764_Y()) {
                                this.G_564_y.n_1700_B();
                            }
                        }
                        this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.n_1700_B);
                    }
                } else if (this.R_4764_Y > 0) {
                    --this.R_4764_Y;
                }
            } else if (this.R_4764_Y > 0) {
                --this.R_4764_Y;
            }
        }
    }

    private void s_956_w() {
        if (this.G_564_y == null || this.G_564_y.R_4764_Y()) {
            int i;
            int j = i = this.n_1700_B.w_1484_f();
            if (this.n_1700_B.M_3508_C().nextInt(8) == 0) {
                this.v_4262_N = !this.v_4262_N;
                j = i + 6;
            }
            j = this.v_4262_N ? ++j : --j;
            if (this.n_1700_B.h_1640_b() != null && this.n_1700_B.h_1640_b().R_4764_Y() > 0) {
                if ((j %= 12) < 0) {
                    j += 12;
                }
            } else {
                j -= 12;
                j &= 7;
                j += 12;
            }
            this.G_564_y = this.n_1700_B.n_1700_B(i, j, (D_1436_R)null);
            if (this.G_564_y != null) {
                this.G_564_y.n_1700_B();
            }
        }
        this.u_2550_I();
    }

    private void u_2550_I() {
        if (this.G_564_y != null && !this.G_564_y.R_4764_Y()) {
            double d1;
            c_1514_x vector3i = this.G_564_y.v_4262_N();
            this.G_564_y.n_1700_B();
            double d0 = vector3i.getX();
            double d2 = vector3i.getZ();
            while ((d1 = (double)((float)vector3i.getY() + this.n_1700_B.M_3508_C().nextFloat() * 20.0f)) < (double)vector3i.getY()) {
            }
            this.P_1922_E = new e_2866_D(d0, d1, d2);
        }
    }

    @Override
    public void R_4764_Y() {
        this.R_4764_Y = 0;
        this.P_1922_E = null;
        this.G_564_y = null;
        this.u_1723_Y = null;
    }

    public void n_1700_B(r_4811_B p_188686_1_) {
        this.u_1723_Y = p_188686_1_;
        int i = this.n_1700_B.w_1484_f();
        int j = this.n_1700_B.M_182_A(this.u_1723_Y.O_3598_v(), this.u_1723_Y.X_2960_b(), this.u_1723_Y.l_2647_k());
        int k = u_530_F.R_4764_Y(this.u_1723_Y.O_3598_v());
        int l = u_530_F.R_4764_Y(this.u_1723_Y.l_2647_k());
        double d0 = (double)k - this.n_1700_B.O_3598_v();
        double d1 = (double)l - this.n_1700_B.l_2647_k();
        double d2 = u_530_F.n_1700_B(d0 * d0 + d1 * d1);
        double d3 = Math.min((double)0.4f + d2 / 80.0 - 1.0, 10.0);
        int i1 = u_530_F.R_4764_Y(this.u_1723_Y.X_2960_b() + d3);
        D_1436_R pathpoint = new D_1436_R(k, i1, l);
        this.G_564_y = this.n_1700_B.n_1700_B(i, j, pathpoint);
        if (this.G_564_y != null) {
            this.G_564_y.n_1700_B();
            this.u_2550_I();
        }
    }

    @Override
    @Nullable
    public e_2866_D u_1723_Y() {
        return this.P_1922_E;
    }

    public Z_1164_j<k_608_m> G_564_y() {
        return Z_1164_j.J_1907_R;
    }
}


