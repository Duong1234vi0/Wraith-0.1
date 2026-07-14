/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.y_2498_m;

public class SetPlayerIdleTimeoutCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("setidletimeout").requires(p_198692_0_ -> p_198692_0_.n_1700_B(3))).then(Q_2241_p.n_1700_B("minutes", IntegerArgumentType.integer((int)0)).executes(p_198691_0_ -> SetPlayerIdleTimeoutCommand.n_1700_B((y_2498_m)p_198691_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_198691_0_, (String)"minutes")))));
    }

    private static int n_1700_B(y_2498_m source, int idleTimeout) {
        source.w_1457_N().G_564_y(idleTimeout);
        source.n_1700_B(new F_2904_S("commands.setidletimeout.success", idleTimeout), true);
        return idleTimeout;
    }
}


