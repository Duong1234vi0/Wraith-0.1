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
import lightning.product.ParticleOptions;
import lightning.product.V_3137_a;
import lightning.product.V_4217_p;
import lightning.product.g_2336_b;
import lightning.product.ParticleType;
import lightning.product.y_2498_m;

public class ParticleArgument
implements ArgumentType<ParticleOptions> {
    private static final Collection<String> J_1907_R = Arrays.asList("foo", "foo:bar", "particle with options");
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(particle -> new F_2904_S("particle.notFound", particle));

    public static ParticleArgument n_1700_B() {
        return new ParticleArgument();
    }

    public static ParticleOptions n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (ParticleOptions)context.getArgument(name, ParticleOptions.class);
    }

    public ParticleOptions n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        return ParticleArgument.J_1907_R(p_parse_1_);
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public static ParticleOptions J_1907_R(StringReader reader) throws CommandSyntaxException {
        g_2336_b resourcelocation = g_2336_b.n_1700_B(reader);
        ParticleType<?> particletype = V_3137_a.g_164_R.J_1907_R(resourcelocation).orElseThrow(() -> n_1700_B.create((Object)resourcelocation));
        return ParticleArgument.n_1700_B(reader, particletype);
    }

    private static <T extends ParticleOptions> T n_1700_B(StringReader reader, ParticleType<T> type) throws CommandSyntaxException {
        return type.u_1723_Y().J_1907_R(type, reader);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return V_4217_p.n_1700_B(V_3137_a.g_164_R.G_564_y(), p_listSuggestions_2_);
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


