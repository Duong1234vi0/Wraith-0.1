/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap
 *  it.unimi.dsi.fastutil.objects.Object2DoubleMap
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.Object2DoubleArrayMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.A_4115_X;
import lightning.product.FluidTags;
import lightning.product.B_4088_l;
import lightning.product.C_4114_x;
import lightning.product.C_415_h;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.D_686_b;
import lightning.product.D_908_R;
import lightning.product.CommandSource;
import lightning.product.E_170_p;
import lightning.product.F_1241_B;
import lightning.product.BlockGetter;
import lightning.product.StringTag;
import lightning.product.ClipContext;
import lightning.product.MutableComponent;
import lightning.product.I_1170_F;
import lightning.product.HitResult;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.K_4096_w;
import lightning.product.L_461_d;
import lightning.product.M_2562_s;
import lightning.product.O_2369_F;
import lightning.product.HoneyBlock;
import lightning.product.Main_1;
import lightning.product.P_11_z;
import lightning.product.P_3504_Q;
import lightning.product.R_1815_U;
import lightning.product.T_1316_M;
import lightning.product.T_2717_K;
import lightning.product.T_2915_h;
import lightning.product.T_603_v;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.V_772_m;
import lightning.product.W_2163_m;
import lightning.product.SoundEvent;
import lightning.product.CollisionContext;
import lightning.product.X_426_i;
import lightning.product.Y_1387_d;
import lightning.product.BlockUtil;
import lightning.product.SeeInvisibles;
import lightning.product.Z_1993_T;
import lightning.product.Z_3903_F;
import lightning.product.Z_4149_q;
import lightning.product.Z_875_P;
import lightning.product.FenceGateBlock;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.PlayerTeam;
import lightning.product.FluidState;
import lightning.product.SoundType;
import lightning.product.c_973_a;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_1186_l;
import lightning.product.f_2392_k;
import lightning.product.g_1462_f;
import lightning.product.g_2336_b;
import lightning.product.Nameable;
import lightning.product.h_256_u;
import lightning.product.h_3270_j;
import lightning.product.i_2909_p;
import lightning.product.TicketType;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.ProtectionEnchantment;
import lightning.product.n_1494_c;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.ClientBootstrap;
import lightning.product.o_3050_h;
import lightning.product.BlockTags;
import lightning.product.q_1803_e;
import lightning.product.q_2896_o;
import lightning.product.q_4099_E;
import lightning.product.r_109_r;
import lightning.product.CrashReportCategory;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.Fluid;
import lightning.product.LightningBolt;
import lightning.product.ParticleTypes;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.v_165_F;
import lightning.product.w_1454_v;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import lightning.product.BooleanOp;
import lightning.product.z_2963_s;
import lightning.product.EntityAnchorArgument;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class N_4263_v
implements CommandSource,
Nameable {
    protected static final Logger D_4792_h = LogManager.getLogger();
    private static final AtomicInteger n_1700_B = new AtomicInteger();
    private static final List<Z_1993_T> J_1907_R = Collections.emptyList();
    private static final I_4817_s R_4764_Y = new I_4817_s(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    private static double G_564_y = 1.0;
    private final t_5_h<?> P_1922_E;
    private int u_1723_Y = n_1700_B.incrementAndGet();
    public boolean s_2632_s;
    private final List<N_4263_v> v_4262_N = Lists.newArrayList();
    protected int l_1233_K;
    @Nullable
    private N_4263_v w_1484_f;
    public boolean z_1333_t;
    public b_4507_u O_508_d;
    public double r_715_M;
    public double A_1038_p;
    public double i_1637_u;
    private e_2866_D t_148_a;
    private c_1514_x s_956_w;
    public e_2866_D Ping = e_2866_D.n_1700_B;
    public float p_178_J;
    public float RealmsClientConfig = -2.14748365E9f;
    public float f_4016_n;
    public float j_276_v;
    public float UploadStatus;
    private I_4817_s u_2550_I = R_4764_Y;
    protected boolean e_1992_r;
    public boolean D_60_a;
    public boolean k_3961_g;
    public boolean Ops;
    protected e_2866_D h_4320_q = e_2866_D.n_1700_B;
    public boolean t_4219_U;
    public float V_1446_Y;
    public float PlayerInfo;
    public float V_1225_t;
    public float U_1241_n;
    private float M_588_G = 1.0f;
    private float P_4830_p = 1.0f;
    public double q_1982_R;
    public double dtoRealmsServerAddress;
    public double w_612_n;
    public float RealmsServerPing;
    public boolean j_1564_a;
    public float M_1641_O;
    protected final Random RealmsWorldOptions = new Random();
    public int RealmsWorldResetDto;
    private int h_1847_R = -this.h_2848_I();
    protected boolean RegionPingResult;
    protected Object2DoubleMap<r_109_r<Fluid>> H_1083_k = new Object2DoubleArrayMap(2);
    protected boolean R_3908_n;
    @Nullable
    protected r_109_r<Fluid> ValueObject;
    public int F_1410_V;
    protected boolean S_4022_R = true;
    protected final C_4114_x l_4537_E;
    protected static final h_256_u<Byte> F_2624_D = C_4114_x.n_1700_B(N_4263_v.class, EntityDataSerializers.n_1700_B);
    private static final h_256_u<Integer> Q_4569_t = C_4114_x.n_1700_B(N_4263_v.class, EntityDataSerializers.J_1907_R);
    private static final h_256_u<Optional<x_282_a>> M_182_A = C_4114_x.n_1700_B(N_4263_v.class, EntityDataSerializers.u_1723_Y);
    private static final h_256_u<Boolean> t_1786_h = C_4114_x.n_1700_B(N_4263_v.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> multiplayerClientSuggestionProvider = C_4114_x.n_1700_B(N_4263_v.class, EntityDataSerializers.t_148_a);
    private static final h_256_u<Boolean> w_1457_N = C_4114_x.n_1700_B(N_4263_v.class, EntityDataSerializers.t_148_a);
    protected static final h_256_u<I_1170_F> RealmsDefaultUncaughtExceptionHandler = C_4114_x.n_1700_B(N_4263_v.class, EntityDataSerializers.w_1457_N);
    public boolean y_1700_S;
    public int u_744_e;
    public int RetryCallException;
    public int r_3651_U;
    private boolean Y_601_j;
    private e_2866_D Y_259_p;
    public boolean RowButton;
    public boolean LongRunningTask;
    private int Q_2552_b;
    protected boolean j_2266_I;
    protected int S_980_j;
    protected c_1514_x R_3077_Z;
    private boolean C_2741_M;
    protected UUID RealmsScreenWithCallback = u_530_F.n_1700_B(this.RealmsWorldOptions);
    protected String M_2677_i = this.RealmsScreenWithCallback.toString();
    protected boolean c_132_F;
    private final Set<String> k_2293_S = Sets.newHashSet();
    private boolean q_2307_F;
    private final double[] Z_875_P = new double[]{0.0, 0.0, 0.0};
    private long c_3005_b;
    private R_1815_U H_2857_Y;
    private float A_4115_X;
    public boolean g_4106_L;
    public boolean RealmsClientOutdatedScreen;

    public N_4263_v(t_5_h<?> entityTypeIn, b_4507_u worldIn) {
        this.P_1922_E = entityTypeIn;
        this.O_508_d = worldIn;
        this.H_2857_Y = entityTypeIn.u_2550_I();
        this.t_148_a = e_2866_D.n_1700_B;
        this.s_956_w = c_1514_x.ZERO;
        this.Y_259_p = e_2866_D.n_1700_B;
        this.J_1907_R(0.0, 0.0, 0.0);
        this.l_4537_E = new C_4114_x(this);
        this.l_4537_E.n_1700_B(F_2624_D, (byte)0);
        this.l_4537_E.n_1700_B(Q_4569_t, this.P_5000_x());
        this.l_4537_E.n_1700_B(t_1786_h, false);
        this.l_4537_E.n_1700_B(M_182_A, Optional.empty());
        this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider, false);
        this.l_4537_E.n_1700_B(w_1457_N, false);
        this.l_4537_E.n_1700_B(RealmsDefaultUncaughtExceptionHandler, I_1170_F.n_1700_B);
        this.a_();
        this.A_4115_X = this.n_1700_B(I_1170_F.n_1700_B, this.H_2857_Y);
    }

    public boolean n_1700_B(c_1514_x p_242278_1_, K_4074_S p_242278_2_) {
        s_1395_c voxelshape = p_242278_2_.R_4764_Y((BlockGetter)this.O_508_d, p_242278_1_, CollisionContext.n_1700_B(this));
        s_1395_c voxelshape1 = voxelshape.n_1700_B(p_242278_1_.getX(), (double)p_242278_1_.getY(), (double)p_242278_1_.getZ());
        return x_268_Y.R_4764_Y(voxelshape1, x_268_Y.n_1700_B(this.i_601_W()), BooleanOp.t_148_a);
    }

    public int i_1637_u() {
        o_3050_h team = this.L_1362_X();
        return team != null && team.P_4830_p().G_564_y() != null ? team.P_4830_p().G_564_y() : 0xFFFFFF;
    }

    public boolean d_2461_k() {
        return false;
    }

    public final void Ping() {
        if (this.H_1883_T()) {
            this.C_3538_G();
        }
        if (this.y_2772_m()) {
            this.A_3959_N();
        }
    }

    public boolean p_178_J() {
        v_165_F event = new v_165_F(this);
        lightning.product.A_4115_X.n_1700_B(event);
        return !event.n_1700_B();
    }

    public void n_1700_B(double x, double y, double z) {
        this.a_(new e_2866_D(x, y, z));
    }

    public void a_(e_2866_D p_242277_1_) {
        this.Y_259_p = p_242277_1_;
    }

    public e_2866_D RealmsClientConfig() {
        return this.Y_259_p;
    }

    public t_5_h<?> f_4016_n() {
        return this.P_1922_E;
    }

    public int j_276_v() {
        return this.u_1723_Y;
    }

    public void G_564_y(int id) {
        this.u_1723_Y = id;
    }

    public Set<String> UploadStatus() {
        return this.k_2293_S;
    }

    public boolean G_564_y(String tag) {
        return this.k_2293_S.size() >= 1024 ? false : this.k_2293_S.add(tag);
    }

    public boolean P_1922_E(String tag) {
        return this.k_2293_S.remove(tag);
    }

    public void e_1992_r() {
        this.Ops();
    }

    protected abstract void a_();

    public C_4114_x D_60_a() {
        return this.l_4537_E;
    }

    public boolean equals(Object p_equals_1_) {
        if (p_equals_1_ instanceof N_4263_v) {
            return ((N_4263_v)p_equals_1_).u_1723_Y == this.u_1723_Y;
        }
        return false;
    }

    public int hashCode() {
        return this.u_1723_Y;
    }

    protected void k_3961_g() {
        if (this.O_508_d != null) {
            for (double d0 = this.X_2960_b(); d0 > 0.0 && d0 < 256.0; d0 += 1.0) {
                this.J_1907_R(this.O_3598_v(), d0, this.l_2647_k());
                if (this.O_508_d.u_1723_Y(this)) break;
            }
            this.v_4262_N(e_2866_D.n_1700_B);
            this.f_4016_n = 0.0f;
        }
    }

    public void Ops() {
        this.t_4219_U = true;
    }

    public void J_1907_R(I_1170_F poseIn) {
        this.l_4537_E.J_1907_R(RealmsDefaultUncaughtExceptionHandler, poseIn);
    }

    public I_1170_F h_4320_q() {
        return this.l_4537_E.n_1700_B(RealmsDefaultUncaughtExceptionHandler);
    }

    public boolean n_1700_B(N_4263_v entity, double distance) {
        double d0 = entity.t_148_a.J_1907_R - this.t_148_a.J_1907_R;
        double d1 = entity.t_148_a.R_4764_Y - this.t_148_a.R_4764_Y;
        double d2 = entity.t_148_a.G_564_y - this.t_148_a.G_564_y;
        return d0 * d0 + d1 * d1 + d2 * d2 < distance * distance;
    }

    protected void J_1907_R(float yaw, float pitch) {
        this.p_178_J = yaw % 360.0f;
        this.f_4016_n = pitch % 360.0f;
    }

    public void J_1907_R(double x, double y, double z) {
        this.Q_4569_t(x, y, z);
        this.n_1700_B(this.H_2857_Y.n_1700_B(x, y, z));
    }

    protected void t_4219_U() {
        this.J_1907_R(this.t_148_a.J_1907_R, this.t_148_a.R_4764_Y, this.t_148_a.G_564_y);
    }

    public void n_1700_B(double yaw, double pitch) {
        double d0 = pitch * 0.15;
        double d1 = yaw * 0.15;
        this.f_4016_n = (float)((double)this.f_4016_n + d0);
        this.p_178_J = (float)((double)this.p_178_J + d1);
        this.f_4016_n = u_530_F.n_1700_B(this.f_4016_n, -90.0f, 90.0f);
        this.UploadStatus = (float)((double)this.UploadStatus + d0);
        this.j_276_v = (float)((double)this.j_276_v + d1);
        this.UploadStatus = u_530_F.n_1700_B(this.UploadStatus, -90.0f, 90.0f);
        if (this.w_1484_f != null) {
            this.w_1484_f.w_1484_f(this);
        }
    }

    public void v_() {
        if (!this.O_508_d.Y_259_p) {
            this.J_1907_R(6, this.j_306_t());
        }
        this.V_1446_Y();
    }

    public void V_1446_Y() {
        if (this.y_2772_m() && this.l_3609_d().t_4219_U) {
            this.A_3959_N();
        }
        if (this.l_1233_K > 0) {
            --this.l_1233_K;
        }
        this.V_1446_Y = this.PlayerInfo;
        this.UploadStatus = this.f_4016_n;
        this.j_276_v = this.p_178_J;
        this.J_739_q();
        if (this.s_956_w()) {
            this.RealmsClientOutdatedScreen();
        }
        this.RealmsScreenWithCallback();
        this.h_1847_R();
        this.R_3077_Z();
        if (this.O_508_d.Y_259_p) {
            this.RealmsServerPing();
        } else if (this.h_1847_R > 0) {
            if (this.r_3651_U()) {
                this.u_1723_Y(this.h_1847_R - 4);
                if (this.h_1847_R < 0) {
                    this.RealmsServerPing();
                }
            } else {
                if (this.h_1847_R % 20 == 0 && !this.W_3464_O()) {
                    this.n_1700_B(P_11_z.R_4764_Y, 1.0f);
                }
                this.u_1723_Y(this.h_1847_R - 1);
            }
        }
        if (this.W_3464_O()) {
            this.dtoRealmsServerAddress();
            this.U_1241_n *= 0.5f;
        }
        if (this.X_2960_b() < -64.0) {
            this.j_1564_a();
        }
        if (!this.O_508_d.Y_259_p) {
            this.J_1907_R(0, this.h_1847_R > 0);
        }
        this.S_4022_R = false;
    }

    public void PlayerInfo() {
        this.Q_2552_b = this.C_1162_e();
    }

    public boolean V_1225_t() {
        return this.Q_2552_b > 0;
    }

    protected void U_1241_n() {
        if (this.V_1225_t()) {
            --this.Q_2552_b;
        }
    }

    public int q_1982_R() {
        return 0;
    }

    protected void dtoRealmsServerAddress() {
        if (!this.r_3651_U()) {
            this.P_1922_E(15);
            this.n_1700_B(P_11_z.G_564_y, 4.0f);
        }
    }

    public void P_1922_E(int seconds) {
        int i = seconds * 20;
        if (this instanceof r_4811_B) {
            i = ProtectionEnchantment.n_1700_B((r_4811_B)this, i);
        }
        if (this.h_1847_R < i) {
            this.u_1723_Y(i);
        }
    }

    public void u_1723_Y(int ticks) {
        this.h_1847_R = ticks;
    }

    public int w_612_n() {
        return this.h_1847_R;
    }

    public void RealmsServerPing() {
        this.u_1723_Y(0);
    }

    protected void j_1564_a() {
        this.Ops();
    }

    public boolean R_4764_Y(double x, double y, double z) {
        return this.J_1907_R(this.i_601_W().offset(x, y, z));
    }

    private boolean J_1907_R(I_4817_s bb) {
        return this.O_508_d.a_(this, bb) && !this.O_508_d.G_564_y(bb);
    }

    public void u_1723_Y(boolean grounded) {
        this.e_1992_r = grounded;
    }

    public boolean M_1641_O() {
        return this.e_1992_r;
    }

    public void n_1700_B(L_461_d typeIn, e_2866_D pos) {
        double preX = this.O_3598_v();
        double preZ = this.l_2647_k();
        if (this.j_1564_a) {
            this.n_1700_B(this.i_601_W().offset(pos));
            this.ValueObject();
        } else {
            e_2866_D vector3d;
            if (typeIn == L_461_d.R_4764_Y && (pos = this.J_1907_R(pos)).equals(e_2866_D.n_1700_B)) {
                return;
            }
            if (this.RealmsClientOutdatedScreen) {
                this.RealmsClientOutdatedScreen = false;
                pos = new e_2866_D(pos.J_1907_R * 0.25, pos.R_4764_Y * (double)0.05f, pos.G_564_y * 0.25);
                this.h_1847_R(0.0, 0.0, 0.0);
            }
            if (this.h_4320_q.v_4262_N() > 1.0E-7) {
                pos = pos.w_1484_f(this.h_4320_q);
                this.h_4320_q = e_2866_D.n_1700_B;
                this.v_4262_N(e_2866_D.n_1700_B);
            }
            boolean ignoreVertical = false;
            boolean ignoreHorizontal = false;
            pos = this.n_1700_B(pos, typeIn);
            e_2866_D predictPos = this.n_1700_B(pos, typeIn);
            e_2866_D predictPos3d = this.n_1700_B(predictPos, false, false);
            if (this instanceof V_772_m) {
                e_2866_D predictedPosition = this.s_4990_V().P_1922_E(predictPos3d);
                e_2866_D fromPosition = this.s_4990_V();
                boolean collidedVertically = predictPos.R_4764_Y != predictPos3d.R_4764_Y;
                boolean collidedHorizontally = !u_530_F.J_1907_R(predictPos.J_1907_R, predictPos3d.J_1907_R) || !u_530_F.J_1907_R(predictPos.G_564_y, predictPos3d.G_564_y);
                boolean onGround = collidedVertically && predictPos.R_4764_Y < 0.0;
                I_4817_s bb = this.i_601_W();
                M_2562_s move = new M_2562_s(fromPosition, predictedPosition, pos, onGround, collidedHorizontally, collidedVertically, bb);
                lightning.product.A_4115_X.n_1700_B(move);
                pos = move.R_4764_Y();
                ignoreHorizontal = move.u_1723_Y();
                ignoreVertical = move.v_4262_N();
            }
            if ((vector3d = this.n_1700_B(pos, ignoreHorizontal, ignoreVertical)).v_4262_N() > 1.0E-7) {
                this.n_1700_B(this.i_601_W().offset(vector3d));
                this.ValueObject();
            }
            this.D_60_a = !u_530_F.J_1907_R(pos.J_1907_R, vector3d.J_1907_R) || !u_530_F.J_1907_R(pos.G_564_y, vector3d.G_564_y);
            this.k_3961_g = pos.R_4764_Y != vector3d.R_4764_Y;
            this.e_1992_r = this.k_3961_g && pos.R_4764_Y < 0.0;
            c_1514_x blockpos = this.RealmsWorldOptions();
            K_4074_S blockstate = this.O_508_d.getBlockState(blockpos);
            this.n_1700_B(vector3d.R_4764_Y, this.e_1992_r, blockstate, blockpos);
            e_2866_D vector3d1 = this.I_4348_c();
            if (pos.J_1907_R != vector3d.J_1907_R) {
                this.h_1847_R(0.0, vector3d1.R_4764_Y, vector3d1.G_564_y);
            }
            if (pos.G_564_y != vector3d.G_564_y) {
                this.h_1847_R(vector3d1.J_1907_R, vector3d1.R_4764_Y, 0.0);
            }
            T_2915_h block = blockstate.J_1907_R();
            if (pos.R_4764_Y != vector3d.R_4764_Y) {
                block.n_1700_B(this.O_508_d, this);
            }
            if (this.e_1992_r && !this.TextRenderingUtils()) {
                block.n_1700_B(this.O_508_d, blockpos, this);
            }
            if (this.RetryCallException() && !this.y_2772_m()) {
                double d0 = vector3d.J_1907_R;
                double d1 = vector3d.R_4764_Y;
                double d2 = vector3d.G_564_y;
                if (!block.n_1700_B(BlockTags.h_4320_q)) {
                    d1 = 0.0;
                }
                this.PlayerInfo = (float)((double)this.PlayerInfo + (double)u_530_F.n_1700_B(N_4263_v.R_4764_Y(vector3d)) * 0.6);
                this.V_1225_t = (float)((double)this.V_1225_t + (double)u_530_F.n_1700_B(d0 * d0 + d1 * d1 + d2 * d2) * 0.6);
                if (this.V_1225_t > this.M_588_G && !blockstate.v_4262_N()) {
                    this.M_588_G = this.R_3908_n();
                    if (this.RowButton()) {
                        N_4263_v entity = this.H_1883_T() && this.n_3864_h() != null ? this.n_3864_h() : this;
                        float f = entity == this ? 0.35f : 0.4f;
                        e_2866_D vector3d2 = entity.I_4348_c();
                        float f1 = u_530_F.n_1700_B(vector3d2.J_1907_R * vector3d2.J_1907_R * (double)0.2f + vector3d2.R_4764_Y * vector3d2.R_4764_Y + vector3d2.G_564_y * vector3d2.G_564_y * (double)0.2f) * f;
                        if (f1 > 1.0f) {
                            f1 = 1.0f;
                        }
                        this.v_4262_N(f1);
                    } else {
                        this.J_1907_R(blockpos, blockstate);
                    }
                } else if (this.V_1225_t > this.P_4830_p && this.RealmsDefaultUncaughtExceptionHandler() && blockstate.v_4262_N()) {
                    this.P_4830_p = this.w_1484_f(this.V_1225_t);
                }
            }
            try {
                this.F_2624_D();
            }
            catch (Throwable throwable) {
                n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Checking entity block collision");
                CrashReportCategory crashreportcategory = crashreport.n_1700_B("Entity being checked for collision");
                this.n_1700_B(crashreportcategory);
                throw new ReportedException(crashreport);
            }
            float f2 = this.RegionPingResult();
            this.v_4262_N(this.I_4348_c().G_564_y(f2, 1.0, f2));
            if (this.O_508_d.R_4764_Y(this.i_601_W().shrink(0.001)).noneMatch(p_233572_0_ -> p_233572_0_.n_1700_B(BlockTags.j_276_v) || p_233572_0_.n_1700_B(a_3742_W.H_2857_Y)) && this.h_1847_R <= 0) {
                this.u_1723_Y(-this.h_2848_I());
            }
            if (this.j_2266_I() && this.RealmsPersistence()) {
                this.n_1700_B(SoundEvents.y_4642_Y, 0.7f, 1.6f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.4f);
                this.u_1723_Y(-this.h_2848_I());
            }
        }
    }

    protected c_1514_x RealmsWorldOptions() {
        c_1514_x blockpos1;
        K_4074_S blockstate;
        T_2915_h block;
        int k;
        int j;
        int i = u_530_F.R_4764_Y(this.t_148_a.J_1907_R);
        c_1514_x blockpos = new c_1514_x(i, j = u_530_F.R_4764_Y(this.t_148_a.R_4764_Y - (double)0.2f), k = u_530_F.R_4764_Y(this.t_148_a.G_564_y));
        if (this.O_508_d.getBlockState(blockpos).v_4262_N() && ((block = (blockstate = this.O_508_d.getBlockState(blockpos1 = blockpos.down())).J_1907_R()).n_1700_B(BlockTags.G_624_v) || block.n_1700_B(BlockTags.x_607_J) || block instanceof FenceGateBlock)) {
            return blockpos1;
        }
        return blockpos;
    }

    protected float RealmsWorldResetDto() {
        float f = this.O_508_d.getBlockState(this.b_2312_j()).J_1907_R().M_182_A();
        float f1 = this.O_508_d.getBlockState(this.H_1083_k()).J_1907_R().M_182_A();
        return (double)f == 1.0 ? f1 : f;
    }

    protected float RegionPingResult() {
        T_2915_h block = this.O_508_d.getBlockState(this.b_2312_j()).J_1907_R();
        float f = block.Q_4569_t();
        if (block != a_3742_W.c_3005_b && block != a_3742_W.S_4325_V) {
            return (double)f == 1.0 ? this.O_508_d.getBlockState(this.H_1083_k()).J_1907_R().Q_4569_t() : f;
        }
        return f;
    }

    protected c_1514_x H_1083_k() {
        return new c_1514_x(this.t_148_a.J_1907_R, this.i_601_W().minY - 0.5000001, this.t_148_a.G_564_y);
    }

    protected e_2866_D n_1700_B(e_2866_D vec, L_461_d mover) {
        return vec;
    }

    protected e_2866_D J_1907_R(e_2866_D pos) {
        if (pos.v_4262_N() <= 1.0E-7) {
            return pos;
        }
        long i = this.O_508_d.X_933_l();
        if (i != this.c_3005_b) {
            Arrays.fill(this.Z_875_P, 0.0);
            this.c_3005_b = i;
        }
        if (pos.J_1907_R != 0.0) {
            double d2 = this.n_1700_B(b_257_Y.n_1700_B.n_1700_B, pos.J_1907_R);
            return Math.abs(d2) <= (double)1.0E-5f ? e_2866_D.n_1700_B : new e_2866_D(d2, 0.0, 0.0);
        }
        if (pos.R_4764_Y != 0.0) {
            double d1 = this.n_1700_B(b_257_Y.n_1700_B.J_1907_R, pos.R_4764_Y);
            return Math.abs(d1) <= (double)1.0E-5f ? e_2866_D.n_1700_B : new e_2866_D(0.0, d1, 0.0);
        }
        if (pos.G_564_y != 0.0) {
            double d0 = this.n_1700_B(b_257_Y.n_1700_B.R_4764_Y, pos.G_564_y);
            return Math.abs(d0) <= (double)1.0E-5f ? e_2866_D.n_1700_B : new e_2866_D(0.0, 0.0, d0);
        }
        return e_2866_D.n_1700_B;
    }

    private double n_1700_B(b_257_Y.n_1700_B axis, double distance) {
        int i = axis.ordinal();
        double d0 = u_530_F.n_1700_B(distance + this.Z_875_P[i], -0.51, 0.51);
        distance = d0 - this.Z_875_P[i];
        this.Z_875_P[i] = d0;
        return distance;
    }

    private e_2866_D n_1700_B(e_2866_D vec, boolean horizontal, boolean vertical) {
        boolean flag3;
        I_4817_s axisalignedbb = this.i_601_W();
        CollisionContext iselectioncontext = CollisionContext.n_1700_B(this);
        s_1395_c voxelshape = this.O_508_d.H_2857_Y().R_4764_Y();
        Stream stream = Stream.empty();
        Stream<s_1395_c> stream1 = this.O_508_d.n_1700_B(this, axisalignedbb.expand(vec), (N_4263_v p_233561_0_) -> true);
        Main_1<s_1395_c> reuseablestream = new Main_1<s_1395_c>(Stream.concat(stream1, stream));
        e_2866_D vector3d = vec.v_4262_N() == 0.0 ? vec : N_4263_v.n_1700_B(this, vec, axisalignedbb, this.O_508_d, iselectioncontext, reuseablestream, horizontal, vertical);
        boolean flag = vec.J_1907_R != vector3d.J_1907_R;
        boolean flag1 = vec.R_4764_Y != vector3d.R_4764_Y;
        boolean flag2 = vec.G_564_y != vector3d.G_564_y;
        boolean bl = flag3 = this.e_1992_r || flag1 && vec.R_4764_Y < 0.0;
        if (this.RealmsServerPing > 0.0f && flag3 && (flag || flag2)) {
            e_2866_D vector3d3;
            e_2866_D vector3d1 = N_4263_v.n_1700_B(this, new e_2866_D(vec.J_1907_R, this.RealmsServerPing, vec.G_564_y), axisalignedbb, this.O_508_d, iselectioncontext, reuseablestream, horizontal, vertical);
            e_2866_D vector3d2 = N_4263_v.n_1700_B(this, new e_2866_D(0.0, this.RealmsServerPing, 0.0), axisalignedbb.expand(vec.J_1907_R, 0.0, vec.G_564_y), this.O_508_d, iselectioncontext, reuseablestream, horizontal, vertical);
            if (vector3d2.R_4764_Y < (double)this.RealmsServerPing && N_4263_v.R_4764_Y(vector3d3 = N_4263_v.n_1700_B(this, new e_2866_D(vec.J_1907_R, 0.0, vec.G_564_y), axisalignedbb.offset(vector3d2), this.O_508_d, iselectioncontext, reuseablestream, horizontal, vertical).P_1922_E(vector3d2)) > N_4263_v.R_4764_Y(vector3d1)) {
                vector3d1 = vector3d3;
            }
            if (N_4263_v.R_4764_Y(vector3d1) > N_4263_v.R_4764_Y(vector3d)) {
                return vector3d1.P_1922_E(N_4263_v.n_1700_B(this, new e_2866_D(0.0, -vector3d1.R_4764_Y + vec.R_4764_Y, 0.0), axisalignedbb.offset(vector3d1), this.O_508_d, iselectioncontext, reuseablestream, horizontal, vertical));
            }
        }
        return vector3d;
    }

    public static double R_4764_Y(e_2866_D vec) {
        return vec.J_1907_R * vec.J_1907_R + vec.G_564_y * vec.G_564_y;
    }

    public static e_2866_D n_1700_B(@Nullable N_4263_v entity, e_2866_D vec, I_4817_s collisionBox, b_4507_u world, CollisionContext context, Main_1<s_1395_c> potentialHits) {
        return N_4263_v.n_1700_B(entity, vec, collisionBox, world, context, potentialHits, false, false);
    }

    public static e_2866_D n_1700_B(@Nullable N_4263_v entity, e_2866_D vec, I_4817_s collisionBox, b_4507_u world, CollisionContext context, Main_1<s_1395_c> potentialHits, boolean hor, boolean ver) {
        boolean flag2;
        boolean flag = vec.J_1907_R == 0.0;
        boolean flag1 = vec.R_4764_Y == 0.0;
        boolean bl = flag2 = vec.G_564_y == 0.0;
        if (flag && flag1 || flag && flag2 || flag1 && flag2) {
            return N_4263_v.n_1700_B(vec, collisionBox, world, context, potentialHits);
        }
        Main_1<s_1395_c> reuseablestream = new Main_1<s_1395_c>(Stream.concat(potentialHits.n_1700_B(), world.J_1907_R(entity, collisionBox.expand(vec))));
        return N_4263_v.n_1700_B(vec, collisionBox, reuseablestream, hor, ver);
    }

    public static e_2866_D n_1700_B(e_2866_D vec, I_4817_s collisionBox, Main_1<s_1395_c> potentialHits, boolean hor, boolean ver) {
        double d0 = vec.J_1907_R;
        double d1 = vec.R_4764_Y;
        double d2 = vec.G_564_y;
        if (!ver && d1 != 0.0 && (d1 = x_268_Y.n_1700_B(b_257_Y.n_1700_B.J_1907_R, collisionBox, potentialHits.n_1700_B(), d1)) != 0.0) {
            collisionBox = collisionBox.offset(0.0, d1, 0.0);
        }
        if (!hor) {
            boolean flag;
            boolean bl = flag = Math.abs(d0) < Math.abs(d2);
            if (flag && d2 != 0.0 && (d2 = x_268_Y.n_1700_B(b_257_Y.n_1700_B.R_4764_Y, collisionBox, potentialHits.n_1700_B(), d2)) != 0.0) {
                collisionBox = collisionBox.offset(0.0, 0.0, d2);
            }
            if (d0 != 0.0) {
                d0 = x_268_Y.n_1700_B(b_257_Y.n_1700_B.n_1700_B, collisionBox, potentialHits.n_1700_B(), d0);
                if (!flag && d0 != 0.0) {
                    collisionBox = collisionBox.offset(d0, 0.0, 0.0);
                }
            }
            if (!flag && d2 != 0.0) {
                d2 = x_268_Y.n_1700_B(b_257_Y.n_1700_B.R_4764_Y, collisionBox, potentialHits.n_1700_B(), d2);
            }
        }
        return new e_2866_D(d0, d1, d2);
    }

    public e_2866_D G_564_y(e_2866_D vec) {
        return this.n_1700_B(vec, false, false);
    }

    public static e_2866_D n_1700_B(e_2866_D vec, I_4817_s collisionBox, T_1316_M worldIn, CollisionContext selectionContext, Main_1<s_1395_c> potentialHits) {
        boolean flag;
        double d0 = vec.J_1907_R;
        double d1 = vec.R_4764_Y;
        double d2 = vec.G_564_y;
        if (d1 != 0.0 && (d1 = x_268_Y.n_1700_B(b_257_Y.n_1700_B.J_1907_R, collisionBox, worldIn, d1, selectionContext, potentialHits.n_1700_B())) != 0.0) {
            collisionBox = collisionBox.offset(0.0, d1, 0.0);
        }
        boolean bl = flag = Math.abs(d0) < Math.abs(d2);
        if (flag && d2 != 0.0 && (d2 = x_268_Y.n_1700_B(b_257_Y.n_1700_B.R_4764_Y, collisionBox, worldIn, d2, selectionContext, potentialHits.n_1700_B())) != 0.0) {
            collisionBox = collisionBox.offset(0.0, 0.0, d2);
        }
        if (d0 != 0.0) {
            d0 = x_268_Y.n_1700_B(b_257_Y.n_1700_B.n_1700_B, collisionBox, worldIn, d0, selectionContext, potentialHits.n_1700_B());
            if (!flag && d0 != 0.0) {
                collisionBox = collisionBox.offset(d0, 0.0, 0.0);
            }
        }
        if (!flag && d2 != 0.0) {
            d2 = x_268_Y.n_1700_B(b_257_Y.n_1700_B.R_4764_Y, collisionBox, worldIn, d2, selectionContext, potentialHits.n_1700_B());
        }
        return new e_2866_D(d0, d1, d2);
    }

    protected float R_3908_n() {
        return (int)this.V_1225_t + 1;
    }

    public void ValueObject() {
        I_4817_s axisalignedbb = this.i_601_W();
        this.Q_4569_t((axisalignedbb.minX + axisalignedbb.maxX) / 2.0, axisalignedbb.minY, (axisalignedbb.minZ + axisalignedbb.maxZ) / 2.0);
    }

    protected SoundEvent F_1410_V() {
        return SoundEvents.J_3635_s;
    }

    protected SoundEvent S_4022_R() {
        return SoundEvents.y_2447_C;
    }

    protected SoundEvent l_4537_E() {
        return SoundEvents.y_2447_C;
    }

    protected void F_2624_D() {
        I_4817_s axisalignedbb = this.i_601_W();
        c_1514_x blockpos = new c_1514_x(axisalignedbb.minX + 0.001, axisalignedbb.minY + 0.001, axisalignedbb.minZ + 0.001);
        c_1514_x blockpos1 = new c_1514_x(axisalignedbb.maxX - 0.001, axisalignedbb.maxY - 0.001, axisalignedbb.maxZ - 0.001);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        if (this.O_508_d.n_1700_B(blockpos, blockpos1)) {
            for (int i = blockpos.getX(); i <= blockpos1.getX(); ++i) {
                for (int j = blockpos.getY(); j <= blockpos1.getY(); ++j) {
                    for (int k = blockpos.getZ(); k <= blockpos1.getZ(); ++k) {
                        blockpos$mutable.n_1700_B(i, j, k);
                        K_4074_S blockstate = this.O_508_d.getBlockState(blockpos$mutable);
                        try {
                            blockstate.n_1700_B(this.O_508_d, (c_1514_x)blockpos$mutable, this);
                            this.n_1700_B(blockstate);
                            continue;
                        }
                        catch (Throwable throwable) {
                            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Colliding entity with block");
                            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Block being collided with");
                            CrashReportCategory.n_1700_B(crashreportcategory, blockpos$mutable, blockstate);
                            throw new ReportedException(crashreport);
                        }
                    }
                }
            }
        }
    }

    protected void n_1700_B(K_4074_S state) {
    }

    protected void J_1907_R(c_1514_x pos, K_4074_S blockIn) {
        if (!blockIn.R_4764_Y().n_1700_B()) {
            K_4074_S blockstate = this.O_508_d.getBlockState(pos.up());
            SoundType soundtype = blockstate.n_1700_B(a_3742_W.X_290_I) ? blockstate.Q_4569_t() : blockIn.Q_4569_t();
            this.n_1700_B(soundtype.G_564_y(), soundtype.n_1700_B() * 0.15f, soundtype.J_1907_R());
        }
    }

    protected void v_4262_N(float volume) {
        this.n_1700_B(this.F_1410_V(), volume, 1.0f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.4f);
    }

    protected float w_1484_f(float volume) {
        return 0.0f;
    }

    protected boolean RealmsDefaultUncaughtExceptionHandler() {
        return false;
    }

    public void n_1700_B(SoundEvent soundIn, float volume, float pitch) {
        if (!this.y_1700_S()) {
            this.O_508_d.n_1700_B((a_3913_L)null, this.O_3598_v(), this.X_2960_b(), this.l_2647_k(), soundIn, this.r_2478_U(), volume, pitch);
        }
    }

    public boolean y_1700_S() {
        return this.l_4537_E.n_1700_B(multiplayerClientSuggestionProvider);
    }

    public void v_4262_N(boolean isSilent) {
        this.l_4537_E.J_1907_R(multiplayerClientSuggestionProvider, isSilent);
    }

    public boolean u_744_e() {
        return this.l_4537_E.n_1700_B(w_1457_N);
    }

    public void w_1484_f(boolean noGravity) {
        this.l_4537_E.J_1907_R(w_1457_N, noGravity);
    }

    protected boolean RetryCallException() {
        return true;
    }

    protected void n_1700_B(double y, boolean onGroundIn, K_4074_S state, c_1514_x pos) {
        if (onGroundIn) {
            if (this.U_1241_n > 0.0f) {
                state.J_1907_R().n_1700_B(this.O_508_d, pos, this, this.U_1241_n);
            }
            this.U_1241_n = 0.0f;
        } else if (y < 0.0) {
            this.U_1241_n = (float)((double)this.U_1241_n - y);
        }
    }

    public boolean r_3651_U() {
        return this.f_4016_n().R_4764_Y();
    }

    public boolean R_4764_Y(float distance, float damageMultiplier) {
        if (this.H_1883_T()) {
            for (N_4263_v entity : this.o_3599_Z()) {
                entity.R_4764_Y(distance, damageMultiplier);
            }
        }
        return false;
    }

    public boolean RowButton() {
        return this.RegionPingResult;
    }

    private boolean t_148_a() {
        c_1514_x blockpos = this.b_2312_j();
        return this.O_508_d.Q_2552_b(blockpos) || this.O_508_d.Q_2552_b(new c_1514_x((double)blockpos.getX(), this.i_601_W().maxY, (double)blockpos.getZ()));
    }

    private boolean u_2550_I() {
        return this.O_508_d.getBlockState(this.b_2312_j()).n_1700_B(a_3742_W.S_4325_V);
    }

    public boolean LongRunningTask() {
        return this.RowButton() || this.t_148_a();
    }

    public boolean j_2266_I() {
        return this.RowButton() || this.t_148_a() || this.u_2550_I();
    }

    public boolean S_980_j() {
        return this.RowButton() || this.u_2550_I();
    }

    public boolean z_1737_N() {
        return this.R_3908_n && this.RowButton();
    }

    public void R_3077_Z() {
        if (this.C_1269_X()) {
            this.s_956_w(this.o_2341_D() && this.RowButton() && !this.y_2772_m());
        } else {
            this.s_956_w(this.o_2341_D() && this.z_1737_N() && !this.y_2772_m());
        }
    }

    protected boolean RealmsScreenWithCallback() {
        this.H_1083_k.clear();
        this.M_2677_i();
        double d0 = this.O_508_d.G_624_v().G_564_y() ? 0.007 : 0.0023333333333333335;
        boolean flag = this.n_1700_B(FluidTags.R_4764_Y, d0);
        return this.RowButton() || flag;
    }

    void M_2677_i() {
        if (this.l_3609_d() instanceof g_1462_f) {
            this.RegionPingResult = false;
        } else if (this.n_1700_B(FluidTags.J_1907_R, 0.014)) {
            if (!this.RegionPingResult && !this.S_4022_R) {
                this.c_132_F();
            }
            this.U_1241_n = 0.0f;
            this.RegionPingResult = true;
            this.RealmsServerPing();
        } else {
            this.RegionPingResult = false;
        }
    }

    private void h_1847_R() {
        g_1462_f boatentity;
        this.R_3908_n = this.n_1700_B(FluidTags.J_1907_R);
        this.ValueObject = null;
        double d0 = this.X_2048_Y() - 0.1111111119389534;
        N_4263_v entity = this.l_3609_d();
        if (entity instanceof g_1462_f && !(boatentity = (g_1462_f)entity).z_1737_N() && boatentity.i_601_W().maxY >= d0 && boatentity.i_601_W().minY <= d0) {
            return;
        }
        c_1514_x blockpos = new c_1514_x(this.O_3598_v(), d0, this.l_2647_k());
        FluidState fluidstate = this.O_508_d.getFluidState(blockpos);
        for (r_109_r r_109_r2 : FluidTags.n_1700_B()) {
            if (!fluidstate.n_1700_B(r_109_r2)) continue;
            double d1 = (float)blockpos.getY() + fluidstate.n_1700_B((BlockGetter)this.O_508_d, blockpos);
            if (d1 > d0) {
                this.ValueObject = r_109_r2;
            }
            return;
        }
    }

    protected void c_132_F() {
        E_170_p.n_1700_B(this);
        N_4263_v entity = this.H_1883_T() && this.n_3864_h() != null ? this.n_3864_h() : this;
        float f = entity == this ? 0.2f : 0.9f;
        e_2866_D vector3d = entity.I_4348_c();
        float f1 = u_530_F.n_1700_B(vector3d.J_1907_R * vector3d.J_1907_R * (double)0.2f + vector3d.R_4764_Y * vector3d.R_4764_Y + vector3d.G_564_y * vector3d.G_564_y * (double)0.2f) * f;
        if (f1 > 1.0f) {
            f1 = 1.0f;
        }
        if ((double)f1 < 0.25) {
            this.n_1700_B(this.S_4022_R(), f1, 1.0f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.4f);
        } else {
            this.n_1700_B(this.l_4537_E(), f1, 1.0f + (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.4f);
        }
        float f2 = u_530_F.R_4764_Y(this.X_2960_b());
        int i = 0;
        while ((float)i < 1.0f + this.H_2857_Y.n_1700_B * 20.0f) {
            double d0 = (this.RealmsWorldOptions.nextDouble() * 2.0 - 1.0) * (double)this.H_2857_Y.n_1700_B;
            double d1 = (this.RealmsWorldOptions.nextDouble() * 2.0 - 1.0) * (double)this.H_2857_Y.n_1700_B;
            this.O_508_d.n_1700_B(ParticleTypes.P_1922_E, this.O_3598_v() + d0, (double)(f2 + 1.0f), this.l_2647_k() + d1, vector3d.J_1907_R, vector3d.R_4764_Y - this.RealmsWorldOptions.nextDouble() * (double)0.2f, vector3d.G_564_y);
            ++i;
        }
        int j = 0;
        while ((float)j < 1.0f + this.H_2857_Y.n_1700_B * 20.0f) {
            double d2 = (this.RealmsWorldOptions.nextDouble() * 2.0 - 1.0) * (double)this.H_2857_Y.n_1700_B;
            double d3 = (this.RealmsWorldOptions.nextDouble() * 2.0 - 1.0) * (double)this.H_2857_Y.n_1700_B;
            this.O_508_d.n_1700_B(ParticleTypes.g_2268_R, this.O_3598_v() + d2, (double)(f2 + 1.0f), this.l_2647_k() + d3, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y);
            ++j;
        }
    }

    protected K_4074_S g_4106_L() {
        return this.O_508_d.getBlockState(this.RealmsWorldOptions());
    }

    public boolean s_956_w() {
        return this.o_2341_D() && !this.RowButton() && !this.d_2461_k() && !this.Z_875_P() && !this.W_3464_O() && this.RealmsLongRunningMcoTaskScreen();
    }

    protected void RealmsClientOutdatedScreen() {
        int k;
        int j;
        int i = u_530_F.R_4764_Y(this.O_3598_v());
        c_1514_x blockpos = new c_1514_x(i, j = u_530_F.R_4764_Y(this.X_2960_b() - (double)0.2f), k = u_530_F.R_4764_Y(this.l_2647_k()));
        K_4074_S blockstate = this.O_508_d.getBlockState(blockpos);
        if (blockstate.w_1484_f() != O_2369_F.n_1700_B) {
            e_2866_D vector3d = this.I_4348_c();
            this.O_508_d.n_1700_B(new X_426_i(ParticleTypes.G_564_y, blockstate), this.O_3598_v() + (this.RealmsWorldOptions.nextDouble() - 0.5) * (double)this.H_2857_Y.n_1700_B, this.X_2960_b() + 0.1, this.l_2647_k() + (this.RealmsWorldOptions.nextDouble() - 0.5) * (double)this.H_2857_Y.n_1700_B, vector3d.J_1907_R * -4.0, 1.5, vector3d.G_564_y * -4.0);
        }
    }

    public boolean n_1700_B(r_109_r<Fluid> tagIn) {
        return this.ValueObject == tagIn;
    }

    public boolean W_3464_O() {
        return !this.S_4022_R && this.H_1083_k.getDouble(FluidTags.R_4764_Y) > 0.0;
    }

    public void n_1700_B(float p_213309_1_, e_2866_D relative) {
        if (this instanceof Z_875_P) {
            e_2866_D vector3d = N_4263_v.n_1700_B(relative, p_213309_1_, this.p_178_J);
            this.v_4262_N(this.I_4348_c().P_1922_E(vector3d));
            return;
        }
        e_2866_D vector3d = N_4263_v.n_1700_B(relative, p_213309_1_, this.p_178_J);
        this.v_4262_N(this.I_4348_c().P_1922_E(vector3d));
    }

    public static e_2866_D n_1700_B(e_2866_D relative, float p_213299_1_, float facing) {
        double d0 = relative.v_4262_N();
        if (d0 < 1.0E-7) {
            return e_2866_D.n_1700_B;
        }
        e_2866_D vector3d = (d0 > 1.0 ? relative.G_564_y() : relative).n_1700_B((double)p_213299_1_);
        float f = u_530_F.n_1700_B(facing * ((float)Math.PI / 180));
        float f1 = u_530_F.J_1907_R(facing * ((float)Math.PI / 180));
        return new e_2866_D(vector3d.J_1907_R * (double)f1 - vector3d.G_564_y * (double)f, vector3d.R_4764_Y, vector3d.G_564_y * (double)f1 + vector3d.J_1907_R * (double)f);
    }

    public float RealmsConfirmScreen() {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(this.O_3598_v(), 0.0, this.l_2647_k());
        if (this.O_508_d.M_588_G(blockpos$mutable)) {
            blockpos$mutable.setY(u_530_F.R_4764_Y(this.X_2048_Y()));
            return this.O_508_d.w_1484_f(blockpos$mutable);
        }
        return 0.0f;
    }

    public void n_1700_B(b_4507_u worldIn) {
        this.O_508_d = worldIn;
    }

    public void n_1700_B(double x, double y, double z, float yaw, float pitch) {
        this.G_564_y(x, y, z);
        this.p_178_J = yaw % 360.0f;
        this.f_4016_n = u_530_F.n_1700_B(pitch, -90.0f, 90.0f) % 360.0f;
        this.j_276_v = this.p_178_J;
        this.UploadStatus = this.f_4016_n;
    }

    public void G_564_y(double p_242281_1_, double p_242281_3_, double p_242281_5_) {
        double d0 = u_530_F.n_1700_B(p_242281_1_, -3.0E7, 3.0E7);
        double d1 = u_530_F.n_1700_B(p_242281_5_, -3.0E7, 3.0E7);
        this.r_715_M = d0;
        this.A_1038_p = p_242281_3_;
        this.i_1637_u = d1;
        this.J_1907_R(d0, p_242281_3_, d1);
    }

    public void P_1922_E(e_2866_D vec) {
        this.P_1922_E(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y);
    }

    public void P_1922_E(double x, double y, double z) {
        this.J_1907_R(x, y, z, this.p_178_J, this.f_4016_n);
    }

    public void n_1700_B(c_1514_x pos, float rotationYawIn, float rotationPitchIn) {
        this.J_1907_R((double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, rotationYawIn, rotationPitchIn);
    }

    public void J_1907_R(double x, double y, double z, float yaw, float pitch) {
        this.u_1723_Y(x, y, z);
        this.p_178_J = yaw;
        this.f_4016_n = pitch;
        this.t_4219_U();
    }

    public void u_1723_Y(double x, double y, double z) {
        this.Q_4569_t(x, y, z);
        this.r_715_M = x;
        this.A_1038_p = y;
        this.i_1637_u = z;
        this.q_1982_R = x;
        this.dtoRealmsServerAddress = y;
        this.w_612_n = z;
    }

    public float R_4764_Y(N_4263_v entityIn) {
        float f = (float)(this.O_3598_v() - entityIn.O_3598_v());
        float f1 = (float)(this.X_2960_b() - entityIn.X_2960_b());
        float f2 = (float)(this.l_2647_k() - entityIn.l_2647_k());
        return u_530_F.R_4764_Y(f * f + f1 * f1 + f2 * f2);
    }

    public double v_4262_N(double x, double y, double z) {
        double d0 = this.O_3598_v() - x;
        double d1 = this.X_2960_b() - y;
        double d2 = this.l_2647_k() - z;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public double G_564_y(N_4263_v entityIn) {
        return this.u_1723_Y(entityIn.s_4990_V());
    }

    public double u_1723_Y(e_2866_D vec) {
        double d0 = this.O_3598_v() - vec.J_1907_R;
        double d1 = this.X_2960_b() - vec.R_4764_Y;
        double d2 = this.l_2647_k() - vec.G_564_y;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public void c_(a_3913_L entityIn) {
    }

    public void P_1922_E(N_4263_v entityIn) {
        double d1;
        double d0;
        double d2;
        if (!this.Y_259_p(entityIn) && !entityIn.j_1564_a && !this.j_1564_a && (d2 = u_530_F.n_1700_B(d0 = entityIn.O_3598_v() - this.O_3598_v(), d1 = entityIn.l_2647_k() - this.l_2647_k())) >= (double)0.01f) {
            d2 = u_530_F.n_1700_B(d2);
            d0 /= d2;
            d1 /= d2;
            double d3 = 1.0 / d2;
            if (d3 > 1.0) {
                d3 = 1.0;
            }
            d0 *= d3;
            d1 *= d3;
            d0 *= (double)0.05f;
            d1 *= (double)0.05f;
            d0 *= (double)(1.0f - this.M_1641_O);
            d1 *= (double)(1.0f - this.M_1641_O);
            if (!this.H_1883_T()) {
                this.w_1484_f(-d0, 0.0, -d1);
            }
            if (!entityIn.H_1883_T()) {
                entityIn.w_1484_f(d0, 0.0, d1);
            }
        }
    }

    public void w_1484_f(double x, double y, double z) {
        this.v_4262_N(this.I_4348_c().J_1907_R(x, y, z));
        this.LongRunningTask = true;
    }

    protected void RealmsCreateRealmScreen() {
        this.Ops = true;
    }

    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        this.RealmsCreateRealmScreen();
        return false;
    }

    public final e_2866_D t_148_a(float partialTicks) {
        return this.G_564_y(this.J_1907_R(partialTicks), this.R_4764_Y(partialTicks));
    }

    public float J_1907_R(float partialTicks) {
        return partialTicks == 1.0f ? this.f_4016_n : u_530_F.v_4262_N(partialTicks, this.UploadStatus, this.f_4016_n);
    }

    public float R_4764_Y(float partialTicks) {
        return partialTicks == 1.0f ? this.p_178_J : u_530_F.v_4262_N(partialTicks, this.j_276_v, this.p_178_J);
    }

    public final e_2866_D G_564_y(float pitch, float yaw) {
        float f = pitch * ((float)Math.PI / 180);
        float f1 = -yaw * ((float)Math.PI / 180);
        float f2 = u_530_F.J_1907_R(f1);
        float f3 = u_530_F.n_1700_B(f1);
        float f4 = u_530_F.J_1907_R(f);
        float f5 = u_530_F.n_1700_B(f);
        return new e_2866_D(f3 * f4, -f5, f2 * f4);
    }

    public final e_2866_D s_956_w(float partialTicks) {
        return this.P_1922_E(this.J_1907_R(partialTicks), this.R_4764_Y(partialTicks));
    }

    protected final e_2866_D P_1922_E(float pitch, float yaw) {
        return this.G_564_y(pitch - 90.0f, yaw);
    }

    public final e_2866_D u_2550_I(float partialTicks) {
        if (partialTicks == 1.0f) {
            return new e_2866_D(this.O_3598_v(), this.X_2048_Y(), this.l_2647_k());
        }
        double d0 = u_530_F.G_564_y((double)partialTicks, this.r_715_M, this.O_3598_v());
        double d1 = u_530_F.G_564_y((double)partialTicks, this.A_1038_p, this.X_2960_b()) + (double)this.X_1313_W();
        double d2 = u_530_F.G_564_y((double)partialTicks, this.i_1637_u, this.l_2647_k());
        return new e_2866_D(d0, d1, d2);
    }

    public e_2866_D M_588_G(float p_241842_1_) {
        return this.u_2550_I(p_241842_1_);
    }

    public final e_2866_D P_4830_p(float p_242282_1_) {
        double d0 = u_530_F.G_564_y((double)p_242282_1_, this.r_715_M, this.O_3598_v());
        double d1 = u_530_F.G_564_y((double)p_242282_1_, this.A_1038_p, this.X_2960_b());
        double d2 = u_530_F.G_564_y((double)p_242282_1_, this.i_1637_u, this.l_2647_k());
        return new e_2866_D(d0, d1, d2);
    }

    public HitResult n_1700_B(double rayTraceDistance, float partialTicks, boolean p_213324_4_) {
        e_2866_D vector3d = this.u_2550_I(partialTicks);
        e_2866_D vector3d1 = this.t_148_a(partialTicks);
        e_2866_D vector3d2 = vector3d.J_1907_R(vector3d1.J_1907_R * rayTraceDistance, vector3d1.R_4764_Y * rayTraceDistance, vector3d1.G_564_y * rayTraceDistance);
        return this.O_508_d.n_1700_B(new ClipContext(vector3d, vector3d2, ClipContext.n_1700_B.J_1907_R, p_213324_4_ ? ClipContext.J_1907_R.R_4764_Y : ClipContext.J_1907_R.n_1700_B, this));
    }

    public boolean C_290_v() {
        return false;
    }

    public boolean w_728_N() {
        return false;
    }

    public void n_1700_B(N_4263_v killed, int scoreValue, P_11_z damageSource) {
        if (killed instanceof B_4088_l) {
            U_3554_Q.R_4764_Y.n_1700_B((B_4088_l)killed, this, damageSource);
        }
    }

    public boolean t_148_a(double x, double y, double z) {
        double d0 = this.O_3598_v() - x;
        double d1 = this.X_2960_b() - y;
        double d2 = this.l_2647_k() - z;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
        return this.n_1700_B(d3);
    }

    public boolean n_1700_B(double distance) {
        double d0 = this.i_601_W().getAverageEdgeLength();
        if (Double.isNaN(d0)) {
            d0 = 1.0;
        }
        return distance < (d0 = d0 * 64.0 * G_564_y) * d0;
    }

    public boolean R_4764_Y(U_2912_j compound) {
        String s = this.RealmsLongConfirmationScreen();
        if (!this.t_4219_U && s != null) {
            compound.n_1700_B("id", s);
            this.P_1922_E(compound);
            return true;
        }
        return false;
    }

    public boolean G_564_y(U_2912_j compound) {
        return this.y_2772_m() ? false : this.R_4764_Y(compound);
    }

    public U_2912_j P_1922_E(U_2912_j compound) {
        try {
            if (this.w_1484_f != null) {
                compound.n_1700_B("Pos", this.n_1700_B(new double[]{this.w_1484_f.O_3598_v(), this.X_2960_b(), this.w_1484_f.l_2647_k()}));
            } else {
                compound.n_1700_B("Pos", this.n_1700_B(new double[]{this.O_3598_v(), this.X_2960_b(), this.l_2647_k()}));
            }
            e_2866_D vector3d = this.I_4348_c();
            compound.n_1700_B("Motion", this.n_1700_B(new double[]{vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y}));
            compound.n_1700_B("Rotation", this.n_1700_B(this.p_178_J, this.f_4016_n));
            compound.n_1700_B("FallDistance", this.U_1241_n);
            compound.n_1700_B("Fire", (short)this.h_1847_R);
            compound.n_1700_B("Air", (short)this.L_4248_u());
            compound.n_1700_B("OnGround", this.e_1992_r);
            compound.n_1700_B("Invulnerable", this.C_2741_M);
            compound.J_1907_R("PortalCooldown", this.Q_2552_b);
            compound.n_1700_B("UUID", this.w_2705_t());
            x_282_a itextcomponent = this.k_2302_P();
            if (itextcomponent != null) {
                compound.n_1700_B("CustomName", x_282_a.n_1700_B.n_1700_B(itextcomponent));
            }
            if (this.V_118_c()) {
                compound.n_1700_B("CustomNameVisible", this.V_118_c());
            }
            if (this.y_1700_S()) {
                compound.n_1700_B("Silent", this.y_1700_S());
            }
            if (this.u_744_e()) {
                compound.n_1700_B("NoGravity", this.u_744_e());
            }
            if (this.c_132_F) {
                compound.n_1700_B("Glowing", this.c_132_F);
            }
            if (!this.k_2293_S.isEmpty()) {
                q_2896_o listnbt = new q_2896_o();
                for (String s : this.k_2293_S) {
                    listnbt.add(StringTag.n_1700_B(s));
                }
                compound.n_1700_B("Tags", listnbt);
            }
            this.n_1700_B(compound);
            if (this.H_1883_T()) {
                q_2896_o listnbt1 = new q_2896_o();
                for (N_4263_v entity : this.o_3599_Z()) {
                    U_2912_j compoundnbt;
                    if (!entity.R_4764_Y(compoundnbt = new U_2912_j())) continue;
                    listnbt1.add(compoundnbt);
                }
                if (!listnbt1.isEmpty()) {
                    compound.n_1700_B("Passengers", listnbt1);
                }
            }
            return compound;
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Saving entity NBT");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Entity being saved");
            this.n_1700_B(crashreportcategory);
            throw new ReportedException(crashreport);
        }
    }

    public void u_1723_Y(U_2912_j compound) {
        block11: {
            try {
                q_2896_o listnbt = compound.G_564_y("Pos", 6);
                q_2896_o listnbt1 = compound.G_564_y("Motion", 6);
                q_2896_o listnbt2 = compound.G_564_y("Rotation", 5);
                double d0 = listnbt1.v_4262_N(0);
                double d1 = listnbt1.v_4262_N(1);
                double d2 = listnbt1.v_4262_N(2);
                this.h_1847_R(Math.abs(d0) > 10.0 ? 0.0 : d0, Math.abs(d1) > 10.0 ? 0.0 : d1, Math.abs(d2) > 10.0 ? 0.0 : d2);
                this.u_1723_Y(listnbt.v_4262_N(0), listnbt.v_4262_N(1), listnbt.v_4262_N(2));
                this.p_178_J = listnbt2.w_1484_f(0);
                this.f_4016_n = listnbt2.w_1484_f(1);
                this.j_276_v = this.p_178_J;
                this.UploadStatus = this.f_4016_n;
                this.h_1847_R(this.p_178_J);
                this.Q_4569_t(this.p_178_J);
                this.U_1241_n = compound.s_956_w("FallDistance");
                this.h_1847_R = compound.v_4262_N("Fire");
                this.w_1484_f(compound.v_4262_N("Air"));
                this.e_1992_r = compound.t_1786_h("OnGround");
                this.C_2741_M = compound.t_1786_h("Invulnerable");
                this.Q_2552_b = compound.w_1484_f("PortalCooldown");
                if (compound.J_1907_R("UUID")) {
                    this.RealmsScreenWithCallback = compound.n_1700_B("UUID");
                    this.M_2677_i = this.RealmsScreenWithCallback.toString();
                }
                if (Double.isFinite(this.O_3598_v()) && Double.isFinite(this.X_2960_b()) && Double.isFinite(this.l_2647_k())) {
                    if (Double.isFinite(this.p_178_J) && Double.isFinite(this.f_4016_n)) {
                        this.t_4219_U();
                        this.J_1907_R(this.p_178_J, this.f_4016_n);
                        if (compound.R_4764_Y("CustomName", 8)) {
                            String s = compound.M_588_G("CustomName");
                            try {
                                this.n_1700_B(x_282_a.n_1700_B.n_1700_B(s));
                            }
                            catch (Exception exception) {
                                D_4792_h.warn("Failed to parse entity custom name {}", (Object)s, (Object)exception);
                            }
                        }
                        this.M_182_A(compound.t_1786_h("CustomNameVisible"));
                        this.v_4262_N(compound.t_1786_h("Silent"));
                        this.w_1484_f(compound.t_1786_h("NoGravity"));
                        this.u_2550_I(compound.t_1786_h("Glowing"));
                        if (compound.R_4764_Y("Tags", 9)) {
                            this.k_2293_S.clear();
                            q_2896_o listnbt3 = compound.G_564_y("Tags", 8);
                            int i = Math.min(listnbt3.size(), 1024);
                            for (int j = 0; j < i; ++j) {
                                this.k_2293_S.add(listnbt3.t_148_a(j));
                            }
                        }
                        this.J_1907_R(compound);
                        if (this.J_4256_G()) {
                            this.t_4219_U();
                        }
                        break block11;
                    }
                    throw new IllegalStateException("Entity has invalid rotation");
                }
                throw new IllegalStateException("Entity has invalid position");
            }
            catch (Throwable throwable) {
                n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Loading entity NBT");
                CrashReportCategory crashreportcategory = crashreport.n_1700_B("Entity being loaded");
                this.n_1700_B(crashreportcategory);
                System.out.println("Skipping entity with broken NBT (" + String.valueOf(this.f_4016_n()) + "): " + String.valueOf(throwable));
                this.t_4219_U = true;
            }
        }
    }

    protected boolean J_4256_G() {
        return true;
    }

    @Nullable
    protected final String RealmsLongConfirmationScreen() {
        t_5_h<?> entitytype = this.f_4016_n();
        g_2336_b resourcelocation = t_5_h.n_1700_B(entitytype);
        return entitytype.n_1700_B() && resourcelocation != null ? resourcelocation.toString() : null;
    }

    protected abstract void J_1907_R(U_2912_j var1);

    protected abstract void n_1700_B(U_2912_j var1);

    protected q_2896_o n_1700_B(double ... numbers) {
        q_2896_o listnbt = new q_2896_o();
        for (double d0 : numbers) {
            listnbt.add(D_908_R.n_1700_B(d0));
        }
        return listnbt;
    }

    protected q_2896_o n_1700_B(float ... numbers) {
        q_2896_o listnbt = new q_2896_o();
        for (float f : numbers) {
            listnbt.add(T_2717_K.n_1700_B(f));
        }
        return listnbt;
    }

    @Nullable
    public n_1494_c n_1700_B(q_1803_e itemIn) {
        return this.n_1700_B(itemIn, 0);
    }

    @Nullable
    public n_1494_c n_1700_B(q_1803_e itemIn, int offset) {
        return this.n_1700_B(new Z_1993_T(itemIn), (float)offset);
    }

    @Nullable
    public n_1494_c a_(Z_1993_T stack) {
        return this.n_1700_B(stack, 0.0f);
    }

    @Nullable
    public n_1494_c n_1700_B(Z_1993_T stack, float offsetY) {
        if (stack.n_1700_B()) {
            return null;
        }
        if (this.O_508_d.Y_259_p) {
            return null;
        }
        n_1494_c itementity = new n_1494_c(this.O_508_d, this.O_3598_v(), this.X_2960_b() + (double)offsetY, this.l_2647_k(), stack);
        itementity.t_148_a();
        this.O_508_d.a_(itementity);
        return itementity;
    }

    public boolean RealmsLongRunningMcoTaskScreen() {
        return !this.t_4219_U;
    }

    public boolean i_2993_w() {
        if (this.j_1564_a) {
            return false;
        }
        float f = 0.1f;
        float f1 = this.H_2857_Y.n_1700_B * 0.8f;
        I_4817_s axisalignedbb = I_4817_s.withSizeAtOrigin(f1, 0.1f, f1).offset(this.O_3598_v(), this.X_2048_Y(), this.l_2647_k());
        return this.O_508_d.J_1907_R(this, axisalignedbb, (p_241338_1_, p_241338_2_) -> p_241338_1_.Q_4569_t(this.O_508_d, (c_1514_x)p_241338_2_)).findAny().isPresent();
    }

    public m_3054_I n_1700_B(a_3913_L player, x_1688_C hand) {
        return m_3054_I.R_4764_Y;
    }

    public boolean u_1723_Y(N_4263_v entity) {
        return entity.RealmsParentalConsentScreen() && !this.Y_259_p(entity);
    }

    public boolean RealmsParentalConsentScreen() {
        return false;
    }

    public void x_607_J() {
        this.v_4262_N(e_2866_D.n_1700_B);
        this.v_();
        if (this.y_2772_m()) {
            this.l_3609_d().v_4262_N(this);
        }
    }

    public void v_4262_N(N_4263_v passenger) {
        this.n_1700_B(passenger, N_4263_v::J_1907_R);
    }

    private void n_1700_B(N_4263_v entity, n_1700_B callback) {
        if (this.Y_601_j(entity)) {
            double d0 = this.X_2960_b() + this.s_1671_u() + entity.O_2151_c();
            callback.accept(entity, this.O_3598_v(), d0, this.l_2647_k());
        }
    }

    public void w_1484_f(N_4263_v entityToUpdate) {
    }

    public double a_(r_4811_B target) {
        double closestY;
        double closestX;
        I_4817_s box = target.i_601_W();
        double eyeX = this.O_3598_v();
        double eyeY = this.X_2960_b() + (double)this.X_1313_W();
        double eyeZ = this.l_2647_k();
        double d = eyeX < box.minX ? box.minX : (closestX = eyeX > box.maxX ? box.maxX : eyeX);
        double d2 = eyeY < box.minY ? box.minY : (closestY = eyeY > box.maxY ? box.maxY : eyeY);
        double closestZ = eyeZ < box.minZ ? box.minZ : (eyeZ > box.maxZ ? box.maxZ : eyeZ);
        double dx = eyeX - closestX;
        double dy = eyeY - closestY;
        double dz = eyeZ - closestZ;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double t_148_a(N_4263_v target) {
        double closestY;
        double closestX;
        I_4817_s box = target.i_601_W();
        double eyeX = this.O_3598_v();
        double eyeY = this.X_2960_b() + (double)this.X_1313_W();
        double eyeZ = this.l_2647_k();
        double d = eyeX < box.minX ? box.minX : (closestX = eyeX > box.maxX ? box.maxX : eyeX);
        double d2 = eyeY < box.minY ? box.minY : (closestY = eyeY > box.maxY ? box.maxY : eyeY);
        double closestZ = eyeZ < box.minZ ? box.minZ : (eyeZ > box.maxZ ? box.maxZ : eyeZ);
        double dx = eyeX - closestX;
        double dy = eyeY - closestY;
        double dz = eyeZ - closestZ;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double O_2151_c() {
        return 0.0;
    }

    public double s_1671_u() {
        return (double)this.H_2857_Y.J_1907_R * 0.75;
    }

    public boolean s_956_w(N_4263_v entityIn) {
        return this.n_1700_B(entityIn, false);
    }

    public boolean RealmsResetNormalWorldScreen() {
        return this instanceof r_4811_B;
    }

    public boolean n_1700_B(N_4263_v entityIn, boolean force) {
        N_4263_v entity = entityIn;
        while (entity.w_1484_f != null) {
            if (entity.w_1484_f == this) {
                return false;
            }
            entity = entity.w_1484_f;
        }
        if (force || this.u_2550_I(entityIn) && entityIn.h_1847_R(this)) {
            if (this.y_2772_m()) {
                this.A_3959_N();
            }
            this.J_1907_R(I_1170_F.n_1700_B);
            this.w_1484_f = entityIn;
            this.w_1484_f.M_588_G(this);
            return true;
        }
        return false;
    }

    protected boolean u_2550_I(N_4263_v entityIn) {
        return !this.q_2307_F() && this.l_1233_K <= 0;
    }

    protected boolean R_4764_Y(I_1170_F pose) {
        return this.O_508_d.a_(this, this.G_564_y(pose).shrink(1.0E-7));
    }

    public void C_3538_G() {
        for (int i = this.v_4262_N.size() - 1; i >= 0; --i) {
            this.v_4262_N.get(i).A_3959_N();
        }
    }

    public void t_() {
        if (this.w_1484_f != null) {
            N_4263_v entity = this.w_1484_f;
            this.w_1484_f = null;
            entity.P_4830_p(this);
        }
    }

    public void A_3959_N() {
        this.t_();
    }

    protected void M_588_G(N_4263_v passenger) {
        if (passenger.l_3609_d() != this) {
            throw new IllegalStateException("Use x.startRiding(y), not y.addPassenger(x)");
        }
        if (!this.O_508_d.Y_259_p && passenger instanceof a_3913_L && !(this.n_3864_h() instanceof a_3913_L)) {
            this.v_4262_N.add(0, passenger);
        } else {
            this.v_4262_N.add(passenger);
        }
    }

    protected void P_4830_p(N_4263_v passenger) {
        if (passenger.l_3609_d() == this) {
            throw new IllegalStateException("Use x.stopRiding(y), not y.removePassenger(x)");
        }
        this.v_4262_N.remove(passenger);
        passenger.l_1233_K = 60;
    }

    protected boolean h_1847_R(N_4263_v passenger) {
        return this.o_3599_Z().size() < 1;
    }

    public void n_1700_B(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.J_1907_R(x, y, z);
        this.J_1907_R(yaw, pitch);
    }

    public void n_1700_B(float yaw, int pitch) {
        this.h_1847_R(yaw);
    }

    public float G_424_k() {
        C_415_h event = new C_415_h(this, 0.0f);
        lightning.product.A_4115_X.n_1700_B(event);
        return event.R_4764_Y();
    }

    public e_2866_D RealmsSettingsScreen() {
        return this.G_564_y(this.f_4016_n, this.p_178_J);
    }

    public P_3504_Q f_1043_S() {
        return new P_3504_Q(this.f_4016_n, this.p_178_J);
    }

    public e_2866_D F_4247_a() {
        return e_2866_D.n_1700_B(this.f_1043_S());
    }

    public void J_1907_R(c_1514_x pos) {
        if (this.V_1225_t()) {
            this.PlayerInfo();
        } else {
            if (!this.O_508_d.Y_259_p && !pos.equals(this.R_3077_Z)) {
                this.R_3077_Z = pos.toImmutable();
            }
            this.j_2266_I = true;
        }
    }

    protected void J_739_q() {
        if (this.O_508_d instanceof e_3591_l) {
            int i = this.q_1982_R();
            e_3591_l serverworld = (e_3591_l)this.O_508_d;
            if (this.j_2266_I) {
                f_2392_k<b_4507_u> registrykey;
                G_564_y minecraftserver = serverworld.T_2506_i();
                e_3591_l serverworld1 = minecraftserver.n_1700_B(registrykey = this.O_508_d.g_2268_R() == b_4507_u.v_4262_N ? b_4507_u.u_1723_Y : b_4507_u.v_4262_N);
                if (serverworld1 != null && minecraftserver.Y_1740_V() && !this.y_2772_m() && this.S_980_j++ >= i) {
                    this.S_980_j = i;
                    this.PlayerInfo();
                    this.n_1700_B(serverworld1);
                }
                this.j_2266_I = false;
            } else {
                if (this.S_980_j > 0) {
                    this.S_980_j -= 4;
                }
                if (this.S_980_j < 0) {
                    this.S_980_j = 0;
                }
            }
            this.U_1241_n();
        }
    }

    public int C_1162_e() {
        return 300;
    }

    public void s_956_w(double x, double y, double z) {
        this.h_1847_R(x, y, z);
    }

    public void n_1700_B(byte id) {
        switch (id) {
            case 53: {
                HoneyBlock.n_1700_B(this);
            }
        }
    }

    public void D_4361_a() {
    }

    public Iterable<Z_1993_T> f_3449_S() {
        return J_1907_R;
    }

    public Iterable<Z_1993_T> u_55_V() {
        return J_1907_R;
    }

    public Iterable<Z_1993_T> JsonUtils() {
        return Iterables.concat(this.f_3449_S(), this.u_55_V());
    }

    public void n_1700_B(e_1174_E slotIn, Z_1993_T stack) {
    }

    public boolean RealmsPersistence() {
        boolean flag = this.O_508_d != null && this.O_508_d.Y_259_p;
        return !this.r_3651_U() && (this.h_1847_R > 0 || flag && this.v_4262_N(0));
    }

    public boolean y_2772_m() {
        return this.l_3609_d() != null;
    }

    public boolean H_1883_T() {
        return !this.o_3599_Z().isEmpty();
    }

    public boolean d_4007_L() {
        return true;
    }

    public void t_148_a(boolean keyDownIn) {
        this.J_1907_R(1, keyDownIn);
    }

    public boolean q_2307_F() {
        return this.v_4262_N(1);
    }

    public boolean TextRenderingUtils() {
        return this.q_2307_F();
    }

    public boolean UploadTokenCache() {
        return this.q_2307_F();
    }

    public boolean U_1341_G() {
        return this.q_2307_F();
    }

    public boolean ClientBootstrap() {
        return this.q_2307_F();
    }

    public boolean Z_875_P() {
        return this.h_4320_q() == I_1170_F.u_1723_Y;
    }

    public boolean o_2341_D() {
        return this.v_4262_N(3);
    }

    public void b_(boolean sprinting) {
        this.J_1907_R(3, sprinting);
    }

    public boolean C_1269_X() {
        return this.v_4262_N(4);
    }

    public boolean x_612_B() {
        return this.h_4320_q() == I_1170_F.G_564_y;
    }

    public boolean t_1446_I() {
        return this.x_612_B() && !this.RowButton();
    }

    public void s_956_w(boolean swimming) {
        this.J_1907_R(4, swimming);
    }

    public boolean j_306_t() {
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.k_2293_S);
        lightning.product.A_4115_X.n_1700_B(event);
        return !event.n_1700_B() ? this.c_132_F || this.O_508_d.Y_259_p && this.v_4262_N(6) : false;
    }

    public void u_2550_I(boolean glowingIn) {
        this.c_132_F = glowingIn;
        if (!this.O_508_d.Y_259_p) {
            this.J_1907_R(6, this.c_132_F);
        }
    }

    public boolean F_3572_x() {
        return this.v_4262_N(5);
    }

    public boolean a_(a_3913_L player) {
        SeeInvisibles module = (SeeInvisibles)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(SeeInvisibles.class);
        if (module.w_1484_f()) {
            if (this instanceof D_686_b) {
                if (module.Q_4569_t().t_148_a().booleanValue()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        if (player.d_2461_k()) {
            return false;
        }
        o_3050_h team = this.L_1362_X();
        return team != null && player != null && player.L_1362_X() == team && team.w_1484_f() ? false : this.F_3572_x();
    }

    @Nullable
    public o_3050_h L_1362_X() {
        return this.O_508_d.Q_4569_t().w_1484_f(this.L_3570_A());
    }

    public boolean Q_4569_t(N_4263_v entityIn) {
        return this.n_1700_B(entityIn.L_1362_X());
    }

    public boolean n_1700_B(o_3050_h teamIn) {
        return this.L_1362_X() != null ? this.L_1362_X().n_1700_B(teamIn) : false;
    }

    public void M_588_G(boolean invisible) {
        this.J_1907_R(5, invisible);
    }

    protected boolean v_4262_N(int flag) {
        return (this.l_4537_E.n_1700_B(F_2624_D) & 1 << flag) != 0;
    }

    protected void J_1907_R(int flag, boolean set) {
        byte b0 = this.l_4537_E.n_1700_B(F_2624_D);
        if (set) {
            this.l_4537_E.J_1907_R(F_2624_D, (byte)(b0 | 1 << flag));
        } else {
            this.l_4537_E.J_1907_R(F_2624_D, (byte)(b0 & ~(1 << flag)));
        }
    }

    public int P_5000_x() {
        return 300;
    }

    public int L_4248_u() {
        return this.l_4537_E.n_1700_B(Q_4569_t);
    }

    public void w_1484_f(int air) {
        this.l_4537_E.J_1907_R(Q_4569_t, air);
    }

    public void n_1700_B(e_3591_l p_241841_1_, LightningBolt p_241841_2_) {
        this.u_1723_Y(this.h_1847_R + 1);
        if (this.h_1847_R == 0) {
            this.P_1922_E(8);
        }
        this.n_1700_B(P_11_z.J_1907_R, 5.0f);
    }

    public void P_4830_p(boolean downwards) {
        e_2866_D vector3d = this.I_4348_c();
        double d0 = downwards ? Math.max(-0.9, vector3d.R_4764_Y - 0.03) : Math.min(1.8, vector3d.R_4764_Y + 0.1);
        this.h_1847_R(vector3d.J_1907_R, d0, vector3d.G_564_y);
    }

    public void h_1847_R(boolean downwards) {
        e_2866_D vector3d = this.I_4348_c();
        double d0 = downwards ? Math.max(-0.3, vector3d.R_4764_Y - 0.03) : Math.min(0.7, vector3d.R_4764_Y + 0.06);
        this.h_1847_R(vector3d.J_1907_R, d0, vector3d.G_564_y);
        this.U_1241_n = 0.0f;
    }

    public void n_1700_B(e_3591_l p_241847_1_, r_4811_B p_241847_2_) {
    }

    protected void u_2550_I(double x, double y, double z) {
        c_1514_x blockpos = new c_1514_x(x, y, z);
        e_2866_D vector3d = new e_2866_D(x - (double)blockpos.getX(), y - (double)blockpos.getY(), z - (double)blockpos.getZ());
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        b_257_Y direction = b_257_Y.J_1907_R;
        double d0 = Double.MAX_VALUE;
        for (b_257_Y direction1 : new b_257_Y[]{b_257_Y.R_4764_Y, b_257_Y.G_564_y, b_257_Y.P_1922_E, b_257_Y.u_1723_Y, b_257_Y.J_1907_R}) {
            double d2;
            blockpos$mutable.n_1700_B(blockpos, direction1);
            if (this.O_508_d.getBlockState(blockpos$mutable).multiplayerClientSuggestionProvider(this.O_508_d, blockpos$mutable)) continue;
            double d1 = vector3d.n_1700_B(direction1.h_1847_R());
            double d = d2 = direction1.P_1922_E() == b_257_Y.J_1907_R.n_1700_B ? 1.0 - d1 : d1;
            if (!(d2 < d0)) continue;
            d0 = d2;
            direction = direction1;
        }
        float f = this.RealmsWorldOptions.nextFloat() * 0.2f + 0.1f;
        float f1 = direction.P_1922_E().n_1700_B();
        e_2866_D vector3d1 = this.I_4348_c().n_1700_B(0.75);
        if (direction.h_1847_R() == b_257_Y.n_1700_B.n_1700_B) {
            this.h_1847_R(f1 * f, vector3d1.R_4764_Y, vector3d1.G_564_y);
        } else if (direction.h_1847_R() == b_257_Y.n_1700_B.J_1907_R) {
            this.h_1847_R(vector3d1.J_1907_R, f1 * f, vector3d1.G_564_y);
        } else if (direction.h_1847_R() == b_257_Y.n_1700_B.R_4764_Y) {
            this.h_1847_R(vector3d1.J_1907_R, vector3d1.R_4764_Y, f1 * f);
        }
    }

    public void n_1700_B(K_4074_S state, e_2866_D motionMultiplierIn) {
        this.U_1241_n = 0.0f;
        this.h_4320_q = motionMultiplierIn;
    }

    private static x_282_a J_1907_R(x_282_a p_233573_0_) {
        MutableComponent iformattabletextcomponent = p_233573_0_.G_564_y().n_1700_B(p_233573_0_.n_1700_B().n_1700_B((i_2909_p)null));
        for (x_282_a itextcomponent : p_233573_0_.R_4764_Y()) {
            iformattabletextcomponent.n_1700_B(N_4263_v.J_1907_R(itextcomponent));
        }
        return iformattabletextcomponent;
    }

    @Override
    public x_282_a O_1309_Q() {
        x_282_a itextcomponent = this.k_2302_P();
        return itextcomponent != null ? N_4263_v.J_1907_R(itextcomponent) : this.O_2934_T();
    }

    protected x_282_a O_2934_T() {
        return this.P_1922_E.v_4262_N();
    }

    public boolean M_182_A(N_4263_v entityIn) {
        return this == entityIn;
    }

    public float l_4088_R() {
        return 0.0f;
    }

    public void h_1847_R(float rotation) {
    }

    public void Q_4569_t(float offset) {
    }

    public boolean Z_735_d() {
        return true;
    }

    public boolean t_1786_h(N_4263_v entityIn) {
        return false;
    }

    public String toString() {
        return String.format(Locale.ROOT, "%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]", this.getClass().getSimpleName(), this.O_1309_Q().getString(), this.u_1723_Y, this.O_508_d == null ? "~NULL~" : this.O_508_d.toString(), this.O_3598_v(), this.X_2960_b(), this.l_2647_k());
    }

    public boolean n_1700_B(P_11_z source) {
        return this.C_2741_M && source != P_11_z.P_4830_p && !source.Q_2552_b();
    }

    public boolean P_925_e() {
        return this.C_2741_M;
    }

    public void Q_4569_t(boolean isInvulnerable) {
        this.C_2741_M = isInvulnerable;
    }

    public void multiplayerClientSuggestionProvider(N_4263_v entityIn) {
        this.J_1907_R(entityIn.O_3598_v(), entityIn.X_2960_b(), entityIn.l_2647_k(), entityIn.p_178_J, entityIn.f_4016_n);
    }

    public void w_1457_N(N_4263_v entityIn) {
        U_2912_j compoundnbt = entityIn.P_1922_E(new U_2912_j());
        compoundnbt.multiplayerClientSuggestionProvider("Dimension");
        this.u_1723_Y(compoundnbt);
        this.Q_2552_b = entityIn.Q_2552_b;
        this.R_3077_Z = entityIn.R_3077_Z;
    }

    @Nullable
    public N_4263_v n_1700_B(e_3591_l server) {
        if (this.O_508_d instanceof e_3591_l && !this.t_4219_U) {
            this.Ping();
            f_1186_l portalinfo = this.J_1907_R(server);
            if (portalinfo == null) {
                return null;
            }
            Object entity = this.f_4016_n().n_1700_B(server);
            if (entity != null) {
                ((N_4263_v)entity).w_1457_N(this);
                ((N_4263_v)entity).J_1907_R(portalinfo.n_1700_B.J_1907_R, portalinfo.n_1700_B.R_4764_Y, portalinfo.n_1700_B.G_564_y, portalinfo.R_4764_Y, ((N_4263_v)entity).f_4016_n);
                ((N_4263_v)entity).v_4262_N(portalinfo.J_1907_R);
                server.v_4262_N((N_4263_v)entity);
                if (server.g_2268_R() == b_4507_u.w_1484_f) {
                    e_3591_l.n_1700_B(server);
                }
            }
            this.X_4895_T();
            ((e_3591_l)this.O_508_d).t_148_a();
            server.t_148_a();
            return entity;
        }
        return null;
    }

    protected void X_4895_T() {
        this.t_4219_U = true;
    }

    @Nullable
    protected f_1186_l J_1907_R(e_3591_l p_241829_1_) {
        boolean flag1;
        boolean flag = this.O_508_d.g_2268_R() == b_4507_u.w_1484_f && p_241829_1_.g_2268_R() == b_4507_u.u_1723_Y;
        boolean bl = flag1 = p_241829_1_.g_2268_R() == b_4507_u.w_1484_f;
        if (!flag && !flag1) {
            boolean flag2;
            boolean bl2 = flag2 = p_241829_1_.g_2268_R() == b_4507_u.v_4262_N;
            if (this.O_508_d.g_2268_R() != b_4507_u.v_4262_N && !flag2) {
                return null;
            }
            T_603_v worldborder = p_241829_1_.H_2857_Y();
            double d0 = Math.max(-2.9999872E7, worldborder.P_1922_E() + 16.0);
            double d1 = Math.max(-2.9999872E7, worldborder.u_1723_Y() + 16.0);
            double d2 = Math.min(2.9999872E7, worldborder.v_4262_N() - 16.0);
            double d3 = Math.min(2.9999872E7, worldborder.w_1484_f() - 16.0);
            double d4 = Z_3903_F.n_1700_B(this.O_508_d.G_624_v(), p_241829_1_.G_624_v());
            c_1514_x blockpos1 = new c_1514_x(u_530_F.n_1700_B(this.O_3598_v() * d4, d0, d2), this.X_2960_b(), u_530_F.n_1700_B(this.l_2647_k() * d4, d1, d3));
            return this.n_1700_B(p_241829_1_, blockpos1, flag2).map(p_242275_2_ -> {
                e_2866_D vector3d;
                b_257_Y.n_1700_B direction$axis;
                K_4074_S blockstate = this.O_508_d.getBlockState(this.R_3077_Z);
                if (blockstate.J_1907_R(BlockStateProperties.t_4043_B)) {
                    direction$axis = blockstate.R_4764_Y(BlockStateProperties.t_4043_B);
                    BlockUtil.J_1907_R teleportationrepositioner$result = BlockUtil.n_1700_B(this.R_3077_Z, direction$axis, 21, b_257_Y.n_1700_B.J_1907_R, 21, (c_1514_x p_242276_2_) -> this.O_508_d.getBlockState((c_1514_x)p_242276_2_) == blockstate);
                    vector3d = this.n_1700_B(direction$axis, teleportationrepositioner$result);
                } else {
                    direction$axis = b_257_Y.n_1700_B.n_1700_B;
                    vector3d = new e_2866_D(0.5, 0.0, 0.0);
                }
                return Z_4149_q.n_1700_B(p_241829_1_, p_242275_2_, direction$axis, vector3d, this.n_1700_B(this.h_4320_q()), this.I_4348_c(), this.p_178_J, this.f_4016_n);
            }).orElse(null);
        }
        c_1514_x blockpos = flag1 ? e_3591_l.n_1700_B : p_241829_1_.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, p_241829_1_.A_1038_p());
        return new f_1186_l(new e_2866_D((double)blockpos.getX() + 0.5, blockpos.getY(), (double)blockpos.getZ() + 0.5), this.I_4348_c(), this.p_178_J, this.f_4016_n);
    }

    protected e_2866_D n_1700_B(b_257_Y.n_1700_B axis, BlockUtil.J_1907_R result) {
        return Z_4149_q.n_1700_B(result, axis, this.s_4990_V(), this.n_1700_B(this.h_4320_q()));
    }

    protected Optional<BlockUtil.J_1907_R> n_1700_B(e_3591_l p_241830_1_, c_1514_x p_241830_2_, boolean p_241830_3_) {
        return p_241830_1_.z_1333_t().n_1700_B(p_241830_2_, p_241830_3_);
    }

    public boolean L_103_L() {
        return true;
    }

    public float n_1700_B(F_1241_B explosionIn, BlockGetter worldIn, c_1514_x pos, K_4074_S blockStateIn, FluidState fluidState, float explosionPower) {
        return explosionPower;
    }

    public boolean n_1700_B(F_1241_B explosionIn, BlockGetter worldIn, c_1514_x pos, K_4074_S blockStateIn, float explosionPower) {
        return true;
    }

    public int n_3197_X() {
        return 3;
    }

    public boolean P_2947_S() {
        return false;
    }

    public void n_1700_B(CrashReportCategory category) {
        category.n_1700_B("Entity Type", () -> String.valueOf(t_5_h.n_1700_B(this.f_4016_n())) + " (" + this.getClass().getCanonicalName() + ")");
        category.n_1700_B("Entity ID", this.u_1723_Y);
        category.n_1700_B("Entity Name", () -> this.O_1309_Q().getString());
        category.n_1700_B("Entity's Exact location", String.format(Locale.ROOT, "%.2f, %.2f, %.2f", this.O_3598_v(), this.X_2960_b(), this.l_2647_k()));
        category.n_1700_B("Entity's Block location", CrashReportCategory.n_1700_B(u_530_F.R_4764_Y(this.O_3598_v()), u_530_F.R_4764_Y(this.X_2960_b()), u_530_F.R_4764_Y(this.l_2647_k())));
        e_2866_D vector3d = this.I_4348_c();
        category.n_1700_B("Entity's Momentum", String.format(Locale.ROOT, "%.2f, %.2f, %.2f", vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y));
        category.n_1700_B("Entity's Passengers", () -> this.o_3599_Z().toString());
        category.n_1700_B("Entity's Vehicle", () -> this.l_3609_d().toString());
    }

    public boolean O_4761_U() {
        return this.RealmsPersistence() && !this.d_2461_k();
    }

    public void a_(UUID uniqueIdIn) {
        this.RealmsScreenWithCallback = uniqueIdIn;
        this.M_2677_i = this.RealmsScreenWithCallback.toString();
    }

    public UUID w_2705_t() {
        return this.RealmsScreenWithCallback;
    }

    public String F_518_D() {
        return this.M_2677_i;
    }

    public String L_3570_A() {
        return this.M_2677_i;
    }

    public boolean Y_776_s() {
        return true;
    }

    public static double S_3139_t() {
        return G_564_y;
    }

    public static void J_1907_R(double renderDistWeight) {
        G_564_y = renderDistWeight;
    }

    @Override
    public x_282_a c_() {
        return PlayerTeam.n_1700_B(this.L_1362_X(), this.O_1309_Q()).n_1700_B(p_211516_1_ -> p_211516_1_.n_1700_B(this.x_92_N()).n_1700_B(this.F_518_D()));
    }

    public void n_1700_B(@Nullable x_282_a name) {
        this.l_4537_E.J_1907_R(M_182_A, Optional.ofNullable(name));
    }

    @Override
    @Nullable
    public x_282_a k_2302_P() {
        return this.l_4537_E.n_1700_B(M_182_A).orElse(null);
    }

    @Override
    public boolean t_3452_g() {
        return this.l_4537_E.n_1700_B(M_182_A).isPresent();
    }

    public void M_182_A(boolean alwaysRenderNameTag) {
        this.l_4537_E.J_1907_R(t_1786_h, alwaysRenderNameTag);
    }

    public boolean V_118_c() {
        return this.l_4537_E.n_1700_B(t_1786_h);
    }

    public final void M_588_G(double x, double y, double z) {
        if (this.O_508_d instanceof e_3591_l) {
            Y_1387_d chunkpos = new Y_1387_d(new c_1514_x(x, y, z));
            ((e_3591_l)this.O_508_d).Y_259_p().n_1700_B(TicketType.v_4262_N, chunkpos, 0, this.j_276_v());
            this.O_508_d.u_1723_Y(chunkpos.J_1907_R, chunkpos.R_4764_Y);
            this.P_4830_p(x, y, z);
        }
    }

    public void P_4830_p(double x, double y, double z) {
        if (this.O_508_d instanceof e_3591_l) {
            e_3591_l serverworld = (e_3591_l)this.O_508_d;
            this.J_1907_R(x, y, z, this.p_178_J, this.f_4016_n);
            this.O_1795_e().forEach(p_233565_1_ -> {
                serverworld.R_4764_Y((N_4263_v)p_233565_1_);
                p_233565_1_.q_2307_F = true;
                for (N_4263_v entity : p_233565_1_.v_4262_N) {
                    p_233565_1_.n_1700_B(entity, N_4263_v::P_1922_E);
                }
            });
        }
    }

    public boolean I_1407_m() {
        return this.V_118_c();
    }

    public void n_1700_B(h_256_u<?> key) {
        if (RealmsDefaultUncaughtExceptionHandler.equals(key)) {
            this.g_();
        }
    }

    public void g_() {
        R_1815_U entitysize1;
        R_1815_U entitysize = this.H_2857_Y;
        I_1170_F pose = this.h_4320_q();
        this.H_2857_Y = entitysize1 = this.n_1700_B(pose);
        this.A_4115_X = this.n_1700_B(pose, entitysize1);
        if (entitysize1.n_1700_B < entitysize.n_1700_B) {
            double d0 = (double)entitysize1.n_1700_B / 2.0;
            this.n_1700_B(new I_4817_s(this.O_3598_v() - d0, this.X_2960_b(), this.l_2647_k() - d0, this.O_3598_v() + d0, this.X_2960_b() + (double)entitysize1.J_1907_R, this.l_2647_k() + d0));
        } else {
            I_4817_s axisalignedbb = this.i_601_W();
            this.n_1700_B(new I_4817_s(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double)entitysize1.n_1700_B, axisalignedbb.minY + (double)entitysize1.J_1907_R, axisalignedbb.minZ + (double)entitysize1.n_1700_B));
            if (entitysize1.n_1700_B > entitysize.n_1700_B && !this.S_4022_R && !this.O_508_d.Y_259_p) {
                float f = entitysize.n_1700_B - entitysize1.n_1700_B;
                this.n_1700_B(L_461_d.n_1700_B, new e_2866_D(f, 0.0, f));
            }
        }
    }

    public b_257_Y o_2767_H() {
        return b_257_Y.n_1700_B(this.p_178_J);
    }

    public b_257_Y d_2545_n() {
        return this.o_2767_H();
    }

    protected c_973_a x_92_N() {
        return new c_973_a(c_973_a.n_1700_B.R_4764_Y, new c_973_a.J_1907_R(this.f_4016_n(), this.w_2705_t(), this.O_1309_Q()));
    }

    public boolean n_1700_B(B_4088_l player) {
        return true;
    }

    public I_4817_s i_601_W() {
        return this.u_2550_I;
    }

    public I_4817_s h_2739_B() {
        return this.i_601_W();
    }

    protected I_4817_s G_564_y(I_1170_F pose) {
        R_1815_U entitysize = this.n_1700_B(pose);
        float f = entitysize.n_1700_B / 2.0f;
        e_2866_D vector3d = new e_2866_D(this.O_3598_v() - (double)f, this.X_2960_b(), this.l_2647_k() - (double)f);
        e_2866_D vector3d1 = new e_2866_D(this.O_3598_v() + (double)f, this.X_2960_b() + (double)entitysize.J_1907_R, this.l_2647_k() + (double)f);
        return new I_4817_s(vector3d, vector3d1);
    }

    public void n_1700_B(I_4817_s bb) {
        this.u_2550_I = bb;
    }

    protected float n_1700_B(I_1170_F poseIn, R_1815_U sizeIn) {
        return sizeIn.J_1907_R * 0.85f;
    }

    public float P_1922_E(I_1170_F pose) {
        return this.n_1700_B(pose, this.n_1700_B(pose));
    }

    public final float X_1313_W() {
        return this.A_4115_X;
    }

    public e_2866_D x_4991_F() {
        return new e_2866_D(0.0, this.X_1313_W(), this.C_415_h() * 0.4f);
    }

    public boolean n_1700_B(int inventorySlot, Z_1993_T itemStackIn) {
        return false;
    }

    @Override
    public void n_1700_B(x_282_a component, UUID senderUUID) {
    }

    public b_4507_u Z_759_W() {
        return this.O_508_d;
    }

    @Nullable
    public G_564_y f_1574_f() {
        return this.O_508_d.T_2506_i();
    }

    public m_3054_I n_1700_B(a_3913_L player, e_2866_D vec, x_1688_C hand) {
        return m_3054_I.R_4764_Y;
    }

    public boolean l_1268_F() {
        return false;
    }

    public void n_1700_B(r_4811_B entityLivingBaseIn, N_4263_v entityIn) {
        if (entityIn instanceof r_4811_B) {
            K_4096_w.n_1700_B((r_4811_B)entityIn, (N_4263_v)entityLivingBaseIn);
        }
        K_4096_w.J_1907_R(entityLivingBaseIn, entityIn);
    }

    public void J_1907_R(B_4088_l player) {
    }

    public void R_4764_Y(B_4088_l player) {
    }

    public float n_1700_B(W_2163_m transformRotation) {
        float f = u_530_F.v_4262_N(this.p_178_J);
        switch (transformRotation) {
            case R_4764_Y: {
                return f + 180.0f;
            }
            case G_564_y: {
                return f + 270.0f;
            }
            case J_1907_R: {
                return f + 90.0f;
            }
        }
        return f;
    }

    public float n_1700_B(q_4099_E transformMirror) {
        float f = u_530_F.v_4262_N(this.p_178_J);
        switch (transformMirror) {
            case J_1907_R: {
                return -f;
            }
            case R_4764_Y: {
                return 180.0f - f;
            }
        }
        return f;
    }

    public boolean J_303_C() {
        return false;
    }

    public boolean o_1800_r() {
        boolean flag = this.q_2307_F;
        this.q_2307_F = false;
        return flag;
    }

    public boolean H_1873_g() {
        boolean flag = this.Y_601_j;
        this.Y_601_j = false;
        return flag;
    }

    @Nullable
    public N_4263_v n_3864_h() {
        return null;
    }

    public List<N_4263_v> o_3599_Z() {
        return this.v_4262_N.isEmpty() ? Collections.emptyList() : Lists.newArrayList(this.v_4262_N);
    }

    public boolean Y_601_j(N_4263_v entityIn) {
        for (N_4263_v entity : this.o_3599_Z()) {
            if (!entity.equals(entityIn)) continue;
            return true;
        }
        return false;
    }

    public boolean n_1700_B(Class<? extends N_4263_v> entityClazz) {
        for (N_4263_v entity : this.o_3599_Z()) {
            if (!entityClazz.isAssignableFrom(entity.getClass())) continue;
            return true;
        }
        return false;
    }

    public Collection<N_4263_v> X_290_I() {
        HashSet set = Sets.newHashSet();
        for (N_4263_v entity : this.o_3599_Z()) {
            set.add(entity);
            entity.n_1700_B(false, set);
        }
        return set;
    }

    public Stream<N_4263_v> O_1795_e() {
        return Stream.concat(Stream.of(this), this.v_4262_N.stream().flatMap(N_4263_v::O_1795_e));
    }

    public boolean l_697_B() {
        HashSet set = Sets.newHashSet();
        this.n_1700_B(true, set);
        return set.size() == 1;
    }

    private void n_1700_B(boolean playersOnly, Set<N_4263_v> p_200604_2_) {
        for (N_4263_v entity : this.o_3599_Z()) {
            if (!playersOnly || B_4088_l.class.isAssignableFrom(entity.getClass())) {
                p_200604_2_.add(entity);
            }
            entity.n_1700_B(playersOnly, p_200604_2_);
        }
    }

    public N_4263_v d_3244_b() {
        N_4263_v entity = this;
        while (entity.y_2772_m()) {
            entity = entity.l_3609_d();
        }
        return entity;
    }

    public boolean Y_259_p(N_4263_v entityIn) {
        return this.d_3244_b() == entityIn.d_3244_b();
    }

    public boolean Q_2552_b(N_4263_v entityIn) {
        for (N_4263_v entity : this.o_3599_Z()) {
            if (entity.equals(entityIn)) {
                return true;
            }
            if (!entity.Q_2552_b(entityIn)) continue;
            return true;
        }
        return false;
    }

    public boolean v_887_r() {
        N_4263_v entity = this.n_3864_h();
        if (entity instanceof a_3913_L) {
            return ((a_3913_L)entity).w_1484_f();
        }
        return !this.O_508_d.Y_259_p;
    }

    protected static e_2866_D n_1700_B(double p_233559_0_, double p_233559_2_, float p_233559_4_) {
        double d0 = (p_233559_0_ + p_233559_2_ + (double)1.0E-5f) / 2.0;
        float f = -u_530_F.n_1700_B(p_233559_4_ * ((float)Math.PI / 180));
        float f1 = u_530_F.J_1907_R(p_233559_4_ * ((float)Math.PI / 180));
        float f2 = Math.max(Math.abs(f), Math.abs(f1));
        return new e_2866_D((double)f * d0 / (double)f2, 0.0, (double)f1 * d0 / (double)f2);
    }

    public e_2866_D b_(r_4811_B livingEntity) {
        return new e_2866_D(this.O_3598_v(), this.i_601_W().maxY, this.l_2647_k());
    }

    @Nullable
    public N_4263_v l_3609_d() {
        return this.w_1484_f;
    }

    public w_1454_v h_() {
        return w_1454_v.n_1700_B;
    }

    public D_38_f r_2478_U() {
        return D_38_f.v_4262_N;
    }

    protected int h_2848_I() {
        return 1;
    }

    public y_2498_m A_3244_K() {
        return new y_2498_m(this, this.s_4990_V(), this.f_1043_S(), this.O_508_d instanceof e_3591_l ? (e_3591_l)this.O_508_d : null, this.t_1786_h(), this.O_1309_Q().getString(), this.c_(), this.O_508_d.T_2506_i(), this);
    }

    protected int t_1786_h() {
        return 0;
    }

    public boolean t_148_a(int level) {
        return this.t_1786_h() >= level;
    }

    @Override
    public boolean O_508_d() {
        return this.O_508_d.H_1990_U().J_1907_R(A_2352_Z.h_1847_R);
    }

    @Override
    public boolean r_715_M() {
        return true;
    }

    @Override
    public boolean A_1038_p() {
        return true;
    }

    public void n_1700_B(EntityAnchorArgument.n_1700_B anchor, e_2866_D target) {
        e_2866_D vector3d = anchor.n_1700_B(this);
        double d0 = target.J_1907_R - vector3d.J_1907_R;
        double d1 = target.R_4764_Y - vector3d.R_4764_Y;
        double d2 = target.G_564_y - vector3d.G_564_y;
        double d3 = u_530_F.n_1700_B(d0 * d0 + d2 * d2);
        this.f_4016_n = u_530_F.v_4262_N((float)(-(u_530_F.G_564_y(d1, d3) * 57.2957763671875)));
        this.p_178_J = u_530_F.v_4262_N((float)(u_530_F.G_564_y(d2, d0) * 57.2957763671875) - 90.0f);
        this.h_1847_R(this.p_178_J);
        this.UploadStatus = this.f_4016_n;
        this.j_276_v = this.p_178_J;
    }

    public boolean n_1700_B(r_109_r<Fluid> fluidTag, double p_210500_2_) {
        int j1;
        I_4817_s axisalignedbb = this.i_601_W().shrink(0.001);
        int i = u_530_F.R_4764_Y(axisalignedbb.minX);
        int j = u_530_F.P_1922_E(axisalignedbb.maxX);
        int k = u_530_F.R_4764_Y(axisalignedbb.minY);
        int l = u_530_F.P_1922_E(axisalignedbb.maxY);
        int i1 = u_530_F.R_4764_Y(axisalignedbb.minZ);
        if (!this.O_508_d.n_1700_B(i, k, i1, j, l, j1 = u_530_F.P_1922_E(axisalignedbb.maxZ))) {
            return false;
        }
        double d0 = 0.0;
        boolean flag = this.Y_776_s();
        boolean flag1 = false;
        e_2866_D vector3d = e_2866_D.n_1700_B;
        int k1 = 0;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int l1 = i; l1 < j; ++l1) {
            for (int i2 = k; i2 < l; ++i2) {
                for (int j2 = i1; j2 < j1; ++j2) {
                    double d1;
                    blockpos$mutable.n_1700_B(l1, i2, j2);
                    FluidState fluidstate = this.O_508_d.getFluidState(blockpos$mutable);
                    if (!fluidstate.n_1700_B(fluidTag) || !((d1 = (double)((float)i2 + fluidstate.n_1700_B((BlockGetter)this.O_508_d, (c_1514_x)blockpos$mutable))) >= axisalignedbb.minY)) continue;
                    flag1 = true;
                    d0 = Math.max(d1 - axisalignedbb.minY, d0);
                    if (!flag) continue;
                    e_2866_D vector3d1 = fluidstate.R_4764_Y(this.O_508_d, blockpos$mutable);
                    if (d0 < 0.4) {
                        vector3d1 = vector3d1.n_1700_B(d0);
                    }
                    vector3d = vector3d.P_1922_E(vector3d1);
                    ++k1;
                }
            }
        }
        if (vector3d.u_1723_Y() > 0.0) {
            if (k1 > 0) {
                vector3d = vector3d.n_1700_B(1.0 / (double)k1);
            }
            if (!(this instanceof a_3913_L)) {
                vector3d = vector3d.G_564_y();
            }
            e_2866_D vector3d2 = this.I_4348_c();
            vector3d = vector3d.n_1700_B(p_210500_2_ * 1.0);
            double d2 = 0.003;
            if (Math.abs(vector3d2.J_1907_R) < 0.003 && Math.abs(vector3d2.G_564_y) < 0.003 && vector3d.u_1723_Y() < 0.0045000000000000005) {
                vector3d = vector3d.G_564_y().n_1700_B(0.0045000000000000005);
            }
            this.v_4262_N(this.I_4348_c().P_1922_E(vector3d));
        }
        this.H_1083_k.put(fluidTag, d0);
        return flag1;
    }

    public double J_1907_R(r_109_r<Fluid> p_233571_1_) {
        return this.H_1083_k.getDouble(p_233571_1_);
    }

    public double i_3196_G() {
        return (double)this.X_1313_W() < 0.4 ? 0.0 : 0.4;
    }

    public final float C_415_h() {
        return this.H_2857_Y.n_1700_B;
    }

    public final float v_165_F() {
        return this.H_2857_Y.J_1907_R;
    }

    public abstract Packet<?> f_();

    public R_1815_U n_1700_B(I_1170_F poseIn) {
        return this.P_1922_E.u_2550_I();
    }

    public e_2866_D s_4990_V() {
        return this.t_148_a;
    }

    public c_1514_x b_2312_j() {
        return this.s_956_w;
    }

    public e_2866_D I_4348_c() {
        return this.Ping;
    }

    public void v_4262_N(e_2866_D motionIn) {
        this.Ping = motionIn;
    }

    public void h_1847_R(double x, double y, double z) {
        this.v_4262_N(new e_2866_D(x, y, z));
    }

    public final double O_3598_v() {
        return this.t_148_a.J_1907_R;
    }

    public double R_4764_Y(double p_226275_1_) {
        return this.t_148_a.J_1907_R + (double)this.C_415_h() * p_226275_1_;
    }

    public double G_564_y(double p_226282_1_) {
        return this.R_4764_Y((2.0 * this.RealmsWorldOptions.nextDouble() - 1.0) * p_226282_1_);
    }

    public final double X_2960_b() {
        return this.t_148_a.R_4764_Y;
    }

    public double P_1922_E(double p_226283_1_) {
        return this.t_148_a.R_4764_Y + (double)this.v_165_F() * p_226283_1_;
    }

    public double M_766_z() {
        return this.P_1922_E(this.RealmsWorldOptions.nextDouble());
    }

    public double X_2048_Y() {
        return this.t_148_a.R_4764_Y + (double)this.A_4115_X;
    }

    public final double l_2647_k() {
        return this.t_148_a.G_564_y;
    }

    public double u_1723_Y(double p_226285_1_) {
        return this.t_148_a.G_564_y + (double)this.C_415_h() * p_226285_1_;
    }

    public double v_4262_N(double p_226287_1_) {
        return this.u_1723_Y((2.0 * this.RealmsWorldOptions.nextDouble() - 1.0) * p_226287_1_);
    }

    public void Q_4569_t(double x, double y, double z) {
        if (this.t_148_a.J_1907_R != x || this.t_148_a.R_4764_Y != y || this.t_148_a.G_564_y != z) {
            this.t_148_a = new e_2866_D(x, y, z);
            int i = u_530_F.R_4764_Y(x);
            int j = u_530_F.R_4764_Y(y);
            int k = u_530_F.R_4764_Y(z);
            if (i != this.s_956_w.getX() || j != this.s_956_w.getY() || k != this.s_956_w.getZ()) {
                this.s_956_w = new c_1514_x(i, j, k);
            }
            this.Y_601_j = true;
        }
    }

    public void a_178_J() {
    }

    public e_2866_D P_1922_E(float partialTicks) {
        return this.P_4830_p(partialTicks).J_1907_R(0.0, (double)this.A_4115_X * 0.7, 0.0);
    }

    public e_2866_D M_182_A(float partialTicks) {
        return new e_2866_D(this.q_1982_R, this.dtoRealmsServerAddress, this.w_612_n).n_1700_B(this.s_4990_V(), partialTicks);
    }

    public void T_437_o() {
        this.RealmsClientOutdatedScreen = true;
        this.U_1241_n = 0.0f;
    }

    @FunctionalInterface
    public static interface n_1700_B {
        public void accept(N_4263_v var1, double var2, double var4, double var6);
    }
}



