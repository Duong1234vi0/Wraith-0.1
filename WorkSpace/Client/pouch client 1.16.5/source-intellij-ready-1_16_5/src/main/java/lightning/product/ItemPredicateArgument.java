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
 *  javax.annotation.Nullable
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
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.ItemTags;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.n_3832_I;
import lightning.product.q_1613_l;
import lightning.product.r_109_r;
import lightning.product.ItemParser;
import lightning.product.y_2498_m;

public class ItemPredicateArgument
implements ArgumentType<n_1700_B> {
    private static final Collection<String> n_1700_B = Arrays.asList("stick", "minecraft:stick", "#stick", "#stick{foo=bar}");
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(tag -> new F_2904_S("arguments.item.tag.unknown", tag));

    public static ItemPredicateArgument n_1700_B() {
        return new ItemPredicateArgument();
    }

    public n_1700_B n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        ItemParser itemparser = new ItemParser(p_parse_1_, true).v_4262_N();
        if (itemparser.n_1700_B() != null) {
            J_1907_R itempredicateargument$itempredicate = new J_1907_R(itemparser.n_1700_B(), itemparser.J_1907_R());
            return context -> itempredicateargument$itempredicate;
        }
        g_2336_b resourcelocation = itemparser.R_4764_Y();
        return context -> {
            r_109_r<q_1613_l> itag = ((y_2498_m)context.getSource()).w_1457_N().F_1410_V().J_1907_R().n_1700_B(resourcelocation);
            if (itag == null) {
                throw J_1907_R.create((Object)resourcelocation.toString());
            }
            return new R_4764_Y(itag, itemparser.J_1907_R());
        };
    }

    public static Predicate<Z_1993_T> n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return ((n_1700_B)context.getArgument(name, n_1700_B.class)).create(context);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        StringReader stringreader = new StringReader(p_listSuggestions_2_.getInput());
        stringreader.setCursor(p_listSuggestions_2_.getStart());
        ItemParser itemparser = new ItemParser(stringreader, true);
        try {
            itemparser.v_4262_N();
        }
        catch (CommandSyntaxException commandSyntaxException) {
            // empty catch block
        }
        return itemparser.n_1700_B(p_listSuggestions_2_, ItemTags.n_1700_B());
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    static class J_1907_R
    implements Predicate<Z_1993_T> {
        private final q_1613_l n_1700_B;
        @Nullable
        private final U_2912_j J_1907_R;

        public J_1907_R(q_1613_l itemIn, @Nullable U_2912_j nbtIn) {
            this.n_1700_B = itemIn;
            this.J_1907_R = nbtIn;
        }

        public boolean n_1700_B(Z_1993_T p_test_1_) {
            return p_test_1_.J_1907_R() == this.n_1700_B && n_3832_I.n_1700_B(this.J_1907_R, p_test_1_.Q_4569_t(), true);
        }

        @Override
        public /* synthetic */ boolean test(Object object) {
            return this.n_1700_B((Z_1993_T)object);
        }
    }

    public static interface n_1700_B {
        public Predicate<Z_1993_T> create(CommandContext<y_2498_m> var1) throws CommandSyntaxException;
    }

    static class R_4764_Y
    implements Predicate<Z_1993_T> {
        private final r_109_r<q_1613_l> n_1700_B;
        @Nullable
        private final U_2912_j J_1907_R;

        public R_4764_Y(r_109_r<q_1613_l> tagIn, @Nullable U_2912_j nbtIn) {
            this.n_1700_B = tagIn;
            this.J_1907_R = nbtIn;
        }

        public boolean n_1700_B(Z_1993_T p_test_1_) {
            return this.n_1700_B.n_1700_B(p_test_1_.J_1907_R()) && n_3832_I.n_1700_B(this.J_1907_R, p_test_1_.Q_4569_t(), true);
        }

        @Override
        public /* synthetic */ boolean test(Object object) {
            return this.n_1700_B((Z_1993_T)object);
        }
    }
}


