/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.context.ParsedCommandNode
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.tree.CommandNode
 */
package lightning.product;

import com.google.common.collect.Iterables;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import java.util.Map;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.U_2871_b;
import lightning.product.y_2498_m;

public class a_117_l {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.help.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("help").executes(p_198511_1_ -> {
            Map map = dispatcher.getSmartUsage((CommandNode)dispatcher.getRoot(), (Object)((y_2498_m)p_198511_1_.getSource()));
            for (String s : map.values()) {
                ((y_2498_m)p_198511_1_.getSource()).n_1700_B(new U_2871_b("/" + s), false);
            }
            return map.size();
        })).then(Q_2241_p.n_1700_B("command", StringArgumentType.greedyString()).executes(p_198512_1_ -> {
            ParseResults parseresults = dispatcher.parse(StringArgumentType.getString((CommandContext)p_198512_1_, (String)"command"), (Object)((y_2498_m)p_198512_1_.getSource()));
            if (parseresults.getContext().getNodes().isEmpty()) {
                throw n_1700_B.create();
            }
            Map map = dispatcher.getSmartUsage(((ParsedCommandNode)Iterables.getLast((Iterable)parseresults.getContext().getNodes())).getNode(), (Object)((y_2498_m)p_198512_1_.getSource()));
            for (String s : map.values()) {
                ((y_2498_m)p_198512_1_.getSource()).n_1700_B(new U_2871_b("/" + parseresults.getReader().getString() + " " + s), false);
            }
            return map.size();
        })));
    }
}

