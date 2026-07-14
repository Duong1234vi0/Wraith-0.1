/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import lightning.product.D_4024_W;
import lightning.product.H_1468_N;
import lightning.product.Objective;
import lightning.product.a_3913_L;
import lightning.product.MinecraftAccess;
import lightning.product.PlayerTeam;
import lightning.product.i_4895_l;
import lightning.product.m_1761_s;
import lightning.product.r_4811_B;
import lightning.product.v_4839_y;
import lightning.product.x_282_a;

public class q_3115_L
implements MinecraftAccess {
    private static final String n_1700_B = "wellmine";
    private static final String J_1907_R = "lonygrief";
    private static final String R_4764_Y = "cakeworld";
    private static final String G_564_y = "holyworld";

    public static boolean n_1700_B() {
        return lightning.product.q_3115_L$n_1700_B.n_1700_B();
    }

    public static boolean J_1907_R() {
        if (c_3005_b.t_4043_B() == null) {
            return false;
        }
        String ip = q_3115_L.c_3005_b.t_4043_B().J_1907_R.toLowerCase();
        if (!ip.contains(n_1700_B)) {
            return false;
        }
        try {
            if (q_3115_L.c_3005_b.M_588_G != null && q_3115_L.c_3005_b.M_588_G.v_4262_N() != null) {
                x_282_a footer = q_3115_L.c_3005_b.M_588_G.v_4262_N().R_4764_Y();
                if (footer != null && footer.getString().toLowerCase().contains(n_1700_B)) {
                    return true;
                }
                x_282_a header = q_3115_L.c_3005_b.M_588_G.v_4262_N().J_1907_R();
                if (header != null && header.getString().toLowerCase().contains(n_1700_B)) {
                    return true;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return ip.contains(n_1700_B);
    }

    public static boolean R_4764_Y() {
        if (c_3005_b.t_4043_B() == null) {
            return false;
        }
        return q_3115_L.c_3005_b.t_4043_B().J_1907_R.toLowerCase().contains(J_1907_R);
    }

    public static boolean G_564_y() {
        if (c_3005_b.t_4043_B() == null) {
            return false;
        }
        String ip = q_3115_L.c_3005_b.t_4043_B().J_1907_R.toLowerCase();
        if (!ip.contains(R_4764_Y)) {
            return false;
        }
        try {
            if (q_3115_L.c_3005_b.M_588_G != null && q_3115_L.c_3005_b.M_588_G.v_4262_N() != null) {
                x_282_a footer = q_3115_L.c_3005_b.M_588_G.v_4262_N().R_4764_Y();
                if (footer != null && footer.getString().toLowerCase().contains(R_4764_Y)) {
                    return true;
                }
                x_282_a header = q_3115_L.c_3005_b.M_588_G.v_4262_N().J_1907_R();
                if (header != null && header.getString().toLowerCase().contains(R_4764_Y)) {
                    return true;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return ip.contains(R_4764_Y);
    }

    public static float n_1700_B(r_4811_B entity) {
        if (entity instanceof a_3913_L) {
            Iterator<Map.Entry<Objective, v_4839_y>> iterator;
            a_3913_L p = (a_3913_L)entity;
            if (q_3115_L.c_3005_b.Y_601_j != null && (iterator = q_3115_L.c_3005_b.Y_601_j.Q_4569_t().G_564_y(p.y_4642_Y().getName()).entrySet().iterator()).hasNext()) {
                Map.Entry<Objective, v_4839_y> entry = iterator.next();
                v_4839_y score = entry.getValue();
                return score.J_1907_R();
            }
        }
        return (int)(entity.g_46_E() + entity.U_3823_u());
    }

    public static boolean n_1700_B(String ip) {
        return c_3005_b.t_4043_B() != null && q_3115_L.c_3005_b.t_4043_B().J_1907_R != null && q_3115_L.c_3005_b.t_4043_B().J_1907_R.contains(ip);
    }

    public static boolean P_1922_E() {
        if (c_3005_b.t_4043_B() == null || q_3115_L.c_3005_b.t_4043_B().J_1907_R == null) {
            return false;
        }
        String ip = q_3115_L.c_3005_b.t_4043_B().J_1907_R.toLowerCase();
        return ip.contains("spookytime");
    }

    public static boolean u_1723_Y() {
        String b;
        String brand;
        if (q_3115_L.c_3005_b.Y_259_p != null && (brand = q_3115_L.c_3005_b.Y_259_p.h_1847_R()) != null && ((b = brand.toLowerCase()).contains(G_564_y) || b.contains("leaf") || b.contains("vk.com/idwok"))) {
            return true;
        }
        if (c_3005_b.t_4043_B() == null || q_3115_L.c_3005_b.t_4043_B().J_1907_R == null) {
            return false;
        }
        return q_3115_L.c_3005_b.t_4043_B().J_1907_R.toLowerCase().contains(G_564_y);
    }

    public static int v_4262_N() {
        if (!q_3115_L.u_1723_Y() || q_3115_L.c_3005_b.Y_601_j == null) {
            return -1;
        }
        i_4895_l scoreboard = q_3115_L.c_3005_b.Y_601_j.Q_4569_t();
        if (scoreboard == null) {
            return -1;
        }
        Objective objective = scoreboard.n_1700_B(1);
        if (objective == null) {
            return -1;
        }
        Collection<v_4839_y> scores = scoreboard.n_1700_B(objective);
        for (v_4839_y score : scores) {
            String line;
            int a;
            String playerName = score.P_1922_E();
            if (playerName == null || (a = q_3115_L.R_4764_Y(line = q_3115_L.n_1700_B(scoreboard, playerName))) <= 0) continue;
            return a;
        }
        return -1;
    }

    private static String n_1700_B(i_4895_l scoreboard, String playerName) {
        PlayerTeam team = scoreboard.w_1484_f(playerName);
        if (team != null) {
            String prefix = D_4024_W.n_1700_B(team.G_564_y().getString());
            String suffix = D_4024_W.n_1700_B(team.P_1922_E().getString());
            String name = D_4024_W.n_1700_B(playerName);
            return (prefix != null ? prefix : "") + (name != null ? name : "") + (suffix != null ? suffix : "");
        }
        return D_4024_W.n_1700_B(playerName);
    }

    private static int R_4764_Y(String text) {
        if (text == null || text.isEmpty()) {
            return -1;
        }
        int hash = text.indexOf(35);
        if (hash < 0) {
            return -1;
        }
        int end = text.indexOf(" -\u25c6-", hash);
        if (end <= hash) {
            end = text.length();
            for (int i = hash + 1; i < text.length(); ++i) {
                char c = text.charAt(i);
                if (Character.isDigit(c) || c == ' ') continue;
                end = i;
                break;
            }
        }
        if (end <= hash) {
            return -1;
        }
        String mid = text.substring(hash + 1, end).replace(" (1.20)", "").trim();
        String digits = mid.replaceAll("[^0-9]", "");
        if (digits.isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(digits);
        }
        catch (NumberFormatException ignored) {
            return -1;
        }
    }

    public boolean w_1484_f() {
        String ip = this.t_148_a();
        return ip.contains("reallyworld") || ip.contains("playrw");
    }

    public boolean J_1907_R(String str) {
        return this.t_148_a().toLowerCase().contains(str);
    }

    public String t_148_a() {
        if (q_3115_L.c_3005_b.Y_601_j == null || q_3115_L.c_3005_b.Y_259_p == null) {
            return "singleplayer";
        }
        String server = c_3005_b.e_4240_b() ? "singleplayer" : (c_3005_b.t_4043_B() == null ? "singleplayer" : q_3115_L.c_3005_b.t_4043_B().J_1907_R);
        return server;
    }

    public static class n_1700_B {
        private static boolean n_1700_B = false;
        private static UUID J_1907_R = null;

        public static void n_1700_B(m_1761_s packet) {
            if (packet.R_4764_Y() == m_1761_s.n_1700_B.n_1700_B) {
                String name = H_1468_N.n_1700_B(packet.G_564_y().getString()).toLowerCase();
                if (name.contains("pvp") || name.contains("\u0443 \u0432\u0430\u0441 \u043e\u0441\u0442\u0430\u043b\u043e\u0441\u044c")) {
                    n_1700_B = true;
                    J_1907_R = packet.J_1907_R();
                }
            } else if (packet.R_4764_Y() == m_1761_s.n_1700_B.J_1907_R && packet.J_1907_R().equals(J_1907_R)) {
                n_1700_B = false;
                J_1907_R = null;
            }
        }

        public static boolean n_1700_B() {
            return n_1700_B;
        }
    }
}



