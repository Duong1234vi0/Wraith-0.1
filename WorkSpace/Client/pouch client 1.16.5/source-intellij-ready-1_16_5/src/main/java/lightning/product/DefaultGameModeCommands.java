/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.I_14_v;
import lightning.product.Q_2241_p;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;

public class DefaultGameModeCommands {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        LiteralArgumentBuilder literalargumentbuilder = (LiteralArgumentBuilder)Q_2241_p.n_1700_B("defaultgamemode").requires(p_198342_0_ -> p_198342_0_.n_1700_B(2));
        for (I_14_v gametype : I_14_v.values()) {
            if (gametype == I_14_v.n_1700_B) continue;
            literalargumentbuilder.then(Q_2241_p.n_1700_B(gametype.J_1907_R()).executes(p_198343_1_ -> DefaultGameModeCommands.n_1700_B((y_2498_m)p_198343_1_.getSource(), gametype)));
        }
        dispatcher.register(literalargumentbuilder);
    }

    private static int n_1700_B(y_2498_m commandSourceIn, I_14_v gamemode) {
        int i = 0;
        G_564_y minecraftserver = commandSourceIn.w_1457_N();
        minecraftserver.n_1700_B(gamemode);
        if (minecraftserver.Ops()) {
            for (B_4088_l serverplayerentity : minecraftserver.p_178_J().w_1457_N()) {
                if (serverplayerentity.R_4764_Y.J_1907_R() == gamemode) continue;
                serverplayerentity.n_1700_B(gamemode);
                ++i;
            }
        }
        commandSourceIn.n_1700_B(new F_2904_S("commands.defaultgamemode.success", gamemode.R_4764_Y()), true);
        return i;
    }
}


