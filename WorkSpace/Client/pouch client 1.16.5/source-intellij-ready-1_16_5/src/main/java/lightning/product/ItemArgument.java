/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import lightning.product.ItemTags;
import lightning.product.ItemInput;
import lightning.product.ItemParser;

public class ItemArgument
implements ArgumentType<ItemInput> {
    private static final Collection<String> n_1700_B = Arrays.asList("stick", "minecraft:stick", "stick{foo=bar}");

    public static ItemArgument n_1700_B() {
        return new ItemArgument();
    }

    public ItemInput n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        ItemParser itemparser = new ItemParser(p_parse_1_, false).v_4262_N();
        return new ItemInput(itemparser.n_1700_B(), itemparser.J_1907_R());
    }

    public static <S> ItemInput n_1700_B(CommandContext<S> context, String name) {
        return (ItemInput)context.getArgument(name, ItemInput.class);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        StringReader stringreader = new StringReader(p_listSuggestions_2_.getInput());
        stringreader.setCursor(p_listSuggestions_2_.getStart());
        ItemParser itemparser = new ItemParser(stringreader, false);
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
}


