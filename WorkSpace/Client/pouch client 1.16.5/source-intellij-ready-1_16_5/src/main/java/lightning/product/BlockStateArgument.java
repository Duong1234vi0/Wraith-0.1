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
import lightning.product.BlockInput;
import lightning.product.f_71_T;
import lightning.product.BlockTags;
import lightning.product.y_2498_m;

public class BlockStateArgument
implements ArgumentType<BlockInput> {
    private static final Collection<String> n_1700_B = Arrays.asList("stone", "minecraft:stone", "stone[foo=bar]", "foo{bar=baz}");

    public static BlockStateArgument n_1700_B() {
        return new BlockStateArgument();
    }

    public BlockInput n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        f_71_T blockstateparser = new f_71_T(p_parse_1_, false).n_1700_B(true);
        return new BlockInput(blockstateparser.J_1907_R(), blockstateparser.n_1700_B().keySet(), blockstateparser.R_4764_Y());
    }

    public static BlockInput n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (BlockInput)context.getArgument(name, BlockInput.class);
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        StringReader stringreader = new StringReader(p_listSuggestions_2_.getInput());
        stringreader.setCursor(p_listSuggestions_2_.getStart());
        f_71_T blockstateparser = new f_71_T(stringreader, false);
        try {
            blockstateparser.n_1700_B(true);
        }
        catch (CommandSyntaxException commandSyntaxException) {
            // empty catch block
        }
        return blockstateparser.n_1700_B(p_listSuggestions_2_, BlockTags.n_1700_B());
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


