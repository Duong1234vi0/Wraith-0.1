/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
import lightning.product.LocalCoordinates;
import lightning.product.Q_2241_p;
import lightning.product.V_4217_p;
import lightning.product.WorldCoordinates;
import lightning.product.Coordinates;
import lightning.product.e_2866_D;
import lightning.product.y_2498_m;

public class u_1579_Y
implements ArgumentType<Coordinates> {
    private static final Collection<String> R_4764_Y = Arrays.asList("0 0 0", "~ ~ ~", "^ ^ ^", "^1 ^ ^-5", "0.1 -0.5 .9", "~0.5 ~1 ~-5");
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.pos3d.incomplete"));
    public static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("argument.pos.mixed"));
    private final boolean G_564_y;

    public u_1579_Y(boolean centerIntegersIn) {
        this.G_564_y = centerIntegersIn;
    }

    public static u_1579_Y n_1700_B() {
        return new u_1579_Y(true);
    }

    public static u_1579_Y n_1700_B(boolean centerIntegersIn) {
        return new u_1579_Y(centerIntegersIn);
    }

    public static e_2866_D n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return ((Coordinates)context.getArgument(name, Coordinates.class)).n_1700_B((y_2498_m)context.getSource());
    }

    public static Coordinates J_1907_R(CommandContext<y_2498_m> context, String name) {
        return (Coordinates)context.getArgument(name, Coordinates.class);
    }

    public Coordinates n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        return p_parse_1_.canRead() && p_parse_1_.peek() == '^' ? LocalCoordinates.n_1700_B(p_parse_1_) : WorldCoordinates.n_1700_B(p_parse_1_, this.G_564_y);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        if (!(p_listSuggestions_1_.getSource() instanceof V_4217_p)) {
            return Suggestions.empty();
        }
        String s = p_listSuggestions_2_.getRemaining();
        Collection<V_4217_p.n_1700_B> collection = !s.isEmpty() && s.charAt(0) == '^' ? Collections.singleton(V_4217_p.n_1700_B.n_1700_B) : ((V_4217_p)p_listSuggestions_1_.getSource()).v_4262_N();
        return V_4217_p.n_1700_B(s, collection, p_listSuggestions_2_, Q_2241_p.n_1700_B(this::n_1700_B));
    }

    public Collection<String> getExamples() {
        return R_4764_Y;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


