/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Multimap
 *  com.google.common.collect.Queues
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.GameProfileRepository
 *  com.mojang.authlib.exceptions.AuthenticationException
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  com.mojang.authlib.minecraft.OfflineSocialInteractions
 *  com.mojang.authlib.minecraft.SocialInteractionsService
 *  com.mojang.authlib.properties.PropertyMap
 *  com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.datafixers.util.Function4
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.JsonOps
 *  com.mojang.serialization.Lifecycle
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Queues;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.minecraft.OfflineSocialInteractions;
import com.mojang.authlib.minecraft.SocialInteractionsService;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.B_3091_S;
import lightning.product.B_3871_I;
import lightning.product.B_4315_y;
import lightning.product.C_3240_x;
import lightning.product.D_1624_i;
import lightning.product.D_2103_L;
import lightning.product.D_3318_r;
import lightning.product.RecipeCollection;
import lightning.product.D_4024_W;
import lightning.product.D_4667_H;
import lightning.product.D_4704_b;
import lightning.product.D_590_W;
import lightning.product.D_686_b;
import lightning.product.E_1407_D;
import lightning.product.E_2727_F;
import lightning.product.E_3343_g;
import lightning.product.ReentrantBlockableEventLoop;
import lightning.product.E_688_b;
import lightning.product.PackRepository;
import lightning.product.GameConfig;
import lightning.product.F_1446_q;
import lightning.product.F_1723_g;
import lightning.product.InactiveProfiler;
import lightning.product.F_2904_S;
import lightning.product.BlockGetter;
import lightning.product.F_491_v;
import lightning.product.F_877_l;
import lightning.product.BlockHitResult;
import lightning.product.StringTag;
import lightning.product.TutorialToast;
import lightning.product.G_463_a;
import lightning.product.G_624_v;
import lightning.product.ClipContext;
import lightning.product.H_3272_P;
import lightning.product.H_3330_w;
import lightning.product.H_4757_Q;
import lightning.product.I_1084_e;
import lightning.product.MusicManager;
import lightning.product.HitResult;
import lightning.product.LeashFenceKnotEntity;
import lightning.product.ClientIntentionPacket;
import lightning.product.SharedConstants;
import lightning.product.RegistryWriteOps;
import lightning.product.J_1851_y;
import lightning.product.K_1289_S;
import lightning.product.K_2069_m;
import lightning.product.K_2357_w;
import lightning.product.K_4074_S;
import lightning.product.L_1105_I;
import lightning.product.L_4465_I;
import lightning.product.PlayerSocialManager;
import lightning.product.M_2935_g;
import lightning.product.M_3020_i;
import lightning.product.M_660_m;
import lightning.product.GrassColorReloadListener;
import lightning.product.N_1599_o;
import lightning.product.ItemTags;
import lightning.product.SystemToast;
import lightning.product.N_4263_v;
import lightning.product.O_1280_J;
import lightning.product.O_2369_F;
import lightning.product.O_2639_P;
import lightning.product.O_2863_d;
import lightning.product.Painting;
import lightning.product.O_3892_W;
import lightning.product.O_922_L;
import lightning.product.InBedChatScreen;
import lightning.product.SearchRegistry;
import lightning.product.P_3084_J;
import lightning.product.P_4249_L;
import lightning.product.Q_1939_l;
import lightning.product.Q_2241_p;
import lightning.product.NonNullList;
import lightning.product.ReloadableResourceManager;
import lightning.product.Q_936_s;
import lightning.product.R_3197_Z;
import lightning.product.PaintingTextureManager;
import lightning.product.FoliageColorReloadListener;
import lightning.product.S_1134_u;
import lightning.product.GenericDirtMessageScreen;
import lightning.product.ResourceManager;
import lightning.product.S_3826_o;
import lightning.product.T_1088_H;
import lightning.product.DatapackLoadFailureScreen;
import lightning.product.T_2915_h;
import lightning.product.T_437_o;
import lightning.product.T_4652_I;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.RenderBuffers;
import lightning.product.U_679_Y;
import lightning.product.V_3137_a;
import lightning.product.V_3163_W;
import lightning.product.V_3354_l;
import lightning.product.V_4423_d;
import lightning.product.ServerData;
import lightning.product.V_772_m;
import lightning.product.W_1619_c;
import lightning.product.W_1671_y;
import lightning.product.W_1689_V;
import lightning.product.W_2800_c;
import lightning.product.W_2853_p;
import lightning.product.W_3491_f;
import lightning.product.X_1446_C;
import lightning.product.ProfilerFiller;
import lightning.product.X_4340_E;
import lightning.product.Y_4083_F;
import lightning.product.Y_589_b;
import lightning.product.Tag;
import lightning.product.Z_1993_T;
import lightning.product.Z_3926_G;
import lightning.product.ServerResources;
import lightning.product.PackMetadataSection;
import lightning.product.Z_875_P;
import lightning.product.ProfileResults;
import lightning.product.Z_976_R;
import lightning.product.a_3913_L;
import lightning.product.PackResources;
import lightning.product.BaritoneSettings;
import lightning.product.b_257_Y;
import lightning.product.b_2625_m;
import lightning.product.ReloadableIdSearchTree;
import lightning.product.b_2971_z;
import lightning.product.b_298_p;
import lightning.product.b_4507_u;
import lightning.product.b_653_U;
import lightning.product.Music;
import lightning.product.c_1514_x;
import lightning.product.c_1633_k;
import lightning.product.c_3005_b;
import lightning.product.c_3102_J;
import lightning.product.c_4037_x;
import lightning.product.ContinuousProfiler;
import lightning.product.ServerboundHelloPacket;
import lightning.product.d_4952_K;
import lightning.product.Musics;
import lightning.product.e_3977_C;
import lightning.product.e_4716_U;
import lightning.product.f_1574_f;
import lightning.product.f_2689_h;
import lightning.product.Overlay;
import lightning.product.LanguageManager;
import lightning.product.g_1462_f;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.DataPackConfig;
import lightning.product.g_3316_o;
import lightning.product.g_4418_P;
import lightning.product.ResultField;
import lightning.product.h_2848_I;
import lightning.product.h_4412_P;
import lightning.product.i_2154_H;
import lightning.product.StoringChunkProgressListener;
import lightning.product.i_4221_J;
import lightning.product.j_1086_C;
import lightning.product.ModelManager;
import lightning.product.j_3341_s;
import lightning.product.j_39_h;
import lightning.product.j_419_j;
import lightning.product.ServerboundPlayerActionPacket;
import lightning.product.k_2603_m;
import lightning.product.k_4218_M;
import lightning.product.k_4467_X;
import lightning.product.k_4690_i;
import lightning.product.FrameTimer;
import lightning.product.k_594_Q;
import lightning.product.k_596_g;
import lightning.product.l_1268_F;
import lightning.product.l_3370_o;
import lightning.product.l_3747_P;
import lightning.product.l_4118_l;
import lightning.product.l_456_f;
import lightning.product.WorldData;
import lightning.product.m_3054_I;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.n_4915_r;
import lightning.product.o_12_W;
import lightning.product.ClientBootstrap;
import lightning.product.o_2767_H;
import lightning.product.o_3912_n;
import lightning.product.MutableSearchTree;
import lightning.product.p_402_W;
import lightning.product.PackSource;
import lightning.product.p_752_J;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.CommonComponents;
import lightning.product.q_3131_N;
import lightning.product.q_3418_t;
import lightning.product.q_3575_f;
import lightning.product.Items;
import lightning.product.q_570_v;
import lightning.product.CrashReportCategory;
import lightning.product.r_3127_A;
import lightning.product.r_4097_j;
import lightning.product.s_1875_c;
import lightning.product.s_4082_G;
import lightning.product.BlockModelShaper;
import lightning.product.t_1920_R;
import lightning.product.t_3048_V;
import lightning.product.t_5_h;
import lightning.product.u_1406_j;
import lightning.product.u_2124_A;
import lightning.product.u_2877_K;
import lightning.product.u_3100_Q;
import lightning.product.HotbarManager;
import lightning.product.u_4650_L;
import lightning.product.u_530_F;
import lightning.product.w_1457_N;
import lightning.product.VirtualScreen;
import lightning.product.w_2040_b;
import lightning.product.w_2223_C;
import lightning.product.EntityHitResult;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import lightning.product.MobEffectTextureManager;
import lightning.product.x_4991_F;
import lightning.product.y_3482_a;
import lightning.product.SpawnEggItem;
import lightning.product.y_4319_k;
import lightning.product.y_4642_Y;
import lightning.product.ReloadableSearchTree;
import lightning.product.y_740_d;
import lightning.product.ProcessorChunkProgressListener;
import lightning.product.z_883_p;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.event.events.PlayerUpdateEvent;
import mods.baritone.api.api.java.baritone.api.event.events.TickEvent;
import mods.baritone.api.api.java.baritone.api.event.events.WorldEvent;
import mods.baritone.api.api.java.baritone.api.event.events.type.EventState;
import mods.viaversion.viamcp.fixes.AttackOrder;
import mods.voicechat.eventforge.ClientPlayerNetworkEvent;
import mods.voicechat.eventforge.TickEvent;
import mods.voicechat.eventforge.WorldEvent;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinecraftClient
extends ReentrantBlockableEventLoop<Runnable>
implements J_1851_y,
Y_589_b {
    private static MinecraftClient v_4276_D;
    private static final Logger d_2461_k;
    public static final boolean n_1700_B;
    public static final g_2336_b J_1907_R;
    public static final g_2336_b R_4764_Y;
    public static final g_2336_b G_564_y;
    private static final CompletableFuture<X_1446_C> G_624_v;
    private static final x_282_a T_2506_i;
    private final File q_4610_l;
    private final PropertyMap z_4693_k;
    private final C_3240_x g_221_o;
    private final DataFixer e_2887_G;
    private final VirtualScreen B_1668_F;
    private final U_679_Y g_164_R;
    public final u_2124_A P_1922_E = new u_2124_A(20.0f, 0L);
    private final E_2727_F X_933_l = new E_2727_F("client", this, j_3341_s.J_1907_R());
    private final RenderBuffers Z_976_R;
    public final z_883_p u_1723_Y;
    private final w_2040_b H_1990_U;
    private final H_3330_w N_2525_X;
    private final l_456_f c_4037_x;
    public final b_298_p v_4262_N;
    private final SearchRegistry g_2268_R = new SearchRegistry();
    public u_3100_Q w_1484_f;
    public final Y_4083_F t_148_a;
    public final M_660_m s_956_w;
    public final n_4915_r u_2550_I;
    private final AtomicReference<StoringChunkProgressListener> T_3594_S = new AtomicReference();
    public final u_1406_j M_588_G;
    public final V_4423_d P_4830_p;
    private final HotbarManager D_4792_h;
    public final L_4465_I h_1847_R;
    public final O_2863_d Q_4569_t;
    public final File M_182_A;
    private final String s_2632_s;
    private final String l_1233_K;
    private final Proxy z_1333_t;
    private final b_2971_z O_508_d;
    public final FrameTimer t_1786_h = new FrameTimer();
    private final boolean r_715_M;
    private final boolean A_1038_p;
    private final boolean i_1637_u;
    private final boolean Ping;
    private final ReloadableResourceManager p_178_J;
    private final L_1105_I RealmsClientConfig;
    private final PackRepository f_4016_n;
    private final LanguageManager j_276_v;
    private final k_4467_X UploadStatus;
    private final N_1599_o e_1992_r;
    private final P_4249_L D_60_a;
    private final k_4218_M k_3961_g;
    private final MusicManager Ops;
    private final o_3912_n h_4320_q;
    private final W_1619_c t_4219_U;
    private final K_2357_w V_1446_Y;
    private final MinecraftSessionService PlayerInfo;
    private final SocialInteractionsService V_1225_t;
    private final D_4667_H U_1241_n;
    private final ModelManager q_1982_R;
    private final e_3977_C dtoRealmsServerAddress;
    private final PaintingTextureManager w_612_n;
    private final MobEffectTextureManager RealmsServerPing;
    private final D_1624_i j_1564_a;
    private final q_3575_f M_1641_O = new q_3575_f(this);
    private final W_1671_y RealmsWorldOptions;
    private final PlayerSocialManager RealmsWorldResetDto;
    public static byte[] multiplayerClientSuggestionProvider;
    @Nullable
    public V_3163_W w_1457_N;
    @Nullable
    public k_4690_i Y_601_j;
    @Nullable
    public V_772_m Y_259_p;
    @Nullable
    private R_3197_Z RegionPingResult;
    @Nullable
    private ServerData H_1083_k;
    @Nullable
    public c_1633_k Q_2552_b;
    private boolean R_3908_n;
    @Nullable
    public N_4263_v C_2741_M;
    @Nullable
    public lightning.product.n_1700_B k_2293_S = null;
    private int ValueObject = 0;
    private static final int F_1410_V = 5;
    @Nullable
    public N_4263_v q_2307_F;
    @Nullable
    public HitResult Z_875_P;
    public int c_3005_b;
    public int H_2857_Y;
    private boolean S_4022_R;
    private float l_4537_E;
    private long F_2624_D = j_3341_s.R_4764_Y();
    private long RealmsDefaultUncaughtExceptionHandler;
    private int y_1700_S;
    public boolean A_4115_X;
    @Nullable
    public k_2603_m Y_1740_V;
    @Nullable
    public Overlay t_4043_B;
    private boolean u_744_e;
    private Thread RetryCallException;
    private volatile boolean r_3651_U = true;
    @Nullable
    private n_3236_c RowButton;
    public static int x_607_J;
    public String e_4240_b = "";
    public boolean n_3318_d;
    public boolean d_2427_y;
    public boolean z_1737_N = true;
    private boolean LongRunningTask;
    private final Queue<Runnable> j_2266_I = Queues.newConcurrentLinkedQueue();
    @Nullable
    private CompletableFuture<Void> S_980_j;
    @Nullable
    private TutorialToast R_3077_Z;
    private ProfilerFiller RealmsScreenWithCallback = InactiveProfiler.n_1700_B;
    private int M_2677_i;
    private final ContinuousProfiler c_132_F = new ContinuousProfiler(j_3341_s.n_1700_B, () -> this.M_2677_i);
    @Nullable
    private ProfileResults g_4106_L;
    private String RealmsClientOutdatedScreen = "root";
    private long W_3464_O = j_3341_s.R_4764_Y();
    private int RealmsConfirmScreen;
    private final o_2767_H RealmsCreateRealmScreen = new o_2767_H();

    @w_2223_C
    public MinecraftClient(GameConfig gameConfig) {
        super("Client");
        int i;
        String s;
        v_4276_D = this;
        this.M_182_A = gameConfig.R_4764_Y.n_1700_B;
        File file1 = gameConfig.R_4764_Y.R_4764_Y;
        this.q_4610_l = gameConfig.R_4764_Y.J_1907_R;
        this.s_2632_s = gameConfig.G_564_y.J_1907_R;
        this.l_1233_K = gameConfig.G_564_y.R_4764_Y;
        this.z_4693_k = gameConfig.n_1700_B.R_4764_Y;
        this.RealmsClientConfig = new L_1105_I(new File(this.M_182_A, "server-resource-packs"), gameConfig.R_4764_Y.n_1700_B());
        this.f_4016_n = new PackRepository(MinecraftClient::n_1700_B, this.RealmsClientConfig, new e_4716_U(this.q_4610_l, PackSource.n_1700_B));
        this.z_1333_t = gameConfig.n_1700_B.G_564_y;
        YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(this.z_1333_t);
        this.PlayerInfo = yggdrasilauthenticationservice.createMinecraftSessionService();
        this.V_1225_t = this.n_1700_B(yggdrasilauthenticationservice, gameConfig);
        this.w_1484_f = gameConfig.n_1700_B.n_1700_B;
        d_2461_k.info("Setting user: {}", (Object)this.w_1484_f.R_4764_Y());
        d_2461_k.debug("(Session ID is {})", (Object)this.w_1484_f.n_1700_B());
        this.A_1038_p = gameConfig.G_564_y.n_1700_B;
        this.i_1637_u = !gameConfig.G_564_y.G_564_y;
        this.Ping = !gameConfig.G_564_y.P_1922_E;
        this.r_715_M = MinecraftClient.RealmsWorldResetDto();
        this.RegionPingResult = null;
        if (this.Y_259_p() && gameConfig.P_1922_E.n_1700_B != null) {
            s = gameConfig.P_1922_E.n_1700_B;
            i = gameConfig.P_1922_E.J_1907_R;
        } else {
            s = null;
            i = 0;
        }
        s_4082_G.n_1700_B(D_590_W::n_1700_B);
        this.e_2887_G = F_491_v.n_1700_B();
        this.j_1564_a = new D_1624_i(this);
        this.RealmsWorldOptions = new W_1671_y(this);
        this.RetryCallException = Thread.currentThread();
        this.P_4830_p = new V_4423_d(this, this.M_182_A);
        this.D_4792_h = new HotbarManager(this.M_182_A, this.e_2887_G);
        d_2461_k.info("Backend library: {}", (Object)lightning.product.c_4037_x.T_2506_i());
        q_570_v screensize = this.P_4830_p.Y_601_j > 0 && this.P_4830_p.w_1457_N > 0 ? new q_570_v(this.P_4830_p.w_1457_N, this.P_4830_p.Y_601_j, gameConfig.J_1907_R.R_4764_Y, gameConfig.J_1907_R.G_564_y, gameConfig.J_1907_R.P_1922_E) : gameConfig.J_1907_R;
        j_3341_s.n_1700_B = lightning.product.c_4037_x.z_4693_k();
        this.B_1668_F = new VirtualScreen(this);
        this.g_164_R = this.B_1668_F.n_1700_B(screensize, this.P_4830_p.h_1847_R, this.M_1641_O());
        this.G_564_y(true);
        try {
            InputStream inputstream = this.z_4693_k().n_1700_B().getResourceStream(i_4221_J.n_1700_B, new g_2336_b("icons/icon_16x16.png"));
            InputStream inputstream1 = this.z_4693_k().n_1700_B().getResourceStream(i_4221_J.n_1700_B, new g_2336_b("icons/icon_32x32.png"));
            this.g_164_R.n_1700_B(inputstream, inputstream1);
        }
        catch (IOException ioexception) {
            d_2461_k.error("Couldn't set icon", (Throwable)ioexception);
        }
        this.g_164_R.n_1700_B(this.P_4830_p.G_564_y);
        this.h_1847_R = new L_4465_I(this);
        this.h_1847_R.n_1700_B(this.g_164_R.t_148_a());
        this.Q_4569_t = new O_2863_d(this);
        this.Q_4569_t.n_1700_B(this.g_164_R.t_148_a());
        lightning.product.c_4037_x.J_1907_R(this.P_4830_p.d_2427_y, false);
        this.D_60_a = new P_4249_L(this.g_164_R.u_2550_I(), this.g_164_R.M_588_G(), true, n_1700_B);
        this.D_60_a.n_1700_B(0.0f, 0.0f, 0.0f, 0.0f);
        this.p_178_J = new b_653_U(i_4221_J.n_1700_B);
        this.f_4016_n.n_1700_B();
        this.P_4830_p.n_1700_B(this.f_4016_n);
        this.j_276_v = new LanguageManager(this.P_4830_p.RealmsConfirmScreen);
        this.p_178_J.n_1700_B(this.j_276_v);
        this.g_221_o = new C_3240_x(this.p_178_J);
        this.p_178_J.n_1700_B(this.g_221_o);
        this.U_1241_n = new D_4667_H(this.g_221_o, new File(file1, "skins"), this.PlayerInfo);
        this.O_508_d = new b_2971_z(this.M_182_A.toPath().resolve("saves"), this.M_182_A.toPath().resolve("backups"), this.e_2887_G);
        this.k_3961_g = new k_4218_M(this.p_178_J, this.P_4830_p);
        this.p_178_J.n_1700_B(this.k_3961_g);
        this.t_4219_U = new W_1619_c(this.w_1484_f);
        this.p_178_J.n_1700_B(this.t_4219_U);
        this.Ops = new MusicManager(this);
        this.h_4320_q = new o_3912_n(this.g_221_o);
        this.t_148_a = this.h_4320_q.n_1700_B();
        this.p_178_J.n_1700_B(this.h_4320_q.J_1907_R());
        this.n_1700_B(this.v_4262_N());
        this.p_178_J.n_1700_B(new GrassColorReloadListener());
        this.p_178_J.n_1700_B(new FoliageColorReloadListener());
        this.g_164_R.n_1700_B("Startup");
        lightning.product.c_4037_x.G_564_y(0, 0, this.g_164_R.u_2550_I(), this.g_164_R.M_588_G());
        this.g_164_R.n_1700_B("Post startup");
        this.UploadStatus = k_4467_X.n_1700_B();
        this.e_1992_r = N_1599_o.n_1700_B(this.UploadStatus);
        this.q_1982_R = new ModelManager(this.g_221_o, this.UploadStatus, this.P_4830_p.c_3005_b);
        this.p_178_J.n_1700_B(this.q_1982_R);
        this.N_2525_X = new H_3330_w(this.g_221_o, this.q_1982_R, this.e_1992_r);
        this.H_1990_U = new w_2040_b(this.g_221_o, this.N_2525_X, this.p_178_J, this.t_148_a, this.P_4830_p);
        this.c_4037_x = new l_456_f(this);
        this.p_178_J.n_1700_B(this.N_2525_X);
        this.Z_976_R = new RenderBuffers();
        this.s_956_w = new M_660_m(this, this.p_178_J, this.Z_976_R);
        this.p_178_J.n_1700_B(this.s_956_w);
        this.RealmsWorldResetDto = new PlayerSocialManager(this, this.V_1225_t);
        this.dtoRealmsServerAddress = new e_3977_C(this.q_1982_R.R_4764_Y(), this.UploadStatus);
        this.p_178_J.n_1700_B(this.dtoRealmsServerAddress);
        this.u_1723_Y = new z_883_p(this, this.Z_976_R);
        this.p_178_J.n_1700_B(this.u_1723_Y);
        this.RealmsWorldOptions();
        this.p_178_J.n_1700_B(this.g_2268_R);
        this.v_4262_N = new b_298_p(this.Y_601_j, this.g_221_o);
        this.p_178_J.n_1700_B(this.v_4262_N);
        this.w_612_n = new PaintingTextureManager(this.g_221_o);
        this.p_178_J.n_1700_B(this.w_612_n);
        this.RealmsServerPing = new MobEffectTextureManager(this.g_221_o);
        this.p_178_J.n_1700_B(this.RealmsServerPing);
        this.V_1446_Y = new K_2357_w();
        this.p_178_J.n_1700_B(this.V_1446_Y);
        this.M_588_G = new u_1406_j(this);
        this.u_2550_I = new n_4915_r(this);
        l_3370_o.n_1700_B();
        new ClientBootstrap();
        lightning.product.c_4037_x.n_1700_B(this::n_1700_B);
        if (this.P_4830_p.g_2268_R && !this.g_164_R.s_956_w()) {
            this.g_164_R.w_1484_f();
            this.P_4830_p.g_2268_R = this.g_164_R.s_956_w();
        }
        this.g_164_R.J_1907_R(this.P_4830_p.q_4610_l);
        this.g_164_R.R_4764_Y(this.P_4830_p.n_3318_d);
        this.g_164_R.R_4764_Y();
        this.u_2550_I();
        if (s != null) {
            this.n_1700_B(new t_3048_V(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L(), this, s, i));
        } else {
            this.n_1700_B(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L());
        }
        u_2877_K.n_1700_B(this);
        List<PackResources> list = this.f_4016_n.u_1723_Y();
        this.n_1700_B(new u_2877_K(this, this.p_178_J.n_1700_B(j_3341_s.u_1723_Y(), (Executor)this, G_624_v, list), throwable -> j_3341_s.n_1700_B(throwable, this::n_1700_B, () -> {
            if (SharedConstants.G_564_y) {
                this.RegionPingResult();
            }
        }), false));
        BaritoneAPI.getProvider().getPrimaryBaritone();
    }

    public void n_1700_B() {
        this.g_164_R.J_1907_R(this.M_1641_O());
    }

    @w_2223_C
    private String M_1641_O() {
        StringBuilder stringbuilder = new StringBuilder("Minecraft");
        if (this.J_1907_R()) {
            stringbuilder.append("*");
        }
        stringbuilder.append(" ");
        stringbuilder.append(SharedConstants.n_1700_B().getName());
        W_2853_p clientplaynethandler = this.k_2293_S();
        if (clientplaynethandler != null && clientplaynethandler.getNetworkManager().u_1723_Y()) {
            stringbuilder.append(" - ");
            if (this.RegionPingResult != null && !this.RegionPingResult.RealmsClientConfig()) {
                stringbuilder.append(K_1289_S.n_1700_B("title.singleplayer", new Object[0]));
            } else if (this.Ping()) {
                stringbuilder.append(K_1289_S.n_1700_B("title.multiplayer.realms", new Object[0]));
            } else if (!(this.RegionPingResult != null || this.H_1083_k != null && this.H_1083_k.G_564_y())) {
                stringbuilder.append(K_1289_S.n_1700_B("title.multiplayer.other", new Object[0]));
            } else {
                stringbuilder.append(K_1289_S.n_1700_B("title.multiplayer.lan", new Object[0]));
            }
        }
        return stringbuilder.toString();
    }

    private SocialInteractionsService n_1700_B(YggdrasilAuthenticationService p_244735_1_, GameConfig p_244735_2_) {
        try {
            return p_244735_1_.createSocialInteractionsService(p_244735_2_.n_1700_B.n_1700_B.G_564_y());
        }
        catch (AuthenticationException authenticationexception) {
            d_2461_k.error("Failed to verify authentication", (Throwable)authenticationexception);
            return new OfflineSocialInteractions();
        }
    }

    public boolean J_1907_R() {
        return !"vanilla".equals(p_752_J.n_1700_B()) || MinecraftClient.class.getSigners() == null;
    }

    @Override
    private void n_1700_B(Throwable throwableIn) {
        if (this.f_4016_n.G_564_y().size() > 1) {
            U_2871_b itextcomponent = throwableIn instanceof b_653_U.n_1700_B ? new U_2871_b(((b_653_U.n_1700_B)throwableIn).n_1700_B().getName()) : null;
            this.n_1700_B(throwableIn, itextcomponent);
        } else {
            j_3341_s.J_1907_R(throwableIn);
        }
    }

    public void n_1700_B(Throwable throwable, @Nullable x_282_a errorMessage) {
        d_2461_k.info("Caught error loading resourcepacks, removing all selected resourcepacks", throwable);
        this.f_4016_n.n_1700_B(Collections.emptyList());
        this.P_4830_p.w_1484_f.clear();
        this.P_4830_p.t_148_a.clear();
        this.P_4830_p.J_1907_R();
        this.w_1484_f().thenRun(() -> {
            D_1624_i toastgui = this.e_1992_r();
            SystemToast.J_1907_R(toastgui, SystemToast.n_1700_B.P_1922_E, new F_2904_S("resourcePack.load_fail"), errorMessage);
        });
    }

    @w_2223_C
    public void R_4764_Y() {
        this.RetryCallException = Thread.currentThread();
        try {
            boolean flag = false;
            while (this.r_3651_U) {
                if (this.RowButton != null) {
                    MinecraftClient.J_1907_R(this.RowButton);
                    return;
                }
                try {
                    W_2800_c longtickdetector = W_2800_c.n_1700_B("Renderer");
                    boolean flag1 = this.H_1083_k();
                    this.n_1700_B(flag1, longtickdetector);
                    this.RealmsScreenWithCallback.n_1700_B();
                    this.P_1922_E(!flag);
                    this.RealmsScreenWithCallback.J_1907_R();
                    this.J_1907_R(flag1, longtickdetector);
                }
                catch (OutOfMemoryError outofmemoryerror) {
                    if (flag) {
                        throw outofmemoryerror;
                    }
                    this.P_4830_p();
                    this.n_1700_B(new M_3020_i());
                    System.gc();
                    d_2461_k.fatal("Out of memory", (Throwable)outofmemoryerror);
                    flag = true;
                }
            }
        }
        catch (ReportedException reportedexception) {
            try {
                ClientBootstrap.Y_601_j().n_1700_B();
            }
            catch (Throwable outofmemoryerror) {
                // empty catch block
            }
            this.R_4764_Y(reportedexception.n_1700_B());
            this.P_4830_p();
            d_2461_k.fatal("Reported exception thrown!", (Throwable)reportedexception);
            MinecraftClient.J_1907_R(reportedexception.n_1700_B());
        }
        catch (Throwable throwable) {
            try {
                ClientBootstrap.Y_601_j().n_1700_B();
            }
            catch (Throwable outofmemoryerror) {
                // empty catch block
            }
            n_3236_c crashreport = this.R_4764_Y(new n_3236_c("Unexpected error", throwable));
            d_2461_k.fatal("Unreported exception thrown!", throwable);
            this.P_4830_p();
            MinecraftClient.J_1907_R(crashreport);
        }
    }

    @Override
    void n_1700_B(boolean forced) {
        this.h_4320_q.n_1700_B((Map<g_2336_b, g_2336_b>)(forced ? ImmutableMap.of((Object)J_1907_R, (Object)R_4764_Y) : ImmutableMap.of()));
    }

    private void RealmsWorldOptions() {
        ReloadableSearchTree<Z_1993_T> searchtree = new ReloadableSearchTree<Z_1993_T>(stack -> stack.n_1700_B((a_3913_L)null, g_3316_o.n_1700_B.n_1700_B).stream().map(textComponent -> D_4024_W.n_1700_B(textComponent.getString()).trim()).filter(name -> !name.isEmpty()), stack -> Stream.of(V_3137_a.e_2887_G.J_1907_R(stack.J_1907_R())));
        ReloadableIdSearchTree<Z_1993_T> searchtreereloadable = new ReloadableIdSearchTree<Z_1993_T>(stack -> ItemTags.n_1700_B().n_1700_B(stack.J_1907_R()).stream());
        NonNullList<Z_1993_T> nonnulllist = NonNullList.n_1700_B();
        for (q_1613_l item : V_3137_a.e_2887_G) {
            item.n_1700_B(S_1134_u.v_4262_N, nonnulllist);
        }
        nonnulllist.forEach(stack -> {
            searchtree.n_1700_B((Z_1993_T)stack);
            searchtreereloadable.n_1700_B((Z_1993_T)stack);
        });
        ReloadableSearchTree<RecipeCollection> searchtree1 = new ReloadableSearchTree<RecipeCollection>(recipeList -> recipeList.G_564_y().stream().flatMap(recipe -> recipe.R_4764_Y().n_1700_B((a_3913_L)null, g_3316_o.n_1700_B.n_1700_B).stream()).map(textComponent -> D_4024_W.n_1700_B(textComponent.getString()).trim()).filter(name -> !name.isEmpty()), recipeList -> recipeList.G_564_y().stream().map(recipe -> V_3137_a.e_2887_G.J_1907_R(recipe.R_4764_Y().J_1907_R())));
        this.g_2268_R.n_1700_B(SearchRegistry.n_1700_B, searchtree);
        this.g_2268_R.n_1700_B(SearchRegistry.J_1907_R, searchtreereloadable);
        this.g_2268_R.n_1700_B(SearchRegistry.R_4764_Y, searchtree1);
    }

    private void n_1700_B(int error, long description) {
        this.P_4830_p.q_4610_l = false;
        this.P_4830_p.J_1907_R();
    }

    @w_2223_C
    private static boolean RealmsWorldResetDto() {
        String[] astring;
        for (String s : astring = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"}) {
            String s1 = System.getProperty(s);
            if (s1 == null || !s1.contains("64")) continue;
            return true;
        }
        return false;
    }

    public P_4249_L G_564_y() {
        return this.D_60_a;
    }

    public String P_1922_E() {
        return this.s_2632_s;
    }

    public String u_1723_Y() {
        return this.l_1233_K;
    }

    @Override
    public void n_1700_B(n_3236_c crash) {
        this.RowButton = crash;
    }

    public static void J_1907_R(n_3236_c report) {
        try {
            ClientBootstrap.Y_601_j().n_1700_B();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        File file1 = new File(MinecraftClient.A_4115_X().M_182_A, "crash-reports");
        File file2 = new File(file1, "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-client.txt");
        y_3482_a.n_1700_B(report.G_564_y());
        if (report.P_1922_E() != null) {
            y_3482_a.n_1700_B("#@!@# Game crashed! Crash report saved to: #@!@# " + String.valueOf(report.P_1922_E()));
            System.exit(-1);
        } else if (report.n_1700_B(file2)) {
            y_3482_a.n_1700_B("#@!@# Game crashed! Crash report saved to: #@!@# " + file2.getAbsolutePath());
            System.exit(-1);
        } else {
            y_3482_a.n_1700_B("#@?@# Game crashed! Crash report could not be saved. #@?@#");
            System.exit(-2);
        }
    }

    public boolean v_4262_N() {
        return this.P_4830_p.g_221_o;
    }

    public CompletableFuture<Void> w_1484_f() {
        if (this.S_980_j != null) {
            return this.S_980_j;
        }
        CompletableFuture<Void> completablefuture = new CompletableFuture<Void>();
        if (this.t_4043_B instanceof u_2877_K) {
            this.S_980_j = completablefuture;
            return completablefuture;
        }
        this.f_4016_n.n_1700_B();
        List<PackResources> list = this.f_4016_n.u_1723_Y();
        this.n_1700_B(new u_2877_K(this, this.p_178_J.n_1700_B(j_3341_s.u_1723_Y(), (Executor)this, G_624_v, list), throwable -> j_3341_s.n_1700_B(throwable, this::n_1700_B, () -> {
            this.u_1723_Y.P_1922_E();
            completablefuture.complete(null);
        }), true));
        return completablefuture;
    }

    private void RegionPingResult() {
        boolean flag = false;
        BlockModelShaper blockmodelshapes = this.z_1333_t().J_1907_R();
        S_3826_o ibakedmodel = blockmodelshapes.n_1700_B().J_1907_R();
        for (T_2915_h t_2915_h : V_3137_a.q_4610_l) {
            for (K_4074_S blockstate : t_2915_h.t_1786_h().n_1700_B()) {
                S_3826_o ibakedmodel1;
                if (blockstate.w_1484_f() != O_2369_F.R_4764_Y || (ibakedmodel1 = blockmodelshapes.J_1907_R(blockstate)) != ibakedmodel) continue;
                d_2461_k.debug("Missing model for: {}", (Object)blockstate);
                flag = true;
            }
        }
        B_3871_I textureatlassprite1 = ibakedmodel.P_1922_E();
        for (T_2915_h block1 : V_3137_a.q_4610_l) {
            for (K_4074_S blockstate1 : block1.t_1786_h().n_1700_B()) {
                B_3871_I textureatlassprite = blockmodelshapes.n_1700_B(blockstate1);
                if (blockstate1.v_4262_N() || textureatlassprite != textureatlassprite1) continue;
                d_2461_k.debug("Missing particle icon for: {}", (Object)blockstate1);
                flag = true;
            }
        }
        NonNullList<Z_1993_T> q_356_t = NonNullList.n_1700_B();
        for (q_1613_l item : V_3137_a.e_2887_G) {
            q_356_t.clear();
            item.n_1700_B(S_1134_u.v_4262_N, q_356_t);
            for (Z_1993_T itemstack : q_356_t) {
                String s = itemstack.s_956_w();
                String s1 = new F_2904_S(s).getString();
                if (!s1.toLowerCase(Locale.ROOT).equals(item.J_1907_R())) continue;
                d_2461_k.debug("Missing translation for: {} {} {}", (Object)itemstack, (Object)s, (Object)itemstack.J_1907_R());
            }
        }
        if (flag |= D_4704_b.n_1700_B()) {
            throw new IllegalStateException("Your game data is foobar, fix the errors above!");
        }
    }

    public b_2971_z t_148_a() {
        return this.O_508_d;
    }

    private void J_1907_R(String defaultText) {
        if (!this.x_607_J() && !this.Q_2552_b()) {
            if (this.Y_259_p != null) {
                this.Y_259_p.n_1700_B((x_282_a)new F_2904_S("chat.cannotSend").n_1700_B(D_4024_W.P_4830_p), j_3341_s.J_1907_R);
            }
        } else {
            this.n_1700_B(new h_4412_P(defaultText));
        }
    }

    @Override
    public void n_1700_B(@Nullable k_2603_m guiScreenIn) {
        lightning.product.n_1700_B bot1 = null;
        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        if (this.Y_1740_V != null) {
            this.Y_1740_V.onClose();
        }
        if (guiScreenIn == null && this.Y_601_j == null) {
            guiScreenIn = y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L();
        } else if (guiScreenIn == null) {
            boolean shouldBeDead;
            if (bot1 != null) {
                shouldBeDead = bot1.P_1922_E.Q_2552_b.Z_2812_M();
            } else {
                boolean bl = shouldBeDead = this.Y_259_p != null && this.Y_259_p.Z_2812_M();
            }
            if (shouldBeDead) {
                boolean showDeathScreen = bot1 != null ? bot1.P_1922_E.Q_2552_b.multiplayerClientSuggestionProvider() : this.Y_259_p.multiplayerClientSuggestionProvider();
                if (showDeathScreen) {
                    guiScreenIn = new E_1407_D(null, this.Y_601_j.Y_259_p().n_1700_B());
                } else if (bot1 == null) {
                    this.Y_259_p.G_564_y();
                }
            }
        }
        if (guiScreenIn instanceof O_922_L || guiScreenIn instanceof q_3131_N) {
            this.P_4830_p.r_3651_U = false;
            this.M_588_G.R_4764_Y().n_1700_B(true);
        }
        this.Y_1740_V = guiScreenIn;
        if (guiScreenIn != null) {
            this.h_1847_R.t_148_a();
            D_590_W.J_1907_R();
            guiScreenIn.init(this, this.g_164_R.Q_4569_t(), this.g_164_R.M_182_A());
            this.A_4115_X = false;
            I_1084_e.J_1907_R.n_1700_B(guiScreenIn.getNarrationMessage());
        } else {
            this.k_3961_g.P_1922_E();
            this.h_1847_R.w_1484_f();
        }
        this.n_1700_B();
    }

    @Override
    public void n_1700_B(@Nullable Overlay loadingGuiIn) {
        this.t_4043_B = loadingGuiIn;
    }

    @w_2223_C
    public void s_956_w() {
        if (!lightning.product.G_624_v.t_148_a.J_1907_R()) {
            lightning.product.G_624_v.t_148_a.n_1700_B();
        }
        try {
            ClientBootstrap.Y_601_j().n_1700_B();
            d_2461_k.info("Stopping!");
            try {
                I_1084_e.J_1907_R.R_4764_Y();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            try {
                if (this.Y_601_j != null) {
                    this.Y_601_j.w_1484_f();
                }
                this.Y_601_j();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            if (this.Y_1740_V != null) {
                this.Y_1740_V.onClose();
            }
            this.close();
        }
        finally {
            j_3341_s.n_1700_B = System::nanoTime;
            if (this.RowButton == null) {
                System.exit(0);
            }
        }
    }

    @Override
    @w_2223_C
    public void close() {
        if (!lightning.product.G_624_v.t_148_a.J_1907_R()) {
            lightning.product.G_624_v.t_148_a.n_1700_B();
        }
        try {
            ClientBootstrap.Y_601_j().n_1700_B();
            this.q_1982_R.close();
            this.h_4320_q.close();
            this.s_956_w.close();
            this.u_1723_Y.close();
            this.k_3961_g.G_564_y();
            this.f_4016_n.close();
            this.v_4262_N.J_1907_R();
            this.RealmsServerPing.close();
            this.w_612_n.close();
            this.g_221_o.close();
            this.p_178_J.close();
            j_3341_s.w_1484_f();
        }
        catch (Throwable throwable) {
            d_2461_k.error("Shutdown failure!", throwable);
            throw throwable;
        }
        finally {
            this.B_1668_F.close();
            this.g_164_R.close();
        }
    }

    @Override
    private void P_1922_E(boolean renderWorldIn) {
        boolean flag;
        Runnable runnable;
        long now = j_3341_s.R_4764_Y();
        long delta = now - this.W_3464_O;
        this.RealmsConfirmScreen += (int)(delta / 4166666L);
        this.W_3464_O += (long)this.RealmsConfirmScreen * 4166666L;
        this.RealmsConfirmScreen = Math.min(this.RealmsConfirmScreen, 240);
        while (this.RealmsConfirmScreen > 0) {
            lightning.product.A_4115_X.n_1700_B(this.RealmsCreateRealmScreen);
            --this.RealmsConfirmScreen;
        }
        this.g_164_R.n_1700_B("Pre render");
        long i = j_3341_s.R_4764_Y();
        if (this.g_164_R.J_1907_R()) {
            this.h_1847_R();
        }
        if (this.S_980_j != null && !(this.t_4043_B instanceof u_2877_K)) {
            CompletableFuture<Void> completablefuture = this.S_980_j;
            this.S_980_j = null;
            this.w_1484_f().thenRun(() -> completablefuture.complete(null));
        }
        while ((runnable = this.j_2266_I.poll()) != null) {
            runnable.run();
        }
        if (renderWorldIn) {
            int j = this.P_1922_E.n_1700_B(j_3341_s.J_1907_R());
            this.RealmsScreenWithCallback.n_1700_B("scheduledExecutables");
            this.RealmsParentalConsentScreen();
            this.RealmsScreenWithCallback.R_4764_Y();
            this.RealmsScreenWithCallback.n_1700_B("tick");
            for (int k = 0; k < Math.min(10, j); ++k) {
                this.RealmsScreenWithCallback.R_4764_Y("clientTick");
                this.w_1457_N();
            }
            this.RealmsScreenWithCallback.R_4764_Y();
        }
        this.h_1847_R.n_1700_B();
        this.g_164_R.n_1700_B("Render");
        this.RealmsScreenWithCallback.n_1700_B("sound");
        this.k_3961_g.n_1700_B(this.s_956_w.M_588_G());
        this.RealmsScreenWithCallback.R_4764_Y();
        this.RealmsScreenWithCallback.n_1700_B("render");
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.n_1700_B(16640, n_1700_B);
        this.D_60_a.J_1907_R(true);
        j_39_h.n_1700_B();
        this.RealmsScreenWithCallback.n_1700_B("display");
        lightning.product.c_4037_x.x_607_J();
        lightning.product.c_4037_x.k_2293_S();
        this.RealmsScreenWithCallback.R_4764_Y();
        if (!this.A_4115_X) {
            this.RealmsScreenWithCallback.J_1907_R("gameRenderer");
            this.s_956_w.n_1700_B(this.S_4022_R ? this.l_4537_E : this.P_1922_E.n_1700_B, i, renderWorldIn);
            this.RealmsScreenWithCallback.J_1907_R("toasts");
            this.j_1564_a.n_1700_B(new g_221_o());
            this.RealmsScreenWithCallback.R_4764_Y();
        }
        if (this.g_4106_L != null) {
            this.RealmsScreenWithCallback.n_1700_B("fpsPie");
            this.n_1700_B(new g_221_o(), this.g_4106_L);
            this.RealmsScreenWithCallback.R_4764_Y();
        }
        this.RealmsScreenWithCallback.n_1700_B("blit");
        this.D_60_a.t_148_a();
        lightning.product.c_4037_x.d_2461_k();
        lightning.product.c_4037_x.v_4276_D();
        this.D_60_a.n_1700_B(this.g_164_R.u_2550_I(), this.g_164_R.M_588_G());
        lightning.product.c_4037_x.d_2461_k();
        this.RealmsScreenWithCallback.J_1907_R("updateDisplay");
        this.g_164_R.P_1922_E();
        int i1 = this.R_3908_n();
        if ((double)i1 < M_2935_g.FRAMERATE_LIMIT.getMaxValue()) {
            lightning.product.c_4037_x.n_1700_B(i1);
        }
        this.RealmsScreenWithCallback.J_1907_R("yield");
        Thread.yield();
        this.RealmsScreenWithCallback.R_4764_Y();
        this.g_164_R.n_1700_B("Post render");
        ++this.y_1700_S;
        boolean bl = flag = this.e_4240_b() && (this.Y_1740_V != null && this.Y_1740_V.isPauseScreen() || this.t_4043_B != null && this.t_4043_B.n_1700_B()) && !this.RegionPingResult.RealmsClientConfig();
        if (this.S_4022_R != flag) {
            if (this.S_4022_R) {
                this.l_4537_E = this.P_1922_E.n_1700_B;
            } else {
                this.P_1922_E.n_1700_B = this.l_4537_E;
            }
            this.S_4022_R = flag;
        }
        long l = j_3341_s.R_4764_Y();
        this.t_1786_h.n_1700_B(l - this.F_2624_D);
        this.F_2624_D = l;
        this.RealmsScreenWithCallback.n_1700_B("fpsUpdate");
        while (j_3341_s.J_1907_R() >= this.RealmsDefaultUncaughtExceptionHandler + 1000L) {
            x_607_J = this.y_1700_S;
            this.e_4240_b = String.format("%d fps T: %s%s%s%s B: %d", x_607_J, (double)this.P_4830_p.G_564_y == M_2935_g.FRAMERATE_LIMIT.getMaxValue() ? "inf" : Integer.valueOf(this.P_4830_p.G_564_y), this.P_4830_p.q_4610_l ? " vsync" : "", this.P_4830_p.u_1723_Y.toString(), this.P_4830_p.P_1922_E == K_2069_m.n_1700_B ? "" : (this.P_4830_p.P_1922_E == K_2069_m.J_1907_R ? " fast-clouds" : " fancy-clouds"), this.P_4830_p.x_607_J);
            this.RealmsDefaultUncaughtExceptionHandler += 1000L;
            this.y_1700_S = 0;
        }
        this.RealmsScreenWithCallback.R_4764_Y();
    }

    private boolean H_1083_k() {
        return this.P_4830_p.r_3651_U && this.P_4830_p.RowButton && !this.P_4830_p.RetryCallException;
    }

    private void n_1700_B(boolean isDebug, @Nullable W_2800_c detector) {
        if (isDebug) {
            if (!this.c_132_F.n_1700_B()) {
                this.M_2677_i = 0;
                this.c_132_F.R_4764_Y();
            }
            ++this.M_2677_i;
        } else {
            this.c_132_F.J_1907_R();
        }
    }

    private void J_1907_R(boolean isDebug, @Nullable W_2800_c detector) {
        if (detector != null) {
            detector.J_1907_R();
        }
        this.g_4106_L = null;
    }

    @Override
    public void u_2550_I() {
        int i = this.g_164_R.n_1700_B(this.P_4830_p.g_4106_L, this.v_4262_N());
        this.g_164_R.n_1700_B((double)i);
        if (this.Y_1740_V != null) {
            this.Y_1740_V.resize(this, this.g_164_R.Q_4569_t(), this.g_164_R.M_182_A());
        }
        P_4249_L framebuffer = this.G_564_y();
        framebuffer.n_1700_B(this.g_164_R.u_2550_I(), this.g_164_R.M_588_G(), n_1700_B);
        this.s_956_w.n_1700_B(this.g_164_R.u_2550_I(), this.g_164_R.M_588_G());
        this.h_1847_R.u_1723_Y();
    }

    @Override
    public void M_588_G() {
        this.h_1847_R.s_956_w();
    }

    private int R_3908_n() {
        return this.g_164_R.G_564_y();
    }

    public void P_4830_p() {
        try {
            multiplayerClientSuggestionProvider = new byte[0];
            this.u_1723_Y.M_588_G();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            System.gc();
            if (this.R_3908_n && this.RegionPingResult != null) {
                this.RegionPingResult.n_1700_B(true);
            }
            this.J_1907_R(new GenericDirtMessageScreen(new F_2904_S("menu.savingLevel")));
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        System.gc();
    }

    @Override
    void n_1700_B(int keyCount) {
        List<ResultField> list;
        if (this.g_4106_L != null && !(list = this.g_4106_L.n_1700_B(this.RealmsClientOutdatedScreen)).isEmpty()) {
            ResultField datapoint = list.remove(0);
            if (keyCount == 0) {
                int i;
                if (!datapoint.G_564_y.isEmpty() && (i = this.RealmsClientOutdatedScreen.lastIndexOf(30)) >= 0) {
                    this.RealmsClientOutdatedScreen = this.RealmsClientOutdatedScreen.substring(0, i);
                }
            } else if (--keyCount < list.size() && !"unspecified".equals(list.get((int)keyCount).G_564_y)) {
                if (!this.RealmsClientOutdatedScreen.isEmpty()) {
                    this.RealmsClientOutdatedScreen = this.RealmsClientOutdatedScreen + "\u001e";
                }
                this.RealmsClientOutdatedScreen = this.RealmsClientOutdatedScreen + list.get((int)keyCount).G_564_y;
            }
        }
    }

    private void n_1700_B(g_221_o matrixStack, ProfileResults profilerResult) {
        List<ResultField> list = profilerResult.n_1700_B(this.RealmsClientOutdatedScreen);
        ResultField datapoint = list.remove(0);
        lightning.product.c_4037_x.n_1700_B(256, n_1700_B);
        lightning.product.c_4037_x.u_2550_I(5889);
        lightning.product.c_4037_x.z_1737_N();
        lightning.product.c_4037_x.n_1700_B(0.0, this.g_164_R.u_2550_I(), this.g_164_R.M_588_G(), 0.0, 1000.0, 3000.0);
        lightning.product.c_4037_x.u_2550_I(5888);
        lightning.product.c_4037_x.z_1737_N();
        lightning.product.c_4037_x.R_4764_Y(0.0f, 0.0f, -2000.0f);
        lightning.product.c_4037_x.G_564_y(1.0f);
        lightning.product.c_4037_x.e_4240_b();
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        int i = 160;
        int j = this.g_164_R.u_2550_I() - 160 - 10;
        int k = this.g_164_R.M_588_G() - 320;
        lightning.product.c_4037_x.Y_601_j();
        bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
        bufferbuilder.pos((float)j - 176.0f, (float)k - 96.0f - 16.0f, 0.0).color(200, 0, 0, 0).endVertex();
        bufferbuilder.pos((float)j - 176.0f, k + 320, 0.0).color(200, 0, 0, 0).endVertex();
        bufferbuilder.pos((float)j + 176.0f, k + 320, 0.0).color(200, 0, 0, 0).endVertex();
        bufferbuilder.pos((float)j + 176.0f, (float)k - 96.0f - 16.0f, 0.0).color(200, 0, 0, 0).endVertex();
        tessellator.J_1907_R();
        lightning.product.c_4037_x.Y_259_p();
        double d0 = 0.0;
        for (ResultField datapoint1 : list) {
            int l = u_530_F.R_4764_Y(datapoint1.n_1700_B / 4.0) + 1;
            bufferbuilder.n_1700_B(6, E_688_b.Y_601_j);
            int i1 = datapoint1.n_1700_B();
            int j1 = i1 >> 16 & 0xFF;
            int k1 = i1 >> 8 & 0xFF;
            int l1 = i1 & 0xFF;
            bufferbuilder.pos(j, k, 0.0).color(j1, k1, l1, 255).endVertex();
            for (int i2 = l; i2 >= 0; --i2) {
                float f = (float)((d0 + datapoint1.n_1700_B * (double)i2 / (double)l) * 6.2831854820251465 / 100.0);
                float f1 = u_530_F.n_1700_B(f) * 160.0f;
                float f2 = u_530_F.J_1907_R(f) * 160.0f * 0.5f;
                bufferbuilder.pos((float)j + f1, (float)k - f2, 0.0).color(j1, k1, l1, 255).endVertex();
            }
            tessellator.J_1907_R();
            bufferbuilder.n_1700_B(5, E_688_b.Y_601_j);
            for (int l2 = l; l2 >= 0; --l2) {
                float f3 = (float)((d0 + datapoint1.n_1700_B * (double)l2 / (double)l) * 6.2831854820251465 / 100.0);
                float f4 = u_530_F.n_1700_B(f3) * 160.0f;
                float f5 = u_530_F.J_1907_R(f3) * 160.0f * 0.5f;
                if (f5 > 0.0f) continue;
                bufferbuilder.pos((float)j + f4, (float)k - f5, 0.0).color(j1 >> 1, k1 >> 1, l1 >> 1, 255).endVertex();
                bufferbuilder.pos((float)j + f4, (float)k - f5 + 10.0f, 0.0).color(j1 >> 1, k1 >> 1, l1 >> 1, 255).endVertex();
            }
            tessellator.J_1907_R();
            d0 += datapoint1.n_1700_B;
        }
        DecimalFormat decimalformat = new DecimalFormat("##0.00");
        decimalformat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
        lightning.product.c_4037_x.x_607_J();
        String s = ProfileResults.J_1907_R(datapoint.G_564_y);
        Object s1 = "";
        if (!"unspecified".equals(s)) {
            s1 = (String)s1 + "[0] ";
        }
        s1 = s.isEmpty() ? (String)s1 + "ROOT " : (String)s1 + s + " ";
        int k2 = 0xFFFFFF;
        this.t_148_a.n_1700_B(matrixStack, (String)s1, (float)(j - 160), (float)(k - 80 - 16), 0xFFFFFF);
        s1 = decimalformat.format(datapoint.J_1907_R) + "%";
        this.t_148_a.n_1700_B(matrixStack, (String)s1, (float)(j + 160 - this.t_148_a.J_1907_R((String)s1)), (float)(k - 80 - 16), 0xFFFFFF);
        for (int j2 = 0; j2 < list.size(); ++j2) {
            ResultField datapoint2 = list.get(j2);
            StringBuilder stringbuilder = new StringBuilder();
            if ("unspecified".equals(datapoint2.G_564_y)) {
                stringbuilder.append("[?] ");
            } else {
                stringbuilder.append("[").append(j2 + 1).append("] ");
            }
            Object s2 = stringbuilder.append(datapoint2.G_564_y).toString();
            this.t_148_a.n_1700_B(matrixStack, (String)s2, (float)(j - 160), (float)(k + 80 + j2 * 8 + 20), datapoint2.n_1700_B());
            s2 = decimalformat.format(datapoint2.n_1700_B) + "%";
            this.t_148_a.n_1700_B(matrixStack, (String)s2, (float)(j + 160 - 50 - this.t_148_a.J_1907_R((String)s2)), (float)(k + 80 + j2 * 8 + 20), datapoint2.n_1700_B());
            s2 = decimalformat.format(datapoint2.J_1907_R) + "%";
            this.t_148_a.n_1700_B(matrixStack, (String)s2, (float)(j + 160 - this.t_148_a.J_1907_R((String)s2)), (float)(k + 80 + j2 * 8 + 20), datapoint2.n_1700_B());
        }
    }

    public void h_1847_R() {
        this.r_3651_U = false;
    }

    public boolean Q_4569_t() {
        return this.r_3651_U;
    }

    public void J_1907_R(boolean pauseOnly) {
        if (this.Y_1740_V == null) {
            boolean flag;
            boolean bl = flag = this.e_4240_b() && !this.RegionPingResult.RealmsClientConfig();
            if (flag) {
                this.n_1700_B(new p_402_W(!pauseOnly));
                this.k_3961_g.J_1907_R();
            } else {
                this.n_1700_B(new p_402_W(true));
            }
        }
    }

    private void u_1723_Y(boolean leftClick) {
        if (!leftClick) {
            this.H_2857_Y = 0;
        }
        b_4507_u world = this.Y_601_j;
        lightning.product.n_1700_B bot1 = null;
        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
            world = bot.P_1922_E.G_564_y();
        }
        if (this.H_2857_Y <= 0) {
            HitResult effectiveMouseOver;
            if (bot1 != null ? bot1.P_1922_E.Q_2552_b.Y_601_j() : this.Y_259_p.Y_601_j()) {
                return;
            }
            HitResult i_3710_B = effectiveMouseOver = bot1 != null ? bot1.P_1922_E.Q_2552_b.v_4262_N : this.Z_875_P;
            if (leftClick && effectiveMouseOver != null && effectiveMouseOver.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
                BlockHitResult blockraytraceresult = (BlockHitResult)effectiveMouseOver;
                c_1514_x blockpos = blockraytraceresult.n_1700_B();
                if (!world.getBlockState(blockpos).v_4262_N()) {
                    b_257_Y direction = blockraytraceresult.J_1907_R();
                    lightning.product.A_4115_X.n_1700_B(new x_4991_F(this.Y_601_j.getBlockState(blockpos), blockpos, x_4991_F.n_1700_B.n_1700_B));
                    boolean botsImprovements = false;
                    for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                        if (this.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                        if (bot.P_1922_E.C_2741_M.J_1907_R(blockpos, direction)) {
                            this.v_4262_N.n_1700_B(blockpos, direction);
                            bot.P_1922_E.Q_2552_b.n_1700_B(x_1688_C.n_1700_B);
                        }
                        botsImprovements = true;
                    }
                    if (!botsImprovements && this.w_1457_N.onPlayerDamageBlock(blockpos, direction)) {
                        this.v_4262_N.n_1700_B(blockpos, direction);
                        AttackOrder.sendConditionalSwing(effectiveMouseOver, x_1688_C.n_1700_B);
                    }
                    lightning.product.A_4115_X.n_1700_B(new x_4991_F(this.Y_601_j.getBlockState(blockpos), blockpos, x_4991_F.n_1700_B.J_1907_R));
                }
            } else {
                boolean botsImprovements = false;
                for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                    if (this.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                    bot.P_1922_E.C_2741_M.J_1907_R();
                    botsImprovements = true;
                }
                if (!botsImprovements) {
                    this.w_1457_N.resetBlockRemoving();
                }
            }
        }
    }

    public void M_182_A() {
        HitResult effectiveMouseOver;
        lightning.product.n_1700_B bot1 = null;
        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            bot1 = bot;
        }
        HitResult i_3710_B = effectiveMouseOver = bot1 != null ? bot1.P_1922_E.Q_2552_b.v_4262_N : this.Z_875_P;
        if (this.H_2857_Y <= 0) {
            if (effectiveMouseOver == null) {
                d_2461_k.error("Null returned as 'hitResult', this shouldn't happen!");
                if (bot1 == null ? !this.w_1457_N.isNotCreative() : !bot1.P_1922_E.C_2741_M.v_4262_N()) {
                    return;
                }
                this.H_2857_Y = 10;
            } else {
                if (bot1 == null ? this.Y_259_p.e_4240_b() : this.T_3594_S().e_4240_b()) {
                    return;
                }
                switch (effectiveMouseOver.R_4764_Y()) {
                    case R_4764_Y: {
                        if (bot1 == null) {
                            AttackOrder.sendFixedAttack(this.Y_259_p, ((EntityHitResult)effectiveMouseOver).n_1700_B());
                            break;
                        }
                        bot1.P_1922_E.C_2741_M.n_1700_B(this.T_3594_S(), ((EntityHitResult)effectiveMouseOver).n_1700_B());
                        break;
                    }
                    case J_1907_R: {
                        b_4507_u world;
                        BlockHitResult blockraytraceresult = (BlockHitResult)effectiveMouseOver;
                        c_1514_x blockpos = blockraytraceresult.n_1700_B();
                        b_4507_u b_4507_u2 = world = bot1 != null ? bot1.P_1922_E.G_564_y() : this.Y_601_j;
                        if (!world.getBlockState(blockpos).v_4262_N()) {
                            boolean botsImprovements = false;
                            for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                                if (this.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                                bot.P_1922_E.C_2741_M.n_1700_B(blockpos, blockraytraceresult.J_1907_R());
                                botsImprovements = true;
                            }
                            if (botsImprovements) break;
                            this.w_1457_N.clickBlock(blockpos, blockraytraceresult.J_1907_R());
                            break;
                        }
                    }
                    case n_1700_B: {
                        if (bot1 == null) {
                            if (this.w_1457_N.isNotCreative()) {
                                this.H_2857_Y = 10;
                            }
                            this.Y_259_p.ModuleCategory();
                            break;
                        }
                        if (bot1.P_1922_E.C_2741_M.v_4262_N()) {
                            this.H_2857_Y = 10;
                        }
                        this.T_3594_S().ModuleCategory();
                    }
                }
                if (bot1 == null) {
                    AttackOrder.sendConditionalSwing(effectiveMouseOver, x_1688_C.n_1700_B);
                } else {
                    this.T_3594_S().n_1700_B(x_1688_C.n_1700_B);
                }
            }
        }
    }

    public void t_1786_h() {
        HitResult effectiveMouseOver;
        X_4340_E currentPlayer = this.Y_259_p;
        lightning.product.n_1700_B bot1 = null;
        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            currentPlayer = bot.P_1922_E.Q_2552_b;
            bot1 = bot;
        }
        if (bot1 == null ? this.w_1457_N.getIsHittingBlock() : bot1.P_1922_E.C_2741_M.h_1847_R()) {
            return;
        }
        this.c_3005_b = 4;
        if (bot1 != null ? bot1.P_1922_E.Q_2552_b.e_4240_b() : this.Y_259_p.e_4240_b()) {
            return;
        }
        HitResult i_3710_B = effectiveMouseOver = bot1 != null ? bot1.P_1922_E.Q_2552_b.v_4262_N : this.Z_875_P;
        if (effectiveMouseOver == null) {
            d_2461_k.warn("Null returned as 'hitResult', this shouldn't happen!");
        }
        for (x_1688_C hand : x_1688_C.values()) {
            m_3054_I actionresulttype2;
            Z_1993_T itemstack = currentPlayer.R_4764_Y(hand);
            if (effectiveMouseOver != null) {
                switch (effectiveMouseOver.R_4764_Y()) {
                    case R_4764_Y: {
                        EntityHitResult entityraytraceresult = (EntityHitResult)effectiveMouseOver;
                        N_4263_v entity = entityraytraceresult.n_1700_B();
                        m_3054_I actionresulttype = bot1 == null ? this.w_1457_N.interactWithEntity(this.Y_259_p, entity, entityraytraceresult, hand) : bot1.P_1922_E.C_2741_M.n_1700_B(currentPlayer, entity, entityraytraceresult, hand);
                        if (!actionresulttype.n_1700_B()) {
                            actionresulttype = bot1 == null ? this.w_1457_N.interactWithEntity(this.Y_259_p, entity, hand) : bot1.P_1922_E.C_2741_M.n_1700_B((a_3913_L)currentPlayer, entity, hand);
                        }
                        T_437_o event = new T_437_o(entity);
                        lightning.product.A_4115_X.n_1700_B(event);
                        if (event.n_1700_B() || !actionresulttype.n_1700_B()) break;
                        if (actionresulttype.J_1907_R()) {
                            currentPlayer.n_1700_B(hand);
                        }
                        return;
                    }
                    case J_1907_R: {
                        HitResult rayTraceResult;
                        if (bot1 == null && (rayTraceResult = F_1446_q.n_1700_B().n_1700_B(this.w_1457_N.getBlockReachDistance(), this.Y_259_p.p_178_J, this.Y_259_p.f_4016_n, this.Y_259_p, ClipContext.n_1700_B.R_4764_Y)).R_4764_Y() == HitResult.n_1700_B.J_1907_R && rayTraceResult instanceof BlockHitResult) {
                            m_3054_I openWallsResult;
                            BlockHitResult traceResult = (BlockHitResult)rayTraceResult;
                            if (ClientBootstrap.Y_601_j().J_1907_R().P_4830_p().w_1484_f() && this.Y_259_p.A_2714_y().J_1907_R() != Items.s_3084_y && (openWallsResult = this.w_1457_N.func_217292_a(this.Y_259_p, this.Y_601_j, hand, traceResult)).n_1700_B()) {
                                if (openWallsResult.J_1907_R()) {
                                    this.Y_259_p.n_1700_B(hand);
                                }
                                return;
                            }
                        }
                        BlockHitResult blockraytraceresult = (BlockHitResult)effectiveMouseOver;
                        int i = itemstack.t_4043_B();
                        m_3054_I actionresulttype1 = bot1 == null ? this.w_1457_N.func_217292_a(this.Y_259_p, this.Y_601_j, hand, blockraytraceresult) : bot1.P_1922_E.C_2741_M.n_1700_B(bot1.P_1922_E.Q_2552_b, bot1.P_1922_E.G_564_y(), hand, blockraytraceresult);
                        if (actionresulttype1.n_1700_B()) {
                            if (actionresulttype1.J_1907_R()) {
                                currentPlayer.n_1700_B(hand);
                                if (!itemstack.n_1700_B()) {
                                    if (itemstack.t_4043_B() != i) {
                                        this.s_956_w.n_1700_B.n_1700_B(hand);
                                    } else if (bot1 != null) {
                                        if (bot1.P_1922_E.C_2741_M.w_1484_f()) {
                                            this.s_956_w.n_1700_B.n_1700_B(hand);
                                        }
                                    } else if (this.w_1457_N.isInCreativeMode()) {
                                        this.s_956_w.n_1700_B.n_1700_B(hand);
                                    }
                                }
                            }
                            return;
                        }
                        if (actionresulttype1 != m_3054_I.G_564_y) break;
                        return;
                    }
                }
            }
            b_2625_m eventCheck = new b_2625_m(itemstack, hand);
            lightning.product.A_4115_X.n_1700_B(eventCheck);
            if (eventCheck.n_1700_B()) {
                return;
            }
            if (itemstack.n_1700_B() || !(actionresulttype2 = bot1 != null ? bot1.P_1922_E.C_2741_M.n_1700_B((a_3913_L)currentPlayer, bot1.P_1922_E.G_564_y(), hand) : this.w_1457_N.processRightClick(this.Y_259_p, this.Y_601_j, hand)).n_1700_B()) continue;
            if (actionresulttype2.J_1907_R()) {
                currentPlayer.n_1700_B(hand);
            }
            this.s_956_w.n_1700_B.n_1700_B(hand);
            return;
        }
    }

    public MusicManager multiplayerClientSuggestionProvider() {
        return this.Ops;
    }

    private boolean G_564_y(k_2603_m screen) {
        return BaritoneAPI.getProvider().getPrimaryBaritone().getPathingBehavior().isPathing() && this.Y_259_p != null || screen.passEvents;
    }

    public void w_1457_N() {
        if (this.c_3005_b > 0) {
            --this.c_3005_b;
        }
        lightning.product.A_4115_X.n_1700_B(new TickEvent.ClientTickEvent(TickEvent.Phase.START));
        this.RealmsScreenWithCallback.n_1700_B("gui");
        if (!this.S_4022_R) {
            this.M_588_G.J_1907_R();
        }
        this.RealmsScreenWithCallback.R_4764_Y();
        this.s_956_w.J_1907_R(1.0f);
        this.s_956_w.R_4764_Y(1.0f);
        this.RealmsWorldOptions.n_1700_B(this.Y_601_j, this.Z_875_P);
        this.RealmsScreenWithCallback.n_1700_B("gameMode");
        if (!this.S_4022_R && this.Y_601_j != null) {
            this.w_1457_N.tick();
            if (lightning.product.J_1907_R.n_1700_B != null && !lightning.product.J_1907_R.n_1700_B.isEmpty()) {
                for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                    if (bot == null || bot.P_1922_E == null || bot.P_1922_E.C_2741_M == null) continue;
                    try {
                        bot.P_1922_E.C_2741_M.G_564_y();
                        if (bot.P_1922_E.Q_2552_b == null || !(bot.P_1922_E.Q_2552_b.g_46_E() <= 0.0f)) continue;
                        bot.P_1922_E.Q_2552_b.G_564_y();
                        this.n_1700_B(bot.P_1922_E.w_1484_f);
                    }
                    catch (Exception exception) {}
                }
            }
        }
        this.RealmsScreenWithCallback.J_1907_R("textures");
        if (this.Y_601_j != null) {
            this.g_221_o.tick();
        }
        boolean baritoneIgnoreScreen = false;
        try {
            baritoneIgnoreScreen = BaritoneSettings.v_4262_N.t_148_a() != false && ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BaritoneSettings.class) != null && ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BaritoneSettings.class).w_1484_f();
        }
        catch (Exception bot) {
            // empty catch block
        }
        if ((this.Y_1740_V == null || baritoneIgnoreScreen) && this.Y_259_p != null) {
            BiFunction<EventState, TickEvent.Type, TickEvent> tickProvider = TickEvent.createNextProvider();
            for (IBaritone baritone2 : BaritoneAPI.getProvider().getAllBaritones()) {
                TickEvent.Type type = baritone2.getPlayerContext().player() != null && baritone2.getPlayerContext().world() != null ? TickEvent.Type.IN : TickEvent.Type.OUT;
                baritone2.getGameEventHandler().onTick(tickProvider.apply(EventState.PRE, type));
            }
            if (this.Y_259_p.Z_2812_M() && !(this.Y_1740_V instanceof E_1407_D)) {
                this.n_1700_B((k_2603_m)null);
            } else if (this.Y_259_p.z_2372_L() && this.Y_601_j != null) {
                this.n_1700_B(new InBedChatScreen());
            }
        } else if (this.Y_1740_V != null && this.Y_1740_V instanceof InBedChatScreen && !this.Y_259_p.z_2372_L()) {
            this.n_1700_B((k_2603_m)null);
        }
        if (this.Y_1740_V != null) {
            this.H_2857_Y = 10000;
        }
        if (this.Y_1740_V != null) {
            k_2603_m.wrapScreenError(() -> this.Y_1740_V.tick(), "Ticking screen", this.Y_1740_V.getClass().getCanonicalName());
        }
        if (!this.P_4830_p.r_3651_U) {
            this.M_588_G.s_956_w();
        }
        if (this.t_4043_B == null && (this.Y_1740_V == null || this.Y_1740_V.passEvents || this.G_564_y(this.Y_1740_V))) {
            this.RealmsScreenWithCallback.J_1907_R("Keybindings");
            this.F_1410_V();
            if (this.H_2857_Y > 0) {
                --this.H_2857_Y;
            }
        }
        if (this.Y_601_j != null) {
            this.RealmsScreenWithCallback.J_1907_R("gameRenderer");
            if (!this.S_4022_R) {
                this.s_956_w.u_1723_Y();
            }
            this.RealmsScreenWithCallback.J_1907_R("levelRenderer");
            if (!this.S_4022_R) {
                this.u_1723_Y.s_956_w();
            }
            this.RealmsScreenWithCallback.J_1907_R("level");
            if (!this.S_4022_R) {
                IBaritone baritone;
                if (this.Y_601_j.P_4830_p() > 0) {
                    this.Y_601_j.R_4764_Y(this.Y_601_j.P_4830_p() - 1);
                }
                this.Y_601_j.R_4764_Y();
                if (lightning.product.J_1907_R.n_1700_B != null && !lightning.product.J_1907_R.n_1700_B.isEmpty()) {
                    for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                        if (bot == null || bot.P_1922_E == null || bot.P_1922_E.G_564_y() == null) continue;
                        try {
                            bot.P_1922_E.G_564_y().R_4764_Y();
                        }
                        catch (Exception baritone2) {}
                    }
                }
                if ((baritone = BaritoneAPI.getProvider().getBaritoneForPlayer(this.Y_259_p)) != null) {
                    baritone.getGameEventHandler().onPlayerUpdate(new PlayerUpdateEvent(EventState.POST));
                }
            }
        } else if (this.s_956_w.v_4262_N() != null) {
            this.s_956_w.G_564_y();
        }
        if (!this.S_4022_R) {
            this.Ops.n_1700_B();
        }
        this.k_3961_g.n_1700_B(this.S_4022_R);
        if (this.Y_601_j != null) {
            lightning.product.n_1700_B activeBot;
            if (!this.S_4022_R) {
                if (!this.P_4830_p.t_4043_B && this.ValueObject()) {
                    F_2904_S itextcomponent = new F_2904_S("tutorial.socialInteractions.title");
                    F_2904_S itextcomponent1 = new F_2904_S("tutorial.socialInteractions.description", W_1671_y.n_1700_B("socialInteractions"));
                    this.R_3077_Z = new TutorialToast(TutorialToast.n_1700_B.u_1723_Y, itextcomponent, itextcomponent1, true);
                    this.RealmsWorldOptions.n_1700_B(this.R_3077_Z, 160);
                    this.P_4830_p.t_4043_B = true;
                    this.P_4830_p.J_1907_R();
                }
                this.RealmsWorldOptions.G_564_y();
                try {
                    this.Y_601_j.n_1700_B(() -> true);
                    if (lightning.product.J_1907_R.n_1700_B != null && !lightning.product.J_1907_R.n_1700_B.isEmpty()) {
                        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                            if (bot == null || bot.P_1922_E == null || bot.P_1922_E.G_564_y() == null) continue;
                            try {
                                bot.P_1922_E.G_564_y().n_1700_B(() -> true);
                            }
                            catch (Exception baritone2) {}
                        }
                    }
                }
                catch (Throwable throwable) {
                    n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Exception in world tick");
                    if (this.Y_601_j == null) {
                        CrashReportCategory crashreportcategory = crashreport.n_1700_B("Affected level");
                        crashreportcategory.n_1700_B("Problem", "Level is null!");
                    } else {
                        this.Y_601_j.n_1700_B(crashreport);
                    }
                    throw new ReportedException(crashreport);
                }
            }
            this.RealmsScreenWithCallback.J_1907_R("animateTick");
            if (!this.S_4022_R && this.Y_601_j != null) {
                this.Y_601_j.n_1700_B(u_530_F.R_4764_Y(this.Y_259_p.O_3598_v()), u_530_F.R_4764_Y(this.Y_259_p.X_2960_b()), u_530_F.R_4764_Y(this.Y_259_p.l_2647_k()));
            }
            if ((activeBot = this.k_2293_S) != null && this.C_2741_M == activeBot.P_1922_E.Q_2552_b && activeBot.P_1922_E.G_564_y() != null) {
                activeBot.P_1922_E.G_564_y().n_1700_B(u_530_F.R_4764_Y(activeBot.P_1922_E.Q_2552_b.O_3598_v()), u_530_F.R_4764_Y(activeBot.P_1922_E.Q_2552_b.X_2960_b()), u_530_F.R_4764_Y(activeBot.P_1922_E.Q_2552_b.l_2647_k()));
            }
            this.RealmsScreenWithCallback.J_1907_R("particles");
            if (!this.S_4022_R) {
                this.v_4262_N.R_4764_Y();
            }
        } else if (this.Q_2552_b != null) {
            this.RealmsScreenWithCallback.J_1907_R("pendingConnection");
            this.Q_2552_b.n_1700_B();
            if (lightning.product.J_1907_R.n_1700_B != null && !lightning.product.J_1907_R.n_1700_B.isEmpty()) {
                for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                    if (bot == null || bot.P_1922_E == null) continue;
                    try {
                        bot.P_1922_E.getBotNetwork().J_1907_R();
                    }
                    catch (Exception exception) {}
                }
            }
        }
        this.RealmsScreenWithCallback.J_1907_R("keyboard");
        this.Q_4569_t.J_1907_R();
        this.RealmsScreenWithCallback.R_4764_Y();
        lightning.product.A_4115_X.n_1700_B(new TickEvent.ClientTickEvent(TickEvent.Phase.END));
    }

    private boolean ValueObject() {
        return !this.R_3908_n || this.RegionPingResult != null && this.RegionPingResult.RealmsClientConfig();
    }

    private void F_1410_V() {
        boolean isHandActiveForUse;
        boolean isHandActive;
        boolean flag2;
        lightning.product.n_1700_B bot1 = this.k_2293_S;
        if (bot1 == null || this.C_2741_M != bot1.P_1922_E.Q_2552_b) {
            for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (this.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                bot1 = bot;
                this.k_2293_S = bot;
                break;
            }
        }
        while (this.P_4830_p.V_1225_t.u_1723_Y()) {
            l_1268_F eventCancelThirdPerson = new l_1268_F();
            lightning.product.A_4115_X.n_1700_B(eventCancelThirdPerson);
            if (!eventCancelThirdPerson.n_1700_B()) {
                t_1920_R newPOV;
                t_1920_R pointofview = this.P_4830_p.P_4830_p();
                this.P_4830_p.n_1700_B(this.P_4830_p.P_4830_p().R_4764_Y());
                if (pointofview.n_1700_B() != this.P_4830_p.P_4830_p().n_1700_B()) {
                    this.s_956_w.n_1700_B(this.P_4830_p.P_4830_p().n_1700_B() ? this.g_2268_R() : null);
                }
                if (!(newPOV = this.P_4830_p.P_4830_p()).n_1700_B() && !newPOV.J_1907_R()) {
                    this.s_956_w.J_1907_R();
                }
            }
            this.u_1723_Y.h_1847_R();
        }
        while (this.P_4830_p.U_1241_n.u_1723_Y()) {
            this.P_4830_p.S_980_j = !this.P_4830_p.S_980_j;
        }
        for (int i = 0; i < 9; ++i) {
            boolean isCreative;
            boolean isSpectator;
            boolean flag = this.P_4830_p.j_1564_a.G_564_y();
            boolean flag1 = this.P_4830_p.M_1641_O.G_564_y();
            if (!this.P_4830_p.RealmsServerPing[i].u_1723_Y()) continue;
            boolean bl = isSpectator = bot1 != null ? bot1.P_1922_E.Q_2552_b.d_2461_k() : this.Y_259_p.d_2461_k();
            if (isSpectator) {
                this.M_588_G.u_1723_Y().n_1700_B(i);
                continue;
            }
            boolean bl2 = isCreative = bot1 != null ? bot1.P_1922_E.Q_2552_b.G_624_v() : this.Y_259_p.G_624_v();
            if (isCreative && this.Y_1740_V == null && (flag1 || flag)) {
                B_3091_S.n_1700_B(this, i, flag1, flag);
                continue;
            }
            f_1574_f eventCancelHotbar = new f_1574_f();
            lightning.product.A_4115_X.n_1700_B(eventCancelHotbar);
            if (eventCancelHotbar.n_1700_B()) continue;
            if (bot1 != null) {
                bot1.P_1922_E.Q_2552_b.l_1268_F.G_564_y = i;
                continue;
            }
            this.Y_259_p.l_1268_F.G_564_y = i;
        }
        while (this.P_4830_p.V_1446_Y.u_1723_Y()) {
            if (!this.ValueObject()) {
                this.Y_259_p.n_1700_B(T_2506_i, true);
                I_1084_e.J_1907_R.n_1700_B(T_2506_i.getString());
                continue;
            }
            if (this.R_3077_Z != null) {
                this.RealmsWorldOptions.n_1700_B(this.R_3077_Z);
                this.R_3077_Z = null;
            }
            this.n_1700_B(new F_1723_g());
        }
        while (this.P_4830_p.f_4016_n.u_1723_Y()) {
            if (bot1 != null) {
                if (bot1.P_1922_E.C_2741_M.s_956_w()) {
                    bot1.P_1922_E.Q_2552_b.P_4830_p();
                    continue;
                }
                this.RealmsWorldOptions.n_1700_B();
                this.n_1700_B(new Q_1939_l(bot1.P_1922_E.Q_2552_b));
                continue;
            }
            if (this.w_1457_N.isRidingHorse()) {
                this.Y_259_p.P_4830_p();
                continue;
            }
            this.RealmsWorldOptions.n_1700_B();
            this.n_1700_B(new Q_1939_l(this.Y_259_p));
        }
        while (this.P_4830_p.w_612_n.u_1723_Y()) {
            this.n_1700_B(new Z_3926_G(this.Y_259_p.n_1700_B.w_1484_f()));
        }
        while (this.P_4830_p.j_276_v.u_1723_Y()) {
            if (bot1 != null) {
                if (bot1.P_1922_E.Q_2552_b.d_2461_k()) continue;
                bot1.P_1922_E.getBotNetwork().n_1700_B(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.n_1700_B.v_4262_N, c_1514_x.ZERO, b_257_Y.n_1700_B));
                continue;
            }
            if (this.Y_259_p.d_2461_k()) continue;
            this.k_2293_S().n_1700_B(new ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.n_1700_B.v_4262_N, c_1514_x.ZERO, b_257_Y.n_1700_B));
        }
        while (this.P_4830_p.UploadStatus.u_1723_Y()) {
            X_4340_E dropPlayer = bot1 != null ? bot1.P_1922_E.Q_2552_b : this.Y_259_p;
            h_2848_I eventDropItem = new h_2848_I(dropPlayer.l_1268_F.G_564_y);
            lightning.product.A_4115_X.n_1700_B(eventDropItem);
            if (eventDropItem.n_1700_B() || ((a_3913_L)dropPlayer).d_2461_k() || !dropPlayer.n_1700_B(k_2603_m.hasControlDown())) continue;
            dropPlayer.n_1700_B(x_1688_C.n_1700_B);
        }
        boolean bl = flag2 = this.P_4830_p.s_956_w != g_4418_P.R_4764_Y;
        while (this.P_4830_p.Ops.u_1723_Y()) {
            this.J_1907_R("");
        }
        if (this.Y_1740_V == null && this.t_4043_B == null && this.P_4830_p.t_4219_U.u_1723_Y()) {
            this.J_1907_R("/");
        }
        boolean bl3 = isHandActive = bot1 != null ? bot1.P_1922_E.Q_2552_b.Y_601_j() : this.Y_259_p.Y_601_j();
        if (isHandActive) {
            if (!this.P_4830_p.e_1992_r.G_564_y()) {
                boolean botsHandled = false;
                for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                    if (this.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
                    bot.P_1922_E.C_2741_M.J_1907_R(bot.P_1922_E.Q_2552_b);
                    botsHandled = true;
                }
                if (!botsHandled) {
                    this.w_1457_N.onStoppedUsingItem(this.Y_259_p);
                }
            }
            while (this.P_4830_p.D_60_a.u_1723_Y()) {
            }
            while (this.P_4830_p.e_1992_r.u_1723_Y()) {
            }
            while (this.P_4830_p.k_3961_g.u_1723_Y()) {
            }
        } else {
            while (this.P_4830_p.D_60_a.u_1723_Y()) {
                this.M_182_A();
            }
            while (this.P_4830_p.e_1992_r.u_1723_Y()) {
                this.t_1786_h();
            }
            while (this.P_4830_p.k_3961_g.u_1723_Y()) {
                this.S_4022_R();
            }
        }
        boolean bl4 = isHandActiveForUse = bot1 != null ? bot1.P_1922_E.Q_2552_b.Y_601_j() : this.Y_259_p.Y_601_j();
        if (this.P_4830_p.e_1992_r.G_564_y() && this.c_3005_b == 0 && !isHandActiveForUse) {
            this.t_1786_h();
        }
        this.u_1723_Y(this.Y_1740_V == null && this.P_4830_p.D_60_a.G_564_y() && this.h_1847_R.v_4262_N());
    }

    public static DataPackConfig n_1700_B(b_2971_z.n_1700_B worldStorage) {
        net.minecraft.server.G_564_y.n_1700_B(worldStorage);
        DataPackConfig datapackcodec = worldStorage.P_1922_E();
        if (datapackcodec == null) {
            throw new IllegalStateException("Failed to load data pack config");
        }
        return datapackcodec;
    }

    public static WorldData n_1700_B(b_2971_z.n_1700_B worldStorage, r_4097_j.J_1907_R dynamicRegistries, ResourceManager resourceManager, DataPackConfig datapackCodec) {
        F_877_l<Tag> worldsettingsimport = F_877_l.n_1700_B(l_4118_l.n_1700_B, resourceManager, dynamicRegistries);
        WorldData iserverconfiguration = worldStorage.n_1700_B(worldsettingsimport, datapackCodec);
        if (iserverconfiguration == null) {
            throw new IllegalStateException("Failed to load world");
        }
        return iserverconfiguration;
    }

    @Override
    public void n_1700_B(String worldName) {
        this.n_1700_B(worldName, r_4097_j.J_1907_R(), MinecraftClient::n_1700_B, (Function4<b_2971_z.n_1700_B, r_4097_j.J_1907_R, ResourceManager, DataPackConfig, WorldData>)((Function4)MinecraftClient::n_1700_B), false, lightning.product.MinecraftClient$J_1907_R.R_4764_Y);
    }

    public void n_1700_B(String worldName, B_4315_y worldSettings, r_4097_j.J_1907_R dynamicRegistriesIn, j_419_j dimensionGeneratorSettings) {
        this.n_1700_B(worldName, dynamicRegistriesIn, worldStorage -> worldSettings.v_4262_N(), (Function4<b_2971_z.n_1700_B, r_4097_j.J_1907_R, ResourceManager, DataPackConfig, WorldData>)((Function4)(worldStorage, dynamicRegistries, resourceManager, datapackCodec) -> {
            RegistryWriteOps worldgensettingsexport = RegistryWriteOps.n_1700_B(JsonOps.INSTANCE, dynamicRegistriesIn);
            F_877_l worldsettingsimport = F_877_l.n_1700_B(JsonOps.INSTANCE, resourceManager, dynamicRegistriesIn);
            DataResult dataresult = j_419_j.n_1700_B.encodeStart(worldgensettingsexport, (Object)dimensionGeneratorSettings).setLifecycle(Lifecycle.stable()).flatMap(p_243209_1_ -> j_419_j.n_1700_B.parse((DynamicOps)worldsettingsimport, p_243209_1_));
            j_419_j dimensiongeneratorsettings = dataresult.resultOrPartial(j_3341_s.n_1700_B("Error reading worldgen settings after loading data packs: ", arg_0 -> ((Logger)d_2461_k).error(arg_0))).orElse(dimensionGeneratorSettings);
            return new c_3102_J(worldSettings, dimensiongeneratorsettings, dataresult.lifecycle());
        }), false, lightning.product.MinecraftClient$J_1907_R.J_1907_R);
    }

    private void n_1700_B(String worldName, r_4097_j.J_1907_R dynamicRegistries, Function<b_2971_z.n_1700_B, DataPackConfig> levelSaveToDatapackFunction, Function4<b_2971_z.n_1700_B, r_4097_j.J_1907_R, ResourceManager, DataPackConfig, WorldData> quadFunction, boolean vanillaOnly, J_1907_R selectionType) {
        boolean flag1;
        n_1700_B minecraft$packmanager;
        b_2971_z.n_1700_B saveformat$levelsave;
        try {
            saveformat$levelsave = this.O_508_d.R_4764_Y(worldName);
        }
        catch (IOException ioexception2) {
            d_2461_k.warn("Failed to read level {} data", (Object)worldName, (Object)ioexception2);
            SystemToast.n_1700_B(this, worldName);
            this.n_1700_B((k_2603_m)null);
            return;
        }
        try {
            minecraft$packmanager = this.n_1700_B(dynamicRegistries, levelSaveToDatapackFunction, quadFunction, vanillaOnly, saveformat$levelsave);
        }
        catch (Exception exception) {
            d_2461_k.warn("Failed to load datapacks, can't proceed with server load", (Throwable)exception);
            this.n_1700_B(new DatapackLoadFailureScreen(() -> this.n_1700_B(worldName, dynamicRegistries, levelSaveToDatapackFunction, quadFunction, true, selectionType)));
            try {
                saveformat$levelsave.close();
            }
            catch (IOException ioexception) {
                d_2461_k.warn("Failed to unlock access to level {}", (Object)worldName, (Object)ioexception);
            }
            return;
        }
        WorldData iserverconfiguration = minecraft$packmanager.R_4764_Y();
        boolean flag = iserverconfiguration.e_4240_b().t_148_a();
        boolean bl = flag1 = iserverconfiguration.n_3318_d() != Lifecycle.stable();
        if (selectionType == lightning.product.MinecraftClient$J_1907_R.n_1700_B || !flag && !flag1) {
            this.Y_601_j();
            this.T_3594_S.set(null);
            try {
                saveformat$levelsave.n_1700_B(dynamicRegistries, iserverconfiguration);
                minecraft$packmanager.J_1907_R().t_148_a();
                YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(this.z_1333_t);
                MinecraftSessionService minecraftsessionservice = yggdrasilauthenticationservice.createMinecraftSessionService();
                GameProfileRepository gameprofilerepository = yggdrasilauthenticationservice.createProfileRepository();
                W_1689_V playerprofilecache = new W_1689_V(gameprofilerepository, new File(this.M_182_A, net.minecraft.server.G_564_y.n_1700_B.getName()));
                O_2639_P.n_1700_B(playerprofilecache);
                O_2639_P.n_1700_B(minecraftsessionservice);
                W_1689_V.n_1700_B(false);
                this.RegionPingResult = net.minecraft.server.G_564_y.n_1700_B((Thread thread) -> new R_3197_Z((Thread)thread, this, dynamicRegistries, saveformat$levelsave, minecraft$packmanager.n_1700_B(), minecraft$packmanager.J_1907_R(), iserverconfiguration, minecraftsessionservice, gameprofilerepository, playerprofilecache, radius -> {
                    StoringChunkProgressListener trackingchunkstatuslistener = new StoringChunkProgressListener(radius + 0);
                    trackingchunkstatuslistener.J_1907_R();
                    this.T_3594_S.set(trackingchunkstatuslistener);
                    return new ProcessorChunkProgressListener(trackingchunkstatuslistener, this.j_2266_I::add);
                }));
                this.R_3908_n = true;
            }
            catch (Throwable throwable) {
                n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Starting integrated server");
                CrashReportCategory crashreportcategory = crashreport.n_1700_B("Starting integrated server");
                crashreportcategory.n_1700_B("Level ID", worldName);
                crashreportcategory.n_1700_B("Level Name", iserverconfiguration.P_4830_p());
                throw new ReportedException(crashreport);
            }
            while (this.T_3594_S.get() == null) {
                Thread.yield();
            }
            O_1280_J worldloadprogressscreen = new O_1280_J(this.T_3594_S.get());
            this.n_1700_B(worldloadprogressscreen);
            this.RealmsScreenWithCallback.n_1700_B("waitForServer");
            while (!this.RegionPingResult.j_276_v()) {
                worldloadprogressscreen.tick();
                this.P_1922_E(false);
                try {
                    Thread.sleep(16L);
                }
                catch (InterruptedException crashreport) {
                    // empty catch block
                }
                if (this.RowButton == null) continue;
                MinecraftClient.J_1907_R(this.RowButton);
                return;
            }
            this.RealmsScreenWithCallback.R_4764_Y();
            SocketAddress socketaddress = this.RegionPingResult.f_4016_n().n_1700_B();
            c_1633_k networkmanager = c_1633_k.n_1700_B(socketaddress);
            networkmanager.n_1700_B(new Q_936_s(networkmanager, this, null, statusMessage -> {}));
            networkmanager.n_1700_B(new ClientIntentionPacket(socketaddress.toString(), 0, d_4952_K.G_564_y));
            networkmanager.n_1700_B(new ServerboundHelloPacket(this.z_1737_N().P_1922_E()));
            this.Q_2552_b = networkmanager;
        } else {
            this.n_1700_B(selectionType, worldName, flag, () -> this.n_1700_B(worldName, dynamicRegistries, levelSaveToDatapackFunction, quadFunction, vanillaOnly, lightning.product.MinecraftClient$J_1907_R.n_1700_B));
            minecraft$packmanager.close();
            try {
                saveformat$levelsave.close();
            }
            catch (IOException ioexception1) {
                d_2461_k.warn("Failed to unlock access to level {}", (Object)worldName, (Object)ioexception1);
            }
        }
    }

    private void n_1700_B(J_1907_R selectionType, String worldName, boolean customized, Runnable runnable) {
        if (selectionType == lightning.product.MinecraftClient$J_1907_R.R_4764_Y) {
            F_2904_S itextcomponent1;
            F_2904_S itextcomponent;
            if (customized) {
                itextcomponent = new F_2904_S("selectWorld.backupQuestion.customized");
                itextcomponent1 = new F_2904_S("selectWorld.backupWarning.customized");
            } else {
                itextcomponent = new F_2904_S("selectWorld.backupQuestion.experimental");
                itextcomponent1 = new F_2904_S("selectWorld.backupWarning.experimental");
            }
            this.n_1700_B(new O_3892_W(null, (editMode, checkedBox) -> {
                if (editMode) {
                    s_1875_c.n_1700_B(this.O_508_d, worldName);
                }
                runnable.run();
            }, itextcomponent, itextcomponent1, false));
        } else {
            this.n_1700_B(new q_3418_t(confirm -> {
                if (confirm) {
                    runnable.run();
                } else {
                    this.n_1700_B((k_2603_m)null);
                    try (b_2971_z.n_1700_B saveformat$levelsave = this.O_508_d.R_4764_Y(worldName);){
                        saveformat$levelsave.v_4262_N();
                    }
                    catch (IOException ioexception) {
                        SystemToast.J_1907_R(this, worldName);
                        d_2461_k.error("Failed to delete world {}", (Object)worldName, (Object)ioexception);
                    }
                }
            }, new F_2904_S("selectWorld.backupQuestion.experimental"), new F_2904_S("selectWorld.backupWarning.experimental"), CommonComponents.v_4262_N, CommonComponents.G_564_y));
        }
    }

    public n_1700_B n_1700_B(r_4097_j.J_1907_R dynamicRegistries, Function<b_2971_z.n_1700_B, DataPackConfig> worldStorageToDatapackFunction, Function4<b_2971_z.n_1700_B, r_4097_j.J_1907_R, ResourceManager, DataPackConfig, WorldData> quadFunction, boolean vanillaOnly, b_2971_z.n_1700_B worldStorage) throws InterruptedException, ExecutionException {
        DataPackConfig datapackcodec = worldStorageToDatapackFunction.apply(worldStorage);
        PackRepository resourcepacklist = new PackRepository(new T_4652_I(), new e_4716_U(worldStorage.n_1700_B(H_4757_Q.v_4262_N).toFile(), PackSource.R_4764_Y));
        try {
            DataPackConfig datapackcodec1 = net.minecraft.server.G_564_y.n_1700_B(resourcepacklist, datapackcodec, vanillaOnly);
            CompletableFuture<ServerResources> completablefuture = ServerResources.n_1700_B(resourcepacklist.u_1723_Y(), Q_2241_p.n_1700_B.R_4764_Y, 2, j_3341_s.u_1723_Y(), this);
            this.R_4764_Y(completablefuture::isDone);
            ServerResources datapackregistries = completablefuture.get();
            WorldData iserverconfiguration = (WorldData)quadFunction.apply((Object)worldStorage, (Object)dynamicRegistries, (Object)datapackregistries.w_1484_f(), (Object)datapackcodec1);
            return new n_1700_B(resourcepacklist, datapackregistries, iserverconfiguration);
        }
        catch (InterruptedException | ExecutionException interruptedexception) {
            resourcepacklist.close();
            throw interruptedexception;
        }
    }

    @Override
    public void n_1700_B(k_4690_i worldClientIn) {
        BaritoneAPI.getProvider().getPrimaryBaritone().getGameEventHandler().onWorldEvent(new WorldEvent(this.Y_601_j, EventState.PRE));
        if (this.Y_601_j != null) {
            lightning.product.A_4115_X.n_1700_B(new WorldEvent.Unload(this.Y_601_j));
        }
        u_4650_L workingscreen = new u_4650_L();
        workingscreen.n_1700_B(new F_2904_S("connect.joining"));
        this.P_1922_E(workingscreen);
        this.Y_601_j = worldClientIn;
        this.J_1907_R(worldClientIn);
        if (!this.R_3908_n) {
            YggdrasilAuthenticationService authenticationservice = new YggdrasilAuthenticationService(this.z_1333_t);
            MinecraftSessionService minecraftsessionservice = authenticationservice.createMinecraftSessionService();
            GameProfileRepository gameprofilerepository = authenticationservice.createProfileRepository();
            W_1689_V playerprofilecache = new W_1689_V(gameprofilerepository, new File(this.M_182_A, net.minecraft.server.G_564_y.n_1700_B.getName()));
            O_2639_P.n_1700_B(playerprofilecache);
            O_2639_P.n_1700_B(minecraftsessionservice);
            W_1689_V.n_1700_B(false);
        }
        BaritoneAPI.getProvider().getPrimaryBaritone().getGameEventHandler().onWorldEvent(new WorldEvent(this.Y_601_j, EventState.POST));
        E_3343_g eventWorld = new E_3343_g(E_3343_g.n_1700_B.n_1700_B);
        lightning.product.A_4115_X.n_1700_B(eventWorld);
    }

    @Override
    public void n_1700_B(c_3005_b worldClientIn) {
        this.J_1907_R(worldClientIn);
        if (!this.R_3908_n) {
            YggdrasilAuthenticationService authenticationservice = new YggdrasilAuthenticationService(this.z_1333_t);
            MinecraftSessionService minecraftsessionservice = authenticationservice.createMinecraftSessionService();
            GameProfileRepository gameprofilerepository = authenticationservice.createProfileRepository();
            W_1689_V playerprofilecache = new W_1689_V(gameprofilerepository, new File(this.M_182_A, net.minecraft.server.G_564_y.n_1700_B.getName()));
            O_2639_P.n_1700_B(playerprofilecache);
            O_2639_P.n_1700_B(minecraftsessionservice);
            W_1689_V.n_1700_B(false);
        }
    }

    private void J_1907_R(@Nullable c_3005_b worldIn) {
        this.u_1723_Y.n_1700_B(worldIn);
        this.v_4262_N.n_1700_B(worldIn);
        f_2689_h.J_1907_R.n_1700_B(worldIn);
        this.n_1700_B();
    }

    public void Y_601_j() {
        this.J_1907_R(new u_4650_L());
    }

    public void J_1907_R(k_2603_m screenIn) {
        W_2853_p clientplaynethandler = this.k_2293_S();
        if (clientplaynethandler != null) {
            this.i_2993_w();
            clientplaynethandler.J_1907_R();
        }
        R_3197_Z integratedserver = this.RegionPingResult;
        this.RegionPingResult = null;
        this.s_956_w.w_1484_f();
        lightning.product.A_4115_X.n_1700_B(new ClientPlayerNetworkEvent.LoggedOutEvent(this.w_1457_N, this.Y_259_p, this.Y_259_p != null ? (this.Y_259_p.n_1700_B != null ? this.Y_259_p.n_1700_B.getNetworkManager() : null) : null));
        this.w_1457_N = null;
        I_1084_e.J_1907_R.J_1907_R();
        this.P_1922_E(screenIn);
        if (this.Y_601_j != null) {
            if (integratedserver != null) {
                lightning.product.A_4115_X.n_1700_B(new WorldEvent.Unload(this.Y_601_j));
                this.RealmsScreenWithCallback.n_1700_B("waitForServer");
                while (!integratedserver.t_4043_B()) {
                    this.P_1922_E(false);
                }
                this.RealmsScreenWithCallback.R_4764_Y();
            }
            this.RealmsClientConfig.J_1907_R();
            this.M_588_G.w_1484_f();
            this.H_1083_k = null;
            this.R_3908_n = false;
            this.M_1641_O.R_4764_Y();
        }
        this.Y_601_j = null;
        this.J_1907_R((k_4690_i)null);
        this.Y_259_p = null;
    }

    @Override
    private void P_1922_E(k_2603_m screenIn) {
        this.RealmsScreenWithCallback.n_1700_B("forcedTick");
        this.k_3961_g.R_4764_Y();
        this.C_2741_M = null;
        this.Q_2552_b = null;
        this.n_1700_B(screenIn);
        this.P_1922_E(false);
        this.RealmsScreenWithCallback.R_4764_Y();
    }

    public void R_4764_Y(k_2603_m screen) {
        this.RealmsScreenWithCallback.n_1700_B("forcedTick");
        this.n_1700_B(screen);
        this.P_1922_E(false);
        this.RealmsScreenWithCallback.R_4764_Y();
    }

    private void J_1907_R(@Nullable k_4690_i worldIn) {
        this.u_1723_Y.n_1700_B(worldIn);
        this.v_4262_N.n_1700_B(worldIn);
        f_2689_h.J_1907_R.n_1700_B(worldIn);
        this.n_1700_B();
    }

    public boolean Y_259_p() {
        return this.i_1637_u;
    }

    public boolean n_1700_B(UUID playerUUID) {
        if (this.Q_2552_b()) {
            return this.RealmsWorldResetDto.R_4764_Y(playerUUID);
        }
        return (this.Y_259_p == null || !playerUUID.equals(this.Y_259_p.w_2705_t())) && !playerUUID.equals(j_3341_s.J_1907_R);
    }

    public boolean Q_2552_b() {
        return this.Ping;
    }

    public final boolean C_2741_M() {
        return this.A_1038_p;
    }

    @Nullable
    public W_2853_p k_2293_S() {
        return this.Y_259_p == null ? null : this.Y_259_p.n_1700_B;
    }

    public static boolean q_2307_F() {
        return !MinecraftClient.v_4276_D.P_4830_p.RetryCallException;
    }

    public static boolean Z_875_P() {
        return MinecraftClient.v_4276_D.P_4830_p.u_1723_Y.n_1700_B() >= P_3084_J.J_1907_R.n_1700_B();
    }

    public static boolean c_3005_b() {
        return MinecraftClient.v_4276_D.P_4830_p.u_1723_Y.n_1700_B() >= P_3084_J.R_4764_Y.n_1700_B();
    }

    public static boolean H_2857_Y() {
        return MinecraftClient.v_4276_D.P_4830_p.v_4262_N != G_463_a.n_1700_B;
    }

    private void S_4022_R() {
        if (this.Z_875_P != null && this.Z_875_P.R_4764_Y() != HitResult.n_1700_B.n_1700_B) {
            Z_1993_T itemstack;
            boolean flag = this.Y_259_p.C_415_h.G_564_y;
            i_2154_H tileentity = null;
            HitResult.n_1700_B raytraceresult$type = this.Z_875_P.R_4764_Y();
            if (raytraceresult$type == HitResult.n_1700_B.J_1907_R) {
                c_1514_x blockpos = ((BlockHitResult)this.Z_875_P).n_1700_B();
                K_4074_S blockstate = this.Y_601_j.getBlockState(blockpos);
                T_2915_h block = blockstate.J_1907_R();
                if (blockstate.v_4262_N()) {
                    return;
                }
                itemstack = block.n_1700_B((BlockGetter)this.Y_601_j, blockpos, blockstate);
                if (itemstack.n_1700_B()) {
                    return;
                }
                if (flag && k_2603_m.hasControlDown() && block.G_564_y()) {
                    tileentity = this.Y_601_j.getTileEntity(blockpos);
                }
            } else {
                if (raytraceresult$type != HitResult.n_1700_B.R_4764_Y || !flag) {
                    return;
                }
                N_4263_v entity = ((EntityHitResult)this.Z_875_P).n_1700_B();
                if (entity instanceof Painting) {
                    itemstack = new Z_1993_T(Items.q_3115_L);
                } else if (entity instanceof LeashFenceKnotEntity) {
                    itemstack = new Z_1993_T(Items.c_1788_D);
                } else if (entity instanceof y_740_d) {
                    y_740_d itemframeentity = (y_740_d)entity;
                    Z_1993_T itemstack1 = itemframeentity.h_1847_R();
                    itemstack = itemstack1.n_1700_B() ? new Z_1993_T(Items.DeadBushBlock) : itemstack1.t_148_a();
                } else if (entity instanceof y_4319_k) {
                    y_4319_k abstractminecartentity = (y_4319_k)entity;
                    itemstack = new Z_1993_T(switch (abstractminecartentity.h_1847_R()) {
                        case y_4319_k.n_1700_B.R_4764_Y -> Items.y_4842_Z;
                        case y_4319_k.n_1700_B.J_1907_R -> Items.k_1366_K;
                        case y_4319_k.n_1700_B.G_564_y -> Items.h_935_G;
                        case y_4319_k.n_1700_B.u_1723_Y -> Items.s_3834_w;
                        case y_4319_k.n_1700_B.v_4262_N -> Items.FaceAttachedHorizontalDirectionalBlock;
                        default -> Items.u_925_K;
                    });
                } else if (entity instanceof g_1462_f) {
                    itemstack = new Z_1993_T(((g_1462_f)entity).P_1922_E());
                } else if (entity instanceof D_686_b) {
                    itemstack = new Z_1993_T(Items.p_1168_n);
                } else if (entity instanceof V_3354_l) {
                    itemstack = new Z_1993_T(Items.LoomBlock);
                } else {
                    SpawnEggItem spawneggitem = SpawnEggItem.n_1700_B(entity.f_4016_n());
                    if (spawneggitem == null) {
                        return;
                    }
                    itemstack = new Z_1993_T(spawneggitem);
                }
            }
            if (itemstack.n_1700_B()) {
                String s = "";
                if (raytraceresult$type == HitResult.n_1700_B.J_1907_R) {
                    s = V_3137_a.q_4610_l.J_1907_R(this.Y_601_j.getBlockState(((BlockHitResult)this.Z_875_P).n_1700_B()).J_1907_R()).toString();
                } else if (raytraceresult$type == HitResult.n_1700_B.R_4764_Y) {
                    s = V_3137_a.g_221_o.J_1907_R(((EntityHitResult)this.Z_875_P).n_1700_B().f_4016_n()).toString();
                }
                d_2461_k.warn("Picking on: [{}] {} gave null item", (Object)raytraceresult$type, (Object)s);
            } else {
                W_3491_f playerinventory = this.Y_259_p.l_1268_F;
                if (tileentity != null) {
                    this.n_1700_B(itemstack, tileentity);
                }
                int i = playerinventory.J_1907_R(itemstack);
                if (flag) {
                    playerinventory.n_1700_B(itemstack);
                    this.w_1457_N.sendSlotPacket(this.Y_259_p.R_4764_Y(x_1688_C.n_1700_B), 36 + playerinventory.G_564_y);
                } else if (i != -1) {
                    if (W_3491_f.J_1907_R(i)) {
                        playerinventory.G_564_y = i;
                    } else {
                        this.w_1457_N.pickItem(i);
                    }
                }
            }
        }
    }

    private Z_1993_T n_1700_B(Z_1993_T stack, i_2154_H te) {
        U_2912_j compoundnbt = te.n_1700_B(new U_2912_j());
        if (stack.J_1907_R() instanceof o_12_W && compoundnbt.P_1922_E("SkullOwner")) {
            U_2912_j compoundnbt2 = compoundnbt.M_182_A("SkullOwner");
            stack.M_182_A().n_1700_B("SkullOwner", compoundnbt2);
            return stack;
        }
        stack.n_1700_B("BlockEntityTag", compoundnbt);
        U_2912_j compoundnbt1 = new U_2912_j();
        q_2896_o listnbt = new q_2896_o();
        listnbt.add(StringTag.n_1700_B("\"(+NBT)\""));
        compoundnbt1.n_1700_B("Lore", listnbt);
        stack.n_1700_B("display", compoundnbt1);
        return stack;
    }

    public n_3236_c R_4764_Y(n_3236_c theCrash) {
        MinecraftClient.n_1700_B(this.j_276_v, this.s_2632_s, this.P_4830_p, theCrash);
        if (this.Y_601_j != null) {
            this.Y_601_j.n_1700_B(theCrash);
        }
        return theCrash;
    }

    public static void n_1700_B(@Nullable LanguageManager languageManagerIn, String versionIn, @Nullable V_4423_d settingsIn, n_3236_c crashReportIn) {
        CrashReportCategory crashreportcategory = crashReportIn.u_1723_Y();
        crashreportcategory.n_1700_B("Launched Version", () -> versionIn);
        crashreportcategory.n_1700_B("Backend library", c_4037_x::T_2506_i);
        crashreportcategory.n_1700_B("Backend API", c_4037_x::q_4610_l);
        crashreportcategory.n_1700_B("GL Caps", c_4037_x::e_2887_G);
        crashreportcategory.n_1700_B("Using VBOs", () -> "Yes");
        crashreportcategory.n_1700_B("Is Modded", () -> {
            String s1 = p_752_J.n_1700_B();
            if (!"vanilla".equals(s1)) {
                return "Definitely; Client brand changed to '" + s1 + "'";
            }
            return MinecraftClient.class.getSigners() == null ? "Very likely; Jar signature invalidated" : "Probably not. Jar signature remains and client brand is untouched.";
        });
        crashreportcategory.n_1700_B("Type", "Client (map_client.txt)");
        if (settingsIn != null) {
            String s;
            if (v_4276_D != null && (s = v_4276_D.X_933_l().P_4830_p()) != null) {
                crashreportcategory.n_1700_B("GPU Warnings", s);
            }
            crashreportcategory.n_1700_B("Graphics mode", (Object)settingsIn.u_1723_Y);
            crashreportcategory.n_1700_B("Resource Packs", () -> {
                StringBuilder stringbuilder = new StringBuilder();
                for (String s1 : settingsIn.w_1484_f) {
                    if (stringbuilder.length() > 0) {
                        stringbuilder.append(", ");
                    }
                    stringbuilder.append(s1);
                    if (!settingsIn.t_148_a.contains(s1)) continue;
                    stringbuilder.append(" (incompatible)");
                }
                return stringbuilder.toString();
            });
        }
        if (languageManagerIn != null) {
            crashreportcategory.n_1700_B("Current Language", () -> languageManagerIn.J_1907_R().toString());
        }
        crashreportcategory.n_1700_B("CPU", Z_976_R::J_1907_R);
    }

    public static MinecraftClient A_4115_X() {
        return v_4276_D;
    }

    public CompletableFuture<Void> Y_1740_V() {
        return ((H_3272_P)this).n_1700_B(this::w_1484_f).thenCompose(voidIn -> voidIn);
    }

    @Override
    public void n_1700_B(E_2727_F snooper) {
        snooper.n_1700_B("fps", x_607_J);
        snooper.n_1700_B("vsync_enabled", this.P_4830_p.q_4610_l);
        snooper.n_1700_B("display_frequency", this.g_164_R.n_1700_B());
        snooper.n_1700_B("display_type", this.g_164_R.s_956_w() ? "fullscreen" : "windowed");
        snooper.n_1700_B("run_time", (j_3341_s.J_1907_R() - snooper.u_1723_Y()) / 60L * 1000L);
        snooper.n_1700_B("current_action", this.l_4537_E());
        snooper.n_1700_B("language", this.P_4830_p.RealmsConfirmScreen == null ? "en_us" : this.P_4830_p.RealmsConfirmScreen);
        String s = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN ? "little" : "big";
        snooper.n_1700_B("endianness", s);
        snooper.n_1700_B("subtitles", this.P_4830_p.H_1990_U);
        snooper.n_1700_B("touch", this.P_4830_p.c_4037_x ? "touch" : "mouse");
        int i = 0;
        for (D_2103_L resourcepackinfo : this.f_4016_n.P_1922_E()) {
            if (resourcepackinfo.u_1723_Y() || resourcepackinfo.v_4262_N()) continue;
            snooper.n_1700_B("resource_pack[" + i++ + "]", resourcepackinfo.P_1922_E());
        }
        snooper.n_1700_B("resource_packs", i);
        if (this.RegionPingResult != null) {
            snooper.n_1700_B("snooper_partner", this.RegionPingResult.D_60_a().P_1922_E());
        }
    }

    private String l_4537_E() {
        if (this.RegionPingResult != null) {
            return this.RegionPingResult.RealmsClientConfig() ? "hosting_lan" : "singleplayer";
        }
        if (this.H_1083_k != null) {
            return this.H_1083_k.G_564_y() ? "playing_lan" : "multiplayer";
        }
        return "out_of_game";
    }

    @Override
    public void n_1700_B(@Nullable ServerData serverDataIn) {
        this.H_1083_k = serverDataIn;
    }

    @Nullable
    public ServerData t_4043_B() {
        return this.H_1083_k;
    }

    public boolean x_607_J() {
        return this.R_3908_n;
    }

    public boolean e_4240_b() {
        return this.R_3908_n && this.RegionPingResult != null;
    }

    @Nullable
    public R_3197_Z n_3318_d() {
        return this.RegionPingResult;
    }

    public E_2727_F d_2427_y() {
        return this.X_933_l;
    }

    public u_3100_Q z_1737_N() {
        return this.w_1484_f;
    }

    public PropertyMap v_4276_D() {
        if (this.z_4693_k.isEmpty()) {
            GameProfile gameprofile = this.N_2525_X().fillProfileProperties(this.w_1484_f.P_1922_E(), false);
            this.z_4693_k.putAll((Multimap)gameprofile.getProperties());
        }
        return this.z_4693_k;
    }

    public Proxy d_2461_k() {
        return this.z_1333_t;
    }

    public C_3240_x G_624_v() {
        return this.g_221_o;
    }

    public ResourceManager T_2506_i() {
        return this.p_178_J;
    }

    public PackRepository q_4610_l() {
        return this.f_4016_n;
    }

    public L_1105_I z_4693_k() {
        return this.RealmsClientConfig;
    }

    public File g_221_o() {
        return this.q_4610_l;
    }

    public LanguageManager e_2887_G() {
        return this.j_276_v;
    }

    public Function<g_2336_b, B_3871_I> n_1700_B(g_2336_b locationIn) {
        return this.q_1982_R.n_1700_B(locationIn)::J_1907_R;
    }

    public boolean B_1668_F() {
        return this.r_715_M;
    }

    public boolean g_164_R() {
        return this.S_4022_R;
    }

    public K_2357_w X_933_l() {
        return this.V_1446_Y;
    }

    public k_4218_M Z_976_R() {
        return this.k_3961_g;
    }

    public Music H_1990_U() {
        if (this.Y_1740_V instanceof T_1088_H) {
            return Musics.R_4764_Y;
        }
        if (this.Y_259_p != null) {
            if (this.Y_259_p.O_508_d.g_2268_R() == b_4507_u.w_1484_f) {
                return this.M_588_G.t_148_a().G_564_y() ? Musics.G_564_y : Musics.P_1922_E;
            }
            k_594_Q.R_4764_Y biome$category = this.Y_259_p.O_508_d.P_1922_E(this.Y_259_p.b_2312_j()).Y_601_j();
            if (!this.Ops.J_1907_R(Musics.u_1723_Y) && (!this.Y_259_p.z_1737_N() || biome$category != k_594_Q.R_4764_Y.M_588_G && biome$category != k_594_Q.R_4764_Y.h_1847_R)) {
                return this.Y_259_p.O_508_d.g_2268_R() != b_4507_u.v_4262_N && this.Y_259_p.C_415_h.G_564_y && this.Y_259_p.C_415_h.R_4764_Y ? Musics.J_1907_R : this.Y_601_j.z_1737_N().J_1907_R(this.Y_259_p.b_2312_j()).w_1457_N().orElse(Musics.v_4262_N);
            }
            return Musics.u_1723_Y;
        }
        return Musics.n_1700_B;
    }

    public MinecraftSessionService N_2525_X() {
        return this.PlayerInfo;
    }

    public D_4667_H c_4037_x() {
        return this.U_1241_n;
    }

    @Nullable
    public N_4263_v g_2268_R() {
        return this.C_2741_M;
    }

    @Override
    public void n_1700_B(N_4263_v viewingEntity) {
        this.C_2741_M = viewingEntity;
        this.k_2293_S = null;
        this.s_956_w.n_1700_B(viewingEntity);
    }

    public Z_875_P T_3594_S() {
        if (this.k_2293_S != null && this.C_2741_M == this.k_2293_S.P_1922_E.Q_2552_b) {
            return this.k_2293_S.P_1922_E.Q_2552_b;
        }
        for (lightning.product.n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (this.C_2741_M != bot.P_1922_E.Q_2552_b) continue;
            this.k_2293_S = bot;
            return bot.P_1922_E.Q_2552_b;
        }
        this.k_2293_S = null;
        return null;
    }

    public void D_4792_h() {
        for (w_1457_N bot : lightning.product.J_1907_R.R_4764_Y) {
            bot.J_1907_R();
        }
    }

    public void s_2632_s() {
        for (w_1457_N bot : lightning.product.J_1907_R.R_4764_Y) {
            bot.n_1700_B(this.s_956_w);
        }
    }

    @Override
    public boolean J_1907_R(N_4263_v entity) {
        return entity.j_306_t() || this.Y_259_p != null && this.Y_259_p.d_2461_k() && this.P_4830_p.dtoRealmsServerAddress.G_564_y() && entity.f_4016_n() == t_5_h.g_4106_L;
    }

    @Override
    protected Thread l_1233_K() {
        return this.RetryCallException;
    }

    @Override
    protected Runnable n_1700_B(Runnable runnable) {
        return runnable;
    }

    @Override
    protected boolean J_1907_R(Runnable runnable) {
        return true;
    }

    public e_3977_C z_1333_t() {
        return this.dtoRealmsServerAddress;
    }

    public w_2040_b O_508_d() {
        return this.H_1990_U;
    }

    public H_3330_w r_715_M() {
        return this.N_2525_X;
    }

    public l_456_f A_1038_p() {
        return this.c_4037_x;
    }

    @Override
    public <T> MutableSearchTree<T> n_1700_B(SearchRegistry.n_1700_B<T> key) {
        return this.g_2268_R.n_1700_B(key);
    }

    public FrameTimer i_1637_u() {
        return this.t_1786_h;
    }

    public boolean Ping() {
        return this.u_744_e;
    }

    public void R_4764_Y(boolean isConnected) {
        this.u_744_e = isConnected;
    }

    public DataFixer p_178_J() {
        return this.e_2887_G;
    }

    public float RealmsClientConfig() {
        return this.P_1922_E.n_1700_B;
    }

    public float f_4016_n() {
        return this.P_1922_E.J_1907_R;
    }

    public k_4467_X j_276_v() {
        return this.UploadStatus;
    }

    public boolean UploadStatus() {
        return this.Y_259_p != null && this.Y_259_p.y_3417_N() || this.P_4830_p.X_933_l;
    }

    public D_1624_i e_1992_r() {
        return this.j_1564_a;
    }

    public W_1671_y D_60_a() {
        return this.RealmsWorldOptions;
    }

    public boolean k_3961_g() {
        return this.LongRunningTask;
    }

    public HotbarManager Ops() {
        return this.D_4792_h;
    }

    public ModelManager h_4320_q() {
        return this.q_1982_R;
    }

    public PaintingTextureManager t_4219_U() {
        return this.w_612_n;
    }

    public MobEffectTextureManager V_1446_Y() {
        return this.RealmsServerPing;
    }

    @Override
    public void G_564_y(boolean focused) {
        this.LongRunningTask = focused;
    }

    public ProfilerFiller PlayerInfo() {
        return InactiveProfiler.n_1700_B;
    }

    public q_3575_f V_1225_t() {
        return this.M_1641_O;
    }

    public W_1619_c U_1241_n() {
        return this.t_4219_U;
    }

    @Nullable
    public Overlay q_1982_R() {
        return this.t_4043_B;
    }

    public PlayerSocialManager dtoRealmsServerAddress() {
        return this.RealmsWorldResetDto;
    }

    public boolean w_612_n() {
        return false;
    }

    public U_679_Y RealmsServerPing() {
        return this.g_164_R;
    }

    public RenderBuffers j_1564_a() {
        return this.Z_976_R;
    }

    private static D_2103_L n_1700_B(String name, boolean isAlwaysEnabled, Supplier<PackResources> resourceSupplier, PackResources resourcePack, PackMetadataSection resourcePackMeta, D_2103_L.J_1907_R priority, PackSource decorator) {
        int i = resourcePackMeta.J_1907_R();
        Supplier<PackResources> supplier = resourceSupplier;
        if (i <= 3) {
            supplier = MinecraftClient.J_1907_R(resourceSupplier);
        }
        if (i <= 4) {
            supplier = MinecraftClient.R_4764_Y(supplier);
        }
        return new D_2103_L(name, isAlwaysEnabled, supplier, resourcePack, resourcePackMeta, priority, decorator);
    }

    private static Supplier<PackResources> J_1907_R(Supplier<PackResources> resourcePackSupplier) {
        return () -> new r_3127_A((PackResources)resourcePackSupplier.get(), r_3127_A.n_1700_B);
    }

    private static Supplier<PackResources> R_4764_Y(Supplier<PackResources> resourcePackSupplier) {
        return () -> new j_1086_C((PackResources)resourcePackSupplier.get());
    }

    public void J_1907_R(int mipMapLevel) {
        this.q_1982_R.n_1700_B(mipMapLevel);
    }

    static {
        d_2461_k = LogManager.getLogger();
        n_1700_B = j_3341_s.t_148_a() == j_3341_s.J_1907_R.G_564_y;
        J_1907_R = new g_2336_b("default");
        R_4764_Y = new g_2336_b("uniform");
        G_564_y = new g_2336_b("alt");
        G_624_v = CompletableFuture.completedFuture(X_1446_C.n_1700_B);
        T_2506_i = new F_2904_S("multiplayer.socialInteractions.not_available");
        multiplayerClientSuggestionProvider = new byte[0xA00000];
    }

    static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] G_564_y;

        public static J_1907_R[] values() {
            return (J_1907_R[])G_564_y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.MinecraftClient$J_1907_R.n_1700_B();
        }
    }

    public static final class n_1700_B
    implements AutoCloseable {
        private final PackRepository n_1700_B;
        private final ServerResources J_1907_R;
        private final WorldData R_4764_Y;

        private n_1700_B(PackRepository resourcePackList, ServerResources datapackRegistries, WorldData serverConfiguration) {
            this.n_1700_B = resourcePackList;
            this.J_1907_R = datapackRegistries;
            this.R_4764_Y = serverConfiguration;
        }

        public PackRepository n_1700_B() {
            return this.n_1700_B;
        }

        public ServerResources J_1907_R() {
            return this.J_1907_R;
        }

        public WorldData R_4764_Y() {
            return this.R_4764_Y;
        }

        @Override
        public void close() {
            this.n_1700_B.close();
            this.J_1907_R.close();
        }
    }
}



