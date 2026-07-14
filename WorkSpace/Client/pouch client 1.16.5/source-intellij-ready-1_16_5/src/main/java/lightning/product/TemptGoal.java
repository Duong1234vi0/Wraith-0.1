/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.FlyingPathNavigation;
import lightning.product.b_3278_X;
import lightning.product.TargetingConditions;
import lightning.product.i_2099_H;
import lightning.product.PathfinderMob;
import lightning.product.Goal;

public class TemptGoal
extends Goal {
    private static final TargetingConditions R_4764_Y = new TargetingConditions().n_1700_B(10.0).n_1700_B().J_1907_R().G_564_y().R_4764_Y();
    protected final PathfinderMob n_1700_B;
    private final double G_564_y;
    private double P_1922_E;
    private double u_1723_Y;
    private double v_4262_N;
    private double w_1484_f;
    private double t_148_a;
    protected a_3913_L J_1907_R;
    private int s_956_w;
    private boolean u_2550_I;
    private final b_3278_X M_588_G;
    private final boolean P_4830_p;

    public TemptGoal(PathfinderMob creatureIn, double speedIn, b_3278_X temptItemsIn, boolean scaredByPlayerMovementIn) {
        this(creatureIn, speedIn, scaredByPlayerMovementIn, temptItemsIn);
    }

    public TemptGoal(PathfinderMob creatureIn, double speedIn, boolean scaredByPlayerMovementIn, b_3278_X temptItemsIn) {
        this.n_1700_B = creatureIn;
        this.G_564_y = speedIn;
        this.M_588_G = temptItemsIn;
        this.P_4830_p = scaredByPlayerMovementIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        if (!(creatureIn.e_4240_b() instanceof i_2099_H) && !(creatureIn.e_4240_b() instanceof FlyingPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
        }
    }

    @Override
    public boolean n_1700_B() {
        if (this.s_956_w > 0) {
            --this.s_956_w;
            return false;
        }
        this.J_1907_R = this.n_1700_B.O_508_d.n_1700_B(R_4764_Y, this.n_1700_B);
        if (this.J_1907_R == null) {
            return false;
        }
        return this.n_1700_B(this.J_1907_R.A_2714_y()) || this.n_1700_B(this.J_1907_R.S_4035_N());
    }

    protected boolean n_1700_B(Z_1993_T stack) {
        return this.M_588_G.n_1700_B(stack);
    }

    @Override
    public boolean J_1907_R() {
        if (this.v_4262_N()) {
            if (this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R) < 36.0) {
                if (this.J_1907_R.v_4262_N(this.P_1922_E, this.u_1723_Y, this.v_4262_N) > 0.010000000000000002) {
                    return false;
                }
                if (Math.abs((double)this.J_1907_R.f_4016_n - this.w_1484_f) > 5.0 || Math.abs((double)this.J_1907_R.p_178_J - this.t_148_a) > 5.0) {
                    return false;
                }
            } else {
                this.P_1922_E = this.J_1907_R.O_3598_v();
                this.u_1723_Y = this.J_1907_R.X_2960_b();
                this.v_4262_N = this.J_1907_R.l_2647_k();
            }
            this.w_1484_f = this.J_1907_R.f_4016_n;
            this.t_148_a = this.J_1907_R.p_178_J;
        }
        return this.n_1700_B();
    }

    protected boolean v_4262_N() {
        return this.P_4830_p;
    }

    @Override
    public void R_4764_Y() {
        this.P_1922_E = this.J_1907_R.O_3598_v();
        this.u_1723_Y = this.J_1907_R.X_2960_b();
        this.v_4262_N = this.J_1907_R.l_2647_k();
        this.u_2550_I = true;
    }

    @Override
    public void G_564_y() {
        this.J_1907_R = null;
        this.n_1700_B.e_4240_b().h_1847_R();
        this.s_956_w = 100;
        this.u_2550_I = false;
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B.c_3005_b().n_1700_B(this.J_1907_R, (float)(this.n_1700_B.H_1990_U() + 20), (float)this.n_1700_B.Z_976_R());
        if (this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R) < 6.25) {
            this.n_1700_B.e_4240_b().h_1847_R();
        } else {
            this.n_1700_B.e_4240_b().n_1700_B((N_4263_v)this.J_1907_R, this.G_564_y);
        }
    }

    public boolean w_1484_f() {
        return this.u_2550_I;
    }
}


