/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import lightning.product.B_4088_l;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.ComponentArgument;
import lightning.product.i_4556_r;
import lightning.product.j_3341_s;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class TellRawCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("tellraw").requires(p_198820_0_ -> p_198820_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).then(Q_2241_p.n_1700_B("message", ComponentArgument.n_1700_B()).executes(p_198819_0_ -> {
            int i = 0;
            for (B_4088_l serverplayerentity : i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198819_0_, "targets")) {
                serverplayerentity.n_1700_B((x_282_a)ComponentUtils.n_1700_B((y_2498_m)p_198819_0_.getSource(), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_198819_0_, "message"), (N_4263_v)serverplayerentity, 0), j_3341_s.J_1907_R);
                ++i;
            }
            return i;
        }))));
    }
}


