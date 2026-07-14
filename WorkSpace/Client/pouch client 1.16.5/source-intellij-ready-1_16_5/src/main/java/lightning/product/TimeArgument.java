/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 */
package lightning.product;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lightning.product.F_2904_S;
import lightning.product.V_4217_p;

public class TimeArgument
implements ArgumentType<Integer> {
    private static final Collection<String> n_1700_B = Arrays.asList("0d", "0s", "0t", "0");
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("argument.time.invalid_unit"));
    private static final DynamicCommandExceptionType R_4764_Y = new DynamicCommandExceptionType(p_218092_0_ -> new F_2904_S("argument.time.invalid_tick_count", p_218092_0_));
    private static final Object2IntMap<String> G_564_y = new Object2IntOpenHashMap();

    public static TimeArgument n_1700_B() {
        return new TimeArgument();
    }

    public Integer n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        float f = p_parse_1_.readFloat();
        String s = p_parse_1_.readUnquotedString();
        int i = G_564_y.getOrDefault((Object)s, 0);
        if (i == 0) {
            throw J_1907_R.create();
        }
        int j = Math.round(f * (float)i);
        if (j < 0) {
            throw R_4764_Y.create((Object)j);
        }
        return j;
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        StringReader stringreader = new StringReader(p_listSuggestions_2_.getRemaining());
        try {
            stringreader.readFloat();
        }
        catch (CommandSyntaxException commandsyntaxexception) {
            return p_listSuggestions_2_.buildFuture();
        }
        return V_4217_p.J_1907_R((Iterable<String>)G_564_y.keySet(), p_listSuggestions_2_.createOffset(p_listSuggestions_2_.getStart() + stringreader.getCursor()));
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    static {
        G_564_y.put((Object)"d", 24000);
        G_564_y.put((Object)"s", 20);
        G_564_y.put((Object)"t", 1);
        G_564_y.put((Object)"", 1);
    }
}


