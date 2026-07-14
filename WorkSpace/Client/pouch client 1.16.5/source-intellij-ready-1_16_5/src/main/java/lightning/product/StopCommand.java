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
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.y_2498_m;

public class StopCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("stop").requires(p_198727_0_ -> p_198727_0_.n_1700_B(4))).executes(p_198726_0_ -> {
            ((y_2498_m)p_198726_0_.getSource()).n_1700_B(new F_2904_S("commands.stop.stopping"), true);
            ((y_2498_m)p_198726_0_.getSource()).w_1457_N().n_1700_B(false);
            return 1;
        }));
    }
}


