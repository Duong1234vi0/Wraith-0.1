/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;
import lightning.product.RangedAttackMob;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class J_548_T
extends Goal {
    private final Z_530_i n_1700_B;
    private final RangedAttackMob J_1907_R;
    private r_4811_B R_4764_Y;
    private int G_564_y = -1;
    private final double P_1922_E;
    private int u_1723_Y;
    private final int v_4262_N;
    private final int w_1484_f;
    private final float t_148_a;
    private final float s_956_w;

    public J_548_T(RangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
        this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn);
    }

    public J_548_T(RangedAttackMob attacker, double movespeed, int p_i1650_4_, int maxAttackTime, float maxAttackDistanceIn) {
        if (!(attacker instanceof r_4811_B)) {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        this.J_1907_R = attacker;
        this.n_1700_B = (Z_530_i)((Object)attacker);
        this.P_1922_E = movespeed;
        this.v_4262_N = p_i1650_4_;
        this.w_1484_f = maxAttackTime;
        this.t_148_a = maxAttackDistanceIn;
        this.s_956_w = maxAttackDistanceIn * maxAttackDistanceIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
    }

    @Override
    public boolean n_1700_B() {
        r_4811_B livingentity = this.n_1700_B.t_148_a();
        if (livingentity != null && livingentity.RealmsLongRunningMcoTaskScreen()) {
            this.R_4764_Y = livingentity;
            return true;
        }
        return false;
    }

    @Override
    public boolean J_1907_R() {
        return this.n_1700_B() || !this.n_1700_B.e_4240_b().M_588_G();
    }

    @Override
    public void G_564_y() {
        this.R_4764_Y = null;
        this.u_1723_Y = 0;
        this.G_564_y = -1;
    }

    @Override
    public void P_1922_E() {
        double d0 = this.n_1700_B.v_4262_N(this.R_4764_Y.O_3598_v(), this.R_4764_Y.X_2960_b(), this.R_4764_Y.l_2647_k());
        boolean flag = this.n_1700_B.n_3318_d().n_1700_B(this.R_4764_Y);
        this.u_1723_Y = flag ? ++this.u_1723_Y : 0;
        if (!(d0 > (double)this.s_956_w) && this.u_1723_Y >= 5) {
            this.n_1700_B.e_4240_b().h_1847_R();
        } else {
            this.n_1700_B.e_4240_b().n_1700_B((N_4263_v)this.R_4764_Y, this.P_1922_E);
        }
        this.n_1700_B.c_3005_b().n_1700_B(this.R_4764_Y, 30.0f, 30.0f);
        if (--this.G_564_y == 0) {
            if (!flag) {
                return;
            }
            float f = u_530_F.n_1700_B(d0) / this.t_148_a;
            float lvt_5_1_ = u_530_F.n_1700_B(f, 0.1f, 1.0f);
            this.J_1907_R.J_1907_R(this.R_4764_Y, lvt_5_1_);
            this.G_564_y = u_530_F.G_564_y(f * (float)(this.w_1484_f - this.v_4262_N) + (float)this.v_4262_N);
        } else if (this.G_564_y < 0) {
            float f2 = u_530_F.n_1700_B(d0) / this.t_148_a;
            this.G_564_y = u_530_F.G_564_y(f2 * (float)(this.w_1484_f - this.v_4262_N) + (float)this.v_4262_N);
        }
    }
}


