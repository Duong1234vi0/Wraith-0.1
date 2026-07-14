/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import java.util.Collections;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.d_4673_Y;
import lightning.product.f_2392_k;
import lightning.product.i_4556_r;
import lightning.product.BlockPosArgument;
import lightning.product.y_2498_m;

public class SetSpawnCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("spawnpoint").requires(p_198699_0_ -> p_198699_0_.n_1700_B(2))).executes(p_198697_0_ -> SetSpawnCommand.n_1700_B((y_2498_m)p_198697_0_.getSource(), Collections.singleton(((y_2498_m)p_198697_0_.getSource()).t_1786_h()), new c_1514_x(((y_2498_m)p_198697_0_.getSource()).P_4830_p()), 0.0f))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).executes(p_198694_0_ -> SetSpawnCommand.n_1700_B((y_2498_m)p_198694_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198694_0_, "targets"), new c_1514_x(((y_2498_m)p_198694_0_.getSource()).P_4830_p()), 0.0f))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("pos", BlockPosArgument.n_1700_B()).executes(p_198698_0_ -> SetSpawnCommand.n_1700_B((y_2498_m)p_198698_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198698_0_, "targets"), BlockPosArgument.J_1907_R((CommandContext<y_2498_m>)p_198698_0_, "pos"), 0.0f))).then(Q_2241_p.n_1700_B("angle", d_4673_Y.n_1700_B()).executes(p_244376_0_ -> SetSpawnCommand.n_1700_B((y_2498_m)p_244376_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_244376_0_, "targets"), BlockPosArgument.J_1907_R((CommandContext<y_2498_m>)p_244376_0_, "pos"), d_4673_Y.n_1700_B((CommandContext<y_2498_m>)p_244376_0_, "angle")))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> targets, c_1514_x pos, float p_198696_3_) {
        f_2392_k<b_4507_u> registrykey = source.h_1847_R().g_2268_R();
        for (B_4088_l serverplayerentity : targets) {
            serverplayerentity.n_1700_B(registrykey, pos, p_198696_3_, true, false);
        }
        String s = registrykey.n_1700_B().toString();
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.spawnpoint.success.single", pos.getX(), pos.getY(), pos.getZ(), Float.valueOf(p_198696_3_), s, targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.spawnpoint.success.multiple", pos.getX(), pos.getY(), pos.getZ(), Float.valueOf(p_198696_3_), s, targets.size()), true);
        }
        return targets.size();
    }
}


