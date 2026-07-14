/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.M_1462_J;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.Objective;
import lightning.product.ObjectiveArgument;
import lightning.product.ServerScoreboard;
import lightning.product.i_4895_l;
import lightning.product.v_4839_y;
import lightning.product.y_2498_m;

public class TriggerCommand {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.trigger.failed.unprimed"));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.trigger.failed.invalid"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)Q_2241_p.n_1700_B("trigger").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).suggests((p_198853_0_, p_198853_1_) -> TriggerCommand.n_1700_B((y_2498_m)p_198853_0_.getSource(), p_198853_1_)).executes(p_198854_0_ -> TriggerCommand.n_1700_B((y_2498_m)p_198854_0_.getSource(), TriggerCommand.n_1700_B(((y_2498_m)p_198854_0_.getSource()).t_1786_h(), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_198854_0_, "objective"))))).then(Q_2241_p.n_1700_B("add").then(Q_2241_p.n_1700_B("value", IntegerArgumentType.integer()).executes(p_198849_0_ -> TriggerCommand.n_1700_B((y_2498_m)p_198849_0_.getSource(), TriggerCommand.n_1700_B(((y_2498_m)p_198849_0_.getSource()).t_1786_h(), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_198849_0_, "objective")), IntegerArgumentType.getInteger((CommandContext)p_198849_0_, (String)"value")))))).then(Q_2241_p.n_1700_B("set").then(Q_2241_p.n_1700_B("value", IntegerArgumentType.integer()).executes(p_198855_0_ -> TriggerCommand.J_1907_R((y_2498_m)p_198855_0_.getSource(), TriggerCommand.n_1700_B(((y_2498_m)p_198855_0_.getSource()).t_1786_h(), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_198855_0_, "objective")), IntegerArgumentType.getInteger((CommandContext)p_198855_0_, (String)"value")))))));
    }

    public static CompletableFuture<Suggestions> n_1700_B(y_2498_m source, SuggestionsBuilder builder) {
        N_4263_v entity = source.Q_4569_t();
        ArrayList list = Lists.newArrayList();
        if (entity != null) {
            ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
            String s = entity.L_3570_A();
            for (Objective scoreobjective : scoreboard.n_1700_B()) {
                v_4839_y score;
                if (scoreobjective.R_4764_Y() != M_1462_J.R_4764_Y || !scoreboard.n_1700_B(s, scoreobjective) || (score = scoreboard.J_1907_R(s, scoreobjective)).v_4262_N()) continue;
                list.add(scoreobjective.J_1907_R());
            }
        }
        return V_4217_p.J_1907_R(list, builder);
    }

    private static int n_1700_B(y_2498_m source, v_4839_y objective, int amount) {
        objective.n_1700_B(amount);
        source.n_1700_B(new F_2904_S("commands.trigger.add.success", objective.G_564_y().P_1922_E(), amount), true);
        return objective.J_1907_R();
    }

    private static int J_1907_R(y_2498_m source, v_4839_y objective, int value) {
        objective.J_1907_R(value);
        source.n_1700_B(new F_2904_S("commands.trigger.set.success", objective.G_564_y().P_1922_E(), value), true);
        return value;
    }

    private static int n_1700_B(y_2498_m source, v_4839_y objectives) {
        objectives.n_1700_B(1);
        source.n_1700_B(new F_2904_S("commands.trigger.simple.success", objectives.G_564_y().P_1922_E()), true);
        return objectives.J_1907_R();
    }

    private static v_4839_y n_1700_B(B_4088_l player, Objective objective) throws CommandSyntaxException {
        String s;
        if (objective.R_4764_Y() != M_1462_J.R_4764_Y) {
            throw J_1907_R.create();
        }
        i_4895_l scoreboard = player.U_3758_B();
        if (!scoreboard.n_1700_B(s = player.L_3570_A(), objective)) {
            throw n_1700_B.create();
        }
        v_4839_y score = scoreboard.J_1907_R(s, objective);
        if (score.v_4262_N()) {
            throw n_1700_B.create();
        }
        score.n_1700_B(true);
        return score;
    }
}


