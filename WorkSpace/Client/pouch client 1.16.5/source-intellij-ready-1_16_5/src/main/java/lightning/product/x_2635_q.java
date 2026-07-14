/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lightning.product.A_2226_Q;
import lightning.product.D_3612_q;
import lightning.product.D_4024_W;
import lightning.product.R_2822_N;
import lightning.product.Interface;
import lightning.product.U_2871_b;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.AutoLeave;
import lightning.product.PlayerTeam;
import lightning.product.h_1015_G;
import lightning.product.i_4895_l;
import lightning.product.ClientBootstrap;
import lightning.product.o_3050_h;
import lightning.product.x_282_a;
import lombok.Generated;

public class x_2635_q
implements MinecraftAccess {
    private static x_2635_q n_1700_B;
    private static final Locale J_1907_R;
    private static final Pattern R_4764_Y;
    private final List<D_3612_q> G_564_y = new ArrayList<D_3612_q>();

    public x_2635_q() {
        n_1700_B = this;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (x_2635_q.c_3005_b.Y_259_p == null || x_2635_q.c_3005_b.Y_601_j == null) {
            return;
        }
        if ((x_2635_q.c_3005_b.Y_259_p.RealmsWorldResetDto & 9) != 0) {
            return;
        }
        if (ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Interface.class).w_1484_f() && Interface.t_148_a.J_1907_R("\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044f \u043e\u043d\u043b\u0430\u0439\u043d").booleanValue() || ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AutoLeave.class).w_1484_f()) {
            this.n_1700_B();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B() {
        List<D_3612_q> online = this.P_1922_E();
        List<D_3612_q> vanished = x_2635_q.J_1907_R();
        online.sort(Comparator.comparing(D_3612_q::n_1700_B, String.CASE_INSENSITIVE_ORDER));
        vanished.sort(Comparator.comparing(D_3612_q::n_1700_B, String.CASE_INSENSITIVE_ORDER));
        List<D_3612_q> list = this.G_564_y;
        synchronized (list) {
            this.G_564_y.clear();
            this.G_564_y.addAll(online);
            this.G_564_y.addAll(vanished);
        }
    }

    private List<D_3612_q> P_1922_E() {
        if (x_2635_q.c_3005_b.Y_601_j == null || c_3005_b.k_2293_S() == null) {
            return Collections.emptyList();
        }
        R_2822_N staffManager = ClientBootstrap.Y_601_j().w_1484_f();
        Set staffNamesLower = staffManager != null ? staffManager.P_4830_p().stream().map(R_2822_N.n_1700_B::n_1700_B).filter(Objects::nonNull).map(name -> name.toLowerCase(J_1907_R)).collect(Collectors.toSet()) : Collections.emptySet();
        ArrayList<D_3612_q> list = new ArrayList<D_3612_q>();
        for (A_2226_Q info : c_3005_b.k_2293_S().P_1922_E()) {
            String username;
            if (info == null || (username = info.n_1700_B().getName()) == null) continue;
            x_282_a rawPrefix = U_2871_b.R_4764_Y;
            for (PlayerTeam team : x_2635_q.c_3005_b.Y_601_j.Q_4569_t().P_1922_E()) {
                if (!team.u_1723_Y().contains(username)) continue;
                rawPrefix = team.G_564_y();
                break;
            }
            if (!staffNamesLower.contains(username.toLowerCase(J_1907_R)) && !x_2635_q.n_1700_B(rawPrefix.getString())) continue;
            list.add(new D_3612_q(username, rawPrefix, false));
        }
        return list;
    }

    public static List<D_3612_q> J_1907_R() {
        if (x_2635_q.c_3005_b.Y_601_j == null || c_3005_b.k_2293_S() == null) {
            return Collections.emptyList();
        }
        Set onlineNames = c_3005_b.k_2293_S().P_1922_E().stream().filter(Objects::nonNull).map(info -> info.n_1700_B().getName()).filter(Objects::nonNull).collect(Collectors.toSet());
        i_4895_l scoreboard = x_2635_q.c_3005_b.Y_601_j.Q_4569_t();
        ArrayList<D_3612_q> list = new ArrayList<D_3612_q>();
        for (PlayerTeam team : scoreboard.P_1922_E().stream().sorted(Comparator.comparing(o_3050_h::n_1700_B)).toList()) {
            String name = team.u_1723_Y().stream().findFirst().orElse(null);
            if (name == null || !R_4764_Y.matcher(name).matches() || onlineNames.contains(name) || team.J_1907_R().getString().trim().startsWith("npc") || team.J_1907_R().getString().trim().startsWith("collideRule_") || team.J_1907_R().getString().trim().startsWith("FS_") || team.J_1907_R().getString().trim().startsWith("STAFF-") || team.J_1907_R().getString().trim().startsWith("HELPER-") || team.J_1907_R().getString().trim().startsWith("ADMIN-") || team.J_1907_R().getString().trim().startsWith("MODER-")) continue;
            x_282_a rawPrefix = team.G_564_y();
            list.add(new D_3612_q(name, rawPrefix, true));
        }
        return list;
    }

    private static boolean n_1700_B(String rawPrefix) {
        String p = D_4024_W.n_1700_B(rawPrefix);
        if (p == null || p.isEmpty()) {
            return false;
        }
        return (p = p.toLowerCase(J_1907_R)).contains("helper") || p.contains("moder") || p.contains("admin") || p.contains("curator") || p.contains("builder") || p.contains("yt") || p.contains("\u043a\u0443\u0440") || p.contains("\u0442\u0435\u0445.\u0430\u0434\u043c\u0438\u043d") || p.contains("s.kurator") || p.contains("\u043f\u043e\u0434\u0434\u0435\u0440\u0436\u043a\u0430") || p.contains("\u0441\u043e\u0442\u0440\u0443\u0434") || p.contains("\ua513") || p.contains("\ua509") || p.contains("\ua505") || p.contains("\u0421\u0442\u0430\u0436\u0435\u0440") || p.contains("\u0421\u0442\u0430") || p.contains("\ua501") || p.contains("\ua537") || p.contains("\ua533") || p.contains("\ua529") || p.contains("\ua525") || p.contains("\ua521") || p.contains("\ua517");
    }

    @Generated
    public List<D_3612_q> R_4764_Y() {
        return this.G_564_y;
    }

    @Generated
    public static x_2635_q G_564_y() {
        return n_1700_B;
    }

    static {
        J_1907_R = Locale.ROOT;
        R_4764_Y = Pattern.compile("^\\w{3,16}$");
    }
}



