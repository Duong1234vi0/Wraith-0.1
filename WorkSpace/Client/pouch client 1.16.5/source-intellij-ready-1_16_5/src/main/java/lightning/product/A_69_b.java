/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.serialization.Dynamic
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_4388_s;
import lightning.product.A_4919_q;
import lightning.product.Projectile;
import lightning.product.C_4114_x;
import lightning.product.SensorType;
import lightning.product.E_4668_a;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.M_4954_p;
import lightning.product.N_1216_z;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.ProjectileWeaponItem;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.Sensor;
import lightning.product.b_4507_u;
import lightning.product.AbstractPiglin;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.q_2464_b;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;
import lightning.product.x_1688_C;

public class A_69_b
extends AbstractPiglin
implements M_4954_p {
    private static final h_256_u<Boolean> Q_4569_t = C_4114_x.n_1700_B(A_69_b.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> M_182_A = C_4114_x.n_1700_B(A_69_b.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> t_1786_h = C_4114_x.n_1700_B(A_69_b.class, EntityDataSerializers.t_148_a);
    private static final UUID multiplayerClientSuggestionProvider = UUID.fromString("766bfa64-11f3-11ea-8d71-362b9e155667");
    private static final U_1880_G w_1457_N = new U_1880_G(multiplayerClientSuggestionProvider, "Baby speed boost", (double)0.2f, U_1880_G.n_1700_B.J_1907_R);
    private final N_1216_z Y_601_j = new N_1216_z(8);
    private boolean Y_259_p = false;
    protected static final ImmutableList<SensorType<? extends Sensor<? super A_69_b>>> R_4764_Y = ImmutableList.of(SensorType.R_4764_Y, SensorType.G_564_y, SensorType.J_1907_R, SensorType.u_1723_Y, SensorType.u_2550_I);
    protected static final ImmutableList<MemoryModuleType<?>> h_1847_R = ImmutableList.of(MemoryModuleType.h_1847_R, MemoryModuleType.Q_2552_b, MemoryModuleType.v_4262_N, MemoryModuleType.w_1484_f, MemoryModuleType.u_2550_I, MemoryModuleType.M_588_G, MemoryModuleType.c_4037_x, MemoryModuleType.N_2525_X, MemoryModuleType.z_1737_N, MemoryModuleType.k_2293_S, MemoryModuleType.q_2307_F, MemoryModuleType.P_4830_p, (Object[])new MemoryModuleType[]{MemoryModuleType.Y_1740_V, MemoryModuleType.Q_4569_t, MemoryModuleType.M_182_A, MemoryModuleType.t_1786_h, MemoryModuleType.Y_601_j, MemoryModuleType.d_2461_k, MemoryModuleType.G_624_v, MemoryModuleType.Z_875_P, MemoryModuleType.T_2506_i, MemoryModuleType.q_4610_l, MemoryModuleType.g_221_o, MemoryModuleType.z_4693_k, MemoryModuleType.B_1668_F, MemoryModuleType.g_164_R, MemoryModuleType.e_2887_G, MemoryModuleType.Z_976_R, MemoryModuleType.v_4276_D, MemoryModuleType.D_4792_h, MemoryModuleType.w_1457_N, MemoryModuleType.s_2632_s, MemoryModuleType.l_1233_K, MemoryModuleType.X_933_l, MemoryModuleType.H_1990_U, MemoryModuleType.z_1333_t, MemoryModuleType.O_508_d, MemoryModuleType.r_715_M});

    public A_69_b(t_5_h<? extends AbstractPiglin> p_i231570_1_, b_4507_u p_i231570_2_) {
        super(p_i231570_1_, p_i231570_2_);
        this.P_1922_E = 5;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.d_()) {
            compound.n_1700_B("IsBaby", true);
        }
        if (this.Y_259_p) {
            compound.n_1700_B("CannotHunt", true);
        }
        compound.n_1700_B("Inventory", this.Y_601_j.R_4764_Y());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.n_1700_B(compound.t_1786_h("IsBaby"));
        this.Y_259_p(compound.t_1786_h("CannotHunt"));
        this.Y_601_j.n_1700_B(compound.G_564_y("Inventory", 10));
    }

    @Override
    protected void n_1700_B(P_11_z source, int looting, boolean recentlyHitIn) {
        super.n_1700_B(source, looting, recentlyHitIn);
        this.Y_601_j.G_564_y().forEach(this::a_);
    }

    protected Z_1993_T u_2550_I(Z_1993_T p_234436_1_) {
        return this.Y_601_j.n_1700_B(p_234436_1_);
    }

    protected boolean M_588_G(Z_1993_T p_234437_1_) {
        return this.Y_601_j.J_1907_R(p_234437_1_);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(Q_4569_t, false);
        this.l_4537_E.n_1700_B(M_182_A, false);
        this.l_4537_E.n_1700_B(t_1786_h, false);
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        super.n_1700_B(key);
        if (Q_4569_t.equals(key)) {
            this.g_();
        }
    }

    public static s_1415_m.n_1700_B f_2787_O() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 16.0).n_1700_B(Attributes.G_564_y, 0.35f).n_1700_B(Attributes.u_1723_Y, 5.0);
    }

    public static boolean J_1907_R(t_5_h<A_69_b> p_234418_0_, LevelAccessor p_234418_1_, a_3160_D p_234418_2_, c_1514_x p_234418_3_, Random p_234418_4_) {
        return !p_234418_1_.getBlockState(p_234418_3_.down()).n_1700_B(a_3742_W.LockSlot);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        if (reason != a_3160_D.G_564_y) {
            if (worldIn.e_4240_b().nextFloat() < 0.2f) {
                this.n_1700_B(true);
            } else if (this.y_2447_C()) {
                this.n_1700_B(e_1174_E.n_1700_B, this.U_1697_c());
            }
        }
        A_4919_q.n_1700_B(this);
        this.n_1700_B(difficultyIn);
        this.J_1907_R(difficultyIn);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected boolean B_1668_F() {
        return false;
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return !this.s_2632_s();
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        if (this.y_2447_C()) {
            this.G_564_y(e_1174_E.u_1723_Y, new Z_1993_T(Items.h_3066_J));
            this.G_564_y(e_1174_E.P_1922_E, new Z_1993_T(Items.m_38_G));
            this.G_564_y(e_1174_E.G_564_y, new Z_1993_T(Items.k_4946_A));
            this.G_564_y(e_1174_E.R_4764_Y, new Z_1993_T(Items.m_4644_u));
        }
    }

    private void G_564_y(e_1174_E p_234419_1_, Z_1993_T p_234419_2_) {
        if (this.O_508_d.w_1457_N.nextFloat() < 0.1f) {
            this.n_1700_B(p_234419_1_, p_234419_2_);
        }
    }

    protected E_4668_a.n_1700_B<A_69_b> U_532_X() {
        return E_4668_a.n_1700_B(h_1847_R, R_4764_Y);
    }

    @Override
    protected E_4668_a<?> n_1700_B(Dynamic<?> dynamicIn) {
        return A_4919_q.n_1700_B(this, this.U_532_X().n_1700_B(dynamicIn));
    }

    public E_4668_a<A_69_b> y_1945_D() {
        return super.y_1945_D();
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        m_3054_I actionresulttype = super.J_1907_R(p_230254_1_, p_230254_2_);
        if (actionresulttype.n_1700_B()) {
            return actionresulttype;
        }
        if (!this.O_508_d.Y_259_p) {
            return A_4919_q.n_1700_B(this, p_230254_1_, p_230254_2_);
        }
        boolean flag = A_4919_q.J_1907_R(this, p_230254_1_.R_4764_Y(p_230254_2_)) && this.J_3635_s() != q_2464_b.G_564_y;
        return flag ? m_3054_I.n_1700_B : m_3054_I.R_4764_Y;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return this.d_() ? 0.93f : 1.74f;
    }

    @Override
    public double s_1671_u() {
        return (double)this.v_165_F() * 0.92;
    }

    @Override
    public void n_1700_B(boolean childZombie) {
        this.D_60_a().J_1907_R(Q_4569_t, childZombie);
        if (!this.O_508_d.Y_259_p) {
            A_4388_s modifiableattributeinstance = this.n_1700_B(Attributes.G_564_y);
            modifiableattributeinstance.G_564_y(w_1457_N);
            if (childZombie) {
                modifiableattributeinstance.J_1907_R(w_1457_N);
            }
        }
    }

    @Override
    public boolean d_() {
        return this.D_60_a().n_1700_B(Q_4569_t);
    }

    private void Y_259_p(boolean p_234443_1_) {
        this.Y_259_p = p_234443_1_;
    }

    @Override
    protected boolean u_1723_Y() {
        return !this.Y_259_p;
    }

    @Override
    protected void X_933_l() {
        this.O_508_d.D_4792_h().n_1700_B("piglinBrain");
        this.y_1945_D().n_1700_B((e_3591_l)this.O_508_d, this);
        this.O_508_d.D_4792_h().R_4764_Y();
        A_4919_q.J_1907_R(this);
        super.X_933_l();
    }

    @Override
    protected int R_4764_Y(a_3913_L player) {
        return this.P_1922_E;
    }

    @Override
    protected void R_4764_Y(e_3591_l p_234416_1_) {
        A_4919_q.R_4764_Y(this);
        this.Y_601_j.G_564_y().forEach(this::a_);
        super.R_4764_Y(p_234416_1_);
    }

    private Z_1993_T U_1697_c() {
        return (double)this.RealmsWorldOptions.nextFloat() < 0.5 ? new Z_1993_T(Items.V_2454_J) : new Z_1993_T(Items.n_2412_y);
    }

    private boolean V_537_k() {
        return this.l_4537_E.n_1700_B(M_182_A);
    }

    @Override
    public void J_1907_R(boolean isCharging) {
        this.l_4537_E.J_1907_R(M_182_A, isCharging);
    }

    @Override
    public void n_1700_B() {
        this.UploadTokenCache = 0;
    }

    @Override
    public q_2464_b J_3635_s() {
        if (this.P_2295_B()) {
            return q_2464_b.P_1922_E;
        }
        if (A_4919_q.n_1700_B(this.S_4035_N().J_1907_R())) {
            return q_2464_b.G_564_y;
        }
        if (this.P_2272_O() && this.o_82_k()) {
            return q_2464_b.n_1700_B;
        }
        if (this.V_537_k()) {
            return q_2464_b.R_4764_Y;
        }
        return this.P_2272_O() && this.n_1700_B(Items.V_2454_J) ? q_2464_b.J_1907_R : q_2464_b.u_1723_Y;
    }

    public boolean P_2295_B() {
        return this.l_4537_E.n_1700_B(t_1786_h);
    }

    public void Y_601_j(boolean p_234442_1_) {
        this.l_4537_E.J_1907_R(t_1786_h, p_234442_1_);
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        boolean flag = super.n_1700_B(source, amount);
        if (this.O_508_d.Y_259_p) {
            return false;
        }
        if (flag && source.u_2550_I() instanceof r_4811_B) {
            A_4919_q.n_1700_B(this, (r_4811_B)source.u_2550_I());
        }
        return flag;
    }

    @Override
    public void J_1907_R(r_4811_B target, float distanceFactor) {
        this.n_1700_B((r_4811_B)this, 1.6f);
    }

    @Override
    public void n_1700_B(r_4811_B p_230284_1_, Z_1993_T p_230284_2_, Projectile p_230284_3_, float p_230284_4_) {
        this.n_1700_B(this, p_230284_1_, p_230284_3_, p_230284_4_, 1.6f);
    }

    @Override
    public boolean n_1700_B(ProjectileWeaponItem p_230280_1_) {
        return p_230280_1_ == Items.V_2454_J;
    }

    protected void P_4830_p(Z_1993_T p_234438_1_) {
        this.J_1907_R(e_1174_E.n_1700_B, p_234438_1_);
    }

    protected void h_1847_R(Z_1993_T p_234439_1_) {
        if (p_234439_1_.J_1907_R() == A_4919_q.n_1700_B) {
            this.n_1700_B(e_1174_E.J_1907_R, p_234439_1_);
            this.G_564_y(e_1174_E.J_1907_R);
        } else {
            this.J_1907_R(e_1174_E.J_1907_R, p_234439_1_);
        }
    }

    @Override
    public boolean t_148_a(Z_1993_T p_230293_1_) {
        return this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R) && this.D_4792_h() && A_4919_q.n_1700_B(this, p_230293_1_);
    }

    protected boolean Q_4569_t(Z_1993_T p_234440_1_) {
        e_1174_E equipmentslottype = Z_530_i.s_956_w(p_234440_1_);
        Z_1993_T itemstack = this.J_1907_R(equipmentslottype);
        return this.n_1700_B(p_234440_1_, itemstack);
    }

    @Override
    protected boolean n_1700_B(Z_1993_T candidate, Z_1993_T existing) {
        boolean flag1;
        if (K_4096_w.G_564_y(existing)) {
            return false;
        }
        boolean flag = A_4919_q.n_1700_B(candidate.J_1907_R()) || candidate.J_1907_R() == Items.V_2454_J;
        boolean bl = flag1 = A_4919_q.n_1700_B(existing.J_1907_R()) || existing.J_1907_R() == Items.V_2454_J;
        if (flag && !flag1) {
            return true;
        }
        if (!flag && flag1) {
            return false;
        }
        return this.y_2447_C() && candidate.J_1907_R() != Items.V_2454_J && existing.J_1907_R() == Items.V_2454_J ? false : super.n_1700_B(candidate, existing);
    }

    @Override
    protected void J_1907_R(n_1494_c itemEntity) {
        this.n_1700_B(itemEntity);
        A_4919_q.n_1700_B(this, itemEntity);
    }

    @Override
    public boolean n_1700_B(N_4263_v entityIn, boolean force) {
        if (this.d_() && entityIn.f_4016_n() == t_5_h.e_4240_b) {
            entityIn = this.J_1907_R(entityIn, 3);
        }
        return super.n_1700_B(entityIn, force);
    }

    private N_4263_v J_1907_R(N_4263_v p_234417_1_, int p_234417_2_) {
        List<N_4263_v> list = p_234417_1_.o_3599_Z();
        return p_234417_2_ != 1 && !list.isEmpty() ? this.J_1907_R(list.get(0), p_234417_2_ - 1) : p_234417_1_;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return this.O_508_d.Y_259_p ? null : A_4919_q.G_564_y(this).orElse(null);
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.t_1509_b;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.V_3441_j;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.z_2759_Q, 0.15f, 1.0f);
    }

    protected void n_1700_B(SoundEvent p_241417_1_) {
        this.n_1700_B(p_241417_1_, this.d_4500_Q(), this.O_2761_o());
    }

    @Override
    protected void h_973_D() {
        this.n_1700_B(SoundEvents.a_1344_X);
    }
}



