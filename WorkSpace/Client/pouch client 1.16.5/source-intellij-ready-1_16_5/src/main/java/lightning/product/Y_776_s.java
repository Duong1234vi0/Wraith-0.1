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
import lightning.product.H_1873_g;
import lightning.product.MutableComponent;
import lightning.product.R_2822_N;
import lightning.product.Interface;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class Y_776_s
extends o_2341_D
implements ArgumentType<String>,
MinecraftAccess {
    public Y_776_s() {
        super("staff");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(Y_776_s.n_1700_B("add").then(Y_776_s.n_1700_B("player", new Y_776_s()).executes(context -> {
            String name = (String)context.getArgument("player", String.class);
            boolean exists = ClientBootstrap.Y_601_j().w_1484_f().R_4764_Y(name);
            if (!exists) {
                ClientBootstrap.Y_601_j().w_1484_f().n_1700_B(name);
            }
            MutableComponent prefix = new U_2871_b("\u041f\u0435\u0440\u0441\u043e\u043d\u0430\u043b \u0441 \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent nameText = new U_2871_b(name).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            MutableComponent suffix = new U_2871_b("'" + (exists ? " \u0443\u0436\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442!" : " \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d!")).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(suffix), new Object[0]);
            return 1;
        })));
        builder.then(Y_776_s.n_1700_B("remove").then(Y_776_s.n_1700_B("player", new Y_776_s()).executes(context -> {
            String name = (String)context.getArgument("player", String.class);
            boolean isStaff = ClientBootstrap.Y_601_j().w_1484_f().R_4764_Y(name);
            if (!isStaff) {
                return this.n_1700_B((CommandContext<V_4217_p>)context);
            }
            ClientBootstrap.Y_601_j().w_1484_f().J_1907_R(name);
            MutableComponent prefix = new U_2871_b("\u041f\u0435\u0440\u0441\u043e\u043d\u0430\u043b \u0441 \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent nameText = new U_2871_b(name).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            MutableComponent suffix = new U_2871_b("' \u0443\u0434\u0430\u043b\u0451\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(suffix), new Object[0]);
            return 1;
        })));
        builder.then(Y_776_s.n_1700_B("list").executes(context -> {
            List<R_2822_N.n_1700_B> list = ClientBootstrap.Y_601_j().w_1484_f().P_4830_p();
            if (list == null || list.isEmpty()) {
                MutableComponent emptyMsg = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430 \u043f\u0443\u0441\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                v_1900_v.n_1700_B(emptyMsg, new Object[0]);
                return 1;
            }
            MutableComponent title = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430:").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(title, new Object[0]);
            for (R_2822_N.n_1700_B e : list) {
                String staffName = e.n_1700_B();
                MutableComponent staffText = new U_2871_b(staffName).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent removeBtn = new U_2871_b(" [\u0423\u0434\u0430\u043b\u0438\u0442\u044c]").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p).n_1700_B(new H_1873_g(i_2909_p.n_1700_B.R_4764_Y, ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B() + this.n_1700_B() + " remove " + staffName)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("\u041a\u043b\u0438\u043a \u0434\u043b\u044f \u0443\u0434\u0430\u043b\u0435\u043d\u0438\u044f \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p)))));
                v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(staffText).n_1700_B(removeBtn), new Object[0]);
            }
            MutableComponent total = new U_2871_b("\u0412\u0441\u0435\u0433\u043e: " + list.size()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(total, new Object[0]);
            return 1;
        }));
        builder.then(Y_776_s.n_1700_B("debug").executes(context -> {
            Interface iface = (Interface)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Interface.class);
            if (iface != null && iface.Y_601_j != null) {
                iface.Y_601_j.n_1700_B();
                v_1900_v.n_1700_B(new U_2871_b("\u00a77[LG] \u00a7f\u0414\u0435\u0431\u0430\u0433 \u0437\u0430\u043f\u0443\u0449\u0435\u043d \u2014 \u043f\u0440\u043e\u0432\u0435\u0440\u044c \u0447\u0430\u0442 \u0447\u0435\u0440\u0435\u0437 3 \u0441\u0435\u043a."), new Object[0]);
            } else {
                v_1900_v.n_1700_B(new U_2871_b("\u00a77[LG] \u00a7cStaffListRender \u043d\u0435 \u0438\u043d\u0438\u0446\u0438\u0430\u043b\u0438\u0437\u0438\u0440\u043e\u0432\u0430\u043d!"), new Object[0]);
            }
            return 1;
        }));
        builder.then(Y_776_s.n_1700_B("clear").executes(context -> {
            List<R_2822_N.n_1700_B> list = ClientBootstrap.Y_601_j().w_1484_f().P_4830_p();
            if (list == null || list.isEmpty()) {
                MutableComponent emptyMsg = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430 \u043f\u0443\u0441\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                v_1900_v.n_1700_B(emptyMsg, new Object[0]);
                return 1;
            }
            ClientBootstrap.Y_601_j().w_1484_f().M_588_G();
            MutableComponent clearedMsg = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u0435\u0440\u0441\u043e\u043d\u0430\u043b\u0430 \u043e\u0447\u0438\u0449\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(clearedMsg, new Object[0]);
            return 1;
        }));
    }

    public String n_1700_B(StringReader reader) throws CommandSyntaxException {
        return reader.readString();
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        String[] inputParts = context.getInput().split(" ");
        boolean isRemove = inputParts.length > 1 && "remove".equals(inputParts[1]);
        List<String> suggestions = isRemove ? ClientBootstrap.Y_601_j().w_1484_f().P_4830_p().stream().map(R_2822_N.n_1700_B::n_1700_B).collect(Collectors.toList()) : c_3005_b.k_2293_S().P_1922_E().stream().map(info -> info.n_1700_B().getName()).collect(Collectors.toList());
        return V_4217_p.J_1907_R(suggestions, builder);
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}



