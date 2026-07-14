/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.PathNavigation;
import lightning.product.DebugPackets;
import lightning.product.C_4114_x;
import lightning.product.AgableMob;
import lightning.product.D_3746_J;
import lightning.product.FlyingMoveControl;
import lightning.product.CropBlock;
import lightning.product.F_997_G;
import lightning.product.G_3246_f;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.I_1869_h;
import lightning.product.LookControl;
import lightning.product.J_2548_M;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.Animal;
import lightning.product.ItemTags;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.BreedGoal;
import lightning.product.R_1815_U;
import lightning.product.ResetUniversalAngerTargetGoal;
import lightning.product.TemptGoal;
import lightning.product.R_2450_T;
import lightning.product.R_4053_F;
import lightning.product.ParticleOptions;
import lightning.product.T_1316_M;
import lightning.product.FloatGoal;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.W_3371_U;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.FlyingPathNavigation;
import lightning.product.b_1722_e;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.b_4946_z;
import lightning.product.b_4953_N;
import lightning.product.c_1514_x;
import lightning.product.NearestAttackableTargetGoal;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.TimeUtil;
import lightning.product.SweetBerryBushBlock;
import lightning.product.MobType;
import lightning.product.g_3212_H;
import lightning.product.g_3408_G;
import lightning.product.g_88_D;
import lightning.product.h_256_u;
import lightning.product.i_2154_H;
import lightning.product.EntityDataSerializers;
import lightning.product.k_2610_C;
import lightning.product.PathfinderMob;
import lightning.product.n_3832_I;
import lightning.product.BlockTags;
import lightning.product.Goal;
import lightning.product.DoublePlantBlock;
import lightning.product.q_2232_A;
import lightning.product.r_109_r;
import lightning.product.BlockEntityType;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.v_2621_q;
import lightning.product.y_2339_p;

public class b_1913_J
extends Animal
implements G_3246_f,
R_4053_F {
    private static final h_256_u<Byte> h_1847_R = C_4114_x.n_1700_B(b_1913_J.class, EntityDataSerializers.n_1700_B);
    private static final h_256_u<Integer> Q_4569_t = C_4114_x.n_1700_B(b_1913_J.class, EntityDataSerializers.J_1907_R);
    private static final J_2548_M M_182_A = TimeUtil.n_1700_B(20, 39);
    private UUID t_1786_h;
    private float multiplayerClientSuggestionProvider;
    private float w_1457_N;
    private int Y_601_j;
    private int Y_259_p;
    private int Q_2552_b;
    private int C_2741_M;
    private int k_2293_S = 0;
    private int q_2307_F = 0;
    @Nullable
    private c_1514_x Z_875_P = null;
    @Nullable
    private c_1514_x c_3005_b = null;
    private t_148_a H_2857_Y;
    private P_1922_E A_4115_X;
    private u_1723_Y Y_1740_V;
    private int t_4043_B;

    public b_1913_J(t_5_h<? extends b_1913_J> type, b_4507_u worldIn) {
        super((t_5_h<? extends Animal>)type, worldIn);
        this.v_4262_N = new FlyingMoveControl(this, 20, true);
        this.u_1723_Y = new R_4764_Y(this);
        this.n_1700_B(I_1869_h.M_588_G, -1.0f);
        this.n_1700_B(I_1869_h.w_1484_f, -1.0f);
        this.n_1700_B(I_1869_h.t_148_a, 16.0f);
        this.n_1700_B(I_1869_h.k_2293_S, -1.0f);
        this.n_1700_B(I_1869_h.u_1723_Y, -1.0f);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(h_1847_R, (byte)0);
        this.l_4537_E.n_1700_B(Q_4569_t, 0);
    }

    @Override
    public float n_1700_B(c_1514_x pos, T_1316_M worldIn) {
        return worldIn.getBlockState(pos).v_4262_N() ? 10.0f : 0.0f;
    }

    @Override
    protected void M_182_A() {
        this.s_956_w.n_1700_B(0, new s_956_w(this, 1.4f, true));
        this.s_956_w.n_1700_B(1, new G_564_y());
        this.s_956_w.n_1700_B(2, new BreedGoal(this, 1.0));
        this.s_956_w.n_1700_B(3, new TemptGoal((PathfinderMob)this, 1.25, b_3278_X.n_1700_B(ItemTags.G_624_v), false));
        this.H_2857_Y = new t_148_a();
        this.s_956_w.n_1700_B(4, this.H_2857_Y);
        this.s_956_w.n_1700_B(5, new v_2621_q(this, 1.25));
        this.s_956_w.n_1700_B(5, new u_2550_I());
        this.A_4115_X = new P_1922_E();
        this.s_956_w.n_1700_B(5, this.A_4115_X);
        this.Y_1740_V = new u_1723_Y();
        this.s_956_w.n_1700_B(6, this.Y_1740_V);
        this.s_956_w.n_1700_B(7, new v_4262_N());
        this.s_956_w.n_1700_B(8, new M_588_G());
        this.s_956_w.n_1700_B(9, new FloatGoal(this));
        this.u_2550_I.n_1700_B(1, new n_1700_B(this).n_1700_B(new Class[0]));
        this.u_2550_I.n_1700_B(2, new J_1907_R(this));
        this.u_2550_I.n_1700_B(3, new ResetUniversalAngerTargetGoal<b_1913_J>(this, true));
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.y_2447_C()) {
            compound.n_1700_B("HivePos", n_3832_I.n_1700_B(this.J_3635_s()));
        }
        if (this.h_1640_b()) {
            compound.n_1700_B("FlowerPos", n_3832_I.n_1700_B(this.y_4642_Y()));
        }
        compound.n_1700_B("HasNectar", this.V_537_k());
        compound.n_1700_B("HasStung", this.c_2086_l());
        compound.J_1907_R("TicksSincePollination", this.Y_259_p);
        compound.J_1907_R("CannotEnterHiveTicks", this.Q_2552_b);
        compound.J_1907_R("CropsGrownSincePollination", this.C_2741_M);
        this.a_(compound);
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        this.c_3005_b = null;
        if (compound.P_1922_E("HivePos")) {
            this.c_3005_b = n_3832_I.J_1907_R(compound.M_182_A("HivePos"));
        }
        this.Z_875_P = null;
        if (compound.P_1922_E("FlowerPos")) {
            this.Z_875_P = n_3832_I.J_1907_R(compound.M_182_A("FlowerPos"));
        }
        super.J_1907_R(compound);
        this.w_1457_N(compound.t_1786_h("HasNectar"));
        this.Y_601_j(compound.t_1786_h("HasStung"));
        this.Y_259_p = compound.w_1484_f("TicksSincePollination");
        this.Q_2552_b = compound.w_1484_f("CannotEnterHiveTicks");
        this.C_2741_M = compound.w_1484_f("CropsGrownSincePollination");
        this.n_1700_B((e_3591_l)this.O_508_d, compound);
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        boolean flag = entityIn.n_1700_B(P_11_z.J_1907_R(this), (float)((int)this.J_1907_R(Attributes.u_1723_Y)));
        if (flag) {
            this.n_1700_B((r_4811_B)this, entityIn);
            if (entityIn instanceof r_4811_B) {
                ((r_4811_B)entityIn).h_1847_R(((r_4811_B)entityIn).U_4087_m() + 1);
                int i = 0;
                if (this.O_508_d.x_607_J() == R_2450_T.R_4764_Y) {
                    i = 10;
                } else if (this.O_508_d.x_607_J() == R_2450_T.G_564_y) {
                    i = 18;
                }
                if (i > 0) {
                    ((r_4811_B)entityIn).n_1700_B(new k_2610_C(MobEffects.w_1457_N, i * 20, 0));
                }
            }
            this.Y_601_j(true);
            this.A_();
            this.n_1700_B(SoundEvents.RealmsServerPing, 1.0f, 1.0f);
        }
        return flag;
    }

    @Override
    public void v_() {
        super.v_();
        if (this.V_537_k() && this.ModuleCategory() < 10 && this.RealmsWorldOptions.nextFloat() < 0.05f) {
            for (int i = 0; i < this.RealmsWorldOptions.nextInt(2) + 1; ++i) {
                this.n_1700_B(this.O_508_d, this.O_3598_v() - (double)0.3f, this.O_3598_v() + (double)0.3f, this.l_2647_k() - (double)0.3f, this.l_2647_k() + (double)0.3f, this.P_1922_E(0.5), ParticleTypes.RealmsClientConfig);
            }
        }
        this.D_3612_q();
    }

    private void n_1700_B(b_4507_u worldIn, double p_226397_2_, double p_226397_4_, double p_226397_6_, double p_226397_8_, double posY, ParticleOptions particleData) {
        worldIn.n_1700_B(particleData, u_530_F.G_564_y(worldIn.w_1457_N.nextDouble(), p_226397_2_, p_226397_4_), posY, u_530_F.G_564_y(worldIn.w_1457_N.nextDouble(), p_226397_6_, p_226397_8_), 0.0, 0.0, 0.0);
    }

    private void w_1484_f(c_1514_x pos) {
        e_2866_D vector3d1;
        e_2866_D vector3d = e_2866_D.R_4764_Y(pos);
        int i = 0;
        c_1514_x blockpos = this.b_2312_j();
        int j = (int)vector3d.R_4764_Y - blockpos.getY();
        if (j > 2) {
            i = 4;
        } else if (j < -2) {
            i = -4;
        }
        int k = 6;
        int l = 8;
        int i1 = blockpos.manhattanDistance(pos);
        if (i1 < 15) {
            k = i1 / 2;
            l = i1 / 2;
        }
        if ((vector3d1 = W_3371_U.J_1907_R(this, k, l, i, vector3d, 0.3141592741012573)) != null) {
            this.t_148_a.n_1700_B(0.5f);
            this.t_148_a.n_1700_B(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y, 1.0);
        }
    }

    @Nullable
    public c_1514_x y_4642_Y() {
        return this.Z_875_P;
    }

    public boolean h_1640_b() {
        return this.Z_875_P != null;
    }

    public void v_4262_N(c_1514_x pos) {
        this.Z_875_P = pos;
    }

    private boolean y_3417_N() {
        return this.Y_259_p > 3600;
    }

    private boolean A_1306_N() {
        if (this.Q_2552_b <= 0 && !this.H_2857_Y.u_2550_I() && !this.c_2086_l() && this.t_148_a() == null) {
            boolean flag = this.y_3417_N() || this.O_508_d.c_4037_x() || this.O_508_d.z_4693_k() || this.V_537_k();
            return flag && !this.R_2822_N();
        }
        return false;
    }

    public void Y_601_j(int p_226450_1_) {
        this.Q_2552_b = p_226450_1_;
    }

    public float c_3005_b(float p_226455_1_) {
        return u_530_F.v_4262_N(p_226455_1_, this.w_1457_N, this.multiplayerClientSuggestionProvider);
    }

    private void D_3612_q() {
        this.w_1457_N = this.multiplayerClientSuggestionProvider;
        this.multiplayerClientSuggestionProvider = this.Setting() ? Math.min(1.0f, this.multiplayerClientSuggestionProvider + 0.2f) : Math.max(0.0f, this.multiplayerClientSuggestionProvider - 0.24f);
    }

    @Override
    protected void X_933_l() {
        boolean flag = this.c_2086_l();
        this.t_4043_B = this.S_980_j() ? ++this.t_4043_B : 0;
        if (this.t_4043_B > 20) {
            this.n_1700_B(P_11_z.w_1484_f, 1.0f);
        }
        if (flag) {
            ++this.Y_601_j;
            if (this.Y_601_j % 5 == 0 && this.RealmsWorldOptions.nextInt(u_530_F.n_1700_B(1200 - this.Y_601_j, 1, 1200)) == 0) {
                this.n_1700_B(P_11_z.h_1847_R, this.g_46_E());
            }
        }
        if (!this.V_537_k()) {
            ++this.Y_259_p;
        }
        if (!this.O_508_d.Y_259_p) {
            this.n_1700_B((e_3591_l)this.O_508_d, false);
        }
    }

    public void V_1176_p() {
        this.Y_259_p = 0;
    }

    private boolean R_2822_N() {
        if (this.c_3005_b == null) {
            return false;
        }
        i_2154_H tileentity = this.O_508_d.getTileEntity(this.c_3005_b);
        return tileentity instanceof F_997_G && ((F_997_G)tileentity).n_1700_B();
    }

    @Override
    public int n_1700_B() {
        return this.l_4537_E.n_1700_B(Q_4569_t);
    }

    @Override
    public void n_1700_B(int time) {
        this.l_4537_E.J_1907_R(Q_4569_t, time);
    }

    @Override
    public UUID G_564_y() {
        return this.t_1786_h;
    }

    @Override
    public void n_1700_B(@Nullable UUID target) {
        this.t_1786_h = target;
    }

    @Override
    public void P_1922_E() {
        this.n_1700_B(M_182_A.n_1700_B(this.RealmsWorldOptions));
    }

    private boolean t_148_a(c_1514_x pos) {
        i_2154_H tileentity = this.O_508_d.getTileEntity(pos);
        if (tileentity instanceof F_997_G) {
            return !((F_997_G)tileentity).w_1484_f();
        }
        return false;
    }

    public boolean y_2447_C() {
        return this.c_3005_b != null;
    }

    @Nullable
    public c_1514_x J_3635_s() {
        return this.c_3005_b;
    }

    @Override
    protected void g_164_R() {
        super.g_164_R();
        DebugPackets.n_1700_B(this);
    }

    private int ModuleCategory() {
        return this.C_2741_M;
    }

    private void p_1458_L() {
        this.C_2741_M = 0;
    }

    private void Module() {
        ++this.C_2741_M;
    }

    @Override
    public void Y_1740_V() {
        super.Y_1740_V();
        if (!this.O_508_d.Y_259_p) {
            if (this.Q_2552_b > 0) {
                --this.Q_2552_b;
            }
            if (this.k_2293_S > 0) {
                --this.k_2293_S;
            }
            if (this.q_2307_F > 0) {
                --this.q_2307_F;
            }
            boolean flag = this.B_() && !this.c_2086_l() && this.t_148_a() != null && this.t_148_a().G_564_y((N_4263_v)this) < 4.0;
            this.Y_259_p(flag);
            if (this.RealmsWorldResetDto % 20 == 0 && !this.ModuleManager()) {
                this.c_3005_b = null;
            }
        }
    }

    private boolean ModuleManager() {
        if (!this.y_2447_C()) {
            return false;
        }
        i_2154_H tileentity = this.O_508_d.getTileEntity(this.c_3005_b);
        return tileentity != null && tileentity.z_1737_N() == BlockEntityType.e_4240_b;
    }

    public boolean V_537_k() {
        return this.Y_259_p(8);
    }

    private void w_1457_N(boolean p_226447_1_) {
        if (p_226447_1_) {
            this.V_1176_p();
        }
        this.G_564_y(8, p_226447_1_);
    }

    public boolean c_2086_l() {
        return this.Y_259_p(4);
    }

    private void Y_601_j(boolean p_226449_1_) {
        this.G_564_y(4, p_226449_1_);
    }

    private boolean Setting() {
        return this.Y_259_p(2);
    }

    private void Y_259_p(boolean p_226452_1_) {
        this.G_564_y(2, p_226452_1_);
    }

    private boolean s_956_w(c_1514_x pos) {
        return !this.J_1907_R(pos, 32);
    }

    private void G_564_y(int flagId, boolean p_226404_2_) {
        if (p_226404_2_) {
            this.l_4537_E.J_1907_R(h_1847_R, (byte)(this.l_4537_E.n_1700_B(h_1847_R) | flagId));
        } else {
            this.l_4537_E.J_1907_R(h_1847_R, (byte)(this.l_4537_E.n_1700_B(h_1847_R) & ~flagId));
        }
    }

    private boolean Y_259_p(int flagId) {
        return (this.l_4537_E.n_1700_B(h_1847_R) & flagId) != 0;
    }

    public static s_1415_m.n_1700_B o_4117_e() {
        return Z_530_i.multiplayerClientSuggestionProvider().n_1700_B(Attributes.n_1700_B, 10.0).n_1700_B(Attributes.P_1922_E, 0.6f).n_1700_B(Attributes.G_564_y, 0.3f).n_1700_B(Attributes.u_1723_Y, 2.0).n_1700_B(Attributes.J_1907_R, 48.0);
    }

    @Override
    protected PathNavigation J_1907_R(b_4507_u worldIn) {
        FlyingPathNavigation flyingpathnavigator = new FlyingPathNavigation(this, worldIn){

            @Override
            public boolean n_1700_B(c_1514_x pos) {
                return !this.R_4764_Y.getBlockState(pos.down()).v_4262_N();
            }

            @Override
            public void n_1700_B() {
                if (!b_1913_J.this.H_2857_Y.u_2550_I()) {
                    super.n_1700_B();
                }
            }
        };
        flyingpathnavigator.n_1700_B(false);
        flyingpathnavigator.R_4764_Y(false);
        flyingpathnavigator.J_1907_R(true);
        return flyingpathnavigator;
    }

    @Override
    public boolean u_2550_I(Z_1993_T stack) {
        return stack.J_1907_R().n_1700_B(ItemTags.G_624_v);
    }

    private boolean u_2550_I(c_1514_x pos) {
        return this.O_508_d.multiplayerClientSuggestionProvider(pos) && this.O_508_d.getBlockState(pos).J_1907_R().n_1700_B(BlockTags.q_4610_l);
    }

    @Override
    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
    }

    @Override
    protected SoundEvent z_4693_k() {
        return null;
    }

    @Override
    protected SoundEvent P_1922_E(P_11_z damageSourceIn) {
        return SoundEvents.q_1982_R;
    }

    @Override
    protected SoundEvent u_796_y() {
        return SoundEvents.U_1241_n;
    }

    @Override
    protected float d_4500_Q() {
        return 0.4f;
    }

    public b_1913_J J_1907_R(e_3591_l p_241840_1_, AgableMob p_241840_2_) {
        return t_5_h.P_1922_E.n_1700_B(p_241840_1_);
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return this.d_() ? sizeIn.J_1907_R * 0.5f : sizeIn.J_1907_R * 0.5f;
    }

    @Override
    public boolean R_4764_Y(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    protected void n_1700_B(double y, boolean onGroundIn, K_4074_S state, c_1514_x pos) {
    }

    @Override
    protected boolean RealmsDefaultUncaughtExceptionHandler() {
        return true;
    }

    public void U_3758_B() {
        this.w_1457_N(false);
        this.p_1458_L();
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        N_4263_v entity = source.u_2550_I();
        if (!this.O_508_d.Y_259_p) {
            this.H_2857_Y.M_588_G();
        }
        return super.n_1700_B(source, amount);
    }

    @Override
    public MobType F_2860_q() {
        return MobType.R_4764_Y;
    }

    @Override
    protected void R_4764_Y(r_109_r<Fluid> fluidTag) {
        this.v_4262_N(this.I_4348_c().J_1907_R(0.0, 0.01, 0.0));
    }

    @Override
    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, 0.5f * this.X_1313_W(), this.C_415_h() * 0.2f);
    }

    private boolean J_1907_R(c_1514_x pos, int distance) {
        return pos.withinDistance(this.b_2312_j(), (double)distance);
    }

    @Override
    public /* synthetic */ AgableMob n_1700_B(e_3591_l e_3591_l2, AgableMob c_893_i) {
        return this.J_1907_R(e_3591_l2, c_893_i);
    }

    class R_4764_Y
    extends LookControl {
        R_4764_Y(Z_530_i beeIn) {
            super(beeIn);
        }

        @Override
        public void n_1700_B() {
            if (!b_1913_J.this.B_()) {
                super.n_1700_B();
            }
        }

        @Override
        protected boolean J_1907_R() {
            return !b_1913_J.this.H_2857_Y.u_2550_I();
        }
    }

    class s_956_w
    extends b_4953_N {
        s_956_w(PathfinderMob creatureIn, double speedIn, boolean useLongMemory) {
            super(creatureIn, speedIn, useLongMemory);
        }

        @Override
        public boolean n_1700_B() {
            return super.n_1700_B() && b_1913_J.this.B_() && !b_1913_J.this.c_2086_l();
        }

        @Override
        public boolean J_1907_R() {
            return super.J_1907_R() && b_1913_J.this.B_() && !b_1913_J.this.c_2086_l();
        }
    }

    class G_564_y
    extends w_1484_f {
        private G_564_y() {
        }

        @Override
        public boolean v_4262_N() {
            i_2154_H tileentity;
            if (b_1913_J.this.y_2447_C() && b_1913_J.this.A_1306_N() && b_1913_J.this.c_3005_b.withinDistance(b_1913_J.this.s_4990_V(), 2.0) && (tileentity = b_1913_J.this.O_508_d.getTileEntity(b_1913_J.this.c_3005_b)) instanceof F_997_G) {
                F_997_G beehivetileentity = (F_997_G)tileentity;
                if (!beehivetileentity.w_1484_f()) {
                    return true;
                }
                b_1913_J.this.c_3005_b = null;
            }
            return false;
        }

        @Override
        public boolean w_1484_f() {
            return false;
        }

        @Override
        public void R_4764_Y() {
            i_2154_H tileentity = b_1913_J.this.O_508_d.getTileEntity(b_1913_J.this.c_3005_b);
            if (tileentity instanceof F_997_G) {
                F_997_G beehivetileentity = (F_997_G)tileentity;
                beehivetileentity.n_1700_B(b_1913_J.this, b_1913_J.this.V_537_k());
            }
        }
    }

    class t_148_a
    extends w_1484_f {
        private final Predicate<K_4074_S> R_4764_Y;
        private int G_564_y;
        private int P_1922_E;
        private boolean u_1723_Y;
        private e_2866_D v_4262_N;
        private int w_1484_f;

        t_148_a() {
            this.R_4764_Y = p_226499_0_ -> {
                if (p_226499_0_.n_1700_B(BlockTags.T_2506_i)) {
                    if (p_226499_0_.n_1700_B(a_3742_W.V_983_n)) {
                        return p_226499_0_.R_4764_Y(DoublePlantBlock.P_4830_p) == g_3212_H.n_1700_B;
                    }
                    return true;
                }
                return p_226499_0_.n_1700_B(BlockTags.v_4276_D);
            };
            this.G_564_y = 0;
            this.P_1922_E = 0;
            this.w_1484_f = 0;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean v_4262_N() {
            if (b_1913_J.this.q_2307_F > 0) {
                return false;
            }
            if (b_1913_J.this.V_537_k()) {
                return false;
            }
            if (b_1913_J.this.O_508_d.c_4037_x()) {
                return false;
            }
            if (b_1913_J.this.RealmsWorldOptions.nextFloat() < 0.7f) {
                return false;
            }
            Optional<c_1514_x> optional = this.Q_4569_t();
            if (optional.isPresent()) {
                b_1913_J.this.Z_875_P = optional.get();
                b_1913_J.this.t_148_a.n_1700_B((double)b_1913_J.this.Z_875_P.getX() + 0.5, (double)b_1913_J.this.Z_875_P.getY() + 0.5, (double)b_1913_J.this.Z_875_P.getZ() + 0.5, 1.2f);
                return true;
            }
            return false;
        }

        @Override
        public boolean w_1484_f() {
            if (!this.u_1723_Y) {
                return false;
            }
            if (!b_1913_J.this.h_1640_b()) {
                return false;
            }
            if (b_1913_J.this.O_508_d.c_4037_x()) {
                return false;
            }
            if (this.s_956_w()) {
                return b_1913_J.this.RealmsWorldOptions.nextFloat() < 0.2f;
            }
            if (b_1913_J.this.RealmsWorldResetDto % 20 == 0 && !b_1913_J.this.u_2550_I(b_1913_J.this.Z_875_P)) {
                b_1913_J.this.Z_875_P = null;
                return false;
            }
            return true;
        }

        private boolean s_956_w() {
            return this.G_564_y > 400;
        }

        private boolean u_2550_I() {
            return this.u_1723_Y;
        }

        private void M_588_G() {
            this.u_1723_Y = false;
        }

        @Override
        public void R_4764_Y() {
            this.G_564_y = 0;
            this.w_1484_f = 0;
            this.P_1922_E = 0;
            this.u_1723_Y = true;
            b_1913_J.this.V_1176_p();
        }

        @Override
        public void G_564_y() {
            if (this.s_956_w()) {
                b_1913_J.this.w_1457_N(true);
            }
            this.u_1723_Y = false;
            b_1913_J.this.t_148_a.h_1847_R();
            b_1913_J.this.q_2307_F = 200;
        }

        @Override
        public void P_1922_E() {
            ++this.w_1484_f;
            if (this.w_1484_f > 600) {
                b_1913_J.this.Z_875_P = null;
            } else {
                e_2866_D vector3d = e_2866_D.R_4764_Y(b_1913_J.this.Z_875_P).J_1907_R(0.0, 0.6f, 0.0);
                if (vector3d.u_1723_Y(b_1913_J.this.s_4990_V()) > 1.0) {
                    this.v_4262_N = vector3d;
                    this.P_4830_p();
                } else {
                    if (this.v_4262_N == null) {
                        this.v_4262_N = vector3d;
                    }
                    boolean flag = b_1913_J.this.s_4990_V().u_1723_Y(this.v_4262_N) <= 0.1;
                    boolean flag1 = true;
                    if (!flag && this.w_1484_f > 600) {
                        b_1913_J.this.Z_875_P = null;
                    } else {
                        if (flag) {
                            boolean flag2;
                            boolean bl = flag2 = b_1913_J.this.RealmsWorldOptions.nextInt(25) == 0;
                            if (flag2) {
                                this.v_4262_N = new e_2866_D(vector3d.n_1700_B() + (double)this.h_1847_R(), vector3d.J_1907_R(), vector3d.R_4764_Y() + (double)this.h_1847_R());
                                b_1913_J.this.t_148_a.h_1847_R();
                            } else {
                                flag1 = false;
                            }
                            b_1913_J.this.c_3005_b().n_1700_B(vector3d.n_1700_B(), vector3d.J_1907_R(), vector3d.R_4764_Y());
                        }
                        if (flag1) {
                            this.P_4830_p();
                        }
                        ++this.G_564_y;
                        if (b_1913_J.this.RealmsWorldOptions.nextFloat() < 0.05f && this.G_564_y > this.P_1922_E + 60) {
                            this.P_1922_E = this.G_564_y;
                            b_1913_J.this.n_1700_B(SoundEvents.j_1564_a, 1.0f, 1.0f);
                        }
                    }
                }
            }
        }

        private void P_4830_p() {
            b_1913_J.this.A_4115_X().n_1700_B(this.v_4262_N.n_1700_B(), this.v_4262_N.J_1907_R(), this.v_4262_N.R_4764_Y(), 0.35f);
        }

        private float h_1847_R() {
            return (b_1913_J.this.RealmsWorldOptions.nextFloat() * 2.0f - 1.0f) * 0.33333334f;
        }

        private Optional<c_1514_x> Q_4569_t() {
            return this.n_1700_B(this.R_4764_Y, 5.0);
        }

        private Optional<c_1514_x> n_1700_B(Predicate<K_4074_S> p_226500_1_, double distance) {
            c_1514_x blockpos = b_1913_J.this.b_2312_j();
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            int i = 0;
            while ((double)i <= distance) {
                int j = 0;
                while ((double)j < distance) {
                    int k = 0;
                    while (k <= j) {
                        int l;
                        int n = l = k < j && k > -j ? j : 0;
                        while (l <= j) {
                            blockpos$mutable.n_1700_B(blockpos, k, i - 1, l);
                            if (blockpos.withinDistance(blockpos$mutable, distance) && p_226500_1_.test(b_1913_J.this.O_508_d.getBlockState(blockpos$mutable))) {
                                return Optional.of(blockpos$mutable);
                            }
                            l = l > 0 ? -l : 1 - l;
                        }
                        k = k > 0 ? -k : 1 - k;
                    }
                    ++j;
                }
                i = i > 0 ? -i : 1 - i;
            }
            return Optional.empty();
        }
    }

    class u_2550_I
    extends w_1484_f {
        private u_2550_I() {
        }

        @Override
        public boolean v_4262_N() {
            return b_1913_J.this.k_2293_S == 0 && !b_1913_J.this.y_2447_C() && b_1913_J.this.A_1306_N();
        }

        @Override
        public boolean w_1484_f() {
            return false;
        }

        @Override
        public void R_4764_Y() {
            b_1913_J.this.k_2293_S = 200;
            List<c_1514_x> list = this.s_956_w();
            if (!list.isEmpty()) {
                for (c_1514_x blockpos : list) {
                    if (b_1913_J.this.A_4115_X.J_1907_R(blockpos)) continue;
                    b_1913_J.this.c_3005_b = blockpos;
                    return;
                }
                b_1913_J.this.A_4115_X.s_956_w();
                b_1913_J.this.c_3005_b = list.get(0);
            }
        }

        private List<c_1514_x> s_956_w() {
            c_1514_x blockpos = b_1913_J.this.b_2312_j();
            b_4946_z pointofinterestmanager = ((e_3591_l)b_1913_J.this.O_508_d).p_178_J();
            Stream<y_2339_p> stream = pointofinterestmanager.R_4764_Y(p_226486_0_ -> p_226486_0_ == q_2232_A.Y_601_j || p_226486_0_ == q_2232_A.Y_259_p, blockpos, 20, b_4946_z.J_1907_R.R_4764_Y);
            return stream.map(y_2339_p::P_1922_E).filter(p_226487_1_ -> b_1913_J.this.t_148_a((c_1514_x)p_226487_1_)).sorted(Comparator.comparingDouble(p_226488_1_ -> p_226488_1_.distanceSq(blockpos))).collect(Collectors.toList());
        }
    }

    public class P_1922_E
    extends w_1484_f {
        private int R_4764_Y;
        private List<c_1514_x> G_564_y;
        @Nullable
        private b_1722_e P_1922_E;
        private int u_1723_Y;

        P_1922_E() {
            this.R_4764_Y = b_1913_J.this.O_508_d.w_1457_N.nextInt(10);
            this.G_564_y = Lists.newArrayList();
            this.P_1922_E = null;
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean v_4262_N() {
            return b_1913_J.this.c_3005_b != null && !b_1913_J.this.z_3000_g() && b_1913_J.this.A_1306_N() && !this.G_564_y(b_1913_J.this.c_3005_b) && b_1913_J.this.O_508_d.getBlockState(b_1913_J.this.c_3005_b).n_1700_B(BlockTags.Ping);
        }

        @Override
        public boolean w_1484_f() {
            return this.v_4262_N();
        }

        @Override
        public void R_4764_Y() {
            this.R_4764_Y = 0;
            this.u_1723_Y = 0;
            super.R_4764_Y();
        }

        @Override
        public void G_564_y() {
            this.R_4764_Y = 0;
            this.u_1723_Y = 0;
            b_1913_J.this.t_148_a.h_1847_R();
            b_1913_J.this.t_148_a.u_1723_Y();
        }

        @Override
        public void P_1922_E() {
            if (b_1913_J.this.c_3005_b != null) {
                ++this.R_4764_Y;
                if (this.R_4764_Y > 600) {
                    this.u_2550_I();
                } else if (!b_1913_J.this.t_148_a.P_4830_p()) {
                    if (!b_1913_J.this.J_1907_R(b_1913_J.this.c_3005_b, 16)) {
                        if (b_1913_J.this.s_956_w(b_1913_J.this.c_3005_b)) {
                            this.M_588_G();
                        } else {
                            b_1913_J.this.w_1484_f(b_1913_J.this.c_3005_b);
                        }
                    } else {
                        boolean flag = this.n_1700_B(b_1913_J.this.c_3005_b);
                        if (!flag) {
                            this.u_2550_I();
                        } else if (this.P_1922_E != null && b_1913_J.this.t_148_a.s_956_w().n_1700_B(this.P_1922_E)) {
                            ++this.u_1723_Y;
                            if (this.u_1723_Y > 60) {
                                this.M_588_G();
                                this.u_1723_Y = 0;
                            }
                        } else {
                            this.P_1922_E = b_1913_J.this.t_148_a.s_956_w();
                        }
                    }
                }
            }
        }

        private boolean n_1700_B(c_1514_x pos) {
            b_1913_J.this.t_148_a.n_1700_B(10.0f);
            b_1913_J.this.t_148_a.n_1700_B((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), 1.0);
            return b_1913_J.this.t_148_a.s_956_w() != null && b_1913_J.this.t_148_a.s_956_w().s_956_w();
        }

        private boolean J_1907_R(c_1514_x pos) {
            return this.G_564_y.contains(pos);
        }

        private void R_4764_Y(c_1514_x pos) {
            this.G_564_y.add(pos);
            while (this.G_564_y.size() > 3) {
                this.G_564_y.remove(0);
            }
        }

        private void s_956_w() {
            this.G_564_y.clear();
        }

        private void u_2550_I() {
            if (b_1913_J.this.c_3005_b != null) {
                this.R_4764_Y(b_1913_J.this.c_3005_b);
            }
            this.M_588_G();
        }

        private void M_588_G() {
            b_1913_J.this.c_3005_b = null;
            b_1913_J.this.k_2293_S = 200;
        }

        private boolean G_564_y(c_1514_x pos) {
            if (b_1913_J.this.J_1907_R(pos, 2)) {
                return true;
            }
            b_1722_e path = b_1913_J.this.t_148_a.s_956_w();
            return path != null && path.P_4830_p().equals(pos) && path.s_956_w() && path.R_4764_Y();
        }
    }

    public class u_1723_Y
    extends w_1484_f {
        private int R_4764_Y;

        u_1723_Y() {
            this.R_4764_Y = b_1913_J.this.O_508_d.w_1457_N.nextInt(10);
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean v_4262_N() {
            return b_1913_J.this.Z_875_P != null && !b_1913_J.this.z_3000_g() && this.s_956_w() && b_1913_J.this.u_2550_I(b_1913_J.this.Z_875_P) && !b_1913_J.this.J_1907_R(b_1913_J.this.Z_875_P, 2);
        }

        @Override
        public boolean w_1484_f() {
            return this.v_4262_N();
        }

        @Override
        public void R_4764_Y() {
            this.R_4764_Y = 0;
            super.R_4764_Y();
        }

        @Override
        public void G_564_y() {
            this.R_4764_Y = 0;
            b_1913_J.this.t_148_a.h_1847_R();
            b_1913_J.this.t_148_a.u_1723_Y();
        }

        @Override
        public void P_1922_E() {
            if (b_1913_J.this.Z_875_P != null) {
                ++this.R_4764_Y;
                if (this.R_4764_Y > 600) {
                    b_1913_J.this.Z_875_P = null;
                } else if (!b_1913_J.this.t_148_a.P_4830_p()) {
                    if (b_1913_J.this.s_956_w(b_1913_J.this.Z_875_P)) {
                        b_1913_J.this.Z_875_P = null;
                    } else {
                        b_1913_J.this.w_1484_f(b_1913_J.this.Z_875_P);
                    }
                }
            }
        }

        private boolean s_956_w() {
            return b_1913_J.this.Y_259_p > 2400;
        }
    }

    class v_4262_N
    extends w_1484_f {
        private v_4262_N() {
        }

        @Override
        public boolean v_4262_N() {
            if (b_1913_J.this.ModuleCategory() >= 10) {
                return false;
            }
            if (b_1913_J.this.RealmsWorldOptions.nextFloat() < 0.3f) {
                return false;
            }
            return b_1913_J.this.V_537_k() && b_1913_J.this.ModuleManager();
        }

        @Override
        public boolean w_1484_f() {
            return this.v_4262_N();
        }

        @Override
        public void P_1922_E() {
            if (b_1913_J.this.RealmsWorldOptions.nextInt(30) == 0) {
                for (int i = 1; i <= 2; ++i) {
                    int k;
                    c_1514_x blockpos = b_1913_J.this.b_2312_j().down(i);
                    K_4074_S blockstate = b_1913_J.this.O_508_d.getBlockState(blockpos);
                    T_2915_h block = blockstate.J_1907_R();
                    boolean flag = false;
                    g_88_D integerproperty = null;
                    if (!block.n_1700_B(BlockTags.RealmsClientConfig)) continue;
                    if (block instanceof CropBlock) {
                        CropBlock cropsblock = (CropBlock)block;
                        if (!cropsblock.t_148_a(blockstate)) {
                            flag = true;
                            integerproperty = cropsblock.J_1907_R();
                        }
                    } else if (block instanceof D_3746_J) {
                        int j = blockstate.R_4764_Y(D_3746_J.P_4830_p);
                        if (j < 7) {
                            flag = true;
                            integerproperty = D_3746_J.P_4830_p;
                        }
                    } else if (block == a_3742_W.s_4405_m && (k = blockstate.R_4764_Y(SweetBerryBushBlock.P_4830_p).intValue()) < 3) {
                        flag = true;
                        integerproperty = SweetBerryBushBlock.P_4830_p;
                    }
                    if (!flag) continue;
                    b_1913_J.this.O_508_d.R_4764_Y(2005, blockpos, 0);
                    b_1913_J.this.O_508_d.J_1907_R(blockpos, (K_4074_S)blockstate.n_1700_B(integerproperty, blockstate.R_4764_Y(integerproperty) + 1));
                    b_1913_J.this.Module();
                }
            }
        }
    }

    class M_588_G
    extends Goal {
        M_588_G() {
            this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
        }

        @Override
        public boolean n_1700_B() {
            return b_1913_J.this.t_148_a.M_588_G() && b_1913_J.this.RealmsWorldOptions.nextInt(10) == 0;
        }

        @Override
        public boolean J_1907_R() {
            return b_1913_J.this.t_148_a.P_4830_p();
        }

        @Override
        public void R_4764_Y() {
            e_2866_D vector3d = this.v_4262_N();
            if (vector3d != null) {
                b_1913_J.this.t_148_a.n_1700_B(b_1913_J.this.t_148_a.n_1700_B(new c_1514_x(vector3d), 1), 1.0);
            }
        }

        @Nullable
        private e_2866_D v_4262_N() {
            e_2866_D vector3d;
            if (b_1913_J.this.ModuleManager() && !b_1913_J.this.J_1907_R(b_1913_J.this.c_3005_b, 22)) {
                e_2866_D vector3d1 = e_2866_D.n_1700_B(b_1913_J.this.c_3005_b);
                vector3d = vector3d1.G_564_y(b_1913_J.this.s_4990_V()).G_564_y();
            } else {
                vector3d = b_1913_J.this.t_148_a(0.0f);
            }
            int i = 8;
            e_2866_D vector3d2 = W_3371_U.n_1700_B(b_1913_J.this, 8, 7, vector3d, 1.5707964f, 2, 1);
            return vector3d2 != null ? vector3d2 : W_3371_U.n_1700_B(b_1913_J.this, 8, 4, -2, vector3d, 1.5707963705062866);
        }
    }

    class n_1700_B
    extends g_3408_G {
        n_1700_B(b_1913_J beeIn) {
            super(beeIn, new Class[0]);
        }

        @Override
        public boolean J_1907_R() {
            return b_1913_J.this.B_() && super.J_1907_R();
        }

        @Override
        protected void n_1700_B(Z_530_i mobIn, r_4811_B targetIn) {
            if (mobIn instanceof b_1913_J && this.P_1922_E.c_3005_b(targetIn)) {
                mobIn.R_4764_Y(targetIn);
            }
        }
    }

    static class J_1907_R
    extends NearestAttackableTargetGoal<a_3913_L> {
        J_1907_R(b_1913_J beeIn) {
            super(beeIn, a_3913_L.class, 10, true, false, beeIn::c_);
        }

        @Override
        public boolean n_1700_B() {
            return this.v_4262_N() && super.n_1700_B();
        }

        @Override
        public boolean J_1907_R() {
            boolean flag = this.v_4262_N();
            if (flag && this.P_1922_E.t_148_a() != null) {
                return super.J_1907_R();
            }
            this.v_4262_N = null;
            return false;
        }

        private boolean v_4262_N() {
            b_1913_J beeentity = (b_1913_J)this.P_1922_E;
            return beeentity.B_() && !beeentity.c_2086_l();
        }
    }

    abstract class w_1484_f
    extends Goal {
        private w_1484_f() {
        }

        public abstract boolean v_4262_N();

        public abstract boolean w_1484_f();

        @Override
        public boolean n_1700_B() {
            return this.v_4262_N() && !b_1913_J.this.B_();
        }

        @Override
        public boolean J_1907_R() {
            return this.w_1484_f() && !b_1913_J.this.B_();
        }
    }
}



