/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.FluidTags;
import lightning.product.C_4114_x;
import lightning.product.BlockGetter;
import lightning.product.G_652_w;
import lightning.product.I_1170_F;
import lightning.product.I_408_V;
import lightning.product.I_4817_s;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.Animal;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.BlockUtil;
import lightning.product.ServerboundPaddleBoatPacket;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.WaterlilyBlock;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.WaterAnimal;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public class g_1462_f
extends N_4263_v {
    private static final h_256_u<Integer> n_1700_B = C_4114_x.n_1700_B(g_1462_f.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Integer> J_1907_R = C_4114_x.n_1700_B(g_1462_f.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Float> R_4764_Y = C_4114_x.n_1700_B(g_1462_f.class, EntityDataSerializers.R_4764_Y);
    private static final h_256_u<Integer> G_564_y = C_4114_x.n_1700_B(g_1462_f.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Boolean> P_1922_E = C_4114_x.n_1700_B(g_1462_f.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> u_1723_Y = C_4114_x.n_1700_B(g_1462_f.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Integer> v_4262_N = C_4114_x.n_1700_B(g_1462_f.class, EntityDataSerializers.J_1907_R);
    private final float[] w_1484_f = new float[2];
    private float t_148_a;
    private float s_956_w;
    private float u_2550_I;
    private int M_588_G;
    private double P_4830_p;
    private double h_1847_R;
    private double Q_4569_t;
    private double M_182_A;
    private double t_1786_h;
    private boolean multiplayerClientSuggestionProvider;
    private boolean w_1457_N;
    private boolean Y_601_j;
    private boolean Y_259_p;
    private double Q_2552_b;
    private float C_2741_M;
    private n_1700_B k_2293_S;
    private n_1700_B q_2307_F;
    private double Z_875_P;
    private boolean c_3005_b;
    private boolean H_2857_Y;
    private float A_4115_X;
    private float Y_1740_V;
    private float t_4043_B;

    public g_1462_f(t_5_h<? extends g_1462_f> type, b_4507_u world) {
        super(type, world);
        this.s_2632_s = true;
    }

    public g_1462_f(b_4507_u worldIn, double x, double y, double z) {
        this((t_5_h<? extends g_1462_f>)t_5_h.v_4262_N, worldIn);
        this.J_1907_R(x, y, z);
        this.v_4262_N(e_2866_D.n_1700_B);
        this.r_715_M = x;
        this.A_1038_p = y;
        this.i_1637_u = z;
    }

    @Override
    protected float n_1700_B(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R;
    }

    @Override
    protected boolean RetryCallException() {
        return false;
    }

    @Override
    protected void a_() {
        this.l_4537_E.n_1700_B(n_1700_B, 0);
        this.l_4537_E.n_1700_B(J_1907_R, 1);
        this.l_4537_E.n_1700_B(R_4764_Y, Float.valueOf(0.0f));
        this.l_4537_E.n_1700_B(G_564_y, lightning.product.g_1462_f$J_1907_R.n_1700_B.ordinal());
        this.l_4537_E.n_1700_B(P_1922_E, false);
        this.l_4537_E.n_1700_B(u_1723_Y, false);
        this.l_4537_E.n_1700_B(v_4262_N, 0);
    }

    @Override
    public boolean u_1723_Y(N_4263_v entity) {
        return g_1462_f.n_1700_B(this, entity);
    }

    public static boolean n_1700_B(N_4263_v p_242378_0_, N_4263_v entity) {
        return (entity.RealmsParentalConsentScreen() || entity.w_728_N()) && !p_242378_0_.Y_259_p(entity);
    }

    @Override
    public boolean RealmsParentalConsentScreen() {
        return true;
    }

    @Override
    public boolean w_728_N() {
        return true;
    }

    @Override
    protected e_2866_D n_1700_B(b_257_Y.n_1700_B axis, BlockUtil.J_1907_R result) {
        return r_4811_B.t_148_a(super.n_1700_B(axis, result));
    }

    @Override
    public double s_1671_u() {
        return -0.1;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        if (!this.O_508_d.Y_259_p && !this.t_4219_U) {
            boolean flag;
            this.R_4764_Y(-this.h_1847_R());
            this.J_1907_R(10);
            this.n_1700_B(this.t_148_a() + amount * 10.0f);
            this.RealmsCreateRealmScreen();
            boolean bl = flag = source.u_2550_I() instanceof a_3913_L && ((a_3913_L)source.u_2550_I()).C_415_h.G_564_y;
            if (flag || this.t_148_a() > 40.0f) {
                if (!flag && this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
                    this.n_1700_B(this.P_1922_E());
                }
                this.Ops();
            }
            return true;
        }
        return true;
    }

    @Override
    public void P_4830_p(boolean downwards) {
        if (!this.O_508_d.Y_259_p) {
            this.c_3005_b = true;
            this.H_2857_Y = downwards;
            if (this.k_2293_S() == 0) {
                this.s_956_w(60);
            }
        }
        this.O_508_d.n_1700_B(ParticleTypes.g_2268_R, this.O_3598_v() + (double)this.RealmsWorldOptions.nextFloat(), this.X_2960_b() + 0.7, this.l_2647_k() + (double)this.RealmsWorldOptions.nextFloat(), 0.0, 0.0, 0.0);
        if (this.RealmsWorldOptions.nextInt(20) == 0) {
            this.O_508_d.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), this.S_4022_R(), this.r_2478_U(), 1.0f, 0.8f + 0.4f * this.RealmsWorldOptions.nextFloat(), false);
        }
    }

    @Override
    public void P_1922_E(N_4263_v entityIn) {
        if (entityIn instanceof g_1462_f) {
            if (entityIn.i_601_W().minY < this.i_601_W().maxY) {
                super.P_1922_E(entityIn);
            }
        } else if (entityIn.i_601_W().minY <= this.i_601_W().minY) {
            super.P_1922_E(entityIn);
        }
    }

    public q_1613_l P_1922_E() {
        switch (this.Q_4569_t().ordinal()) {
            default: {
                return Items.m_1628_s;
            }
            case 1: {
                return Items.ObserverBlock;
            }
            case 2: {
                return Items.OreBlock;
            }
            case 3: {
                return Items.IronBarsBlock;
            }
            case 4: {
                return Items.h_4152_b;
            }
            case 5: 
        }
        return Items.M_1398_d;
    }

    @Override
    public void D_4361_a() {
        this.R_4764_Y(-this.h_1847_R());
        this.J_1907_R(10);
        this.n_1700_B(this.t_148_a() * 11.0f);
    }

    @Override
    public boolean C_290_v() {
        return !this.t_4219_U;
    }

    @Override
    public void n_1700_B(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.P_4830_p = x;
        this.h_1847_R = y;
        this.Q_4569_t = z;
        this.M_182_A = yaw;
        this.t_1786_h = pitch;
        this.M_588_G = 10;
    }

    @Override
    public b_257_Y d_2545_n() {
        return this.o_2767_H().v_4262_N();
    }

    @Override
    public void v_() {
        this.q_2307_F = this.k_2293_S;
        this.k_2293_S = this.w_1457_N();
        this.s_956_w = this.k_2293_S != lightning.product.g_1462_f$n_1700_B.J_1907_R && this.k_2293_S != lightning.product.g_1462_f$n_1700_B.R_4764_Y ? 0.0f : (this.s_956_w += 1.0f);
        if (!this.O_508_d.Y_259_p && this.s_956_w >= 60.0f) {
            this.C_3538_G();
        }
        if (this.u_2550_I() > 0) {
            this.J_1907_R(this.u_2550_I() - 1);
        }
        if (this.t_148_a() > 0.0f) {
            this.n_1700_B(this.t_148_a() - 1.0f);
        }
        super.v_();
        this.multiplayerClientSuggestionProvider();
        if (this.v_887_r()) {
            if (this.o_3599_Z().isEmpty() || !(this.o_3599_Z().get(0) instanceof a_3913_L)) {
                this.n_1700_B(false, false);
            }
            this.Q_2552_b();
            if (this.O_508_d.Y_259_p) {
                this.C_2741_M();
                this.O_508_d.n_1700_B(new ServerboundPaddleBoatPacket(this.n_1700_B(0), this.n_1700_B(1)));
            }
            this.n_1700_B(L_461_d.n_1700_B, this.I_4348_c());
        } else {
            this.v_4262_N(e_2866_D.n_1700_B);
        }
        this.M_182_A();
        for (int i = 0; i <= 1; ++i) {
            if (this.n_1700_B(i)) {
                SoundEvent soundevent;
                if (!this.y_1700_S() && (double)(this.w_1484_f[i] % ((float)Math.PI * 2)) <= 0.7853981852531433 && ((double)this.w_1484_f[i] + (double)0.3926991f) % 6.2831854820251465 >= 0.7853981852531433 && (soundevent = this.u_1723_Y()) != null) {
                    e_2866_D vector3d = this.t_148_a(1.0f);
                    double d0 = i == 1 ? -vector3d.G_564_y : vector3d.G_564_y;
                    double d1 = i == 1 ? vector3d.J_1907_R : -vector3d.J_1907_R;
                    this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v() + d0, this.X_2960_b(), this.l_2647_k() + d1, soundevent, this.r_2478_U(), 1.0f, 0.8f + 0.4f * this.RealmsWorldOptions.nextFloat());
                }
                this.w_1484_f[i] = (float)((double)this.w_1484_f[i] + (double)0.3926991f);
                continue;
            }
            this.w_1484_f[i] = 0.0f;
        }
        this.F_2624_D();
        List<N_4263_v> list = this.O_508_d.J_1907_R((N_4263_v)this, this.i_601_W().grow(0.2f, -0.01f, 0.2f), I_408_V.n_1700_B(this));
        if (!list.isEmpty()) {
            boolean flag = !this.O_508_d.Y_259_p && !(this.n_3864_h() instanceof a_3913_L);
            for (int j = 0; j < list.size(); ++j) {
                N_4263_v entity = list.get(j);
                if (entity.Y_601_j(this)) continue;
                if (flag && this.o_3599_Z().size() < 2 && !entity.y_2772_m() && entity.C_415_h() < this.C_415_h() && entity instanceof r_4811_B && !(entity instanceof WaterAnimal) && !(entity instanceof a_3913_L)) {
                    entity.s_956_w(this);
                    continue;
                }
                this.P_1922_E(entity);
            }
        }
    }

    private void M_182_A() {
        if (this.O_508_d.Y_259_p) {
            int i = this.k_2293_S();
            this.A_4115_X = i > 0 ? (this.A_4115_X += 0.05f) : (this.A_4115_X -= 0.1f);
            this.A_4115_X = u_530_F.n_1700_B(this.A_4115_X, 0.0f, 1.0f);
            this.t_4043_B = this.Y_1740_V;
            this.Y_1740_V = 10.0f * (float)Math.sin(0.5f * (float)this.O_508_d.X_933_l()) * this.A_4115_X;
        } else {
            int k;
            if (!this.c_3005_b) {
                this.s_956_w(0);
            }
            if ((k = this.k_2293_S()) > 0) {
                this.s_956_w(--k);
                int j = 60 - k - 1;
                if (j > 0 && k == 0) {
                    this.s_956_w(0);
                    e_2866_D vector3d = this.I_4348_c();
                    if (this.H_2857_Y) {
                        this.v_4262_N(vector3d.J_1907_R(0.0, -0.7, 0.0));
                        this.C_3538_G();
                    } else {
                        this.h_1847_R(vector3d.J_1907_R, this.n_1700_B(a_3913_L.class) ? 2.7 : 0.6, vector3d.G_564_y);
                    }
                }
                this.c_3005_b = false;
            }
        }
    }

    @Nullable
    protected SoundEvent u_1723_Y() {
        switch (this.w_1457_N().ordinal()) {
            case 0: 
            case 1: 
            case 2: {
                return SoundEvents.u_744_e;
            }
            case 3: {
                return SoundEvents.y_1700_S;
            }
        }
        return null;
    }

    private void multiplayerClientSuggestionProvider() {
        if (this.v_887_r()) {
            this.M_588_G = 0;
            this.n_1700_B(this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
        }
        if (this.M_588_G > 0) {
            double d0 = this.O_3598_v() + (this.P_4830_p - this.O_3598_v()) / (double)this.M_588_G;
            double d1 = this.X_2960_b() + (this.h_1847_R - this.X_2960_b()) / (double)this.M_588_G;
            double d2 = this.l_2647_k() + (this.Q_4569_t - this.l_2647_k()) / (double)this.M_588_G;
            double d3 = u_530_F.u_1723_Y(this.M_182_A - (double)this.p_178_J);
            this.p_178_J = (float)((double)this.p_178_J + d3 / (double)this.M_588_G);
            this.f_4016_n = (float)((double)this.f_4016_n + (this.t_1786_h - (double)this.f_4016_n) / (double)this.M_588_G);
            --this.M_588_G;
            this.J_1907_R(d0, d1, d2);
            this.J_1907_R(this.p_178_J, this.f_4016_n);
        }
    }

    public void n_1700_B(boolean left, boolean right) {
        this.l_4537_E.J_1907_R(P_1922_E, left);
        this.l_4537_E.J_1907_R(u_1723_Y, right);
    }

    public float n_1700_B(int side, float limbSwing) {
        return this.n_1700_B(side) ? (float)u_530_F.J_1907_R((double)this.w_1484_f[side] - (double)0.3926991f, (double)this.w_1484_f[side], (double)limbSwing) : 0.0f;
    }

    private n_1700_B w_1457_N() {
        n_1700_B boatentity$status = this.Y_259_p();
        if (boatentity$status != null) {
            this.Q_2552_b = this.i_601_W().maxY;
            return boatentity$status;
        }
        if (this.Y_601_j()) {
            return lightning.product.g_1462_f$n_1700_B.n_1700_B;
        }
        float f = this.w_1484_f();
        if (f > 0.0f) {
            this.C_2741_M = f;
            return lightning.product.g_1462_f$n_1700_B.G_564_y;
        }
        return lightning.product.g_1462_f$n_1700_B.P_1922_E;
    }

    public float v_4262_N() {
        I_4817_s axisalignedbb = this.i_601_W();
        int i = u_530_F.R_4764_Y(axisalignedbb.minX);
        int j = u_530_F.P_1922_E(axisalignedbb.maxX);
        int k = u_530_F.R_4764_Y(axisalignedbb.maxY);
        int l = u_530_F.P_1922_E(axisalignedbb.maxY - this.Z_875_P);
        int i1 = u_530_F.R_4764_Y(axisalignedbb.minZ);
        int j1 = u_530_F.P_1922_E(axisalignedbb.maxZ);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        block0: for (int k1 = k; k1 < l; ++k1) {
            float f = 0.0f;
            for (int l1 = i; l1 < j; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutable.n_1700_B(l1, k1, i2);
                    FluidState fluidstate = this.O_508_d.getFluidState(blockpos$mutable);
                    if (fluidstate.n_1700_B(FluidTags.J_1907_R)) {
                        f = Math.max(f, fluidstate.n_1700_B((BlockGetter)this.O_508_d, (c_1514_x)blockpos$mutable));
                    }
                    if (f >= 1.0f) continue block0;
                }
            }
            if (!(f < 1.0f)) continue;
            return (float)blockpos$mutable.getY() + f;
        }
        return l + 1;
    }

    public float w_1484_f() {
        I_4817_s axisalignedbb = this.i_601_W();
        I_4817_s axisalignedbb1 = new I_4817_s(axisalignedbb.minX, axisalignedbb.minY - 0.001, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        int i = u_530_F.R_4764_Y(axisalignedbb1.minX) - 1;
        int j = u_530_F.P_1922_E(axisalignedbb1.maxX) + 1;
        int k = u_530_F.R_4764_Y(axisalignedbb1.minY) - 1;
        int l = u_530_F.P_1922_E(axisalignedbb1.maxY) + 1;
        int i1 = u_530_F.R_4764_Y(axisalignedbb1.minZ) - 1;
        int j1 = u_530_F.P_1922_E(axisalignedbb1.maxZ) + 1;
        s_1395_c voxelshape = x_268_Y.n_1700_B(axisalignedbb1);
        float f = 0.0f;
        int k1 = 0;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int l1 = i; l1 < j; ++l1) {
            for (int i2 = i1; i2 < j1; ++i2) {
                int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
                if (j2 == 2) continue;
                for (int k2 = k; k2 < l; ++k2) {
                    if (j2 > 0 && (k2 == k || k2 == l - 1)) continue;
                    blockpos$mutable.n_1700_B(l1, k2, i2);
                    K_4074_S blockstate = this.O_508_d.getBlockState(blockpos$mutable);
                    if (blockstate.J_1907_R() instanceof WaterlilyBlock || !x_268_Y.R_4764_Y(blockstate.u_2550_I(this.O_508_d, blockpos$mutable).n_1700_B(l1, (double)k2, (double)i2), voxelshape, BooleanOp.t_148_a)) continue;
                    f += blockstate.J_1907_R().h_1847_R();
                    ++k1;
                }
            }
        }
        return f / (float)k1;
    }

    private boolean Y_601_j() {
        I_4817_s axisalignedbb = this.i_601_W();
        int i = u_530_F.R_4764_Y(axisalignedbb.minX);
        int j = u_530_F.P_1922_E(axisalignedbb.maxX);
        int k = u_530_F.R_4764_Y(axisalignedbb.minY);
        int l = u_530_F.P_1922_E(axisalignedbb.minY + 0.001);
        int i1 = u_530_F.R_4764_Y(axisalignedbb.minZ);
        int j1 = u_530_F.P_1922_E(axisalignedbb.maxZ);
        boolean flag = false;
        this.Q_2552_b = Double.MIN_VALUE;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutable.n_1700_B(k1, l1, i2);
                    FluidState fluidstate = this.O_508_d.getFluidState(blockpos$mutable);
                    if (!fluidstate.n_1700_B(FluidTags.J_1907_R)) continue;
                    float f = (float)l1 + fluidstate.n_1700_B((BlockGetter)this.O_508_d, (c_1514_x)blockpos$mutable);
                    this.Q_2552_b = Math.max((double)f, this.Q_2552_b);
                    flag |= axisalignedbb.minY < (double)f;
                }
            }
        }
        return flag;
    }

    @Nullable
    private n_1700_B Y_259_p() {
        I_4817_s axisalignedbb = this.i_601_W();
        double d0 = axisalignedbb.maxY + 0.001;
        int i = u_530_F.R_4764_Y(axisalignedbb.minX);
        int j = u_530_F.P_1922_E(axisalignedbb.maxX);
        int k = u_530_F.R_4764_Y(axisalignedbb.maxY);
        int l = u_530_F.P_1922_E(d0);
        int i1 = u_530_F.R_4764_Y(axisalignedbb.minZ);
        int j1 = u_530_F.P_1922_E(axisalignedbb.maxZ);
        boolean flag = false;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutable.n_1700_B(k1, l1, i2);
                    FluidState fluidstate = this.O_508_d.getFluidState(blockpos$mutable);
                    if (!fluidstate.n_1700_B(FluidTags.J_1907_R) || !(d0 < (double)((float)blockpos$mutable.getY() + fluidstate.n_1700_B((BlockGetter)this.O_508_d, (c_1514_x)blockpos$mutable)))) continue;
                    if (!fluidstate.J_1907_R()) {
                        return lightning.product.g_1462_f$n_1700_B.R_4764_Y;
                    }
                    flag = true;
                }
            }
        }
        return flag ? lightning.product.g_1462_f$n_1700_B.J_1907_R : null;
    }

    private void Q_2552_b() {
        double d0 = -0.04f;
        double d1 = this.u_744_e() ? 0.0 : (double)-0.04f;
        double d2 = 0.0;
        this.t_148_a = 0.05f;
        if (this.q_2307_F == lightning.product.g_1462_f$n_1700_B.P_1922_E && this.k_2293_S != lightning.product.g_1462_f$n_1700_B.P_1922_E && this.k_2293_S != lightning.product.g_1462_f$n_1700_B.G_564_y) {
            this.Q_2552_b = this.P_1922_E(1.0);
            this.J_1907_R(this.O_3598_v(), (double)(this.v_4262_N() - this.v_165_F()) + 0.101, this.l_2647_k());
            this.v_4262_N(this.I_4348_c().G_564_y(1.0, 0.0, 1.0));
            this.Z_875_P = 0.0;
            this.k_2293_S = lightning.product.g_1462_f$n_1700_B.n_1700_B;
        } else {
            if (this.k_2293_S == lightning.product.g_1462_f$n_1700_B.n_1700_B) {
                d2 = (this.Q_2552_b - this.X_2960_b()) / (double)this.v_165_F();
                this.t_148_a = 0.9f;
            } else if (this.k_2293_S == lightning.product.g_1462_f$n_1700_B.R_4764_Y) {
                d1 = -7.0E-4;
                this.t_148_a = 0.9f;
            } else if (this.k_2293_S == lightning.product.g_1462_f$n_1700_B.J_1907_R) {
                d2 = 0.01f;
                this.t_148_a = 0.45f;
            } else if (this.k_2293_S == lightning.product.g_1462_f$n_1700_B.P_1922_E) {
                this.t_148_a = 0.9f;
            } else if (this.k_2293_S == lightning.product.g_1462_f$n_1700_B.G_564_y) {
                this.t_148_a = this.C_2741_M;
                if (this.n_3864_h() instanceof a_3913_L) {
                    this.C_2741_M /= 2.0f;
                }
            }
            e_2866_D vector3d = this.I_4348_c();
            this.h_1847_R(vector3d.J_1907_R * (double)this.t_148_a, vector3d.R_4764_Y + d1, vector3d.G_564_y * (double)this.t_148_a);
            this.u_2550_I *= this.t_148_a;
            if (d2 > 0.0) {
                e_2866_D vector3d1 = this.I_4348_c();
                this.h_1847_R(vector3d1.J_1907_R, (vector3d1.R_4764_Y + d2 * 0.06153846016296973) * 0.75, vector3d1.G_564_y);
            }
        }
    }

    private void C_2741_M() {
        if (this.H_1883_T()) {
            float f = 0.0f;
            if (this.multiplayerClientSuggestionProvider) {
                this.u_2550_I -= 1.0f;
            }
            if (this.w_1457_N) {
                this.u_2550_I += 1.0f;
            }
            if (this.w_1457_N != this.multiplayerClientSuggestionProvider && !this.Y_601_j && !this.Y_259_p) {
                f += 0.005f;
            }
            this.p_178_J += this.u_2550_I;
            if (this.Y_601_j) {
                f += 0.04f;
            }
            if (this.Y_259_p) {
                f -= 0.005f;
            }
            this.v_4262_N(this.I_4348_c().J_1907_R(u_530_F.n_1700_B(-this.p_178_J * ((float)Math.PI / 180)) * f, 0.0, u_530_F.J_1907_R(this.p_178_J * ((float)Math.PI / 180)) * f));
            this.n_1700_B(this.w_1457_N && !this.multiplayerClientSuggestionProvider || this.Y_601_j, this.multiplayerClientSuggestionProvider && !this.w_1457_N || this.Y_601_j);
        }
    }

    @Override
    public void v_4262_N(N_4263_v passenger) {
        if (this.Y_601_j(passenger)) {
            float f = 0.0f;
            float f1 = (float)((this.t_4219_U ? (double)0.01f : this.s_1671_u()) + passenger.O_2151_c());
            if (this.o_3599_Z().size() > 1) {
                int i = this.o_3599_Z().indexOf(passenger);
                f = i == 0 ? 0.2f : -0.6f;
                if (passenger instanceof Animal) {
                    f = (float)((double)f + 0.2);
                }
            }
            e_2866_D vector3d = new e_2866_D(f, 0.0, 0.0).J_1907_R(-this.p_178_J * ((float)Math.PI / 180) - 1.5707964f);
            passenger.J_1907_R(this.O_3598_v() + vector3d.J_1907_R, this.X_2960_b() + (double)f1, this.l_2647_k() + vector3d.G_564_y);
            passenger.p_178_J += this.u_2550_I;
            passenger.h_1847_R(passenger.l_4088_R() + this.u_2550_I);
            this.n_1700_B(passenger);
            if (passenger instanceof Animal && this.o_3599_Z().size() > 1) {
                int j = passenger.j_276_v() % 2 == 0 ? 90 : 270;
                passenger.Q_4569_t(((Animal)passenger).C_1162_e + (float)j);
                passenger.h_1847_R(passenger.l_4088_R() + (float)j);
            }
        }
    }

    @Override
    public e_2866_D b_(r_4811_B livingEntity) {
        double d1;
        e_2866_D vector3d = g_1462_f.n_1700_B(this.C_415_h() * u_530_F.n_1700_B, (double)livingEntity.C_415_h(), this.p_178_J);
        double d0 = this.O_3598_v() + vector3d.J_1907_R;
        c_1514_x blockpos = new c_1514_x(d0, this.i_601_W().maxY, d1 = this.l_2647_k() + vector3d.G_564_y);
        c_1514_x blockpos1 = blockpos.down();
        if (!this.O_508_d.s_956_w(blockpos1)) {
            double d2 = (double)blockpos.getY() + this.O_508_d.G_564_y(blockpos);
            double d3 = (double)blockpos.getY() + this.O_508_d.G_564_y(blockpos1);
            for (I_1170_F pose : livingEntity.x_2635_q()) {
                e_2866_D vector3d1 = G_652_w.n_1700_B(this.O_508_d, d0, d2, d1, livingEntity, pose);
                if (vector3d1 != null) {
                    livingEntity.J_1907_R(pose);
                    return vector3d1;
                }
                e_2866_D vector3d2 = G_652_w.n_1700_B(this.O_508_d, d0, d3, d1, livingEntity, pose);
                if (vector3d2 == null) continue;
                livingEntity.J_1907_R(pose);
                return vector3d2;
            }
        }
        return super.b_(livingEntity);
    }

    protected void n_1700_B(N_4263_v entityToUpdate) {
        entityToUpdate.Q_4569_t(this.p_178_J);
        float f = u_530_F.v_4262_N(entityToUpdate.p_178_J - this.p_178_J);
        float f1 = u_530_F.n_1700_B(f, -105.0f, 105.0f);
        entityToUpdate.j_276_v += f1 - f;
        entityToUpdate.p_178_J += f1 - f;
        entityToUpdate.h_1847_R(entityToUpdate.p_178_J);
    }

    @Override
    public void w_1484_f(N_4263_v entityToUpdate) {
        this.n_1700_B(entityToUpdate);
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
        compound.n_1700_B("Type", this.Q_4569_t().n_1700_B());
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
        if (compound.R_4764_Y("Type", 8)) {
            this.n_1700_B(lightning.product.g_1462_f$J_1907_R.n_1700_B(compound.M_588_G("Type")));
        }
    }

    @Override
    public m_3054_I n_1700_B(a_3913_L player, x_1688_C hand) {
        if (player.z_3000_g()) {
            return m_3054_I.R_4764_Y;
        }
        if (this.s_956_w < 60.0f) {
            if (!this.O_508_d.Y_259_p) {
                return player.s_956_w(this) ? m_3054_I.J_1907_R : m_3054_I.R_4764_Y;
            }
            return m_3054_I.n_1700_B;
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    protected void n_1700_B(double y, boolean onGroundIn, K_4074_S state, c_1514_x pos) {
        this.Z_875_P = this.I_4348_c().R_4764_Y;
        if (!this.y_2772_m()) {
            if (onGroundIn) {
                if (this.U_1241_n > 3.0f) {
                    if (this.k_2293_S != lightning.product.g_1462_f$n_1700_B.G_564_y) {
                        this.U_1241_n = 0.0f;
                        return;
                    }
                    this.R_4764_Y(this.U_1241_n, 1.0f);
                    if (!this.O_508_d.Y_259_p && !this.t_4219_U) {
                        this.Ops();
                        if (this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.v_4262_N)) {
                            for (int i = 0; i < 3; ++i) {
                                this.n_1700_B(this.Q_4569_t().J_1907_R());
                            }
                            for (int j = 0; j < 2; ++j) {
                                this.n_1700_B(Items.A_4514_U);
                            }
                        }
                    }
                }
                this.U_1241_n = 0.0f;
            } else if (!this.O_508_d.getFluidState(this.b_2312_j().down()).n_1700_B(FluidTags.J_1907_R) && y < 0.0) {
                this.U_1241_n = (float)((double)this.U_1241_n - y);
            }
        }
    }

    public boolean n_1700_B(int side) {
        return this.l_4537_E.n_1700_B(side == 0 ? P_1922_E : u_1723_Y) != false && this.n_3864_h() != null;
    }

    public void n_1700_B(float damageTaken) {
        this.l_4537_E.J_1907_R(R_4764_Y, Float.valueOf(damageTaken));
    }

    public float t_148_a() {
        return this.l_4537_E.n_1700_B(R_4764_Y).floatValue();
    }

    public void J_1907_R(int timeSinceHit) {
        this.l_4537_E.J_1907_R(n_1700_B, timeSinceHit);
    }

    public int u_2550_I() {
        return this.l_4537_E.n_1700_B(n_1700_B);
    }

    private void s_956_w(int ticks) {
        this.l_4537_E.J_1907_R(v_4262_N, ticks);
    }

    private int k_2293_S() {
        return this.l_4537_E.n_1700_B(v_4262_N);
    }

    public float G_564_y(float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, this.t_4043_B, this.Y_1740_V);
    }

    public void R_4764_Y(int forwardDirection) {
        this.l_4537_E.J_1907_R(J_1907_R, forwardDirection);
    }

    public int h_1847_R() {
        return this.l_4537_E.n_1700_B(J_1907_R);
    }

    public void n_1700_B(J_1907_R boatType) {
        this.l_4537_E.J_1907_R(G_564_y, boatType.ordinal());
    }

    public J_1907_R Q_4569_t() {
        return lightning.product.g_1462_f$J_1907_R.n_1700_B(this.l_4537_E.n_1700_B(G_564_y));
    }

    @Override
    protected boolean h_1847_R(N_4263_v passenger) {
        return this.o_3599_Z().size() < 2 && !this.n_1700_B(FluidTags.J_1907_R);
    }

    @Override
    @Nullable
    public N_4263_v n_3864_h() {
        List<N_4263_v> list = this.o_3599_Z();
        return list.isEmpty() ? null : list.get(0);
    }

    public void n_1700_B(boolean leftInputDown, boolean rightInputDown, boolean forwardInputDown, boolean backInputDown) {
        this.multiplayerClientSuggestionProvider = leftInputDown;
        this.w_1457_N = rightInputDown;
        this.Y_601_j = forwardInputDown;
        this.Y_259_p = backInputDown;
    }

    @Override
    public Packet<?> f_() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public boolean z_1737_N() {
        return this.k_2293_S == lightning.product.g_1462_f$n_1700_B.J_1907_R || this.k_2293_S == lightning.product.g_1462_f$n_1700_B.R_4764_Y;
    }

    public static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R(a_3742_W.h_1847_R, "oak");
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R(a_3742_W.Q_4569_t, "spruce");
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(a_3742_W.M_182_A, "birch");
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R(a_3742_W.t_1786_h, "jungle");
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R(a_3742_W.multiplayerClientSuggestionProvider, "acacia");
        public static final /* enum */ J_1907_R u_1723_Y = new J_1907_R(a_3742_W.w_1457_N, "dark_oak");
        private final String v_4262_N;
        private final T_2915_h w_1484_f;
        private static final /* synthetic */ J_1907_R[] t_148_a;

        public static J_1907_R[] values() {
            return (J_1907_R[])t_148_a.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private J_1907_R(T_2915_h block, String name) {
            this.v_4262_N = name;
            this.w_1484_f = block;
        }

        public String n_1700_B() {
            return this.v_4262_N;
        }

        public T_2915_h J_1907_R() {
            return this.w_1484_f;
        }

        public String toString() {
            return this.v_4262_N;
        }

        public static J_1907_R n_1700_B(int id) {
            J_1907_R[] aboatentity$type = lightning.product.g_1462_f$J_1907_R.values();
            if (id < 0 || id >= aboatentity$type.length) {
                id = 0;
            }
            return aboatentity$type[id];
        }

        public static J_1907_R n_1700_B(String nameIn) {
            J_1907_R[] aboatentity$type = lightning.product.g_1462_f$J_1907_R.values();
            for (int i = 0; i < aboatentity$type.length; ++i) {
                if (!aboatentity$type[i].n_1700_B().equals(nameIn)) continue;
                return aboatentity$type[i];
            }
            return aboatentity$type[0];
        }

        private static /* synthetic */ J_1907_R[] R_4764_Y() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            t_148_a = lightning.product.g_1462_f$J_1907_R.R_4764_Y();
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            u_1723_Y = lightning.product.g_1462_f$n_1700_B.n_1700_B();
        }
    }
}


