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
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.D_4024_W;
import lightning.product.U_2871_b;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.Z_1567_W;
import lightning.product.MinecraftAccess;
import lightning.product.g_2336_b;
import lightning.product.ResourceLocationArgument;
import lightning.product.ClientBootstrap;
import lightning.product.o_2341_D;
import lightning.product.v_1900_v;
import lightning.product.z_3000_g;

public class j_306_t
extends o_2341_D
implements ArgumentType<String>,
MinecraftAccess {
    private static final Map<String, Integer> J_1907_R = Map.of("white", 0xFFFFFF, "red", 0xFF0000, "blue", 255, "green", 65280, "yellow", 0xFFFF00, "cyan", 65535, "magenta", 0xFF00FF, "black", 0, "gray", 0x808080);
    private static final String[] R_4764_Y = new String[]{"white_shulker_box", "orange_shulker_box", "magenta_shulker_box", "light_blue_shulker_box", "yellow_shulker_box", "lime_shulker_box", "pink_shulker_box", "gray_shulker_box", "light_gray_shulker_box", "cyan_shulker_box", "purple_shulker_box", "blue_shulker_box", "brown_shulker_box", "green_shulker_box", "red_shulker_box", "black_shulker_box"};

    public j_306_t() {
        super("blockesp");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(((LiteralArgumentBuilder)j_306_t.n_1700_B("add").then(j_306_t.n_1700_B("shulkers").then(j_306_t.n_1700_B("color", this).suggests(this::n_1700_B).executes(this::R_4764_Y)))).then(j_306_t.n_1700_B("block", ResourceLocationArgument.n_1700_B()).suggests(this::J_1907_R).then(j_306_t.n_1700_B("color", this).suggests(this::n_1700_B).executes(this::J_1907_R))));
        builder.then(j_306_t.n_1700_B("remove").then(j_306_t.n_1700_B("block", ResourceLocationArgument.n_1700_B()).suggests(this::R_4764_Y).executes(this::G_564_y)));
        builder.then(j_306_t.n_1700_B("clear").executes(ctx -> {
            this.J_1907_R().P_4830_p();
            U_2871_b message = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0431\u043b\u043e\u043a\u043e\u0432 \u043e\u0447\u0438\u0449\u0435\u043d!");
            message.n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(message, new Object[0]);
            return 1;
        }));
        builder.then(j_306_t.n_1700_B("list").executes(ctx -> {
            List<z_3000_g.n_1700_B> list = this.J_1907_R().M_588_G();
            if (list.isEmpty()) {
                U_2871_b message = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u0443\u0441\u0442!");
                message.n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
                v_1900_v.n_1700_B(message, new Object[0]);
                return 1;
            }
            U_2871_b headerMessage = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0431\u043b\u043e\u043a\u043e\u0432:");
            headerMessage.n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(headerMessage, new Object[0]);
            for (z_3000_g.n_1700_B entry : list) {
                U_2871_b blockMessage = new U_2871_b(entry.n_1700_B());
                blockMessage.n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
                v_1900_v.n_1700_B(blockMessage, new Object[0]);
            }
            U_2871_b totalMessage = new U_2871_b("\u0412\u0441\u0435\u0433\u043e: " + list.size());
            totalMessage.n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(totalMessage, new Object[0]);
            return 1;
        }));
    }

    private int J_1907_R(CommandContext<V_4217_p> ctx) {
        g_2336_b id = (g_2336_b)ctx.getArgument("block", g_2336_b.class);
        String colorArg = (String)ctx.getArgument("color", String.class);
        int color = this.J_1907_R(colorArg);
        this.J_1907_R().n_1700_B(id.toString(), color);
        U_2871_b message = new U_2871_b("");
        message.n_1700_B(new U_2871_b("\u0411\u043b\u043e\u043a \u0441 \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
        message.n_1700_B(new U_2871_b(id.toString()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)));
        message.n_1700_B(new U_2871_b("' \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
        v_1900_v.n_1700_B(message, new Object[0]);
        return 1;
    }

    private int R_4764_Y(CommandContext<V_4217_p> ctx) {
        String colorArg = (String)ctx.getArgument("color", String.class);
        int color = this.J_1907_R(colorArg);
        for (String type : R_4764_Y) {
            this.J_1907_R().n_1700_B(type, color);
        }
        U_2871_b message = new U_2871_b("");
        message.n_1700_B(new U_2871_b("\u0412\u0441\u0435 ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
        message.n_1700_B(new U_2871_b("\u0448\u0430\u043b\u043a\u0435\u0440\u044b").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)));
        message.n_1700_B(new U_2871_b(" \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u044b!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
        v_1900_v.n_1700_B(message, new Object[0]);
        return 1;
    }

    private int G_564_y(CommandContext<V_4217_p> ctx) {
        g_2336_b id = (g_2336_b)ctx.getArgument("block", g_2336_b.class);
        boolean removed = this.J_1907_R().n_1700_B(id.toString());
        if (removed) {
            U_2871_b message = new U_2871_b("");
            message.n_1700_B(new U_2871_b("\u0411\u043b\u043e\u043a \u0441 \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
            message.n_1700_B(new U_2871_b(id.toString()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)));
            message.n_1700_B(new U_2871_b("' \u0443\u0434\u0430\u043b\u0451\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
            v_1900_v.n_1700_B(message, new Object[0]);
        } else {
            v_1900_v.n_1700_B("BlockESP entry not found: " + String.valueOf(id), new Object[0]);
        }
        return 1;
    }

    private CompletableFuture<Suggestions> n_1700_B(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        return V_4217_p.J_1907_R(J_1907_R.keySet(), builder);
    }

    private CompletableFuture<Suggestions> J_1907_R(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        List<String> suggestions = Stream.concat(V_3137_a.q_4610_l.G_564_y().stream().map(g_2336_b::toString), Stream.of("shulkers")).collect(Collectors.toList());
        return V_4217_p.J_1907_R(suggestions, builder);
    }

    private CompletableFuture<Suggestions> R_4764_Y(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        List<String> suggestions = this.J_1907_R().M_588_G().stream().map(z_3000_g.n_1700_B::n_1700_B).collect(Collectors.toList());
        return V_4217_p.J_1907_R(suggestions, builder);
    }

    private int J_1907_R(String input) {
        if (input.startsWith("#")) {
            try {
                return Integer.parseInt(input.substring(1), 16);
            }
            catch (NumberFormatException e) {
                return J_1907_R.getOrDefault(input.toLowerCase(), 0xFFFFFF);
            }
        }
        return J_1907_R.getOrDefault(input.toLowerCase(), 0xFFFFFF);
    }

    public String n_1700_B(StringReader reader) throws CommandSyntaxException {
        return reader.readString();
    }

    private z_3000_g J_1907_R() {
        return ClientBootstrap.Y_601_j().u_2550_I();
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}



