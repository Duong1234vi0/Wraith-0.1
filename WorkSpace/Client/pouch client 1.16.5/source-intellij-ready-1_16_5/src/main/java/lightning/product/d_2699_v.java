/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import lightning.product.C_131_O;
import lightning.product.F_2904_S;
import lightning.product.M_1462_J;
import lightning.product.Q_2241_p;
import lightning.product.ScoreboardSlotArgument;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.ObjectiveCriteriaArgument;
import lightning.product.Objective;
import lightning.product.ComponentArgument;
import lightning.product.ObjectiveArgument;
import lightning.product.ServerScoreboard;
import lightning.product.i_4895_l;
import lightning.product.j_284_m;
import lightning.product.v_4839_y;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class d_2699_v {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.scoreboard.objectives.add.duplicate"));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.scoreboard.objectives.display.alreadyEmpty"));
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.scoreboard.objectives.display.alreadySet"));
    private static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.scoreboard.players.enable.failed"));
    private static final SimpleCommandExceptionType P_1922_E = new SimpleCommandExceptionType((Message)new F_2904_S("commands.scoreboard.players.enable.invalid"));
    private static final Dynamic2CommandExceptionType u_1723_Y = new Dynamic2CommandExceptionType((p_208907_0_, p_208907_1_) -> new F_2904_S("commands.scoreboard.players.get.null", p_208907_0_, p_208907_1_));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("scoreboard").requires(p_198650_0_ -> p_198650_0_.n_1700_B(2))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("objectives").then(Q_2241_p.n_1700_B("list").executes(p_198640_0_ -> d_2699_v.J_1907_R((y_2498_m)p_198640_0_.getSource())))).then(Q_2241_p.n_1700_B("add").then(Q_2241_p.n_1700_B("objective", StringArgumentType.word()).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("criteria", ObjectiveCriteriaArgument.n_1700_B()).executes(p_198636_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198636_0_.getSource(), StringArgumentType.getString((CommandContext)p_198636_0_, (String)"objective"), ObjectiveCriteriaArgument.n_1700_B((CommandContext<y_2498_m>)p_198636_0_, "criteria"), new U_2871_b(StringArgumentType.getString((CommandContext)p_198636_0_, (String)"objective"))))).then(Q_2241_p.n_1700_B("displayName", ComponentArgument.n_1700_B()).executes(p_198649_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198649_0_.getSource(), StringArgumentType.getString((CommandContext)p_198649_0_, (String)"objective"), ObjectiveCriteriaArgument.n_1700_B((CommandContext<y_2498_m>)p_198649_0_, "criteria"), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_198649_0_, "displayName")))))))).then(Q_2241_p.n_1700_B("modify").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).then(Q_2241_p.n_1700_B("displayname").then(Q_2241_p.n_1700_B("displayName", ComponentArgument.n_1700_B()).executes(p_211750_0_ -> d_2699_v.n_1700_B((y_2498_m)p_211750_0_.getSource(), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_211750_0_, "objective"), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_211750_0_, "displayName")))))).then(d_2699_v.n_1700_B())))).then(Q_2241_p.n_1700_B("remove").then(Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).executes(p_198646_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198646_0_.getSource(), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_198646_0_, "objective")))))).then(Q_2241_p.n_1700_B("setdisplay").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("slot", ScoreboardSlotArgument.n_1700_B()).executes(p_198652_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198652_0_.getSource(), ScoreboardSlotArgument.n_1700_B((CommandContext<y_2498_m>)p_198652_0_, "slot")))).then(Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).executes(p_198639_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198639_0_.getSource(), ScoreboardSlotArgument.n_1700_B((CommandContext<y_2498_m>)p_198639_0_, "slot"), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_198639_0_, "objective")))))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("players").then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("list").executes(p_198642_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198642_0_.getSource()))).then(Q_2241_p.n_1700_B("target", C_131_O.n_1700_B()).suggests(C_131_O.n_1700_B).executes(p_198631_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198631_0_.getSource(), C_131_O.n_1700_B((CommandContext<y_2498_m>)p_198631_0_, "target")))))).then(Q_2241_p.n_1700_B("set").then(Q_2241_p.n_1700_B("targets", C_131_O.J_1907_R()).suggests(C_131_O.n_1700_B).then(Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).then(Q_2241_p.n_1700_B("score", IntegerArgumentType.integer()).executes(p_198655_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198655_0_.getSource(), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198655_0_, "targets"), ObjectiveArgument.J_1907_R((CommandContext<y_2498_m>)p_198655_0_, "objective"), IntegerArgumentType.getInteger((CommandContext)p_198655_0_, (String)"score")))))))).then(Q_2241_p.n_1700_B("get").then(Q_2241_p.n_1700_B("target", C_131_O.n_1700_B()).suggests(C_131_O.n_1700_B).then(Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).executes(p_198660_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198660_0_.getSource(), C_131_O.n_1700_B((CommandContext<y_2498_m>)p_198660_0_, "target"), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_198660_0_, "objective"))))))).then(Q_2241_p.n_1700_B("add").then(Q_2241_p.n_1700_B("targets", C_131_O.J_1907_R()).suggests(C_131_O.n_1700_B).then(Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).then(Q_2241_p.n_1700_B("score", IntegerArgumentType.integer((int)0)).executes(p_198645_0_ -> d_2699_v.J_1907_R((y_2498_m)p_198645_0_.getSource(), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198645_0_, "targets"), ObjectiveArgument.J_1907_R((CommandContext<y_2498_m>)p_198645_0_, "objective"), IntegerArgumentType.getInteger((CommandContext)p_198645_0_, (String)"score")))))))).then(Q_2241_p.n_1700_B("remove").then(Q_2241_p.n_1700_B("targets", C_131_O.J_1907_R()).suggests(C_131_O.n_1700_B).then(Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).then(Q_2241_p.n_1700_B("score", IntegerArgumentType.integer((int)0)).executes(p_198648_0_ -> d_2699_v.R_4764_Y((y_2498_m)p_198648_0_.getSource(), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198648_0_, "targets"), ObjectiveArgument.J_1907_R((CommandContext<y_2498_m>)p_198648_0_, "objective"), IntegerArgumentType.getInteger((CommandContext)p_198648_0_, (String)"score")))))))).then(Q_2241_p.n_1700_B("reset").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targets", C_131_O.J_1907_R()).suggests(C_131_O.n_1700_B).executes(p_198635_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198635_0_.getSource(), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198635_0_, "targets")))).then(Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).executes(p_198630_0_ -> d_2699_v.J_1907_R((y_2498_m)p_198630_0_.getSource(), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198630_0_, "targets"), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_198630_0_, "objective"))))))).then(Q_2241_p.n_1700_B("enable").then(Q_2241_p.n_1700_B("targets", C_131_O.J_1907_R()).suggests(C_131_O.n_1700_B).then(Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).suggests((p_198638_0_, p_198638_1_) -> d_2699_v.n_1700_B((y_2498_m)p_198638_0_.getSource(), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198638_0_, "targets"), p_198638_1_)).executes(p_198628_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198628_0_.getSource(), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198628_0_, "targets"), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_198628_0_, "objective"))))))).then(Q_2241_p.n_1700_B("operation").then(Q_2241_p.n_1700_B("targets", C_131_O.J_1907_R()).suggests(C_131_O.n_1700_B).then(Q_2241_p.n_1700_B("targetObjective", ObjectiveArgument.n_1700_B()).then(Q_2241_p.n_1700_B("operation", j_284_m.n_1700_B()).then(Q_2241_p.n_1700_B("source", C_131_O.J_1907_R()).suggests(C_131_O.n_1700_B).then(Q_2241_p.n_1700_B("sourceObjective", ObjectiveArgument.n_1700_B()).executes(p_198657_0_ -> d_2699_v.n_1700_B((y_2498_m)p_198657_0_.getSource(), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198657_0_, "targets"), ObjectiveArgument.J_1907_R((CommandContext<y_2498_m>)p_198657_0_, "targetObjective"), j_284_m.n_1700_B((CommandContext<y_2498_m>)p_198657_0_, "operation"), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198657_0_, "source"), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_198657_0_, "sourceObjective")))))))))));
    }

    private static LiteralArgumentBuilder<y_2498_m> n_1700_B() {
        LiteralArgumentBuilder<y_2498_m> literalargumentbuilder = Q_2241_p.n_1700_B("rendertype");
        for (M_1462_J.n_1700_B scorecriteria$rendertype : M_1462_J.n_1700_B.values()) {
            literalargumentbuilder.then(Q_2241_p.n_1700_B(scorecriteria$rendertype.n_1700_B()).executes(p_211912_1_ -> d_2699_v.n_1700_B((y_2498_m)p_211912_1_.getSource(), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_211912_1_, "objective"), scorecriteria$rendertype)));
        }
        return literalargumentbuilder;
    }

    private static CompletableFuture<Suggestions> n_1700_B(y_2498_m source, Collection<String> targets, SuggestionsBuilder suggestions) {
        ArrayList list = Lists.newArrayList();
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        for (Objective scoreobjective : scoreboard.n_1700_B()) {
            if (scoreobjective.R_4764_Y() != M_1462_J.R_4764_Y) continue;
            boolean flag = false;
            for (String s : targets) {
                if (scoreboard.n_1700_B(s, scoreobjective) && !scoreboard.J_1907_R(s, scoreobjective).v_4262_N()) continue;
                flag = true;
                break;
            }
            if (!flag) continue;
            list.add(scoreobjective.J_1907_R());
        }
        return V_4217_p.J_1907_R(list, suggestions);
    }

    private static int n_1700_B(y_2498_m source, String player, Objective objective) throws CommandSyntaxException {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        if (!scoreboard.n_1700_B(player, objective)) {
            throw u_1723_Y.create((Object)objective.J_1907_R(), (Object)player);
        }
        v_4839_y score = scoreboard.J_1907_R(player, objective);
        source.n_1700_B(new F_2904_S("commands.scoreboard.players.get.success", player, score.J_1907_R(), objective.P_1922_E()), false);
        return score.J_1907_R();
    }

    private static int n_1700_B(y_2498_m source, Collection<String> targetEntities, Objective targetObjectives, j_284_m.J_1907_R operation, Collection<String> sourceEntities, Objective sourceObjective) throws CommandSyntaxException {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        int i = 0;
        for (String s : targetEntities) {
            v_4839_y score = scoreboard.J_1907_R(s, targetObjectives);
            for (String s1 : sourceEntities) {
                v_4839_y score1 = scoreboard.J_1907_R(s1, sourceObjective);
                operation.apply(score, score1);
            }
            i += score.J_1907_R();
        }
        if (targetEntities.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.operation.success.single", targetObjectives.P_1922_E(), targetEntities.iterator().next(), i), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.operation.success.multiple", targetObjectives.P_1922_E(), targetEntities.size()), true);
        }
        return i;
    }

    private static int n_1700_B(y_2498_m source, Collection<String> targets, Objective objective) throws CommandSyntaxException {
        if (objective.R_4764_Y() != M_1462_J.R_4764_Y) {
            throw P_1922_E.create();
        }
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        int i = 0;
        for (String s : targets) {
            v_4839_y score = scoreboard.J_1907_R(s, objective);
            if (!score.v_4262_N()) continue;
            score.n_1700_B(false);
            ++i;
        }
        if (i == 0) {
            throw G_564_y.create();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.enable.success.single", objective.P_1922_E(), targets.iterator().next()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.enable.success.multiple", objective.P_1922_E(), targets.size()), true);
        }
        return i;
    }

    private static int n_1700_B(y_2498_m source, Collection<String> targets) {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        for (String s : targets) {
            scoreboard.R_4764_Y(s, null);
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.reset.all.single", targets.iterator().next()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.reset.all.multiple", targets.size()), true);
        }
        return targets.size();
    }

    private static int J_1907_R(y_2498_m source, Collection<String> targets, Objective objective) {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        for (String s : targets) {
            scoreboard.R_4764_Y(s, objective);
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.reset.specific.single", objective.P_1922_E(), targets.iterator().next()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.reset.specific.multiple", objective.P_1922_E(), targets.size()), true);
        }
        return targets.size();
    }

    private static int n_1700_B(y_2498_m source, Collection<String> targets, Objective objective, int newValue) {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        for (String s : targets) {
            v_4839_y score = scoreboard.J_1907_R(s, objective);
            score.J_1907_R(newValue);
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.set.success.single", objective.P_1922_E(), targets.iterator().next(), newValue), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.set.success.multiple", objective.P_1922_E(), targets.size(), newValue), true);
        }
        return newValue * targets.size();
    }

    private static int J_1907_R(y_2498_m source, Collection<String> targets, Objective objective, int amount) {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        int i = 0;
        for (String s : targets) {
            v_4839_y score = scoreboard.J_1907_R(s, objective);
            score.J_1907_R(score.J_1907_R() + amount);
            i += score.J_1907_R();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.add.success.single", amount, objective.P_1922_E(), targets.iterator().next(), i), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.add.success.multiple", amount, objective.P_1922_E(), targets.size()), true);
        }
        return i;
    }

    private static int R_4764_Y(y_2498_m source, Collection<String> targets, Objective objective, int amount) {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        int i = 0;
        for (String s : targets) {
            v_4839_y score = scoreboard.J_1907_R(s, objective);
            score.J_1907_R(score.J_1907_R() - amount);
            i += score.J_1907_R();
        }
        if (targets.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.remove.success.single", amount, objective.P_1922_E(), targets.iterator().next(), i), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.remove.success.multiple", amount, objective.P_1922_E(), targets.size()), true);
        }
        return i;
    }

    private static int n_1700_B(y_2498_m source) {
        Collection<String> collection = source.w_1457_N().S_4022_R().R_4764_Y();
        if (collection.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.list.empty"), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.list.success", collection.size(), ComponentUtils.n_1700_B(collection)), false);
        }
        return collection.size();
    }

    private static int n_1700_B(y_2498_m source, String player) {
        Map<Objective, v_4839_y> map = source.w_1457_N().S_4022_R().G_564_y(player);
        if (map.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.list.entity.empty", player), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.scoreboard.players.list.entity.success", player, map.size()), false);
            for (Map.Entry<Objective, v_4839_y> entry : map.entrySet()) {
                source.n_1700_B(new F_2904_S("commands.scoreboard.players.list.entity.entry", entry.getKey().P_1922_E(), entry.getValue().J_1907_R()), false);
            }
        }
        return map.size();
    }

    private static int n_1700_B(y_2498_m source, int slotId) throws CommandSyntaxException {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        if (scoreboard.n_1700_B(slotId) == null) {
            throw J_1907_R.create();
        }
        ((i_4895_l)scoreboard).n_1700_B(slotId, (Objective)null);
        source.n_1700_B(new F_2904_S("commands.scoreboard.objectives.display.cleared", i_4895_l.u_1723_Y()[slotId]), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, int slotId, Objective objective) throws CommandSyntaxException {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        if (scoreboard.n_1700_B(slotId) == objective) {
            throw R_4764_Y.create();
        }
        ((i_4895_l)scoreboard).n_1700_B(slotId, objective);
        source.n_1700_B(new F_2904_S("commands.scoreboard.objectives.display.set", i_4895_l.u_1723_Y()[slotId], objective.G_564_y()), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, Objective objective, x_282_a displayName) {
        if (!objective.G_564_y().equals(displayName)) {
            objective.n_1700_B(displayName);
            source.n_1700_B(new F_2904_S("commands.scoreboard.objectives.modify.displayname", objective.J_1907_R(), objective.P_1922_E()), true);
        }
        return 0;
    }

    private static int n_1700_B(y_2498_m source, Objective objective, M_1462_J.n_1700_B renderType) {
        if (objective.u_1723_Y() != renderType) {
            objective.n_1700_B(renderType);
            source.n_1700_B(new F_2904_S("commands.scoreboard.objectives.modify.rendertype", objective.P_1922_E()), true);
        }
        return 0;
    }

    private static int n_1700_B(y_2498_m source, Objective objective) {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        scoreboard.J_1907_R(objective);
        source.n_1700_B(new F_2904_S("commands.scoreboard.objectives.remove.success", objective.P_1922_E()), true);
        return scoreboard.n_1700_B().size();
    }

    private static int n_1700_B(y_2498_m source, String name, M_1462_J criteria, x_282_a displayName) throws CommandSyntaxException {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        if (scoreboard.R_4764_Y(name) != null) {
            throw n_1700_B.create();
        }
        if (name.length() > 16) {
            throw ObjectiveArgument.n_1700_B.create((Object)16);
        }
        scoreboard.n_1700_B(name, criteria, displayName, criteria.R_4764_Y());
        Objective scoreobjective = scoreboard.R_4764_Y(name);
        source.n_1700_B(new F_2904_S("commands.scoreboard.objectives.add.success", scoreobjective.P_1922_E()), true);
        return scoreboard.n_1700_B().size();
    }

    private static int J_1907_R(y_2498_m source) {
        Collection<Objective> collection = source.w_1457_N().S_4022_R().n_1700_B();
        if (collection.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.scoreboard.objectives.list.empty"), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.scoreboard.objectives.list.success", collection.size(), ComponentUtils.J_1907_R(collection, Objective::P_1922_E)), false);
        }
        return collection.size();
    }
}


