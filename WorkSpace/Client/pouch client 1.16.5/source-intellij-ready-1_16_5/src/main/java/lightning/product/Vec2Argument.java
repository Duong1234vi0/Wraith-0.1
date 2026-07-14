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
import lightning.product.P_3504_Q;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.WorldCoordinates;
import lightning.product.Coordinates;
import lightning.product.e_2866_D;
import lightning.product.y_2498_m;

public class Vec2Argument
implements ArgumentType<Coordinates> {
    private static final Collection<String> J_1907_R = Arrays.asList("0 0", "~ ~", "0.1 -0.5", "~1 ~-2");
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.pos2d.incomplete"));
    private final boolean R_4764_Y;

    public Vec2Argument(boolean centerIntegersIn) {
        this.R_4764_Y = centerIntegersIn;
    }

    public static Vec2Argument n_1700_B() {
        return new Vec2Argument(true);
    }

    public static P_3504_Q n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        e_2866_D vector3d = ((Coordinates)context.getArgument(name, Coordinates.class)).n_1700_B((y_2498_m)context.getSource());
        return new P_3504_Q((float)vector3d.J_1907_R, (float)vector3d.G_564_y);
    }

    public Coordinates n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        int i = p_parse_1_.getCursor();
        if (!p_parse_1_.canRead()) {
            throw n_1700_B.createWithContext((ImmutableStringReader)p_parse_1_);
        }
        WorldCoordinate locationpart = WorldCoordinate.n_1700_B(p_parse_1_, this.R_4764_Y);
        if (p_parse_1_.canRead() && p_parse_1_.peek() == ' ') {
            p_parse_1_.skip();
            WorldCoordinate locationpart1 = WorldCoordinate.n_1700_B(p_parse_1_, this.R_4764_Y);
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
        Collection<V_4217_p.n_1700_B> collection = !s.isEmpty() && s.charAt(0) == '^' ? Collections.singleton(V_4217_p.n_1700_B.n_1700_B) : ((V_4217_p)p_listSuggestions_1_.getSource()).v_4262_N();
        return V_4217_p.J_1907_R(s, collection, p_listSuggestions_2_, Q_2241_p.n_1700_B(this::n_1700_B));
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


