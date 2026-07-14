/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.Container;
import lightning.product.Q_2241_p;
import lightning.product.Z_1993_T;
import lightning.product.i_4556_r;
import lightning.product.ItemPredicateArgument;
import lightning.product.y_2498_m;

public class ClearInventoryCommands {
    private static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(p_208785_0_ -> new F_2904_S("clear.failed.single", p_208785_0_));
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208787_0_ -> new F_2904_S("clear.failed.multiple", p_208787_0_));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("clear").requires(p_198247_0_ -> p_198247_0_.n_1700_B(2))).executes(p_198241_0_ -> ClearInventoryCommands.n_1700_B((y_2498_m)p_198241_0_.getSource(), Collections.singleton(((y_2498_m)p_198241_0_.getSource()).t_1786_h()), p_198248_0_ -> true, -1))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).executes(p_198245_0_ -> ClearInventoryCommands.n_1700_B((y_2498_m)p_198245_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198245_0_, "targets"), p_198242_0_ -> true, -1))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("item", ItemPredicateArgument.n_1700_B()).executes(p_198240_0_ -> ClearInventoryCommands.n_1700_B((y_2498_m)p_198240_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198240_0_, "targets"), ItemPredicateArgument.n_1700_B((CommandContext<y_2498_m>)p_198240_0_, "item"), -1))).then(Q_2241_p.n_1700_B("maxCount", IntegerArgumentType.integer((int)0)).executes(p_198246_0_ -> ClearInventoryCommands.n_1700_B((y_2498_m)p_198246_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198246_0_, "targets"), ItemPredicateArgument.n_1700_B((CommandContext<y_2498_m>)p_198246_0_, "item"), IntegerArgumentType.getInteger((CommandContext)p_198246_0_, (String)"maxCount")))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> targetPlayers, Predicate<Z_1993_T> itemPredicateIn, int maxCount) throws CommandSyntaxException {
        int i = 0;
        for (B_4088_l serverplayerentity : targetPlayers) {
            i += serverplayerentity.l_1268_F.n_1700_B(itemPredicateIn, maxCount, serverplayerentity.o_1800_r.u_1723_Y());
            serverplayerentity.H_1873_g.M_588_G();
            serverplayerentity.o_1800_r.n_1700_B((Container)serverplayerentity.l_1268_F);
            serverplayerentity.Q_4569_t();
        }
        if (i == 0) {
            if (targetPlayers.size() == 1) {
                throw n_1700_B.create((Object)targetPlayers.iterator().next().O_1309_Q());
            }
            throw J_1907_R.create((Object)targetPlayers.size());
        }
        if (maxCount == 0) {
            if (targetPlayers.size() == 1) {
                source.n_1700_B(new F_2904_S("commands.clear.test.single", i, targetPlayers.iterator().next().c_()), true);
            } else {
                source.n_1700_B(new F_2904_S("commands.clear.test.multiple", i, targetPlayers.size()), true);
            }
        } else if (targetPlayers.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.clear.success.single", i, targetPlayers.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.clear.success.multiple", i, targetPlayers.size()), true);
        }
        return i;
    }
}


