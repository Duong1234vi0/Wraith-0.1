/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.B_4088_l;
import lightning.product.PathNavigation;
import lightning.product.C_4114_x;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_408_V;
import lightning.product.RandomSwimmingGoal;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.R_1815_U;
import lightning.product.MoveControl;
import lightning.product.AvoidEntityGoal;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_1972_S;
import lightning.product.e_2866_D;
import lightning.product.WaterAnimal;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.Items;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;

public abstract class AbstractFish
extends WaterAnimal {
    private static final h_256_u<Boolean> n_1700_B = C_4114_x.n_1700_B(AbstractFish.class, EntityDataSerializers.t_148_a);

    public AbstractFish(t_5_h<? extends AbstractFish> type, b_4507_u worldIn) {
        super((t_5_h<? extends WaterAnimal>)type, worldIn);
        this.v_4262_N = new n_1700_B(this);
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R * 0.65f;
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 3.0);
    }

    @Override
    public boolean e_2887_G() {
        return super.e_2887_G() || this.y_2447_C();
    }

    public static boolean J_1907_R(t_5_h<? extends AbstractFish> type, LevelAccessor worldIn, a_3160_D reason, c_1514_x p_223363_3_, Random randomIn) {
        return worldIn.getBlockState(p_223363_3_).n_1700_B(a_3742_W.c_3005_b) && worldIn.getBlockState(p_223363_3_.up()).n_1700_B(a_3742_W.c_3005_b);
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return !this.y_2447_C() && !this.t_3452_g();
    }

    @Override
    public int c_4037_x() {
        return 8;
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, false);
    }

    private boolean y_2447_C() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    public void w_1457_N(boolean p_203706_1_) {
        this.l_4537_E.J_1907_R(n_1700_B, p_203706_1_);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("FromBucket", this.y_2447_C());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.w_1457_N(compound.t_1786_h("FromBucket"));
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(0, new PanicGoal(this, 1.25));
        this.s_956_w.n_1700_B(2, new AvoidEntityGoal<a_3913_L>(this, a_3913_L.class, 8.0f, 1.6, 1.4, I_408_V.v_4262_N::test));
        this.s_956_w.n_1700_B(4, new J_1907_R(this));
    }

    @Override
    protected PathNavigation J_1907_R(b_4507_u worldIn) {
        return new c_1972_S(this, worldIn);
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        if (this.w_1457_N() && this.RowButton()) {
            this.n_1700_B(0.01f, travelVector);
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
            this.v_4262_N(this.I_4348_c().n_1700_B(0.9));
            if (this.t_148_a() == null) {
                this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.005, 0.0));
            }
        } else {
            super.w_1484_f(travelVector);
        }
    }

    @Override
    public void Y_1740_V() {
        if (!this.RowButton() && this.e_1992_r && this.k_3961_g) {
            this.v_4262_N(this.I_4348_c().J_1907_R((this.RealmsWorldOptions.nextFloat() * 2.0f - 1.0f) * 0.05f, 0.4f, (this.RealmsWorldOptions.nextFloat() * 2.0f - 1.0f) * 0.05f));
            this.e_1992_r = false;
            this.LongRunningTask = true;
            this.n_1700_B(this.V_1176_p(), this.d_4500_Q(), this.O_2761_o());
        }
        super.Y_1740_V();
    }

    @Override
    protected m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (itemstack.J_1907_R() == Items.W_2770_z && this.RealmsLongRunningMcoTaskScreen()) {
            this.n_1700_B(SoundEvents.RealmsParentalConsentScreen, 1.0f, 1.0f);
            itemstack.v_4262_N(1);
            Z_1993_T itemstack1 = this.y_4642_Y();
            this.u_2550_I(itemstack1);
            if (!this.O_508_d.Y_259_p) {
                U_3554_Q.s_956_w.n_1700_B((B_4088_l)p_230254_1_, itemstack1);
            }
            if (itemstack.n_1700_B()) {
                p_230254_1_.n_1700_B(p_230254_2_, itemstack1);
            } else if (!p_230254_1_.l_1268_F.P_1922_E(itemstack1)) {
                p_230254_1_.n_1700_B(itemstack1, false);
            }
            this.Ops();
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    protected void u_2550_I(Z_1993_T bucket) {
        if (this.t_3452_g()) {
            bucket.n_1700_B(this.k_2302_P());
        }
    }

    protected abstract Z_1993_T y_4642_Y();

    protected boolean h_1640_b() {
        return true;
    }

    protected abstract SoundEvent V_1176_p();

    @Override
    protected SoundEvent F_1410_V() {
        return SoundEvents.k_2348_i;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
    }

    static class n_1700_B
    extends MoveControl {
        private final AbstractFish t_148_a;

        n_1700_B(AbstractFish fish) {
            super(fish);
            this.t_148_a = fish;
        }

        @Override
        public void n_1700_B() {
            if (((N_4263_v)this.t_148_a).n_1700_B(FluidTags.J_1907_R)) {
                this.t_148_a.v_4262_N(this.t_148_a.I_4348_c().J_1907_R(0.0, 0.005, 0.0));
            }
            if (this.w_1484_f == MoveControl.n_1700_B.J_1907_R && !this.t_148_a.e_4240_b().M_588_G()) {
                float f = (float)(this.P_1922_E * this.t_148_a.J_1907_R(Attributes.G_564_y));
                this.t_148_a.w_1457_N(u_530_F.v_4262_N(0.125f, this.t_148_a.l_2995_s(), f));
                double d0 = this.J_1907_R - this.t_148_a.O_3598_v();
                double d1 = this.R_4764_Y - this.t_148_a.X_2960_b();
                double d2 = this.G_564_y - this.t_148_a.l_2647_k();
                if (d1 != 0.0) {
                    double d3 = u_530_F.n_1700_B(d0 * d0 + d1 * d1 + d2 * d2);
                    this.t_148_a.v_4262_N(this.t_148_a.I_4348_c().J_1907_R(0.0, (double)this.t_148_a.l_2995_s() * (d1 / d3) * 0.1, 0.0));
                }
                if (d0 != 0.0 || d2 != 0.0) {
                    float f1 = (float)(u_530_F.G_564_y(d2, d0) * 57.2957763671875) - 90.0f;
                    this.t_148_a.C_1162_e = this.t_148_a.p_178_J = this.n_1700_B(this.t_148_a.p_178_J, f1, 90.0f);
                }
            } else {
                this.t_148_a.w_1457_N(0.0f);
            }
        }
    }

    static class J_1907_R
    extends RandomSwimmingGoal {
        private final AbstractFish w_1484_f;

        public J_1907_R(AbstractFish fish) {
            super(fish, 1.0, 40);
            this.w_1484_f = fish;
        }

        @Override
        public boolean n_1700_B() {
            return this.w_1484_f.h_1640_b() && super.n_1700_B();
        }
    }
}


