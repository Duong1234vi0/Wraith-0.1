/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.github.javafaker.Faker
 *  com.viaversion.viaversion.api.Via
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  com.viaversion.viaversion.api.protocol.version.ProtocolVersion
 */
package lightning.product;

import com.github.javafaker.Faker;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.text.Normalizer;
import java.util.Locale;
import lightning.product.P_3504_Q;
import lightning.product.V_772_m;
import lightning.product.MinecraftAccess;
import lightning.product.b_4507_u;
import lightning.product.u_530_F;
import mods.viaversion.vialoadingbase.ViaLoadingBase;

public class l_3729_r {
    private static final Faker n_1700_B = new Faker();

    public static String n_1700_B() {
        Object s = l_3729_r.n_1700_B(n_1700_B.name().username());
        if (((String)s).length() < 3) {
            s = (String)s + n_1700_B.number().digits(3);
        }
        return ((String)s).length() > 16 ? ((String)s).substring(0, 16) : s;
    }

    private static String n_1700_B(String input) {
        if (input == null) {
            return "Player" + n_1700_B.number().digits(3);
        }
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Object cleaned = (normalized = normalized.replaceAll("\\p{M}+", "")).replaceAll("[^A-Za-z0-9_]", "");
        if (((String)cleaned).isEmpty()) {
            cleaned = "Player";
        }
        if (((String)cleaned).length() >= 1) {
            String first = ((String)cleaned).substring(0, 1).toUpperCase(Locale.ROOT);
            String rest = ((String)cleaned).substring(1);
            cleaned = first + rest;
        }
        return cleaned;
    }

    public static P_3504_Q n_1700_B(int mouseX, int mouseY) {
        return lightning.product.l_3729_r$n_1700_B.n_1700_B(mouseX, mouseY);
    }

    public static String J_1907_R() {
        return J_1907_R.n_1700_B();
    }

    public static String R_4764_Y() {
        return J_1907_R.J_1907_R();
    }

    public static String G_564_y() {
        return J_1907_R.R_4764_Y();
    }

    public static int P_1922_E() {
        return J_1907_R.G_564_y();
    }

    public static boolean n_1700_B(ProtocolVersion protocolVersion) {
        if (!ViaLoadingBase.getInstance().getTargetVersion().newerThanOrEqualTo(protocolVersion)) {
            return false;
        }
        for (UserConnection conn : Via.getManager().getConnectionManager().getConnections()) {
            if (conn == null) {
                return false;
            }
            if (!conn.getProtocolInfo().getUsername().equalsIgnoreCase(MinecraftAccess.c_3005_b.w_1484_f.P_1922_E().getName())) continue;
            return true;
        }
        return false;
    }

    public static class n_1700_B {
        public static P_3504_Q n_1700_B(float mouseX, float mouseY) {
            double scale = MinecraftAccess.H_2857_Y.w_1457_N() / 2.0;
            return new P_3504_Q((float)((double)mouseX * scale), (float)((double)mouseY * scale));
        }
    }

    public static class J_1907_R {
        private static final int n_1700_B = 29999984;
        private static final int J_1907_R = -2048;
        private static final int R_4764_Y = 2048;
        private static int G_564_y = 0;
        private static long P_1922_E = 0L;

        public static String n_1700_B() {
            V_772_m player = MinecraftAccess.c_3005_b.Y_259_p;
            if (player != null) {
                int posX = lightning.product.l_3729_r$J_1907_R.n_1700_B(player.O_3598_v());
                int posY = lightning.product.l_3729_r$J_1907_R.J_1907_R(player.X_2960_b());
                int posZ = lightning.product.l_3729_r$J_1907_R.n_1700_B(player.l_2647_k());
                return String.format("%d %d %d", posX, posY, posZ);
            }
            return "";
        }

        public static String J_1907_R() {
            V_772_m player = MinecraftAccess.c_3005_b.Y_259_p;
            if (player != null) {
                double x = player.O_3598_v();
                double z = player.l_2647_k();
                double y = player.X_2960_b();
                if (player.O_508_d.g_2268_R() == b_4507_u.u_1723_Y) {
                    x /= 8.0;
                    z /= 8.0;
                } else if (player.O_508_d.g_2268_R() == b_4507_u.v_4262_N) {
                    x *= 8.0;
                    z *= 8.0;
                }
                int posX = lightning.product.l_3729_r$J_1907_R.n_1700_B(x);
                int posY = lightning.product.l_3729_r$J_1907_R.J_1907_R(y);
                int posZ = lightning.product.l_3729_r$J_1907_R.n_1700_B(z);
                return String.format("%d %d %d", posX, posY, posZ);
            }
            return "";
        }

        public static String R_4764_Y() {
            V_772_m player = MinecraftAccess.c_3005_b.Y_259_p;
            if (player == null) {
                return "0.0";
            }
            double deltaX = player.O_3598_v() - player.r_715_M;
            double deltaZ = player.l_2647_k() - player.i_1637_u;
            double deltaY = player.X_2960_b() - player.A_1038_p;
            if (!(Double.isFinite(deltaX) && Double.isFinite(deltaY) && Double.isFinite(deltaZ))) {
                return "0.0";
            }
            double distance = Math.hypot(deltaX, deltaZ);
            if (!Double.isFinite(distance = Math.hypot(distance, deltaY))) {
                return "0.0";
            }
            distance = u_530_F.n_1700_B(distance, 0.0, 5000.0);
            return String.format(Locale.US, "%.1f", distance * 20.0);
        }

        public static int G_564_y() {
            long currentTime = System.currentTimeMillis();
            if (currentTime - P_1922_E > 100L) {
                G_564_y = MinecraftAccess.c_3005_b.k_2293_S() != null && MinecraftAccess.c_3005_b.k_2293_S().n_1700_B(MinecraftAccess.c_3005_b.Y_259_p.w_2705_t()) != null ? MinecraftAccess.c_3005_b.k_2293_S().n_1700_B(MinecraftAccess.c_3005_b.Y_259_p.w_2705_t()).R_4764_Y() : 0;
                P_1922_E = currentTime;
            }
            return G_564_y;
        }

        private static int n_1700_B(double value) {
            if (!Double.isFinite(value)) {
                return 0;
            }
            return u_530_F.n_1700_B(u_530_F.R_4764_Y(value), -29999984, 29999984);
        }

        private static int J_1907_R(double value) {
            if (!Double.isFinite(value)) {
                return 0;
            }
            return u_530_F.n_1700_B(u_530_F.R_4764_Y(value), -2048, 2048);
        }
    }
}


