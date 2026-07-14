/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Predicate;
import lightning.product.A_2352_Z;
import lightning.product.R_2450_T;
import lightning.product.T_2915_h;
import lightning.product.Z_530_i;
import lightning.product.c_1219_i;

public class BreakDoorGoal
extends c_1219_i {
    private final Predicate<R_2450_T> v_4262_N;
    protected int n_1700_B;
    protected int J_1907_R = -1;
    protected int R_4764_Y = -1;

    public BreakDoorGoal(Z_530_i entity, Predicate<R_2450_T> difficultyPredicate) {
        super(entity);
        this.v_4262_N = difficultyPredicate;
    }

    public BreakDoorGoal(Z_530_i entity, int timeToBreak, Predicate<R_2450_T> difficultyPredicate) {
        this(entity, difficultyPredicate);
        this.R_4764_Y = timeToBreak;
    }

    protected int u_1723_Y() {
        return Math.max(240, this.R_4764_Y);
    }

    @Override
    public boolean n_1700_B() {
        if (!super.n_1700_B()) {
            return false;
        }
        if (!this.G_564_y.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
            return false;
        }
        return this.n_1700_B(this.G_564_y.O_508_d.x_607_J()) && !this.v_4262_N();
    }

    @Override
    public void R_4764_Y() {
        super.R_4764_Y();
        this.n_1700_B = 0;
    }

    @Override
    public boolean J_1907_R() {
        return this.n_1700_B <= this.u_1723_Y() && !this.v_4262_N() && this.P_1922_E.withinDistance(this.G_564_y.s_4990_V(), 2.0) && this.n_1700_B(this.G_564_y.O_508_d.x_607_J());
    }

    @Override
    public void G_564_y() {
        super.G_564_y();
        this.G_564_y.O_508_d.n_1700_B(this.G_564_y.j_276_v(), this.P_1922_E, -1);
    }

    @Override
    public void P_1922_E() {
        super.P_1922_E();
        if (this.G_564_y.M_3508_C().nextInt(20) == 0) {
            this.G_564_y.O_508_d.R_4764_Y(1019, this.P_1922_E, 0);
            if (!this.G_564_y.RealmsCreateRealmScreen) {
                this.G_564_y.n_1700_B(this.G_564_y.Q_2552_b());
            }
        }
        ++this.n_1700_B;
        int i = (int)((float)this.n_1700_B / (float)this.u_1723_Y() * 10.0f);
        if (i != this.J_1907_R) {
            this.G_564_y.O_508_d.n_1700_B(this.G_564_y.j_276_v(), this.P_1922_E, i);
            this.J_1907_R = i;
        }
        if (this.n_1700_B == this.u_1723_Y() && this.n_1700_B(this.G_564_y.O_508_d.x_607_J())) {
            this.G_564_y.O_508_d.n_1700_B(this.P_1922_E, false);
            this.G_564_y.O_508_d.R_4764_Y(1021, this.P_1922_E, 0);
            this.G_564_y.O_508_d.R_4764_Y(2001, this.P_1922_E, T_2915_h.s_956_w(this.G_564_y.O_508_d.getBlockState(this.P_1922_E)));
        }
    }

    private boolean n_1700_B(R_2450_T difficulty) {
        return this.v_4262_N.test(difficulty);
    }
}


