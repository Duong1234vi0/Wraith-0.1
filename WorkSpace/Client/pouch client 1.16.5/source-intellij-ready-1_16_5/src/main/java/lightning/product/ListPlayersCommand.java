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
import java.util.List;
import java.util.function.Function;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.Q_2241_p;
import lightning.product.a_3913_L;
import lightning.product.g_1995_W;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class ListPlayersCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("list").executes(p_198523_0_ -> ListPlayersCommand.n_1700_B((y_2498_m)p_198523_0_.getSource()))).then(Q_2241_p.n_1700_B("uuids").executes(p_208202_0_ -> ListPlayersCommand.J_1907_R((y_2498_m)p_208202_0_.getSource()))));
    }

    private static int n_1700_B(y_2498_m source) {
        return ListPlayersCommand.n_1700_B(source, a_3913_L::c_);
    }

    private static int J_1907_R(y_2498_m source) {
        return ListPlayersCommand.n_1700_B(source, p_244373_0_ -> new F_2904_S("commands.list.nameAndId", p_244373_0_.O_1309_Q(), p_244373_0_.y_4642_Y().getId()));
    }

    private static int n_1700_B(y_2498_m source, Function<B_4088_l, x_282_a> nameExtractor) {
        g_1995_W playerlist = source.w_1457_N().p_178_J();
        List<B_4088_l> list = playerlist.w_1457_N();
        MutableComponent itextcomponent = ComponentUtils.J_1907_R(list, nameExtractor);
        source.n_1700_B(new F_2904_S("commands.list.players", list.size(), playerlist.Q_4569_t(), itextcomponent), false);
        return list.size();
    }
}


