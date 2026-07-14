/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.gson.JsonObject
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

import com.google.common.collect.Iterables;
import com.google.gson.JsonObject;
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
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.J_2545_z;
import lightning.product.N_4263_v;
import lightning.product.V_4217_p;
import lightning.product.Y_995_C;
import lightning.product.b_2585_i;
import lightning.product.ArgumentSerializer;
import lightning.product.y_2498_m;

public class i_4556_r
implements ArgumentType<Y_995_C> {
    private static final Collection<String> v_4262_N = Arrays.asList("Player", "0123", "@e", "@e[type=foo]", "dd12be42-52a9-4a91-a8a1-11c01849e498");
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.toomany"));
    public static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("argument.player.toomany"));
    public static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("argument.player.entities"));
    public static final SimpleCommandExceptionType G_564_y = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.notfound.entity"));
    public static final SimpleCommandExceptionType P_1922_E = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.notfound.player"));
    public static final SimpleCommandExceptionType u_1723_Y = new SimpleCommandExceptionType((Message)new F_2904_S("argument.entity.selector.not_allowed"));
    private final boolean w_1484_f;
    private final boolean t_148_a;

    protected i_4556_r(boolean singleIn, boolean playersOnlyIn) {
        this.w_1484_f = singleIn;
        this.t_148_a = playersOnlyIn;
    }

    public static i_4556_r n_1700_B() {
        return new i_4556_r(true, false);
    }

    public static N_4263_v n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return ((Y_995_C)context.getArgument(name, Y_995_C.class)).n_1700_B((y_2498_m)context.getSource());
    }

    public static i_4556_r J_1907_R() {
        return new i_4556_r(false, false);
    }

    public static Collection<? extends N_4263_v> J_1907_R(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        Collection<? extends N_4263_v> collection = i_4556_r.R_4764_Y(context, name);
        if (collection.isEmpty()) {
            throw G_564_y.create();
        }
        return collection;
    }

    public static Collection<? extends N_4263_v> R_4764_Y(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return ((Y_995_C)context.getArgument(name, Y_995_C.class)).J_1907_R((y_2498_m)context.getSource());
    }

    public static Collection<B_4088_l> G_564_y(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return ((Y_995_C)context.getArgument(name, Y_995_C.class)).G_564_y((y_2498_m)context.getSource());
    }

    public static i_4556_r R_4764_Y() {
        return new i_4556_r(true, true);
    }

    public static B_4088_l P_1922_E(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return ((Y_995_C)context.getArgument(name, Y_995_C.class)).R_4764_Y((y_2498_m)context.getSource());
    }

    public static i_4556_r G_564_y() {
        return new i_4556_r(false, true);
    }

    public static Collection<B_4088_l> u_1723_Y(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        List<B_4088_l> list = ((Y_995_C)context.getArgument(name, Y_995_C.class)).G_564_y((y_2498_m)context.getSource());
        if (list.isEmpty()) {
            throw P_1922_E.create();
        }
        return list;
    }

    public Y_995_C n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        boolean i = false;
        J_2545_z entityselectorparser = new J_2545_z(p_parse_1_);
        Y_995_C entityselector = entityselectorparser.w_1457_N();
        if (entityselector.n_1700_B() > 1 && this.w_1484_f) {
            if (this.t_148_a) {
                p_parse_1_.setCursor(0);
                throw J_1907_R.createWithContext((ImmutableStringReader)p_parse_1_);
            }
            p_parse_1_.setCursor(0);
            throw n_1700_B.createWithContext((ImmutableStringReader)p_parse_1_);
        }
        if (entityselector.J_1907_R() && this.t_148_a && !entityselector.R_4764_Y()) {
            p_parse_1_.setCursor(0);
            throw R_4764_Y.createWithContext((ImmutableStringReader)p_parse_1_);
        }
        return entityselector;
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        if (p_listSuggestions_1_.getSource() instanceof V_4217_p) {
            StringReader stringreader = new StringReader(p_listSuggestions_2_.getInput());
            stringreader.setCursor(p_listSuggestions_2_.getStart());
            V_4217_p isuggestionprovider = (V_4217_p)p_listSuggestions_1_.getSource();
            J_2545_z entityselectorparser = new J_2545_z(stringreader, isuggestionprovider.n_1700_B(2));
            try {
                entityselectorparser.w_1457_N();
            }
            catch (CommandSyntaxException commandSyntaxException) {
                // empty catch block
            }
            return entityselectorparser.n_1700_B(p_listSuggestions_2_, (SuggestionsBuilder p_201942_2_) -> {
                Collection<String> collection = isuggestionprovider.n_1700_B();
                Collection<String> iterable = this.t_148_a ? collection : Iterables.concat(collection, isuggestionprovider.J_1907_R());
                V_4217_p.J_1907_R(iterable, p_201942_2_);
            });
        }
        return Suggestions.empty();
    }

    public Collection<String> getExamples() {
        return v_4262_N;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    public static class n_1700_B
    implements ArgumentSerializer<i_4556_r> {
        @Override
        public void n_1700_B(i_4556_r argument, b_2585_i buffer) {
            byte b0 = 0;
            if (argument.w_1484_f) {
                b0 = (byte)(b0 | 1);
            }
            if (argument.t_148_a) {
                b0 = (byte)(b0 | 2);
            }
            buffer.writeByte(b0);
        }

        public i_4556_r J_1907_R(b_2585_i buffer) {
            byte b0 = buffer.readByte();
            return new i_4556_r((b0 & 1) != 0, (b0 & 2) != 0);
        }

        @Override
        public void n_1700_B(i_4556_r p_212244_1_, JsonObject p_212244_2_) {
            p_212244_2_.addProperty("amount", p_212244_1_.w_1484_f ? "single" : "multiple");
            p_212244_2_.addProperty("type", p_212244_1_.t_148_a ? "players" : "entities");
        }

        @Override
        public /* synthetic */ ArgumentType n_1700_B(b_2585_i b_2585_i2) {
            return this.J_1907_R(b_2585_i2);
        }
    }
}


