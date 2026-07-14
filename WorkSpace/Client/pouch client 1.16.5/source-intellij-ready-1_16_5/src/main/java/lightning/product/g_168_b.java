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
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.F_2904_S;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.y_2498_m;

public class g_168_b
implements ArgumentType<g_2336_b> {
    private static final Collection<String> n_1700_B = Stream.of(b_4507_u.u_1723_Y, b_4507_u.v_4262_N).map(worldKey -> worldKey.n_1700_B().toString()).collect(Collectors.toList());
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(worldKey -> new F_2904_S("argument.dimension.invalid", worldKey));

    public g_2336_b n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        return g_2336_b.n_1700_B(p_parse_1_);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return p_listSuggestions_1_.getSource() instanceof V_4217_p ? V_4217_p.n_1700_B(((V_4217_p)p_listSuggestions_1_.getSource()).w_1484_f().stream().map(f_2392_k::n_1700_B), p_listSuggestions_2_) : Suggestions.empty();
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public static g_168_b n_1700_B() {
        return new g_168_b();
    }

    public static e_3591_l n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        g_2336_b resourcelocation = (g_2336_b)context.getArgument(name, g_2336_b.class);
        f_2392_k<b_4507_u> registrykey = f_2392_k.n_1700_B(V_3137_a.z_1737_N, resourcelocation);
        e_3591_l serverworld = ((y_2498_m)context.getSource()).w_1457_N().n_1700_B(registrykey);
        if (serverworld == null) {
            throw J_1907_R.create((Object)resourcelocation);
        }
        return serverworld;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}

