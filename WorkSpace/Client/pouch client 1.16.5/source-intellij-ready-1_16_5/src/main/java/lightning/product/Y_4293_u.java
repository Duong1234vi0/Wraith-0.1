/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lightning.product.A_2226_Q;
import lightning.product.D_3612_q;
import lightning.product.D_4024_W;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.I_14_v;
import lightning.product.J_3635_s;
import lightning.product.K_1200_E;
import lightning.product.N_4263_v;
import lightning.product.U_2871_b;
import lightning.product.U_3758_B;
import lightning.product.Z_2491_A;
import lightning.product.a_3913_L;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_2367_h;
import lightning.product.h_4412_P;
import lightning.product.j_1376_w;
import lightning.product.Animation;
import lightning.product.l_3370_o;
import lightning.product.BooleanSetting;
import lightning.product.q_3148_R;
import lightning.product.ModeSetting;
import lightning.product.x_2635_q;
import lightning.product.x_282_a;
import lombok.Generated;

public class Y_4293_u
implements ServerHandshakePacketListener {
    public static ModeSetting n_1700_B = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "LonyGrief");
    public static BooleanSetting J_1907_R = new BooleanSetting("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0441\u043a\u0438\u043d", true);
    public static h_2367_h R_4764_Y = new h_2367_h("\u0425\u0435\u0434\u0435\u0440", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h G_564_y = new h_2367_h("\u0424\u043e\u043d", true, H_2506_c.n_1700_B(30, 25, 40, 255));
    public static h_2367_h P_1922_E = new h_2367_h("\u0422\u0435\u043a\u0441\u0442", true, H_2506_c.n_1700_B(180, 140, 255, 255));
    public static h_2367_h u_1723_Y = new h_2367_h("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", true, H_2506_c.n_1700_B(120, 80, 160, 255));
    public static h_2367_h v_4262_N = new h_2367_h("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", true, H_2506_c.n_1700_B(120, 80, 160, 100));
    private final J_3635_s w_1484_f;
    private final Animation t_148_a = new Animation(0.0f, 10.0f);
    private final Animation s_956_w = new Animation(22.0f, 10.0f);
    private final Animation u_2550_I = new Animation(60.0f, 10.0f);
    private final Map<String, Animation[]> M_588_G = new HashMap<String, Animation[]>();
    private final Map<String, D_3612_q> P_4830_p = new HashMap<String, D_3612_q>();
    private static final Set<String> h_1847_R = new HashSet<String>(Arrays.asList("Amogus119", "No_NE_BAN", "phylantrop", "mortaliusss", "Neveger", "Solomie", "po4kado4ka", "willashmy", "sozored", "FryzziQ1", "BudibuKiller", "XDeadForBanan4ik", "crusshhing2rist", "nvmokxd", "dabal_ez", "anqxk", "_ItsDragoN", "akcerman", "Thanak1s", "mortaliuss", "26_APOSTOL_26", "admin", "EmpressWave", "andrey_borisov", "XMassive", "OLEGxPVP", "Shirtayni", "Emberspirit2001", "kisylii", "melwis", "nickleodeon23", "Rinato4ka__0", "mandarika16", "Holdikbral22", "Literium", "Revayer", "JoffBezoss", "BOG090909", "Kiryu_Kazuma", "QWE_BOSS_KFC", "MatPeP1231", "Slavchik_g", "qwertyonil", "BoT_TE6e_BaN", "Empress_Wave", "xDeadForBanan4ik", "Samuraiiee", "MbIshaa", "willashny", "fryktoviy_sad", "YuraFeemchik", "Led9Noi_0", "U_N_F_Y", "MyNameK1zzy", "Capitanzalupka52", "NaigralisRebyata"));
    private final Set<String> Q_4569_t = new HashSet<String>();
    private final Set<String> M_182_A = new HashSet<String>();
    private final Map<String, D_3612_q> t_1786_h = new HashMap<String, D_3612_q>();
    private final List<D_3612_q> multiplayerClientSuggestionProvider = new ArrayList<D_3612_q>();
    private boolean w_1457_N = false;
    private long Y_601_j = 0L;
    private static final List<String> Y_259_p = Arrays.asList("helper", "moder", "staff", "admin", "curator", "intern", "\u0441\u0442\u0430\u0436\u0451\u0440", "\u0441\u0442\u0430\u0436\u0435\u0440", "\u026a\u0274\u1d1b\u1d07\u0280\u0274", "\u0441\u043e\u0442\u0440\u0443\u0434\u043d\u0438\u043a", "\u043f\u043e\u043c\u043e\u0449\u043d\u0438\u043a", "\u0430\u0434\u043c\u0438\u043d", "\u0438\u043d\u0442\u0435\u0440\u043d", "\u029c\u1d07\u029f\u1d18\u1d07\u0280", "\u1d0d\u1d0f\u1d05\u1d07\u0280", "\u1d05.\u1d0f\u1d21\u0274\u1d07\u0280", "\u1d0f\u1d21\u0274\u1d07\u0280", "\u1d22\u1d00\u1d0d.\u1d04\u1d1c\u0280\u1d00\u1d1b\u1d0f\u0280", "\u1d04\u1d1c\u0280\u1d00\u1d1b\u1d0f\u0280", "s\u1d18\u1d07\u1d04\u1d1b\u1d00\u1d1b\u1d0f\u0280", "\u043c\u043b.\u0441\u043e\u0442\u0440\u0443\u0434\u043d\u0438\u043a", "\u0441\u043e\u0442\u0440\u0443\u0434\u043d\u0438\u043a", "\u0441\u0442\u0430\u0436\u0451\u0440");
    private static final Set<String> Q_2552_b = new HashSet<String>(Arrays.asList("Inkvini1", "marilyn_manson", "LsParadox", "loz1ch", "i_love_v_cupsize", "Ne_Kamin", "Ilomasus", "hishiro", "Ca1iforniaHelp", "AlexFrezirovshik", "TizzyxHelp", "uncovershadd", "velya_standoff", "S3tshy", "Fanat0chka", "hightier1", "ErkoShaGood", "Ca1ifornialove", "popka_w", "whymescared", "molodec_ananas", "toperezka12", "manchest", "In_imortal", "KaminEZZ", "Barsik9999n", "underwingofangel", "SanDex", "Lemonacho", "pax_gBAX_HAX_HD", "645432", "xDeadForMan4ik", "lolpak_1", "Gruzin2011", "Munux", "webforever", "uncovershadd", "ImWortyEzz", "lisrtix", "NewAntiCheat", "feariu", "Futz"));
    private final List<D_3612_q> C_2741_M = new ArrayList<D_3612_q>();
    private final Set<String> k_2293_S = new HashSet<String>();
    private final Map<String, D_3612_q> q_2307_F = new HashMap<String, D_3612_q>();
    private final Set<String> Z_875_P = new HashSet<String>();
    private boolean t_4043_B = false;
    private long x_607_J = 0L;
    private boolean e_4240_b = false;
    private boolean n_3318_d = false;

    @Override
    public void n_1700_B(b_3528_u event) {
        String name;
        Animation[] anims;
        ArrayList<D_3612_q> activeItems;
        g_221_o ms = event.J_1907_R();
        float posX = this.w_1484_f.J_1907_R();
        float posY = this.w_1484_f.R_4764_Y();
        if (n_1700_B.J_1907_R("Sunrise")) {
            this.J_1907_R();
            activeItems = new ArrayList<D_3612_q>(this.multiplayerClientSuggestionProvider);
        } else if (n_1700_B.J_1907_R("LonyGrief")) {
            this.P_1922_E();
            activeItems = new ArrayList<D_3612_q>(this.C_2741_M);
        } else {
            activeItems = x_2635_q.G_564_y() != null ? x_2635_q.G_564_y().R_4764_Y() : new ArrayList();
        }
        this.t_148_a.n_1700_B(Y_4293_u.c_3005_b.Y_1740_V instanceof h_4412_P || !activeItems.isEmpty() ? 1.0f : 0.0f);
        float globalAlpha = this.t_148_a.n_1700_B();
        ArrayList<D_3612_q> dimsItems = new ArrayList<D_3612_q>(activeItems);
        for (String name2 : this.M_588_G.keySet()) {
            D_3612_q prev;
            boolean exists = activeItems.stream().anyMatch(s -> s.n_1700_B().equals(name2));
            if (exists || (prev = this.P_4830_p.get(name2)) == null) continue;
            dimsItems.add(prev);
        }
        float[] dimensions = this.n_1700_B(dimsItems);
        float targetWidth = dimensions[0];
        this.u_2550_I.n_1700_B(targetWidth);
        float width = this.u_2550_I.n_1700_B();
        float headerHeight = 15.0f;
        float itemSpacing = 11.0f;
        float targetHeight = Math.max(20.0f, 16.5f + (float)activeItems.size() * itemSpacing);
        this.s_956_w.n_1700_B(targetHeight);
        float animatedHeight = this.s_956_w.n_1700_B();
        int glow = (Integer)v_4262_N.J_1907_R();
        float glowAlpha = (float)H_2506_c.G_564_y(glow) / 255.0f;
        F_489_x.n_1700_B(posX - 10.0f, posY - 10.0f, width + 20.0f, animatedHeight + 20.0f, 5.0f, glow, glow, glow, glow, glowAlpha * globalAlpha, 10.0f);
        F_489_x.n_1700_B(posX, posY, width, animatedHeight, 5.0f, (int)((Integer)G_564_y.J_1907_R()), globalAlpha);
        F_489_x.n_1700_B(posX, posY, width, headerHeight, new Z_2491_A(5.0f, 0.0f, 5.0f, 0.0f), (int)((Integer)R_4764_Y.J_1907_R()), globalAlpha);
        int outline = (Integer)u_1723_Y.J_1907_R();
        float outlineAlphaValue = (float)H_2506_c.G_564_y(outline) * globalAlpha;
        F_489_x.J_1907_R(posX, posY, width, animatedHeight, 5.0f, outline, outlineAlphaValue);
        int headerTextColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.w_1457_N), globalAlpha);
        String headerTitle = n_1700_B.J_1907_R("Sunrise") ? "Staff Sunrise" : (n_1700_B.J_1907_R("LonyGrief") ? "Staff LonyGrief" : "Staff");
        MutableComponent gradientLogo = j_1376_w.n_1700_B(headerTitle, q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 4, 15.0f);
        l_3370_o.J_1907_R[15].n_1700_B(ms, gradientLogo, (double)(posX + 4.5f), (double)(posY + 5.5f), headerTextColor);
        MutableComponent iconText = j_1376_w.n_1700_B("O", q_3148_R.n_1700_B(K_1200_E.w_1457_N), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.w_1457_N), 0.3f), 7, 15.0f);
        l_3370_o.u_1723_Y[16].n_1700_B(ms, iconText, (double)(posX + width - l_3370_o.u_1723_Y[16].n_1700_B("O") - 4.5f), (double)(posY + 6.5f), headerTextColor);
        float baseItemY = posY + headerHeight;
        ArrayList<String> toRemove = new ArrayList<String>();
        for (int i = 0; i < activeItems.size(); ++i) {
            D_3612_q item = (D_3612_q)activeItems.get(i);
            String key = item.n_1700_B();
            int finalI = i;
            anims = this.M_588_G.computeIfAbsent(key, k -> new Animation[]{new Animation(0.0f, 10.0f), new Animation(-5.0f, 10.0f), new Animation((float)finalI * itemSpacing, 10.0f)});
            anims[0].n_1700_B(1.0f);
            anims[1].n_1700_B(0.0f);
            float targetY = (float)i * itemSpacing;
            anims[2].n_1700_B(targetY);
            this.P_4830_p.put(key, item);
        }
        for (Map.Entry<String, Animation[]> entry : this.M_588_G.entrySet()) {
            name = entry.getKey();
            boolean stillActive = activeItems.stream().anyMatch(s -> s.n_1700_B().equals(name));
            if (stillActive) continue;
            anims = entry.getValue();
            anims[0].n_1700_B(0.0f);
            anims[1].n_1700_B(-5.0f);
            if (!anims[0].R_4764_Y() || !anims[1].R_4764_Y()) continue;
            toRemove.add(name);
        }
        toRemove.forEach(this.M_588_G::remove);
        for (Map.Entry<String, Animation[]> entry : this.M_588_G.entrySet()) {
            a_3913_L player;
            float avatarOffset;
            name = entry.getKey();
            Animation[] anims2 = entry.getValue();
            float itemAlpha = globalAlpha * anims2[0].n_1700_B();
            if (itemAlpha <= 0.01f && anims2[0].R_4764_Y()) continue;
            float itemY = baseItemY + anims2[2].n_1700_B();
            D_3612_q item = activeItems.stream().filter(s -> s.n_1700_B().equals(name)).findFirst().orElse(this.P_4830_p.get(name));
            if (item == null) continue;
            A_2226_Q playerInfo = c_3005_b.k_2293_S() != null ? c_3005_b.k_2293_S().n_1700_B(item.n_1700_B()) : null;
            g_2336_b skin = playerInfo != null ? playerInfo.u_1723_Y() : new g_2336_b("textures/entity/steve.png");
            int fontSize = 13;
            String plainName = D_4024_W.n_1700_B(item.J_1907_R().getString());
            float prefixWidth = l_3370_o.G_564_y[fontSize].n_1700_B(plainName);
            float nameWidth = l_3370_o.G_564_y[fontSize].n_1700_B(item.n_1700_B());
            float prefixHeight = l_3370_o.G_564_y[fontSize].h_1847_R();
            float nameHeight = l_3370_o.G_564_y[fontSize].h_1847_R();
            int animatedTextColor = H_2506_c.n_1700_B(H_2506_c.n_1700_B(255, 255, 255, 255), itemAlpha);
            float xOffset = anims2[1].n_1700_B();
            float centerY = itemY + itemSpacing / 2.0f;
            float prefixY = centerY - prefixHeight / 2.0f;
            float nameY = centerY - nameHeight / 2.0f;
            float f = avatarOffset = J_1907_R.t_148_a() != false ? 12.0f : 3.0f;
            if (J_1907_R.t_148_a().booleanValue()) {
                F_489_x.n_1700_B(skin, null, posX + xOffset + 2.5f, itemY + 2.0f, 7.0f, 7.0f, 2.5f, itemAlpha);
            }
            float textX = posX + xOffset + avatarOffset;
            l_3370_o.G_564_y[fontSize].n_1700_B(ms, item.J_1907_R(), (double)textX, (double)(prefixY + 1.0f), H_2506_c.n_1700_B(-1, itemAlpha));
            l_3370_o.G_564_y[fontSize].n_1700_B(ms, item.n_1700_B(), (double)(textX + prefixWidth), (double)(nameY + 1.0f), animatedTextColor);
            a_3913_L a_3913_L2 = player = Y_4293_u.c_3005_b.Y_601_j != null ? (a_3913_L)Y_4293_u.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider().stream().filter(p -> p.O_1309_Q().getString().equals(item.n_1700_B())).findFirst().orElse(null) : null;
            int baseColor = item.R_4764_Y() ? H_2506_c.n_1700_B(255, 60, 60) : (player != null && (double)Y_4293_u.c_3005_b.Y_259_p.R_4764_Y((N_4263_v)player) <= 100.0 ? H_2506_c.n_1700_B(255, 200, 0) : H_2506_c.n_1700_B(60, 255, 60));
            int color = H_2506_c.n_1700_B(baseColor, itemAlpha);
            float separatorX = posX + xOffset + width - 11.0f;
            int separatorColor = H_2506_c.n_1700_B(q_3148_R.n_1700_B(K_1200_E.Q_4569_t), q_3148_R.J_1907_R(K_1200_E.Q_4569_t) / 255.0f * itemAlpha);
            F_489_x.n_1700_B(ms, separatorX, itemY + 3.0f, 0.5f, 5.0f, separatorColor);
            float circleX = separatorX + 1.0f + 2.0f;
            F_489_x.n_1700_B(circleX, itemY + 3.0f, 5.0f, 5.0f, 1.5f, color);
        }
        this.w_1484_f.G_564_y(headerHeight + (float)activeItems.size() * itemSpacing);
        this.w_1484_f.R_4764_Y(width);
    }

    private void J_1907_R() {
        long currentTime;
        boolean isOnServer;
        if (Y_4293_u.c_3005_b.Y_259_p == null || c_3005_b.e_4240_b()) {
            return;
        }
        boolean bl = isOnServer = Y_4293_u.c_3005_b.Y_259_p != null && !c_3005_b.e_4240_b() && Y_4293_u.c_3005_b.Y_259_p.n_1700_B != null && Y_4293_u.c_3005_b.Y_259_p.n_1700_B.n_1700_B(Y_4293_u.c_3005_b.Y_259_p.y_4642_Y().getId()) != null && Y_4293_u.c_3005_b.Y_259_p.n_1700_B.n_1700_B(Y_4293_u.c_3005_b.Y_259_p.y_4642_Y().getId()).J_1907_R() != I_14_v.P_1922_E;
        if (isOnServer && !this.w_1457_N) {
            this.multiplayerClientSuggestionProvider.clear();
            this.t_1786_h.clear();
            this.M_182_A.clear();
            this.Q_4569_t.clear();
        }
        this.w_1457_N = isOnServer;
        if (this.w_1457_N && (currentTime = System.currentTimeMillis()) - this.Y_601_j > 3000L) {
            this.R_4764_Y();
            this.Y_601_j = currentTime;
        }
    }

    private void R_4764_Y() {
        boolean isVanished;
        boolean isOnline;
        A_2226_Q playerInfo;
        ArrayList<D_3612_q> currentStaff = new ArrayList<D_3612_q>();
        List<String> onlinePlayers = this.G_564_y();
        for (String staffName : h_1847_R) {
            D_3612_q member;
            x_282_a prefix;
            boolean wasSeenOnline;
            playerInfo = this.J_1907_R(staffName);
            isOnline = this.n_1700_B(staffName, playerInfo, onlinePlayers);
            isVanished = this.J_1907_R(staffName, playerInfo, onlinePlayers);
            boolean bl = wasSeenOnline = this.M_182_A.contains(staffName + "_ONLINE") || this.t_1786_h.containsKey(staffName);
            if (wasSeenOnline && (isOnline || isVanished)) {
                prefix = this.n_1700_B(playerInfo, staffName);
                member = new D_3612_q(staffName, prefix, isVanished);
                currentStaff.add(member);
                this.t_1786_h.put(staffName, member);
                this.n_1700_B(staffName, isOnline, isVanished);
                continue;
            }
            if (!isOnline) continue;
            prefix = this.n_1700_B(playerInfo, staffName);
            member = new D_3612_q(staffName, prefix, false);
            currentStaff.add(member);
            this.t_1786_h.put(staffName, member);
            this.n_1700_B(staffName, true, false);
        }
        for (String staffName : this.Q_4569_t) {
            playerInfo = this.J_1907_R(staffName);
            isOnline = this.n_1700_B(staffName, playerInfo, onlinePlayers);
            isVanished = this.J_1907_R(staffName, playerInfo, onlinePlayers);
            if (!isOnline && !isVanished && !this.t_1786_h.containsKey(staffName)) continue;
            x_282_a prefix = this.n_1700_B(playerInfo, staffName);
            D_3612_q member = new D_3612_q(staffName, prefix, isVanished);
            boolean alreadyAdded = currentStaff.stream().anyMatch(m -> m.n_1700_B().equals(staffName));
            if (alreadyAdded) continue;
            currentStaff.add(member);
            this.t_1786_h.put(staffName, member);
            this.n_1700_B(staffName, isOnline, isVanished);
        }
        this.n_1700_B(currentStaff, onlinePlayers);
        currentStaff.sort(Comparator.comparing(D_3612_q::n_1700_B, String.CASE_INSENSITIVE_ORDER));
        this.multiplayerClientSuggestionProvider.clear();
        this.multiplayerClientSuggestionProvider.addAll(currentStaff);
    }

    private void n_1700_B(List<D_3612_q> currentStaff, List<String> onlinePlayers) {
        if (Y_4293_u.c_3005_b.Y_259_p == null || Y_4293_u.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        for (A_2226_Q playerInfo : Y_4293_u.c_3005_b.Y_259_p.n_1700_B.P_1922_E()) {
            boolean isVanished;
            String combinedText;
            boolean isStaff;
            String playerName = playerInfo.n_1700_B().getName();
            String prefixText = "";
            x_282_a prefix = new U_2871_b("");
            if (playerInfo.t_148_a() != null) {
                prefix = playerInfo.t_148_a().G_564_y();
                prefixText = prefix.getString().toLowerCase();
            }
            String displayText = "";
            if (playerInfo.u_2550_I() != null) {
                displayText = playerInfo.u_2550_I().getString().toLowerCase();
            }
            if (!(isStaff = (combinedText = (prefixText + " " + displayText).toLowerCase()).contains("\u043a\u0443\u0440\u0430\u0442\u043e\u0440") || combinedText.contains("\u0430\u0434\u043c\u0438\u043d") || combinedText.contains("\u043c\u043e\u0434\u0435\u0440") || combinedText.contains("\u0445\u0435\u043b\u043f\u0435\u0440") || combinedText.contains("\u0441\u0442\u0430\u0436\u0435\u0440") || combinedText.contains("helper") || combinedText.contains("moder") || combinedText.contains("admin") || combinedText.contains("curator") || combinedText.contains("media") || combinedText.contains("\u043c\u043b.\u0441\u043e\u0442\u0440\u0443\u0434\u043d\u0438\u043a") || combinedText.contains("\u0441\u043e\u0442\u0440\u0443\u0434\u043d\u0438\u043a") || combinedText.contains("\u0441\u0442\u0430\u0436\u0451\u0440") || combinedText.contains("\u0442\u0435\u0445.\u043f\u043e\u0434\u0434\u0435\u0440\u0436\u043a\u0430") || combinedText.contains("\u0442\u0435\u0445\u043f\u043e\u0434\u0434\u0435\u0440\u0436\u043a\u0430") || combinedText.contains("support") || combinedText.contains("\u0441\u0442.\u0445\u0435\u043b\u043f\u0435\u0440") || combinedText.contains("sr.helper") || combinedText.contains("senior") || combinedText.contains("\u0433\u043b\u0430\u0432\u043d\u044b\u0439") || combinedText.contains("\u0433\u043b.\u043c\u043e\u0434\u0435\u0440") || combinedText.contains("\u0433\u043b.\u0430\u0434\u043c\u0438\u043d") || combinedText.contains("j.moder") || combinedText.contains("jmoder") || combinedText.contains("st.moder") || combinedText.contains("owner") || combinedText.contains("developer") || combinedText.contains("dev"))) continue;
            boolean bl = isVanished = playerInfo.J_1907_R() == I_14_v.P_1922_E || !onlinePlayers.contains(playerName);
            boolean alreadyAdded = currentStaff.stream().anyMatch(m -> m.n_1700_B().equals(playerName));
            if (alreadyAdded) continue;
            D_3612_q member = new D_3612_q(playerName, prefix, isVanished);
            currentStaff.add(member);
            this.t_1786_h.put(playerName, member);
            this.n_1700_B(playerName, !isVanished, isVanished);
        }
    }

    private void n_1700_B(String staffName, boolean isOnline, boolean isVanished) {
        if (isOnline) {
            if (!this.M_182_A.contains(staffName + "_ONLINE")) {
                U_3758_B.n_1700_B("O", staffName + " \u0437\u0430\u0448\u0435\u043b \u043d\u0430 \u0441\u0435\u0440\u0432\u0435\u0440!", H_2506_c.n_1700_B(60, 255, 60));
                this.M_182_A.add(staffName + "_ONLINE");
                this.M_182_A.remove(staffName + "_VANISHED");
            }
        } else if (isVanished && !this.M_182_A.contains(staffName + "_VANISHED") && this.M_182_A.contains(staffName + "_ONLINE")) {
            U_3758_B.n_1700_B("O", staffName + " \u0443\u0448\u0435\u043b \u0432 \u0432\u0430\u043d\u0438\u0448!", H_2506_c.n_1700_B(255, 60, 60));
            this.M_182_A.add(staffName + "_VANISHED");
            this.M_182_A.remove(staffName + "_ONLINE");
        }
    }

    private A_2226_Q J_1907_R(String playerName) {
        if (Y_4293_u.c_3005_b.Y_259_p == null || Y_4293_u.c_3005_b.Y_259_p.n_1700_B == null) {
            return null;
        }
        for (A_2226_Q info : Y_4293_u.c_3005_b.Y_259_p.n_1700_B.P_1922_E()) {
            if (!info.n_1700_B().getName().equalsIgnoreCase(playerName)) continue;
            return info;
        }
        return null;
    }

    private boolean n_1700_B(String playerName, A_2226_Q playerInfo, List<String> onlinePlayers) {
        if (playerInfo != null && playerInfo.J_1907_R() == I_14_v.P_1922_E) {
            return false;
        }
        return onlinePlayers.stream().anyMatch(name -> name.equalsIgnoreCase(playerName));
    }

    private boolean J_1907_R(String playerName, A_2226_Q playerInfo, List<String> onlinePlayers) {
        if (playerInfo != null && playerInfo.J_1907_R() == I_14_v.P_1922_E) {
            return true;
        }
        boolean isOnline = onlinePlayers.stream().anyMatch(name -> name.equalsIgnoreCase(playerName));
        return !isOnline && (this.M_182_A.contains(playerName + "_ONLINE") || this.t_1786_h.containsKey(playerName) || this.Q_4569_t.contains(playerName));
    }

    private boolean R_4764_Y(String playerName) {
        for (String s : this.Z_875_P) {
            if (!s.equalsIgnoreCase(playerName)) continue;
            return true;
        }
        return false;
    }

    private String G_564_y(String fragment) {
        if (fragment == null) {
            return null;
        }
        for (String nick : Q_2552_b) {
            if (!nick.equalsIgnoreCase(fragment)) continue;
            return nick;
        }
        return null;
    }

    private boolean R_4764_Y(String playerName, A_2226_Q playerInfo, List<String> onlinePlayers) {
        if (playerInfo != null && playerInfo.J_1907_R() == I_14_v.P_1922_E) {
            return true;
        }
        boolean isOnline = this.n_1700_B(playerName, playerInfo, onlinePlayers);
        return !isOnline && this.R_4764_Y(playerName);
    }

    private boolean P_1922_E(String name) {
        if (name == null) {
            return false;
        }
        for (String k : this.q_2307_F.keySet()) {
            if (!k.equalsIgnoreCase(name)) continue;
            return true;
        }
        return false;
    }

    private x_282_a u_1723_Y(String name) {
        for (Map.Entry<String, D_3612_q> e : this.q_2307_F.entrySet()) {
            if (!e.getKey().equalsIgnoreCase(name)) continue;
            return e.getValue().J_1907_R();
        }
        return new U_2871_b("");
    }

    private x_282_a n_1700_B(A_2226_Q playerInfo, String staffName) {
        if (playerInfo != null && playerInfo.t_148_a() != null) {
            return playerInfo.t_148_a().G_564_y();
        }
        D_3612_q cached = this.t_1786_h.get(staffName);
        if (cached != null) {
            return cached.J_1907_R();
        }
        return new U_2871_b("");
    }

    private List<String> G_564_y() {
        if (Y_4293_u.c_3005_b.Y_259_p == null || Y_4293_u.c_3005_b.Y_259_p.n_1700_B == null) {
            return Collections.emptyList();
        }
        return Y_4293_u.c_3005_b.Y_259_p.n_1700_B.P_1922_E().stream().map(info -> info.n_1700_B().getName()).filter(name -> name != null && !name.isEmpty()).collect(Collectors.toList());
    }

    public void n_1700_B(String message) {
        if (n_1700_B.J_1907_R("Sunrise")) {
            String cleanMessage = message.replaceAll("\u00a7[0-9a-fk-orA-FK-OR]", "").replaceAll("[\u2666\u2665\u272a\u2726\u2756\u262f\u273f\u25c6\u25cf\u25cb\u25a0\u25a1\u25aa\u25ab\u2605\u2606]", "").replaceAll("\u2192|->|\u00bb|:", " ").replaceAll("\\s+", " ").trim();
            String detectedStaffName = this.v_4262_N(cleanMessage);
            if (detectedStaffName != null) {
                this.w_1484_f(detectedStaffName);
            }
        } else if (n_1700_B.J_1907_R("LonyGrief")) {
            this.t_148_a(message);
        }
    }

    private String v_4262_N(String cleanMessage) {
        for (String staffName : h_1847_R) {
            String[] words;
            String lowerStaffName;
            if (cleanMessage.contains(staffName)) {
                return staffName;
            }
            String lowerCleanMessage = cleanMessage.toLowerCase();
            if (lowerCleanMessage.contains(lowerStaffName = staffName.toLowerCase())) {
                return staffName;
            }
            for (String word : words = cleanMessage.split("\\s+")) {
                String cleanWord = word.replaceAll("[^a-zA-Z0-9_]", "");
                if (!cleanWord.toLowerCase().startsWith(lowerStaffName) || cleanWord.length() < lowerStaffName.length()) continue;
                return staffName;
            }
        }
        return null;
    }

    private void w_1484_f(String staffName) {
        boolean alreadyInList = this.multiplayerClientSuggestionProvider.stream().anyMatch(m -> m.n_1700_B().equalsIgnoreCase(staffName));
        boolean alreadyDetected = this.Q_4569_t.contains(staffName);
        if (!alreadyInList && !alreadyDetected) {
            U_3758_B.n_1700_B("O", "\u041e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d \u0441\u0442\u0430\u0444\u0444: " + staffName, H_2506_c.n_1700_B(255, 200, 0));
        }
        this.Q_4569_t.add(staffName);
        A_2226_Q playerInfo = this.J_1907_R(staffName);
        List<String> onlinePlayers = this.G_564_y();
        boolean isOnline = this.n_1700_B(staffName, playerInfo, onlinePlayers);
        boolean isVanished = this.J_1907_R(staffName, playerInfo, onlinePlayers);
        x_282_a prefix = this.n_1700_B(playerInfo, staffName);
        D_3612_q staffMember = new D_3612_q(staffName, prefix, isVanished);
        this.t_1786_h.put(staffName, staffMember);
        if (isOnline && !this.M_182_A.contains(staffName + "_ONLINE")) {
            this.M_182_A.add(staffName + "_ONLINE");
            this.M_182_A.remove(staffName + "_VANISHED");
        }
    }

    private void t_148_a(String message) {
        String knownFromChat;
        String clean = message.replaceAll("\u00a7[0-9a-fk-orA-FK-OR]", "").replaceAll("[\u2666\u2665\u272a\u2726\u2756\u262f\u273f\u25c6\u25cf\u25cb\u25a0\u25a1\u25aa\u25ab\u2605\u2606]", "").replaceAll("\u2192|->|\u00bb|:", " ").replaceAll("\\s+", " ").trim();
        Matcher matcher = Pattern.compile("^[\\[\\(\u00ab]?([^\\]\\)\u00bb\\s]+)[\\]\\)\u00bb]?\\s+(\\w{3,16})").matcher(clean);
        if (matcher.find()) {
            String prefix = matcher.group(1);
            String nick = matcher.group(2);
            if (this.M_588_G(prefix)) {
                String canonical = this.G_564_y(nick);
                this.n_1700_B(canonical != null ? canonical : nick, prefix);
            }
        }
        if ((knownFromChat = this.s_956_w(clean)) != null) {
            this.u_2550_I(knownFromChat);
        }
    }

    private String s_956_w(String cleanMessage) {
        for (String staffName : Q_2552_b) {
            String[] words;
            String lowerStaff;
            if (cleanMessage.contains(staffName)) {
                return staffName;
            }
            String lowerClean = cleanMessage.toLowerCase(Locale.ROOT);
            if (lowerClean.contains(lowerStaff = staffName.toLowerCase(Locale.ROOT))) {
                return staffName;
            }
            for (String word : words = cleanMessage.split("\\s+")) {
                String cleanWord = word.replaceAll("[^a-zA-Z0-9_]", "");
                if (!cleanWord.toLowerCase(Locale.ROOT).startsWith(lowerStaff) || cleanWord.length() < lowerStaff.length()) continue;
                return staffName;
            }
        }
        return null;
    }

    private void u_2550_I(String staffName) {
        if (staffName == null) {
            return;
        }
        boolean alreadyInList = this.C_2741_M.stream().anyMatch(m -> m.n_1700_B().equalsIgnoreCase(staffName));
        boolean alreadyDetected = this.R_4764_Y(staffName);
        if (!alreadyInList && !alreadyDetected) {
            U_3758_B.n_1700_B("O", "\u041e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d \u0441\u0442\u0430\u0444\u0444: " + staffName, H_2506_c.n_1700_B(255, 200, 0));
        }
        this.Z_875_P.add(staffName);
        A_2226_Q playerInfo = this.J_1907_R(staffName);
        U_2871_b prefixComponent = playerInfo != null && playerInfo.t_148_a() != null ? playerInfo.t_148_a().G_564_y() : new U_2871_b("");
        List<String> onlinePlayers = this.G_564_y();
        boolean isVanished = this.R_4764_Y(staffName, playerInfo, onlinePlayers);
        D_3612_q member = new D_3612_q(staffName, prefixComponent, isVanished);
        this.q_2307_F.put(staffName, member);
        if (!isVanished && this.k_2293_S.stream().noneMatch(n -> n.equalsIgnoreCase(staffName))) {
            this.k_2293_S.add(staffName);
        }
    }

    private void n_1700_B(String playerName, String rawPrefix) {
        boolean alreadyInList = this.C_2741_M.stream().anyMatch(m -> m.n_1700_B().equalsIgnoreCase(playerName));
        boolean alreadyDetected = this.R_4764_Y(playerName);
        if (!alreadyInList && !alreadyDetected) {
            U_3758_B.n_1700_B("O", "\u041e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d \u0441\u0442\u0430\u0444\u0444: " + playerName, H_2506_c.n_1700_B(255, 200, 0));
        }
        this.Z_875_P.add(playerName);
        A_2226_Q playerInfo = this.J_1907_R(playerName);
        x_282_a prefixComponent = playerInfo != null && playerInfo.t_148_a() != null ? playerInfo.t_148_a().G_564_y() : new U_2871_b((String)(rawPrefix != null ? rawPrefix + " " : ""));
        List<String> onlinePlayers = this.G_564_y();
        boolean isVanished = this.R_4764_Y(playerName, playerInfo, onlinePlayers);
        D_3612_q member = new D_3612_q(playerName, prefixComponent, isVanished);
        this.q_2307_F.put(playerName, member);
        if (!isVanished && this.k_2293_S.stream().noneMatch(n -> n.equalsIgnoreCase(playerName))) {
            this.k_2293_S.add(playerName);
        }
    }

    private void P_1922_E() {
        long currentTime;
        boolean isOnServer;
        if (Y_4293_u.c_3005_b.Y_259_p == null || c_3005_b.e_4240_b()) {
            return;
        }
        boolean bl = isOnServer = Y_4293_u.c_3005_b.Y_259_p.n_1700_B != null && Y_4293_u.c_3005_b.Y_259_p.n_1700_B.n_1700_B(Y_4293_u.c_3005_b.Y_259_p.y_4642_Y().getId()) != null;
        if (isOnServer && !this.t_4043_B) {
            this.C_2741_M.clear();
            this.q_2307_F.clear();
            this.k_2293_S.clear();
            this.Z_875_P.clear();
        }
        this.t_4043_B = isOnServer;
        if (this.t_4043_B && (currentTime = System.currentTimeMillis()) - this.x_607_J > 3000L) {
            this.u_1723_Y();
            this.x_607_J = currentTime;
        }
    }

    public void n_1700_B() {
        this.n_3318_d = true;
    }

    private void u_1723_Y() {
        D_3612_q member;
        if (Y_4293_u.c_3005_b.Y_259_p == null || Y_4293_u.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        this.e_4240_b = this.n_3318_d;
        this.n_3318_d = false;
        ArrayList<D_3612_q> currentStaff = new ArrayList<D_3612_q>();
        Collection<A_2226_Q> playerInfos = Y_4293_u.c_3005_b.Y_259_p.n_1700_B.P_1922_E();
        List<String> onlinePlayers = this.G_564_y();
        for (A_2226_Q playerInfo : playerInfos) {
            String prefix;
            String playerName;
            if (playerInfo == null || (playerName = playerInfo.n_1700_B().getName()) == null || playerName.isEmpty()) continue;
            String displayText = this.J_1907_R(playerInfo, playerName);
            if (this.e_4240_b) {
                String cleanDisplay = D_4024_W.n_1700_B(displayText);
                String debugPrefix = this.J_1907_R(displayText, playerName);
                String cleanPrefix = D_4024_W.n_1700_B(debugPrefix);
                Y_4293_u.c_3005_b.M_588_G.R_4764_Y().n_1700_B(new U_2871_b("\u00a77[LG Debug] \u00a7fNick: \u00a7a" + playerName + " \u00a7f| Display: \u00a7e" + cleanDisplay + " \u00a7f| Prefix: \u00a7d" + (cleanPrefix.isEmpty() ? "\u2014" : cleanPrefix)));
            }
            if (displayText.isEmpty() || displayText.equals(playerName) || (prefix = this.J_1907_R(displayText, playerName)).isEmpty() || !this.M_588_G(prefix)) continue;
            x_282_a prefixComponent = playerInfo.t_148_a() != null ? playerInfo.t_148_a().G_564_y() : new U_2871_b(prefix);
            boolean isVanished = playerInfo.J_1907_R() == I_14_v.P_1922_E || !onlinePlayers.contains(playerName);
            member = new D_3612_q(playerName, prefixComponent, isVanished);
            currentStaff.add(member);
            this.q_2307_F.put(playerName, member);
            this.J_1907_R(playerName, !isVanished, isVanished);
        }
        for (String chatName : this.Z_875_P) {
            boolean alreadyAdded = currentStaff.stream().anyMatch(s -> s.n_1700_B().equalsIgnoreCase(chatName));
            if (alreadyAdded) continue;
            A_2226_Q chatPlayerInfo = this.J_1907_R(chatName);
            boolean isOnline = this.n_1700_B(chatName, chatPlayerInfo, onlinePlayers);
            boolean isVanished = this.R_4764_Y(chatName, chatPlayerInfo, onlinePlayers);
            if (!isOnline && !isVanished && !this.P_1922_E(chatName)) continue;
            x_282_a prefix = chatPlayerInfo != null && chatPlayerInfo.t_148_a() != null ? chatPlayerInfo.t_148_a().G_564_y() : this.u_1723_Y(chatName);
            member = new D_3612_q(chatName, prefix, isVanished);
            currentStaff.add(member);
            this.q_2307_F.put(chatName, member);
            this.J_1907_R(chatName, isOnline, isVanished);
        }
        Set currentNames = currentStaff.stream().map(D_3612_q::n_1700_B).collect(Collectors.toSet());
        for (String name : new HashSet<String>(this.k_2293_S)) {
            if (currentNames.contains(name)) continue;
            U_3758_B.n_1700_B("O", name + " \u0432\u044b\u0448\u0435\u043b \u0441 \u0441\u0435\u0440\u0432\u0435\u0440\u0430!", H_2506_c.n_1700_B(255, 60, 60));
            this.k_2293_S.remove(name);
            this.q_2307_F.remove(name);
            this.Z_875_P.remove(name);
        }
        currentStaff.sort(Comparator.comparing(D_3612_q::n_1700_B, String.CASE_INSENSITIVE_ORDER));
        this.C_2741_M.clear();
        this.C_2741_M.addAll(currentStaff);
        this.e_4240_b = false;
    }

    private String J_1907_R(A_2226_Q playerInfo, String playerName) {
        if (playerInfo.u_2550_I() != null) {
            return playerInfo.u_2550_I().getString();
        }
        if (playerInfo.t_148_a() != null) {
            String teamPrefix = playerInfo.t_148_a().G_564_y().getString();
            String teamSuffix = playerInfo.t_148_a().P_1922_E().getString();
            return teamPrefix + playerName + teamSuffix;
        }
        return playerName;
    }

    private void J_1907_R(String staffName, boolean isOnline, boolean isVanished) {
        if (isOnline && !this.k_2293_S.contains(staffName)) {
            U_3758_B.n_1700_B("O", staffName + " \u0437\u0430\u0448\u0435\u043b \u043d\u0430 \u0441\u0435\u0440\u0432\u0435\u0440!", H_2506_c.n_1700_B(60, 255, 60));
            this.k_2293_S.add(staffName);
        } else if (!isVanished || this.k_2293_S.contains(staffName)) {
            // empty if block
        }
    }

    private String J_1907_R(String displayName, String playerName) {
        String cleanPlayer;
        if (displayName == null || playerName == null || displayName.equals(playerName)) {
            return "";
        }
        String cleanDisplay = this.P_4830_p(displayName);
        int nameIndex = cleanDisplay.indexOf(cleanPlayer = this.P_4830_p(playerName));
        if (nameIndex > 0) {
            int realPos = this.n_1700_B(displayName, nameIndex);
            return displayName.substring(0, realPos).trim();
        }
        return displayName.replace(playerName, "").trim();
    }

    private boolean M_588_G(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return false;
        }
        String cleanPrefix = this.P_4830_p(prefix.toLowerCase()).replaceAll("[\\[\\](){}\u00ab\u00bb\\s\u00a7]", "");
        return Y_259_p.stream().anyMatch(keyword -> cleanPrefix.contains(keyword.toLowerCase()));
    }

    private String P_4830_p(String text) {
        return text == null ? "" : text.replaceAll("\u00a7[0-9a-fk-or]", "");
    }

    private int n_1700_B(String text, int strippedPosition) {
        int realPos = 0;
        int strippedPos = 0;
        for (int i = 0; i < text.length() && strippedPos < strippedPosition; ++i) {
            if (text.charAt(i) == '\u00a7' && i + 1 < text.length()) {
                ++i;
            } else {
                ++strippedPos;
            }
            realPos = i + 1;
        }
        return realPos;
    }

    private float[] n_1700_B(List<D_3612_q> dimsItems) {
        float avatarBlock;
        int fontSize = 13;
        float maxTextWidth = 0.0f;
        String headerTitle = n_1700_B.J_1907_R("Sunrise") ? "Staff " : (n_1700_B.J_1907_R("LonyGrief") ? "Staff LG" : "Staff");
        maxTextWidth = Math.max(maxTextWidth, l_3370_o.J_1907_R[15].n_1700_B(headerTitle));
        for (D_3612_q item : dimsItems) {
            String plainName = D_4024_W.n_1700_B(item.J_1907_R().getString());
            float prefixWidth = l_3370_o.G_564_y[fontSize].n_1700_B(plainName);
            float nameWidth = l_3370_o.G_564_y[fontSize].n_1700_B(item.n_1700_B());
            maxTextWidth = Math.max(maxTextWidth, prefixWidth + nameWidth);
        }
        float f = avatarBlock = J_1907_R.t_148_a() != false ? 12.0f : 0.0f;
        float width = Math.max(n_1700_B.J_1907_R("Sunrise") ? 70.0f : (n_1700_B.J_1907_R("LonyGrief") ? 80.0f : 60.0f), avatarBlock + maxTextWidth + 18.0f + 5.0f + 4.0f);
        return new float[]{width};
    }

    @Generated
    public Y_4293_u(J_3635_s dragging) {
        this.w_1484_f = dragging;
    }
}


