/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import lightning.product.ColorArgument;
import lightning.product.C_131_O;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.Q_2106_h;
import lightning.product.Q_2241_p;
import lightning.product.U_2871_b;
import lightning.product.PlayerTeam;
import lightning.product.ComponentArgument;
import lightning.product.ServerScoreboard;
import lightning.product.i_4895_l;
import lightning.product.o_3050_h;
import lightning.product.ComponentUtils;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class N_927_Q {
    private static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.add.duplicate"));
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208916_0_ -> new F_2904_S("commands.team.add.longName", p_208916_0_));
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.empty.unchanged"));
    private static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.option.name.unchanged"));
    private static final SimpleCommandExceptionType P_1922_E = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.option.color.unchanged"));
    private static final SimpleCommandExceptionType u_1723_Y = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.option.friendlyfire.alreadyEnabled"));
    private static final SimpleCommandExceptionType v_4262_N = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.option.friendlyfire.alreadyDisabled"));
    private static final SimpleCommandExceptionType w_1484_f = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.option.seeFriendlyInvisibles.alreadyEnabled"));
    private static final SimpleCommandExceptionType t_148_a = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.option.seeFriendlyInvisibles.alreadyDisabled"));
    private static final SimpleCommandExceptionType s_956_w = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.option.nametagVisibility.unchanged"));
    private static final SimpleCommandExceptionType u_2550_I = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.option.deathMessageVisibility.unchanged"));
    private static final SimpleCommandExceptionType M_588_G = new SimpleCommandExceptionType((Message)new F_2904_S("commands.team.option.collisionRule.unchanged"));

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("team").requires(p_198780_0_ -> p_198780_0_.n_1700_B(2))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("list").executes(p_198760_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198760_0_.getSource()))).then(Q_2241_p.n_1700_B("team", Q_2106_h.n_1700_B()).executes(p_198763_0_ -> N_927_Q.R_4764_Y((y_2498_m)p_198763_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198763_0_, "team")))))).then(Q_2241_p.n_1700_B("add").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("team", StringArgumentType.word()).executes(p_198767_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198767_0_.getSource(), StringArgumentType.getString((CommandContext)p_198767_0_, (String)"team")))).then(Q_2241_p.n_1700_B("displayName", ComponentArgument.n_1700_B()).executes(p_198779_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198779_0_.getSource(), StringArgumentType.getString((CommandContext)p_198779_0_, (String)"team"), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_198779_0_, "displayName"))))))).then(Q_2241_p.n_1700_B("remove").then(Q_2241_p.n_1700_B("team", Q_2106_h.n_1700_B()).executes(p_198773_0_ -> N_927_Q.J_1907_R((y_2498_m)p_198773_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198773_0_, "team")))))).then(Q_2241_p.n_1700_B("empty").then(Q_2241_p.n_1700_B("team", Q_2106_h.n_1700_B()).executes(p_198785_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198785_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198785_0_, "team")))))).then(Q_2241_p.n_1700_B("join").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("team", Q_2106_h.n_1700_B()).executes(p_198758_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198758_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198758_0_, "team"), Collections.singleton(((y_2498_m)p_198758_0_.getSource()).M_182_A().L_3570_A())))).then(Q_2241_p.n_1700_B("members", C_131_O.J_1907_R()).suggests(C_131_O.n_1700_B).executes(p_198755_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198755_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198755_0_, "team"), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198755_0_, "members"))))))).then(Q_2241_p.n_1700_B("leave").then(Q_2241_p.n_1700_B("members", C_131_O.J_1907_R()).suggests(C_131_O.n_1700_B).executes(p_198765_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198765_0_.getSource(), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_198765_0_, "members")))))).then(Q_2241_p.n_1700_B("modify").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("team", Q_2106_h.n_1700_B()).then(Q_2241_p.n_1700_B("displayName").then(Q_2241_p.n_1700_B("displayName", ComponentArgument.n_1700_B()).executes(p_211919_0_ -> N_927_Q.n_1700_B((y_2498_m)p_211919_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_211919_0_, "team"), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_211919_0_, "displayName")))))).then(Q_2241_p.n_1700_B("color").then(Q_2241_p.n_1700_B("value", ColorArgument.n_1700_B()).executes(p_198762_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198762_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198762_0_, "team"), ColorArgument.n_1700_B((CommandContext<y_2498_m>)p_198762_0_, "value")))))).then(Q_2241_p.n_1700_B("friendlyFire").then(Q_2241_p.n_1700_B("allowed", BoolArgumentType.bool()).executes(p_198775_0_ -> N_927_Q.J_1907_R((y_2498_m)p_198775_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198775_0_, "team"), BoolArgumentType.getBool((CommandContext)p_198775_0_, (String)"allowed")))))).then(Q_2241_p.n_1700_B("seeFriendlyInvisibles").then(Q_2241_p.n_1700_B("allowed", BoolArgumentType.bool()).executes(p_198770_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198770_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198770_0_, "team"), BoolArgumentType.getBool((CommandContext)p_198770_0_, (String)"allowed")))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("nametagVisibility").then(Q_2241_p.n_1700_B("never").executes(p_198778_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198778_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198778_0_, "team"), o_3050_h.J_1907_R.J_1907_R)))).then(Q_2241_p.n_1700_B("hideForOtherTeams").executes(p_198764_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198764_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198764_0_, "team"), o_3050_h.J_1907_R.R_4764_Y)))).then(Q_2241_p.n_1700_B("hideForOwnTeam").executes(p_198766_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198766_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198766_0_, "team"), o_3050_h.J_1907_R.G_564_y)))).then(Q_2241_p.n_1700_B("always").executes(p_198759_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198759_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198759_0_, "team"), o_3050_h.J_1907_R.n_1700_B))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("deathMessageVisibility").then(Q_2241_p.n_1700_B("never").executes(p_198789_0_ -> N_927_Q.J_1907_R((y_2498_m)p_198789_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198789_0_, "team"), o_3050_h.J_1907_R.J_1907_R)))).then(Q_2241_p.n_1700_B("hideForOtherTeams").executes(p_198791_0_ -> N_927_Q.J_1907_R((y_2498_m)p_198791_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198791_0_, "team"), o_3050_h.J_1907_R.R_4764_Y)))).then(Q_2241_p.n_1700_B("hideForOwnTeam").executes(p_198769_0_ -> N_927_Q.J_1907_R((y_2498_m)p_198769_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198769_0_, "team"), o_3050_h.J_1907_R.G_564_y)))).then(Q_2241_p.n_1700_B("always").executes(p_198774_0_ -> N_927_Q.J_1907_R((y_2498_m)p_198774_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198774_0_, "team"), o_3050_h.J_1907_R.n_1700_B))))).then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("collisionRule").then(Q_2241_p.n_1700_B("never").executes(p_198761_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198761_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198761_0_, "team"), o_3050_h.n_1700_B.J_1907_R)))).then(Q_2241_p.n_1700_B("pushOwnTeam").executes(p_198756_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198756_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198756_0_, "team"), o_3050_h.n_1700_B.G_564_y)))).then(Q_2241_p.n_1700_B("pushOtherTeams").executes(p_198754_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198754_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198754_0_, "team"), o_3050_h.n_1700_B.R_4764_Y)))).then(Q_2241_p.n_1700_B("always").executes(p_198790_0_ -> N_927_Q.n_1700_B((y_2498_m)p_198790_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_198790_0_, "team"), o_3050_h.n_1700_B.n_1700_B))))).then(Q_2241_p.n_1700_B("prefix").then(Q_2241_p.n_1700_B("prefix", ComponentArgument.n_1700_B()).executes(p_207514_0_ -> N_927_Q.J_1907_R((y_2498_m)p_207514_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_207514_0_, "team"), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_207514_0_, "prefix")))))).then(Q_2241_p.n_1700_B("suffix").then(Q_2241_p.n_1700_B("suffix", ComponentArgument.n_1700_B()).executes(p_207516_0_ -> N_927_Q.R_4764_Y((y_2498_m)p_207516_0_.getSource(), Q_2106_h.n_1700_B((CommandContext<y_2498_m>)p_207516_0_, "team"), ComponentArgument.n_1700_B((CommandContext<y_2498_m>)p_207516_0_, "suffix"))))))));
    }

    private static int n_1700_B(y_2498_m source, Collection<String> players) {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        for (String s : players) {
            scoreboard.v_4262_N(s);
        }
        if (players.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.team.leave.success.single", players.iterator().next()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.team.leave.success.multiple", players.size()), true);
        }
        return players.size();
    }

    private static int n_1700_B(y_2498_m source, PlayerTeam teamIn, Collection<String> players) {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        for (String s : players) {
            ((i_4895_l)scoreboard).n_1700_B(s, teamIn);
        }
        if (players.size() == 1) {
            source.n_1700_B(new F_2904_S("commands.team.join.success.single", players.iterator().next(), teamIn.R_4764_Y()), true);
        } else {
            source.n_1700_B(new F_2904_S("commands.team.join.success.multiple", players.size(), teamIn.R_4764_Y()), true);
        }
        return players.size();
    }

    private static int n_1700_B(y_2498_m source, PlayerTeam teamIn, o_3050_h.J_1907_R visibility) throws CommandSyntaxException {
        if (teamIn.t_148_a() == visibility) {
            throw s_956_w.create();
        }
        teamIn.n_1700_B(visibility);
        source.n_1700_B(new F_2904_S("commands.team.option.nametagVisibility.success", teamIn.R_4764_Y(), visibility.n_1700_B()), true);
        return 0;
    }

    private static int J_1907_R(y_2498_m source, PlayerTeam teamIn, o_3050_h.J_1907_R visibility) throws CommandSyntaxException {
        if (teamIn.s_956_w() == visibility) {
            throw u_2550_I.create();
        }
        teamIn.J_1907_R(visibility);
        source.n_1700_B(new F_2904_S("commands.team.option.deathMessageVisibility.success", teamIn.R_4764_Y(), visibility.n_1700_B()), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, PlayerTeam teamIn, o_3050_h.n_1700_B rule) throws CommandSyntaxException {
        if (teamIn.u_2550_I() == rule) {
            throw M_588_G.create();
        }
        teamIn.n_1700_B(rule);
        source.n_1700_B(new F_2904_S("commands.team.option.collisionRule.success", teamIn.R_4764_Y(), rule.n_1700_B()), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, PlayerTeam teamIn, boolean value) throws CommandSyntaxException {
        if (teamIn.w_1484_f() == value) {
            if (value) {
                throw w_1484_f.create();
            }
            throw t_148_a.create();
        }
        teamIn.J_1907_R(value);
        source.n_1700_B(new F_2904_S("commands.team.option.seeFriendlyInvisibles." + (value ? "enabled" : "disabled"), teamIn.R_4764_Y()), true);
        return 0;
    }

    private static int J_1907_R(y_2498_m source, PlayerTeam teamIn, boolean value) throws CommandSyntaxException {
        if (teamIn.v_4262_N() == value) {
            if (value) {
                throw u_1723_Y.create();
            }
            throw v_4262_N.create();
        }
        teamIn.n_1700_B(value);
        source.n_1700_B(new F_2904_S("commands.team.option.friendlyfire." + (value ? "enabled" : "disabled"), teamIn.R_4764_Y()), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, PlayerTeam teamIn, x_282_a value) throws CommandSyntaxException {
        if (teamIn.J_1907_R().equals(value)) {
            throw G_564_y.create();
        }
        teamIn.n_1700_B(value);
        source.n_1700_B(new F_2904_S("commands.team.option.name.success", teamIn.R_4764_Y()), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, PlayerTeam teamIn, D_4024_W value) throws CommandSyntaxException {
        if (teamIn.P_4830_p() == value) {
            throw P_1922_E.create();
        }
        teamIn.n_1700_B(value);
        source.n_1700_B(new F_2904_S("commands.team.option.color.success", teamIn.R_4764_Y(), value.P_1922_E()), true);
        return 0;
    }

    private static int n_1700_B(y_2498_m source, PlayerTeam teamIn) throws CommandSyntaxException {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        ArrayList collection = Lists.newArrayList(teamIn.u_1723_Y());
        if (collection.isEmpty()) {
            throw R_4764_Y.create();
        }
        for (String s : collection) {
            ((i_4895_l)scoreboard).J_1907_R(s, teamIn);
        }
        source.n_1700_B(new F_2904_S("commands.team.empty.success", collection.size(), teamIn.R_4764_Y()), true);
        return collection.size();
    }

    private static int J_1907_R(y_2498_m source, PlayerTeam teamIn) {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        scoreboard.n_1700_B(teamIn);
        source.n_1700_B(new F_2904_S("commands.team.remove.success", teamIn.R_4764_Y()), true);
        return scoreboard.P_1922_E().size();
    }

    private static int n_1700_B(y_2498_m source, String name) throws CommandSyntaxException {
        return N_927_Q.n_1700_B(source, name, (x_282_a)new U_2871_b(name));
    }

    private static int n_1700_B(y_2498_m source, String name, x_282_a displayName) throws CommandSyntaxException {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        if (scoreboard.P_1922_E(name) != null) {
            throw n_1700_B.create();
        }
        if (name.length() > 16) {
            throw J_1907_R.create((Object)16);
        }
        PlayerTeam scoreplayerteam = scoreboard.u_1723_Y(name);
        scoreplayerteam.n_1700_B(displayName);
        source.n_1700_B(new F_2904_S("commands.team.add.success", scoreplayerteam.R_4764_Y()), true);
        return scoreboard.P_1922_E().size();
    }

    private static int R_4764_Y(y_2498_m source, PlayerTeam teamIn) {
        Collection<String> collection = teamIn.u_1723_Y();
        if (collection.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.team.list.members.empty", teamIn.R_4764_Y()), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.team.list.members.success", teamIn.R_4764_Y(), collection.size(), ComponentUtils.n_1700_B(collection)), false);
        }
        return collection.size();
    }

    private static int n_1700_B(y_2498_m source) {
        Collection<PlayerTeam> collection = source.w_1457_N().S_4022_R().P_1922_E();
        if (collection.isEmpty()) {
            source.n_1700_B(new F_2904_S("commands.team.list.teams.empty"), false);
        } else {
            source.n_1700_B(new F_2904_S("commands.team.list.teams.success", collection.size(), ComponentUtils.J_1907_R(collection, PlayerTeam::R_4764_Y)), false);
        }
        return collection.size();
    }

    private static int J_1907_R(y_2498_m source, PlayerTeam teamIn, x_282_a prefix) {
        teamIn.J_1907_R(prefix);
        source.n_1700_B(new F_2904_S("commands.team.option.prefix.success", prefix), false);
        return 1;
    }

    private static int R_4764_Y(y_2498_m source, PlayerTeam teamIn, x_282_a suffix) {
        teamIn.R_4764_Y(suffix);
        source.n_1700_B(new F_2904_S("commands.team.option.suffix.success", suffix), false);
        return 1;
    }
}


