/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.collect.Lists
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.GameProfileRepository
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  com.mojang.datafixers.DataFixer
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.datafixers.DataFixer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.E_2727_F;
import lightning.product.PackRepository;
import lightning.product.G_4455_Z;
import lightning.product.I_14_v;
import lightning.product.SharedConstants;
import lightning.product.K_2202_x;
import lightning.product.M_1608_O;
import lightning.product.O_2639_P;
import lightning.product.DedicatedServerProperties;
import lightning.product.ChunkProgressListenerFactory;
import lightning.product.NonNullList;
import lightning.product.S_1134_u;
import lightning.product.S_1227_I;
import lightning.product.W_1689_V;
import lightning.product.Z_2505_m;
import lightning.product.ServerResources;
import lightning.product.a_3913_L;
import lightning.product.b_2971_z;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.c_1690_M;
import lightning.product.DedicatedPlayerList;
import lightning.product.d_2511_z;
import lightning.product.TextFilter;
import lightning.product.e_3591_l;
import lightning.product.g_1995_W;
import lightning.product.g_4820_x;
import lightning.product.RconThread;
import lightning.product.j_3341_s;
import lightning.product.WorldData;
import lightning.product.DefaultUncaughtExceptionHandlerWithName;
import lightning.product.n_3236_c;
import lightning.product.Items;
import lightning.product.DefaultUncaughtExceptionHandler;
import lightning.product.r_4097_j;
import lightning.product.u_530_F;
import lightning.product.ServerInterface;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;
import net.minecraft.server.u_1723_Y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class V_4604_M
extends G_564_y
implements ServerInterface {
    private static final Logger t_148_a = LogManager.getLogger();
    private static final Pattern s_956_w = Pattern.compile("^[a-fA-F0-9]{40}$");
    private final List<c_1690_M> u_2550_I = Collections.synchronizedList(Lists.newArrayList());
    private M_1608_O M_588_G;
    private final K_2202_x P_4830_p;
    private RconThread h_1847_R;
    private final u_1723_Y Q_4569_t;
    @Nullable
    private G_4455_Z M_182_A;
    @Nullable
    private final g_4820_x t_1786_h;

    public V_4604_M(Thread p_i232601_1_, r_4097_j.J_1907_R p_i232601_2_, b_2971_z.n_1700_B p_i232601_3_, PackRepository p_i232601_4_, ServerResources p_i232601_5_, WorldData p_i232601_6_, u_1723_Y p_i232601_7_, DataFixer p_i232601_8_, MinecraftSessionService p_i232601_9_, GameProfileRepository p_i232601_10_, W_1689_V p_i232601_11_, ChunkProgressListenerFactory p_i232601_12_) {
        super(p_i232601_1_, p_i232601_2_, p_i232601_3_, p_i232601_6_, p_i232601_4_, Proxy.NO_PROXY, p_i232601_8_, p_i232601_5_, p_i232601_9_, p_i232601_10_, p_i232601_11_, p_i232601_12_);
        this.Q_4569_t = p_i232601_7_;
        this.P_4830_p = new K_2202_x(this);
        this.t_1786_h = null;
    }

    @Override
    public boolean u_2550_I() throws IOException {
        Thread thread = new Thread("Server console handler"){

            @Override
            public void run() {
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
                try {
                    String s1;
                    while (!V_4604_M.this.Ping() && V_4604_M.this.Q_2552_b() && (s1 = bufferedreader.readLine()) != null) {
                        V_4604_M.this.n_1700_B(s1, V_4604_M.this.R_3908_n());
                    }
                }
                catch (IOException ioexception1) {
                    t_148_a.error("Exception handling console input", (Throwable)ioexception1);
                }
            }
        };
        thread.setDaemon(true);
        thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(t_148_a));
        thread.start();
        t_148_a.info("Starting minecraft server version " + SharedConstants.n_1700_B().getName());
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            t_148_a.warn("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }
        t_148_a.info("Loading properties");
        DedicatedServerProperties serverproperties = this.Q_4569_t.n_1700_B();
        if (this.T_2506_i()) {
            this.J_1907_R("127.0.0.1");
        } else {
            this.G_564_y(serverproperties.n_1700_B);
            this.P_1922_E(serverproperties.J_1907_R);
            this.J_1907_R(serverproperties.R_4764_Y);
        }
        this.u_1723_Y(serverproperties.u_1723_Y);
        this.v_4262_N(serverproperties.v_4262_N);
        this.n_1700_B(serverproperties.w_1484_f, this.RealmsClientOutdatedScreen());
        this.u_1723_Y(serverproperties.t_148_a);
        this.w_1484_f(serverproperties.s_956_w);
        super.G_564_y(serverproperties.X_933_l.get());
        this.t_148_a(serverproperties.u_2550_I);
        this.w_1484_f.n_1700_B(serverproperties.P_4830_p);
        t_148_a.info("Default game type: {}", (Object)serverproperties.P_4830_p);
        InetAddress inetaddress = null;
        if (!this.Y_259_p().isEmpty()) {
            inetaddress = InetAddress.getByName(this.Y_259_p());
        }
        if (this.d_2461_k() < 0) {
            this.n_1700_B(serverproperties.Q_4569_t);
        }
        this.q_4610_l();
        t_148_a.info("Starting Minecraft server on {}:{}", (Object)(this.Y_259_p().isEmpty() ? "*" : this.Y_259_p()), (Object)this.d_2461_k());
        try {
            this.f_4016_n().n_1700_B(inetaddress, this.d_2461_k());
        }
        catch (IOException ioexception) {
            t_148_a.warn("**** FAILED TO BIND TO PORT!");
            t_148_a.warn("The exception was: {}", (Object)ioexception.toString());
            t_148_a.warn("Perhaps a server is already running on that port?");
            return false;
        }
        if (!this.Z_976_R()) {
            t_148_a.warn("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
            t_148_a.warn("The server will make no attempt to authenticate usernames. Beware.");
            t_148_a.warn("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
            t_148_a.warn("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
        }
        if (this.C_290_v()) {
            this.V_1225_t().J_1907_R();
        }
        if (!d_2511_z.P_1922_E(this)) {
            return false;
        }
        this.n_1700_B(new DedicatedPlayerList(this, this.P_1922_E, this.G_564_y));
        long i = j_3341_s.R_4764_Y();
        this.R_4764_Y(serverproperties.M_182_A);
        O_2639_P.n_1700_B(this.V_1225_t());
        O_2639_P.n_1700_B(this.V_1446_Y());
        W_1689_V.n_1700_B(this.Z_976_R());
        t_148_a.info("Preparing level \"{}\"", (Object)this.t_148_a());
        this.M_588_G();
        long j = j_3341_s.R_4764_Y() - i;
        String s = String.format(Locale.ROOT, "%.3fs", (double)j / 1.0E9);
        t_148_a.info("Done ({})! For help, type \"help\"", (Object)s);
        if (serverproperties.t_1786_h != null) {
            this.y_1700_S().n_1700_B(A_2352_Z.C_2741_M).n_1700_B(serverproperties.t_1786_h, (G_564_y)this);
        }
        if (serverproperties.multiplayerClientSuggestionProvider) {
            t_148_a.info("Starting GS4 status listener");
            this.M_588_G = M_1608_O.n_1700_B(this);
        }
        if (serverproperties.Y_601_j) {
            t_148_a.info("Starting remote control listener");
            this.h_1847_R = RconThread.n_1700_B(this);
        }
        if (this.w_728_N() > 0L) {
            Thread thread1 = new Thread(new S_1227_I(this));
            thread1.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandlerWithName(t_148_a));
            thread1.setName("Server Watchdog");
            thread1.setDaemon(true);
            thread1.start();
        }
        Items.n_1700_B.n_1700_B(S_1134_u.v_4262_N, NonNullList.n_1700_B());
        if (serverproperties.g_221_o) {
            Z_2505_m.n_1700_B(this);
        }
        return true;
    }

    @Override
    public boolean N_2525_X() {
        return this.n_1700_B().G_564_y && super.N_2525_X();
    }

    @Override
    public boolean z_4693_k() {
        return this.Q_4569_t.n_1700_B().c_3005_b && super.z_4693_k();
    }

    @Override
    public boolean c_4037_x() {
        return this.Q_4569_t.n_1700_B().P_1922_E && super.c_4037_x();
    }

    public String RealmsClientOutdatedScreen() {
        String s;
        DedicatedServerProperties serverproperties = this.Q_4569_t.n_1700_B();
        if (!serverproperties.k_2293_S.isEmpty()) {
            s = serverproperties.k_2293_S;
            if (!Strings.isNullOrEmpty((String)serverproperties.C_2741_M)) {
                t_148_a.warn("resource-pack-hash is deprecated and found along side resource-pack-sha1. resource-pack-hash will be ignored.");
            }
        } else if (!Strings.isNullOrEmpty((String)serverproperties.C_2741_M)) {
            t_148_a.warn("resource-pack-hash is deprecated. Please use resource-pack-sha1 instead.");
            s = serverproperties.C_2741_M;
        } else {
            s = "";
        }
        if (!s.isEmpty() && !s_956_w.matcher(s).matches()) {
            t_148_a.warn("Invalid sha1 for ressource-pack-sha1");
        }
        if (!serverproperties.w_1484_f.isEmpty() && s.isEmpty()) {
            t_148_a.warn("You specified a resource pack without providing a sha1 hash. Pack will be updated on the client only if you change the name of the pack.");
        }
        return s;
    }

    @Override
    public DedicatedServerProperties n_1700_B() {
        return this.Q_4569_t.n_1700_B();
    }

    @Override
    public void P_4830_p() {
        this.n_1700_B(this.n_1700_B().M_588_G, true);
    }

    @Override
    public boolean M_182_A() {
        return this.n_1700_B().q_2307_F;
    }

    @Override
    public n_3236_c J_1907_R(n_3236_c report) {
        report = super.J_1907_R(report);
        report.u_1723_Y().n_1700_B("Is Modded", () -> this.z_1737_N().orElse("Unknown (can't tell)"));
        report.u_1723_Y().n_1700_B("Type", () -> "Dedicated Server (map_server.txt)");
        return report;
    }

    @Override
    public Optional<String> z_1737_N() {
        String s = this.d_2427_y();
        return !"vanilla".equals(s) ? Optional.of("Definitely; Server brand changed to '" + s + "'") : Optional.empty();
    }

    @Override
    public void A_4115_X() {
        if (this.t_1786_h != null) {
            this.t_1786_h.close();
        }
        if (this.M_182_A != null) {
            this.M_182_A.J_1907_R();
        }
        if (this.h_1847_R != null) {
            this.h_1847_R.n_1700_B();
        }
        if (this.M_588_G != null) {
            this.M_588_G.n_1700_B();
        }
    }

    @Override
    public void J_1907_R(BooleanSupplier hasTimeLeft) {
        super.J_1907_R(hasTimeLeft);
        this.W_3464_O();
    }

    @Override
    public boolean Y_1740_V() {
        return this.n_1700_B().Z_875_P;
    }

    @Override
    public void n_1700_B(E_2727_F snooper) {
        snooper.n_1700_B("whitelist_enabled", this.RealmsConfirmScreen().M_182_A());
        snooper.n_1700_B("whitelist_count", this.RealmsConfirmScreen().u_2550_I().length);
        super.n_1700_B(snooper);
    }

    public void n_1700_B(String p_195581_1_, y_2498_m p_195581_2_) {
        this.u_2550_I.add(new c_1690_M(p_195581_1_, p_195581_2_));
    }

    public void W_3464_O() {
        while (!this.u_2550_I.isEmpty()) {
            c_1690_M pendingcommand = this.u_2550_I.remove(0);
            this.H_1083_k().n_1700_B(pendingcommand.J_1907_R, pendingcommand.n_1700_B);
        }
    }

    @Override
    public boolean g_164_R() {
        return true;
    }

    @Override
    public int X_933_l() {
        return this.n_1700_B().d_2427_y;
    }

    @Override
    public boolean g_2268_R() {
        return this.n_1700_B().A_4115_X;
    }

    public DedicatedPlayerList RealmsConfirmScreen() {
        return (DedicatedPlayerList)super.p_178_J();
    }

    @Override
    public boolean RealmsClientConfig() {
        return true;
    }

    @Override
    public String J_1907_R() {
        return this.Y_259_p();
    }

    @Override
    public int R_4764_Y() {
        return this.d_2461_k();
    }

    @Override
    public String G_564_y() {
        return this.z_1333_t();
    }

    public void RealmsCreateRealmScreen() {
        if (this.M_182_A == null) {
            this.M_182_A = G_4455_Z.n_1700_B(this);
        }
    }

    @Override
    public boolean UploadStatus() {
        return this.M_182_A != null;
    }

    @Override
    public boolean n_1700_B(I_14_v gameMode, boolean cheats, int port) {
        return false;
    }

    @Override
    public boolean s_2632_s() {
        return this.n_1700_B().Y_1740_V;
    }

    @Override
    public int k_3961_g() {
        return this.n_1700_B().t_4043_B;
    }

    @Override
    public boolean n_1700_B(e_3591_l worldIn, c_1514_x pos, a_3913_L playerIn) {
        int j;
        if (worldIn.g_2268_R() != b_4507_u.u_1723_Y) {
            return false;
        }
        if (this.RealmsConfirmScreen().M_588_G().R_4764_Y()) {
            return false;
        }
        if (this.RealmsConfirmScreen().u_1723_Y(playerIn.y_4642_Y())) {
            return false;
        }
        if (this.k_3961_g() <= 0) {
            return false;
        }
        c_1514_x blockpos = worldIn.A_1038_p();
        int i = u_530_F.n_1700_B(pos.getX() - blockpos.getX());
        int k = Math.max(i, j = u_530_F.n_1700_B(pos.getZ() - blockpos.getZ()));
        return k <= this.k_3961_g();
    }

    @Override
    public boolean h_4320_q() {
        return this.n_1700_B().e_2887_G;
    }

    @Override
    public int t_1786_h() {
        return this.n_1700_B().x_607_J;
    }

    @Override
    public int multiplayerClientSuggestionProvider() {
        return this.n_1700_B().e_4240_b;
    }

    @Override
    public void G_564_y(int idleTimeout) {
        super.G_564_y(idleTimeout);
        this.Q_4569_t.n_1700_B(p_213224_2_ -> (DedicatedServerProperties)p_213224_2_.X_933_l.n_1700_B(this.g_4106_L(), idleTimeout));
    }

    @Override
    public boolean w_1457_N() {
        return this.n_1700_B().G_624_v;
    }

    @Override
    public boolean A_1038_p() {
        return this.n_1700_B().T_2506_i;
    }

    @Override
    public int dtoRealmsServerAddress() {
        return this.n_1700_B().q_4610_l;
    }

    @Override
    public int RealmsServerPing() {
        return this.n_1700_B().d_2461_k;
    }

    protected boolean C_290_v() {
        boolean flag = false;
        for (int i = 0; !flag && i <= 2; ++i) {
            if (i > 0) {
                t_148_a.warn("Encountered a problem while converting the user banlist, retrying in a few seconds");
                this.O_2151_c();
            }
            flag = d_2511_z.n_1700_B((G_564_y)this);
        }
        boolean flag1 = false;
        for (int j = 0; !flag1 && j <= 2; ++j) {
            if (j > 0) {
                t_148_a.warn("Encountered a problem while converting the ip banlist, retrying in a few seconds");
                this.O_2151_c();
            }
            flag1 = d_2511_z.J_1907_R(this);
        }
        boolean flag2 = false;
        for (int k = 0; !flag2 && k <= 2; ++k) {
            if (k > 0) {
                t_148_a.warn("Encountered a problem while converting the op list, retrying in a few seconds");
                this.O_2151_c();
            }
            flag2 = d_2511_z.R_4764_Y(this);
        }
        boolean flag3 = false;
        for (int l = 0; !flag3 && l <= 2; ++l) {
            if (l > 0) {
                t_148_a.warn("Encountered a problem while converting the whitelist, retrying in a few seconds");
                this.O_2151_c();
            }
            flag3 = d_2511_z.G_564_y(this);
        }
        boolean flag4 = false;
        for (int i1 = 0; !flag4 && i1 <= 2; ++i1) {
            if (i1 > 0) {
                t_148_a.warn("Encountered a problem while converting the player save files, retrying in a few seconds");
                this.O_2151_c();
            }
            flag4 = d_2511_z.n_1700_B(this);
        }
        return flag || flag1 || flag2 || flag3 || flag4;
    }

    private void O_2151_c() {
        try {
            Thread.sleep(5000L);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    public long w_728_N() {
        return this.n_1700_B().n_3318_d;
    }

    @Override
    public String s_956_w() {
        return "";
    }

    @Override
    public String n_1700_B(String command) {
        this.P_4830_p.n_1700_B();
        this.v_4262_N(() -> this.H_1083_k().n_1700_B(this.P_4830_p.R_4764_Y(), command));
        return this.P_4830_p.J_1907_R();
    }

    public void s_956_w(boolean p_213223_1_) {
        this.Q_4569_t.n_1700_B(p_213222_2_ -> (DedicatedServerProperties)p_213222_2_.Z_976_R.n_1700_B(this.g_4106_L(), p_213223_1_));
    }

    @Override
    public void Y_601_j() {
        super.Y_601_j();
        j_3341_s.w_1484_f();
    }

    @Override
    public boolean J_1907_R(GameProfile profileIn) {
        return false;
    }

    @Override
    public int J_1907_R(int p_230512_1_) {
        return this.n_1700_B().B_1668_F * p_230512_1_ / 100;
    }

    @Override
    public String t_148_a() {
        return this.R_4764_Y.n_1700_B();
    }

    @Override
    public boolean RealmsScreenWithCallback() {
        return this.Q_4569_t.n_1700_B().z_4693_k;
    }

    @Override
    @Nullable
    public TextFilter n_1700_B(B_4088_l p_244435_1_) {
        return this.t_1786_h != null ? this.t_1786_h.n_1700_B(p_244435_1_.y_4642_Y()) : null;
    }

    @Override
    public /* synthetic */ g_1995_W p_178_J() {
        return this.RealmsConfirmScreen();
    }
}


