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
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Collection;
import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.ItemInput;
import lightning.product.i_4556_r;
import lightning.product.n_1494_c;
import lightning.product.y_2498_m;
import lightning.product.ItemArgument;

public class GiveCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("give").requires(p_198496_0_ -> p_198496_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("item", ItemArgument.n_1700_B()).executes(p_198493_0_ -> GiveCommand.n_1700_B((y_2498_m)p_198493_0_.getSource(), ItemArgument.n_1700_B(p_198493_0_, "item"), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198493_0_, "targets"), 1))).then(Q_2241_p.n_1700_B("count", IntegerArgumentType.integer((int)1)).executes(p_198495_0_ -> GiveCommand.n_1700_B((y_2498_m)p_198495_0_.getSource(), ItemArgument.n_1700_B(p_198495_0_, "item"), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198495_0_, "targets"), IntegerArgumentType.getInteger((CommandContext)p_198495_0_, (String)"count")))))));
    }

    private static int n_1700_B(y_2498_m source, ItemInput itemIn, Collection<B_4088_l> targets, int count) throws CommandSyntaxException {
        for (B_4088_l serverplayerentity : targets) {
            int i = count;
            while (i > 0) {
                int j = Math.min(itemIn.n_1700_B().u_2550_I(), i);
                i -= j;
                Z_1993_T itemstack = itemIn.n_1700_B(j, false);
                boolean flag = serverplayerentity.l_1268_F.P_1922_E(itemstack);
                if (flag && itemstack.n_1700_B()) {
                    itemstack.P_1922_E(1);
                    n_1494_c itementity1 = serverplayerentity.n_1700_B(itemstack, false);
                    if (itementity1 != null) {
                        itementity1.multiplayerClientSuggestionProvider();
                    }
                    serverplayerentity.O_508_d.n_1700_B((a_3913_L)null, serverplayerentity.O_3598_v(), serverplayerentity.X_2960_b(), serverplayerentity.l_2647_k(), SoundEvents.DeathCoords, D_38_f.w_1484_f, 0.2f, ((serverplayerentity.M_3508_C().nextFloat() - serverplayerentity.M_3508_C().nextFloat()) * 0.7f + 1.0f) * 2.0f);
                    serverplayerentity.o_1800_r.M_588_G();
                    continue;
                }
                n_1494_c itementity = serverplayerentity.n_1700_B(itemstack, false);
                if (itementity == null) continue;
                itementity.u_2550_I();
                itementity.J_1907_R(serverplayerentity.w_2705_t());
            }
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.give.success.single", count, itemIn.n_1700_B(count, false).A_4115_X(), targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.give.success.single", count, itemIn.n_1700_B(count, false).A_4115_X(), targets.size()), true);
        }
        return targets.size();
    }
}



