/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.I_408_V;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.TargetingConditions;
import lightning.product.Goal;
import lightning.product.r_4811_B;

public class LookAtPlayerGoal
extends Goal {
    protected final Z_530_i n_1700_B;
    protected N_4263_v J_1907_R;
    protected final float R_4764_Y;
    private int v_4262_N;
    protected final float G_564_y;
    protected final Class<? extends r_4811_B> P_1922_E;
    protected final TargetingConditions u_1723_Y;

    public LookAtPlayerGoal(Z_530_i entityIn, Class<? extends r_4811_B> watchTargetClass, float maxDistance) {
        this(entityIn, watchTargetClass, maxDistance, 0.02f);
    }

    public LookAtPlayerGoal(Z_530_i entityIn, Class<? extends r_4811_B> watchTargetClass, float maxDistance, float chanceIn) {
        this.n_1700_B = entityIn;
        this.P_1922_E = watchTargetClass;
        this.R_4764_Y = maxDistance;
        this.G_564_y = chanceIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.J_1907_R));
        this.u_1723_Y = watchTargetClass == a_3913_L.class ? new TargetingConditions().n_1700_B(maxDistance).J_1907_R().n_1700_B().G_564_y().n_1700_B((r_4811_B target) -> I_408_V.J_1907_R(entityIn).test((N_4263_v)target)) : new TargetingConditions().n_1700_B(maxDistance).J_1907_R().n_1700_B().G_564_y();
    }

    @Override
    public boolean n_1700_B() {
        if (this.n_1700_B.M_3508_C().nextFloat() >= this.G_564_y) {
            return false;
        }
        if (this.n_1700_B.t_148_a() != null) {
            this.J_1907_R = this.n_1700_B.t_148_a();
        }
        this.J_1907_R = this.P_1922_E == a_3913_L.class ? this.n_1700_B.O_508_d.n_1700_B(this.u_1723_Y, this.n_1700_B, this.n_1700_B.O_3598_v(), this.n_1700_B.X_2048_Y(), this.n_1700_B.l_2647_k()) : this.n_1700_B.O_508_d.J_1907_R(this.P_1922_E, this.u_1723_Y, this.n_1700_B, this.n_1700_B.O_3598_v(), this.n_1700_B.X_2048_Y(), this.n_1700_B.l_2647_k(), this.n_1700_B.i_601_W().grow(this.R_4764_Y, 3.0, this.R_4764_Y));
        return this.J_1907_R != null;
    }

    @Override
    public boolean J_1907_R() {
        if (!this.J_1907_R.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        if (this.n_1700_B.G_564_y(this.J_1907_R) > (double)(this.R_4764_Y * this.R_4764_Y)) {
            return false;
        }
        return this.v_4262_N > 0;
    }

    @Override
    public void R_4764_Y() {
        this.v_4262_N = 40 + this.n_1700_B.M_3508_C().nextInt(40);
    }

    @Override
    public void G_564_y() {
        this.J_1907_R = null;
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B.c_3005_b().n_1700_B(this.J_1907_R.O_3598_v(), this.J_1907_R.X_2048_Y(), this.J_1907_R.l_2647_k());
        --this.v_4262_N;
    }
}


