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
import lightning.product.K_1310_v;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.g_2336_b;
import lightning.product.y_2498_m;

public class ItemEnchantmentArgument
implements ArgumentType<K_1310_v> {
    private static final Collection<String> J_1907_R = Arrays.asList("unbreaking", "silk_touch");
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(enchantment -> new F_2904_S("enchantment.unknown", enchantment));

    public static ItemEnchantmentArgument n_1700_B() {
        return new ItemEnchantmentArgument();
    }

    public static K_1310_v n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (K_1310_v)context.getArgument(name, K_1310_v.class);
    }

    public K_1310_v n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        g_2336_b resourcelocation = g_2336_b.n_1700_B(p_parse_1_);
        return V_3137_a.z_4693_k.J_1907_R(resourcelocation).orElseThrow(() -> n_1700_B.create((Object)resourcelocation));
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return V_4217_p.n_1700_B(V_3137_a.z_4693_k.G_564_y(), p_listSuggestions_2_);
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


