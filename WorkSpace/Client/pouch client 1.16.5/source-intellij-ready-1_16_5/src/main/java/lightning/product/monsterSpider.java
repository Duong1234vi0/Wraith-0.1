/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.PathNavigation;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.D_2364_U;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.DifficultyInstance;
import lightning.product.LeapAtTargetGoal;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.R_2450_T;
import lightning.product.FloatGoal;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.g_1941_L;
import lightning.product.MobType;
import lightning.product.g_3408_G;
import lightning.product.g_422_i;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.WallClimberNavigation;
import lightning.product.k_2610_C;
import lightning.product.monsterSkeleton;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.LookAtPlayerGoal;

public class monsterSpider
extends Monster {
    private static final h_256_u<Byte> n_1700_B = C_4114_x.n_1700_B(monsterSpider.class, EntityDataSerializers.n_1700_B);

    public monsterSpider(t_5_h<? extends monsterSpider> type, b_4507_u worldIn) {
        super((t_5_h<? extends Monster>)type, worldIn);
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(1, new FloatGoal(this));
        this.s_956_w.n_1700_B(3, new LeapAtTargetGoal(this, 0.4f));
        this.s_956_w.n_1700_B(4, new n_1700_B(this));
        this.s_956_w.n_1700_B(5, new g_1941_L(this, 0.8));
        this.s_956_w.n_1700_B(6, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(6, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, new Class[0]));
        this.u_2550_I.n_1700_B(2, new R_4764_Y<a_3913_L>(this, a_3913_L.class));
        this.u_2550_I.n_1700_B(3, new R_4764_Y<D_2364_U>(this, D_2364_U.class));
    }

    @Override
    public double s_1671_u() {
        return this.v_165_F() * 0.5f;
    }

    @Override
    protected PathNavigation J_1907_R(b_4507_u worldIn) {
        return new WallClimberNavigation(this, worldIn);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, (byte)0);
    }

    @Override
    public void v_() {
        super.v_();
        if (!this.O_508_d.Y_259_p) {
            this.w_1457_N(this.D_60_a);
        }
    }

    public static s_1415_m.n_1700_B y_4642_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 16.0).n_1700_B(Attributes.G_564_y, 0.3f);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.z_127_w;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.h_479_I;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.K_2390_Z;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.v_3445_Z, 0.15f, 1.0f);
    }

    @Override
    public boolean e_() {
        return this.V_1176_p();
    }

    @Override
    public void n_1700_B(K_4074_S state, e_2866_D motionMultiplierIn) {
        if (!state.n_1700_B(a_3742_W.y_1700_S)) {
            super.n_1700_B(state, motionMultiplierIn);
        }
    }

    @Override
    public MobType F_2860_q() {
        return MobType.R_4764_Y;
    }

    @Override
    public boolean J_1907_R(k_2610_C potioneffectIn) {
        return potioneffectIn.n_1700_B() == MobEffects.w_1457_N ? false : super.J_1907_R(potioneffectIn);
    }

    public boolean V_1176_p() {
        return (this.l_4537_E.n_1700_B(n_1700_B) & 1) != 0;
    }

    public void w_1457_N(boolean climbing) {
        byte b0 = this.l_4537_E.n_1700_B(n_1700_B);
        b0 = climbing ? (byte)(b0 | 1) : (byte)(b0 & 0xFFFFFFFE);
        this.l_4537_E.J_1907_R(n_1700_B, b0);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        g_422_i effect;
        spawnDataIn = super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (worldIn.e_4240_b().nextInt(100) == 0) {
            monsterSkeleton skeletonentity = t_5_h.V_1446_Y.n_1700_B(this.O_508_d);
            skeletonentity.J_1907_R(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, 0.0f);
            skeletonentity.n_1700_B(worldIn, difficultyIn, reason, (V_3157_k)null, null);
            skeletonentity.s_956_w(this);
        }
        if (spawnDataIn == null) {
            spawnDataIn = new J_1907_R();
            if (worldIn.x_607_J() == R_2450_T.G_564_y && worldIn.e_4240_b().nextFloat() < 0.1f * difficultyIn.R_4764_Y()) {
                ((J_1907_R)spawnDataIn).n_1700_B(worldIn.e_4240_b());
            }
        }
        if (spawnDataIn instanceof J_1907_R && (effect = ((J_1907_R)spawnDataIn).n_1700_B) != null) {
            this.n_1700_B(new k_2610_C(effect, Integer.MAX_VALUE));
        }
        return spawnDataIn;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.65f;
    }

    static class n_1700_B
    extends b_4953_N {
        public n_1700_B(monsterSpider spider) {
            super(spider, 1.0, true);
        }

        @Override
        public boolean n_1700_B() {
            return super.n_1700_B() && !this.n_1700_B.H_1883_T();
        }

        @Override
        public boolean J_1907_R() {
            float f = this.n_1700_B.RealmsConfirmScreen();
            if (f >= 0.5f && this.n_1700_B.M_3508_C().nextInt(100) == 0) {
                this.n_1700_B.R_4764_Y((r_4811_B)null);
                return false;
            }
            return super.J_1907_R();
        }

        @Override
        protected double n_1700_B(r_4811_B attackTarget) {
            return 4.0f + attackTarget.C_415_h();
        }
    }

    static class R_4764_Y<T extends r_4811_B>
    extends NearestAttackableTargetGoal<T> {
        public R_4764_Y(monsterSpider spider, Class<T> classTarget) {
            super((Z_530_i)spider, classTarget, true);
        }

        @Override
        public boolean n_1700_B() {
            float f = this.P_1922_E.RealmsConfirmScreen();
            return f >= 0.5f ? false : super.n_1700_B();
        }
    }

    public static class J_1907_R
    implements V_3157_k {
        public g_422_i n_1700_B;

        public void n_1700_B(Random rand) {
            int i = rand.nextInt(5);
            if (i <= 1) {
                this.n_1700_B = MobEffects.n_1700_B;
            } else if (i <= 2) {
                this.n_1700_B = MobEffects.P_1922_E;
            } else if (i <= 3) {
                this.n_1700_B = MobEffects.s_956_w;
            } else if (i <= 4) {
                this.n_1700_B = MobEffects.h_1847_R;
            }
        }
    }
}


