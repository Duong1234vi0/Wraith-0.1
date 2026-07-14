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
import java.util.Locale;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.ClientboundSetTitlesPacket;
import lightning.product.Q_2241_p;
import lightning.product.ComponentArgument;
import lightning.product.i_4556_r;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class i_918_k {
    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("title").requires(p_198847_0_ -> p_198847_0_.n_1700_B(2))).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).then(Q_2241_p.n_1700_B("clear").executes(p_198838_0_ -> i_918_k.n_1700_B((y_2498_m)p_198838_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198838_0_, "targets"))))).then(Q_2241_p.n_1700_B("reset").executes(p_198841_0_ -> i_918_k.J_1907_R((y_2498_m)p_198841_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198841_0_, "targets"))))).then(Q_2241_p.n_1700_B("title").then(Q_2241_p.n_1700_B("title", ComponentArgument.n_1700_B()).executes(p_198837_0_ -> i_918_k.n_1700_B((y_2498_m)p_198837_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198837_0_, "targets"), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_198837_0_, "title"), ClientboundSetTitlesPacket.n_1700_B.n_1700_B))))).then(Q_2241_p.n_1700_B("subtitle").then(Q_2241_p.n_1700_B("title", ComponentArgument.n_1700_B()).executes(p_198842_0_ -> i_918_k.n_1700_B((y_2498_m)p_198842_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198842_0_, "targets"), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_198842_0_, "title"), ClientboundSetTitlesPacket.n_1700_B.J_1907_R))))).then(Q_2241_p.n_1700_B("actionbar").then(Q_2241_p.n_1700_B("title", ComponentArgument.n_1700_B()).executes(p_198836_0_ -> i_918_k.n_1700_B((y_2498_m)p_198836_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198836_0_, "targets"), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_198836_0_, "title"), ClientboundSetTitlesPacket.n_1700_B.R_4764_Y))))).then(Q_2241_p.n_1700_B("times").then(Q_2241_p.n_1700_B("fadeIn", IntegerArgumentType.integer((int)0)).then(Q_2241_p.n_1700_B("stay", IntegerArgumentType.integer((int)0)).then(Q_2241_p.n_1700_B("fadeOut", IntegerArgumentType.integer((int)0)).executes(p_198843_0_ -> i_918_k.n_1700_B((y_2498_m)p_198843_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198843_0_, "targets"), IntegerArgumentType.getInteger((CommandContext)p_198843_0_, (String)"fadeIn"), IntegerArgumentType.getInteger((CommandContext)p_198843_0_, (String)"stay"), IntegerArgumentType.getInteger((CommandContext)p_198843_0_, (String)"fadeOut")))))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> targets) {
        ClientboundSetTitlesPacket stitlepacket = new ClientboundSetTitlesPacket(ClientboundSetTitlesPacket.n_1700_B.P_1922_E, null);
        for (B_4088_l serverplayerentity : targets) {
            serverplayerentity.n_1700_B.n_1700_B(stitlepacket);
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.title.cleared.single", targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.title.cleared.multiple", targets.size()), true);
        }
        return targets.size();
    }

    private static int J_1907_R(y_2498_m source, Collection<B_4088_l> targets) {
        ClientboundSetTitlesPacket stitlepacket = new ClientboundSetTitlesPacket(ClientboundSetTitlesPacket.n_1700_B.u_1723_Y, null);
        for (B_4088_l serverplayerentity : targets) {
            serverplayerentity.n_1700_B.n_1700_B(stitlepacket);
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.title.reset.single", targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.title.reset.multiple", targets.size()), true);
        }
        return targets.size();
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> targets, x_282_a message, ClientboundSetTitlesPacket.n_1700_B type) throws CommandSyntaxException {
        for (B_4088_l serverplayerentity : targets) {
            serverplayerentity.n_1700_B.n_1700_B(new ClientboundSetTitlesPacket(type, ComponentUtils.n_1700_B(source, message, (N_4263_v)serverplayerentity, 0)));
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.title.show." + type.name().toLowerCase(Locale.ROOT) + ".single", targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.title.show." + type.name().toLowerCase(Locale.ROOT) + ".multiple", targets.size()), true);
        }
        return targets.size();
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> target, int fadeIn, int stay, int fadeOut) {
        ClientboundSetTitlesPacket stitlepacket = new ClientboundSetTitlesPacket(fadeIn, stay, fadeOut);
        for (B_4088_l serverplayerentity : target) {
            serverplayerentity.n_1700_B.n_1700_B(stitlepacket);
        }
        if (target.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.title.times.single", target.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.title.times.multiple", target.size()), true);
        }
        return target.size();
    }
}


