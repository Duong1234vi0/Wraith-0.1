/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_4388_s;
import lightning.product.FluidTags;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.D_2364_U;
import lightning.product.D_38_f;
import lightning.product.F_4023_g;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_408_V;
import lightning.product.K_4074_S;
import lightning.product.L_2225_p;
import lightning.product.M_3841_V;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.ZombieAttackGoal;
import lightning.product.R_1815_U;
import lightning.product.R_2450_T;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.GoalUtils;
import lightning.product.X_4861_v;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_3485_j;
import lightning.product.BreakDoorGoal;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.g_1941_L;
import lightning.product.MobType;
import lightning.product.g_3408_G;
import lightning.product.g_4621_i;
import lightning.product.h_256_u;
import lightning.product.i_2099_H;
import lightning.product.ZombifiedPiglin;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.l_4118_l;
import lightning.product.l_4140_i;
import lightning.product.PathfinderMob;
import lightning.product.RemoveBlockGoal;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_4149_i;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.u_743_i;
import lightning.product.LookAtPlayerGoal;

public class F_4355_q
extends Monster {
    private static final UUID n_1700_B = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final U_1880_G J_1907_R = new U_1880_G(n_1700_B, "Baby speed boost", 0.5, U_1880_G.n_1700_B.J_1907_R);
    private static final h_256_u<Boolean> R_4764_Y = C_4114_x.n_1700_B(F_4355_q.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Integer> h_1847_R = C_4114_x.n_1700_B(F_4355_q.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Boolean> Q_4569_t = C_4114_x.n_1700_B(F_4355_q.class, EntityDataSerializers.t_148_a);
    private static final Predicate<R_2450_T> M_182_A = p_213697_0_ -> p_213697_0_ == R_2450_T.G_564_y;
    private final BreakDoorGoal t_1786_h = new BreakDoorGoal(this, M_182_A);
    private boolean multiplayerClientSuggestionProvider;
    private int w_1457_N;
    private int Y_601_j;

    public F_4355_q(t_5_h<? extends F_4355_q> type, b_4507_u worldIn) {
        super((t_5_h<? extends Monster>)type, worldIn);
    }

    public F_4355_q(b_4507_u worldIn) {
        this((t_5_h<? extends F_4355_q>)t_5_h.R_3077_Z, worldIn);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(4, new n_1700_B((PathfinderMob)this, 1.0, 3));
        this.s_956_w.n_1700_B(8, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
        this.u_1723_Y();
    }

    protected void u_1723_Y() {
        this.s_956_w.n_1700_B(2, new ZombieAttackGoal(this, 1.0, false));
        this.s_956_w.n_1700_B(6, new M_3841_V(this, 1.0, true, 4, this::U_1697_c));
        this.s_956_w.n_1700_B(7, new g_1941_L(this, 1.0));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, new Class[0]).n_1700_B(ZombifiedPiglin.class));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<g_4621_i>((Z_530_i)this, g_4621_i.class, false));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<D_2364_U>((Z_530_i)this, D_2364_U.class, true));
        this.u_2550_I.n_1700_B(5, new NearestAttackableTargetGoal<t_4149_i>(this, t_4149_i.class, 10, true, false, t_4149_i.h_1847_R));
    }

    public static s_1415_m.n_1700_B f_2787_O() {
        return Monster.o_4117_e().n_1700_B(Attributes.J_1907_R, 35.0).n_1700_B(Attributes.G_564_y, 0.23f).n_1700_B(Attributes.u_1723_Y, 3.0).n_1700_B(Attributes.t_148_a, 2.0).n_1700_B(Attributes.M_588_G);
    }

    @Override
    protected void a_() {
        super.a_();
        this.D_60_a().n_1700_B(R_4764_Y, false);
        this.D_60_a().n_1700_B(h_1847_R, 0);
        this.D_60_a().n_1700_B(Q_4569_t, false);
    }

    public boolean P_2295_B() {
        return this.D_60_a().n_1700_B(Q_4569_t);
    }

    public boolean U_1697_c() {
        return this.multiplayerClientSuggestionProvider;
    }

    public void Y_601_j(boolean enabled) {
        if (this.y_4642_Y() && GoalUtils.n_1700_B(this)) {
            if (this.multiplayerClientSuggestionProvider != enabled) {
                this.multiplayerClientSuggestionProvider = enabled;
                ((i_2099_H)this.e_4240_b()).n_1700_B(enabled);
                if (enabled) {
                    this.s_956_w.n_1700_B(1, this.t_1786_h);
                } else {
                    this.s_956_w.n_1700_B(this.t_1786_h);
                }
            }
        } else if (this.multiplayerClientSuggestionProvider) {
            this.s_956_w.n_1700_B(this.t_1786_h);
            this.multiplayerClientSuggestionProvider = false;
        }
    }

    protected boolean y_4642_Y() {
        return true;
    }

    @Override
    public boolean d_() {
        return this.D_60_a().n_1700_B(R_4764_Y);
    }

    @Override
    protected int R_4764_Y(a_3913_L player) {
        if (this.d_()) {
            this.P_1922_E = (int)((float)this.P_1922_E * 2.5f);
        }
        return super.R_4764_Y(player);
    }

    @Override
    public void n_1700_B(boolean childZombie) {
        this.D_60_a().J_1907_R(R_4764_Y, childZombie);
        if (this.O_508_d != null && !this.O_508_d.Y_259_p) {
            A_4388_s modifiableattributeinstance = this.n_1700_B(Attributes.G_564_y);
            modifiableattributeinstance.G_564_y(J_1907_R);
            if (childZombie) {
                modifiableattributeinstance.J_1907_R(J_1907_R);
            }
        }
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (R_4764_Y.equals(key)) {
            this.g_();
        }
        super.n_1700_B(key);
    }

    protected boolean J_3635_s() {
        return true;
    }

    @Override
    public void v_() {
        if (!this.O_508_d.Y_259_p && this.RealmsLongRunningMcoTaskScreen() && !this.n_473_l()) {
            if (this.P_2295_B()) {
                --this.Y_601_j;
                if (this.Y_601_j < 0) {
                    this.h_973_D();
                }
            } else if (this.J_3635_s()) {
                if (((N_4263_v)this).n_1700_B(FluidTags.J_1907_R)) {
                    ++this.w_1457_N;
                    if (this.w_1457_N >= 600) {
                        this.n_1700_B(300);
                    }
                } else {
                    this.w_1457_N = -1;
                }
            }
        }
        super.v_();
    }

    @Override
    public void Y_1740_V() {
        if (this.RealmsLongRunningMcoTaskScreen()) {
            boolean flag;
            boolean bl = flag = this.z_() && this.S_2828_i();
            if (flag) {
                Z_1993_T itemstack = this.J_1907_R(e_1174_E.u_1723_Y);
                if (!itemstack.n_1700_B()) {
                    if (itemstack.P_1922_E()) {
                        itemstack.J_1907_R(itemstack.v_4262_N() + this.RealmsWorldOptions.nextInt(2));
                        if (itemstack.v_4262_N() >= itemstack.w_1484_f()) {
                            this.R_4764_Y(e_1174_E.u_1723_Y);
                            this.n_1700_B(e_1174_E.u_1723_Y, Z_1993_T.J_1907_R);
                        }
                    }
                    flag = false;
                }
                if (flag) {
                    this.P_1922_E(8);
                }
            }
        }
        super.Y_1740_V();
    }

    private void n_1700_B(int p_204704_1_) {
        this.Y_601_j = p_204704_1_;
        this.D_60_a().J_1907_R(Q_4569_t, true);
    }

    protected void h_973_D() {
        this.J_1907_R(t_5_h.t_1786_h);
        if (!this.y_1700_S()) {
            this.O_508_d.n_1700_B((a_3913_L)null, 1040, this.b_2312_j(), 0);
        }
    }

    protected void J_1907_R(t_5_h<? extends F_4355_q> p_234341_1_) {
        F_4355_q zombieentity = this.n_1700_B(p_234341_1_, true);
        if (zombieentity != null) {
            zombieentity.c_3005_b(zombieentity.O_508_d.J_1907_R(zombieentity.b_2312_j()).R_4764_Y());
            zombieentity.Y_601_j(zombieentity.y_4642_Y() && this.U_1697_c());
        }
    }

    protected boolean z_() {
        return true;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (!super.n_1700_B(source, amount)) {
            return false;
        }
        if (!(this.O_508_d instanceof e_3591_l)) {
            return false;
        }
        e_3591_l serverworld = (e_3591_l)this.O_508_d;
        r_4811_B livingentity = this.t_148_a();
        if (livingentity == null && source.u_2550_I() instanceof r_4811_B) {
            livingentity = (r_4811_B)source.u_2550_I();
        }
        if (livingentity != null && this.O_508_d.x_607_J() == R_2450_T.G_564_y && (double)this.RealmsWorldOptions.nextFloat() < this.J_1907_R(Attributes.M_588_G) && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.G_564_y)) {
            int i = u_530_F.R_4764_Y(this.O_3598_v());
            int j = u_530_F.R_4764_Y(this.X_2960_b());
            int k = u_530_F.R_4764_Y(this.l_2647_k());
            F_4355_q zombieentity = new F_4355_q(this.O_508_d);
            for (int l = 0; l < 50; ++l) {
                int i1 = i + u_530_F.n_1700_B(this.RealmsWorldOptions, 7, 40) * u_530_F.n_1700_B(this.RealmsWorldOptions, -1, 1);
                int j1 = j + u_530_F.n_1700_B(this.RealmsWorldOptions, 7, 40) * u_530_F.n_1700_B(this.RealmsWorldOptions, -1, 1);
                int k1 = k + u_530_F.n_1700_B(this.RealmsWorldOptions, 7, 40) * u_530_F.n_1700_B(this.RealmsWorldOptions, -1, 1);
                c_1514_x blockpos = new c_1514_x(i1, j1, k1);
                t_5_h<?> entitytype = zombieentity.f_4016_n();
                F_4023_g.R_4764_Y entityspawnplacementregistry$placementtype = F_4023_g.n_1700_B(entitytype);
                if (!u_743_i.n_1700_B(entityspawnplacementregistry$placementtype, this.O_508_d, blockpos, entitytype) || !F_4023_g.n_1700_B(entitytype, serverworld, a_3160_D.s_956_w, blockpos, this.O_508_d.w_1457_N)) continue;
                zombieentity.J_1907_R(i1, j1, k1);
                if (this.O_508_d.n_1700_B((double)i1, (double)j1, (double)k1, 7.0) || !this.O_508_d.P_1922_E(zombieentity) || !this.O_508_d.u_1723_Y(zombieentity) || this.O_508_d.G_564_y(zombieentity.i_601_W())) continue;
                zombieentity.R_4764_Y(livingentity);
                zombieentity.n_1700_B(serverworld, this.O_508_d.J_1907_R(zombieentity.b_2312_j()), a_3160_D.s_956_w, (V_3157_k)null, null);
                serverworld.n_1700_B((N_4263_v)zombieentity);
                this.n_1700_B(Attributes.M_588_G).R_4764_Y(new U_1880_G("Zombie reinforcement caller charge", -0.05f, U_1880_G.n_1700_B.n_1700_B));
                zombieentity.n_1700_B(Attributes.M_588_G).R_4764_Y(new U_1880_G("Zombie reinforcement callee charge", -0.05f, U_1880_G.n_1700_B.n_1700_B));
                break;
            }
        }
        return true;
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        boolean flag = super.q_2307_F(entityIn);
        if (flag) {
            float f = this.O_508_d.J_1907_R(this.b_2312_j()).J_1907_R();
            if (this.A_2714_y().n_1700_B() && this.RealmsPersistence() && this.RealmsWorldOptions.nextFloat() < f * 0.3f) {
                entityIn.P_1922_E(2 * (int)f);
            }
        }
        return flag;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.L_2467_I;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.j_942_t;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.W_3538_l;
    }

    protected SoundEvent V_1176_p() {
        return SoundEvents.WitherSkullBlock;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(this.V_1176_p(), 0.15f, 1.0f);
    }

    @Override
    public MobType F_2860_q() {
        return MobType.J_1907_R;
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        super.n_1700_B(difficulty);
        float f = this.RealmsWorldOptions.nextFloat();
        float f2 = this.O_508_d.x_607_J() == R_2450_T.G_564_y ? 0.05f : 0.01f;
        if (f < f2) {
            int i = this.RealmsWorldOptions.nextInt(3);
            if (i == 0) {
                this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.w_2152_d));
            } else {
                this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.Z_1243_X));
            }
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("IsBaby", this.d_());
        compound.n_1700_B("CanBreakDoors", this.U_1697_c());
        compound.J_1907_R("InWaterTime", this.RowButton() ? this.w_1457_N : -1);
        compound.J_1907_R("DrownedConversionTime", this.P_2295_B() ? this.Y_601_j : -1);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.n_1700_B(compound.t_1786_h("IsBaby"));
        this.Y_601_j(compound.t_1786_h("CanBreakDoors"));
        this.w_1457_N = compound.w_1484_f("InWaterTime");
        if (compound.R_4764_Y("DrownedConversionTime", 99) && compound.w_1484_f("DrownedConversionTime") > -1) {
            this.n_1700_B(compound.w_1484_f("DrownedConversionTime"));
        }
    }

    @Override
    public void n_1700_B(e_3591_l p_241847_1_, r_4811_B p_241847_2_) {
        super.n_1700_B(p_241847_1_, p_241847_2_);
        if ((p_241847_1_.x_607_J() == R_2450_T.R_4764_Y || p_241847_1_.x_607_J() == R_2450_T.G_564_y) && p_241847_2_ instanceof L_2225_p) {
            if (p_241847_1_.x_607_J() != R_2450_T.G_564_y && this.RealmsWorldOptions.nextBoolean()) {
                return;
            }
            L_2225_p villagerentity = (L_2225_p)p_241847_2_;
            l_4140_i zombievillagerentity = villagerentity.n_1700_B(t_5_h.M_2677_i, false);
            zombievillagerentity.n_1700_B(p_241847_1_, p_241847_1_.J_1907_R(zombievillagerentity.b_2312_j()), a_3160_D.t_148_a, new J_1907_R(false, true), null);
            zombievillagerentity.n_1700_B(villagerentity.c_2086_l());
            zombievillagerentity.n_1700_B((Tag)villagerentity.D_3612_q().n_1700_B(l_4118_l.n_1700_B).getValue());
            zombievillagerentity.v_4262_N(villagerentity.J_1907_R().n_1700_B());
            zombievillagerentity.n_1700_B(villagerentity.G_564_y());
            if (!this.y_1700_S()) {
                p_241847_1_.n_1700_B((a_3913_L)null, 1026, this.b_2312_j(), 0);
            }
        }
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return this.d_() ? 0.93f : 1.74f;
    }

    @Override
    public boolean w_1484_f(Z_1993_T stack) {
        return stack.J_1907_R() == Items.s_4405_m && this.d_() && this.y_2772_m() ? false : super.w_1484_f(stack);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        spawnDataIn = super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        float f = difficultyIn.R_4764_Y();
        this.R_4764_Y(this.RealmsWorldOptions.nextFloat() < 0.55f * f);
        if (spawnDataIn == null) {
            spawnDataIn = new J_1907_R(F_4355_q.n_1700_B(worldIn.e_4240_b()), true);
        }
        if (spawnDataIn instanceof J_1907_R) {
            J_1907_R zombieentity$groupdata = (J_1907_R)spawnDataIn;
            if (zombieentity$groupdata.n_1700_B) {
                this.n_1700_B(true);
                if (zombieentity$groupdata.J_1907_R) {
                    if ((double)worldIn.e_4240_b().nextFloat() < 0.05) {
                        List<N_4263_v> list = worldIn.n_1700_B(X_4861_v.class, this.i_601_W().grow(5.0, 3.0, 5.0), I_408_V.R_4764_Y);
                        if (!list.isEmpty()) {
                            X_4861_v chickenentity = (X_4861_v)list.get(0);
                            chickenentity.w_1457_N(true);
                            this.s_956_w(chickenentity);
                        }
                    } else if ((double)worldIn.e_4240_b().nextFloat() < 0.05) {
                        X_4861_v chickenentity1 = t_5_h.s_956_w.n_1700_B(this.O_508_d);
                        chickenentity1.J_1907_R(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, 0.0f);
                        chickenentity1.n_1700_B(worldIn, difficultyIn, a_3160_D.v_4262_N, (V_3157_k)null, null);
                        chickenentity1.w_1457_N(true);
                        this.s_956_w(chickenentity1);
                        worldIn.a_(chickenentity1);
                    }
                }
            }
            this.Y_601_j(this.y_4642_Y() && this.RealmsWorldOptions.nextFloat() < f * 0.1f);
            this.n_1700_B(difficultyIn);
            this.J_1907_R(difficultyIn);
        }
        if (this.J_1907_R(e_1174_E.u_1723_Y).n_1700_B()) {
            LocalDate localdate = LocalDate.now();
            int i = localdate.get(ChronoField.DAY_OF_MONTH);
            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && this.RealmsWorldOptions.nextFloat() < 0.25f) {
                this.n_1700_B(e_1174_E.u_1723_Y, new Z_1993_T(this.RealmsWorldOptions.nextFloat() < 0.1f ? a_3742_W.l_2647_k : a_3742_W.X_2048_Y));
                this.P_4830_p[e_1174_E.u_1723_Y.J_1907_R()] = 0.0f;
            }
        }
        this.c_3005_b(f);
        return spawnDataIn;
    }

    public static boolean n_1700_B(Random p_241399_0_) {
        return p_241399_0_.nextFloat() < 0.05f;
    }

    protected void c_3005_b(float difficulty) {
        this.V_537_k();
        this.n_1700_B(Attributes.R_4764_Y).R_4764_Y(new U_1880_G("Random spawn bonus", this.RealmsWorldOptions.nextDouble() * (double)0.05f, U_1880_G.n_1700_B.n_1700_B));
        double d0 = this.RealmsWorldOptions.nextDouble() * 1.5 * (double)difficulty;
        if (d0 > 1.0) {
            this.n_1700_B(Attributes.J_1907_R).R_4764_Y(new U_1880_G("Random zombie-spawn bonus", d0, U_1880_G.n_1700_B.R_4764_Y));
        }
        if (this.RealmsWorldOptions.nextFloat() < difficulty * 0.05f) {
            this.n_1700_B(Attributes.M_588_G).R_4764_Y(new U_1880_G("Leader zombie bonus", this.RealmsWorldOptions.nextDouble() * 0.25 + 0.5, U_1880_G.n_1700_B.n_1700_B));
            this.n_1700_B(Attributes.n_1700_B).R_4764_Y(new U_1880_G("Leader zombie bonus", this.RealmsWorldOptions.nextDouble() * 3.0 + 1.0, U_1880_G.n_1700_B.R_4764_Y));
            this.Y_601_j(this.y_4642_Y());
        }
    }

    protected void V_537_k() {
        this.n_1700_B(Attributes.M_588_G).n_1700_B(this.RealmsWorldOptions.nextDouble() * (double)0.1f);
    }

    @Override
    public double O_2151_c() {
        return this.d_() ? 0.0 : -0.45;
    }

    @Override
    protected void n_1700_B(P_11_z source, int looting, boolean recentlyHitIn) {
        Z_1993_T itemstack;
        b_3485_j creeperentity;
        super.n_1700_B(source, looting, recentlyHitIn);
        N_4263_v entity = source.u_2550_I();
        if (entity instanceof b_3485_j && (creeperentity = (b_3485_j)entity).J_3635_s() && !(itemstack = this.y_2447_C()).n_1700_B()) {
            creeperentity.o_82_k();
            this.a_(itemstack);
        }
    }

    protected Z_1993_T y_2447_C() {
        return new Z_1993_T(Items.EndGatewayBlock);
    }

    class n_1700_B
    extends RemoveBlockGoal {
        n_1700_B(PathfinderMob creatureIn, double speed, int yMax) {
            super(a_3742_W.d_560_A, creatureIn, speed, yMax);
        }

        @Override
        public void n_1700_B(LevelAccessor worldIn, c_1514_x pos) {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.Y_1820_h, D_38_f.u_1723_Y, 0.5f, 0.9f + F_4355_q.this.RealmsWorldOptions.nextFloat() * 0.2f);
        }

        @Override
        public void n_1700_B(b_4507_u worldIn, c_1514_x pos) {
            worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.FrostedIceBlock, D_38_f.P_1922_E, 0.7f, 0.9f + worldIn.w_1457_N.nextFloat() * 0.2f);
        }

        @Override
        public double w_1484_f() {
            return 1.14;
        }
    }

    public static class J_1907_R
    implements V_3157_k {
        public final boolean n_1700_B;
        public final boolean J_1907_R;

        public J_1907_R(boolean p_i231567_1_, boolean p_i231567_2_) {
            this.n_1700_B = p_i231567_1_;
            this.J_1907_R = p_i231567_2_;
        }
    }
}


