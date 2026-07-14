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

public class WeatherCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("weather").requires(p_198868_0_ -> p_198868_0_.n_1700_B(2))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("clear").executes(p_198861_0_ -> WeatherCommand.n_1700_B((y_2498_m)p_198861_0_.getSource(), 6000))).then(Q_2241_p.n_1700_B("duration", IntegerArgumentType.integer((int)0, (int)1000000)).executes(p_198864_0_ -> WeatherCommand.n_1700_B((y_2498_m)p_198864_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_198864_0_, (String)"duration") * 20))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("rain").executes(p_198860_0_ -> WeatherCommand.J_1907_R((y_2498_m)p_198860_0_.getSource(), 6000))).then(Q_2241_p.n_1700_B("duration", IntegerArgumentType.integer((int)0, (int)1000000)).executes(p_198866_0_ -> WeatherCommand.J_1907_R((y_2498_m)p_198866_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_198866_0_, (String)"duration") * 20))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("thunder").executes(p_198859_0_ -> WeatherCommand.R_4764_Y((y_2498_m)p_198859_0_.getSource(), 6000))).then(Q_2241_p.n_1700_B("duration", IntegerArgumentType.integer((int)0, (int)1000000)).executes(p_198867_0_ -> WeatherCommand.R_4764_Y((y_2498_m)p_198867_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_198867_0_, (String)"duration") * 20)))));
    }

    private static int n_1700_B(y_2498_m source, int time) {
        source.h_1847_R().n_1700_B(time, 0, false, false);
        source.n_1700_B(new F_2904_S("commands.weather.set.clear"), true);
        return time;
    }

    private static int J_1907_R(y_2498_m source, int time) {
        source.h_1847_R().n_1700_B(0, time, true, false);
        source.n_1700_B(new F_2904_S("commands.weather.set.rain"), true);
        return time;
    }

    private static int R_4764_Y(y_2498_m source, int time) {
        source.h_1847_R().n_1700_B(0, time, true, true);
        source.n_1700_B(new F_2904_S("commands.weather.set.thunder"), true);
        return time;
    }
}


