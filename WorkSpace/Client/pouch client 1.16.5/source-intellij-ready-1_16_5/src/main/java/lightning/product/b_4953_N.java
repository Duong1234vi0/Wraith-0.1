/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.I_408_V;
import lightning.product.N_4263_v;
import lightning.product.a_3913_L;
import lightning.product.b_1722_e;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;

public class b_4953_N
extends Goal {
    protected final PathfinderMob n_1700_B;
    private final double J_1907_R;
    private final boolean R_4764_Y;
    private b_1722_e G_564_y;
    private double P_1922_E;
    private double u_1723_Y;
    private double v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private final int s_956_w = 20;
    private long u_2550_I;

    public b_4953_N(PathfinderMob creature, double speedIn, boolean useLongMemory) {
        this.n_1700_B = creature;
        this.J_1907_R = speedIn;
        this.R_4764_Y = useLongMemory;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
    }

    @Override
    public boolean n_1700_B() {
        long i = this.n_1700_B.O_508_d.X_933_l();
        if (i - this.u_2550_I < 20L) {
            return false;
        }
        this.u_2550_I = i;
        r_4811_B livingentity = this.n_1700_B.t_148_a();
        if (livingentity == null) {
            return false;
        }
        if (!livingentity.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        this.G_564_y = this.n_1700_B.e_4240_b().n_1700_B((N_4263_v)livingentity, 0);
        if (this.G_564_y != null) {
            return true;
        }
        return this.n_1700_B(livingentity) >= this.n_1700_B.v_4262_N(livingentity.O_3598_v(), livingentity.X_2960_b(), livingentity.l_2647_k());
    }

    @Override
    public boolean J_1907_R() {
        r_4811_B livingentity = this.n_1700_B.t_148_a();
        if (livingentity == null) {
            return false;
        }
        if (!livingentity.RealmsLongRunningMcoTaskScreen()) {
            return false;
        }
        if (!this.R_4764_Y) {
            return !this.n_1700_B.e_4240_b().M_588_G();
        }
        if (!this.n_1700_B.u_1723_Y(livingentity.b_2312_j())) {
            return false;
        }
        return !(livingentity instanceof a_3913_L) || !livingentity.d_2461_k() && !((a_3913_L)livingentity).G_624_v();
    }

    @Override
    public void R_4764_Y() {
        this.n_1700_B.e_4240_b().n_1700_B(this.G_564_y, this.J_1907_R);
        this.n_1700_B.multiplayerClientSuggestionProvider(true);
        this.w_1484_f = 0;
        this.t_148_a = 0;
    }

    @Override
    public void G_564_y() {
        r_4811_B livingentity = this.n_1700_B.t_148_a();
        if (!I_408_V.P_1922_E.test(livingentity)) {
            this.n_1700_B.R_4764_Y((r_4811_B)null);
        }
        this.n_1700_B.multiplayerClientSuggestionProvider(false);
        this.n_1700_B.e_4240_b().h_1847_R();
    }

    @Override
    public void P_1922_E() {
        r_4811_B livingentity = this.n_1700_B.t_148_a();
        this.n_1700_B.c_3005_b().n_1700_B(livingentity, 30.0f, 30.0f);
        double d0 = this.n_1700_B.v_4262_N(livingentity.O_3598_v(), livingentity.X_2960_b(), livingentity.l_2647_k());
        this.w_1484_f = Math.max(this.w_1484_f - 1, 0);
        if ((this.R_4764_Y || this.n_1700_B.n_3318_d().n_1700_B(livingentity)) && this.w_1484_f <= 0 && (this.P_1922_E == 0.0 && this.u_1723_Y == 0.0 && this.v_4262_N == 0.0 || livingentity.v_4262_N(this.P_1922_E, this.u_1723_Y, this.v_4262_N) >= 1.0 || this.n_1700_B.M_3508_C().nextFloat() < 0.05f)) {
            this.P_1922_E = livingentity.O_3598_v();
            this.u_1723_Y = livingentity.X_2960_b();
            this.v_4262_N = livingentity.l_2647_k();
            this.w_1484_f = 4 + this.n_1700_B.M_3508_C().nextInt(7);
            if (d0 > 1024.0) {
                this.w_1484_f += 10;
            } else if (d0 > 256.0) {
                this.w_1484_f += 5;
            }
            if (!this.n_1700_B.e_4240_b().n_1700_B((N_4263_v)livingentity, this.J_1907_R)) {
                this.w_1484_f += 15;
            }
        }
        this.t_148_a = Math.max(this.t_148_a - 1, 0);
        this.n_1700_B(livingentity, d0);
    }

    protected void n_1700_B(r_4811_B enemy, double distToEnemySqr) {
        double d0 = this.n_1700_B(enemy);
        if (distToEnemySqr <= d0 && this.t_148_a <= 0) {
            this.v_4262_N();
            this.n_1700_B.n_1700_B(x_1688_C.n_1700_B);
            this.n_1700_B.q_2307_F(enemy);
        }
    }

    protected void v_4262_N() {
        this.t_148_a = 20;
    }

    protected boolean w_1484_f() {
        return this.t_148_a <= 0;
    }

    protected int s_956_w() {
        return this.t_148_a;
    }

    protected int u_2550_I() {
        return 20;
    }

    protected double n_1700_B(r_4811_B attackTarget) {
        return this.n_1700_B.C_415_h() * 2.0f * this.n_1700_B.C_415_h() * 2.0f + attackTarget.C_415_h();
    }
}


