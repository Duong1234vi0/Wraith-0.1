/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import lightning.product.D_4024_W;
import lightning.product.G_1539_D;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class w_2705_t
extends o_2341_D {
    public w_2705_t() {
        super("party");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(this::J_1907_R);
        builder.then(w_2705_t.n_1700_B("create").executes(ctx -> {
            if (!G_1539_D.n_1700_B.G_564_y()) {
                v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041d\u0435\u0442 \u0430\u0434\u0440\u0435\u0441\u0430 relay (-Dpouch.irc.url \u0438\u043b\u0438 DEFAULT_IRC_BASE)"), new Object[0]);
                return 1;
            }
            G_1539_D.n_1700_B.h_1847_R();
            return 1;
        }));
        builder.then(w_2705_t.n_1700_B("join").then(w_2705_t.n_1700_B("code", StringArgumentType.word()).executes(ctx -> {
            if (!G_1539_D.n_1700_B.G_564_y()) {
                v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041d\u0435\u0442 \u0430\u0434\u0440\u0435\u0441\u0430 relay (-Dpouch.irc.url \u0438\u043b\u0438 DEFAULT_IRC_BASE)"), new Object[0]);
                return 1;
            }
            String code = (String)ctx.getArgument("code", String.class);
            G_1539_D.n_1700_B.G_564_y(code);
            return 1;
        })));
        builder.then(w_2705_t.n_1700_B("leave").executes(ctx -> {
            G_1539_D.n_1700_B.Q_4569_t();
            return 1;
        }));
        builder.then(w_2705_t.n_1700_B("info").executes(ctx -> {
            if (!G_1539_D.n_1700_B.G_564_y()) {
                v_1900_v.n_1700_B(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[Party] \u041d\u0435\u0442 \u0430\u0434\u0440\u0435\u0441\u0430 relay (-Dpouch.irc.url \u0438\u043b\u0438 DEFAULT_IRC_BASE)"), new Object[0]);
                return 1;
            }
            G_1539_D.n_1700_B.M_182_A();
            return 1;
        }));
    }

    private int J_1907_R(CommandContext<V_4217_p> context) {
        v_1900_v.n_1700_B(new U_2871_b(".party create \u2014 \u043d\u043e\u0432\u0430\u044f \u043b\u0438\u0447\u043d\u0430\u044f \u043a\u043e\u043c\u043d\u0430\u0442\u0430 \u0438 6-\u0437\u043d\u0430\u0447\u043d\u044b\u0439 \u043a\u043e\u0434").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)), new Object[0]);
        v_1900_v.n_1700_B(new U_2871_b(".party join <\u043a\u043e\u0434> \u2014 \u0432\u043e\u0439\u0442\u0438 \u043f\u043e \u043a\u043e\u0434\u0443 (\u043d\u0430\u043f\u0440\u0438\u043c\u0435\u0440 .party join 798546)").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)), new Object[0]);
        v_1900_v.n_1700_B(new U_2871_b(".party leave \u2014 \u0432\u044b\u0439\u0442\u0438 \u0438\u0437 \u043b\u0438\u0447\u043d\u043e\u0439 \u043a\u043e\u043c\u043d\u0430\u0442\u044b").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)), new Object[0]);
        v_1900_v.n_1700_B(new U_2871_b(".party info \u2014 \u043a\u043e\u0434 \u043a\u043e\u043c\u043d\u0430\u0442\u044b \u0438 \u043d\u0438\u043a\u0438 \u0443\u0447\u0430\u0441\u0442\u043d\u0438\u043a\u043e\u0432 (GET /irc/party)").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)), new Object[0]);
        return 1;
    }
}

