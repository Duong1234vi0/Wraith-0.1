/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.authlib.GameProfile
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

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lightning.product.B_4088_l;
import lightning.product.F_2904_S;
import lightning.product.J_2545_z;
import lightning.product.V_4217_p;
import lightning.product.Y_995_C;
import lightning.product.i_4556_r;
import lightning.product.y_2498_m;

public class GameProfileArgument
implements ArgumentType<n_1700_B> {
    private static final Collection<String> J_1907_R = Arrays.asList("Player", "0123", "dd12be42-52a9-4a91-a8a1-11c01849e498", "@e");
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.player.unknown"));

    public static Collection<GameProfile> n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return ((n_1700_B)context.getArgument(name, n_1700_B.class)).getNames((y_2498_m)context.getSource());
    }

    public static GameProfileArgument n_1700_B() {
        return new GameProfileArgument();
    }

    public n_1700_B n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        if (p_parse_1_.canRead() && p_parse_1_.peek() == '@') {
            J_2545_z entityselectorparser = new J_2545_z(p_parse_1_);
            Y_995_C entityselector = entityselectorparser.w_1457_N();
            if (entityselector.J_1907_R()) {
                throw i_4556_r.R_4764_Y.create();
            }
            return new J_1907_R(entityselector);
        }
        int i = p_parse_1_.getCursor();
        while (p_parse_1_.canRead() && p_parse_1_.peek() != ' ') {
            p_parse_1_.skip();
        }
        String s = p_parse_1_.getString().substring(i, p_parse_1_.getCursor());
        return p_197107_1_ -> {
            GameProfile gameprofile = p_197107_1_.w_1457_N().V_1225_t().n_1700_B(s);
            if (gameprofile == null) {
                throw n_1700_B.create();
            }
            return Collections.singleton(gameprofile);
        };
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        if (p_listSuggestions_1_.getSource() instanceof V_4217_p) {
            StringReader stringreader = new StringReader(p_listSuggestions_2_.getInput());
            stringreader.setCursor(p_listSuggestions_2_.getStart());
            J_2545_z entityselectorparser = new J_2545_z(stringreader);
            try {
                entityselectorparser.w_1457_N();
            }
            catch (CommandSyntaxException commandSyntaxException) {
                // empty catch block
            }
            return entityselectorparser.n_1700_B(p_listSuggestions_2_, p_201943_1_ -> V_4217_p.J_1907_R(((V_4217_p)p_listSuggestions_1_.getSource()).n_1700_B(), p_201943_1_));
        }
        return Suggestions.empty();
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    @FunctionalInterface
    public static interface n_1700_B {
        public Collection<GameProfile> getNames(y_2498_m var1) throws CommandSyntaxException;
    }

    public static class J_1907_R
    implements n_1700_B {
        private final Y_995_C n_1700_B;

        public J_1907_R(Y_995_C selectorIn) {
            this.n_1700_B = selectorIn;
        }

        @Override
        public Collection<GameProfile> getNames(y_2498_m p_getNames_1_) throws CommandSyntaxException {
            List<B_4088_l> list = this.n_1700_B.G_564_y(p_getNames_1_);
            if (list.isEmpty()) {
                throw i_4556_r.P_1922_E.create();
            }
            ArrayList list1 = Lists.newArrayList();
            for (B_4088_l serverplayerentity : list) {
                list1.add(serverplayerentity.y_4642_Y());
            }
            return list1;
        }
    }
}


