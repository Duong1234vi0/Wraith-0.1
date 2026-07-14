/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.g_1995_W;
import lightning.product.GameProfileArgument;
import lightning.product.y_2498_m;

public class DeOpCommands {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.deop.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("deop").requires(p_198325_0_ -> p_198325_0_.n_1700_B(3))).then(Q_2241_p.n_1700_B("targets", GameProfileArgument.n_1700_B()).suggests((p_198323_0_, p_198323_1_) -> V_4217_p.n_1700_B(((y_2498_m)p_198323_0_.getSource()).w_1457_N().p_178_J().P_4830_p(), p_198323_1_)).executes(p_198324_0_ -> DeOpCommands.n_1700_B((y_2498_m)p_198324_0_.getSource(), GameProfileArgument.n_1700_B((CommandContext<y_2498_m>)p_198324_0_, "targets")))));
    }

    private static int n_1700_B(y_2498_m source, Collection<GameProfile> players) throws CommandSyntaxException {
        g_1995_W playerlist = source.w_1457_N().p_178_J();
        int i = 0;
        for (GameProfile gameprofile : players) {
            if (!playerlist.u_1723_Y(gameprofile)) continue;
            playerlist.J_1907_R(gameprofile);
            ++i;
            source.n_1700_B(new F_2904_S("commands.deop.success", players.iterator().next().getName()), true);
        }
        if (i == 0) {
            throw n_1700_B.create();
        }
        source.w_1457_N().n_1700_B(source);
        return i;
    }
}


