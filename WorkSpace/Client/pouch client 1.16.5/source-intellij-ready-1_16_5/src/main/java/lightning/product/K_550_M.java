/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.LootContextParams;
import lightning.product.NonTameRandomTargetGoal;
import lightning.product.C_3622_I;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.StructureFeature;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.OcelotAttackGoal;
import lightning.product.J_2868_p;
import lightning.product.K_4074_S;
import lightning.product.M_2433_H;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.LeapAtTargetGoal;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.R_1815_U;
import lightning.product.TemptGoal;
import lightning.product.AvoidEntityGoal;
import lightning.product.SitWhenOrderedToGoal;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.DyeItem;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.CatLieOnBedGoal;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_3591_l;
import lightning.product.e_933_M;
import lightning.product.f_1402_I;
import lightning.product.f_4739_a;
import lightning.product.g_1941_L;
import lightning.product.g_2336_b;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.j_3341_s;
import lightning.product.m_3054_I;
import lightning.product.PathfinderMob;
import lightning.product.n_1494_c;
import lightning.product.BlockTags;
import lightning.product.o_4810_o;
import lightning.product.Goal;
import lightning.product.p_4985_U;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.CatSitOnBlockGoal;
import lightning.product.t_4149_i;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_1688_C;

public class K_550_M
extends C_3622_I {
    private static final b_3278_X Q_4569_t = b_3278_X.n_1700_B(Items.ServerAdvancementManager, Items.C_3304_p);
    private static final h_256_u<Integer> M_182_A = C_4114_x.n_1700_B(K_550_M.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Boolean> t_1786_h = C_4114_x.n_1700_B(K_550_M.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> Y_601_j = C_4114_x.n_1700_B(K_550_M.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Integer> Y_259_p = C_4114_x.n_1700_B(K_550_M.class, EntityDataSerializers.J_1907_R);
    public static final Map<Integer, g_2336_b> h_1847_R = j_3341_s.n_1700_B(Maps.newHashMap(), p_213410_0_ -> {
        p_213410_0_.put(0, new g_2336_b("textures/entity/cat/tabby.png"));
        p_213410_0_.put(1, new g_2336_b("textures/entity/cat/black.png"));
        p_213410_0_.put(2, new g_2336_b("textures/entity/cat/red.png"));
        p_213410_0_.put(3, new g_2336_b("textures/entity/cat/siamese.png"));
        p_213410_0_.put(4, new g_2336_b("textures/entity/cat/british_shorthair.png"));
        p_213410_0_.put(5, new g_2336_b("textures/entity/cat/calico.png"));
        p_213410_0_.put(6, new g_2336_b("textures/entity/cat/persian.png"));
        p_213410_0_.put(7, new g_2336_b("textures/entity/cat/ragdoll.png"));
        p_213410_0_.put(8, new g_2336_b("textures/entity/cat/white.png"));
        p_213410_0_.put(9, new g_2336_b("textures/entity/cat/jellie.png"));
        p_213410_0_.put(10, new g_2336_b("textures/entity/cat/all_black.png"));
    });
    private n_1700_B<a_3913_L> Q_2552_b;
    private TemptGoal C_2741_M;
    private float k_2293_S;
    private float q_2307_F;
    private float Z_875_P;
    private float c_3005_b;
    private float H_2857_Y;
    private float A_4115_X;

    public K_550_M(t_5_h<? extends K_550_M> type, b_4507_u worldIn) {
        super((t_5_h<? extends C_3622_I>)type, worldIn);
    }

    public g_2336_b y_4642_Y() {
        return h_1847_R.getOrDefault(this.h_1640_b(), h_1847_R.get(0));
    }

    @Override
    protected void M_182_A() {
        this.C_2741_M = new R_4764_Y(this, 0.6, Q_4569_t, true);
        this.s_956_w.n_1700_B(1, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new SitWhenOrderedToGoal(this));
        this.s_956_w.n_1700_B(2, new J_1907_R(this));
        this.s_956_w.n_1700_B(3, this.C_2741_M);
        this.s_956_w.n_1700_B(5, new CatLieOnBedGoal(this, 1.1, 8));
        this.s_956_w.n_1700_B(6, new f_4739_a(this, 1.0, 10.0f, 5.0f, false));
        this.s_956_w.n_1700_B(7, new CatSitOnBlockGoal(this, 0.8));
        this.s_956_w.n_1700_B(8, new LeapAtTargetGoal(this, 0.3f));
        this.s_956_w.n_1700_B(9, new OcelotAttackGoal(this));
        this.s_956_w.n_1700_B(10, new BreedGoal(this, 0.8));
        this.s_956_w.n_1700_B(11, new g_1941_L((PathfinderMob)this, 0.8, 1.0000001E-5f));
        this.s_956_w.n_1700_B(12, new LookAtPlayerGoal(this, a_3913_L.class, 10.0f));
        this.u_2550_I.n_1700_B(1, new NonTameRandomTargetGoal<M_2433_H>(this, M_2433_H.class, false, null));
        this.u_2550_I.n_1700_B(1, new NonTameRandomTargetGoal<t_4149_i>(this, t_4149_i.class, false, t_4149_i.h_1847_R));
    }

    public int h_1640_b() {
        return this.l_4537_E.n_1700_B(M_182_A);
    }

    public void Y_601_j(int type) {
        if (type < 0 || type >= 11) {
            type = this.RealmsWorldOptions.nextInt(10);
        }
        this.l_4537_E.J_1907_R(M_182_A, type);
    }

    public void w_1457_N(boolean p_213419_1_) {
        this.l_4537_E.J_1907_R(t_1786_h, p_213419_1_);
    }

    public boolean V_1176_p() {
        return this.l_4537_E.n_1700_B(t_1786_h);
    }

    public void Y_601_j(boolean p_213415_1_) {
        this.l_4537_E.J_1907_R(Y_601_j, p_213415_1_);
    }

    public boolean y_2447_C() {
        return this.l_4537_E.n_1700_B(Y_601_j);
    }

    public e_933_M J_3635_s() {
        return e_933_M.n_1700_B(this.l_4537_E.n_1700_B(Y_259_p));
    }

    public void n_1700_B(e_933_M color) {
        this.l_4537_E.J_1907_R(Y_259_p, color.J_1907_R());
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(M_182_A, 1);
        this.l_4537_E.n_1700_B(t_1786_h, false);
        this.l_4537_E.n_1700_B(Y_601_j, false);
        this.l_4537_E.n_1700_B(Y_259_p, e_933_M.Q_4569_t.J_1907_R());
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("CatType", this.h_1640_b());
        compound.n_1700_B("CollarColor", (byte)this.J_3635_s().J_1907_R());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.Y_601_j(compound.w_1484_f("CatType"));
        if (compound.R_4764_Y("CollarColor", 99)) {
            this.n_1700_B(e_933_M.n_1700_B(compound.w_1484_f("CollarColor")));
        }
    }

    @Override
    public void X_933_l() {
        if (this.A_4115_X().J_1907_R()) {
            double d0 = this.A_4115_X().R_4764_Y();
            if (d0 == 0.6) {
                this.J_1907_R(I_1170_F.u_1723_Y);
                this.b_(false);
            } else if (d0 == 1.33) {
                this.J_1907_R(I_1170_F.n_1700_B);
                this.b_(true);
            } else {
                this.J_1907_R(I_1170_F.n_1700_B);
                this.b_(false);
            }
        } else {
            this.J_1907_R(I_1170_F.n_1700_B);
            this.b_(false);
        }
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        if (this.U_3758_B()) {
            if (this.P_2295_B()) {
                return SoundEvents.J_739_q;
            }
            return this.RealmsWorldOptions.nextInt(4) == 0 ? SoundEvents.C_1162_e : SoundEvents.RealmsResetNormalWorldScreen;
        }
        return SoundEvents.C_3538_G;
    }

    @Override
    public int v_4276_D() {
        return 120;
    }

    public void V_537_k() {
        this.n_1700_B(SoundEvents.RealmsSettingsScreen, this.d_4500_Q(), this.O_2761_o());
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.F_4247_a;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.A_3959_N;
    }

    public static s_1415_m.n_1700_B c_2086_l() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 10.0).n_1700_B(Attributes.G_564_y, 0.3f).n_1700_B(Attributes.u_1723_Y, 3.0);
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void n_1700_B(a_3913_L player, Z_1993_T stack) {
        if (this.u_2550_I(stack)) {
            this.n_1700_B(SoundEvents.G_424_k, 1.0f, 1.0f);
        }
        super.n_1700_B(player, stack);
    }

    private float R_2822_N() {
        return (float)this.J_1907_R(Attributes.u_1723_Y);
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        return entityIn.n_1700_B(P_11_z.R_4764_Y(this), this.R_2822_N());
    }

    @Override
    public void v_() {
        super.v_();
        if (this.C_2741_M != null && this.C_2741_M.w_1484_f() && !this.U_3758_B() && this.RealmsWorldResetDto % 100 == 0) {
            this.n_1700_B(SoundEvents.f_1043_S, 1.0f, 1.0f);
        }
        this.ModuleCategory();
    }

    private void ModuleCategory() {
        if ((this.V_1176_p() || this.y_2447_C()) && this.RealmsWorldResetDto % 5 == 0) {
            this.n_1700_B(SoundEvents.J_739_q, 0.6f + 0.4f * (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()), 1.0f);
        }
        this.p_1458_L();
        this.Module();
    }

    private void p_1458_L() {
        this.q_2307_F = this.k_2293_S;
        this.c_3005_b = this.Z_875_P;
        if (this.V_1176_p()) {
            this.k_2293_S = Math.min(1.0f, this.k_2293_S + 0.15f);
            this.Z_875_P = Math.min(1.0f, this.Z_875_P + 0.08f);
        } else {
            this.k_2293_S = Math.max(0.0f, this.k_2293_S - 0.22f);
            this.Z_875_P = Math.max(0.0f, this.Z_875_P - 0.13f);
        }
    }

    private void Module() {
        this.A_4115_X = this.H_2857_Y;
        this.H_2857_Y = this.y_2447_C() ? Math.min(1.0f, this.H_2857_Y + 0.1f) : Math.max(0.0f, this.H_2857_Y - 0.13f);
    }

    public float c_3005_b(float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, this.q_2307_F, this.k_2293_S);
    }

    public float H_2857_Y(float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, this.c_3005_b, this.Z_875_P);
    }

    public float A_4115_X(float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, this.A_4115_X, this.H_2857_Y);
    }

    public K_550_M J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        K_550_M catentity = t_5_h.w_1484_f.n_1700_B(p_241840_1_);
        if (p_241840_2_ instanceof K_550_M) {
            if (this.RealmsWorldOptions.nextBoolean()) {
                catentity.Y_601_j(this.h_1640_b());
            } else {
                catentity.Y_601_j(((K_550_M)p_241840_2_).h_1640_b());
            }
            if (this.U_3758_B()) {
                catentity.J_1907_R(this.y_3417_N());
                catentity.Q_2552_b(true);
                if (this.RealmsWorldOptions.nextBoolean()) {
                    catentity.n_1700_B(this.J_3635_s());
                } else {
                    catentity.n_1700_B(((K_550_M)p_241840_2_).J_3635_s());
                }
            }
        }
        return catentity;
    }

    @Override
    public boolean n_1700_B(Animal otherAnimal) {
        if (!this.U_3758_B()) {
            return false;
        }
        if (!(otherAnimal instanceof K_550_M)) {
            return false;
        }
        K_550_M catentity = (K_550_M)otherAnimal;
        return catentity.U_3758_B() && super.n_1700_B(otherAnimal);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        spawnDataIn = super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (worldIn.Y_1740_V() > 0.9f) {
            this.Y_601_j(this.RealmsWorldOptions.nextInt(11));
        } else {
            this.Y_601_j(this.RealmsWorldOptions.nextInt(10));
        }
        e_3591_l world = worldIn.J_1907_R();
        if (world instanceof e_3591_l && world.R_4764_Y().n_1700_B(this.b_2312_j(), true, StructureFeature.s_956_w).P_1922_E()) {
            this.Y_601_j(10);
            this.T_3594_S();
        }
        return spawnDataIn;
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        m_3054_I actionresulttype1;
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        q_1613_l item = itemstack.J_1907_R();
        if (this.O_508_d.Y_259_p) {
            if (this.U_3758_B() && this.w_1484_f(p_230254_1_)) {
                return m_3054_I.n_1700_B;
            }
            return !this.u_2550_I(itemstack) || !(this.g_46_E() < this.L_1733_J()) && this.U_3758_B() ? m_3054_I.R_4764_Y : m_3054_I.n_1700_B;
        }
        if (this.U_3758_B()) {
            if (this.w_1484_f(p_230254_1_)) {
                if (!(item instanceof DyeItem)) {
                    if (item.Y_259_p() && this.u_2550_I(itemstack) && this.g_46_E() < this.L_1733_J()) {
                        this.n_1700_B(p_230254_1_, itemstack);
                        this.n_1700_B((float)item.Q_2552_b().n_1700_B());
                        return m_3054_I.J_1907_R;
                    }
                    m_3054_I actionresulttype = super.J_1907_R(p_230254_1_, p_230254_2_);
                    if (!actionresulttype.n_1700_B() || this.d_()) {
                        this.k_2293_S(!this.D_3612_q());
                    }
                    return actionresulttype;
                }
                e_933_M dyecolor = ((DyeItem)item).R_4764_Y();
                if (dyecolor != this.J_3635_s()) {
                    this.n_1700_B(dyecolor);
                    if (!p_230254_1_.C_415_h.G_564_y) {
                        itemstack.v_4262_N(1);
                    }
                    this.T_3594_S();
                    return m_3054_I.J_1907_R;
                }
            }
        } else if (this.u_2550_I(itemstack)) {
            this.n_1700_B(p_230254_1_, itemstack);
            if (this.RealmsWorldOptions.nextInt(3) == 0) {
                this.u_1723_Y(p_230254_1_);
                this.k_2293_S(true);
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)7);
            } else {
                this.O_508_d.n_1700_B((N_4263_v)this, (byte)6);
            }
            this.T_3594_S();
            return m_3054_I.J_1907_R;
        }
        if ((actionresulttype1 = super.J_1907_R(p_230254_1_, p_230254_2_)).n_1700_B()) {
            this.T_3594_S();
        }
        return actionresulttype1;
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return Q_4569_t.n_1700_B(stack);
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R * 0.5f;
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return !this.U_3758_B() && this.RealmsWorldResetDto > 2400;
    }

    @Override
    protected void o_4117_e() {
        if (this.Q_2552_b == null) {
            this.Q_2552_b = new n_1700_B<a_3913_L>(this, a_3913_L.class, 16.0f, 0.8, 1.33);
        }
        this.s_956_w.n_1700_B(this.Q_2552_b);
        if (!this.U_3758_B()) {
            this.s_956_w.n_1700_B(4, this.Q_2552_b);
        }
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }

    static class R_4764_Y
    extends TemptGoal {
        @Nullable
        private a_3913_L R_4764_Y;
        private final K_550_M G_564_y;

        public R_4764_Y(K_550_M catIn, double speedIn, b_3278_X temptItemsIn, boolean scaredByPlayerMovementIn) {
            super((PathfinderMob)catIn, speedIn, temptItemsIn, scaredByPlayerMovementIn);
            this.G_564_y = catIn;
        }

        @Override
        public void P_1922_E() {
            super.P_1922_E();
            if (this.R_4764_Y == null && this.n_1700_B.M_3508_C().nextInt(600) == 0) {
                this.R_4764_Y = this.J_1907_R;
            } else if (this.n_1700_B.M_3508_C().nextInt(500) == 0) {
                this.R_4764_Y = null;
            }
        }

        @Override
        protected boolean v_4262_N() {
            return this.R_4764_Y != null && this.R_4764_Y.equals(this.J_1907_R) ? false : super.v_4262_N();
        }

        @Override
        public boolean n_1700_B() {
            return super.n_1700_B() && !this.G_564_y.U_3758_B();
        }
    }

    static class J_1907_R
    extends Goal {
        private final K_550_M n_1700_B;
        private a_3913_L J_1907_R;
        private c_1514_x R_4764_Y;
        private int G_564_y;

        public J_1907_R(K_550_M catIn) {
            this.n_1700_B = catIn;
        }

        @Override
        public boolean n_1700_B() {
            if (!this.n_1700_B.U_3758_B()) {
                return false;
            }
            if (this.n_1700_B.D_3612_q()) {
                return false;
            }
            r_4811_B livingentity = this.n_1700_B.A_1306_N();
            if (livingentity instanceof a_3913_L) {
                this.J_1907_R = (a_3913_L)livingentity;
                if (!livingentity.z_2372_L()) {
                    return false;
                }
                if (this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R) > 100.0) {
                    return false;
                }
                c_1514_x blockpos = this.J_1907_R.b_2312_j();
                K_4074_S blockstate = this.n_1700_B.O_508_d.getBlockState(blockpos);
                if (blockstate.J_1907_R().n_1700_B(BlockTags.d_2461_k)) {
                    this.R_4764_Y = blockstate.G_564_y(J_2868_p.w_612_n).map(p_234186_1_ -> blockpos.offset(p_234186_1_.u_1723_Y())).orElseGet(() -> new c_1514_x(blockpos));
                    return !this.v_4262_N();
                }
            }
            return false;
        }

        private boolean v_4262_N() {
            for (K_550_M catentity : this.n_1700_B.O_508_d.n_1700_B(K_550_M.class, new I_4817_s(this.R_4764_Y).grow(2.0))) {
                if (catentity == this.n_1700_B || !catentity.V_1176_p() && !catentity.y_2447_C()) continue;
                return true;
            }
            return false;
        }

        @Override
        public boolean J_1907_R() {
            return this.n_1700_B.U_3758_B() && !this.n_1700_B.D_3612_q() && this.J_1907_R != null && this.J_1907_R.z_2372_L() && this.R_4764_Y != null && !this.v_4262_N();
        }

        @Override
        public void R_4764_Y() {
            if (this.R_4764_Y != null) {
                this.n_1700_B.C_2741_M(false);
                this.n_1700_B.e_4240_b().n_1700_B((double)this.R_4764_Y.getX(), (double)this.R_4764_Y.getY(), (double)this.R_4764_Y.getZ(), 1.1f);
            }
        }

        @Override
        public void G_564_y() {
            this.n_1700_B.w_1457_N(false);
            float f = this.n_1700_B.O_508_d.G_564_y(1.0f);
            if (this.J_1907_R.V_1176_p() >= 100 && (double)f > 0.77 && (double)f < 0.8 && (double)this.n_1700_B.O_508_d.e_4240_b().nextFloat() < 0.7) {
                this.w_1484_f();
            }
            this.G_564_y = 0;
            this.n_1700_B.Y_601_j(false);
            this.n_1700_B.e_4240_b().h_1847_R();
        }

        private void w_1484_f() {
            Random random = this.n_1700_B.M_3508_C();
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            blockpos$mutable.n_1700_B(this.n_1700_B.b_2312_j());
            this.n_1700_B.n_1700_B((double)(blockpos$mutable.getX() + random.nextInt(11) - 5), (double)(blockpos$mutable.getY() + random.nextInt(5) - 2), (double)(blockpos$mutable.getZ() + random.nextInt(11) - 5), false);
            blockpos$mutable.n_1700_B(this.n_1700_B.b_2312_j());
            p_4985_U loottable = this.n_1700_B.O_508_d.T_2506_i().F_2624_D().n_1700_B(o_4810_o.p_178_J);
            q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B((e_3591_l)this.n_1700_B.O_508_d).n_1700_B(LootContextParams.u_1723_Y, this.n_1700_B.s_4990_V()).n_1700_B(LootContextParams.n_1700_B, this.n_1700_B).n_1700_B(random);
            for (Z_1993_T itemstack : loottable.n_1700_B(lootcontext$builder.n_1700_B(f_1402_I.v_4262_N))) {
                this.n_1700_B.O_508_d.a_(new n_1494_c(this.n_1700_B.O_508_d, (double)blockpos$mutable.getX() - (double)u_530_F.n_1700_B(this.n_1700_B.C_1162_e * ((float)Math.PI / 180)), blockpos$mutable.getY(), (double)blockpos$mutable.getZ() + (double)u_530_F.J_1907_R(this.n_1700_B.C_1162_e * ((float)Math.PI / 180)), itemstack));
            }
        }

        @Override
        public void P_1922_E() {
            if (this.J_1907_R != null && this.R_4764_Y != null) {
                this.n_1700_B.C_2741_M(false);
                this.n_1700_B.e_4240_b().n_1700_B((double)this.R_4764_Y.getX(), (double)this.R_4764_Y.getY(), (double)this.R_4764_Y.getZ(), 1.1f);
                if (this.n_1700_B.G_564_y((N_4263_v)this.J_1907_R) < 2.5) {
                    ++this.G_564_y;
                    if (this.G_564_y > 16) {
                        this.n_1700_B.w_1457_N(true);
                        this.n_1700_B.Y_601_j(false);
                    } else {
                        this.n_1700_B.n_1700_B((N_4263_v)this.J_1907_R, 45.0f, 45.0f);
                        this.n_1700_B.Y_601_j(true);
                    }
                } else {
                    this.n_1700_B.w_1457_N(false);
                }
            }
        }
    }

    static class n_1700_B<T extends r_4811_B>
    extends AvoidEntityGoal<T> {
        private final K_550_M t_148_a;

        public n_1700_B(K_550_M catIn, Class<T> entityClassToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
            super(catIn, entityClassToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn, I_408_V.P_1922_E::test);
            this.t_148_a = catIn;
        }

        @Override
        public boolean n_1700_B() {
            return !this.t_148_a.U_3758_B() && super.n_1700_B();
        }

        @Override
        public boolean J_1907_R() {
            return !this.t_148_a.U_3758_B() && super.J_1907_R();
        }
    }
}



