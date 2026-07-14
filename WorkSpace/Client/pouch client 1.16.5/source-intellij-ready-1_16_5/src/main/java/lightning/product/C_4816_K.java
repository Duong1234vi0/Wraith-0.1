/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lightning.product.DebugPackets;
import lightning.product.C_4114_x;
import lightning.product.SensorType;
import lightning.product.E_1879_e;
import lightning.product.SetWalkTargetFromLookTarget;
import lightning.product.E_4668_a;
import lightning.product.Attributes;
import lightning.product.I_408_V;
import lightning.product.J_2548_M;
import lightning.product.SetWalkTargetFromAttackTargetIfTargetOutOfReach;
import lightning.product.K_4074_S;
import lightning.product.L_1885_c;
import lightning.product.LookAtTargetSink;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.SetEntityLookTarget;
import lightning.product.a_3236_r;
import lightning.product.a_3913_L;
import lightning.product.b_3448_R;
import lightning.product.Sensor;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.MoveToTargetSink;
import lightning.product.Behavior;
import lightning.product.MobType;
import lightning.product.StartAttacking;
import lightning.product.h_256_u;
import lightning.product.Activity;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.MeleeAttack;
import lightning.product.RandomStroll;
import lightning.product.DoNothing;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;
import lightning.product.x_1835_e;
import lightning.product.HoglinBase;
import lightning.product.z_1480_R;

public class C_4816_K
extends Monster
implements x_1835_e,
HoglinBase {
    private static final h_256_u<Boolean> R_4764_Y = C_4114_x.n_1700_B(C_4816_K.class, EntityDataSerializers.t_148_a);
    private int h_1847_R;
    protected static final ImmutableList<? extends SensorType<? extends Sensor<? super C_4816_K>>> n_1700_B = ImmutableList.of(SensorType.R_4764_Y, SensorType.G_564_y);
    protected static final ImmutableList<? extends MemoryModuleType<?>> J_1907_R = ImmutableList.of(MemoryModuleType.v_4262_N, MemoryModuleType.w_1484_f, MemoryModuleType.u_2550_I, MemoryModuleType.M_588_G, MemoryModuleType.h_1847_R, MemoryModuleType.P_4830_p, MemoryModuleType.Y_1740_V, MemoryModuleType.Y_601_j, MemoryModuleType.Q_4569_t, MemoryModuleType.M_182_A);

    public C_4816_K(t_5_h<? extends C_4816_K> p_i231566_1_, b_4507_u p_i231566_2_) {
        super((t_5_h<? extends Monster>)p_i231566_1_, p_i231566_2_);
        this.P_1922_E = 5;
    }

    protected E_4668_a.n_1700_B<C_4816_K> U_532_X() {
        return E_4668_a.n_1700_B(J_1907_R, n_1700_B);
    }

    @Override
    protected E_4668_a<?> n_1700_B(Dynamic<?> dynamicIn) {
        E_4668_a<C_4816_K> brain = this.U_532_X().n_1700_B(dynamicIn);
        C_4816_K.n_1700_B(brain);
        C_4816_K.J_1907_R(brain);
        C_4816_K.R_4764_Y(brain);
        brain.n_1700_B((Set<Activity>)ImmutableSet.of((Object)Activity.n_1700_B));
        brain.J_1907_R(Activity.J_1907_R);
        brain.R_4764_Y();
        return brain;
    }

    private static void n_1700_B(E_4668_a<C_4816_K> p_234328_0_) {
        p_234328_0_.n_1700_B(Activity.n_1700_B, 0, (ImmutableList<Behavior<C_4816_K>>)ImmutableList.of((Object)new LookAtTargetSink(45, 90), (Object)new MoveToTargetSink()));
    }

    private static void J_1907_R(E_4668_a<C_4816_K> p_234329_0_) {
        p_234329_0_.n_1700_B(Activity.J_1907_R, 10, (ImmutableList<Behavior<C_4816_K>>)ImmutableList.of(new StartAttacking<C_4816_K>(C_4816_K::J_3635_s), new z_1480_R<r_4811_B>(new SetEntityLookTarget(8.0f), J_2548_M.n_1700_B(30, 60)), new E_1879_e(ImmutableList.of((Object)Pair.of((Object)new RandomStroll(0.4f), (Object)2), (Object)Pair.of((Object)new SetWalkTargetFromLookTarget(0.4f, 3), (Object)2), (Object)Pair.of((Object)new DoNothing(30, 60), (Object)1)))));
    }

    private static void R_4764_Y(E_4668_a<C_4816_K> p_234330_0_) {
        p_234330_0_.n_1700_B(Activity.u_2550_I, 10, (ImmutableList<Behavior<C_4816_K>>)ImmutableList.of((Object)new SetWalkTargetFromAttackTargetIfTargetOutOfReach(1.0f), new L_1885_c<Z_530_i>(C_4816_K::y_4642_Y, new MeleeAttack(40)), new L_1885_c<Z_530_i>(C_4816_K::d_, new MeleeAttack(15)), new b_3448_R()), MemoryModuleType.Q_4569_t);
    }

    private Optional<? extends r_4811_B> J_3635_s() {
        return this.y_1945_D().R_4764_Y(MemoryModuleType.w_1484_f).orElse((List<r_4811_B>)ImmutableList.of()).stream().filter(C_4816_K::w_1484_f).findFirst();
    }

    private static boolean w_1484_f(r_4811_B p_234337_0_) {
        t_5_h<?> entitytype = p_234337_0_.f_4016_n();
        return entitytype != t_5_h.S_980_j && entitytype != t_5_h.P_4830_p && I_408_V.u_1723_Y.test(p_234337_0_);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(R_4764_Y, false);
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        super.n_1700_B(key);
        if (R_4764_Y.equals(key)) {
            this.g_();
        }
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 40.0).n_1700_B(Attributes.G_564_y, 0.3f).n_1700_B(Attributes.R_4764_Y, 0.6f).n_1700_B(Attributes.v_4262_N, 1.0).n_1700_B(Attributes.u_1723_Y, 6.0);
    }

    public boolean y_4642_Y() {
        return !this.d_();
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        if (!(entityIn instanceof r_4811_B)) {
            return false;
        }
        this.h_1847_R = 10;
        this.O_508_d.n_1700_B((N_4263_v)this, (byte)4);
        this.n_1700_B(SoundEvents.x_2838_H, 1.0f, this.O_2761_o());
        return HoglinBase.n_1700_B(this, (r_4811_B)entityIn);
    }

    @Override
    public boolean G_564_y(a_3913_L player) {
        return !this.n_4915_F();
    }

    @Override
    protected void P_1922_E(r_4811_B entityIn) {
        if (!this.d_()) {
            HoglinBase.J_1907_R(this, entityIn);
        }
    }

    @Override
    public double s_1671_u() {
        return (double)this.v_165_F() - (this.d_() ? 0.2 : 0.15);
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        boolean flag = super.n_1700_B(source, amount);
        if (this.O_508_d.Y_259_p) {
            return false;
        }
        if (flag && source.u_2550_I() instanceof r_4811_B) {
            r_4811_B livingentity = (r_4811_B)source.u_2550_I();
            if (I_408_V.u_1723_Y.test(livingentity) && !a_3236_r.n_1700_B((r_4811_B)this, livingentity, 4.0)) {
                this.t_148_a(livingentity);
            }
            return flag;
        }
        return flag;
    }

    private void t_148_a(r_4811_B p_234338_1_) {
        this.Y_776_s.J_1907_R(MemoryModuleType.Y_1740_V);
        this.Y_776_s.n_1700_B(MemoryModuleType.Q_4569_t, p_234338_1_, 200L);
    }

    public E_4668_a<C_4816_K> y_1945_D() {
        return super.y_1945_D();
    }

    protected void V_1176_p() {
        Activity activity = this.Y_776_s.G_564_y().orElse(null);
        this.Y_776_s.n_1700_B((List<Activity>)ImmutableList.of((Object)Activity.u_2550_I, (Object)Activity.J_1907_R));
        Activity activity1 = this.Y_776_s.G_564_y().orElse(null);
        if (activity1 == Activity.u_2550_I && activity != Activity.u_2550_I) {
            this.y_2447_C();
        }
        this.multiplayerClientSuggestionProvider(this.Y_776_s.n_1700_B(MemoryModuleType.Q_4569_t));
    }

    @Override
    protected void X_933_l() {
        this.O_508_d.D_4792_h().n_1700_B("zoglinBrain");
        this.y_1945_D().n_1700_B((e_3591_l)this.O_508_d, this);
        this.O_508_d.D_4792_h().R_4764_Y();
        this.V_1176_p();
    }

    @Override
    public void n_1700_B(boolean childZombie) {
        this.D_60_a().J_1907_R(R_4764_Y, childZombie);
        if (!this.O_508_d.Y_259_p && childZombie) {
            this.n_1700_B(Attributes.u_1723_Y).n_1700_B(0.5);
        }
    }

    @Override
    public boolean d_() {
        return this.D_60_a().n_1700_B(R_4764_Y);
    }

    @Override
    public void Y_1740_V() {
        if (this.h_1847_R > 0) {
            --this.h_1847_R;
        }
        super.Y_1740_V();
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 4) {
            this.h_1847_R = 10;
            this.n_1700_B(SoundEvents.x_2838_H, 1.0f, this.O_2761_o());
        } else {
            super.n_1700_B(id);
        }
    }

    @Override
    public int h_1640_b() {
        return this.h_1847_R;
    }

    @Override
    protected SoundEvent z_4693_k() {
        if (this.O_508_d.Y_259_p) {
            return null;
        }
        return this.Y_776_s.n_1700_B(MemoryModuleType.Q_4569_t) ? SoundEvents.TorchBlock : SoundEvents.TargetBlock;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.E_1708_F;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.TrappedChestBlock;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.l_311_L, 0.15f, 1.0f);
    }

    protected void y_2447_C() {
        this.n_1700_B(SoundEvents.TorchBlock, 1.0f, this.O_2761_o());
    }

    @Override
    protected void g_164_R() {
        super.g_164_R();
        DebugPackets.n_1700_B(this);
    }

    @Override
    public MobType F_2860_q() {
        return MobType.J_1907_R;
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.d_()) {
            compound.n_1700_B("IsBaby", true);
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.t_1786_h("IsBaby")) {
            this.n_1700_B(true);
        }
    }
}


