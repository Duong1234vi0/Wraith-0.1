/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.LiteralCommandNode
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.Collection;
import java.util.UUID;
import java.util.function.Consumer;
import lightning.product.B_4088_l;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.i_4556_r;
import lightning.product.j_3341_s;
import lightning.product.MessageArgument;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class MsgCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        LiteralCommandNode literalcommandnode = dispatcher.register((LiteralArgumentBuilder)Q_2241_p.n_1700_B("msg").then(Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).then(Q_2241_p.n_1700_B("message", MessageArgument.n_1700_B()).executes(p_198539_0_ -> MsgCommand.n_1700_B((y_2498_m)p_198539_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198539_0_, "targets"), MessageArgument.n_1700_B((CommandContext<y_2498_m>)p_198539_0_, "message"))))));
        dispatcher.register((LiteralArgumentBuilder)Q_2241_p.n_1700_B("tell").redirect((CommandNode)literalcommandnode));
        dispatcher.register((LiteralArgumentBuilder)Q_2241_p.n_1700_B("w").redirect((CommandNode)literalcommandnode));
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> recipients, x_282_a message) {
        Consumer<x_282_a> consumer;
        UUID uuid = source.Q_4569_t() == null ? j_3341_s.J_1907_R : source.Q_4569_t().w_2705_t();
        N_4263_v entity = source.Q_4569_t();
        if (entity instanceof B_4088_l) {
            B_4088_l serverplayerentity = (B_4088_l)entity;
            consumer = p_244374_2_ -> serverplayerentity.n_1700_B((x_282_a)new F_2904_S("commands.message.display.outgoing", p_244374_2_, message).n_1700_B(D_4024_W.w_1484_f, D_4024_W.Y_259_p), serverplayerentity.w_2705_t());
        } else {
            consumer = p_244375_2_ -> source.n_1700_B(new F_2904_S("commands.message.display.outgoing", p_244375_2_, message).n_1700_B(D_4024_W.w_1484_f, D_4024_W.Y_259_p), false);
        }
        for (B_4088_l serverplayerentity1 : recipients) {
            consumer.accept(serverplayerentity1.c_());
            serverplayerentity1.n_1700_B((x_282_a)new F_2904_S("commands.message.display.incoming", source.u_2550_I(), message).n_1700_B(D_4024_W.w_1484_f, D_4024_W.Y_259_p), uuid);
        }
        return recipients.size();
    }
}


