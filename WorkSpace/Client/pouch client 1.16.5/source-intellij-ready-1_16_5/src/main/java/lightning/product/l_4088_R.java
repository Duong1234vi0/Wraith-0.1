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
import lightning.product.G_624_v;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class l_4088_R
extends o_2341_D {
    public l_4088_R() {
        super("irc");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(this::J_1907_R);
        builder.then(l_4088_R.n_1700_B("on").executes(ctx -> {
            if (!G_1539_D.n_1700_B.G_564_y()) {
                v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[IRC] \u041d\u0435\u0442 \u0430\u0434\u0440\u0435\u0441\u0430 relay: DEFAULT_IRC_BASE \u0432 \u043a\u043e\u0434\u0435 \u0438\u043b\u0438 -Dpouch.irc.url=\u2026"), new Object[0]);
                return 1;
            }
            if (G_1539_D.n_1700_B.P_1922_E()) {
                v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.M_182_A) + "\u0423\u0436\u0435 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u043e"), new Object[0]);
                return 1;
            }
            G_1539_D.n_1700_B.M_588_G();
            v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.w_1484_f) + "\u041f\u043e\u0434\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u043a IRC\u2026"), new Object[0]);
            return 1;
        }));
        builder.then(l_4088_R.n_1700_B("off").executes(ctx -> {
            G_1539_D.n_1700_B.P_4830_p();
            v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.M_182_A) + "\u041e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u043e"), new Object[0]);
            return 1;
        }));
        builder.then(((LiteralArgumentBuilder)l_4088_R.n_1700_B("party").executes(ctx -> {
            v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.w_1484_f) + "\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435: .irc party <\u0442\u0435\u043a\u0441\u0442>"), new Object[0]);
            return 1;
        })).then(l_4088_R.n_1700_B("message", StringArgumentType.greedyString()).executes(ctx -> {
            if (!G_1539_D.n_1700_B.P_1922_E()) {
                v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[IRC] \u0421\u043d\u0430\u0447\u0430\u043b\u0430 .irc on"), new Object[0]);
                return 1;
            }
            String room = G_1539_D.u_1723_Y();
            if (room == null || !G_1539_D.J_1907_R(room)) {
                v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[IRC] \u0421\u043d\u0430\u0447\u0430\u043b\u0430 \u0432\u043e\u0439\u0434\u0438\u0442\u0435 \u0432 \u043b\u0438\u0447\u043d\u0443\u044e \u043a\u043e\u043c\u043d\u0430\u0442\u0443 (.party create / .party join)"), new Object[0]);
                return 1;
            }
            String message = (String)ctx.getArgument("message", String.class);
            if (MinecraftAccess.c_3005_b.Y_259_p == null) {
                return 1;
            }
            String nick = l_4088_R.J_1907_R();
            G_1539_D.n_1700_B.n_1700_B(nick, message, room.trim());
            return 1;
        })));
        builder.then(l_4088_R.n_1700_B("message", StringArgumentType.greedyString()).executes(ctx -> {
            if (!G_1539_D.n_1700_B.P_1922_E()) {
                v_1900_v.J_1907_R(new U_2871_b(String.valueOf((Object)D_4024_W.P_4830_p) + "[IRC] \u0421\u043d\u0430\u0447\u0430\u043b\u0430 .irc on"), new Object[0]);
                return 1;
            }
            String message = (String)ctx.getArgument("message", String.class);
            if (MinecraftAccess.c_3005_b.Y_259_p == null) {
                return 1;
            }
            String nick = l_4088_R.J_1907_R();
            G_1539_D.n_1700_B.n_1700_B(nick, message);
            return 1;
        }));
    }

    private static String J_1907_R() {
        String u = G_624_v.t_148_a.n_1700_B;
        if (u != null && !(u = u.trim()).isEmpty()) {
            return u;
        }
        if (MinecraftAccess.c_3005_b.Y_259_p != null) {
            return MinecraftAccess.c_3005_b.Y_259_p.y_4642_Y().getName();
        }
        return "?";
    }

    private int J_1907_R(CommandContext<V_4217_p> context) {
        l_4088_R.n_1700_B(".irc on \u2014 \u043f\u043e\u0434\u043a\u043b\u044e\u0447\u0438\u0442\u044c\u0441\u044f ", D_4024_W.M_182_A);
        l_4088_R.n_1700_B(".irc off \u2014 \u043e\u0442\u043a\u043b\u044e\u0447\u0438\u0442\u044c\u0441\u044f", D_4024_W.M_182_A);
        l_4088_R.n_1700_B(".irc <\u0442\u0435\u043a\u0441\u0442> \u2014 \u043e\u0431\u0449\u0438\u0439 IRC (\u043f\u043e\u0441\u043b\u0435 .irc on)", D_4024_W.M_182_A);
        l_4088_R.n_1700_B(".irc party <\u0442\u0435\u043a\u0441\u0442> \u2014 \u0442\u043e\u043b\u044c\u043a\u043e \u0443\u0447\u0430\u0441\u0442\u043d\u0438\u043a\u0430\u043c \u0432\u0430\u0448\u0435\u0439 .party \u043a\u043e\u043c\u043d\u0430\u0442\u044b", D_4024_W.M_182_A);
        return 1;
    }

    private static void n_1700_B(String text, D_4024_W color) {
        v_1900_v.J_1907_R(new U_2871_b(text).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(color)), new Object[0]);
    }
}


