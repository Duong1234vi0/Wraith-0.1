/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import lightning.product.I_14_v;
import lightning.product.R_2450_T;
import lightning.product.c_612_s;
import lightning.product.j_419_j;
import lightning.product.r_4097_j;
import lightning.product.u_530_F;

public class DedicatedServerProperties
extends c_612_s<DedicatedServerProperties> {
    public final boolean n_1700_B = this.n_1700_B("online-mode", true);
    public final boolean J_1907_R = this.n_1700_B("prevent-proxy-connections", false);
    public final String R_4764_Y = this.n_1700_B("server-ip", "");
    public final boolean G_564_y = this.n_1700_B("spawn-animals", true);
    public final boolean P_1922_E = this.n_1700_B("spawn-npcs", true);
    public final boolean u_1723_Y = this.n_1700_B("pvp", true);
    public final boolean v_4262_N = this.n_1700_B("allow-flight", false);
    public final String w_1484_f = this.n_1700_B("resource-pack", "");
    public final String t_148_a = this.n_1700_B("motd", "A Minecraft Server");
    public final boolean s_956_w = this.n_1700_B("force-gamemode", false);
    public final boolean u_2550_I = this.n_1700_B("enforce-whitelist", false);
    public final R_2450_T M_588_G = this.n_1700_B("difficulty", DedicatedServerProperties.n_1700_B(R_2450_T::n_1700_B, R_2450_T::n_1700_B), R_2450_T::R_4764_Y, R_2450_T.J_1907_R);
    public final I_14_v P_4830_p = this.n_1700_B("gamemode", DedicatedServerProperties.n_1700_B(I_14_v::n_1700_B, I_14_v::n_1700_B), I_14_v::J_1907_R, I_14_v.J_1907_R);
    public final String h_1847_R = this.n_1700_B("level-name", "world");
    public final int Q_4569_t = this.n_1700_B("server-port", 25565);
    public final int M_182_A = this.n_1700_B("max-build-height", p_218987_0_ -> u_530_F.n_1700_B((p_218987_0_ + 8) / 16 * 16, 64, 256), 256);
    public final Boolean t_1786_h = this.J_1907_R("announce-player-achievements");
    public final boolean multiplayerClientSuggestionProvider = this.n_1700_B("enable-query", false);
    public final int w_1457_N = this.n_1700_B("query.port", 25565);
    public final boolean Y_601_j = this.n_1700_B("enable-rcon", false);
    public final int Y_259_p = this.n_1700_B("rcon.port", 25575);
    public final String Q_2552_b = this.n_1700_B("rcon.password", "");
    public final String C_2741_M = this.n_1700_B("resource-pack-hash");
    public final String k_2293_S = this.n_1700_B("resource-pack-sha1", "");
    public final boolean q_2307_F = this.n_1700_B("hardcore", false);
    public final boolean Z_875_P = this.n_1700_B("allow-nether", true);
    public final boolean c_3005_b = this.n_1700_B("spawn-monsters", true);
    public final boolean H_2857_Y;
    public final boolean A_4115_X;
    public final boolean Y_1740_V;
    public final int t_4043_B;
    public final int x_607_J;
    public final int e_4240_b;
    public final long n_3318_d;
    public final int d_2427_y;
    public final int z_1737_N;
    public final int v_4276_D;
    public final int d_2461_k;
    public final boolean G_624_v;
    public final boolean T_2506_i;
    public final int q_4610_l;
    public final boolean z_4693_k;
    public final boolean g_221_o;
    public final boolean e_2887_G;
    public final int B_1668_F;
    public final String g_164_R;
    public final c_612_s.n_1700_B<Integer> X_933_l;
    public final c_612_s.n_1700_B<Boolean> Z_976_R;
    public final j_419_j H_1990_U;

    public DedicatedServerProperties(Properties p_i242099_1_, r_4097_j p_i242099_2_) {
        super(p_i242099_1_);
        if (this.n_1700_B("snooper-enabled", true)) {
            // empty if block
        }
        this.H_2857_Y = false;
        this.A_4115_X = this.n_1700_B("use-native-transport", true);
        this.Y_1740_V = this.n_1700_B("enable-command-block", false);
        this.t_4043_B = this.n_1700_B("spawn-protection", 16);
        this.x_607_J = this.n_1700_B("op-permission-level", 4);
        this.e_4240_b = this.n_1700_B("function-permission-level", 2);
        this.n_3318_d = this.n_1700_B("max-tick-time", TimeUnit.MINUTES.toMillis(1L));
        this.d_2427_y = this.n_1700_B("rate-limit", 0);
        this.z_1737_N = this.n_1700_B("view-distance", 10);
        this.v_4276_D = this.n_1700_B("max-players", 20);
        this.d_2461_k = this.n_1700_B("network-compression-threshold", 256);
        this.G_624_v = this.n_1700_B("broadcast-rcon-to-ops", true);
        this.T_2506_i = this.n_1700_B("broadcast-console-to-ops", true);
        this.q_4610_l = this.n_1700_B("max-world-size", p_218986_0_ -> u_530_F.n_1700_B((int)p_218986_0_, 1, 29999984), 29999984);
        this.z_4693_k = this.n_1700_B("sync-chunk-writes", true);
        this.g_221_o = this.n_1700_B("enable-jmx-monitoring", false);
        this.e_2887_G = this.n_1700_B("enable-status", true);
        this.B_1668_F = this.n_1700_B("entity-broadcast-range-percentage", p_241083_0_ -> u_530_F.n_1700_B((int)p_241083_0_, 10, 1000), 100);
        this.g_164_R = this.n_1700_B("text-filtering-config", "");
        this.X_933_l = this.J_1907_R("player-idle-timeout", 0);
        this.Z_976_R = this.J_1907_R("white-list", false);
        this.H_1990_U = j_419_j.n_1700_B(p_i242099_2_, p_i242099_1_);
    }

    public static DedicatedServerProperties n_1700_B(r_4097_j registries, Path p_244380_1_) {
        return new DedicatedServerProperties(DedicatedServerProperties.n_1700_B(p_244380_1_), registries);
    }

    protected DedicatedServerProperties J_1907_R(r_4097_j p_241881_1_, Properties p_241881_2_) {
        return new DedicatedServerProperties(p_241881_2_, p_241881_1_);
    }

    @Override
    protected /* synthetic */ c_612_s n_1700_B(r_4097_j r_4097_j2, Properties properties) {
        return this.J_1907_R(r_4097_j2, properties);
    }
}


