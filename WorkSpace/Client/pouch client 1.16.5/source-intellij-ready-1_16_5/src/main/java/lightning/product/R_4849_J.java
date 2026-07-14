/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandExceptionType
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandExceptionType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import lightning.product.TestFunction;
import lightning.product.U_2871_b;
import lightning.product.V_4217_p;
import lightning.product.u_3096_I;
import lightning.product.y_2498_m;

public class R_4849_J
implements ArgumentType<TestFunction> {
    private static final Collection<String> n_1700_B = Arrays.asList("techtests.piston", "techtests");

    public TestFunction n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        String s = p_parse_1_.readUnquotedString();
        Optional<TestFunction> optional = u_3096_I.G_564_y(s);
        if (optional.isPresent()) {
            return optional.get();
        }
        U_2871_b message = new U_2871_b("No such test: " + s);
        throw new CommandSyntaxException((CommandExceptionType)new SimpleCommandExceptionType((Message)message), (Message)message);
    }

    public static R_4849_J n_1700_B() {
        return new R_4849_J();
    }

    public static TestFunction n_1700_B(CommandContext<y_2498_m> p_229666_0_, String p_229666_1_) {
        return (TestFunction)p_229666_0_.getArgument(p_229666_1_, TestFunction.class);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        Stream<String> stream = u_3096_I.n_1700_B().stream().map(TestFunction::n_1700_B);
        return V_4217_p.J_1907_R(stream, p_listSuggestions_2_);
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


