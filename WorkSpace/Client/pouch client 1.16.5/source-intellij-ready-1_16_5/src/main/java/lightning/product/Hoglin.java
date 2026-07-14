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
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.DebugPackets;
import lightning.product.C_4114_x;
import lightning.product.C_4816_K;
import lightning.product.AgableMob;
import lightning.product.D_38_f;
import lightning.product.SensorType;
import lightning.product.E_4668_a;
import lightning.product.Attributes;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.T_1316_M;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.Sensor;
import lightning.product.b_4507_u;
import lightning.product.c_1065_N;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.k_2610_C;
import lightning.product.m_3054_I;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;
import lightning.product.x_1688_C;
import lightning.product.x_1835_e;
import lightning.product.HoglinBase;

public class Hoglin
extends Animal
implements x_1835_e,
HoglinBase {
    private static final h_256_u<Boolean> M_182_A = C_4114_x.n_1700_B(Hoglin.class, EntityDataSerializers.t_148_a);
    private int t_1786_h;
    private int multiplayerClientSuggestionProvider = 0;
    private boolean w_1457_N = false;
    protected static final ImmutableList<? extends SensorType<? extends Sensor<? super Hoglin>>> h_1847_R = ImmutableList.of(SensorType.R_4764_Y, SensorType.G_564_y, SensorType.h_1847_R, SensorType.P_4830_p);
    protected static final ImmutableList<? extends MemoryModuleType<?>> Q_4569_t = ImmutableList.of(MemoryModuleType.multiplayerClientSuggestionProvider, MemoryModuleType.v_4262_N, MemoryModuleType.w_1484_f, MemoryModuleType.u_2550_I, MemoryModuleType.M_588_G, MemoryModuleType.h_1847_R, MemoryModuleType.P_4830_p, MemoryModuleType.Y_1740_V, MemoryModuleType.Y_601_j, MemoryModuleType.Q_4569_t, MemoryModuleType.M_182_A, MemoryModuleType.T_3594_S, (Object[])new MemoryModuleType[]{MemoryModuleType.Z_875_P, MemoryModuleType.s_2632_s, MemoryModuleType.l_1233_K, MemoryModuleType.g_2268_R, MemoryModuleType.d_2427_y, MemoryModuleType.r_715_M, MemoryModuleType.A_1038_p});

    public Hoglin(t_5_h<? extends Hoglin> p_i231569_1_, b_4507_u p_i231569_2_) {
        super((t_5_h<? extends Animal>)p_i231569_1_, p_i231569_2_);
        this.P_1922_E = 5;
    }

    @Override
    public boolean G_564_y(a_3913_L player) {
        return !this.n_4915_F();
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 40.0).n_1700_B(Attributes.G_564_y, 0.3f).n_1700_B(Attributes.R_4764_Y, 0.6f).n_1700_B(Attributes.v_4262_N, 1.0).n_1700_B(Attributes.u_1723_Y, 6.0);
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        if (!(entityIn instanceof r_4811_B)) {
            return false;
        }
        this.t_1786_h = 10;
        this.O_508_d.n_1700_B((N_4263_v)this, (byte)4);
        this.n_1700_B(SoundEvents.H_1475_K, 1.0f, this.O_2761_o());
        c_1065_N.n_1700_B(this, (r_4811_B)entityIn);
        return HoglinBase.n_1700_B(this, (r_4811_B)entityIn);
    }

    @Override
    protected void P_1922_E(r_4811_B entityIn) {
        if (this.V_1176_p()) {
            HoglinBase.J_1907_R(this, entityIn);
        }
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        boolean flag = super.n_1700_B(source, amount);
        if (this.O_508_d.Y_259_p) {
            return false;
        }
        if (flag && source.u_2550_I() instanceof r_4811_B) {
            c_1065_N.J_1907_R(this, (r_4811_B)source.u_2550_I());
        }
        return flag;
    }

    protected E_4668_a.n_1700_B<Hoglin> U_532_X() {
        return E_4668_a.n_1700_B(Q_4569_t, h_1847_R);
    }

    @Override
    protected E_4668_a<?> n_1700_B(Dynamic<?> dynamicIn) {
        return c_1065_N.n_1700_B(this.U_532_X().n_1700_B(dynamicIn));
    }

    public E_4668_a<Hoglin> y_1945_D() {
        return super.y_1945_D();
    }

    @Override
    protected void X_933_l() {
        this.O_508_d.D_4792_h().n_1700_B("hoglinBrain");
        this.y_1945_D().n_1700_B((e_3591_l)this.O_508_d, this);
        this.O_508_d.D_4792_h().R_4764_Y();
        c_1065_N.n_1700_B(this);
        if (this.y_2447_C()) {
            ++this.multiplayerClientSuggestionProvider;
            if (this.multiplayerClientSuggestionProvider > 300) {
                this.n_1700_B(SoundEvents.c_1732_c);
                this.R_4764_Y((e_3591_l)this.O_508_d);
            }
        } else {
            this.multiplayerClientSuggestionProvider = 0;
        }
    }

    @Override
    public void Y_1740_V() {
        if (this.t_1786_h > 0) {
            --this.t_1786_h;
        }
        super.Y_1740_V();
    }

    @Override
    protected void y_() {
        if (this.d_()) {
            this.P_1922_E = 3;
            this.n_1700_B(Attributes.u_1723_Y).n_1700_B(0.5);
        } else {
            this.P_1922_E = 5;
            this.n_1700_B(Attributes.u_1723_Y).n_1700_B(6.0);
        }
    }

    public static boolean J_1907_R(t_5_h<Hoglin> p_234361_0_, LevelAccessor p_234361_1_, a_3160_D p_234361_2_, c_1514_x p_234361_3_, Random p_234361_4_) {
        return !p_234361_1_.getBlockState(p_234361_3_.down()).n_1700_B(a_3742_W.LockSlot);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        if (worldIn.e_4240_b().nextFloat() < 0.2f) {
            this.n_1700_B(true);
        }
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return !this.s_2632_s();
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        if (c_1065_N.n_1700_B(this, pos)) {
            return -1.0f;
        }
        return worldIn.getBlockState(pos.down()).n_1700_B(a_3742_W.ServerFunctionManager) ? 10.0f : 0.0f;
    }

    @Override
    public double s_1671_u() {
        return (double)this.v_165_F() - (this.d_() ? 0.2 : 0.15);
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        m_3054_I actionresulttype = super.J_1907_R(p_230254_1_, p_230254_2_);
        if (actionresulttype.n_1700_B()) {
            this.T_3594_S();
        }
        return actionresulttype;
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 4) {
            this.t_1786_h = 10;
            this.n_1700_B(SoundEvents.H_1475_K, 1.0f, this.O_2761_o());
        } else {
            super.n_1700_B(id);
        }
    }

    @Override
    public int h_1640_b() {
        return this.t_1786_h;
    }

    @Override
    protected boolean Q_3581_n() {
        return true;
    }

    @Override
    protected int R_4764_Y(a_3913_L player) {
        return this.P_1922_E;
    }

    private void R_4764_Y(e_3591_l p_234360_1_) {
        C_4816_K zoglinentity = this.n_1700_B(t_5_h.S_980_j, true);
        if (zoglinentity != null) {
            zoglinentity.n_1700_B(new k_2610_C(MobEffects.t_148_a, 200, 0));
        }
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return stack.J_1907_R() == Items.F_4247_a;
    }

    public boolean V_1176_p() {
        return !this.d_();
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(M_182_A, false);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.V_537_k()) {
            compound.n_1700_B("IsImmuneToZombification", true);
        }
        compound.J_1907_R("TimeInOverworld", this.multiplayerClientSuggestionProvider);
        if (this.w_1457_N) {
            compound.n_1700_B("CannotBeHunted", true);
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.w_1457_N(compound.t_1786_h("IsImmuneToZombification"));
        this.multiplayerClientSuggestionProvider = compound.w_1484_f("TimeInOverworld");
        this.Y_601_j(compound.t_1786_h("CannotBeHunted"));
    }

    public void w_1457_N(boolean p_234370_1_) {
        this.D_60_a().J_1907_R(M_182_A, p_234370_1_);
    }

    private boolean V_537_k() {
        return this.D_60_a().n_1700_B(M_182_A);
    }

    public boolean y_2447_C() {
        return !this.O_508_d.G_624_v().v_4262_N() && !this.V_537_k() && !this.n_473_l();
    }

    private void Y_601_j(boolean p_234371_1_) {
        this.w_1457_N = p_234371_1_;
    }

    public boolean J_3635_s() {
        return this.V_1176_p() && !this.w_1457_N;
    }

    @Override
    @Nullable
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        Hoglin hoglinentity = t_5_h.e_4240_b.n_1700_B(p_241840_1_);
        if (hoglinentity != null) {
            hoglinentity.T_3594_S();
        }
        return hoglinentity;
    }

    @Override
    public boolean o_82_k() {
        return !c_1065_N.R_4764_Y(this) && super.o_82_k();
    }

    @Override
    public D_38_f r_2478_U() {
        return D_38_f.u_1723_Y;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return this.O_508_d.Y_259_p ? null : c_1065_N.J_1907_R(this).orElse(null);
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.h_3858_e;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.I_2209_R;
    }

    @Override
    protected SoundEvent F_1410_V() {
        return SoundEvents.O_726_g;
    }

    @Override
    protected SoundEvent S_4022_R() {
        return SoundEvents.r_4601_j;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.t_4433_T, 0.15f, 1.0f);
    }

    protected void n_1700_B(SoundEvent p_241412_1_) {
        this.n_1700_B(p_241412_1_, this.d_4500_Q(), this.O_2761_o());
    }

    @Override
    protected void g_164_R() {
        super.g_164_R();
        DebugPackets.n_1700_B(this);
    }
}



