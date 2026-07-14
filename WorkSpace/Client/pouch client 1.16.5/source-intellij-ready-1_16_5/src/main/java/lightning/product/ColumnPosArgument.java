/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import lightning.product.F_2904_S;
import lightning.product.WorldCoordinate;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.W_2944_a;
import lightning.product.WorldCoordinates;
import lightning.product.Coordinates;
import lightning.product.c_1514_x;
import lightning.product.y_2498_m;

public class ColumnPosArgument
implements ArgumentType<Coordinates> {
    private static final Collection<String> J_1907_R = Arrays.asList("0 0", "~ ~", "~1 ~-2", "^ ^", "^-1 ^0");
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.pos2d.incomplete"));

    public static ColumnPosArgument n_1700_B() {
        return new ColumnPosArgument();
    }

    public static W_2944_a n_1700_B(CommandContext<y_2498_m> context, String name) {
        c_1514_x blockpos = ((Coordinates)context.getArgument(name, Coordinates.class)).R_4764_Y((y_2498_m)context.getSource());
        return new W_2944_a(blockpos.getX(), blockpos.getZ());
    }

    public Coordinates n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        int i = p_parse_1_.getCursor();
        if (!p_parse_1_.canRead()) {
            throw n_1700_B.createWithContext((ImmutableStringReader)p_parse_1_);
        }
        WorldCoordinate locationpart = WorldCoordinate.n_1700_B(p_parse_1_);
        if (p_parse_1_.canRead() && p_parse_1_.peek() == ' ') {
            p_parse_1_.skip();
            WorldCoordinate locationpart1 = WorldCoordinate.n_1700_B(p_parse_1_);
            return new WorldCoordinates(locationpart, new WorldCoordinate(true, 0.0), locationpart1);
        }
        p_parse_1_.setCursor(i);
        throw n_1700_B.createWithContext((ImmutableStringReader)p_parse_1_);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        if (!(p_listSuggestions_1_.getSource() instanceof V_4217_p)) {
            return Suggestions.empty();
        }
        String s = p_listSuggestions_2_.getRemaining();
        Collection<V_4217_p.n_1700_B> collection = !s.isEmpty() && s.charAt(0) == '^' ? Collections.singleton(V_4217_p.n_1700_B.n_1700_B) : ((V_4217_p)p_listSuggestions_1_.getSource()).u_1723_Y();
        return V_4217_p.J_1907_R(s, collection, p_listSuggestions_2_, Q_2241_p.n_1700_B(this::n_1700_B));
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


