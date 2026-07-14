/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.common.util.concurrent.RateLimiter
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.RealmsPersistence;
import lightning.product.RealmsLongRunningMcoTaskScreen;
import lightning.product.K_1289_S;
import lightning.product.ObjectSelectionList;
import lightning.product.RealmsParentalConsentScreen;
import lightning.product.RegionPingResult;
import lightning.product.M_1641_O;
import lightning.product.N_2445_q;
import lightning.product.O_2151_c;
import lightning.product.Q_201_j;
import lightning.product.ResourceManager;
import lightning.product.Ping;
import lightning.product.U_2871_b;
import lightning.product.V_1446_Y;
import lightning.product.Button;
import lightning.product.W_3464_O;
import lightning.product.X_933_l;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.e_1813_Z;
import lightning.product.RealmsLongConfirmationScreen;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.RealmsScreen;
import lightning.product.j_1564_a;
import lightning.product.j_2266_I;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.k_3238_x;
import lightning.product.o_2488_o;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.RealmsCreateRealmScreen;
import lightning.product.RealmsClientOutdatedScreen;
import lightning.product.NarrationHelper;
import lightning.product.u_530_F;
import lightning.product.u_744_e;
import lightning.product.w_728_N;
import lightning.product.x_282_a;
import lightning.product.y_2772_m;
import lightning.product.z_3470_q;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class r_715_M
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final g_2336_b J_1907_R = new g_2336_b("realms", "textures/gui/realms/on_icon.png");
    private static final g_2336_b R_4764_Y = new g_2336_b("realms", "textures/gui/realms/off_icon.png");
    private static final g_2336_b G_564_y = new g_2336_b("realms", "textures/gui/realms/expired_icon.png");
    private static final g_2336_b P_1922_E = new g_2336_b("realms", "textures/gui/realms/expires_soon_icon.png");
    private static final g_2336_b u_1723_Y = new g_2336_b("realms", "textures/gui/realms/leave_icon.png");
    private static final g_2336_b v_4262_N = new g_2336_b("realms", "textures/gui/realms/invitation_icons.png");
    private static final g_2336_b w_1484_f = new g_2336_b("realms", "textures/gui/realms/invite_icon.png");
    private static final g_2336_b t_148_a = new g_2336_b("realms", "textures/gui/realms/world_icon.png");
    private static final g_2336_b s_956_w = new g_2336_b("realms", "textures/gui/title/realms.png");
    private static final g_2336_b u_2550_I = new g_2336_b("realms", "textures/gui/realms/configure_icon.png");
    private static final g_2336_b M_588_G = new g_2336_b("realms", "textures/gui/realms/questionmark.png");
    private static final g_2336_b P_4830_p = new g_2336_b("realms", "textures/gui/realms/news_icon.png");
    private static final g_2336_b h_1847_R = new g_2336_b("realms", "textures/gui/realms/popup.png");
    private static final g_2336_b Q_4569_t = new g_2336_b("realms", "textures/gui/realms/darken.png");
    private static final g_2336_b M_182_A = new g_2336_b("realms", "textures/gui/realms/cross_icon.png");
    private static final g_2336_b t_1786_h = new g_2336_b("realms", "textures/gui/realms/trial_icon.png");
    private static final g_2336_b multiplayerClientSuggestionProvider = new g_2336_b("minecraft", "textures/gui/widgets.png");
    private static final x_282_a w_1457_N = new F_2904_S("mco.invites.nopending");
    private static final x_282_a Y_601_j = new F_2904_S("mco.invites.pending");
    private static final List<x_282_a> Y_259_p = ImmutableList.of((Object)new F_2904_S("mco.trial.message.line1"), (Object)new F_2904_S("mco.trial.message.line2"));
    private static final x_282_a Q_2552_b = new F_2904_S("mco.selectServer.uninitialized");
    private static final x_282_a C_2741_M = new F_2904_S("mco.selectServer.expiredList");
    private static final x_282_a k_2293_S = new F_2904_S("mco.selectServer.expiredRenew");
    private static final x_282_a q_2307_F = new F_2904_S("mco.selectServer.expiredTrial");
    private static final x_282_a Z_875_P = new F_2904_S("mco.selectServer.expiredSubscribe");
    private static final x_282_a c_3005_b = new F_2904_S("mco.selectServer.minigame").n_1700_B(" ");
    private static final x_282_a H_2857_Y = new F_2904_S("mco.selectServer.popup");
    private static final x_282_a A_4115_X = new F_2904_S("mco.selectServer.expired");
    private static final x_282_a Y_1740_V = new F_2904_S("mco.selectServer.expires.soon");
    private static final x_282_a t_4043_B = new F_2904_S("mco.selectServer.expires.day");
    private static final x_282_a x_607_J = new F_2904_S("mco.selectServer.open");
    private static final x_282_a e_4240_b = new F_2904_S("mco.selectServer.closed");
    private static final x_282_a n_3318_d = new F_2904_S("mco.selectServer.leave");
    private static final x_282_a d_2427_y = new F_2904_S("mco.selectServer.configure");
    private static final x_282_a z_1737_N = new F_2904_S("mco.selectServer.info");
    private static final x_282_a v_4276_D = new F_2904_S("mco.news");
    private static List<g_2336_b> d_2461_k = ImmutableList.of();
    private static final j_2266_I G_624_v = new j_2266_I();
    private static boolean T_2506_i;
    private static int q_4610_l;
    private static volatile boolean z_4693_k;
    private static volatile boolean g_221_o;
    private static volatile boolean e_2887_G;
    private static k_2603_m B_1668_F;
    private static boolean g_164_R;
    private final RateLimiter X_933_l;
    private boolean Z_976_R;
    private final k_2603_m H_1990_U;
    private volatile v_4262_N N_2525_X;
    private long c_4037_x = -1L;
    private Button g_2268_R;
    private Button T_3594_S;
    private Button D_4792_h;
    private Button s_2632_s;
    private Button l_1233_K;
    private List<x_282_a> z_1333_t;
    private List<q_1982_R> O_508_d = Lists.newArrayList();
    private volatile int r_715_M;
    private int A_1038_p;
    private boolean i_1637_u;
    private boolean Ping;
    private boolean p_178_J;
    private volatile boolean RealmsClientConfig;
    private volatile boolean f_4016_n;
    private volatile boolean j_276_v;
    private volatile boolean UploadStatus;
    private volatile String e_1992_r;
    private int D_60_a;
    private int k_3961_g;
    private boolean Ops;
    private List<k_3238_x> h_4320_q;
    private int t_4219_U;
    private ReentrantLock V_1446_Y = new ReentrantLock();
    private N_2445_q PlayerInfo = N_2445_q.n_1700_B;
    private w_1484_f V_1225_t;
    private Button U_1241_n;
    private Button q_1982_R;
    private Button dtoRealmsServerAddress;
    private Button w_612_n;
    private Button RealmsServerPing;
    private Button j_1564_a;

    public r_715_M(k_2603_m p_i232181_1_) {
        this.H_1990_U = p_i232181_1_;
        this.X_933_l = RateLimiter.create((double)0.01666666753590107);
    }

    private boolean u_1723_Y() {
        if (lightning.product.r_715_M.v_4262_N() && this.i_1637_u) {
            if (this.RealmsClientConfig && !this.f_4016_n) {
                return true;
            }
            for (q_1982_R realmsserver : this.O_508_d) {
                if (!realmsserver.v_4262_N.equals(this.minecraft.z_1737_N().J_1907_R())) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean n_1700_B() {
        if (lightning.product.r_715_M.v_4262_N() && this.i_1637_u) {
            if (this.Ping) {
                return true;
            }
            return this.RealmsClientConfig && !this.f_4016_n && this.O_508_d.isEmpty() ? true : this.O_508_d.isEmpty();
        }
        return false;
    }

    @Override
    public void init() {
        this.h_4320_q = Lists.newArrayList((Object[])new k_3238_x[]{new k_3238_x(new char[]{'3', '2', '1', '4', '5', '6'}, () -> {
            T_2506_i = !T_2506_i;
        }), new k_3238_x(new char[]{'9', '8', '7', '1', '2', '3'}, () -> {
            if (lightning.product.p_178_J.n_1700_B == p_178_J.J_1907_R.J_1907_R) {
                this.t_1786_h();
            } else {
                this.Q_4569_t();
            }
        }), new k_3238_x(new char[]{'9', '8', '7', '4', '5', '6'}, () -> {
            if (lightning.product.p_178_J.n_1700_B == p_178_J.J_1907_R.R_4764_Y) {
                this.t_1786_h();
            } else {
                this.M_182_A();
            }
        })});
        if (B_1668_F != null) {
            this.minecraft.n_1700_B(B_1668_F);
        } else {
            this.V_1446_Y = new ReentrantLock();
            if (e_2887_G && !lightning.product.r_715_M.v_4262_N()) {
                this.h_1847_R();
            }
            this.M_588_G();
            this.P_4830_p();
            if (!this.Z_976_R) {
                this.minecraft.R_4764_Y(false);
            }
            this.minecraft.Q_4569_t.n_1700_B(true);
            if (lightning.product.r_715_M.v_4262_N()) {
                G_624_v.P_1922_E();
            }
            this.j_276_v = false;
            if (lightning.product.r_715_M.v_4262_N() && this.i_1637_u) {
                this.J_1907_R();
            }
            this.N_2525_X = new v_4262_N();
            if (q_4610_l != -1) {
                this.N_2525_X.setScrollAmount(q_4610_l);
            }
            this.addListener(this.N_2525_X);
            this.J_1907_R(this.N_2525_X);
            this.PlayerInfo = N_2445_q.n_1700_B(this.font, (FormattedText)H_2857_Y, 100);
        }
    }

    private static boolean v_4262_N() {
        return g_221_o && z_4693_k;
    }

    public void J_1907_R() {
        this.l_1233_K = this.addButton(new Button(this.width / 2 - 202, this.height - 32, 90, 20, new F_2904_S("mco.selectServer.leave"), p_237624_1_ -> this.v_4262_N(this.n_1700_B(this.c_4037_x))));
        this.s_2632_s = this.addButton(new Button(this.width / 2 - 190, this.height - 32, 90, 20, new F_2904_S("mco.selectServer.configure"), p_237637_1_ -> this.u_1723_Y(this.n_1700_B(this.c_4037_x))));
        this.g_2268_R = this.addButton(new Button(this.width / 2 - 93, this.height - 32, 90, 20, new F_2904_S("mco.selectServer.play"), p_237635_1_ -> {
            q_1982_R realmsserver1 = this.n_1700_B(this.c_4037_x);
            if (realmsserver1 != null) {
                this.n_1700_B(realmsserver1, this);
            }
        }));
        this.T_3594_S = this.addButton(new Button(this.width / 2 + 4, this.height - 32, 90, 20, CommonComponents.w_1484_f, p_237632_1_ -> {
            if (!this.p_178_J) {
                this.minecraft.n_1700_B(this.H_1990_U);
            }
        }));
        this.D_4792_h = this.addButton(new Button(this.width / 2 + 100, this.height - 32, 90, 20, new F_2904_S("mco.selectServer.expiredRenew"), p_237629_1_ -> this.u_2550_I()));
        this.q_1982_R = this.addButton(new P_1922_E());
        this.dtoRealmsServerAddress = this.addButton(new G_564_y());
        this.U_1241_n = this.addButton(new J_1907_R());
        this.j_1564_a = this.addButton(new n_1700_B());
        this.w_612_n = this.addButton(new Button(this.width / 2 + 52, this.Q_2552_b() + 137 - 20, 98, 20, new F_2904_S("mco.selectServer.trial"), p_237618_1_ -> {
            if (this.RealmsClientConfig && !this.f_4016_n) {
                j_3341_s.t_148_a().n_1700_B("https://aka.ms/startjavarealmstrial");
                this.minecraft.n_1700_B(this.H_1990_U);
            }
        }));
        this.RealmsServerPing = this.addButton(new Button(this.width / 2 + 52, this.Q_2552_b() + 160 - 20, 98, 20, new F_2904_S("mco.selectServer.buy"), p_237612_0_ -> j_3341_s.t_148_a().n_1700_B("https://aka.ms/BuyJavaRealms")));
        q_1982_R realmsserver = this.n_1700_B(this.c_4037_x);
        this.n_1700_B(realmsserver);
    }

    private void n_1700_B(@Nullable q_1982_R p_223915_1_) {
        boolean flag;
        this.g_2268_R.active = this.J_1907_R(p_223915_1_) && !this.n_1700_B();
        this.D_4792_h.visible = this.R_4764_Y(p_223915_1_);
        this.s_2632_s.visible = this.G_564_y(p_223915_1_);
        this.l_1233_K.visible = this.P_1922_E(p_223915_1_);
        this.w_612_n.visible = flag = this.n_1700_B() && this.RealmsClientConfig && !this.f_4016_n;
        this.w_612_n.active = flag;
        this.RealmsServerPing.visible = this.n_1700_B();
        this.j_1564_a.visible = this.n_1700_B() && this.Ping;
        this.D_4792_h.active = !this.n_1700_B();
        this.s_2632_s.active = !this.n_1700_B();
        this.l_1233_K.active = !this.n_1700_B();
        this.dtoRealmsServerAddress.active = true;
        this.q_1982_R.active = true;
        this.T_3594_S.active = true;
        this.U_1241_n.active = !this.n_1700_B();
    }

    private boolean w_1484_f() {
        return (!this.n_1700_B() || this.Ping) && lightning.product.r_715_M.v_4262_N() && this.i_1637_u;
    }

    private boolean J_1907_R(@Nullable q_1982_R p_223897_1_) {
        return p_223897_1_ != null && !p_223897_1_.s_956_w && p_223897_1_.P_1922_E == q_1982_R.R_4764_Y.J_1907_R;
    }

    private boolean R_4764_Y(@Nullable q_1982_R p_223920_1_) {
        return p_223920_1_ != null && p_223920_1_.s_956_w && this.t_148_a(p_223920_1_);
    }

    private boolean G_564_y(@Nullable q_1982_R p_223941_1_) {
        return p_223941_1_ != null && this.t_148_a(p_223941_1_);
    }

    private boolean P_1922_E(@Nullable q_1982_R p_223959_1_) {
        return p_223959_1_ != null && !this.t_148_a(p_223959_1_);
    }

    @Override
    public void tick() {
        super.tick();
        this.p_178_J = false;
        ++this.A_1038_p;
        --this.t_4219_U;
        if (this.t_4219_U < 0) {
            this.t_4219_U = 0;
        }
        if (lightning.product.r_715_M.v_4262_N()) {
            G_624_v.J_1907_R();
            if (G_624_v.n_1700_B(j_2266_I.G_564_y.n_1700_B)) {
                boolean flag;
                List<q_1982_R> list = G_624_v.u_1723_Y();
                this.N_2525_X.n_1700_B();
                boolean bl = flag = !this.i_1637_u;
                if (flag) {
                    this.i_1637_u = true;
                }
                if (list != null) {
                    boolean flag1 = false;
                    for (q_1982_R realmsserver : list) {
                        if (!this.s_956_w(realmsserver)) continue;
                        flag1 = true;
                    }
                    this.O_508_d = list;
                    if (this.u_1723_Y()) {
                        this.N_2525_X.n_1700_B(new t_148_a());
                    }
                    for (q_1982_R realmsserver1 : this.O_508_d) {
                        this.N_2525_X.n_1700_B(new u_1723_Y(realmsserver1));
                    }
                    if (!g_164_R && flag1) {
                        g_164_R = true;
                        this.t_148_a();
                    }
                }
                if (flag) {
                    this.J_1907_R();
                }
            }
            if (G_624_v.n_1700_B(j_2266_I.G_564_y.J_1907_R)) {
                this.r_715_M = G_624_v.v_4262_N();
                if (this.r_715_M > 0 && this.X_933_l.tryAcquire(1)) {
                    NarrationHelper.n_1700_B(K_1289_S.n_1700_B("mco.configure.world.invite.narration", this.r_715_M));
                }
            }
            if (G_624_v.n_1700_B(j_2266_I.G_564_y.R_4764_Y) && !this.f_4016_n) {
                boolean flag2 = G_624_v.w_1484_f();
                if (flag2 != this.RealmsClientConfig && this.n_1700_B()) {
                    this.RealmsClientConfig = flag2;
                    this.j_276_v = false;
                } else {
                    this.RealmsClientConfig = flag2;
                }
            }
            if (G_624_v.n_1700_B(j_2266_I.G_564_y.G_564_y)) {
                M_1641_O realmsserverplayerlists = G_624_v.t_148_a();
                block2: for (j_1564_a realmsserverplayerlist : realmsserverplayerlists.n_1700_B) {
                    for (q_1982_R realmsserver2 : this.O_508_d) {
                        if (realmsserver2.n_1700_B != realmsserverplayerlist.n_1700_B) continue;
                        realmsserver2.n_1700_B(realmsserverplayerlist);
                        continue block2;
                    }
                }
            }
            if (G_624_v.n_1700_B(j_2266_I.G_564_y.P_1922_E)) {
                this.UploadStatus = G_624_v.s_956_w();
                this.e_1992_r = G_624_v.u_2550_I();
            }
            G_624_v.G_564_y();
            if (this.n_1700_B()) {
                ++this.k_3961_g;
            }
            if (this.U_1241_n != null) {
                this.U_1241_n.visible = this.w_1484_f();
            }
        }
    }

    private void t_148_a() {
        new Thread(() -> {
            List<RegionPingResult> list = lightning.product.Ping.n_1700_B();
            p_178_J realmsclient = lightning.product.p_178_J.n_1700_B();
            V_1446_Y pingresult = new V_1446_Y();
            pingresult.n_1700_B = list;
            pingresult.J_1907_R = this.s_956_w();
            try {
                realmsclient.n_1700_B(pingresult);
            }
            catch (Throwable throwable) {
                n_1700_B.warn("Could not send ping result to Realms: ", throwable);
            }
        }).start();
    }

    private List<Long> s_956_w() {
        ArrayList list = Lists.newArrayList();
        for (q_1982_R realmsserver : this.O_508_d) {
            if (!this.s_956_w(realmsserver)) continue;
            list.add(realmsserver.n_1700_B);
        }
        return list;
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
        this.multiplayerClientSuggestionProvider();
    }

    private void u_2550_I() {
        q_1982_R realmsserver = this.n_1700_B(this.c_4037_x);
        if (realmsserver != null) {
            String s = "https://aka.ms/ExtendJavaRealms?subscriptionId=" + realmsserver.J_1907_R + "&profileId=" + this.minecraft.z_1737_N().J_1907_R() + "&ref=" + (realmsserver.u_2550_I ? "expiredTrial" : "expiredRealm");
            this.minecraft.Q_4569_t.n_1700_B(s);
            j_3341_s.t_148_a().n_1700_B(s);
        }
    }

    private void M_588_G() {
        if (!e_2887_G) {
            e_2887_G = true;
            new Thread("MCO Compatability Checker #1"){

                @Override
                public void run() {
                    p_178_J realmsclient = lightning.product.p_178_J.n_1700_B();
                    try {
                        p_178_J.n_1700_B realmsclient$compatibleversionresponse = realmsclient.t_148_a();
                        if (realmsclient$compatibleversionresponse == p_178_J.n_1700_B.J_1907_R) {
                            B_1668_F = new RealmsClientOutdatedScreen(r_715_M.this.H_1990_U, true);
                            r_715_M.this.minecraft.execute(() -> r_715_M.this.minecraft.n_1700_B(B_1668_F));
                            return;
                        }
                        if (realmsclient$compatibleversionresponse == p_178_J.n_1700_B.R_4764_Y) {
                            B_1668_F = new RealmsClientOutdatedScreen(r_715_M.this.H_1990_U, false);
                            r_715_M.this.minecraft.execute(() -> r_715_M.this.minecraft.n_1700_B(B_1668_F));
                            return;
                        }
                        r_715_M.this.h_1847_R();
                    }
                    catch (u_744_e realmsserviceexception) {
                        e_2887_G = false;
                        n_1700_B.error("Couldn't connect to realms", (Throwable)realmsserviceexception);
                        if (realmsserviceexception.n_1700_B == 401) {
                            B_1668_F = new w_728_N(new F_2904_S("mco.error.invalid.session.title"), new F_2904_S("mco.error.invalid.session.message"), r_715_M.this.H_1990_U);
                            r_715_M.this.minecraft.execute(() -> r_715_M.this.minecraft.n_1700_B(B_1668_F));
                        }
                        r_715_M.this.minecraft.execute(() -> r_715_M.this.minecraft.n_1700_B(new w_728_N(realmsserviceexception, r_715_M.this.H_1990_U)));
                    }
                }
            }.start();
        }
    }

    private void P_4830_p() {
    }

    private void h_1847_R() {
        new Thread("MCO Compatability Checker #1"){

            @Override
            public void run() {
                p_178_J realmsclient = lightning.product.p_178_J.n_1700_B();
                try {
                    Boolean obool = realmsclient.v_4262_N();
                    if (obool.booleanValue()) {
                        n_1700_B.info("Realms is available for this user");
                        z_4693_k = true;
                    } else {
                        n_1700_B.info("Realms is not available for this user");
                        z_4693_k = false;
                        r_715_M.this.minecraft.execute(() -> r_715_M.this.minecraft.n_1700_B(new RealmsParentalConsentScreen(r_715_M.this.H_1990_U)));
                    }
                    g_221_o = true;
                }
                catch (u_744_e realmsserviceexception) {
                    n_1700_B.error("Couldn't connect to realms", (Throwable)realmsserviceexception);
                    r_715_M.this.minecraft.execute(() -> r_715_M.this.minecraft.n_1700_B(new w_728_N(realmsserviceexception, r_715_M.this.H_1990_U)));
                }
            }
        }.start();
    }

    private void Q_4569_t() {
        if (lightning.product.p_178_J.n_1700_B != p_178_J.J_1907_R.J_1907_R) {
            new Thread(this, "MCO Stage Availability Checker #1"){

                @Override
                public void run() {
                    p_178_J realmsclient = lightning.product.p_178_J.n_1700_B();
                    try {
                        Boolean obool = realmsclient.w_1484_f();
                        if (obool.booleanValue()) {
                            lightning.product.p_178_J.J_1907_R();
                            n_1700_B.info("Switched to stage");
                            G_624_v.P_1922_E();
                        }
                    }
                    catch (u_744_e realmsserviceexception) {
                        n_1700_B.error("Couldn't connect to Realms: " + String.valueOf(realmsserviceexception));
                    }
                }
            }.start();
        }
    }

    private void M_182_A() {
        if (lightning.product.p_178_J.n_1700_B != p_178_J.J_1907_R.R_4764_Y) {
            new Thread(this, "MCO Local Availability Checker #1"){

                @Override
                public void run() {
                    p_178_J realmsclient = lightning.product.p_178_J.n_1700_B();
                    try {
                        Boolean obool = realmsclient.w_1484_f();
                        if (obool.booleanValue()) {
                            lightning.product.p_178_J.G_564_y();
                            n_1700_B.info("Switched to local");
                            G_624_v.P_1922_E();
                        }
                    }
                    catch (u_744_e realmsserviceexception) {
                        n_1700_B.error("Couldn't connect to Realms: " + String.valueOf(realmsserviceexception));
                    }
                }
            }.start();
        }
    }

    private void t_1786_h() {
        lightning.product.p_178_J.R_4764_Y();
        G_624_v.P_1922_E();
    }

    private void multiplayerClientSuggestionProvider() {
        G_624_v.M_588_G();
    }

    private void u_1723_Y(q_1982_R p_223966_1_) {
        if (this.minecraft.z_1737_N().J_1907_R().equals(p_223966_1_.v_4262_N) || T_2506_i) {
            this.w_1457_N();
            this.minecraft.n_1700_B(new W_3464_O(this, p_223966_1_.n_1700_B));
        }
    }

    private void v_4262_N(@Nullable q_1982_R p_223906_1_) {
        if (p_223906_1_ != null && !this.minecraft.z_1737_N().J_1907_R().equals(p_223906_1_.v_4262_N)) {
            this.w_1457_N();
            F_2904_S itextcomponent = new F_2904_S("mco.configure.world.leave.question.line1");
            F_2904_S itextcomponent1 = new F_2904_S("mco.configure.world.leave.question.line2");
            this.minecraft.n_1700_B(new RealmsLongConfirmationScreen(this::n_1700_B, RealmsLongConfirmationScreen.n_1700_B.J_1907_R, itextcomponent, itextcomponent1, true));
        }
    }

    private void w_1457_N() {
        q_4610_l = (int)this.N_2525_X.getScrollAmount();
    }

    @Nullable
    private q_1982_R n_1700_B(long p_223967_1_) {
        for (q_1982_R realmsserver : this.O_508_d) {
            if (realmsserver.n_1700_B != p_223967_1_) continue;
            return realmsserver;
        }
        return null;
    }

    private void n_1700_B(boolean p_237625_1_) {
        if (p_237625_1_) {
            new Thread("Realms-leave-server"){

                @Override
                public void run() {
                    try {
                        q_1982_R realmsserver = r_715_M.this.n_1700_B(r_715_M.this.c_4037_x);
                        if (realmsserver != null) {
                            p_178_J realmsclient = lightning.product.p_178_J.n_1700_B();
                            realmsclient.R_4764_Y(realmsserver.n_1700_B);
                            r_715_M.this.minecraft.execute(() -> r_715_M.this.w_1484_f(realmsserver));
                        }
                    }
                    catch (u_744_e realmsserviceexception) {
                        n_1700_B.error("Couldn't configure world");
                        r_715_M.this.minecraft.execute(() -> r_715_M.this.minecraft.n_1700_B(new w_728_N(realmsserviceexception, (k_2603_m)r_715_M.this)));
                    }
                }
            }.start();
        }
        this.minecraft.n_1700_B(this);
    }

    private void w_1484_f(q_1982_R p_243059_1_) {
        G_624_v.n_1700_B(p_243059_1_);
        this.O_508_d.remove(p_243059_1_);
        this.N_2525_X.getEventListeners().removeIf(p_243041_1_ -> p_243041_1_ instanceof u_1723_Y && ((u_1723_Y)p_243041_1_).J_1907_R.n_1700_B == this.c_4037_x);
        this.N_2525_X.J_1907_R((R_4764_Y)null);
        this.n_1700_B((q_1982_R)null);
        this.c_4037_x = -1L;
        this.g_2268_R.active = false;
    }

    public void R_4764_Y() {
        this.c_4037_x = -1L;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.h_4320_q.forEach(k_3238_x::n_1700_B);
            this.Y_601_j();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void Y_601_j() {
        if (this.n_1700_B() && this.Ping) {
            this.Ping = false;
        } else {
            this.minecraft.n_1700_B(this.H_1990_U);
        }
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        this.h_4320_q.forEach(p_237578_1_ -> p_237578_1_.n_1700_B(codePoint));
        return true;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.V_1225_t = lightning.product.r_715_M$w_1484_f.n_1700_B;
        this.z_1333_t = null;
        this.renderBackground(matrixStack);
        this.N_2525_X.render(matrixStack, mouseX, mouseY, partialTicks);
        this.n_1700_B(matrixStack, this.width / 2 - 50, 7);
        if (lightning.product.p_178_J.n_1700_B == p_178_J.J_1907_R.J_1907_R) {
            this.J_1907_R(matrixStack);
        }
        if (lightning.product.p_178_J.n_1700_B == p_178_J.J_1907_R.R_4764_Y) {
            this.n_1700_B(matrixStack);
        }
        if (this.n_1700_B()) {
            this.J_1907_R(matrixStack, mouseX, mouseY);
        } else {
            if (this.j_276_v) {
                this.n_1700_B((q_1982_R)null);
                if (!this.children.contains(this.N_2525_X)) {
                    this.children.add(this.N_2525_X);
                }
                q_1982_R realmsserver = this.n_1700_B(this.c_4037_x);
                this.g_2268_R.active = this.J_1907_R(realmsserver);
            }
            this.j_276_v = false;
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.z_1333_t != null) {
            this.n_1700_B(matrixStack, this.z_1333_t, mouseX, mouseY);
        }
        if (this.RealmsClientConfig && !this.f_4016_n && this.n_1700_B()) {
            this.minecraft.G_624_v().n_1700_B(t_1786_h);
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            int k = 8;
            int i = 8;
            int j = 0;
            if ((j_3341_s.J_1907_R() / 800L & 1L) == 1L) {
                j = 8;
            }
            C_2701_A.blit(matrixStack, this.w_612_n.x + this.w_612_n.getWidth() - 8 - 4, this.w_612_n.y + this.w_612_n.getHeightRealms() / 2 - 4, 0.0f, j, 8, 8, 8, 16);
        }
    }

    private void n_1700_B(g_221_o p_237579_1_, int p_237579_2_, int p_237579_3_) {
        this.minecraft.G_624_v().n_1700_B(s_956_w);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.J_1907_R(0.5f, 0.5f, 0.5f);
        C_2701_A.blit(p_237579_1_, p_237579_2_ * 2, p_237579_3_ * 2 - 5, 0.0f, 0.0f, 200, 50, 200, 50);
        lightning.product.c_4037_x.d_2461_k();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.R_4764_Y(mouseX, mouseY) && this.Ping) {
            this.Ping = false;
            this.p_178_J = true;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean R_4764_Y(double p_223979_1_, double p_223979_3_) {
        int i = this.Y_259_p();
        int j = this.Q_2552_b();
        return p_223979_1_ < (double)(i - 5) || p_223979_1_ > (double)(i + 315) || p_223979_3_ < (double)(j - 5) || p_223979_3_ > (double)(j + 171);
    }

    private void J_1907_R(g_221_o p_237605_1_, int p_237605_2_, int p_237605_3_) {
        int i = this.Y_259_p();
        int j = this.Q_2552_b();
        if (!this.j_276_v) {
            v_4262_N iguieventlistener;
            this.D_60_a = 0;
            this.k_3961_g = 0;
            this.Ops = true;
            this.n_1700_B((q_1982_R)null);
            if (this.children.contains(this.N_2525_X) && !this.children.remove(iguieventlistener = this.N_2525_X)) {
                n_1700_B.error("Unable to remove widget: " + String.valueOf(iguieventlistener));
            }
            NarrationHelper.n_1700_B(H_2857_Y.getString());
        }
        if (this.i_1637_u) {
            this.j_276_v = true;
        }
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 0.7f);
        lightning.product.c_4037_x.Y_601_j();
        this.minecraft.G_624_v().n_1700_B(Q_4569_t);
        boolean l = false;
        int k = 32;
        C_2701_A.blit(p_237605_1_, 0, 32, 0.0f, 0.0f, this.width, this.height - 40 - 32, 310, 166);
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.G_624_v().n_1700_B(h_1847_R);
        C_2701_A.blit(p_237605_1_, i, j, 0.0f, 0.0f, 310, 166, 310, 166);
        if (!d_2461_k.isEmpty()) {
            this.minecraft.G_624_v().n_1700_B(d_2461_k.get(this.D_60_a));
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            C_2701_A.blit(p_237605_1_, i + 7, j + 7, 0.0f, 0.0f, 195, 152, 195, 152);
            if (this.k_3961_g % 95 < 5) {
                if (!this.Ops) {
                    this.D_60_a = (this.D_60_a + 1) % d_2461_k.size();
                    this.Ops = true;
                }
            } else {
                this.Ops = false;
            }
        }
        this.PlayerInfo.R_4764_Y(p_237605_1_, this.width / 2 + 52, j + 7, 10, 0x4C4C4C);
    }

    private int Y_259_p() {
        return (this.width - 310) / 2;
    }

    private int Q_2552_b() {
        return this.height / 2 - 80;
    }

    private void n_1700_B(g_221_o p_237581_1_, int p_237581_2_, int p_237581_3_, int p_237581_4_, int p_237581_5_, boolean p_237581_6_, boolean p_237581_7_) {
        boolean flag4;
        boolean flag2;
        boolean flag1;
        int i = this.r_715_M;
        boolean flag = this.G_564_y(p_237581_2_, p_237581_3_);
        boolean bl = flag1 = p_237581_7_ && p_237581_6_;
        if (flag1) {
            float f = 0.25f + (1.0f + u_530_F.n_1700_B((float)this.A_1038_p * 0.5f)) * 0.25f;
            int j = 0xFF000000 | (int)(f * 64.0f) << 16 | (int)(f * 64.0f) << 8 | (int)(f * 64.0f) << 0;
            lightning.product.r_715_M.fillGradient(p_237581_1_, p_237581_4_ - 2, p_237581_5_ - 2, p_237581_4_ + 18, p_237581_5_ + 18, j, j);
            j = 0xFF000000 | (int)(f * 255.0f) << 16 | (int)(f * 255.0f) << 8 | (int)(f * 255.0f) << 0;
            lightning.product.r_715_M.fillGradient(p_237581_1_, p_237581_4_ - 2, p_237581_5_ - 2, p_237581_4_ + 18, p_237581_5_ - 1, j, j);
            lightning.product.r_715_M.fillGradient(p_237581_1_, p_237581_4_ - 2, p_237581_5_ - 2, p_237581_4_ - 1, p_237581_5_ + 18, j, j);
            lightning.product.r_715_M.fillGradient(p_237581_1_, p_237581_4_ + 17, p_237581_5_ - 2, p_237581_4_ + 18, p_237581_5_ + 18, j, j);
            lightning.product.r_715_M.fillGradient(p_237581_1_, p_237581_4_ - 2, p_237581_5_ + 17, p_237581_4_ + 18, p_237581_5_ + 18, j, j);
        }
        this.minecraft.G_624_v().n_1700_B(w_1484_f);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        boolean flag3 = p_237581_7_ && p_237581_6_;
        float f2 = flag3 ? 16.0f : 0.0f;
        C_2701_A.blit(p_237581_1_, p_237581_4_, p_237581_5_ - 6, f2, 0.0f, 15, 25, 31, 25);
        boolean bl2 = flag2 = p_237581_7_ && i != 0;
        if (flag2) {
            int k = (Math.min(i, 6) - 1) * 8;
            int l = (int)(Math.max(0.0f, Math.max(u_530_F.n_1700_B((float)(10 + this.A_1038_p) * 0.57f), u_530_F.J_1907_R((float)this.A_1038_p * 0.35f))) * -6.0f);
            this.minecraft.G_624_v().n_1700_B(v_4262_N);
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            float f1 = flag ? 8.0f : 0.0f;
            C_2701_A.blit(p_237581_1_, p_237581_4_ + 4, p_237581_5_ + 4 + l, k, f1, 8, 8, 48, 16);
        }
        int j1 = p_237581_2_ + 12;
        boolean bl3 = flag4 = p_237581_7_ && flag;
        if (flag4) {
            x_282_a itextcomponent = i == 0 ? w_1457_N : Y_601_j;
            int i1 = this.font.n_1700_B((FormattedText)itextcomponent);
            lightning.product.r_715_M.fillGradient(p_237581_1_, j1 - 3, p_237581_3_ - 3, j1 + i1 + 3, p_237581_3_ + 8 + 3, -1073741824, -1073741824);
            this.font.n_1700_B(p_237581_1_, itextcomponent, (float)j1, (float)p_237581_3_, -1);
        }
    }

    private boolean G_564_y(double p_223931_1_, double p_223931_3_) {
        int i = this.width / 2 + 50;
        int j = this.width / 2 + 66;
        int k = 11;
        int l = 23;
        if (this.r_715_M != 0) {
            i -= 3;
            j += 3;
            k -= 5;
            l += 5;
        }
        return (double)i <= p_223931_1_ && p_223931_1_ <= (double)j && (double)k <= p_223931_3_ && p_223931_3_ <= (double)l;
    }

    public void n_1700_B(q_1982_R p_223911_1_, k_2603_m p_223911_2_) {
        if (p_223911_1_ != null) {
            try {
                if (!this.V_1446_Y.tryLock(1L, TimeUnit.SECONDS)) {
                    return;
                }
                if (this.V_1446_Y.getHoldCount() > 1) {
                    return;
                }
            }
            catch (InterruptedException interruptedexception) {
                return;
            }
            this.Z_976_R = true;
            this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(p_223911_2_, new Q_201_j(this, p_223911_2_, p_223911_1_, this.V_1446_Y)));
        }
    }

    private boolean t_148_a(q_1982_R p_223885_1_) {
        return p_223885_1_.v_4262_N != null && p_223885_1_.v_4262_N.equals(this.minecraft.z_1737_N().J_1907_R());
    }

    private boolean s_956_w(q_1982_R p_223991_1_) {
        return this.t_148_a(p_223991_1_) && !p_223991_1_.s_956_w;
    }

    private void n_1700_B(g_221_o p_237614_1_, int p_237614_2_, int p_237614_3_, int p_237614_4_, int p_237614_5_) {
        this.minecraft.G_624_v().n_1700_B(G_564_y);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        C_2701_A.blit(p_237614_1_, p_237614_2_, p_237614_3_, 0.0f, 0.0f, 10, 28, 10, 28);
        if (p_237614_4_ >= p_237614_2_ && p_237614_4_ <= p_237614_2_ + 9 && p_237614_5_ >= p_237614_3_ && p_237614_5_ <= p_237614_3_ + 27 && p_237614_5_ < this.height - 40 && p_237614_5_ > 32 && !this.n_1700_B()) {
            this.n_1700_B(A_4115_X);
        }
    }

    private void n_1700_B(g_221_o p_237606_1_, int p_237606_2_, int p_237606_3_, int p_237606_4_, int p_237606_5_, int p_237606_6_) {
        this.minecraft.G_624_v().n_1700_B(P_1922_E);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.A_1038_p % 20 < 10) {
            C_2701_A.blit(p_237606_1_, p_237606_2_, p_237606_3_, 0.0f, 0.0f, 10, 28, 20, 28);
        } else {
            C_2701_A.blit(p_237606_1_, p_237606_2_, p_237606_3_, 10.0f, 0.0f, 10, 28, 20, 28);
        }
        if (p_237606_4_ >= p_237606_2_ && p_237606_4_ <= p_237606_2_ + 9 && p_237606_5_ >= p_237606_3_ && p_237606_5_ <= p_237606_3_ + 27 && p_237606_5_ < this.height - 40 && p_237606_5_ > 32 && !this.n_1700_B()) {
            if (p_237606_6_ <= 0) {
                this.n_1700_B(Y_1740_V);
            } else if (p_237606_6_ == 1) {
                this.n_1700_B(t_4043_B);
            } else {
                this.n_1700_B(new F_2904_S("mco.selectServer.expires.days", p_237606_6_));
            }
        }
    }

    private void J_1907_R(g_221_o p_237620_1_, int p_237620_2_, int p_237620_3_, int p_237620_4_, int p_237620_5_) {
        this.minecraft.G_624_v().n_1700_B(J_1907_R);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        C_2701_A.blit(p_237620_1_, p_237620_2_, p_237620_3_, 0.0f, 0.0f, 10, 28, 10, 28);
        if (p_237620_4_ >= p_237620_2_ && p_237620_4_ <= p_237620_2_ + 9 && p_237620_5_ >= p_237620_3_ && p_237620_5_ <= p_237620_3_ + 27 && p_237620_5_ < this.height - 40 && p_237620_5_ > 32 && !this.n_1700_B()) {
            this.n_1700_B(x_607_J);
        }
    }

    private void R_4764_Y(g_221_o p_237626_1_, int p_237626_2_, int p_237626_3_, int p_237626_4_, int p_237626_5_) {
        this.minecraft.G_624_v().n_1700_B(R_4764_Y);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        C_2701_A.blit(p_237626_1_, p_237626_2_, p_237626_3_, 0.0f, 0.0f, 10, 28, 10, 28);
        if (p_237626_4_ >= p_237626_2_ && p_237626_4_ <= p_237626_2_ + 9 && p_237626_5_ >= p_237626_3_ && p_237626_5_ <= p_237626_3_ + 27 && p_237626_5_ < this.height - 40 && p_237626_5_ > 32 && !this.n_1700_B()) {
            this.n_1700_B(e_4240_b);
        }
    }

    private void G_564_y(g_221_o p_237630_1_, int p_237630_2_, int p_237630_3_, int p_237630_4_, int p_237630_5_) {
        boolean flag = false;
        if (p_237630_4_ >= p_237630_2_ && p_237630_4_ <= p_237630_2_ + 28 && p_237630_5_ >= p_237630_3_ && p_237630_5_ <= p_237630_3_ + 28 && p_237630_5_ < this.height - 40 && p_237630_5_ > 32 && !this.n_1700_B()) {
            flag = true;
        }
        this.minecraft.G_624_v().n_1700_B(u_1723_Y);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        float f = flag ? 28.0f : 0.0f;
        C_2701_A.blit(p_237630_1_, p_237630_2_, p_237630_3_, f, 0.0f, 28, 28, 56, 28);
        if (flag) {
            this.n_1700_B(n_3318_d);
            this.V_1225_t = lightning.product.r_715_M$w_1484_f.R_4764_Y;
        }
    }

    private void P_1922_E(g_221_o p_237633_1_, int p_237633_2_, int p_237633_3_, int p_237633_4_, int p_237633_5_) {
        boolean flag = false;
        if (p_237633_4_ >= p_237633_2_ && p_237633_4_ <= p_237633_2_ + 28 && p_237633_5_ >= p_237633_3_ && p_237633_5_ <= p_237633_3_ + 28 && p_237633_5_ < this.height - 40 && p_237633_5_ > 32 && !this.n_1700_B()) {
            flag = true;
        }
        this.minecraft.G_624_v().n_1700_B(u_2550_I);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        float f = flag ? 28.0f : 0.0f;
        C_2701_A.blit(p_237633_1_, p_237633_2_, p_237633_3_, f, 0.0f, 28, 28, 56, 28);
        if (flag) {
            this.n_1700_B(d_2427_y);
            this.V_1225_t = lightning.product.r_715_M$w_1484_f.G_564_y;
        }
    }

    protected void n_1700_B(g_221_o p_237583_1_, List<x_282_a> p_237583_2_, int p_237583_3_, int p_237583_4_) {
        if (!p_237583_2_.isEmpty()) {
            int i = 0;
            int j = 0;
            for (x_282_a itextcomponent : p_237583_2_) {
                int k = this.font.n_1700_B((FormattedText)itextcomponent);
                if (k <= j) continue;
                j = k;
            }
            int i1 = p_237583_3_ - j - 5;
            int j1 = p_237583_4_;
            if (i1 < 0) {
                i1 = p_237583_3_ + 12;
            }
            for (x_282_a itextcomponent1 : p_237583_2_) {
                int l = j1 - (i == 0 ? 3 : 0) + i;
                lightning.product.r_715_M.fillGradient(p_237583_1_, i1 - 3, l, i1 + j + 3, j1 + 8 + 3 + i, -1073741824, -1073741824);
                this.font.n_1700_B(p_237583_1_, itextcomponent1, (float)i1, (float)(j1 + i), 0xFFFFFF);
                i += 10;
            }
        }
    }

    private void n_1700_B(g_221_o p_237580_1_, int p_237580_2_, int p_237580_3_, int p_237580_4_, int p_237580_5_, boolean p_237580_6_) {
        boolean flag = false;
        if (p_237580_2_ >= p_237580_4_ && p_237580_2_ <= p_237580_4_ + 20 && p_237580_3_ >= p_237580_5_ && p_237580_3_ <= p_237580_5_ + 20) {
            flag = true;
        }
        this.minecraft.G_624_v().n_1700_B(M_588_G);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        float f = p_237580_6_ ? 20.0f : 0.0f;
        C_2701_A.blit(p_237580_1_, p_237580_4_, p_237580_5_, f, 0.0f, 20, 20, 40, 20);
        if (flag) {
            this.n_1700_B(z_1737_N);
        }
    }

    private void n_1700_B(g_221_o p_237582_1_, int p_237582_2_, int p_237582_3_, boolean p_237582_4_, int p_237582_5_, int p_237582_6_, boolean p_237582_7_, boolean p_237582_8_) {
        boolean flag = false;
        if (p_237582_2_ >= p_237582_5_ && p_237582_2_ <= p_237582_5_ + 20 && p_237582_3_ >= p_237582_6_ && p_237582_3_ <= p_237582_6_ + 20) {
            flag = true;
        }
        this.minecraft.G_624_v().n_1700_B(P_4830_p);
        if (p_237582_8_) {
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        } else {
            lightning.product.c_4037_x.G_564_y(0.5f, 0.5f, 0.5f, 1.0f);
        }
        boolean flag1 = p_237582_8_ && p_237582_7_;
        float f = flag1 ? 20.0f : 0.0f;
        C_2701_A.blit(p_237582_1_, p_237582_5_, p_237582_6_, f, 0.0f, 20, 20, 40, 20);
        if (flag && p_237582_8_) {
            this.n_1700_B(v_4276_D);
        }
        if (p_237582_4_ && p_237582_8_) {
            int i = flag ? 0 : (int)(Math.max(0.0f, Math.max(u_530_F.n_1700_B((float)(10 + this.A_1038_p) * 0.57f), u_530_F.J_1907_R((float)this.A_1038_p * 0.35f))) * -6.0f);
            this.minecraft.G_624_v().n_1700_B(v_4262_N);
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            C_2701_A.blit(p_237582_1_, p_237582_5_ + 10, p_237582_6_ + 2 + i, 40.0f, 0.0f, 8, 8, 48, 16);
        }
    }

    private void n_1700_B(g_221_o p_237604_1_) {
        String s = "LOCAL!";
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.R_4764_Y((float)(this.width / 2 - 25), 20.0f, 0.0f);
        lightning.product.c_4037_x.R_4764_Y(-20.0f, 0.0f, 0.0f, 1.0f);
        lightning.product.c_4037_x.J_1907_R(1.5f, 1.5f, 1.5f);
        this.font.J_1907_R(p_237604_1_, "LOCAL!", 0.0f, 0.0f, 0x7FFF7F);
        lightning.product.c_4037_x.d_2461_k();
    }

    private void J_1907_R(g_221_o p_237613_1_) {
        String s = "STAGE!";
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.R_4764_Y((float)(this.width / 2 - 25), 20.0f, 0.0f);
        lightning.product.c_4037_x.R_4764_Y(-20.0f, 0.0f, 0.0f, 1.0f);
        lightning.product.c_4037_x.J_1907_R(1.5f, 1.5f, 1.5f);
        this.font.J_1907_R(p_237613_1_, "STAGE!", 0.0f, 0.0f, -256);
        lightning.product.c_4037_x.d_2461_k();
    }

    public r_715_M G_564_y() {
        r_715_M realmsmainscreen = new r_715_M(this.H_1990_U);
        realmsmainscreen.init(this.minecraft, this.width, this.height);
        return realmsmainscreen;
    }

    public static void n_1700_B(ResourceManager p_227932_0_) {
        Collection<g_2336_b> collection = p_227932_0_.n_1700_B("textures/gui/images", (String p_227934_0_) -> p_227934_0_.endsWith(".png"));
        d_2461_k = (List)collection.stream().filter(p_227931_0_ -> p_227931_0_.R_4764_Y().equals("realms")).collect(ImmutableList.toImmutableList());
    }

    private void n_1700_B(x_282_a ... p_237603_1_) {
        this.z_1333_t = Arrays.asList(p_237603_1_);
    }

    private void n_1700_B(Button p_237598_1_) {
        this.minecraft.n_1700_B(new O_2151_c(this.H_1990_U));
    }

    static {
        q_4610_l = -1;
    }

    class v_4262_N
    extends z_3470_q<R_4764_Y> {
        private boolean J_1907_R;

        public v_4262_N() {
            super(r_715_M.this.width, r_715_M.this.height, 32, r_715_M.this.height - 40, 36);
        }

        @Override
        public void n_1700_B() {
            super.n_1700_B();
            this.J_1907_R = false;
        }

        @Override
        public int n_1700_B(R_4764_Y p_241825_1_) {
            this.J_1907_R = true;
            return this.n_1700_B(p_241825_1_);
        }

        @Override
        public boolean isFocused() {
            return r_715_M.this.getListener() == this;
        }

        @Override
        public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
            if (keyCode != 257 && keyCode != 32 && keyCode != 335) {
                return super.keyPressed(keyCode, scanCode, modifiers);
            }
            ObjectSelectionList.n_1700_B extendedlist$abstractlistentry = (ObjectSelectionList.n_1700_B)this.getSelected();
            return extendedlist$abstractlistentry == null ? super.keyPressed(keyCode, scanCode, modifiers) : extendedlist$abstractlistentry.mouseClicked(0.0, 0.0, 0);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (button == 0 && mouseX < (double)this.getScrollbarPosition() && mouseY >= (double)this.y0 && mouseY <= (double)this.y1) {
                int i = r_715_M.this.N_2525_X.getRowLeft();
                int j = this.getScrollbarPosition();
                int k = (int)Math.floor(mouseY - (double)this.y0) - this.headerHeight + (int)this.getScrollAmount() - 4;
                int l = k / this.itemHeight;
                if (mouseX >= (double)i && mouseX <= (double)j && l >= 0 && k >= 0 && l < this.getItemCount()) {
                    this.n_1700_B(k, l, mouseX, mouseY, this.width);
                    r_715_M.this.t_4219_U += 7;
                    this.n_1700_B(l);
                }
                return true;
            }
            return super.mouseClicked(mouseX, mouseY, button);
        }

        @Override
        public void n_1700_B(int p_231400_1_) {
            this.G_564_y(p_231400_1_);
            if (p_231400_1_ != -1) {
                q_1982_R realmsserver;
                if (this.J_1907_R) {
                    if (p_231400_1_ == 0) {
                        realmsserver = null;
                    } else {
                        if (p_231400_1_ - 1 >= r_715_M.this.O_508_d.size()) {
                            r_715_M.this.c_4037_x = -1L;
                            return;
                        }
                        realmsserver = r_715_M.this.O_508_d.get(p_231400_1_ - 1);
                    }
                } else {
                    if (p_231400_1_ >= r_715_M.this.O_508_d.size()) {
                        r_715_M.this.c_4037_x = -1L;
                        return;
                    }
                    realmsserver = r_715_M.this.O_508_d.get(p_231400_1_);
                }
                r_715_M.this.n_1700_B(realmsserver);
                if (realmsserver == null) {
                    r_715_M.this.c_4037_x = -1L;
                } else if (realmsserver.P_1922_E == q_1982_R.R_4764_Y.R_4764_Y) {
                    r_715_M.this.c_4037_x = -1L;
                } else {
                    r_715_M.this.c_4037_x = realmsserver.n_1700_B;
                    if (r_715_M.this.t_4219_U >= 10 && r_715_M.this.g_2268_R.active) {
                        r_715_M.this.n_1700_B(r_715_M.this.n_1700_B(r_715_M.this.c_4037_x), r_715_M.this);
                    }
                }
            }
        }

        public void J_1907_R(@Nullable R_4764_Y entry) {
            super.setSelected(entry);
            int i = this.getEventListeners().indexOf(entry);
            if (this.J_1907_R && i == 0) {
                NarrationHelper.n_1700_B(K_1289_S.n_1700_B("mco.trial.message.line1", new Object[0]), K_1289_S.n_1700_B("mco.trial.message.line2", new Object[0]));
            } else if (!this.J_1907_R || i > 0) {
                q_1982_R realmsserver = r_715_M.this.O_508_d.get(i - (this.J_1907_R ? 1 : 0));
                r_715_M.this.c_4037_x = realmsserver.n_1700_B;
                r_715_M.this.n_1700_B(realmsserver);
                if (realmsserver.P_1922_E == q_1982_R.R_4764_Y.R_4764_Y) {
                    NarrationHelper.n_1700_B(K_1289_S.n_1700_B("mco.selectServer.uninitialized", new Object[0]) + K_1289_S.n_1700_B("mco.gui.button", new Object[0]));
                } else {
                    NarrationHelper.n_1700_B(K_1289_S.n_1700_B("narrator.select", realmsserver.R_4764_Y));
                }
            }
        }

        @Override
        public void n_1700_B(int p_231401_1_, int p_231401_2_, double p_231401_3_, double p_231401_5_, int p_231401_7_) {
            q_1982_R realmsserver;
            if (this.J_1907_R) {
                if (p_231401_2_ == 0) {
                    r_715_M.this.Ping = true;
                    return;
                }
                --p_231401_2_;
            }
            if (p_231401_2_ < r_715_M.this.O_508_d.size() && (realmsserver = r_715_M.this.O_508_d.get(p_231401_2_)) != null) {
                if (realmsserver.P_1922_E == q_1982_R.R_4764_Y.R_4764_Y) {
                    r_715_M.this.c_4037_x = -1L;
                    MinecraftClient.A_4115_X().n_1700_B(new RealmsCreateRealmScreen(realmsserver, r_715_M.this));
                } else {
                    r_715_M.this.c_4037_x = realmsserver.n_1700_B;
                }
                if (r_715_M.this.V_1225_t == lightning.product.r_715_M$w_1484_f.G_564_y) {
                    r_715_M.this.c_4037_x = realmsserver.n_1700_B;
                    r_715_M.this.u_1723_Y(realmsserver);
                } else if (r_715_M.this.V_1225_t == lightning.product.r_715_M$w_1484_f.R_4764_Y) {
                    r_715_M.this.c_4037_x = realmsserver.n_1700_B;
                    r_715_M.this.v_4262_N(realmsserver);
                } else if (r_715_M.this.V_1225_t == lightning.product.r_715_M$w_1484_f.J_1907_R) {
                    r_715_M.this.u_2550_I();
                }
            }
        }

        @Override
        public int getMaxPosition() {
            return this.getItemCount() * 36;
        }

        @Override
        public int getRowWidth() {
            return 300;
        }

        @Override
        public /* synthetic */ void setSelected(@Nullable o_2488_o.n_1700_B n_1700_B2) {
            this.J_1907_R((R_4764_Y)n_1700_B2);
        }
    }

    class P_1922_E
    extends Button
    implements e_1813_Z {
        public P_1922_E() {
            super(r_715_M.this.width / 2 + 47, 6, 22, 22, U_2871_b.R_4764_Y, null);
        }

        @Override
        public void onPress() {
            r_715_M.this.n_1700_B(this);
        }

        @Override
        public void tick() {
            this.setMessage(new F_2904_S(r_715_M.this.r_715_M == 0 ? "mco.invites.nopending" : "mco.invites.pending"));
        }

        @Override
        public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
            r_715_M.this.n_1700_B(matrixStack, mouseX, mouseY, this.x, this.y, this.isHovered(), this.active);
        }
    }

    class G_564_y
    extends Button {
        public G_564_y() {
            super(r_715_M.this.width - 62, 6, 20, 20, U_2871_b.R_4764_Y, null);
            this.setMessage(new F_2904_S("mco.news"));
        }

        @Override
        public void onPress() {
            if (r_715_M.this.e_1992_r != null) {
                j_3341_s.t_148_a().n_1700_B(r_715_M.this.e_1992_r);
                if (r_715_M.this.UploadStatus) {
                    RealmsPersistence.n_1700_B realmspersistence$realmspersistencedata = RealmsPersistence.n_1700_B();
                    realmspersistence$realmspersistencedata.J_1907_R = false;
                    r_715_M.this.UploadStatus = false;
                    RealmsPersistence.n_1700_B(realmspersistence$realmspersistencedata);
                }
            }
        }

        @Override
        public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
            r_715_M.this.n_1700_B(matrixStack, mouseX, mouseY, r_715_M.this.UploadStatus, this.x, this.y, this.isHovered(), this.active);
        }
    }

    class J_1907_R
    extends Button {
        public J_1907_R() {
            super(r_715_M.this.width - 37, 6, 20, 20, new F_2904_S("mco.selectServer.info"), null);
        }

        @Override
        public void onPress() {
            r_715_M.this.Ping = !r_715_M.this.Ping;
        }

        @Override
        public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
            r_715_M.this.n_1700_B(matrixStack, mouseX, mouseY, this.x, this.y, this.isHovered());
        }
    }

    class n_1700_B
    extends Button {
        public n_1700_B() {
            super(r_715_M.this.Y_259_p() + 4, r_715_M.this.Q_2552_b() + 4, 12, 12, new F_2904_S("mco.selectServer.close"), null);
        }

        @Override
        public void onPress() {
            r_715_M.this.Y_601_j();
        }

        @Override
        public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
            r_715_M.this.minecraft.G_624_v().n_1700_B(M_182_A);
            lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            float f = this.isHovered() ? 12.0f : 0.0f;
            lightning.product.r_715_M$n_1700_B.blit(matrixStack, this.x, this.y, 0.0f, f, 12, 12, 12, 24);
            if (this.isMouseOver(mouseX, mouseY)) {
                r_715_M.this.n_1700_B(this.getMessage());
            }
        }
    }

    class t_148_a
    extends R_4764_Y {
        private t_148_a() {
            super(r_715_M.this);
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B(p_230432_1_, p_230432_2_, p_230432_4_, p_230432_3_, p_230432_7_, p_230432_8_);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            r_715_M.this.Ping = true;
            return true;
        }

        private void n_1700_B(g_221_o p_237681_1_, int p_237681_2_, int p_237681_3_, int p_237681_4_, int p_237681_5_, int p_237681_6_) {
            int i = p_237681_4_ + 8;
            int j = 0;
            boolean flag = false;
            if (p_237681_3_ <= p_237681_5_ && p_237681_5_ <= (int)r_715_M.this.N_2525_X.getScrollAmount() && p_237681_4_ <= p_237681_6_ && p_237681_6_ <= p_237681_4_ + 32) {
                flag = true;
            }
            int k = 0x7FFF7F;
            if (flag && !r_715_M.this.n_1700_B()) {
                k = 6077788;
            }
            for (x_282_a itextcomponent : Y_259_p) {
                C_2701_A.drawCenteredString(p_237681_1_, r_715_M.this.font, itextcomponent, r_715_M.this.width / 2, i + j, k);
                j += 10;
            }
        }
    }

    abstract class R_4764_Y
    extends ObjectSelectionList.n_1700_B<R_4764_Y> {
        private R_4764_Y(r_715_M this$0) {
        }
    }

    class u_1723_Y
    extends R_4764_Y {
        private final q_1982_R J_1907_R;

        public u_1723_Y(q_1982_R resourceManagerIn) {
            super(r_715_M.this);
            this.J_1907_R = resourceManagerIn;
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            this.n_1700_B(this.J_1907_R, p_230432_1_, p_230432_4_, p_230432_3_, p_230432_7_, p_230432_8_);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (this.J_1907_R.P_1922_E == q_1982_R.R_4764_Y.R_4764_Y) {
                r_715_M.this.c_4037_x = -1L;
                r_715_M.this.minecraft.n_1700_B(new RealmsCreateRealmScreen(this.J_1907_R, r_715_M.this));
            } else {
                r_715_M.this.c_4037_x = this.J_1907_R.n_1700_B;
            }
            return true;
        }

        private void n_1700_B(q_1982_R p_237678_1_, g_221_o p_237678_2_, int p_237678_3_, int p_237678_4_, int p_237678_5_, int p_237678_6_) {
            this.J_1907_R(p_237678_1_, p_237678_2_, p_237678_3_ + 36, p_237678_4_, p_237678_5_, p_237678_6_);
        }

        private void J_1907_R(q_1982_R p_237679_1_, g_221_o p_237679_2_, int p_237679_3_, int p_237679_4_, int p_237679_5_, int p_237679_6_) {
            if (p_237679_1_.P_1922_E == q_1982_R.R_4764_Y.R_4764_Y) {
                r_715_M.this.minecraft.G_624_v().n_1700_B(t_148_a);
                lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                lightning.product.c_4037_x.M_588_G();
                C_2701_A.blit(p_237679_2_, p_237679_3_ + 10, p_237679_4_ + 6, 0.0f, 0.0f, 40, 20, 40, 20);
                float f = 0.5f + (1.0f + u_530_F.n_1700_B((float)r_715_M.this.A_1038_p * 0.25f)) * 0.25f;
                int k2 = 0xFF000000 | (int)(127.0f * f) << 16 | (int)(255.0f * f) << 8 | (int)(127.0f * f);
                C_2701_A.drawCenteredString(p_237679_2_, r_715_M.this.font, Q_2552_b, p_237679_3_ + 10 + 40 + 75, p_237679_4_ + 12, k2);
            } else {
                int i = 225;
                int j = 2;
                if (p_237679_1_.s_956_w) {
                    r_715_M.this.n_1700_B(p_237679_2_, p_237679_3_ + 225 - 14, p_237679_4_ + 2, p_237679_5_, p_237679_6_);
                } else if (p_237679_1_.P_1922_E == q_1982_R.R_4764_Y.n_1700_B) {
                    r_715_M.this.R_4764_Y(p_237679_2_, p_237679_3_ + 225 - 14, p_237679_4_ + 2, p_237679_5_, p_237679_6_);
                } else if (r_715_M.this.t_148_a(p_237679_1_) && p_237679_1_.M_588_G < 7) {
                    r_715_M.this.n_1700_B(p_237679_2_, p_237679_3_ + 225 - 14, p_237679_4_ + 2, p_237679_5_, p_237679_6_, p_237679_1_.M_588_G);
                } else if (p_237679_1_.P_1922_E == q_1982_R.R_4764_Y.J_1907_R) {
                    r_715_M.this.J_1907_R(p_237679_2_, p_237679_3_ + 225 - 14, p_237679_4_ + 2, p_237679_5_, p_237679_6_);
                }
                if (!r_715_M.this.t_148_a(p_237679_1_) && !T_2506_i) {
                    r_715_M.this.G_564_y(p_237679_2_, p_237679_3_ + 225, p_237679_4_ + 2, p_237679_5_, p_237679_6_);
                } else {
                    r_715_M.this.P_1922_E(p_237679_2_, p_237679_3_ + 225, p_237679_4_ + 2, p_237679_5_, p_237679_6_);
                }
                if (!"0".equals(p_237679_1_.multiplayerClientSuggestionProvider.n_1700_B)) {
                    String s = String.valueOf((Object)D_4024_W.w_1484_f) + p_237679_1_.multiplayerClientSuggestionProvider.n_1700_B;
                    r_715_M.this.font.J_1907_R(p_237679_2_, s, (float)(p_237679_3_ + 207 - r_715_M.this.font.J_1907_R(s)), (float)(p_237679_4_ + 3), 0x808080);
                    if (p_237679_5_ >= p_237679_3_ + 207 - r_715_M.this.font.J_1907_R(s) && p_237679_5_ <= p_237679_3_ + 207 && p_237679_6_ >= p_237679_4_ + 1 && p_237679_6_ <= p_237679_4_ + 10 && p_237679_6_ < r_715_M.this.height - 40 && p_237679_6_ > 32 && !r_715_M.this.n_1700_B()) {
                        r_715_M.this.n_1700_B(new U_2871_b(p_237679_1_.multiplayerClientSuggestionProvider.J_1907_R));
                    }
                }
                if (r_715_M.this.t_148_a(p_237679_1_) && p_237679_1_.s_956_w) {
                    x_282_a itextcomponent1;
                    x_282_a itextcomponent;
                    lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                    lightning.product.c_4037_x.Y_601_j();
                    r_715_M.this.minecraft.G_624_v().n_1700_B(multiplayerClientSuggestionProvider);
                    lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
                    if (p_237679_1_.u_2550_I) {
                        itextcomponent = q_2307_F;
                        itextcomponent1 = Z_875_P;
                    } else {
                        itextcomponent = C_2741_M;
                        itextcomponent1 = k_2293_S;
                    }
                    int l = r_715_M.this.font.n_1700_B((FormattedText)itextcomponent1) + 17;
                    int i1 = 16;
                    int j1 = p_237679_3_ + r_715_M.this.font.n_1700_B((FormattedText)itextcomponent) + 8;
                    int k1 = p_237679_4_ + 13;
                    boolean flag = false;
                    if (p_237679_5_ >= j1 && p_237679_5_ < j1 + l && p_237679_6_ > k1 && p_237679_6_ <= k1 + 16 & p_237679_6_ < r_715_M.this.height - 40 && p_237679_6_ > 32 && !r_715_M.this.n_1700_B()) {
                        flag = true;
                        r_715_M.this.V_1225_t = lightning.product.r_715_M$w_1484_f.J_1907_R;
                    }
                    int l1 = flag ? 2 : 1;
                    C_2701_A.blit(p_237679_2_, j1, k1, 0.0f, 46 + l1 * 20, l / 2, 8, 256, 256);
                    C_2701_A.blit(p_237679_2_, j1 + l / 2, k1, 200 - l / 2, 46 + l1 * 20, l / 2, 8, 256, 256);
                    C_2701_A.blit(p_237679_2_, j1, k1 + 8, 0.0f, 46 + l1 * 20 + 12, l / 2, 8, 256, 256);
                    C_2701_A.blit(p_237679_2_, j1 + l / 2, k1 + 8, 200 - l / 2, 46 + l1 * 20 + 12, l / 2, 8, 256, 256);
                    lightning.product.c_4037_x.Y_259_p();
                    int i2 = p_237679_4_ + 11 + 5;
                    int j2 = flag ? 0xFFFFA0 : 0xFFFFFF;
                    r_715_M.this.font.J_1907_R(p_237679_2_, itextcomponent, (float)(p_237679_3_ + 2), (float)(i2 + 1), 15553363);
                    C_2701_A.drawCenteredString(p_237679_2_, r_715_M.this.font, itextcomponent1, j1 + l / 2, i2 + 1, j2);
                } else {
                    if (p_237679_1_.P_4830_p == q_1982_R.J_1907_R.J_1907_R) {
                        int l2 = 0xCCAC5C;
                        int k = r_715_M.this.font.n_1700_B((FormattedText)c_3005_b);
                        r_715_M.this.font.J_1907_R(p_237679_2_, c_3005_b, (float)(p_237679_3_ + 2), (float)(p_237679_4_ + 12), 0xCCAC5C);
                        r_715_M.this.font.J_1907_R(p_237679_2_, p_237679_1_.R_4764_Y(), (float)(p_237679_3_ + 2 + k), (float)(p_237679_4_ + 12), 0x6C6C6C);
                    } else {
                        r_715_M.this.font.J_1907_R(p_237679_2_, p_237679_1_.n_1700_B(), (float)(p_237679_3_ + 2), (float)(p_237679_4_ + 12), 0x6C6C6C);
                    }
                    if (!r_715_M.this.t_148_a(p_237679_1_)) {
                        r_715_M.this.font.J_1907_R(p_237679_2_, p_237679_1_.u_1723_Y, (float)(p_237679_3_ + 2), (float)(p_237679_4_ + 12 + 11), 0x4C4C4C);
                    }
                }
                r_715_M.this.font.J_1907_R(p_237679_2_, p_237679_1_.J_1907_R(), (float)(p_237679_3_ + 2), (float)(p_237679_4_ + 1), 0xFFFFFF);
                y_2772_m.n_1700_B(p_237679_1_.v_4262_N, () -> {
                    lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                    C_2701_A.blit(p_237679_2_, p_237679_3_ - 36, p_237679_4_, 32, 32, 8.0f, 8.0f, 8, 8, 64, 64);
                    C_2701_A.blit(p_237679_2_, p_237679_3_ - 36, p_237679_4_, 32, 32, 40.0f, 8.0f, 8, 8, 64, 64);
                });
            }
        }
    }

    static final class w_1484_f
    extends Enum<w_1484_f> {
        public static final /* enum */ w_1484_f n_1700_B = new w_1484_f();
        public static final /* enum */ w_1484_f J_1907_R = new w_1484_f();
        public static final /* enum */ w_1484_f R_4764_Y = new w_1484_f();
        public static final /* enum */ w_1484_f G_564_y = new w_1484_f();
        private static final /* synthetic */ w_1484_f[] P_1922_E;

        public static w_1484_f[] values() {
            return (w_1484_f[])P_1922_E.clone();
        }

        public static w_1484_f valueOf(String name) {
            return Enum.valueOf(w_1484_f.class, name);
        }

        private static /* synthetic */ w_1484_f[] n_1700_B() {
            return new w_1484_f[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.r_715_M$w_1484_f.n_1700_B();
        }
    }
}



