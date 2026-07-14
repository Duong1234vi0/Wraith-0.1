/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import lightning.product.F_2904_S;
import lightning.product.O_2332_X;
import lightning.product.Q_2241_p;
import lightning.product.y_2498_m;

public class PublishCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.publish.failed"));
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208900_0_ -> new F_2904_S("commands.publish.alreadyPublished", p_208900_0_));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("publish").requires(p_198583_0_ -> p_198583_0_.n_1700_B(4))).executes(p_198580_0_ -> PublishCommand.n_1700_B((y_2498_m)p_198580_0_.getSource(), O_2332_X.n_1700_B()))).then(Q_2241_p.n_1700_B("port", IntegerArgumentType.integer((int)0, (int)65535)).executes(p_198582_0_ -> PublishCommand.n_1700_B((y_2498_m)p_198582_0_.getSource(), IntegerArgumentType.getInteger((CommandContext)p_198582_0_, (String)"port")))));
    }

    private static int n_1700_B(y_2498_m source, int port) throws CommandSyntaxException {
        if (source.w_1457_N().RealmsClientConfig()) {
            throw J_1907_R.create((Object)source.w_1457_N().d_2461_k());
        }
        if (!source.w_1457_N().n_1700_B(source.w_1457_N().Q_4569_t(), false, port)) {
            throw n_1700_B.create();
        }
        source.n_1700_B(new F_2904_S("commands.publish.success", port), true);
        return port;
    }
}


