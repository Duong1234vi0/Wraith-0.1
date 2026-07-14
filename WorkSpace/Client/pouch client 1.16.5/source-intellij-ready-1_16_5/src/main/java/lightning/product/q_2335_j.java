/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.BegGoal;
import lightning.product.NonTameRandomTargetGoal;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_3622_I;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.E_514_j;
import lightning.product.G_3246_f;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.J_2548_M;
import lightning.product.K_4074_S;
import lightning.product.Animal;
import lightning.product.N_4263_v;
import lightning.product.LeapAtTargetGoal;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.R_1815_U;
import lightning.product.ResetUniversalAngerTargetGoal;
import lightning.product.AvoidEntityGoal;
import lightning.product.SitWhenOrderedToGoal;
import lightning.product.FloatGoal;
import lightning.product.U_2534_D;
import lightning.product.U_2912_j;
import lightning.product.Ghast;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.AbstractSkeleton;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.DyeItem;
import lightning.product.b_3485_j;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.TimeUtil;
import lightning.product.e_933_M;
import lightning.product.f_4739_a;
import lightning.product.g_1941_L;
import lightning.product.g_3408_G;
import lightning.product.g_4407_j;
import lightning.product.h_256_u;
import lightning.product.h_384_L;
import lightning.product.EntityDataSerializers;
import lightning.product.l_311_l_0;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_4149_i;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_1688_C;

public class q_2335_j
extends C_3622_I
implements G_3246_f {
    private static final h_256_u<Boolean> Q_4569_t = C_4114_x.n_1700_B(q_2335_j.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Integer> M_182_A = C_4114_x.n_1700_B(q_2335_j.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> t_1786_h = C_4114_x.n_1700_B(q_2335_j.class, EntityDataSerializers.J_1907_R);
    public static final Predicate<r_4811_B> h_1847_R = p_213440_0_ -> {
        t_5_h<?> entitytype = p_213440_0_.f_4016_n();
        return entitytype == t_5_h.k_3961_g || entitytype == t_5_h.UploadStatus || entitytype == t_5_h.A_4115_X;
    };
    private float Y_601_j;
    private float Y_259_p;
    private boolean Q_2552_b;
    private boolean C_2741_M;
    private float k_2293_S;
    private float q_2307_F;
    private static final J_2548_M Z_875_P = TimeUtil.n_1700_B(20, 39);
    private UUID c_3005_b;

    public q_2335_j(t_5_h<? extends q_2335_j> type, b_4507_u worldIn) {
        super((t_5_h<? extends C_3622_I>)type, worldIn);
        this.Q_2552_b(false);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new FloatGoal(this));
        this.s_956_w.n_1700_B(2, new SitWhenOrderedToGoal(this));
        this.s_956_w.n_1700_B(3, new n_1700_B<g_4407_j>(this, g_4407_j.class, 24.0f, 1.5, 1.5));
        this.s_956_w.n_1700_B(4, new LeapAtTargetGoal(this, 0.4f));
        this.s_956_w.n_1700_B(5, new b_4953_N(this, 1.0, true));
        this.s_956_w.n_1700_B(6, new f_4739_a(this, 1.0, 10.0f, 2.0f, false));
        this.s_956_w.n_1700_B(7, new BreedGoal(this, 1.0));
        this.s_956_w.n_1700_B(8, new g_1941_L(this, 1.0));
        this.s_956_w.n_1700_B(9, new BegGoal(this, 8.0f));
        this.s_956_w.n_1700_B(10, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(10, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new E_514_j(this));
        this.u_2550_I.n_1700_B(2, new l_311_l_0(this));
        this.u_2550_I.n_1700_B(3, new g_3408_G(this, new Class[0]).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(4, new NearestAttackableTargetGoal<a_3913_L>(this, a_3913_L.class, 10, true, false, this::c_));
        this.u_2550_I.n_1700_B(5, new NonTameRandomTargetGoal<Animal>(this, Animal.class, false, h_1847_R));
        this.u_2550_I.n_1700_B(6, new NonTameRandomTargetGoal<t_4149_i>(this, t_4149_i.class, false, t_4149_i.h_1847_R));
        this.u_2550_I.n_1700_B(7, new NearestAttackableTargetGoal<AbstractSkeleton>((Z_530_i)this, AbstractSkeleton.class, false));
        this.u_2550_I.n_1700_B(8, new ResetUniversalAngerTargetGoal<q_2335_j>(this, true));
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.G_564_y, 0.3f).n_1700_B(Attributes.n_1700_B, 8.0).n_1700_B(Attributes.u_1723_Y, 2.0);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(Q_4569_t, false);
        this.l_4537_E.n_1700_B(M_182_A, e_933_M.Q_4569_t.J_1907_R());
        this.l_4537_E.n_1700_B(t_1786_h, 0);
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.SoundType, 0.15f, 1.0f);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("CollarColor", (byte)this.y_2447_C().J_1907_R());
        this.a_(compound);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("CollarColor", 99)) {
            this.n_1700_B(e_933_M.n_1700_B(compound.w_1484_f("CollarColor")));
        }
        this.n_1700_B((e_3591_l)this.O_508_d, compound);
    }

    @Override
    protected SoundEvent z_4693_k() {
        if (this.B_()) {
            return SoundEvents.SmokerBlock;
        }
        if (this.RealmsWorldOptions.nextInt(3) == 0) {
            return this.U_3758_B() && this.g_46_E() < 10.0f ? SoundEvents.SpawnerBlock : SoundEvents.SoulFireBlock;
        }
        return SoundEvents.SlimeBlock;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.SnowyDirtBlock;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.SmithingTableBlock;
    }

    @Override
    protected float d_4500_Q() {
        return 0.4f;
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (!this.O_508_d.Y_259_p && this.Q_2552_b && !this.C_2741_M && !this.w_1484_f() && this.e_1992_r) {
            this.C_2741_M = true;
            this.k_2293_S = 0.0f;
            this.q_2307_F = 0.0f;
            this.O_508_d.n_1700_B((N_4263_v)this, (byte)8);
        }
        if (!this.O_508_d.Y_259_p) {
            this.n_1700_B((e_3591_l)this.O_508_d, true);
        }
    }

    @Override
    public void v_() {
        super.v_();
        if (this.RealmsLongRunningMcoTaskScreen()) {
            this.Y_259_p = this.Y_601_j;
            this.Y_601_j = this.J_3635_s() ? (this.Y_601_j += (1.0f - this.Y_601_j) * 0.4f) : (this.Y_601_j += (0.0f - this.Y_601_j) * 0.4f);
            if (this.j_2266_I()) {
                this.Q_2552_b = true;
                if (this.C_2741_M && !this.O_508_d.Y_259_p) {
                    this.O_508_d.n_1700_B((N_4263_v)this, (byte)56);
                    this.V_537_k();
                }
            } else if ((this.Q_2552_b || this.C_2741_M) && this.C_2741_M) {
                if (this.k_2293_S == 0.0f) {
                    this.n_1700_B(SoundEvents.SoulSandBlock, this.d_4500_Q(), (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
                }
                this.q_2307_F = this.k_2293_S;
                this.k_2293_S += 0.05f;
                if (this.q_2307_F >= 2.0f) {
                    this.Q_2552_b = false;
                    this.C_2741_M = false;
                    this.q_2307_F = 0.0f;
                    this.k_2293_S = 0.0f;
                }
                if (this.k_2293_S > 0.4f) {
                    float f = (float)this.X_2960_b();
                    int i = (int)(u_530_F.n_1700_B((this.k_2293_S - 0.4f) * (float)Math.PI) * 7.0f);
                    e_2866_D vector3d = this.I_4348_c();
                    for (int j = 0; j < i; ++j) {
                        float f1 = (this.RealmsWorldOptions.nextFloat() * 2.0f - 1.0f) * this.C_415_h() * 0.5f;
                        float f2 = (this.RealmsWorldOptions.nextFloat() * 2.0f - 1.0f) * this.C_415_h() * 0.5f;
                        this.O_508_d.n_1700_B(ParticleTypes.g_2268_R, this.O_3598_v() + (double)f1, (double)(f + 0.8f), this.l_2647_k() + (double)f2, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
                    }
                }
            }
        }
    }

    private void V_537_k() {
        this.C_2741_M = false;
        this.k_2293_S = 0.0f;
        this.q_2307_F = 0.0f;
    }

    @Override
    public void R_4764_Y(P_11_z cause) {
        this.Q_2552_b = false;
        this.C_2741_M = false;
        this.q_2307_F = 0.0f;
        this.k_2293_S = 0.0f;
        super.R_4764_Y(cause);
    }

    public boolean h_1640_b() {
        return this.Q_2552_b;
    }

    public float c_3005_b(float partialTicks) {
        return Math.min(0.5f + u_530_F.v_4262_N(partialTicks, this.q_2307_F, this.k_2293_S) / 2.0f * 0.5f, 1.0f);
    }

    public float n_1700_B(float partialTicks, float offset) {
        float f = (u_530_F.v_4262_N(partialTicks, this.q_2307_F, this.k_2293_S) + offset) / 1.8f;
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        return u_530_F.n_1700_B(f * (float)Math.PI) * u_530_F.n_1700_B(f * (float)Math.PI * 11.0f) * 0.15f * (float)Math.PI;
    }

    public float H_2857_Y(float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, this.Y_259_p, this.Y_601_j) * 0.15f * (float)Math.PI;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R * 0.8f;
    }

    @Override
    public int Z_976_R() {
        return this.z_2372_L() ? 20 : super.Z_976_R();
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        N_4263_v entity = source.u_2550_I();
        this.k_2293_S(false);
        if (entity != null && !(entity instanceof a_3913_L) && !(entity instanceof h_384_L)) {
            amount = (amount + 1.0f) / 2.0f;
        }
        return super.n_1700_B(source, amount);
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        boolean flag = entityIn.n_1700_B(P_11_z.R_4764_Y(this), (float)((int)this.J_1907_R(Attributes.u_1723_Y)));
        if (flag) {
            this.n_1700_B((r_4811_B)this, entityIn);
        }
        return flag;
    }

    @Override
    public void Q_2552_b(boolean tamed) {
        super.Q_2552_b(tamed);
        if (tamed) {
            this.n_1700_B(Attributes.n_1700_B).n_1700_B(20.0);
            this.t_1786_h(20.0f);
        } else {
            this.n_1700_B(Attributes.n_1700_B).n_1700_B(8.0);
        }
        this.n_1700_B(Attributes.u_1723_Y).n_1700_B(4.0);
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        q_1613_l item = itemstack.J_1907_R();
        if (this.O_508_d.Y_259_p) {
            boolean flag = this.w_1484_f(p_230254_1_) || this.U_3758_B() || item == Items.DamageSourcePredicate && !this.U_3758_B() && !this.B_();
            return flag ? m_3054_I.J_1907_R : m_3054_I.R_4764_Y;
        }
        if (this.U_3758_B()) {
            if (this.u_2550_I(itemstack) && this.g_46_E() < this.L_1733_J()) {
                if (!p_230254_1_.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                }
                this.n_1700_B((float)item.Q_2552_b().n_1700_B());
                return m_3054_I.n_1700_B;
            }
            if (!(item instanceof DyeItem)) {
                m_3054_I actionresulttype = super.J_1907_R(p_230254_1_, p_230254_2_);
                if ((!actionresulttype.n_1700_B() || this.d_()) && this.w_1484_f(p_230254_1_)) {
                    this.k_2293_S(!this.D_3612_q());
                    this.F_3572_x = false;
                    this.t_148_a.h_1847_R();
                    this.R_4764_Y((r_4811_B)null);
                    return m_3054_I.n_1700_B;
                }
                return actionresulttype;
            }
            e_933_M dyecolor = ((DyeItem)item).R_4764_Y();
            if (dyecolor != this.y_2447_C()) {
                this.n_1700_B(dyecolor);
                if (!p_230254_1_.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                }
                return m_3054_I.n_1700_B;
            }
        } else if (item == Items.DamageSourcePredicate && !this.B_()) {
            if (!p_230254_1_.C_415_h.G_564_y) {
                itemstack.v_4262_N(1);
            }
            if (this.RealmsWorldOptions.nextInt(3) == 0) {
                this.u_1723_Y(p_230254_1_);
                this.t_148_a.h_1847_R();
                this.R_4764_Y((r_4811_B)null);
                this.k_2293_S(true);
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)7);
            } else {
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)6);
            }
            return m_3054_I.n_1700_B;
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 8) {
            this.C_2741_M = true;
            this.k_2293_S = 0.0f;
            this.q_2307_F = 0.0f;
        } else if (id == 56) {
            this.V_537_k();
        } else {
            super.n_1700_B(id);
        }
    }

    public float V_1176_p() {
        if (this.B_()) {
            return 1.5393804f;
        }
        return this.U_3758_B() ? (0.55f - (this.L_1733_J() - this.g_46_E()) * 0.02f) * (float)Math.PI : 0.62831855f;
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        return item.Y_259_p() && item.Q_2552_b().R_4764_Y();
    }

    @Override
    public int c_4037_x() {
        return 8;
    }

    @Override
    public int n_1700_B() {
        return this.l_4537_E.n_1700_B(t_1786_h);
    }

    @Override
    public void n_1700_B(int time) {
        this.l_4537_E.J_1907_R(t_1786_h, time);
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B(Z_875_P.n_1700_B(this.RealmsWorldOptions));
    }

    @Override
    @Nullable
    public UUID G_564_y() {
        return this.c_3005_b;
    }

    @Override
    public void n_1700_B(@Nullable UUID target) {
        this.c_3005_b = target;
    }

    public e_933_M y_2447_C() {
        return e_933_M.n_1700_B(this.l_4537_E.n_1700_B(M_182_A));
    }

    public void n_1700_B(e_933_M collarcolor) {
        this.l_4537_E.J_1907_R(M_182_A, collarcolor.J_1907_R());
    }

    public q_2335_j J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        q_2335_j wolfentity = t_5_h.j_2266_I.n_1700_B(p_241840_1_);
        UUID uuid = this.y_3417_N();
        if (uuid != null) {
            wolfentity.J_1907_R(uuid);
            wolfentity.Q_2552_b(true);
        }
        return wolfentity;
    }

    public void w_1457_N(boolean beg) {
        this.l_4537_E.J_1907_R(Q_4569_t, beg);
    }

    @Override
    public boolean n_1700_B(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        }
        if (!this.U_3758_B()) {
            return false;
        }
        if (!(otherAnimal instanceof q_2335_j)) {
            return false;
        }
        q_2335_j wolfentity = (q_2335_j)otherAnimal;
        if (!wolfentity.U_3758_B()) {
            return false;
        }
        if (wolfentity.z_2372_L()) {
            return false;
        }
        return this.P_2295_B() && wolfentity.P_2295_B();
    }

    public boolean J_3635_s() {
        return this.l_4537_E.n_1700_B(Q_4569_t);
    }

    @Override
    public boolean n_1700_B(r_4811_B target, r_4811_B owner) {
        if (!(target instanceof b_3485_j) && !(target instanceof Ghast)) {
            if (target instanceof q_2335_j) {
                q_2335_j wolfentity = (q_2335_j)target;
                return !wolfentity.U_3758_B() || wolfentity.A_1306_N() != owner;
            }
            if (target instanceof a_3913_L && owner instanceof a_3913_L && !((a_3913_L)owner).G_564_y((a_3913_L)target)) {
                return false;
            }
            if (target instanceof U_2534_D && ((U_2534_D)target).o_4117_e()) {
                return false;
            }
            return !(target instanceof C_3622_I) || !((C_3622_I)target).U_3758_B();
        }
        return false;
    }

    @Override
    public boolean G_564_y(a_3913_L player) {
        return !this.B_() && super.G_564_y(player);
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.6f * this.X_1313_W(), this.C_415_h() * 0.4f);
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }

    class n_1700_B<T extends r_4811_B>
    extends AvoidEntityGoal<T> {
        private final q_2335_j s_956_w;

        public n_1700_B(q_2335_j wolfIn, Class<T> entityClassToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
            super(wolfIn, entityClassToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
            this.s_956_w = wolfIn;
        }

        @Override
        public boolean n_1700_B() {
            if (super.n_1700_B() && this.J_1907_R instanceof g_4407_j) {
                return !this.s_956_w.U_3758_B() && this.n_1700_B((g_4407_j)this.J_1907_R);
            }
            return false;
        }

        private boolean n_1700_B(g_4407_j llamaIn) {
            return llamaIn.N_2266_w() >= q_2335_j.this.RealmsWorldOptions.nextInt(5);
        }

        @Override
        public void R_4764_Y() {
            q_2335_j.this.R_4764_Y((r_4811_B)null);
            super.R_4764_Y();
        }

        @Override
        public void P_1922_E() {
            q_2335_j.this.R_4764_Y((r_4811_B)null);
            super.P_1922_E();
        }
    }
}


