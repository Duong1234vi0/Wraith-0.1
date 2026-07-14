/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.H_2333_J;
import lightning.product.J_2548_M;
import lightning.product.M_4954_p;
import lightning.product.N_4263_v;
import lightning.product.Z_1630_j;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.RangedAttackMob;
import lightning.product.Monster;
import lightning.product.Goal;
import lightning.product.Items;
import lightning.product.r_4811_B;

public class RangedCrossbowAttackGoal<T extends Monster & M_4954_p>
extends Goal {
    public static final J_2548_M n_1700_B = new J_2548_M(20, 40);
    private final T J_1907_R;
    private n_1700_B R_4764_Y = lightning.product.RangedCrossbowAttackGoal$n_1700_B.n_1700_B;
    private final double G_564_y;
    private final float P_1922_E;
    private int u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;

    public RangedCrossbowAttackGoal(T shooter, double speed, float p_i50322_4_) {
        this.J_1907_R = shooter;
        this.G_564_y = speed;
        this.P_1922_E = p_i50322_4_ * p_i50322_4_;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
    }

    @Override
    public boolean n_1700_B() {
        return this.w_1484_f() && this.v_4262_N();
    }

    private boolean v_4262_N() {
        return ((r_4811_B)this.J_1907_R).n_1700_B(Items.V_2454_J);
    }

    @Override
    public boolean J_1907_R() {
        return this.w_1484_f() && (this.n_1700_B() || !((Z_530_i)this.J_1907_R).e_4240_b().M_588_G()) && this.v_4262_N();
    }

    private boolean w_1484_f() {
        return ((Z_530_i)this.J_1907_R).t_148_a() != null && ((Z_530_i)this.J_1907_R).t_148_a().RealmsLongRunningMcoTaskScreen();
    }

    @Override
    public void G_564_y() {
        super.G_564_y();
        ((Z_530_i)this.J_1907_R).multiplayerClientSuggestionProvider(false);
        ((Z_530_i)this.J_1907_R).R_4764_Y((r_4811_B)null);
        this.u_1723_Y = 0;
        if (((r_4811_B)this.J_1907_R).Y_601_j()) {
            ((r_4811_B)this.J_1907_R).Y_259_p();
            ((M_4954_p)this.J_1907_R).J_1907_R(false);
            Z_1630_j.n_1700_B(((r_4811_B)this.J_1907_R).B_2580_P(), false);
        }
    }

    @Override
    public void P_1922_E() {
        r_4811_B livingentity = ((Z_530_i)this.J_1907_R).t_148_a();
        if (livingentity != null) {
            boolean flag2;
            boolean flag1;
            boolean flag = ((Z_530_i)this.J_1907_R).n_3318_d().n_1700_B(livingentity);
            boolean bl = flag1 = this.u_1723_Y > 0;
            if (flag != flag1) {
                this.u_1723_Y = 0;
            }
            this.u_1723_Y = flag ? ++this.u_1723_Y : --this.u_1723_Y;
            double d0 = ((N_4263_v)this.J_1907_R).G_564_y(livingentity);
            boolean bl2 = flag2 = (d0 > (double)this.P_1922_E || this.u_1723_Y < 5) && this.v_4262_N == 0;
            if (flag2) {
                --this.w_1484_f;
                if (this.w_1484_f <= 0) {
                    ((Z_530_i)this.J_1907_R).e_4240_b().n_1700_B((N_4263_v)livingentity, this.s_956_w() ? this.G_564_y : this.G_564_y * 0.5);
                    this.w_1484_f = n_1700_B.n_1700_B(((r_4811_B)this.J_1907_R).M_3508_C());
                }
            } else {
                this.w_1484_f = 0;
                ((Z_530_i)this.J_1907_R).e_4240_b().h_1847_R();
            }
            ((Z_530_i)this.J_1907_R).c_3005_b().n_1700_B(livingentity, 30.0f, 30.0f);
            if (this.R_4764_Y == lightning.product.RangedCrossbowAttackGoal$n_1700_B.n_1700_B) {
                if (!flag2) {
                    ((r_4811_B)this.J_1907_R).J_1907_R(H_2333_J.n_1700_B(this.J_1907_R, Items.V_2454_J));
                    this.R_4764_Y = lightning.product.RangedCrossbowAttackGoal$n_1700_B.J_1907_R;
                    ((M_4954_p)this.J_1907_R).J_1907_R(true);
                }
            } else if (this.R_4764_Y == lightning.product.RangedCrossbowAttackGoal$n_1700_B.J_1907_R) {
                Z_1993_T itemstack;
                int i;
                if (!((r_4811_B)this.J_1907_R).Y_601_j()) {
                    this.R_4764_Y = lightning.product.RangedCrossbowAttackGoal$n_1700_B.n_1700_B;
                }
                if ((i = ((r_4811_B)this.J_1907_R).g_1031_K()) >= Z_1630_j.v_4262_N(itemstack = ((r_4811_B)this.J_1907_R).B_2580_P())) {
                    ((r_4811_B)this.J_1907_R).g_134_G();
                    this.R_4764_Y = lightning.product.RangedCrossbowAttackGoal$n_1700_B.R_4764_Y;
                    this.v_4262_N = 20 + ((r_4811_B)this.J_1907_R).M_3508_C().nextInt(20);
                    ((M_4954_p)this.J_1907_R).J_1907_R(false);
                }
            } else if (this.R_4764_Y == lightning.product.RangedCrossbowAttackGoal$n_1700_B.R_4764_Y) {
                --this.v_4262_N;
                if (this.v_4262_N == 0) {
                    this.R_4764_Y = lightning.product.RangedCrossbowAttackGoal$n_1700_B.G_564_y;
                }
            } else if (this.R_4764_Y == lightning.product.RangedCrossbowAttackGoal$n_1700_B.G_564_y && flag) {
                ((RangedAttackMob)this.J_1907_R).J_1907_R(livingentity, 1.0f);
                Z_1993_T itemstack1 = ((r_4811_B)this.J_1907_R).R_4764_Y(H_2333_J.n_1700_B(this.J_1907_R, Items.V_2454_J));
                Z_1630_j.n_1700_B(itemstack1, false);
                this.R_4764_Y = lightning.product.RangedCrossbowAttackGoal$n_1700_B.n_1700_B;
            }
        }
    }

    private boolean s_956_w() {
        return this.R_4764_Y == lightning.product.RangedCrossbowAttackGoal$n_1700_B.n_1700_B;
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.RangedCrossbowAttackGoal$n_1700_B.n_1700_B();
        }
    }
}


