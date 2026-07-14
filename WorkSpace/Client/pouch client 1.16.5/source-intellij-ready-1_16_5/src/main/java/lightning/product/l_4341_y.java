/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.ArgumentBuilder
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.ClientboundStopSoundPacket;
import lightning.product.Q_2241_p;
import lightning.product.g_2336_b;
import lightning.product.h_4126_t;
import lightning.product.i_4556_r;
import lightning.product.ResourceLocationArgument;
import lightning.product.y_2498_m;

public class l_4341_y {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        RequiredArgumentBuilder requiredargumentbuilder = (RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).executes(p_198729_0_ -> l_4341_y.n_1700_B((y_2498_m)p_198729_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198729_0_, "targets"), null, null))).then(Q_2241_p.n_1700_B("*").then(Q_2241_p.n_1700_B("sound", ResourceLocationArgument.n_1700_B()).suggests(h_4126_t.R_4764_Y).executes(p_198732_0_ -> l_4341_y.n_1700_B((y_2498_m)p_198732_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198732_0_, "targets"), null, ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_198732_0_, "sound")))));
        for (D_38_f soundcategory : D_38_f.values()) {
            requiredargumentbuilder.then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B(soundcategory.n_1700_B()).executes(p_198731_1_ -> l_4341_y.n_1700_B((y_2498_m)p_198731_1_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198731_1_, "targets"), soundcategory, null))).then(Q_2241_p.n_1700_B("sound", ResourceLocationArgument.n_1700_B()).suggests(h_4126_t.R_4764_Y).executes(p_198728_1_ -> l_4341_y.n_1700_B((y_2498_m)p_198728_1_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198728_1_, "targets"), soundcategory, ResourceLocationArgument.P_1922_E((CommandContext<y_2498_m>)p_198728_1_, "sound")))));
        }
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("stopsound").requires(p_198734_0_ -> p_198734_0_.n_1700_B(2))).then((ArgumentBuilder)requiredargumentbuilder));
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> targets, @Nullable D_38_f category, @Nullable g_2336_b soundIn) {
        ClientboundStopSoundPacket sstopsoundpacket = new ClientboundStopSoundPacket(soundIn, category);
        for (B_4088_l serverplayerentity : targets) {
            serverplayerentity.n_1700_B.n_1700_B(sstopsoundpacket);
        }
        if (category != null) {
            if (soundIn != null) {
                source.n_1700_B(new F_2904_S("commands.stopsound.success.source.sound", soundIn, category.n_1700_B()), true);
            } else {
                source.n_1700_B(new F_2904_S("commands.stopsound.success.source.any", category.n_1700_B()), true);
            }
        } else if (soundIn != null) {
            source.n_1700_B(new F_2904_S("commands.stopsound.success.sourceless.sound", soundIn), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.stopsound.success.sourceless.any"), true);
        }
        return targets.size();
    }
}


