/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lightning.product.F_2904_S;
import lightning.product.V_4217_p;
import lightning.product.Objective;
import lightning.product.ServerScoreboard;
import lightning.product.y_2498_m;

public class ObjectiveArgument
implements ArgumentType<String> {
    private static final Collection<String> J_1907_R = Arrays.asList("foo", "*", "012");
    private static final DynamicCommandExceptionType R_4764_Y = new DynamicCommandExceptionType(p_208671_0_ -> new F_2904_S("arguments.objective.notFound", p_208671_0_));
    private static final DynamicCommandExceptionType G_564_y = new DynamicCommandExceptionType(p_208669_0_ -> new F_2904_S("arguments.objective.readonly", p_208669_0_));
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(p_208670_0_ -> new F_2904_S("commands.scoreboard.objectives.add.longName", p_208670_0_));

    public static ObjectiveArgument n_1700_B() {
        return new ObjectiveArgument();
    }

    public static Objective n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        String s = (String)context.getArgument(name, String.class);
        ServerScoreboard scoreboard = ((y_2498_m)context.getSource()).w_1457_N().S_4022_R();
        Objective scoreobjective = scoreboard.R_4764_Y(s);
        if (scoreobjective == null) {
            throw R_4764_Y.create((Object)s);
        }
        return scoreobjective;
    }

    public static Objective J_1907_R(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        Objective scoreobjective = ObjectiveArgument.n_1700_B(context, name);
        if (scoreobjective.R_4764_Y().J_1907_R()) {
            throw G_564_y.create((Object)scoreobjective.J_1907_R());
        }
        return scoreobjective;
    }

    public String n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        String s = p_parse_1_.readUnquotedString();
        if (s.length() > 16) {
            throw n_1700_B.create((Object)16);
        }
        return s;
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        if (p_listSuggestions_1_.getSource() instanceof y_2498_m) {
            return V_4217_p.J_1907_R(((y_2498_m)p_listSuggestions_1_.getSource()).w_1457_N().S_4022_R().J_1907_R(), p_listSuggestions_2_);
        }
        if (p_listSuggestions_1_.getSource() instanceof V_4217_p) {
            V_4217_p isuggestionprovider = (V_4217_p)p_listSuggestions_1_.getSource();
            return isuggestionprovider.n_1700_B(p_listSuggestions_1_, p_listSuggestions_2_);
        }
        return Suggestions.empty();
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


