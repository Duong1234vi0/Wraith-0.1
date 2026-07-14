/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.concurrent.Executor;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.Y_408_h;
import lightning.product.TextFilter;
import lightning.product.j_3341_s;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;

public class EmoteCommands {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)Q_2241_p.n_1700_B("me").then(Q_2241_p.n_1700_B("action", StringArgumentType.greedyString()).executes(p_198365_0_ -> {
            String s = StringArgumentType.getString((CommandContext)p_198365_0_, (String)"action");
            N_4263_v entity = ((y_2498_m)p_198365_0_.getSource()).Q_4569_t();
            G_564_y minecraftserver = ((y_2498_m)p_198365_0_.getSource()).w_1457_N();
            if (entity != null) {
                TextFilter ichatfilter;
                if (entity instanceof B_4088_l && (ichatfilter = ((B_4088_l)entity).g_2268_R()) != null) {
                    ichatfilter.n_1700_B(s).thenAcceptAsync(p_244713_3_ -> p_244713_3_.ifPresent(p_244712_3_ -> minecraftserver.p_178_J().n_1700_B(EmoteCommands.n_1700_B((CommandContext<y_2498_m>)p_198365_0_, p_244712_3_), Y_408_h.n_1700_B, entity.w_2705_t())), (Executor)minecraftserver);
                    return 1;
                }
                minecraftserver.p_178_J().n_1700_B(EmoteCommands.n_1700_B((CommandContext<y_2498_m>)p_198365_0_, s), Y_408_h.n_1700_B, entity.w_2705_t());
            } else {
                minecraftserver.p_178_J().n_1700_B(EmoteCommands.n_1700_B((CommandContext<y_2498_m>)p_198365_0_, s), Y_408_h.J_1907_R, j_3341_s.J_1907_R);
            }
            return 1;
        })));
    }

    private static x_282_a n_1700_B(CommandContext<y_2498_m> p_244711_0_, String p_244711_1_) {
        return new F_2904_S("chat.type.emote", ((y_2498_m)p_244711_0_.getSource()).u_2550_I(), p_244711_1_);
    }
}


