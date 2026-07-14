/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.Attributes;
import lightning.product.J_548_T;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.Container;
import lightning.product.Animal;
import lightning.product.ItemTags;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.WoolCarpetBlock;
import lightning.product.FloatGoal;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.W_3443_Y;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.e_933_M;
import lightning.product.g_1941_L;
import lightning.product.g_3408_G;
import lightning.product.RangedAttackMob;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.q_1613_l;
import lightning.product.q_2335_j;
import lightning.product.Items;
import lightning.product.r_214_x;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.v_2621_q;
import lightning.product.w_4807_f;
import lightning.product.RunAroundLikeCrazyGoal;

public class g_4407_j
extends W_3443_Y
implements RangedAttackMob {
    private static final b_3278_X Q_2552_b = b_3278_X.n_1700_B(Items.V_3441_j, a_3742_W.M_4609_z.u_1723_Y());
    private static final h_256_u<Integer> C_2741_M = C_4114_x.n_1700_B(g_4407_j.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> k_2293_S = C_4114_x.n_1700_B(g_4407_j.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> q_2307_F = C_4114_x.n_1700_B(g_4407_j.class, EntityDataSerializers.J_1907_R);
    private boolean Z_875_P;
    @Nullable
    private g_4407_j c_3005_b;
    @Nullable
    private g_4407_j H_2857_Y;

    public g_4407_j(t_5_h<? extends g_4407_j> type, b_4507_u worldIn) {
        super((t_5_h<? extends W_3443_Y>)type, worldIn);
    }

    public boolean s_3815_K() {
        return false;
    }

    private void k_2293_S(int strengthIn) {
        this.l_4537_E.J_1907_R(C_2741_M, Math.max(1, Math.min(5, strengthIn)));
    }

    private void h_3858_e() {
        int i = this.RealmsWorldOptions.nextFloat() < 0.04f ? 5 : 3;
        this.k_2293_S(1 + this.RealmsWorldOptions.nextInt(i));
    }

    public int N_2266_w() {
        return this.l_4537_E.n_1700_B(C_2741_M);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("Variant", this.P_3676_m());
        compound.J_1907_R("Strength", this.N_2266_w());
        if (!this.t_1786_h.s_956_w(1).n_1700_B()) {
            compound.n_1700_B("DecorItem", this.t_1786_h.s_956_w(1).J_1907_R(new U_2912_j()));
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        this.k_2293_S(compound.w_1484_f("Strength"));
        super.J_1907_R(compound);
        this.C_2741_M(compound.w_1484_f("Variant"));
        if (compound.R_4764_Y("DecorItem", 10)) {
            this.t_1786_h.J_1907_R(1, Z_1993_T.n_1700_B(compound.M_182_A("DecorItem")));
        }
        this.Module();
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new RunAroundLikeCrazyGoal(this, 1.2));
        this.s_956_w.n_1700_B(2, new w_4807_f(this, 2.1f));
        this.s_956_w.n_1700_B(3, new J_548_T(this, 1.25, 40, 20.0f));
        this.s_956_w.n_1700_B(3, new PanicGoal(this, 1.2));
        this.s_956_w.n_1700_B(4, new BreedGoal(this, 1.0));
        this.s_956_w.n_1700_B(5, new v_2621_q(this, 1.0));
        this.s_956_w.n_1700_B(6, new g_1941_L(this, 0.7));
        this.s_956_w.n_1700_B(7, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new J_1907_R(this));
        this.u_2550_I.n_1700_B(2, new n_1700_B(this));
    }

    public static s_1415_m.n_1700_B p_3749_n() {
        return g_4407_j.h_1640_b().n_1700_B(Attributes.J_1907_R, 40.0);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(C_2741_M, 0);
        this.l_4537_E.n_1700_B(k_2293_S, -1);
        this.l_4537_E.n_1700_B(q_2307_F, 0);
    }

    public int P_3676_m() {
        return u_530_F.n_1700_B((int)this.l_4537_E.n_1700_B(q_2307_F), 0, 3);
    }

    public void C_2741_M(int variantIn) {
        this.l_4537_E.J_1907_R(q_2307_F, variantIn);
    }

    @Override
    protected int y_2447_C() {
        return this.V_1176_p() ? 2 + 3 * this.V_537_k() : super.y_2447_C();
    }

    @Override
    public void v_4262_N(N_4263_v passenger) {
        if (this.Y_601_j(passenger)) {
            float f = u_530_F.J_1907_R(this.C_1162_e * ((float)Math.PI / 180));
            float f1 = u_530_F.n_1700_B(this.C_1162_e * ((float)Math.PI / 180));
            float f2 = 0.3f;
            passenger.J_1907_R(this.O_3598_v() + (double)(0.3f * f1), this.X_2960_b() + this.s_1671_u() + passenger.O_2151_c(), this.l_2647_k() - (double)(0.3f * f));
        }
    }

    @Override
    public double s_1671_u() {
        return (double)this.v_165_F() * 0.67;
    }

    @Override
    public boolean g_2268_R() {
        return false;
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return Q_2552_b.n_1700_B(stack);
    }

    @Override
    protected boolean R_4764_Y(a_3913_L player, Z_1993_T stack) {
        SoundEvent soundevent;
        int i = 0;
        int j = 0;
        float f = 0.0f;
        boolean flag = false;
        q_1613_l item = stack.J_1907_R();
        if (item == Items.V_3441_j) {
            i = 10;
            j = 3;
            f = 2.0f;
        } else if (item == a_3742_W.M_4609_z.u_1723_Y()) {
            i = 90;
            j = 6;
            f = 10.0f;
            if (this.o_4117_e() && this.x_() == 0 && this.o_82_k()) {
                flag = true;
                this.P_1922_E(player);
            }
        }
        if (this.g_46_E() < this.L_1733_J() && f > 0.0f) {
            this.n_1700_B(f);
            flag = true;
        }
        if (this.d_() && i > 0) {
            this.O_508_d.n_1700_B(ParticleTypes.t_4043_B, this.G_564_y(1.0), this.M_766_z() + 0.5, this.v_4262_N(1.0), 0.0, 0.0, 0.0);
            if (!this.O_508_d.Y_259_p) {
                this.a_(i);
            }
            flag = true;
        }
        if (j > 0 && (flag || !this.o_4117_e()) && this.ModuleCategory() < this.SoundEventRegistration()) {
            flag = true;
            if (!this.O_508_d.Y_259_p) {
                this.Q_2552_b(j);
            }
        }
        if (flag && !this.y_1700_S() && (soundevent = this.Setting()) != null) {
            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.Setting(), this.r_2478_U(), 1.0f, 1.0f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f);
        }
        return flag;
    }

    @Override
    protected boolean W_3729_Q() {
        return this.Z_2812_M() || this.A_1306_N();
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        int i;
        this.h_3858_e();
        if (spawnDataIn instanceof R_4764_Y) {
            i = ((R_4764_Y)spawnDataIn).n_1700_B;
        } else {
            i = this.RealmsWorldOptions.nextInt(4);
            spawnDataIn = new R_4764_Y(i);
        }
        this.C_2741_M(i);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected SoundEvent KeyBindSetting() {
        return SoundEvents.TPLoot;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.AhHelper;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.UseTracker;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.ToggleSounds;
    }

    @Override
    @Nullable
    protected SoundEvent Setting() {
        return SoundEvents.TrashTalk;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.a_2587_Z, 0.15f, 1.0f);
    }

    @Override
    protected void J_3635_s() {
        this.n_1700_B(SoundEvents.TapeMouse, 1.0f, (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
    }

    @Override
    public void c_1608_O() {
        SoundEvent soundevent = this.KeyBindSetting();
        if (soundevent != null) {
            this.n_1700_B(soundevent, this.d_4500_Q(), this.O_2761_o());
        }
    }

    @Override
    public int V_537_k() {
        return this.N_2266_w();
    }

    @Override
    public boolean N_4006_T() {
        return true;
    }

    @Override
    public boolean k_1608_N() {
        return !this.t_1786_h.s_956_w(1).n_1700_B();
    }

    @Override
    public boolean M_588_G(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        return ItemTags.v_4262_N.n_1700_B(item);
    }

    @Override
    public boolean n_1700_B() {
        return false;
    }

    @Override
    public void n_1700_B(Container invBasic) {
        e_933_M dyecolor = this.Q_4222_k();
        super.n_1700_B(invBasic);
        e_933_M dyecolor1 = this.Q_4222_k();
        if (this.RealmsWorldResetDto > 20 && dyecolor1 != null && dyecolor1 != dyecolor) {
            this.n_1700_B(SoundEvents.D_3097_e, 0.5f, 1.0f);
        }
    }

    @Override
    protected void Module() {
        if (!this.O_508_d.Y_259_p) {
            super.Module();
            this.n_1700_B(g_4407_j.P_4830_p(this.t_1786_h.s_956_w(1)));
        }
    }

    private void n_1700_B(@Nullable e_933_M color) {
        this.l_4537_E.J_1907_R(k_2293_S, color == null ? -1 : color.J_1907_R());
    }

    @Nullable
    private static e_933_M P_4830_p(Z_1993_T p_195403_0_) {
        T_2915_h block = T_2915_h.n_1700_B(p_195403_0_.J_1907_R());
        return block instanceof WoolCarpetBlock ? ((WoolCarpetBlock)block).J_1907_R() : null;
    }

    @Nullable
    public e_933_M Q_4222_k() {
        int i = this.l_4537_E.n_1700_B(k_2293_S);
        return i == -1 ? null : e_933_M.n_1700_B(i);
    }

    @Override
    public int SoundEventRegistration() {
        return 30;
    }

    @Override
    public boolean n_1700_B(Animal otherAnimal) {
        return otherAnimal != this && otherAnimal instanceof g_4407_j && this.MultiBooleanSetting() && ((g_4407_j)otherAnimal).MultiBooleanSetting();
    }

    public g_4407_j J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        g_4407_j llamaentity = this.f_887_Z();
        this.n_1700_B(p_241840_2_, llamaentity);
        g_4407_j llamaentity1 = (g_4407_j)p_241840_2_;
        int i = this.RealmsWorldOptions.nextInt(Math.max(this.N_2266_w(), llamaentity1.N_2266_w())) + 1;
        if (this.RealmsWorldOptions.nextFloat() < 0.03f) {
            ++i;
        }
        llamaentity.k_2293_S(i);
        llamaentity.C_2741_M(this.RealmsWorldOptions.nextBoolean() ? this.P_3676_m() : llamaentity1.P_3676_m());
        return llamaentity;
    }

    protected g_4407_j f_887_Z() {
        return t_5_h.g_221_o.n_1700_B(this.O_508_d);
    }

    private void w_1484_f(r_4811_B target) {
        r_214_x llamaspitentity = new r_214_x(this.O_508_d, this);
        double d0 = target.O_3598_v() - this.O_3598_v();
        double d1 = target.P_1922_E(0.3333333333333333) - llamaspitentity.X_2960_b();
        double d2 = target.l_2647_k() - this.l_2647_k();
        float f = u_530_F.n_1700_B(d0 * d0 + d2 * d2) * 0.2f;
        llamaspitentity.R_4764_Y(d0, d1 + (double)f, d2, 1.5f, 10.0f);
        if (!this.y_1700_S()) {
            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.VoiceChat, this.r_2478_U(), 1.0f, 1.0f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f);
        }
        this.O_508_d.a_(llamaspitentity);
        this.Z_875_P = true;
    }

    private void Z_875_P(boolean didSpitIn) {
        this.Z_875_P = didSpitIn;
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        int i = this.u_1723_Y(distance, damageMultiplier);
        if (i <= 0) {
            return false;
        }
        if (distance >= 6.0f) {
            this.n_1700_B(P_11_z.u_2550_I, (float)i);
            if (this.H_1883_T()) {
                for (N_4263_v entity : this.X_290_I()) {
                    entity.n_1700_B(P_11_z.u_2550_I, (float)i);
                }
            }
        }
        this.q_839_y();
        return true;
    }

    public void R_3213_X() {
        if (this.c_3005_b != null) {
            this.c_3005_b.H_2857_Y = null;
        }
        this.c_3005_b = null;
    }

    public void n_1700_B(g_4407_j caravanHeadIn) {
        this.c_3005_b = caravanHeadIn;
        this.c_3005_b.H_2857_Y = this;
    }

    public boolean H_1475_K() {
        return this.H_2857_Y != null;
    }

    public boolean c_1732_c() {
        return this.c_3005_b != null;
    }

    @Nullable
    public g_4407_j I_2209_R() {
        return this.c_3005_b;
    }

    @Override
    protected double Q_4569_t() {
        return 2.0;
    }

    @Override
    protected void H_1491_c() {
        if (!this.c_1732_c() && this.d_()) {
            super.H_1491_c();
        }
    }

    @Override
    public boolean h_2367_h() {
        return false;
    }

    @Override
    public void J_1907_R(r_4811_B target, float distanceFactor) {
        this.w_1484_f(target);
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.75 * (double)this.X_1313_W(), (double)this.C_415_h() * 0.5);
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }

    static class J_1907_R
    extends g_3408_G {
        public J_1907_R(g_4407_j llama) {
            super(llama, new Class[0]);
        }

        @Override
        public boolean J_1907_R() {
            if (this.P_1922_E instanceof g_4407_j) {
                g_4407_j llamaentity = (g_4407_j)this.P_1922_E;
                if (llamaentity.Z_875_P) {
                    llamaentity.Z_875_P(false);
                    return false;
                }
            }
            return super.J_1907_R();
        }
    }

    static class n_1700_B
    extends NearestAttackableTargetGoal<q_2335_j> {
        public n_1700_B(g_4407_j llama) {
            super(llama, q_2335_j.class, 16, false, true, p_220789_0_ -> !((q_2335_j)p_220789_0_).U_3758_B());
        }

        @Override
        protected double u_2550_I() {
            return super.u_2550_I() * 0.25;
        }
    }

    static class R_4764_Y
    extends AgableMob.n_1700_B {
        public final int n_1700_B;

        private R_4764_Y(int variantIn) {
            super(true);
            this.n_1700_B = variantIn;
        }
    }
}



