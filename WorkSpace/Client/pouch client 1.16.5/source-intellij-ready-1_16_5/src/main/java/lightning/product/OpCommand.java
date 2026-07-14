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

public class OpCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.op.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("op").requires(p_198545_0_ -> p_198545_0_.n_1700_B(3))).then(Q_2241_p.n_1700_B("targets", GameProfileArgument.n_1700_B()).suggests((p_198543_0_, p_198543_1_) -> {
            g_1995_W playerlist = ((y_2498_m)p_198543_0_.getSource()).w_1457_N().p_178_J();
            return V_4217_p.J_1907_R(playerlist.w_1457_N().stream().filter(p_198540_1_ -> !playerlist.u_1723_Y(p_198540_1_.y_4642_Y())).map(p_200545_0_ -> p_200545_0_.y_4642_Y().getName()), p_198543_1_);
        }).executes(p_198544_0_ -> OpCommand.n_1700_B((y_2498_m)p_198544_0_.getSource(), GameProfileArgument.n_1700_B((CommandContext<y_2498_m>)p_198544_0_, "targets")))));
    }

    private static int n_1700_B(y_2498_m source, Collection<GameProfile> gameProfiles) throws CommandSyntaxException {
        g_1995_W playerlist = source.w_1457_N().p_178_J();
        int i = 0;
        for (GameProfile gameprofile : gameProfiles) {
            if (playerlist.u_1723_Y(gameprofile)) continue;
            playerlist.n_1700_B(gameprofile);
            ++i;
            source.n_1700_B(new F_2904_S("commands.op.success", gameProfiles.iterator().next().getName()), true);
        }
        if (i == 0) {
            throw n_1700_B.create();
        }
        return i;
    }
}


