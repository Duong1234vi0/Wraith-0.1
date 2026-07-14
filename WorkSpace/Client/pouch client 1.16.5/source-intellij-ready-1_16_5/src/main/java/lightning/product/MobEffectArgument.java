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
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.g_2336_b;
import lightning.product.g_422_i;
import lightning.product.y_2498_m;

public class MobEffectArgument
implements ArgumentType<g_422_i> {
    private static final Collection<String> J_1907_R = Arrays.asList("spooky", "effect");
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(effect -> new F_2904_S("effect.effectNotFound", effect));

    public static MobEffectArgument n_1700_B() {
        return new MobEffectArgument();
    }

    public static g_422_i n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return (g_422_i)context.getArgument(name, g_422_i.class);
    }

    public g_422_i n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        g_2336_b resourcelocation = g_2336_b.n_1700_B(p_parse_1_);
        return V_3137_a.T_2506_i.J_1907_R(resourcelocation).orElseThrow(() -> n_1700_B.create((Object)resourcelocation));
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return V_4217_p.n_1700_B(V_3137_a.T_2506_i.G_564_y(), p_listSuggestions_2_);
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


