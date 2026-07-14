/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.PathNavigation;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.BlockGetter;
import lightning.product.StructureFeature;
import lightning.product.G_1455_B;
import lightning.product.BreathAirGoal;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.RandomSwimmingGoal;
import lightning.product.MobEffects;
import lightning.product.L_461_d;
import lightning.product.ItemTags;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.MoveControl;
import lightning.product.AvoidEntityGoal;
import lightning.product.ParticleOptions;
import lightning.product.biomeBiomes;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.W_3371_U;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.DolphinJumpGoal;
import lightning.product.c_1972_S;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.TargetingConditions;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.WaterAnimal;
import lightning.product.g_3408_G;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2610_C;
import lightning.product.k_594_Q;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.DolphinLookControl;
import lightning.product.FollowBoatGoal;
import lightning.product.Goal;
import lightning.product.TryFindWaterGoal;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.FeatureConfiguration;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.x_1688_C;

public class Y_559_r
extends WaterAnimal {
    private static final h_256_u<c_1514_x> J_1907_R = C_4114_x.n_1700_B(Y_559_r.class, EntityDataSerializers.M_588_G);
    private static final h_256_u<Boolean> R_4764_Y = C_4114_x.n_1700_B(Y_559_r.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Integer> h_1847_R = C_4114_x.n_1700_B(Y_559_r.class, EntityDataSerializers.J_1907_R);
    private static final TargetingConditions Q_4569_t = new TargetingConditions().n_1700_B(10.0).J_1907_R().n_1700_B().R_4764_Y();
    public static final Predicate<n_1494_c> n_1700_B = p_205023_0_ -> !p_205023_0_.Q_4569_t() && p_205023_0_.RealmsLongRunningMcoTaskScreen() && p_205023_0_.RowButton();

    public Y_559_r(t_5_h<? extends Y_559_r> type, b_4507_u worldIN) {
        super((t_5_h<? extends WaterAnimal>)type, worldIN);
        this.v_4262_N = new n_1700_B(this);
        this.u_1723_Y = new DolphinLookControl(this, 10);
        this.R_4764_Y(true);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        this.w_1484_f(this.P_5000_x());
        this.f_4016_n = 0.0f;
        return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean P_328_a() {
        return false;
    }

    @Override
    protected void n_1700_B(int p_209207_1_) {
    }

    public void v_4262_N(c_1514_x posIn) {
        this.l_4537_E.J_1907_R(J_1907_R, posIn);
    }

    public c_1514_x u_1723_Y() {
        return this.l_4537_E.n_1700_B(J_1907_R);
    }

    public boolean y_4642_Y() {
        return this.l_4537_E.n_1700_B(R_4764_Y);
    }

    public void w_1457_N(boolean p_208008_1_) {
        this.l_4537_E.J_1907_R(R_4764_Y, p_208008_1_);
    }

    public int h_1640_b() {
        return this.l_4537_E.n_1700_B(h_1847_R);
    }

    public void J_1907_R(int p_211137_1_) {
        this.l_4537_E.J_1907_R(h_1847_R, p_211137_1_);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(J_1907_R, c_1514_x.ZERO);
        this.l_4537_E.n_1700_B(R_4764_Y, false);
        this.l_4537_E.n_1700_B(h_1847_R, 2400);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.J_1907_R("TreasurePosX", this.u_1723_Y().getX());
        compound.J_1907_R("TreasurePosY", this.u_1723_Y().getY());
        compound.J_1907_R("TreasurePosZ", this.u_1723_Y().getZ());
        compound.n_1700_B("GotFish", this.y_4642_Y());
        compound.J_1907_R("Moistness", this.h_1640_b());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        int i = compound.w_1484_f("TreasurePosX");
        int j = compound.w_1484_f("TreasurePosY");
        int k = compound.w_1484_f("TreasurePosZ");
        this.v_4262_N(new c_1514_x(i, j, k));
        super.J_1907_R(compound);
        this.w_1457_N(compound.t_1786_h("GotFish"));
        this.J_1907_R(compound.w_1484_f("Moistness"));
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new BreathAirGoal(this));
        this.s_956_w.n_1700_B(0, new TryFindWaterGoal(this));
        this.s_956_w.n_1700_B(1, new R_4764_Y(this));
        this.s_956_w.n_1700_B(2, new G_564_y(this, 4.0));
        this.s_956_w.n_1700_B(4, new RandomSwimmingGoal(this, 1.0, 10));
        this.s_956_w.n_1700_B(4, new RandomLookAroundGoal(this));
        this.s_956_w.n_1700_B(5, new LookAtPlayerGoal(this, a_3913_L.class, 6.0f));
        this.s_956_w.n_1700_B(5, new DolphinJumpGoal(this, 10));
        this.s_956_w.n_1700_B(6, new b_4953_N(this, 1.2f, true));
        this.s_956_w.n_1700_B(8, new J_1907_R());
        this.s_956_w.n_1700_B(8, new FollowBoatGoal(this));
        this.s_956_w.n_1700_B(9, new AvoidEntityGoal<G_1455_B>(this, G_1455_B.class, 8.0f, 1.0, 1.0));
        this.u_2550_I.n_1700_B(1, new g_3408_G(this, G_1455_B.class).n_1700_B(new Class[0]));
    }

    public static s_1415_m.n_1700_B V_1176_p() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 10.0).n_1700_B(Attributes.G_564_y, 1.2f).n_1700_B(Attributes.u_1723_Y, 3.0);
    }

    @Override
    protected PathNavigation J_1907_R(b_4507_u worldIn) {
        return new c_1972_S(this, worldIn);
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        boolean flag = entityIn.n_1700_B(P_11_z.R_4764_Y(this), (float)((int)this.J_1907_R(Attributes.u_1723_Y)));
        if (flag) {
            this.n_1700_B((r_4811_B)this, entityIn);
            this.n_1700_B(SoundEvents.d_3244_b, 1.0f, 1.0f);
        }
        return flag;
    }

    @Override
    public int P_5000_x() {
        return 4800;
    }

    @Override
    protected int s_956_w(int currentAir) {
        return this.P_5000_x();
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.3f;
    }

    @Override
    public int Z_976_R() {
        return 1;
    }

    @Override
    public int H_1990_U() {
        return 1;
    }

    @Override
    protected boolean u_2550_I(N_4263_v entityIn) {
        return true;
    }

    @Override
    public boolean P_1922_E(Z_1993_T itemstackIn) {
        e_1174_E equipmentslottype = Z_530_i.s_956_w(itemstackIn);
        if (!this.J_1907_R(equipmentslottype).n_1700_B()) {
            return false;
        }
        return equipmentslottype == e_1174_E.n_1700_B && super.P_1922_E(itemstackIn);
    }

    @Override
    protected void J_1907_R(n_1494_c itemEntity) {
        Z_1993_T itemstack;
        if (this.J_1907_R(e_1174_E.n_1700_B).n_1700_B() && this.w_1484_f(itemstack = itemEntity.P_1922_E())) {
            this.n_1700_B(itemEntity);
            this.n_1700_B(e_1174_E.n_1700_B, itemstack);
            this.M_588_G[e_1174_E.n_1700_B.J_1907_R()] = 2.0f;
            this.n_1700_B((N_4263_v)itemEntity, itemstack.t_4043_B());
            itemEntity.Ops();
        }
    }

    @Override
    public void v_() {
        super.v_();
        if (this.n_473_l()) {
            this.w_1484_f(this.P_5000_x());
        } else {
            if (this.j_2266_I()) {
                this.J_1907_R(2400);
            } else {
                this.J_1907_R(this.h_1640_b() - 1);
                if (this.h_1640_b() <= 0) {
                    this.n_1700_B(P_11_z.Y_601_j, 1.0f);
                }
                if (this.e_1992_r) {
                    this.v_4262_N(this.I_4348_c().J_1907_R((this.RealmsWorldOptions.nextFloat() * 2.0f - 1.0f) * 0.2f, 0.5, (this.RealmsWorldOptions.nextFloat() * 2.0f - 1.0f) * 0.2f));
                    this.p_178_J = this.RealmsWorldOptions.nextFloat() * 360.0f;
                    this.e_1992_r = false;
                    this.LongRunningTask = true;
                }
            }
            if (this.O_508_d.Y_259_p && this.RowButton() && this.I_4348_c().v_4262_N() > 0.03) {
                e_2866_D vector3d = this.t_148_a(0.0f);
                float f = u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180)) * 0.3f;
                float f1 = u_530_F.n_1700_B(this.p_178_J * ((float)Math.PI / 180)) * 0.3f;
                float f2 = 1.2f - this.RealmsWorldOptions.nextFloat() * 0.7f;
                for (int i = 0; i < 2; ++i) {
                    this.O_508_d.n_1700_B(ParticleTypes.O_508_d, this.O_3598_v() - vector3d.J_1907_R * (double)f2 + (double)f, this.X_2960_b() - vector3d.R_4764_Y, this.l_2647_k() - vector3d.G_564_y * (double)f2 + (double)f1, 0.0, 0.0, 0.0);
                    this.O_508_d.n_1700_B(ParticleTypes.O_508_d, this.O_3598_v() - vector3d.J_1907_R * (double)f2 - (double)f, this.X_2960_b() - vector3d.R_4764_Y, this.l_2647_k() - vector3d.G_564_y * (double)f2 - (double)f1, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    @Override
    public void n_1700_B(byte id) {
        if (id == 38) {
            this.n_1700_B(ParticleTypes.t_4043_B);
        } else {
            super.n_1700_B(id);
        }
    }

    private void n_1700_B(ParticleOptions p_208401_1_) {
        for (int i = 0; i < 7; ++i) {
            double d0 = this.RealmsWorldOptions.nextGaussian() * 0.01;
            double d1 = this.RealmsWorldOptions.nextGaussian() * 0.01;
            double d2 = this.RealmsWorldOptions.nextGaussian() * 0.01;
            this.O_508_d.n_1700_B(p_208401_1_, this.G_564_y(1.0), this.M_766_z() + 0.2, this.v_4262_N(1.0), d0, d1, d2);
        }
    }

    @Override
    protected m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (!itemstack.n_1700_B() && itemstack.J_1907_R().n_1700_B(ItemTags.g_164_R)) {
            if (!this.O_508_d.Y_259_p) {
                this.n_1700_B(SoundEvents.l_3609_d, 1.0f, 1.0f);
            }
            this.w_1457_N(true);
            if (!p_230254_1_.C_415_h.G_564_y) {
                itemstack.v_4262_N(1);
            }
            return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
        }
        return super.J_1907_R(p_230254_1_, p_230254_2_);
    }

    public static boolean J_1907_R(t_5_h<Y_559_r> p_223364_0_, LevelAccessor p_223364_1_, a_3160_D reason, c_1514_x p_223364_3_, Random p_223364_4_) {
        if (p_223364_3_.getY() > 45 && p_223364_3_.getY() < p_223364_1_.d_2461_k()) {
            Optional<f_2392_k<k_594_Q>> optional = p_223364_1_.n_1700_B(p_223364_3_);
            return (!Objects.equals(optional, Optional.of(biomeBiomes.n_1700_B)) || !Objects.equals(optional, Optional.of(biomeBiomes.q_2307_F))) && p_223364_1_.getFluidState(p_223364_3_).n_1700_B(FluidTags.J_1907_R);
        }
        return false;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.r_2478_U;
    }

    @Override
    @Nullable
    protected SoundEvent u_796_y() {
        return SoundEvents.v_887_r;
    }

    @Override
    @Nullable
    protected SoundEvent z_4693_k() {
        return this.RowButton() ? SoundEvents.l_697_B : SoundEvents.O_1795_e;
    }

    @Override
    protected SoundEvent S_4022_R() {
        return SoundEvents.i_3196_G;
    }

    @Override
    protected SoundEvent F_1410_V() {
        return SoundEvents.C_415_h;
    }

    protected boolean y_2447_C() {
        c_1514_x blockpos = this.e_4240_b().v_4262_N();
        return blockpos != null ? blockpos.withinDistance(this.s_4990_V(), 12.0) : false;
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        if (this.w_1457_N() && this.RowButton()) {
            this.n_1700_B(this.l_2995_s(), travelVector);
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
            this.v_4262_N(this.I_4348_c().n_1700_B(0.9));
            if (this.t_148_a() == null) {
                this.v_4262_N(this.I_4348_c().J_1907_R(0.0, -0.005, 0.0));
            }
        } else {
            super.w_1484_f(travelVector);
        }
    }

    @Override
    public boolean G_564_y(a_3913_L player) {
        return true;
    }

    static class n_1700_B
    extends MoveControl {
        private final Y_559_r t_148_a;

        public n_1700_B(Y_559_r dolphinIn) {
            super(dolphinIn);
            this.t_148_a = dolphinIn;
        }

        @Override
        public void n_1700_B() {
            if (this.t_148_a.RowButton()) {
                this.t_148_a.v_4262_N(this.t_148_a.I_4348_c().J_1907_R(0.0, 0.005, 0.0));
            }
            if (this.w_1484_f == MoveControl.n_1700_B.J_1907_R && !this.t_148_a.e_4240_b().M_588_G()) {
                double d2;
                double d1;
                double d0 = this.J_1907_R - this.t_148_a.O_3598_v();
                double d3 = d0 * d0 + (d1 = this.R_4764_Y - this.t_148_a.X_2960_b()) * d1 + (d2 = this.G_564_y - this.t_148_a.l_2647_k()) * d2;
                if (d3 < 2.500000277905201E-7) {
                    this.n_1700_B.C_2741_M(0.0f);
                } else {
                    float f = (float)(u_530_F.G_564_y(d2, d0) * 57.2957763671875) - 90.0f;
                    this.t_148_a.C_1162_e = this.t_148_a.p_178_J = this.n_1700_B(this.t_148_a.p_178_J, f, 10.0f);
                    this.t_148_a.f_3449_S = this.t_148_a.p_178_J;
                    float f1 = (float)(this.P_1922_E * this.t_148_a.J_1907_R(Attributes.G_564_y));
                    if (this.t_148_a.RowButton()) {
                        this.t_148_a.w_1457_N(f1 * 0.02f);
                        float f2 = -((float)(u_530_F.G_564_y(d1, (double)u_530_F.n_1700_B(d0 * d0 + d2 * d2)) * 57.2957763671875));
                        f2 = u_530_F.n_1700_B(u_530_F.v_4262_N(f2), -85.0f, 85.0f);
                        this.t_148_a.f_4016_n = this.n_1700_B(this.t_148_a.f_4016_n, f2, 5.0f);
                        float f3 = u_530_F.J_1907_R(this.t_148_a.f_4016_n * ((float)Math.PI / 180));
                        float f4 = u_530_F.n_1700_B(this.t_148_a.f_4016_n * ((float)Math.PI / 180));
                        this.t_148_a.L_4248_u = f3 * f1;
                        this.t_148_a.P_5000_x = -f4 * f1;
                    } else {
                        this.t_148_a.w_1457_N(f1 * 0.1f);
                    }
                }
            } else {
                this.t_148_a.w_1457_N(0.0f);
                this.t_148_a.q_2307_F(0.0f);
                this.t_148_a.k_2293_S(0.0f);
                this.t_148_a.C_2741_M(0.0f);
            }
        }
    }

    static class R_4764_Y
    extends Goal {
        private final Y_559_r n_1700_B;
        private boolean J_1907_R;

        R_4764_Y(Y_559_r dolphinIn) {
            this.n_1700_B = dolphinIn;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean r_() {
            return false;
        }

        @Override
        public boolean n_1700_B() {
            return this.n_1700_B.y_4642_Y() && this.n_1700_B.L_4248_u() >= 100;
        }

        @Override
        public boolean J_1907_R() {
            c_1514_x blockpos = this.n_1700_B.u_1723_Y();
            return !new c_1514_x((double)blockpos.getX(), this.n_1700_B.X_2960_b(), (double)blockpos.getZ()).withinDistance(this.n_1700_B.s_4990_V(), 4.0) && !this.J_1907_R && this.n_1700_B.L_4248_u() >= 100;
        }

        @Override
        public void R_4764_Y() {
            if (this.n_1700_B.O_508_d instanceof e_3591_l) {
                e_3591_l serverworld = (e_3591_l)this.n_1700_B.O_508_d;
                this.J_1907_R = false;
                this.n_1700_B.e_4240_b().h_1847_R();
                c_1514_x blockpos = this.n_1700_B.b_2312_j();
                StructureFeature<FeatureConfiguration> structure = (double)serverworld.w_1457_N.nextFloat() >= 0.5 ? StructureFeature.P_4830_p : StructureFeature.t_148_a;
                c_1514_x blockpos1 = serverworld.n_1700_B(structure, blockpos, 50, false);
                if (blockpos1 == null) {
                    StructureFeature<FeatureConfiguration> structure1 = structure.equals(StructureFeature.P_4830_p) ? StructureFeature.t_148_a : StructureFeature.P_4830_p;
                    c_1514_x blockpos2 = serverworld.n_1700_B(structure1, blockpos, 50, false);
                    if (blockpos2 == null) {
                        this.J_1907_R = true;
                        return;
                    }
                    this.n_1700_B.v_4262_N(blockpos2);
                } else {
                    this.n_1700_B.v_4262_N(blockpos1);
                }
                serverworld.n_1700_B((N_4263_v)this.n_1700_B, (byte)38);
            }
        }

        @Override
        public void G_564_y() {
            c_1514_x blockpos = this.n_1700_B.u_1723_Y();
            if (new c_1514_x((double)blockpos.getX(), this.n_1700_B.X_2960_b(), (double)blockpos.getZ()).withinDistance(this.n_1700_B.s_4990_V(), 4.0) || this.J_1907_R) {
                this.n_1700_B.w_1457_N(false);
            }
        }

        @Override
        public void P_1922_E() {
            b_4507_u world = this.n_1700_B.O_508_d;
            if (this.n_1700_B.y_2447_C() || this.n_1700_B.e_4240_b().M_588_G()) {
                c_1514_x blockpos;
                e_2866_D vector3d = e_2866_D.n_1700_B(this.n_1700_B.u_1723_Y());
                e_2866_D vector3d1 = W_3371_U.n_1700_B(this.n_1700_B, 16, 1, vector3d, 0.3926991f);
                if (vector3d1 == null) {
                    vector3d1 = W_3371_U.J_1907_R(this.n_1700_B, 8, 4, vector3d);
                }
                if (!(vector3d1 == null || world.getFluidState(blockpos = new c_1514_x(vector3d1)).n_1700_B(FluidTags.J_1907_R) && world.getBlockState(blockpos).n_1700_B((BlockGetter)world, blockpos, t_3546_P.J_1907_R))) {
                    vector3d1 = W_3371_U.J_1907_R(this.n_1700_B, 8, 5, vector3d);
                }
                if (vector3d1 == null) {
                    this.J_1907_R = true;
                    return;
                }
                this.n_1700_B.c_3005_b().n_1700_B(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, this.n_1700_B.H_1990_U() + 20, this.n_1700_B.Z_976_R());
                this.n_1700_B.e_4240_b().n_1700_B(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, 1.3);
                if (world.w_1457_N.nextInt(80) == 0) {
                    world.n_1700_B((N_4263_v)this.n_1700_B, (byte)38);
                }
            }
        }
    }

    static class G_564_y
    extends Goal {
        private final Y_559_r n_1700_B;
        private final double J_1907_R;
        private a_3913_L R_4764_Y;

        G_564_y(Y_559_r dolphinIn, double speedIn) {
            this.n_1700_B = dolphinIn;
            this.J_1907_R = speedIn;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.J_1907_R));
        }

        @Override
        public boolean n_1700_B() {
            this.R_4764_Y = this.n_1700_B.O_508_d.n_1700_B(Q_4569_t, this.n_1700_B);
            if (this.R_4764_Y == null) {
                return false;
            }
            return this.R_4764_Y.C_1269_X() && this.n_1700_B.t_148_a() != this.R_4764_Y;
        }

        @Override
        public boolean J_1907_R() {
            return this.R_4764_Y != null && this.R_4764_Y.C_1269_X() && this.n_1700_B.G_564_y((N_4263_v)this.R_4764_Y) < 256.0;
        }

        @Override
        public void R_4764_Y() {
            this.R_4764_Y.n_1700_B(new k_2610_C(MobEffects.Y_1740_V, 100));
        }

        @Override
        public void G_564_y() {
            this.R_4764_Y = null;
            this.n_1700_B.e_4240_b().h_1847_R();
        }

        @Override
        public void P_1922_E() {
            this.n_1700_B.c_3005_b().n_1700_B(this.R_4764_Y, (float)(this.n_1700_B.H_1990_U() + 20), (float)this.n_1700_B.Z_976_R());
            if (this.n_1700_B.G_564_y((N_4263_v)this.R_4764_Y) < 6.25) {
                this.n_1700_B.e_4240_b().h_1847_R();
            } else {
                this.n_1700_B.e_4240_b().n_1700_B((N_4263_v)this.R_4764_Y, this.J_1907_R);
            }
            if (this.R_4764_Y.C_1269_X() && this.R_4764_Y.O_508_d.w_1457_N.nextInt(6) == 0) {
                this.R_4764_Y.n_1700_B(new k_2610_C(MobEffects.Y_1740_V, 100));
            }
        }
    }

    class J_1907_R
    extends Goal {
        private int J_1907_R;

        private J_1907_R() {
        }

        @Override
        public boolean n_1700_B() {
            if (this.J_1907_R > Y_559_r.this.RealmsWorldResetDto) {
                return false;
            }
            List<n_1494_c> list = Y_559_r.this.O_508_d.n_1700_B(n_1494_c.class, Y_559_r.this.i_601_W().grow(8.0, 8.0, 8.0), n_1700_B);
            return !list.isEmpty() || !Y_559_r.this.J_1907_R(e_1174_E.n_1700_B).n_1700_B();
        }

        @Override
        public void R_4764_Y() {
            List<n_1494_c> list = Y_559_r.this.O_508_d.n_1700_B(n_1494_c.class, Y_559_r.this.i_601_W().grow(8.0, 8.0, 8.0), n_1700_B);
            if (!list.isEmpty()) {
                Y_559_r.this.e_4240_b().n_1700_B((N_4263_v)list.get(0), (double)1.2f);
                Y_559_r.this.n_1700_B(SoundEvents.A_3244_K, 1.0f, 1.0f);
            }
            this.J_1907_R = 0;
        }

        @Override
        public void G_564_y() {
            Z_1993_T itemstack = Y_559_r.this.J_1907_R(e_1174_E.n_1700_B);
            if (!itemstack.n_1700_B()) {
                this.n_1700_B(itemstack);
                Y_559_r.this.n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
                this.J_1907_R = Y_559_r.this.RealmsWorldResetDto + Y_559_r.this.RealmsWorldOptions.nextInt(100);
            }
        }

        @Override
        public void P_1922_E() {
            List<n_1494_c> list = Y_559_r.this.O_508_d.n_1700_B(n_1494_c.class, Y_559_r.this.i_601_W().grow(8.0, 8.0, 8.0), n_1700_B);
            Z_1993_T itemstack = Y_559_r.this.J_1907_R(e_1174_E.n_1700_B);
            if (!itemstack.n_1700_B()) {
                this.n_1700_B(itemstack);
                Y_559_r.this.n_1700_B(e_1174_E.n_1700_B, Z_1993_T.J_1907_R);
            } else if (!list.isEmpty()) {
                Y_559_r.this.e_4240_b().n_1700_B((N_4263_v)list.get(0), (double)1.2f);
            }
        }

        private void n_1700_B(Z_1993_T p_220810_1_) {
            if (!p_220810_1_.n_1700_B()) {
                double d0 = Y_559_r.this.X_2048_Y() - (double)0.3f;
                n_1494_c itementity = new n_1494_c(Y_559_r.this.O_508_d, Y_559_r.this.O_3598_v(), d0, Y_559_r.this.l_2647_k(), p_220810_1_);
                itementity.n_1700_B(40);
                itementity.R_4764_Y(Y_559_r.this.w_2705_t());
                float f = 0.3f;
                float f1 = Y_559_r.this.RealmsWorldOptions.nextFloat() * ((float)Math.PI * 2);
                float f2 = 0.02f * Y_559_r.this.RealmsWorldOptions.nextFloat();
                itementity.h_1847_R(0.3f * -u_530_F.n_1700_B(Y_559_r.this.p_178_J * ((float)Math.PI / 180)) * u_530_F.J_1907_R(Y_559_r.this.f_4016_n * ((float)Math.PI / 180)) + u_530_F.J_1907_R(f1) * f2, 0.3f * u_530_F.n_1700_B(Y_559_r.this.f_4016_n * ((float)Math.PI / 180)) * 1.5f, 0.3f * u_530_F.J_1907_R(Y_559_r.this.p_178_J * ((float)Math.PI / 180)) * u_530_F.J_1907_R(Y_559_r.this.f_4016_n * ((float)Math.PI / 180)) + u_530_F.n_1700_B(f1) * f2);
                Y_559_r.this.O_508_d.a_(itementity);
            }
        }
    }
}


