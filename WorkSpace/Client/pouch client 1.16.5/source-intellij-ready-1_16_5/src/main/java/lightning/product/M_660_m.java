/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.B_1049_N;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_3343_g;
import lightning.product.E_688_b;
import lightning.product.ItemTransforms;
import lightning.product.G_2460_P;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.H_2333_J;
import lightning.product.I_14_v;
import lightning.product.HitResult;
import lightning.product.I_4817_s;
import lightning.product.J_1907_R;
import lightning.product.ResourceManagerReloadListener;
import lightning.product.MobEffects;
import lightning.product.K_1289_S;
import lightning.product.K_4074_S;
import lightning.product.M_1336_P;
import lightning.product.M_914_T;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.monsterSpider;
import lightning.product.R_3197_Z;
import lightning.product.ResourceManager;
import lightning.product.U_2871_b;
import lightning.product.RenderBuffers;
import lightning.product.U_679_Y;
import lightning.product.W_3265_k;
import lightning.product.X_2048_Y;
import lightning.product.X_4340_E;
import lightning.product.X_933_l;
import lightning.product.Z_1567_W;
import lightning.product.Z_1993_T;
import lightning.product.Z_3224_L;
import lightning.product.Z_759_W;
import lightning.product.Z_875_P;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_3485_j;
import lightning.product.BetterMinecraft;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.c_4037_x;
import lightning.product.e_1689_x;
import lightning.product.e_2866_D;
import lightning.product.g_164_R;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_3270_j;
import lightning.product.h_3572_K;
import lightning.product.h_4412_P;
import lightning.product.i_2518_W;
import lightning.product.i_2909_p;
import lightning.product.Easing;
import lightning.product.i_601_W;
import lightning.product.j_2129_E;
import lightning.product.j_3341_s;
import lightning.product.Animation;
import lightning.product.k_4690_i;
import lightning.product.k_596_g;
import lightning.product.l_3747_P;
import lightning.product.l_456_f;
import lightning.product.m_891_U;
import lightning.product.n_1700_B;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.ClientBootstrap;
import lightning.product.o_3091_w;
import lightning.product.BlockInWorld;
import lightning.product.CrashReportCategory;
import lightning.product.r_4811_B;
import lightning.product.t_3340_s;
import lightning.product.u_2877_K;
import lightning.product.u_530_F;
import lightning.product.EntityHitResult;
import lightning.product.x_2151_q;
import lightning.product.x_3498_p;
import lightning.product.y_740_d;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.VanillaResourceType;
import net.optifine.Config;
import net.optifine.GlErrors;
import net.optifine.Lagometer;
import net.optifine.RandomEntities;
import net.optifine.gui.GuiChatOF;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorResolver;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.util.MemoryMonitor;
import net.optifine.util.TimedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class M_660_m
implements AutoCloseable,
ResourceManagerReloadListener {
    private static final g_2336_b R_4764_Y = new g_2336_b("textures/misc/nausea.png");
    private static final Logger G_564_y = LogManager.getLogger();
    private static final Predicate<N_4263_v> P_1922_E = entity -> !entity.d_2461_k() && entity.C_290_v();
    private n_1700_B u_1723_Y = null;
    private N_4263_v v_4262_N = null;
    private final MinecraftClient w_1484_f;
    private final ResourceManager t_148_a;
    private final Random s_956_w = new Random();
    private float u_2550_I;
    public final l_456_f n_1700_B;
    private final t_3340_s M_588_G;
    private final RenderBuffers P_4830_p;
    private int h_1847_R;
    private float Q_4569_t;
    private float M_182_A;
    private float t_1786_h;
    private float multiplayerClientSuggestionProvider;
    private boolean w_1457_N = true;
    private boolean Y_601_j = true;
    private long Y_259_p;
    private long Q_2552_b = j_3341_s.J_1907_R();
    private final e_1689_x C_2741_M;
    private final Z_3224_L k_2293_S = new Z_3224_L();
    private boolean q_2307_F;
    private float Z_875_P = 1.0f;
    private float c_3005_b;
    private float H_2857_Y;
    private final Animation A_4115_X = new Animation(1.0f, 6.0f, Easing.Y_601_j);
    private final Animation Y_1740_V = new Animation(1.0f, 12.0f, Easing.u_2550_I);
    @Nullable
    private Z_1993_T t_4043_B;
    private int x_607_J;
    private float e_4240_b;
    private float n_3318_d;
    @Nullable
    private x_2151_q d_2427_y;
    private static final g_2336_b[] z_1737_N = new g_2336_b[]{new g_2336_b("shaders/post/notch.json"), new g_2336_b("shaders/post/fxaa.json"), new g_2336_b("shaders/post/art.json"), new g_2336_b("shaders/post/bumpy.json"), new g_2336_b("shaders/post/blobs2.json"), new g_2336_b("shaders/post/pencil.json"), new g_2336_b("shaders/post/color_convolve.json"), new g_2336_b("shaders/post/deconverge.json"), new g_2336_b("shaders/post/flip.json"), new g_2336_b("shaders/post/invert.json"), new g_2336_b("shaders/post/ntsc.json"), new g_2336_b("shaders/post/outline.json"), new g_2336_b("shaders/post/phosphor.json"), new g_2336_b("shaders/post/scan_pincushion.json"), new g_2336_b("shaders/post/sobel.json"), new g_2336_b("shaders/post/bits.json"), new g_2336_b("shaders/post/desaturate.json"), new g_2336_b("shaders/post/green.json"), new g_2336_b("shaders/post/blur.json"), new g_2336_b("shaders/post/wobble.json"), new g_2336_b("shaders/post/blobs.json"), new g_2336_b("shaders/post/antialias.json"), new g_2336_b("shaders/post/creeper.json"), new g_2336_b("shaders/post/spider.json")};
    public static final int J_1907_R = z_1737_N.length;
    private int v_4276_D = J_1907_R;
    private boolean d_2461_k;
    private final h_3572_K G_624_v = new h_3572_K();
    private boolean T_2506_i = false;
    private b_4507_u q_4610_l = null;
    private float z_4693_k = 128.0f;
    private long g_221_o = 0L;
    private int e_2887_G = 0;
    private int B_1668_F = 0;
    private int g_164_R = 0;
    private float X_933_l = 0.0f;
    private float Z_976_R = 0.0f;
    private x_2151_q[] H_1990_U = new x_2151_q[10];
    private boolean N_2525_X = false;

    private n_1700_B Q_4569_t() {
        N_4263_v currentRenderView = this.w_1484_f.C_2741_M;
        if (currentRenderView != this.v_4262_N) {
            this.v_4262_N = currentRenderView;
            this.u_1723_Y = null;
            if (lightning.product.J_1907_R.n_1700_B != null && !lightning.product.J_1907_R.n_1700_B.isEmpty()) {
                for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                    if (bot == null || bot.P_1922_E == null || bot.P_1922_E.Q_2552_b != currentRenderView) continue;
                    this.u_1723_Y = bot;
                    break;
                }
            }
        }
        if (this.u_1723_Y != null && (this.u_1723_Y.P_1922_E == null || this.u_1723_Y.P_1922_E.Q_2552_b != currentRenderView)) {
            this.u_1723_Y = null;
        }
        return this.u_1723_Y;
    }

    public void J_1907_R() {
        BetterMinecraft betterMinecraft = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        if (betterMinecraft == null || !betterMinecraft.w_1484_f()) {
            return;
        }
        Boolean enabled = betterMinecraft.Z_875_P().J_1907_R("\u0418\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u0435 \u043f\u0435\u0440\u0441\u043f\u0435\u043a\u0442\u0438\u0432\u044b");
        if (!Boolean.TRUE.equals(enabled)) {
            return;
        }
        this.Y_1740_V.J_1907_R(0.7f);
        this.Y_1740_V.n_1700_B(1.0f);
    }

    public M_660_m(MinecraftClient mcIn, ResourceManager resourceManagerIn, RenderBuffers renderTypeBuffersIn) {
        this.w_1484_f = mcIn;
        this.t_148_a = resourceManagerIn;
        this.n_1700_B = mcIn.A_1038_p();
        this.M_588_G = new t_3340_s(mcIn.G_624_v());
        this.C_2741_M = new e_1689_x(this, mcIn);
        this.P_4830_p = renderTypeBuffersIn;
        this.d_2427_y = null;
    }

    public void n_1700_B(float scale) {
        if (scale <= 0.0f || this.w_1484_f == null || this.w_1484_f.RealmsServerPing() == null) {
            return;
        }
        U_679_Y window = this.w_1484_f.RealmsServerPing();
        window.n_1700_B(scale);
        c_4037_x.u_2550_I(5889);
        c_4037_x.z_1737_N();
        c_4037_x.n_1700_B(0.0, (float)window.u_2550_I() / scale, (float)window.M_588_G() / scale, 0.0, 1000.0, 3000.0);
        c_4037_x.u_2550_I(5888);
        c_4037_x.z_1737_N();
        c_4037_x.R_4764_Y(0.0f, 0.0f, -2000.0f);
    }

    public void R_4764_Y() {
        if (this.w_1484_f == null || this.w_1484_f.RealmsServerPing() == null) {
            return;
        }
        U_679_Y window = this.w_1484_f.RealmsServerPing();
        int baseScale = window.n_1700_B(this.w_1484_f.P_4830_p.g_4106_L, this.w_1484_f.v_4262_N());
        window.n_1700_B((double)baseScale);
        c_4037_x.u_2550_I(5889);
        c_4037_x.z_1737_N();
        c_4037_x.n_1700_B(0.0, (double)window.u_2550_I() / (double)baseScale, (double)window.M_588_G() / (double)baseScale, 0.0, 1000.0, 3000.0);
        c_4037_x.u_2550_I(5888);
        c_4037_x.z_1737_N();
        c_4037_x.R_4764_Y(0.0f, 0.0f, -2000.0f);
    }

    @Override
    public void close() {
        this.C_2741_M.close();
        this.M_588_G.close();
        this.k_2293_S.close();
        this.G_564_y();
    }

    public void G_564_y() {
        if (this.d_2427_y != null) {
            this.d_2427_y.close();
        }
        this.d_2427_y = null;
        this.v_4276_D = J_1907_R;
    }

    public void P_1922_E() {
        this.d_2461_k = !this.d_2461_k;
    }

    public void n_1700_B(@Nullable N_4263_v entityIn) {
        if (this.d_2427_y != null) {
            this.d_2427_y.close();
        }
        this.d_2427_y = null;
        if (entityIn instanceof b_3485_j) {
            this.n_1700_B(new g_2336_b("shaders/post/creeper.json"));
        } else if (entityIn instanceof monsterSpider) {
            this.n_1700_B(new g_2336_b("shaders/post/spider.json"));
        } else if (entityIn instanceof M_914_T) {
            this.n_1700_B(new g_2336_b("shaders/post/invert.json"));
        } else if (Reflector.ForgeHooksClient_loadEntityShader.exists()) {
            Reflector.call(Reflector.ForgeHooksClient_loadEntityShader, entityIn, this);
        }
    }

    private void n_1700_B(g_2336_b resourceLocationIn) {
        if (lightning.product.g_164_R.v_4262_N()) {
            if (this.d_2427_y != null) {
                this.d_2427_y.close();
            }
            try {
                this.d_2427_y = new x_2151_q(this.w_1484_f.G_624_v(), this.t_148_a, this.w_1484_f.G_564_y(), resourceLocationIn);
                this.d_2427_y.n_1700_B(this.w_1484_f.RealmsServerPing().u_2550_I(), this.w_1484_f.RealmsServerPing().M_588_G());
                this.d_2461_k = true;
            }
            catch (IOException ioexception) {
                G_564_y.warn("Failed to load shader: {}", (Object)resourceLocationIn, (Object)ioexception);
                this.v_4276_D = J_1907_R;
                this.d_2461_k = false;
            }
            catch (JsonSyntaxException jsonsyntaxexception) {
                G_564_y.warn("Failed to parse shader: {}", (Object)resourceLocationIn, (Object)jsonsyntaxexception);
                this.v_4276_D = J_1907_R;
                this.d_2461_k = false;
            }
        }
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        if (this.d_2427_y != null) {
            this.d_2427_y.close();
        }
        this.d_2427_y = null;
        if (this.v_4276_D == J_1907_R) {
            this.n_1700_B(this.w_1484_f.g_2268_R());
        } else {
            this.n_1700_B(z_1737_N[this.v_4276_D]);
        }
    }

    public void u_1723_Y() {
        this.M_182_A();
        this.C_2741_M.n_1700_B();
        if (this.w_1484_f.g_2268_R() == null) {
            this.w_1484_f.n_1700_B(this.w_1484_f.Y_259_p);
        }
        this.G_624_v.n_1700_B();
        ++this.h_1847_R;
        this.n_1700_B.n_1700_B();
        this.w_1484_f.u_1723_Y.n_1700_B(this.G_624_v);
        this.multiplayerClientSuggestionProvider = this.t_1786_h;
        if (this.w_1484_f.M_588_G.t_148_a().P_1922_E()) {
            this.t_1786_h += 0.05f;
            if (this.t_1786_h > 1.0f) {
                this.t_1786_h = 1.0f;
            }
        } else if (this.t_1786_h > 0.0f) {
            this.t_1786_h -= 0.0125f;
        }
        if (this.x_607_J > 0) {
            --this.x_607_J;
            if (this.x_607_J == 0) {
                this.t_4043_B = null;
            }
        }
    }

    @Nullable
    public x_2151_q v_4262_N() {
        return this.d_2427_y;
    }

    public void n_1700_B(int width, int height) {
        if (this.d_2427_y != null) {
            this.d_2427_y.n_1700_B(width, height);
        }
        this.w_1484_f.u_1723_Y.n_1700_B(width, height);
    }

    public void J_1907_R(float partialTicks) {
        N_4263_v entity = this.w_1484_f.g_2268_R();
        if (entity != null && this.w_1484_f.Y_601_j != null) {
            I_4817_s axisalignedbb;
            e_2866_D vector3d2;
            EntityHitResult entityraytraceresult;
            this.w_1484_f.PlayerInfo().n_1700_B("pick");
            this.w_1484_f.q_2307_F = null;
            double d0 = this.w_1484_f.w_1457_N.getBlockReachDistance();
            n_1700_B activeBot = this.Q_4569_t();
            if (activeBot != null && entity == activeBot.P_1922_E.Q_2552_b) {
                d0 = activeBot.P_1922_E.C_2741_M.R_4764_Y();
            }
            this.w_1484_f.Z_875_P = entity.n_1700_B(d0, partialTicks, false);
            e_2866_D vector3d = entity.u_2550_I(partialTicks);
            e_2866_D vector3d1 = entity.t_148_a(1.0f);
            if (activeBot == null) {
                P_3504_Q entityRotation = new P_3504_Q(entity.p_178_J, entity.f_4016_n);
                m_891_U eventMouseOver = new m_891_U(vector3d, entityRotation);
                lightning.product.A_4115_X.n_1700_B(eventMouseOver);
                vector3d = eventMouseOver.n_1700_B();
                float yaw = eventMouseOver.J_1907_R().t_148_a;
                float pitch = eventMouseOver.J_1907_R().s_956_w;
                vector3d1 = this.w_1484_f.Y_259_p.G_564_y(pitch, yaw);
                e_2866_D endVec = vector3d.J_1907_R(vector3d1.J_1907_R * d0, vector3d1.R_4764_Y * d0, vector3d1.G_564_y * d0);
                this.w_1484_f.Z_875_P = this.w_1484_f.Y_601_j.n_1700_B(new ClipContext(vector3d, endVec, ClipContext.n_1700_B.J_1907_R, ClipContext.J_1907_R.n_1700_B, entity));
                for (int iter = 0; iter < 32 && this.w_1484_f.Z_875_P != null && this.w_1484_f.Z_875_P.R_4764_Y() == HitResult.n_1700_B.J_1907_R; ++iter) {
                    BlockHitResult bhr = (BlockHitResult)this.w_1484_f.Z_875_P;
                    K_4074_S hitState = this.w_1484_f.Y_601_j.getBlockState(bhr.n_1700_B());
                    Z_759_W blockRayTraceEvent = new Z_759_W(hitState, bhr.n_1700_B());
                    lightning.product.A_4115_X.n_1700_B(blockRayTraceEvent);
                    if (!blockRayTraceEvent.n_1700_B()) break;
                    c_1514_x hitPos = bhr.n_1700_B();
                    double offset = 0.001;
                    e_2866_D newStart = bhr.P_1922_E().J_1907_R(vector3d1.J_1907_R * offset, vector3d1.R_4764_Y * offset, vector3d1.G_564_y * offset);
                    while (offset < 2.0 && new c_1514_x(newStart).equals(hitPos)) {
                        newStart = bhr.P_1922_E().J_1907_R(vector3d1.J_1907_R * (offset *= 2.0), vector3d1.R_4764_Y * offset, vector3d1.G_564_y * offset);
                    }
                    this.w_1484_f.Z_875_P = this.w_1484_f.Y_601_j.n_1700_B(new ClipContext(newStart, endVec, ClipContext.n_1700_B.J_1907_R, ClipContext.J_1907_R.n_1700_B, entity));
                }
            }
            boolean flag = false;
            double d1 = d0;
            if (activeBot != null && entity == activeBot.P_1922_E.Q_2552_b ? activeBot.P_1922_E.C_2741_M.t_148_a() : this.w_1484_f.w_1457_N.extendedReach()) {
                d0 = d1 = 6.0;
            } else if (d0 > 3.0) {
                flag = true;
            }
            d1 *= d1;
            if (this.w_1484_f.Z_875_P != null) {
                d1 = this.w_1484_f.Z_875_P.P_1922_E().v_4262_N(vector3d);
            }
            if ((entityraytraceresult = H_2333_J.n_1700_B(entity, vector3d, vector3d2 = vector3d.J_1907_R(vector3d1.J_1907_R * d0, vector3d1.R_4764_Y * d0, vector3d1.G_564_y * d0), axisalignedbb = entity.i_601_W().expand(vector3d1.n_1700_B(d0)).grow(1.0, 1.0, 1.0), e -> e.p_178_J() && !e.d_2461_k() && e.C_290_v(), d1)) != null) {
                N_4263_v entity1 = entityraytraceresult.n_1700_B();
                e_2866_D vector3d3 = entityraytraceresult.P_1922_E();
                double d2 = vector3d.v_4262_N(vector3d3);
                if (flag && d2 > 9.0) {
                    this.w_1484_f.Z_875_P = BlockHitResult.n_1700_B(vector3d3, b_257_Y.n_1700_B(vector3d1.J_1907_R, vector3d1.R_4764_Y, vector3d1.G_564_y), new c_1514_x(vector3d3));
                } else if (d2 < d1 || this.w_1484_f.Z_875_P == null) {
                    this.w_1484_f.Z_875_P = entityraytraceresult;
                    if (entity1 instanceof r_4811_B || entity1 instanceof y_740_d) {
                        this.w_1484_f.q_2307_F = entity1;
                    }
                }
            }
            this.w_1484_f.PlayerInfo().R_4764_Y();
        }
    }

    public void R_4764_Y(float partialTicks) {
        if (lightning.product.J_1907_R.n_1700_B == null || lightning.product.J_1907_R.n_1700_B.isEmpty()) {
            return;
        }
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            double scaleZ;
            double scaleY;
            double scaleX;
            if (bot == null || bot.P_1922_E == null || bot.P_1922_E.C_2741_M == null || bot.P_1922_E.G_564_y() == null) continue;
            double reachDistance = bot.P_1922_E.C_2741_M.R_4764_Y();
            bot.P_1922_E.Q_2552_b.v_4262_N = bot.P_1922_E.Q_2552_b.n_1700_B(reachDistance, partialTicks, false);
            e_2866_D eyePosition = bot.P_1922_E.Q_2552_b.u_2550_I(partialTicks);
            boolean isFarReach = false;
            double maxReachDistance = reachDistance;
            if (bot.P_1922_E.C_2741_M.t_148_a()) {
                maxReachDistance = 6.0;
            } else if (reachDistance > 3.0) {
                isFarReach = true;
            }
            double squaredDistance = maxReachDistance * maxReachDistance;
            if (bot.P_1922_E.Q_2552_b.v_4262_N != null) {
                squaredDistance = bot.P_1922_E.Q_2552_b.v_4262_N.P_1922_E().v_4262_N(eyePosition);
            }
            e_2866_D lookVector = bot.P_1922_E.Q_2552_b.t_148_a(1.0f);
            double endX = eyePosition.J_1907_R + lookVector.J_1907_R * maxReachDistance;
            double endY = eyePosition.R_4764_Y + lookVector.R_4764_Y * maxReachDistance;
            double endZ = eyePosition.G_564_y + lookVector.G_564_y * maxReachDistance;
            e_2866_D endPosition = new e_2866_D(endX, endY, endZ);
            I_4817_s botBB = bot.P_1922_E.Q_2552_b.i_601_W();
            I_4817_s searchBox = botBB.expand(scaleX = lookVector.J_1907_R * maxReachDistance, scaleY = lookVector.R_4764_Y * maxReachDistance, scaleZ = lookVector.G_564_y * maxReachDistance).grow(1.0, 1.0, 1.0);
            EntityHitResult entityHit = H_2333_J.n_1700_B(bot.P_1922_E.Q_2552_b, eyePosition, endPosition, searchBox, P_1922_E, squaredDistance);
            if (entityHit == null) continue;
            e_2866_D hitPosition = entityHit.P_1922_E();
            double hitDistanceSquared = eyePosition.v_4262_N(hitPosition);
            if (isFarReach && hitDistanceSquared > 9.0) {
                c_1514_x blockPos = new c_1514_x(hitPosition.J_1907_R, hitPosition.R_4764_Y, hitPosition.G_564_y);
                bot.P_1922_E.Q_2552_b.v_4262_N = BlockHitResult.n_1700_B(hitPosition, b_257_Y.n_1700_B(lookVector.J_1907_R, lookVector.R_4764_Y, lookVector.G_564_y), blockPos);
                continue;
            }
            if (!(hitDistanceSquared < squaredDistance) && bot.P_1922_E.Q_2552_b.v_4262_N != null) continue;
            bot.P_1922_E.Q_2552_b.v_4262_N = entityHit;
        }
    }

    private void M_182_A() {
        float f = 1.0f;
        if (this.w_1484_f.g_2268_R() instanceof X_4340_E) {
            X_4340_E abstractclientplayerentity = (X_4340_E)this.w_1484_f.g_2268_R();
            f = abstractclientplayerentity.N_2525_X();
        }
        this.M_182_A = this.Q_4569_t;
        this.Q_4569_t += (f - this.Q_4569_t) * 0.5f;
        if (this.Q_4569_t > 1.5f) {
            this.Q_4569_t = 1.5f;
        }
        if (this.Q_4569_t < 0.1f) {
            this.Q_4569_t = 0.1f;
        }
    }

    public double n_1700_B(h_3572_K activeRenderInfoIn, float partialTicks, boolean useFOVSetting) {
        FluidState fluidstate;
        if (this.q_2307_F) {
            return 90.0;
        }
        double d0 = 70.0;
        if (useFOVSetting) {
            d0 = this.w_1484_f.P_4830_p.R_3077_Z;
            if (Config.isDynamicFov()) {
                d0 *= (double)u_530_F.v_4262_N(partialTicks, this.M_182_A, this.Q_4569_t);
            }
        }
        boolean flag = false;
        if (this.w_1484_f.Y_1740_V == null) {
            flag = this.w_1484_f.P_4830_p.A_3244_K.G_564_y();
        }
        if (flag) {
            if (!Config.zoomMode) {
                Config.zoomMode = true;
                Config.zoomSmoothCamera = this.w_1484_f.P_4830_p.S_980_j;
                this.w_1484_f.P_4830_p.S_980_j = true;
                this.w_1484_f.u_1723_Y.h_1847_R();
            }
        } else if (Config.zoomMode) {
            Config.zoomMode = false;
            this.w_1484_f.P_4830_p.S_980_j = Config.zoomSmoothCamera;
            this.w_1484_f.u_1723_Y.h_1847_R();
        }
        float customZoomLevel = 4.0f;
        BetterMinecraft betterMinecraftZoom = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        if (betterMinecraftZoom != null && betterMinecraftZoom.w_1484_f()) {
            customZoomLevel = betterMinecraftZoom.w_1457_N();
        }
        if (flag && Config.zoomMode) {
            float target = 1.0f / customZoomLevel;
            this.A_4115_X.n_1700_B(target);
            d0 *= (double)this.A_4115_X.n_1700_B();
        } else {
            this.A_4115_X.n_1700_B(1.0f);
        }
        boolean perspectiveAnimEnabled = false;
        BetterMinecraft betterMinecraft2 = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        if (betterMinecraft2 != null && betterMinecraft2.w_1484_f()) {
            Boolean enabled2 = betterMinecraft2.Z_875_P().J_1907_R("\u0418\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u0435 \u043f\u0435\u0440\u0441\u043f\u0435\u043a\u0442\u0438\u0432\u044b");
            perspectiveAnimEnabled = Boolean.TRUE.equals(enabled2);
        }
        if (perspectiveAnimEnabled && this.Y_1740_V.G_564_y()) {
            this.Y_1740_V.n_1700_B(1.0f);
            d0 *= (double)u_530_F.n_1700_B(this.Y_1740_V.n_1700_B(), 0.5f, 1.0f);
        }
        if (activeRenderInfoIn.v_4262_N() instanceof r_4811_B && ((r_4811_B)activeRenderInfoIn.v_4262_N()).Z_2812_M()) {
            float f = Math.min((float)((r_4811_B)activeRenderInfoIn.v_4262_N()).O_2151_c + partialTicks, 20.0f);
            d0 /= (double)((1.0f - 500.0f / (f + 500.0f)) * 2.0f + 1.0f);
        }
        if (!(fluidstate = activeRenderInfoIn.s_956_w()).R_4764_Y()) {
            d0 = d0 * 60.0 / 70.0;
        }
        return Reflector.ForgeHooksClient_getFOVModifier.exists() ? Reflector.callDouble(Reflector.ForgeHooksClient_getFOVModifier, this, activeRenderInfoIn, Float.valueOf(partialTicks), d0) : d0;
    }

    private void n_1700_B(g_221_o matrixStackIn, float partialTicks) {
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.u_1723_Y);
        lightning.product.A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            return;
        }
        if (this.w_1484_f.g_2268_R() instanceof r_4811_B) {
            r_4811_B livingentity = (r_4811_B)this.w_1484_f.g_2268_R();
            float f = (float)livingentity.RealmsLongRunningMcoTaskScreen - partialTicks;
            if (livingentity.Z_2812_M()) {
                float f1 = Math.min((float)livingentity.O_2151_c + partialTicks, 20.0f);
                matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(40.0f - 8000.0f / (f1 + 200.0f)));
            }
            if (f < 0.0f) {
                return;
            }
            f /= (float)livingentity.i_2993_w;
            f = u_530_F.n_1700_B(f * f * f * f * (float)Math.PI);
            float f2 = livingentity.RealmsParentalConsentScreen;
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-f2));
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-f * 14.0f));
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(f2));
        }
    }

    private void J_1907_R(g_221_o matrixStackIn, float partialTicks) {
        if (this.w_1484_f.g_2268_R() instanceof a_3913_L) {
            a_3913_L playerentity = (a_3913_L)this.w_1484_f.g_2268_R();
            float f = playerentity.PlayerInfo - playerentity.V_1446_Y;
            float f1 = -(playerentity.PlayerInfo + f * partialTicks);
            float f2 = u_530_F.v_4262_N(partialTicks, playerentity.X_290_I, playerentity.O_1795_e);
            matrixStackIn.n_1700_B((double)(u_530_F.n_1700_B(f1 * (float)Math.PI) * f2 * 0.5f), (double)(-Math.abs(u_530_F.J_1907_R(f1 * (float)Math.PI) * f2)), 0.0);
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(u_530_F.n_1700_B(f1 * (float)Math.PI) * f2 * 3.0f));
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(Math.abs(u_530_F.J_1907_R(f1 * (float)Math.PI - 0.2f) * f2) * 5.0f));
        }
    }

    private void n_1700_B(g_221_o matrixStackIn, h_3572_K activeRenderInfoIn, float partialTicks) {
        this.n_1700_B(matrixStackIn, activeRenderInfoIn, partialTicks, true, true, false);
    }

    public void n_1700_B(g_221_o p_renderHand_1_, h_3572_K p_renderHand_2_, float p_renderHand_3_, boolean p_renderHand_4_, boolean p_renderHand_5_, boolean p_renderHand_6_) {
        if (!this.q_2307_F) {
            Shaders.beginRenderFirstPersonHand(p_renderHand_6_);
            this.n_1700_B(this.J_1907_R(p_renderHand_2_, p_renderHand_3_, false));
            g_221_o.n_1700_B matrixstack$entry = p_renderHand_1_.R_4764_Y();
            matrixstack$entry.n_1700_B().n_1700_B();
            matrixstack$entry.J_1907_R().R_4764_Y();
            boolean flag = false;
            if (p_renderHand_4_) {
                N_4263_v renderViewEntity;
                p_renderHand_1_.n_1700_B();
                this.n_1700_B(p_renderHand_1_, p_renderHand_3_);
                if (this.w_1484_f.P_4830_p.T_3594_S) {
                    this.J_1907_R(p_renderHand_1_, p_renderHand_3_);
                }
                flag = (renderViewEntity = this.w_1484_f.g_2268_R()) instanceof r_4811_B && ((r_4811_B)renderViewEntity).z_2372_L();
                n_1700_B bot1 = this.Q_4569_t();
                if (this.w_1484_f.P_4830_p.P_4830_p().n_1700_B() && !flag && !this.w_1484_f.P_4830_p.RetryCallException && !(bot1 != null ? bot1.P_1922_E.C_2741_M.P_4830_p() == I_14_v.P_1922_E : this.w_1484_f.w_1457_N != null && this.w_1484_f.w_1457_N.getCurrentGameType() == I_14_v.P_1922_E)) {
                    this.C_2741_M.R_4764_Y();
                    lightning.product.A_4115_X.n_1700_B(new X_2048_Y.J_1907_R(this.G_624_v, p_renderHand_1_, p_renderHand_3_));
                    if (bot1 != null) {
                        if (Config.isShaders()) {
                            ShadersRender.renderItemFP(this.n_1700_B, p_renderHand_3_, p_renderHand_1_, this.P_4830_p.J_1907_R(), this.w_1484_f.T_3594_S(), this.w_1484_f.O_508_d().n_1700_B(this.w_1484_f.T_3594_S(), p_renderHand_3_), p_renderHand_6_);
                        } else {
                            this.n_1700_B.n_1700_B(p_renderHand_3_, p_renderHand_1_, this.P_4830_p.J_1907_R(), this.w_1484_f.T_3594_S(), this.w_1484_f.O_508_d().n_1700_B(this.w_1484_f.T_3594_S(), p_renderHand_3_));
                        }
                    } else if (Config.isShaders()) {
                        ShadersRender.renderItemFP(this.n_1700_B, p_renderHand_3_, p_renderHand_1_, this.P_4830_p.J_1907_R(), this.w_1484_f.Y_259_p, this.w_1484_f.O_508_d().n_1700_B(this.w_1484_f.Y_259_p, p_renderHand_3_), p_renderHand_6_);
                    } else {
                        this.n_1700_B.n_1700_B(p_renderHand_3_, p_renderHand_1_, this.P_4830_p.J_1907_R(), this.w_1484_f.Y_259_p, this.w_1484_f.O_508_d().n_1700_B(this.w_1484_f.Y_259_p, p_renderHand_3_));
                    }
                    lightning.product.A_4115_X.n_1700_B(new X_2048_Y.n_1700_B(this.G_624_v, p_renderHand_1_, p_renderHand_3_));
                    this.C_2741_M.J_1907_R();
                }
                p_renderHand_1_.J_1907_R();
            }
            Shaders.endRenderFirstPersonHand();
            if (!p_renderHand_5_) {
                return;
            }
            this.C_2741_M.J_1907_R();
            if (this.w_1484_f.P_4830_p.P_4830_p().n_1700_B() && !flag) {
                B_1049_N.n_1700_B(this.w_1484_f, p_renderHand_1_);
                this.n_1700_B(p_renderHand_1_, p_renderHand_3_);
            }
            if (this.w_1484_f.P_4830_p.T_3594_S) {
                this.J_1907_R(p_renderHand_1_, p_renderHand_3_);
            }
        }
    }

    public void n_1700_B(D_1098_v matrixIn) {
        c_4037_x.u_2550_I(5889);
        c_4037_x.z_1737_N();
        c_4037_x.n_1700_B(matrixIn);
        c_4037_x.u_2550_I(5888);
    }

    public D_1098_v J_1907_R(h_3572_K activeRenderInfoIn, float partialTicks, boolean useFovSetting) {
        g_221_o matrixstack = new g_221_o();
        matrixstack.R_4764_Y().n_1700_B().n_1700_B();
        if (Config.isShaders() && Shaders.isRenderingFirstPersonHand()) {
            Shaders.applyHandDepth(matrixstack);
        }
        this.z_4693_k = Math.max(this.u_2550_I * 2.0f, 173.0f);
        if (this.Z_875_P != 1.0f) {
            matrixstack.n_1700_B((double)this.c_3005_b, (double)(-this.H_2857_Y), 0.0);
            matrixstack.n_1700_B(this.Z_875_P, this.Z_875_P, 1.0f);
        }
        float aspectRatio = (float)this.w_1484_f.RealmsServerPing().u_2550_I() / (float)this.w_1484_f.RealmsServerPing().M_588_G();
        i_601_W event = new i_601_W(aspectRatio);
        lightning.product.A_4115_X.n_1700_B(event);
        if (!event.n_1700_B()) {
            aspectRatio = event.J_1907_R();
        }
        matrixstack.R_4764_Y().n_1700_B().n_1700_B(D_1098_v.n_1700_B(this.n_1700_B(activeRenderInfoIn, partialTicks, useFovSetting), aspectRatio, 0.05f, this.z_4693_k));
        return matrixstack.R_4764_Y().n_1700_B();
    }

    public static float n_1700_B(r_4811_B livingEntityIn, float entitylivingbaseIn) {
        int i = livingEntityIn.R_4764_Y(MobEffects.M_182_A).J_1907_R();
        return i > 200 ? 1.0f : 0.7f + u_530_F.n_1700_B(((float)i - entitylivingbaseIn) * (float)Math.PI * 0.2f) * 0.3f;
    }

    public void n_1700_B(float partialTicks, long nanoTime, boolean renderWorldIn) {
        this.Y_601_j();
        n_1700_B activeBot = this.Q_4569_t();
        if (!(this.w_1484_f.k_3961_g() || !this.w_1484_f.P_4830_p.t_1786_h || this.w_1484_f.P_4830_p.c_4037_x && this.w_1484_f.h_1847_R.R_4764_Y())) {
            if (j_3341_s.J_1907_R() - this.Q_2552_b > 500L) {
                this.w_1484_f.J_1907_R(false);
            }
        } else {
            this.Q_2552_b = j_3341_s.J_1907_R();
        }
        if (!this.w_1484_f.A_4115_X) {
            int i = (int)(this.w_1484_f.h_1847_R.G_564_y() * (double)this.w_1484_f.RealmsServerPing().Q_4569_t() / (double)this.w_1484_f.RealmsServerPing().P_4830_p());
            int j = (int)(this.w_1484_f.h_1847_R.P_1922_E() * (double)this.w_1484_f.RealmsServerPing().M_182_A() / (double)this.w_1484_f.RealmsServerPing().h_1847_R());
            if (renderWorldIn && (activeBot != null ? activeBot.P_1922_E.G_564_y() != null : this.w_1484_f.Y_601_j != null) && !Config.isReloadingResources()) {
                this.w_1484_f.PlayerInfo().n_1700_B("level");
                this.n_1700_B(partialTicks, nanoTime, new g_221_o());
                if (this.w_1484_f.e_4240_b() && this.Y_259_p < j_3341_s.J_1907_R() - 1000L) {
                    this.Y_259_p = j_3341_s.J_1907_R();
                    if (!this.w_1484_f.n_3318_d().Z_875_P()) {
                        this.t_1786_h();
                    }
                }
                this.w_1484_f.u_1723_Y.R_4764_Y();
                if (this.d_2427_y != null && this.d_2461_k) {
                    c_4037_x.Y_259_p();
                    c_4037_x.t_1786_h();
                    c_4037_x.u_2550_I();
                    c_4037_x.x_607_J();
                    c_4037_x.u_2550_I(5890);
                    c_4037_x.v_4276_D();
                    c_4037_x.z_1737_N();
                    this.d_2427_y.n_1700_B(partialTicks);
                    c_4037_x.d_2461_k();
                    c_4037_x.x_607_J();
                }
                this.w_1484_f.G_564_y().J_1907_R(true);
            } else {
                c_4037_x.R_4764_Y(0, 0, this.w_1484_f.RealmsServerPing().u_2550_I(), this.w_1484_f.RealmsServerPing().M_588_G());
            }
            U_679_Y mainwindow = this.w_1484_f.RealmsServerPing();
            c_4037_x.n_1700_B(256, MinecraftClient.n_1700_B);
            c_4037_x.u_2550_I(5889);
            c_4037_x.z_1737_N();
            c_4037_x.n_1700_B(0.0, (double)mainwindow.u_2550_I() / mainwindow.w_1457_N(), (double)mainwindow.M_588_G() / mainwindow.w_1457_N(), 0.0, 1000.0, 3000.0);
            c_4037_x.u_2550_I(5888);
            c_4037_x.z_1737_N();
            c_4037_x.R_4764_Y(0.0f, 0.0f, -2000.0f);
            W_3265_k.G_564_y();
            g_221_o matrixstack = new g_221_o();
            if (this.C_2741_M.P_1922_E()) {
                this.C_2741_M.n_1700_B(false);
            }
            if (renderWorldIn && (activeBot != null ? activeBot.P_1922_E.G_564_y() != null : this.w_1484_f.Y_601_j != null)) {
                float f;
                this.w_1484_f.PlayerInfo().J_1907_R("gui");
                j_2129_E.n_1700_B();
                if ((activeBot != null ? activeBot.P_1922_E.Q_2552_b : this.w_1484_f.Y_259_p) != null && (f = u_530_F.v_4262_N(partialTicks, activeBot != null ? this.w_1484_f.T_3594_S().t_4043_B : this.w_1484_f.Y_259_p.P_4830_p, activeBot != null ? this.w_1484_f.T_3594_S().Y_1740_V : this.w_1484_f.Y_259_p.M_588_G)) > 0.0f && (activeBot != null ? activeBot.P_1922_E.Q_2552_b.J_1907_R(MobEffects.t_148_a) : this.w_1484_f.Y_259_p.J_1907_R(MobEffects.t_148_a)) && this.w_1484_f.P_4830_p.RealmsScreenWithCallback < 1.0f) {
                    this.P_1922_E(f * (1.0f - this.w_1484_f.P_4830_p.RealmsScreenWithCallback));
                }
                if (!this.w_1484_f.P_4830_p.RetryCallException || this.w_1484_f.Y_1740_V != null) {
                    c_4037_x.l_1233_K();
                    this.n_1700_B(this.w_1484_f.RealmsServerPing().Q_4569_t(), this.w_1484_f.RealmsServerPing().M_182_A(), partialTicks);
                    this.w_1484_f.M_588_G.n_1700_B(matrixstack, partialTicks);
                    if (this.w_1484_f.P_4830_p.JsonUtils && !this.w_1484_f.P_4830_p.r_3651_U) {
                        Config.drawFps(matrixstack);
                    }
                    if (this.w_1484_f.P_4830_p.r_3651_U) {
                        Lagometer.showLagometer(matrixstack, (int)this.w_1484_f.RealmsServerPing().w_1457_N());
                    }
                    c_4037_x.n_1700_B(256, MinecraftClient.n_1700_B);
                }
                this.w_1484_f.PlayerInfo().R_4764_Y();
            }
            if (this.N_2525_X != (this.w_1484_f.t_4043_B != null)) {
                if (this.w_1484_f.t_4043_B != null) {
                    u_2877_K.n_1700_B(this.w_1484_f);
                    if (this.w_1484_f.t_4043_B instanceof u_2877_K) {
                        u_2877_K resourceloadprogressgui = (u_2877_K)this.w_1484_f.t_4043_B;
                        resourceloadprogressgui.J_1907_R();
                    }
                }
                boolean bl = this.N_2525_X = this.w_1484_f.t_4043_B != null;
            }
            if (this.w_1484_f.t_4043_B != null) {
                try {
                    this.w_1484_f.t_4043_B.render(matrixstack, i, j, this.w_1484_f.f_4016_n());
                }
                catch (Throwable throwable1) {
                    n_3236_c crashreport = n_3236_c.n_1700_B(throwable1, "Rendering overlay");
                    CrashReportCategory crashreportcategory = crashreport.n_1700_B("Overlay render details");
                    crashreportcategory.n_1700_B("Overlay name", () -> this.w_1484_f.t_4043_B.getClass().getCanonicalName());
                    throw new ReportedException(crashreport);
                }
            }
            if (this.w_1484_f.Y_1740_V != null) {
                try {
                    if (Reflector.ForgeHooksClient_drawScreen.exists()) {
                        Reflector.callVoid(Reflector.ForgeHooksClient_drawScreen, this.w_1484_f.Y_1740_V, matrixstack, i, j, Float.valueOf(this.w_1484_f.f_4016_n()));
                    } else {
                        this.w_1484_f.Y_1740_V.render(matrixstack, i, j, this.w_1484_f.f_4016_n());
                    }
                }
                catch (Throwable throwable1) {
                    n_3236_c crashreport1 = n_3236_c.n_1700_B(throwable1, "Rendering screen");
                    CrashReportCategory crashreportcategory1 = crashreport1.n_1700_B("Screen render details");
                    crashreportcategory1.n_1700_B("Screen name", () -> this.w_1484_f.Y_1740_V.getClass().getCanonicalName());
                    crashreportcategory1.n_1700_B("Mouse location", () -> String.format(Locale.ROOT, "Scaled: (%d, %d). Absolute: (%f, %f)", i, j, this.w_1484_f.h_1847_R.G_564_y(), this.w_1484_f.h_1847_R.P_1922_E()));
                    crashreportcategory1.n_1700_B("Screen size", () -> String.format(Locale.ROOT, "Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %f", this.w_1484_f.RealmsServerPing().Q_4569_t(), this.w_1484_f.RealmsServerPing().M_182_A(), this.w_1484_f.RealmsServerPing().u_2550_I(), this.w_1484_f.RealmsServerPing().M_588_G(), this.w_1484_f.RealmsServerPing().w_1457_N()));
                    throw new ReportedException(crashreport1);
                }
            }
            this.C_2741_M.n_1700_B(true);
        }
        this.Y_259_p();
        this.w_1457_N();
        MemoryMonitor.update();
        Lagometer.updateLagometer();
        if (this.w_1484_f.P_4830_p.u_55_V) {
            this.w_1484_f.P_4830_p.RowButton = true;
        }
    }

    private void t_1786_h() {
        if (this.w_1484_f.u_1723_Y.w_1484_f() > 10 && this.w_1484_f.u_1723_Y.P_4830_p() && !this.w_1484_f.n_3318_d().Z_875_P()) {
            i_2518_W nativeimage = x_3498_p.n_1700_B(this.w_1484_f.RealmsServerPing().u_2550_I(), this.w_1484_f.RealmsServerPing().M_588_G(), this.w_1484_f.G_564_y());
            j_3341_s.v_4262_N().execute(() -> {
                int i = nativeimage.n_1700_B();
                int j = nativeimage.J_1907_R();
                int k = 0;
                int l = 0;
                if (i > j) {
                    k = (i - j) / 2;
                    i = j;
                } else {
                    l = (j - i) / 2;
                    j = i;
                }
                try (i_2518_W nativeimage1 = new i_2518_W(64, 64, false);){
                    nativeimage.n_1700_B(k, l, i, j, nativeimage1);
                    nativeimage1.n_1700_B(this.w_1484_f.n_3318_d().c_3005_b());
                }
                catch (IOException ioexception1) {
                    G_564_y.warn("Couldn't save auto screenshot", (Throwable)ioexception1);
                }
                finally {
                    nativeimage.close();
                }
            });
        }
    }

    private boolean multiplayerClientSuggestionProvider() {
        boolean flag;
        if (!this.Y_601_j) {
            return false;
        }
        n_1700_B bot1 = this.Q_4569_t();
        N_4263_v entity = this.w_1484_f.g_2268_R();
        boolean bl = flag = entity instanceof a_3913_L && !this.w_1484_f.P_4830_p.RetryCallException;
        if (flag && !((a_3913_L)entity).C_415_h.P_1922_E) {
            Z_1993_T itemstack = ((r_4811_B)entity).A_2714_y();
            HitResult raytraceresult = this.w_1484_f.Z_875_P;
            if (raytraceresult != null && raytraceresult.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
                K_4074_S blockstate;
                c_1514_x blockpos = ((BlockHitResult)raytraceresult).n_1700_B();
                K_4074_S k_4074_S = blockstate = bot1 != null ? bot1.P_1922_E.G_564_y().getBlockState(blockpos) : this.w_1484_f.Y_601_j.getBlockState(blockpos);
                if (bot1 != null) {
                    if (bot1.P_1922_E.C_2741_M.P_4830_p() != I_14_v.P_1922_E) {
                        BlockInWorld cachedblockinfo = new BlockInWorld(bot1.P_1922_E.G_564_y(), blockpos, false);
                        flag = !itemstack.n_1700_B() && (itemstack.n_1700_B(bot1.P_1922_E.G_564_y().M_182_A(), cachedblockinfo) || itemstack.J_1907_R(bot1.P_1922_E.G_564_y().M_182_A(), cachedblockinfo));
                        return flag;
                    }
                    flag = blockstate.J_1907_R(bot1.P_1922_E.G_564_y(), blockpos) != null;
                    return flag;
                }
                if (this.w_1484_f.w_1457_N != null && this.w_1484_f.w_1457_N.getCurrentGameType() == I_14_v.P_1922_E) {
                    flag = blockstate.J_1907_R(this.w_1484_f.Y_601_j, blockpos) != null;
                } else {
                    BlockInWorld cachedblockinfo = new BlockInWorld(this.w_1484_f.Y_601_j, blockpos, false);
                    flag = !itemstack.n_1700_B() && (itemstack.n_1700_B(this.w_1484_f.Y_601_j.M_182_A(), cachedblockinfo) || itemstack.J_1907_R(this.w_1484_f.Y_601_j.M_182_A(), cachedblockinfo));
                }
            }
        }
        return flag;
    }

    public void n_1700_B(float partialTicks, long finishTimeNano, g_221_o matrixStackIn) {
        boolean hasNausea;
        float f;
        n_1700_B bot1;
        this.C_2741_M.n_1700_B(partialTicks);
        if (this.w_1484_f.g_2268_R() == null) {
            this.w_1484_f.n_1700_B(this.w_1484_f.Y_259_p);
        }
        this.J_1907_R(partialTicks);
        this.R_4764_Y(partialTicks);
        if (Config.isShaders()) {
            Shaders.beginRender(this.w_1484_f, this.G_624_v, partialTicks, finishTimeNano);
        }
        this.w_1484_f.PlayerInfo().n_1700_B("center");
        boolean flag = Config.isShaders();
        if (flag) {
            Shaders.beginRenderPass(partialTicks, finishTimeNano);
        }
        boolean flag1 = this.multiplayerClientSuggestionProvider();
        this.w_1484_f.PlayerInfo().J_1907_R("camera");
        h_3572_K activerenderinfo = this.G_624_v;
        this.u_2550_I = this.w_1484_f.P_4830_p.J_1907_R * 16;
        if (Config.isFogFancy()) {
            this.u_2550_I *= 0.95f;
        }
        if (Config.isFogFast()) {
            this.u_2550_I *= 0.83f;
        }
        g_221_o matrixstack = new g_221_o();
        matrixstack.R_4764_Y().n_1700_B().n_1700_B(this.J_1907_R(activerenderinfo, partialTicks, true));
        g_221_o matrixstack1 = matrixstack;
        if (Shaders.isEffectsModelView()) {
            matrixstack = matrixStackIn;
        }
        this.n_1700_B(matrixstack, partialTicks);
        if (this.w_1484_f.P_4830_p.T_3594_S) {
            this.J_1907_R(matrixstack, partialTicks);
        }
        if ((bot1 = this.Q_4569_t()) != null && this.w_1484_f.T_3594_S() != null) {
            Z_875_P botPlayer = this.w_1484_f.T_3594_S();
            f = u_530_F.v_4262_N(partialTicks, botPlayer.t_4043_B, botPlayer.Y_1740_V) * this.w_1484_f.P_4830_p.RealmsScreenWithCallback * this.w_1484_f.P_4830_p.RealmsScreenWithCallback;
            hasNausea = botPlayer.J_1907_R(MobEffects.t_148_a);
        } else {
            f = u_530_F.v_4262_N(partialTicks, this.w_1484_f.Y_259_p.P_4830_p, this.w_1484_f.Y_259_p.M_588_G) * this.w_1484_f.P_4830_p.RealmsScreenWithCallback * this.w_1484_f.P_4830_p.RealmsScreenWithCallback;
            hasNausea = this.w_1484_f.Y_259_p.J_1907_R(MobEffects.t_148_a);
        }
        if (f > 0.0f) {
            int i = hasNausea ? 7 : 20;
            float f1 = 5.0f / (f * f + 5.0f) - f * 0.04f;
            f1 *= f1;
            M_1336_P vector3f = new M_1336_P(0.0f, u_530_F.n_1700_B / 2.0f, u_530_F.n_1700_B / 2.0f);
            matrixstack.n_1700_B(vector3f.R_4764_Y(((float)this.h_1847_R + partialTicks) * (float)i));
            matrixstack.n_1700_B(1.0f / f1, 1.0f, 1.0f);
            float f2 = -((float)this.h_1847_R + partialTicks) * (float)i;
            matrixstack.n_1700_B(vector3f.R_4764_Y(f2));
        }
        if (Shaders.isEffectsModelView()) {
            matrixstack = matrixstack1;
        }
        D_1098_v matrix4f = matrixstack.R_4764_Y().n_1700_B();
        this.n_1700_B(matrix4f);
        k_4690_i world = bot1 != null && bot1.P_1922_E != null ? bot1.P_1922_E.G_564_y() : this.w_1484_f.Y_601_j;
        activerenderinfo.n_1700_B(world, bot1 != null ? this.w_1484_f.T_3594_S() : this.w_1484_f.Y_259_p, !this.w_1484_f.P_4830_p.P_4830_p().n_1700_B(), this.w_1484_f.P_4830_p.P_4830_p().J_1907_R(), partialTicks);
        if (Reflector.ForgeHooksClient_onCameraSetup.exists()) {
            Object object = Reflector.ForgeHooksClient_onCameraSetup.call(this, activerenderinfo, Float.valueOf(partialTicks));
            float f4 = Reflector.callFloat(object, Reflector.EntityViewRenderEvent_CameraSetup_getYaw, new Object[0]);
            float f5 = Reflector.callFloat(object, Reflector.EntityViewRenderEvent_CameraSetup_getPitch, new Object[0]);
            float f3 = Reflector.callFloat(object, Reflector.EntityViewRenderEvent_CameraSetup_getRoll, new Object[0]);
            activerenderinfo.J_1907_R(f4, f5);
            matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f3));
        }
        matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(activerenderinfo.G_564_y()));
        matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(activerenderinfo.P_1922_E() + 180.0f));
        this.w_1484_f.u_1723_Y.n_1700_B(matrixStackIn, partialTicks, finishTimeNano, flag1, activerenderinfo, this, this.C_2741_M, matrix4f);
        if (Reflector.ForgeHooksClient_dispatchRenderLast.exists()) {
            this.w_1484_f.PlayerInfo().J_1907_R("forge_render_last");
            Reflector.callVoid(Reflector.ForgeHooksClient_dispatchRenderLast, this.w_1484_f.u_1723_Y, matrixStackIn, Float.valueOf(partialTicks), matrix4f, finishTimeNano);
        }
        this.w_1484_f.PlayerInfo().J_1907_R("hand");
        if (this.w_1457_N && !Shaders.isShadowPass) {
            if (flag) {
                ShadersRender.renderHand1(this, matrixStackIn, activerenderinfo, partialTicks);
                Shaders.renderCompositeFinal();
            }
            c_4037_x.n_1700_B(256, MinecraftClient.n_1700_B);
            if (flag) {
                ShadersRender.renderFPOverlay(this, matrixStackIn, activerenderinfo, partialTicks);
            } else {
                this.n_1700_B(matrixStackIn, activerenderinfo, partialTicks);
            }
        }
        if (flag) {
            Shaders.endRender();
        }
        this.w_1484_f.PlayerInfo().R_4764_Y();
    }

    public void w_1484_f() {
        this.t_4043_B = null;
        this.M_588_G.n_1700_B();
        this.G_624_v.Q_4569_t();
    }

    public t_3340_s t_148_a() {
        return this.M_588_G;
    }

    private void w_1457_N() {
        this.g_164_R = 0;
        if (Config.isSmoothWorld() && Config.isSingleProcessor()) {
            R_3197_Z integratedserver;
            if (this.w_1484_f.x_607_J() && (integratedserver = this.w_1484_f.n_3318_d()) != null) {
                boolean flag = this.w_1484_f.g_164_R();
                if (!flag && !(this.w_1484_f.Y_1740_V instanceof G_2460_P)) {
                    if (this.B_1668_F > 0) {
                        Lagometer.timerServer.start();
                        Config.sleep(this.B_1668_F);
                        Lagometer.timerServer.end();
                        this.g_164_R = this.B_1668_F;
                    }
                    long i = System.nanoTime() / 1000000L;
                    if (this.g_221_o != 0L && this.e_2887_G != 0) {
                        long j = i - this.g_221_o;
                        if (j < 0L) {
                            this.g_221_o = i;
                            j = 0L;
                        }
                        if (j >= 50L) {
                            this.g_221_o = i;
                            int k = integratedserver.e_1992_r();
                            int l = k - this.e_2887_G;
                            if (l < 0) {
                                this.e_2887_G = k;
                                l = 0;
                            }
                            if (l < 1 && this.B_1668_F < 100) {
                                this.B_1668_F += 2;
                            }
                            if (l > 1 && this.B_1668_F > 0) {
                                --this.B_1668_F;
                            }
                            this.e_2887_G = k;
                        }
                    } else {
                        this.g_221_o = i;
                        this.e_2887_G = integratedserver.e_1992_r();
                        this.Z_976_R = 1.0f;
                        this.X_933_l = 50.0f;
                    }
                } else {
                    if (this.w_1484_f.Y_1740_V instanceof G_2460_P) {
                        Config.sleep(20L);
                    }
                    this.g_221_o = 0L;
                    this.e_2887_G = 0;
                }
            }
        } else {
            this.g_221_o = 0L;
            this.e_2887_G = 0;
        }
    }

    private void Y_601_j() {
        n_1700_B bot1;
        k_4690_i world;
        Config.frameStart();
        GlErrors.frameStart();
        if (!this.T_2506_i) {
            ReflectorResolver.resolve();
            if (Config.getBitsOs() == 64 && Config.getBitsJre() == 32) {
                Config.setNotify64BitJava(true);
            }
            this.T_2506_i = true;
        }
        b_4507_u b_4507_u2 = world = (bot1 = this.Q_4569_t()) != null && bot1.P_1922_E != null ? bot1.P_1922_E.G_564_y() : this.w_1484_f.Y_601_j;
        if (world != null) {
            if (Config.getNewRelease() != null) {
                String s = "HD_U".replace("HD_U", "HD Ultra").replace("L", "Light");
                String s1 = s + " " + Config.getNewRelease();
                U_2871_b stringtextcomponent = new U_2871_b(K_1289_S.n_1700_B("of.message.newVersion", "\u00a7n" + s1 + "\u00a7r"));
                stringtextcomponent.n_1700_B(Z_1567_W.n_1700_B.n_1700_B(new i_2909_p(i_2909_p.n_1700_B.n_1700_B, "https://optifine.net/downloads")));
                this.w_1484_f.M_588_G.R_4764_Y().n_1700_B(stringtextcomponent);
                Config.setNewRelease(null);
            }
            if (Config.isNotify64BitJava()) {
                Config.setNotify64BitJava(false);
                U_2871_b stringtextcomponent1 = new U_2871_b(K_1289_S.n_1700_B("of.message.java64Bit", new Object[0]));
                this.w_1484_f.M_588_G.R_4764_Y().n_1700_B(stringtextcomponent1);
            }
        }
        if (this.w_1484_f.Y_1740_V instanceof k_596_g) {
            this.n_1700_B((k_596_g)this.w_1484_f.Y_1740_V);
        }
        if (this.q_4610_l != world) {
            RandomEntities.worldChanged(this.q_4610_l, world);
            Config.updateThreadPriorities();
            this.g_221_o = 0L;
            this.e_2887_G = 0;
            this.q_4610_l = world;
            lightning.product.A_4115_X.n_1700_B(new E_3343_g(E_3343_g.n_1700_B.J_1907_R));
        }
        if (!this.n_1700_B(Shaders.configAntialiasingLevel)) {
            Shaders.configAntialiasingLevel = 0;
        }
        if (this.w_1484_f.Y_1740_V != null && this.w_1484_f.Y_1740_V.getClass() == h_4412_P.class) {
            this.w_1484_f.n_1700_B(new GuiChatOF((h_4412_P)this.w_1484_f.Y_1740_V));
        }
    }

    private void Y_259_p() {
        n_1700_B bot1 = this.Q_4569_t();
        if (bot1 != null ? bot1.P_1922_E.G_564_y() == null : this.w_1484_f.Y_601_j == null || !Config.isShowGlErrors() || !TimedEvent.isActive("CheckGlErrorFrameFinish", 10000L)) {
            return;
        }
        int i = lightning.product.X_933_l.g_164_R();
        if (i != 0 && GlErrors.isEnabled(i)) {
            String s = Config.getGlErrorString(i);
            U_2871_b stringtextcomponent = new U_2871_b(K_1289_S.n_1700_B("of.message.openglError", i, s));
            this.w_1484_f.M_588_G.R_4764_Y().n_1700_B(stringtextcomponent);
        }
    }

    private void n_1700_B(k_596_g p_updateMainMenu_1_) {
        try {
            String s = null;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int i = calendar.get(5);
            int j = calendar.get(2) + 1;
            if (i == 8 && j == 4) {
                s = "Happy birthday, OptiFine!";
            }
            if (i == 14 && j == 8) {
                s = "Happy birthday, sp614x!";
            }
            if (s == null) {
                return;
            }
            Reflector.setFieldValue(p_updateMainMenu_1_, Reflector.GuiMainMenu_splashText, s);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public boolean n_1700_B(int p_setFxaaShader_1_) {
        n_1700_B bot1 = this.Q_4569_t();
        if (!lightning.product.g_164_R.v_4262_N()) {
            return false;
        }
        if (this.d_2427_y != null && this.d_2427_y != this.H_1990_U[2] && this.d_2427_y != this.H_1990_U[4]) {
            return true;
        }
        if (p_setFxaaShader_1_ != 2 && p_setFxaaShader_1_ != 4) {
            if (this.d_2427_y == null) {
                return true;
            }
            this.d_2427_y.close();
            this.d_2427_y = null;
            return true;
        }
        if (this.d_2427_y != null && this.d_2427_y == this.H_1990_U[p_setFxaaShader_1_]) {
            return true;
        }
        if (bot1 != null ? bot1.P_1922_E.G_564_y() == null : this.w_1484_f.Y_601_j == null) {
            return true;
        }
        this.n_1700_B(new g_2336_b("shaders/post/fxaa_of_" + p_setFxaaShader_1_ + "x.json"));
        this.H_1990_U[p_setFxaaShader_1_] = this.d_2427_y;
        return this.d_2461_k;
    }

    public IResourceType s_956_w() {
        return VanillaResourceType.SHADERS;
    }

    public void n_1700_B(Z_1993_T stack) {
        this.t_4043_B = stack;
        this.x_607_J = 40;
        this.e_4240_b = this.s_956_w.nextFloat() * 2.0f - 1.0f;
        this.n_3318_d = this.s_956_w.nextFloat() * 2.0f - 1.0f;
    }

    private void n_1700_B(int widthsp, int heightScaled, float partialTicks) {
        if (this.t_4043_B != null && this.x_607_J > 0) {
            int i = 40 - this.x_607_J;
            float f = ((float)i + partialTicks) / 40.0f;
            float f1 = f * f;
            float f2 = f * f1;
            float f3 = 10.25f * f2 * f1 - 24.95f * f1 * f1 + 25.5f * f2 - 13.8f * f1 + 4.0f * f;
            float f4 = f3 * (float)Math.PI;
            float f5 = this.e_4240_b * (float)(widthsp / 4);
            float f6 = this.n_3318_d * (float)(heightScaled / 4);
            c_4037_x.M_588_G();
            c_4037_x.v_4276_D();
            c_4037_x.w_1484_f();
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.q_2307_F();
            g_221_o matrixstack = new g_221_o();
            matrixstack.n_1700_B();
            matrixstack.n_1700_B((double)((float)(widthsp / 2) + f5 * u_530_F.P_1922_E(u_530_F.n_1700_B(f4 * 2.0f))), (double)((float)(heightScaled / 2) + f6 * u_530_F.P_1922_E(u_530_F.n_1700_B(f4 * 2.0f))), -50.0);
            float f7 = 50.0f + 175.0f * u_530_F.n_1700_B(f4);
            matrixstack.n_1700_B(f7, -f7, f7);
            matrixstack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(900.0f * u_530_F.P_1922_E(u_530_F.n_1700_B(f4))));
            matrixstack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(6.0f * u_530_F.J_1907_R(f * 8.0f)));
            matrixstack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(6.0f * u_530_F.J_1907_R(f * 8.0f)));
            o_3091_w.n_1700_B irendertypebuffer$impl = this.P_4830_p.J_1907_R();
            this.w_1484_f.r_715_M().n_1700_B(this.t_4043_B, ItemTransforms.J_1907_R.t_148_a, 0xF000F0, Z_3224_L.n_1700_B, matrixstack, irendertypebuffer$impl);
            matrixstack.J_1907_R();
            irendertypebuffer$impl.J_1907_R();
            c_4037_x.s_956_w();
            c_4037_x.d_2461_k();
            c_4037_x.k_2293_S();
            c_4037_x.t_1786_h();
        }
    }

    private void P_1922_E(float p_243497_1_) {
        int i = this.w_1484_f.RealmsServerPing().Q_4569_t();
        int j = this.w_1484_f.RealmsServerPing().M_182_A();
        double d0 = u_530_F.G_564_y((double)p_243497_1_, 2.0, 1.0);
        float f = 0.2f * p_243497_1_;
        float f1 = 0.4f * p_243497_1_;
        float f2 = 0.2f * p_243497_1_;
        double d1 = (double)i * d0;
        double d2 = (double)j * d0;
        double d3 = ((double)i - d1) / 2.0;
        double d4 = ((double)j - d2) / 2.0;
        c_4037_x.t_1786_h();
        c_4037_x.J_1907_R(false);
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.P_1922_E, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.P_1922_E);
        c_4037_x.G_564_y(f, f1, f2, 1.0f);
        this.w_1484_f.G_624_v().n_1700_B(R_4764_Y);
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.Q_2552_b);
        bufferbuilder.pos(d3, d4 + d2, -90.0).tex(0.0f, 1.0f).endVertex();
        bufferbuilder.pos(d3 + d1, d4 + d2, -90.0).tex(1.0f, 1.0f).endVertex();
        bufferbuilder.pos(d3 + d1, d4, -90.0).tex(1.0f, 0.0f).endVertex();
        bufferbuilder.pos(d3, d4, -90.0).tex(0.0f, 0.0f).endVertex();
        tessellator.J_1907_R();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.s_2632_s();
        c_4037_x.Y_259_p();
        c_4037_x.J_1907_R(true);
        c_4037_x.multiplayerClientSuggestionProvider();
    }

    public float G_564_y(float partialTicks) {
        return u_530_F.v_4262_N(partialTicks, this.multiplayerClientSuggestionProvider, this.t_1786_h);
    }

    public float u_2550_I() {
        return this.u_2550_I;
    }

    public h_3572_K M_588_G() {
        return this.G_624_v;
    }

    public e_1689_x P_4830_p() {
        return this.C_2741_M;
    }

    public Z_3224_L h_1847_R() {
        return this.k_2293_S;
    }
}



