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
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.TagContainer;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_2912_j;
import lightning.product.f_71_T;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;
import lightning.product.n_3832_I;
import lightning.product.BlockInWorld;
import lightning.product.BlockTags;
import lightning.product.r_109_r;
import lightning.product.v_3760_Q;
import lightning.product.y_2498_m;

public class BlockPredicateArgument
implements ArgumentType<J_1907_R> {
    private static final Collection<String> n_1700_B = Arrays.asList("stone", "minecraft:stone", "stone[foo=bar]", "#stone", "#stone[foo=bar]{baz=nbt}");
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208682_0_ -> new F_2904_S("arguments.block.tag.unknown", p_208682_0_));

    public static BlockPredicateArgument n_1700_B() {
        return new BlockPredicateArgument();
    }

    public J_1907_R n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        f_71_T blockstateparser = new f_71_T(p_parse_1_, true).n_1700_B(true);
        if (blockstateparser.J_1907_R() != null) {
            n_1700_B blockpredicateargument$blockpredicate = new n_1700_B(blockstateparser.J_1907_R(), blockstateparser.n_1700_B().keySet(), blockstateparser.R_4764_Y());
            return p_199823_1_ -> blockpredicateargument$blockpredicate;
        }
        g_2336_b resourcelocation = blockstateparser.G_564_y();
        return p_199822_2_ -> {
            r_109_r<T_2915_h> itag = p_199822_2_.n_1700_B().n_1700_B(resourcelocation);
            if (itag == null) {
                throw J_1907_R.create((Object)resourcelocation.toString());
            }
            return new R_4764_Y(itag, blockstateparser.s_956_w(), blockstateparser.R_4764_Y());
        };
    }

    public static Predicate<BlockInWorld> n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return ((J_1907_R)context.getArgument(name, J_1907_R.class)).create(((y_2498_m)context.getSource()).w_1457_N().F_1410_V());
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        StringReader stringreader = new StringReader(p_listSuggestions_2_.getInput());
        stringreader.setCursor(p_listSuggestions_2_.getStart());
        f_71_T blockstateparser = new f_71_T(stringreader, true);
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

    static class n_1700_B
    implements Predicate<BlockInWorld> {
        private final K_4074_S n_1700_B;
        private final Set<v_3760_Q<?>> J_1907_R;
        @Nullable
        private final U_2912_j R_4764_Y;

        public n_1700_B(K_4074_S stateIn, Set<v_3760_Q<?>> propertiesIn, @Nullable U_2912_j nbtIn) {
            this.n_1700_B = stateIn;
            this.J_1907_R = propertiesIn;
            this.R_4764_Y = nbtIn;
        }

        public boolean n_1700_B(BlockInWorld p_test_1_) {
            K_4074_S blockstate = p_test_1_.n_1700_B();
            if (!blockstate.n_1700_B(this.n_1700_B.J_1907_R())) {
                return false;
            }
            for (v_3760_Q<?> property : this.J_1907_R) {
                if (blockstate.R_4764_Y(property) == this.n_1700_B.R_4764_Y(property)) continue;
                return false;
            }
            if (this.R_4764_Y == null) {
                return true;
            }
            i_2154_H tileentity = p_test_1_.J_1907_R();
            return tileentity != null && n_3832_I.n_1700_B(this.R_4764_Y, tileentity.n_1700_B(new U_2912_j()), true);
        }

        @Override
        public /* synthetic */ boolean test(Object object) {
            return this.n_1700_B((BlockInWorld)object);
        }
    }

    public static interface J_1907_R {
        public Predicate<BlockInWorld> create(TagContainer var1) throws CommandSyntaxException;
    }

    static class R_4764_Y
    implements Predicate<BlockInWorld> {
        private final r_109_r<T_2915_h> n_1700_B;
        @Nullable
        private final U_2912_j J_1907_R;
        private final Map<String, String> R_4764_Y;

        private R_4764_Y(r_109_r<T_2915_h> tagIn, Map<String, String> propertiesIn, @Nullable U_2912_j nbtIn) {
            this.n_1700_B = tagIn;
            this.R_4764_Y = propertiesIn;
            this.J_1907_R = nbtIn;
        }

        public boolean n_1700_B(BlockInWorld p_test_1_) {
            K_4074_S blockstate = p_test_1_.n_1700_B();
            if (!blockstate.n_1700_B(this.n_1700_B)) {
                return false;
            }
            for (Map.Entry<String, String> entry : this.R_4764_Y.entrySet()) {
                v_3760_Q<?> property = blockstate.J_1907_R().t_1786_h().n_1700_B(entry.getKey());
                if (property == null) {
                    return false;
                }
                Comparable comparable = property.J_1907_R(entry.getValue()).orElse(null);
                if (comparable == null) {
                    return false;
                }
                if (blockstate.R_4764_Y(property) == comparable) continue;
                return false;
            }
            if (this.J_1907_R == null) {
                return true;
            }
            i_2154_H tileentity = p_test_1_.J_1907_R();
            return tileentity != null && n_3832_I.n_1700_B(this.J_1907_R, tileentity.n_1700_B(new U_2912_j()), true);
        }

        @Override
        public /* synthetic */ boolean test(Object object) {
            return this.n_1700_B((BlockInWorld)object);
        }
    }
}


