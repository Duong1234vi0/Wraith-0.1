/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.function.Predicate;
import lightning.product.B_4088_l;
import lightning.product.C_4114_x;
import lightning.product.I_1170_F;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.MobEffects;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.U_2912_j;
import lightning.product.AbstractFish;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.MobType;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2610_C;
import lightning.product.Goal;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public class x_742_i
extends AbstractFish {
    private static final h_256_u<Integer> n_1700_B = C_4114_x.n_1700_B(x_742_i.class, EntityDataSerializers.J_1907_R);
    private int J_1907_R;
    private int R_4764_Y;
    private static final Predicate<r_4811_B> h_1847_R = p_210139_0_ -> {
        if (p_210139_0_ == null) {
            return false;
        }
        if (!(p_210139_0_ instanceof a_3913_L) || !p_210139_0_.d_2461_k() && !((a_3913_L)p_210139_0_).G_624_v()) {
            return p_210139_0_.F_2860_q() != MobType.P_1922_E;
        }
        return false;
    };

    public x_742_i(t_5_h<? extends x_742_i> p_i50248_1_, b_4507_u p_i50248_2_) {
        super((t_5_h<? extends AbstractFish>)p_i50248_1_, p_i50248_2_);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, 0);
    }

    public int y_2447_C() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    public void J_1907_R(int p_203714_1_) {
        this.l_4537_E.J_1907_R(n_1700_B, p_203714_1_);
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (n_1700_B.equals(key)) {
            this.g_();
        }
        super.n_1700_B(key);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("PuffState", this.y_2447_C());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.J_1907_R(compound.w_1484_f("PuffState"));
    }

    @Override
    protected Z_1993_T y_4642_Y() {
        return new Z_1993_T(Items.F_2052_z);
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(1, new n_1700_B(this));
    }

    @Override
    public void v_() {
        if (!this.O_508_d.Y_259_p && this.RealmsLongRunningMcoTaskScreen() && this.w_1457_N()) {
            if (this.J_1907_R > 0) {
                if (this.y_2447_C() == 0) {
                    this.n_1700_B(SoundEvents.y_254_d, this.d_4500_Q(), this.O_2761_o());
                    this.J_1907_R(1);
                } else if (this.J_1907_R > 40 && this.y_2447_C() == 1) {
                    this.n_1700_B(SoundEvents.y_254_d, this.d_4500_Q(), this.O_2761_o());
                    this.J_1907_R(2);
                }
                ++this.J_1907_R;
            } else if (this.y_2447_C() != 0) {
                if (this.R_4764_Y > 60 && this.y_2447_C() == 2) {
                    this.n_1700_B(SoundEvents.m_1628_s, this.d_4500_Q(), this.O_2761_o());
                    this.J_1907_R(1);
                } else if (this.R_4764_Y > 100 && this.y_2447_C() == 1) {
                    this.n_1700_B(SoundEvents.m_1628_s, this.d_4500_Q(), this.O_2761_o());
                    this.J_1907_R(0);
                }
                ++this.R_4764_Y;
            }
        }
        super.v_();
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (this.RealmsLongRunningMcoTaskScreen() && this.y_2447_C() > 0) {
            for (Z_530_i z_530_i : this.O_508_d.n_1700_B(Z_530_i.class, this.i_601_W().grow(0.3), h_1847_R)) {
                if (!z_530_i.RealmsLongRunningMcoTaskScreen()) continue;
                this.n_1700_B(z_530_i);
            }
        }
    }

    private void n_1700_B(Z_530_i p_205719_1_) {
        int i = this.y_2447_C();
        if (p_205719_1_.n_1700_B(P_11_z.R_4764_Y(this), (float)(1 + i))) {
            p_205719_1_.n_1700_B(new k_2610_C(MobEffects.w_1457_N, 60 * i, 0));
            this.n_1700_B(SoundEvents.W_3801_h, 1.0f, 1.0f);
        }
    }

    @Override
    public void c_(a_3913_L entityIn) {
        int i = this.y_2447_C();
        if (entityIn instanceof B_4088_l && i > 0 && entityIn.n_1700_B(P_11_z.R_4764_Y(this), (float)(1 + i))) {
            if (!this.y_1700_S()) {
                ((B_4088_l)entityIn).n_1700_B.n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.s_956_w, 0.0f));
            }
            entityIn.n_1700_B(new k_2610_C(MobEffects.w_1457_N, 60 * i, 0));
        }
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.i_770_g;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.H_2506_c;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.j_1376_w;
    }

    @Override
    protected SoundEvent V_1176_p() {
        return SoundEvents.F_2052_z;
    }

    @Override
    public R_1815_U n_1700_B(I_1170_F poseIn) {
        return super.n_1700_B(poseIn).n_1700_B(x_742_i.w_1457_N(this.y_2447_C()));
    }

    private static float w_1457_N(int p_213806_0_) {
        switch (p_213806_0_) {
            case 0: {
                return 0.5f;
            }
            case 1: {
                return 0.7f;
            }
        }
        return 1.0f;
    }

    static class n_1700_B
    extends Goal {
        private final x_742_i n_1700_B;

        public n_1700_B(x_742_i fish) {
            this.n_1700_B = fish;
        }

        @Override
        public boolean n_1700_B() {
            List<r_4811_B> list = this.n_1700_B.O_508_d.n_1700_B(r_4811_B.class, this.n_1700_B.i_601_W().grow(2.0), h_1847_R);
            return !list.isEmpty();
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B.J_1907_R = 1;
            this.n_1700_B.R_4764_Y = 0;
        }

        @Override
        public void G_564_y() {
            this.n_1700_B.J_1907_R = 0;
        }

        @Override
        public boolean J_1907_R() {
            List<r_4811_B> list = this.n_1700_B.O_508_d.n_1700_B(r_4811_B.class, this.n_1700_B.i_601_W().grow(2.0), h_1847_R);
            return !list.isEmpty();
        }
    }
}


