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
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
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
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lightning.product.C_332_W;
import lightning.product.D_4024_W;
import lightning.product.H_1873_g;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.c_973_a;
import lightning.product.i_2909_p;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;

public class L_1362_X
extends o_2341_D
implements ArgumentType<String> {
    public L_1362_X() {
        super("config", "cfg");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(L_1362_X.n_1700_B("save").then(L_1362_X.n_1700_B("name", StringArgumentType.word()).executes(context -> {
            String configName = (String)context.getArgument("name", String.class);
            ClientBootstrap.Y_601_j().R_4764_Y().R_4764_Y(configName);
            MutableComponent prefix = new U_2871_b("\u041a\u043e\u043d\u0444\u0438\u0433 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent nameText = new U_2871_b("\"" + configName + "\"").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            MutableComponent suffix = new U_2871_b(" \u0441\u043e\u0445\u0440\u0430\u043d\u0451\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(suffix), new Object[0]);
            return 1;
        })));
        builder.then(L_1362_X.n_1700_B("load").then(L_1362_X.n_1700_B("name", new L_1362_X()).executes(context -> {
            String configName = (String)context.getArgument("name", String.class);
            if (!ClientBootstrap.Y_601_j().R_4764_Y().P_4830_p().contains(configName)) {
                return this.n_1700_B((CommandContext<V_4217_p>)context);
            }
            ClientBootstrap.Y_601_j().R_4764_Y().J_1907_R(configName);
            MutableComponent prefix = new U_2871_b("\u041a\u043e\u043d\u0444\u0438\u0433 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent nameText = new U_2871_b("\"" + configName + "\"").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            MutableComponent suffix = new U_2871_b(" \u0437\u0430\u0433\u0440\u0443\u0436\u0451\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(suffix), new Object[0]);
            return 1;
        })));
        builder.then(L_1362_X.n_1700_B("remove").then(L_1362_X.n_1700_B("name", new L_1362_X()).executes(context -> {
            String configName = (String)context.getArgument("name", String.class);
            if (!ClientBootstrap.Y_601_j().R_4764_Y().P_4830_p().contains(configName)) {
                return this.n_1700_B((CommandContext<V_4217_p>)context);
            }
            ClientBootstrap.Y_601_j().R_4764_Y().G_564_y(configName);
            MutableComponent prefix = new U_2871_b("\u041a\u043e\u043d\u0444\u0438\u0433 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            MutableComponent nameText = new U_2871_b("\"" + configName + "\"").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            MutableComponent suffix = new U_2871_b(" \u0443\u0434\u0430\u043b\u0451\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(prefix).n_1700_B(nameText).n_1700_B(suffix), new Object[0]);
            return 1;
        })));
        builder.then(L_1362_X.n_1700_B("list").executes(context -> {
            List<String> configList = ClientBootstrap.Y_601_j().R_4764_Y().P_4830_p();
            if (configList == null || configList.isEmpty()) {
                MutableComponent emptyMsg = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u0439 \u043f\u0443\u0441\u0442!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                v_1900_v.n_1700_B(emptyMsg, new Object[0]);
                return 1;
            }
            MutableComponent title = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043a\u043e\u043d\u0444\u0438\u0433\u043e\u0432:").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(title, new Object[0]);
            for (String cfgName : configList) {
                MutableComponent configText = new U_2871_b(cfgName).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                MutableComponent loadBtn = new U_2871_b(" [\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c]").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.u_2550_I).n_1700_B(new H_1873_g(i_2909_p.n_1700_B.R_4764_Y, ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B() + this.n_1700_B() + " load " + cfgName)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c \u043a\u043e\u043d\u0444\u0438\u0433 " + cfgName))));
                MutableComponent removeBtn = new U_2871_b(" [\u0423\u0434\u0430\u043b\u0438\u0442\u044c]").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.P_4830_p).n_1700_B(new H_1873_g(i_2909_p.n_1700_B.R_4764_Y, ClientBootstrap.Y_601_j().Q_4569_t().n_1700_B() + this.n_1700_B() + " remove " + cfgName)).n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, new U_2871_b("\u0423\u0434\u0430\u043b\u0438\u0442\u044c \u043a\u043e\u043d\u0444\u0438\u0433 " + cfgName))));
                v_1900_v.n_1700_B(new U_2871_b("").n_1700_B(configText).n_1700_B(loadBtn).n_1700_B(removeBtn), new Object[0]);
            }
            return 1;
        }));
        builder.then(L_1362_X.n_1700_B("dir").executes(context -> {
            try {
                File configDirectory = new File(C_332_W.n_1700_B + "custom\\");
                Runtime.getRuntime().exec("explorer " + configDirectory.getAbsolutePath());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }));
        builder.then(L_1362_X.n_1700_B("clear").executes(context -> {
            List<String> configs = ClientBootstrap.Y_601_j().R_4764_Y().P_4830_p();
            configs.forEach(config -> ClientBootstrap.Y_601_j().R_4764_Y().G_564_y((String)config));
            MutableComponent clearedMsg = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043a\u043e\u043d\u0444\u0438\u0433\u043e\u0432 \u043e\u0447\u0438\u0449\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(clearedMsg, new Object[0]);
            return 1;
        }));
        builder.then(L_1362_X.n_1700_B("reset").executes(context -> {
            ClientBootstrap.Y_601_j().R_4764_Y().h_1847_R();
            MutableComponent resetMsg = new U_2871_b("\u041a\u043e\u043d\u0444\u0438\u0433 \u0441\u0431\u0440\u043e\u0448\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(resetMsg, new Object[0]);
            return 1;
        }));
    }

    public String n_1700_B(StringReader reader) throws CommandSyntaxException {
        String config = reader.readString();
        boolean exists = ClientBootstrap.Y_601_j().R_4764_Y().P_4830_p().contains(config);
        if (!exists) {
            throw new DynamicCommandExceptionType(name -> new U_2871_b("\u041a\u043e\u043d\u0444\u0438\u0433 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c " + String.valueOf(name) + " \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442")).create((Object)config);
        }
        return config;
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        List<String> configNames = ClientBootstrap.Y_601_j().R_4764_Y().P_4830_p();
        return V_4217_p.J_1907_R(configNames, builder);
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}



