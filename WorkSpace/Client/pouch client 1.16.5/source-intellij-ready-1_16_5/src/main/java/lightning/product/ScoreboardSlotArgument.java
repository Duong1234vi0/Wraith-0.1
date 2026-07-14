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
import lightning.product.i_4895_l;
import lightning.product.y_2498_m;

public class ScoreboardSlotArgument
implements ArgumentType<Integer> {
    private static final Collection<String> J_1907_R = Arrays.asList("sidebar", "foo.bar");
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(p_208678_0_ -> new F_2904_S("argument.scoreboardDisplaySlot.invalid", p_208678_0_));

    private ScoreboardSlotArgument() {
    }

    public static ScoreboardSlotArgument n_1700_B() {
        return new ScoreboardSlotArgument();
    }

    public static int n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (Integer)context.getArgument(name, Integer.class);
    }

    public Integer n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        String s = p_parse_1_.readUnquotedString();
        int i = i_4895_l.s_956_w(s);
        if (i == -1) {
            throw n_1700_B.create((Object)s);
        }
        return i;
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return V_4217_p.n_1700_B(i_4895_l.u_1723_Y(), p_listSuggestions_2_);
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


