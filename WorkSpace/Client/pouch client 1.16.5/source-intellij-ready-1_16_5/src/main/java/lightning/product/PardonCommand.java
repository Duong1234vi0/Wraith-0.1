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
import lightning.product.q_1829_g;
import lightning.product.ComponentUtils;
import lightning.product.GameProfileArgument;
import lightning.product.y_2498_m;

public class PardonCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.pardon.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("pardon").requires(p_198551_0_ -> p_198551_0_.n_1700_B(3))).then(Q_2241_p.n_1700_B("targets", GameProfileArgument.n_1700_B()).suggests((p_198549_0_, p_198549_1_) -> V_4217_p.n_1700_B(((y_2498_m)p_198549_0_.getSource()).w_1457_N().p_178_J().v_4262_N().n_1700_B(), p_198549_1_)).executes(p_198550_0_ -> PardonCommand.n_1700_B((y_2498_m)p_198550_0_.getSource(), GameProfileArgument.n_1700_B((CommandContext<y_2498_m>)p_198550_0_, "targets")))));
    }

    private static int n_1700_B(y_2498_m source, Collection<GameProfile> gameProfiles) throws CommandSyntaxException {
        q_1829_g banlist = source.w_1457_N().p_178_J().v_4262_N();
        int i = 0;
        for (GameProfile gameprofile : gameProfiles) {
            if (!banlist.n_1700_B(gameprofile)) continue;
            banlist.R_4764_Y(gameprofile);
            ++i;
            source.n_1700_B(new F_2904_S("commands.pardon.success", ComponentUtils.n_1700_B(gameprofile)), true);
        }
        if (i == 0) {
            throw n_1700_B.create();
        }
        return i;
    }
}


