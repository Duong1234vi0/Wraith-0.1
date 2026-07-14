/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.A_2226_Q;
import lightning.product.C_332_W;
import lightning.product.D_4024_W;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.TextColor;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Objective;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.PlayerTeam;
import lightning.product.i_4895_l;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;
import lightning.product.v_4839_y;

public class O_4761_U
extends o_2341_D
implements MinecraftAccess {
    private static final String J_1907_R = "parse\\";
    private static final SimpleDateFormat R_4764_Y = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private static final Pattern G_564_y = Pattern.compile("(?:\u0413\u0420\u0418\u0424|GRIEF|\u0433\u0440\u0438\u0444)\\s*#?(\\d+)", 2);
    private String P_1922_E = null;
    private boolean u_1723_Y = true;
    private static final Map<Character, String> v_4262_N = new LinkedHashMap<Character, String>();
    private static final Map<Character, Integer> w_1484_f = new LinkedHashMap<Character, Integer>();
    private static final String[] t_148_a = new String[]{"KILLER", "ALPHA", "MODER", "HELPER", "ADMIN", "OWNER"};
    private Map<String, String> s_956_w = new HashMap<String, String>();

    public O_4761_U() {
        super("parse");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(ctx -> {
            if (c_3005_b.k_2293_S() == null) {
                v_1900_v.n_1700_B(new U_2871_b("\u041d\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d \u043a \u0441\u0435\u0440\u0432\u0435\u0440\u0443!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
                return 1;
            }
            String serverType = this.P_4830_p();
            this.P_1922_E(serverType);
            return 1;
        });
        builder.then(O_4761_U.n_1700_B("rw").executes(ctx -> {
            this.P_1922_E("reallyworld");
            return 1;
        }));
        builder.then(O_4761_U.n_1700_B("bravo").executes(ctx -> {
            this.P_1922_E("bravo");
            return 1;
        }));
        builder.then(O_4761_U.n_1700_B("other").executes(ctx -> {
            this.P_1922_E("other");
            return 1;
        }));
        builder.then(O_4761_U.n_1700_B("dir").executes(ctx -> {
            O_4761_U.J_1907_R();
            return 1;
        }));
        builder.then(O_4761_U.n_1700_B("list").executes(ctx -> {
            this.u_2550_I();
            return 1;
        }));
        builder.then(O_4761_U.n_1700_B("clear").executes(ctx -> {
            this.M_588_G();
            return 1;
        }));
        builder.then(((LiteralArgumentBuilder)O_4761_U.n_1700_B("grief").then(RequiredArgumentBuilder.argument((String)"name", (ArgumentType)StringArgumentType.greedyString()).executes(ctx -> {
            String griefName = StringArgumentType.getString((CommandContext)ctx, (String)"name");
            this.J_1907_R(griefName);
            return 1;
        }))).executes(ctx -> {
            this.P_1922_E();
            return 1;
        }));
        builder.then(O_4761_U.n_1700_B("griefs").executes(ctx -> {
            this.w_1484_f();
            return 1;
        }));
        builder.then(O_4761_U.n_1700_B("resetgrief").executes(ctx -> {
            this.P_1922_E = null;
            this.u_1723_Y = true;
            v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u0420\u0443\u0447\u043d\u043e\u0439 \u0433\u0440\u0438\u0444 \u0441\u0431\u0440\u043e\u0448\u0435\u043d! \u0410\u0432\u0442\u043e\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u0438\u0435 \u0432\u043a\u043b\u044e\u0447\u0435\u043d\u043e."), new Object[0]);
            String detected = this.u_1723_Y();
            if (detected != null) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0422\u0435\u043a\u0443\u0449\u0438\u0439 \u0433\u0440\u0438\u0444 (\u0430\u0432\u0442\u043e): \u00a7a" + detected), new Object[0]);
            }
            return 1;
        }));
        builder.then(O_4761_U.n_1700_B("debug").executes(ctx -> {
            this.G_564_y();
            return 1;
        }));
        builder.then(O_4761_U.n_1700_B("debugtab").executes(ctx -> {
            this.R_4764_Y();
            return 1;
        }));
    }

    private void R_4764_Y() {
        if (c_3005_b.k_2293_S() == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041d\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d \u043a \u0441\u0435\u0440\u0432\u0435\u0440\u0443!"), new Object[0]);
            return;
        }
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550 \u00a7fDebug Tab List \u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550"), new Object[0]);
        Collection<A_2226_Q> players = c_3005_b.k_2293_S().P_1922_E();
        i_4895_l scoreboard = O_4761_U.c_3005_b.Y_601_j != null ? O_4761_U.c_3005_b.Y_601_j.Q_4569_t() : null;
        int count = 0;
        for (A_2226_Q info : players) {
            String extracted;
            PlayerTeam team;
            if (count++ >= 10) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a78...\u0438 \u0435\u0449\u0451 " + (players.size() - 10) + " \u0438\u0433\u0440\u043e\u043a\u043e\u0432"), new Object[0]);
                break;
            }
            String name = info.n_1700_B().getName();
            String displayName = info.u_2550_I() != null ? info.u_2550_I().getString() : "NULL";
            String teamPrefix = "";
            String teamSuffix = "";
            if (scoreboard != null && (team = scoreboard.w_1484_f(name)) != null) {
                teamPrefix = team.G_564_y().getString();
                teamSuffix = team.P_1922_E().getString();
            }
            v_1900_v.n_1700_B(new U_2871_b("\u00a77Name: \u00a7f[" + name + "]"), new Object[0]);
            v_1900_v.n_1700_B(new U_2871_b("\u00a77Display: \u00a7f[" + displayName + "]"), new Object[0]);
            v_1900_v.n_1700_B(new U_2871_b("\u00a77TeamPrefix: \u00a7f[" + teamPrefix + "]"), new Object[0]);
            v_1900_v.n_1700_B(new U_2871_b("\u00a77TeamSuffix: \u00a7f[" + teamSuffix + "]"), new Object[0]);
            Object fullDisplay = displayName;
            if (displayName.equals("NULL") && !teamPrefix.isEmpty()) {
                fullDisplay = teamPrefix + name + teamSuffix;
            }
            v_1900_v.n_1700_B(new U_2871_b("\u00a77Extracted: \u00a7a" + ((extracted = this.n_1700_B((String)fullDisplay, name)) != null ? extracted : "null")), new Object[0]);
            v_1900_v.n_1700_B(new U_2871_b("\u00a78---"), new Object[0]);
        }
    }

    private void G_564_y() {
        if (O_4761_U.c_3005_b.Y_601_j == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041c\u0438\u0440 \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d"), new Object[0]);
            return;
        }
        i_4895_l scoreboard = O_4761_U.c_3005_b.Y_601_j.Q_4569_t();
        if (scoreboard == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u0421\u043a\u043e\u0440\u0431\u043e\u0440\u0434 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d"), new Object[0]);
            return;
        }
        Objective objective = scoreboard.n_1700_B(1);
        if (objective == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7cSidebar objective \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d"), new Object[0]);
            return;
        }
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550 \u00a7fDebug Scoreboard \u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550"), new Object[0]);
        String titleRaw = objective.G_564_y().getString();
        String titleClean = D_4024_W.n_1700_B(titleRaw);
        v_1900_v.n_1700_B(new U_2871_b("\u00a77Title (raw): \u00a7f" + titleRaw), new Object[0]);
        v_1900_v.n_1700_B(new U_2871_b("\u00a77Title (clean): \u00a7f" + titleClean), new Object[0]);
        String detected = this.R_4764_Y(titleRaw);
        if (detected == null) {
            detected = this.R_4764_Y(titleClean);
        }
        v_1900_v.n_1700_B(new U_2871_b("\u00a77Detected grief: \u00a7f" + (detected != null ? detected : "null")), new Object[0]);
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0421\u0442\u0440\u043e\u043a\u0438 \u0441\u043a\u043e\u0440\u0431\u043e\u0440\u0434\u0430:"), new Object[0]);
        Collection<v_4839_y> scores = scoreboard.n_1700_B(objective);
        int i = 0;
        for (v_4839_y score : scores) {
            if (i++ > 5) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a78...\u0438 \u0435\u0449\u0451 " + (scores.size() - 5)), new Object[0]);
                break;
            }
            String line = this.n_1700_B(scoreboard, score.P_1922_E());
            v_1900_v.n_1700_B(new U_2871_b("\u00a78- \u00a7f" + line), new Object[0]);
        }
    }

    private void J_1907_R(String name) {
        String safeName;
        this.P_1922_E = safeName = name.replaceAll("[^a-zA-Z\u0430-\u044f\u0410-\u042f0-9._-]", "_");
        this.u_1723_Y = false;
        v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u0413\u0440\u0438\u0444 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d: \u00a7f" + safeName), new Object[0]);
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0410\u0432\u0442\u043e\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u0438\u0435 \u0433\u0440\u0438\u0444\u0430: \u00a7c\u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u043e"), new Object[0]);
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0422\u0435\u043f\u0435\u0440\u044c \u043f\u0430\u0440\u0441\u0438\u043d\u0433 \u0431\u0443\u0434\u0435\u0442 \u0441\u043e\u0445\u0440\u0430\u043d\u044f\u0442\u044c\u0441\u044f \u0432: \u00a7f" + this.t_148_a() + "\\" + safeName + "\\"), new Object[0]);
    }

    private void P_1922_E() {
        String detected = this.u_1723_Y();
        if (this.u_1723_Y) {
            if (detected != null) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0422\u0435\u043a\u0443\u0449\u0438\u0439 \u0433\u0440\u0438\u0444 (\u0430\u0432\u0442\u043e): \u00a7a" + detected), new Object[0]);
            } else {
                v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0422\u0435\u043a\u0443\u0449\u0438\u0439 \u0433\u0440\u0438\u0444: \u00a7f\u043d\u0435 \u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0451\u043d (\u043d\u0435\u0442 \u043d\u0430 \u0433\u0440\u0438\u0444\u0435)"), new Object[0]);
            }
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0410\u0432\u0442\u043e\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u0438\u0435: \u00a7a\u0432\u043a\u043b\u044e\u0447\u0435\u043d\u043e"), new Object[0]);
        } else {
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0422\u0435\u043a\u0443\u0449\u0438\u0439 \u0433\u0440\u0438\u0444 (\u0440\u0443\u0447\u043d\u043e\u0439): \u00a7f" + (this.P_1922_E != null ? this.P_1922_E : "\u043d\u0435 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d")), new Object[0]);
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0410\u0432\u0442\u043e\u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u0438\u0435: \u00a7c\u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d\u043e"), new Object[0]);
            if (detected != null) {
                v_1900_v.n_1700_B(new U_2871_b("\u00a77\u041d\u0430 \u0441\u043a\u043e\u0440\u0431\u043e\u0440\u0434\u0435: \u00a7f" + detected), new Object[0]);
            }
        }
    }

    private String u_1723_Y() {
        if (O_4761_U.c_3005_b.Y_601_j == null) {
            return null;
        }
        i_4895_l scoreboard = O_4761_U.c_3005_b.Y_601_j.Q_4569_t();
        if (scoreboard == null) {
            return null;
        }
        Objective objective = scoreboard.n_1700_B(1);
        if (objective == null) {
            return null;
        }
        String titleRaw = objective.G_564_y().getString();
        String griefNum = this.R_4764_Y(titleRaw);
        if (griefNum != null) {
            return griefNum;
        }
        String titleClean = D_4024_W.n_1700_B(titleRaw);
        griefNum = this.R_4764_Y(titleClean);
        if (griefNum != null) {
            return griefNum;
        }
        Collection<v_4839_y> scores = scoreboard.n_1700_B(objective);
        for (v_4839_y score : scores) {
            String line;
            String playerName = score.P_1922_E();
            if (playerName == null || (line = this.n_1700_B(scoreboard, playerName)) == null || line.isEmpty() || (griefNum = this.R_4764_Y(line)) == null) continue;
            return griefNum;
        }
        return null;
    }

    private String R_4764_Y(String text) {
        String nums;
        if (text == null || text.isEmpty()) {
            return null;
        }
        Matcher matcher = G_564_y.matcher(text);
        if (matcher.find()) {
            return "grief-" + matcher.group(1);
        }
        Pattern hashPattern = Pattern.compile("#\\s*(\\d+)");
        matcher = hashPattern.matcher(text);
        if (matcher.find()) {
            return "grief-" + matcher.group(1);
        }
        String lower = text.toLowerCase();
        if ((lower.contains("\u0433\u0440\u0438\u0444") || lower.contains("grief")) && !(nums = text.replaceAll("[^0-9]", "")).isEmpty() && nums.length() <= 2) {
            try {
                int n = Integer.parseInt(nums);
                if (n > 0 && n < 100) {
                    return "grief-" + n;
                }
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return null;
    }

    private String n_1700_B(i_4895_l scoreboard, String playerName) {
        PlayerTeam team = scoreboard.w_1484_f(playerName);
        if (team != null) {
            String prefix = D_4024_W.n_1700_B(team.G_564_y().getString());
            String suffix = D_4024_W.n_1700_B(team.P_1922_E().getString());
            String name = D_4024_W.n_1700_B(playerName);
            return (prefix != null ? prefix : "") + (name != null ? name : "") + (suffix != null ? suffix : "");
        }
        return D_4024_W.n_1700_B(playerName);
    }

    private String v_4262_N() {
        if (this.u_1723_Y) {
            return this.u_1723_Y();
        }
        return this.P_1922_E;
    }

    private void w_1484_f() {
        String server = this.t_148_a();
        if (server == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041d\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d \u043a \u0441\u0435\u0440\u0432\u0435\u0440\u0443!"), new Object[0]);
            return;
        }
        File serverDir = new File(C_332_W.n_1700_B + J_1907_R + server);
        if (!serverDir.exists() || !serverDir.isDirectory()) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u041d\u0435\u0442 \u0441\u043e\u0445\u0440\u0430\u043d\u0451\u043d\u043d\u044b\u0445 \u0433\u0440\u0438\u0444\u043e\u0432 \u0434\u043b\u044f " + server), new Object[0]);
            return;
        }
        File[] griefDirs = serverDir.listFiles(File::isDirectory);
        if (griefDirs == null || griefDirs.length == 0) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u041d\u0435\u0442 \u0441\u043e\u0445\u0440\u0430\u043d\u0451\u043d\u043d\u044b\u0445 \u0433\u0440\u0438\u0444\u043e\u0432 \u0434\u043b\u044f " + server), new Object[0]);
            return;
        }
        String activeGrief = this.v_4262_N();
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550 \u00a7f\u0413\u0440\u0438\u0444\u044b \u043d\u0430 " + server + " \u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550"), new Object[0]);
        for (File griefDir : griefDirs) {
            String griefName = griefDir.getName();
            int donateCount = this.n_1700_B(griefDir);
            int rankCount = this.J_1907_R(griefDir);
            String date = R_4764_Y.format(new Date(griefDir.lastModified()));
            String marker = griefName.equals(activeGrief) ? "\u00a7a\u25ba " : "\u00a77  ";
            v_1900_v.n_1700_B(new U_2871_b(marker + "\u00a7f" + griefName + " \u00a77- " + donateCount + " \u0434\u043e\u043d\u0430\u0442\u0435\u0440\u043e\u0432 \u00a78(" + rankCount + " \u0440\u0430\u043d\u0433\u043e\u0432, " + date + ")"), new Object[0]);
        }
        if (activeGrief != null) {
            String mode = this.u_1723_Y ? "\u0430\u0432\u0442\u043e" : "\u0440\u0443\u0447\u043d\u043e\u0439";
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0422\u0435\u043a\u0443\u0449\u0438\u0439 \u0433\u0440\u0438\u0444 (" + mode + "): \u00a7a" + activeGrief), new Object[0]);
        }
    }

    private String t_148_a() {
        if (c_3005_b.t_4043_B() == null) {
            return null;
        }
        String ip = O_4761_U.c_3005_b.t_4043_B().J_1907_R;
        if (ip == null || ip.isEmpty()) {
            return null;
        }
        return ip.toLowerCase().replaceAll(":\\d+$", "").replaceAll("[^a-z0-9.-]", "_");
    }

    private String s_956_w() {
        String server = this.t_148_a();
        if (server == null) {
            return null;
        }
        String grief = this.v_4262_N();
        if (grief != null) {
            return J_1907_R + server + "\\" + grief + "\\";
        }
        return J_1907_R + server + "\\";
    }

    private String G_564_y(String rank) {
        String serverDir = this.s_956_w();
        if (serverDir == null) {
            return null;
        }
        String safeRank = rank.replaceAll("[^a-zA-Z0-9._+-]", "_");
        return serverDir + safeRank + ".txt";
    }

    public static void J_1907_R() {
        try {
            File dir = new File(C_332_W.n_1700_B + J_1907_R);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Runtime.getRuntime().exec("explorer " + dir.getAbsolutePath());
            v_1900_v.n_1700_B(new U_2871_b("\u041f\u0430\u043f\u043a\u0430 Parse \u043e\u0442\u043a\u0440\u044b\u0442\u0430!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I)), new Object[0]);
        }
        catch (IOException e) {
            v_1900_v.n_1700_B(new U_2871_b("\u041e\u0448\u0438\u0431\u043a\u0430 \u043e\u0442\u043a\u0440\u044b\u0442\u0438\u044f \u043f\u0430\u043f\u043a\u0438!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
        }
    }

    private void u_2550_I() {
        File dir = new File(C_332_W.n_1700_B + J_1907_R);
        if (!dir.exists() || !dir.isDirectory()) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u041d\u0435\u0442 \u0441\u043e\u0445\u0440\u0430\u043d\u0451\u043d\u043d\u044b\u0445 \u0441\u0435\u0440\u0432\u0435\u0440\u043e\u0432"), new Object[0]);
            return;
        }
        File[] serverDirs = dir.listFiles(File::isDirectory);
        if (serverDirs == null || serverDirs.length == 0) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u041d\u0435\u0442 \u0441\u043e\u0445\u0440\u0430\u043d\u0451\u043d\u043d\u044b\u0445 \u0441\u0435\u0440\u0432\u0435\u0440\u043e\u0432"), new Object[0]);
            return;
        }
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550 \u00a7f\u0421\u043e\u0445\u0440\u0430\u043d\u0451\u043d\u043d\u044b\u0435 \u0441\u0435\u0440\u0432\u0435\u0440\u0430 \u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550"), new Object[0]);
        for (File serverDir : serverDirs) {
            String serverName = serverDir.getName();
            File[] griefDirs = serverDir.listFiles(File::isDirectory);
            File[] txtFiles = serverDir.listFiles((d, name) -> name.endsWith(".txt"));
            if (griefDirs != null && griefDirs.length > 0) {
                int totalDonates = 0;
                for (File griefDir : griefDirs) {
                    totalDonates += this.n_1700_B(griefDir);
                }
                v_1900_v.n_1700_B(new U_2871_b("\u00a7f" + serverName + " \u00a77- " + totalDonates + " \u0434\u043e\u043d\u0430\u0442\u0435\u0440\u043e\u0432 \u00a78(" + griefDirs.length + " \u0433\u0440\u0438\u0444\u043e\u0432)"), new Object[0]);
                continue;
            }
            if (txtFiles == null || txtFiles.length <= 0) continue;
            int donateCount = this.n_1700_B(serverDir);
            int rankCount = this.J_1907_R(serverDir);
            String date = R_4764_Y.format(new Date(serverDir.lastModified()));
            v_1900_v.n_1700_B(new U_2871_b("\u00a7f" + serverName + " \u00a77- " + donateCount + " \u0434\u043e\u043d\u0430\u0442\u0435\u0440\u043e\u0432 \u00a78(" + rankCount + " \u0440\u0430\u043d\u0433\u043e\u0432, " + date + ")"), new Object[0]);
        }
    }

    private int n_1700_B(File serverDir) {
        int count = 0;
        File[] files = serverDir.listFiles((d, name) -> name.endsWith(".txt") && !name.equals("all.txt"));
        if (files == null) {
            return 0;
        }
        for (File file : files) {
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                count += lines.size();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return count;
    }

    private int J_1907_R(File serverDir) {
        File[] files = serverDir.listFiles((d, name) -> name.endsWith(".txt") && !name.equals("all.txt"));
        return files != null ? files.length : 0;
    }

    private void M_588_G() {
        String serverDir = this.s_956_w();
        if (serverDir == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041d\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d \u043a \u0441\u0435\u0440\u0432\u0435\u0440\u0443!"), new Object[0]);
            return;
        }
        File dir = new File(C_332_W.n_1700_B + serverDir);
        if (dir.exists() && dir.isDirectory()) {
            this.R_4764_Y(dir);
            String activeGrief = this.v_4262_N();
            String clearedPath = activeGrief != null ? this.t_148_a() + "\\" + activeGrief : this.t_148_a();
            v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u0414\u0430\u043d\u043d\u044b\u0435 \u0434\u043b\u044f " + clearedPath + " \u043e\u0447\u0438\u0449\u0435\u043d\u044b!"), new Object[0]);
        } else {
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u041d\u0435\u0442 \u0434\u0430\u043d\u043d\u044b\u0445 \u0434\u043b\u044f \u044d\u0442\u043e\u0433\u043e \u0441\u0435\u0440\u0432\u0435\u0440\u0430/\u0433\u0440\u0438\u0444\u0430"), new Object[0]);
        }
    }

    private void R_4764_Y(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    this.R_4764_Y(file);
                    continue;
                }
                file.delete();
            }
        }
        dir.delete();
    }

    private String P_4830_p() {
        if (c_3005_b.t_4043_B() == null) {
            return "other";
        }
        String ip = O_4761_U.c_3005_b.t_4043_B().J_1907_R.toLowerCase();
        if (ip.contains("reallyworld") || ip.contains("rw")) {
            return "reallyworld";
        }
        if (ip.contains("bravo") || ip.contains("hvh")) {
            return "bravo";
        }
        if (ip.contains("holyworld") || ip.contains("holy")) {
            return "holyworld";
        }
        return "other";
    }

    private void P_1922_E(String serverType) {
        if (c_3005_b.k_2293_S() == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041d\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d \u043a \u0441\u0435\u0440\u0432\u0435\u0440\u0443!"), new Object[0]);
            return;
        }
        this.s_956_w.clear();
        LinkedHashMap<String, List<String>> rankPlayers = new LinkedHashMap<String, List<String>>();
        Collection<A_2226_Q> players = c_3005_b.k_2293_S().P_1922_E();
        String serverName = this.t_148_a();
        String activeGrief = this.v_4262_N();
        Object griefInfo = activeGrief != null ? " / " + activeGrief : "";
        v_1900_v.n_1700_B(new U_2871_b(""), new Object[0]);
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550 \u00a7fParse Donates \u00a77[" + (String)(serverName != null ? serverName + (String)griefInfo : serverType) + "] \u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550"), new Object[0]);
        int totalDonates = 0;
        int newDonates = 0;
        Map<String, Set<String>> existingData = this.h_1847_R();
        i_4895_l scoreboard = O_4761_U.c_3005_b.Y_601_j != null ? O_4761_U.c_3005_b.Y_601_j.Q_4569_t() : null;
        for (A_2226_Q a_2226_Q : players) {
            String[] suffix22;
            Object prefix;
            PlayerTeam team;
            Object displayName;
            String name = a_2226_Q.n_1700_B().getName();
            Object object = displayName = a_2226_Q.u_2550_I() != null ? a_2226_Q.u_2550_I().getString() : null;
            if (displayName == null && scoreboard != null && (team = scoreboard.w_1484_f(name)) != null) {
                prefix = team.G_564_y().getString();
                suffix22 = team.P_1922_E().getString();
                if (!((String)prefix).isEmpty() || !suffix22.isEmpty()) {
                    displayName = (String)prefix + name + (String)suffix22;
                }
            }
            if (displayName == null) {
                displayName = name;
            }
            String rank = null;
            if (serverType.equals("reallyworld")) {
                prefix = ((String)displayName).toCharArray();
                int suffix22 = ((char[])prefix).length;
                for (var18_25 = 0; var18_25 < suffix22; ++var18_25) {
                    c = prefix[var18_25];
                    if (!v_4262_N.containsKey(Character.valueOf((char)c))) continue;
                    rank = v_4262_N.get(Character.valueOf((char)c));
                    break;
                }
            } else if (serverType.equals("bravo")) {
                String afterLevel;
                int nextSpace;
                int spaceIdx;
                String upper = ((String)displayName).toUpperCase();
                suffix22 = t_148_a;
                var18_25 = suffix22.length;
                for (c = false; c < var18_25; c = (Object)(c + 1)) {
                    String r = suffix22[c];
                    if (!upper.contains(r)) continue;
                    rank = r;
                    break;
                }
                if (rank == null && (upper.startsWith("I ") || upper.startsWith("II ") || upper.startsWith("III ") || upper.startsWith("X ") || upper.startsWith("V ")) && (spaceIdx = ((String)displayName).indexOf(32)) > 0 && spaceIdx < ((String)displayName).length() - 1 && (nextSpace = (afterLevel = ((String)displayName).substring(spaceIdx + 1)).indexOf(32)) > 0) {
                    rank = afterLevel.substring(0, nextSpace).toUpperCase();
                }
            } else if (serverType.equals("holyworld")) {
                rank = this.n_1700_B((String)displayName, name);
                if (rank == null && (rank = this.u_1723_Y((String)displayName)) != null) {
                    continue;
                }
            } else {
                rank = this.n_1700_B((String)displayName, name);
            }
            if (rank == null || rank.equals("PLAYER")) continue;
            rankPlayers.computeIfAbsent(rank, k -> new ArrayList()).add(name);
            ++totalDonates;
            Set<String> existingForRank = existingData.get(rank);
            if (existingForRank != null && existingForRank.contains(name)) continue;
            ++newDonates;
        }
        if (rankPlayers.isEmpty()) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0414\u043e\u043d\u0430\u0442\u0435\u0440\u044b \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u044b"), new Object[0]);
        } else {
            for (Map.Entry entry : rankPlayers.entrySet()) {
                String rank = (String)entry.getKey();
                List names = (List)entry.getValue();
                int color = this.J_1907_R(rank, serverType);
                MutableComponent rankText = new U_2871_b("\u00a77[").n_1700_B(new U_2871_b(rank).n_1700_B(Z_1567_W.n_1700_B.n_1700_B(TextColor.n_1700_B(color)))).n_1700_B(new U_2871_b("\u00a77] \u00a7f(" + names.size() + "): "));
                StringBuilder playerList = new StringBuilder();
                Set<String> existingForRank = existingData.get(rank);
                for (int i = 0; i < names.size(); ++i) {
                    boolean isNew;
                    String playerName = (String)names.get(i);
                    boolean bl = isNew = existingForRank == null || !existingForRank.contains(playerName);
                    if (isNew) {
                        playerList.append("\u00a7a").append(playerName).append("\u00a7a[NEW]");
                    } else {
                        playerList.append(playerName);
                    }
                    if (i >= names.size() - 1) continue;
                    playerList.append("\u00a77, \u00a7f");
                }
                rankText.n_1700_B(new U_2871_b(playerList.toString()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)));
                v_1900_v.n_1700_B(rankText, new Object[0]);
            }
        }
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0412\u0441\u0435\u0433\u043e: \u00a7f" + totalDonates + " \u00a77(\u043d\u043e\u0432\u044b\u0445: \u00a7a" + newDonates + "\u00a77) \u0438\u0437 \u00a7f" + players.size()), new Object[0]);
        if (serverName != null) {
            this.n_1700_B(rankPlayers, existingData, serverType);
            String savePath = activeGrief != null ? serverName + "\\" + activeGrief + "\\" : serverName + "\\";
            int n = serverType.equals("holyworld") ? 1 : rankPlayers.size();
            v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0421\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u043e \u0432: \u00a7f" + savePath + " \u00a78(" + n + " \u0444\u0430\u0439\u043b\u043e\u0432)"), new Object[0]);
        }
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550"), new Object[0]);
    }

    private Map<String, Set<String>> h_1847_R() {
        HashMap<String, Set<String>> result = new HashMap<String, Set<String>>();
        String serverDir = this.s_956_w();
        if (serverDir == null) {
            return result;
        }
        File dir = new File(C_332_W.n_1700_B + serverDir);
        if (!dir.exists() || !dir.isDirectory()) {
            return result;
        }
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt") && !name.equals("all.txt"));
        if (files == null) {
            return result;
        }
        for (File file : files) {
            try {
                String rank = file.getName().replace(".txt", "");
                List<String> lines = Files.readAllLines(file.toPath());
                HashSet<String> players = new HashSet<String>(lines);
                players.removeIf(String::isEmpty);
                result.put(rank, players);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return result;
    }

    private void n_1700_B(Map<String, List<String>> newData, Map<String, Set<String>> existingData, String serverType) {
        String serverDir = this.s_956_w();
        if (serverDir == null) {
            return;
        }
        try {
            for (Map.Entry<String, List<String>> entry : newData.entrySet()) {
                Set existing = existingData.computeIfAbsent(entry.getKey(), k -> new HashSet());
                existing.addAll((Collection)entry.getValue());
            }
            File dir = new File(C_332_W.n_1700_B + serverDir);
            dir.mkdirs();
            ArrayList<CallSite> allPlayers = new ArrayList<CallSite>();
            for (Map.Entry entry : existingData.entrySet()) {
                String filePath;
                String rank = (String)entry.getKey();
                Set players = (Set)entry.getValue();
                if (!serverType.equals("holyworld") && (filePath = this.G_564_y(rank)) != null) {
                    File file = new File(C_332_W.n_1700_B + filePath);
                    ArrayList sortedPlayers = new ArrayList(players);
                    Collections.sort(sortedPlayers);
                    Files.write(file.toPath(), sortedPlayers, new OpenOption[0]);
                }
                for (String player : players) {
                    allPlayers.add((CallSite)((Object)(player + " [" + rank + "]")));
                }
            }
            if (!allPlayers.isEmpty()) {
                File allFile = new File(C_332_W.n_1700_B + serverDir + "all.txt");
                Collections.sort(allPlayers);
                Files.write(allFile.toPath(), allPlayers, new OpenOption[0]);
            }
        }
        catch (Exception e) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041e\u0448\u0438\u0431\u043a\u0430 \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u044f: " + e.getMessage()), new Object[0]);
        }
    }

    private String u_1723_Y(String display) {
        String[] words;
        if (display == null) {
            return null;
        }
        if (!display.contains("---") && !display.contains("$")) {
            return null;
        }
        String clean = D_4024_W.n_1700_B(display);
        if (clean == null) {
            return null;
        }
        clean = clean.replaceAll("[$\\-\u2605\\s]", " ").trim();
        if ((clean = clean.replaceAll("\\s+", " ")).isEmpty()) {
            return null;
        }
        for (String word : words = clean.split("\\s+")) {
            if (word.length() < 3 || !word.matches("[A-Za-z\u0410-\u042f\u0430-\u044f\u0401\u0451]+")) continue;
            return word.toUpperCase();
        }
        return null;
    }

    private String n_1700_B(String displayName, String realName) {
        String[] words;
        if (displayName == null || realName == null) {
            return null;
        }
        String display = D_4024_W.n_1700_B(displayName);
        if (display == null) {
            display = displayName;
        }
        if ((display = display.trim()).equalsIgnoreCase(realName)) {
            return null;
        }
        int nameIdx = display.toLowerCase().indexOf(realName.toLowerCase());
        if (nameIdx > 0) {
            String prefix = display.substring(0, nameIdx).trim();
            String[] parts = prefix.split("[\\s\\p{Punct}\u2605$\\-]+");
            for (int i = parts.length - 1; i >= 0; --i) {
                String letters;
                String part = parts[i].trim();
                if (part.isEmpty() || part.length() < 2 || (letters = part.replaceAll("[^\\p{L}]", "")).length() < 2) continue;
                return letters.toUpperCase();
            }
        }
        for (String word : words = display.split("[\\s\\p{Punct}\u2605$\\-]+")) {
            String letters;
            if ((word = word.trim()).isEmpty() || word.equalsIgnoreCase(realName) || (letters = word.replaceAll("[^\\p{L}]", "")).length() < 3 || letters.equalsIgnoreCase(realName)) continue;
            return letters.toUpperCase();
        }
        return null;
    }

    private int v_4262_N(String rank) {
        return switch (rank.toUpperCase()) {
            case "KILLER" -> H_2506_c.n_1700_B(255, 85, 85);
            case "ALPHA" -> H_2506_c.n_1700_B(85, 255, 85);
            case "MODER" -> H_2506_c.n_1700_B(85, 85, 255);
            case "HELPER" -> H_2506_c.n_1700_B(255, 255, 85);
            case "ADMIN" -> H_2506_c.n_1700_B(255, 85, 85);
            case "OWNER" -> H_2506_c.n_1700_B(170, 0, 0);
            case "VIP" -> H_2506_c.n_1700_B(85, 255, 85);
            case "PREMIUM" -> H_2506_c.n_1700_B(255, 170, 0);
            case "MVP" -> H_2506_c.n_1700_B(85, 255, 255);
            case "ELITE" -> H_2506_c.n_1700_B(255, 85, 255);
            default -> H_2506_c.n_1700_B(170, 170, 170);
        };
    }

    private int J_1907_R(String rank, String serverType) {
        if (serverType.equals("reallyworld")) {
            for (Map.Entry<Character, String> entry : v_4262_N.entrySet()) {
                if (!entry.getValue().equals(rank)) continue;
                return w_1484_f.getOrDefault(entry.getKey(), -1);
            }
        }
        return this.v_4262_N(rank);
    }

    static {
        v_4262_N.put(Character.valueOf('\ua500'), "PLAYER");
        v_4262_N.put(Character.valueOf('\ua504'), "HERO");
        v_4262_N.put(Character.valueOf('\ua508'), "TITAN");
        v_4262_N.put(Character.valueOf('\ua512'), "AVENGER");
        v_4262_N.put(Character.valueOf('\ua516'), "OVERLORD");
        v_4262_N.put(Character.valueOf('\ua520'), "MAGISTER");
        v_4262_N.put(Character.valueOf('\ua524'), "IMPERATOR");
        v_4262_N.put(Character.valueOf('\ua528'), "DRAGON");
        v_4262_N.put(Character.valueOf('\ua560'), "D.HELPER");
        v_4262_N.put(Character.valueOf('\ua532'), "BULL");
        v_4262_N.put(Character.valueOf('\ua536'), "TIGER");
        v_4262_N.put(Character.valueOf('\ua544'), "DRACULA");
        v_4262_N.put(Character.valueOf('\ua556'), "BUNNY");
        v_4262_N.put(Character.valueOf('\ua548'), "COBRA");
        v_4262_N.put(Character.valueOf('\ua540'), "HYDRA");
        v_4262_N.put(Character.valueOf('\ua552'), "RABBIT");
        v_4262_N.put(Character.valueOf('\ua509'), "HELPER");
        v_4262_N.put(Character.valueOf('\ua513'), "ML.MODER");
        v_4262_N.put(Character.valueOf('\ua517'), "MODER");
        v_4262_N.put(Character.valueOf('\ua521'), "MODER+");
        v_4262_N.put(Character.valueOf('\ua525'), "ST.MODER");
        v_4262_N.put(Character.valueOf('\ua529'), "GL.MODER");
        v_4262_N.put(Character.valueOf('\ua533'), "ML.ADMIN");
        v_4262_N.put(Character.valueOf('\ua537'), "ADMIN");
        v_4262_N.put(Character.valueOf('\ua501'), "MEDIA");
        v_4262_N.put(Character.valueOf('\ua505'), "YT");
        v_4262_N.put(Character.valueOf('\ua541'), "GOD");
        v_4262_N.put(Character.valueOf('\ua549'), "PEGAS");
        w_1484_f.put(Character.valueOf('\ua549'), H_2506_c.n_1700_B(255, 140, 0));
        w_1484_f.put(Character.valueOf('\ua500'), H_2506_c.n_1700_B(120, 120, 120));
        w_1484_f.put(Character.valueOf('\ua504'), H_2506_c.n_1700_B(100, 113, 251));
        w_1484_f.put(Character.valueOf('\ua508'), H_2506_c.n_1700_B(214, 200, 42));
        w_1484_f.put(Character.valueOf('\ua512'), H_2506_c.n_1700_B(101, 189, 56));
        w_1484_f.put(Character.valueOf('\ua516'), H_2506_c.n_1700_B(64, 151, 214));
        w_1484_f.put(Character.valueOf('\ua520'), H_2506_c.n_1700_B(202, 130, 60));
        w_1484_f.put(Character.valueOf('\ua524'), H_2506_c.n_1700_B(202, 60, 60));
        w_1484_f.put(Character.valueOf('\ua528'), H_2506_c.n_1700_B(245, 51, 238));
        w_1484_f.put(Character.valueOf('\ua560'), H_2506_c.n_1700_B(214, 200, 42));
        w_1484_f.put(Character.valueOf('\ua532'), H_2506_c.n_1700_B(121, 81, 202));
        w_1484_f.put(Character.valueOf('\ua536'), H_2506_c.n_1700_B(202, 130, 60));
        w_1484_f.put(Character.valueOf('\ua544'), H_2506_c.n_1700_B(202, 60, 60));
        w_1484_f.put(Character.valueOf('\ua556'), H_2506_c.n_1700_B(68, 65, 66));
        w_1484_f.put(Character.valueOf('\ua548'), H_2506_c.n_1700_B(127, 214, 86));
        w_1484_f.put(Character.valueOf('\ua540'), H_2506_c.n_1700_B(92, 120, 7));
        w_1484_f.put(Character.valueOf('\ua552'), H_2506_c.n_1700_B(120, 120, 120));
        w_1484_f.put(Character.valueOf('\ua509'), H_2506_c.n_1700_B(214, 200, 42));
        w_1484_f.put(Character.valueOf('\ua513'), H_2506_c.n_1700_B(100, 113, 251));
        w_1484_f.put(Character.valueOf('\ua517'), H_2506_c.n_1700_B(100, 113, 251));
        w_1484_f.put(Character.valueOf('\ua521'), H_2506_c.n_1700_B(121, 81, 202));
        w_1484_f.put(Character.valueOf('\ua525'), H_2506_c.n_1700_B(100, 113, 251));
        w_1484_f.put(Character.valueOf('\ua529'), H_2506_c.n_1700_B(121, 81, 202));
        w_1484_f.put(Character.valueOf('\ua533'), H_2506_c.n_1700_B(64, 151, 214));
        w_1484_f.put(Character.valueOf('\ua537'), H_2506_c.n_1700_B(202, 60, 60));
        w_1484_f.put(Character.valueOf('\ua501'), H_2506_c.n_1700_B(121, 81, 202));
        w_1484_f.put(Character.valueOf('\ua505'), H_2506_c.n_1700_B(255, 255, 255));
        w_1484_f.put(Character.valueOf('\ua541'), H_2506_c.n_1700_B(214, 200, 42));
    }
}



