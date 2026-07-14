/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 *  com.mojang.datafixers.util.Either
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import lightning.product.B_3241_B;
import lightning.product.F_2904_S;
import lightning.product.I_4764_L;
import lightning.product.Q_2241_p;
import lightning.product.FunctionCommand;
import lightning.product.V_4217_p;
import lightning.product.Z_1125_b;
import lightning.product.TimeArgument;
import lightning.product.c_853_z;
import lightning.product.g_2336_b;
import lightning.product.r_109_r;
import lightning.product.r_3448_Z;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;

public class u_2362_t {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.schedule.same_tick"));
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_229818_0_ -> new F_2904_S("commands.schedule.cleared.failure", p_229818_0_));
    private static final SuggestionProvider<y_2498_m> R_4764_Y = (p_229814_0_, p_229814_1_) -> V_4217_p.J_1907_R(((y_2498_m)p_229814_0_.getSource()).w_1457_N().c_132_F().H_2857_Y().Y_259_p().n_1700_B(), p_229814_1_);

    public static void n_1700_B(CommandDispatcher<y_2498_m> p_218909_0_) {
        p_218909_0_.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("schedule").requires(p_229815_0_ -> p_229815_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("function").then(Q_2241_p.n_1700_B("function", c_853_z.n_1700_B()).suggests(FunctionCommand.n_1700_B).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("time", TimeArgument.n_1700_B()).executes(p_229823_0_ -> u_2362_t.n_1700_B((y_2498_m)p_229823_0_.getSource(), c_853_z.J_1907_R((CommandContext<y_2498_m>)p_229823_0_, "function"), IntegerArgumentType.getInteger((CommandContext)p_229823_0_, (String)"time"), true))).then(Q_2241_p.n_1700_B("append").executes(p_229822_0_ -> u_2362_t.n_1700_B((y_2498_m)p_229822_0_.getSource(), c_853_z.J_1907_R((CommandContext<y_2498_m>)p_229822_0_, "function"), IntegerArgumentType.getInteger((CommandContext)p_229822_0_, (String)"time"), false)))).then(Q_2241_p.n_1700_B("replace").executes(p_229821_0_ -> u_2362_t.n_1700_B((y_2498_m)p_229821_0_.getSource(), c_853_z.J_1907_R((CommandContext<y_2498_m>)p_229821_0_, "function"), IntegerArgumentType.getInteger((CommandContext)p_229821_0_, (String)"time"), true))))))).then(Q_2241_p.n_1700_B("clear").then(Q_2241_p.n_1700_B("function", StringArgumentType.greedyString()).suggests(R_4764_Y).executes(p_229813_0_ -> u_2362_t.n_1700_B((y_2498_m)p_229813_0_.getSource(), StringArgumentType.getString((CommandContext)p_229813_0_, (String)"function"))))));
    }

    private static int n_1700_B(y_2498_m p_241063_0_, Pair<g_2336_b, Either<r_3448_Z, r_109_r<r_3448_Z>>> p_241063_1_, int p_241063_2_, boolean p_241063_3_) throws CommandSyntaxException {
        if (p_241063_2_ == 0) {
            throw n_1700_B.create();
        }
        long i = p_241063_0_.h_1847_R().X_933_l() + (long)p_241063_2_;
        g_2336_b resourcelocation = (g_2336_b)p_241063_1_.getFirst();
        Z_1125_b<G_564_y> timercallbackmanager = p_241063_0_.w_1457_N().c_132_F().H_2857_Y().Y_259_p();
        ((Either)p_241063_1_.getSecond()).ifLeft(p_229820_7_ -> {
            String s = resourcelocation.toString();
            if (p_241063_3_) {
                timercallbackmanager.n_1700_B(s);
            }
            timercallbackmanager.n_1700_B(s, i, new B_3241_B(resourcelocation));
            p_241063_0_.n_1700_B(new F_2904_S("commands.schedule.created.function", resourcelocation, p_241063_2_, i), true);
        }).ifRight(p_229819_7_ -> {
            String s = "#" + resourcelocation.toString();
            if (p_241063_3_) {
                timercallbackmanager.n_1700_B(s);
            }
            timercallbackmanager.n_1700_B(s, i, new I_4764_L(resourcelocation));
            p_241063_0_.n_1700_B(new F_2904_S("commands.schedule.created.tag", resourcelocation, p_241063_2_, i), true);
        });
        return (int)Math.floorMod(i, Integer.MAX_VALUE);
    }

    private static int n_1700_B(y_2498_m p_229817_0_, String p_229817_1_) throws CommandSyntaxException {
        int i = p_229817_0_.w_1457_N().c_132_F().H_2857_Y().Y_259_p().n_1700_B(p_229817_1_);
        if (i == 0) {
            throw J_1907_R.create((Object)p_229817_1_);
        }
        p_229817_0_.n_1700_B(new F_2904_S("commands.schedule.cleared.success", i, p_229817_1_), true);
        return i;
    }
}


