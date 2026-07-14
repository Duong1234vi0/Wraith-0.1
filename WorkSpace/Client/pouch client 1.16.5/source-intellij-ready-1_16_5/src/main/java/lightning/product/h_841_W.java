/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.Command
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.ResultConsumer
 *  com.mojang.brigadier.arguments.DoubleArgumentType
 *  com.mojang.brigadier.builder.ArgumentBuilder
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.LiteralCommandNode
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.ResultConsumer;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import lightning.product.LootContextParams;
import lightning.product.C_131_O;
import lightning.product.D_908_R;
import lightning.product.BlockPredicateArgument;
import lightning.product.F_2904_S;
import lightning.product.K_1178_t;
import lightning.product.K_4074_S;
import lightning.product.DataAccessor;
import lightning.product.L_3985_e;
import lightning.product.RangeArgument;
import lightning.product.BoundingBox;
import lightning.product.N_4263_v;
import lightning.product.Q_2241_p;
import lightning.product.LootItemCondition;
import lightning.product.RotationArgument;
import lightning.product.T_2717_K;
import lightning.product.U_2912_j;
import lightning.product.V_4217_p;
import lightning.product.Objective;
import lightning.product.Tag;
import lightning.product.Z_4195_o;
import lightning.product.SwizzleArgument;
import lightning.product.a_3742_W;
import lightning.product.a_969_m;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.f_1402_I;
import lightning.product.g_168_b;
import lightning.product.ObjectiveArgument;
import lightning.product.i_2154_H;
import lightning.product.ServerScoreboard;
import lightning.product.i_4556_r;
import lightning.product.k_1471_n;
import lightning.product.ResourceLocationArgument;
import lightning.product.BlockPosArgument;
import lightning.product.BlockInWorld;
import lightning.product.q_1704_m;
import lightning.product.q_2567_I;
import lightning.product.IntTag;
import lightning.product.MinMaxBounds;
import lightning.product.u_1579_Y;
import lightning.product.v_4839_y;
import lightning.product.y_2498_m;
import lightning.product.z_1856_e;
import lightning.product.EntityAnchorArgument;

public class h_841_W {
    private static final Dynamic2CommandExceptionType n_1700_B = new Dynamic2CommandExceptionType((p_208885_0_, p_208885_1_) -> new F_2904_S("commands.execute.blocks.toobig", p_208885_0_, p_208885_1_));
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("commands.execute.conditional.fail"));
    private static final DynamicCommandExceptionType R_4764_Y = new DynamicCommandExceptionType(p_210446_0_ -> new F_2904_S("commands.execute.conditional.fail_count", p_210446_0_));
    private static final BinaryOperator<ResultConsumer<y_2498_m>> G_564_y = (p_209937_0_, p_209937_1_) -> (p_209939_2_, p_209939_3_, p_209939_4_) -> {
        p_209937_0_.onCommandComplete(p_209939_2_, p_209939_3_, p_209939_4_);
        p_209937_1_.onCommandComplete(p_209939_2_, p_209939_3_, p_209939_4_);
    };
    private static final SuggestionProvider<y_2498_m> P_1922_E = (p_229763_0_, p_229763_1_) -> {
        k_1471_n lootpredicatemanager = ((y_2498_m)p_229763_0_.getSource()).w_1457_N().RealmsDefaultUncaughtExceptionHandler();
        return V_4217_p.n_1700_B(lootpredicatemanager.J_1907_R(), p_229763_1_);
    };

    public static void n_1700_B(CommandDispatcher<y_2498_m> dispatcher) {
        LiteralCommandNode literalcommandnode = dispatcher.register((LiteralArgumentBuilder)Q_2241_p.n_1700_B("execute").requires(p_198387_0_ -> p_198387_0_.n_1700_B(2)));
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Q_2241_p.n_1700_B("execute").requires(p_229766_0_ -> p_229766_0_.n_1700_B(2))).then(Q_2241_p.n_1700_B("run").redirect((CommandNode)dispatcher.getRoot()))).then(h_841_W.n_1700_B((CommandNode<y_2498_m>)literalcommandnode, Q_2241_p.n_1700_B("if"), true))).then(h_841_W.n_1700_B((CommandNode<y_2498_m>)literalcommandnode, Q_2241_p.n_1700_B("unless"), false))).then(Q_2241_p.n_1700_B("as").then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).fork((CommandNode)literalcommandnode, p_198384_0_ -> {
            ArrayList list = Lists.newArrayList();
            for (N_4263_v n_4263_v : i_4556_r.R_4764_Y((CommandContext<y_2498_m>)p_198384_0_, "targets")) {
                list.add(((y_2498_m)p_198384_0_.getSource()).n_1700_B(n_4263_v));
            }
            return list;
        })))).then(Q_2241_p.n_1700_B("at").then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).fork((CommandNode)literalcommandnode, p_229809_0_ -> {
            ArrayList list = Lists.newArrayList();
            for (N_4263_v n_4263_v : i_4556_r.R_4764_Y((CommandContext<y_2498_m>)p_229809_0_, "targets")) {
                list.add(((y_2498_m)p_229809_0_.getSource()).n_1700_B((e_3591_l)n_4263_v.O_508_d).n_1700_B(n_4263_v.s_4990_V()).n_1700_B(n_4263_v.f_1043_S()));
            }
            return list;
        })))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("store").then(h_841_W.n_1700_B((LiteralCommandNode<y_2498_m>)literalcommandnode, Q_2241_p.n_1700_B("result"), true))).then(h_841_W.n_1700_B((LiteralCommandNode<y_2498_m>)literalcommandnode, Q_2241_p.n_1700_B("success"), false)))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("positioned").then(Q_2241_p.n_1700_B("pos", u_1579_Y.n_1700_B()).redirect((CommandNode)literalcommandnode, p_229808_0_ -> ((y_2498_m)p_229808_0_.getSource()).n_1700_B(u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_229808_0_, "pos")).n_1700_B(EntityAnchorArgument.n_1700_B.n_1700_B)))).then(Q_2241_p.n_1700_B("as").then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).fork((CommandNode)literalcommandnode, p_229807_0_ -> {
            ArrayList list = Lists.newArrayList();
            for (N_4263_v n_4263_v : i_4556_r.R_4764_Y((CommandContext<y_2498_m>)p_229807_0_, "targets")) {
                list.add(((y_2498_m)p_229807_0_.getSource()).n_1700_B(n_4263_v.s_4990_V()));
            }
            return list;
        }))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("rotated").then(Q_2241_p.n_1700_B("rot", RotationArgument.n_1700_B()).redirect((CommandNode)literalcommandnode, p_229806_0_ -> ((y_2498_m)p_229806_0_.getSource()).n_1700_B(RotationArgument.n_1700_B((CommandContext<y_2498_m>)p_229806_0_, "rot").J_1907_R((y_2498_m)p_229806_0_.getSource()))))).then(Q_2241_p.n_1700_B("as").then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).fork((CommandNode)literalcommandnode, p_201083_0_ -> {
            ArrayList list = Lists.newArrayList();
            for (N_4263_v n_4263_v : i_4556_r.R_4764_Y((CommandContext<y_2498_m>)p_201083_0_, "targets")) {
                list.add(((y_2498_m)p_201083_0_.getSource()).n_1700_B(n_4263_v.f_1043_S()));
            }
            return list;
        }))))).then(((LiteralArgumentBuilder)Q_2241_p.n_1700_B("facing").then(Q_2241_p.n_1700_B("entity").then(Q_2241_p.n_1700_B("targets", i_4556_r.J_1907_R()).then(Q_2241_p.n_1700_B("anchor", EntityAnchorArgument.n_1700_B()).fork((CommandNode)literalcommandnode, p_229805_0_ -> {
            ArrayList list = Lists.newArrayList();
            EntityAnchorArgument.n_1700_B entityanchorargument$type = EntityAnchorArgument.n_1700_B((CommandContext<y_2498_m>)p_229805_0_, "anchor");
            for (N_4263_v n_4263_v : i_4556_r.R_4764_Y((CommandContext<y_2498_m>)p_229805_0_, "targets")) {
                list.add(((y_2498_m)p_229805_0_.getSource()).n_1700_B(n_4263_v, entityanchorargument$type));
            }
            return list;
        }))))).then(Q_2241_p.n_1700_B("pos", u_1579_Y.n_1700_B()).redirect((CommandNode)literalcommandnode, p_198381_0_ -> ((y_2498_m)p_198381_0_.getSource()).J_1907_R(u_1579_Y.n_1700_B((CommandContext<y_2498_m>)p_198381_0_, "pos")))))).then(Q_2241_p.n_1700_B("align").then(Q_2241_p.n_1700_B("axes", SwizzleArgument.n_1700_B()).redirect((CommandNode)literalcommandnode, p_201091_0_ -> ((y_2498_m)p_201091_0_.getSource()).n_1700_B(((y_2498_m)p_201091_0_.getSource()).P_4830_p().n_1700_B(SwizzleArgument.n_1700_B((CommandContext<y_2498_m>)p_201091_0_, "axes"))))))).then(Q_2241_p.n_1700_B("anchored").then(Q_2241_p.n_1700_B("anchor", EntityAnchorArgument.n_1700_B()).redirect((CommandNode)literalcommandnode, p_201089_0_ -> ((y_2498_m)p_201089_0_.getSource()).n_1700_B(EntityAnchorArgument.n_1700_B((CommandContext<y_2498_m>)p_201089_0_, "anchor")))))).then(Q_2241_p.n_1700_B("in").then(Q_2241_p.n_1700_B("dimension", g_168_b.n_1700_B()).redirect((CommandNode)literalcommandnode, p_229804_0_ -> ((y_2498_m)p_229804_0_.getSource()).n_1700_B(g_168_b.n_1700_B((CommandContext<y_2498_m>)p_229804_0_, "dimension"))))));
    }

    private static ArgumentBuilder<y_2498_m, ?> n_1700_B(LiteralCommandNode<y_2498_m> parent, LiteralArgumentBuilder<y_2498_m> literal, boolean storingResult) {
        literal.then(Q_2241_p.n_1700_B("score").then(Q_2241_p.n_1700_B("targets", C_131_O.J_1907_R()).suggests(C_131_O.n_1700_B).then(Q_2241_p.n_1700_B("objective", ObjectiveArgument.n_1700_B()).redirect(parent, p_201468_1_ -> h_841_W.n_1700_B((y_2498_m)p_201468_1_.getSource(), C_131_O.R_4764_Y((CommandContext<y_2498_m>)p_201468_1_, "targets"), ObjectiveArgument.n_1700_B((CommandContext<y_2498_m>)p_201468_1_, "objective"), storingResult)))));
        literal.then(Q_2241_p.n_1700_B("bossbar").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("id", ResourceLocationArgument.n_1700_B()).suggests(Z_4195_o.n_1700_B).then(Q_2241_p.n_1700_B("value").redirect(parent, p_201457_1_ -> h_841_W.n_1700_B((y_2498_m)p_201457_1_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_201457_1_), true, storingResult)))).then(Q_2241_p.n_1700_B("max").redirect(parent, p_229795_1_ -> h_841_W.n_1700_B((y_2498_m)p_229795_1_.getSource(), Z_4195_o.n_1700_B((CommandContext<y_2498_m>)p_229795_1_), false, storingResult)))));
        for (z_1856_e.n_1700_B datacommand$idataprovider : z_1856_e.J_1907_R) {
            datacommand$idataprovider.n_1700_B((ArgumentBuilder<y_2498_m, ?>)literal, p_229765_3_ -> p_229765_3_.then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("path", K_1178_t.n_1700_B()).then(Q_2241_p.n_1700_B("int").then(Q_2241_p.n_1700_B("scale", DoubleArgumentType.doubleArg()).redirect((CommandNode)parent, p_229801_2_ -> h_841_W.n_1700_B((y_2498_m)p_229801_2_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_229801_2_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_229801_2_, "path"), (int p_229800_1_) -> IntTag.n_1700_B((int)((double)p_229800_1_ * DoubleArgumentType.getDouble((CommandContext)p_229801_2_, (String)"scale"))), storingResult))))).then(Q_2241_p.n_1700_B("float").then(Q_2241_p.n_1700_B("scale", DoubleArgumentType.doubleArg()).redirect((CommandNode)parent, p_229798_2_ -> h_841_W.n_1700_B((y_2498_m)p_229798_2_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_229798_2_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_229798_2_, "path"), (int p_229797_1_) -> T_2717_K.n_1700_B((float)((double)p_229797_1_ * DoubleArgumentType.getDouble((CommandContext)p_229798_2_, (String)"scale"))), storingResult))))).then(Q_2241_p.n_1700_B("short").then(Q_2241_p.n_1700_B("scale", DoubleArgumentType.doubleArg()).redirect((CommandNode)parent, p_229794_2_ -> h_841_W.n_1700_B((y_2498_m)p_229794_2_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_229794_2_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_229794_2_, "path"), (int p_229792_1_) -> a_969_m.n_1700_B((short)((double)p_229792_1_ * DoubleArgumentType.getDouble((CommandContext)p_229794_2_, (String)"scale"))), storingResult))))).then(Q_2241_p.n_1700_B("long").then(Q_2241_p.n_1700_B("scale", DoubleArgumentType.doubleArg()).redirect((CommandNode)parent, p_229790_2_ -> h_841_W.n_1700_B((y_2498_m)p_229790_2_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_229790_2_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_229790_2_, "path"), (int p_229788_1_) -> q_2567_I.n_1700_B((long)((double)p_229788_1_ * DoubleArgumentType.getDouble((CommandContext)p_229790_2_, (String)"scale"))), storingResult))))).then(Q_2241_p.n_1700_B("double").then(Q_2241_p.n_1700_B("scale", DoubleArgumentType.doubleArg()).redirect((CommandNode)parent, p_229784_2_ -> h_841_W.n_1700_B((y_2498_m)p_229784_2_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_229784_2_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_229784_2_, "path"), (int p_229781_1_) -> D_908_R.n_1700_B((double)p_229781_1_ * DoubleArgumentType.getDouble((CommandContext)p_229784_2_, (String)"scale")), storingResult))))).then(Q_2241_p.n_1700_B("byte").then(Q_2241_p.n_1700_B("scale", DoubleArgumentType.doubleArg()).redirect((CommandNode)parent, p_229774_2_ -> h_841_W.n_1700_B((y_2498_m)p_229774_2_.getSource(), datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_229774_2_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_229774_2_, "path"), (int p_229762_1_) -> L_3985_e.n_1700_B((byte)((double)p_229762_1_ * DoubleArgumentType.getDouble((CommandContext)p_229774_2_, (String)"scale"))), storingResult))))));
        }
        return literal;
    }

    private static y_2498_m n_1700_B(y_2498_m source, Collection<String> targets, Objective objective, boolean storingResult) {
        ServerScoreboard scoreboard = source.w_1457_N().S_4022_R();
        return source.n_1700_B((ResultConsumer<y_2498_m>)((ResultConsumer)(p_229769_4_, p_229769_5_, p_229769_6_) -> {
            for (String s : targets) {
                v_4839_y score = scoreboard.J_1907_R(s, objective);
                int i = storingResult ? p_229769_6_ : (p_229769_5_ ? 1 : 0);
                score.J_1907_R(i);
            }
        }), G_564_y);
    }

    private static y_2498_m n_1700_B(y_2498_m source, net.minecraft.server.n_1700_B bar, boolean storingValue, boolean storingResult) {
        return source.n_1700_B((ResultConsumer<y_2498_m>)((ResultConsumer)(p_229779_3_, p_229779_4_, p_229779_5_) -> {
            int i;
            int n = storingResult ? p_229779_5_ : (i = p_229779_4_ ? 1 : 0);
            if (storingValue) {
                bar.n_1700_B(i);
            } else {
                bar.J_1907_R(i);
            }
        }), G_564_y);
    }

    private static y_2498_m n_1700_B(y_2498_m source, DataAccessor accessor, K_1178_t.v_4262_N pathIn, IntFunction<Tag> tagConverter, boolean storingResult) {
        return source.n_1700_B((ResultConsumer<y_2498_m>)((ResultConsumer)(p_229772_4_, p_229772_5_, p_229772_6_) -> {
            try {
                U_2912_j compoundnbt = accessor.n_1700_B();
                int i = storingResult ? p_229772_6_ : (p_229772_5_ ? 1 : 0);
                pathIn.J_1907_R(compoundnbt, () -> (Tag)tagConverter.apply(i));
                accessor.n_1700_B(compoundnbt);
            }
            catch (CommandSyntaxException commandSyntaxException) {
                // empty catch block
            }
        }), G_564_y);
    }

    private static ArgumentBuilder<y_2498_m, ?> n_1700_B(CommandNode<y_2498_m> parent, LiteralArgumentBuilder<y_2498_m> literal, boolean isIf) {
        ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)literal.then(Q_2241_p.n_1700_B("block").then(Q_2241_p.n_1700_B("pos", BlockPosArgument.n_1700_B()).then(h_841_W.n_1700_B(parent, Q_2241_p.n_1700_B("block", BlockPredicateArgument.n_1700_B()), isIf, (CommandContext<y_2498_m> p_210438_0_) -> BlockPredicateArgument.n_1700_B((CommandContext<y_2498_m>)p_210438_0_, "block").test(new BlockInWorld(((y_2498_m)p_210438_0_.getSource()).h_1847_R(), BlockPosArgument.n_1700_B((CommandContext<y_2498_m>)p_210438_0_, "pos"), true))))))).then(Q_2241_p.n_1700_B("score").then(Q_2241_p.n_1700_B("target", C_131_O.n_1700_B()).suggests(C_131_O.n_1700_B).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)Q_2241_p.n_1700_B("targetObjective", ObjectiveArgument.n_1700_B()).then(Q_2241_p.n_1700_B("=").then(Q_2241_p.n_1700_B("source", C_131_O.n_1700_B()).suggests(C_131_O.n_1700_B).then(h_841_W.n_1700_B(parent, Q_2241_p.n_1700_B("sourceObjective", ObjectiveArgument.n_1700_B()), isIf, (CommandContext<y_2498_m> p_229803_0_) -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_229803_0_, Integer::equals)))))).then(Q_2241_p.n_1700_B("<").then(Q_2241_p.n_1700_B("source", C_131_O.n_1700_B()).suggests(C_131_O.n_1700_B).then(h_841_W.n_1700_B(parent, Q_2241_p.n_1700_B("sourceObjective", ObjectiveArgument.n_1700_B()), isIf, (CommandContext<y_2498_m> p_229802_0_) -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_229802_0_, (Integer p_229793_0_, Integer p_229793_1_) -> p_229793_0_ < p_229793_1_)))))).then(Q_2241_p.n_1700_B("<=").then(Q_2241_p.n_1700_B("source", C_131_O.n_1700_B()).suggests(C_131_O.n_1700_B).then(h_841_W.n_1700_B(parent, Q_2241_p.n_1700_B("sourceObjective", ObjectiveArgument.n_1700_B()), isIf, (CommandContext<y_2498_m> p_229799_0_) -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_229799_0_, (Integer p_229789_0_, Integer p_229789_1_) -> p_229789_0_ <= p_229789_1_)))))).then(Q_2241_p.n_1700_B(">").then(Q_2241_p.n_1700_B("source", C_131_O.n_1700_B()).suggests(C_131_O.n_1700_B).then(h_841_W.n_1700_B(parent, Q_2241_p.n_1700_B("sourceObjective", ObjectiveArgument.n_1700_B()), isIf, (CommandContext<y_2498_m> p_229796_0_) -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_229796_0_, (Integer p_229782_0_, Integer p_229782_1_) -> p_229782_0_ > p_229782_1_)))))).then(Q_2241_p.n_1700_B(">=").then(Q_2241_p.n_1700_B("source", C_131_O.n_1700_B()).suggests(C_131_O.n_1700_B).then(h_841_W.n_1700_B(parent, Q_2241_p.n_1700_B("sourceObjective", ObjectiveArgument.n_1700_B()), isIf, (CommandContext<y_2498_m> p_201088_0_) -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_201088_0_, (Integer p_229768_0_, Integer p_229768_1_) -> p_229768_0_ >= p_229768_1_)))))).then(Q_2241_p.n_1700_B("matches").then(h_841_W.n_1700_B(parent, Q_2241_p.n_1700_B("range", RangeArgument.n_1700_B()), isIf, (CommandContext<y_2498_m> p_229787_0_) -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_229787_0_, RangeArgument.J_1907_R.n_1700_B((CommandContext<y_2498_m>)p_229787_0_, "range"))))))))).then(Q_2241_p.n_1700_B("blocks").then(Q_2241_p.n_1700_B("start", BlockPosArgument.n_1700_B()).then(Q_2241_p.n_1700_B("end", BlockPosArgument.n_1700_B()).then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("destination", BlockPosArgument.n_1700_B()).then(h_841_W.n_1700_B(parent, Q_2241_p.n_1700_B("all"), isIf, false))).then(h_841_W.n_1700_B(parent, Q_2241_p.n_1700_B("masked"), isIf, true))))))).then(Q_2241_p.n_1700_B("entity").then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("entities", i_4556_r.J_1907_R()).fork(parent, p_229791_1_ -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_229791_1_, isIf, !i_4556_r.R_4764_Y((CommandContext<y_2498_m>)p_229791_1_, "entities").isEmpty()))).executes(h_841_W.n_1700_B(isIf, (CommandContext<y_2498_m> p_229780_0_) -> i_4556_r.R_4764_Y((CommandContext<y_2498_m>)p_229780_0_, "entities").size()))))).then(Q_2241_p.n_1700_B("predicate").then(h_841_W.n_1700_B(parent, Q_2241_p.n_1700_B("predicate", ResourceLocationArgument.n_1700_B()).suggests(P_1922_E), isIf, (CommandContext<y_2498_m> p_229761_0_) -> h_841_W.n_1700_B((y_2498_m)p_229761_0_.getSource(), ResourceLocationArgument.R_4764_Y((CommandContext<y_2498_m>)p_229761_0_, "predicate")))));
        for (z_1856_e.n_1700_B datacommand$idataprovider : z_1856_e.R_4764_Y) {
            literal.then(datacommand$idataprovider.n_1700_B((ArgumentBuilder<y_2498_m, ?>)Q_2241_p.n_1700_B("data"), p_229764_3_ -> p_229764_3_.then(((RequiredArgumentBuilder)Q_2241_p.n_1700_B("path", K_1178_t.n_1700_B()).fork(parent, p_229777_2_ -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_229777_2_, isIf, h_841_W.n_1700_B(datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_229777_2_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_229777_2_, "path")) > 0))).executes(h_841_W.n_1700_B(isIf, (CommandContext<y_2498_m> p_229773_1_) -> h_841_W.n_1700_B(datacommand$idataprovider.n_1700_B((CommandContext<y_2498_m>)p_229773_1_), K_1178_t.n_1700_B((CommandContext<y_2498_m>)p_229773_1_, "path")))))));
        }
        return literal;
    }

    private static Command<y_2498_m> n_1700_B(boolean p_218834_0_, J_1907_R p_218834_1_) {
        return p_218834_0_ ? p_229783_1_ -> {
            int i = p_218834_1_.test((CommandContext<y_2498_m>)p_229783_1_);
            if (i > 0) {
                ((y_2498_m)p_229783_1_.getSource()).n_1700_B(new F_2904_S("commands.execute.conditional.pass_count", i), false);
                return i;
            }
            throw J_1907_R.create();
        } : p_229771_1_ -> {
            int i = p_218834_1_.test((CommandContext<y_2498_m>)p_229771_1_);
            if (i == 0) {
                ((y_2498_m)p_229771_1_.getSource()).n_1700_B(new F_2904_S("commands.execute.conditional.pass"), false);
                return 1;
            }
            throw R_4764_Y.create((Object)i);
        };
    }

    private static int n_1700_B(DataAccessor p_218831_0_, K_1178_t.v_4262_N p_218831_1_) throws CommandSyntaxException {
        return p_218831_1_.J_1907_R(p_218831_0_.n_1700_B());
    }

    private static boolean n_1700_B(CommandContext<y_2498_m> context, BiPredicate<Integer, Integer> comparison) throws CommandSyntaxException {
        String s = C_131_O.n_1700_B(context, "target");
        Objective scoreobjective = ObjectiveArgument.n_1700_B(context, "targetObjective");
        String s1 = C_131_O.n_1700_B(context, "source");
        Objective scoreobjective1 = ObjectiveArgument.n_1700_B(context, "sourceObjective");
        ServerScoreboard scoreboard = ((y_2498_m)context.getSource()).w_1457_N().S_4022_R();
        if (scoreboard.n_1700_B(s, scoreobjective) && scoreboard.n_1700_B(s1, scoreobjective1)) {
            v_4839_y score = scoreboard.J_1907_R(s, scoreobjective);
            v_4839_y score1 = scoreboard.J_1907_R(s1, scoreobjective1);
            return comparison.test(score.J_1907_R(), score1.J_1907_R());
        }
        return false;
    }

    private static boolean n_1700_B(CommandContext<y_2498_m> context, MinMaxBounds.G_564_y bounds) throws CommandSyntaxException {
        String s = C_131_O.n_1700_B(context, "target");
        Objective scoreobjective = ObjectiveArgument.n_1700_B(context, "targetObjective");
        ServerScoreboard scoreboard = ((y_2498_m)context.getSource()).w_1457_N().S_4022_R();
        return !scoreboard.n_1700_B(s, scoreobjective) ? false : bounds.R_4764_Y(scoreboard.J_1907_R(s, scoreobjective).J_1907_R());
    }

    private static boolean n_1700_B(y_2498_m p_229767_0_, LootItemCondition p_229767_1_) {
        e_3591_l serverworld = p_229767_0_.h_1847_R();
        q_1704_m.n_1700_B lootcontext$builder = new q_1704_m.n_1700_B(serverworld).n_1700_B(LootContextParams.u_1723_Y, p_229767_0_.P_4830_p()).J_1907_R(LootContextParams.n_1700_B, p_229767_0_.Q_4569_t());
        return p_229767_1_.test(lootcontext$builder.n_1700_B(f_1402_I.R_4764_Y));
    }

    private static Collection<y_2498_m> n_1700_B(CommandContext<y_2498_m> context, boolean actual, boolean expected) {
        return expected == actual ? Collections.singleton((y_2498_m)context.getSource()) : Collections.emptyList();
    }

    private static ArgumentBuilder<y_2498_m, ?> n_1700_B(CommandNode<y_2498_m> context, ArgumentBuilder<y_2498_m, ?> builder, boolean value, n_1700_B test) {
        return builder.fork(context, p_229786_2_ -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_229786_2_, value, test.test((CommandContext<y_2498_m>)p_229786_2_))).executes(p_229776_2_ -> {
            if (value == test.test((CommandContext<y_2498_m>)p_229776_2_)) {
                ((y_2498_m)p_229776_2_.getSource()).n_1700_B(new F_2904_S("commands.execute.conditional.pass"), false);
                return 1;
            }
            throw J_1907_R.create();
        });
    }

    private static ArgumentBuilder<y_2498_m, ?> n_1700_B(CommandNode<y_2498_m> parent, ArgumentBuilder<y_2498_m, ?> literal, boolean isIf, boolean isMasked) {
        return literal.fork(parent, p_229778_2_ -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_229778_2_, isIf, h_841_W.R_4764_Y((CommandContext<y_2498_m>)p_229778_2_, isMasked).isPresent())).executes(isIf ? p_229785_1_ -> h_841_W.n_1700_B((CommandContext<y_2498_m>)p_229785_1_, isMasked) : p_229775_1_ -> h_841_W.J_1907_R((CommandContext<y_2498_m>)p_229775_1_, isMasked));
    }

    private static int n_1700_B(CommandContext<y_2498_m> context, boolean isMasked) throws CommandSyntaxException {
        OptionalInt optionalint = h_841_W.R_4764_Y(context, isMasked);
        if (optionalint.isPresent()) {
            ((y_2498_m)context.getSource()).n_1700_B(new F_2904_S("commands.execute.conditional.pass_count", optionalint.getAsInt()), false);
            return optionalint.getAsInt();
        }
        throw J_1907_R.create();
    }

    private static int J_1907_R(CommandContext<y_2498_m> context, boolean isMasked) throws CommandSyntaxException {
        OptionalInt optionalint = h_841_W.R_4764_Y(context, isMasked);
        if (optionalint.isPresent()) {
            throw R_4764_Y.create((Object)optionalint.getAsInt());
        }
        ((y_2498_m)context.getSource()).n_1700_B(new F_2904_S("commands.execute.conditional.pass"), false);
        return 1;
    }

    private static OptionalInt R_4764_Y(CommandContext<y_2498_m> context, boolean isMasked) throws CommandSyntaxException {
        return h_841_W.n_1700_B(((y_2498_m)context.getSource()).h_1847_R(), BlockPosArgument.n_1700_B(context, "start"), BlockPosArgument.n_1700_B(context, "end"), BlockPosArgument.n_1700_B(context, "destination"), isMasked);
    }

    private static OptionalInt n_1700_B(e_3591_l worldIn, c_1514_x begin, c_1514_x end, c_1514_x destination, boolean isMasked) throws CommandSyntaxException {
        BoundingBox mutableboundingbox = new BoundingBox(begin, end);
        BoundingBox mutableboundingbox1 = new BoundingBox(destination, destination.add(mutableboundingbox.R_4764_Y()));
        c_1514_x blockpos = new c_1514_x(mutableboundingbox1.n_1700_B - mutableboundingbox.n_1700_B, mutableboundingbox1.J_1907_R - mutableboundingbox.J_1907_R, mutableboundingbox1.R_4764_Y - mutableboundingbox.R_4764_Y);
        int i = mutableboundingbox.G_564_y() * mutableboundingbox.P_1922_E() * mutableboundingbox.u_1723_Y();
        if (i > 32768) {
            throw n_1700_B.create((Object)32768, (Object)i);
        }
        int j = 0;
        for (int k = mutableboundingbox.R_4764_Y; k <= mutableboundingbox.u_1723_Y; ++k) {
            for (int l = mutableboundingbox.J_1907_R; l <= mutableboundingbox.P_1922_E; ++l) {
                for (int i1 = mutableboundingbox.n_1700_B; i1 <= mutableboundingbox.G_564_y; ++i1) {
                    c_1514_x blockpos1 = new c_1514_x(i1, l, k);
                    c_1514_x blockpos2 = blockpos1.add(blockpos);
                    K_4074_S blockstate = worldIn.getBlockState(blockpos1);
                    if (isMasked && blockstate.n_1700_B(a_3742_W.n_1700_B)) continue;
                    if (blockstate != worldIn.getBlockState(blockpos2)) {
                        return OptionalInt.empty();
                    }
                    i_2154_H tileentity = worldIn.getTileEntity(blockpos1);
                    i_2154_H tileentity1 = worldIn.getTileEntity(blockpos2);
                    if (tileentity != null) {
                        if (tileentity1 == null) {
                            return OptionalInt.empty();
                        }
                        U_2912_j compoundnbt = tileentity.n_1700_B(new U_2912_j());
                        compoundnbt.multiplayerClientSuggestionProvider("x");
                        compoundnbt.multiplayerClientSuggestionProvider("y");
                        compoundnbt.multiplayerClientSuggestionProvider("z");
                        U_2912_j compoundnbt1 = tileentity1.n_1700_B(new U_2912_j());
                        compoundnbt1.multiplayerClientSuggestionProvider("x");
                        compoundnbt1.multiplayerClientSuggestionProvider("y");
                        compoundnbt1.multiplayerClientSuggestionProvider("z");
                        if (!compoundnbt.equals(compoundnbt1)) {
                            return OptionalInt.empty();
                        }
                    }
                    ++j;
                }
            }
        }
        return OptionalInt.of(j);
    }

    @FunctionalInterface
    static interface n_1700_B {
        public boolean test(CommandContext<y_2498_m> var1) throws CommandSyntaxException;
    }

    @FunctionalInterface
    static interface J_1907_R {
        public int test(CommandContext<y_2498_m> var1) throws CommandSyntaxException;
    }
}


