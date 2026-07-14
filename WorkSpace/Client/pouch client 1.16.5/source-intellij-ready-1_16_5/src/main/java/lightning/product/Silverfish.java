/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import java.util.Random;
import lightning.product.A_2352_Z;
import lightning.product.RandomStrollGoal;
import lightning.product.Attributes;
import lightning.product.InfestedBlock;
import lightning.product.I_1170_F;
import lightning.product.K_4074_S;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.T_1316_M;
import lightning.product.FloatGoal;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.f_2785_f;
import lightning.product.MobType;
import lightning.product.g_3408_G;
import lightning.product.Monster;
import lightning.product.Goal;
import lightning.product.s_1415_m;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;

public class Silverfish
extends Monster {
    private J_1907_R n_1700_B;

    public Silverfish(t_5_h<? extends Silverfish> typeIn, b_4507_u worldIn) {
        super((t_5_h<? extends Monster>)typeIn, worldIn);
    }

    @Override
    protected void M_182_A() {
        this.n_1700_B = new J_1907_R(this);
        this.s_956_w.n_1700_B(1, new FloatGoal(this));
        this.s_956_w.n_1700_B(3, this.n_1700_B);
        this.s_956_w.n_1700_B(4, new b_4953_N(this, 1.0, false));
        this.s_956_w.n_1700_B(5, new n_1700_B(this));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, new Class[0]).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>((Z_530_i)this, a_3913_L.class, true));
    }

    @Override
    public double O_2151_c() {
        return 0.1;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.13f;
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return Monster.o_4117_e().n_1700_B(Attributes.n_1700_B, 8.0).n_1700_B(Attributes.G_564_y, 0.25).n_1700_B(Attributes.u_1723_Y, 1.0);
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return SoundEvents.m_3052_r;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.v_2746_S;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.m_1964_F;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(SoundEvents.A_2487_t, 0.15f, 1.0f);
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        if ((source instanceof f_2785_f || source == P_11_z.Q_4569_t) && this.n_1700_B != null) {
            this.n_1700_B.v_4262_N();
        }
        return super.n_1700_B(source, amount);
    }

    @Override
    public void v_() {
        this.C_1162_e = this.p_178_J;
        super.v_();
    }

    @Override
    public void Q_4569_t(float offset) {
        this.p_178_J = offset;
        super.Q_4569_t(offset);
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        return InfestedBlock.w_1484_f(worldIn.getBlockState(pos.down())) ? 10.0f : super.n_1700_B(pos, worldIn);
    }

    public static boolean J_1907_R(t_5_h<Silverfish> p_223331_0_, LevelAccessor p_223331_1_, a_3160_D reason, c_1514_x p_223331_3_, Random p_223331_4_) {
        if (Silverfish.R_4764_Y(p_223331_0_, p_223331_1_, reason, p_223331_3_, p_223331_4_)) {
            a_3913_L playerentity = p_223331_1_.n_1700_B((double)p_223331_3_.getX() + 0.5, (double)p_223331_3_.getY() + 0.5, (double)p_223331_3_.getZ() + 0.5, 5.0, true);
            return playerentity == null;
        }
        return false;
    }

    @Override
    public MobType F_2860_q() {
        return MobType.R_4764_Y;
    }

    static class J_1907_R
    extends Goal {
        private final Silverfish n_1700_B;
        private int J_1907_R;

        public J_1907_R(Silverfish silverfishIn) {
            this.n_1700_B = silverfishIn;
        }

        public void v_4262_N() {
            if (this.J_1907_R == 0) {
                this.J_1907_R = 20;
            }
        }

        @Override
        public boolean n_1700_B() {
            return this.J_1907_R > 0;
        }

        @Override
        public void P_1922_E() {
            --this.J_1907_R;
            if (this.J_1907_R <= 0) {
                b_4507_u world = this.n_1700_B.O_508_d;
                Random random = this.n_1700_B.M_3508_C();
                c_1514_x blockpos = this.n_1700_B.b_2312_j();
                int i = 0;
                while (i <= 5 && i >= -5) {
                    int j = 0;
                    while (j <= 10 && j >= -10) {
                        int k = 0;
                        while (k <= 10 && k >= -10) {
                            c_1514_x blockpos1 = blockpos.add(j, i, k);
                            K_4074_S blockstate = world.getBlockState(blockpos1);
                            T_2915_h block = blockstate.J_1907_R();
                            if (block instanceof InfestedBlock) {
                                if (world.H_1990_U().J_1907_R(A_2352_Z.J_1907_R)) {
                                    world.n_1700_B(blockpos1, true, this.n_1700_B);
                                } else {
                                    world.n_1700_B(blockpos1, ((InfestedBlock)block).J_1907_R().multiplayerClientSuggestionProvider(), 3);
                                }
                                if (random.nextBoolean()) {
                                    return;
                                }
                            }
                            k = (k <= 0 ? 1 : 0) - k;
                        }
                        j = (j <= 0 ? 1 : 0) - j;
                    }
                    i = (i <= 0 ? 1 : 0) - i;
                }
            }
        }
    }

    static class n_1700_B
    extends RandomStrollGoal {
        private b_257_Y w_1484_f;
        private boolean t_148_a;

        public n_1700_B(Silverfish silverfishIn) {
            super(silverfishIn, 1.0, 10);
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            if (this.n_1700_B.t_148_a() != null) {
                return false;
            }
            if (!this.n_1700_B.e_4240_b().M_588_G()) {
                return false;
            }
            Random random = this.n_1700_B.M_3508_C();
            if (this.n_1700_B.O_508_d.H_1990_U().J_1907_R(A_2352_Z.J_1907_R) && random.nextInt(10) == 0) {
                this.w_1484_f = b_257_Y.n_1700_B(random);
                c_1514_x blockpos = new c_1514_x(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b() + 0.5, this.n_1700_B.l_2647_k()).offset(this.w_1484_f);
                K_4074_S blockstate = this.n_1700_B.O_508_d.getBlockState(blockpos);
                if (InfestedBlock.w_1484_f(blockstate)) {
                    this.t_148_a = true;
                    return true;
                }
            }
            this.t_148_a = false;
            return super.n_1700_B();
        }

        @Override
        public boolean J_1907_R() {
            return this.t_148_a ? false : super.J_1907_R();
        }

        @Override
        public void R_4764_Y() {
            if (!this.t_148_a) {
                super.R_4764_Y();
            } else {
                b_4507_u iworld = this.n_1700_B.O_508_d;
                c_1514_x blockpos = new c_1514_x(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b() + 0.5, this.n_1700_B.l_2647_k()).offset(this.w_1484_f);
                K_4074_S blockstate = iworld.getBlockState(blockpos);
                if (InfestedBlock.w_1484_f(blockstate)) {
                    iworld.n_1700_B(blockpos, InfestedBlock.n_1700_B(blockstate.J_1907_R()), 3);
                    this.n_1700_B.T_2506_i();
                    this.n_1700_B.Ops();
                }
            }
        }
    }
}


