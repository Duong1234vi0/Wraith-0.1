/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.e_3591_l;
import lightning.product.y_2498_m;

public class SaveOffCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.save.alreadyOff"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("save-off").requires(p_198619_0_ -> p_198619_0_.n_1700_B(4))).executes(p_198618_0_ -> {
            y_2498_m commandsource = (y_2498_m)p_198618_0_.getSource();
            boolean flag = false;
            for (e_3591_l serverworld : commandsource.w_1457_N().n_3318_d()) {
                if (serverworld == null || serverworld.R_4764_Y) continue;
                serverworld.R_4764_Y = true;
                flag = true;
            }
            if (!flag) {
                throw n_1700_B.create();
            }
            commandsource.n_1700_B(new F_2904_S("commands.save.disabled"), true);
            return 1;
        }));
    }
}


