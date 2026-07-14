/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_3622_I;
import lightning.product.N_4263_v;
import lightning.product.T_1316_M;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.Goal;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;

public abstract class PathfinderMob
extends Z_530_i {
    protected PathfinderMob(t_5_h<? extends PathfinderMob> type, b_4507_u worldIn) {
        super((t_5_h<? extends Z_530_i>)type, worldIn);
    }

    public float n_1700_B(c_1514_x pos) {
        return this.n_1700_B(pos, this.O_508_d);
    }

    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        return 0.0f;
    }

    @Override
    public boolean n_1700_B(LevelAccessor worldIn, a_3160_D spawnReasonIn) {
        return this.n_1700_B(this.b_2312_j(), worldIn) >= 0.0f;
    }

    public boolean w_1484_f() {
        return !this.e_4240_b().M_588_G();
    }

    @Override
    protected void h_1847_R() {
        super.h_1847_R();
        N_4263_v entity = this.y_2622_c();
        if (entity != null && entity.O_508_d == this.O_508_d) {
            this.n_1700_B(entity.b_2312_j(), 5);
            float f = this.R_4764_Y(entity);
            if (this instanceof C_3622_I && ((C_3622_I)this).z_2372_L()) {
                if (f > 10.0f) {
                    this.n_1700_B(true, true);
                }
                return;
            }
            this.G_564_y(f);
            if (f > 10.0f) {
                this.n_1700_B(true, true);
                this.s_956_w.n_1700_B(Goal.n_1700_B.n_1700_B);
            } else if (f > 6.0f) {
                double d0 = (entity.O_3598_v() - this.O_3598_v()) / (double)f;
                double d1 = (entity.X_2960_b() - this.X_2960_b()) / (double)f;
                double d2 = (entity.l_2647_k() - this.l_2647_k()) / (double)f;
                this.v_4262_N(this.I_4348_c().J_1907_R(Math.copySign(d0 * d0 * 0.4, d0), Math.copySign(d1 * d1 * 0.4, d1), Math.copySign(d2 * d2 * 0.4, d2)));
            } else {
                this.s_956_w.J_1907_R(Goal.n_1700_B.n_1700_B);
                float f1 = 2.0f;
                e_2866_D vector3d = new e_2866_D(entity.O_3598_v() - this.O_3598_v(), entity.X_2960_b() - this.X_2960_b(), entity.l_2647_k() - this.l_2647_k()).G_564_y().n_1700_B((double)Math.max(f - 2.0f, 0.0f));
                this.e_4240_b().n_1700_B(this.O_3598_v() + vector3d.J_1907_R, this.X_2960_b() + vector3d.R_4764_Y, this.l_2647_k() + vector3d.G_564_y, this.Q_4569_t());
            }
        }
    }

    protected double Q_4569_t() {
        return 1.0;
    }

    protected void G_564_y(float distance) {
    }
}


