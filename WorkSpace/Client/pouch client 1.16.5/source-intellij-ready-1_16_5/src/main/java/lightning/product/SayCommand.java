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
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.Y_408_h;
import lightning.product.j_3341_s;
import lightning.product.MessageArgument;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class SayCommand {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("say").requires(p_198627_0_ -> p_198627_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("message", MessageArgument.n_1700_B()).executes(p_198626_0_ -> {
            x_282_a itextcomponent = MessageArgument.n_1700_B((CommandContext<y_2498_m>)p_198626_0_, "message");
            F_2904_S translationtextcomponent = new F_2904_S("chat.type.announcement", ((y_2498_m)p_198626_0_.getSource()).u_2550_I(), itextcomponent);
            N_4263_v entity = ((y_2498_m)p_198626_0_.getSource()).Q_4569_t();
            if (entity != null) {
                ((y_2498_m)p_198626_0_.getSource()).w_1457_N().p_178_J().n_1700_B(translationtextcomponent, Y_408_h.n_1700_B, entity.w_2705_t());
            } else {
                ((y_2498_m)p_198626_0_.getSource()).w_1457_N().p_178_J().n_1700_B(translationtextcomponent, Y_408_h.J_1907_R, j_3341_s.J_1907_R);
            }
            return 1;
        })));
    }
}


