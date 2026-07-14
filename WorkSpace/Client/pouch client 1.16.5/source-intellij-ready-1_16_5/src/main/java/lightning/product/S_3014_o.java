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
import javax.annotation.Nullable;
import lightning.product.SensorType;
import lightning.product.E_4668_a;
import lightning.product.Attributes;
import lightning.product.K_4074_S;
import lightning.product.DifficultyInstance;
import lightning.product.P_11_z;
import lightning.product.Q_3304_A;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.Sensor;
import lightning.product.b_4507_u;
import lightning.product.AbstractPiglin;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.Monster;
import lightning.product.q_2464_b;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class S_3014_o
extends AbstractPiglin {
    protected static final ImmutableList<SensorType<? extends Sensor<? super S_3014_o>>> R_4764_Y = ImmutableList.of(SensorType.R_4764_Y, SensorType.G_564_y, SensorType.J_1907_R, SensorType.u_1723_Y, SensorType.M_588_G);
    protected static final ImmutableList<MemoryModuleType<?>> h_1847_R = ImmutableList.of(MemoryModuleType.h_1847_R, MemoryModuleType.Q_2552_b, MemoryModuleType.v_4262_N, MemoryModuleType.w_1484_f, MemoryModuleType.u_2550_I, MemoryModuleType.M_588_G, MemoryModuleType.c_4037_x, MemoryModuleType.N_2525_X, MemoryModuleType.k_2293_S, MemoryModuleType.q_2307_F, MemoryModuleType.P_4830_p, MemoryModuleType.Y_1740_V, (Object[])new MemoryModuleType[]{MemoryModuleType.Q_4569_t, MemoryModuleType.M_182_A, MemoryModuleType.t_1786_h, MemoryModuleType.Y_601_j, MemoryModuleType.d_2461_k, MemoryModuleType.v_4276_D, MemoryModuleType.J_1907_R});

    public S_3014_o(t_5_h<? extends S_3014_o> p_i241917_1_, b_4507_u p_i241917_2_) {
        super((t_5_h<? extends AbstractPiglin>)p_i241917_1_, p_i241917_2_);
        this.P_1922_E = 20;
    }

    public static s_1415_m.n_1700_B f_2787_O() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 50.0).n_1700_B(Attributes.G_564_y, 0.35f).n_1700_B(Attributes.u_1723_Y, 7.0);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        Q_3304_A.n_1700_B(this);
        this.n_1700_B(difficultyIn);
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.u_4724_w));
    }

    protected E_4668_a.n_1700_B<S_3014_o> U_532_X() {
        return E_4668_a.n_1700_B(h_1847_R, R_4764_Y);
    }

    @Override
    protected E_4668_a<?> n_1700_B(Dynamic<?> dynamicIn) {
        return Q_3304_A.n_1700_B(this, this.U_532_X().n_1700_B(dynamicIn));
    }

    public E_4668_a<S_3014_o> y_1945_D() {
        return super.y_1945_D();
    }

    @Override
    public boolean u_1723_Y() {
        return false;
    }

    @Override
    public boolean t_148_a(Z_1993_T p_230293_1_) {
        return p_230293_1_.J_1907_R() == Items.u_4724_w ? super.t_148_a(p_230293_1_) : false;
    }

    @Override
    protected void X_933_l() {
        this.O_508_d.D_4792_h().n_1700_B("piglinBruteBrain");
        this.y_1945_D().n_1700_B((e_3591_l)this.O_508_d, this);
        this.O_508_d.D_4792_h().R_4764_Y();
        Q_3304_A.J_1907_R(this);
        Q_3304_A.R_4764_Y(this);
        super.X_933_l();
    }

    @Override
    public q_2464_b J_3635_s() {
        return this.P_2272_O() && this.o_82_k() ? q_2464_b.n_1700_B : q_2464_b.u_1723_Y;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        boolean flag = super.n_1700_B(source, amount);
        if (this.O_508_d.Y_259_p) {
            return false;
        }
        if (flag && source.u_2550_I() instanceof r_4811_B) {
            Q_3304_A.n_1700_B(this, (r_4811_B)source.u_2550_I());
        }
        return flag;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.S_4325_V;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.F_747_P;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.R_2329_T;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.T_1170_t, 0.15f, 1.0f);
    }

    protected void P_2295_B() {
        this.n_1700_B(SoundEvents.f_800_j, 1.0f, this.O_2761_o());
    }

    @Override
    protected void h_973_D() {
        this.n_1700_B(SoundEvents.k_2282_P, 1.0f, this.O_2761_o());
    }
}


