/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.D_38_f;
import lightning.product.G_652_w;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.Animal;
import lightning.product.Saddleable;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.TemptGoal;
import lightning.product.R_2450_T;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_1941_L;
import lightning.product.h_256_u;
import lightning.product.ZombifiedPiglin;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.PathfinderMob;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.LightningBolt;
import lightning.product.t_5_h;
import lightning.product.LookAtPlayerGoal;
import lightning.product.v_2621_q;
import lightning.product.ItemSteerable;
import lightning.product.x_1688_C;
import lightning.product.ItemBasedSteering;

public class B_4271_P
extends Animal
implements Saddleable,
ItemSteerable {
    private static final h_256_u<Boolean> h_1847_R = C_4114_x.n_1700_B(B_4271_P.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Integer> Q_4569_t = C_4114_x.n_1700_B(B_4271_P.class, EntityDataSerializers.J_1907_R);
    private static final b_3278_X M_182_A = b_3278_X.n_1700_B(Items.BaseCoralWallFanBlock, Items.l_683_e, Items.s_3401_U);
    private final ItemBasedSteering t_1786_h;

    public B_4271_P(t_5_h<? extends B_4271_P> p_i50250_1_, b_4507_u p_i50250_2_) {
        super((t_5_h<? extends Animal>)p_i50250_1_, p_i50250_2_);
        this.t_1786_h = new ItemBasedSteering(this.l_4537_E, Q_4569_t, h_1847_R);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new PanicGoal(this, 1.25));
        this.s_956_w.n_1700_B(3, new BreedGoal(this, 1.0));
        this.s_956_w.n_1700_B(4, new TemptGoal((PathfinderMob)this, 1.2, b_3278_X.n_1700_B(Items.EndRodBlock), false));
        this.s_956_w.n_1700_B(4, new TemptGoal((PathfinderMob)this, 1.2, false, M_182_A));
        this.s_956_w.n_1700_B(5, new v_2621_q(this, 1.1));
        this.s_956_w.n_1700_B(6, new g_1941_L(this, 1.0));
        this.s_956_w.n_1700_B(7, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 10.0).n_1700_B(Attributes.G_564_y, 0.25);
    }

    @Override
    @Nullable
    public N_4263_v n_3864_h() {
        return this.o_3599_Z().isEmpty() ? null : this.o_3599_Z().get(0);
    }

    @Override
    public boolean g_2268_R() {
        N_4263_v entity = this.n_3864_h();
        if (!(entity instanceof a_3913_L)) {
            return false;
        }
        a_3913_L playerentity = (a_3913_L)entity;
        return playerentity.A_2714_y().J_1907_R() == Items.EndRodBlock || playerentity.S_4035_N().J_1907_R() == Items.EndRodBlock;
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (Q_4569_t.equals(key) && this.O_508_d.Y_259_p) {
            this.t_1786_h.n_1700_B();
        }
        super.n_1700_B(key);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(h_1847_R, false);
        this.l_4537_E.n_1700_B(Q_4569_t, 0);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        this.t_1786_h.n_1700_B(compound);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.t_1786_h.J_1907_R(compound);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.O_922_L;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.A_4514_U;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.D_940_S;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.MinecraftAccess, 0.15f, 1.0f);
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        boolean flag = this.u_2550_I(p_230254_1_.R_4764_Y(p_230254_2_));
        if (!flag && this.G_564_y() && !this.H_1883_T() && !p_230254_1_.z_3000_g()) {
            if (!this.O_508_d.Y_259_p) {
                p_230254_1_.s_956_w(this);
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        m_3054_I actionresulttype = super.J_1907_R(p_230254_1_, p_230254_2_);
        if (!actionresulttype.n_1700_B()) {
            Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
            return itemstack.J_1907_R() == Items.Z_361_l ? itemstack.n_1700_B(p_230254_1_, (r_4811_B)this, p_230254_2_) : m_3054_I.R_4764_Y;
        }
        return actionresulttype;
    }

    @Override
    public boolean n_1700_B() {
        return this.RealmsLongRunningMcoTaskScreen() && !this.d_();
    }

    @Override
    protected void A_229_v() {
        super.A_229_v();
        if (this.G_564_y()) {
            this.n_1700_B((q_1803_e)Items.Z_361_l);
        }
    }

    @Override
    public boolean G_564_y() {
        return this.t_1786_h.J_1907_R();
    }

    @Override
    public void n_1700_B(@Nullable D_38_f p_230266_1_) {
        this.t_1786_h.n_1700_B(true);
        if (p_230266_1_ != null) {
            this.O_508_d.n_1700_B((a_3913_L)null, this, SoundEvents.S_4088_D, p_230266_1_, 0.5f, 1.0f);
        }
    }

    @Override
    public e_2866_D b_(r_4811_B livingEntity) {
        b_257_Y direction = this.d_2545_n();
        if (direction.h_1847_R() == b_257_Y.n_1700_B.J_1907_R) {
            return super.b_(livingEntity);
        }
        int[][] aint = G_652_w.n_1700_B(direction);
        c_1514_x blockpos = this.b_2312_j();
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (I_1170_F pose : livingEntity.x_2635_q()) {
            I_4817_s axisalignedbb = livingEntity.u_1723_Y(pose);
            for (int[] aint1 : aint) {
                e_2866_D vector3d;
                blockpos$mutable.n_1700_B(blockpos.getX() + aint1[0], blockpos.getY(), blockpos.getZ() + aint1[1]);
                double d0 = this.O_508_d.G_564_y(blockpos$mutable);
                if (!G_652_w.n_1700_B(d0) || !G_652_w.n_1700_B(this.O_508_d, livingEntity, axisalignedbb.offset(vector3d = e_2866_D.n_1700_B(blockpos$mutable, d0)))) continue;
                livingEntity.J_1907_R(pose);
                return vector3d;
            }
        }
        return super.b_(livingEntity);
    }

    @Override
    public void n_1700_B(e_3591_l p_241841_1_, LightningBolt p_241841_2_) {
        if (p_241841_1_.x_607_J() != R_2450_T.n_1700_B) {
            ZombifiedPiglin zombifiedpiglinentity = t_5_h.c_132_F.n_1700_B(p_241841_1_);
            zombifiedpiglinentity.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.n_2412_y));
            zombifiedpiglinentity.J_1907_R(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, this.f_4016_n);
            zombifiedpiglinentity.G_564_y(this.n_473_l());
            zombifiedpiglinentity.n_1700_B(this.d_());
            if (this.t_3452_g()) {
                zombifiedpiglinentity.n_1700_B(this.k_2302_P());
                zombifiedpiglinentity.M_182_A(this.V_118_c());
            }
            zombifiedpiglinentity.T_3594_S();
            p_241841_1_.a_(zombifiedpiglinentity);
            this.Ops();
        } else {
            super.n_1700_B(p_241841_1_, p_241841_2_);
        }
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        this.n_1700_B(this, this.t_1786_h, travelVector);
    }

    @Override
    public float u_1723_Y() {
        return (float)this.J_1907_R(Attributes.G_564_y) * 0.225f;
    }

    @Override
    public void n_1700_B(e_2866_D travelVec) {
        super.w_1484_f(travelVec);
    }

    @Override
    public boolean P_1922_E() {
        return this.t_1786_h.n_1700_B(this.M_3508_C());
    }

    public B_4271_P J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.A_1038_p.n_1700_B(p_241840_1_);
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return M_182_A.n_1700_B(stack);
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.6f * this.X_1313_W(), this.C_415_h() * 0.4f);
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }
}



