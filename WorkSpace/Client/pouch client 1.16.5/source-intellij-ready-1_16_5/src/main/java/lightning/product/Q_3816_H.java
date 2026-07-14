/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.RandomStrollGoal;
import lightning.product.G_3246_f;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.J_2548_M;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.ResetUniversalAngerTargetGoal;
import lightning.product.FloatGoal;
import lightning.product.biomeBiomes;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
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
import lightning.product.e_3591_l;
import lightning.product.TimeUtil;
import lightning.product.f_2392_k;
import lightning.product.g_1253_u;
import lightning.product.g_3408_G;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.k_594_Q;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.v_2621_q;

public class Q_3816_H
extends Animal
implements G_3246_f {
    private static final h_256_u<Boolean> h_1847_R = C_4114_x.n_1700_B(Q_3816_H.class, EntityDataSerializers.t_148_a);
    private float Q_4569_t;
    private float M_182_A;
    private int t_1786_h;
    private static final J_2548_M multiplayerClientSuggestionProvider = TimeUtil.n_1700_B(20, 39);
    private int w_1457_N;
    private UUID Y_601_j;

    public Q_3816_H(t_5_h<? extends Q_3816_H> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
    }

    @Override
    public AgableMob n_1700_B(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.RealmsClientConfig.n_1700_B(p_241840_1_);
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return false;
    }

    @Override
    protected void M_182_A() {
        super.M_182_A();
        this.s_956_w.n_1700_B(0, new FloatGoal(this));
        this.s_956_w.n_1700_B(1, new R_4764_Y());
        this.s_956_w.n_1700_B(1, new G_564_y());
        this.s_956_w.n_1700_B(4, new v_2621_q(this, 1.25));
        this.s_956_w.n_1700_B(5, new RandomStrollGoal(this, 1.0));
        this.s_956_w.n_1700_B(6, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(7, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new J_1907_R());
        this.u_2550_I.n_1700_B(2, new n_1700_B());
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<a_3913_L>(this, a_3913_L.class, 10, true, false, this::c_));
        this.u_2550_I.n_1700_B(4, new NearestAttackableTargetGoal<g_1253_u>(this, g_1253_u.class, 10, true, true, null));
        this.u_2550_I.n_1700_B(5, new ResetUniversalAngerTargetGoal<Q_3816_H>(this, false));
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 30.0).n_1700_B(Attributes.J_1907_R, 20.0).n_1700_B(Attributes.G_564_y, 0.25).n_1700_B(Attributes.u_1723_Y, 6.0);
    }

    public static boolean J_1907_R(t_5_h<Q_3816_H> p_223320_0_, LevelAccessor p_223320_1_, a_3160_D reason, c_1514_x p_223320_3_, Random p_223320_4_) {
        Optional<f_2392_k<k_594_Q>> optional = p_223320_1_.n_1700_B(p_223320_3_);
        if (!Objects.equals(optional, Optional.of(biomeBiomes.u_2550_I)) && !Objects.equals(optional, Optional.of(biomeBiomes.c_4037_x))) {
            return Q_3816_H.R_4764_Y(p_223320_0_, p_223320_1_, reason, p_223320_3_, p_223320_4_);
        }
        return p_223320_1_.n_1700_B(p_223320_3_, 0) > 8 && p_223320_1_.getBlockState(p_223320_3_.down()).n_1700_B(a_3742_W.O_1795_e);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.n_1700_B((e_3591_l)this.O_508_d, compound);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        this.a_(compound);
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B(multiplayerClientSuggestionProvider.n_1700_B(this.RealmsWorldOptions));
    }

    @Override
    public void n_1700_B(int time) {
        this.w_1457_N = time;
    }

    @Override
    public int n_1700_B() {
        return this.w_1457_N;
    }

    @Override
    public void n_1700_B(@Nullable UUID target) {
        this.Y_601_j = target;
    }

    @Override
    public UUID G_564_y() {
        return this.Y_601_j;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return this.d_() ? SoundEvents.H_3529_d : SoundEvents.t_4562_T;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.G_1539_D;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.U_3005_m;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.W_2770_z, 0.15f, 1.0f);
    }

    protected void h_1640_b() {
        if (this.t_1786_h <= 0) {
            this.n_1700_B(SoundEvents.u_1934_K, 1.0f, this.O_2761_o());
            this.t_1786_h = 40;
        }
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(h_1847_R, false);
    }

    @Override
    public void v_() {
        super.v_();
        if (this.O_508_d.Y_259_p) {
            if (this.M_182_A != this.Q_4569_t) {
                this.g_();
            }
            this.Q_4569_t = this.M_182_A;
            this.M_182_A = this.V_1176_p() ? u_530_F.n_1700_B(this.M_182_A + 1.0f, 0.0f, 6.0f) : u_530_F.n_1700_B(this.M_182_A - 1.0f, 0.0f, 6.0f);
        }
        if (this.t_1786_h > 0) {
            --this.t_1786_h;
        }
        if (!this.O_508_d.Y_259_p) {
            this.n_1700_B((e_3591_l)this.O_508_d, true);
        }
    }

    @Override
    public R_1815_U n_1700_B(I_1170_F poseIn) {
        if (this.M_182_A > 0.0f) {
            float f = this.M_182_A / 6.0f;
            float f1 = 1.0f + f;
            return super.n_1700_B(poseIn).n_1700_B(1.0f, f1);
        }
        return super.n_1700_B(poseIn);
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        boolean flag = entityIn.n_1700_B(P_11_z.R_4764_Y(this), (float)((int)this.J_1907_R(Attributes.u_1723_Y)));
        if (flag) {
            this.n_1700_B((r_4811_B)this, entityIn);
        }
        return flag;
    }

    public boolean V_1176_p() {
        return this.l_4537_E.n_1700_B(h_1847_R);
    }

    public void w_1457_N(boolean standing) {
        this.l_4537_E.J_1907_R(h_1847_R, standing);
    }

    public float c_3005_b(float p_189795_1_) {
        return u_530_F.v_4262_N(p_189795_1_, this.Q_4569_t, this.M_182_A) / 6.0f;
    }

    @Override
    protected float M_2562_s() {
        return 0.98f;
    }

    @Override
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        if (spawnDataIn == null) {
            spawnDataIn = new AgableMob.n_1700_B(1.0f);
        }
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    class R_4764_Y
    extends b_4953_N {
        public R_4764_Y() {
            super(Q_3816_H.this, 1.25, true);
        }

        @Override
        protected void n_1700_B(r_4811_B enemy, double distToEnemySqr) {
            double d0 = this.n_1700_B(enemy);
            if (distToEnemySqr <= d0 && this.w_1484_f()) {
                this.v_4262_N();
                this.n_1700_B.q_2307_F(enemy);
                Q_3816_H.this.w_1457_N(false);
            } else if (distToEnemySqr <= d0 * 2.0) {
                if (this.w_1484_f()) {
                    Q_3816_H.this.w_1457_N(false);
                    this.v_4262_N();
                }
                if (this.s_956_w() <= 10) {
                    Q_3816_H.this.w_1457_N(true);
                    Q_3816_H.this.h_1640_b();
                }
            } else {
                this.v_4262_N();
                Q_3816_H.this.w_1457_N(false);
            }
        }

        @Override
        public void G_564_y() {
            Q_3816_H.this.w_1457_N(false);
            super.G_564_y();
        }

        @Override
        protected double n_1700_B(r_4811_B attackTarget) {
            return 4.0f + attackTarget.C_415_h();
        }
    }

    class G_564_y
    extends PanicGoal {
        public G_564_y() {
            super(Q_3816_H.this, 2.0);
        }

        @Override
        public boolean n_1700_B() {
            return !Q_3816_H.this.d_() && !Q_3816_H.this.RealmsPersistence() ? false : super.n_1700_B();
        }
    }

    class J_1907_R
    extends g_3408_G {
        public J_1907_R() {
            super(Q_3816_H.this, new Class[0]);
        }

        @Override
        public void R_4764_Y() {
            super.R_4764_Y();
            if (Q_3816_H.this.d_()) {
                this.v_4262_N();
                this.G_564_y();
            }
        }

        @Override
        protected void n_1700_B(Z_530_i mobIn, r_4811_B targetIn) {
            if (mobIn instanceof Q_3816_H && !mobIn.d_()) {
                super.n_1700_B(mobIn, targetIn);
            }
        }
    }

    class n_1700_B
    extends NearestAttackableTargetGoal<a_3913_L> {
        public n_1700_B() {
            super(Q_3816_H.this, a_3913_L.class, 20, true, true, null);
        }

        @Override
        public boolean n_1700_B() {
            if (Q_3816_H.this.d_()) {
                return false;
            }
            if (super.n_1700_B()) {
                for (Q_3816_H polarbearentity : Q_3816_H.this.O_508_d.n_1700_B(Q_3816_H.class, Q_3816_H.this.i_601_W().grow(8.0, 4.0, 8.0))) {
                    if (!polarbearentity.d_()) continue;
                    return true;
                }
            }
            return false;
        }

        @Override
        protected double u_2550_I() {
            return super.u_2550_I() * 0.5;
        }
    }
}


