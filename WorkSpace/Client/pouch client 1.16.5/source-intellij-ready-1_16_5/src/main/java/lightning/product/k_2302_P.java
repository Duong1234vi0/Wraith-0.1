/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lightning.product.D_4024_W;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.a_3913_L;
import lightning.product.MinecraftAccess;
import lightning.product.e_2866_D;
import lightning.product.m_1628_s;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class k_2302_P
extends o_2341_D
implements ArgumentType<String>,
MinecraftAccess {
    public k_2302_P() {
        super("tp");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(context -> {
            MutableComponent usage = new U_2871_b(".tp <\u043d\u0438\u043a> - \u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442 \u043a \u0438\u0433\u0440\u043e\u043a\u0443").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(usage, new Object[0]);
            return 1;
        });
        builder.then(k_2302_P.n_1700_B("player", new k_2302_P()).executes(context -> {
            String rawInput = (String)context.getArgument("player", String.class);
            String targetName = this.J_1907_R(rawInput);
            if (k_2302_P.c_3005_b.Y_601_j == null || k_2302_P.c_3005_b.Y_259_p == null) {
                MutableComponent errorMsg = new U_2871_b("\u041c\u0438\u0440 \u043d\u0435 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d.").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p));
                v_1900_v.n_1700_B(errorMsg, new Object[0]);
                return 1;
            }
            a_3913_L target = k_2302_P.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider().stream().filter(p -> p != null && p.y_4642_Y() != null).filter(p -> {
                String profileName = p.y_4642_Y().getName();
                String display = p.c_() != null ? p.c_().getString() : profileName;
                String n1 = profileName != null ? this.J_1907_R(profileName) : "";
                String n2 = this.J_1907_R(display);
                return n1.equalsIgnoreCase(targetName) || n2.equalsIgnoreCase(targetName);
            }).findFirst().orElse(null);
            if (target == null) {
                MutableComponent errorMsg = new U_2871_b("\u0418\u0433\u0440\u043e\u043a '" + targetName + "' \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d.").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p));
                v_1900_v.n_1700_B(errorMsg, new Object[0]);
                return 1;
            }
            double x = target.O_3598_v();
            double y = target.X_2960_b();
            double z = target.l_2647_k();
            e_2866_D start = new e_2866_D(k_2302_P.c_3005_b.Y_259_p.O_3598_v(), k_2302_P.c_3005_b.Y_259_p.X_2960_b(), k_2302_P.c_3005_b.Y_259_p.l_2647_k());
            e_2866_D dest = new e_2866_D(x, y, z);
            double distance = start.u_1723_Y(dest);
            int steps = Math.max(3, Math.min(5, (int)Math.ceil(distance / 12.0)));
            m_1628_s.n_1700_B().n_1700_B(start, dest, steps);
            MutableComponent msg = new U_2871_b("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442 \u043d\u0430 \u0438\u0433\u0440\u043e\u043a\u0430 ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)).n_1700_B(new U_2871_b(targetName).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A))).n_1700_B(new U_2871_b(" (" + steps + " \u0448\u0430\u0433\u0430)").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.t_148_a)));
            v_1900_v.n_1700_B(msg, new Object[0]);
            return 1;
        }));
        builder.then(k_2302_P.n_1700_B("stop").executes(context -> {
            m_1628_s.n_1700_B().R_4764_Y();
            MutableComponent msg = new U_2871_b("\u0422\u0435\u043b\u0435\u043f\u043e\u0440\u0442 \u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(msg, new Object[0]);
            return 1;
        }));
    }

    public String n_1700_B(StringReader reader) throws CommandSyntaxException {
        return reader.readString();
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        if (c_3005_b.k_2293_S() == null) {
            return Suggestions.empty();
        }
        List<String> suggestions = c_3005_b.k_2293_S().P_1922_E().stream().map(info -> info.n_1700_B().getName()).collect(Collectors.toList());
        return V_4217_p.J_1907_R(suggestions, builder);
    }

    private String J_1907_R(String name) {
        int end;
        if (name == null) {
            return "";
        }
        String s = name.replaceAll("\u00a7.", "").trim();
        while (s.startsWith("[") && (end = s.indexOf(93)) > 0) {
            s = s.substring(end + 1).trim();
        }
        return s;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}



