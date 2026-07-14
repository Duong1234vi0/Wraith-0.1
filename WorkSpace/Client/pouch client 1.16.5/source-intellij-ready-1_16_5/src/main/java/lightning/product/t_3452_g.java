/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.List;
import lightning.product.TrashTalk;
import lightning.product.C_332_W;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class t_3452_g
extends o_2341_D {
    public t_3452_g() {
        super("trashtalk", "tt");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(this::u_1723_Y);
        builder.then(t_3452_g.n_1700_B("add").then(t_3452_g.n_1700_B("type", StringArgumentType.word()).then(t_3452_g.n_1700_B("phrase", StringArgumentType.greedyString()).executes(this::J_1907_R))));
        builder.then(t_3452_g.n_1700_B("remove").then(t_3452_g.n_1700_B("type", StringArgumentType.word()).then(t_3452_g.n_1700_B("index", IntegerArgumentType.integer((int)1)).executes(this::R_4764_Y))));
        builder.then(t_3452_g.n_1700_B("list").executes(ctx -> this.n_1700_B((CommandContext<V_4217_p>)ctx, null)));
        builder.then(t_3452_g.n_1700_B("list").then(t_3452_g.n_1700_B("type", StringArgumentType.word()).executes(ctx -> this.n_1700_B((CommandContext<V_4217_p>)ctx, StringArgumentType.getString((CommandContext)ctx, (String)"type")))));
        builder.then(t_3452_g.n_1700_B("clear").then(t_3452_g.n_1700_B("type", StringArgumentType.word()).executes(this::G_564_y)));
        builder.then(t_3452_g.n_1700_B("help").executes(this::u_1723_Y));
    }

    private int J_1907_R(CommandContext<V_4217_p> ctx) {
        TrashTalk.n_1700_B kind = TrashTalk.n_1700_B.n_1700_B((String)ctx.getArgument("type", String.class));
        if (kind == null) {
            return this.P_1922_E(ctx);
        }
        String phrase = (String)ctx.getArgument("phrase", String.class);
        TrashTalk tt = TrashTalk.h_1847_R();
        if (tt == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7cTrashTalk \u043c\u043e\u0434\u0443\u043b\u044c \u043d\u0435 \u0438\u043d\u0438\u0446\u0438\u0430\u043b\u0438\u0437\u0438\u0440\u043e\u0432\u0430\u043d"), new Object[0]);
            return 1;
        }
        boolean ok = tt.n_1700_B(kind, phrase);
        if (ok) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u0414\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u043e \u0432 \u00a7f" + kind.G_564_y + "\u00a7a: \u00a7f" + phrase.trim()), new Object[0]);
        } else {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041f\u0443\u0441\u0442\u0430\u044f \u0444\u0440\u0430\u0437\u0430 \u043d\u0435 \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u0430"), new Object[0]);
        }
        return 1;
    }

    private int R_4764_Y(CommandContext<V_4217_p> ctx) {
        TrashTalk.n_1700_B kind = TrashTalk.n_1700_B.n_1700_B((String)ctx.getArgument("type", String.class));
        if (kind == null) {
            return this.P_1922_E(ctx);
        }
        int idx = (Integer)ctx.getArgument("index", Integer.class);
        TrashTalk tt = TrashTalk.h_1847_R();
        if (tt == null) {
            return 1;
        }
        String removed = tt.n_1700_B(kind, idx);
        if (removed == null) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041d\u0435\u0442 \u0444\u0440\u0430\u0437\u044b \u0441 \u0438\u043d\u0434\u0435\u043a\u0441\u043e\u043c " + idx + " \u0432 \u00a7f" + kind.G_564_y), new Object[0]);
        } else {
            v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u0423\u0434\u0430\u043b\u0435\u043d\u043e \u00a77(" + kind.G_564_y + " #" + idx + ")\u00a7a: \u00a7f" + removed), new Object[0]);
        }
        return 1;
    }

    private int n_1700_B(CommandContext<V_4217_p> ctx, String typeRaw) {
        TrashTalk.n_1700_B only;
        TrashTalk tt = TrashTalk.h_1847_R();
        if (tt == null) {
            return 1;
        }
        TrashTalk.n_1700_B n_1700_B2 = only = typeRaw == null ? null : TrashTalk.n_1700_B.n_1700_B(typeRaw);
        if (typeRaw != null && only == null) {
            return this.P_1922_E(ctx);
        }
        if (only == null || only == TrashTalk.n_1700_B.n_1700_B) {
            this.n_1700_B(tt, TrashTalk.n_1700_B.n_1700_B);
        }
        if (only == null || only == TrashTalk.n_1700_B.J_1907_R) {
            this.n_1700_B(tt, TrashTalk.n_1700_B.J_1907_R);
        }
        return 1;
    }

    private void n_1700_B(TrashTalk tt, TrashTalk.n_1700_B kind) {
        List<String> custom = tt.J_1907_R(kind);
        int def = tt.R_4764_Y(kind);
        v_1900_v.n_1700_B(new U_2871_b("\u00a77=== \u00a7f" + kind.G_564_y + " \u00a77(\u0434\u0435\u0444\u043e\u043b\u0442: \u00a7f" + def + "\u00a77, \u043a\u0430\u0441\u0442\u043e\u043c: \u00a7f" + custom.size() + "\u00a77) ==="), new Object[0]);
        if (custom.isEmpty()) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a78(\u043d\u0435\u0442 \u043a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0445 \u0444\u0440\u0430\u0437)"), new Object[0]);
            return;
        }
        for (int i = 0; i < custom.size(); ++i) {
            v_1900_v.n_1700_B(new U_2871_b("\u00a77" + (i + 1) + ". \u00a7f" + custom.get(i)), new Object[0]);
        }
    }

    private int G_564_y(CommandContext<V_4217_p> ctx) {
        TrashTalk.n_1700_B kind = TrashTalk.n_1700_B.n_1700_B((String)ctx.getArgument("type", String.class));
        if (kind == null) {
            return this.P_1922_E(ctx);
        }
        TrashTalk tt = TrashTalk.h_1847_R();
        if (tt == null) {
            return 1;
        }
        int n = tt.n_1700_B(kind);
        v_1900_v.n_1700_B(new U_2871_b("\u00a7a\u041e\u0447\u0438\u0449\u0435\u043d\u043e: \u00a7f" + n + " \u00a7a\u043a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0445 \u0444\u0440\u0430\u0437 \u0432 \u00a7f" + kind.G_564_y), new Object[0]);
        return 1;
    }

    private int P_1922_E(CommandContext<V_4217_p> ctx) {
        v_1900_v.n_1700_B(new U_2871_b("\u00a7c\u041d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 \u0442\u0438\u043f. \u0418\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439 \u00a7fkill\u00a7c (\u0438\u043b\u0438 \u00a7f\u0432\u0440\u0430\u0433\u0443\u00a7c) \u043b\u0438\u0431\u043e \u00a7fdeath\u00a7c (\u0438\u043b\u0438 \u00a7f\u0441\u043c\u0435\u0440\u0442\u044c\u00a7c)"), new Object[0]);
        return 1;
    }

    private int u_1723_Y(CommandContext<V_4217_p> ctx) {
        v_1900_v.n_1700_B(new U_2871_b("\u00a76=== TrashTalk ===\n\u00a77.trashtalk add <kill|death> <\u0444\u0440\u0430\u0437\u0430> \u00a78- \u0434\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u043a\u0430\u0441\u0442\u043e\u043c\u043d\u0443\u044e \u0444\u0440\u0430\u0437\u0443\n\u00a77.trashtalk remove <kill|death> <\u0438\u043d\u0434\u0435\u043a\u0441> \u00a78- \u0443\u0434\u0430\u043b\u0438\u0442\u044c \u043f\u043e \u043d\u043e\u043c\u0435\u0440\u0443 \u0438\u0437 \u0441\u043f\u0438\u0441\u043a\u0430\n\u00a77.trashtalk list [kill|death] \u00a78- \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u044c \u043a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0435 \u0444\u0440\u0430\u0437\u044b\n\u00a77.trashtalk clear <kill|death> \u00a78- \u043e\u0447\u0438\u0441\u0442\u0438\u0442\u044c \u043a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0439 \u0441\u043f\u0438\u0441\u043e\u043a\n\u00a78(\u043f\u0441\u0435\u0432\u0434\u043e\u043d\u0438\u043c: \u00a77.tt\u00a78, kill = \u0437\u0430 \u043a\u0438\u043b\u043b \u0432\u0440\u0430\u0433\u0430, death = \u043a\u043e\u0433\u0434\u0430 \u0441\u0430\u043c \u0443\u043c\u0435\u0440)\n\u00a78\u041a\u0430\u0441\u0442\u043e\u043c\u043d\u044b\u0435 \u0444\u0440\u0430\u0437\u044b \u0441\u043e\u0445\u0440\u0430\u043d\u044f\u044e\u0442\u0441\u044f \u0432 \u00a77" + C_332_W.n_1700_B + "trashtalk.json"), new Object[0]);
        return 1;
    }
}


