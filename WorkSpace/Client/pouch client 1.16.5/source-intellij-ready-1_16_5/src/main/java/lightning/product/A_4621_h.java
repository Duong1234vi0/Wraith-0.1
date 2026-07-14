/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.LiteralCommandNode
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.a_3913_L;
import lightning.product.i_4556_r;
import lightning.product.u_530_F;
import lightning.product.y_2498_m;

public class A_4621_h {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.experience.set.points.invalid"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        LiteralCommandNode literalcommandnode = dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("experience").requires(p_198442_0_ -> p_198442_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("add").then(Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("amount", IntegerArgumentType.integer()).executes(p_198445_0_ -> A_4621_h.n_1700_B((y_2498_m)p_198445_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198445_0_, "targets"), IntegerArgumentType.getInteger((CommandContext)p_198445_0_, (String)"amount"), lightning.product.A_4621_h$n_1700_B.n_1700_B))).then(Q_2241_p.n_1700_B("points").executes(p_198447_0_ -> A_4621_h.n_1700_B((y_2498_m)p_198447_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198447_0_, "targets"), IntegerArgumentType.getInteger((CommandContext)p_198447_0_, (String)"amount"), lightning.product.A_4621_h$n_1700_B.n_1700_B)))).then(Q_2241_p.n_1700_B("levels").executes(p_198436_0_ -> A_4621_h.n_1700_B((y_2498_m)p_198436_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198436_0_, "targets"), IntegerArgumentType.getInteger((CommandContext)p_198436_0_, (String)"amount"), lightning.product.A_4621_h$n_1700_B.J_1907_R))))))).then(Q_2241_p.n_1700_B("set").then(Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("amount", IntegerArgumentType.integer((int)0)).executes(p_198439_0_ -> A_4621_h.J_1907_R((y_2498_m)p_198439_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198439_0_, "targets"), IntegerArgumentType.getInteger((CommandContext)p_198439_0_, (String)"amount"), lightning.product.A_4621_h$n_1700_B.n_1700_B))).then(Q_2241_p.n_1700_B("points").executes(p_198444_0_ -> A_4621_h.J_1907_R((y_2498_m)p_198444_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198444_0_, "targets"), IntegerArgumentType.getInteger((CommandContext)p_198444_0_, (String)"amount"), lightning.product.A_4621_h$n_1700_B.n_1700_B)))).then(Q_2241_p.n_1700_B("levels").executes(p_198440_0_ -> A_4621_h.J_1907_R((y_2498_m)p_198440_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198440_0_, "targets"), IntegerArgumentType.getInteger((CommandContext)p_198440_0_, (String)"amount"), lightning.product.A_4621_h$n_1700_B.J_1907_R))))))).then(Q_2241_p.n_1700_B("query").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.R_4764_Y()).then(Q_2241_p.n_1700_B("points").executes(p_198435_0_ -> A_4621_h.n_1700_B((y_2498_m)p_198435_0_.getSource(), i_4556_r.P_1922_E((CommandContext<y_2498_m>)p_198435_0_, "targets"), lightning.product.A_4621_h$n_1700_B.n_1700_B)))).then(Q_2241_p.n_1700_B("levels").executes(p_198446_0_ -> A_4621_h.n_1700_B((y_2498_m)p_198446_0_.getSource(), i_4556_r.P_1922_E((CommandContext<y_2498_m>)p_198446_0_, "targets"), lightning.product.A_4621_h$n_1700_B.J_1907_R))))));
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("xp").requires(p_198441_0_ -> p_198441_0_.n_1700_B(2))).redirect((CommandNode)literalcommandnode));
    }

    private static int n_1700_B(y_2498_m source, B_4088_l player, n_1700_B type) {
        int i = type.u_1723_Y.applyAsInt(player);
        source.n_1700_B(new F_2904_S("commands.experience.query." + type.P_1922_E, player.c_(), i), false);
        return i;
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends B_4088_l> targets, int amount, n_1700_B type) {
        for (B_4088_l b_4088_l : targets) {
            type.R_4764_Y.accept(b_4088_l, amount);
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.experience.add." + type.P_1922_E + ".success.single", amount, targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.experience.add." + type.P_1922_E + ".success.multiple", amount, targets.size()), true);
        }
        return targets.size();
    }

    private static int J_1907_R(y_2498_m source, Collection<? extends B_4088_l> targets, int amount, n_1700_B type) throws CommandSyntaxException {
        int i = 0;
        for (B_4088_l b_4088_l : targets) {
            if (!type.G_564_y.test(b_4088_l, amount)) continue;
            ++i;
        }
        if (i == 0) {
            throw n_1700_B.create();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.experience.set." + type.P_1922_E + ".success.single", amount, targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.experience.set." + type.P_1922_E + ".success.multiple", amount, targets.size()), true);
        }
        return targets.size();
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("points", a_3913_L::multiplayerClientSuggestionProvider, (p_198424_0_, p_198424_1_) -> {
            if (p_198424_1_ >= p_198424_0_.f_2787_O()) {
                return false;
            }
            p_198424_0_.n_1700_B((int)p_198424_1_);
            return true;
        }, p_198422_0_ -> u_530_F.G_564_y(p_198422_0_.b_2312_j * (float)p_198422_0_.f_2787_O()));
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("levels", B_4088_l::w_1457_N, (p_198425_0_, p_198425_1_) -> {
            p_198425_0_.Y_601_j((int)p_198425_1_);
            return true;
        }, p_198427_0_ -> p_198427_0_.v_165_F);
        public final BiConsumer<B_4088_l, Integer> R_4764_Y;
        public final BiPredicate<B_4088_l, Integer> G_564_y;
        public final String P_1922_E;
        private final ToIntFunction<B_4088_l> u_1723_Y;
        private static final /* synthetic */ n_1700_B[] v_4262_N;

        public static n_1700_B[] values() {
            return (n_1700_B[])v_4262_N.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String nameIn, BiConsumer<B_4088_l, Integer> xpAdderIn, BiPredicate<B_4088_l, Integer> xpSetterIn, ToIntFunction<B_4088_l> xpGetterIn) {
            this.R_4764_Y = xpAdderIn;
            this.P_1922_E = nameIn;
            this.G_564_y = xpSetterIn;
            this.u_1723_Y = xpGetterIn;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            v_4262_N = lightning.product.A_4621_h$n_1700_B.n_1700_B();
        }
    }
}


