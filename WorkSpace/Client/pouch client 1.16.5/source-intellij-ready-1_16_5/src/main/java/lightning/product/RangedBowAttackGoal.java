/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.H_2333_J;
import lightning.product.N_4263_v;
import lightning.product.BowItem;
import lightning.product.Z_530_i;
import lightning.product.RangedAttackMob;
import lightning.product.Monster;
import lightning.product.Goal;
import lightning.product.Items;
import lightning.product.r_4811_B;

public class RangedBowAttackGoal<T extends Monster>
extends Goal {
    private final T n_1700_B;
    private final double J_1907_R;
    private int R_4764_Y;
    private final float G_564_y;
    private int P_1922_E = -1;
    private int u_1723_Y;
    private boolean v_4262_N;
    private boolean w_1484_f;
    private int t_148_a = -1;

    public RangedBowAttackGoal(T mob, double moveSpeedAmpIn, int attackCooldownIn, float maxAttackDistanceIn) {
        this.n_1700_B = mob;
        this.J_1907_R = moveSpeedAmpIn;
        this.R_4764_Y = attackCooldownIn;
        this.G_564_y = maxAttackDistanceIn * maxAttackDistanceIn;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
    }

    public void n_1700_B(int attackCooldownIn) {
        this.R_4764_Y = attackCooldownIn;
    }

    @Override
    public boolean n_1700_B() {
        return ((Z_530_i)this.n_1700_B).t_148_a() == null ? false : this.v_4262_N();
    }

    protected boolean v_4262_N() {
        return ((r_4811_B)this.n_1700_B).n_1700_B(Items.R_1796_s);
    }

    @Override
    public boolean J_1907_R() {
        return (this.n_1700_B() || !((Z_530_i)this.n_1700_B).e_4240_b().M_588_G()) && this.v_4262_N();
    }

    @Override
    public void R_4764_Y() {
        super.R_4764_Y();
        ((Z_530_i)this.n_1700_B).multiplayerClientSuggestionProvider(true);
    }

    @Override
    public void G_564_y() {
        super.G_564_y();
        ((Z_530_i)this.n_1700_B).multiplayerClientSuggestionProvider(false);
        this.u_1723_Y = 0;
        this.P_1922_E = -1;
        ((r_4811_B)this.n_1700_B).Y_259_p();
    }

    @Override
    public void P_1922_E() {
        r_4811_B livingentity = ((Z_530_i)this.n_1700_B).t_148_a();
        if (livingentity != null) {
            boolean flag1;
            double d0 = ((N_4263_v)this.n_1700_B).v_4262_N(livingentity.O_3598_v(), livingentity.X_2960_b(), livingentity.l_2647_k());
            boolean flag = ((Z_530_i)this.n_1700_B).n_3318_d().n_1700_B(livingentity);
            boolean bl = flag1 = this.u_1723_Y > 0;
            if (flag != flag1) {
                this.u_1723_Y = 0;
            }
            this.u_1723_Y = flag ? ++this.u_1723_Y : --this.u_1723_Y;
            if (!(d0 > (double)this.G_564_y) && this.u_1723_Y >= 20) {
                ((Z_530_i)this.n_1700_B).e_4240_b().h_1847_R();
                ++this.t_148_a;
            } else {
                ((Z_530_i)this.n_1700_B).e_4240_b().n_1700_B((N_4263_v)livingentity, this.J_1907_R);
                this.t_148_a = -1;
            }
            if (this.t_148_a >= 20) {
                if ((double)((r_4811_B)this.n_1700_B).M_3508_C().nextFloat() < 0.3) {
                    boolean bl2 = this.v_4262_N = !this.v_4262_N;
                }
                if ((double)((r_4811_B)this.n_1700_B).M_3508_C().nextFloat() < 0.3) {
                    this.w_1484_f = !this.w_1484_f;
                }
                this.t_148_a = 0;
            }
            if (this.t_148_a > -1) {
                if (d0 > (double)(this.G_564_y * 0.75f)) {
                    this.w_1484_f = false;
                } else if (d0 < (double)(this.G_564_y * 0.25f)) {
                    this.w_1484_f = true;
                }
                ((Z_530_i)this.n_1700_B).A_4115_X().n_1700_B(this.w_1484_f ? -0.5f : 0.5f, this.v_4262_N ? 0.5f : -0.5f);
                ((Z_530_i)this.n_1700_B).n_1700_B((N_4263_v)livingentity, 30.0f, 30.0f);
            } else {
                ((Z_530_i)this.n_1700_B).c_3005_b().n_1700_B(livingentity, 30.0f, 30.0f);
            }
            if (((r_4811_B)this.n_1700_B).Y_601_j()) {
                int i;
                if (!flag && this.u_1723_Y < -60) {
                    ((r_4811_B)this.n_1700_B).Y_259_p();
                } else if (flag && (i = ((r_4811_B)this.n_1700_B).g_1031_K()) >= 20) {
                    ((r_4811_B)this.n_1700_B).Y_259_p();
                    ((RangedAttackMob)this.n_1700_B).J_1907_R(livingentity, BowItem.n_1700_B(i));
                    this.P_1922_E = this.R_4764_Y;
                }
            } else if (--this.P_1922_E <= 0 && this.u_1723_Y >= -60) {
                ((r_4811_B)this.n_1700_B).J_1907_R(H_2333_J.n_1700_B(this.n_1700_B, Items.R_1796_s));
            }
        }
    }
}


