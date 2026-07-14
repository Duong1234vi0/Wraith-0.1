/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.FluidTags;
import lightning.product.B_4088_l;
import lightning.product.C_3622_I;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.E_2941_n;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_1869_h;
import lightning.product.LookControl;
import lightning.product.I_408_V;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.M_2433_H;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.N_3869_i;
import lightning.product.N_4263_v;
import lightning.product.LeapAtTargetGoal;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.MoveToBlockGoal;
import lightning.product.Q_3816_H;
import lightning.product.R_1815_U;
import lightning.product.MoveControl;
import lightning.product.AvoidEntityGoal;
import lightning.product.S_199_U;
import lightning.product.T_1316_M;
import lightning.product.FloatGoal;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.biomeBiomes;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.V_3157_k;
import lightning.product.AbstractFish;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.StrollThroughVillageGoal;
import lightning.product.X_4861_v;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.SweetBerryBushBlock;
import lightning.product.g_1941_L;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.AbstractSchoolingFish;
import lightning.product.k_594_Q;
import lightning.product.n_1494_c;
import lightning.product.n_3832_I;
import lightning.product.n_4637_L;
import lightning.product.Goal;
import lightning.product.q_1613_l;
import lightning.product.q_2335_j;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.ParticleTypes;
import lightning.product.t_4149_i;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.v_2621_q;

public class g_1253_u
extends Animal {
    private static final h_256_u<Integer> h_1847_R = C_4114_x.n_1700_B(g_1253_u.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Byte> Q_4569_t = C_4114_x.n_1700_B(g_1253_u.class, EntityDataSerializers.n_1700_B);
    private static final h_256_u<Optional<UUID>> M_182_A = C_4114_x.n_1700_B(g_1253_u.class, EntityDataSerializers.Q_4569_t);
    private static final h_256_u<Optional<UUID>> t_1786_h = C_4114_x.n_1700_B(g_1253_u.class, EntityDataSerializers.Q_4569_t);
    private static final Predicate<n_1494_c> multiplayerClientSuggestionProvider = p_213489_0_ -> !p_213489_0_.Q_4569_t() && p_213489_0_.RealmsLongRunningMcoTaskScreen();
    private static final Predicate<N_4263_v> w_1457_N = p_213470_0_ -> {
        if (!(p_213470_0_ instanceof r_4811_B)) {
            return false;
        }
        r_4811_B livingentity = (r_4811_B)p_213470_0_;
        return livingentity.Q_2753_H() != null && livingentity.Y_2080_q() < livingentity.RealmsWorldResetDto + 600;
    };
    private static final Predicate<N_4263_v> Y_601_j = p_213498_0_ -> p_213498_0_ instanceof X_4861_v || p_213498_0_ instanceof M_2433_H;
    private static final Predicate<N_4263_v> Y_259_p = p_213463_0_ -> !p_213463_0_.U_1341_G() && I_408_V.P_1922_E.test((N_4263_v)p_213463_0_);
    private Goal Q_2552_b;
    private Goal C_2741_M;
    private Goal k_2293_S;
    private float q_2307_F;
    private float Z_875_P;
    private float c_3005_b;
    private float H_2857_Y;
    private int A_4115_X;

    public g_1253_u(t_5_h<? extends g_1253_u> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
        this.u_1723_Y = new u_2550_I();
        this.v_4262_N = new P_4830_p();
        this.n_1700_B(I_1869_h.M_182_A, 0.0f);
        this.n_1700_B(I_1869_h.t_1786_h, 0.0f);
        this.R_4764_Y(true);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(M_182_A, Optional.empty());
        this.l_4537_E.n_1700_B(t_1786_h, Optional.empty());
        this.l_4537_E.n_1700_B(h_1847_R, 0);
        this.l_4537_E.n_1700_B(Q_4569_t, (byte)0);
    }

    @Override
    protected void M_182_A() {
        this.Q_2552_b = new NearestAttackableTargetGoal<Animal>(this, Animal.class, 10, false, false, p_213487_0_ -> p_213487_0_ instanceof X_4861_v || p_213487_0_ instanceof M_2433_H);
        this.C_2741_M = new NearestAttackableTargetGoal<t_4149_i>(this, t_4149_i.class, 10, false, false, t_4149_i.h_1847_R);
        this.k_2293_S = new NearestAttackableTargetGoal<AbstractFish>(this, AbstractFish.class, 20, false, false, p_213456_0_ -> p_213456_0_ instanceof AbstractSchoolingFish);
        this.s_956_w.n_1700_B(0, new Y_601_j());
        this.s_956_w.n_1700_B(1, new s_956_w());
        this.s_956_w.n_1700_B(2, new h_1847_R(2.2));
        this.s_956_w.n_1700_B(3, new M_588_G(this, 1.0));
        this.s_956_w.n_1700_B(4, new AvoidEntityGoal<a_3913_L>(this, a_3913_L.class, 16.0f, 1.6, 1.4, p_213497_1_ -> Y_259_p.test((N_4263_v)p_213497_1_) && !this.R_4764_Y(p_213497_1_.w_2705_t()) && !this.y_3417_N()));
        this.s_956_w.n_1700_B(4, new AvoidEntityGoal<q_2335_j>(this, q_2335_j.class, 8.0f, 1.6, 1.4, p_213469_1_ -> !((q_2335_j)p_213469_1_).U_3758_B() && !this.y_3417_N()));
        this.s_956_w.n_1700_B(4, new AvoidEntityGoal<Q_3816_H>(this, Q_3816_H.class, 8.0f, 1.6, 1.4, p_213493_1_ -> !this.y_3417_N()));
        this.s_956_w.n_1700_B(5, new w_1484_f());
        this.s_956_w.n_1700_B(6, new Q_4569_t());
        this.s_956_w.n_1700_B(6, new u_1723_Y(1.25));
        this.s_956_w.n_1700_B(7, new R_4764_Y((double)1.2f, true));
        this.s_956_w.n_1700_B(7, new multiplayerClientSuggestionProvider());
        this.s_956_w.n_1700_B(8, new v_4262_N(this, this, 1.25));
        this.s_956_w.n_1700_B(9, new w_1457_N(32, 200));
        this.s_956_w.n_1700_B(10, new G_564_y((double)1.2f, 12, 2));
        this.s_956_w.n_1700_B(10, new LeapAtTargetGoal(this, 0.4f));
        this.s_956_w.n_1700_B(11, new g_1941_L(this, 1.0));
        this.s_956_w.n_1700_B(11, new P_1922_E());
        this.s_956_w.n_1700_B(12, new Q_2552_b(this, a_3913_L.class, 24.0f));
        this.s_956_w.n_1700_B(13, new t_1786_h());
        this.u_2550_I.n_1700_B(3, new M_182_A(r_4811_B.class, false, false, p_234193_1_ -> w_1457_N.test((N_4263_v)p_234193_1_) && !this.R_4764_Y(p_234193_1_.w_2705_t())));
    }

    @Override
    public SoundEvent G_564_y(Z_1993_T itemStackIn) {
        return SoundEvents.h_3859_C;
    }

    @Override
    public void Y_1740_V() {
        if (!this.O_508_d.Y_259_p && this.RealmsLongRunningMcoTaskScreen() && this.w_1457_N()) {
            r_4811_B livingentity;
            ++this.A_4115_X;
            Z_1993_T itemstack = this.J_1907_R(e_1174_E.n_1700_B);
            if (this.M_588_G(itemstack)) {
                if (this.A_4115_X > 600) {
                    Z_1993_T itemstack1 = itemstack.n_1700_B(this.O_508_d, this);
                    if (!itemstack1.n_1700_B()) {
                        this.n_1700_B(e_1174_E.n_1700_B, itemstack1);
                    }
                    this.A_4115_X = 0;
                } else if (this.A_4115_X > 560 && this.RealmsWorldOptions.nextFloat() < 0.1f) {
                    this.n_1700_B(this.G_564_y(itemstack), 1.0f, 1.0f);
                    this.O_508_d.n_1700_B((N_4263_v)this, (byte)45);
                }
            }
            if ((livingentity = this.t_148_a()) == null || !livingentity.RealmsLongRunningMcoTaskScreen()) {
                this.Y_259_p(false);
                this.Q_2552_b(false);
            }
        }
        if (this.z_2372_L() || this.W_3729_Q()) {
            this.F_3572_x = false;
            this.L_1362_X = 0.0f;
            this.L_4248_u = 0.0f;
        }
        super.Y_1740_V();
        if (this.y_3417_N() && this.RealmsWorldOptions.nextFloat() < 0.05f) {
            this.n_1700_B(SoundEvents.g_1031_K, 1.0f, 1.0f);
        }
    }

    @Override
    protected boolean W_3729_Q() {
        return this.Z_2812_M();
    }

    private boolean M_588_G(Z_1993_T itemStackIn) {
        return itemStackIn.J_1907_R().Y_259_p() && this.t_148_a() == null && this.e_1992_r && !this.z_2372_L();
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        if (this.RealmsWorldOptions.nextFloat() < 0.2f) {
            float f = this.RealmsWorldOptions.nextFloat();
            Z_1993_T itemstack = f < 0.05f ? new Z_1993_T(Items.Y_2905_A) : (f < 0.2f ? new Z_1993_T(Items.s_4405_m) : (f < 0.4f ? (this.RealmsWorldOptions.nextBoolean() ? new Z_1993_T(Items.GlazedTerracottaBlock) : new Z_1993_T(Items.GrassBlock)) : (f < 0.6f ? new Z_1993_T(Items.V_3441_j) : (f < 0.8f ? new Z_1993_T(Items.y_254_d) : new Z_1993_T(Items.H_274_C)))));
            this.n_1700_B(e_1174_E.n_1700_B, itemstack);
        }
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 45) {
            Z_1993_T itemstack = this.J_1907_R(e_1174_E.n_1700_B);
            if (!itemstack.n_1700_B()) {
                for (int i = 0; i < 8; ++i) {
                    e_2866_D vector3d = new e_2866_D(((double)this.RealmsWorldOptions.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0).n_1700_B(-this.f_4016_n * ((float)Math.PI / 180)).J_1907_R(-this.p_178_J * ((float)Math.PI / 180));
                    this.O_508_d.n_1700_B(new N_3869_i(ParticleTypes.d_2427_y, itemstack), this.O_3598_v() + this.RealmsSettingsScreen().J_1907_R / 2.0, this.X_2960_b(), this.l_2647_k() + this.RealmsSettingsScreen().G_564_y / 2.0, vector3d.J_1907_R, vector3d.R_4764_Y + 0.05, vector3d.G_564_y);
                }
            }
        } else {
            super.n_1700_B(id);
        }
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.G_564_y, 0.3f).n_1700_B(Attributes.n_1700_B, 10.0).n_1700_B(Attributes.J_1907_R, 32.0).n_1700_B(Attributes.u_1723_Y, 2.0);
    }

    public g_1253_u J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        g_1253_u foxentity = t_5_h.A_4115_X.n_1700_B(p_241840_1_);
        foxentity.n_1700_B(this.RealmsWorldOptions.nextBoolean() ? this.h_1640_b() : ((g_1253_u)p_241840_2_).h_1640_b());
        return foxentity;
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        Optional<f_2392_k<k_594_Q>> optional = worldIn.n_1700_B(this.b_2312_j());
        Y_259_p foxentity$type = lightning.product.g_1253_u$Y_259_p.n_1700_B(optional);
        boolean flag = false;
        if (spawnDataIn instanceof t_148_a) {
            foxentity$type = ((t_148_a)spawnDataIn).n_1700_B;
            if (((t_148_a)spawnDataIn).n_1700_B() >= 2) {
                flag = true;
            }
        } else {
            spawnDataIn = new t_148_a(foxentity$type);
        }
        this.n_1700_B(foxentity$type);
        if (flag) {
            this.b_(-24000);
        }
        if (worldIn instanceof e_3591_l) {
            this.o_4117_e();
        }
        this.n_1700_B(difficultyIn);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private void o_4117_e() {
        if (this.h_1640_b() == lightning.product.g_1253_u$Y_259_p.n_1700_B) {
            this.u_2550_I.n_1700_B(4, this.Q_2552_b);
            this.u_2550_I.n_1700_B(4, this.C_2741_M);
            this.u_2550_I.n_1700_B(6, this.k_2293_S);
        } else {
            this.u_2550_I.n_1700_B(4, this.k_2293_S);
            this.u_2550_I.n_1700_B(6, this.Q_2552_b);
            this.u_2550_I.n_1700_B(6, this.C_2741_M);
        }
    }

    @Override
    protected void n_1700_B(a_3913_L player, Z_1993_T stack) {
        if (this.u_2550_I(stack)) {
            this.n_1700_B(this.G_564_y(stack), 1.0f, 1.0f);
        }
        super.n_1700_B(player, stack);
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return this.d_() ? sizeIn.J_1907_R * 0.85f : 0.4f;
    }

    public Y_259_p h_1640_b() {
        return lightning.product.g_1253_u$Y_259_p.n_1700_B(this.l_4537_E.n_1700_B(h_1847_R));
    }

    private void n_1700_B(Y_259_p typeIn) {
        this.l_4537_E.J_1907_R(h_1847_R, typeIn.J_1907_R());
    }

    private List<UUID> U_3758_B() {
        ArrayList list = Lists.newArrayList();
        list.add(this.l_4537_E.n_1700_B(M_182_A).orElse(null));
        list.add(this.l_4537_E.n_1700_B(t_1786_h).orElse(null));
        return list;
    }

    private void J_1907_R(@Nullable UUID uuidIn) {
        if (this.l_4537_E.n_1700_B(M_182_A).isPresent()) {
            this.l_4537_E.J_1907_R(t_1786_h, Optional.ofNullable(uuidIn));
        } else {
            this.l_4537_E.J_1907_R(M_182_A, Optional.ofNullable(uuidIn));
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        List<UUID> list = this.U_3758_B();
        q_2896_o listnbt = new q_2896_o();
        for (UUID uuid : list) {
            if (uuid == null) continue;
            listnbt.add(n_3832_I.n_1700_B(uuid));
        }
        compound.n_1700_B("Trusted", listnbt);
        compound.n_1700_B("Sleeping", this.z_2372_L());
        compound.n_1700_B("Type", this.h_1640_b().n_1700_B());
        compound.n_1700_B("Sitting", this.V_1176_p());
        compound.n_1700_B("Crouching", this.Z_875_P());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        q_2896_o listnbt = compound.G_564_y("Trusted", 11);
        for (int i = 0; i < listnbt.size(); ++i) {
            this.J_1907_R(n_3832_I.n_1700_B(listnbt.s_956_w(i)));
        }
        this.q_2307_F(compound.t_1786_h("Sleeping"));
        this.n_1700_B(lightning.product.g_1253_u$Y_259_p.n_1700_B(compound.M_588_G("Type")));
        this.w_1457_N(compound.t_1786_h("Sitting"));
        this.Y_259_p(compound.t_1786_h("Crouching"));
        if (this.O_508_d instanceof e_3591_l) {
            this.o_4117_e();
        }
    }

    public boolean V_1176_p() {
        return this.Y_601_j(1);
    }

    public void w_1457_N(boolean p_213466_1_) {
        this.G_564_y(1, p_213466_1_);
    }

    public boolean y_2447_C() {
        return this.Y_601_j(64);
    }

    private void C_2741_M(boolean p_213492_1_) {
        this.G_564_y(64, p_213492_1_);
    }

    private boolean y_3417_N() {
        return this.Y_601_j(128);
    }

    private void k_2293_S(boolean p_213482_1_) {
        this.G_564_y(128, p_213482_1_);
    }

    @Override
    public boolean z_2372_L() {
        return this.Y_601_j(32);
    }

    private void q_2307_F(boolean p_213485_1_) {
        this.G_564_y(32, p_213485_1_);
    }

    private void G_564_y(int p_213505_1_, boolean p_213505_2_) {
        if (p_213505_2_) {
            this.l_4537_E.J_1907_R(Q_4569_t, (byte)(this.l_4537_E.n_1700_B(Q_4569_t) | p_213505_1_));
        } else {
            this.l_4537_E.J_1907_R(Q_4569_t, (byte)(this.l_4537_E.n_1700_B(Q_4569_t) & ~p_213505_1_));
        }
    }

    private boolean Y_601_j(int p_213507_1_) {
        return (this.l_4537_E.n_1700_B(Q_4569_t) & p_213507_1_) != 0;
    }

    @Override
    public boolean P_1922_E(Z_1993_T itemstackIn) {
        e_1174_E equipmentslottype = Z_530_i.s_956_w(itemstackIn);
        if (!this.J_1907_R(equipmentslottype).n_1700_B()) {
            return false;
        }
        return equipmentslottype == e_1174_E.n_1700_B && super.P_1922_E(itemstackIn);
    }

    @Override
    public boolean w_1484_f(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        Z_1993_T itemstack = this.J_1907_R(e_1174_E.n_1700_B);
        return itemstack.n_1700_B() || this.A_4115_X > 0 && item.Y_259_p() && !itemstack.J_1907_R().Y_259_p();
    }

    private void P_4830_p(Z_1993_T stackIn) {
        if (!stackIn.n_1700_B() && !this.O_508_d.Y_259_p) {
            n_1494_c itementity = new n_1494_c(this.O_508_d, this.O_3598_v() + this.RealmsSettingsScreen().J_1907_R, this.X_2960_b() + 1.0, this.l_2647_k() + this.RealmsSettingsScreen().G_564_y, stackIn);
            itementity.n_1700_B(40);
            itementity.R_4764_Y(this.w_2705_t());
            this.n_1700_B(SoundEvents.c_776_E, 1.0f, 1.0f);
            this.O_508_d.a_(itementity);
        }
    }

    private void h_1847_R(Z_1993_T stackIn) {
        n_1494_c itementity = new n_1494_c(this.O_508_d, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), stackIn);
        this.O_508_d.a_(itementity);
    }

    @Override
    protected void J_1907_R(n_1494_c itemEntity) {
        Z_1993_T itemstack = itemEntity.P_1922_E();
        if (this.w_1484_f(itemstack)) {
            int i = itemstack.t_4043_B();
            if (i > 1) {
                this.h_1847_R(itemstack.n_1700_B(i - 1));
            }
            this.P_4830_p(this.J_1907_R(e_1174_E.n_1700_B));
            this.n_1700_B(itemEntity);
            this.n_1700_B(e_1174_E.n_1700_B, itemstack.n_1700_B(1));
            this.M_588_G[e_1174_E.n_1700_B.J_1907_R()] = 2.0f;
            this.n_1700_B((N_4263_v)itemEntity, itemstack.t_4043_B());
            itemEntity.Ops();
            this.A_4115_X = 0;
        }
    }

    @Override
    public void v_() {
        super.v_();
        if (this.w_1457_N()) {
            boolean flag = this.RowButton();
            if (flag || this.t_148_a() != null || this.O_508_d.N_2525_X()) {
                this.A_1306_N();
            }
            if (flag || this.z_2372_L()) {
                this.w_1457_N(false);
            }
            if (this.y_2447_C() && this.O_508_d.w_1457_N.nextFloat() < 0.2f) {
                c_1514_x blockpos = this.b_2312_j();
                K_4074_S blockstate = this.O_508_d.getBlockState(blockpos);
                this.O_508_d.R_4764_Y(2001, blockpos, T_2915_h.s_956_w(blockstate));
            }
        }
        this.Z_875_P = this.q_2307_F;
        this.q_2307_F = this.c_2086_l() ? (this.q_2307_F += (1.0f - this.q_2307_F) * 0.4f) : (this.q_2307_F += (0.0f - this.q_2307_F) * 0.4f);
        this.H_2857_Y = this.c_3005_b;
        if (this.Z_875_P()) {
            this.c_3005_b += 0.2f;
            if (this.c_3005_b > 3.0f) {
                this.c_3005_b = 3.0f;
            }
        } else {
            this.c_3005_b = 0.0f;
        }
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return stack.J_1907_R() == Items.D_265_n;
    }

    @Override
    protected void n_1700_B(a_3913_L playerIn, Z_530_i child) {
        ((g_1253_u)child).J_1907_R(playerIn.w_2705_t());
    }

    public boolean J_3635_s() {
        return this.Y_601_j(16);
    }

    public void Y_601_j(boolean p_213461_1_) {
        this.G_564_y(16, p_213461_1_);
    }

    public boolean V_537_k() {
        return this.c_3005_b == 3.0f;
    }

    public void Y_259_p(boolean p_213451_1_) {
        this.G_564_y(4, p_213451_1_);
    }

    @Override
    public boolean Z_875_P() {
        return this.Y_601_j(4);
    }

    public void Q_2552_b(boolean p_213502_1_) {
        this.G_564_y(8, p_213502_1_);
    }

    public boolean c_2086_l() {
        return this.Y_601_j(8);
    }

    public float c_3005_b(float p_213475_1_) {
        return u_530_F.v_4262_N(p_213475_1_, this.Z_875_P, this.q_2307_F) * 0.11f * (float)Math.PI;
    }

    public float H_2857_Y(float p_213503_1_) {
        return u_530_F.v_4262_N(p_213503_1_, this.H_2857_Y, this.c_3005_b);
    }

    @Override
    public void R_4764_Y(@Nullable r_4811_B entitylivingbaseIn) {
        if (this.y_3417_N() && entitylivingbaseIn == null) {
            this.k_2293_S(false);
        }
        super.R_4764_Y(entitylivingbaseIn);
    }

    @Override
    protected int u_1723_Y(float distance, float damageMultiplier) {
        return u_530_F.u_1723_Y((distance - 5.0f) * damageMultiplier);
    }

    private void A_1306_N() {
        this.q_2307_F(false);
    }

    private void D_3612_q() {
        this.Q_2552_b(false);
        this.Y_259_p(false);
        this.w_1457_N(false);
        this.q_2307_F(false);
        this.k_2293_S(false);
        this.C_2741_M(false);
    }

    private boolean R_2822_N() {
        return !this.z_2372_L() && !this.V_1176_p() && !this.y_2447_C();
    }

    @Override
    public void G_624_v() {
        SoundEvent soundevent = this.z_4693_k();
        if (soundevent == SoundEvents.r_4790_y) {
            this.n_1700_B(soundevent, 2.0f, this.O_2761_o());
        } else {
            super.G_624_v();
        }
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        List<N_4263_v> list;
        if (this.z_2372_L()) {
            return SoundEvents.x_2635_q;
        }
        if (!this.O_508_d.q_4610_l() && this.RealmsWorldOptions.nextFloat() < 0.1f && (list = this.O_508_d.n_1700_B(a_3913_L.class, this.i_601_W().grow(16.0, 16.0, 16.0), I_408_V.v_4262_N)).isEmpty()) {
            return SoundEvents.r_4790_y;
        }
        return SoundEvents.g_134_G;
    }

    @Override
    @Nullable
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.F_1446_q;
    }

    @Override
    @Nullable
    protected SoundEvent u_796_y() {
        return SoundEvents.k_578_l;
    }

    private boolean R_4764_Y(UUID p_213468_1_) {
        return this.U_3758_B().contains(p_213468_1_);
    }

    @Override
    protected void G_564_y(P_11_z damageSourceIn) {
        Z_1993_T itemstack = this.J_1907_R(e_1174_E.n_1700_B);
        if (!itemstack.n_1700_B()) {
            this.a_(itemstack);
            this.n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
        }
        super.G_564_y(damageSourceIn);
    }

    public static boolean n_1700_B(g_1253_u p_213481_0_, r_4811_B p_213481_1_) {
        double d0 = p_213481_1_.l_2647_k() - p_213481_0_.l_2647_k();
        double d1 = p_213481_1_.O_3598_v() - p_213481_0_.O_3598_v();
        double d2 = d0 / d1;
        int i = 6;
        for (int j = 0; j < 6; ++j) {
            double d3 = d2 == 0.0 ? 0.0 : d0 * (double)((float)j / 6.0f);
            double d4 = d2 == 0.0 ? d1 * (double)((float)j / 6.0f) : d3 / d2;
            for (int k = 1; k < 4; ++k) {
                if (p_213481_0_.O_508_d.getBlockState(new c_1514_x(p_213481_0_.O_3598_v() + d4, p_213481_0_.X_2960_b() + (double)k, p_213481_0_.l_2647_k() + d3)).R_4764_Y().P_1922_E()) continue;
                return false;
            }
        }
        return true;
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.55f * this.X_1313_W(), this.C_415_h() * 0.4f);
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }

    public class u_2550_I
    extends LookControl {
        public u_2550_I() {
            super(g_1253_u.this);
        }

        @Override
        public void n_1700_B() {
            if (!g_1253_u.this.z_2372_L()) {
                super.n_1700_B();
            }
        }

        @Override
        protected boolean J_1907_R() {
            return !g_1253_u.this.J_3635_s() && !g_1253_u.this.Z_875_P() && !g_1253_u.this.c_2086_l() & !g_1253_u.this.y_2447_C();
        }
    }

    class P_4830_p
    extends MoveControl {
        public P_4830_p() {
            super(g_1253_u.this);
        }

        @Override
        public void n_1700_B() {
            if (g_1253_u.this.R_2822_N()) {
                super.n_1700_B();
            }
        }
    }

    class Y_601_j
    extends FloatGoal {
        public Y_601_j() {
            super(g_1253_u.this);
        }

        @Override
        public void R_4764_Y() {
            super.R_4764_Y();
            g_1253_u.this.D_3612_q();
        }

        @Override
        public boolean n_1700_B() {
            return g_1253_u.this.RowButton() && g_1253_u.this.J_1907_R(FluidTags.J_1907_R) > 0.25 || g_1253_u.this.W_3464_O();
        }
    }

    class s_956_w
    extends Goal {
        int n_1700_B;

        public s_956_w() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.J_1907_R, Goal.n_1700_B.R_4764_Y, Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            return g_1253_u.this.y_2447_C();
        }

        @Override
        public boolean J_1907_R() {
            return this.n_1700_B() && this.n_1700_B > 0;
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B = 40;
        }

        @Override
        public void G_564_y() {
            g_1253_u.this.C_2741_M(false);
        }

        @Override
        public void P_1922_E() {
            --this.n_1700_B;
        }
    }

    class h_1847_R
    extends PanicGoal {
        public h_1847_R(double p_i50729_2_) {
            super(g_1253_u.this, p_i50729_2_);
        }

        @Override
        public boolean n_1700_B() {
            return !g_1253_u.this.y_3417_N() && super.n_1700_B();
        }
    }

    class M_588_G
    extends BreedGoal {
        public M_588_G(g_1253_u this$0, double p_i50738_2_) {
            super(this$0, p_i50738_2_);
        }

        @Override
        public void R_4764_Y() {
            ((g_1253_u)this.n_1700_B).D_3612_q();
            ((g_1253_u)this.R_4764_Y).D_3612_q();
            super.R_4764_Y();
        }

        @Override
        protected void v_4262_N() {
            e_3591_l serverworld = (e_3591_l)this.J_1907_R;
            g_1253_u foxentity = (g_1253_u)this.n_1700_B.n_1700_B(serverworld, (AgableMob)this.R_4764_Y);
            if (foxentity != null) {
                B_4088_l serverplayerentity = this.n_1700_B.f_2787_O();
                B_4088_l serverplayerentity1 = this.R_4764_Y.f_2787_O();
                B_4088_l serverplayerentity2 = serverplayerentity;
                if (serverplayerentity != null) {
                    foxentity.J_1907_R(serverplayerentity.w_2705_t());
                } else {
                    serverplayerentity2 = serverplayerentity1;
                }
                if (serverplayerentity1 != null && serverplayerentity != serverplayerentity1) {
                    foxentity.J_1907_R(serverplayerentity1.w_2705_t());
                }
                if (serverplayerentity2 != null) {
                    serverplayerentity2.J_1907_R(Stats.q_4610_l);
                    U_3554_Q.Q_4569_t.n_1700_B(serverplayerentity2, this.n_1700_B, this.R_4764_Y, foxentity);
                }
                this.n_1700_B.b_(6000);
                this.R_4764_Y.b_(6000);
                this.n_1700_B.U_1697_c();
                this.R_4764_Y.U_1697_c();
                foxentity.b_(-24000);
                foxentity.J_1907_R(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k(), 0.0f, 0.0f);
                serverworld.n_1700_B((N_4263_v)foxentity);
                this.J_1907_R.n_1700_B((N_4263_v)this.n_1700_B, (byte)18);
                if (this.J_1907_R.H_1990_U().J_1907_R(A_2352_Z.P_1922_E)) {
                    this.J_1907_R.a_(new n_4637_L(this.J_1907_R, this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k(), this.n_1700_B.M_3508_C().nextInt(7) + 1));
                }
            }
        }
    }

    class w_1484_f
    extends Goal {
        public w_1484_f() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            if (g_1253_u.this.z_2372_L()) {
                return false;
            }
            r_4811_B livingentity = g_1253_u.this.t_148_a();
            return livingentity != null && livingentity.RealmsLongRunningMcoTaskScreen() && Y_601_j.test(livingentity) && g_1253_u.this.G_564_y((N_4263_v)livingentity) > 36.0 && !g_1253_u.this.Z_875_P() && !g_1253_u.this.c_2086_l() && !g_1253_u.this.F_3572_x;
        }

        @Override
        public void R_4764_Y() {
            g_1253_u.this.w_1457_N(false);
            g_1253_u.this.C_2741_M(false);
        }

        @Override
        public void G_564_y() {
            r_4811_B livingentity = g_1253_u.this.t_148_a();
            if (livingentity != null && g_1253_u.n_1700_B(g_1253_u.this, livingentity)) {
                g_1253_u.this.Q_2552_b(true);
                g_1253_u.this.Y_259_p(true);
                g_1253_u.this.e_4240_b().h_1847_R();
                g_1253_u.this.c_3005_b().n_1700_B(livingentity, (float)g_1253_u.this.H_1990_U(), (float)g_1253_u.this.Z_976_R());
            } else {
                g_1253_u.this.Q_2552_b(false);
                g_1253_u.this.Y_259_p(false);
            }
        }

        @Override
        public void P_1922_E() {
            r_4811_B livingentity = g_1253_u.this.t_148_a();
            g_1253_u.this.c_3005_b().n_1700_B(livingentity, (float)g_1253_u.this.H_1990_U(), (float)g_1253_u.this.Z_976_R());
            if (g_1253_u.this.G_564_y((N_4263_v)livingentity) <= 36.0) {
                g_1253_u.this.Q_2552_b(true);
                g_1253_u.this.Y_259_p(true);
                g_1253_u.this.e_4240_b().h_1847_R();
            } else {
                g_1253_u.this.e_4240_b().n_1700_B((N_4263_v)livingentity, 1.5);
            }
        }
    }

    public class Q_4569_t
    extends E_2941_n {
        @Override
        public boolean n_1700_B() {
            if (!g_1253_u.this.V_537_k()) {
                return false;
            }
            r_4811_B livingentity = g_1253_u.this.t_148_a();
            if (livingentity != null && livingentity.RealmsLongRunningMcoTaskScreen()) {
                if (livingentity.d_2545_n() != livingentity.o_2767_H()) {
                    return false;
                }
                boolean flag = g_1253_u.n_1700_B(g_1253_u.this, livingentity);
                if (!flag) {
                    g_1253_u.this.e_4240_b().n_1700_B((N_4263_v)livingentity, 0);
                    g_1253_u.this.Y_259_p(false);
                    g_1253_u.this.Q_2552_b(false);
                }
                return flag;
            }
            return false;
        }

        @Override
        public boolean J_1907_R() {
            r_4811_B livingentity = g_1253_u.this.t_148_a();
            if (livingentity != null && livingentity.RealmsLongRunningMcoTaskScreen()) {
                double d0 = g_1253_u.this.I_4348_c().R_4764_Y;
                return !(d0 * d0 < (double)0.05f && Math.abs(g_1253_u.this.f_4016_n) < 15.0f && g_1253_u.this.e_1992_r || g_1253_u.this.y_2447_C());
            }
            return false;
        }

        @Override
        public boolean r_() {
            return false;
        }

        @Override
        public void R_4764_Y() {
            g_1253_u.this.t_1786_h(true);
            g_1253_u.this.Y_601_j(true);
            g_1253_u.this.Q_2552_b(false);
            r_4811_B livingentity = g_1253_u.this.t_148_a();
            g_1253_u.this.c_3005_b().n_1700_B(livingentity, 60.0f, 30.0f);
            e_2866_D vector3d = new e_2866_D(livingentity.O_3598_v() - g_1253_u.this.O_3598_v(), livingentity.X_2960_b() - g_1253_u.this.X_2960_b(), livingentity.l_2647_k() - g_1253_u.this.l_2647_k()).G_564_y();
            g_1253_u.this.v_4262_N(g_1253_u.this.I_4348_c().J_1907_R(vector3d.J_1907_R * 0.8, 0.9, vector3d.G_564_y * 0.8));
            g_1253_u.this.e_4240_b().h_1847_R();
        }

        @Override
        public void G_564_y() {
            g_1253_u.this.Y_259_p(false);
            g_1253_u.this.c_3005_b = 0.0f;
            g_1253_u.this.H_2857_Y = 0.0f;
            g_1253_u.this.Q_2552_b(false);
            g_1253_u.this.Y_601_j(false);
        }

        @Override
        public void P_1922_E() {
            r_4811_B livingentity = g_1253_u.this.t_148_a();
            if (livingentity != null) {
                g_1253_u.this.c_3005_b().n_1700_B(livingentity, 60.0f, 30.0f);
            }
            if (!g_1253_u.this.y_2447_C()) {
                e_2866_D vector3d = g_1253_u.this.I_4348_c();
                if (vector3d.R_4764_Y * vector3d.R_4764_Y < (double)0.03f && g_1253_u.this.f_4016_n != 0.0f) {
                    g_1253_u.this.f_4016_n = u_530_F.t_148_a(g_1253_u.this.f_4016_n, 0.0f, 0.2f);
                } else {
                    double d0 = Math.sqrt(N_4263_v.R_4764_Y(vector3d));
                    double d1 = Math.signum(-vector3d.R_4764_Y) * Math.acos(d0 / vector3d.u_1723_Y()) * 57.2957763671875;
                    g_1253_u.this.f_4016_n = (float)d1;
                }
            }
            if (livingentity != null && g_1253_u.this.R_4764_Y((N_4263_v)livingentity) <= 2.0f) {
                g_1253_u.this.q_2307_F(livingentity);
            } else if (g_1253_u.this.f_4016_n > 0.0f && g_1253_u.this.e_1992_r && (float)g_1253_u.this.I_4348_c().R_4764_Y != 0.0f && g_1253_u.this.O_508_d.getBlockState(g_1253_u.this.b_2312_j()).n_1700_B(a_3742_W.X_290_I)) {
                g_1253_u.this.f_4016_n = 60.0f;
                g_1253_u.this.R_4764_Y((r_4811_B)null);
                g_1253_u.this.C_2741_M(true);
            }
        }
    }

    class u_1723_Y
    extends S_199_U {
        private int R_4764_Y;

        public u_1723_Y(double p_i50724_2_) {
            super(g_1253_u.this, p_i50724_2_);
            this.R_4764_Y = 100;
        }

        @Override
        public boolean n_1700_B() {
            if (!g_1253_u.this.z_2372_L() && this.n_1700_B.t_148_a() == null) {
                if (g_1253_u.this.O_508_d.N_2525_X()) {
                    return true;
                }
                if (this.R_4764_Y > 0) {
                    --this.R_4764_Y;
                    return false;
                }
                this.R_4764_Y = 100;
                c_1514_x blockpos = this.n_1700_B.b_2312_j();
                return g_1253_u.this.O_508_d.q_4610_l() && g_1253_u.this.O_508_d.canSeeSky(blockpos) && !((e_3591_l)g_1253_u.this.O_508_d).q_2307_F(blockpos) && this.v_4262_N();
            }
            return false;
        }

        @Override
        public void R_4764_Y() {
            g_1253_u.this.D_3612_q();
            super.R_4764_Y();
        }
    }

    class R_4764_Y
    extends b_4953_N {
        public R_4764_Y(double p_i50731_2_, boolean p_i50731_4_) {
            super(g_1253_u.this, p_i50731_2_, p_i50731_4_);
        }

        @Override
        protected void n_1700_B(r_4811_B enemy, double distToEnemySqr) {
            double d0 = this.n_1700_B(enemy);
            if (distToEnemySqr <= d0 && this.w_1484_f()) {
                this.v_4262_N();
                this.n_1700_B.q_2307_F(enemy);
                g_1253_u.this.n_1700_B(SoundEvents.N_260_m, 1.0f, 1.0f);
            }
        }

        @Override
        public void R_4764_Y() {
            g_1253_u.this.Q_2552_b(false);
            super.R_4764_Y();
        }

        @Override
        public boolean n_1700_B() {
            return !g_1253_u.this.V_1176_p() && !g_1253_u.this.z_2372_L() && !g_1253_u.this.Z_875_P() && !g_1253_u.this.y_2447_C() && super.n_1700_B();
        }
    }

    class multiplayerClientSuggestionProvider
    extends J_1907_R {
        private int R_4764_Y;

        public multiplayerClientSuggestionProvider() {
            this.R_4764_Y = g_1253_u.this.RealmsWorldOptions.nextInt(140);
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R, Goal.n_1700_B.R_4764_Y));
        }

        @Override
        public boolean n_1700_B() {
            if (g_1253_u.this.L_1362_X == 0.0f && g_1253_u.this.P_5000_x == 0.0f && g_1253_u.this.L_4248_u == 0.0f) {
                return this.s_956_w() || g_1253_u.this.z_2372_L();
            }
            return false;
        }

        @Override
        public boolean J_1907_R() {
            return this.s_956_w();
        }

        private boolean s_956_w() {
            if (this.R_4764_Y > 0) {
                --this.R_4764_Y;
                return false;
            }
            return g_1253_u.this.O_508_d.q_4610_l() && this.v_4262_N() && !this.w_1484_f();
        }

        @Override
        public void G_564_y() {
            this.R_4764_Y = g_1253_u.this.RealmsWorldOptions.nextInt(140);
            g_1253_u.this.D_3612_q();
        }

        @Override
        public void R_4764_Y() {
            g_1253_u.this.w_1457_N(false);
            g_1253_u.this.Y_259_p(false);
            g_1253_u.this.Q_2552_b(false);
            g_1253_u.this.t_1786_h(false);
            g_1253_u.this.q_2307_F(true);
            g_1253_u.this.e_4240_b().h_1847_R();
            g_1253_u.this.A_4115_X().n_1700_B(g_1253_u.this.O_3598_v(), g_1253_u.this.X_2960_b(), g_1253_u.this.l_2647_k(), 0.0);
        }
    }

    class v_4262_N
    extends v_2621_q {
        private final g_1253_u n_1700_B;

        public v_4262_N(g_1253_u this$0, g_1253_u p_i50735_2_, double p_i50735_3_) {
            super(p_i50735_2_, p_i50735_3_);
            this.n_1700_B = p_i50735_2_;
        }

        @Override
        public boolean n_1700_B() {
            return !this.n_1700_B.y_3417_N() && super.n_1700_B();
        }

        @Override
        public boolean J_1907_R() {
            return !this.n_1700_B.y_3417_N() && super.J_1907_R();
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B.D_3612_q();
            super.R_4764_Y();
        }
    }

    class w_1457_N
    extends StrollThroughVillageGoal {
        public w_1457_N(int p_i50726_2_, int p_i50726_3_) {
            super(g_1253_u.this, p_i50726_3_);
        }

        @Override
        public void R_4764_Y() {
            g_1253_u.this.D_3612_q();
            super.R_4764_Y();
        }

        @Override
        public boolean n_1700_B() {
            return super.n_1700_B() && this.v_4262_N();
        }

        @Override
        public boolean J_1907_R() {
            return super.J_1907_R() && this.v_4262_N();
        }

        private boolean v_4262_N() {
            return !g_1253_u.this.z_2372_L() && !g_1253_u.this.V_1176_p() && !g_1253_u.this.y_3417_N() && g_1253_u.this.t_148_a() == null;
        }
    }

    public class G_564_y
    extends MoveToBlockGoal {
        protected int v_4262_N;

        public G_564_y(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
            super(g_1253_u.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
        }

        @Override
        public double w_1484_f() {
            return 2.0;
        }

        @Override
        public boolean u_2550_I() {
            return this.G_564_y % 100 == 0;
        }

        @Override
        protected boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
            K_4074_S blockstate = worldIn.getBlockState(pos);
            return blockstate.n_1700_B(a_3742_W.s_4405_m) && blockstate.R_4764_Y(SweetBerryBushBlock.P_4830_p) >= 2;
        }

        @Override
        public void P_1922_E() {
            if (this.M_588_G()) {
                if (this.v_4262_N >= 40) {
                    this.h_1847_R();
                } else {
                    ++this.v_4262_N;
                }
            } else if (!this.M_588_G() && g_1253_u.this.RealmsWorldOptions.nextFloat() < 0.05f) {
                g_1253_u.this.n_1700_B(SoundEvents.f_2403_E, 1.0f, 1.0f);
            }
            super.P_1922_E();
        }

        protected void h_1847_R() {
            K_4074_S blockstate;
            if (g_1253_u.this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R) && (blockstate = g_1253_u.this.O_508_d.getBlockState(this.P_1922_E)).n_1700_B(a_3742_W.s_4405_m)) {
                int i = blockstate.R_4764_Y(SweetBerryBushBlock.P_4830_p);
                blockstate.n_1700_B(SweetBerryBushBlock.P_4830_p, 1);
                int j = 1 + g_1253_u.this.O_508_d.w_1457_N.nextInt(2) + (i == 3 ? 1 : 0);
                Z_1993_T itemstack = g_1253_u.this.J_1907_R(e_1174_E.n_1700_B);
                if (itemstack.n_1700_B()) {
                    g_1253_u.this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.D_265_n));
                    --j;
                }
                if (j > 0) {
                    T_2915_h.n_1700_B(g_1253_u.this.O_508_d, this.P_1922_E, new Z_1993_T(Items.D_265_n, j));
                }
                g_1253_u.this.n_1700_B(SoundEvents.l_683_e, 1.0f, 1.0f);
                g_1253_u.this.O_508_d.n_1700_B(this.P_1922_E, (K_4074_S)blockstate.n_1700_B(SweetBerryBushBlock.P_4830_p, 1), 2);
            }
        }

        @Override
        public boolean n_1700_B() {
            return !g_1253_u.this.z_2372_L() && super.n_1700_B();
        }

        @Override
        public void R_4764_Y() {
            this.v_4262_N = 0;
            g_1253_u.this.w_1457_N(false);
            super.R_4764_Y();
        }
    }

    class P_1922_E
    extends Goal {
        public P_1922_E() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            if (!g_1253_u.this.J_1907_R(e_1174_E.n_1700_B).n_1700_B()) {
                return false;
            }
            if (g_1253_u.this.t_148_a() == null && g_1253_u.this.q_817_e() == null) {
                if (!g_1253_u.this.R_2822_N()) {
                    return false;
                }
                if (g_1253_u.this.M_3508_C().nextInt(10) != 0) {
                    return false;
                }
                List<n_1494_c> list = g_1253_u.this.O_508_d.n_1700_B(n_1494_c.class, g_1253_u.this.i_601_W().grow(8.0, 8.0, 8.0), multiplayerClientSuggestionProvider);
                return !list.isEmpty() && g_1253_u.this.J_1907_R(e_1174_E.n_1700_B).n_1700_B();
            }
            return false;
        }

        @Override
        public void P_1922_E() {
            List<n_1494_c> list = g_1253_u.this.O_508_d.n_1700_B(n_1494_c.class, g_1253_u.this.i_601_W().grow(8.0, 8.0, 8.0), multiplayerClientSuggestionProvider);
            Z_1993_T itemstack = g_1253_u.this.J_1907_R(e_1174_E.n_1700_B);
            if (itemstack.n_1700_B() && !list.isEmpty()) {
                g_1253_u.this.e_4240_b().n_1700_B((N_4263_v)list.get(0), (double)1.2f);
            }
        }

        @Override
        public void R_4764_Y() {
            List<n_1494_c> list = g_1253_u.this.O_508_d.n_1700_B(n_1494_c.class, g_1253_u.this.i_601_W().grow(8.0, 8.0, 8.0), multiplayerClientSuggestionProvider);
            if (!list.isEmpty()) {
                g_1253_u.this.e_4240_b().n_1700_B((N_4263_v)list.get(0), (double)1.2f);
            }
        }
    }

    class Q_2552_b
    extends LookAtPlayerGoal {
        public Q_2552_b(Z_530_i p_i50733_2_, Class<? extends r_4811_B> p_i50733_3_, float p_i50733_4_) {
            super(p_i50733_2_, p_i50733_3_, p_i50733_4_);
        }

        @Override
        public boolean n_1700_B() {
            return super.n_1700_B() && !g_1253_u.this.y_2447_C() && !g_1253_u.this.c_2086_l();
        }

        @Override
        public boolean J_1907_R() {
            return super.J_1907_R() && !g_1253_u.this.y_2447_C() && !g_1253_u.this.c_2086_l();
        }
    }

    class t_1786_h
    extends J_1907_R {
        private double R_4764_Y;
        private double G_564_y;
        private int P_1922_E;
        private int u_1723_Y;

        public t_1786_h() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            return g_1253_u.this.q_817_e() == null && g_1253_u.this.M_3508_C().nextFloat() < 0.02f && !g_1253_u.this.z_2372_L() && g_1253_u.this.t_148_a() == null && g_1253_u.this.e_4240_b().M_588_G() && !this.w_1484_f() && !g_1253_u.this.J_3635_s() && !g_1253_u.this.Z_875_P();
        }

        @Override
        public boolean J_1907_R() {
            return this.u_1723_Y > 0;
        }

        @Override
        public void R_4764_Y() {
            this.s_956_w();
            this.u_1723_Y = 2 + g_1253_u.this.M_3508_C().nextInt(3);
            g_1253_u.this.w_1457_N(true);
            g_1253_u.this.e_4240_b().h_1847_R();
        }

        @Override
        public void G_564_y() {
            g_1253_u.this.w_1457_N(false);
        }

        @Override
        public void P_1922_E() {
            --this.P_1922_E;
            if (this.P_1922_E <= 0) {
                --this.u_1723_Y;
                this.s_956_w();
            }
            g_1253_u.this.c_3005_b().n_1700_B(g_1253_u.this.O_3598_v() + this.R_4764_Y, g_1253_u.this.X_2048_Y(), g_1253_u.this.l_2647_k() + this.G_564_y, g_1253_u.this.H_1990_U(), g_1253_u.this.Z_976_R());
        }

        private void s_956_w() {
            double d0 = Math.PI * 2 * g_1253_u.this.M_3508_C().nextDouble();
            this.R_4764_Y = Math.cos(d0);
            this.G_564_y = Math.sin(d0);
            this.P_1922_E = 80 + g_1253_u.this.M_3508_C().nextInt(20);
        }
    }

    class M_182_A
    extends NearestAttackableTargetGoal<r_4811_B> {
        @Nullable
        private r_4811_B s_956_w;
        private r_4811_B u_2550_I;
        private int M_588_G;

        public M_182_A(Class<r_4811_B> p_i50743_2_, boolean p_i50743_3_, @Nullable boolean p_i50743_4_, Predicate<r_4811_B> p_i50743_5_) {
            super(g_1253_u.this, p_i50743_2_, 10, p_i50743_3_, p_i50743_4_, p_i50743_5_);
        }

        @Override
        public boolean n_1700_B() {
            if (this.J_1907_R > 0 && this.P_1922_E.M_3508_C().nextInt(this.J_1907_R) != 0) {
                return false;
            }
            for (UUID uuid : g_1253_u.this.U_3758_B()) {
                r_4811_B livingentity;
                N_4263_v entity;
                if (uuid == null || !(g_1253_u.this.O_508_d instanceof e_3591_l) || !((entity = ((e_3591_l)g_1253_u.this.O_508_d).J_1907_R(uuid)) instanceof r_4811_B)) continue;
                this.u_2550_I = livingentity = (r_4811_B)entity;
                this.s_956_w = livingentity.q_817_e();
                int i = livingentity.r_260_T();
                return i != this.M_588_G && this.n_1700_B(this.s_956_w, this.G_564_y);
            }
            return false;
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B(this.s_956_w);
            this.R_4764_Y = this.s_956_w;
            if (this.u_2550_I != null) {
                this.M_588_G = this.u_2550_I.r_260_T();
            }
            g_1253_u.this.n_1700_B(SoundEvents.g_1031_K, 1.0f, 1.0f);
            g_1253_u.this.k_2293_S(true);
            g_1253_u.this.A_1306_N();
            super.R_4764_Y();
        }
    }

    public static final class Y_259_p
    extends Enum<Y_259_p> {
        public static final /* enum */ Y_259_p n_1700_B = new Y_259_p(0, "red", biomeBiomes.u_1723_Y, biomeBiomes.Y_601_j, biomeBiomes.z_1333_t, biomeBiomes.e_4240_b, biomeBiomes.j_276_v, biomeBiomes.n_3318_d, biomeBiomes.UploadStatus);
        public static final /* enum */ Y_259_p J_1907_R = new Y_259_p(1, "snow", biomeBiomes.t_4043_B, biomeBiomes.x_607_J, biomeBiomes.f_4016_n);
        private static final Y_259_p[] R_4764_Y;
        private static final Map<String, Y_259_p> G_564_y;
        private final int P_1922_E;
        private final String u_1723_Y;
        private final List<f_2392_k<k_594_Q>> v_4262_N;
        private static final /* synthetic */ Y_259_p[] w_1484_f;

        public static Y_259_p[] values() {
            return (Y_259_p[])w_1484_f.clone();
        }

        public static Y_259_p valueOf(String name) {
            return Enum.valueOf(Y_259_p.class, name);
        }

        private Y_259_p(int p_i241911_3_, String p_i241911_4_, f_2392_k<k_594_Q> ... p_i241911_5_) {
            this.P_1922_E = p_i241911_3_;
            this.u_1723_Y = p_i241911_4_;
            this.v_4262_N = Arrays.asList(p_i241911_5_);
        }

        public String n_1700_B() {
            return this.u_1723_Y;
        }

        public int J_1907_R() {
            return this.P_1922_E;
        }

        public static Y_259_p n_1700_B(String nameIn) {
            return G_564_y.getOrDefault(nameIn, n_1700_B);
        }

        public static Y_259_p n_1700_B(int indexIn) {
            if (indexIn < 0 || indexIn > R_4764_Y.length) {
                indexIn = 0;
            }
            return R_4764_Y[indexIn];
        }

        public static Y_259_p n_1700_B(Optional<f_2392_k<k_594_Q>> p_242325_0_) {
            return p_242325_0_.isPresent() && lightning.product.g_1253_u$Y_259_p.J_1907_R.v_4262_N.contains(p_242325_0_.get()) ? J_1907_R : n_1700_B;
        }

        private static /* synthetic */ Y_259_p[] R_4764_Y() {
            return new Y_259_p[]{n_1700_B, J_1907_R};
        }

        static {
            w_1484_f = lightning.product.g_1253_u$Y_259_p.R_4764_Y();
            R_4764_Y = (Y_259_p[])Arrays.stream(lightning.product.g_1253_u$Y_259_p.values()).sorted(Comparator.comparingInt(Y_259_p::J_1907_R)).toArray(Y_259_p[]::new);
            G_564_y = Arrays.stream(lightning.product.g_1253_u$Y_259_p.values()).collect(Collectors.toMap(Y_259_p::n_1700_B, p_221081_0_ -> p_221081_0_));
        }
    }

    public static class t_148_a
    extends AgableMob.n_1700_B {
        public final Y_259_p n_1700_B;

        public t_148_a(Y_259_p p_i50734_1_) {
            super(false);
            this.n_1700_B = p_i50734_1_;
        }
    }

    abstract class J_1907_R
    extends Goal {
        private final TargetingConditions J_1907_R;

        private J_1907_R() {
            this.J_1907_R = new TargetingConditions().n_1700_B(12.0).R_4764_Y().n_1700_B(new n_1700_B());
        }

        protected boolean v_4262_N() {
            c_1514_x blockpos = new c_1514_x(g_1253_u.this.O_3598_v(), g_1253_u.this.i_601_W().maxY, g_1253_u.this.l_2647_k());
            return !g_1253_u.this.O_508_d.canSeeSky(blockpos) && g_1253_u.this.n_1700_B(blockpos) >= 0.0f;
        }

        protected boolean w_1484_f() {
            return !g_1253_u.this.O_508_d.n_1700_B(r_4811_B.class, this.J_1907_R, g_1253_u.this, g_1253_u.this.i_601_W().grow(12.0, 6.0, 12.0)).isEmpty();
        }
    }

    public class n_1700_B
    implements Predicate<r_4811_B> {
        public boolean n_1700_B(r_4811_B p_test_1_) {
            if (p_test_1_ instanceof g_1253_u) {
                return false;
            }
            if (!(p_test_1_ instanceof X_4861_v || p_test_1_ instanceof M_2433_H || p_test_1_ instanceof Monster)) {
                if (p_test_1_ instanceof C_3622_I) {
                    return !((C_3622_I)p_test_1_).U_3758_B();
                }
                if (!(p_test_1_ instanceof a_3913_L) || !p_test_1_.d_2461_k() && !((a_3913_L)p_test_1_).G_624_v()) {
                    if (g_1253_u.this.R_4764_Y(p_test_1_.w_2705_t())) {
                        return false;
                    }
                    return !p_test_1_.z_2372_L() && !p_test_1_.U_1341_G();
                }
                return false;
            }
            return true;
        }

        @Override
        public /* synthetic */ boolean test(Object object) {
            return this.n_1700_B((r_4811_B)object);
        }
    }
}


