/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.D_2364_U;
import lightning.product.RandomStrollGoal;
import lightning.product.E_4925_L;
import lightning.product.BlockGetter;
import lightning.product.F_4355_q;
import lightning.product.Attributes;
import lightning.product.I_1869_h;
import lightning.product.J_548_T;
import lightning.product.L_461_d;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.ZombieAttackGoal;
import lightning.product.MoveToBlockGoal;
import lightning.product.R_2450_T;
import lightning.product.MoveControl;
import lightning.product.T_1316_M;
import lightning.product.biomeBiomes;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.W_3371_U;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_1722_e;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_1972_S;
import lightning.product.ServerLevelAccessor;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.f_2392_k;
import lightning.product.g_3408_G;
import lightning.product.g_4621_i;
import lightning.product.RangedAttackMob;
import lightning.product.i_2099_H;
import lightning.product.ZombifiedPiglin;
import lightning.product.k_594_Q;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.LevelAccessor;
import lightning.product.t_4149_i;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;

public class S_3848_S
extends F_4355_q
implements RangedAttackMob {
    private boolean R_4764_Y;
    protected final c_1972_S n_1700_B;
    protected final i_2099_H J_1907_R;

    public S_3848_S(t_5_h<? extends S_3848_S> type, b_4507_u worldIn) {
        super((t_5_h<? extends F_4355_q>)type, worldIn);
        this.RealmsServerPing = 1.0f;
        this.v_4262_N = new G_564_y(this);
        this.n_1700_B(I_1869_h.w_1484_f, 0.0f);
        this.n_1700_B = new c_1972_S(this, worldIn);
        this.J_1907_R = new i_2099_H(this, worldIn);
    }

    @Override
    protected void u_1723_Y() {
        this.s_956_w.n_1700_B(1, new R_4764_Y(this, 1.0));
        this.s_956_w.n_1700_B(2, new u_1723_Y(this, 1.0, 40, 10.0f));
        this.s_956_w.n_1700_B(2, new n_1700_B(this, 1.0, false));
        this.s_956_w.n_1700_B(5, new J_1907_R(this, 1.0));
        this.s_956_w.n_1700_B(6, new P_1922_E(this, 1.0, this.O_508_d.d_2461_k()));
        this.s_956_w.n_1700_B(7, new RandomStrollGoal(this, 1.0));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, S_3848_S.class).n_1700_B(ZombifiedPiglin.class));
        this.u_2550_I.n_1700_B(2, new NearestAttackableTargetGoal<a_3913_L>(this, a_3913_L.class, 10, true, false, this::w_1484_f));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<g_4621_i>((Z_530_i)this, g_4621_i.class, false));
        this.u_2550_I.n_1700_B(3, new NearestAttackableTargetGoal<D_2364_U>((Z_530_i)this, D_2364_U.class, true));
        this.u_2550_I.n_1700_B(5, new NearestAttackableTargetGoal<t_4149_i>(this, t_4149_i.class, 10, true, false, t_4149_i.h_1847_R));
    }

    @Override
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        spawnDataIn = super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        if (this.J_1907_R(e_1174_E.J_1907_R).n_1700_B() && this.RealmsWorldOptions.nextFloat() < 0.03f) {
            this.n_1700_B(e_1174_E.J_1907_R, new Z_1993_T(Items.SandBlock));
            this.M_588_G[e_1174_E.J_1907_R.J_1907_R()] = 2.0f;
        }
        return spawnDataIn;
    }

    public static boolean n_1700_B(t_5_h<S_3848_S> p_223332_0_, ServerLevelAccessor p_223332_1_, a_3160_D reason, c_1514_x p_223332_3_, Random p_223332_4_) {
        boolean flag;
        Optional<f_2392_k<k_594_Q>> optional = p_223332_1_.n_1700_B(p_223332_3_);
        boolean bl = flag = p_223332_1_.x_607_J() != R_2450_T.n_1700_B && S_3848_S.n_1700_B(p_223332_1_, p_223332_3_, p_223332_4_) && (reason == a_3160_D.R_4764_Y || p_223332_1_.getFluidState(p_223332_3_).n_1700_B(FluidTags.J_1907_R));
        if (!Objects.equals(optional, Optional.of(biomeBiomes.w_1484_f)) && !Objects.equals(optional, Optional.of(biomeBiomes.M_588_G))) {
            return p_223332_4_.nextInt(40) == 0 && S_3848_S.n_1700_B(p_223332_1_, p_223332_3_) && flag;
        }
        return p_223332_4_.nextInt(15) == 0 && flag;
    }

    private static boolean n_1700_B(LevelAccessor p_223333_0_, c_1514_x p_223333_1_) {
        return p_223333_1_.getY() < p_223333_0_.d_2461_k() - 5;
    }

    @Override
    protected boolean y_4642_Y() {
        return false;
    }

    @Override
    protected SoundEvent z_4693_k() {
        return this.RowButton() ? SoundEvents.X_2048_Y : SoundEvents.M_766_z;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return this.RowButton() ? SoundEvents.y_1945_D : SoundEvents.T_437_o;
    }

    @Override
    protected SoundEvent u_796_y() {
        return this.RowButton() ? SoundEvents.a_178_J : SoundEvents.l_2647_k;
    }

    @Override
    protected SoundEvent V_1176_p() {
        return SoundEvents.P_4639_N;
    }

    @Override
    protected SoundEvent F_1410_V() {
        return SoundEvents.i_4434_b;
    }

    @Override
    protected Z_1993_T y_2447_C() {
        return Z_1993_T.J_1907_R;
    }

    @Override
    protected void n_1700_B(DifficultyInstance difficulty) {
        if ((double)this.RealmsWorldOptions.nextFloat() > 0.9) {
            int i = this.RealmsWorldOptions.nextInt(16);
            if (i < 10) {
                this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.P_2605_j));
            } else {
                this.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.w_2223_C));
            }
        }
    }

    @Override
    protected boolean n_1700_B(Z_1993_T candidate, Z_1993_T existing) {
        if (existing.J_1907_R() == Items.SandBlock) {
            return false;
        }
        if (existing.J_1907_R() == Items.P_2605_j) {
            if (candidate.J_1907_R() == Items.P_2605_j) {
                return candidate.v_4262_N() < existing.v_4262_N();
            }
            return false;
        }
        return candidate.J_1907_R() == Items.P_2605_j ? true : super.n_1700_B(candidate, existing);
    }

    @Override
    protected boolean J_3635_s() {
        return false;
    }

    @Override
    public boolean n_1700_B(T_1316_M worldIn) {
        return worldIn.P_1922_E(this);
    }

    public boolean w_1484_f(@Nullable r_4811_B p_204714_1_) {
        if (p_204714_1_ != null) {
            return !this.O_508_d.q_4610_l() || p_204714_1_.RowButton();
        }
        return false;
    }

    @Override
    public boolean Y_776_s() {
        return !this.C_1269_X();
    }

    private boolean c_2086_l() {
        if (this.R_4764_Y) {
            return true;
        }
        r_4811_B livingentity = this.t_148_a();
        return livingentity != null && livingentity.RowButton();
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        if (this.w_1457_N() && this.RowButton() && this.c_2086_l()) {
            this.n_1700_B(0.01f, travelVector);
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
            this.v_4262_N(this.I_4348_c().n_1700_B(0.9));
        } else {
            super.w_1484_f(travelVector);
        }
    }

    @Override
    public void R_3077_Z() {
        if (!this.O_508_d.Y_259_p) {
            if (this.w_1457_N() && this.RowButton() && this.c_2086_l()) {
                this.t_148_a = this.n_1700_B;
                this.s_956_w(true);
            } else {
                this.t_148_a = this.J_1907_R;
                this.s_956_w(false);
            }
        }
    }

    protected boolean o_82_k() {
        double d0;
        c_1514_x blockpos;
        b_1722_e path = this.e_4240_b().s_956_w();
        return path != null && (blockpos = path.P_4830_p()) != null && (d0 = this.v_4262_N(blockpos.getX(), blockpos.getY(), blockpos.getZ())) < 4.0;
    }

    @Override
    public void J_1907_R(r_4811_B target, float distanceFactor) {
        E_4925_L tridententity = new E_4925_L(this.O_508_d, (r_4811_B)this, new Z_1993_T(Items.P_2605_j));
        double d0 = target.O_3598_v() - this.O_3598_v();
        double d1 = target.P_1922_E(0.3333333333333333) - tridententity.X_2960_b();
        double d2 = target.l_2647_k() - this.l_2647_k();
        double d3 = u_530_F.n_1700_B(d0 * d0 + d2 * d2);
        tridententity.R_4764_Y(d0, d1 + d3 * (double)0.2f, d2, 1.6f, 14 - this.O_508_d.x_607_J().n_1700_B() * 4);
        this.n_1700_B(SoundEvents.U_532_X, 1.0f, 1.0f / (this.M_3508_C().nextFloat() * 0.4f + 0.8f));
        this.O_508_d.a_(tridententity);
    }

    public void w_1457_N(boolean p_204713_1_) {
        this.R_4764_Y = p_204713_1_;
    }

    static class G_564_y
    extends MoveControl {
        private final S_3848_S t_148_a;

        public G_564_y(S_3848_S p_i48909_1_) {
            super(p_i48909_1_);
            this.t_148_a = p_i48909_1_;
        }

        @Override
        public void n_1700_B() {
            r_4811_B livingentity = this.t_148_a.t_148_a();
            if (this.t_148_a.c_2086_l() && this.t_148_a.RowButton()) {
                if (livingentity != null && livingentity.X_2960_b() > this.t_148_a.X_2960_b() || this.t_148_a.R_4764_Y) {
                    this.t_148_a.v_4262_N(this.t_148_a.I_4348_c().J_1907_R(0.0, 0.002, 0.0));
                }
                if (this.w_1484_f != MoveControl.n_1700_B.J_1907_R || this.t_148_a.e_4240_b().M_588_G()) {
                    this.t_148_a.w_1457_N(0.0f);
                    return;
                }
                double d0 = this.J_1907_R - this.t_148_a.O_3598_v();
                double d1 = this.R_4764_Y - this.t_148_a.X_2960_b();
                double d2 = this.G_564_y - this.t_148_a.l_2647_k();
                double d3 = u_530_F.n_1700_B(d0 * d0 + d1 * d1 + d2 * d2);
                d1 /= d3;
                float f = (float)(u_530_F.G_564_y(d2, d0) * 57.2957763671875) - 90.0f;
                this.t_148_a.C_1162_e = this.t_148_a.p_178_J = this.n_1700_B(this.t_148_a.p_178_J, f, 90.0f);
                float f1 = (float)(this.P_1922_E * this.t_148_a.J_1907_R(Attributes.G_564_y));
                float f2 = u_530_F.v_4262_N(0.125f, this.t_148_a.l_2995_s(), f1);
                this.t_148_a.w_1457_N(f2);
                this.t_148_a.v_4262_N(this.t_148_a.I_4348_c().J_1907_R((double)f2 * d0 * 0.005, (double)f2 * d1 * 0.1, (double)f2 * d2 * 0.005));
            } else {
                if (!this.t_148_a.e_1992_r) {
                    this.t_148_a.v_4262_N(this.t_148_a.I_4348_c().J_1907_R(0.0, -0.008, 0.0));
                }
                super.n_1700_B();
            }
        }
    }

    static class R_4764_Y
    extends Goal {
        private final PathfinderMob n_1700_B;
        private double J_1907_R;
        private double R_4764_Y;
        private double G_564_y;
        private final double P_1922_E;
        private final b_4507_u u_1723_Y;

        public R_4764_Y(PathfinderMob p_i48910_1_, double p_i48910_2_) {
            this.n_1700_B = p_i48910_1_;
            this.P_1922_E = p_i48910_2_;
            this.u_1723_Y = p_i48910_1_.O_508_d;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            if (!this.u_1723_Y.q_4610_l()) {
                return false;
            }
            if (this.n_1700_B.RowButton()) {
                return false;
            }
            e_2866_D vector3d = this.v_4262_N();
            if (vector3d == null) {
                return false;
            }
            this.J_1907_R = vector3d.J_1907_R;
            this.R_4764_Y = vector3d.R_4764_Y;
            this.G_564_y = vector3d.G_564_y;
            return true;
        }

        @Override
        public boolean J_1907_R() {
            return !this.n_1700_B.e_4240_b().M_588_G();
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B.e_4240_b().n_1700_B(this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }

        @Nullable
        private e_2866_D v_4262_N() {
            Random random = this.n_1700_B.M_3508_C();
            c_1514_x blockpos = this.n_1700_B.b_2312_j();
            for (int i = 0; i < 10; ++i) {
                c_1514_x blockpos1 = blockpos.add(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);
                if (!this.u_1723_Y.getBlockState(blockpos1).n_1700_B(a_3742_W.c_3005_b)) continue;
                return e_2866_D.R_4764_Y(blockpos1);
            }
            return null;
        }
    }

    static class u_1723_Y
    extends J_548_T {
        private final S_3848_S n_1700_B;

        public u_1723_Y(RangedAttackMob p_i48907_1_, double p_i48907_2_, int p_i48907_4_, float p_i48907_5_) {
            super(p_i48907_1_, p_i48907_2_, p_i48907_4_, p_i48907_5_);
            this.n_1700_B = (S_3848_S)p_i48907_1_;
        }

        @Override
        public boolean n_1700_B() {
            return super.n_1700_B() && this.n_1700_B.A_2714_y().J_1907_R() == Items.P_2605_j;
        }

        @Override
        public void R_4764_Y() {
            super.R_4764_Y();
            this.n_1700_B.multiplayerClientSuggestionProvider(true);
            this.n_1700_B.J_1907_R(x_1688_C.n_1700_B);
        }

        @Override
        public void G_564_y() {
            super.G_564_y();
            this.n_1700_B.Y_259_p();
            this.n_1700_B.multiplayerClientSuggestionProvider(false);
        }
    }

    static class n_1700_B
    extends ZombieAttackGoal {
        private final S_3848_S J_1907_R;

        public n_1700_B(S_3848_S p_i48913_1_, double p_i48913_2_, boolean p_i48913_4_) {
            super(p_i48913_1_, p_i48913_2_, p_i48913_4_);
            this.J_1907_R = p_i48913_1_;
        }

        @Override
        public boolean n_1700_B() {
            return super.n_1700_B() && this.J_1907_R.w_1484_f(this.J_1907_R.t_148_a());
        }

        @Override
        public boolean J_1907_R() {
            return super.J_1907_R() && this.J_1907_R.w_1484_f(this.J_1907_R.t_148_a());
        }
    }

    static class J_1907_R
    extends MoveToBlockGoal {
        private final S_3848_S v_4262_N;

        public J_1907_R(S_3848_S p_i48911_1_, double p_i48911_2_) {
            super(p_i48911_1_, p_i48911_2_, 8, 2);
            this.v_4262_N = p_i48911_1_;
        }

        @Override
        public boolean n_1700_B() {
            return super.n_1700_B() && !this.v_4262_N.O_508_d.q_4610_l() && this.v_4262_N.RowButton() && this.v_4262_N.X_2960_b() >= (double)(this.v_4262_N.O_508_d.d_2461_k() - 3);
        }

        @Override
        public boolean J_1907_R() {
            return super.J_1907_R();
        }

        @Override
        protected boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
            c_1514_x blockpos = pos.up();
            return worldIn.u_1723_Y(blockpos) && worldIn.u_1723_Y(blockpos.up()) ? worldIn.getBlockState(pos).n_1700_B((BlockGetter)worldIn, pos, (N_4263_v)this.v_4262_N) : false;
        }

        @Override
        public void R_4764_Y() {
            this.v_4262_N.w_1457_N(false);
            this.v_4262_N.t_148_a = this.v_4262_N.J_1907_R;
            super.R_4764_Y();
        }

        @Override
        public void G_564_y() {
            super.G_564_y();
        }
    }

    static class P_1922_E
    extends Goal {
        private final S_3848_S n_1700_B;
        private final double J_1907_R;
        private final int R_4764_Y;
        private boolean G_564_y;

        public P_1922_E(S_3848_S p_i48908_1_, double p_i48908_2_, int p_i48908_4_) {
            this.n_1700_B = p_i48908_1_;
            this.J_1907_R = p_i48908_2_;
            this.R_4764_Y = p_i48908_4_;
        }

        @Override
        public boolean n_1700_B() {
            return !this.n_1700_B.O_508_d.q_4610_l() && this.n_1700_B.RowButton() && this.n_1700_B.X_2960_b() < (double)(this.R_4764_Y - 2);
        }

        @Override
        public boolean J_1907_R() {
            return this.n_1700_B() && !this.G_564_y;
        }

        @Override
        public void P_1922_E() {
            if (this.n_1700_B.X_2960_b() < (double)(this.R_4764_Y - 1) && (this.n_1700_B.e_4240_b().M_588_G() || this.n_1700_B.o_82_k())) {
                e_2866_D vector3d = W_3371_U.J_1907_R(this.n_1700_B, 4, 8, new e_2866_D(this.n_1700_B.O_3598_v(), this.R_4764_Y - 1, this.n_1700_B.l_2647_k()));
                if (vector3d == null) {
                    this.G_564_y = true;
                    return;
                }
                this.n_1700_B.e_4240_b().n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, this.J_1907_R);
            }
        }

        @Override
        public void R_4764_Y() {
            this.n_1700_B.w_1457_N(true);
            this.G_564_y = false;
        }

        @Override
        public void G_564_y() {
            this.n_1700_B.w_1457_N(false);
        }
    }
}


