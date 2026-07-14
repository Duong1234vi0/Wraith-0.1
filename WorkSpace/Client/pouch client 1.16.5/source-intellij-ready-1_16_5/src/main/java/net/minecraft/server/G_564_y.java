/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Splitter
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.GameProfileRepository
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  com.mojang.datafixers.DataFixer
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufOutputStream
 *  io.netty.buffer.Unpooled
 *  it.unimi.dsi.fastutil.longs.LongIterator
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.minecraft.server;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.datafixers.DataFixer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.longs.LongIterator;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.net.Proxy;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import lightning.product.A_2352_Z;
import lightning.product.A_4115_X;
import lightning.product.SaveDataDirtyRunnable;
import lightning.product.B_4088_l;
import lightning.product.B_4315_y;
import lightning.product.TagContainer;
import lightning.product.C_3615_s;
import lightning.product.D_2103_L;
import lightning.product.CommandSource;
import lightning.product.E_2727_F;
import lightning.product.ReentrantBlockableEventLoop;
import lightning.product.PackRepository;
import lightning.product.CustomSpawner;
import lightning.product.InactiveProfiler;
import lightning.product.F_2904_S;
import lightning.product.LevelStem;
import lightning.product.G_3474_H;
import lightning.product.G_4584_Z;
import lightning.product.BiomeManager;
import lightning.product.ClientboundChangeDifficultyPacket;
import lightning.product.H_4757_Q;
import lightning.product.I_14_v;
import lightning.product.I_1965_o;
import lightning.product.SharedConstants;
import lightning.product.J_4848_t;
import lightning.product.K_2528_s;
import lightning.product.K_398_W;
import lightning.product.ServerStatus;
import lightning.product.L_4413_M;
import lightning.product.ServerFunctionManager;
import lightning.product.BorderChangeListener;
import lightning.product.P_3504_Q;
import lightning.product.Q_2241_p;
import lightning.product.ChunkProgressListenerFactory;
import lightning.product.R_2450_T;
import lightning.product.LevelData;
import lightning.product.T_2915_h;
import lightning.product.T_603_v;
import lightning.product.U_2871_b;
import lightning.product.Features;
import lightning.product.V_3137_a;
import lightning.product.W_1689_V;
import lightning.product.W_2800_c;
import lightning.product.X_1446_C;
import lightning.product.ProfilerFiller;
import lightning.product.PlayerRespawnLogic;
import lightning.product.Y_1387_d;
import lightning.product.Y_2605_X;
import lightning.product.Y_589_b;
import lightning.product.Z_3903_F;
import lightning.product.Z_4308_L;
import lightning.product.ServerResources;
import lightning.product.ProfileResults;
import lightning.product.a_1738_M;
import lightning.product.ForcedChunksSavedData;
import lightning.product.a_3913_L;
import lightning.product.PackResources;
import lightning.product.ConfiguredFeature;
import lightning.product.b_2085_h;
import lightning.product.b_2971_z;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelData;
import lightning.product.ContinuousProfiler;
import lightning.product.TextFilter;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.f_4186_T;
import lightning.product.g_1995_W;
import lightning.product.g_2336_b;
import lightning.product.DataPackConfig;
import lightning.product.ServerScoreboard;
import lightning.product.i_452_g;
import lightning.product.ProgressListener;
import lightning.product.TicketType;
import lightning.product.j_3341_s;
import lightning.product.GameTestTicker;
import lightning.product.ChunkProgressListener;
import lightning.product.j_419_j;
import lightning.product.k_1471_n;
import lightning.product.FrameTimer;
import lightning.product.l_4845_j;
import lightning.product.WorldData;
import lightning.product.n_3236_c;
import lightning.product.BiomeSource;
import lightning.product.ServerAdvancementManager;
import lightning.product.ReportedException;
import lightning.product.BlockTags;
import lightning.product.ClientboundSetTimePacket;
import lightning.product.r_4097_j;
import lightning.product.s_4380_l;
import lightning.product.u_1863_S;
import lightning.product.u_530_F;
import lightning.product.v_1758_J;
import lightning.product.Crypt;
import lightning.product.DerivedLevelData;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import lightning.product.z_1753_f;
import lightning.product.z_3520_U;
import mods.voicechat.eventforge.FMLServerStartedEvent;
import mods.voicechat.eventforge.FMLServerStoppingEvent;
import mods.voicechat.eventforge.TickEvent;
import mods.voicechat.eventforge.WorldEvent;
import net.minecraft.server.J_1907_R;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class G_564_y
extends ReentrantBlockableEventLoop<i_452_g>
implements AutoCloseable,
CommandSource,
Y_589_b {
    private static final Logger t_148_a = LogManager.getLogger();
    public static final File n_1700_B = new File("usercache.json");
    public static final B_4315_y J_1907_R = new B_4315_y("Demo World", I_14_v.J_1907_R, false, R_2450_T.R_4764_Y, false, new A_2352_Z(), DataPackConfig.n_1700_B);
    protected final b_2971_z.n_1700_B R_4764_Y;
    protected final Z_4308_L G_564_y;
    private final E_2727_F s_956_w = new E_2727_F("server", this, j_3341_s.J_1907_R());
    private final List<Runnable> u_2550_I = Lists.newArrayList();
    private final ContinuousProfiler M_588_G = new ContinuousProfiler(j_3341_s.n_1700_B, this::e_1992_r);
    private ProfilerFiller P_4830_p = InactiveProfiler.n_1700_B;
    private final l_4845_j h_1847_R;
    private final ChunkProgressListenerFactory Q_4569_t;
    private final ServerStatus M_182_A = new ServerStatus();
    private final Random t_1786_h = new Random();
    private final DataFixer multiplayerClientSuggestionProvider;
    private String w_1457_N;
    private int Y_601_j = -1;
    protected final r_4097_j.J_1907_R P_1922_E;
    private final Map<f_2392_k<b_4507_u>, e_3591_l> Y_259_p = Maps.newLinkedHashMap();
    private g_1995_W Q_2552_b;
    private volatile boolean C_2741_M = true;
    private boolean k_2293_S;
    private int q_2307_F;
    protected final Proxy u_1723_Y;
    private boolean Z_875_P;
    private boolean c_3005_b;
    private boolean H_2857_Y;
    private boolean A_4115_X;
    @Nullable
    private String Y_1740_V;
    private int t_4043_B;
    private int x_607_J;
    public final long[] v_4262_N = new long[100];
    @Nullable
    private KeyPair e_4240_b;
    @Nullable
    private String n_3318_d;
    private boolean d_2427_y;
    private String z_1737_N = "";
    private String v_4276_D = "";
    private volatile boolean d_2461_k;
    private long G_624_v;
    private boolean T_2506_i;
    private boolean q_4610_l;
    private final MinecraftSessionService z_4693_k;
    private final GameProfileRepository g_221_o;
    private final W_1689_V e_2887_G;
    private long B_1668_F;
    private final Thread g_164_R;
    private long X_933_l = j_3341_s.J_1907_R();
    private long Z_976_R;
    private boolean H_1990_U;
    private boolean N_2525_X;
    private final PackRepository c_4037_x;
    private final ServerScoreboard g_2268_R = new ServerScoreboard(this);
    @Nullable
    private J_4848_t D_4792_h;
    private final J_1907_R s_2632_s = new J_1907_R();
    private final ServerFunctionManager l_1233_K;
    private final FrameTimer z_1333_t = new FrameTimer();
    private boolean O_508_d;
    private float r_715_M;
    private final Executor A_1038_p;
    @Nullable
    private String i_1637_u;
    private ServerResources Ping;
    private final b_2085_h p_178_J;
    protected final WorldData w_1484_f;
    private static AtomicBoolean RealmsClientConfig = new AtomicBoolean(false);

    public static <S extends G_564_y> S n_1700_B(Function<Thread, S> p_240784_0_) {
        AtomicReference<G_564_y> atomicreference = new AtomicReference<G_564_y>();
        Thread thread = new Thread(() -> ((G_564_y)atomicreference.get()).C_2741_M(), "Server thread");
        thread.setUncaughtExceptionHandler((p_240779_0_, p_240779_1_) -> t_148_a.error((Object)p_240779_1_));
        G_564_y s = (G_564_y)p_240784_0_.apply(thread);
        atomicreference.set(s);
        thread.start();
        return (S)s;
    }

    public G_564_y(Thread p_i232576_1_, r_4097_j.J_1907_R p_i232576_2_, b_2971_z.n_1700_B p_i232576_3_, WorldData p_i232576_4_, PackRepository p_i232576_5_, Proxy p_i232576_6_, DataFixer p_i232576_7_, ServerResources p_i232576_8_, MinecraftSessionService p_i232576_9_, GameProfileRepository p_i232576_10_, W_1689_V p_i232576_11_, ChunkProgressListenerFactory p_i232576_12_) {
        super("Server");
        this.P_1922_E = p_i232576_2_;
        this.w_1484_f = p_i232576_4_;
        this.u_1723_Y = p_i232576_6_;
        this.c_4037_x = p_i232576_5_;
        this.Ping = p_i232576_8_;
        this.z_4693_k = p_i232576_9_;
        this.g_221_o = p_i232576_10_;
        this.e_2887_G = p_i232576_11_;
        this.h_1847_R = new l_4845_j(this);
        this.Q_4569_t = p_i232576_12_;
        this.R_4764_Y = p_i232576_3_;
        this.G_564_y = p_i232576_3_.J_1907_R();
        this.multiplayerClientSuggestionProvider = p_i232576_7_;
        this.l_1233_K = new ServerFunctionManager(this, p_i232576_8_.n_1700_B());
        this.p_178_J = new b_2085_h(p_i232576_8_.w_1484_f(), p_i232576_3_, p_i232576_7_);
        this.g_164_R = p_i232576_1_;
        this.A_1038_p = j_3341_s.u_1723_Y();
    }

    @Override
    private void n_1700_B(s_4380_l p_213204_1_) {
        K_2528_s scoreboardsavedata = p_213204_1_.n_1700_B(K_2528_s::new, "scoreboard");
        scoreboardsavedata.n_1700_B(this.S_4022_R());
        this.S_4022_R().n_1700_B(new SaveDataDirtyRunnable(scoreboardsavedata));
    }

    protected abstract boolean u_2550_I() throws IOException;

    public static void n_1700_B(b_2971_z.n_1700_B p_240777_0_) {
        if (p_240777_0_.R_4764_Y()) {
            t_148_a.info("Converting map!");
            p_240777_0_.n_1700_B(new ProgressListener(){
                private long n_1700_B = j_3341_s.J_1907_R();

                @Override
                public void n_1700_B(x_282_a component) {
                }

                @Override
                public void J_1907_R(x_282_a component) {
                }

                @Override
                public void n_1700_B(int progress) {
                    if (j_3341_s.J_1907_R() - this.n_1700_B >= 1000L) {
                        this.n_1700_B = j_3341_s.J_1907_R();
                        t_148_a.info("Converting... {}%", (Object)progress);
                    }
                }

                @Override
                public void n_1700_B() {
                }

                @Override
                public void R_4764_Y(x_282_a component) {
                }
            });
        }
    }

    protected void M_588_G() {
        this.h_1847_R();
        this.w_1484_f.n_1700_B(this.d_2427_y(), this.z_1737_N().isPresent());
        ChunkProgressListener ichunkstatuslistener = this.Q_4569_t.create(11);
        this.n_1700_B(ichunkstatuslistener);
        this.P_4830_p();
        this.J_1907_R(ichunkstatuslistener);
    }

    protected void P_4830_p() {
    }

    @Override
    protected void n_1700_B(ChunkProgressListener p_240787_1_) {
        z_1753_f chunkgenerator;
        Z_3903_F dimensiontype;
        ServerLevelData iserverworldinfo = this.w_1484_f.H_2857_Y();
        j_419_j dimensiongeneratorsettings = this.w_1484_f.e_4240_b();
        boolean flag = dimensiongeneratorsettings.v_4262_N();
        long i = dimensiongeneratorsettings.n_1700_B();
        long j = BiomeManager.n_1700_B(i);
        ImmutableList list = ImmutableList.of((Object)new u_1863_S(), (Object)new L_4413_M(), (Object)new a_1738_M(), (Object)new z_3520_U(), (Object)new K_398_W(iserverworldinfo));
        v_1758_J<LevelStem> simpleregistry = dimensiongeneratorsettings.G_564_y();
        LevelStem dimension = simpleregistry.n_1700_B(LevelStem.J_1907_R);
        if (dimension == null) {
            dimensiontype = this.P_1922_E.n_1700_B().R_4764_Y(Z_3903_F.u_1723_Y);
            chunkgenerator = j_419_j.n_1700_B(this.P_1922_E.J_1907_R(V_3137_a.PlayerInfo), this.P_1922_E.J_1907_R(V_3137_a.e_1992_r), new Random().nextLong());
        } else {
            dimensiontype = dimension.J_1907_R();
            chunkgenerator = dimension.R_4764_Y();
        }
        e_3591_l serverworld = new e_3591_l(this, this.A_1038_p, this.R_4764_Y, iserverworldinfo, b_4507_u.u_1723_Y, dimensiontype, p_240787_1_, chunkgenerator, flag, j, (List<CustomSpawner>)list, true);
        this.Y_259_p.put(b_4507_u.u_1723_Y, serverworld);
        s_4380_l dimensionsaveddatamanager = serverworld.r_715_M();
        this.n_1700_B(dimensionsaveddatamanager);
        this.D_4792_h = new J_4848_t(dimensionsaveddatamanager);
        T_603_v worldborder = serverworld.H_2857_Y();
        worldborder.n_1700_B(iserverworldinfo.Y_601_j());
        if (!iserverworldinfo.w_1457_N()) {
            try {
                net.minecraft.server.G_564_y.n_1700_B(serverworld, iserverworldinfo, dimensiongeneratorsettings.R_4764_Y(), flag, true);
                iserverworldinfo.R_4764_Y(true);
                if (flag) {
                    this.n_1700_B(this.w_1484_f);
                }
            }
            catch (Throwable throwable1) {
                n_3236_c crashreport = n_3236_c.n_1700_B(throwable1, "Exception initializing level");
                try {
                    serverworld.n_1700_B(crashreport);
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                throw new ReportedException(crashreport);
            }
            iserverworldinfo.R_4764_Y(true);
        }
        this.p_178_J().n_1700_B(serverworld);
        if (this.w_1484_f.c_3005_b() != null) {
            this.u_744_e().n_1700_B(this.w_1484_f.c_3005_b());
        }
        for (Map.Entry<f_2392_k<LevelStem>, LevelStem> entry : simpleregistry.P_1922_E()) {
            f_2392_k<LevelStem> registrykey = entry.getKey();
            if (registrykey != LevelStem.J_1907_R) {
                f_2392_k<b_4507_u> registrykey1 = f_2392_k.n_1700_B(V_3137_a.z_1737_N, registrykey.n_1700_B());
                Z_3903_F dimensiontype1 = entry.getValue().J_1907_R();
                z_1753_f chunkgenerator1 = entry.getValue().R_4764_Y();
                DerivedLevelData derivedworldinfo = new DerivedLevelData(this.w_1484_f, iserverworldinfo);
                e_3591_l serverworld1 = new e_3591_l(this, this.A_1038_p, this.R_4764_Y, derivedworldinfo, registrykey1, dimensiontype1, p_240787_1_, chunkgenerator1, flag, j, (List<CustomSpawner>)ImmutableList.of(), false);
                worldborder.n_1700_B(new BorderChangeListener.n_1700_B(serverworld1.H_2857_Y()));
                this.Y_259_p.put(registrykey1, serverworld1);
            }
            lightning.product.A_4115_X.n_1700_B(new WorldEvent.Load(this.Y_259_p.get(registrykey)));
        }
    }

    private static void n_1700_B(e_3591_l p_240786_0_, ServerLevelData p_240786_1_, boolean hasBonusChest, boolean p_240786_3_, boolean p_240786_4_) {
        z_1753_f chunkgenerator = p_240786_0_.Y_259_p().t_148_a();
        if (!p_240786_4_) {
            p_240786_1_.n_1700_B(c_1514_x.ZERO.up(chunkgenerator.R_4764_Y()), 0.0f);
        } else if (p_240786_3_) {
            p_240786_1_.n_1700_B(c_1514_x.ZERO.up(), 0.0f);
        } else {
            Y_1387_d chunkpos;
            BiomeSource biomeprovider = chunkgenerator.G_564_y();
            Random random = new Random(p_240786_0_.n_1700_B());
            c_1514_x blockpos = biomeprovider.n_1700_B(0, p_240786_0_.d_2461_k(), 0, 256, p_244265_0_ -> p_244265_0_.J_1907_R().J_1907_R(), random);
            Y_1387_d y_1387_d = chunkpos = blockpos == null ? new Y_1387_d(0, 0) : new Y_1387_d(blockpos);
            if (blockpos == null) {
                t_148_a.warn("Unable to find spawn biome");
            }
            boolean flag = false;
            for (T_2915_h block : BlockTags.Z_976_R.n_1700_B()) {
                if (!biomeprovider.R_4764_Y().contains(block.multiplayerClientSuggestionProvider())) continue;
                flag = true;
                break;
            }
            p_240786_1_.n_1700_B(chunkpos.s_956_w().add(8, chunkgenerator.R_4764_Y(), 8), 0.0f);
            int i1 = 0;
            int j1 = 0;
            int i = 0;
            int j = -1;
            int k = 32;
            for (int l = 0; l < 1024; ++l) {
                c_1514_x blockpos1;
                if (i1 > -16 && i1 <= 16 && j1 > -16 && j1 <= 16 && (blockpos1 = PlayerRespawnLogic.n_1700_B(p_240786_0_, new Y_1387_d(chunkpos.J_1907_R + i1, chunkpos.R_4764_Y + j1), flag)) != null) {
                    p_240786_1_.n_1700_B(blockpos1, 0.0f);
                    break;
                }
                if (i1 == j1 || i1 < 0 && i1 == -j1 || i1 > 0 && i1 == 1 - j1) {
                    int k1 = i;
                    i = -j;
                    j = k1;
                }
                i1 += i;
                j1 += j;
            }
            if (hasBonusChest) {
                ConfiguredFeature<?, ?> configuredfeature = Features.X_933_l;
                configuredfeature.n_1700_B(p_240786_0_, chunkgenerator, p_240786_0_.w_1457_N, new c_1514_x(p_240786_1_.J_1907_R(), p_240786_1_.R_4764_Y(), p_240786_1_.G_564_y()));
            }
        }
    }

    @Override
    private void n_1700_B(WorldData p_240778_1_) {
        p_240778_1_.n_1700_B(R_2450_T.n_1700_B);
        p_240778_1_.G_564_y(true);
        ServerLevelData iserverworldinfo = p_240778_1_.H_2857_Y();
        iserverworldinfo.n_1700_B(false);
        iserverworldinfo.J_1907_R(false);
        iserverworldinfo.G_564_y(1000000000);
        iserverworldinfo.J_1907_R(6000L);
        iserverworldinfo.n_1700_B(I_14_v.P_1922_E);
    }

    private void J_1907_R(ChunkProgressListener p_213186_1_) {
        e_3591_l serverworld = this.x_607_J();
        t_148_a.info("Preparing start region for dimension {}", (Object)serverworld.g_2268_R().n_1700_B());
        c_1514_x blockpos = serverworld.A_1038_p();
        p_213186_1_.n_1700_B(new Y_1387_d(blockpos));
        C_3615_s serverchunkprovider = serverworld.Y_259_p();
        serverchunkprovider.R_4764_Y().n_1700_B(500);
        this.X_933_l = j_3341_s.J_1907_R();
        serverchunkprovider.n_1700_B(TicketType.n_1700_B, new Y_1387_d(blockpos), 11, X_1446_C.n_1700_B);
        while (serverchunkprovider.P_1922_E() != 441) {
            this.X_933_l = j_3341_s.J_1907_R() + 10L;
            this.k_2293_S();
        }
        this.X_933_l = j_3341_s.J_1907_R() + 10L;
        this.k_2293_S();
        for (e_3591_l serverworld1 : this.Y_259_p.values()) {
            ForcedChunksSavedData forcedchunkssavedata = serverworld1.r_715_M().J_1907_R(ForcedChunksSavedData::new, "chunks");
            if (forcedchunkssavedata == null) continue;
            LongIterator longiterator = forcedchunkssavedata.n_1700_B().iterator();
            while (longiterator.hasNext()) {
                long i = longiterator.nextLong();
                Y_1387_d chunkpos = new Y_1387_d(i);
                serverworld1.Y_259_p().n_1700_B(chunkpos, true);
            }
        }
        this.X_933_l = j_3341_s.J_1907_R() + 10L;
        this.k_2293_S();
        p_213186_1_.n_1700_B();
        serverchunkprovider.R_4764_Y().n_1700_B(5);
        this.G_564_y();
    }

    protected void h_1847_R() {
        File file1 = this.R_4764_Y.n_1700_B(H_4757_Q.w_1484_f).toFile();
        if (file1.isFile()) {
            String s = this.R_4764_Y.n_1700_B();
            try {
                this.n_1700_B("level://" + URLEncoder.encode(s, StandardCharsets.UTF_8.toString()) + "/resources.zip", "");
            }
            catch (UnsupportedEncodingException unsupportedencodingexception) {
                t_148_a.warn("Something went wrong url encoding {}", (Object)s);
            }
        }
    }

    public I_14_v Q_4569_t() {
        return this.w_1484_f.t_1786_h();
    }

    public boolean M_182_A() {
        return this.w_1484_f.n_1700_B();
    }

    public abstract int t_1786_h();

    public abstract int multiplayerClientSuggestionProvider();

    public abstract boolean w_1457_N();

    public boolean n_1700_B(boolean suppressLog, boolean flush, boolean forced) {
        boolean flag = false;
        for (e_3591_l serverworld : this.n_3318_d()) {
            if (!suppressLog) {
                t_148_a.info("Saving chunks for level '{}'/{}", (Object)serverworld, (Object)serverworld.g_2268_R().n_1700_B());
            }
            serverworld.n_1700_B((ProgressListener)null, flush, serverworld.R_4764_Y && !forced);
            flag = true;
        }
        e_3591_l serverworld1 = this.x_607_J();
        ServerLevelData iserverworldinfo = this.w_1484_f.H_2857_Y();
        iserverworldinfo.n_1700_B(serverworld1.H_2857_Y().Y_601_j());
        this.w_1484_f.n_1700_B(this.u_744_e().R_4764_Y());
        this.R_4764_Y.n_1700_B(this.P_1922_E, this.w_1484_f, this.p_178_J().G_564_y());
        return flag;
    }

    @Override
    public void close() {
        this.Y_601_j();
    }

    protected void Y_601_j() {
        t_148_a.info("Stopping server");
        if (this.f_4016_n() != null) {
            this.f_4016_n().J_1907_R();
        }
        if (this.Q_2552_b != null) {
            t_148_a.info("Saving players");
            this.Q_2552_b.t_148_a();
            this.Q_2552_b.multiplayerClientSuggestionProvider();
        }
        t_148_a.info("Saving worlds");
        for (e_3591_l serverworld : this.n_3318_d()) {
            if (serverworld == null) continue;
            serverworld.R_4764_Y = false;
        }
        this.n_1700_B(false, true, false);
        for (e_3591_l serverworld1 : this.n_3318_d()) {
            if (serverworld1 == null) continue;
            try {
                lightning.product.A_4115_X.n_1700_B(new WorldEvent.Unload(serverworld1));
                serverworld1.close();
            }
            catch (IOException ioexception1) {
                t_148_a.error("Exception closing the level", (Throwable)ioexception1);
            }
        }
        if (this.s_956_w.R_4764_Y()) {
            this.s_956_w.G_564_y();
        }
        this.Ping.close();
        try {
            this.R_4764_Y.close();
        }
        catch (IOException ioexception) {
            t_148_a.error("Failed to unlock level {}", (Object)this.R_4764_Y.n_1700_B(), (Object)ioexception);
        }
    }

    public String Y_259_p() {
        return this.w_1457_N;
    }

    public void J_1907_R(String host) {
        this.w_1457_N = host;
    }

    public boolean Q_2552_b() {
        return this.C_2741_M;
    }

    @Override
    public void n_1700_B(boolean waitForServer) {
        this.C_2741_M = false;
        if (waitForServer) {
            try {
                this.g_164_R.join();
            }
            catch (InterruptedException interruptedexception) {
                t_148_a.error("Error while shutting down", (Throwable)interruptedexception);
            }
        }
    }

    public static void n_1700_B(G_564_y server) {
        lightning.product.A_4115_X.n_1700_B(new FMLServerStartedEvent(server));
        RealmsClientConfig.set(true);
    }

    public static void J_1907_R(G_564_y server) {
        RealmsClientConfig.set(false);
        lightning.product.A_4115_X.n_1700_B(new FMLServerStoppingEvent(server));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void C_2741_M() {
        try {
            if (this.u_2550_I()) {
                net.minecraft.server.G_564_y.n_1700_B(this);
                this.X_933_l = j_3341_s.J_1907_R();
                this.M_182_A.n_1700_B(new U_2871_b(this.Y_1740_V));
                this.M_182_A.n_1700_B(new ServerStatus.R_4764_Y(SharedConstants.n_1700_B().getName(), SharedConstants.n_1700_B().getProtocolVersion()));
                this.n_1700_B(this.M_182_A);
                while (this.C_2741_M) {
                    long i = j_3341_s.J_1907_R() - this.X_933_l;
                    if (i > 2000L && this.X_933_l - this.G_624_v >= 15000L) {
                        long j = i / 50L;
                        t_148_a.warn("Can't keep up! Is the server overloaded? Running {}ms or {} ticks behind", (Object)i, (Object)j);
                        this.X_933_l += j * 50L;
                        this.G_624_v = this.X_933_l;
                    }
                    this.X_933_l += 50L;
                    W_2800_c longtickdetector = W_2800_c.n_1700_B("Server");
                    this.n_1700_B(longtickdetector);
                    this.P_4830_p.n_1700_B();
                    this.P_4830_p.n_1700_B("tick");
                    this.n_1700_B(this::J_1907_R);
                    this.P_4830_p.J_1907_R("nextTickWait");
                    this.H_1990_U = true;
                    this.Z_976_R = Math.max(j_3341_s.J_1907_R() + 50L, this.X_933_l);
                    this.k_2293_S();
                    this.P_4830_p.R_4764_Y();
                    this.P_4830_p.J_1907_R();
                    this.J_1907_R(longtickdetector);
                    this.d_2461_k = true;
                }
                net.minecraft.server.G_564_y.J_1907_R(this);
            } else {
                this.n_1700_B((n_3236_c)null);
            }
        }
        catch (Throwable throwable1) {
            t_148_a.error("Encountered an unexpected exception", throwable1);
            n_3236_c crashreport = throwable1 instanceof ReportedException ? this.J_1907_R(((ReportedException)throwable1).n_1700_B()) : this.J_1907_R(new n_3236_c("Exception in server tick loop", throwable1));
            File file1 = new File(new File(this.H_2857_Y(), "crash-reports"), "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-server.txt");
            if (crashreport.n_1700_B(file1)) {
                t_148_a.error("This crash report has been saved to: {}", (Object)file1.getAbsolutePath());
            } else {
                t_148_a.error("We were unable to save this crash report to disk.");
            }
            this.n_1700_B(crashreport);
        }
        finally {
            try {
                this.k_2293_S = true;
                this.Y_601_j();
            }
            catch (Throwable throwable) {
                t_148_a.error("Exception stopping the server", throwable);
            }
            finally {
                this.A_4115_X();
            }
        }
    }

    private boolean J_1907_R() {
        return this.J_4256_G() || j_3341_s.J_1907_R() < (this.H_1990_U ? this.Z_976_R : this.X_933_l);
    }

    protected void k_2293_S() {
        this.RealmsParentalConsentScreen();
        this.R_4764_Y(() -> !this.J_1907_R());
    }

    protected i_452_g R_4764_Y(Runnable runnable) {
        return new i_452_g(this.q_2307_F, runnable);
    }

    protected boolean n_1700_B(i_452_g runnable) {
        return runnable.n_1700_B() + 3 < this.q_2307_F || this.J_1907_R();
    }

    @Override
    public boolean l_() {
        boolean flag;
        this.H_1990_U = flag = this.R_4764_Y();
        return flag;
    }

    private boolean R_4764_Y() {
        if (super.l_()) {
            return true;
        }
        if (this.J_1907_R()) {
            for (e_3591_l serverworld : this.n_3318_d()) {
                if (!serverworld.Y_259_p().v_4262_N()) continue;
                return true;
            }
        }
        return false;
    }

    protected void J_1907_R(i_452_g taskIn) {
        this.LongRunningTask().R_4764_Y("runTask");
        super.P_1922_E(taskIn);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    private void n_1700_B(ServerStatus response) {
        File file1 = this.G_564_y("server-icon.png");
        if (!file1.exists()) {
            file1 = this.R_4764_Y.u_1723_Y();
        }
        if (file1.isFile()) {
            ByteBuf bytebuf = Unpooled.buffer();
            try {
                BufferedImage bufferedimage = ImageIO.read(file1);
                Validate.validState((bufferedimage.getWidth() == 64 ? 1 : 0) != 0, (String)"Must be 64 pixels wide", (Object[])new Object[0]);
                Validate.validState((bufferedimage.getHeight() == 64 ? 1 : 0) != 0, (String)"Must be 64 pixels high", (Object[])new Object[0]);
                ImageIO.write((RenderedImage)bufferedimage, "PNG", (OutputStream)new ByteBufOutputStream(bytebuf));
                ByteBuffer bytebuffer = Base64.getEncoder().encode(bytebuf.nioBuffer());
                response.n_1700_B("data:image/png;base64," + String.valueOf(StandardCharsets.UTF_8.decode(bytebuffer)));
            }
            catch (Exception exception) {
                t_148_a.error("Couldn't load server icon", (Throwable)exception);
            }
            finally {
                bytebuf.release();
            }
        }
    }

    public boolean Z_875_P() {
        this.N_2525_X = this.N_2525_X || this.c_3005_b().isFile();
        return this.N_2525_X;
    }

    public File c_3005_b() {
        return this.R_4764_Y.u_1723_Y();
    }

    public File H_2857_Y() {
        return new File(".");
    }

    @Override
    protected void n_1700_B(n_3236_c report) {
    }

    protected void A_4115_X() {
    }

    @Override
    protected void n_1700_B(BooleanSupplier hasTimeLeft) {
        long i = j_3341_s.R_4764_Y();
        lightning.product.A_4115_X.n_1700_B(new TickEvent.ServerTickEvent(TickEvent.Phase.START));
        ++this.q_2307_F;
        this.J_1907_R(hasTimeLeft);
        if (i - this.B_1668_F >= 5000000000L) {
            this.B_1668_F = i;
            this.M_182_A.n_1700_B(new ServerStatus.n_1700_B(this.v_4262_N(), this.u_1723_Y()));
            GameProfile[] agameprofile = new GameProfile[Math.min(this.u_1723_Y(), 12)];
            int j = u_530_F.n_1700_B(this.t_1786_h, 0, this.u_1723_Y() - agameprofile.length);
            for (int k = 0; k < agameprofile.length; ++k) {
                agameprofile[k] = this.Q_2552_b.w_1457_N().get(j + k).y_4642_Y();
            }
            Collections.shuffle(Arrays.asList(agameprofile));
            this.M_182_A.J_1907_R().n_1700_B(agameprofile);
        }
        if (this.q_2307_F % 6000 == 0) {
            t_148_a.debug("Autosave started");
            this.P_4830_p.n_1700_B("save");
            this.Q_2552_b.t_148_a();
            this.n_1700_B(true, false, false);
            this.P_4830_p.R_4764_Y();
            t_148_a.debug("Autosave finished");
        }
        this.P_4830_p.n_1700_B("snooper");
        if (!this.s_956_w.R_4764_Y() && this.q_2307_F > 100) {
            this.s_956_w.n_1700_B();
        }
        if (this.q_2307_F % 6000 == 0) {
            this.s_956_w.J_1907_R();
        }
        this.P_4830_p.R_4764_Y();
        this.P_4830_p.n_1700_B("tallying");
        long l = j_3341_s.R_4764_Y() - i;
        this.v_4262_N[this.q_2307_F % 100] = l;
        long l2 = l;
        this.r_715_M = this.r_715_M * 0.8f + (float)l2 / 1000000.0f * 0.19999999f;
        long i1 = j_3341_s.R_4764_Y();
        this.z_1333_t.n_1700_B(i1 - i);
        this.P_4830_p.R_4764_Y();
        lightning.product.A_4115_X.n_1700_B(new TickEvent.ServerTickEvent(TickEvent.Phase.END));
    }

    protected void J_1907_R(BooleanSupplier hasTimeLeft) {
        this.P_4830_p.n_1700_B("commandFunctions");
        this.RealmsWorldResetDto().R_4764_Y();
        this.P_4830_p.J_1907_R("levels");
        for (e_3591_l serverworld : this.n_3318_d()) {
            this.P_4830_p.n_1700_B(() -> String.valueOf(serverworld) + " " + String.valueOf(serverworld.g_2268_R().n_1700_B()));
            if (this.q_2307_F % 20 == 0) {
                this.P_4830_p.n_1700_B("timeSync");
                this.Q_2552_b.n_1700_B(new ClientboundSetTimePacket(serverworld.X_933_l(), serverworld.Z_976_R(), serverworld.H_1990_U().J_1907_R(A_2352_Z.s_956_w)), serverworld.g_2268_R());
                this.P_4830_p.R_4764_Y();
            }
            this.P_4830_p.n_1700_B("tick");
            try {
                serverworld.n_1700_B(hasTimeLeft);
            }
            catch (Throwable throwable) {
                n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Exception ticking world");
                serverworld.n_1700_B(crashreport);
                throw new ReportedException(crashreport);
            }
            this.P_4830_p.R_4764_Y();
            this.P_4830_p.R_4764_Y();
        }
        this.P_4830_p.J_1907_R("connection");
        this.f_4016_n().R_4764_Y();
        this.P_4830_p.J_1907_R("players");
        this.Q_2552_b.P_1922_E();
        if (SharedConstants.G_564_y) {
            GameTestTicker.n_1700_B.J_1907_R();
        }
        this.P_4830_p.J_1907_R("server gui refresh");
        for (int i = 0; i < this.u_2550_I.size(); ++i) {
            this.u_2550_I.get(i).run();
        }
        this.P_4830_p.R_4764_Y();
    }

    public boolean Y_1740_V() {
        return true;
    }

    public void G_564_y(Runnable tickable) {
        this.u_2550_I.add(tickable);
    }

    protected void R_4764_Y(String serverIdIn) {
        this.i_1637_u = serverIdIn;
    }

    public boolean t_4043_B() {
        return !this.g_164_R.isAlive();
    }

    public File G_564_y(String fileName) {
        return new File(this.H_2857_Y(), fileName);
    }

    public final e_3591_l x_607_J() {
        return this.Y_259_p.get(b_4507_u.u_1723_Y);
    }

    @Nullable
    public e_3591_l n_1700_B(f_2392_k<b_4507_u> dimension) {
        return this.Y_259_p.get(dimension);
    }

    public Set<f_2392_k<b_4507_u>> e_4240_b() {
        return this.Y_259_p.keySet();
    }

    public Iterable<e_3591_l> n_3318_d() {
        return this.Y_259_p.values();
    }

    public String P_1922_E() {
        return SharedConstants.n_1700_B().getName();
    }

    public int u_1723_Y() {
        return this.Q_2552_b.h_1847_R();
    }

    public int v_4262_N() {
        return this.Q_2552_b.Q_4569_t();
    }

    public String[] w_1484_f() {
        return this.Q_2552_b.u_1723_Y();
    }

    public String d_2427_y() {
        return "vanilla";
    }

    public n_3236_c J_1907_R(n_3236_c report) {
        if (this.Q_2552_b != null) {
            report.u_1723_Y().n_1700_B("Player Count", () -> this.Q_2552_b.h_1847_R() + " / " + this.Q_2552_b.Q_4569_t() + "; " + String.valueOf(this.Q_2552_b.w_1457_N()));
        }
        report.u_1723_Y().n_1700_B("Data Packs", () -> {
            StringBuilder stringbuilder = new StringBuilder();
            for (D_2103_L resourcepackinfo : this.c_4037_x.P_1922_E()) {
                if (stringbuilder.length() > 0) {
                    stringbuilder.append(", ");
                }
                stringbuilder.append(resourcepackinfo.P_1922_E());
                if (resourcepackinfo.R_4764_Y().n_1700_B()) continue;
                stringbuilder.append(" (incompatible)");
            }
            return stringbuilder.toString();
        });
        if (this.i_1637_u != null) {
            report.u_1723_Y().n_1700_B("Server Id", () -> this.i_1637_u);
        }
        return report;
    }

    public abstract Optional<String> z_1737_N();

    @Override
    public void n_1700_B(x_282_a component, UUID senderUUID) {
        t_148_a.info(component.getString());
    }

    public KeyPair v_4276_D() {
        return this.e_4240_b;
    }

    public int d_2461_k() {
        return this.Y_601_j;
    }

    @Override
    public void n_1700_B(int port) {
        this.Y_601_j = port;
    }

    public String G_624_v() {
        return this.n_3318_d;
    }

    @Override
    public void P_1922_E(String owner) {
        this.n_3318_d = owner;
    }

    public boolean T_2506_i() {
        return this.n_3318_d != null;
    }

    protected void q_4610_l() {
        t_148_a.info("Generating keypair");
        try {
            this.e_4240_b = Crypt.J_1907_R();
        }
        catch (Y_2605_X cryptexception) {
            throw new IllegalStateException("Failed to generate key pair", cryptexception);
        }
    }

    public void n_1700_B(R_2450_T difficulty, boolean p_147139_2_) {
        if (p_147139_2_ || !this.w_1484_f.M_588_G()) {
            this.w_1484_f.n_1700_B(this.w_1484_f.n_1700_B() ? R_2450_T.G_564_y : difficulty);
            this.G_564_y();
            this.p_178_J().w_1457_N().forEach(this::J_1907_R);
        }
    }

    public int J_1907_R(int p_230512_1_) {
        return p_230512_1_;
    }

    private void G_564_y() {
        for (e_3591_l serverworld : this.n_3318_d()) {
            serverworld.n_1700_B(this.z_4693_k(), this.N_2525_X());
        }
    }

    public void J_1907_R(boolean locked) {
        this.w_1484_f.G_564_y(locked);
        this.p_178_J().w_1457_N().forEach(this::J_1907_R);
    }

    private void J_1907_R(B_4088_l playerIn) {
        LevelData iworldinfo = playerIn.c_3005_b().k_2293_S();
        playerIn.n_1700_B.n_1700_B(new ClientboundChangeDifficultyPacket(iworldinfo.u_2550_I(), iworldinfo.M_588_G()));
    }

    protected boolean z_4693_k() {
        return this.w_1484_f.u_2550_I() != R_2450_T.n_1700_B;
    }

    public boolean g_221_o() {
        return this.d_2427_y;
    }

    public void R_4764_Y(boolean demo) {
        this.d_2427_y = demo;
    }

    public String e_2887_G() {
        return this.z_1737_N;
    }

    public String B_1668_F() {
        return this.v_4276_D;
    }

    public void n_1700_B(String url, String hash) {
        this.z_1737_N = url;
        this.v_4276_D = hash;
    }

    @Override
    public void n_1700_B(E_2727_F snooper) {
        snooper.n_1700_B("whitelist_enabled", false);
        snooper.n_1700_B("whitelist_count", 0);
        if (this.Q_2552_b != null) {
            snooper.n_1700_B("players_current", this.u_1723_Y());
            snooper.n_1700_B("players_max", this.v_4262_N());
            snooper.n_1700_B("players_seen", this.G_564_y.n_1700_B().length);
        }
        snooper.n_1700_B("uses_auth", this.Z_875_P);
        snooper.n_1700_B("gui_state", this.UploadStatus() ? "enabled" : "disabled");
        snooper.n_1700_B("run_time", (j_3341_s.J_1907_R() - snooper.u_1723_Y()) / 60L * 1000L);
        snooper.n_1700_B("avg_tick_ms", (int)(u_530_F.n_1700_B(this.v_4262_N) * 1.0E-6));
        int i = 0;
        for (e_3591_l serverworld : this.n_3318_d()) {
            if (serverworld == null) continue;
            snooper.n_1700_B("world[" + i + "][dimension]", serverworld.g_2268_R().n_1700_B());
            snooper.n_1700_B("world[" + i + "][mode]", (Object)this.w_1484_f.t_1786_h());
            snooper.n_1700_B("world[" + i + "][difficulty]", (Object)serverworld.x_607_J());
            snooper.n_1700_B("world[" + i + "][hardcore]", this.w_1484_f.n_1700_B());
            snooper.n_1700_B("world[" + i + "][height]", this.t_4043_B);
            snooper.n_1700_B("world[" + i + "][chunks_loaded]", serverworld.Y_259_p().s_956_w());
            ++i;
        }
        snooper.n_1700_B("worlds", i);
    }

    public abstract boolean g_164_R();

    public abstract int X_933_l();

    public boolean Z_976_R() {
        return this.Z_875_P;
    }

    public void G_564_y(boolean online) {
        this.Z_875_P = online;
    }

    public boolean H_1990_U() {
        return this.c_3005_b;
    }

    @Override
    public void P_1922_E(boolean p_190517_1_) {
        this.c_3005_b = p_190517_1_;
    }

    public boolean N_2525_X() {
        return true;
    }

    public boolean c_4037_x() {
        return true;
    }

    public abstract boolean g_2268_R();

    public boolean T_3594_S() {
        return this.H_2857_Y;
    }

    public void u_1723_Y(boolean allowPvp) {
        this.H_2857_Y = allowPvp;
    }

    public boolean D_4792_h() {
        return this.A_4115_X;
    }

    public void v_4262_N(boolean allow) {
        this.A_4115_X = allow;
    }

    public abstract boolean s_2632_s();

    public String z_1333_t() {
        return this.Y_1740_V;
    }

    public void u_1723_Y(String motdIn) {
        this.Y_1740_V = motdIn;
    }

    public int i_1637_u() {
        return this.t_4043_B;
    }

    public void R_4764_Y(int maxBuildHeight) {
        this.t_4043_B = maxBuildHeight;
    }

    public boolean Ping() {
        return this.k_2293_S;
    }

    public g_1995_W p_178_J() {
        return this.Q_2552_b;
    }

    @Override
    public void n_1700_B(g_1995_W list) {
        this.Q_2552_b = list;
    }

    public abstract boolean RealmsClientConfig();

    @Override
    public void n_1700_B(I_14_v gameMode) {
        this.w_1484_f.n_1700_B(gameMode);
    }

    @Nullable
    public l_4845_j f_4016_n() {
        return this.h_1847_R;
    }

    public boolean j_276_v() {
        return this.d_2461_k;
    }

    public boolean UploadStatus() {
        return false;
    }

    public abstract boolean n_1700_B(I_14_v var1, boolean var2, int var3);

    public int e_1992_r() {
        return this.q_2307_F;
    }

    public E_2727_F D_60_a() {
        return this.s_956_w;
    }

    public int k_3961_g() {
        return 16;
    }

    public boolean n_1700_B(e_3591_l worldIn, c_1514_x pos, a_3913_L playerIn) {
        return false;
    }

    @Override
    public void w_1484_f(boolean force) {
        this.q_4610_l = force;
    }

    public boolean Ops() {
        return this.q_4610_l;
    }

    public boolean h_4320_q() {
        return true;
    }

    public int t_4219_U() {
        return this.x_607_J;
    }

    public void G_564_y(int idleTimeout) {
        this.x_607_J = idleTimeout;
    }

    public MinecraftSessionService V_1446_Y() {
        return this.z_4693_k;
    }

    public GameProfileRepository PlayerInfo() {
        return this.g_221_o;
    }

    public W_1689_V V_1225_t() {
        return this.e_2887_G;
    }

    public ServerStatus U_1241_n() {
        return this.M_182_A;
    }

    public void q_1982_R() {
        this.B_1668_F = 0L;
    }

    public int dtoRealmsServerAddress() {
        return 29999984;
    }

    @Override
    public boolean j_() {
        return super.j_() && !this.Ping();
    }

    @Override
    public Thread l_1233_K() {
        return this.g_164_R;
    }

    public int RealmsServerPing() {
        return 256;
    }

    public long j_1564_a() {
        return this.X_933_l;
    }

    public DataFixer M_1641_O() {
        return this.multiplayerClientSuggestionProvider;
    }

    public int n_1700_B(@Nullable e_3591_l worldIn) {
        return worldIn != null ? worldIn.H_1990_U().R_4764_Y(A_2352_Z.t_1786_h) : 10;
    }

    public ServerAdvancementManager RealmsWorldOptions() {
        return this.Ping.v_4262_N();
    }

    public ServerFunctionManager RealmsWorldResetDto() {
        return this.l_1233_K;
    }

    @Override
    public CompletableFuture<Void> n_1700_B(Collection<String> p_240780_1_) {
        CompletionStage completablefuture = ((CompletableFuture)CompletableFuture.supplyAsync(() -> (ImmutableList)p_240780_1_.stream().map(this.c_4037_x::n_1700_B).filter(Objects::nonNull).map(D_2103_L::G_564_y).collect(ImmutableList.toImmutableList()), this).thenCompose(p_240775_1_ -> ServerResources.n_1700_B((List<PackResources>)p_240775_1_, this.g_164_R() ? Q_2241_p.n_1700_B.J_1907_R : Q_2241_p.n_1700_B.R_4764_Y, this.multiplayerClientSuggestionProvider(), this.A_1038_p, this))).thenAcceptAsync(p_240782_2_ -> {
            this.Ping.close();
            this.Ping = p_240782_2_;
            this.c_4037_x.n_1700_B(p_240780_1_);
            this.w_1484_f.n_1700_B(net.minecraft.server.G_564_y.n_1700_B(this.c_4037_x));
            p_240782_2_.t_148_a();
            this.p_178_J().t_148_a();
            this.p_178_J().Y_601_j();
            this.l_1233_K.n_1700_B(this.Ping.n_1700_B());
            this.p_178_J.n_1700_B(this.Ping.w_1484_f());
        }, (Executor)this);
        if (this.RealmsLongConfirmationScreen()) {
            this.R_4764_Y(((CompletableFuture)completablefuture)::isDone);
        }
        return completablefuture;
    }

    public static DataPackConfig n_1700_B(PackRepository p_240772_0_, DataPackConfig p_240772_1_, boolean p_240772_2_) {
        p_240772_0_.n_1700_B();
        if (p_240772_2_) {
            p_240772_0_.n_1700_B(Collections.singleton("vanilla"));
            return new DataPackConfig((List<String>)ImmutableList.of((Object)"vanilla"), (List<String>)ImmutableList.of());
        }
        LinkedHashSet set = Sets.newLinkedHashSet();
        for (String s : p_240772_1_.n_1700_B()) {
            if (p_240772_0_.J_1907_R(s)) {
                set.add(s);
                continue;
            }
            t_148_a.warn("Missing data pack {}", (Object)s);
        }
        for (D_2103_L resourcepackinfo : p_240772_0_.R_4764_Y()) {
            String s1 = resourcepackinfo.P_1922_E();
            if (p_240772_1_.J_1907_R().contains(s1) || set.contains(s1)) continue;
            t_148_a.info("Found new data pack {}, loading it automatically", (Object)s1);
            set.add(s1);
        }
        if (set.isEmpty()) {
            t_148_a.info("No datapacks selected, forcing vanilla");
            set.add("vanilla");
        }
        p_240772_0_.n_1700_B(set);
        return net.minecraft.server.G_564_y.n_1700_B(p_240772_0_);
    }

    private static DataPackConfig n_1700_B(PackRepository p_240771_0_) {
        Collection<String> collection = p_240771_0_.G_564_y();
        ImmutableList list = ImmutableList.copyOf(collection);
        List list1 = (List)p_240771_0_.J_1907_R().stream().filter(p_240781_1_ -> !collection.contains(p_240781_1_)).collect(ImmutableList.toImmutableList());
        return new DataPackConfig((List<String>)list, list1);
    }

    @Override
    public void n_1700_B(y_2498_m commandSourceIn) {
        if (this.RetryCallException()) {
            g_1995_W playerlist = commandSourceIn.w_1457_N().p_178_J();
            G_4584_Z whitelist = playerlist.s_956_w();
            for (B_4088_l serverplayerentity : Lists.newArrayList(playerlist.w_1457_N())) {
                if (whitelist.n_1700_B(serverplayerentity.y_4642_Y())) continue;
                serverplayerentity.n_1700_B.n_1700_B(new F_2904_S("multiplayer.disconnect.not_whitelisted"));
            }
        }
    }

    public PackRepository RegionPingResult() {
        return this.c_4037_x;
    }

    public Q_2241_p H_1083_k() {
        return this.Ping.u_1723_Y();
    }

    public y_2498_m R_3908_n() {
        e_3591_l serverworld = this.x_607_J();
        return new y_2498_m(this, serverworld == null ? e_2866_D.n_1700_B : e_2866_D.J_1907_R(serverworld.A_1038_p()), P_3504_Q.n_1700_B, serverworld, 4, "Server", new U_2871_b("Server"), this, null);
    }

    @Override
    public boolean O_508_d() {
        return true;
    }

    @Override
    public boolean r_715_M() {
        return true;
    }

    public G_3474_H ValueObject() {
        return this.Ping.P_1922_E();
    }

    public TagContainer F_1410_V() {
        return this.Ping.G_564_y();
    }

    public ServerScoreboard S_4022_R() {
        return this.g_2268_R;
    }

    public J_4848_t l_4537_E() {
        if (this.D_4792_h == null) {
            throw new NullPointerException("Called before server init");
        }
        return this.D_4792_h;
    }

    public f_4186_T F_2624_D() {
        return this.Ping.R_4764_Y();
    }

    public k_1471_n RealmsDefaultUncaughtExceptionHandler() {
        return this.Ping.J_1907_R();
    }

    public A_2352_Z y_1700_S() {
        return this.x_607_J().H_1990_U();
    }

    public J_1907_R u_744_e() {
        return this.s_2632_s;
    }

    public boolean RetryCallException() {
        return this.O_508_d;
    }

    public void t_148_a(boolean whitelistEnabledIn) {
        this.O_508_d = whitelistEnabledIn;
    }

    public float r_3651_U() {
        return this.r_715_M;
    }

    public int n_1700_B(GameProfile profile) {
        if (this.p_178_J().u_1723_Y(profile)) {
            I_1965_o opentry = (I_1965_o)this.p_178_J().M_588_G().J_1907_R(profile);
            if (opentry != null) {
                return opentry.n_1700_B();
            }
            if (this.J_1907_R(profile)) {
                return 4;
            }
            if (this.T_2506_i()) {
                return this.p_178_J().Y_259_p() ? 4 : 0;
            }
            return this.t_1786_h();
        }
        return 0;
    }

    public FrameTimer RowButton() {
        return this.z_1333_t;
    }

    public ProfilerFiller LongRunningTask() {
        return this.P_4830_p;
    }

    @Override
    public abstract boolean J_1907_R(GameProfile var1);

    @Override
    public void n_1700_B(Path p_223711_1_) throws IOException {
        Path path = p_223711_1_.resolve("levels");
        for (Map.Entry<f_2392_k<b_4507_u>, e_3591_l> entry : this.Y_259_p.entrySet()) {
            g_2336_b resourcelocation = entry.getKey().n_1700_B();
            Path path1 = path.resolve(resourcelocation.R_4764_Y()).resolve(resourcelocation.J_1907_R());
            Files.createDirectories(path1, new FileAttribute[0]);
            entry.getValue().n_1700_B(path1);
        }
        this.G_564_y(p_223711_1_.resolve("gamerules.txt"));
        this.P_1922_E(p_223711_1_.resolve("classpath.txt"));
        this.R_4764_Y(p_223711_1_.resolve("example_crash.txt"));
        this.J_1907_R(p_223711_1_.resolve("stats.txt"));
        this.u_1723_Y(p_223711_1_.resolve("threads.txt"));
    }

    private void J_1907_R(Path p_223710_1_) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(p_223710_1_, new OpenOption[0]);){
            writer.write(String.format("pending_tasks: %d\n", this.RealmsLongRunningMcoTaskScreen()));
            writer.write(String.format("average_tick_time: %f\n", Float.valueOf(this.r_3651_U())));
            writer.write(String.format("tick_times: %s\n", Arrays.toString(this.v_4262_N)));
            writer.write(String.format("queue: %s\n", j_3341_s.u_1723_Y()));
        }
    }

    private void R_4764_Y(Path p_223709_1_) throws IOException {
        n_3236_c crashreport = new n_3236_c("Server dump", new Exception("dummy"));
        this.J_1907_R(crashreport);
        try (BufferedWriter writer = Files.newBufferedWriter(p_223709_1_, new OpenOption[0]);){
            writer.write(crashreport.G_564_y());
        }
    }

    private void G_564_y(Path p_223708_1_) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(p_223708_1_, new OpenOption[0]);){
            final ArrayList list = Lists.newArrayList();
            final A_2352_Z gamerules = this.y_1700_S();
            A_2352_Z.n_1700_B(new A_2352_Z.G_564_y(){

                @Override
                public <T extends A_2352_Z.w_1484_f<T>> void R_4764_Y(A_2352_Z.u_1723_Y<T> key, A_2352_Z.v_4262_N<T> type) {
                    list.add(String.format("%s=%s\n", key.n_1700_B(), ((A_2352_Z.w_1484_f)gamerules.n_1700_B(key)).toString()));
                }
            });
            for (String s : list) {
                writer.write(s);
            }
        }
    }

    @Override
    private void P_1922_E(Path p_223706_1_) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(p_223706_1_, new OpenOption[0]);){
            String s = System.getProperty("java.class.path");
            String s1 = System.getProperty("path.separator");
            for (String s2 : Splitter.on((String)s1).split((CharSequence)s)) {
                writer.write(s2);
                writer.write("\n");
            }
        }
    }

    private void u_1723_Y(Path p_223712_1_) throws IOException {
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] athreadinfo = threadmxbean.dumpAllThreads(true, true);
        Arrays.sort(athreadinfo, Comparator.comparing(ThreadInfo::getThreadName));
        try (BufferedWriter writer = Files.newBufferedWriter(p_223712_1_, new OpenOption[0]);){
            for (ThreadInfo threadinfo : athreadinfo) {
                writer.write(threadinfo.toString());
                ((Writer)writer).write(10);
            }
        }
    }

    @Override
    private void n_1700_B(@Nullable W_2800_c p_240773_1_) {
        if (this.T_2506_i) {
            this.T_2506_i = false;
            this.M_588_G.R_4764_Y();
        }
        this.P_4830_p = W_2800_c.n_1700_B(this.M_588_G.G_564_y(), p_240773_1_);
    }

    private void J_1907_R(@Nullable W_2800_c p_240795_1_) {
        if (p_240795_1_ != null) {
            p_240795_1_.J_1907_R();
        }
        this.P_4830_p = this.M_588_G.G_564_y();
    }

    public boolean j_2266_I() {
        return this.M_588_G.n_1700_B();
    }

    public void S_980_j() {
        this.T_2506_i = true;
    }

    public ProfileResults R_3077_Z() {
        ProfileResults iprofileresult = this.M_588_G.P_1922_E();
        this.M_588_G.J_1907_R();
        return iprofileresult;
    }

    public Path n_1700_B(H_4757_Q p_240776_1_) {
        return this.R_4764_Y.n_1700_B(p_240776_1_);
    }

    public boolean RealmsScreenWithCallback() {
        return true;
    }

    public b_2085_h M_2677_i() {
        return this.p_178_J;
    }

    public WorldData c_132_F() {
        return this.w_1484_f;
    }

    public r_4097_j g_4106_L() {
        return this.P_1922_E;
    }

    @Nullable
    public TextFilter n_1700_B(B_4088_l p_244435_1_) {
        return null;
    }

    @Override
    protected /* synthetic */ void P_1922_E(Runnable runnable) {
        this.J_1907_R((i_452_g)runnable);
    }

    @Override
    protected /* synthetic */ boolean J_1907_R(Runnable runnable) {
        return this.n_1700_B((i_452_g)runnable);
    }

    @Override
    protected /* synthetic */ Runnable n_1700_B(Runnable runnable) {
        return this.R_4764_Y(runnable);
    }
}


