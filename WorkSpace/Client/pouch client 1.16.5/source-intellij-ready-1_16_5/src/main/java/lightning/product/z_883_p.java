/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.google.gson.JsonSyntaxException
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet
 *  it.unimi.dsi.fastutil.objects.ObjectList
 *  it.unimi.dsi.fastutil.objects.ObjectListIterator
 *  javax.annotation.Nullable
 *  lombok.Generated
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonSyntaxException;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.FluidTags;
import lightning.product.B_1647_r;
import lightning.product.OutlineBufferSource;
import lightning.product.BoneMealItem;
import lightning.product.C_3240_x;
import lightning.product.C_4998_y;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.D_38_f;
import lightning.product.D_4792_h;
import lightning.product.D_4883_k;
import lightning.product.E_270_p;
import lightning.product.E_4918_z;
import lightning.product.E_688_b;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.H_1748_a;
import lightning.product.HitResult;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.ResourceManagerReloadListener;
import lightning.product.J_4125_o;
import lightning.product.DimensionSpecialEffects;
import lightning.product.K_2069_m;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.L_3848_p;
import lightning.product.M_1336_P;
import lightning.product.M_660_m;
import lightning.product.N_3869_i;
import lightning.product.N_4263_v;
import lightning.product.O_1806_w;
import lightning.product.P_3084_J;
import lightning.product.P_4249_L;
import lightning.product.ResourceManager;
import lightning.product.ParticleOptions;
import lightning.product.T_2915_h;
import lightning.product.SimpleSoundInstance;
import lightning.product.T_603_v;
import lightning.product.U_2871_b;
import lightning.product.RenderBuffers;
import lightning.product.V_3137_a;
import lightning.product.SoundEvents;
import lightning.product.V_772_m;
import lightning.product.W_3265_k;
import lightning.product.W_3801_h;
import lightning.product.SoundEvent;
import lightning.product.W_571_B;
import lightning.product.CollisionContext;
import lightning.product.ProfilerFiller;
import lightning.product.X_4340_E;
import lightning.product.X_933_l;
import lightning.product.SoundInstance;
import lightning.product.BlockAndTintGetter;
import lightning.product.Z_1993_T;
import lightning.product.Z_2491_A;
import lightning.product.Z_875_P;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.a_648_i;
import lightning.product.b_1213_w;
import lightning.product.b_257_Y;
import lightning.product.b_4440_Q;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.c_3005_b;
import lightning.product.c_3457_g;
import lightning.product.c_4037_x;
import lightning.product.SoundType;
import lightning.product.d_1620_j;
import lightning.product.BlockOverlay;
import lightning.product.e_1689_x;
import lightning.product.e_2866_D;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.g_2561_p;
import lightning.product.h_3036_f;
import lightning.product.h_3270_j;
import lightning.product.h_3572_K;
import lightning.product.i_2154_H;
import lightning.product.BlockDestructionProgress;
import lightning.product.j_3341_s;
import lightning.product.j_39_h;
import lightning.product.j_4067_x;
import lightning.product.Ambience;
import lightning.product.k_4690_i;
import lightning.product.k_594_Q;
import lightning.product.l_1233_K;
import lightning.product.l_3747_P;
import lightning.product.SimpleParticleType;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.ClientBootstrap;
import lightning.product.o_2576_A;
import lightning.product.o_2840_r;
import lightning.product.o_3091_w;
import lightning.product.p_2951_v;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.CrashReportCategory;
import lightning.product.r_4399_U;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.Vector3d;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.Chams;
import lightning.product.Cosmetics;
import lightning.product.w_2040_b;
import lightning.product.x_2151_q;
import lightning.product.x_268_Y;
import lightning.product.y_254_d;
import lightning.product.RunningTrimmedMean;
import lightning.product.z_1333_t;
import lightning.product.z_2963_s;
import lightning.product.z_3539_x;
import lightning.product.z_4547_I;
import lombok.Generated;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.event.events.RenderEvent;
import net.minecraftforge.client.ICloudRenderHandler;
import net.minecraftforge.client.ISkyRenderHandler;
import net.minecraftforge.client.IWeatherParticleRenderHandler;
import net.minecraftforge.client.IWeatherRenderHandler;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.VanillaResourceType;
import net.optifine.Config;
import net.optifine.CustomColors;
import net.optifine.CustomSky;
import net.optifine.DynamicLights;
import net.optifine.Lagometer;
import net.optifine.SmartAnimations;
import net.optifine.entity.model.CustomEntityModels;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.render.ChunkVisibility;
import net.optifine.render.RenderEnv;
import net.optifine.render.RenderStateManager;
import net.optifine.render.RenderUtils;
import net.optifine.render.VboRegion;
import net.optifine.shaders.RenderStage;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;
import net.optifine.shaders.ShadowUtils;
import net.optifine.util.BiomeUtils;
import net.optifine.util.ChunkUtils;
import net.optifine.util.MathUtils;
import net.optifine.util.PairInt;
import net.optifine.util.RenderChunkUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class z_883_p
implements AutoCloseable,
ResourceManagerReloadListener {
    private static final Logger s_956_w = LogManager.getLogger();
    private static final g_2336_b u_2550_I = new g_2336_b("textures/environment/moon_phases.png");
    private static final g_2336_b M_588_G = new g_2336_b("textures/environment/sun.png");
    private static final g_2336_b P_4830_p = new g_2336_b("textures/environment/clouds.png");
    private static final g_2336_b h_1847_R = new g_2336_b("textures/environment/end_sky.png");
    private static final g_2336_b Q_4569_t = new g_2336_b("textures/misc/forcefield.png");
    private static final g_2336_b M_182_A = new g_2336_b("textures/environment/rain.png");
    private static final g_2336_b t_1786_h = new g_2336_b("textures/environment/snow.png");
    public static final b_257_Y[] n_1700_B = b_257_Y.values();
    private final MinecraftClient multiplayerClientSuggestionProvider;
    private final C_3240_x w_1457_N;
    private final w_2040_b Y_601_j;
    private final RenderBuffers Y_259_p;
    private b_4507_u Q_2552_b;
    public static E_4918_z J_1907_R;
    private Set<z_4547_I.n_1700_B> C_2741_M = new ObjectLinkedOpenHashSet();
    private ObjectList<n_1700_B> k_2293_S = new ObjectArrayList(69696);
    private final Set<i_2154_H> q_2307_F = Sets.newHashSet();
    private B_1647_r Z_875_P;
    private final b_1213_w c_3005_b = E_688_b.w_1457_N;
    @Nullable
    private D_4883_k H_2857_Y;
    @Nullable
    private D_4883_k A_4115_X;
    @Nullable
    private D_4883_k Y_1740_V;
    private boolean t_4043_B = true;
    @Nullable
    private D_4883_k x_607_J;
    private final RunningTrimmedMean e_4240_b = new RunningTrimmedMean(100);
    private int n_3318_d;
    private final Int2ObjectMap<BlockDestructionProgress> d_2427_y = new Int2ObjectOpenHashMap();
    private final Long2ObjectMap<SortedSet<BlockDestructionProgress>> z_1737_N = new Long2ObjectOpenHashMap();
    private final Map<c_1514_x, SoundInstance> v_4276_D = Maps.newHashMap();
    @Nullable
    private P_4249_L d_2461_k;
    @Nullable
    private x_2151_q G_624_v;
    @Nullable
    private P_4249_L T_2506_i;
    @Nullable
    private P_4249_L q_4610_l;
    @Nullable
    private P_4249_L z_4693_k;
    @Nullable
    private P_4249_L g_221_o;
    @Nullable
    private P_4249_L e_2887_G;
    @Nullable
    private x_2151_q B_1668_F;
    private double g_164_R = Double.MIN_VALUE;
    private double X_933_l = Double.MIN_VALUE;
    private double Z_976_R = Double.MIN_VALUE;
    private int H_1990_U = Integer.MIN_VALUE;
    private int N_2525_X = Integer.MIN_VALUE;
    private int c_4037_x = Integer.MIN_VALUE;
    private double g_2268_R = Double.MIN_VALUE;
    private double T_3594_S = Double.MIN_VALUE;
    private double D_4792_h = Double.MIN_VALUE;
    private double s_2632_s = Double.MIN_VALUE;
    private double l_1233_K = Double.MIN_VALUE;
    private int z_1333_t = Integer.MIN_VALUE;
    private int O_508_d = Integer.MIN_VALUE;
    private int r_715_M = Integer.MIN_VALUE;
    private e_2866_D A_1038_p = e_2866_D.n_1700_B;
    private K_2069_m i_1637_u;
    private z_4547_I Ping;
    private final b_1213_w p_178_J = E_688_b.w_1484_f;
    private int RealmsClientConfig = -1;
    private int f_4016_n;
    private int j_276_v;
    private boolean UploadStatus;
    @Nullable
    private E_4918_z e_1992_r;
    private final Z_2491_A[] D_60_a = new Z_2491_A[8];
    private final Vector3d k_3961_g = new Vector3d(0.0, 0.0, 0.0);
    private double Ops;
    private double h_4320_q;
    private double t_4219_U;
    private boolean V_1446_Y = true;
    private int PlayerInfo;
    private int V_1225_t;
    private final float[] U_1241_n = new float[1024];
    private final float[] q_1982_R = new float[1024];
    public N_4263_v R_4764_Y;
    public Set G_564_y = new LinkedHashSet();
    public Set P_1922_E = new LinkedHashSet();
    private Set<z_4547_I.n_1700_B> dtoRealmsServerAddress = new ObjectLinkedOpenHashSet();
    private Deque w_612_n = new ArrayDeque();
    private List<n_1700_B> RealmsServerPing = new ArrayList<n_1700_B>(1024);
    private List<n_1700_B> j_1564_a = new ArrayList<n_1700_B>(1024);
    private ObjectList M_1641_O = new ObjectArrayList(1024);
    private List RealmsWorldOptions = new ArrayList(1024);
    private List RealmsWorldResetDto = new ArrayList(1024);
    private ObjectList RegionPingResult = new ObjectArrayList(1024);
    private List H_1083_k = new ArrayList(1024);
    private List R_3908_n = new ArrayList(1024);
    private int ValueObject = 0;
    private int F_1410_V = 0;
    private static final Set S_4022_R;
    private int l_4537_E;
    private int F_2624_D = 0;
    private RenderEnv RealmsDefaultUncaughtExceptionHandler = new RenderEnv(a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), new c_1514_x(0, 0, 0));
    public boolean u_1723_Y = false;
    public boolean v_4262_N = false;
    private boolean y_1700_S = false;
    private static int u_744_e;
    public int w_1484_f = -1;
    public static final int t_148_a = 201435902;
    private static boolean RetryCallException;
    private Map<String, List<N_4263_v>> r_3651_U = new HashMap<String, List<N_4263_v>>();
    private Map<o_2576_A, Map> RowButton = new LinkedHashMap<o_2576_A, Map>();
    private lightning.product.n_1700_B LongRunningTask = null;
    private N_4263_v j_2266_I = null;
    private E_4918_z S_980_j;

    private lightning.product.n_1700_B d_2461_k() {
        N_4263_v currentRenderView = this.multiplayerClientSuggestionProvider.C_2741_M;
        if (currentRenderView != this.j_2266_I) {
            this.j_2266_I = currentRenderView;
            this.LongRunningTask = null;
            if (lightning.product.J_1907_R.n_1700_B != null && !lightning.product.J_1907_R.n_1700_B.isEmpty()) {
                for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                    if (bot == null || bot.P_1922_E == null || bot.P_1922_E.Q_2552_b != currentRenderView) continue;
                    this.LongRunningTask = bot;
                    break;
                }
            }
        }
        if (this.LongRunningTask != null && (this.LongRunningTask.P_1922_E == null || this.LongRunningTask.P_1922_E.Q_2552_b != currentRenderView)) {
            this.LongRunningTask = null;
        }
        return this.LongRunningTask;
    }

    private b_4507_u G_624_v() {
        lightning.product.n_1700_B bot = this.d_2461_k();
        return bot != null && bot.P_1922_E != null ? bot.P_1922_E.G_564_y() : this.multiplayerClientSuggestionProvider.Y_601_j;
    }

    private <T> T n_1700_B(b_4507_u world, Function<k_4690_i, T> clientWorldFunc, Function<c_3005_b, T> botWorldFunc) {
        if (world instanceof k_4690_i) {
            return clientWorldFunc.apply((k_4690_i)world);
        }
        if (world instanceof c_3005_b) {
            return botWorldFunc.apply((c_3005_b)world);
        }
        return null;
    }

    private void n_1700_B(c_1514_x pos, SoundEvent soundIn, D_38_f category, float volume, float pitch, boolean distanceDelay) {
        if (this.Q_2552_b instanceof k_4690_i) {
            ((k_4690_i)this.Q_2552_b).n_1700_B(pos, soundIn, category, volume, pitch, distanceDelay);
        } else if (this.Q_2552_b instanceof c_3005_b) {
            ((c_3005_b)this.Q_2552_b).n_1700_B(pos, soundIn, category, volume, pitch, distanceDelay);
        }
    }

    private void n_1700_B(double x, double y, double z, SoundEvent soundIn, D_38_f category, float volume, float pitch, boolean distanceDelay) {
        if (this.Q_2552_b instanceof k_4690_i) {
            ((k_4690_i)this.Q_2552_b).n_1700_B(x, y, z, soundIn, category, volume, pitch, distanceDelay);
        } else if (this.Q_2552_b instanceof c_3005_b) {
            ((c_3005_b)this.Q_2552_b).n_1700_B(x, y, z, soundIn, category, volume, pitch, distanceDelay);
        }
    }

    public z_883_p(MinecraftClient mcIn, RenderBuffers rainTimeBuffersIn) {
        this.multiplayerClientSuggestionProvider = mcIn;
        this.Y_601_j = mcIn.O_508_d();
        this.Y_259_p = rainTimeBuffersIn;
        this.w_1457_N = mcIn.G_624_v();
        for (int i = 0; i < 32; ++i) {
            for (int j = 0; j < 32; ++j) {
                float f = j - 16;
                float f1 = i - 16;
                float f2 = u_530_F.R_4764_Y(f * f + f1 * f1);
                this.U_1241_n[i << 5 | j] = -f1 / f2;
                this.q_1982_R[i << 5 | j] = f / f2;
            }
        }
        this.e_2887_G();
        this.g_221_o();
        this.z_4693_k();
    }

    private void n_1700_B(e_1689_x lightmapIn, float partialTicks, double xIn, double yIn, double zIn) {
        IWeatherRenderHandler iweatherrenderhandler;
        if (Reflector.ForgeDimensionRenderInfo_getWeatherRenderHandler.exists() && (iweatherrenderhandler = this.n_1700_B(this.Q_2552_b, (k_4690_i w) -> (IWeatherRenderHandler)Reflector.call(w.n_1700_B(), Reflector.ForgeDimensionRenderInfo_getWeatherRenderHandler, new Object[0]), (c_3005_b w) -> (IWeatherRenderHandler)Reflector.call(w.n_1700_B(), Reflector.ForgeDimensionRenderInfo_getWeatherRenderHandler, new Object[0]))) != null) {
            iweatherrenderhandler.render(this.n_3318_d, partialTicks, this.Q_2552_b, this.multiplayerClientSuggestionProvider, lightmapIn, xIn, yIn, zIn);
            return;
        }
        float f5 = this.multiplayerClientSuggestionProvider.Y_601_j.w_1484_f(partialTicks);
        b_4507_u world = this.G_624_v();
        if (world != this.multiplayerClientSuggestionProvider.Y_601_j) {
            f5 = world.w_1484_f(partialTicks);
        }
        if (!(f5 <= 0.0f)) {
            if (Config.isRainOff()) {
                return;
            }
            h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.t_148_a);
            lightning.product.A_4115_X.n_1700_B(event);
            if (event.n_1700_B()) {
                return;
            }
            lightmapIn.R_4764_Y();
            int i = u_530_F.R_4764_Y(xIn);
            int j = u_530_F.R_4764_Y(yIn);
            int k = u_530_F.R_4764_Y(zIn);
            l_3747_P tessellator = l_3747_P.n_1700_B();
            D_3318_r bufferbuilder = tessellator.R_4764_Y();
            lightning.product.c_4037_x.M_588_G();
            lightning.product.c_4037_x.q_2307_F();
            lightning.product.c_4037_x.n_1700_B(0.0f, 1.0f, 0.0f);
            lightning.product.c_4037_x.Y_601_j();
            lightning.product.c_4037_x.s_2632_s();
            lightning.product.c_4037_x.l_1233_K();
            lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
            int l = 5;
            if (Config.isRainFancy()) {
                l = 10;
            }
            lightning.product.c_4037_x.J_1907_R(MinecraftClient.c_3005_b());
            int i1 = -1;
            float f = (float)this.n_3318_d + partialTicks;
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (int j1 = k - l; j1 <= k + l; ++j1) {
                for (int k1 = i - l; k1 <= i + l; ++k1) {
                    int l1 = (j1 - k + 16) * 32 + k1 - i + 16;
                    double d0 = (double)this.U_1241_n[l1] * 0.5;
                    double d1 = (double)this.q_1982_R[l1] * 0.5;
                    blockpos$mutable.n_1700_B(k1, 0, j1);
                    k_594_Q biome = world.P_1922_E(blockpos$mutable);
                    if (biome.R_4764_Y() == k_594_Q.P_1922_E.n_1700_B) continue;
                    int i2 = world.n_1700_B(z_2963_s.n_1700_B.P_1922_E, (c_1514_x)blockpos$mutable).getY();
                    int j2 = j - l;
                    int k2 = j + l;
                    if (j2 < i2) {
                        j2 = i2;
                    }
                    if (k2 < i2) {
                        k2 = i2;
                    }
                    int l2 = i2;
                    if (i2 < j) {
                        l2 = j;
                    }
                    if (j2 == k2) continue;
                    Random random = new Random(k1 * k1 * 3121 + k1 * 45238971 ^ j1 * j1 * 418711 + j1 * 13761);
                    blockpos$mutable.n_1700_B(k1, j2, j1);
                    float f1 = biome.n_1700_B(blockpos$mutable);
                    if (f1 >= 0.15f) {
                        if (i1 != 0) {
                            if (i1 >= 0) {
                                tessellator.J_1907_R();
                            }
                            i1 = 0;
                            this.multiplayerClientSuggestionProvider.G_624_v().n_1700_B(M_182_A);
                            bufferbuilder.n_1700_B(7, E_688_b.multiplayerClientSuggestionProvider);
                        }
                        int i3 = this.n_3318_d + k1 * k1 * 3121 + k1 * 45238971 + j1 * j1 * 418711 + j1 * 13761 & 0x1F;
                        float f2 = -((float)i3 + partialTicks) / 32.0f * (3.0f + random.nextFloat());
                        double d2 = (double)((float)k1 + 0.5f) - xIn;
                        double d4 = (double)((float)j1 + 0.5f) - zIn;
                        float f3 = u_530_F.n_1700_B(d2 * d2 + d4 * d4) / (float)l;
                        float f4 = ((1.0f - f3 * f3) * 0.5f + 0.5f) * f5;
                        blockpos$mutable.n_1700_B(k1, l2, j1);
                        int j3 = z_883_p.n_1700_B(world, blockpos$mutable);
                        bufferbuilder.pos((double)k1 - xIn - d0 + 0.5, (double)k2 - yIn, (double)j1 - zIn - d1 + 0.5).tex(0.0f, (float)j2 * 0.25f + f2).n_1700_B(1.0f, 1.0f, 1.0f, f4).J_1907_R(j3).endVertex();
                        bufferbuilder.pos((double)k1 - xIn + d0 + 0.5, (double)k2 - yIn, (double)j1 - zIn + d1 + 0.5).tex(1.0f, (float)j2 * 0.25f + f2).n_1700_B(1.0f, 1.0f, 1.0f, f4).J_1907_R(j3).endVertex();
                        bufferbuilder.pos((double)k1 - xIn + d0 + 0.5, (double)j2 - yIn, (double)j1 - zIn + d1 + 0.5).tex(1.0f, (float)k2 * 0.25f + f2).n_1700_B(1.0f, 1.0f, 1.0f, f4).J_1907_R(j3).endVertex();
                        bufferbuilder.pos((double)k1 - xIn - d0 + 0.5, (double)j2 - yIn, (double)j1 - zIn - d1 + 0.5).tex(0.0f, (float)k2 * 0.25f + f2).n_1700_B(1.0f, 1.0f, 1.0f, f4).J_1907_R(j3).endVertex();
                        continue;
                    }
                    if (i1 != 1) {
                        if (i1 >= 0) {
                            tessellator.J_1907_R();
                        }
                        i1 = 1;
                        this.multiplayerClientSuggestionProvider.G_624_v().n_1700_B(t_1786_h);
                        bufferbuilder.n_1700_B(7, E_688_b.multiplayerClientSuggestionProvider);
                    }
                    float f6 = -((float)(this.n_3318_d & 0x1FF) + partialTicks) / 512.0f;
                    float f7 = (float)(random.nextDouble() + (double)f * 0.01 * (double)((float)random.nextGaussian()));
                    float f8 = (float)(random.nextDouble() + (double)(f * (float)random.nextGaussian()) * 0.001);
                    double d3 = (double)((float)k1 + 0.5f) - xIn;
                    double d5 = (double)((float)j1 + 0.5f) - zIn;
                    float f9 = u_530_F.n_1700_B(d3 * d3 + d5 * d5) / (float)l;
                    float f10 = ((1.0f - f9 * f9) * 0.3f + 0.5f) * f5;
                    blockpos$mutable.n_1700_B(k1, l2, j1);
                    int k3 = z_883_p.n_1700_B(world, blockpos$mutable);
                    int l3 = k3 >> 16 & 0xFFFF;
                    int i4 = (k3 & 0xFFFF) * 3;
                    int j4 = (l3 * 3 + 240) / 4;
                    int k4 = (i4 * 3 + 240) / 4;
                    bufferbuilder.pos((double)k1 - xIn - d0 + 0.5, (double)k2 - yIn, (double)j1 - zIn - d1 + 0.5).tex(0.0f + f7, (float)j2 * 0.25f + f6 + f8).n_1700_B(1.0f, 1.0f, 1.0f, f10).lightmap(k4, j4).endVertex();
                    bufferbuilder.pos((double)k1 - xIn + d0 + 0.5, (double)k2 - yIn, (double)j1 - zIn + d1 + 0.5).tex(1.0f + f7, (float)j2 * 0.25f + f6 + f8).n_1700_B(1.0f, 1.0f, 1.0f, f10).lightmap(k4, j4).endVertex();
                    bufferbuilder.pos((double)k1 - xIn + d0 + 0.5, (double)j2 - yIn, (double)j1 - zIn + d1 + 0.5).tex(1.0f + f7, (float)k2 * 0.25f + f6 + f8).n_1700_B(1.0f, 1.0f, 1.0f, f10).lightmap(k4, j4).endVertex();
                    bufferbuilder.pos((double)k1 - xIn - d0 + 0.5, (double)j2 - yIn, (double)j1 - zIn - d1 + 0.5).tex(0.0f + f7, (float)k2 * 0.25f + f6 + f8).n_1700_B(1.0f, 1.0f, 1.0f, f10).lightmap(k4, j4).endVertex();
                }
            }
            if (i1 >= 0) {
                tessellator.J_1907_R();
            }
            lightning.product.c_4037_x.k_2293_S();
            lightning.product.c_4037_x.Y_259_p();
            lightning.product.c_4037_x.l_1233_K();
            lightning.product.c_4037_x.u_2550_I();
            lightmapIn.J_1907_R();
        }
    }

    public void n_1700_B(h_3572_K activeRenderInfoIn) {
        if (Reflector.ForgeDimensionRenderInfo_getWeatherParticleRenderHandler.exists()) {
            b_4507_u w;
            IWeatherParticleRenderHandler iweatherparticlerenderhandler = null;
            b_4507_u var4 = this.Q_2552_b;
            if (var4 instanceof k_4690_i) {
                w = (k_4690_i)var4;
                iweatherparticlerenderhandler = (IWeatherParticleRenderHandler)Reflector.call(((k_4690_i)w).n_1700_B(), Reflector.ForgeDimensionRenderInfo_getWeatherParticleRenderHandler, new Object[0]);
            }
            if ((var4 = this.Q_2552_b) instanceof c_3005_b) {
                w = (c_3005_b)var4;
                iweatherparticlerenderhandler = (IWeatherParticleRenderHandler)Reflector.call(((c_3005_b)w).n_1700_B(), Reflector.ForgeDimensionRenderInfo_getWeatherParticleRenderHandler, new Object[0]);
            }
            if (iweatherparticlerenderhandler != null) {
                iweatherparticlerenderhandler.render(this.n_3318_d, this.Q_2552_b, this.multiplayerClientSuggestionProvider, activeRenderInfoIn);
                return;
            }
        }
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.t_148_a);
        lightning.product.A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            return;
        }
        float f = this.multiplayerClientSuggestionProvider.Y_601_j.w_1484_f(1.0f) / (MinecraftClient.Z_875_P() ? 1.0f : 2.0f);
        b_4507_u currentWorld = this.G_624_v();
        if (currentWorld != this.multiplayerClientSuggestionProvider.Y_601_j) {
            f = currentWorld.w_1484_f(1.0f) / (MinecraftClient.Z_875_P() ? 1.0f : 2.0f);
        }
        b_4507_u iworldreader = currentWorld;
        if (!Config.isRainFancy()) {
            f /= 2.0f;
        }
        if (!(f <= 0.0f) && Config.isRainSplash()) {
            Random random = new Random((long)this.n_3318_d * 312987231L);
            c_1514_x blockpos = new c_1514_x(activeRenderInfoIn.J_1907_R());
            z_3539_x blockpos1 = null;
            int i = (int)(100.0f * f * f) / (this.multiplayerClientSuggestionProvider.P_4830_p.RealmsClientOutdatedScreen == j_4067_x.J_1907_R ? 2 : 1);
            lightning.product.n_1700_B activeBot = this.d_2461_k();
            boolean isBotView = activeBot != null;
            c_3005_b particleWorld = activeBot != null && activeBot.P_1922_E != null ? activeBot.P_1922_E.G_564_y() : null;
            for (int j = 0; j < i; ++j) {
                SimpleParticleType iparticledata;
                int k = random.nextInt(21) - 10;
                int l = random.nextInt(21) - 10;
                c_1514_x blockpos2 = iworldreader.n_1700_B(z_2963_s.n_1700_B.P_1922_E, blockpos.add(k, 0, l)).down();
                k_594_Q biome = iworldreader.P_1922_E(blockpos2);
                if (blockpos2.getY() <= 0 || blockpos2.getY() > blockpos.getY() + 10 || blockpos2.getY() < blockpos.getY() - 10 || biome.R_4764_Y() != k_594_Q.P_1922_E.J_1907_R || !(biome.n_1700_B(blockpos2) >= 0.15f)) continue;
                blockpos1 = blockpos2;
                if (this.multiplayerClientSuggestionProvider.P_4830_p.RealmsClientOutdatedScreen == j_4067_x.R_4764_Y) break;
                double d0 = random.nextDouble();
                double d1 = random.nextDouble();
                K_4074_S blockstate = iworldreader.getBlockState(blockpos2);
                FluidState fluidstate = iworldreader.getFluidState(blockpos2);
                s_1395_c voxelshape = blockstate.u_2550_I(iworldreader, blockpos2);
                double d2 = voxelshape.n_1700_B(b_257_Y.n_1700_B.J_1907_R, d0, d1);
                double d3 = fluidstate.n_1700_B((BlockGetter)iworldreader, blockpos2);
                double d4 = Math.max(d2, d3);
                SimpleParticleType m_917_X2 = iparticledata = !fluidstate.n_1700_B(FluidTags.R_4764_Y) && !blockstate.n_1700_B(a_3742_W.LevitationControl) && !C_4998_y.w_1484_f(blockstate) ? ParticleTypes.e_2887_G : ParticleTypes.B_1668_F;
                if (isBotView && particleWorld != null) {
                    ((b_4507_u)particleWorld).n_1700_B(iparticledata, (double)blockpos2.getX() + d0, (double)blockpos2.getY() + d4, (double)blockpos2.getZ() + d1, 0.0, 0.0, 0.0);
                }
                this.multiplayerClientSuggestionProvider.Y_601_j.n_1700_B(iparticledata, (double)blockpos2.getX() + d0, (double)blockpos2.getY() + d4, (double)blockpos2.getZ() + d1, 0.0, 0.0, 0.0);
            }
            if (blockpos1 != null && random.nextInt(3) < this.V_1225_t++) {
                this.V_1225_t = 0;
                if (blockpos1.getY() > blockpos.getY() + 1 && iworldreader.n_1700_B(z_2963_s.n_1700_B.P_1922_E, blockpos).getY() > u_530_F.G_564_y((float)blockpos.getY())) {
                    this.multiplayerClientSuggestionProvider.Y_601_j.n_1700_B((c_1514_x)blockpos1, SoundEvents.PoweredBlock, D_38_f.G_564_y, 0.1f, 0.5f, false);
                } else {
                    this.multiplayerClientSuggestionProvider.Y_601_j.n_1700_B((c_1514_x)blockpos1, SoundEvents.U_2334_m, D_38_f.G_564_y, 0.2f, 1.0f, false);
                }
            }
        }
    }

    @Override
    public void close() {
        if (this.G_624_v != null) {
            this.G_624_v.close();
        }
        if (this.B_1668_F != null) {
            this.B_1668_F.close();
        }
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        this.w_1457_N.n_1700_B(Q_4569_t);
        lightning.product.c_4037_x.n_1700_B(3553, 10242, 10497);
        lightning.product.c_4037_x.n_1700_B(3553, 10243, 10497);
        lightning.product.c_4037_x.v_4262_N(0);
        this.J_1907_R();
        if (MinecraftClient.c_3005_b()) {
            this.T_2506_i();
        }
    }

    public void J_1907_R() {
        if (this.G_624_v != null) {
            this.G_624_v.close();
        }
        g_2336_b resourcelocation = new g_2336_b("shaders/post/entity_outline.json");
        try {
            this.G_624_v = new x_2151_q(this.multiplayerClientSuggestionProvider.G_624_v(), this.multiplayerClientSuggestionProvider.T_2506_i(), this.multiplayerClientSuggestionProvider.G_564_y(), resourcelocation);
            this.G_624_v.n_1700_B(this.multiplayerClientSuggestionProvider.RealmsServerPing().u_2550_I(), this.multiplayerClientSuggestionProvider.RealmsServerPing().M_588_G());
            this.d_2461_k = this.G_624_v.n_1700_B("final");
        }
        catch (IOException ioexception) {
            s_956_w.warn("Failed to load shader: {}", (Object)resourcelocation, (Object)ioexception);
            this.G_624_v = null;
            this.d_2461_k = null;
        }
        catch (JsonSyntaxException jsonsyntaxexception) {
            s_956_w.warn("Failed to parse shader: {}", (Object)resourcelocation, (Object)jsonsyntaxexception);
            this.G_624_v = null;
            this.d_2461_k = null;
        }
    }

    private void T_2506_i() {
        this.q_4610_l();
        g_2336_b resourcelocation = new g_2336_b("shaders/post/transparency.json");
        try {
            x_2151_q shadergroup = new x_2151_q(this.multiplayerClientSuggestionProvider.G_624_v(), this.multiplayerClientSuggestionProvider.T_2506_i(), this.multiplayerClientSuggestionProvider.G_564_y(), resourcelocation);
            shadergroup.n_1700_B(this.multiplayerClientSuggestionProvider.RealmsServerPing().u_2550_I(), this.multiplayerClientSuggestionProvider.RealmsServerPing().M_588_G());
            P_4249_L framebuffer1 = shadergroup.n_1700_B("translucent");
            P_4249_L framebuffer2 = shadergroup.n_1700_B("itemEntity");
            P_4249_L framebuffer3 = shadergroup.n_1700_B("particles");
            P_4249_L framebuffer4 = shadergroup.n_1700_B("weather");
            P_4249_L framebuffer = shadergroup.n_1700_B("clouds");
            this.B_1668_F = shadergroup;
            this.T_2506_i = framebuffer1;
            this.q_4610_l = framebuffer2;
            this.z_4693_k = framebuffer3;
            this.g_221_o = framebuffer4;
            this.e_2887_G = framebuffer;
        }
        catch (Exception exception1) {
            String s = exception1 instanceof JsonSyntaxException ? "parse" : "load";
            String s1 = "Failed to " + s + " shader: " + String.valueOf(resourcelocation);
            J_1907_R worldrenderer$shaderexception = new J_1907_R(s1, exception1);
            if (this.multiplayerClientSuggestionProvider.q_4610_l().G_564_y().size() > 1) {
                U_2871_b itextcomponent;
                try {
                    itextcomponent = new U_2871_b(this.multiplayerClientSuggestionProvider.T_2506_i().n_1700_B(resourcelocation).R_4764_Y());
                }
                catch (IOException ioexception1) {
                    itextcomponent = null;
                }
                this.multiplayerClientSuggestionProvider.P_4830_p.u_1723_Y = P_3084_J.J_1907_R;
                this.multiplayerClientSuggestionProvider.n_1700_B(worldrenderer$shaderexception, itextcomponent);
            }
            n_3236_c crashreport = this.multiplayerClientSuggestionProvider.R_4764_Y(new n_3236_c(s1, worldrenderer$shaderexception));
            this.multiplayerClientSuggestionProvider.P_4830_p.u_1723_Y = P_3084_J.J_1907_R;
            this.multiplayerClientSuggestionProvider.P_4830_p.J_1907_R();
            s_956_w.fatal(s1, (Throwable)worldrenderer$shaderexception);
            this.multiplayerClientSuggestionProvider.P_4830_p();
            MinecraftClient.J_1907_R(crashreport);
        }
    }

    private void q_4610_l() {
        if (this.B_1668_F != null) {
            this.B_1668_F.close();
            this.T_2506_i.P_1922_E();
            this.q_4610_l.P_1922_E();
            this.z_4693_k.P_1922_E();
            this.g_221_o.P_1922_E();
            this.e_2887_G.P_1922_E();
            this.B_1668_F = null;
            this.T_2506_i = null;
            this.q_4610_l = null;
            this.z_4693_k = null;
            this.g_221_o = null;
            this.e_2887_G = null;
        }
    }

    public void R_4764_Y() {
        if (this.G_564_y()) {
            lightning.product.c_4037_x.Y_601_j();
            lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w, X_933_l.t_1786_h.Q_4569_t, X_933_l.s_956_w.P_1922_E);
            this.d_2461_k.R_4764_Y(this.multiplayerClientSuggestionProvider.RealmsServerPing().u_2550_I(), this.multiplayerClientSuggestionProvider.RealmsServerPing().M_588_G(), false);
            lightning.product.c_4037_x.Y_259_p();
        }
    }

    public boolean G_564_y() {
        if (!Config.isShaders() && !Config.isAntialiasing()) {
            lightning.product.n_1700_B bot = this.d_2461_k();
            X_4340_E player = bot != null ? bot.P_1922_E.Q_2552_b : this.multiplayerClientSuggestionProvider.Y_259_p;
            return this.d_2461_k != null && this.G_624_v != null && player != null;
        }
        return false;
    }

    private void z_4693_k() {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        if (this.Y_1740_V != null) {
            this.Y_1740_V.close();
        }
        this.Y_1740_V = new D_4883_k(this.c_3005_b);
        this.n_1700_B(bufferbuilder, -16.0f, true);
        bufferbuilder.u_1723_Y();
        this.Y_1740_V.n_1700_B(bufferbuilder);
    }

    private void g_221_o() {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        if (this.A_4115_X != null) {
            this.A_4115_X.close();
        }
        this.A_4115_X = new D_4883_k(this.c_3005_b);
        this.n_1700_B(bufferbuilder, 16.0f, false);
        bufferbuilder.u_1723_Y();
        this.A_4115_X.n_1700_B(bufferbuilder);
    }

    private void n_1700_B(D_3318_r bufferBuilderIn, float posY, boolean reverseX) {
        int i = 64;
        int j = 6;
        bufferBuilderIn.n_1700_B(7, E_688_b.w_1457_N);
        int k = (this.ValueObject / 64 + 1) * 64 + 64;
        for (int l = -k; l <= k; l += 64) {
            for (int i1 = -k; i1 <= k; i1 += 64) {
                float f = l;
                float f1 = l + 64;
                if (reverseX) {
                    f1 = l;
                    f = l + 64;
                }
                bufferBuilderIn.pos(f, posY, i1).endVertex();
                bufferBuilderIn.pos(f1, posY, i1).endVertex();
                bufferBuilderIn.pos(f1, posY, i1 + 64).endVertex();
                bufferBuilderIn.pos(f, posY, i1 + 64).endVertex();
            }
        }
    }

    private void e_2887_G() {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        if (this.H_2857_Y != null) {
            this.H_2857_Y.close();
        }
        this.H_2857_Y = new D_4883_k(this.c_3005_b);
        this.n_1700_B(bufferbuilder);
        bufferbuilder.u_1723_Y();
        this.H_2857_Y.n_1700_B(bufferbuilder);
    }

    private void n_1700_B(D_3318_r bufferBuilderIn) {
        Random random = new Random(10842L);
        bufferBuilderIn.n_1700_B(7, E_688_b.w_1457_N);
        for (int i = 0; i < 1500; ++i) {
            double d0 = random.nextFloat() * 2.0f - 1.0f;
            double d1 = random.nextFloat() * 2.0f - 1.0f;
            double d2 = random.nextFloat() * 2.0f - 1.0f;
            double d3 = 0.15f + random.nextFloat() * 0.1f;
            double d4 = d0 * d0 + d1 * d1 + d2 * d2;
            if (!(d4 < 1.0) || !(d4 > 0.01)) continue;
            d4 = 1.0 / Math.sqrt(d4);
            double d5 = (d0 *= d4) * 100.0;
            double d6 = (d1 *= d4) * 100.0;
            double d7 = (d2 *= d4) * 100.0;
            double d8 = Math.atan2(d0, d2);
            double d9 = Math.sin(d8);
            double d10 = Math.cos(d8);
            double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
            double d12 = Math.sin(d11);
            double d13 = Math.cos(d11);
            double d14 = random.nextDouble() * Math.PI * 2.0;
            double d15 = Math.sin(d14);
            double d16 = Math.cos(d14);
            for (int j = 0; j < 4; ++j) {
                double d17 = 0.0;
                double d18 = (double)((j & 2) - 1) * d3;
                double d19 = (double)((j + 1 & 2) - 1) * d3;
                double d20 = 0.0;
                double d21 = d18 * d16 - d19 * d15;
                double d22 = d19 * d16 + d18 * d15;
                double d23 = d21 * d12 + 0.0 * d13;
                double d24 = 0.0 * d12 - d21 * d13;
                double d25 = d24 * d9 - d22 * d10;
                double d26 = d22 * d9 + d24 * d10;
                bufferBuilderIn.pos(d5 + d25, d6 + d23, d7 + d26).endVertex();
            }
        }
    }

    public void n_1700_B(@Nullable k_4690_i worldClientIn) {
        this.n_1700_B((b_4507_u)worldClientIn);
    }

    public void n_1700_B(@Nullable b_4507_u worldIn) {
        this.g_164_R = Double.MIN_VALUE;
        this.X_933_l = Double.MIN_VALUE;
        this.Z_976_R = Double.MIN_VALUE;
        this.H_1990_U = Integer.MIN_VALUE;
        this.N_2525_X = Integer.MIN_VALUE;
        this.c_4037_x = Integer.MIN_VALUE;
        this.Y_601_j.n_1700_B(worldIn);
        this.Q_2552_b = worldIn;
        if (Config.isDynamicLights()) {
            DynamicLights.clear();
        }
        ChunkVisibility.reset();
        this.RealmsDefaultUncaughtExceptionHandler.reset(null, null);
        BiomeUtils.onWorldChanged(this.Q_2552_b);
        Shaders.checkWorldChanged(this.Q_2552_b);
        if (worldIn != null) {
            this.P_1922_E();
        } else {
            this.C_2741_M.clear();
            this.dtoRealmsServerAddress.clear();
            this.B_1668_F();
            if (this.Z_875_P != null) {
                this.Z_875_P.n_1700_B();
                this.Z_875_P = null;
            }
            if (this.Ping != null) {
                this.Ping.u_1723_Y();
            }
            this.Ping = null;
            this.q_2307_F.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void P_1922_E() {
        lightning.product.n_1700_B botFL;
        V_772_m entityFL;
        V_772_m viewEntity;
        lightning.product.n_1700_B activeBot = this.d_2461_k();
        X_4340_E x_4340_E = viewEntity = activeBot != null && activeBot.P_1922_E != null ? activeBot.P_1922_E.Q_2552_b : this.multiplayerClientSuggestionProvider.Y_259_p;
        if (this.Q_2552_b != null) {
            lightning.product.n_1700_B botLR;
            N_4263_v entity;
            if (MinecraftClient.c_3005_b()) {
                this.T_2506_i();
            } else {
                this.q_4610_l();
            }
            if (this.Q_2552_b instanceof k_4690_i) {
                ((k_4690_i)this.Q_2552_b).G_564_y();
            } else if (this.Q_2552_b instanceof c_3005_b) {
                ((c_3005_b)this.Q_2552_b).u_1723_Y();
            }
            if (this.Ping == null) {
                this.Ping = new z_4547_I(this.Q_2552_b, this, j_3341_s.u_1723_Y(), this.multiplayerClientSuggestionProvider.B_1668_F(), this.Y_259_p.n_1700_B());
            } else {
                this.Ping.n_1700_B(this.Q_2552_b);
            }
            this.V_1446_Y = true;
            this.t_4043_B = true;
            d_1620_j.n_1700_B(Config.isTreesFancy());
            W_571_B.R_4764_Y();
            if (Config.isDynamicLights()) {
                DynamicLights.clear();
            }
            SmartAnimations.update();
            RetryCallException = MinecraftClient.H_2857_Y();
            this.RealmsClientConfig = this.multiplayerClientSuggestionProvider.P_4830_p.J_1907_R;
            this.ValueObject = this.RealmsClientConfig * 16;
            this.F_1410_V = this.ValueObject * this.ValueObject;
            this.e_2887_G();
            this.g_221_o();
            this.z_4693_k();
            if (this.Z_875_P != null) {
                this.Z_875_P.n_1700_B();
            }
            this.u_1723_Y();
            Set<i_2154_H> set = this.q_2307_F;
            synchronized (set) {
                this.q_2307_F.clear();
            }
            this.Z_875_P = new B_1647_r(this.Ping, this.Q_2552_b, this.multiplayerClientSuggestionProvider.P_4830_p.J_1907_R, this);
            if (this.Q_2552_b != null && (entity = (botLR = this.d_2461_k()) != null && botLR.P_1922_E != null && botLR.P_1922_E.Q_2552_b != null ? botLR.P_1922_E.Q_2552_b : this.multiplayerClientSuggestionProvider.g_2268_R()) != null) {
                this.Z_875_P.n_1700_B(entity.O_3598_v(), entity.l_2647_k());
            }
        }
        X_4340_E x_4340_E2 = entityFL = (botFL = this.d_2461_k()) != null && botFL.P_1922_E != null ? botFL.P_1922_E.Q_2552_b : this.multiplayerClientSuggestionProvider.Y_259_p;
        if (entityFL == null) {
            this.y_1700_S = true;
        }
    }

    protected void u_1723_Y() {
        this.C_2741_M.clear();
        this.Ping.G_564_y();
    }

    public void n_1700_B(int width, int height) {
        this.h_1847_R();
        if (this.G_624_v != null) {
            this.G_624_v.n_1700_B(width, height);
        }
        if (this.B_1668_F != null) {
            this.B_1668_F.n_1700_B(width, height);
        }
    }

    public String v_4262_N() {
        int i = this.Z_875_P.u_1723_Y.length;
        int j = this.w_1484_f();
        return String.format("C: %d/%d %sD: %d, %s", j, i, this.multiplayerClientSuggestionProvider.z_1737_N ? "(s) " : "", this.RealmsClientConfig, this.Ping == null ? "null" : this.Ping.n_1700_B());
    }

    protected int w_1484_f() {
        int i = 0;
        for (n_1700_B worldrenderer$localrenderinformationcontainer : this.k_2293_S) {
            if (worldrenderer$localrenderinformationcontainer.n_1700_B.R_4764_Y().n_1700_B()) continue;
            ++i;
        }
        return i;
    }

    public String t_148_a() {
        Integer entityCount = this.n_1700_B(this.Q_2552_b, (k_4690_i w) -> w.P_1922_E(), (c_3005_b w) -> w.v_4262_N());
        return "E: " + this.f_4016_n + "/" + (entityCount != null ? entityCount : 0) + ", B: " + this.j_276_v + ", " + Config.getVersionDebug();
    }

    public void n_1700_B(h_3572_K activeRenderInfoIn, E_4918_z camera, boolean debugCamera, int frameCount, boolean playerSpectator) {
        e_2866_D vector3d = activeRenderInfoIn.J_1907_R();
        if (this.multiplayerClientSuggestionProvider.P_4830_p.J_1907_R != this.RealmsClientConfig) {
            this.P_1922_E();
        }
        this.Q_2552_b.D_4792_h().n_1700_B("camera");
        lightning.product.n_1700_B botST = this.d_2461_k();
        V_772_m playerST = botST != null && botST.P_1922_E != null ? botST.P_1922_E.Q_2552_b : this.multiplayerClientSuggestionProvider.Y_259_p;
        double d0 = playerST.O_3598_v() - this.g_164_R;
        double d1 = playerST.X_2960_b() - this.X_933_l;
        double d2 = playerST.l_2647_k() - this.Z_976_R;
        if (this.H_1990_U != playerST.u_744_e || this.N_2525_X != playerST.RetryCallException || this.c_4037_x != playerST.r_3651_U || d0 * d0 + d1 * d1 + d2 * d2 > 16.0) {
            this.g_164_R = playerST.O_3598_v();
            this.X_933_l = playerST.X_2960_b();
            this.Z_976_R = playerST.l_2647_k();
            this.H_1990_U = playerST.u_744_e;
            this.N_2525_X = playerST.RetryCallException;
            this.c_4037_x = playerST.r_3651_U;
            this.Z_875_P.n_1700_B(playerST.O_3598_v(), playerST.l_2647_k());
        }
        if (Config.isDynamicLights()) {
            DynamicLights.update(this);
        }
        this.Ping.n_1700_B(vector3d);
        this.Q_2552_b.D_4792_h().J_1907_R("cull");
        this.multiplayerClientSuggestionProvider.PlayerInfo().J_1907_R("culling");
        c_1514_x blockpos = activeRenderInfoIn.R_4764_Y();
        z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender = this.Z_875_P.n_1700_B(blockpos);
        int i = 16;
        c_1514_x blockpos1 = new c_1514_x(u_530_F.R_4764_Y(vector3d.J_1907_R / 16.0) * 16, u_530_F.R_4764_Y(vector3d.R_4764_Y / 16.0) * 16, u_530_F.R_4764_Y(vector3d.G_564_y / 16.0) * 16);
        float f = activeRenderInfoIn.G_564_y();
        float f1 = activeRenderInfoIn.P_1922_E();
        this.V_1446_Y = this.V_1446_Y || !this.C_2741_M.isEmpty() || vector3d.J_1907_R != this.g_2268_R || vector3d.R_4764_Y != this.T_3594_S || vector3d.G_564_y != this.D_4792_h || (double)f != this.s_2632_s || (double)f1 != this.l_1233_K;
        this.g_2268_R = vector3d.J_1907_R;
        this.T_3594_S = vector3d.R_4764_Y;
        this.D_4792_h = vector3d.G_564_y;
        this.s_2632_s = f;
        this.l_1233_K = f1;
        this.multiplayerClientSuggestionProvider.PlayerInfo().J_1907_R("update");
        Lagometer.timerVisibility.start();
        int j = this.w_1457_N();
        if (j != this.F_2624_D) {
            this.F_2624_D = j;
            this.V_1446_Y = true;
        }
        N_4263_v entity = activeRenderInfoIn.v_4262_N();
        int k = 256;
        if (!ChunkVisibility.isFinished()) {
            this.V_1446_Y = true;
        }
        if (!debugCamera && this.V_1446_Y && Config.isIntegratedServerRunning() && !Shaders.isShadowPass) {
            k = ChunkVisibility.getMaxChunkY(this.Q_2552_b, entity, this.RealmsClientConfig);
        }
        z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender1 = this.Z_875_P.n_1700_B(new c_1514_x(entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k()));
        if (Shaders.isShadowPass) {
            this.k_2293_S = this.RegionPingResult;
            this.RealmsServerPing = this.H_1083_k;
            this.j_1564_a = this.R_3908_n;
            if (!debugCamera && this.V_1446_Y) {
                this.B_1668_F();
                if (chunkrenderdispatcher$chunkrender1 != null && chunkrenderdispatcher$chunkrender1.P_1922_E().getY() > k) {
                    this.RealmsServerPing.add(chunkrenderdispatcher$chunkrender1.Q_4569_t());
                }
                Iterator<z_4547_I.n_1700_B> iterator = ShadowUtils.makeShadowChunkIterator(this.Q_2552_b, 0.0, entity, this.RealmsClientConfig, this.Z_875_P);
                while (iterator.hasNext()) {
                    z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender2 = iterator.next();
                    if (chunkrenderdispatcher$chunkrender2 == null || chunkrenderdispatcher$chunkrender2.P_1922_E().getY() > k) continue;
                    n_1700_B worldrenderer$localrenderinformationcontainer = chunkrenderdispatcher$chunkrender2.Q_4569_t();
                    if (!chunkrenderdispatcher$chunkrender2.n_1700_B.get().n_1700_B()) {
                        this.k_2293_S.add((Object)worldrenderer$localrenderinformationcontainer);
                    }
                    if (ChunkUtils.hasEntities(chunkrenderdispatcher$chunkrender2.P_4830_p())) {
                        this.RealmsServerPing.add(worldrenderer$localrenderinformationcontainer);
                    }
                    if (chunkrenderdispatcher$chunkrender2.R_4764_Y().J_1907_R().size() <= 0) continue;
                    this.j_1564_a.add(worldrenderer$localrenderinformationcontainer);
                }
            }
        } else {
            this.k_2293_S = this.M_1641_O;
            this.RealmsServerPing = this.RealmsWorldOptions;
            this.j_1564_a = this.RealmsWorldResetDto;
        }
        if (!debugCamera && this.V_1446_Y && !Shaders.isShadowPass) {
            this.V_1446_Y = false;
            this.B_1668_F();
            this.w_612_n.clear();
            Deque deque = this.w_612_n;
            N_4263_v.J_1907_R(u_530_F.n_1700_B((double)this.multiplayerClientSuggestionProvider.P_4830_p.J_1907_R / 8.0, 1.0, 2.5) * (double)this.multiplayerClientSuggestionProvider.P_4830_p.R_4764_Y);
            boolean flag = this.multiplayerClientSuggestionProvider.z_1737_N;
            c_1514_x blockpos2 = activeRenderInfoIn.R_4764_Y();
            int l = blockpos2.getY();
            int i1 = l >> 4 << 4;
            if (i1 > k && i1 > (k += 16) && k < 256) {
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender3;
                if (chunkrenderdispatcher$chunkrender1 != null) {
                    this.RealmsServerPing.add(chunkrenderdispatcher$chunkrender1.Q_4569_t());
                }
                e_2866_D vector3d1 = new e_2866_D(blockpos2.getX(), k, blockpos2.getZ());
                e_2866_D vector3d2 = new e_2866_D(vector3d1.n_1700_B(), vector3d1.J_1907_R(), vector3d1.R_4764_Y());
                M_1336_P vector3f = activeRenderInfoIn.P_4830_p();
                M_1336_P vector3f1 = new M_1336_P(vector3f.n_1700_B(), 0.0f, vector3f.R_4764_Y());
                if (!vector3f1.G_564_y()) {
                    vector3f1 = new M_1336_P(1.0f, 0.0f, 0.0f);
                }
                double d3 = vector3f1.n_1700_B() * 16.0f;
                double d4 = vector3f1.R_4764_Y() * 16.0f;
                double d5 = this.RealmsClientConfig * 16;
                double d6 = d5 * d5;
                while (vector3d2.v_4262_N(vector3d1) < d6 && (chunkrenderdispatcher$chunkrender3 = this.Z_875_P.n_1700_B(new c_1514_x(vector3d2))) != null) {
                    if (camera.isBoundingBoxInFrustum(chunkrenderdispatcher$chunkrender3.J_1907_R)) {
                        chunkrenderdispatcher$chunkrender3.n_1700_B(frameCount);
                        deque.add(new n_1700_B(chunkrenderdispatcher$chunkrender3, null, 0));
                        break;
                    }
                    vector3d2 = vector3d2.J_1907_R(d3, 0.0, d4);
                }
            }
            if (deque.isEmpty()) {
                if (chunkrenderdispatcher$chunkrender != null && chunkrenderdispatcher$chunkrender.P_1922_E().getY() <= k) {
                    if (playerSpectator && this.Q_2552_b.getBlockState(blockpos).t_148_a(this.Q_2552_b, blockpos)) {
                        flag = false;
                    }
                    chunkrenderdispatcher$chunkrender.n_1700_B(frameCount);
                    deque.add(new n_1700_B(chunkrenderdispatcher$chunkrender, null, 0));
                } else {
                    int l1;
                    int n = l1 = blockpos1.getY() > 0 ? Math.min(k, 248) : 8;
                    if (chunkrenderdispatcher$chunkrender1 != null) {
                        this.RealmsServerPing.add(chunkrenderdispatcher$chunkrender1.Q_4569_t());
                    }
                    int i2 = u_530_F.R_4764_Y(vector3d.J_1907_R / 16.0) * 16;
                    int j2 = u_530_F.R_4764_Y(vector3d.G_564_y / 16.0) * 16;
                    ArrayList list = Lists.newArrayList();
                    for (int k2 = -this.RealmsClientConfig; k2 <= this.RealmsClientConfig; ++k2) {
                        for (int j1 = -this.RealmsClientConfig; j1 <= this.RealmsClientConfig; ++j1) {
                            z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender6 = this.Z_875_P.n_1700_B(new c_1514_x(i2 + (k2 << 4) + 8, l1, j2 + (j1 << 4) + 8));
                            if (chunkrenderdispatcher$chunkrender6 == null || !camera.isBoundingBoxInFrustum(chunkrenderdispatcher$chunkrender6.J_1907_R)) continue;
                            chunkrenderdispatcher$chunkrender6.n_1700_B(frameCount);
                            n_1700_B worldrenderer$localrenderinformationcontainer1 = chunkrenderdispatcher$chunkrender6.Q_4569_t();
                            worldrenderer$localrenderinformationcontainer1.n_1700_B(null, 0, 0);
                            list.add(worldrenderer$localrenderinformationcontainer1);
                        }
                    }
                    list.sort(Comparator.comparingDouble(p_lambda$setupTerrain$0_1_ -> blockpos.distanceSq(p_lambda$setupTerrain$0_1_.n_1700_B.P_1922_E().add(8, 8, 8))));
                    deque.addAll(list);
                }
            }
            this.multiplayerClientSuggestionProvider.PlayerInfo().n_1700_B("iteration");
            boolean flag1 = Config.isFogOn();
            while (!deque.isEmpty()) {
                b_257_Y[] adirection;
                n_1700_B worldrenderer$localrenderinformationcontainer3 = (n_1700_B)deque.poll();
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender5 = worldrenderer$localrenderinformationcontainer3.n_1700_B;
                b_257_Y direction1 = worldrenderer$localrenderinformationcontainer3.J_1907_R;
                z_4547_I.R_4764_Y chunkrenderdispatcher$compiledchunk = chunkrenderdispatcher$chunkrender5.n_1700_B.get();
                if (!chunkrenderdispatcher$compiledchunk.n_1700_B() || chunkrenderdispatcher$chunkrender5.v_4262_N()) {
                    this.k_2293_S.add((Object)worldrenderer$localrenderinformationcontainer3);
                }
                if (ChunkUtils.hasEntities(chunkrenderdispatcher$chunkrender5.P_4830_p())) {
                    this.RealmsServerPing.add(worldrenderer$localrenderinformationcontainer3);
                }
                if (chunkrenderdispatcher$compiledchunk.J_1907_R().size() > 0) {
                    this.j_1564_a.add(worldrenderer$localrenderinformationcontainer3);
                }
                for (b_257_Y direction : adirection = flag ? ChunkVisibility.getFacingsNotOpposite(worldrenderer$localrenderinformationcontainer3.R_4764_Y) : b_257_Y.v_4262_N) {
                    z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender7;
                    if (flag && direction1 != null && !chunkrenderdispatcher$compiledchunk.n_1700_B(direction1.u_1723_Y(), direction) || (chunkrenderdispatcher$chunkrender7 = this.n_1700_B(blockpos1, chunkrenderdispatcher$chunkrender5, direction, flag1, k)) == null || !chunkrenderdispatcher$chunkrender7.n_1700_B(frameCount) || !camera.isBoundingBoxInFrustum(chunkrenderdispatcher$chunkrender7.J_1907_R)) continue;
                    int k1 = worldrenderer$localrenderinformationcontainer3.R_4764_Y | 1 << direction.ordinal();
                    n_1700_B worldrenderer$localrenderinformationcontainer4 = chunkrenderdispatcher$chunkrender7.Q_4569_t();
                    worldrenderer$localrenderinformationcontainer4.n_1700_B(direction, k1, worldrenderer$localrenderinformationcontainer3.G_564_y + 1);
                    deque.add(worldrenderer$localrenderinformationcontainer4);
                }
            }
            this.multiplayerClientSuggestionProvider.PlayerInfo().R_4764_Y();
        }
        Lagometer.timerVisibility.end();
        if (Shaders.isShadowPass) {
            Shaders.mcProfilerEndSection();
        } else {
            this.multiplayerClientSuggestionProvider.PlayerInfo().J_1907_R("rebuildNear");
            Set<z_4547_I.n_1700_B> set = this.C_2741_M;
            this.C_2741_M = this.dtoRealmsServerAddress;
            this.dtoRealmsServerAddress = set;
            this.C_2741_M.clear();
            Lagometer.timerChunkUpdate.start();
            for (n_1700_B worldrenderer$localrenderinformationcontainer2 : this.k_2293_S) {
                boolean flag2;
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender4 = worldrenderer$localrenderinformationcontainer2.n_1700_B;
                if (!chunkrenderdispatcher$chunkrender4.v_4262_N() && !set.contains(chunkrenderdispatcher$chunkrender4)) continue;
                this.V_1446_Y = true;
                c_1514_x blockpos3 = chunkrenderdispatcher$chunkrender4.P_1922_E();
                boolean bl = flag2 = (double)MathUtils.distanceSq(blockpos1, blockpos3.getX() + 8, blockpos3.getY() + 8, blockpos3.getZ() + 8) < 768.0;
                if (!chunkrenderdispatcher$chunkrender4.w_1484_f() && !flag2) {
                    this.C_2741_M.add(chunkrenderdispatcher$chunkrender4);
                    continue;
                }
                if (!chunkrenderdispatcher$chunkrender4.M_588_G()) {
                    this.P_1922_E.add(chunkrenderdispatcher$chunkrender4);
                    continue;
                }
                this.multiplayerClientSuggestionProvider.PlayerInfo().n_1700_B("build near");
                this.Ping.n_1700_B(chunkrenderdispatcher$chunkrender4);
                chunkrenderdispatcher$chunkrender4.u_1723_Y();
                this.multiplayerClientSuggestionProvider.PlayerInfo().R_4764_Y();
            }
            Lagometer.timerChunkUpdate.end();
            this.C_2741_M.addAll(set);
            this.multiplayerClientSuggestionProvider.PlayerInfo().R_4764_Y();
        }
    }

    @Nullable
    private z_4547_I.n_1700_B n_1700_B(c_1514_x p_getRenderChunkOffset_1_, z_4547_I.n_1700_B p_getRenderChunkOffset_2_, b_257_Y p_getRenderChunkOffset_3_, boolean p_getRenderChunkOffset_4_, int p_getRenderChunkOffset_5_) {
        z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender = p_getRenderChunkOffset_2_.J_1907_R(p_getRenderChunkOffset_3_);
        if (chunkrenderdispatcher$chunkrender == null) {
            return null;
        }
        if (chunkrenderdispatcher$chunkrender.P_1922_E().getY() > p_getRenderChunkOffset_5_) {
            return null;
        }
        if (p_getRenderChunkOffset_4_) {
            int j;
            c_1514_x blockpos = chunkrenderdispatcher$chunkrender.P_1922_E();
            int i = p_getRenderChunkOffset_1_.getX() - blockpos.getX();
            int k = i * i + (j = p_getRenderChunkOffset_1_.getZ() - blockpos.getZ()) * j;
            if (k > this.F_1410_V) {
                return null;
            }
        }
        return chunkrenderdispatcher$chunkrender;
    }

    private void n_1700_B(D_1098_v p_228419_1_, D_1098_v p_228419_2_, double p_228419_3_, double p_228419_5_, double p_228419_7_, E_4918_z p_228419_9_) {
        this.e_1992_r = p_228419_9_;
        D_1098_v matrix4f = p_228419_2_.u_1723_Y();
        matrix4f.n_1700_B(p_228419_1_);
        matrix4f.P_1922_E();
        this.k_3961_g.n_1700_B = p_228419_3_;
        this.k_3961_g.J_1907_R = p_228419_5_;
        this.k_3961_g.R_4764_Y = p_228419_7_;
        this.D_60_a[0] = new Z_2491_A(-1.0f, -1.0f, -1.0f, 1.0f);
        this.D_60_a[1] = new Z_2491_A(1.0f, -1.0f, -1.0f, 1.0f);
        this.D_60_a[2] = new Z_2491_A(1.0f, 1.0f, -1.0f, 1.0f);
        this.D_60_a[3] = new Z_2491_A(-1.0f, 1.0f, -1.0f, 1.0f);
        this.D_60_a[4] = new Z_2491_A(-1.0f, -1.0f, 1.0f, 1.0f);
        this.D_60_a[5] = new Z_2491_A(1.0f, -1.0f, 1.0f, 1.0f);
        this.D_60_a[6] = new Z_2491_A(1.0f, 1.0f, 1.0f, 1.0f);
        this.D_60_a[7] = new Z_2491_A(-1.0f, 1.0f, 1.0f, 1.0f);
        for (int i = 0; i < 8; ++i) {
            this.D_60_a[i].n_1700_B(matrix4f);
            this.D_60_a[i].u_1723_Y();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(g_221_o matrixStackIn, float partialTicks, long finishTimeNano, boolean drawBlockOutline, h_3572_K activeRenderInfoIn, M_660_m gameRendererIn, e_1689_x lightmapIn, D_1098_v projectionIn) {
        Cosmetics cosmetics;
        Chams chams;
        Object chunkrenderdispatcher$chunkrender;
        lightning.product.n_1700_B bot;
        boolean flag2;
        boolean flag;
        lightning.product.n_1700_B botWR;
        if (this.Q_2552_b == null || this.multiplayerClientSuggestionProvider.Y_601_j == null) {
            return;
        }
        f_2689_h.J_1907_R.n_1700_B(this.Q_2552_b, this.multiplayerClientSuggestionProvider.G_624_v(), this.multiplayerClientSuggestionProvider.t_148_a, activeRenderInfoIn, this.multiplayerClientSuggestionProvider.Z_875_P);
        this.Y_601_j.n_1700_B(this.Q_2552_b, activeRenderInfoIn, this.multiplayerClientSuggestionProvider.q_2307_F);
        ProfilerFiller iprofiler = this.Q_2552_b != null ? this.Q_2552_b.D_4792_h() : this.multiplayerClientSuggestionProvider.PlayerInfo();
        iprofiler.J_1907_R("light_updates");
        if (this.multiplayerClientSuggestionProvider.Y_601_j != null) {
            this.multiplayerClientSuggestionProvider.Y_601_j.v_4262_N().G_564_y().n_1700_B(Integer.MAX_VALUE, true, true);
        }
        if ((botWR = this.d_2461_k()) != null && botWR.P_1922_E != null && botWR.P_1922_E.G_564_y() != null) {
            botWR.P_1922_E.G_564_y().P_4830_p().G_564_y().n_1700_B(Integer.MAX_VALUE, true, true);
        }
        e_2866_D vector3d = activeRenderInfoIn.J_1907_R();
        double d0 = vector3d.n_1700_B();
        double d1 = vector3d.J_1907_R();
        double d2 = vector3d.R_4764_Y();
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        iprofiler.J_1907_R("culling");
        boolean bl = flag = this.e_1992_r != null;
        if (flag) {
            this.S_980_j = this.e_1992_r;
            this.S_980_j.setCameraPosition(this.k_3961_g.n_1700_B, this.k_3961_g.J_1907_R, this.k_3961_g.R_4764_Y);
        } else {
            this.S_980_j = new E_4918_z(matrix4f, projectionIn);
            this.S_980_j.setCameraPosition(d0, d1, d2);
        }
        J_1907_R = new E_4918_z(matrix4f, projectionIn);
        this.multiplayerClientSuggestionProvider.PlayerInfo().J_1907_R("captureFrustum");
        if (this.UploadStatus) {
            this.n_1700_B(matrix4f, projectionIn, vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, flag ? new E_4918_z(matrix4f, projectionIn) : this.S_980_j);
            this.UploadStatus = false;
        }
        iprofiler.J_1907_R("clear");
        if (Config.isShaders()) {
            Shaders.setViewport(0, 0, this.multiplayerClientSuggestionProvider.RealmsServerPing().u_2550_I(), this.multiplayerClientSuggestionProvider.RealmsServerPing().M_588_G());
        } else {
            lightning.product.c_4037_x.R_4764_Y(0, 0, this.multiplayerClientSuggestionProvider.RealmsServerPing().u_2550_I(), this.multiplayerClientSuggestionProvider.RealmsServerPing().M_588_G());
        }
        j_39_h.n_1700_B(activeRenderInfoIn, partialTicks, this.multiplayerClientSuggestionProvider.Y_601_j, this.multiplayerClientSuggestionProvider.P_4830_p.J_1907_R, gameRendererIn.G_564_y(partialTicks));
        lightning.product.c_4037_x.n_1700_B(16640, MinecraftClient.n_1700_B);
        boolean flag1 = Config.isShaders();
        if (flag1) {
            Shaders.clearRenderBuffer();
            Shaders.setCamera(matrixStackIn, activeRenderInfoIn, partialTicks);
            Shaders.renderPrepare();
        }
        this.S_980_j.disabled = Config.isShaders() && !Shaders.isFrustumCulling();
        float f = gameRendererIn.u_2550_I();
        boolean bl2 = flag2 = this.multiplayerClientSuggestionProvider.Y_601_j.n_1700_B().n_1700_B(u_530_F.R_4764_Y(d0), u_530_F.R_4764_Y(d1)) || this.multiplayerClientSuggestionProvider.M_588_G.t_148_a().u_1723_Y();
        if ((Config.isSkyEnabled() || Config.isSunMoonEnabled() || Config.isStarsEnabled()) && !Shaders.isShadowPass) {
            j_39_h.n_1700_B(activeRenderInfoIn, j_39_h.n_1700_B.n_1700_B, f, flag2, partialTicks);
            iprofiler.J_1907_R("sky");
            if (flag1) {
                Shaders.beginSky();
            }
            this.n_1700_B(matrixStackIn, partialTicks);
            if (flag1) {
                Shaders.endSky();
            }
        } else {
            lightning.product.X_933_l.h_1847_R();
        }
        iprofiler.J_1907_R("fog");
        j_39_h.n_1700_B(activeRenderInfoIn, j_39_h.n_1700_B.J_1907_R, Math.max(f - 16.0f, 32.0f), flag2, partialTicks);
        iprofiler.J_1907_R("terrain_setup");
        lightning.product.n_1700_B botSpectator = this.d_2461_k();
        V_772_m currentPlayer = botSpectator != null && botSpectator.P_1922_E != null && botSpectator.P_1922_E.Q_2552_b != null ? botSpectator.P_1922_E.Q_2552_b : this.multiplayerClientSuggestionProvider.Y_259_p;
        boolean isSpectator = currentPlayer != null && ((a_3913_L)currentPlayer).d_2461_k();
        this.n_1700_B(activeRenderInfoIn, this.S_980_j, isSpectator);
        this.n_1700_B(activeRenderInfoIn, this.S_980_j, flag, this.PlayerInfo++, isSpectator);
        iprofiler.J_1907_R("updatechunks");
        this.u_2550_I();
        iprofiler.J_1907_R("terrain");
        Lagometer.timerTerrain.start();
        if (this.multiplayerClientSuggestionProvider.P_4830_p.RealmsLongRunningMcoTaskScreen) {
            this.multiplayerClientSuggestionProvider.PlayerInfo().J_1907_R("finish");
            GL11.glFinish();
            this.multiplayerClientSuggestionProvider.PlayerInfo().J_1907_R("terrain");
        }
        if (Config.isFogOff() && j_39_h.G_564_y) {
            lightning.product.X_933_l.R_4764_Y(false);
        }
        this.n_1700_B(o_2576_A.u_1723_Y(), matrixStackIn, d0, d1, d2);
        this.multiplayerClientSuggestionProvider.G_624_v().J_1907_R(L_3848_p.n_1700_B).setBlurMipmapDirect(false, this.multiplayerClientSuggestionProvider.P_4830_p.c_3005_b > 0);
        this.n_1700_B(o_2576_A.v_4262_N(), matrixStackIn, d0, d1, d2);
        this.multiplayerClientSuggestionProvider.G_624_v().J_1907_R(L_3848_p.n_1700_B).restoreLastBlurMipmap();
        this.n_1700_B(o_2576_A.w_1484_f(), matrixStackIn, d0, d1, d2);
        if (flag1) {
            ShadersRender.endTerrain();
        }
        Lagometer.timerTerrain.end();
        Boolean useDiffuseGuiLighting = this.n_1700_B(this.Q_2552_b, (k_4690_i w) -> w.n_1700_B().P_1922_E(), (c_3005_b w) -> w.n_1700_B().P_1922_E());
        if (useDiffuseGuiLighting != null && useDiffuseGuiLighting.booleanValue()) {
            W_3265_k.n_1700_B(matrixStackIn.R_4764_Y().n_1700_B());
        } else {
            W_3265_k.J_1907_R(matrixStackIn.R_4764_Y().n_1700_B());
        }
        if (flag1) {
            Shaders.beginEntities();
        }
        p_2951_v.P_1922_E();
        iprofiler.J_1907_R("entities");
        ++u_744_e;
        this.f_4016_n = 0;
        this.j_276_v = 0;
        this.l_4537_E = 0;
        if (this.q_4610_l != null) {
            this.q_4610_l.R_4764_Y(MinecraftClient.n_1700_B);
            this.q_4610_l.n_1700_B(this.multiplayerClientSuggestionProvider.G_564_y());
            this.multiplayerClientSuggestionProvider.G_564_y().J_1907_R(false);
        }
        if (this.g_221_o != null) {
            this.g_221_o.R_4764_Y(MinecraftClient.n_1700_B);
        }
        if (this.G_564_y()) {
            this.d_2461_k.R_4764_Y(MinecraftClient.n_1700_B);
            this.multiplayerClientSuggestionProvider.G_564_y().J_1907_R(false);
        }
        boolean flag3 = false;
        o_3091_w.n_1700_B irendertypebuffer$impl = this.Y_259_p.J_1907_R();
        if (Config.isFastRender()) {
            RenderStateManager.enableCache();
        }
        V_772_m player = (bot = this.d_2461_k()) != null && bot.P_1922_E != null && bot.P_1922_E.Q_2552_b != null ? bot.P_1922_E.Q_2552_b : this.multiplayerClientSuggestionProvider.Y_259_p;
        for (n_1700_B n_1700_B2 : this.RealmsServerPing) {
            chunkrenderdispatcher$chunkrender = n_1700_B2.n_1700_B;
            H_1748_a chunk = ((z_4547_I.n_1700_B)chunkrenderdispatcher$chunkrender).P_4830_p();
            for (N_4263_v entity : chunk.getEntityLists()[((z_4547_I.n_1700_B)chunkrenderdispatcher$chunkrender).P_1922_E().getY() / 16]) {
                boolean flag4;
                boolean bl3 = flag4 = entity == player && player instanceof a_3913_L && !((a_3913_L)player).d_2461_k();
                if (!this.Y_601_j.n_1700_B(entity, this.S_980_j, d0, d1, d2) && !entity.Q_2552_b(player) || entity == activeRenderInfoIn.v_4262_N() && !activeRenderInfoIn.t_148_a() && (!(activeRenderInfoIn.v_4262_N() instanceof r_4811_B) || !((r_4811_B)activeRenderInfoIn.v_4262_N()).z_2372_L()) || entity instanceof V_772_m && entity instanceof Z_875_P && activeRenderInfoIn.v_4262_N() != entity && !flag4) continue;
                String s = entity.getClass().getName();
                List<N_4263_v> list = this.r_3651_U.get(s);
                if (list == null) {
                    list = new ArrayList<N_4263_v>();
                    this.r_3651_U.put(s, list);
                }
                list.add(entity);
            }
        }
        for (List list : this.r_3651_U.values()) {
            for (N_4263_v entity1 : list) {
                o_3091_w irendertypebuffer;
                ++this.f_4016_n;
                if (entity1.RealmsWorldResetDto == 0) {
                    entity1.q_1982_R = entity1.O_3598_v();
                    entity1.dtoRealmsServerAddress = entity1.X_2960_b();
                    entity1.w_612_n = entity1.l_2647_k();
                }
                if (this.G_564_y() && this.multiplayerClientSuggestionProvider.J_1907_R(entity1)) {
                    flag3 = true;
                    OutlineBufferSource outlinelayerbuffer = this.Y_259_p.G_564_y();
                    irendertypebuffer = outlinelayerbuffer;
                    int k2 = entity1.i_1637_u();
                    int l2 = 255;
                    int i3 = k2 >> 16 & 0xFF;
                    int i2 = k2 >> 8 & 0xFF;
                    int j2 = k2 & 0xFF;
                    outlinelayerbuffer.n_1700_B(i3, i2, j2, 255);
                } else {
                    irendertypebuffer = irendertypebuffer$impl;
                }
                this.R_4764_Y = entity1;
                if (flag1) {
                    Shaders.nextEntity(entity1);
                }
                this.n_1700_B(entity1, d0, d1, d2, partialTicks, matrixStackIn, irendertypebuffer);
                this.R_4764_Y = null;
            }
            list.clear();
        }
        this.n_1700_B(matrixStackIn);
        irendertypebuffer$impl.n_1700_B(o_2576_A.J_1907_R(L_3848_p.n_1700_B));
        irendertypebuffer$impl.n_1700_B(o_2576_A.R_4764_Y(L_3848_p.n_1700_B));
        irendertypebuffer$impl.n_1700_B(o_2576_A.G_564_y(L_3848_p.n_1700_B));
        irendertypebuffer$impl.n_1700_B(o_2576_A.t_148_a(L_3848_p.n_1700_B));
        if (flag1) {
            Shaders.endEntities();
            Shaders.beginBlockEntities();
        }
        iprofiler.J_1907_R("blockentities");
        O_1806_w.n_1700_B();
        boolean flag5 = Reflector.IForgeTileEntity_getRenderBoundingBox.exists();
        E_4918_z e_4918_z = this.S_980_j;
        for (n_1700_B worldrenderer$localrenderinformationcontainer1 : this.j_1564_a) {
            List<i_2154_H> list2 = worldrenderer$localrenderinformationcontainer1.n_1700_B.R_4764_Y().J_1907_R();
            if (list2.isEmpty()) continue;
            for (i_2154_H tileentity1 : list2) {
                int j3;
                I_4817_s axisalignedbb1;
                if (flag5 && (axisalignedbb1 = (I_4817_s)Reflector.call(tileentity1, Reflector.IForgeTileEntity_getRenderBoundingBox, new Object[0])) != null && !e_4918_z.isBoundingBoxInFrustum(axisalignedbb1)) continue;
                if (flag1) {
                    Shaders.nextBlockEntity(tileentity1);
                }
                c_1514_x blockpos3 = tileentity1.x_607_J();
                o_3091_w irendertypebuffer1 = irendertypebuffer$impl;
                matrixStackIn.n_1700_B();
                matrixStackIn.n_1700_B((double)blockpos3.getX() - d0, (double)blockpos3.getY() - d1, (double)blockpos3.getZ() - d2);
                SortedSet sortedset = (SortedSet)this.z_1737_N.get(blockpos3.toLong());
                if (sortedset != null && !sortedset.isEmpty() && (j3 = ((BlockDestructionProgress)sortedset.last()).J_1907_R()) >= 0) {
                    g_221_o.n_1700_B matrixstack$entry = matrixStackIn.R_4764_Y();
                    l_1233_K ivertexbuilder = new l_1233_K(this.Y_259_p.R_4764_Y().getBuffer(g_2561_p.u_2550_I.get(j3)), matrixstack$entry.n_1700_B(), matrixstack$entry.J_1907_R());
                    irendertypebuffer1 = p_lambda$updateCameraAndRender$1_2_ -> {
                        D_4792_h ivertexbuilder3 = irendertypebuffer$impl.getBuffer(p_lambda$updateCameraAndRender$1_2_);
                        return p_lambda$updateCameraAndRender$1_2_.Y_1740_V() ? lightning.product.z_1333_t.n_1700_B(ivertexbuilder, ivertexbuilder3) : ivertexbuilder3;
                    };
                }
                f_2689_h.J_1907_R.n_1700_B(tileentity1, partialTicks, matrixStackIn, irendertypebuffer1);
                matrixStackIn.J_1907_R();
                ++this.l_4537_E;
            }
        }
        chunkrenderdispatcher$chunkrender = this.q_2307_F;
        synchronized (chunkrenderdispatcher$chunkrender) {
            for (i_2154_H tileentity : this.q_2307_F) {
                I_4817_s axisalignedbb;
                if (flag5 && (axisalignedbb = (I_4817_s)Reflector.call(tileentity, Reflector.IForgeTileEntity_getRenderBoundingBox, new Object[0])) != null && !e_4918_z.isBoundingBoxInFrustum(axisalignedbb)) continue;
                if (flag1) {
                    Shaders.nextBlockEntity(tileentity);
                }
                c_1514_x blockpos2 = tileentity.x_607_J();
                matrixStackIn.n_1700_B();
                matrixStackIn.n_1700_B((double)blockpos2.getX() - d0, (double)blockpos2.getY() - d1, (double)blockpos2.getZ() - d2);
                f_2689_h.J_1907_R.n_1700_B(tileentity, partialTicks, matrixStackIn, irendertypebuffer$impl);
                matrixStackIn.J_1907_R();
                ++this.l_4537_E;
            }
        }
        this.n_1700_B(matrixStackIn);
        irendertypebuffer$impl.n_1700_B(o_2576_A.u_1723_Y());
        irendertypebuffer$impl.n_1700_B(b_4440_Q.v_4262_N());
        irendertypebuffer$impl.n_1700_B(b_4440_Q.w_1484_f());
        irendertypebuffer$impl.n_1700_B(b_4440_Q.R_4764_Y());
        irendertypebuffer$impl.n_1700_B(b_4440_Q.G_564_y());
        irendertypebuffer$impl.n_1700_B(b_4440_Q.P_1922_E());
        irendertypebuffer$impl.n_1700_B(b_4440_Q.u_1723_Y());
        irendertypebuffer$impl.n_1700_B(b_4440_Q.n_1700_B());
        this.Y_259_p.G_564_y().J_1907_R();
        if (Config.isFastRender()) {
            RenderStateManager.disableCache();
        }
        if (flag3) {
            this.G_624_v.n_1700_B(partialTicks);
            this.multiplayerClientSuggestionProvider.G_564_y().J_1907_R(false);
        }
        if (flag1) {
            Shaders.endBlockEntities();
        }
        this.u_1723_Y = true;
        iprofiler.J_1907_R("destroyProgress");
        for (Long2ObjectMap.Entry entry : this.z_1737_N.long2ObjectEntrySet()) {
            SortedSet sortedset1;
            double d5;
            double d4;
            c_1514_x blockpos1 = c_1514_x.fromLong(entry.getLongKey());
            double d3 = (double)blockpos1.getX() - d0;
            if (d3 * d3 + (d4 = (double)blockpos1.getY() - d1) * d4 + (d5 = (double)blockpos1.getZ() - d2) * d5 > 1024.0 || (sortedset1 = (SortedSet)entry.getValue()) == null || sortedset1.isEmpty()) continue;
            int k3 = ((BlockDestructionProgress)sortedset1.last()).J_1907_R();
            matrixStackIn.n_1700_B();
            matrixStackIn.n_1700_B((double)blockpos1.getX() - d0, (double)blockpos1.getY() - d1, (double)blockpos1.getZ() - d2);
            g_221_o.n_1700_B matrixstack$entry1 = matrixStackIn.R_4764_Y();
            l_1233_K ivertexbuilder1 = new l_1233_K(this.Y_259_p.R_4764_Y().getBuffer(g_2561_p.u_2550_I.get(k3)), matrixstack$entry1.n_1700_B(), matrixstack$entry1.J_1907_R());
            this.multiplayerClientSuggestionProvider.z_1333_t().n_1700_B(this.Q_2552_b.getBlockState(blockpos1), blockpos1, this.Q_2552_b, matrixStackIn, ivertexbuilder1);
            matrixStackIn.J_1907_R();
        }
        this.u_1723_Y = false;
        RenderUtils.flushRenderBuffers();
        --u_744_e;
        this.n_1700_B(matrixStackIn);
        HitResult raytraceresult = this.multiplayerClientSuggestionProvider.Z_875_P;
        if (drawBlockOutline && raytraceresult != null && raytraceresult.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            BlockOverlay blockOverlay;
            boolean flag7;
            iprofiler.J_1907_R("outline");
            c_1514_x blockpos = ((BlockHitResult)raytraceresult).n_1700_B();
            K_4074_S blockstate = this.Q_2552_b.getBlockState(blockpos);
            if (flag1) {
                ShadersRender.beginOutline();
            }
            if (Reflector.IForgeBlockState_isAir2.exists() && Reflector.ForgeHooksClient_onDrawBlockHighlight.exists()) {
                flag7 = !Reflector.callBoolean(Reflector.ForgeHooksClient_onDrawBlockHighlight, this, activeRenderInfoIn, raytraceresult, Float.valueOf(partialTicks), matrixStackIn, irendertypebuffer$impl) && !Reflector.callBoolean(blockstate, Reflector.IForgeBlockState_isAir2, this.Q_2552_b, blockpos) && this.Q_2552_b.H_2857_Y().n_1700_B(blockpos);
            } else {
                boolean bl4 = flag7 = !blockstate.v_4262_N() && this.Q_2552_b.H_2857_Y().n_1700_B(blockpos);
            }
            if (flag7 && ((blockOverlay = (BlockOverlay)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BlockOverlay.class)) == null || !blockOverlay.w_1484_f())) {
                D_4792_h ivertexbuilder2 = irendertypebuffer$impl.getBuffer(o_2576_A.C_2741_M());
                this.n_1700_B(matrixStackIn, ivertexbuilder2, activeRenderInfoIn.v_4262_N(), d0, d1, d2, blockpos, blockstate);
            }
            if (flag1) {
                irendertypebuffer$impl.n_1700_B(o_2576_A.C_2741_M());
                ShadersRender.endOutline();
            }
        } else if (raytraceresult != null && raytraceresult.R_4764_Y() == HitResult.n_1700_B.R_4764_Y) {
            Reflector.ForgeHooksClient_onDrawBlockHighlight.call(this, activeRenderInfoIn, raytraceresult, Float.valueOf(partialTicks), matrixStackIn, irendertypebuffer$impl);
        }
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.n_1700_B(matrixStackIn.R_4764_Y().n_1700_B());
        boolean flag6 = lightning.product.X_933_l.g_2268_R();
        lightning.product.X_933_l.H_2857_Y();
        if (flag1) {
            ShadersRender.beginDebug();
        }
        this.multiplayerClientSuggestionProvider.u_2550_I.n_1700_B(matrixStackIn, irendertypebuffer$impl, d0, d1, d2);
        lightning.product.c_4037_x.d_2461_k();
        irendertypebuffer$impl.n_1700_B(b_4440_Q.s_956_w());
        irendertypebuffer$impl.n_1700_B(b_4440_Q.n_1700_B());
        irendertypebuffer$impl.n_1700_B(b_4440_Q.J_1907_R());
        irendertypebuffer$impl.n_1700_B(o_2576_A.h_1847_R());
        irendertypebuffer$impl.n_1700_B(o_2576_A.Q_4569_t());
        irendertypebuffer$impl.n_1700_B(o_2576_A.t_1786_h());
        irendertypebuffer$impl.n_1700_B(o_2576_A.multiplayerClientSuggestionProvider());
        irendertypebuffer$impl.n_1700_B(o_2576_A.M_182_A());
        irendertypebuffer$impl.n_1700_B(o_2576_A.w_1457_N());
        irendertypebuffer$impl.n_1700_B(o_2576_A.Y_601_j());
        irendertypebuffer$impl.n_1700_B(o_2576_A.P_4830_p());
        this.Y_259_p.R_4764_Y().J_1907_R();
        lightning.product.X_933_l.J_1907_R(flag6);
        if (flag1) {
            irendertypebuffer$impl.J_1907_R();
            ShadersRender.endDebug();
            Shaders.preRenderHand();
            ShadersRender.renderHand0(gameRendererIn, matrixStackIn, activeRenderInfoIn, partialTicks);
            Shaders.preWater();
        }
        if (this.B_1668_F != null) {
            irendertypebuffer$impl.n_1700_B(o_2576_A.C_2741_M());
            irendertypebuffer$impl.J_1907_R();
            this.T_2506_i.R_4764_Y(MinecraftClient.n_1700_B);
            this.T_2506_i.n_1700_B(this.multiplayerClientSuggestionProvider.G_564_y());
            iprofiler.J_1907_R("translucent");
            this.n_1700_B(o_2576_A.t_148_a(), matrixStackIn, d0, d1, d2);
            iprofiler.J_1907_R("string");
            this.n_1700_B(o_2576_A.Q_2552_b(), matrixStackIn, d0, d1, d2);
            this.z_4693_k.R_4764_Y(MinecraftClient.n_1700_B);
            this.z_4693_k.n_1700_B(this.multiplayerClientSuggestionProvider.G_564_y());
            E_270_p.e_2887_G.n_1700_B();
            iprofiler.J_1907_R("particles");
            this.multiplayerClientSuggestionProvider.v_4262_N.n_1700_B(matrixStackIn, irendertypebuffer$impl, lightmapIn, activeRenderInfoIn, partialTicks, this.S_980_j);
            E_270_p.e_2887_G.J_1907_R();
        } else {
            iprofiler.J_1907_R("translucent");
            if (flag1) {
                Shaders.beginWater();
            }
            this.n_1700_B(o_2576_A.t_148_a(), matrixStackIn, d0, d1, d2);
            if (flag1) {
                Shaders.endWater();
            }
            irendertypebuffer$impl.n_1700_B(o_2576_A.C_2741_M());
            irendertypebuffer$impl.J_1907_R();
            iprofiler.J_1907_R("string");
            this.n_1700_B(o_2576_A.Q_2552_b(), matrixStackIn, d0, d1, d2);
            iprofiler.J_1907_R("particles");
            if (flag1) {
                Shaders.beginParticles();
            }
            this.multiplayerClientSuggestionProvider.v_4262_N.n_1700_B(matrixStackIn, irendertypebuffer$impl, lightmapIn, activeRenderInfoIn, partialTicks, this.S_980_j);
            if (flag1) {
                Shaders.endParticles();
            }
        }
        lightning.product.X_933_l.R_4764_Y(true);
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.n_1700_B(matrixStackIn.R_4764_Y().n_1700_B());
        lightning.product.A_4115_X.n_1700_B(new I_4477_R(partialTicks));
        if (this.multiplayerClientSuggestionProvider.P_4830_p.P_1922_E() != K_2069_m.n_1700_B) {
            if (this.B_1668_F != null) {
                this.e_2887_G.R_4764_Y(MinecraftClient.n_1700_B);
                E_270_p.g_164_R.n_1700_B();
                iprofiler.J_1907_R("clouds");
                this.n_1700_B(matrixStackIn, partialTicks, d0, d1, d2);
                E_270_p.g_164_R.J_1907_R();
            } else {
                iprofiler.J_1907_R("clouds");
                this.n_1700_B(matrixStackIn, partialTicks, d0, d1, d2);
            }
        }
        if ((chams = (Chams)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Chams.class)) != null && chams.w_1484_f()) {
            chams.n_1700_B(partialTicks);
        }
        if ((cosmetics = Cosmetics.h_1847_R()) != null && cosmetics.w_1484_f()) {
            cosmetics.n_1700_B(partialTicks);
        }
        if (this.B_1668_F != null) {
            E_270_p.B_1668_F.n_1700_B();
            iprofiler.J_1907_R("weather");
            this.n_1700_B(lightmapIn, partialTicks, d0, d1, d2);
            this.R_4764_Y(activeRenderInfoIn);
            E_270_p.B_1668_F.J_1907_R();
            this.B_1668_F.n_1700_B(partialTicks);
            this.multiplayerClientSuggestionProvider.G_564_y().J_1907_R(false);
        } else {
            lightning.product.c_4037_x.J_1907_R(false);
            if (Config.isShaders()) {
                lightning.product.X_933_l.n_1700_B(Shaders.isRainDepth());
            }
            iprofiler.J_1907_R("weather");
            if (flag1) {
                Shaders.beginWeather();
            }
            this.n_1700_B(lightmapIn, partialTicks, d0, d1, d2);
            if (flag1) {
                Shaders.endWeather();
            }
            this.R_4764_Y(activeRenderInfoIn);
            lightning.product.c_4037_x.J_1907_R(true);
        }
        this.J_1907_R(activeRenderInfoIn);
        lightning.product.c_4037_x.w_1484_f(7424);
        lightning.product.c_4037_x.J_1907_R(true);
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.d_2461_k();
        j_39_h.n_1700_B();
        for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
            ibaritone.getGameEventHandler().onRenderPass(new RenderEvent(partialTicks, matrixStackIn, projectionIn));
        }
    }

    public void n_1700_B(g_221_o matrixStackIn) {
        if (!matrixStackIn.G_564_y()) {
            throw new IllegalStateException("Pose stack not empty");
        }
    }

    public void n_1700_B(N_4263_v entityIn, double camX, double camY, double camZ, float partialTicks, g_221_o matrixStackIn, o_3091_w bufferIn) {
        double d0 = u_530_F.G_564_y((double)partialTicks, entityIn.q_1982_R, entityIn.O_3598_v());
        double d1 = u_530_F.G_564_y((double)partialTicks, entityIn.dtoRealmsServerAddress, entityIn.X_2960_b());
        double d2 = u_530_F.G_564_y((double)partialTicks, entityIn.w_612_n, entityIn.l_2647_k());
        float f = u_530_F.v_4262_N(partialTicks, entityIn.j_276_v, entityIn.p_178_J);
        this.Y_601_j.n_1700_B(entityIn, d0 - camX, d1 - camY, d2 - camZ, f, partialTicks, matrixStackIn, bufferIn, this.Y_601_j.n_1700_B(entityIn, partialTicks));
    }

    public void n_1700_B(o_2576_A blockLayerIn, g_221_o matrixStackIn, double xIn, double yIn, double zIn) {
        blockLayerIn.n_1700_B();
        boolean flag = Config.isShaders();
        if (blockLayerIn == o_2576_A.t_148_a() && !Shaders.isShadowPass) {
            this.multiplayerClientSuggestionProvider.PlayerInfo().n_1700_B("translucent_sort");
            double d0 = xIn - this.Ops;
            double d1 = yIn - this.h_4320_q;
            double d2 = zIn - this.t_4219_U;
            if (d0 * d0 + d1 * d1 + d2 * d2 > 1.0) {
                this.Ops = xIn;
                this.h_4320_q = yIn;
                this.t_4219_U = zIn;
                int i = 0;
                this.G_564_y.clear();
                for (n_1700_B worldrenderer$localrenderinformationcontainer1 : this.k_2293_S) {
                    if (i >= 15 || !worldrenderer$localrenderinformationcontainer1.n_1700_B.R_4764_Y().R_4764_Y(blockLayerIn)) continue;
                    this.G_564_y.add(worldrenderer$localrenderinformationcontainer1.n_1700_B);
                    ++i;
                }
            }
            this.multiplayerClientSuggestionProvider.PlayerInfo().R_4764_Y();
        }
        this.multiplayerClientSuggestionProvider.PlayerInfo().n_1700_B("filterempty");
        if (flag) {
            ShadersRender.preRenderChunkLayer(blockLayerIn);
        }
        boolean flag2 = SmartAnimations.isActive();
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.z_1737_N();
        lightning.product.c_4037_x.n_1700_B(matrixStackIn.R_4764_Y().n_1700_B());
        this.multiplayerClientSuggestionProvider.PlayerInfo().J_1907_R(() -> "render_" + String.valueOf(blockLayerIn));
        boolean flag1 = blockLayerIn != o_2576_A.t_148_a();
        ObjectListIterator objectlistiterator = this.k_2293_S.listIterator(flag1 ? 0 : this.k_2293_S.size());
        if (Config.isRenderRegions()) {
            int j = Integer.MIN_VALUE;
            int k = Integer.MIN_VALUE;
            VboRegion vboregion2 = null;
            Map map = this.RowButton.computeIfAbsent(blockLayerIn, p_lambda$renderBlockLayer$3_0_ -> new LinkedHashMap(16));
            Map map1 = null;
            List list1 = null;
            while (!(!flag1 ? !objectlistiterator.hasPrevious() : !objectlistiterator.hasNext())) {
                BitSet bitset1;
                n_1700_B worldrenderer$localrenderinformationcontainer2;
                n_1700_B n_1700_B2 = worldrenderer$localrenderinformationcontainer2 = flag1 ? (n_1700_B)objectlistiterator.next() : (n_1700_B)objectlistiterator.previous();
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender = worldrenderer$localrenderinformationcontainer2.n_1700_B;
                if (chunkrenderdispatcher$chunkrender.R_4764_Y().n_1700_B(blockLayerIn)) continue;
                D_4883_k vertexbuffer1 = chunkrenderdispatcher$chunkrender.n_1700_B(blockLayerIn);
                VboRegion vboregion = vertexbuffer1.R_4764_Y();
                if (chunkrenderdispatcher$chunkrender.R_4764_Y != j || chunkrenderdispatcher$chunkrender.G_564_y != k) {
                    PairInt pairint = PairInt.of(chunkrenderdispatcher$chunkrender.R_4764_Y, chunkrenderdispatcher$chunkrender.G_564_y);
                    map1 = map.computeIfAbsent(pairint, p_lambda$renderBlockLayer$4_0_ -> new LinkedHashMap(8));
                    j = chunkrenderdispatcher$chunkrender.R_4764_Y;
                    k = chunkrenderdispatcher$chunkrender.G_564_y;
                    vboregion2 = null;
                }
                if (vboregion != vboregion2) {
                    list1 = map1.computeIfAbsent(vboregion, p_lambda$renderBlockLayer$5_0_ -> new ArrayList());
                    vboregion2 = vboregion;
                }
                list1.add(vertexbuffer1);
                if (!SmartAnimations.isActive() || (bitset1 = chunkrenderdispatcher$chunkrender.R_4764_Y().J_1907_R(blockLayerIn)) == null) continue;
                SmartAnimations.spritesRendered(bitset1);
            }
            for (Map.Entry entry : map.entrySet()) {
                PairInt pairint1 = (PairInt)entry.getKey();
                Map map2 = (Map)entry.getValue();
                for (Map.Entry entry1 : map2.entrySet()) {
                    VboRegion vboregion1 = (VboRegion)entry1.getKey();
                    List list = (List)entry1.getValue();
                    for (D_4883_k vertexbuffer2 : list) {
                        vertexbuffer2.n_1700_B(7);
                    }
                    this.n_1700_B(pairint1.getLeft(), 0, pairint1.getRight(), xIn, yIn, zIn, vboregion1);
                    list.clear();
                }
            }
        } else {
            while (!(!flag1 ? !objectlistiterator.hasPrevious() : !objectlistiterator.hasNext())) {
                BitSet bitset;
                n_1700_B worldrenderer$localrenderinformationcontainer;
                n_1700_B n_1700_B3 = worldrenderer$localrenderinformationcontainer = flag1 ? (n_1700_B)objectlistiterator.next() : (n_1700_B)objectlistiterator.previous();
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender1 = worldrenderer$localrenderinformationcontainer.n_1700_B;
                if (chunkrenderdispatcher$chunkrender1.R_4764_Y().n_1700_B(blockLayerIn)) continue;
                D_4883_k vertexbuffer = chunkrenderdispatcher$chunkrender1.n_1700_B(blockLayerIn);
                lightning.product.X_933_l.g_221_o();
                c_1514_x blockpos = chunkrenderdispatcher$chunkrender1.P_1922_E();
                lightning.product.X_933_l.J_1907_R((double)blockpos.getX() - xIn, (double)blockpos.getY() - yIn, (double)blockpos.getZ() - zIn);
                lightning.product.A_4115_X.n_1700_B(new J_4125_o(chunkrenderdispatcher$chunkrender1));
                vertexbuffer.n_1700_B();
                E_688_b.w_1484_f.n_1700_B(0L);
                lightning.product.X_933_l.z_1333_t();
                if (flag) {
                    ShadersRender.setupArrayPointersVbo();
                }
                vertexbuffer.n_1700_B(7);
                lightning.product.X_933_l.e_2887_G();
                if (!flag2 || (bitset = chunkrenderdispatcher$chunkrender1.R_4764_Y().J_1907_R(blockLayerIn)) == null) continue;
                SmartAnimations.spritesRendered(bitset);
            }
        }
        lightning.product.X_933_l.O_508_d();
        lightning.product.c_4037_x.d_2461_k();
        if (Config.isMultiTexture()) {
            this.multiplayerClientSuggestionProvider.G_624_v().n_1700_B(L_3848_p.n_1700_B);
        }
        D_4883_k.J_1907_R();
        lightning.product.c_4037_x.G_624_v();
        E_688_b.w_1484_f.G_564_y();
        this.multiplayerClientSuggestionProvider.PlayerInfo().R_4764_Y();
        if (flag) {
            ShadersRender.postRenderChunkLayer(blockLayerIn);
        }
        blockLayerIn.J_1907_R();
    }

    private void n_1700_B(int p_drawRegion_1_, int p_drawRegion_2_, int p_drawRegion_3_, double p_drawRegion_4_, double p_drawRegion_6_, double p_drawRegion_8_, VboRegion p_drawRegion_10_) {
        lightning.product.X_933_l.g_221_o();
        lightning.product.X_933_l.J_1907_R((double)p_drawRegion_1_ - p_drawRegion_4_, (double)p_drawRegion_2_ - p_drawRegion_6_, (double)p_drawRegion_3_ - p_drawRegion_8_);
        p_drawRegion_10_.finishDraw();
        lightning.product.X_933_l.z_1333_t();
        lightning.product.X_933_l.e_2887_G();
    }

    private void J_1907_R(h_3572_K activeRenderInfoIn) {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        if (this.multiplayerClientSuggestionProvider.n_3318_d || this.multiplayerClientSuggestionProvider.d_2427_y) {
            double d0 = activeRenderInfoIn.J_1907_R().n_1700_B();
            double d1 = activeRenderInfoIn.J_1907_R().J_1907_R();
            double d2 = activeRenderInfoIn.J_1907_R().R_4764_Y();
            lightning.product.c_4037_x.J_1907_R(true);
            lightning.product.c_4037_x.q_2307_F();
            lightning.product.c_4037_x.Y_601_j();
            lightning.product.c_4037_x.s_2632_s();
            lightning.product.c_4037_x.e_4240_b();
            for (n_1700_B worldrenderer$localrenderinformationcontainer : this.k_2293_S) {
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender = worldrenderer$localrenderinformationcontainer.n_1700_B;
                lightning.product.c_4037_x.v_4276_D();
                c_1514_x blockpos = chunkrenderdispatcher$chunkrender.P_1922_E();
                lightning.product.c_4037_x.J_1907_R((double)blockpos.getX() - d0, (double)blockpos.getY() - d1, (double)blockpos.getZ() - d2);
                if (this.multiplayerClientSuggestionProvider.n_3318_d) {
                    bufferbuilder.n_1700_B(1, E_688_b.Y_601_j);
                    lightning.product.c_4037_x.G_564_y(10.0f);
                    int i = worldrenderer$localrenderinformationcontainer.G_564_y == 0 ? 0 : u_530_F.u_1723_Y((float)worldrenderer$localrenderinformationcontainer.G_564_y / 50.0f, 0.9f, 0.9f);
                    int j = i >> 16 & 0xFF;
                    int k = i >> 8 & 0xFF;
                    int l = i & 0xFF;
                    b_257_Y direction = worldrenderer$localrenderinformationcontainer.J_1907_R;
                    if (direction != null) {
                        bufferbuilder.pos(8.0, 8.0, 8.0).color(j, k, l, 255).endVertex();
                        bufferbuilder.pos(8 - 16 * direction.t_148_a(), 8 - 16 * direction.s_956_w(), 8 - 16 * direction.u_2550_I()).color(j, k, l, 255).endVertex();
                    }
                    tessellator.J_1907_R();
                    lightning.product.c_4037_x.G_564_y(1.0f);
                }
                if (this.multiplayerClientSuggestionProvider.d_2427_y && !chunkrenderdispatcher$chunkrender.R_4764_Y().n_1700_B()) {
                    bufferbuilder.n_1700_B(1, E_688_b.Y_601_j);
                    lightning.product.c_4037_x.G_564_y(10.0f);
                    int i1 = 0;
                    for (b_257_Y direction2 : n_1700_B) {
                        for (b_257_Y direction1 : n_1700_B) {
                            boolean flag = chunkrenderdispatcher$chunkrender.R_4764_Y().n_1700_B(direction2, direction1);
                            if (flag) continue;
                            ++i1;
                            bufferbuilder.pos(8 + 8 * direction2.t_148_a(), 8 + 8 * direction2.s_956_w(), 8 + 8 * direction2.u_2550_I()).color(1, 0, 0, 1).endVertex();
                            bufferbuilder.pos(8 + 8 * direction1.t_148_a(), 8 + 8 * direction1.s_956_w(), 8 + 8 * direction1.u_2550_I()).color(1, 0, 0, 1).endVertex();
                        }
                    }
                    tessellator.J_1907_R();
                    lightning.product.c_4037_x.G_564_y(1.0f);
                    if (i1 > 0) {
                        bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
                        float f = 0.5f;
                        float f1 = 0.2f;
                        bufferbuilder.pos(0.5, 15.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 15.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 15.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 15.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 0.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 0.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 0.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 0.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 15.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 15.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 0.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 0.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 0.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 0.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 15.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 15.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 0.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 0.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 15.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 15.5, 0.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 15.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 15.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(15.5, 0.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        bufferbuilder.pos(0.5, 0.5, 15.5).n_1700_B(0.9f, 0.9f, 0.0f, 0.2f).endVertex();
                        tessellator.J_1907_R();
                    }
                }
                lightning.product.c_4037_x.d_2461_k();
            }
            lightning.product.c_4037_x.J_1907_R(true);
            lightning.product.c_4037_x.Y_259_p();
            lightning.product.c_4037_x.k_2293_S();
            lightning.product.c_4037_x.x_607_J();
        }
        if (this.e_1992_r != null) {
            lightning.product.c_4037_x.q_2307_F();
            lightning.product.c_4037_x.e_4240_b();
            lightning.product.c_4037_x.Y_601_j();
            lightning.product.c_4037_x.s_2632_s();
            lightning.product.c_4037_x.G_564_y(10.0f);
            lightning.product.c_4037_x.v_4276_D();
            lightning.product.c_4037_x.R_4764_Y((float)(this.k_3961_g.n_1700_B - activeRenderInfoIn.J_1907_R().J_1907_R), (float)(this.k_3961_g.J_1907_R - activeRenderInfoIn.J_1907_R().R_4764_Y), (float)(this.k_3961_g.R_4764_Y - activeRenderInfoIn.J_1907_R().G_564_y));
            lightning.product.c_4037_x.J_1907_R(true);
            bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
            this.n_1700_B(bufferbuilder, 0, 1, 2, 3, 0, 1, 1);
            this.n_1700_B(bufferbuilder, 4, 5, 6, 7, 1, 0, 0);
            this.n_1700_B(bufferbuilder, 0, 1, 5, 4, 1, 1, 0);
            this.n_1700_B(bufferbuilder, 2, 3, 7, 6, 0, 0, 1);
            this.n_1700_B(bufferbuilder, 0, 4, 7, 3, 0, 1, 0);
            this.n_1700_B(bufferbuilder, 1, 5, 6, 2, 1, 0, 1);
            tessellator.J_1907_R();
            lightning.product.c_4037_x.J_1907_R(false);
            bufferbuilder.n_1700_B(1, E_688_b.w_1457_N);
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.n_1700_B(bufferbuilder, 0);
            this.n_1700_B(bufferbuilder, 1);
            this.n_1700_B(bufferbuilder, 1);
            this.n_1700_B(bufferbuilder, 2);
            this.n_1700_B(bufferbuilder, 2);
            this.n_1700_B(bufferbuilder, 3);
            this.n_1700_B(bufferbuilder, 3);
            this.n_1700_B(bufferbuilder, 0);
            this.n_1700_B(bufferbuilder, 4);
            this.n_1700_B(bufferbuilder, 5);
            this.n_1700_B(bufferbuilder, 5);
            this.n_1700_B(bufferbuilder, 6);
            this.n_1700_B(bufferbuilder, 6);
            this.n_1700_B(bufferbuilder, 7);
            this.n_1700_B(bufferbuilder, 7);
            this.n_1700_B(bufferbuilder, 4);
            this.n_1700_B(bufferbuilder, 0);
            this.n_1700_B(bufferbuilder, 4);
            this.n_1700_B(bufferbuilder, 1);
            this.n_1700_B(bufferbuilder, 5);
            this.n_1700_B(bufferbuilder, 2);
            this.n_1700_B(bufferbuilder, 6);
            this.n_1700_B(bufferbuilder, 3);
            this.n_1700_B(bufferbuilder, 7);
            tessellator.J_1907_R();
            lightning.product.c_4037_x.d_2461_k();
            lightning.product.c_4037_x.J_1907_R(true);
            lightning.product.c_4037_x.Y_259_p();
            lightning.product.c_4037_x.k_2293_S();
            lightning.product.c_4037_x.x_607_J();
            lightning.product.c_4037_x.G_564_y(1.0f);
        }
    }

    private void n_1700_B(D_4792_h bufferIn, int vertex) {
        bufferIn.pos(this.D_60_a[vertex].n_1700_B(), this.D_60_a[vertex].J_1907_R(), this.D_60_a[vertex].R_4764_Y()).endVertex();
    }

    private void n_1700_B(D_4792_h bufferIn, int vertex1, int vertex2, int vertex3, int vertex4, int red, int green, int blue) {
        float f = 0.25f;
        bufferIn.pos(this.D_60_a[vertex1].n_1700_B(), this.D_60_a[vertex1].J_1907_R(), this.D_60_a[vertex1].R_4764_Y()).n_1700_B(red, (float)green, (float)blue, 0.25f).endVertex();
        bufferIn.pos(this.D_60_a[vertex2].n_1700_B(), this.D_60_a[vertex2].J_1907_R(), this.D_60_a[vertex2].R_4764_Y()).n_1700_B(red, (float)green, (float)blue, 0.25f).endVertex();
        bufferIn.pos(this.D_60_a[vertex3].n_1700_B(), this.D_60_a[vertex3].J_1907_R(), this.D_60_a[vertex3].R_4764_Y()).n_1700_B(red, (float)green, (float)blue, 0.25f).endVertex();
        bufferIn.pos(this.D_60_a[vertex4].n_1700_B(), this.D_60_a[vertex4].J_1907_R(), this.D_60_a[vertex4].R_4764_Y()).n_1700_B(red, (float)green, (float)blue, 0.25f).endVertex();
    }

    public void s_956_w() {
        ++this.n_3318_d;
        if (this.n_3318_d % 20 == 0) {
            ObjectIterator iterator = this.d_2427_y.values().iterator();
            while (iterator.hasNext()) {
                BlockDestructionProgress destroyblockprogress = (BlockDestructionProgress)iterator.next();
                int i = destroyblockprogress.R_4764_Y();
                if (this.n_3318_d - i <= 400) continue;
                iterator.remove();
                this.n_1700_B(destroyblockprogress);
            }
        }
        if (Config.isRenderRegions() && this.n_3318_d % 20 == 0) {
            this.RowButton.clear();
        }
    }

    private void n_1700_B(BlockDestructionProgress progressIn) {
        long i = progressIn.n_1700_B().toLong();
        Set set = (Set)this.z_1737_N.get(i);
        set.remove(progressIn);
        if (set.isEmpty()) {
            this.z_1737_N.remove(i);
        }
    }

    private void J_1907_R(g_221_o matrixStackIn) {
        if (Config.isSkyEnabled()) {
            lightning.product.c_4037_x.u_2550_I();
            lightning.product.c_4037_x.Y_601_j();
            lightning.product.c_4037_x.s_2632_s();
            lightning.product.c_4037_x.J_1907_R(false);
            this.w_1457_N.n_1700_B(h_1847_R);
            l_3747_P tessellator = l_3747_P.n_1700_B();
            D_3318_r bufferbuilder = tessellator.R_4764_Y();
            for (int i = 0; i < 6; ++i) {
                matrixStackIn.n_1700_B();
                if (i == 1) {
                    matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
                }
                if (i == 2) {
                    matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(-90.0f));
                }
                if (i == 3) {
                    matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(180.0f));
                }
                if (i == 4) {
                    matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(90.0f));
                }
                if (i == 5) {
                    matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(-90.0f));
                }
                D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
                bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
                int j = 40;
                int k = 40;
                int l = 40;
                if (Config.isCustomColors()) {
                    e_2866_D vector3d = new e_2866_D((double)j / 255.0, (double)k / 255.0, (double)l / 255.0);
                    vector3d = CustomColors.getWorldSkyColor(vector3d, this.Q_2552_b, this.multiplayerClientSuggestionProvider.g_2268_R(), 0.0f);
                    j = (int)(vector3d.J_1907_R * 255.0);
                    k = (int)(vector3d.R_4764_Y * 255.0);
                    l = (int)(vector3d.G_564_y * 255.0);
                }
                bufferbuilder.n_1700_B(matrix4f, -100.0f, -100.0f, -100.0f).tex(0.0f, 0.0f).color(j, k, l, 255).endVertex();
                bufferbuilder.n_1700_B(matrix4f, -100.0f, -100.0f, 100.0f).tex(0.0f, 16.0f).color(j, k, l, 255).endVertex();
                bufferbuilder.n_1700_B(matrix4f, 100.0f, -100.0f, 100.0f).tex(16.0f, 16.0f).color(j, k, l, 255).endVertex();
                bufferbuilder.n_1700_B(matrix4f, 100.0f, -100.0f, -100.0f).tex(16.0f, 0.0f).color(j, k, l, 255).endVertex();
                tessellator.J_1907_R();
                matrixStackIn.J_1907_R();
            }
            lightning.product.c_4037_x.J_1907_R(true);
            lightning.product.c_4037_x.x_607_J();
            lightning.product.c_4037_x.Y_259_p();
            lightning.product.c_4037_x.M_588_G();
        }
    }

    public void n_1700_B(g_221_o matrixStackIn, float partialTicks) {
        ISkyRenderHandler iskyrenderhandler;
        if (Reflector.ForgeDimensionRenderInfo_getSkyRenderHandler.exists() && this.Q_2552_b instanceof k_4690_i && (iskyrenderhandler = (ISkyRenderHandler)Reflector.call(((k_4690_i)this.Q_2552_b).n_1700_B(), Reflector.ForgeDimensionRenderInfo_getSkyRenderHandler, new Object[0])) != null) {
            iskyrenderhandler.render(this.n_3318_d, partialTicks, matrixStackIn, (k_4690_i)this.Q_2552_b, this.multiplayerClientSuggestionProvider);
            return;
        }
        if (this.multiplayerClientSuggestionProvider.Y_601_j.n_1700_B().R_4764_Y() == DimensionSpecialEffects.J_1907_R.R_4764_Y) {
            this.J_1907_R(matrixStackIn);
        } else if (this.multiplayerClientSuggestionProvider.Y_601_j.n_1700_B().R_4764_Y() == DimensionSpecialEffects.J_1907_R.J_1907_R) {
            Boolean hasSkyLight;
            Float starBrightness;
            float f12;
            boolean pouchCustomSky = false;
            boolean pouchCosmosSky = false;
            boolean pouchPlasmaBalatroSky = false;
            Ambience ambience = ClientBootstrap.Y_601_j().J_1907_R().v_4262_N;
            if (ambience != null && ambience.w_1484_f() && !Config.isShaders()) {
                pouchCustomSky = Ambience.multiplayerClientSuggestionProvider();
                pouchCosmosSky = Ambience.h_1847_R();
                pouchPlasmaBalatroSky = Ambience.t_1786_h();
            }
            lightning.product.c_4037_x.e_4240_b();
            boolean flag = Config.isShaders();
            if (flag) {
                Shaders.disableTexture2D();
            }
            e_2866_D vector3d = this.n_1700_B(this.Q_2552_b, (k_4690_i w) -> w.n_1700_B(this.multiplayerClientSuggestionProvider.s_956_w.M_588_G().R_4764_Y(), partialTicks), (c_3005_b w) -> w.n_1700_B(this.multiplayerClientSuggestionProvider.s_956_w.M_588_G().R_4764_Y(), partialTicks));
            vector3d = CustomColors.getSkyColor(vector3d, this.multiplayerClientSuggestionProvider.Y_601_j, this.multiplayerClientSuggestionProvider.g_2268_R().O_3598_v(), this.multiplayerClientSuggestionProvider.g_2268_R().X_2960_b() + 1.0, this.multiplayerClientSuggestionProvider.g_2268_R().l_2647_k());
            if (flag) {
                Shaders.setSkyColor(vector3d);
            }
            float f = (float)vector3d.J_1907_R;
            float f1 = (float)vector3d.R_4764_Y;
            float f2 = (float)vector3d.G_564_y;
            j_39_h.J_1907_R();
            D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
            lightning.product.c_4037_x.J_1907_R(false);
            lightning.product.c_4037_x.Q_2552_b();
            if (flag) {
                Shaders.enableFog();
            }
            lightning.product.c_4037_x.G_564_y(f, f1, f2);
            if (flag) {
                Shaders.preSkyList(matrixStackIn);
            }
            if (pouchCosmosSky) {
                y_254_d.n_1700_B(matrixStackIn, partialTicks, flag);
            } else if (pouchPlasmaBalatroSky) {
                W_3801_h.n_1700_B(matrixStackIn, partialTicks, flag);
            } else if (Config.isSkyEnabled()) {
                this.A_4115_X.n_1700_B();
                this.c_3005_b.n_1700_B(0L);
                this.A_4115_X.n_1700_B(matrixStackIn.R_4764_Y().n_1700_B(), 7);
                D_4883_k.J_1907_R();
                this.c_3005_b.G_564_y();
            }
            lightning.product.c_4037_x.C_2741_M();
            if (flag) {
                Shaders.disableFog();
            }
            lightning.product.c_4037_x.u_2550_I();
            lightning.product.c_4037_x.Y_601_j();
            lightning.product.c_4037_x.s_2632_s();
            float[] afloat = this.n_1700_B(this.Q_2552_b, (k_4690_i w) -> w.n_1700_B().n_1700_B(this.Q_2552_b.G_564_y(partialTicks), partialTicks), (c_3005_b w) -> w.n_1700_B().n_1700_B(this.Q_2552_b.G_564_y(partialTicks), partialTicks));
            if (afloat != null && Config.isSunMoonEnabled() && !pouchCustomSky) {
                lightning.product.c_4037_x.e_4240_b();
                if (flag) {
                    Shaders.disableTexture2D();
                }
                if (flag) {
                    Shaders.setRenderStage(RenderStage.SUNSET);
                }
                lightning.product.c_4037_x.w_1484_f(7425);
                matrixStackIn.n_1700_B();
                matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
                float f3 = u_530_F.n_1700_B(this.Q_2552_b.P_1922_E(partialTicks)) < 0.0f ? 180.0f : 0.0f;
                matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f3));
                matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(90.0f));
                float f4 = afloat[0];
                float f5 = afloat[1];
                float f6 = afloat[2];
                D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
                bufferbuilder.n_1700_B(6, E_688_b.Y_601_j);
                bufferbuilder.n_1700_B(matrix4f, 0.0f, 100.0f, 0.0f).n_1700_B(f4, f5, f6, afloat[3]).endVertex();
                int i = 16;
                for (int j = 0; j <= 16; ++j) {
                    float f7 = (float)j * ((float)Math.PI * 2) / 16.0f;
                    float f8 = u_530_F.n_1700_B(f7);
                    float f9 = u_530_F.J_1907_R(f7);
                    bufferbuilder.n_1700_B(matrix4f, f8 * 120.0f, f9 * 120.0f, -f9 * 40.0f * afloat[3]).n_1700_B(afloat[0], afloat[1], afloat[2], 0.0f).endVertex();
                }
                bufferbuilder.u_1723_Y();
                o_2840_r.n_1700_B(bufferbuilder);
                matrixStackIn.J_1907_R();
                lightning.product.c_4037_x.w_1484_f(7424);
            }
            lightning.product.c_4037_x.x_607_J();
            if (flag) {
                Shaders.enableTexture2D();
            }
            lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.h_1847_R);
            matrixStackIn.n_1700_B();
            float f10 = 1.0f - this.Q_2552_b.w_1484_f(partialTicks);
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, f10);
            matrixStackIn.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-90.0f));
            if (!pouchCustomSky) {
                CustomSky.renderSky(this.Q_2552_b, this.w_1457_N, matrixStackIn, partialTicks);
            }
            if (flag) {
                Shaders.preCelestialRotate(matrixStackIn);
            }
            matrixStackIn.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(this.Q_2552_b.G_564_y(partialTicks) * 360.0f));
            if (flag) {
                Shaders.postCelestialRotate(matrixStackIn);
            }
            D_1098_v matrix4f1 = matrixStackIn.R_4764_Y().n_1700_B();
            float f11 = 30.0f;
            if (Config.isSunTexture()) {
                if (flag) {
                    Shaders.setRenderStage(RenderStage.SUN);
                }
                this.w_1457_N.n_1700_B(M_588_G);
                bufferbuilder.n_1700_B(7, E_688_b.Q_2552_b);
                bufferbuilder.n_1700_B(matrix4f1, -f11, 100.0f, -f11).tex(0.0f, 0.0f).endVertex();
                bufferbuilder.n_1700_B(matrix4f1, f11, 100.0f, -f11).tex(1.0f, 0.0f).endVertex();
                bufferbuilder.n_1700_B(matrix4f1, f11, 100.0f, f11).tex(1.0f, 1.0f).endVertex();
                bufferbuilder.n_1700_B(matrix4f1, -f11, 100.0f, f11).tex(0.0f, 1.0f).endVertex();
                bufferbuilder.u_1723_Y();
                o_2840_r.n_1700_B(bufferbuilder);
            }
            f11 = 20.0f;
            if (Config.isMoonTexture()) {
                if (flag) {
                    Shaders.setRenderStage(RenderStage.MOON);
                }
                this.w_1457_N.n_1700_B(u_2550_I);
                int k = this.Q_2552_b.t_4043_B();
                int l = k % 4;
                int i1 = k / 4 % 2;
                float f13 = (float)(l + 0) / 4.0f;
                float f14 = (float)(i1 + 0) / 2.0f;
                float f15 = (float)(l + 1) / 4.0f;
                float f16 = (float)(i1 + 1) / 2.0f;
                bufferbuilder.n_1700_B(7, E_688_b.Q_2552_b);
                bufferbuilder.n_1700_B(matrix4f1, -f11, -100.0f, f11).tex(f15, f16).endVertex();
                bufferbuilder.n_1700_B(matrix4f1, f11, -100.0f, f11).tex(f13, f16).endVertex();
                bufferbuilder.n_1700_B(matrix4f1, f11, -100.0f, -f11).tex(f13, f14).endVertex();
                bufferbuilder.n_1700_B(matrix4f1, -f11, -100.0f, -f11).tex(f15, f14).endVertex();
                bufferbuilder.u_1723_Y();
                o_2840_r.n_1700_B(bufferbuilder);
            }
            lightning.product.c_4037_x.e_4240_b();
            if (flag) {
                Shaders.disableTexture2D();
            }
            if ((f12 = ((starBrightness = this.n_1700_B(this.Q_2552_b, (k_4690_i w) -> Float.valueOf(w.R_4764_Y(partialTicks)), (c_3005_b w) -> Float.valueOf(w.R_4764_Y(partialTicks)))) != null ? starBrightness.floatValue() : 0.0f) * f10) > 0.0f && Config.isStarsEnabled() && !CustomSky.hasSkyLayers(this.Q_2552_b) && !pouchCustomSky) {
                if (flag) {
                    Shaders.setRenderStage(RenderStage.STARS);
                }
                lightning.product.c_4037_x.G_564_y(f12, f12, f12, f12);
                this.H_2857_Y.n_1700_B();
                this.c_3005_b.n_1700_B(0L);
                this.H_2857_Y.n_1700_B(matrixStackIn.R_4764_Y().n_1700_B(), 7);
                D_4883_k.J_1907_R();
                this.c_3005_b.G_564_y();
            }
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            lightning.product.c_4037_x.Y_259_p();
            lightning.product.c_4037_x.M_588_G();
            lightning.product.c_4037_x.Q_2552_b();
            if (flag) {
                Shaders.enableFog();
            }
            matrixStackIn.J_1907_R();
            lightning.product.c_4037_x.e_4240_b();
            if (flag) {
                Shaders.disableTexture2D();
            }
            lightning.product.c_4037_x.G_564_y(0.0f, 0.0f, 0.0f);
            double voidFogHeight = 63.0;
            if (this.Q_2552_b instanceof k_4690_i) {
                voidFogHeight = ((k_4690_i)this.Q_2552_b).Y_259_p().P_4830_p();
            } else if (this.Q_2552_b instanceof c_3005_b) {
                voidFogHeight = ((c_3005_b)this.Q_2552_b).Q_2552_b().P_4830_p();
            }
            double d0 = this.multiplayerClientSuggestionProvider.Y_259_p.u_2550_I((float)partialTicks).R_4764_Y - voidFogHeight;
            boolean flag1 = false;
            if (d0 < 0.0) {
                if (flag) {
                    Shaders.setRenderStage(RenderStage.VOID);
                }
                matrixStackIn.n_1700_B();
                matrixStackIn.n_1700_B(0.0, 12.0, 0.0);
                this.Y_1740_V.n_1700_B();
                this.c_3005_b.n_1700_B(0L);
                this.Y_1740_V.n_1700_B(matrixStackIn.R_4764_Y().n_1700_B(), 7);
                D_4883_k.J_1907_R();
                this.c_3005_b.G_564_y();
                matrixStackIn.J_1907_R();
                flag1 = true;
            }
            if ((hasSkyLight = this.n_1700_B(this.Q_2552_b, (k_4690_i w) -> w.n_1700_B().J_1907_R(), (c_3005_b w) -> w.n_1700_B().J_1907_R())) != null && hasSkyLight.booleanValue()) {
                lightning.product.c_4037_x.G_564_y(f * 0.2f + 0.04f, f1 * 0.2f + 0.04f, f2 * 0.6f + 0.1f);
            } else {
                lightning.product.c_4037_x.G_564_y(f, f1, f2);
            }
            lightning.product.c_4037_x.x_607_J();
            lightning.product.c_4037_x.J_1907_R(true);
            lightning.product.c_4037_x.C_2741_M();
        }
    }

    public void n_1700_B(g_221_o matrixStackIn, float partialTicks, double viewEntityX, double viewEntityY, double viewEntityZ) {
        if (!Config.isCloudsOff()) {
            float f5;
            ICloudRenderHandler icloudrenderhandler;
            if (Reflector.ForgeDimensionRenderInfo_getCloudRenderHandler.exists() && this.Q_2552_b instanceof k_4690_i && (icloudrenderhandler = (ICloudRenderHandler)Reflector.call(((k_4690_i)this.Q_2552_b).n_1700_B(), Reflector.ForgeDimensionRenderInfo_getCloudRenderHandler, new Object[0])) != null) {
                icloudrenderhandler.render(this.n_3318_d, partialTicks, matrixStackIn, (k_4690_i)this.Q_2552_b, this.multiplayerClientSuggestionProvider, viewEntityX, viewEntityY, viewEntityZ);
                return;
            }
            Float cloudHeight = this.n_1700_B(this.Q_2552_b, (k_4690_i w) -> Float.valueOf(w.n_1700_B().n_1700_B()), (c_3005_b w) -> Float.valueOf(w.n_1700_B().n_1700_B()));
            float f = f5 = cloudHeight != null ? cloudHeight.floatValue() : 0.0f;
            if (!Float.isNaN(f5)) {
                if (Config.isShaders()) {
                    Shaders.beginClouds();
                }
                lightning.product.c_4037_x.q_2307_F();
                lightning.product.c_4037_x.Y_601_j();
                lightning.product.c_4037_x.M_588_G();
                lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
                lightning.product.c_4037_x.l_1233_K();
                lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.s_956_w);
                lightning.product.c_4037_x.Q_2552_b();
                lightning.product.c_4037_x.J_1907_R(true);
                float f2 = 12.0f;
                float f1 = 4.0f;
                double d0 = 2.0E-4;
                double d1 = ((float)this.n_3318_d + partialTicks) * 0.03f;
                double d2 = (viewEntityX + d1) / 12.0;
                double d3 = f5 - (float)viewEntityY + 0.33f;
                double d4 = viewEntityZ / 12.0 + (double)0.33f;
                d2 -= (double)(u_530_F.R_4764_Y(d2 / 2048.0) * 2048);
                d4 -= (double)(u_530_F.R_4764_Y(d4 / 2048.0) * 2048);
                float f22 = (float)(d2 - (double)u_530_F.R_4764_Y(d2));
                float f3 = (float)((d3 += this.multiplayerClientSuggestionProvider.P_4830_p.RealmsSettingsScreen * 128.0) / 4.0 - (double)u_530_F.R_4764_Y(d3 / 4.0)) * 4.0f;
                float f4 = (float)(d4 - (double)u_530_F.R_4764_Y(d4));
                e_2866_D vector3d = this.n_1700_B(this.Q_2552_b, (k_4690_i w) -> w.J_1907_R(partialTicks), (c_3005_b w) -> w.J_1907_R(partialTicks));
                int i = (int)Math.floor(d2);
                int j = (int)Math.floor(d3 / 4.0);
                int k = (int)Math.floor(d4);
                if (i != this.z_1333_t || j != this.O_508_d || k != this.r_715_M || this.multiplayerClientSuggestionProvider.P_4830_p.P_1922_E() != this.i_1637_u || this.A_1038_p.v_4262_N(vector3d) > 2.0E-4) {
                    this.z_1333_t = i;
                    this.O_508_d = j;
                    this.r_715_M = k;
                    this.A_1038_p = vector3d;
                    this.i_1637_u = this.multiplayerClientSuggestionProvider.P_4830_p.P_1922_E();
                    this.t_4043_B = true;
                }
                if (this.t_4043_B) {
                    this.t_4043_B = false;
                    D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
                    if (this.x_607_J != null) {
                        this.x_607_J.close();
                    }
                    this.x_607_J = new D_4883_k(E_688_b.c_3005_b);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, vector3d);
                    bufferbuilder.u_1723_Y();
                    this.x_607_J.n_1700_B(bufferbuilder);
                }
                lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                lightning.product.c_4037_x.h_1847_R();
                this.w_1457_N.n_1700_B(P_4830_p);
                matrixStackIn.n_1700_B();
                matrixStackIn.n_1700_B(12.0f, 1.0f, 12.0f);
                matrixStackIn.n_1700_B((double)(-f22), (double)f3, (double)(-f4));
                if (this.x_607_J != null) {
                    int i1;
                    this.x_607_J.n_1700_B();
                    E_688_b.c_3005_b.n_1700_B(0L);
                    for (int l = i1 = this.i_1637_u == K_2069_m.R_4764_Y ? 0 : 1; l < 2; ++l) {
                        if (l == 0) {
                            lightning.product.c_4037_x.n_1700_B(false, false, false, false);
                        } else {
                            lightning.product.c_4037_x.n_1700_B(true, true, true, true);
                        }
                        this.x_607_J.n_1700_B(matrixStackIn.R_4764_Y().n_1700_B(), 7);
                    }
                    D_4883_k.J_1907_R();
                    E_688_b.c_3005_b.G_564_y();
                }
                matrixStackIn.J_1907_R();
                lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                lightning.product.c_4037_x.u_2550_I();
                lightning.product.c_4037_x.k_2293_S();
                lightning.product.c_4037_x.Y_259_p();
                lightning.product.c_4037_x.C_2741_M();
                if (Config.isShaders()) {
                    Shaders.endClouds();
                }
            }
        }
    }

    private void n_1700_B(D_3318_r bufferIn, double cloudsX, double cloudsY, double cloudsZ, e_2866_D cloudsColor) {
        float f = 4.0f;
        float f1 = 0.00390625f;
        int i = 8;
        int j = 4;
        float f2 = 9.765625E-4f;
        float f3 = (float)u_530_F.R_4764_Y(cloudsX) * 0.00390625f;
        float f4 = (float)u_530_F.R_4764_Y(cloudsZ) * 0.00390625f;
        float f5 = (float)cloudsColor.J_1907_R;
        float f6 = (float)cloudsColor.R_4764_Y;
        float f7 = (float)cloudsColor.G_564_y;
        float f8 = f5 * 0.9f;
        float f9 = f6 * 0.9f;
        float f10 = f7 * 0.9f;
        float f11 = f5 * 0.7f;
        float f12 = f6 * 0.7f;
        float f13 = f7 * 0.7f;
        float f14 = f5 * 0.8f;
        float f15 = f6 * 0.8f;
        float f16 = f7 * 0.8f;
        bufferIn.n_1700_B(7, E_688_b.c_3005_b);
        float f17 = (float)Math.floor(cloudsY / 4.0) * 4.0f;
        if (Config.isCloudsFancy()) {
            for (int k = -3; k <= 4; ++k) {
                for (int l = -3; l <= 4; ++l) {
                    float f18 = k * 8;
                    float f19 = l * 8;
                    if (f17 > -5.0f) {
                        bufferIn.pos(f18 + 0.0f, f17 + 0.0f, f19 + 8.0f).tex((f18 + 0.0f) * 0.00390625f + f3, (f19 + 8.0f) * 0.00390625f + f4).n_1700_B(f11, f12, f13, 0.8f).normal(0.0f, -1.0f, 0.0f).endVertex();
                        bufferIn.pos(f18 + 8.0f, f17 + 0.0f, f19 + 8.0f).tex((f18 + 8.0f) * 0.00390625f + f3, (f19 + 8.0f) * 0.00390625f + f4).n_1700_B(f11, f12, f13, 0.8f).normal(0.0f, -1.0f, 0.0f).endVertex();
                        bufferIn.pos(f18 + 8.0f, f17 + 0.0f, f19 + 0.0f).tex((f18 + 8.0f) * 0.00390625f + f3, (f19 + 0.0f) * 0.00390625f + f4).n_1700_B(f11, f12, f13, 0.8f).normal(0.0f, -1.0f, 0.0f).endVertex();
                        bufferIn.pos(f18 + 0.0f, f17 + 0.0f, f19 + 0.0f).tex((f18 + 0.0f) * 0.00390625f + f3, (f19 + 0.0f) * 0.00390625f + f4).n_1700_B(f11, f12, f13, 0.8f).normal(0.0f, -1.0f, 0.0f).endVertex();
                    }
                    if (f17 <= 5.0f) {
                        bufferIn.pos(f18 + 0.0f, f17 + 4.0f - 9.765625E-4f, f19 + 8.0f).tex((f18 + 0.0f) * 0.00390625f + f3, (f19 + 8.0f) * 0.00390625f + f4).n_1700_B(f5, f6, f7, 0.8f).normal(0.0f, 1.0f, 0.0f).endVertex();
                        bufferIn.pos(f18 + 8.0f, f17 + 4.0f - 9.765625E-4f, f19 + 8.0f).tex((f18 + 8.0f) * 0.00390625f + f3, (f19 + 8.0f) * 0.00390625f + f4).n_1700_B(f5, f6, f7, 0.8f).normal(0.0f, 1.0f, 0.0f).endVertex();
                        bufferIn.pos(f18 + 8.0f, f17 + 4.0f - 9.765625E-4f, f19 + 0.0f).tex((f18 + 8.0f) * 0.00390625f + f3, (f19 + 0.0f) * 0.00390625f + f4).n_1700_B(f5, f6, f7, 0.8f).normal(0.0f, 1.0f, 0.0f).endVertex();
                        bufferIn.pos(f18 + 0.0f, f17 + 4.0f - 9.765625E-4f, f19 + 0.0f).tex((f18 + 0.0f) * 0.00390625f + f3, (f19 + 0.0f) * 0.00390625f + f4).n_1700_B(f5, f6, f7, 0.8f).normal(0.0f, 1.0f, 0.0f).endVertex();
                    }
                    if (k > -1) {
                        for (int i1 = 0; i1 < 8; ++i1) {
                            bufferIn.pos(f18 + (float)i1 + 0.0f, f17 + 0.0f, f19 + 8.0f).tex((f18 + (float)i1 + 0.5f) * 0.00390625f + f3, (f19 + 8.0f) * 0.00390625f + f4).n_1700_B(f8, f9, f10, 0.8f).normal(-1.0f, 0.0f, 0.0f).endVertex();
                            bufferIn.pos(f18 + (float)i1 + 0.0f, f17 + 4.0f, f19 + 8.0f).tex((f18 + (float)i1 + 0.5f) * 0.00390625f + f3, (f19 + 8.0f) * 0.00390625f + f4).n_1700_B(f8, f9, f10, 0.8f).normal(-1.0f, 0.0f, 0.0f).endVertex();
                            bufferIn.pos(f18 + (float)i1 + 0.0f, f17 + 4.0f, f19 + 0.0f).tex((f18 + (float)i1 + 0.5f) * 0.00390625f + f3, (f19 + 0.0f) * 0.00390625f + f4).n_1700_B(f8, f9, f10, 0.8f).normal(-1.0f, 0.0f, 0.0f).endVertex();
                            bufferIn.pos(f18 + (float)i1 + 0.0f, f17 + 0.0f, f19 + 0.0f).tex((f18 + (float)i1 + 0.5f) * 0.00390625f + f3, (f19 + 0.0f) * 0.00390625f + f4).n_1700_B(f8, f9, f10, 0.8f).normal(-1.0f, 0.0f, 0.0f).endVertex();
                        }
                    }
                    if (k <= 1) {
                        for (int j2 = 0; j2 < 8; ++j2) {
                            bufferIn.pos(f18 + (float)j2 + 1.0f - 9.765625E-4f, f17 + 0.0f, f19 + 8.0f).tex((f18 + (float)j2 + 0.5f) * 0.00390625f + f3, (f19 + 8.0f) * 0.00390625f + f4).n_1700_B(f8, f9, f10, 0.8f).normal(1.0f, 0.0f, 0.0f).endVertex();
                            bufferIn.pos(f18 + (float)j2 + 1.0f - 9.765625E-4f, f17 + 4.0f, f19 + 8.0f).tex((f18 + (float)j2 + 0.5f) * 0.00390625f + f3, (f19 + 8.0f) * 0.00390625f + f4).n_1700_B(f8, f9, f10, 0.8f).normal(1.0f, 0.0f, 0.0f).endVertex();
                            bufferIn.pos(f18 + (float)j2 + 1.0f - 9.765625E-4f, f17 + 4.0f, f19 + 0.0f).tex((f18 + (float)j2 + 0.5f) * 0.00390625f + f3, (f19 + 0.0f) * 0.00390625f + f4).n_1700_B(f8, f9, f10, 0.8f).normal(1.0f, 0.0f, 0.0f).endVertex();
                            bufferIn.pos(f18 + (float)j2 + 1.0f - 9.765625E-4f, f17 + 0.0f, f19 + 0.0f).tex((f18 + (float)j2 + 0.5f) * 0.00390625f + f3, (f19 + 0.0f) * 0.00390625f + f4).n_1700_B(f8, f9, f10, 0.8f).normal(1.0f, 0.0f, 0.0f).endVertex();
                        }
                    }
                    if (l > -1) {
                        for (int k2 = 0; k2 < 8; ++k2) {
                            bufferIn.pos(f18 + 0.0f, f17 + 4.0f, f19 + (float)k2 + 0.0f).tex((f18 + 0.0f) * 0.00390625f + f3, (f19 + (float)k2 + 0.5f) * 0.00390625f + f4).n_1700_B(f14, f15, f16, 0.8f).normal(0.0f, 0.0f, -1.0f).endVertex();
                            bufferIn.pos(f18 + 8.0f, f17 + 4.0f, f19 + (float)k2 + 0.0f).tex((f18 + 8.0f) * 0.00390625f + f3, (f19 + (float)k2 + 0.5f) * 0.00390625f + f4).n_1700_B(f14, f15, f16, 0.8f).normal(0.0f, 0.0f, -1.0f).endVertex();
                            bufferIn.pos(f18 + 8.0f, f17 + 0.0f, f19 + (float)k2 + 0.0f).tex((f18 + 8.0f) * 0.00390625f + f3, (f19 + (float)k2 + 0.5f) * 0.00390625f + f4).n_1700_B(f14, f15, f16, 0.8f).normal(0.0f, 0.0f, -1.0f).endVertex();
                            bufferIn.pos(f18 + 0.0f, f17 + 0.0f, f19 + (float)k2 + 0.0f).tex((f18 + 0.0f) * 0.00390625f + f3, (f19 + (float)k2 + 0.5f) * 0.00390625f + f4).n_1700_B(f14, f15, f16, 0.8f).normal(0.0f, 0.0f, -1.0f).endVertex();
                        }
                    }
                    if (l > 1) continue;
                    for (int l2 = 0; l2 < 8; ++l2) {
                        bufferIn.pos(f18 + 0.0f, f17 + 4.0f, f19 + (float)l2 + 1.0f - 9.765625E-4f).tex((f18 + 0.0f) * 0.00390625f + f3, (f19 + (float)l2 + 0.5f) * 0.00390625f + f4).n_1700_B(f14, f15, f16, 0.8f).normal(0.0f, 0.0f, 1.0f).endVertex();
                        bufferIn.pos(f18 + 8.0f, f17 + 4.0f, f19 + (float)l2 + 1.0f - 9.765625E-4f).tex((f18 + 8.0f) * 0.00390625f + f3, (f19 + (float)l2 + 0.5f) * 0.00390625f + f4).n_1700_B(f14, f15, f16, 0.8f).normal(0.0f, 0.0f, 1.0f).endVertex();
                        bufferIn.pos(f18 + 8.0f, f17 + 0.0f, f19 + (float)l2 + 1.0f - 9.765625E-4f).tex((f18 + 8.0f) * 0.00390625f + f3, (f19 + (float)l2 + 0.5f) * 0.00390625f + f4).n_1700_B(f14, f15, f16, 0.8f).normal(0.0f, 0.0f, 1.0f).endVertex();
                        bufferIn.pos(f18 + 0.0f, f17 + 0.0f, f19 + (float)l2 + 1.0f - 9.765625E-4f).tex((f18 + 0.0f) * 0.00390625f + f3, (f19 + (float)l2 + 0.5f) * 0.00390625f + f4).n_1700_B(f14, f15, f16, 0.8f).normal(0.0f, 0.0f, 1.0f).endVertex();
                    }
                }
            }
        } else {
            boolean j1 = true;
            int k1 = 32;
            for (int l1 = -32; l1 < 32; l1 += 32) {
                for (int i2 = -32; i2 < 32; i2 += 32) {
                    bufferIn.pos(l1 + 0, f17, i2 + 32).tex((float)(l1 + 0) * 0.00390625f + f3, (float)(i2 + 32) * 0.00390625f + f4).n_1700_B(f5, f6, f7, 0.8f).normal(0.0f, -1.0f, 0.0f).endVertex();
                    bufferIn.pos(l1 + 32, f17, i2 + 32).tex((float)(l1 + 32) * 0.00390625f + f3, (float)(i2 + 32) * 0.00390625f + f4).n_1700_B(f5, f6, f7, 0.8f).normal(0.0f, -1.0f, 0.0f).endVertex();
                    bufferIn.pos(l1 + 32, f17, i2 + 0).tex((float)(l1 + 32) * 0.00390625f + f3, (float)(i2 + 0) * 0.00390625f + f4).n_1700_B(f5, f6, f7, 0.8f).normal(0.0f, -1.0f, 0.0f).endVertex();
                    bufferIn.pos(l1 + 0, f17, i2 + 0).tex((float)(l1 + 0) * 0.00390625f + f3, (float)(i2 + 0) * 0.00390625f + f4).n_1700_B(f5, f6, f7, 0.8f).normal(0.0f, -1.0f, 0.0f).endVertex();
                }
            }
        }
    }

    public void u_2550_I() {
        this.n_1700_B(j_3341_s.R_4764_Y());
    }

    public void n_1700_B(long finishTimeNano) {
        z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender2;
        Iterator iterator2;
        finishTimeNano = (long)((double)finishTimeNano + 1.0E8);
        this.V_1446_Y |= this.Ping.R_4764_Y();
        long i = j_3341_s.R_4764_Y();
        boolean j = false;
        if (this.P_1922_E.size() > 0) {
            z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender;
            Iterator iterator = this.P_1922_E.iterator();
            while (iterator.hasNext() && this.Ping.R_4764_Y(chunkrenderdispatcher$chunkrender = (z_4547_I.n_1700_B)iterator.next())) {
                chunkrenderdispatcher$chunkrender.u_1723_Y();
                iterator.remove();
                this.C_2741_M.remove(chunkrenderdispatcher$chunkrender);
                this.G_564_y.remove(chunkrenderdispatcher$chunkrender);
            }
        }
        if (this.G_564_y.size() > 0 && (iterator2 = this.G_564_y.iterator()).hasNext() && this.Ping.G_564_y(chunkrenderdispatcher$chunkrender2 = (z_4547_I.n_1700_B)iterator2.next())) {
            iterator2.remove();
        }
        double d1 = 0.0;
        int k = Config.getUpdatesPerFrame();
        if (!this.C_2741_M.isEmpty()) {
            Iterator<z_4547_I.n_1700_B> iterator1 = this.C_2741_M.iterator();
            while (iterator1.hasNext()) {
                double d0;
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender1 = iterator1.next();
                boolean flag1 = chunkrenderdispatcher$chunkrender1.h_1847_R();
                boolean flag = !chunkrenderdispatcher$chunkrender1.w_1484_f() && !flag1 ? this.Ping.R_4764_Y(chunkrenderdispatcher$chunkrender1) : this.Ping.J_1907_R(chunkrenderdispatcher$chunkrender1);
                if (!flag) break;
                chunkrenderdispatcher$chunkrender1.u_1723_Y();
                iterator1.remove();
                if (flag1 || !((d1 += (d0 = 2.0 * RenderChunkUtils.getRelativeBufferSize(chunkrenderdispatcher$chunkrender1))) > (double)k)) continue;
                break;
            }
        }
    }

    private void R_4764_Y(h_3572_K activeRenderInfoIn) {
        D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
        T_603_v worldborder = this.Q_2552_b.H_2857_Y();
        double d0 = this.multiplayerClientSuggestionProvider.P_4830_p.J_1907_R * 16;
        if (!(activeRenderInfoIn.J_1907_R().J_1907_R < worldborder.v_4262_N() - d0 && activeRenderInfoIn.J_1907_R().J_1907_R > worldborder.P_1922_E() + d0 && activeRenderInfoIn.J_1907_R().G_564_y < worldborder.w_1484_f() - d0 && activeRenderInfoIn.J_1907_R().G_564_y > worldborder.u_1723_Y() + d0)) {
            if (Config.isShaders()) {
                Shaders.pushProgram();
                Shaders.useProgram(Shaders.ProgramTexturedLit);
                Shaders.setRenderStage(RenderStage.WORLD_BORDER);
            }
            double d1 = 1.0 - worldborder.n_1700_B(activeRenderInfoIn.J_1907_R().J_1907_R, activeRenderInfoIn.J_1907_R().G_564_y) / d0;
            d1 = Math.pow(d1, 4.0);
            double d2 = activeRenderInfoIn.J_1907_R().J_1907_R;
            double d3 = activeRenderInfoIn.J_1907_R().R_4764_Y;
            double d4 = activeRenderInfoIn.J_1907_R().G_564_y;
            lightning.product.c_4037_x.Y_601_j();
            lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
            lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.h_1847_R);
            this.w_1457_N.n_1700_B(Q_4569_t);
            lightning.product.c_4037_x.J_1907_R(MinecraftClient.c_3005_b());
            lightning.product.c_4037_x.v_4276_D();
            int i = worldborder.G_564_y().n_1700_B();
            float f = (float)(i >> 16 & 0xFF) / 255.0f;
            float f1 = (float)(i >> 8 & 0xFF) / 255.0f;
            float f2 = (float)(i & 0xFF) / 255.0f;
            lightning.product.c_4037_x.G_564_y(f, f1, f2, (float)d1);
            lightning.product.c_4037_x.n_1700_B(-3.0f, -3.0f);
            lightning.product.c_4037_x.Z_875_P();
            lightning.product.c_4037_x.l_1233_K();
            lightning.product.c_4037_x.M_588_G();
            lightning.product.c_4037_x.q_2307_F();
            float f3 = (float)(j_3341_s.J_1907_R() % 3000L) / 3000.0f;
            float f4 = 0.0f;
            float f5 = 0.0f;
            float f6 = 128.0f;
            bufferbuilder.n_1700_B(7, E_688_b.Q_2552_b);
            double d5 = Math.max((double)u_530_F.R_4764_Y(d4 - d0), worldborder.u_1723_Y());
            double d6 = Math.min((double)u_530_F.P_1922_E(d4 + d0), worldborder.w_1484_f());
            if (d2 > worldborder.v_4262_N() - d0) {
                float f7 = 0.0f;
                double d7 = d5;
                while (d7 < d6) {
                    double d8 = Math.min(1.0, d6 - d7);
                    float f8 = (float)d8 * 0.5f;
                    this.n_1700_B(bufferbuilder, d2, d3, d4, worldborder.v_4262_N(), 256, d7, f3 + f7, f3 + 0.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, worldborder.v_4262_N(), 256, d7 + d8, f3 + f8 + f7, f3 + 0.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, worldborder.v_4262_N(), 0, d7 + d8, f3 + f8 + f7, f3 + 128.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, worldborder.v_4262_N(), 0, d7, f3 + f7, f3 + 128.0f);
                    d7 += 1.0;
                    f7 += 0.5f;
                }
            }
            if (d2 < worldborder.P_1922_E() + d0) {
                float f9 = 0.0f;
                double d9 = d5;
                while (d9 < d6) {
                    double d12 = Math.min(1.0, d6 - d9);
                    float f12 = (float)d12 * 0.5f;
                    this.n_1700_B(bufferbuilder, d2, d3, d4, worldborder.P_1922_E(), 256, d9, f3 + f9, f3 + 0.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, worldborder.P_1922_E(), 256, d9 + d12, f3 + f12 + f9, f3 + 0.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, worldborder.P_1922_E(), 0, d9 + d12, f3 + f12 + f9, f3 + 128.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, worldborder.P_1922_E(), 0, d9, f3 + f9, f3 + 128.0f);
                    d9 += 1.0;
                    f9 += 0.5f;
                }
            }
            d5 = Math.max((double)u_530_F.R_4764_Y(d2 - d0), worldborder.P_1922_E());
            d6 = Math.min((double)u_530_F.P_1922_E(d2 + d0), worldborder.v_4262_N());
            if (d4 > worldborder.w_1484_f() - d0) {
                float f10 = 0.0f;
                double d10 = d5;
                while (d10 < d6) {
                    double d13 = Math.min(1.0, d6 - d10);
                    float f13 = (float)d13 * 0.5f;
                    this.n_1700_B(bufferbuilder, d2, d3, d4, d10, 256, worldborder.w_1484_f(), f3 + f10, f3 + 0.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, d10 + d13, 256, worldborder.w_1484_f(), f3 + f13 + f10, f3 + 0.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, d10 + d13, 0, worldborder.w_1484_f(), f3 + f13 + f10, f3 + 128.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, d10, 0, worldborder.w_1484_f(), f3 + f10, f3 + 128.0f);
                    d10 += 1.0;
                    f10 += 0.5f;
                }
            }
            if (d4 < worldborder.u_1723_Y() + d0) {
                float f11 = 0.0f;
                double d11 = d5;
                while (d11 < d6) {
                    double d14 = Math.min(1.0, d6 - d11);
                    float f14 = (float)d14 * 0.5f;
                    this.n_1700_B(bufferbuilder, d2, d3, d4, d11, 256, worldborder.u_1723_Y(), f3 + f11, f3 + 0.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, d11 + d14, 256, worldborder.u_1723_Y(), f3 + f14 + f11, f3 + 0.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, d11 + d14, 0, worldborder.u_1723_Y(), f3 + f14 + f11, f3 + 128.0f);
                    this.n_1700_B(bufferbuilder, d2, d3, d4, d11, 0, worldborder.u_1723_Y(), f3 + f11, f3 + 128.0f);
                    d11 += 1.0;
                    f11 += 0.5f;
                }
            }
            bufferbuilder.u_1723_Y();
            o_2840_r.n_1700_B(bufferbuilder);
            lightning.product.c_4037_x.k_2293_S();
            lightning.product.c_4037_x.u_2550_I();
            lightning.product.c_4037_x.n_1700_B(0.0f, 0.0f);
            lightning.product.c_4037_x.c_3005_b();
            lightning.product.c_4037_x.M_588_G();
            lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.h_1847_R);
            lightning.product.c_4037_x.Y_259_p();
            lightning.product.c_4037_x.d_2461_k();
            lightning.product.c_4037_x.J_1907_R(true);
            if (Config.isShaders()) {
                Shaders.popProgram();
                Shaders.setRenderStage(RenderStage.NONE);
            }
        }
    }

    private void n_1700_B(D_3318_r bufferIn, double camX, double camY, double camZ, double xIn, int yIn, double zIn, float texU, float texV) {
        bufferIn.pos(xIn - camX, (double)yIn - camY, zIn - camZ).tex(texU, texV).endVertex();
    }

    private void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, N_4263_v entityIn, double xIn, double yIn, double zIn, c_1514_x blockPosIn, K_4074_S blockStateIn) {
        if (!Config.isCustomEntityModels() || !CustomEntityModels.isCustomModel(blockStateIn)) {
            z_883_p.J_1907_R(matrixStackIn, bufferIn, blockStateIn.n_1700_B((BlockGetter)this.Q_2552_b, blockPosIn, CollisionContext.n_1700_B(entityIn)), (double)blockPosIn.getX() - xIn, (double)blockPosIn.getY() - yIn, (double)blockPosIn.getZ() - zIn, 0.0f, 0.0f, 0.0f, 0.4f);
        }
    }

    public static void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, s_1395_c shapeIn, double xIn, double yIn, double zIn, float red, float green, float blue, float alpha) {
        List<I_4817_s> list = shapeIn.G_564_y();
        int i = u_530_F.P_1922_E((double)list.size() / 3.0);
        for (int j = 0; j < list.size(); ++j) {
            I_4817_s axisalignedbb = list.get(j);
            float f = ((float)j % (float)i + 1.0f) / (float)i;
            float f1 = j / i;
            float f2 = f * (float)(f1 == 0.0f ? 1 : 0);
            float f3 = f * (float)(f1 == 1.0f ? 1 : 0);
            float f4 = f * (float)(f1 == 2.0f ? 1 : 0);
            z_883_p.J_1907_R(matrixStackIn, bufferIn, x_268_Y.n_1700_B(axisalignedbb.offset(0.0, 0.0, 0.0)), xIn, yIn, zIn, f2, f3, f4, 1.0f);
        }
    }

    private static void J_1907_R(g_221_o matrixStackIn, D_4792_h bufferIn, s_1395_c shapeIn, double xIn, double yIn, double zIn, float red, float green, float blue, float alpha) {
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        shapeIn.n_1700_B((double p_lambda$drawShape$6_12_, double p_lambda$drawShape$6_14_, double p_lambda$drawShape$6_16_, double p_lambda$drawShape$6_18_, double p_lambda$drawShape$6_20_, double p_lambda$drawShape$6_22_) -> {
            bufferIn.n_1700_B(matrix4f, (float)(p_lambda$drawShape$6_12_ + xIn), (float)(p_lambda$drawShape$6_14_ + yIn), (float)(p_lambda$drawShape$6_16_ + zIn)).n_1700_B(red, green, blue, alpha).endVertex();
            bufferIn.n_1700_B(matrix4f, (float)(p_lambda$drawShape$6_18_ + xIn), (float)(p_lambda$drawShape$6_20_ + yIn), (float)(p_lambda$drawShape$6_22_ + zIn)).n_1700_B(red, green, blue, alpha).endVertex();
        });
    }

    public static void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, I_4817_s aabbIn, float red, float green, float blue, float alpha) {
        z_883_p.n_1700_B(matrixStackIn, bufferIn, aabbIn.minX, aabbIn.minY, aabbIn.minZ, aabbIn.maxX, aabbIn.maxY, aabbIn.maxZ, red, green, blue, alpha, red, green, blue);
    }

    public static void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        z_883_p.n_1700_B(matrixStackIn, bufferIn, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha, red, green, blue);
    }

    public static void n_1700_B(g_221_o matrixStackIn, D_4792_h bufferIn, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha, float red2, float green2, float blue2) {
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        float f = (float)minX;
        float f1 = (float)minY;
        float f2 = (float)minZ;
        float f3 = (float)maxX;
        float f4 = (float)maxY;
        float f5 = (float)maxZ;
        bufferIn.n_1700_B(matrix4f, f, f1, f2).n_1700_B(red, green2, blue2, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f1, f2).n_1700_B(red, green2, blue2, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f1, f2).n_1700_B(red2, green, blue2, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f4, f2).n_1700_B(red2, green, blue2, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f1, f2).n_1700_B(red2, green2, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f1, f5).n_1700_B(red2, green2, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f1, f2).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f4, f2).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f4, f2).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f4, f2).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f4, f2).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f4, f5).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f4, f5).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f1, f5).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f1, f5).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f1, f5).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f1, f5).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f1, f2).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f, f4, f5).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f4, f5).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f1, f5).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f4, f5).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f4, f2).n_1700_B(red, green, blue, alpha).endVertex();
        bufferIn.n_1700_B(matrix4f, f3, f4, f5).n_1700_B(red, green, blue, alpha).endVertex();
    }

    public static void n_1700_B(D_3318_r builder, double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha) {
        builder.pos(x1, y1, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y1, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y1, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y1, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y2, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y2, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y2, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y1, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y2, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y1, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y1, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y1, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y2, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y2, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y2, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y1, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y2, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y1, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y1, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y1, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y1, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y1, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y1, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y2, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y2, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x1, y2, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y2, z1).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y2, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y2, z2).n_1700_B(red, green, blue, alpha).endVertex();
        builder.pos(x2, y2, z2).n_1700_B(red, green, blue, alpha).endVertex();
    }

    public void n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S oldState, K_4074_S newState, int flags) {
        this.n_1700_B(pos, (flags & 8) != 0);
    }

    private void n_1700_B(c_1514_x posIn, boolean rerenderOnMainThread) {
        for (int i = posIn.getZ() - 1; i <= posIn.getZ() + 1; ++i) {
            for (int j = posIn.getX() - 1; j <= posIn.getX() + 1; ++j) {
                for (int k = posIn.getY() - 1; k <= posIn.getY() + 1; ++k) {
                    this.n_1700_B(j >> 4, k >> 4, i >> 4, rerenderOnMainThread);
                }
            }
        }
    }

    public void n_1700_B(int x1, int y1, int z1, int x2, int y2, int z2) {
        for (int i = z1 - 1; i <= z2 + 1; ++i) {
            for (int j = x1 - 1; j <= x2 + 1; ++j) {
                for (int k = y1 - 1; k <= y2 + 1; ++k) {
                    this.J_1907_R(j >> 4, k >> 4, i >> 4);
                }
            }
        }
    }

    public void n_1700_B(c_1514_x blockPosIn, K_4074_S oldState, K_4074_S newState) {
        if (this.multiplayerClientSuggestionProvider.h_4320_q().n_1700_B(oldState, newState)) {
            this.n_1700_B(blockPosIn.getX(), blockPosIn.getY(), blockPosIn.getZ(), blockPosIn.getX(), blockPosIn.getY(), blockPosIn.getZ());
        }
    }

    public void n_1700_B(int sectionX, int sectionY, int sectionZ) {
        for (int i = sectionZ - 1; i <= sectionZ + 1; ++i) {
            for (int j = sectionX - 1; j <= sectionX + 1; ++j) {
                for (int k = sectionY - 1; k <= sectionY + 1; ++k) {
                    this.J_1907_R(j, k, i);
                }
            }
        }
    }

    public void J_1907_R(int sectionX, int sectionY, int sectionZ) {
        this.n_1700_B(sectionX, sectionY, sectionZ, false);
    }

    private void n_1700_B(int sectionX, int sectionY, int sectionZ, boolean rerenderOnMainThread) {
        this.Z_875_P.n_1700_B(sectionX, sectionY, sectionZ, rerenderOnMainThread);
    }

    public void n_1700_B(@Nullable SoundEvent soundIn, c_1514_x pos) {
        this.n_1700_B(soundIn, pos, soundIn == null ? null : h_3036_f.n_1700_B(soundIn));
    }

    public void n_1700_B(@Nullable SoundEvent p_playRecord_1_, c_1514_x p_playRecord_2_, @Nullable h_3036_f p_playRecord_3_) {
        SoundInstance isound = this.v_4276_D.get(p_playRecord_2_);
        if (isound != null) {
            this.multiplayerClientSuggestionProvider.Z_976_R().J_1907_R(isound);
            this.v_4276_D.remove(p_playRecord_2_);
        }
        if (p_playRecord_1_ != null) {
            h_3036_f musicdiscitem = h_3036_f.n_1700_B(p_playRecord_1_);
            if (Reflector.MinecraftForgeClient.exists()) {
                musicdiscitem = p_playRecord_3_;
            }
            if (musicdiscitem != null) {
                this.multiplayerClientSuggestionProvider.M_588_G.n_1700_B(musicdiscitem.w_1484_f());
            }
            SimpleSoundInstance isound1 = SimpleSoundInstance.n_1700_B(p_playRecord_1_, p_playRecord_2_.getX(), p_playRecord_2_.getY(), p_playRecord_2_.getZ());
            this.v_4276_D.put(p_playRecord_2_, isound1);
            this.multiplayerClientSuggestionProvider.Z_976_R().n_1700_B(isound1);
        }
        this.n_1700_B(this.Q_2552_b, p_playRecord_2_, p_playRecord_1_ != null);
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos, boolean isPartying) {
        for (r_4811_B livingentity : worldIn.n_1700_B(r_4811_B.class, new I_4817_s(pos).grow(3.0))) {
            livingentity.n_1700_B(pos, isPartying);
        }
    }

    public void n_1700_B(ParticleOptions particleData, boolean alwaysRender, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        this.n_1700_B(particleData, alwaysRender, false, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    public void n_1700_B(ParticleOptions particleData, boolean ignoreRange, boolean minimizeLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        try {
            this.J_1907_R(particleData, ignoreRange, minimizeLevel, x, y, z, xSpeed, ySpeed, zSpeed);
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Exception while adding particle");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Particle being added");
            crashreportcategory.n_1700_B("ID", V_3137_a.g_164_R.J_1907_R(particleData.G_564_y()));
            crashreportcategory.n_1700_B("Parameters", particleData.R_4764_Y());
            crashreportcategory.n_1700_B("Position", () -> CrashReportCategory.n_1700_B(x, y, z));
            throw new ReportedException(crashreport);
        }
    }

    private <T extends ParticleOptions> void n_1700_B(T particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        this.n_1700_B(particleData, particleData.G_564_y().P_1922_E(), x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @Nullable
    private c_3457_g J_1907_R(ParticleOptions particleData, boolean alwaysRender, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        return this.J_1907_R(particleData, alwaysRender, false, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @Nullable
    private c_3457_g J_1907_R(ParticleOptions particleData, boolean alwaysRender, boolean minimizeLevel, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        h_3572_K activerenderinfo = this.multiplayerClientSuggestionProvider.s_956_w.M_588_G();
        if (this.multiplayerClientSuggestionProvider != null && activerenderinfo.w_1484_f() && this.multiplayerClientSuggestionProvider.v_4262_N != null) {
            j_4067_x particlestatus = this.n_1700_B(minimizeLevel);
            if (particleData == ParticleTypes.Q_2552_b && !Config.isAnimatedExplosion()) {
                return null;
            }
            if (particleData == ParticleTypes.C_2741_M && !Config.isAnimatedExplosion()) {
                return null;
            }
            if (particleData == ParticleTypes.z_4693_k && !Config.isAnimatedExplosion()) {
                return null;
            }
            if (particleData == ParticleTypes.c_4037_x && !Config.isWaterParticles()) {
                return null;
            }
            if (particleData == ParticleTypes.B_1668_F && !Config.isAnimatedSmoke()) {
                return null;
            }
            if (particleData == ParticleTypes.d_2461_k && !Config.isAnimatedSmoke()) {
                return null;
            }
            if (particleData == ParticleTypes.Y_259_p && !Config.isPotionParticles()) {
                return null;
            }
            if (particleData == ParticleTypes.n_1700_B && !Config.isPotionParticles()) {
                return null;
            }
            if (particleData == ParticleTypes.M_182_A && !Config.isPotionParticles()) {
                return null;
            }
            if (particleData == ParticleTypes.n_3318_d && !Config.isPotionParticles()) {
                return null;
            }
            if (particleData == ParticleTypes.T_3594_S && !Config.isPotionParticles()) {
                return null;
            }
            if (particleData == ParticleTypes.g_221_o && !Config.isPortalParticles()) {
                return null;
            }
            if (particleData == ParticleTypes.c_3005_b && !Config.isAnimatedFlame()) {
                return null;
            }
            if (particleData == ParticleTypes.H_2857_Y && !Config.isAnimatedFlame()) {
                return null;
            }
            if (particleData == ParticleTypes.Q_4569_t && !Config.isAnimatedRedstone()) {
                return null;
            }
            if (particleData == ParticleTypes.P_4830_p && !Config.isDrippingWaterLava()) {
                return null;
            }
            if (particleData == ParticleTypes.s_956_w && !Config.isDrippingWaterLava()) {
                return null;
            }
            if (particleData == ParticleTypes.q_2307_F && !Config.isFireworkParticles()) {
                return null;
            }
            if (!alwaysRender) {
                double d0 = 1024.0;
                if (particleData == ParticleTypes.v_4262_N) {
                    d0 = 38416.0;
                }
                if (activerenderinfo.J_1907_R().R_4764_Y(x, y, z) > d0) {
                    return null;
                }
                if (particlestatus == j_4067_x.R_4764_Y) {
                    return null;
                }
            }
            c_3457_g particle = this.multiplayerClientSuggestionProvider.v_4262_N.n_1700_B(particleData, x, y, z, xSpeed, ySpeed, zSpeed);
            if (particleData == ParticleTypes.P_1922_E) {
                CustomColors.updateWaterFX(particle, this.Q_2552_b, x, y, z, this.RealmsDefaultUncaughtExceptionHandler);
            }
            if (particleData == ParticleTypes.g_2268_R) {
                CustomColors.updateWaterFX(particle, this.Q_2552_b, x, y, z, this.RealmsDefaultUncaughtExceptionHandler);
            }
            if (particleData == ParticleTypes.e_2887_G) {
                CustomColors.updateWaterFX(particle, this.Q_2552_b, x, y, z, this.RealmsDefaultUncaughtExceptionHandler);
            }
            if (particleData == ParticleTypes.T_2506_i) {
                CustomColors.updateMyceliumFX(particle);
            }
            if (particleData == ParticleTypes.g_221_o) {
                CustomColors.updatePortalFX(particle);
            }
            if (particleData == ParticleTypes.Q_4569_t) {
                CustomColors.updateReddustFX(particle, this.Q_2552_b, x, y, z);
            }
            return particle;
        }
        return null;
    }

    private j_4067_x n_1700_B(boolean minimiseLevel) {
        j_4067_x particlestatus = this.multiplayerClientSuggestionProvider.P_4830_p.RealmsClientOutdatedScreen;
        if (minimiseLevel && particlestatus == j_4067_x.R_4764_Y && this.Q_2552_b.w_1457_N.nextInt(10) == 0) {
            particlestatus = j_4067_x.J_1907_R;
        }
        if (particlestatus == j_4067_x.J_1907_R && this.Q_2552_b.w_1457_N.nextInt(3) == 0) {
            particlestatus = j_4067_x.R_4764_Y;
        }
        return particlestatus;
    }

    public void M_588_G() {
    }

    public void n_1700_B(int soundID, c_1514_x pos, int data) {
        switch (soundID) {
            case 1023: 
            case 1028: 
            case 1038: {
                h_3572_K activerenderinfo = this.multiplayerClientSuggestionProvider.s_956_w.M_588_G();
                if (!activerenderinfo.w_1484_f()) break;
                double d0 = (double)pos.getX() - activerenderinfo.J_1907_R().J_1907_R;
                double d1 = (double)pos.getY() - activerenderinfo.J_1907_R().R_4764_Y;
                double d2 = (double)pos.getZ() - activerenderinfo.J_1907_R().G_564_y;
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                double d4 = activerenderinfo.J_1907_R().J_1907_R;
                double d5 = activerenderinfo.J_1907_R().R_4764_Y;
                double d6 = activerenderinfo.J_1907_R().G_564_y;
                if (d3 > 0.0) {
                    d4 += d0 / d3 * 2.0;
                    d5 += d1 / d3 * 2.0;
                    d6 += d2 / d3 * 2.0;
                }
                if (soundID == 1023) {
                    this.n_1700_B(d4, d5, d6, SoundEvents.y_3008_A, D_38_f.u_1723_Y, 1.0f, 1.0f, false);
                    break;
                }
                if (soundID == 1038) {
                    this.n_1700_B(d4, d5, d6, SoundEvents.i_789_Q, D_38_f.u_1723_Y, 1.0f, 1.0f, false);
                    break;
                }
                this.n_1700_B(d4, d5, d6, SoundEvents.Q_2753_H, D_38_f.u_1723_Y, 5.0f, 1.0f, false);
            }
        }
    }

    public void n_1700_B(a_3913_L player, int type, c_1514_x blockPosIn, int data) {
        Random random = this.Q_2552_b.w_1457_N;
        switch (type) {
            case 1000: {
                this.n_1700_B(blockPosIn, SoundEvents.n_3864_h, D_38_f.P_1922_E, 1.0f, 1.0f, false);
                break;
            }
            case 1001: {
                this.n_1700_B(blockPosIn, SoundEvents.o_3599_Z, D_38_f.P_1922_E, 1.0f, 1.2f, false);
                break;
            }
            case 1002: {
                this.n_1700_B(blockPosIn, SoundEvents.X_290_I, D_38_f.P_1922_E, 1.0f, 1.2f, false);
                break;
            }
            case 1003: {
                this.n_1700_B(blockPosIn, SoundEvents.I_3457_f, D_38_f.v_4262_N, 1.0f, 1.2f, false);
                break;
            }
            case 1004: {
                this.n_1700_B(blockPosIn, SoundEvents.F_391_H, D_38_f.v_4262_N, 1.0f, 1.2f, false);
                break;
            }
            case 1005: {
                this.n_1700_B(blockPosIn, SoundEvents.l_1757_S, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1006: {
                this.n_1700_B(blockPosIn, SoundEvents.SpreadingSnowyDirtBlock, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1007: {
                this.n_1700_B(blockPosIn, SoundEvents.D_265_n, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1008: {
                this.n_1700_B(blockPosIn, SoundEvents.W_3729_Q, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1009: {
                this.n_1700_B(blockPosIn, SoundEvents.V_1665_T, D_38_f.P_1922_E, 0.5f, 2.6f + (random.nextFloat() - random.nextFloat()) * 0.8f, false);
                break;
            }
            case 1010: {
                if (q_1613_l.J_1907_R(data) instanceof h_3036_f) {
                    if (Reflector.MinecraftForgeClient.exists()) {
                        this.n_1700_B(((h_3036_f)q_1613_l.J_1907_R(data)).t_148_a(), blockPosIn, (h_3036_f)q_1613_l.J_1907_R(data));
                        break;
                    }
                    this.n_1700_B(((h_3036_f)q_1613_l.J_1907_R(data)).t_148_a(), blockPosIn);
                    break;
                }
                this.n_1700_B((SoundEvent)null, blockPosIn);
                break;
            }
            case 1011: {
                this.n_1700_B(blockPosIn, SoundEvents.P_459_I, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1012: {
                this.n_1700_B(blockPosIn, SoundEvents.SpongeBlock, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1013: {
                this.n_1700_B(blockPosIn, SoundEvents.F_4312_i, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1014: {
                this.n_1700_B(blockPosIn, SoundEvents.O_2761_o, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1015: {
                this.n_1700_B(blockPosIn, SoundEvents.V_537_k, D_38_f.u_1723_Y, 10.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1016: {
                this.n_1700_B(blockPosIn, SoundEvents.U_1697_c, D_38_f.u_1723_Y, 10.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1017: {
                this.n_1700_B(blockPosIn, SoundEvents.I_4481_g, D_38_f.u_1723_Y, 10.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1018: {
                this.n_1700_B(blockPosIn, SoundEvents.RealmsDefaultUncaughtExceptionHandler, D_38_f.u_1723_Y, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1019: {
                this.n_1700_B(blockPosIn, SoundEvents.u_1147_u, D_38_f.u_1723_Y, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1020: {
                this.n_1700_B(blockPosIn, SoundEvents.M_3703_h, D_38_f.u_1723_Y, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1021: {
                this.n_1700_B(blockPosIn, SoundEvents.Q_4220_D, D_38_f.u_1723_Y, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1022: {
                this.n_1700_B(blockPosIn, SoundEvents.Q_2342_H, D_38_f.u_1723_Y, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1024: {
                this.n_1700_B(blockPosIn, SoundEvents.InfestedBlock, D_38_f.u_1723_Y, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1025: {
                this.n_1700_B(blockPosIn, SoundEvents.h_4320_q, D_38_f.v_4262_N, 0.05f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1026: {
                this.n_1700_B(blockPosIn, SoundEvents.G_4243_y, D_38_f.u_1723_Y, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1027: {
                this.n_1700_B(blockPosIn, SoundEvents.Q_3125_S, D_38_f.v_4262_N, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1029: {
                this.n_1700_B(blockPosIn, SoundEvents.A_4115_X, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1030: {
                this.n_1700_B(blockPosIn, SoundEvents.d_2427_y, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1031: {
                this.n_1700_B(blockPosIn, SoundEvents.x_607_J, D_38_f.P_1922_E, 0.3f, this.Q_2552_b.w_1457_N.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1032: {
                this.multiplayerClientSuggestionProvider.Z_976_R().n_1700_B(SimpleSoundInstance.J_1907_R(SoundEvents.Z_361_l, random.nextFloat() * 0.4f + 0.8f, 0.25f));
                break;
            }
            case 1033: {
                this.n_1700_B(blockPosIn, SoundEvents.x_612_B, D_38_f.P_1922_E, 1.0f, 1.0f, false);
                break;
            }
            case 1034: {
                this.n_1700_B(blockPosIn, SoundEvents.C_1269_X, D_38_f.P_1922_E, 1.0f, 1.0f, false);
                break;
            }
            case 1035: {
                this.n_1700_B(blockPosIn, SoundEvents.RealmsClientOutdatedScreen, D_38_f.P_1922_E, 1.0f, 1.0f, false);
                break;
            }
            case 1036: {
                this.n_1700_B(blockPosIn, SoundEvents.AutoContract, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1037: {
                this.n_1700_B(blockPosIn, SoundEvents.AutoDuel, D_38_f.P_1922_E, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1039: {
                this.n_1700_B(blockPosIn, SoundEvents.M_2029_A, D_38_f.u_1723_Y, 0.3f, this.Q_2552_b.w_1457_N.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1040: {
                this.n_1700_B(blockPosIn, SoundEvents.WallBannerBlock, D_38_f.v_4262_N, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1041: {
                this.n_1700_B(blockPosIn, SoundEvents.W_1707_M, D_38_f.v_4262_N, 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1042: {
                this.n_1700_B(blockPosIn, SoundEvents.NumberSetting, D_38_f.P_1922_E, 1.0f, this.Q_2552_b.w_1457_N.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1043: {
                this.n_1700_B(blockPosIn, SoundEvents.S_980_j, D_38_f.P_1922_E, 1.0f, this.Q_2552_b.w_1457_N.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1044: {
                this.n_1700_B(blockPosIn, SoundEvents.O_2369_F, D_38_f.P_1922_E, 1.0f, this.Q_2552_b.w_1457_N.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1500: {
                a_648_i.n_1700_B(this.Q_2552_b, blockPosIn, data > 0);
                break;
            }
            case 1501: {
                this.n_1700_B(blockPosIn, SoundEvents.OpenWalls, D_38_f.P_1922_E, 0.5f, 2.6f + (random.nextFloat() - random.nextFloat()) * 0.8f, false);
                for (int l1 = 0; l1 < 8; ++l1) {
                    this.Q_2552_b.n_1700_B(ParticleTypes.d_2461_k, (double)blockPosIn.getX() + random.nextDouble(), (double)blockPosIn.getY() + 1.2, (double)blockPosIn.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
                }
                break;
            }
            case 1502: {
                this.n_1700_B(blockPosIn, SoundEvents.CriterionTrigger, D_38_f.P_1922_E, 0.5f, 2.6f + (random.nextFloat() - random.nextFloat()) * 0.8f, false);
                for (int k1 = 0; k1 < 5; ++k1) {
                    double d14 = (double)blockPosIn.getX() + random.nextDouble() * 0.6 + 0.2;
                    double d16 = (double)blockPosIn.getY() + random.nextDouble() * 0.6 + 0.2;
                    double d17 = (double)blockPosIn.getZ() + random.nextDouble() * 0.6 + 0.2;
                    this.Q_2552_b.n_1700_B(ParticleTypes.B_1668_F, d14, d16, d17, 0.0, 0.0, 0.0);
                }
                break;
            }
            case 1503: {
                this.n_1700_B(blockPosIn, SoundEvents.E_3343_g, D_38_f.P_1922_E, 1.0f, 1.0f, false);
                for (int j1 = 0; j1 < 16; ++j1) {
                    double d13 = (double)blockPosIn.getX() + (5.0 + random.nextDouble() * 6.0) / 16.0;
                    double d15 = (double)blockPosIn.getY() + 0.8125;
                    double d1 = (double)blockPosIn.getZ() + (5.0 + random.nextDouble() * 6.0) / 16.0;
                    this.Q_2552_b.n_1700_B(ParticleTypes.B_1668_F, d13, d15, d1, 0.0, 0.0, 0.0);
                }
                break;
            }
            case 2000: {
                b_257_Y direction = b_257_Y.n_1700_B(data);
                int i = direction.t_148_a();
                int j = direction.s_956_w();
                int k = direction.u_2550_I();
                double d0 = (double)blockPosIn.getX() + (double)i * 0.6 + 0.5;
                double d2 = (double)blockPosIn.getY() + (double)j * 0.6 + 0.5;
                double d3 = (double)blockPosIn.getZ() + (double)k * 0.6 + 0.5;
                for (int i2 = 0; i2 < 10; ++i2) {
                    double d18 = random.nextDouble() * 0.2 + 0.01;
                    double d19 = d0 + (double)i * 0.01 + (random.nextDouble() - 0.5) * (double)k * 0.5;
                    double d20 = d2 + (double)j * 0.01 + (random.nextDouble() - 0.5) * (double)j * 0.5;
                    double d21 = d3 + (double)k * 0.01 + (random.nextDouble() - 0.5) * (double)i * 0.5;
                    double d22 = (double)i * d18 + random.nextGaussian() * 0.01;
                    double d23 = (double)j * d18 + random.nextGaussian() * 0.01;
                    double d27 = (double)k * d18 + random.nextGaussian() * 0.01;
                    this.n_1700_B(ParticleTypes.B_1668_F, d19, d20, d21, d22, d23, d27);
                }
                break;
            }
            case 2001: {
                K_4074_S blockstate = T_2915_h.n_1700_B(data);
                if (!ReflectorForge.isAir(blockstate, this.Q_2552_b, blockPosIn)) {
                    SoundType soundtype = blockstate.Q_4569_t();
                    if (Reflector.IForgeBlockState_getSoundType3.exists()) {
                        soundtype = (SoundType)Reflector.call(blockstate, Reflector.IForgeBlockState_getSoundType3, this.Q_2552_b, blockPosIn, null);
                    }
                    this.n_1700_B(blockPosIn, soundtype.R_4764_Y(), D_38_f.P_1922_E, (soundtype.n_1700_B() + 1.0f) / 2.0f, soundtype.J_1907_R() * 0.8f, false);
                }
                h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.w_1484_f);
                lightning.product.A_4115_X.n_1700_B(event);
                if (event.n_1700_B()) break;
                this.multiplayerClientSuggestionProvider.v_4262_N.n_1700_B(blockPosIn, blockstate);
                break;
            }
            case 2002: 
            case 2007: {
                e_2866_D vector3d = e_2866_D.R_4764_Y(blockPosIn);
                for (int l = 0; l < 8; ++l) {
                    this.n_1700_B(new N_3869_i(ParticleTypes.d_2427_y, new Z_1993_T(Items.g_2492_v)), vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, random.nextGaussian() * 0.15, random.nextDouble() * 0.2, random.nextGaussian() * 0.15);
                }
                float f5 = (float)(data >> 16 & 0xFF) / 255.0f;
                float f = (float)(data >> 8 & 0xFF) / 255.0f;
                float f1 = (float)(data >> 0 & 0xFF) / 255.0f;
                SimpleParticleType iparticledata = type == 2007 ? ParticleTypes.n_3318_d : ParticleTypes.M_182_A;
                for (int j2 = 0; j2 < 100; ++j2) {
                    double d5 = random.nextDouble() * 4.0;
                    double d7 = random.nextDouble() * Math.PI * 2.0;
                    double d9 = Math.cos(d7) * d5;
                    double d26 = 0.01 + random.nextDouble() * 0.5;
                    double d29 = Math.sin(d7) * d5;
                    c_3457_g particle1 = this.J_1907_R(iparticledata, iparticledata.G_564_y().P_1922_E(), vector3d.J_1907_R + d9 * 0.1, vector3d.R_4764_Y + 0.3, vector3d.G_564_y + d29 * 0.1, d9, d26, d29);
                    if (particle1 == null) continue;
                    float f4 = 0.75f + random.nextFloat() * 0.25f;
                    particle1.n_1700_B(f5 * f4, f * f4, f1 * f4);
                    particle1.R_4764_Y((float)d5);
                }
                this.n_1700_B(blockPosIn, SoundEvents.ChorusFlowerBlock, D_38_f.v_4262_N, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 2003: {
                double d4 = (double)blockPosIn.getX() + 0.5;
                double d6 = blockPosIn.getY();
                double d8 = (double)blockPosIn.getZ() + 0.5;
                for (int i3 = 0; i3 < 8; ++i3) {
                    this.n_1700_B(new N_3869_i(ParticleTypes.d_2427_y, new Z_1993_T(Items.V_1824_v)), d4, d6, d8, random.nextGaussian() * 0.15, random.nextDouble() * 0.2, random.nextGaussian() * 0.15);
                }
                for (double d24 = 0.0; d24 < Math.PI * 2; d24 += 0.15707963267948966) {
                    this.n_1700_B(ParticleTypes.g_221_o, d4 + Math.cos(d24) * 5.0, d6 - 0.4, d8 + Math.sin(d24) * 5.0, Math.cos(d24) * -5.0, 0.0, Math.sin(d24) * -5.0);
                    this.n_1700_B(ParticleTypes.g_221_o, d4 + Math.cos(d24) * 5.0, d6 - 0.4, d8 + Math.sin(d24) * 5.0, Math.cos(d24) * -7.0, 0.0, Math.sin(d24) * -7.0);
                }
                break;
            }
            case 2004: {
                for (int l2 = 0; l2 < 20; ++l2) {
                    double d25 = (double)blockPosIn.getX() + 0.5 + (random.nextDouble() - 0.5) * 2.0;
                    double d28 = (double)blockPosIn.getY() + 0.5 + (random.nextDouble() - 0.5) * 2.0;
                    double d30 = (double)blockPosIn.getZ() + 0.5 + (random.nextDouble() - 0.5) * 2.0;
                    this.Q_2552_b.n_1700_B(ParticleTypes.B_1668_F, d25, d28, d30, 0.0, 0.0, 0.0);
                    this.Q_2552_b.n_1700_B(ParticleTypes.c_3005_b, d25, d28, d30, 0.0, 0.0, 0.0);
                }
                break;
            }
            case 2005: {
                BoneMealItem.n_1700_B((LevelAccessor)this.Q_2552_b, blockPosIn, data);
                break;
            }
            case 2006: {
                for (int k2 = 0; k2 < 200; ++k2) {
                    float f2 = random.nextFloat() * 4.0f;
                    float f3 = random.nextFloat() * ((float)Math.PI * 2);
                    double d10 = u_530_F.J_1907_R(f3) * f2;
                    double d11 = 0.01 + random.nextDouble() * 0.5;
                    double d12 = u_530_F.n_1700_B(f3) * f2;
                    c_3457_g particle = this.J_1907_R(ParticleTypes.t_148_a, false, (double)blockPosIn.getX() + d10 * 0.1, (double)blockPosIn.getY() + 0.3, (double)blockPosIn.getZ() + d12 * 0.1, d10, d11, d12);
                    if (particle == null) continue;
                    particle.R_4764_Y(f2);
                }
                if (data != 1) break;
                this.n_1700_B(blockPosIn, SoundEvents.Y_2080_q, D_38_f.u_1723_Y, 1.0f, random.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 2008: {
                this.Q_2552_b.n_1700_B(ParticleTypes.C_2741_M, (double)blockPosIn.getX() + 0.5, (double)blockPosIn.getY() + 0.5, (double)blockPosIn.getZ() + 0.5, 0.0, 0.0, 0.0);
                break;
            }
            case 2009: {
                for (int i1 = 0; i1 < 8; ++i1) {
                    this.Q_2552_b.n_1700_B(ParticleTypes.u_1723_Y, (double)blockPosIn.getX() + random.nextDouble(), (double)blockPosIn.getY() + 1.2, (double)blockPosIn.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
                }
                break;
            }
            case 3000: {
                this.Q_2552_b.n_1700_B(ParticleTypes.Q_2552_b, true, (double)blockPosIn.getX() + 0.5, (double)blockPosIn.getY() + 0.5, (double)blockPosIn.getZ() + 0.5, 0.0, 0.0, 0.0);
                this.n_1700_B(blockPosIn, SoundEvents.q_839_y, D_38_f.P_1922_E, 10.0f, (1.0f + (this.Q_2552_b.w_1457_N.nextFloat() - this.Q_2552_b.w_1457_N.nextFloat()) * 0.2f) * 0.7f, false);
                break;
            }
            case 3001: {
                this.n_1700_B(blockPosIn, SoundEvents.z_2025_Z, D_38_f.u_1723_Y, 64.0f, 0.8f + this.Q_2552_b.w_1457_N.nextFloat() * 0.3f, false);
            }
        }
    }

    public void J_1907_R(int breakerId, c_1514_x pos, int progress) {
        if (progress >= 0 && progress < 10) {
            BlockDestructionProgress destroyblockprogress1 = (BlockDestructionProgress)this.d_2427_y.get(breakerId);
            if (destroyblockprogress1 != null) {
                this.n_1700_B(destroyblockprogress1);
            }
            if (destroyblockprogress1 == null || destroyblockprogress1.n_1700_B().getX() != pos.getX() || destroyblockprogress1.n_1700_B().getY() != pos.getY() || destroyblockprogress1.n_1700_B().getZ() != pos.getZ()) {
                destroyblockprogress1 = new BlockDestructionProgress(breakerId, pos);
                this.d_2427_y.put(breakerId, (Object)destroyblockprogress1);
            }
            destroyblockprogress1.n_1700_B(progress);
            destroyblockprogress1.J_1907_R(this.n_3318_d);
            ((SortedSet)this.z_1737_N.computeIfAbsent(destroyblockprogress1.n_1700_B().toLong(), p_lambda$sendBlockBreakProgress$8_0_ -> Sets.newTreeSet())).add(destroyblockprogress1);
        } else {
            BlockDestructionProgress destroyblockprogress = (BlockDestructionProgress)this.d_2427_y.remove(breakerId);
            if (destroyblockprogress != null) {
                this.n_1700_B(destroyblockprogress);
            }
        }
    }

    public boolean P_4830_p() {
        return this.C_2741_M.isEmpty() && this.Ping.P_1922_E();
    }

    public void h_1847_R() {
        this.V_1446_Y = true;
        this.t_4043_B = true;
    }

    public int Q_4569_t() {
        return this.Z_875_P.u_1723_Y.length;
    }

    public int M_182_A() {
        return this.k_2293_S.size();
    }

    public int t_1786_h() {
        return this.f_4016_n;
    }

    public int multiplayerClientSuggestionProvider() {
        return this.l_4537_E;
    }

    public int w_1457_N() {
        if (this.Q_2552_b == null) {
            return 0;
        }
        r_4399_U clientchunkprovider = null;
        if (this.Q_2552_b instanceof k_4690_i) {
            clientchunkprovider = ((k_4690_i)this.Q_2552_b).v_4262_N();
        } else if (this.Q_2552_b instanceof c_3005_b) {
            clientchunkprovider = ((c_3005_b)this.Q_2552_b).P_4830_p();
        }
        return clientchunkprovider == null ? 0 : clientchunkprovider.R_4764_Y();
    }

    public int Y_601_j() {
        return this.C_2741_M.size();
    }

    public z_4547_I.n_1700_B n_1700_B(c_1514_x p_getRenderChunk_1_) {
        return this.Z_875_P.n_1700_B(p_getRenderChunk_1_);
    }

    public b_4507_u Y_259_p() {
        return this.Q_2552_b;
    }

    private void B_1668_F() {
        if (u_744_e > 0) {
            this.k_2293_S = new ObjectArrayList(this.k_2293_S.size() + 16);
            this.RealmsServerPing = new ArrayList<n_1700_B>(this.RealmsServerPing.size() + 16);
            this.j_1564_a = new ArrayList<n_1700_B>(this.j_1564_a.size() + 16);
        } else {
            this.k_2293_S.clear();
            this.RealmsServerPing.clear();
            this.j_1564_a.clear();
        }
    }

    public void Q_2552_b() {
        if (this.y_1700_S) {
            this.P_1922_E();
            this.y_1700_S = false;
        }
    }

    public void C_2741_M() {
        if (this.Ping != null) {
            this.Ping.v_4262_N();
        }
    }

    public void k_2293_S() {
        if (this.Ping != null) {
            this.Ping.w_1484_f();
        }
    }

    public int q_2307_F() {
        return this.PlayerInfo;
    }

    public int Z_875_P() {
        return ++this.PlayerInfo;
    }

    public RenderBuffers c_3005_b() {
        return this.Y_259_p;
    }

    public List<n_1700_B> H_2857_Y() {
        return this.RealmsServerPing;
    }

    public List<n_1700_B> A_4115_X() {
        return this.j_1564_a;
    }

    private void n_1700_B(h_3572_K p_checkLoadVisibleChunks_1_, E_4918_z p_checkLoadVisibleChunks_2_, boolean p_checkLoadVisibleChunks_3_) {
        if (this.w_1484_f == 0) {
            this.J_1907_R(p_checkLoadVisibleChunks_1_, p_checkLoadVisibleChunks_2_, p_checkLoadVisibleChunks_3_);
            this.multiplayerClientSuggestionProvider.M_588_G.R_4764_Y().n_1700_B(201435902);
        }
        if (this.w_1484_f > -1) {
            --this.w_1484_f;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void J_1907_R(h_3572_K p_loadAllVisibleChunks_1_, E_4918_z p_loadAllVisibleChunks_2_, boolean p_loadAllVisibleChunks_3_) {
        int i = this.multiplayerClientSuggestionProvider.P_4830_p.UploadTokenCache;
        boolean flag = this.multiplayerClientSuggestionProvider.P_4830_p.RealmsParentalConsentScreen;
        try {
            this.multiplayerClientSuggestionProvider.P_4830_p.UploadTokenCache = 1000;
            this.multiplayerClientSuggestionProvider.P_4830_p.RealmsParentalConsentScreen = false;
            z_883_p worldrenderer = Config.getRenderGlobal();
            int j = worldrenderer.w_1457_N();
            long k = System.currentTimeMillis();
            Config.dbg("Loading visible chunks");
            long l = System.currentTimeMillis() + 5000L;
            int i1 = 0;
            boolean flag1 = false;
            do {
                flag1 = false;
                for (int j1 = 0; j1 < 100; ++j1) {
                    worldrenderer.h_1847_R();
                    worldrenderer.n_1700_B(p_loadAllVisibleChunks_1_, p_loadAllVisibleChunks_2_, false, this.PlayerInfo++, p_loadAllVisibleChunks_3_);
                    if (!worldrenderer.P_4830_p()) {
                        flag1 = true;
                    }
                    i1 += worldrenderer.Y_601_j();
                    while (!worldrenderer.P_4830_p()) {
                        worldrenderer.n_1700_B(System.nanoTime() + 1000000000L);
                    }
                    i1 -= worldrenderer.Y_601_j();
                    if (!flag1) break;
                }
                if (worldrenderer.w_1457_N() != j) {
                    flag1 = true;
                    j = worldrenderer.w_1457_N();
                }
                if (System.currentTimeMillis() <= l) continue;
                Config.log("Chunks loaded: " + i1);
                l = System.currentTimeMillis() + 5000L;
            } while (flag1);
            Config.log("Chunks loaded: " + i1);
            Config.log("Finished loading visible chunks");
            z_4547_I.J_1907_R = 0;
        }
        finally {
            this.multiplayerClientSuggestionProvider.P_4830_p.UploadTokenCache = i;
            this.multiplayerClientSuggestionProvider.P_4830_p.RealmsParentalConsentScreen = flag;
        }
    }

    public IResourceType Y_1740_V() {
        return VanillaResourceType.MODELS;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(Collection<i_2154_H> tileEntitiesToRemove, Collection<i_2154_H> tileEntitiesToAdd) {
        Set<i_2154_H> set = this.q_2307_F;
        synchronized (set) {
            this.q_2307_F.removeAll(tileEntitiesToRemove);
            this.q_2307_F.addAll(tileEntitiesToAdd);
        }
    }

    public static int n_1700_B(BlockAndTintGetter lightReaderIn, c_1514_x blockPosIn) {
        return z_883_p.n_1700_B(lightReaderIn, lightReaderIn.getBlockState(blockPosIn), blockPosIn);
    }

    public static int n_1700_B(BlockAndTintGetter lightReaderIn, K_4074_S blockStateIn, c_1514_x blockPosIn) {
        int k;
        if (blockStateIn.P_1922_E(lightReaderIn, blockPosIn)) {
            return 0xF000F0;
        }
        int i = lightReaderIn.getLightFor(K_4719_o.n_1700_B, blockPosIn);
        int j = lightReaderIn.getLightFor(K_4719_o.J_1907_R, blockPosIn);
        if (j < (k = blockStateIn.w_1457_N(lightReaderIn, blockPosIn))) {
            j = k;
        }
        int l = i << 20 | j << 4;
        if (Config.isDynamicLights() && lightReaderIn instanceof BlockGetter && (!RetryCallException || !blockStateIn.t_148_a(lightReaderIn, blockPosIn))) {
            l = DynamicLights.getCombinedLight(blockPosIn, l);
        }
        return l;
    }

    @Nullable
    public P_4249_L t_4043_B() {
        return this.d_2461_k;
    }

    @Nullable
    public P_4249_L x_607_J() {
        return this.T_2506_i;
    }

    @Nullable
    public P_4249_L e_4240_b() {
        return this.q_4610_l;
    }

    @Nullable
    public P_4249_L n_3318_d() {
        return this.z_4693_k;
    }

    @Nullable
    public P_4249_L d_2427_y() {
        return this.g_221_o;
    }

    @Nullable
    public P_4249_L z_1737_N() {
        return this.e_2887_G;
    }

    @Generated
    public E_4918_z v_4276_D() {
        return this.S_980_j;
    }

    static {
        S_4022_R = Collections.unmodifiableSet(new HashSet<b_257_Y>(Arrays.asList(b_257_Y.v_4262_N)));
        u_744_e = 0;
        RetryCallException = false;
    }

    public static class J_1907_R
    extends RuntimeException {
        public J_1907_R(String p_i232463_1_, Throwable p_i232463_2_) {
            super(p_i232463_1_, p_i232463_2_);
        }
    }

    public static class n_1700_B {
        public final z_4547_I.n_1700_B n_1700_B;
        private b_257_Y J_1907_R;
        private int R_4764_Y;
        private int G_564_y;

        public n_1700_B(z_4547_I.n_1700_B p_i242106_1_, @Nullable b_257_Y p_i242106_2_, int p_i242106_3_) {
            this.n_1700_B = p_i242106_1_;
            this.J_1907_R = p_i242106_2_;
            this.R_4764_Y = p_i242106_3_;
        }

        public void n_1700_B(byte dir, b_257_Y facingIn) {
            this.R_4764_Y = this.R_4764_Y | dir | 1 << this.J_1907_R.ordinal();
        }

        public boolean n_1700_B(b_257_Y facingIn) {
            return (this.R_4764_Y & 1 << facingIn.ordinal()) > 0;
        }

        private void n_1700_B(b_257_Y p_initialize_1_, int p_initialize_2_, int p_initialize_3_) {
            this.J_1907_R = p_initialize_1_;
            this.R_4764_Y = p_initialize_2_;
            this.G_564_y = p_initialize_3_;
        }
    }
}



