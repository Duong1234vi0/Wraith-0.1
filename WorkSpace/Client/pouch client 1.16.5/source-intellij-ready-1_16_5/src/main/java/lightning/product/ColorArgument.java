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
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.V_4217_p;
import lightning.product.y_2498_m;

public class ColorArgument
implements ArgumentType<D_4024_W> {
    private static final Collection<String> J_1907_R = Arrays.asList("red", "green");
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(color -> new F_2904_S("argument.color.invalid", color));

    private ColorArgument() {
    }

    public static ColorArgument n_1700_B() {
        return new ColorArgument();
    }

    public static D_4024_W n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (D_4024_W)((Object)context.getArgument(name, D_4024_W.class));
    }

    public D_4024_W n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        String s = p_parse_1_.readUnquotedString();
        D_4024_W textformatting = D_4024_W.J_1907_R(s);
        if (textformatting != null && !textformatting.J_1907_R()) {
            return textformatting;
        }
        throw n_1700_B.create((Object)s);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return V_4217_p.J_1907_R(D_4024_W.n_1700_B(true, false), p_listSuggestions_2_);
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


