/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import lightning.product.n_473_l;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class L_4248_u
extends o_2341_D
implements ArgumentType<String>,
MinecraftAccess {
    public L_4248_u() {
        super("fr", "friend");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(L_4248_u.n_1700_B("add").then(((RequiredArgumentBuilder)L_4248_u.n_1700_B("player", new L_4248_u()).executes(context -> {
            String friendName = (String)context.getArgument("player", String.class);
            boolean alreadyFriend = ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(friendName);
            MutableComponent prefix = new U_2871_b("\u0414\u0440\u0443\u0433 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent nameText = new U_2871_b("\"" + friendName + "\"").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            MutableComponent suffix = new U_2871_b(alreadyFriend ? " \u0443\u0436\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442!" : " \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            if (!alreadyFriend) {
                ClientBootstrap.Y_601_j().v_4262_N().n_1700_B(friendName);
            }
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(suffix), new Object[0]);
            return 1;
        })).then(L_4248_u.n_1700_B("display_as", StringArgumentType.greedyString()).executes(context -> {
            String friendName = (String)context.getArgument("player", String.class);
            String displayAs = StringArgumentType.getString((CommandContext)context, (String)"display_as");
            boolean wasFriend = ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(friendName);
            ClientBootstrap.Y_601_j().v_4262_N().n_1700_B(friendName, displayAs);
            MutableComponent prefix = new U_2871_b(wasFriend ? "\u0414\u043b\u044f " : "\u0414\u0440\u0443\u0433 ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent nameText = new U_2871_b("\"" + friendName + "\"").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            MutableComponent mid = new U_2871_b(wasFriend ? " \u043e\u0431\u043d\u043e\u0432\u043b\u0451\u043d \u043e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0435\u043c\u044b\u0439 \u043d\u0438\u043a: " : " \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d, \u0432 NameProtect \u043a\u0430\u043a: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent disp = new U_2871_b("\"" + displayAs.trim() + "\"").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(mid).n_1700_B(disp), new Object[0]);
            return 1;
        }))));
        builder.then(L_4248_u.n_1700_B("remove").then(L_4248_u.n_1700_B("friend", new L_4248_u()).executes(context -> {
            String friendName = (String)context.getArgument("friend", String.class);
            boolean isFriend = ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(friendName);
            if (!isFriend) {
                return this.n_1700_B((CommandContext<V_4217_p>)context);
            }
            ClientBootstrap.Y_601_j().v_4262_N().J_1907_R(friendName);
            MutableComponent prefix = new U_2871_b("\u0414\u0440\u0443\u0433 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent nameText = new U_2871_b("\"" + friendName + "\"").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            MutableComponent suffix = new U_2871_b(" \u0443\u0434\u0430\u043b\u0451\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(suffix), new Object[0]);
            return 1;
        })));
        builder.then(L_4248_u.n_1700_B("list").executes(context -> {
            List<n_473_l.n_1700_B> friends = ClientBootstrap.Y_601_j().v_4262_N().P_4830_p();
            if (friends == null || friends.isEmpty()) {
                MutableComponent emptyMsg = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0434\u0440\u0443\u0437\u0435\u0439 \u043f\u0443\u0441\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                v_1900_v.n_1700_B(emptyMsg, new Object[0]);
                return 1;
            }
            MutableComponent title = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0434\u0440\u0443\u0437\u0435\u0439:").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(title, new Object[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            for (n_473_l.n_1700_B friend : friends) {
                String friendName = friend.n_1700_B();
                MutableComponent friendText = new U_2871_b(friendName).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent added = new U_2871_b(" \u0414\u043e\u0431\u0430\u0432\u043b\u0435\u043d: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                MutableComponent date = new U_2871_b(friend.J_1907_R().format(formatter)).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                String alias = friend.R_4764_Y();
                U_2871_b aliasPart = alias != null ? new U_2871_b(" \u2192 " + alias).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.t_148_a)) : new U_2871_b("");
                MutableComponent removeBtn = new U_2871_b(" [\u0423\u0434\u0430\u043b\u0438\u0442\u044c]").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p).n_1700_B(new H_1873_g(i_2909_p.n_1700_B.R_4764_Y, ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B() + this.n_1700_B() + " remove " + friendName)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("\u041a\u043b\u0438\u043a \u0434\u043b\u044f \u0443\u0434\u0430\u043b\u0435\u043d\u0438\u044f" + friendName).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)))));
                v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(friendText).n_1700_B(aliasPart).n_1700_B(added).n_1700_B(date).n_1700_B(removeBtn), new Object[0]);
            }
            MutableComponent total = new U_2871_b("\u0412\u0441\u0435\u0433\u043e: " + friends.size()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(total, new Object[0]);
            return 1;
        }));
        builder.then(L_4248_u.n_1700_B("clear").executes(context -> {
            ClientBootstrap.Y_601_j().v_4262_N().M_588_G();
            MutableComponent clearedMsg = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0434\u0440\u0443\u0437\u0435\u0439 \u043e\u0447\u0438\u0449\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(clearedMsg, new Object[0]);
            return 1;
        }));
    }

    public String n_1700_B(StringReader reader) throws CommandSyntaxException {
        String input = reader.readString();
        if ("remove".equals(reader.getString().split(" ")[1]) && (ClientBootstrap.Y_601_j().v_4262_N() == null || !ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(input))) {
            throw new DynamicCommandExceptionType(name -> new U_2871_b("\u0414\u0440\u0443\u0433\u0430 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + String.valueOf(name) + " \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442")).create((Object)input);
        }
        return input;
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        String[] inputParts = context.getInput().split(" ");
        boolean isRemoveCommand = inputParts.length > 1 && "remove".equals(inputParts[1]);
        List<String> suggestions = isRemoveCommand ? ClientBootstrap.Y_601_j().v_4262_N().P_4830_p().stream().map(n_473_l.n_1700_B::n_1700_B).collect(Collectors.toList()) : c_3005_b.k_2293_S().P_1922_E().stream().map(info -> info.n_1700_B().getName()).collect(Collectors.toList());
        return V_4217_p.J_1907_R(suggestions, builder);
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}



