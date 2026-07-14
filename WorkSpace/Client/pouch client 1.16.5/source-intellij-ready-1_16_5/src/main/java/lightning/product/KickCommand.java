/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.i_4556_r;
import lightning.product.MessageArgument;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class KickCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("kick").requires(p_198517_0_ -> p_198517_0_.n_1700_B(3))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).executes(p_198513_0_ -> KickCommand.n_1700_B((y_2498_m)p_198513_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198513_0_, "targets"), new F_2904_S("multiplayer.disconnect.kicked")))).then(Q_2241_p.n_1700_B("reason", MessageArgument.n_1700_B()).executes(p_198516_0_ -> KickCommand.n_1700_B((y_2498_m)p_198516_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198516_0_, "targets"), MessageArgument.n_1700_B((CommandContext<y_2498_m>)p_198516_0_, "reason"))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> players, x_282_a reason) {
        for (B_4088_l serverplayerentity : players) {
            serverplayerentity.n_1700_B.n_1700_B(reason);
            source.n_1700_B(new F_2904_S("commands.kick.success", serverplayerentity.c_(), reason), true);
        }
        return players.size();
    }
}


