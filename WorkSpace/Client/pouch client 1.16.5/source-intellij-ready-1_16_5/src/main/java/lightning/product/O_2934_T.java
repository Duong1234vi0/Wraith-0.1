/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 */
package lightning.product;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lightning.product.D_4024_W;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.K_1200_E;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.j_1376_w;
import lightning.product.o_2341_D;
import lightning.product.q_3148_R;
import lightning.product.v_1900_v;
import lightning.product.y_4642_Y;

public class O_2934_T
extends o_2341_D {
    public O_2934_T() {
        super("help");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(ctx -> {
            int start = q_3148_R.n_1700_B(K_1200_E.w_1457_N);
            int end = H_2506_c.J_1907_R(start, 0.5f);
            J_1907_R[] commands = y_4642_Y.R_4764_Y() ? new J_1907_R[]{new J_1907_R("panic", new String[0], lightning.product.O_2934_T$n_1700_B.G_564_y, "p", "\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u0440\u0435\u0436\u0438\u043c \u043f\u0430\u043d\u0438\u043a\u0438"), new J_1907_R("self", new String[0], lightning.product.O_2934_T$n_1700_B.G_564_y, "@self", "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u0440\u0435\u0436\u0438\u043c \u043f\u0430\u043d\u0438\u043a\u0438")} : new J_1907_R[]{new J_1907_R("config", new String[]{"clear", "dir", "list", "load", "remove", "save", "reset"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, "cfg", "\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u043a\u043e\u043d\u0444\u0438\u0433\u0430\u043c\u0438"), new J_1907_R("friend", new String[]{"add <\u043d\u0438\u043a> [\u043a\u0430\u043a \u0432 NP]", "clear", "list", "remove"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, "fr", "\u0414\u0440\u0443\u0437\u044c\u044f; \u0432\u0442\u043e\u0440\u043e\u0439 \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442 \u2014 \u043e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0435\u043c\u044b\u0439 \u043d\u0438\u043a \u0432 NameProtect"), new J_1907_R("macros", new String[]{"add", "clear", "list", "remove"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, "mac", "\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u043c\u0430\u043a\u0440\u043e\u0441\u0430\u043c\u0438"), new J_1907_R("bind", new String[]{"add", "clear", "list", "remove"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, null, "\u041f\u0440\u0438\u0432\u044f\u0437\u043a\u0430 \u043c\u043e\u0434\u0443\u043b\u0435\u0439 \u043a \u043a\u043b\u0430\u0432\u0438\u0448\u0430\u043c"), new J_1907_R("staff", new String[]{"add", "clear", "list", "remove"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, null, "\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u0441\u043f\u0438\u0441\u043a\u043e\u043c \u0430\u0434\u043c\u0438\u043d\u043e\u0432"), new J_1907_R("gps", new String[]{"off", "info", "<x> <z>"}, lightning.product.O_2934_T$n_1700_B.J_1907_R, null, "\u041d\u0430\u0432\u0438\u0433\u0430\u0446\u0438\u044f \u043a \u043a\u043e\u043e\u0440\u0434\u0438\u043d\u0430\u0442\u0430\u043c"), new J_1907_R("waypoint", new String[]{"add", "clear", "list", "remove"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, "way", "\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u0432\u0435\u0439\u043f\u043e\u0438\u043d\u0442\u0430\u043c\u0438"), new J_1907_R("prefix", new String[]{"<prefix>"}, lightning.product.O_2934_T$n_1700_B.R_4764_Y, null, "\u0418\u0437\u043c\u0435\u043d\u0438\u0442\u044c \u043f\u0440\u0435\u0444\u0438\u043a\u0441 \u043a\u043e\u043c\u0430\u043d\u0434"), new J_1907_R("help", new String[0], lightning.product.O_2934_T$n_1700_B.G_564_y, null, "\u0421\u043f\u0438\u0441\u043e\u043a \u043a\u043e\u043c\u0430\u043d\u0434"), new J_1907_R("autocontract", new String[]{"<nickname>"}, lightning.product.O_2934_T$n_1700_B.R_4764_Y, "act", "\u0410\u0432\u0442\u043e-\u043a\u043e\u043d\u0442\u0440\u0430\u043a\u0442 \u043d\u0430 \u0438\u0433\u0440\u043e\u043a\u0430"), new J_1907_R("nuker", new String[]{"add", "remove", "clear", "list"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, "nuk", "\u0411\u043b\u043e\u043a\u0438 \u0434\u043b\u044f Nuker"), new J_1907_R("vclip", new String[]{"<value>", "up", "down", "bd"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, null, "\u0412\u0435\u0440\u0442\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u043a\u043b\u0438\u043f"), new J_1907_R("blockesp", new String[]{"add", "remove", "clear", "list"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, null, "\u0411\u043b\u043e\u043a\u0438 \u0434\u043b\u044f BlockESP"), new J_1907_R("theme", new String[]{"list", "import", "export <name>"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, null, "\u0418\u043c\u043f\u043e\u0440\u0442/\u044d\u043a\u0441\u043f\u043e\u0440\u0442 \u0442\u0435\u043c"), new J_1907_R("panic", new String[0], lightning.product.O_2934_T$n_1700_B.G_564_y, "p", "\u0412\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u0440\u0435\u0436\u0438\u043c \u043f\u0430\u043d\u0438\u043a\u0438"), new J_1907_R("self", new String[0], lightning.product.O_2934_T$n_1700_B.G_564_y, null, "\u0412\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u0440\u0435\u0436\u0438\u043c \u043f\u0430\u043d\u0438\u043a\u0438"), new J_1907_R("neuro", new String[]{"record", "stop", "list", "delete", "export", "import", "dir"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, "nr", "\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u043d\u0435\u0439\u0440\u043e\u0440\u043e\u0442\u0430\u0446\u0438\u044f\u043c\u0438"), new J_1907_R("tp", new String[]{"<player>", "stop"}, lightning.product.O_2934_T$n_1700_B.J_1907_R, null, "\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442 \u043a \u0438\u0433\u0440\u043e\u043a\u0443"), new J_1907_R("fakeplayer", new String[]{"add", "del"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, "fp", "\u0421\u043e\u0437\u0434\u0430\u0442\u044c \u0444\u0435\u0439\u043a \u0438\u0433\u0440\u043e\u043a\u0430"), new J_1907_R("rct", new String[]{"<\u0430\u043d\u0430\u0440\u0445\u0438\u044f>"}, lightning.product.O_2934_T$n_1700_B.R_4764_Y, null, "\u0420\u0435\u043a\u043e\u043d\u043d\u0435\u043a\u0442 HolyWorld \u043b\u0430\u0439\u0442 (.rct \u0438\u043b\u0438 .rct <1\u201363>)"), new J_1907_R("logout", new String[]{"dir"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, null, "\u041f\u0430\u043f\u043a\u0430 LogoutSpots"), new J_1907_R("parse", new String[]{"rw", "bravo", "dir", "list", "clear"}, lightning.product.O_2934_T$n_1700_B.J_1907_R, null, "\u041f\u0430\u0440\u0441\u0435\u0440 \u0434\u043e\u043d\u0430\u0442\u043e\u0432 \u0438\u0437 \u0442\u0430\u0431\u0430"), new J_1907_R("party", new String[]{"create", "leave", "join <code>", "info"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, null, "\u041b\u0438\u0447\u043d\u0430\u044f party-\u043a\u043e\u043c\u043d\u0430\u0442\u0430 (relay)"), new J_1907_R("irc", new String[]{"on", "off", "party <\u0442\u0435\u043a\u0441\u0442>", "<\u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435>"}, lightning.product.O_2934_T$n_1700_B.n_1700_B, null, "IRC-\u0447\u0430\u0442 \u0447\u0435\u0440\u0435\u0437 relay")};
            for (J_1907_R cmd : commands) {
                this.n_1700_B(cmd.n_1700_B, cmd.J_1907_R, cmd.R_4764_Y, cmd.P_1922_E, start, end);
                if (cmd.G_564_y == null) continue;
                this.n_1700_B(cmd.G_564_y, cmd.n_1700_B, start, end);
            }
            return 1;
        });
    }

    private void n_1700_B(String cmd, String[] subcommands, n_1700_B bracketType, String description, int start, int end) {
        MutableComponent name = j_1376_w.n_1700_B("." + cmd, start, end);
        MutableComponent details = new U_2871_b(" ").n_1700_B(Z_1567_W.n_1700_B);
        if (subcommands.length > 0) {
            details.n_1700_B(new U_2871_b(bracketType.P_1922_E).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
            for (int i = 0; i < subcommands.length; ++i) {
                details.n_1700_B(new U_2871_b(subcommands[i]).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
                if (i >= subcommands.length - 1) continue;
                details.n_1700_B(new U_2871_b(" \u00a6 ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
            }
            details.n_1700_B(new U_2871_b(bracketType.u_1723_Y).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
        }
        if (description != null && !description.isEmpty()) {
            details.n_1700_B(new U_2871_b(" - " + description).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.t_148_a)));
        }
        v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(name).n_1700_B(details), new Object[0]);
    }

    private void n_1700_B(String alias, String full, int start, int end) {
        MutableComponent aliasComp = alias.startsWith("@") ? j_1376_w.n_1700_B(alias, start, end) : j_1376_w.n_1700_B("." + alias, start, end);
        MutableComponent map = new U_2871_b(" -> " + full).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
        v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(aliasComp).n_1700_B(map), new Object[0]);
    }

    private static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("(", ")");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("[", "]");
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B("<", ">");
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B("", "");
        private final String P_1922_E;
        private final String u_1723_Y;
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String open, String close) {
            this.P_1922_E = open;
            this.u_1723_Y = close;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            v_4262_N = lightning.product.O_2934_T$n_1700_B.n_1700_B();
        }
    }

    private static class J_1907_R {
        String n_1700_B;
        String[] J_1907_R;
        n_1700_B R_4764_Y;
        String G_564_y;
        String P_1922_E;

        J_1907_R(String name, String[] subcommands, n_1700_B bracketType, String alias, String description) {
            this.n_1700_B = name;
            this.J_1907_R = subcommands;
            this.R_4764_Y = bracketType;
            this.G_564_y = alias;
            this.P_1922_E = description;
        }

        J_1907_R(String name, String[] subcommands, n_1700_B bracketType) {
            this(name, subcommands, bracketType, null, null);
        }
    }
}


