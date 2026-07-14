/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.RandomLookAroundGoal;
import lightning.product.AgableMob;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_1869_h;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.Animal;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.R_1815_U;
import lightning.product.TemptGoal;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_1941_L;
import lightning.product.PathfinderMob;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.v_2621_q;

public class X_4861_v
extends Animal {
    private static final b_3278_X Y_259_p = b_3278_X.n_1700_B(Items.G_4691_Q, Items.y_2836_h, Items.WrappedMinMaxBounds, Items.MushroomBlock);
    public float h_1847_R;
    public float Q_4569_t;
    public float M_182_A;
    public float t_1786_h;
    public float multiplayerClientSuggestionProvider = 1.0f;
    public int w_1457_N = this.RealmsWorldOptions.nextInt(6000) + 6000;
    public boolean Y_601_j;

    public X_4861_v(t_5_h<? extends X_4861_v> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
        this.n_1700_B(I_1869_h.w_1484_f, 0.0f);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new PanicGoal(this, 1.4));
        this.s_956_w.n_1700_B(2, new BreedGoal(this, 1.0));
        this.s_956_w.n_1700_B(3, new TemptGoal((PathfinderMob)this, 1.0, false, Y_259_p));
        this.s_956_w.n_1700_B(4, new v_2621_q(this, 1.1));
        this.s_956_w.n_1700_B(5, new g_1941_L(this, 1.0));
        this.s_956_w.n_1700_B(6, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(7, new RandomLookAroundGoal(this));
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return this.d_() ? sizeIn.J_1907_R * 0.85f : sizeIn.J_1907_R * 0.92f;
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 4.0).n_1700_B(Attributes.G_564_y, 0.25);
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        this.t_1786_h = this.h_1847_R;
        this.M_182_A = this.Q_4569_t;
        this.Q_4569_t = (float)((double)this.Q_4569_t + (double)(this.e_1992_r ? -1 : 4) * 0.3);
        this.Q_4569_t = u_530_F.n_1700_B(this.Q_4569_t, 0.0f, 1.0f);
        if (!this.e_1992_r && this.multiplayerClientSuggestionProvider < 1.0f) {
            this.multiplayerClientSuggestionProvider = 1.0f;
        }
        this.multiplayerClientSuggestionProvider = (float)((double)this.multiplayerClientSuggestionProvider * 0.9);
        e_2866_D vector3d = this.I_4348_c();
        if (!this.e_1992_r && vector3d.R_4764_Y < 0.0) {
            this.v_4262_N(vector3d.G_564_y(1.0, 0.6, 1.0));
        }
        this.h_1847_R += this.multiplayerClientSuggestionProvider * 2.0f;
        if (!this.O_508_d.Y_259_p && this.RealmsLongRunningMcoTaskScreen() && !this.d_() && !this.h_1640_b() && --this.w_1457_N <= 0) {
            this.n_1700_B(SoundEvents.U_1341_G, 1.0f, (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
            this.n_1700_B((q_1803_e)Items.s_4405_m);
            this.w_1457_N = this.RealmsWorldOptions.nextInt(6000) + 6000;
        }
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.TextRenderingUtils;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.ClientBootstrap;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.UploadTokenCache;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.o_2341_D, 0.15f, 1.0f);
    }

    public X_4861_v J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.s_956_w.n_1700_B(p_241840_1_);
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return Y_259_p.n_1700_B(stack);
    }

    @Override
    protected int R_4764_Y(a_3913_L player) {
        return this.h_1640_b() ? 10 : super.R_4764_Y(player);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.Y_601_j = compound.t_1786_h("IsChickenJockey");
        if (compound.P_1922_E("EggLayTime")) {
            this.w_1457_N = compound.w_1484_f("EggLayTime");
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("IsChickenJockey", this.Y_601_j);
        compound.J_1907_R("EggLayTime", this.w_1457_N);
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return this.h_1640_b();
    }

    @Override
    public void v_4262_N(N_4263_v passenger) {
        super.v_4262_N(passenger);
        float f = u_530_F.n_1700_B(this.C_1162_e * ((float)Math.PI / 180));
        float f1 = u_530_F.J_1907_R(this.C_1162_e * ((float)Math.PI / 180));
        float f2 = 0.1f;
        float f3 = 0.0f;
        passenger.J_1907_R(this.O_3598_v() + (double)(0.1f * f), this.P_1922_E(0.5) + passenger.O_2151_c() + 0.0, this.l_2647_k() - (double)(0.1f * f1));
        if (passenger instanceof r_4811_B) {
            ((r_4811_B)passenger).C_1162_e = this.C_1162_e;
        }
    }

    public boolean h_1640_b() {
        return this.Y_601_j;
    }

    public void w_1457_N(boolean jockey) {
        this.Y_601_j = jockey;
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }
}



