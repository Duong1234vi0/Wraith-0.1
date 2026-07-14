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
import lightning.product.PlayerTeam;
import lightning.product.ServerScoreboard;
import lightning.product.y_2498_m;

public class Q_2106_h
implements ArgumentType<String> {
    private static final Collection<String> n_1700_B = Arrays.asList("foo", "123");
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208680_0_ -> new F_2904_S("team.notFound", p_208680_0_));

    public static Q_2106_h n_1700_B() {
        return new Q_2106_h();
    }

    public static PlayerTeam n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        String s = (String)context.getArgument(name, String.class);
        ServerScoreboard scoreboard = ((y_2498_m)context.getSource()).w_1457_N().S_4022_R();
        PlayerTeam scoreplayerteam = scoreboard.P_1922_E(s);
        if (scoreplayerteam == null) {
            throw J_1907_R.create((Object)s);
        }
        return scoreplayerteam;
    }

    public String n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        return p_parse_1_.readUnquotedString();
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return p_listSuggestions_1_.getSource() instanceof V_4217_p ? V_4217_p.J_1907_R(((V_4217_p)p_listSuggestions_1_.getSource()).R_4764_Y(), p_listSuggestions_2_) : Suggestions.empty();
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


