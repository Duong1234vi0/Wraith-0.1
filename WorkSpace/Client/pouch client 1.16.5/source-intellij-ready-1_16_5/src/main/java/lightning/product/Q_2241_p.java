/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.ArgumentBuilder
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 *  com.mojang.brigadier.tree.CommandNode
 *  com.mojang.brigadier.tree.RootCommandNode
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.A_4621_h;
import lightning.product.GiveCommand;
import lightning.product.A_723_Z;
import lightning.product.A_958_X;
import lightning.product.B_3790_C;
import lightning.product.B_4088_l;
import lightning.product.ClearInventoryCommands;
import lightning.product.SetPlayerIdleTimeoutCommand;
import lightning.product.PlaySoundCommand;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.DataPackCommand;
import lightning.product.MutableComponent;
import lightning.product.PublishCommand;
import lightning.product.SharedConstants;
import lightning.product.J_2586_d;
import lightning.product.PardonCommand;
import lightning.product.L_2332_A;
import lightning.product.M_3778_G;
import lightning.product.N_927_Q;
import lightning.product.BanPlayerCommands;
import lightning.product.ReplaceItemCommand;
import lightning.product.DeOpCommands;
import lightning.product.SaveOnCommand;
import lightning.product.DifficultyCommand;
import lightning.product.MsgCommand;
import lightning.product.S_1016_k;
import lightning.product.FunctionCommand;
import lightning.product.SeedCommand;
import lightning.product.TimeCommand;
import lightning.product.LocateBiomeCommand;
import lightning.product.TeamMsgCommand;
import lightning.product.U_2871_b;
import lightning.product.V_3164_a;
import lightning.product.V_4217_p;
import lightning.product.KillCommand;
import lightning.product.EffectCommands;
import lightning.product.Z_4195_o;
import lightning.product.a_117_l;
import lightning.product.a_3139_m;
import lightning.product.DefaultGameModeCommands;
import lightning.product.c_973_a;
import lightning.product.d_2699_v;
import lightning.product.d_3113_t;
import lightning.product.d_360_y;
import lightning.product.ListPlayersCommand;
import lightning.product.e_3847_T;
import lightning.product.KickCommand;
import lightning.product.h_1834_T;
import lightning.product.h_4126_t;
import lightning.product.h_841_W;
import lightning.product.i_2909_p;
import lightning.product.i_4673_G;
import lightning.product.i_918_k;
import lightning.product.StopCommand;
import lightning.product.j_3341_s;
import lightning.product.l_4341_y;
import lightning.product.BanListCommands;
import lightning.product.ReloadCommand;
import lightning.product.SetBlockCommand;
import lightning.product.OpCommand;
import lightning.product.SaveAllCommand;
import lightning.product.o_505_N;
import lightning.product.SaveOffCommand;
import lightning.product.EnchantCommand;
import lightning.product.AttributeCommand;
import lightning.product.SayCommand;
import lightning.product.r_1709_F;
import lightning.product.CloneCommands;
import lightning.product.RecipeCommand;
import lightning.product.SetSpawnCommand;
import lightning.product.t_1690_w;
import lightning.product.t_4308_H;
import lightning.product.u_2362_t;
import lightning.product.TellRawCommand;
import lightning.product.TriggerCommand;
import lightning.product.WeatherCommand;
import lightning.product.EmoteCommands;
import lightning.product.SetWorldSpawnCommand;
import lightning.product.w_3933_U;
import lightning.product.ComponentUtils;
import lightning.product.ParticleCommand;
import lightning.product.TagCommand;
import lightning.product.PardonIpCommand;
import lightning.product.y_2498_m;
import lightning.product.z_1856_e;
import mods.voicechat.eventforge.RegisterCommandsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Q_2241_p {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final CommandDispatcher<y_2498_m> J_1907_R = new CommandDispatcher();

    public Q_2241_p(n_1700_B envType) {
        J_2586_d.n_1700_B(this.J_1907_R);
        AttributeCommand.n_1700_B(this.J_1907_R);
        h_841_W.n_1700_B(this.J_1907_R);
        Z_4195_o.n_1700_B(this.J_1907_R);
        ClearInventoryCommands.n_1700_B(this.J_1907_R);
        CloneCommands.n_1700_B(this.J_1907_R);
        z_1856_e.n_1700_B(this.J_1907_R);
        DataPackCommand.n_1700_B(this.J_1907_R);
        L_2332_A.n_1700_B(this.J_1907_R);
        DefaultGameModeCommands.n_1700_B(this.J_1907_R);
        DifficultyCommand.n_1700_B(this.J_1907_R);
        EffectCommands.n_1700_B(this.J_1907_R);
        EmoteCommands.n_1700_B(this.J_1907_R);
        EnchantCommand.n_1700_B(this.J_1907_R);
        A_4621_h.n_1700_B(this.J_1907_R);
        a_3139_m.n_1700_B(this.J_1907_R);
        d_3113_t.n_1700_B(this.J_1907_R);
        FunctionCommand.n_1700_B(this.J_1907_R);
        A_723_Z.n_1700_B(this.J_1907_R);
        i_4673_G.n_1700_B(this.J_1907_R);
        GiveCommand.n_1700_B(this.J_1907_R);
        a_117_l.n_1700_B(this.J_1907_R);
        KickCommand.n_1700_B(this.J_1907_R);
        KillCommand.n_1700_B(this.J_1907_R);
        ListPlayersCommand.n_1700_B(this.J_1907_R);
        e_3847_T.n_1700_B(this.J_1907_R);
        LocateBiomeCommand.n_1700_B(this.J_1907_R);
        h_1834_T.n_1700_B(this.J_1907_R);
        MsgCommand.n_1700_B(this.J_1907_R);
        ParticleCommand.n_1700_B(this.J_1907_R);
        PlaySoundCommand.n_1700_B(this.J_1907_R);
        ReloadCommand.n_1700_B(this.J_1907_R);
        RecipeCommand.n_1700_B(this.J_1907_R);
        ReplaceItemCommand.n_1700_B(this.J_1907_R);
        SayCommand.n_1700_B(this.J_1907_R);
        u_2362_t.n_1700_B(this.J_1907_R);
        d_2699_v.n_1700_B(this.J_1907_R);
        SeedCommand.n_1700_B(this.J_1907_R, envType != lightning.product.Q_2241_p$n_1700_B.R_4764_Y);
        SetBlockCommand.n_1700_B(this.J_1907_R);
        SetSpawnCommand.n_1700_B(this.J_1907_R);
        SetWorldSpawnCommand.n_1700_B(this.J_1907_R);
        S_1016_k.n_1700_B(this.J_1907_R);
        M_3778_G.n_1700_B(this.J_1907_R);
        l_4341_y.n_1700_B(this.J_1907_R);
        o_505_N.n_1700_B(this.J_1907_R);
        TagCommand.n_1700_B(this.J_1907_R);
        N_927_Q.n_1700_B(this.J_1907_R);
        TeamMsgCommand.n_1700_B(this.J_1907_R);
        t_1690_w.n_1700_B(this.J_1907_R);
        TellRawCommand.n_1700_B(this.J_1907_R);
        TimeCommand.n_1700_B(this.J_1907_R);
        i_918_k.n_1700_B(this.J_1907_R);
        TriggerCommand.n_1700_B(this.J_1907_R);
        WeatherCommand.n_1700_B(this.J_1907_R);
        t_4308_H.n_1700_B(this.J_1907_R);
        if (SharedConstants.G_564_y) {
            w_3933_U.n_1700_B(this.J_1907_R);
        }
        if (envType.P_1922_E) {
            d_360_y.n_1700_B(this.J_1907_R);
            BanListCommands.n_1700_B(this.J_1907_R);
            BanPlayerCommands.n_1700_B(this.J_1907_R);
            DeOpCommands.n_1700_B(this.J_1907_R);
            OpCommand.n_1700_B(this.J_1907_R);
            PardonCommand.n_1700_B(this.J_1907_R);
            PardonIpCommand.n_1700_B(this.J_1907_R);
            SaveAllCommand.n_1700_B(this.J_1907_R);
            SaveOffCommand.n_1700_B(this.J_1907_R);
            SaveOnCommand.n_1700_B(this.J_1907_R);
            SetPlayerIdleTimeoutCommand.n_1700_B(this.J_1907_R);
            StopCommand.n_1700_B(this.J_1907_R);
            r_1709_F.n_1700_B(this.J_1907_R);
        }
        if (envType.G_564_y) {
            PublishCommand.n_1700_B(this.J_1907_R);
        }
        A_4115_X.n_1700_B(new RegisterCommandsEvent(this.J_1907_R, envType));
        this.J_1907_R.findAmbiguities((p_201302_1_, p_201302_2_, p_201302_3_, p_201302_4_) -> n_1700_B.warn("Ambiguity between arguments {} and {} with inputs: {}", (Object)this.J_1907_R.getPath(p_201302_2_), (Object)this.J_1907_R.getPath(p_201302_3_), (Object)p_201302_4_));
        this.J_1907_R.setConsumer((p_197058_0_, p_197058_1_, p_197058_2_) -> ((y_2498_m)p_197058_0_.getSource()).n_1700_B((CommandContext<y_2498_m>)p_197058_0_, p_197058_1_, p_197058_2_));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int n_1700_B(y_2498_m source, String command) {
        int n;
        StringReader stringreader = new StringReader(command);
        if (stringreader.canRead() && stringreader.peek() == '/') {
            stringreader.skip();
        }
        source.w_1457_N().LongRunningTask().n_1700_B(command);
        try {
            int n2 = this.J_1907_R.execute(stringreader, (Object)source);
            return n2;
        }
        catch (V_3164_a commandexception) {
            source.n_1700_B(commandexception.n_1700_B());
            int n3 = 0;
            return n3;
        }
        catch (CommandSyntaxException commandsyntaxexception) {
            source.n_1700_B(ComponentUtils.n_1700_B(commandsyntaxexception.getRawMessage()));
            if (commandsyntaxexception.getInput() != null && commandsyntaxexception.getCursor() >= 0) {
                int j = Math.min(commandsyntaxexception.getInput().length(), commandsyntaxexception.getCursor());
                MutableComponent iformattabletextcomponent1 = new U_2871_b("").n_1700_B(D_4024_W.w_1484_f).n_1700_B(p_211705_1_ -> p_211705_1_.n_1700_B(new i_2909_p(i_2909_p.n_1700_B.G_564_y, command)));
                if (j > 10) {
                    iformattabletextcomponent1.n_1700_B("...");
                }
                iformattabletextcomponent1.n_1700_B(commandsyntaxexception.getInput().substring(Math.max(0, j - 10), j));
                if (j < commandsyntaxexception.getInput().length()) {
                    MutableComponent itextcomponent = new U_2871_b(commandsyntaxexception.getInput().substring(j)).n_1700_B(D_4024_W.P_4830_p, D_4024_W.Y_601_j);
                    iformattabletextcomponent1.n_1700_B(itextcomponent);
                }
                iformattabletextcomponent1.n_1700_B(new F_2904_S("command.context.here").n_1700_B(D_4024_W.P_4830_p, D_4024_W.Y_259_p));
                source.n_1700_B(iformattabletextcomponent1);
            }
        }
        catch (Exception exception) {
            U_2871_b iformattabletextcomponent = new U_2871_b(exception.getMessage() == null ? exception.getClass().getName() : exception.getMessage());
            if (n_1700_B.isDebugEnabled()) {
                n_1700_B.error("Command exception: {}", (Object)command, (Object)exception);
                StackTraceElement[] astacktraceelement = exception.getStackTrace();
                for (int i = 0; i < Math.min(astacktraceelement.length, 3); ++i) {
                    iformattabletextcomponent.n_1700_B("\n\n").n_1700_B(astacktraceelement[i].getMethodName()).n_1700_B("\n ").n_1700_B(astacktraceelement[i].getFileName()).n_1700_B(":").n_1700_B(String.valueOf(astacktraceelement[i].getLineNumber()));
                }
            }
            source.n_1700_B(new F_2904_S("command.failed").n_1700_B(p_211704_1_ -> p_211704_1_.n_1700_B(new c_973_a(c_973_a.n_1700_B.n_1700_B, iformattabletextcomponent))));
            if (SharedConstants.G_564_y) {
                source.n_1700_B(new U_2871_b(j_3341_s.G_564_y(exception)));
                n_1700_B.error("'" + command + "' threw an exception", (Throwable)exception);
            }
            int n4 = 0;
            return n4;
        }
        {
            n = 0;
        }
        return n;
        finally {
            source.w_1457_N().LongRunningTask().R_4764_Y();
        }
    }

    public void n_1700_B(B_4088_l player) {
        HashMap map = Maps.newHashMap();
        RootCommandNode rootcommandnode = new RootCommandNode();
        map.put(this.J_1907_R.getRoot(), rootcommandnode);
        this.n_1700_B((CommandNode<y_2498_m>)this.J_1907_R.getRoot(), (CommandNode<V_4217_p>)rootcommandnode, player.A_3244_K(), map);
        player.n_1700_B.n_1700_B(new B_3790_C((RootCommandNode<V_4217_p>)rootcommandnode));
    }

    private void n_1700_B(CommandNode<y_2498_m> rootCommandSource, CommandNode<V_4217_p> rootSuggestion, y_2498_m source, Map<CommandNode<y_2498_m>, CommandNode<V_4217_p>> commandNodeToSuggestionNode) {
        for (CommandNode commandnode : rootCommandSource.getChildren()) {
            RequiredArgumentBuilder requiredargumentbuilder;
            if (!commandnode.canUse((Object)source)) continue;
            ArgumentBuilder argumentbuilder = commandnode.createBuilder();
            argumentbuilder.requires(p_197060_0_ -> true);
            if (argumentbuilder.getCommand() != null) {
                argumentbuilder.executes(p_197053_0_ -> 0);
            }
            if (argumentbuilder instanceof RequiredArgumentBuilder && (requiredargumentbuilder = (RequiredArgumentBuilder)argumentbuilder).getSuggestionsProvider() != null) {
                requiredargumentbuilder.suggests(h_4126_t.J_1907_R((SuggestionProvider<V_4217_p>)requiredargumentbuilder.getSuggestionsProvider()));
            }
            if (argumentbuilder.getRedirect() != null) {
                argumentbuilder.redirect(commandNodeToSuggestionNode.get(argumentbuilder.getRedirect()));
            }
            CommandNode commandnode1 = argumentbuilder.build();
            commandNodeToSuggestionNode.put((CommandNode<y_2498_m>)commandnode, (CommandNode<V_4217_p>)commandnode1);
            rootSuggestion.addChild(commandnode1);
            if (commandnode.getChildren().isEmpty()) continue;
            this.n_1700_B((CommandNode<y_2498_m>)commandnode, (CommandNode<V_4217_p>)commandnode1, source, commandNodeToSuggestionNode);
        }
    }

    public static LiteralArgumentBuilder<y_2498_m> n_1700_B(String name) {
        return LiteralArgumentBuilder.literal((String)name);
    }

    public static <T> RequiredArgumentBuilder<y_2498_m, T> n_1700_B(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument((String)name, type);
    }

    public static Predicate<String> n_1700_B(J_1907_R parser) {
        return p_212591_1_ -> {
            try {
                parser.parse(new StringReader(p_212591_1_));
                return true;
            }
            catch (CommandSyntaxException commandsyntaxexception) {
                return false;
            }
        };
    }

    public CommandDispatcher<y_2498_m> n_1700_B() {
        return this.J_1907_R;
    }

    @Nullable
    public static <S> CommandSyntaxException n_1700_B(ParseResults<S> p_227481_0_) {
        if (!p_227481_0_.getReader().canRead()) {
            return null;
        }
        if (p_227481_0_.getExceptions().size() == 1) {
            return (CommandSyntaxException)((Object)p_227481_0_.getExceptions().values().iterator().next());
        }
        return p_227481_0_.getContext().getRange().isEmpty() ? CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownCommand().createWithContext(p_227481_0_.getReader()) : CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().createWithContext(p_227481_0_.getReader());
    }

    public static void J_1907_R() {
        RootCommandNode rootcommandnode = new Q_2241_p(lightning.product.Q_2241_p$n_1700_B.n_1700_B).n_1700_B().getRoot();
        Set<ArgumentType<?>> set = A_958_X.n_1700_B(rootcommandnode);
        Set set1 = set.stream().filter(p_242987_0_ -> !A_958_X.n_1700_B(p_242987_0_)).collect(Collectors.toSet());
        if (!set1.isEmpty()) {
            n_1700_B.warn("Missing type registration for following arguments:\n {}", (Object)set1.stream().map(p_242985_0_ -> "\t" + String.valueOf(p_242985_0_)).collect(Collectors.joining(",\n")));
            throw new IllegalStateException("Unregistered argument types");
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(true, true);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(false, true);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(true, false);
        private final boolean G_564_y;
        private final boolean P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(boolean p_i232149_3_, boolean p_i232149_4_) {
            this.G_564_y = p_i232149_3_;
            this.P_1922_E = p_i232149_4_;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            u_1723_Y = lightning.product.Q_2241_p$n_1700_B.n_1700_B();
        }
    }

    @FunctionalInterface
    public static interface J_1907_R {
        public void parse(StringReader var1) throws CommandSyntaxException;
    }
}


