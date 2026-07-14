/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 */
package lightning.product;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.Collection;
import lightning.product.F_2904_S;
import lightning.product.BanListEntry;
import lightning.product.Q_2241_p;
import lightning.product.g_1995_W;
import lightning.product.y_2498_m;

public class BanListCommands {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("banlist").requires(p_198233_0_ -> p_198233_0_.n_1700_B(3))).executes(p_198231_0_ -> {
            g_1995_W playerlist = ((y_2498_m)p_198231_0_.getSource()).w_1457_N().p_178_J();
            return BanListCommands.n_1700_B((y_2498_m)p_198231_0_.getSource(), Lists.newArrayList((Iterable)Iterables.concat(playerlist.v_4262_N().G_564_y(), playerlist.w_1484_f().G_564_y())));
        })).then(Q_2241_p.n_1700_B("ips").executes(p_198228_0_ -> BanListCommands.n_1700_B((y_2498_m)p_198228_0_.getSource(), ((y_2498_m)p_198228_0_.getSource()).w_1457_N().p_178_J().w_1484_f().G_564_y())))).then(Q_2241_p.n_1700_B("players").executes(p_198232_0_ -> BanListCommands.n_1700_B((y_2498_m)p_198232_0_.getSource(), ((y_2498_m)p_198232_0_.getSource()).w_1457_N().p_178_J().v_4262_N().G_564_y()))));
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends BanListEntry<?>> bannedPlayerList) {
        if (bannedPlayerList.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.banlist.none"), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.banlist.list", bannedPlayerList.size()), false);
            for (BanListEntry<?> banentry : bannedPlayerList) {
                source.n_1700_B(new F_2904_S("commands.banlist.entry", banentry.G_564_y(), banentry.n_1700_B(), banentry.R_4764_Y()), false);
            }
        }
        return bannedPlayerList.size();
    }
}


