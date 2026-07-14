/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.D_1436_R;
import lightning.product.Attributes;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_1722_e;
import lightning.product.TargetingConditions;
import lightning.product.o_3050_h;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public abstract class J_133_e
extends Goal {
    protected final Z_530_i P_1922_E;
    protected final boolean u_1723_Y;
    private final boolean n_1700_B;
    private int J_1907_R;
    private int R_4764_Y;
    private int G_564_y;
    protected r_4811_B v_4262_N;
    protected int w_1484_f = 60;

    public J_133_e(Z_530_i mobIn, boolean checkSight) {
        this(mobIn, checkSight, false);
    }

    public J_133_e(Z_530_i mobIn, boolean checkSight, boolean nearbyOnlyIn) {
        this.P_1922_E = mobIn;
        this.u_1723_Y = checkSight;
        this.n_1700_B = nearbyOnlyIn;
    }

    @Override
    public boolean J_1907_R() {
        r_4811_B livingentity = this.P_1922_E.t_148_a();
        if (livingentity == null) {
            livingentity = this.v_4262_N;
        }
        if (livingentity == null) {
            return false;
        }
        if (!livingentity.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        o_3050_h team = this.P_1922_E.L_1362_X();
        o_3050_h team1 = livingentity.L_1362_X();
        if (team != null && team1 == team) {
            return false;
        }
        double d0 = this.u_2550_I();
        if (this.P_1922_E.G_564_y((N_4263_v)livingentity) > d0 * d0) {
            return false;
        }
        if (this.u_1723_Y) {
            if (this.P_1922_E.n_3318_d().n_1700_B(livingentity)) {
                this.G_564_y = 0;
            } else if (++this.G_564_y > this.w_1484_f) {
                return false;
            }
        }
        if (livingentity instanceof a_3913_L && ((a_3913_L)livingentity).C_415_h.n_1700_B) {
            return false;
        }
        this.P_1922_E.R_4764_Y(livingentity);
        return true;
    }

    protected double u_2550_I() {
        return this.P_1922_E.J_1907_R(Attributes.J_1907_R);
    }

    @Override
    public void R_4764_Y() {
        this.J_1907_R = 0;
        this.R_4764_Y = 0;
        this.G_564_y = 0;
    }

    @Override
    public void G_564_y() {
        this.P_1922_E.R_4764_Y((r_4811_B)null);
        this.v_4262_N = null;
    }

    protected boolean n_1700_B(@Nullable r_4811_B potentialTarget, TargetingConditions targetPredicate) {
        if (potentialTarget == null) {
            return false;
        }
        if (!targetPredicate.n_1700_B(this.P_1922_E, potentialTarget)) {
            return false;
        }
        if (!this.P_1922_E.u_1723_Y(potentialTarget.b_2312_j())) {
            return false;
        }
        if (this.n_1700_B) {
            if (--this.R_4764_Y <= 0) {
                this.J_1907_R = 0;
            }
            if (this.J_1907_R == 0) {
                int n = this.J_1907_R = this.n_1700_B(potentialTarget) ? 1 : 2;
            }
            if (this.J_1907_R == 2) {
                return false;
            }
        }
        return true;
    }

    private boolean n_1700_B(r_4811_B target) {
        int j;
        this.R_4764_Y = 10 + this.P_1922_E.M_3508_C().nextInt(5);
        b_1722_e path = this.P_1922_E.e_4240_b().n_1700_B((N_4263_v)target, 0);
        if (path == null) {
            return false;
        }
        D_1436_R pathpoint = path.G_564_y();
        if (pathpoint == null) {
            return false;
        }
        int i = pathpoint.n_1700_B - u_530_F.R_4764_Y(target.O_3598_v());
        return (double)(i * i + (j = pathpoint.R_4764_Y - u_530_F.R_4764_Y(target.l_2647_k())) * j) <= 2.25;
    }

    public J_133_e n_1700_B(int unseenMemoryTicksIn) {
        this.w_1484_f = unseenMemoryTicksIn;
        return this;
    }
}


