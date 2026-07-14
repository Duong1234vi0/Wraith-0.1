/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.D_2364_U;
import lightning.product.RandomStrollGoal;
import lightning.product.Attributes;
import lightning.product.K_4096_w;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_2450_T;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.AbstractIllager;
import lightning.product.W_4304_a;
import lightning.product.SoundEvent;
import lightning.product.GoalUtils;
import lightning.product.X_1275_n;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_3129_s;
import lightning.product.BreakDoorGoal;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.MobType;
import lightning.product.g_3408_G;
import lightning.product.g_4621_i;
import lightning.product.i_2099_H;
import lightning.product.Monster;
import lightning.product.Goal;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_282_a;

public class i_1663_p
extends AbstractIllager {
    private static final Predicate<R_2450_T> R_4764_Y = p_213678_0_ -> p_213678_0_ == R_2450_T.R_4764_Y || p_213678_0_ == R_2450_T.G_564_y;
    private boolean h_1847_R;

    public i_1663_p(t_5_h<? extends i_1663_p> p_i50189_1_, b_4507_u p_i50189_2_) {
        super((t_5_h<? extends AbstractIllager>)p_i50189_1_, p_i50189_2_);
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new J_1907_R(this));
        this.s_956_w.n_1700_B(2, new AbstractIllager.J_1907_R(this));
        this.s_956_w.n_1700_B(3, new W_4304_a.J_1907_R(this, this, 10.0f));
        this.s_956_w.n_1700_B(4, new n_1700_B(this, this));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, W_4304_a.class).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<g_4621_i>((Z_530_i)this, g_4621_i.class, true));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<D_2364_U>((Z_530_i)this, D_2364_U.class, true));
        this.u_2550_I.n_1700_B(4, new R_4764_Y(this));
        this.s_956_w.n_1700_B(8, new RandomStrollGoal(this, 0.6));
        this.s_956_w.n_1700_B(9, new LookAtPlayerGoal(this, a_3913_L.class, 3.0f, 1.0f));
        this.s_956_w.n_1700_B(10, new LookAtPlayerGoal(this, Z_530_i.class, 8.0f));
    }

    @Override
    protected void X_933_l() {
        if (!this.n_473_l() && GoalUtils.n_1700_B(this)) {
            boolean flag = ((e_3591_l)this.O_508_d).c_3005_b(this.b_2312_j());
            ((i_2099_H)this.e_4240_b()).n_1700_B(flag);
        }
        super.X_933_l();
    }

    public static s_1415_m.n_1700_B U_1697_c() {
        return Monster.o_4117_e().n_1700_B(Attributes.G_564_y, 0.35f).n_1700_B(Attributes.J_1907_R, 12.0).n_1700_B(Attributes.n_1700_B, 24.0).n_1700_B(Attributes.u_1723_Y, 5.0);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.h_1847_R) {
            compound.n_1700_B("Johnny", true);
        }
    }

    @Override
    public AbstractIllager.n_1700_B u_1723_Y() {
        if (this.P_2272_O()) {
            return AbstractIllager.n_1700_B.J_1907_R;
        }
        return this.h_973_D() ? AbstractIllager.n_1700_B.v_4262_N : AbstractIllager.n_1700_B.n_1700_B;
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("Johnny", 99)) {
            this.h_1847_R = compound.t_1786_h("Johnny");
        }
    }

    @Override
    public SoundEvent P_2295_B() {
        return SoundEvents.NoteBlock;
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        V_3157_k ilivingentitydata = super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        ((i_2099_H)this.e_4240_b()).n_1700_B(true);
        this.n_1700_B(difficultyIn);
        this.J_1907_R(difficultyIn);
        return ilivingentitydata;
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        if (this.y_2447_C() == null) {
            this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.E_390_U));
        }
    }

    @Override
    public boolean Q_4569_t(N_4263_v entityIn) {
        if (super.Q_4569_t(entityIn)) {
            return true;
        }
        if (entityIn instanceof r_4811_B && ((r_4811_B)entityIn).F_2860_q() == MobType.G_564_y) {
            return this.L_1362_X() == null && entityIn.L_1362_X() == null;
        }
        return false;
    }

    @Override
    public void n_1700_B(@Nullable x_282_a name) {
        super.n_1700_B(name);
        if (!this.h_1847_R && name != null && name.getString().equals("Johnny")) {
            this.h_1847_R = true;
        }
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.NetherrackBlock;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.NyliumBlock;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.ObserverBlock;
    }

    @Override
    public void n_1700_B(int wave, boolean p_213660_2_) {
        boolean flag;
        Z_1993_T itemstack = new Z_1993_T(Items.E_390_U);
        b_3129_s raid = this.y_2447_C();
        int i = 1;
        if (wave > raid.n_1700_B(R_2450_T.R_4764_Y)) {
            i = 2;
        }
        boolean bl = flag = this.RealmsWorldOptions.nextFloat() <= raid.Y_259_p();
        if (flag) {
            HashMap map = Maps.newHashMap();
            map.put(Enchantments.P_4830_p, i);
            K_4096_w.n_1700_B(map, itemstack);
        }
        this.n_1700_B(e_1174_E.n_1700_B, itemstack);
    }

    static class J_1907_R
    extends BreakDoorGoal {
        public J_1907_R(Z_530_i p_i50578_1_) {
            super(p_i50578_1_, 6, R_4764_Y);
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean J_1907_R() {
            i_1663_p vindicatorentity = (i_1663_p)this.G_564_y;
            return vindicatorentity.J_3635_s() && super.J_1907_R();
        }

        @Override
        public boolean n_1700_B() {
            i_1663_p vindicatorentity = (i_1663_p)this.G_564_y;
            return vindicatorentity.J_3635_s() && vindicatorentity.RealmsWorldOptions.nextInt(10) == 0 && super.n_1700_B();
        }

        @Override
        public void R_4764_Y() {
            super.R_4764_Y();
            this.G_564_y.u_2550_I(0);
        }
    }

    class n_1700_B
    extends b_4953_N {
        public n_1700_B(i_1663_p this$0, i_1663_p p_i50577_2_) {
            super(p_i50577_2_, 1.0, false);
        }

        @Override
        protected double n_1700_B(r_4811_B attackTarget) {
            if (this.n_1700_B.l_3609_d() instanceof X_1275_n) {
                float f = this.n_1700_B.l_3609_d().C_415_h() - 0.1f;
                return f * 2.0f * f * 2.0f + attackTarget.C_415_h();
            }
            return super.n_1700_B(attackTarget);
        }
    }

    static class R_4764_Y
    extends NearestAttackableTargetGoal<r_4811_B> {
        public R_4764_Y(i_1663_p vindicator) {
            super(vindicator, r_4811_B.class, 0, true, true, r_4811_B::r_4790_y);
        }

        @Override
        public boolean n_1700_B() {
            return ((i_1663_p)this.P_1922_E).h_1847_R && super.n_1700_B();
        }

        @Override
        public void R_4764_Y() {
            super.R_4764_Y();
            this.P_1922_E.u_2550_I(0);
        }
    }
}


