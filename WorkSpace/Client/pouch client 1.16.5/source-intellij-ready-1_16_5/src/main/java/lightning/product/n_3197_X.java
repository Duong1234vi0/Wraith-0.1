/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lightning.product.D_4024_W;
import lightning.product.S_2828_i;
import lightning.product.T_2915_h;
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

public class n_3197_X
extends o_2341_D
implements MinecraftAccess {
    public n_3197_X() {
        super("nuker", "nuk");
    }

    @Override
    public void n_1700_B(LiteralArgumentBuilder<V_4217_p> builder) {
        builder.executes(x$0 -> this.n_1700_B((CommandContext<V_4217_p>)x$0));
        builder.then(n_3197_X.n_1700_B("add").then(n_3197_X.n_1700_B("block", ResourceLocationArgument.n_1700_B()).suggests(this::n_1700_B).executes(this::J_1907_R)));
        builder.then(n_3197_X.n_1700_B("remove").then(n_3197_X.n_1700_B("block", ResourceLocationArgument.n_1700_B()).suggests(this::J_1907_R).executes(this::R_4764_Y)));
        builder.then(n_3197_X.n_1700_B("clear").executes(this::G_564_y));
        builder.then(n_3197_X.n_1700_B("list").executes(this::P_1922_E));
    }

    private int J_1907_R(CommandContext<V_4217_p> ctx) {
        g_2336_b id = (g_2336_b)ctx.getArgument("block", g_2336_b.class);
        S_2828_i manager = ClientBootstrap.Y_601_j().M_588_G();
        boolean added = manager.n_1700_B(id);
        U_2871_b message = new U_2871_b("");
        if (added) {
            message.n_1700_B(new U_2871_b("\u0411\u043b\u043e\u043a \u0441 \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
            message.n_1700_B(new U_2871_b(id.toString()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)));
            message.n_1700_B(new U_2871_b("' \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
        } else {
            message.n_1700_B(new U_2871_b("\u0411\u043b\u043e\u043a \u0443\u0436\u0435 \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 \u0438\u043b\u0438 \u043d\u0435\u043a\u043e\u0440\u0440\u0435\u043a\u0442\u0435\u043d: ").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
            message.n_1700_B(new U_2871_b(id.toString()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)));
        }
        v_1900_v.n_1700_B(message, new Object[0]);
        return 1;
    }

    private int R_4764_Y(CommandContext<V_4217_p> ctx) {
        g_2336_b id = (g_2336_b)ctx.getArgument("block", g_2336_b.class);
        S_2828_i manager = ClientBootstrap.Y_601_j().M_588_G();
        boolean removed = manager.J_1907_R(id);
        if (removed) {
            U_2871_b message = new U_2871_b("");
            message.n_1700_B(new U_2871_b("\u0411\u043b\u043e\u043a \u0441 \u0438\u043c\u0435\u043d\u0435\u043c '").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
            message.n_1700_B(new U_2871_b(id.toString()).n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A)));
            message.n_1700_B(new U_2871_b("' \u0443\u0434\u0430\u043b\u0451\u043d!").n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f)));
            v_1900_v.n_1700_B(message, new Object[0]);
        }
        return 1;
    }

    private int G_564_y(CommandContext<V_4217_p> ctx) {
        S_2828_i manager = ClientBootstrap.Y_601_j().M_588_G();
        manager.P_4830_p();
        U_2871_b message = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0431\u043b\u043e\u043a\u043e\u0432 \u043e\u0447\u0438\u0449\u0435\u043d!");
        message.n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
        v_1900_v.n_1700_B(message, new Object[0]);
        return 1;
    }

    private int P_1922_E(CommandContext<V_4217_p> ctx) {
        S_2828_i manager = ClientBootstrap.Y_601_j().M_588_G();
        List<T_2915_h> list = manager.M_588_G();
        if (list.isEmpty()) {
            U_2871_b message = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u043f\u0443\u0441\u0442!");
            message.n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
            v_1900_v.n_1700_B(message, new Object[0]);
            return 1;
        }
        U_2871_b headerMessage = new U_2871_b("\u0421\u043f\u0438\u0441\u043e\u043a \u0431\u043b\u043e\u043a\u043e\u0432:");
        headerMessage.n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
        v_1900_v.n_1700_B(headerMessage, new Object[0]);
        for (T_2915_h b : list) {
            U_2871_b blockMessage = new U_2871_b(V_3137_a.q_4610_l.J_1907_R(b).toString());
            blockMessage.n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.M_182_A));
            v_1900_v.n_1700_B(blockMessage, new Object[0]);
        }
        U_2871_b totalMessage = new U_2871_b("\u0412\u0441\u0435\u0433\u043e: " + list.size());
        totalMessage.n_1700_B(Z_1567_W.n_1700_B.J_1907_R(D_4024_W.w_1484_f));
        v_1900_v.n_1700_B(totalMessage, new Object[0]);
        return 1;
    }

    private CompletableFuture<Suggestions> n_1700_B(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        List<String> suggestions = V_3137_a.q_4610_l.G_564_y().stream().map(g_2336_b::toString).collect(Collectors.toList());
        return V_4217_p.J_1907_R(suggestions, builder);
    }

    private CompletableFuture<Suggestions> J_1907_R(CommandContext<V_4217_p> ctx, SuggestionsBuilder builder) {
        S_2828_i manager = ClientBootstrap.Y_601_j().M_588_G();
        List<String> suggestions = manager.M_588_G().stream().map(block -> V_3137_a.q_4610_l.J_1907_R((T_2915_h)block).toString()).collect(Collectors.toList());
        return V_4217_p.J_1907_R(suggestions, builder);
    }
}



