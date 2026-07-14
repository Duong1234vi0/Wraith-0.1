/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.GameProfileRepository
 *  com.mojang.authlib.minecraft.MinecraftSessionService
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BooleanSupplier;
import lightning.product.B_4088_l;
import lightning.product.E_2727_F;
import lightning.product.PackRepository;
import lightning.product.I_14_v;
import lightning.product.SharedConstants;
import lightning.product.DifficultyInstance;
import lightning.product.ChunkProgressListenerFactory;
import lightning.product.W_1689_V;
import lightning.product.ProfilerFiller;
import lightning.product.ServerResources;
import lightning.product.b_2971_z;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_196_S;
import lightning.product.e_3591_l;
import lightning.product.IntegratedPlayerList;
import lightning.product.WorldData;
import lightning.product.n_3236_c;
import lightning.product.p_752_J;
import lightning.product.r_4097_j;
import net.minecraft.server.G_564_y;
import net.optifine.Config;
import net.optifine.reflect.Reflector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class R_3197_Z
extends G_564_y {
    private static final Logger M_588_G = LogManager.getLogger();
    private final MinecraftClient P_4830_p;
    private boolean h_1847_R;
    private int Q_4569_t = -1;
    private c_196_S M_182_A;
    private UUID t_1786_h;
    private long multiplayerClientSuggestionProvider = 0L;
    public b_4507_u t_148_a = null;
    public c_1514_x s_956_w = null;
    public DifficultyInstance u_2550_I = null;

    public R_3197_Z(Thread p_i232494_1_, MinecraftClient p_i232494_2_, r_4097_j.J_1907_R p_i232494_3_, b_2971_z.n_1700_B p_i232494_4_, PackRepository p_i232494_5_, ServerResources p_i232494_6_, WorldData p_i232494_7_, MinecraftSessionService p_i232494_8_, GameProfileRepository p_i232494_9_, W_1689_V p_i232494_10_, ChunkProgressListenerFactory p_i232494_11_) {
        super(p_i232494_1_, p_i232494_3_, p_i232494_4_, p_i232494_7_, p_i232494_5_, p_i232494_2_.d_2461_k(), p_i232494_2_.p_178_J(), p_i232494_6_, p_i232494_8_, p_i232494_9_, p_i232494_10_, p_i232494_11_);
        this.P_1922_E(p_i232494_2_.z_1737_N().R_4764_Y());
        this.R_4764_Y(p_i232494_2_.C_2741_M());
        this.R_4764_Y(256);
        this.n_1700_B(new IntegratedPlayerList(this, this.P_1922_E, this.G_564_y));
        this.P_4830_p = p_i232494_2_;
    }

    @Override
    public boolean u_2550_I() {
        M_588_G.info("Starting integrated minecraft server version " + SharedConstants.n_1700_B().getName());
        this.G_564_y(true);
        this.u_1723_Y(true);
        this.v_4262_N(true);
        this.q_4610_l();
        if (Reflector.ServerLifecycleHooks_handleServerAboutToStart.exists() && !Reflector.callBoolean(Reflector.ServerLifecycleHooks_handleServerAboutToStart, this)) {
            return false;
        }
        this.M_588_G();
        this.u_1723_Y(this.G_624_v() + " - " + this.c_132_F().P_4830_p());
        return Reflector.ServerLifecycleHooks_handleServerStarting.exists() ? Reflector.callBoolean(Reflector.ServerLifecycleHooks_handleServerStarting, this) : true;
    }

    @Override
    public void n_1700_B(BooleanSupplier hasTimeLeft) {
        this.J_1907_R();
        boolean flag = this.h_1847_R;
        this.h_1847_R = MinecraftClient.A_4115_X().k_2293_S() != null && MinecraftClient.A_4115_X().g_164_R();
        ProfilerFiller iprofiler = this.LongRunningTask();
        if (!flag && this.h_1847_R) {
            iprofiler.n_1700_B("autoSave");
            M_588_G.info("Saving and pausing game...");
            this.p_178_J().t_148_a();
            this.n_1700_B(false, false, false);
            iprofiler.R_4764_Y();
        }
        if (!this.h_1847_R) {
            super.n_1700_B(hasTimeLeft);
            int i = Math.max(2, this.P_4830_p.P_4830_p.J_1907_R + -1);
            if (i != this.p_178_J().t_1786_h()) {
                M_588_G.info("Changing view distance to {}, from {}", (Object)i, (Object)this.p_178_J().t_1786_h());
                this.p_178_J().n_1700_B(i);
            }
        }
    }

    @Override
    public boolean w_1457_N() {
        return true;
    }

    @Override
    public boolean A_1038_p() {
        return true;
    }

    @Override
    public File H_2857_Y() {
        return this.P_4830_p.M_182_A;
    }

    @Override
    public boolean g_164_R() {
        return false;
    }

    @Override
    public int X_933_l() {
        return 0;
    }

    @Override
    public boolean g_2268_R() {
        return false;
    }

    @Override
    public void n_1700_B(n_3236_c report) {
        this.P_4830_p.n_1700_B(report);
    }

    @Override
    public n_3236_c J_1907_R(n_3236_c report) {
        report = super.J_1907_R(report);
        report.u_1723_Y().n_1700_B("Type", "Integrated Server (map_client.txt)");
        report.u_1723_Y().n_1700_B("Is Modded", () -> this.z_1737_N().orElse("Probably not. Jar signature remains and both client + server brands are untouched."));
        return report;
    }

    @Override
    public Optional<String> z_1737_N() {
        String s = p_752_J.n_1700_B();
        if (!s.equals("vanilla")) {
            return Optional.of("Definitely; Client brand changed to '" + s + "'");
        }
        s = this.d_2427_y();
        if (!"vanilla".equals(s)) {
            return Optional.of("Definitely; Server brand changed to '" + s + "'");
        }
        return MinecraftClient.class.getSigners() == null ? Optional.of("Very likely; Jar signature invalidated") : Optional.empty();
    }

    @Override
    public void n_1700_B(E_2727_F snooper) {
        super.n_1700_B(snooper);
        snooper.n_1700_B("snooper_partner", this.P_4830_p.d_2427_y().P_1922_E());
    }

    @Override
    public boolean n_1700_B(I_14_v gameMode, boolean cheats, int port) {
        try {
            this.f_4016_n().n_1700_B(null, port);
            M_588_G.info("Started serving on {}", (Object)port);
            this.Q_4569_t = port;
            this.M_182_A = new c_196_S(this.z_1333_t(), "" + port);
            this.M_182_A.start();
            this.p_178_J().n_1700_B(gameMode);
            this.p_178_J().J_1907_R(cheats);
            int i = this.n_1700_B(this.P_4830_p.Y_259_p.y_4642_Y());
            this.P_4830_p.Y_259_p.n_1700_B(i);
            for (B_4088_l serverplayerentity : this.p_178_J().w_1457_N()) {
                this.H_1083_k().n_1700_B(serverplayerentity);
            }
            return true;
        }
        catch (IOException ioexception1) {
            return false;
        }
    }

    @Override
    public void Y_601_j() {
        super.Y_601_j();
        if (this.M_182_A != null) {
            this.M_182_A.interrupt();
            this.M_182_A = null;
        }
    }

    @Override
    public void n_1700_B(boolean waitForServer) {
        if (!Reflector.MinecraftForge.exists() || this.Q_2552_b()) {
            this.v_4262_N(() -> {
                for (B_4088_l serverplayerentity : Lists.newArrayList(this.p_178_J().w_1457_N())) {
                    if (serverplayerentity.w_2705_t().equals(this.t_1786_h)) continue;
                    this.p_178_J().R_4764_Y(serverplayerentity);
                }
            });
        }
        super.n_1700_B(waitForServer);
        if (this.M_182_A != null) {
            this.M_182_A.interrupt();
            this.M_182_A = null;
        }
    }

    @Override
    public boolean RealmsClientConfig() {
        return this.Q_4569_t > -1;
    }

    @Override
    public int d_2461_k() {
        return this.Q_4569_t;
    }

    @Override
    public void n_1700_B(I_14_v gameMode) {
        super.n_1700_B(gameMode);
        this.p_178_J().n_1700_B(gameMode);
    }

    @Override
    public boolean s_2632_s() {
        return true;
    }

    @Override
    public int t_1786_h() {
        return 2;
    }

    @Override
    public int multiplayerClientSuggestionProvider() {
        return 2;
    }

    @Override
    public void n_1700_B(UUID uuid) {
        this.t_1786_h = uuid;
    }

    @Override
    public boolean J_1907_R(GameProfile profileIn) {
        return profileIn.getName().equalsIgnoreCase(this.G_624_v());
    }

    @Override
    public int J_1907_R(int p_230512_1_) {
        return (int)(this.P_4830_p.P_4830_p.R_4764_Y * (float)p_230512_1_);
    }

    @Override
    public boolean RealmsScreenWithCallback() {
        return this.P_4830_p.P_4830_p.RealmsCreateRealmScreen;
    }

    private void J_1907_R() {
        for (e_3591_l serverworld : this.n_3318_d()) {
            this.J_1907_R(serverworld);
        }
    }

    private void J_1907_R(e_3591_l p_onTick_1_) {
        if (!Config.isTimeDefault()) {
            this.G_564_y(p_onTick_1_);
        }
        if (!Config.isWeatherEnabled()) {
            this.R_4764_Y(p_onTick_1_);
        }
        if (this.t_148_a == p_onTick_1_ && this.s_956_w != null) {
            this.u_2550_I = p_onTick_1_.J_1907_R(this.s_956_w);
            this.t_148_a = null;
            this.s_956_w = null;
        }
    }

    public DifficultyInstance n_1700_B(b_4507_u p_getDifficultyAsync_1_, c_1514_x p_getDifficultyAsync_2_) {
        this.t_148_a = p_getDifficultyAsync_1_;
        this.s_956_w = p_getDifficultyAsync_2_;
        return this.u_2550_I;
    }

    private void R_4764_Y(e_3591_l p_fixWorldWeather_1_) {
        if (p_fixWorldWeather_1_.w_1484_f(1.0f) > 0.0f || p_fixWorldWeather_1_.N_2525_X()) {
            p_fixWorldWeather_1_.n_1700_B(6000, 0, false, false);
        }
    }

    private void G_564_y(e_3591_l p_fixWorldTime_1_) {
        if (this.Q_4569_t() == I_14_v.R_4764_Y) {
            long i = p_fixWorldTime_1_.Z_976_R();
            long j = i % 24000L;
            if (Config.isTimeDayOnly()) {
                if (j <= 1000L) {
                    p_fixWorldTime_1_.n_1700_B(i - j + 1001L);
                }
                if (j >= 11000L) {
                    p_fixWorldTime_1_.n_1700_B(i - j + 24001L);
                }
            }
            if (Config.isTimeNightOnly()) {
                if (j <= 14000L) {
                    p_fixWorldTime_1_.n_1700_B(i - j + 14001L);
                }
                if (j >= 22000L) {
                    p_fixWorldTime_1_.n_1700_B(i - j + 24000L + 14001L);
                }
            }
        }
    }

    @Override
    public boolean n_1700_B(boolean suppressLog, boolean flush, boolean forced) {
        if (suppressLog) {
            int j;
            int i = this.e_1992_r();
            if ((long)i < this.multiplayerClientSuggestionProvider + (long)(j = this.P_4830_p.P_4830_p.D_4361_a)) {
                return false;
            }
            this.multiplayerClientSuggestionProvider = i;
        }
        return super.n_1700_B(suppressLog, flush, forced);
    }
}



