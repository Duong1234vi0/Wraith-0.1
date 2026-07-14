/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.LinkedHashSet;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.PathNavigation;
import lightning.product.RandomLookAroundGoal;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.D_3856_V;
import lightning.product.D_38_f;
import lightning.product.RandomStrollGoal;
import lightning.product.BlockGetter;
import lightning.product.F_4355_q;
import lightning.product.G_652_w;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_1869_h;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.PanicGoal;
import lightning.product.Animal;
import lightning.product.Saddleable;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.MoveToBlockGoal;
import lightning.product.TemptGoal;
import lightning.product.T_1316_M;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.CollisionContext;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.Z_535_q;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.i_2099_H;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.PathfinderMob;
import lightning.product.BlockTags;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.s_3834_w;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.LookAtPlayerGoal;
import lightning.product.v_2621_q;
import lightning.product.ItemSteerable;
import lightning.product.x_1688_C;
import lightning.product.ItemBasedSteering;

public class L_3233_K
extends Animal
implements Saddleable,
ItemSteerable {
    private static final b_3278_X h_1847_R = b_3278_X.n_1700_B(Items.J_739_q);
    private static final b_3278_X Q_4569_t = b_3278_X.n_1700_B(Items.J_739_q, Items.j_3599_p);
    private static final h_256_u<Integer> M_182_A = C_4114_x.n_1700_B(L_3233_K.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Boolean> t_1786_h = C_4114_x.n_1700_B(L_3233_K.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> multiplayerClientSuggestionProvider = C_4114_x.n_1700_B(L_3233_K.class, EntityDataSerializers.t_148_a);
    private final ItemBasedSteering w_1457_N;
    private TemptGoal Y_601_j;
    private PanicGoal Y_259_p;

    public L_3233_K(t_5_h<? extends L_3233_K> p_i231562_1_, b_4507_u p_i231562_2_) {
        super((t_5_h<? extends Animal>)p_i231562_1_, p_i231562_2_);
        this.w_1457_N = new ItemBasedSteering(this.l_4537_E, M_182_A, multiplayerClientSuggestionProvider);
        this.s_2632_s = true;
        this.n_1700_B(I_1869_h.w_1484_f, -1.0f);
        this.n_1700_B(I_1869_h.v_4262_N, 0.0f);
        this.n_1700_B(I_1869_h.M_588_G, 0.0f);
        this.n_1700_B(I_1869_h.P_4830_p, 0.0f);
    }

    public static boolean J_1907_R(t_5_h<L_3233_K> p_234314_0_, LevelAccessor p_234314_1_, a_3160_D p_234314_2_, c_1514_x p_234314_3_, Random p_234314_4_) {
        c_1514_x.n_1700_B blockpos$mutable = p_234314_3_.toMutable();
        do {
            blockpos$mutable.n_1700_B(b_257_Y.J_1907_R);
        } while (p_234314_1_.getFluidState(blockpos$mutable).n_1700_B(FluidTags.R_4764_Y));
        return p_234314_1_.getBlockState(blockpos$mutable).v_4262_N();
    }

    @Override
    public void n_1700_B(h_256_u<?> key) {
        if (M_182_A.equals(key) && this.O_508_d.Y_259_p) {
            this.w_1457_N.n_1700_B();
        }
        super.n_1700_B(key);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(M_182_A, 0);
        this.l_4537_E.n_1700_B(t_1786_h, false);
        this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider, false);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        this.w_1457_N.n_1700_B(compound);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.w_1457_N.J_1907_R(compound);
    }

    @Override
    public boolean G_564_y() {
        return this.w_1457_N.J_1907_R();
    }

    @Override
    public boolean n_1700_B() {
        return this.RealmsLongRunningMcoTaskScreen() && !this.d_();
    }

    @Override
    public void n_1700_B(@Nullable D_38_f p_230266_1_) {
        this.w_1457_N.n_1700_B(true);
        if (p_230266_1_ != null) {
            this.O_508_d.n_1700_B((a_3913_L)null, this, SoundEvents.BeetrootBlock, p_230266_1_, 0.5f, 1.0f);
        }
    }

    @Override
    protected void M_182_A() {
        this.Y_259_p = new PanicGoal(this, 1.65);
        this.s_956_w.n_1700_B(1, this.Y_259_p);
        this.s_956_w.n_1700_B(2, new BreedGoal(this, 1.0));
        this.Y_601_j = new TemptGoal((PathfinderMob)this, 1.4, false, Q_4569_t);
        this.s_956_w.n_1700_B(3, this.Y_601_j);
        this.s_956_w.n_1700_B(4, new J_1907_R(this, 1.5));
        this.s_956_w.n_1700_B(5, new v_2621_q(this, 1.1));
        this.s_956_w.n_1700_B(7, new RandomStrollGoal(this, 1.0, 60));
        this.s_956_w.n_1700_B(8, new LookAtPlayerGoal(this, a_3913_L.class, 8.0f));
        this.s_956_w.n_1700_B(8, new RandomLookAroundGoal(this));
        this.s_956_w.n_1700_B(9, new LookAtPlayerGoal(this, L_3233_K.class, 8.0f));
    }

    public void w_1457_N(boolean p_234319_1_) {
        this.l_4537_E.J_1907_R(t_1786_h, p_234319_1_);
    }

    public boolean y_4642_Y() {
        return this.l_3609_d() instanceof L_3233_K ? ((L_3233_K)this.l_3609_d()).y_4642_Y() : this.l_4537_E.n_1700_B(t_1786_h).booleanValue();
    }

    @Override
    public boolean n_1700_B(Fluid p_230285_1_) {
        return p_230285_1_.n_1700_B(FluidTags.R_4764_Y);
    }

    @Override
    public double s_1671_u() {
        float f = Math.min(0.25f, this.G_424_k);
        float f1 = this.RealmsSettingsScreen;
        return (double)this.v_165_F() - 0.19 + (double)(0.12f * u_530_F.J_1907_R(f1 * 1.5f) * 2.0f * f);
    }

    @Override
    public boolean g_2268_R() {
        N_4263_v entity = this.n_3864_h();
        if (!(entity instanceof a_3913_L)) {
            return false;
        }
        a_3913_L playerentity = (a_3913_L)entity;
        return playerentity.A_2714_y().J_1907_R() == Items.j_3599_p || playerentity.S_4035_N().J_1907_R() == Items.j_3599_p;
    }

    @Override
    public boolean n_1700_B(T_1316_M worldIn) {
        return worldIn.P_1922_E(this);
    }

    @Override
    @Nullable
    public N_4263_v n_3864_h() {
        return this.o_3599_Z().isEmpty() ? null : this.o_3599_Z().get(0);
    }

    @Override
    public e_2866_D b_(r_4811_B livingEntity) {
        e_2866_D[] avector3d = new e_2866_D[]{L_3233_K.n_1700_B(this.C_415_h(), (double)livingEntity.C_415_h(), livingEntity.p_178_J), L_3233_K.n_1700_B(this.C_415_h(), (double)livingEntity.C_415_h(), livingEntity.p_178_J - 22.5f), L_3233_K.n_1700_B(this.C_415_h(), (double)livingEntity.C_415_h(), livingEntity.p_178_J + 22.5f), L_3233_K.n_1700_B(this.C_415_h(), (double)livingEntity.C_415_h(), livingEntity.p_178_J - 45.0f), L_3233_K.n_1700_B(this.C_415_h(), (double)livingEntity.C_415_h(), livingEntity.p_178_J + 45.0f)};
        LinkedHashSet set = Sets.newLinkedHashSet();
        double d0 = this.i_601_W().maxY;
        double d1 = this.i_601_W().minY - 0.5;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (e_2866_D vector3d : avector3d) {
            blockpos$mutable.n_1700_B(this.O_3598_v() + vector3d.J_1907_R, d0, this.l_2647_k() + vector3d.G_564_y);
            for (double d2 = d0; d2 > d1; d2 -= 1.0) {
                set.add(blockpos$mutable.toImmutable());
                blockpos$mutable.n_1700_B(b_257_Y.n_1700_B);
            }
        }
        for (c_1514_x blockpos : set) {
            double d3;
            if (this.O_508_d.getFluidState(blockpos).n_1700_B(FluidTags.R_4764_Y) || !G_652_w.n_1700_B(d3 = this.O_508_d.G_564_y(blockpos))) continue;
            e_2866_D vector3d1 = e_2866_D.n_1700_B(blockpos, d3);
            for (I_1170_F pose : livingEntity.x_2635_q()) {
                I_4817_s axisalignedbb = livingEntity.u_1723_Y(pose);
                if (!G_652_w.n_1700_B(this.O_508_d, livingEntity, axisalignedbb.offset(vector3d1))) continue;
                livingEntity.J_1907_R(pose);
                return vector3d1;
            }
        }
        return new e_2866_D(this.O_3598_v(), this.i_601_W().maxY, this.l_2647_k());
    }

    @Override
    public void w_1484_f(e_2866_D travelVector) {
        this.w_1457_N(this.h_1640_b());
        this.n_1700_B(this, this.w_1457_N, travelVector);
    }

    public float h_1640_b() {
        return (float)this.J_1907_R(Attributes.G_564_y) * (this.y_4642_Y() ? 0.66f : 1.0f);
    }

    @Override
    public float u_1723_Y() {
        return (float)this.J_1907_R(Attributes.G_564_y) * (this.y_4642_Y() ? 0.23f : 0.55f);
    }

    @Override
    public void n_1700_B(e_2866_D travelVec) {
        super.w_1484_f(travelVec);
    }

    @Override
    protected float R_3908_n() {
        return this.V_1225_t + 0.6f;
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        this.n_1700_B(this.W_3464_O() ? SoundEvents.J_2868_p : SoundEvents.BeaconBlock, 1.0f, 1.0f);
    }

    @Override
    public boolean P_1922_E() {
        return this.w_1457_N.n_1700_B(this.M_3508_C());
    }

    @Override
    protected void n_1700_B(double y, boolean onGroundIn, K_4074_S state, c_1514_x pos) {
        this.F_2624_D();
        if (this.W_3464_O()) {
            this.U_1241_n = 0.0f;
        } else {
            super.n_1700_B(y, onGroundIn, state, pos);
        }
    }

    @Override
    public void v_() {
        if (this.J_3635_s() && this.RealmsWorldOptions.nextInt(140) == 0) {
            this.n_1700_B(SoundEvents.BambooSaplingBlock, 1.0f, this.O_2761_o());
        } else if (this.y_2447_C() && this.RealmsWorldOptions.nextInt(60) == 0) {
            this.n_1700_B(SoundEvents.b_1320_N, 1.0f, this.O_2761_o());
        }
        K_4074_S blockstate = this.O_508_d.getBlockState(this.b_2312_j());
        K_4074_S blockstate1 = this.g_4106_L();
        boolean flag = blockstate.n_1700_B(BlockTags.V_1225_t) || blockstate1.n_1700_B(BlockTags.V_1225_t) || this.J_1907_R(FluidTags.R_4764_Y) > 0.0;
        this.w_1457_N(!flag);
        super.v_();
        this.V_537_k();
        this.F_2624_D();
    }

    private boolean y_2447_C() {
        return this.Y_259_p != null && this.Y_259_p.w_1484_f();
    }

    private boolean J_3635_s() {
        return this.Y_601_j != null && this.Y_601_j.w_1484_f();
    }

    @Override
    protected boolean C_2741_M() {
        return true;
    }

    private void V_537_k() {
        if (this.W_3464_O()) {
            CollisionContext iselectioncontext = CollisionContext.n_1700_B(this);
            if (iselectioncontext.n_1700_B(s_3834_w.Q_4569_t, this.b_2312_j(), true) && !this.O_508_d.getFluidState(this.b_2312_j().up()).n_1700_B(FluidTags.R_4764_Y)) {
                this.e_1992_r = true;
            } else {
                this.v_4262_N(this.I_4348_c().n_1700_B(0.5).J_1907_R(0.0, 0.05, 0.0));
            }
        }
    }

    public static s_1415_m.n_1700_B V_1176_p() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.G_564_y, 0.175f).n_1700_B(Attributes.J_1907_R, 16.0);
    }

    @Override
    protected SoundEvent z_4693_k() {
        return !this.y_2447_C() && !this.J_3635_s() ? SoundEvents.E_872_n : null;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.BarrierBlock;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.q_36_y;
    }

    @Override
    protected boolean h_1847_R(N_4263_v passenger) {
        return this.o_3599_Z().isEmpty() && !((N_4263_v)this).n_1700_B(FluidTags.R_4764_Y);
    }

    @Override
    public boolean e_1231_S() {
        return true;
    }

    @Override
    public boolean RealmsPersistence() {
        return false;
    }

    @Override
    protected PathNavigation J_1907_R(b_4507_u worldIn) {
        return new n_1700_B(this, worldIn);
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        if (worldIn.getBlockState(pos).P_4830_p().n_1700_B(FluidTags.R_4764_Y)) {
            return 10.0f;
        }
        return this.W_3464_O() ? Float.NEGATIVE_INFINITY : 0.0f;
    }

    public L_3233_K J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.RealmsWorldOptions.n_1700_B(p_241840_1_);
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return h_1847_R.n_1700_B(stack);
    }

    @Override
    protected void A_229_v() {
        super.A_229_v();
        if (this.G_564_y()) {
            this.n_1700_B((q_1803_e)Items.Z_361_l);
        }
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
        if (flag && !this.y_1700_S()) {
            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), SoundEvents.v_1577_d, this.r_2478_U(), 1.0f, 1.0f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f);
        }
        return actionresulttype;
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.6f * this.X_1313_W(), this.C_415_h() * 0.4f);
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        V_3157_k object;
        if (this.d_()) {
            return super.n_1700_B(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        }
        if (this.RealmsWorldOptions.nextInt(30) == 0) {
            Z_530_i mobentity = t_5_h.c_132_F.n_1700_B(worldIn.J_1907_R());
            object = this.n_1700_B(worldIn, difficultyIn, mobentity, new F_4355_q.J_1907_R(F_4355_q.n_1700_B(this.RealmsWorldOptions), false));
            mobentity.n_1700_B(e_1174_E.n_1700_B, new Z_1993_T(Items.j_3599_p));
            this.n_1700_B((D_38_f)null);
        } else if (this.RealmsWorldOptions.nextInt(10) == 0) {
            AgableMob ageableentity = t_5_h.RealmsWorldOptions.n_1700_B(worldIn.J_1907_R());
            ageableentity.b_(-24000);
            object = this.n_1700_B(worldIn, difficultyIn, ageableentity, (V_3157_k)null);
        } else {
            object = new AgableMob.n_1700_B(0.5f);
        }
        return super.n_1700_B(worldIn, difficultyIn, reason, object, dataTag);
    }

    private V_3157_k n_1700_B(ServerLevelAccessor p_242331_1_, DifficultyInstance p_242331_2_, Z_530_i p_242331_3_, @Nullable V_3157_k p_242331_4_) {
        p_242331_3_.J_1907_R(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.p_178_J, 0.0f);
        p_242331_3_.n_1700_B(p_242331_1_, p_242331_2_, a_3160_D.v_4262_N, p_242331_4_, null);
        p_242331_3_.n_1700_B((N_4263_v)this, true);
        return new AgableMob.n_1700_B(0.0f);
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }

    static class J_1907_R
    extends MoveToBlockGoal {
        private final L_3233_K v_4262_N;

        private J_1907_R(L_3233_K p_i241913_1_, double p_i241913_2_) {
            super(p_i241913_1_, p_i241913_2_, 8, 2);
            this.v_4262_N = p_i241913_1_;
        }

        @Override
        public c_1514_x s_956_w() {
            return this.P_1922_E;
        }

        @Override
        public boolean J_1907_R() {
            return !this.v_4262_N.W_3464_O() && this.n_1700_B(this.v_4262_N.O_508_d, this.P_1922_E);
        }

        @Override
        public boolean n_1700_B() {
            return !this.v_4262_N.W_3464_O() && super.n_1700_B();
        }

        @Override
        public boolean u_2550_I() {
            return this.G_564_y % 20 == 0;
        }

        @Override
        protected boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
            return worldIn.getBlockState(pos).n_1700_B(a_3742_W.H_2857_Y) && worldIn.getBlockState(pos.up()).n_1700_B((BlockGetter)worldIn, pos, t_3546_P.n_1700_B);
        }
    }

    static class n_1700_B
    extends i_2099_H {
        n_1700_B(L_3233_K p_i231565_1_, b_4507_u p_i231565_2_) {
            super(p_i231565_1_, p_i231565_2_);
        }

        @Override
        protected D_3856_V n_1700_B(int p_179679_1_) {
            this.M_182_A = new Z_535_q();
            return new D_3856_V(this.M_182_A, p_179679_1_);
        }

        @Override
        protected boolean n_1700_B(I_1869_h p_230287_1_) {
            return p_230287_1_ != I_1869_h.v_4262_N && p_230287_1_ != I_1869_h.P_4830_p && p_230287_1_ != I_1869_h.M_588_G ? super.n_1700_B(p_230287_1_) : true;
        }

        @Override
        public boolean n_1700_B(c_1514_x pos) {
            return this.R_4764_Y.getBlockState(pos).n_1700_B(a_3742_W.H_2857_Y) || super.n_1700_B(pos);
        }
    }
}


