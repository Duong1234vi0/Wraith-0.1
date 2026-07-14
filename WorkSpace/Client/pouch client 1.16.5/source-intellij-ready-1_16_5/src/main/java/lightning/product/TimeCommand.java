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
import lightning.product.TimeArgument;
import lightning.product.e_3591_l;
import lightning.product.y_2498_m;

public class TimeCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("time").requires(p_198828_0_ -> p_198828_0_.n_1700_B(2))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("set").then(Q_2241_p.n_1700_B("day").executes(p_198832_0_ -> TimeCommand.n_1700_B((y_2498_m)p_198832_0_.getSource(), 1000)))).then(Q_2241_p.n_1700_B("noon").executes(p_198825_0_ -> TimeCommand.n_1700_B((y_2498_m)p_198825_0_.getSource(), 6000)))).then(Q_2241_p.n_1700_B("night").executes(p_198822_0_ -> TimeCommand.n_1700_B((y_2498_m)p_198822_0_.getSource(), 13000)))).then(Q_2241_p.n_1700_B("midnight").executes(p_200563_0_ -> TimeCommand.n_1700_B((y_2498_m)p_200563_0_.getSource(), 18000)))).then(Q_2241_p.n_1700_B("time", TimeArgument.n_1700_B()).executes(p_200564_0_ -> TimeCommand.n_1700_B((y_2498_m)p_200564_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_200564_0_, (String)"time")))))).then(Q_2241_p.n_1700_B("add").then(Q_2241_p.n_1700_B("time", TimeArgument.n_1700_B()).executes(p_198830_0_ -> TimeCommand.J_1907_R((y_2498_m)p_198830_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_198830_0_, (String)"time")))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("query").then(Q_2241_p.n_1700_B("daytime").executes(p_198827_0_ -> TimeCommand.R_4764_Y((y_2498_m)p_198827_0_.getSource(), TimeCommand.n_1700_B(((y_2498_m)p_198827_0_.getSource()).h_1847_R()))))).then(Q_2241_p.n_1700_B("gametime").executes(p_198821_0_ -> TimeCommand.R_4764_Y((y_2498_m)p_198821_0_.getSource(), (int)(((y_2498_m)p_198821_0_.getSource()).h_1847_R().X_933_l() % Integer.MAX_VALUE))))).then(Q_2241_p.n_1700_B("day").executes(p_198831_0_ -> TimeCommand.R_4764_Y((y_2498_m)p_198831_0_.getSource(), (int)(((y_2498_m)p_198831_0_.getSource()).h_1847_R().Z_976_R() / 24000L % Integer.MAX_VALUE))))));
    }

    private static int n_1700_B(e_3591_l worldIn) {
        return (int)(worldIn.Z_976_R() % 24000L);
    }

    private static int R_4764_Y(y_2498_m source, int time) {
        source.n_1700_B(new F_2904_S("commands.time.query", time), false);
        return time;
    }

    public static int n_1700_B(y_2498_m source, int time) {
        for (e_3591_l serverworld : source.w_1457_N().n_3318_d()) {
            serverworld.n_1700_B(time);
        }
        source.n_1700_B(new F_2904_S("commands.time.set", time), true);
        return TimeCommand.n_1700_B(source.h_1847_R());
    }

    public static int J_1907_R(y_2498_m source, int amount) {
        for (e_3591_l serverworld : source.w_1457_N().n_3318_d()) {
            serverworld.n_1700_B(serverworld.Z_976_R() + (long)amount);
        }
        int i = TimeCommand.n_1700_B(source.h_1847_R());
        source.n_1700_B(new F_2904_S("commands.time.set", i), true);
        return i;
    }
}


