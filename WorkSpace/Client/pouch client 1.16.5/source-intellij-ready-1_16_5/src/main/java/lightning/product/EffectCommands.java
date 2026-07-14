/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.g_422_i;
import lightning.product.i_4556_r;
import lightning.product.k_2610_C;
import lightning.product.MobEffectArgument;
import lightning.product.r_4811_B;
import lightning.product.y_2498_m;

public class EffectCommands {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.effect.give.failed"));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.effect.clear.everything.failed"));
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.effect.clear.specific.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("effect").requires(p_198359_0_ -> p_198359_0_.n_1700_B(2))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("clear").executes(p_198352_0_ -> EffectCommands.n_1700_B((y_2498_m)p_198352_0_.getSource(), (Collection<? extends N_4263_v>)ImmutableList.of((Object)((y_2498_m)p_198352_0_.getSource()).M_182_A())))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).executes(p_198356_0_ -> EffectCommands.n_1700_B((y_2498_m)p_198356_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198356_0_, "targets")))).then(Q_2241_p.n_1700_B("effect", MobEffectArgument.n_1700_B()).executes(p_198351_0_ -> EffectCommands.n_1700_B((y_2498_m)p_198351_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198351_0_, "targets"), MobEffectArgument.n_1700_B((CommandContext<y_2498_m>)p_198351_0_, "effect"))))))).then(Q_2241_p.n_1700_B("give").then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("effect", MobEffectArgument.n_1700_B()).executes(p_198357_0_ -> EffectCommands.n_1700_B((y_2498_m)p_198357_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198357_0_, "targets"), MobEffectArgument.n_1700_B((CommandContext<y_2498_m>)p_198357_0_, "effect"), null, 0, true))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("seconds", IntegerArgumentType.integer((int)1, (int)1000000)).executes(p_198350_0_ -> EffectCommands.n_1700_B((y_2498_m)p_198350_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198350_0_, "targets"), MobEffectArgument.n_1700_B((CommandContext<y_2498_m>)p_198350_0_, "effect"), IntegerArgumentType.getInteger((CommandContext)p_198350_0_, (String)"seconds"), 0, true))).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("amplifier", IntegerArgumentType.integer((int)0, (int)255)).executes(p_198358_0_ -> EffectCommands.n_1700_B((y_2498_m)p_198358_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_198358_0_, "targets"), MobEffectArgument.n_1700_B((CommandContext<y_2498_m>)p_198358_0_, "effect"), IntegerArgumentType.getInteger((CommandContext)p_198358_0_, (String)"seconds"), IntegerArgumentType.getInteger((CommandContext)p_198358_0_, (String)"amplifier"), true))).then(Q_2241_p.n_1700_B("hideParticles", BoolArgumentType.bool()).executes(p_229759_0_ -> EffectCommands.n_1700_B((y_2498_m)p_229759_0_.getSource(), i_4556_r.J_1907_R((CommandContext<y_2498_m>)p_229759_0_, "targets"), MobEffectArgument.n_1700_B((CommandContext<y_2498_m>)p_229759_0_, "effect"), IntegerArgumentType.getInteger((CommandContext)p_229759_0_, (String)"seconds"), IntegerArgumentType.getInteger((CommandContext)p_229759_0_, (String)"amplifier"), !BoolArgumentType.getBool((CommandContext)p_229759_0_, (String)"hideParticles"))))))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends N_4263_v> targets, g_422_i effect, @Nullable Integer seconds, int amplifier, boolean showParticles) throws CommandSyntaxException {
        int i = 0;
        int j = seconds != null ? (effect.n_1700_B() ? seconds : seconds * 20) : (effect.n_1700_B() ? 1 : 600);
        for (N_4263_v n_4263_v : targets) {
            k_2610_C effectinstance;
            if (!(n_4263_v instanceof r_4811_B) || !((r_4811_B)n_4263_v).n_1700_B(effectinstance = new k_2610_C(effect, j, amplifier, false, showParticles))) continue;
            ++i;
        }
        if (i == 0) {
            throw n_1700_B.create();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.effect.give.success.single", effect.G_564_y(), targets.iterator().next().c_(), j / 20), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.effect.give.success.multiple", effect.G_564_y(), targets.size(), j / 20), true);
        }
        return i;
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends N_4263_v> targets) throws CommandSyntaxException {
        int i = 0;
        for (N_4263_v n_4263_v : targets) {
            if (!(n_4263_v instanceof r_4811_B) || !((r_4811_B)n_4263_v).g_1734_y()) continue;
            ++i;
        }
        if (i == 0) {
            throw J_1907_R.create();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.effect.clear.everything.success.single", targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.effect.clear.everything.success.multiple", targets.size()), true);
        }
        return i;
    }

    private static int n_1700_B(y_2498_m source, Collection<? extends N_4263_v> targets, g_422_i effect) throws CommandSyntaxException {
        int i = 0;
        for (N_4263_v n_4263_v : targets) {
            if (!(n_4263_v instanceof r_4811_B) || !((r_4811_B)n_4263_v).G_564_y(effect)) continue;
            ++i;
        }
        if (i == 0) {
            throw R_4764_Y.create();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.effect.clear.specific.success.single", effect.G_564_y(), targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.effect.clear.specific.success.multiple", effect.G_564_y(), targets.size()), true);
        }
        return i;
    }
}


