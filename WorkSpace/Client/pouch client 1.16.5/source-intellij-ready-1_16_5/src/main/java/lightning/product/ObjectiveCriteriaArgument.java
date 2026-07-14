/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lightning.product.F_2904_S;
import lightning.product.M_1462_J;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.o_98_P;
import lightning.product.q_3277_O;
import lightning.product.y_2498_m;

public class ObjectiveCriteriaArgument
implements ArgumentType<M_1462_J> {
    private static final Collection<String> J_1907_R = Arrays.asList("foo", "foo.bar.baz", "minecraft:foo");
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(p_208672_0_ -> new F_2904_S("argument.criteria.invalid", p_208672_0_));

    private ObjectiveCriteriaArgument() {
    }

    public static ObjectiveCriteriaArgument n_1700_B() {
        return new ObjectiveCriteriaArgument();
    }

    public static M_1462_J n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (M_1462_J)context.getArgument(name, M_1462_J.class);
    }

    public M_1462_J n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        int i = p_parse_1_.getCursor();
        while (p_parse_1_.canRead() && p_parse_1_.peek() != ' ') {
            p_parse_1_.skip();
        }
        String s = p_parse_1_.getString().substring(i, p_parse_1_.getCursor());
        return M_1462_J.n_1700_B(s).orElseThrow(() -> {
            p_parse_1_.setCursor(i);
            return n_1700_B.create((Object)s);
        });
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        ArrayList list = Lists.newArrayList(M_1462_J.n_1700_B.keySet());
        for (q_3277_O q_3277_O2 : V_3137_a.z_1333_t) {
            for (Object object : q_3277_O2.n_1700_B()) {
                String s = this.n_1700_B(q_3277_O2, object);
                list.add(s);
            }
        }
        return V_4217_p.J_1907_R(list, p_listSuggestions_2_);
    }

    public <T> String n_1700_B(q_3277_O<T> type, Object value) {
        return o_98_P.n_1700_B(type, value);
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


