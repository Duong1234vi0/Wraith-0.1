/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lightning.product.D_4024_W;
import lightning.product.H_1873_g;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.j_1654_T;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.r_4414_L;
import lightning.product.v_1900_v;

public class X_4895_T
extends o_2341_D
implements ArgumentType<String>,
MinecraftAccess {
    public X_4895_T() {
        super("mac", "macros");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(X_4895_T.n_1700_B("add").then(X_4895_T.n_1700_B("args", StringArgumentType.greedyString()).suggests(this::J_1907_R).executes(this::J_1907_R)));
        builder.then(X_4895_T.n_1700_B("remove").then(X_4895_T.n_1700_B("name", StringArgumentType.word()).suggests(this::n_1700_B).executes(ctx -> {
            String name = (String)ctx.getArgument("name", String.class);
            ClientBootstrap.Y_601_j().t_148_a().n_1700_B(name);
            MutableComponent msg = new U_2871_b("\u041c\u0430\u043a\u0440\u043e\u0441 c \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)).n_1700_B(new U_2871_b(name).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A))).n_1700_B(new U_2871_b("' \u0443\u0434\u0430\u043b\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
            v_1900_v.n_1700_B(msg, new Object[0]);
            return 1;
        })));
        builder.then(X_4895_T.n_1700_B("list").executes(ctx -> {
            List<r_4414_L.n_1700_B> macros = ClientBootstrap.Y_601_j().t_148_a().P_4830_p();
            if (macros.isEmpty()) {
                v_1900_v.n_1700_B(new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043c\u0430\u043a\u0440\u043e\u0441\u043e\u0432 \u043f\u0443\u0441\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
                return 1;
            }
            v_1900_v.n_1700_B(new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043c\u0430\u043a\u0440\u043e\u0441\u043e\u0432:").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
            for (r_4414_L.n_1700_B m : macros) {
                String key = j_1654_T.n_1700_B(m.J_1907_R());
                MutableComponent nameLbl = new U_2871_b("\u0418\u043c\u044f: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent nameVal = new U_2871_b(m.n_1700_B()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent keyLbl = new U_2871_b(" \u041a\u043b\u0430\u0432\u0438\u0448\u0430: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent keyVal = new U_2871_b(key).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent textLbl = new U_2871_b(" \u0422\u0435\u043a\u0441\u0442: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent textVal = new U_2871_b(m.R_4764_Y()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I));
                String removeCmd = ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B() + this.n_1700_B() + " remove " + m.n_1700_B();
                MutableComponent removeBtn = new U_2871_b(" [\u0423\u0434\u0430\u043b\u0438\u0442\u044c]").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p).n_1700_B(new H_1873_g(i_2909_p.n_1700_B.R_4764_Y, removeCmd)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("\u041a\u043b\u0438\u043a \u0434\u043b\u044f \u0443\u0434\u0430\u043b\u0435\u043d\u0438\u044f \u043c\u0430\u043a\u0440\u043e\u0441\u0430").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)))));
                v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(nameLbl).n_1700_B(nameVal).n_1700_B(keyLbl).n_1700_B(keyVal).n_1700_B(textLbl).n_1700_B(textVal).n_1700_B(removeBtn), new Object[0]);
            }
            v_1900_v.n_1700_B(new U_2871_b("\u0412\u0441\u0435\u0433\u043e: " + macros.size()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
            return 1;
        }));
        builder.then(X_4895_T.n_1700_B("clear").executes(ctx -> {
            List<r_4414_L.n_1700_B> macros = ClientBootstrap.Y_601_j().t_148_a().P_4830_p();
            if (macros.isEmpty()) {
                v_1900_v.n_1700_B(new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043c\u0430\u043a\u0440\u043e\u0441\u043e\u0432 \u043f\u0443\u0441\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
                return 1;
            }
            ClientBootstrap.Y_601_j().t_148_a().M_588_G();
            v_1900_v.n_1700_B(new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043c\u0430\u043a\u0440\u043e\u0441\u043e\u0432 \u043e\u0447\u0438\u0449\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)), new Object[0]);
            return 1;
        }));
    }

    private int J_1907_R(CommandContext<V_4217_p> ctx) {
        String rest = ((String)ctx.getArgument("args", String.class)).trim();
        if (rest.isEmpty()) {
            return this.n_1700_B(ctx);
        }
        String[] tokens = rest.split("\\s+");
        if (tokens.length < 3) {
            return this.n_1700_B(ctx);
        }
        String name = tokens[0];
        String keyToken = tokens[1];
        String command = String.join((CharSequence)" ", Arrays.asList(tokens).subList(2, tokens.length));
        j_1654_T key = j_1654_T.n_1700_B(keyToken);
        if (key == null) {
            return this.n_1700_B(ctx);
        }
        ClientBootstrap.Y_601_j().t_148_a().n_1700_B(name, key.J_1907_R(), command);
        MutableComponent msg = new U_2871_b("\u041c\u0430\u043a\u0440\u043e\u0441 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)).n_1700_B(new U_2871_b(name).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A))).n_1700_B(new U_2871_b("' \u0438 \u043a\u043d\u043e\u043f\u043a\u043e\u0439 \u0430\u043a\u0442\u0438\u0432\u0430\u0446\u0438\u0438: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f))).n_1700_B(new U_2871_b(j_1654_T.n_1700_B(key.J_1907_R())).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A))).n_1700_B(new U_2871_b(" \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
        v_1900_v.n_1700_B(msg, new Object[0]);
        return 1;
    }

    private CompletableFuture<Suggestions> n_1700_B(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        List<String> names = ClientBootstrap.Y_601_j().t_148_a().P_4830_p().stream().map(r_4414_L.n_1700_B::n_1700_B).collect(Collectors.toList());
        return V_4217_p.J_1907_R(names, builder);
    }

    private CompletableFuture<Suggestions> J_1907_R(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        String[] parts;
        String input = ctx.getInput();
        String remaining = builder.getRemaining();
        boolean ends = remaining.endsWith(" ");
        String trimmed = remaining.trim();
        String[] stringArray = parts = trimmed.isEmpty() ? new String[]{} : trimmed.split("\\s+");
        int replaceStart = parts.length == 0 ? input.length() : (ends ? input.length() : input.length() - parts[parts.length - 1].length());
        SuggestionsBuilder sb = new SuggestionsBuilder(input, replaceStart);
        if (parts.length == 0 || parts.length == 1 && !ends) {
            return sb.buildFuture();
        }
        if (parts.length == 1 || parts.length == 2 && !ends) {
            String keyPrefix = parts.length == 2 ? parts[1].toUpperCase(Locale.ROOT) : "";
            for (j_1654_T key : j_1654_T.values()) {
                if (!key.name().startsWith(keyPrefix)) continue;
                sb.suggest(key.name());
            }
            return sb.buildFuture();
        }
        return sb.buildFuture();
    }

    public String n_1700_B(StringReader reader) throws CommandSyntaxException {
        return reader.readString();
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}



