/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;

public class SaveAllCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.save.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("save-all").requires(p_198615_0_ -> p_198615_0_.n_1700_B(4))).executes(p_198610_0_ -> SaveAllCommand.n_1700_B((y_2498_m)p_198610_0_.getSource(), false))).then(Q_2241_p.n_1700_B("flush").executes(p_198613_0_ -> SaveAllCommand.n_1700_B((y_2498_m)p_198613_0_.getSource(), true))));
    }

    private static int n_1700_B(y_2498_m source, boolean flush) throws CommandSyntaxException {
        source.n_1700_B(new F_2904_S("commands.save.saving"), false);
        G_564_y minecraftserver = source.w_1457_N();
        minecraftserver.p_178_J().t_148_a();
        boolean flag = minecraftserver.n_1700_B(true, flush, true);
        if (!flag) {
            throw n_1700_B.create();
        }
        source.n_1700_B(new F_2904_S("commands.save.success"), true);
        return 1;
    }
}


