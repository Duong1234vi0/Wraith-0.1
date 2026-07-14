/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import lightning.product.PathNavigation;
import lightning.product.I_1869_h;
import lightning.product.LookControl;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.FlyingPathNavigation;
import lightning.product.i_2099_H;
import lightning.product.Goal;

public class FollowMobGoal
extends Goal {
    private final Z_530_i n_1700_B;
    private final Predicate<Z_530_i> J_1907_R;
    private Z_530_i R_4764_Y;
    private final double G_564_y;
    private final PathNavigation P_1922_E;
    private int u_1723_Y;
    private final float v_4262_N;
    private float w_1484_f;
    private final float t_148_a;

    public FollowMobGoal(Z_530_i p_i47417_1_, double p_i47417_2_, float p_i47417_4_, float p_i47417_5_) {
        this.n_1700_B = p_i47417_1_;
        this.J_1907_R = p_210291_1_ -> p_210291_1_ != null && p_i47417_1_.getClass() != p_210291_1_.getClass();
        this.G_564_y = p_i47417_2_;
        this.P_1922_E = p_i47417_1_.e_4240_b();
        this.v_4262_N = p_i47417_4_;
        this.t_148_a = p_i47417_5_;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        if (!(p_i47417_1_.e_4240_b() instanceof i_2099_H) && !(p_i47417_1_.e_4240_b() instanceof FlyingPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowMobGoal");
        }
    }

    @Override
    public boolean n_1700_B() {
        List<Z_530_i> list = this.n_1700_B.O_508_d.n_1700_B(Z_530_i.class, this.n_1700_B.i_601_W().grow(this.t_148_a), this.J_1907_R);
        if (!list.isEmpty()) {
            for (Z_530_i mobentity : list) {
                if (mobentity.F_3572_x()) continue;
                this.R_4764_Y = mobentity;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean J_1907_R() {
        return this.R_4764_Y != null && !this.P_1922_E.M_588_G() && this.n_1700_B.G_564_y((N_4263_v)this.R_4764_Y) > (double)(this.v_4262_N * this.v_4262_N);
    }

    @Override
    public void R_4764_Y() {
        this.u_1723_Y = 0;
        this.w_1484_f = this.n_1700_B.n_1700_B(I_1869_h.w_1484_f);
        this.n_1700_B.n_1700_B(I_1869_h.w_1484_f, 0.0f);
    }

    @Override
    public void G_564_y() {
        this.R_4764_Y = null;
        this.P_1922_E.h_1847_R();
        this.n_1700_B.n_1700_B(I_1869_h.w_1484_f, this.w_1484_f);
    }

    @Override
    public void P_1922_E() {
        if (this.R_4764_Y != null && !this.n_1700_B.n_4915_F()) {
            this.n_1700_B.c_3005_b().n_1700_B(this.R_4764_Y, 10.0f, (float)this.n_1700_B.Z_976_R());
            if (--this.u_1723_Y <= 0) {
                double d2;
                double d1;
                this.u_1723_Y = 10;
                double d0 = this.n_1700_B.O_3598_v() - this.R_4764_Y.O_3598_v();
                double d3 = d0 * d0 + (d1 = this.n_1700_B.X_2960_b() - this.R_4764_Y.X_2960_b()) * d1 + (d2 = this.n_1700_B.l_2647_k() - this.R_4764_Y.l_2647_k()) * d2;
                if (!(d3 <= (double)(this.v_4262_N * this.v_4262_N))) {
                    this.P_1922_E.n_1700_B((N_4263_v)this.R_4764_Y, this.G_564_y);
                } else {
                    this.P_1922_E.h_1847_R();
                    LookControl lookcontroller = this.R_4764_Y.c_3005_b();
                    if (d3 <= (double)this.v_4262_N || lookcontroller.G_564_y() == this.n_1700_B.O_3598_v() && lookcontroller.P_1922_E() == this.n_1700_B.X_2960_b() && lookcontroller.u_1723_Y() == this.n_1700_B.l_2647_k()) {
                        double d4 = this.R_4764_Y.O_3598_v() - this.n_1700_B.O_3598_v();
                        double d5 = this.R_4764_Y.l_2647_k() - this.n_1700_B.l_2647_k();
                        this.P_1922_E.n_1700_B(this.n_1700_B.O_3598_v() - d4, this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k() - d5, this.G_564_y);
                    }
                }
            }
        }
    }
}


