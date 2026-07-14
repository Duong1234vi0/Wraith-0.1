/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Collection;
import java.util.Collections;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.Q_2241_p;
import lightning.product.h_4126_t;
import lightning.product.Recipe;
import lightning.product.i_4556_r;
import lightning.product.ResourceLocationArgument;
import lightning.product.y_2498_m;

public class RecipeCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.recipe.give.failed"));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.recipe.take.failed"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("recipe").requires(p_198593_0_ -> p_198593_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("give").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).then(Q_2241_p.n_1700_B("recipe", ResourceLocationArgument.n_1700_B()).suggests(h_4126_t.J_1907_R).executes(p_198588_0_ -> RecipeCommand.n_1700_B((y_2498_m)p_198588_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198588_0_, "targets"), Collections.singleton(ResourceLocationArgument.J_1907_R((CommandContext<y_2498_m>)p_198588_0_, "recipe")))))).then(Q_2241_p.n_1700_B("*").executes(p_198591_0_ -> RecipeCommand.n_1700_B((y_2498_m)p_198591_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198591_0_, "targets"), ((y_2498_m)p_198591_0_.getSource()).w_1457_N().ValueObject().J_1907_R())))))).then(Q_2241_p.n_1700_B("take").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", i_4556_r.G_564_y()).then(Q_2241_p.n_1700_B("recipe", ResourceLocationArgument.n_1700_B()).suggests(h_4126_t.J_1907_R).executes(p_198587_0_ -> RecipeCommand.J_1907_R((y_2498_m)p_198587_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198587_0_, "targets"), Collections.singleton(ResourceLocationArgument.J_1907_R((CommandContext<y_2498_m>)p_198587_0_, "recipe")))))).then(Q_2241_p.n_1700_B("*").executes(p_198592_0_ -> RecipeCommand.J_1907_R((y_2498_m)p_198592_0_.getSource(), i_4556_r.u_1723_Y((CommandContext<y_2498_m>)p_198592_0_, "targets"), ((y_2498_m)p_198592_0_.getSource()).w_1457_N().ValueObject().J_1907_R()))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<B_4088_l> targets, Collection<Recipe<?>> recipes) throws CommandSyntaxException {
        int i = 0;
        for (B_4088_l serverplayerentity : targets) {
            i += serverplayerentity.J_1907_R(recipes);
        }
        if (i == 0) {
            throw n_1700_B.create();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.recipe.give.success.single", recipes.size(), targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.recipe.give.success.multiple", recipes.size(), targets.size()), true);
        }
        return i;
    }

    private static int J_1907_R(y_2498_m source, Collection<B_4088_l> targets, Collection<Recipe<?>> recipes) throws CommandSyntaxException {
        int i = 0;
        for (B_4088_l serverplayerentity : targets) {
            i += serverplayerentity.R_4764_Y(recipes);
        }
        if (i == 0) {
            throw J_1907_R.create();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.recipe.take.success.single", recipes.size(), targets.iterator().next().c_()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.recipe.take.success.multiple", recipes.size(), targets.size()), true);
        }
        return i;
    }
}


