/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import javax.annotation.Nullable;
import lightning.product.C_4816_K;
import lightning.product.AgableMob;
import lightning.product.D_3833_N;
import lightning.product.Pillager;
import lightning.product.F_4355_q;
import lightning.product.PanicGoal;
import lightning.product.L_1875_m;
import lightning.product.O_240_F;
import lightning.product.P_11_z;
import lightning.product.AvoidEntityGoal;
import lightning.product.Potions;
import lightning.product.UseItemGoal;
import lightning.product.FloatGoal;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.e_3714_r;
import lightning.product.MoveTowardsRestrictionGoal;
import lightning.product.g_1941_L;
import lightning.product.g_4621_i;
import lightning.product.i_1663_p;
import lightning.product.MerchantOffers;
import lightning.product.VillagerTrades;
import lightning.product.m_3054_I;
import lightning.product.n_3832_I;
import lightning.product.n_4637_L;
import lightning.product.Goal;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.t_5_h;
import lightning.product.u_1458_e;
import lightning.product.LookAtPlayerGoal;
import lightning.product.MerchantOffer;
import lightning.product.x_1688_C;
import lightning.product.y_2798_W;
import lightning.product.TradeWithPlayerGoal;

public class T_426_Y
extends g_4621_i {
    @Nullable
    private c_1514_x Q_4569_t;
    private int M_182_A;

    public T_426_Y(t_5_h<? extends T_426_Y> type, b_4507_u worldIn) {
        super((t_5_h<? extends g_4621_i>)type, worldIn);
        this.z_1333_t = true;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(0, new UseItemGoal<T_426_Y>(this, L_1875_m.n_1700_B(new Z_1993_T(Items.j_2461_G), Potions.w_1484_f), SoundEvents.N_81_X, trader -> this.O_508_d.z_4693_k() && !trader.F_3572_x()));
        this.s_956_w.n_1700_B(0, new UseItemGoal<T_426_Y>(this, new Z_1993_T(Items.H_2506_c), SoundEvents.v_448_E, trader -> this.O_508_d.q_4610_l() && trader.F_3572_x()));
        this.s_956_w.n_1700_B(1, new TradeWithPlayerGoal(this));
        this.s_956_w.n_1700_B(1, new AvoidEntityGoal<F_4355_q>(this, F_4355_q.class, 8.0f, 0.5, 0.5));
        this.s_956_w.n_1700_B(1, new AvoidEntityGoal<e_3714_r>(this, e_3714_r.class, 12.0f, 0.5, 0.5));
        this.s_956_w.n_1700_B(1, new AvoidEntityGoal<i_1663_p>(this, i_1663_p.class, 8.0f, 0.5, 0.5));
        this.s_956_w.n_1700_B(1, new AvoidEntityGoal<D_3833_N>(this, D_3833_N.class, 8.0f, 0.5, 0.5));
        this.s_956_w.n_1700_B(1, new AvoidEntityGoal<Pillager>(this, Pillager.class, 15.0f, 0.5, 0.5));
        this.s_956_w.n_1700_B(1, new AvoidEntityGoal<y_2798_W>(this, y_2798_W.class, 12.0f, 0.5, 0.5));
        this.s_956_w.n_1700_B(1, new AvoidEntityGoal<C_4816_K>(this, C_4816_K.class, 10.0f, 0.5, 0.5));
        this.s_956_w.n_1700_B(1, new PanicGoal(this, 0.5));
        this.s_956_w.n_1700_B(1, new O_240_F(this));
        this.s_956_w.n_1700_B(2, new n_1700_B(this, 2.0, 0.35));
        this.s_956_w.n_1700_B(4, new MoveTowardsRestrictionGoal(this, 0.35));
        this.s_956_w.n_1700_B(8, new g_1941_L(this, 0.35));
        this.s_956_w.n_1700_B(9, new u_1458_e(this, a_3913_L.class, 3.0f, 1.0f));
        this.s_956_w.n_1700_B(10, new LookAtPlayerGoal(this, Z_530_i.class, 8.0f));
    }

    @Override
    @Nullable
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return null;
    }

    @Override
    public boolean P_1922_E() {
        return false;
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (itemstack.J_1907_R() != Items.CocoaBlock && this.RealmsLongRunningMcoTaskScreen() && !this.h_1640_b() && !this.d_()) {
            if (p_230254_2_ == x_1688_C.n_1700_B) {
                p_230254_1_.J_1907_R(Stats.e_2887_G);
            }
            if (this.J_1907_R().isEmpty()) {
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
            if (!this.O_508_d.Y_259_p) {
                this.n_1700_B(p_230254_1_);
                this.n_1700_B(p_230254_1_, this.c_(), 1);
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    @Override
    protected void o_82_k() {
        VillagerTrades.v_4262_N[] avillagertrades$itrade = (VillagerTrades.v_4262_N[])VillagerTrades.J_1907_R.get(1);
        VillagerTrades.v_4262_N[] avillagertrades$itrade1 = (VillagerTrades.v_4262_N[])VillagerTrades.J_1907_R.get(2);
        if (avillagertrades$itrade != null && avillagertrades$itrade1 != null) {
            MerchantOffers merchantoffers = this.J_1907_R();
            this.n_1700_B(merchantoffers, avillagertrades$itrade, 5);
            int i = this.RealmsWorldOptions.nextInt(avillagertrades$itrade1.length);
            VillagerTrades.v_4262_N villagertrades$itrade = avillagertrades$itrade1[i];
            MerchantOffer merchantoffer = villagertrades$itrade.n_1700_B(this, this.RealmsWorldOptions);
            if (merchantoffer != null) {
                merchantoffers.add(merchantoffer);
            }
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("DespawnDelay", this.M_182_A);
        if (this.Q_4569_t != null) {
            compound.n_1700_B("WanderTarget", n_3832_I.n_1700_B(this.Q_4569_t));
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        if (compound.R_4764_Y("DespawnDelay", 99)) {
            this.M_182_A = compound.w_1484_f("DespawnDelay");
        }
        if (compound.P_1922_E("WanderTarget")) {
            this.Q_4569_t = n_3832_I.J_1907_R(compound.M_182_A("WanderTarget"));
        }
        this.b_(Math.max(0, this.x_()));
    }

    @Override
    public boolean w_1484_f(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected void J_1907_R(MerchantOffer offer) {
        if (offer.w_1457_N()) {
            int i = 3 + this.RealmsWorldOptions.nextInt(4);
            this.O_508_d.a_(new n_4637_L(this.O_508_d, this.O_3598_v(), this.X_2960_b() + 0.5, this.l_2647_k(), i));
        }
    }

    @Override
    protected SoundEvent z_4693_k() {
        return this.h_1640_b() ? SoundEvents.L_2801_l : SoundEvents.h_4152_b;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.Z_4149_q;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.M_1398_d;
    }

    @Override
    protected SoundEvent R_4764_Y(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        return item == Items.H_2506_c ? SoundEvents.NetherVines : SoundEvents.f_1186_l;
    }

    @Override
    protected SoundEvent w_1457_N(boolean getYesSound) {
        return getYesSound ? SoundEvents.PumpkinBlock : SoundEvents.q_3398_T;
    }

    @Override
    public SoundEvent u_1723_Y() {
        return SoundEvents.PumpkinBlock;
    }

    public void Y_601_j(int delay) {
        this.M_182_A = delay;
    }

    public int h_973_D() {
        return this.M_182_A;
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (!this.O_508_d.Y_259_p) {
            this.f_2787_O();
        }
    }

    private void f_2787_O() {
        if (this.M_182_A > 0 && !this.h_1640_b() && --this.M_182_A == 0) {
            this.Ops();
        }
    }

    public void v_4262_N(@Nullable c_1514_x pos) {
        this.Q_4569_t = pos;
    }

    @Nullable
    private c_1514_x P_2295_B() {
        return this.Q_4569_t;
    }

    class n_1700_B
    extends Goal {
        final T_426_Y n_1700_B;
        final double J_1907_R;
        final double R_4764_Y;

        n_1700_B(T_426_Y traderEntityIn, double distanceIn, double speedIn) {
            this.n_1700_B = traderEntityIn;
            this.J_1907_R = distanceIn;
            this.R_4764_Y = speedIn;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public void G_564_y() {
            this.n_1700_B.v_4262_N((c_1514_x)null);
            T_426_Y.this.t_148_a.h_1847_R();
        }

        @Override
        public boolean n_1700_B() {
            c_1514_x blockpos = this.n_1700_B.P_2295_B();
            return blockpos != null && this.n_1700_B(blockpos, this.J_1907_R);
        }

        @Override
        public void P_1922_E() {
            c_1514_x blockpos = this.n_1700_B.P_2295_B();
            if (blockpos != null && T_426_Y.this.t_148_a.M_588_G()) {
                if (this.n_1700_B(blockpos, 10.0)) {
                    e_2866_D vector3d = new e_2866_D((double)blockpos.getX() - this.n_1700_B.O_3598_v(), (double)blockpos.getY() - this.n_1700_B.X_2960_b(), (double)blockpos.getZ() - this.n_1700_B.l_2647_k()).G_564_y();
                    e_2866_D vector3d1 = vector3d.n_1700_B(10.0).J_1907_R(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k());
                    T_426_Y.this.t_148_a.n_1700_B(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, this.R_4764_Y);
                } else {
                    T_426_Y.this.t_148_a.n_1700_B((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), this.R_4764_Y);
                }
            }
        }

        private boolean n_1700_B(c_1514_x pos, double distance) {
            return !pos.withinDistance(this.n_1700_B.s_4990_V(), distance);
        }
    }
}


