/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import javax.annotation.Nullable;
import lightning.product.RandomLookAroundGoal;
import lightning.product.D_2364_U;
import lightning.product.H_2333_J;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.K_4074_S;
import lightning.product.DifficultyInstance;
import lightning.product.R_1815_U;
import lightning.product.R_2450_T;
import lightning.product.AvoidEntityGoal;
import lightning.product.S_199_U;
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
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_1174_E;
import lightning.product.g_1941_L;
import lightning.product.MobType;
import lightning.product.g_3408_G;
import lightning.product.RangedAttackMob;
import lightning.product.h_384_L;
import lightning.product.Monster;
import lightning.product.PathfinderMob;
import lightning.product.q_2335_j;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_4149_i;
import lightning.product.t_5_h;
import lightning.product.RestrictSunGoal;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.RangedBowAttackGoal;

public abstract class AbstractSkeleton
extends Monster
implements RangedAttackMob {
    private final RangedBowAttackGoal<AbstractSkeleton> n_1700_B = new RangedBowAttackGoal<AbstractSkeleton>(this, 1.0, 20, 15.0f);
    private final b_4953_N J_1907_R = new b_4953_N(this, 1.2, false){

        @Override
        public void G_564_y() {
            super.G_564_y();
            AbstractSkeleton.this.multiplayerClientSuggestionProvider(false);
        }

        @Override
        public void R_4764_Y() {
            super.R_4764_Y();
            AbstractSkeleton.this.multiplayerClientSuggestionProvider(true);
        }
    };

    protected AbstractSkeleton(t_5_h<? extends AbstractSkeleton> type, b_4507_u worldIn) {
        super((t_5_h<? extends Monster>)type, worldIn);
        this.V_1176_p();
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(2, new RestrictSunGoal(this));
        this.s_956_w.n_1700_B(3, new S_199_U(this, 1.0));
        this.s_956_w.n_1700_B(3, new AvoidEntityGoal<q_2335_j>(this, q_2335_j.class, 6.0f, 1.0, 1.2));
        this.s_956_w.n_1700_B(5, new g_1941_L(this, 1.0));
        this.s_956_w.n_1700_B(6, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(6, new RandomLookAroundGoal(this));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, new Class[0]));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<D_2364_U>((Z_530_i)this, D_2364_U.class, true));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<t_4149_i>(this, t_4149_i.class, 10, true, false, t_4149_i.h_1847_R));
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.G_564_y, 0.25);
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(this.y_4642_Y(), 0.15f, 1.0f);
    }

    abstract SoundEvent y_4642_Y();

    @Override
    public MobType F_2860_q() {
        return MobType.J_1907_R;
    }

    @Override
    public void Y_1740_V() {
        boolean flag = this.S_2828_i();
        if (flag) {
            Z_1993_T itemstack = this.J_1907_R(e_1174_E.u_1723_Y);
            if (!itemstack.n_1700_B()) {
                if (itemstack.P_1922_E()) {
                    itemstack.J_1907_R(itemstack.v_4262_N() + this.RealmsWorldOptions.nextInt(2));
                    if (itemstack.v_4262_N() >= itemstack.w_1484_f()) {
                        this.R_4764_Y(e_1174_E.u_1723_Y);
                        this.n_1700_B(e_1174_E.u_1723_Y, Z_1993_T.J_1907_R);
                    }
                }
                flag = false;
            }
            if (flag) {
                this.P_1922_E(8);
            }
        }
        super.Y_1740_V();
    }

    @Override
    public void x_607_J() {
        super.x_607_J();
        if (this.l_3609_d() instanceof PathfinderMob) {
            PathfinderMob creatureentity = (PathfinderMob)this.l_3609_d();
            this.C_1162_e = creatureentity.C_1162_e;
        }
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        super.n_1700_B(difficulty);
        this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.R_1796_s));
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        spawnDataIn = super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.n_1700_B(difficultyIn);
        this.J_1907_R(difficultyIn);
        this.V_1176_p();
        this.R_4764_Y(this.RealmsWorldOptions.nextFloat() < 0.55f * difficultyIn.R_4764_Y());
        if (this.J_1907_R(e_1174_E.u_1723_Y).n_1700_B()) {
            LocalDate localdate = LocalDate.now();
            int i = localdate.get(ChronoField.DAY_OF_MONTH);
            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && this.RealmsWorldOptions.nextFloat() < 0.25f) {
                this.n_1700_B(e_1174_E.u_1723_Y, new Z_1993_T(this.RealmsWorldOptions.nextFloat() < 0.1f ? a_3742_W.l_2647_k : a_3742_W.X_2048_Y));
                this.P_4830_p[e_1174_E.u_1723_Y.J_1907_R()] = 0.0f;
            }
        }
        return spawnDataIn;
    }

    public void V_1176_p() {
        if (this.O_508_d != null && !this.O_508_d.Y_259_p) {
            this.s_956_w.n_1700_B(this.J_1907_R);
            this.s_956_w.n_1700_B(this.n_1700_B);
            Z_1993_T itemstack = this.R_4764_Y(H_2333_J.n_1700_B(this, Items.R_1796_s));
            if (itemstack.J_1907_R() == Items.R_1796_s) {
                int i = 20;
                if (this.O_508_d.x_607_J() != R_2450_T.G_564_y) {
                    i = 40;
                }
                this.n_1700_B.n_1700_B(i);
                this.s_956_w.n_1700_B(4, this.n_1700_B);
            } else {
                this.s_956_w.n_1700_B(4, this.J_1907_R);
            }
        }
    }

    @Override
    public void J_1907_R(r_4811_B target, float distanceFactor) {
        Z_1993_T itemstack = this.u_1723_Y(this.R_4764_Y(H_2333_J.n_1700_B(this, Items.R_1796_s)));
        h_384_L abstractarrowentity = this.J_1907_R(itemstack, distanceFactor);
        double d0 = target.O_3598_v() - this.O_3598_v();
        double d1 = target.P_1922_E(0.3333333333333333) - abstractarrowentity.X_2960_b();
        double d2 = target.l_2647_k() - this.l_2647_k();
        double d3 = u_530_F.n_1700_B(d0 * d0 + d2 * d2);
        abstractarrowentity.R_4764_Y(d0, d1 + d3 * (double)0.2f, d2, 1.6f, 14 - this.O_508_d.x_607_J().n_1700_B() * 4);
        this.n_1700_B(SoundEvents.V_1824_v, 1.0f, 1.0f / (this.M_3508_C().nextFloat() * 0.4f + 0.8f));
        this.O_508_d.a_(abstractarrowentity);
    }

    protected h_384_L J_1907_R(Z_1993_T arrowStack, float distanceFactor) {
        return H_2333_J.n_1700_B(this, arrowStack, distanceFactor);
    }

    @Override
    public boolean n_1700_B(ProjectileWeaponItem p_230280_1_) {
        return p_230280_1_ == Items.R_1796_s;
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.V_1176_p();
    }

    @Override
    public void n_1700_B(e_1174_E slotIn, Z_1993_T stack) {
        super.n_1700_B(slotIn, stack);
        if (!this.O_508_d.Y_259_p) {
            this.V_1176_p();
        }
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 1.74f;
    }

    @Override
    public double O_2151_c() {
        return -0.6;
    }
}


