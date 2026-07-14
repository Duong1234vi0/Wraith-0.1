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
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.c_1514_x;
import lightning.product.d_4673_Y;
import lightning.product.BlockPosArgument;
import lightning.product.y_2498_m;

public class SetWorldSpawnCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("setworldspawn").requires(p_198704_0_ -> p_198704_0_.n_1700_B(2))).executes(p_198700_0_ -> SetWorldSpawnCommand.n_1700_B((y_2498_m)p_198700_0_.getSource(), new c_1514_x(((y_2498_m)p_198700_0_.getSource()).P_4830_p()), 0.0f))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("pos", BlockPosArgument.n_1700_B()).executes(p_198703_0_ -> SetWorldSpawnCommand.n_1700_B((y_2498_m)p_198703_0_.getSource(), BlockPosArgument.J_1907_R((CommandContext<y_2498_m>)p_198703_0_, "pos"), 0.0f))).then(Q_2241_p.n_1700_B("angle", d_4673_Y.n_1700_B()).executes(p_244377_0_ -> SetWorldSpawnCommand.n_1700_B((y_2498_m)p_244377_0_.getSource(), BlockPosArgument.J_1907_R((CommandContext<y_2498_m>)p_244377_0_, "pos"), d_4673_Y.n_1700_B((CommandContext<y_2498_m>)p_244377_0_, "angle"))))));
    }

    private static int n_1700_B(y_2498_m source, c_1514_x pos, float p_198701_2_) {
        source.h_1847_R().n_1700_B(pos, p_198701_2_);
        source.n_1700_B(new F_2904_S("commands.setworldspawn.success", pos.getX(), pos.getY(), pos.getZ(), Float.valueOf(p_198701_2_)), true);
        return 1;
    }
}


