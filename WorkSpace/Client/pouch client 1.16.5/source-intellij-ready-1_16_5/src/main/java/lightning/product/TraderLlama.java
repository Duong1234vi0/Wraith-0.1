/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import javax.annotation.Nullable;
import lightning.product.AgableMob;
import lightning.product.J_133_e;
import lightning.product.PanicGoal;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.T_426_Y;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.ServerLevelAccessor;
import lightning.product.TargetingConditions;
import lightning.product.g_4407_j;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public class TraderLlama
extends g_4407_j {
    private int Q_2552_b = 47999;

    public TraderLlama(t_5_h<? extends TraderLlama> p_i50234_1_, b_4507_u p_i50234_2_) {
        super((t_5_h<? extends g_4407_j>)p_i50234_1_, p_i50234_2_);
    }

    @Override
    public boolean s_3815_K() {
        return true;
    }

    @Override
    protected g_4407_j f_887_Z() {
        return t_5_h.F_1410_V.n_1700_B(this.O_508_d);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("DespawnDelay", this.Q_2552_b);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("DespawnDelay", 99)) {
            this.Q_2552_b = compound.w_1484_f("DespawnDelay");
        }
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(1, new PanicGoal(this, 2.0));
        this.u_2550_I.n_1700_B(1, new n_1700_B(this, this));
    }

    @Override
    protected void v_4262_N(a_3913_L player) {
        N_4263_v entity = this.y_2622_c();
        if (!(entity instanceof T_426_Y)) {
            super.v_4262_N(player);
        }
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (!this.O_508_d.Y_259_p) {
            this.h_3858_e();
        }
    }

    private void h_3858_e() {
        if (this.l_4397_i()) {
            int n = this.Q_2552_b = this.t_4433_T() ? ((T_426_Y)this.y_2622_c()).h_973_D() - 1 : this.Q_2552_b - 1;
            if (this.Q_2552_b <= 0) {
                this.n_1700_B(true, false);
                this.Ops();
            }
        }
    }

    private boolean l_4397_i() {
        return !this.o_4117_e() && !this.AimAssist() && !this.l_697_B();
    }

    private boolean t_4433_T() {
        return this.y_2622_c() instanceof T_426_Y;
    }

    private boolean AimAssist() {
        return this.n_4915_F() && !this.t_4433_T();
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        if (reason == a_3160_D.w_1484_f) {
            this.b_(0);
        }
        if (spawnDataIn == null) {
            spawnDataIn = new AgableMob.n_1700_B(false);
        }
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public class n_1700_B
    extends J_133_e {
        private final g_4407_j n_1700_B;
        private r_4811_B J_1907_R;
        private int R_4764_Y;

        public n_1700_B(TraderLlama this$0, g_4407_j p_i50458_2_) {
            super(p_i50458_2_, false);
            this.n_1700_B = p_i50458_2_;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.G_564_y));
        }

        @Override
        public boolean n_1700_B() {
            if (!this.n_1700_B.n_4915_F()) {
                return false;
            }
            N_4263_v entity = this.n_1700_B.y_2622_c();
            if (!(entity instanceof T_426_Y)) {
                return false;
            }
            T_426_Y wanderingtraderentity = (T_426_Y)entity;
            this.J_1907_R = wanderingtraderentity.q_817_e();
            int i = wanderingtraderentity.r_260_T();
            return i != this.R_4764_Y && this.n_1700_B(this.J_1907_R, TargetingConditions.n_1700_B);
        }

        @Override
        public void R_4764_Y() {
            this.P_1922_E.R_4764_Y(this.J_1907_R);
            N_4263_v entity = this.n_1700_B.y_2622_c();
            if (entity instanceof T_426_Y) {
                this.R_4764_Y = ((T_426_Y)entity).r_260_T();
            }
            super.R_4764_Y();
        }
    }
}



