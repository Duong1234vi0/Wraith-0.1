/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import lightning.product.D_4024_W;
import lightning.product.G_564_y;
import lightning.product.J_1907_R;
import lightning.product.P_1922_E;
import lightning.product.P_4830_p;
import lightning.product.R_4764_Y;
import lightning.product.T_2915_h;
import lightning.product.U_2871_b;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.e_869_m;
import lightning.product.g_2336_b;
import lightning.product.h_1847_R;
import lightning.product.k_2603_m;
import lightning.product.n_1700_B;
import lightning.product.o_2341_D;
import lightning.product.s_956_w;
import lightning.product.t_148_a;
import lightning.product.u_2550_I;
import lightning.product.v_1900_v;
import lightning.product.v_4262_N;
import lightning.product.w_1457_N;
import mods.proxy.Config;
import mods.proxy.Proxy;
import mods.proxy.ProxyServer;
import mods.proxy.TestPing;

public class F_3572_x
extends o_2341_D {
    private static final MinecraftClient J_1907_R = MinecraftClient.A_4115_X();
    private final Map<String, h_1847_R> R_4764_Y = new HashMap<String, h_1847_R>();
    private final AtomicReference<Thread> G_564_y = new AtomicReference();
    private final AtomicBoolean P_1922_E = new AtomicBoolean(false);

    public F_3572_x() {
        super("bot");
    }

    private static List<String> J_1907_R() {
        if (lightning.product.J_1907_R.n_1700_B == null || lightning.product.J_1907_R.n_1700_B.isEmpty()) {
            return Collections.emptyList();
        }
        LinkedHashSet<String> names = new LinkedHashSet<String>();
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            String n;
            if (bot == null) continue;
            if (bot.P_1922_E != null && bot.P_1922_E.Q_2552_b != null) {
                n = bot.P_1922_E.Q_2552_b.t_4043_B();
                if (n == null || n.isEmpty()) continue;
                names.add(n);
                continue;
            }
            if (bot.R_4764_Y == null || (n = bot.R_4764_Y.O_1309_Q().getString()) == null || n.isEmpty()) continue;
            names.add(n);
        }
        return new ArrayList<String>(names);
    }

    private static CompletableFuture<Suggestions> n_1700_B(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        return V_4217_p.J_1907_R(F_3572_x.J_1907_R(), builder);
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(ctx -> {
            this.G_564_y();
            return 1;
        });
        builder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)F_3572_x.n_1700_B("start").then(F_3572_x.n_1700_B("stop").executes(ctx -> {
            lightning.product.R_4764_Y.n_1700_B();
            this.s_956_w(String.valueOf((Object)D_4024_W.Q_4569_t) + "\u041e\u0447\u0435\u0440\u0435\u0434\u044c \u0437\u0430\u043f\u0443\u0441\u043a\u0430 \u0447\u0435\u0440\u0435\u0437 .bot start \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0430");
            return 1;
        }))).then(F_3572_x.n_1700_B("pause").executes(ctx -> {
            if (lightning.product.R_4764_Y.J_1907_R()) {
                this.s_956_w(String.valueOf((Object)D_4024_W.Q_4569_t) + "\u041e\u0447\u0435\u0440\u0435\u0434\u044c \u0437\u0430\u043f\u0443\u0441\u043a\u0430 \u0447\u0435\u0440\u0435\u0437 .bot start \u043f\u043e\u0441\u0442\u0430\u0432\u043b\u0435\u043d\u0430 \u043d\u0430 \u043f\u0430\u0443\u0437\u0443");
            } else {
                this.u_2550_I("\u041e\u0447\u0435\u0440\u0435\u0434\u044c \u0443\u0436\u0435 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u043d\u0430 \u043f\u0430\u0443\u0437\u0435");
            }
            return 1;
        }))).then(F_3572_x.n_1700_B("unpause").executes(ctx -> {
            if (lightning.product.R_4764_Y.R_4764_Y()) {
                this.s_956_w(String.valueOf((Object)D_4024_W.u_2550_I) + "\u041e\u0447\u0435\u0440\u0435\u0434\u044c \u0437\u0430\u043f\u0443\u0441\u043a\u0430 \u0447\u0435\u0440\u0435\u0437 .bot start \u0432\u043e\u0437\u043e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0430");
            } else {
                this.u_2550_I("\u041e\u0447\u0435\u0440\u0435\u0434\u044c \u043d\u0435 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u043d\u0430 \u043f\u0430\u0443\u0437\u0435");
            }
            return 1;
        }))).then(F_3572_x.n_1700_B("count", IntegerArgumentType.integer((int)1, (int)100)).then(F_3572_x.n_1700_B("ip", StringArgumentType.greedyString()).executes(ctx -> {
            int count = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"count");
            String server = StringArgumentType.getString((CommandContext)ctx, (String)"ip");
            lightning.product.R_4764_Y.n_1700_B(count, server);
            this.s_956_w("\u0417\u0430\u043f\u0443\u0441\u043a\u0430\u044e " + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u0442\u043e\u0432 \u043d\u0430 " + server);
            return 1;
        }))));
        builder.then(F_3572_x.n_1700_B("connect").then(F_3572_x.n_1700_B("name", StringArgumentType.word()).then(F_3572_x.n_1700_B("ip", StringArgumentType.greedyString()).executes(ctx -> {
            String name = StringArgumentType.getString((CommandContext)ctx, (String)"name");
            String ip = StringArgumentType.getString((CommandContext)ctx, (String)"ip");
            lightning.product.R_4764_Y.n_1700_B(name, ip);
            this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + name + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0430\u0435\u0442\u0441\u044f \u043a " + ip);
            return 1;
        }))));
        builder.then(F_3572_x.n_1700_B("connectwithport").then(F_3572_x.n_1700_B("name", StringArgumentType.word()).then(F_3572_x.n_1700_B("ip", StringArgumentType.word()).then(F_3572_x.n_1700_B("port", IntegerArgumentType.integer((int)1, (int)65535)).executes(ctx -> {
            String name = StringArgumentType.getString((CommandContext)ctx, (String)"name");
            String ip = StringArgumentType.getString((CommandContext)ctx, (String)"ip");
            int port = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"port");
            lightning.product.R_4764_Y.n_1700_B(name, ip, port);
            this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + name + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0430\u0435\u0442\u0441\u044f \u043a " + ip + ":" + port);
            return 1;
        })))));
        builder.then(((LiteralArgumentBuilder)F_3572_x.n_1700_B("connectfast").then(F_3572_x.n_1700_B("stop").executes(ctx -> {
            this.n_1700_B(this.G_564_y, this.P_1922_E);
            return 1;
        }))).then(F_3572_x.n_1700_B("file", StringArgumentType.word()).then(F_3572_x.n_1700_B("ip", StringArgumentType.word()).then(F_3572_x.n_1700_B("interval", IntegerArgumentType.integer((int)1)).executes(ctx -> {
            String fileName = StringArgumentType.getString((CommandContext)ctx, (String)"file");
            String server = StringArgumentType.getString((CommandContext)ctx, (String)"ip");
            int intervalSeconds = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"interval");
            this.n_1700_B(fileName, server, intervalSeconds);
            return 1;
        })))));
        builder.then(F_3572_x.n_1700_B("stop").then(F_3572_x.n_1700_B("name", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            String name = StringArgumentType.getString((CommandContext)ctx, (String)"name");
            boolean found = lightning.product.J_1907_R.n_1700_B.stream().anyMatch(bot -> bot.R_4764_Y != null && bot.R_4764_Y.O_1309_Q().getString().equals(name));
            if (found) {
                lightning.product.R_4764_Y.n_1700_B(name);
                this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + name + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u044b\u043b \u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d.");
            } else {
                this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0442\u0430\u043a\u0438\u043c \u0438\u043c\u0435\u043d\u0435\u043c \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            }
            return 1;
        })));
        builder.then(F_3572_x.n_1700_B("stopall").executes(ctx -> {
            if (lightning.product.J_1907_R.n_1700_B.isEmpty()) {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
            } else {
                lightning.product.R_4764_Y.P_1922_E();
                this.s_956_w(String.valueOf((Object)D_4024_W.v_4262_N) + "\u0412\u0441\u0435 \u0431\u043e\u0442\u044b \u0431\u044b\u043b\u0438 \u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u044b.");
            }
            return 1;
        }));
        builder.then(F_3572_x.n_1700_B("control").then(F_3572_x.n_1700_B("name", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            String name = StringArgumentType.getString((CommandContext)ctx, (String)"name");
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (!bot.P_1922_E.Q_2552_b.t_4043_B().equals(name)) continue;
                if (F_3572_x.J_1907_R.Y_1740_V != null) {
                    J_1907_R.n_1700_B((k_2603_m)null);
                }
                F_3572_x.J_1907_R.C_2741_M = bot.P_1922_E.Q_2552_b;
                J_1907_R.n_1700_B(bot.P_1922_E.w_1484_f);
                this.s_956_w("\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u043f\u0435\u0440\u0435\u0434\u0430\u043d\u043e \u0431\u043e\u0442\u0443 " + String.valueOf((Object)D_4024_W.P_4830_p) + name);
                return 1;
            }
            if (lightning.product.J_1907_R.n_1700_B.isEmpty()) {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432! \u041f\u043e\u0434\u043e\u0436\u0434\u0438 \u043f\u043e\u043a\u0430 \u0431\u043e\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0438\u0442\u0441\u044f.");
            } else {
                this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0442\u0430\u043a\u0438\u043c \u0438\u043c\u0435\u043d\u0435\u043c \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            }
            return 1;
        })));
        builder.then(F_3572_x.n_1700_B("return").executes(ctx -> {
            if (F_3572_x.J_1907_R.Y_1740_V != null) {
                J_1907_R.n_1700_B((k_2603_m)null);
            }
            J_1907_R.n_1700_B(F_3572_x.J_1907_R.Y_601_j);
            F_3572_x.J_1907_R.C_2741_M = F_3572_x.J_1907_R.Y_259_p;
            if (F_3572_x.J_1907_R.Y_259_p != null) {
                F_3572_x.J_1907_R.Y_259_p.G_564_y = new e_869_m(F_3572_x.J_1907_R.P_4830_p);
                F_3572_x.J_1907_R.Y_259_p.L_4248_u = 0.0f;
                F_3572_x.J_1907_R.Y_259_p.L_1362_X = 0.0f;
                F_3572_x.J_1907_R.Y_259_p.t_1786_h(false);
            }
            this.s_956_w("\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435 \u0432\u043e\u0437\u0432\u0440\u0430\u0449\u0435\u043d\u043e \u0438\u0433\u0440\u043e\u043a\u0443");
            return 1;
        }));
        builder.then(F_3572_x.n_1700_B("chat").then(F_3572_x.n_1700_B("target", StringArgumentType.word()).suggests((ctx, b) -> V_4217_p.J_1907_R(F_3572_x.R_4764_Y(), b)).then(F_3572_x.n_1700_B("message", StringArgumentType.greedyString()).executes(ctx -> {
            String target = StringArgumentType.getString((CommandContext)ctx, (String)"target");
            String message = StringArgumentType.getString((CommandContext)ctx, (String)"message");
            if (target.equalsIgnoreCase("all")) {
                int count = 0;
                for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                    bot.P_1922_E.Q_2552_b.n_1700_B(message);
                    ++count;
                }
                if (count > 0) {
                    this.s_956_w("\u0421\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435 \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u043e \u043e\u0442 \u0438\u043c\u0435\u043d\u0438 " + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u0442\u043e\u0432");
                } else {
                    this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
                }
            } else {
                n_1700_B targetBot = lightning.product.J_1907_R.R_4764_Y(target);
                if (targetBot != null && targetBot.R_4764_Y != null) {
                    targetBot.R_4764_Y.n_1700_B(message);
                    this.s_956_w("\u0421\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435 \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u043e \u043e\u0442 \u0438\u043c\u0435\u043d\u0438 \u0431\u043e\u0442\u0430 " + String.valueOf((Object)D_4024_W.P_4830_p) + target);
                    return 1;
                }
                this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0442\u0430\u043a\u0438\u043c \u0438\u043c\u0435\u043d\u0435\u043c \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            }
            return 1;
        }))));
        builder.then(F_3572_x.n_1700_B("list").executes(ctx -> {
            this.P_1922_E();
            return 1;
        }));
        builder.then(F_3572_x.n_1700_B("password").then(F_3572_x.n_1700_B("pwd", StringArgumentType.word()).executes(ctx -> {
            String pwd = StringArgumentType.getString((CommandContext)ctx, (String)"pwd");
            lightning.product.J_1907_R.n_1700_B(pwd);
            this.s_956_w("\u041f\u0430\u0440\u043e\u043b\u044c \u0434\u043b\u044f \u0431\u043e\u0442\u043e\u0432 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d: " + String.valueOf((Object)D_4024_W.u_2550_I) + pwd);
            return 1;
        })));
        builder.then(((LiteralArgumentBuilder)F_3572_x.n_1700_B("tapemouse").then(F_3572_x.n_1700_B("add").then(F_3572_x.n_1700_B("name", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            String nick = StringArgumentType.getString((CommandContext)ctx, (String)"name");
            this.J_1907_R(nick);
            return 1;
        })))).then(F_3572_x.n_1700_B("remove").then(F_3572_x.n_1700_B("name", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            String nick = StringArgumentType.getString((CommandContext)ctx, (String)"name");
            this.R_4764_Y(nick);
            return 1;
        }))));
        builder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)F_3572_x.n_1700_B("follow").then(F_3572_x.n_1700_B("stopall").executes(ctx -> {
            this.u_1723_Y();
            return 1;
        }))).then(F_3572_x.n_1700_B("stop").then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).executes(ctx -> {
            this.G_564_y(StringArgumentType.getString((CommandContext)ctx, (String)"botName"));
            return 1;
        })))).then(F_3572_x.n_1700_B("all").then(F_3572_x.n_1700_B("targetName", StringArgumentType.word()).executes(ctx -> {
            String targetName = StringArgumentType.getString((CommandContext)ctx, (String)"targetName");
            int count = 0;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                bot.n_1700_B(new t_148_a(targetName));
                ++count;
            }
            if (count > 0) {
                this.s_956_w("\u0412\u0441\u0435 \u0431\u043e\u0442\u044b (" + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + ") \u0442\u0435\u043f\u0435\u0440\u044c \u0441\u043b\u0435\u0434\u0443\u044e\u0442 \u0437\u0430 " + String.valueOf((Object)D_4024_W.M_588_G) + targetName);
            } else {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
            }
            return 1;
        })))).then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).then(F_3572_x.n_1700_B("targetName", StringArgumentType.word()).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            String targetName = StringArgumentType.getString((CommandContext)ctx, (String)"targetName");
            n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
            if (bot == null) {
                this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            } else {
                bot.n_1700_B(new t_148_a(targetName));
                this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0442\u0435\u043f\u0435\u0440\u044c \u0441\u043b\u0435\u0434\u0443\u0435\u0442 \u0437\u0430 " + String.valueOf((Object)D_4024_W.M_588_G) + targetName);
            }
            return 1;
        }))));
        builder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)F_3572_x.n_1700_B("attack").then(F_3572_x.n_1700_B("stopall").executes(ctx -> {
            this.v_4262_N();
            return 1;
        }))).then(F_3572_x.n_1700_B("stop").then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).executes(ctx -> {
            this.P_1922_E(StringArgumentType.getString((CommandContext)ctx, (String)"botName"));
            return 1;
        })))).then(F_3572_x.n_1700_B("all").then(F_3572_x.n_1700_B("targetName", StringArgumentType.word()).executes(ctx -> {
            String targetName = StringArgumentType.getString((CommandContext)ctx, (String)"targetName");
            int count = 0;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                bot.n_1700_B(new G_564_y(targetName));
                ++count;
            }
            if (count > 0) {
                this.s_956_w("\u0412\u0441\u0435 \u0431\u043e\u0442\u044b (" + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + ") \u0442\u0435\u043f\u0435\u0440\u044c \u0430\u0442\u0430\u043a\u0443\u044e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + targetName);
            } else {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
            }
            return 1;
        })))).then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).then(F_3572_x.n_1700_B("targetName", StringArgumentType.word()).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            String targetName = StringArgumentType.getString((CommandContext)ctx, (String)"targetName");
            n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
            if (bot == null) {
                this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            } else {
                bot.n_1700_B(new G_564_y(targetName));
                this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0442\u0435\u043f\u0435\u0440\u044c \u0430\u0442\u0430\u043a\u0443\u0435\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + targetName);
            }
            return 1;
        }))));
        builder.then(((LiteralArgumentBuilder)F_3572_x.n_1700_B("idle").then(F_3572_x.n_1700_B("all").executes(ctx -> {
            int count = 0;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                bot.n_1700_B(new u_2550_I());
                ++count;
            }
            if (count > 0) {
                this.s_956_w("\u0412\u0441\u0435 \u0431\u043e\u0442\u044b (" + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + ") \u043f\u0435\u0440\u0435\u0432\u0435\u0434\u0435\u043d\u044b \u0432 \u0440\u0435\u0436\u0438\u043c \u043e\u0436\u0438\u0434\u0430\u043d\u0438\u044f");
            } else {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
            }
            return 1;
        }))).then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
            if (bot == null) {
                this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            } else {
                bot.n_1700_B(new u_2550_I());
                this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0442\u0435\u043f\u0435\u0440\u044c \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 \u043e\u0436\u0438\u0434\u0430\u043d\u0438\u044f");
            }
            return 1;
        })));
        builder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)F_3572_x.n_1700_B("rebreak").then(F_3572_x.n_1700_B("stopall").executes(ctx -> {
            this.w_1484_f();
            return 1;
        }))).then(F_3572_x.n_1700_B("stop").then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            this.u_1723_Y(StringArgumentType.getString((CommandContext)ctx, (String)"botName"));
            return 1;
        })))).then(F_3572_x.n_1700_B("all").executes(ctx -> {
            int count = 0;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                bot.n_1700_B(new v_4262_N());
                ++count;
            }
            if (count > 0) {
                this.s_956_w("\u0412\u0441\u0435 \u0431\u043e\u0442\u044b (" + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + ") \u0432\u043a\u043b\u044e\u0447\u0438\u043b\u0438 CyclicRebreak");
            } else {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
            }
            return 1;
        }))).then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
            if (bot == null) {
                this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
                return 1;
            }
            bot.n_1700_B(new v_4262_N());
            this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0432\u043a\u043b\u044e\u0447\u0438\u043b CyclicRebreak");
            return 1;
        })));
        builder.then(F_3572_x.n_1700_B("pay").then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests((ctx, b) -> V_4217_p.J_1907_R(F_3572_x.R_4764_Y(), b)).then(F_3572_x.n_1700_B("targetName", StringArgumentType.word()).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            String target = StringArgumentType.getString((CommandContext)ctx, (String)"targetName");
            this.n_1700_B(botName, target);
            return 1;
        }))));
        builder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)F_3572_x.n_1700_B("antiafk").then(F_3572_x.n_1700_B("enable").executes(ctx -> {
            lightning.product.J_1907_R.J_1907_R(true);
            lightning.product.J_1907_R.n_1700_B(true);
            this.s_956_w(String.valueOf((Object)D_4024_W.u_2550_I) + "Anti-AFK \u0432\u043a\u043b\u044e\u0447\u0435\u043d \u0434\u043b\u044f \u0432\u0441\u0435\u0445 \u0431\u043e\u0442\u043e\u0432");
            this.s_956_w(String.valueOf((Object)D_4024_W.w_1484_f) + "\u0411\u043e\u0442\u044b \u0431\u0443\u0434\u0443\u0442 \u0434\u0435\u043b\u0430\u0442\u044c \u0448\u0430\u0433 \u0432\u043f\u0435\u0440\u0451\u0434/\u043d\u0430\u0437\u0430\u0434 \u043a\u0430\u0436\u0434\u044b\u0435 3 \u043c\u0438\u043d\u0443\u0442\u044b");
            return 1;
        }))).then(F_3572_x.n_1700_B("disable").executes(ctx -> {
            lightning.product.J_1907_R.J_1907_R(false);
            lightning.product.J_1907_R.n_1700_B(false);
            this.s_956_w(String.valueOf((Object)D_4024_W.P_4830_p) + "Anti-AFK \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d");
            return 1;
        }))).then(F_3572_x.n_1700_B("status").executes(ctx -> {
            boolean enabled = lightning.product.J_1907_R.v_4262_N();
            boolean walkEnabled = lightning.product.J_1907_R.P_1922_E();
            String status = enabled ? String.valueOf((Object)D_4024_W.u_2550_I) + "\u0432\u043a\u043b\u044e\u0447\u0435\u043d" : String.valueOf((Object)D_4024_W.P_4830_p) + "\u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d";
            String walkStatus = walkEnabled ? String.valueOf((Object)D_4024_W.u_2550_I) + "\u0432\u043a\u043b\u044e\u0447\u0435\u043d" : String.valueOf((Object)D_4024_W.P_4830_p) + "\u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d";
            String command = lightning.product.J_1907_R.w_1484_f();
            int interval = lightning.product.J_1907_R.n_1700_B() / 1000 / 60;
            this.s_956_w("Anti-AFK: " + status);
            this.s_956_w("Anti-AFK Walk: " + walkStatus);
            this.s_956_w("\u041a\u043e\u043c\u0430\u043d\u0434\u0430: " + String.valueOf((Object)D_4024_W.M_588_G) + command);
            this.s_956_w("\u0418\u043d\u0442\u0435\u0440\u0432\u0430\u043b \u043a\u043e\u043c\u0430\u043d\u0434\u044b: " + String.valueOf((Object)D_4024_W.Q_4569_t) + interval + " \u043c\u0438\u043d\u0443\u0442");
            this.s_956_w("\u0418\u043d\u0442\u0435\u0440\u0432\u0430\u043b \u0448\u0430\u0433\u043e\u0432: " + String.valueOf((Object)D_4024_W.Q_4569_t) + "3 \u043c\u0438\u043d\u0443\u0442\u044b (\u0432\u043f\u0435\u0440\u0451\u0434/\u043d\u0430\u0437\u0430\u0434)");
            return 1;
        }))).then(F_3572_x.n_1700_B("setcommand").then(F_3572_x.n_1700_B("command", StringArgumentType.greedyString()).executes(ctx -> {
            String newCommand = StringArgumentType.getString((CommandContext)ctx, (String)"command");
            lightning.product.J_1907_R.G_564_y(newCommand);
            this.s_956_w("Anti-AFK \u043a\u043e\u043c\u0430\u043d\u0434\u0430 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0430: " + String.valueOf((Object)D_4024_W.M_588_G) + newCommand);
            return 1;
        })))).then(F_3572_x.n_1700_B("setinterval").then(F_3572_x.n_1700_B("minutes", IntegerArgumentType.integer((int)1)).executes(ctx -> {
            int minutes = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"minutes");
            lightning.product.J_1907_R.n_1700_B((long)(minutes * 60) * 1000L);
            this.s_956_w("Anti-AFK \u0438\u043d\u0442\u0435\u0440\u0432\u0430\u043b \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d: " + String.valueOf((Object)D_4024_W.Q_4569_t) + minutes + " \u043c\u0438\u043d\u0443\u0442");
            return 1;
        })))).then(((LiteralArgumentBuilder)F_3572_x.n_1700_B("walk").then(F_3572_x.n_1700_B("enable").executes(ctx -> {
            lightning.product.J_1907_R.n_1700_B(true);
            this.s_956_w(String.valueOf((Object)D_4024_W.u_2550_I) + "Anti-AFK Walk \u0432\u043a\u043b\u044e\u0447\u0435\u043d (\u0448\u0430\u0433 \u043a\u0430\u0436\u0434\u044b\u0435 3 \u043c\u0438\u043d)");
            return 1;
        }))).then(F_3572_x.n_1700_B("disable").executes(ctx -> {
            lightning.product.J_1907_R.n_1700_B(false);
            this.s_956_w(String.valueOf((Object)D_4024_W.P_4830_p) + "Anti-AFK Walk \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d");
            return 1;
        }))));
        builder.then(((LiteralArgumentBuilder)F_3572_x.n_1700_B("goto").then(F_3572_x.n_1700_B("all").then(F_3572_x.n_1700_B("x", IntegerArgumentType.integer()).then(F_3572_x.n_1700_B("y", IntegerArgumentType.integer()).then(F_3572_x.n_1700_B("z", IntegerArgumentType.integer()).executes(ctx -> {
            int x = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"x");
            int y = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"y");
            int z = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"z");
            int count = 0;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (bot == null) continue;
                bot.n_1700_B(new s_956_w(x, y, z));
                ++count;
            }
            if (count > 0) {
                this.s_956_w("\u0412\u0441\u0435 \u0431\u043e\u0442\u044b (" + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + ") \u0438\u0434\u0443\u0442 \u043a \u0442\u043e\u0447\u043a\u0435 " + String.valueOf((Object)D_4024_W.Q_4569_t) + "X:" + x + " Y:" + y + " Z:" + z);
            } else {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
            }
            return 1;
        })))))).then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).then(F_3572_x.n_1700_B("x", IntegerArgumentType.integer()).then(F_3572_x.n_1700_B("y", IntegerArgumentType.integer()).then(F_3572_x.n_1700_B("z", IntegerArgumentType.integer()).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            int x = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"x");
            int y = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"y");
            int z = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"z");
            n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
            if (bot == null) {
                this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            } else {
                bot.n_1700_B(new s_956_w(x, y, z));
                this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0438\u0434\u0451\u0442 \u043a \u0442\u043e\u0447\u043a\u0435 " + String.valueOf((Object)D_4024_W.Q_4569_t) + "X:" + x + " Y:" + y + " Z:" + z);
            }
            return 1;
        }))))));
        builder.then(((LiteralArgumentBuilder)F_3572_x.n_1700_B("autojoingrief").then(F_3572_x.n_1700_B("all").then(((RequiredArgumentBuilder)F_3572_x.n_1700_B("griefNumber", IntegerArgumentType.integer((int)1)).executes(ctx -> {
            int griefNumber = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"griefNumber");
            this.n_1700_B("all", griefNumber, 1);
            return 1;
        })).then(F_3572_x.n_1700_B("interval", IntegerArgumentType.integer((int)1)).executes(ctx -> {
            int griefNumber = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"griefNumber");
            int interval = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"interval");
            this.n_1700_B("all", griefNumber, interval);
            return 1;
        }))))).then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).then(((RequiredArgumentBuilder)F_3572_x.n_1700_B("griefNumber", IntegerArgumentType.integer((int)1)).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            int griefNumber = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"griefNumber");
            this.n_1700_B(botName, griefNumber, 1);
            return 1;
        })).then(F_3572_x.n_1700_B("interval", IntegerArgumentType.integer((int)1)).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            int griefNumber = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"griefNumber");
            int interval = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"interval");
            this.n_1700_B(botName, griefNumber, interval);
            return 1;
        })))));
        builder.then(F_3572_x.n_1700_B("mining").then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).then(F_3572_x.n_1700_B("blockName", StringArgumentType.word()).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            String blockName = StringArgumentType.getString((CommandContext)ctx, (String)"blockName");
            this.J_1907_R(botName, blockName);
            return 1;
        }))));
        builder.then(((LiteralArgumentBuilder)F_3572_x.n_1700_B("rejoin").then(((LiteralArgumentBuilder)F_3572_x.n_1700_B("enable").then(((LiteralArgumentBuilder)F_3572_x.n_1700_B("all").executes(ctx -> {
            this.n_1700_B("all", 100);
            return 1;
        })).then(F_3572_x.n_1700_B("ticks", IntegerArgumentType.integer((int)1)).executes(ctx -> {
            int ticks = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"ticks");
            this.n_1700_B("all", ticks);
            return 1;
        })))).then(((RequiredArgumentBuilder)F_3572_x.n_1700_B("botName", StringArgumentType.word()).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            this.n_1700_B(botName, 100);
            return 1;
        })).then(F_3572_x.n_1700_B("ticks", IntegerArgumentType.integer((int)1)).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            int ticks = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"ticks");
            this.n_1700_B(botName, ticks);
            return 1;
        }))))).then(((LiteralArgumentBuilder)F_3572_x.n_1700_B("disable").then(F_3572_x.n_1700_B("all").executes(ctx -> {
            this.t_148_a("all");
            return 1;
        }))).then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            this.t_148_a(botName);
            return 1;
        }))));
        builder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)F_3572_x.n_1700_B("patrol").then(F_3572_x.n_1700_B("add").then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
            if (bot == null) {
                this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            } else {
                this.n_1700_B(botName, bot);
            }
            return 1;
        })))).then(F_3572_x.n_1700_B("clear").then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            this.v_4262_N(botName);
            return 1;
        })))).then(F_3572_x.n_1700_B("start").then(F_3572_x.n_1700_B("botName", StringArgumentType.word()).suggests(F_3572_x::n_1700_B).executes(ctx -> {
            String botName = StringArgumentType.getString((CommandContext)ctx, (String)"botName");
            n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
            if (bot == null) {
                this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            } else {
                this.J_1907_R(botName, bot);
            }
            return 1;
        }))));
        builder.then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)F_3572_x.n_1700_B("proxy").then(F_3572_x.n_1700_B("status").executes(ctx -> {
            this.t_148_a();
            return 1;
        }))).then(F_3572_x.n_1700_B("enable").executes(ctx -> {
            ProxyServer.proxyEnabled = true;
            Config.saveConfig();
            this.s_956_w("\u041f\u0440\u043e\u043a\u0441\u0438 " + String.valueOf((Object)D_4024_W.u_2550_I) + "\u0432\u043a\u043b\u044e\u0447\u0451\u043d");
            return 1;
        }))).then(F_3572_x.n_1700_B("on").executes(ctx -> {
            ProxyServer.proxyEnabled = true;
            Config.saveConfig();
            this.s_956_w("\u041f\u0440\u043e\u043a\u0441\u0438 " + String.valueOf((Object)D_4024_W.u_2550_I) + "\u0432\u043a\u043b\u044e\u0447\u0451\u043d");
            return 1;
        }))).then(F_3572_x.n_1700_B("disable").executes(ctx -> {
            ProxyServer.proxyEnabled = false;
            Config.saveConfig();
            this.s_956_w("\u041f\u0440\u043e\u043a\u0441\u0438 " + String.valueOf((Object)D_4024_W.P_4830_p) + "\u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d");
            return 1;
        }))).then(F_3572_x.n_1700_B("off").executes(ctx -> {
            ProxyServer.proxyEnabled = false;
            Config.saveConfig();
            this.s_956_w("\u041f\u0440\u043e\u043a\u0441\u0438 " + String.valueOf((Object)D_4024_W.P_4830_p) + "\u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d");
            return 1;
        }))).then(F_3572_x.n_1700_B("set").then(F_3572_x.n_1700_B("type", StringArgumentType.word()).then(((RequiredArgumentBuilder)F_3572_x.n_1700_B("ipport", StringArgumentType.word()).executes(ctx -> {
            String type = StringArgumentType.getString((CommandContext)ctx, (String)"type");
            String ipPort = StringArgumentType.getString((CommandContext)ctx, (String)"ipport");
            return this.n_1700_B(type, ipPort, "", "");
        })).then(((RequiredArgumentBuilder)F_3572_x.n_1700_B("user", StringArgumentType.word()).executes(ctx -> {
            String type = StringArgumentType.getString((CommandContext)ctx, (String)"type");
            String ipPort = StringArgumentType.getString((CommandContext)ctx, (String)"ipport");
            String user = StringArgumentType.getString((CommandContext)ctx, (String)"user");
            return this.n_1700_B(type, ipPort, user, "");
        })).then(F_3572_x.n_1700_B("pass", StringArgumentType.word()).executes(ctx -> {
            String type = StringArgumentType.getString((CommandContext)ctx, (String)"type");
            String ipPort = StringArgumentType.getString((CommandContext)ctx, (String)"ipport");
            String user = StringArgumentType.getString((CommandContext)ctx, (String)"user");
            String pass = StringArgumentType.getString((CommandContext)ctx, (String)"pass");
            return this.n_1700_B(type, ipPort, user, pass);
        }))))))).then(F_3572_x.n_1700_B("test").executes(ctx -> {
            this.s_956_w();
            return 1;
        }))).then(F_3572_x.n_1700_B("help").executes(ctx -> {
            this.u_2550_I();
            return 1;
        })));
    }

    private static List<String> R_4764_Y() {
        ArrayList<String> out = new ArrayList<String>();
        out.add("all");
        out.addAll(F_3572_x.J_1907_R());
        return out;
    }

    private void G_564_y() {
        this.s_956_w("\u0414\u043e\u0441\u0442\u0443\u043f\u043d\u044b\u0435 \u043f\u043e\u0434\u043a\u043e\u043c\u0430\u043d\u0434\u044b \u0431\u043e\u0442\u0430:");
        this.s_956_w(String.valueOf((Object)D_4024_W.v_4262_N) + "\u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435:");
        this.s_956_w(" .bot connect " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f> <ip>");
        this.s_956_w(" .bot connectWithPort " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f> <ip> <\u043f\u043e\u0440\u0442>");
        this.s_956_w(" .bot connectfast " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0444\u0430\u0439\u043b.txt> <ip> <\u0438\u043d\u0442\u0435\u0440\u0432\u0430\u043b_\u0441\u0435\u043a>");
        this.s_956_w(" .bot connectfast stop " + String.valueOf((Object)D_4024_W.w_1484_f) + "\u2014 \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c \u043c\u0430\u0441\u0441\u043e\u0432\u044b\u0439 \u0437\u0430\u043f\u0443\u0441\u043a");
        this.s_956_w(" .bot start " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u043a\u043e\u043b-\u0432\u043e> <ip[:port]>");
        this.s_956_w(" .bot start stop|pause|unpause");
        this.s_956_w(" .bot password " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u043f\u0430\u0440\u043e\u043b\u044c>");
        this.s_956_w(String.valueOf((Object)D_4024_W.v_4262_N) + "\u0423\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435/\u0447\u0430\u0442:");
        this.s_956_w(" .bot control " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f>");
        this.s_956_w(" .bot return");
        this.s_956_w(" .bot chat " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f> <\u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435>");
        this.s_956_w(" .bot chat all " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435>");
        this.s_956_w(" .bot tapemouse add|remove " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f>");
        this.s_956_w(" .bot list");
        this.s_956_w(String.valueOf((Object)D_4024_W.v_4262_N) + "\u041f\u043e\u0432\u0435\u0434\u0435\u043d\u0438\u0435:");
        this.s_956_w(" .bot follow " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f_\u0431\u043e\u0442\u0430> <\u0438\u043c\u044f_\u0446\u0435\u043b\u0438|stop|stopall|all>");
        this.s_956_w(" .bot attack " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f_\u0431\u043e\u0442\u0430> <\u0438\u043c\u044f_\u0446\u0435\u043b\u0438|stop|stopall|all>");
        this.s_956_w(" .bot idle " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f_\u0431\u043e\u0442\u0430|all>");
        this.s_956_w(" .bot goto " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f_\u0431\u043e\u0442\u0430|all> <x> <y> <z>");
        this.s_956_w(" .bot rebreak " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f_\u0431\u043e\u0442\u0430|all|stop|stopall>");
        this.s_956_w(" .bot patrol add|clear|start " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f_\u0431\u043e\u0442\u0430>");
        this.s_956_w(" .bot autojoingrief " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u043d\u0438\u043a|all> <\u043d\u043e\u043c\u0435\u0440_\u0433\u0440\u0438\u0444\u0430> [\u0438\u043d\u0442\u0435\u0440\u0432\u0430\u043b_\u0441\u0435\u043a]");
        this.s_956_w(" .bot mining " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f_\u0431\u043e\u0442\u0430> <minecraft:block>");
        this.s_956_w(" .bot rejoin " + String.valueOf((Object)D_4024_W.P_4830_p) + "enable|disable <\u0438\u043c\u044f_\u0431\u043e\u0442\u0430|all> [\u0442\u0438\u043a\u0438]");
        this.s_956_w(String.valueOf((Object)D_4024_W.v_4262_N) + "\u041f\u0440\u043e\u0447\u0435\u0435:");
        this.s_956_w(" .bot stop " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f>");
        this.s_956_w(" .bot stopall");
        this.s_956_w(" .bot pay " + String.valueOf((Object)D_4024_W.P_4830_p) + "<\u0438\u043c\u044f_\u0431\u043e\u0442\u0430|all> <\u0438\u043c\u044f_\u0446\u0435\u043b\u0438>");
        this.s_956_w(" .bot antiafk " + String.valueOf((Object)D_4024_W.P_4830_p) + "enable|disable|status|setcommand <cmd>|setinterval <\u043c\u0438\u043d>|walk enable|disable");
        this.s_956_w(String.valueOf((Object)D_4024_W.v_4262_N) + "\u041f\u0440\u043e\u043a\u0441\u0438:");
        this.s_956_w(" .bot proxy status");
        this.s_956_w(" .bot proxy enable|disable");
        this.s_956_w(" .bot proxy set " + String.valueOf((Object)D_4024_W.P_4830_p) + "<socks4|socks5> <ip:port> [user] [pass]");
        this.s_956_w(" .bot proxy test");
        this.s_956_w(" .bot proxy help");
    }

    private void P_1922_E() {
        if (lightning.product.J_1907_R.n_1700_B.isEmpty()) {
            this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
            return;
        }
        this.s_956_w(String.valueOf((Object)D_4024_W.v_4262_N) + "\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432:");
        LinkedHashSet<String> printed = new LinkedHashSet<String>();
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            String botName;
            if (bot == null || bot.R_4764_Y == null || !printed.add(botName = bot.R_4764_Y.O_1309_Q().getString())) continue;
            boolean isTapeMouse = lightning.product.J_1907_R.R_4764_Y.stream().anyMatch(tb -> tb.n_1700_B() != null && tb.n_1700_B().R_4764_Y != null && tb.n_1700_B().R_4764_Y.O_1309_Q().getString().equals(botName));
            Object behaviorType = "";
            if (bot.M_588_G instanceof t_148_a) {
                behaviorType = String.valueOf((Object)D_4024_W.M_588_G) + " [Follow]";
            } else if (bot.M_588_G instanceof G_564_y) {
                behaviorType = String.valueOf((Object)D_4024_W.P_4830_p) + " [Attack]";
            } else if (bot.M_588_G instanceof u_2550_I) {
                behaviorType = String.valueOf((Object)D_4024_W.w_1484_f) + " [Idle]";
            } else if (bot.M_588_G instanceof h_1847_R) {
                behaviorType = String.valueOf((Object)D_4024_W.u_2550_I) + " [Patrol]";
            } else if (bot.M_588_G instanceof P_4830_p) {
                behaviorType = String.valueOf((Object)D_4024_W.v_4262_N) + " [Mining]";
            } else if (bot.M_588_G instanceof v_4262_N) {
                behaviorType = String.valueOf((Object)D_4024_W.h_1847_R) + " [CyclicRebreak]";
            } else if (bot.M_588_G instanceof s_956_w) {
                s_956_w gotoBehavior = (s_956_w)bot.M_588_G;
                c_1514_x target = gotoBehavior.n_1700_B();
                behaviorType = String.valueOf((Object)D_4024_W.Q_4569_t) + " [Goto X:" + target.getX() + " Y:" + target.getY() + " Z:" + target.getZ() + "]";
            }
            String status = isTapeMouse ? String.valueOf((Object)D_4024_W.u_2550_I) + " [TapeMouse]" : "";
            this.s_956_w(" - " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + status + (String)behaviorType);
        }
    }

    private void J_1907_R(String nick) {
        n_1700_B foundBot = lightning.product.J_1907_R.R_4764_Y(nick);
        if (foundBot != null) {
            boolean alreadyExists = lightning.product.J_1907_R.R_4764_Y.stream().anyMatch(tb -> tb.n_1700_B() != null && tb.n_1700_B().R_4764_Y != null && tb.n_1700_B().R_4764_Y.O_1309_Q().getString().equals(nick));
            if (!alreadyExists) {
                lightning.product.J_1907_R.R_4764_Y.add(new w_1457_N(foundBot));
                this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + nick + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d \u0432 TapeMouse");
            } else {
                this.u_2550_I("\u042d\u0442\u043e\u0442 \u0431\u043e\u0442 \u0443\u0436\u0435 \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 TapeMouse!");
            }
            return;
        }
        this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0442\u0430\u043a\u0438\u043c \u0438\u043c\u0435\u043d\u0435\u043c \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
    }

    private void R_4764_Y(String nick) {
        boolean removed = lightning.product.J_1907_R.R_4764_Y.removeIf(tb -> tb.n_1700_B() != null && tb.n_1700_B().R_4764_Y != null && tb.n_1700_B().R_4764_Y.O_1309_Q().getString().equals(nick));
        if (removed) {
            this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + nick + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0443\u0434\u0430\u043b\u0451\u043d \u0438\u0437 TapeMouse");
        } else {
            this.u_2550_I("\u042d\u0442\u043e\u0442 \u0431\u043e\u0442 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 TapeMouse!");
        }
    }

    private void G_564_y(String botName) {
        n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
        if (bot == null) {
            this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            return;
        }
        if (bot.M_588_G instanceof t_148_a) {
            bot.n_1700_B(new u_2550_I());
            this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u043b\u044c\u0448\u0435 \u043d\u0435 \u0441\u043b\u0435\u0434\u0443\u0435\u0442 \u0437\u0430 \u0446\u0435\u043b\u044c\u044e");
        } else {
            this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u043d\u0435 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 \u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u044f");
        }
    }

    private void u_1723_Y() {
        int count = 0;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (!(bot.M_588_G instanceof t_148_a)) continue;
            bot.n_1700_B(new u_2550_I());
            ++count;
        }
        if (count > 0) {
            this.s_956_w("\u0420\u0435\u0436\u0438\u043c \u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u044f \u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d \u0443 " + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u0442\u043e\u0432");
        } else {
            this.u_2550_I("\u041d\u0438 \u043e\u0434\u0438\u043d \u0431\u043e\u0442 \u043d\u0435 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 \u0441\u043b\u0435\u0434\u043e\u0432\u0430\u043d\u0438\u044f!");
        }
    }

    private void P_1922_E(String botName) {
        n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
        if (bot == null) {
            this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            return;
        }
        if (bot.M_588_G instanceof G_564_y) {
            bot.n_1700_B(new u_2550_I());
            this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u043b\u044c\u0448\u0435 \u043d\u0435 \u0430\u0442\u0430\u043a\u0443\u0435\u0442 \u0446\u0435\u043b\u044c");
        } else {
            this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u043d\u0435 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 \u0430\u0442\u0430\u043a\u0438");
        }
    }

    private void v_4262_N() {
        int count = 0;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (!(bot.M_588_G instanceof G_564_y)) continue;
            bot.n_1700_B(new u_2550_I());
            ++count;
        }
        if (count > 0) {
            this.s_956_w("\u0420\u0435\u0436\u0438\u043c \u0430\u0442\u0430\u043a\u0438 \u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d \u0443 " + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u0442\u043e\u0432");
        } else {
            this.u_2550_I("\u041d\u0438 \u043e\u0434\u0438\u043d \u0431\u043e\u0442 \u043d\u0435 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 \u0430\u0442\u0430\u043a\u0438!");
        }
    }

    private void u_1723_Y(String botName) {
        n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
        if (bot == null) {
            this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            return;
        }
        if (bot.M_588_G instanceof v_4262_N) {
            bot.n_1700_B(new u_2550_I());
            this.s_956_w("Rebreak \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d \u0434\u043b\u044f " + String.valueOf((Object)D_4024_W.P_4830_p) + botName);
        } else {
            this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u043d\u0435 \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 Rebreak.");
        }
    }

    private void w_1484_f() {
        int count = 0;
        for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
            if (!(bot.M_588_G instanceof v_4262_N)) continue;
            bot.n_1700_B(new u_2550_I());
            ++count;
        }
        if (count > 0) {
            this.s_956_w("Rebreak \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d \u0443 " + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u0442\u043e\u0432");
        } else {
            this.u_2550_I("\u041d\u0438 \u043e\u0434\u0438\u043d \u0431\u043e\u0442 \u043d\u0435 \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 Rebreak.");
        }
    }

    private void n_1700_B(String botName, int griefNumber, int intervalSeconds) {
        if (botName.equalsIgnoreCase("all")) {
            if (lightning.product.J_1907_R.n_1700_B.isEmpty()) {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
                return;
            }
            int count = 0;
            int idx = 0;
            long stepDelayMs = Math.max(250L, Math.min(2000L, (long)intervalSeconds * 250L));
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                if (bot == null || bot.P_1922_E == null || bot.P_1922_E.Q_2552_b == null) continue;
                bot.n_1700_B(new P_1922_E(griefNumber, intervalSeconds, (long)idx * stepDelayMs));
                ++count;
                ++idx;
            }
            if (count > 0) {
                this.s_956_w("AutoJoinGrief \u0432\u043a\u043b\u044e\u0447\u0435\u043d \u0434\u043b\u044f " + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u0442\u043e\u0432. \u0413\u0440\u0438\u0444: " + String.valueOf((Object)D_4024_W.M_588_G) + "#" + griefNumber + String.valueOf((Object)D_4024_W.Q_2552_b) + ", \u0438\u043d\u0442\u0435\u0440\u0432\u0430\u043b: " + String.valueOf((Object)D_4024_W.Q_4569_t) + intervalSeconds + "\u0441" + String.valueOf((Object)D_4024_W.Q_2552_b) + ", step: " + String.valueOf((Object)D_4024_W.Q_4569_t) + stepDelayMs + "\u043c\u0441");
            }
            return;
        }
        n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
        if (bot == null) {
            this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            return;
        }
        if (bot.P_1922_E == null || bot.P_1922_E.Q_2552_b == null) {
            this.u_2550_I("\u0411\u043e\u0442 " + botName + " \u043d\u0435 \u0438\u043c\u0435\u0435\u0442 \u0430\u043a\u0442\u0438\u0432\u043d\u043e\u0433\u043e \u0441\u043e\u0435\u0434\u0438\u043d\u0435\u043d\u0438\u044f!");
            return;
        }
        bot.n_1700_B(new P_1922_E(griefNumber, intervalSeconds));
        this.s_956_w("AutoJoinGrief \u0432\u043a\u043b\u044e\u0447\u0435\u043d \u0434\u043b\u044f \u0431\u043e\u0442\u0430 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + ". \u0413\u0440\u0438\u0444: " + String.valueOf((Object)D_4024_W.M_588_G) + "#" + griefNumber + String.valueOf((Object)D_4024_W.Q_2552_b) + ", \u0438\u043d\u0442\u0435\u0440\u0432\u0430\u043b: " + String.valueOf((Object)D_4024_W.Q_4569_t) + intervalSeconds + "\u0441");
    }

    private void n_1700_B(String botName, String targetPlayer) {
        if (botName.equalsIgnoreCase("all")) {
            if (lightning.product.J_1907_R.n_1700_B.isEmpty()) {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
                return;
            }
            String target = targetPlayer;
            new Thread(() -> {
                int count = 0;
                for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                    if (bot == null || bot.P_1922_E == null || bot.P_1922_E.Q_2552_b == null) continue;
                    try {
                        if (count > 0) {
                            Thread.sleep(5000L);
                        }
                        String name = bot.P_1922_E.Q_2552_b.t_4043_B();
                        lightning.product.J_1907_R.n_1700_B(name, target);
                        bot.P_1922_E.Q_2552_b.n_1700_B("/balance");
                        ++count;
                    }
                    catch (InterruptedException interruptedException) {}
                }
                if (count > 0) {
                    this.s_956_w("\u0417\u0430\u043f\u0440\u043e\u0441 \u0431\u0430\u043b\u0430\u043d\u0441\u0430 \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d " + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u0442\u0430\u043c. \u0426\u0435\u043b\u044c: " + String.valueOf((Object)D_4024_W.M_588_G) + target);
                }
            }).start();
            return;
        }
        n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
        if (bot == null) {
            this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            return;
        }
        if (bot.P_1922_E == null || bot.P_1922_E.Q_2552_b == null) {
            this.u_2550_I("\u0411\u043e\u0442 \u043d\u0435 \u0433\u043e\u0442\u043e\u0432.");
            return;
        }
        lightning.product.J_1907_R.n_1700_B(bot.P_1922_E.Q_2552_b.t_4043_B(), targetPlayer);
        bot.P_1922_E.Q_2552_b.n_1700_B("/balance");
        this.s_956_w("\u0417\u0430\u043f\u0440\u043e\u0441 \u0431\u0430\u043b\u0430\u043d\u0441\u0430 \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d \u0431\u043e\u0442\u0443 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + ", \u0446\u0435\u043b\u044c: " + String.valueOf((Object)D_4024_W.M_588_G) + targetPlayer);
    }

    private void J_1907_R(String botName, String blockName) {
        n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
        if (bot == null) {
            this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            return;
        }
        T_2915_h block = null;
        try {
            block = V_3137_a.q_4610_l.J_1907_R(new g_2336_b("minecraft:" + blockName.toLowerCase())).orElse(null);
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (block == null) {
            this.u_2550_I("\u0411\u043b\u043e\u043a \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + blockName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            return;
        }
        P_4830_p miningBehavior = new P_4830_p();
        miningBehavior.n_1700_B(block);
        bot.n_1700_B(miningBehavior);
        this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0442\u0435\u043f\u0435\u0440\u044c \u0434\u043e\u0431\u044b\u0432\u0430\u0435\u0442 " + String.valueOf((Object)D_4024_W.v_4262_N) + blockName);
    }

    private void n_1700_B(String botName, n_1700_B bot) {
        h_1847_R patrolBehavior = this.R_4764_Y.computeIfAbsent(botName, k -> new h_1847_R());
        c_1514_x playerPos = F_3572_x.J_1907_R.Y_259_p.b_2312_j();
        patrolBehavior.n_1700_B(playerPos);
        this.s_956_w("\u0422\u043e\u0447\u043a\u0430 \u043f\u0430\u0442\u0440\u0443\u043b\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u044f \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0430 \u0434\u043b\u044f \u0431\u043e\u0442\u0430 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u043d\u0430 " + String.valueOf((Object)D_4024_W.u_2550_I) + "X:" + playerPos.getX() + " Y:" + playerPos.getY() + " Z:" + playerPos.getZ());
    }

    private void v_4262_N(String botName) {
        h_1847_R patrolBehavior = this.R_4764_Y.get(botName);
        if (patrolBehavior == null) {
            this.u_2550_I("\u0423 \u0431\u043e\u0442\u0430 " + botName + " \u043d\u0435\u0442 \u0442\u043e\u0447\u0435\u043a \u043f\u0430\u0442\u0440\u0443\u043b\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u044f");
            return;
        }
        patrolBehavior.n_1700_B();
        this.s_956_w("\u0412\u0441\u0435 \u0442\u043e\u0447\u043a\u0438 \u043f\u0430\u0442\u0440\u0443\u043b\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u044f \u0434\u043b\u044f \u0431\u043e\u0442\u0430 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u044b\u043b\u0438 \u0443\u0434\u0430\u043b\u0435\u043d\u044b");
    }

    private void J_1907_R(String botName, n_1700_B bot) {
        h_1847_R patrolBehavior = this.R_4764_Y.get(botName);
        if (patrolBehavior == null || patrolBehavior.J_1907_R()) {
            this.u_2550_I("\u0423 \u0431\u043e\u0442\u0430 " + botName + " \u043d\u0435\u0442 \u0442\u043e\u0447\u0435\u043a \u043f\u0430\u0442\u0440\u0443\u043b\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u044f");
            return;
        }
        bot.n_1700_B(patrolBehavior);
        this.s_956_w("\u0411\u043e\u0442 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u043d\u0430\u0447\u0430\u043b \u043f\u0430\u0442\u0440\u0443\u043b\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u0435");
    }

    private void t_148_a() {
        String enabled = ProxyServer.proxyEnabled ? String.valueOf((Object)D_4024_W.u_2550_I) + "\u0432\u043a\u043b\u044e\u0447\u0451\u043d" : String.valueOf((Object)D_4024_W.P_4830_p) + "\u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d";
        String ip = ProxyServer.proxy != null && ProxyServer.proxy.ipPort != null && !ProxyServer.proxy.ipPort.isEmpty() ? ProxyServer.proxy.ipPort : "none";
        String type = ProxyServer.proxy != null ? ProxyServer.proxy.type.name() : "SOCKS5";
        String user = ProxyServer.proxy != null && ProxyServer.proxy.username != null && !ProxyServer.proxy.username.isEmpty() ? ProxyServer.proxy.username : "-";
        String pass = ProxyServer.proxy != null && ProxyServer.proxy.password != null && !ProxyServer.proxy.password.isEmpty() ? "*****" : "-";
        this.s_956_w("\u041f\u0440\u043e\u043a\u0441\u0438: " + enabled + String.valueOf((Object)D_4024_W.Q_2552_b) + ", \u0442\u0438\u043f: " + type + ", \u0430\u0434\u0440\u0435\u0441: " + ip + ", user: " + user + ", pass: " + pass);
    }

    private int n_1700_B(String type, String ipPort, String user, String pass) {
        boolean isSocks4;
        if (type.equalsIgnoreCase("socks4") || type.equalsIgnoreCase("s4")) {
            isSocks4 = true;
        } else if (type.equalsIgnoreCase("socks5") || type.equalsIgnoreCase("s5")) {
            isSocks4 = false;
        } else {
            this.u_2550_I("\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u044b\u0439 \u0442\u0438\u043f: " + type + ". \u0414\u043e\u043f\u0443\u0441\u0442\u0438\u043c\u043e: socks4, socks5");
            return 1;
        }
        if (!this.w_1484_f(ipPort)) {
            this.u_2550_I("\u041d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 \u0444\u043e\u0440\u043c\u0430\u0442 ip:port - \u043e\u0436\u0438\u0434\u0430\u0435\u0442\u0441\u044f \u0432\u0440\u043e\u0434\u0435 127.0.0.1:1080");
            return 1;
        }
        ProxyServer.proxy = new Proxy(isSocks4, ipPort, user, pass);
        Config.saveConfig();
        this.s_956_w("\u041f\u0440\u043e\u043a\u0441\u0438 \u0441\u043e\u0445\u0440\u0430\u043d\u0451\u043d: \u0442\u0438\u043f " + (isSocks4 ? "SOCKS4" : "SOCKS5") + ", \u0430\u0434\u0440\u0435\u0441 " + String.valueOf((Object)D_4024_W.M_588_G) + ipPort);
        return 1;
    }

    private void s_956_w() {
        if (ProxyServer.proxy == null || ProxyServer.proxy.ipPort == null || ProxyServer.proxy.ipPort.isEmpty()) {
            this.u_2550_I("\u041f\u0440\u043e\u043a\u0441\u0438 \u043d\u0435 \u0437\u0430\u0434\u0430\u043d. \u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439\u0442\u0435 .bot proxy set ...");
            return;
        }
        TestPing tp = new TestPing();
        String host = J_1907_R.t_4043_B() != null ? F_3572_x.J_1907_R.t_4043_B().J_1907_R : "mc.funtime.su:25565";
        String[] hp = host.split(":");
        String h = hp[0];
        int p = hp.length > 1 ? Integer.parseInt(hp[1]) : 25565;
        tp.run(h, p, ProxyServer.proxy);
        this.s_956_w("\u0417\u0430\u043f\u0443\u0449\u0435\u043d \u0442\u0435\u0441\u0442 \u043f\u0440\u043e\u043a\u0441\u0438 \u043a " + h + ":" + p + "; \u043f\u0440\u043e\u0432\u0435\u0440\u044c\u0442\u0435 \u043e\u043a\u043d\u043e Proxy \u0438\u043b\u0438 \u043b\u043e\u0433.");
    }

    private void u_2550_I() {
        this.s_956_w(String.valueOf((Object)D_4024_W.v_4262_N) + "\u041f\u0440\u043e\u043a\u0441\u0438 - \u0441\u043f\u0440\u0430\u0432\u043a\u0430");
        this.s_956_w(String.valueOf((Object)D_4024_W.w_1484_f) + "\u041a\u043e\u043c\u0430\u043d\u0434\u044b:");
        this.s_956_w(" .bot proxy status - \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u044c \u0442\u0435\u043a\u0443\u0449\u0438\u0435 \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438");
        this.s_956_w(" .bot proxy enable/on / disable/off - \u0432\u043a\u043b\u044e\u0447\u0438\u0442\u044c/\u0432\u044b\u043a\u043b\u044e\u0447\u0438\u0442\u044c \u043f\u0440\u043e\u043a\u0441\u0438 \u0434\u043b\u044f \u0431\u043e\u0442\u043e\u0432");
        this.s_956_w(" .bot proxy set " + String.valueOf((Object)D_4024_W.P_4830_p) + "<socks4|socks5> <ip:port> [user] [pass]");
        this.s_956_w(" .bot proxy test - \u043f\u0440\u043e\u0442\u0435\u0441\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u0447\u0435\u0440\u0435\u0437 \u043f\u0440\u043e\u043a\u0441\u0438 \u043a \u0442\u0435\u043a\u0443\u0449\u0435\u043c\u0443 \u0441\u0435\u0440\u0432\u0435\u0440\u0443");
        this.s_956_w(String.valueOf((Object)D_4024_W.w_1484_f) + "\u041f\u0440\u0438\u043c\u0435\u0440\u044b:");
        this.s_956_w("  " + String.valueOf((Object)D_4024_W.M_182_A) + ".bot proxy set socks5 127.0.0.1:9050");
        this.s_956_w("  " + String.valueOf((Object)D_4024_W.M_182_A) + ".bot proxy set socks5 127.0.0.1:9050 user pass");
        this.s_956_w("  " + String.valueOf((Object)D_4024_W.M_182_A) + ".bot proxy enable");
        this.s_956_w("  " + String.valueOf((Object)D_4024_W.M_182_A) + ".bot start 5 my.server.com:25565");
    }

    private boolean w_1484_f(String ipP) {
        String[] split = ipP.split(":");
        if (split.length != 2) {
            return false;
        }
        try {
            int port = Integer.parseInt(split[1]);
            return port >= 0 && port <= 65535 && !split[0].isEmpty();
        }
        catch (Exception e) {
            return false;
        }
    }

    private void n_1700_B(String fileName, String server, int intervalSeconds) {
        if (this.G_564_y.get() != null && this.G_564_y.get().isAlive()) {
            this.u_2550_I("\u041c\u0430\u0441\u0441\u043e\u0432\u044b\u0439 \u0437\u0430\u043f\u0443\u0441\u043a \u0443\u0436\u0435 \u0430\u043a\u0442\u0438\u0432\u0435\u043d. \u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439\u0442\u0435 .bot connectfast stop");
            return;
        }
        Path filePath = Paths.get(fileName, new String[0]);
        if (!Files.exists(filePath, new LinkOption[0])) {
            this.u_2550_I("\u0424\u0430\u0439\u043b \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d: " + fileName);
            return;
        }
        List<String> names = this.n_1700_B(filePath);
        if (names.isEmpty()) {
            this.u_2550_I("\u0424\u0430\u0439\u043b \u043d\u0435 \u0441\u043e\u0434\u0435\u0440\u0436\u0438\u0442 \u0432\u0430\u043b\u0438\u0434\u043d\u044b\u0445 \u0438\u043c\u0451\u043d!");
            return;
        }
        long intervalMillis = (long)intervalSeconds * 1000L;
        this.s_956_w("\u0417\u0430\u043f\u0443\u0441\u043a " + String.valueOf((Object)D_4024_W.u_2550_I) + names.size() + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u0442\u043e\u0432 \u0441 \u0438\u043d\u0442\u0435\u0440\u0432\u0430\u043b\u043e\u043c " + String.valueOf((Object)D_4024_W.Q_4569_t) + intervalSeconds + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0441\u0435\u043a");
        this.s_956_w(String.valueOf((Object)D_4024_W.w_1484_f) + "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439\u0442\u0435 " + String.valueOf((Object)D_4024_W.P_4830_p) + ".bot connectfast stop" + String.valueOf((Object)D_4024_W.w_1484_f) + " \u0434\u043b\u044f \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043a\u0438");
        this.P_1922_E.set(false);
        Thread worker = new Thread(() -> {
            int launched = 0;
            try {
                for (String name : names) {
                    if (this.P_1922_E.get()) {
                        this.s_956_w(String.valueOf((Object)D_4024_W.Q_4569_t) + "\u041c\u0430\u0441\u0441\u043e\u0432\u044b\u0439 \u0437\u0430\u043f\u0443\u0441\u043a \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d! \u0417\u0430\u043f\u0443\u0449\u0435\u043d\u043e: " + launched + " \u0431\u043e\u0442\u043e\u0432");
                        return;
                    }
                    try {
                        lightning.product.R_4764_Y.n_1700_B(name, server);
                        this.s_956_w("\u0417\u0430\u043f\u0443\u0449\u0435\u043d \u0431\u043e\u0442: " + String.valueOf((Object)D_4024_W.M_588_G) + name + String.valueOf((Object)D_4024_W.w_1484_f) + " (" + ++launched + "/" + names.size() + ")");
                    }
                    catch (Exception e) {
                        this.u_2550_I("\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u0437\u0430\u043f\u0443\u0441\u043a\u0435 " + name + ": " + e.getMessage());
                    }
                    try {
                        Thread.sleep(intervalMillis);
                    }
                    catch (InterruptedException e) {
                        if (this.P_1922_E.get()) {
                            this.s_956_w(String.valueOf((Object)D_4024_W.Q_4569_t) + "\u041c\u0430\u0441\u0441\u043e\u0432\u044b\u0439 \u0437\u0430\u043f\u0443\u0441\u043a \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d! \u0417\u0430\u043f\u0443\u0449\u0435\u043d\u043e: " + launched + " \u0431\u043e\u0442\u043e\u0432");
                        } else {
                            this.u_2550_I("\u041c\u0430\u0441\u0441\u043e\u0432\u044b\u0439 \u0437\u0430\u043f\u0443\u0441\u043a \u043f\u0440\u0435\u0440\u0432\u0430\u043d!");
                        }
                        this.G_564_y.set(null);
                        this.P_1922_E.set(false);
                        return;
                    }
                }
                this.s_956_w(String.valueOf((Object)D_4024_W.u_2550_I) + "\u0412\u0441\u0435 \u0431\u043e\u0442\u044b \u0437\u0430\u043f\u0443\u0449\u0435\u043d\u044b! \u0412\u0441\u0435\u0433\u043e: " + launched);
            }
            finally {
                this.G_564_y.set(null);
                this.P_1922_E.set(false);
            }
        }, "BotConnectFast");
        this.G_564_y.set(worker);
        worker.start();
    }

    private List<String> n_1700_B(Path filePath) {
        try {
            List<String> rawNames = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            ArrayList<String> names = new ArrayList<String>();
            for (String name : rawNames) {
                String trimmed = name == null ? "" : name.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) continue;
                names.add(trimmed);
            }
            return names;
        }
        catch (Exception e) {
            this.u_2550_I("\u041e\u0448\u0438\u0431\u043a\u0430 \u0447\u0442\u0435\u043d\u0438\u044f \u0444\u0430\u0439\u043b\u0430: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private void n_1700_B(AtomicReference<Thread> threadRef, AtomicBoolean stopFlag) {
        Thread thread = threadRef.get();
        if (thread != null && thread.isAlive()) {
            stopFlag.set(true);
            thread.interrupt();
            this.s_956_w(String.valueOf((Object)D_4024_W.Q_4569_t) + "\u041e\u0441\u0442\u0430\u043d\u043e\u0432\u043a\u0430 \u043c\u0430\u0441\u0441\u043e\u0432\u043e\u0433\u043e \u0437\u0430\u043f\u0443\u0441\u043a\u0430 \u0431\u043e\u0442\u043e\u0432...");
        } else {
            this.u_2550_I("\u041c\u0430\u0441\u0441\u043e\u0432\u044b\u0439 \u0437\u0430\u043f\u0443\u0441\u043a \u043d\u0435 \u0430\u043a\u0442\u0438\u0432\u0435\u043d!");
        }
    }

    private void n_1700_B(String botName, int delayTicks) {
        if (botName.equalsIgnoreCase("all")) {
            int count = 0;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                bot.P_4830_p = true;
                bot.h_1847_R = delayTicks;
                ++count;
            }
            if (count > 0) {
                this.s_956_w("Rejoin \u0432\u043a\u043b\u044e\u0447\u0451\u043d \u0434\u043b\u044f " + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u0442\u043e\u0432. \u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430: " + String.valueOf((Object)D_4024_W.Q_4569_t) + delayTicks + " \u0442\u0438\u043a\u043e\u0432 (" + delayTicks / 20 + " \u0441\u0435\u043a)");
            } else {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
            }
            return;
        }
        n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
        if (bot == null) {
            this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            return;
        }
        bot.P_4830_p = true;
        bot.h_1847_R = delayTicks;
        this.s_956_w("Rejoin \u0432\u043a\u043b\u044e\u0447\u0451\u043d \u0434\u043b\u044f \u0431\u043e\u0442\u0430 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName + String.valueOf((Object)D_4024_W.Q_2552_b) + ". \u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430: " + String.valueOf((Object)D_4024_W.Q_4569_t) + delayTicks + " \u0442\u0438\u043a\u043e\u0432 (" + delayTicks / 20 + " \u0441\u0435\u043a)");
    }

    private void t_148_a(String botName) {
        if (botName.equalsIgnoreCase("all")) {
            int count = 0;
            for (n_1700_B bot : lightning.product.J_1907_R.n_1700_B) {
                bot.P_4830_p = false;
                ++count;
            }
            if (count > 0) {
                this.s_956_w("Rejoin \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d \u0434\u043b\u044f " + String.valueOf((Object)D_4024_W.P_4830_p) + count + String.valueOf((Object)D_4024_W.Q_2552_b) + " \u0431\u043e\u0442\u043e\u0432");
            } else {
                this.u_2550_I("\u041d\u0435\u0442 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043d\u044b\u0445 \u0431\u043e\u0442\u043e\u0432!");
            }
            return;
        }
        n_1700_B bot = lightning.product.J_1907_R.R_4764_Y(botName);
        if (bot == null) {
            this.u_2550_I("\u0411\u043e\u0442 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + botName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
            return;
        }
        bot.P_4830_p = false;
        this.s_956_w("Rejoin \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d \u0434\u043b\u044f \u0431\u043e\u0442\u0430 " + String.valueOf((Object)D_4024_W.P_4830_p) + botName);
    }

    private void s_956_w(String message) {
        v_1900_v.n_1700_B(new U_2871_b(message), new Object[0]);
    }

    private void u_2550_I(String message) {
        v_1900_v.n_1700_B(new U_2871_b(message).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)), new Object[0]);
    }
}


