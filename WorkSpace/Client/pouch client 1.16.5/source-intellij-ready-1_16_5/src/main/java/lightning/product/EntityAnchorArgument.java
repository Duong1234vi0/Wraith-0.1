/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.brigadier.ImmutableStringReader
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

import com.google.common.collect.Maps;
import com.mojang.brigadier.ImmutableStringReader;
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
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.V_4217_p;
import lightning.product.e_2866_D;
import lightning.product.j_3341_s;
import lightning.product.y_2498_m;

public class EntityAnchorArgument
implements ArgumentType<n_1700_B> {
    private static final Collection<String> n_1700_B = Arrays.asList("eyes", "feet");
    private static final DynamicCommandExceptionType J_1907_R = new DynamicCommandExceptionType(p_208661_0_ -> new F_2904_S("argument.anchor.invalid", p_208661_0_));

    public static n_1700_B n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (n_1700_B)((Object)context.getArgument(name, n_1700_B.class));
    }

    public static EntityAnchorArgument n_1700_B() {
        return new EntityAnchorArgument();
    }

    public n_1700_B n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        int i = p_parse_1_.getCursor();
        String s = p_parse_1_.readUnquotedString();
        n_1700_B entityanchorargument$type = lightning.product.EntityAnchorArgument$n_1700_B.n_1700_B(s);
        if (entityanchorargument$type == null) {
            p_parse_1_.setCursor(i);
            throw J_1907_R.createWithContext((ImmutableStringReader)p_parse_1_, (Object)s);
        }
        return entityanchorargument$type;
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return V_4217_p.J_1907_R(lightning.product.EntityAnchorArgument$n_1700_B.R_4764_Y.keySet(), p_listSuggestions_2_);
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("feet", (p_201019_0_, p_201019_1_) -> p_201019_0_);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("eyes", (p_201018_0_, p_201018_1_) -> new e_2866_D(p_201018_0_.J_1907_R, p_201018_0_.R_4764_Y + (double)p_201018_1_.X_1313_W(), p_201018_0_.G_564_y));
        private static final Map<String, n_1700_B> R_4764_Y;
        private final String G_564_y;
        private final BiFunction<e_2866_D, N_4263_v, e_2866_D> P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String nameIn, BiFunction<e_2866_D, N_4263_v, e_2866_D> offsetFuncIn) {
            this.G_564_y = nameIn;
            this.P_1922_E = offsetFuncIn;
        }

        @Nullable
        public static n_1700_B n_1700_B(String nameIn) {
            return R_4764_Y.get(nameIn);
        }

        public e_2866_D n_1700_B(N_4263_v entityIn) {
            return this.P_1922_E.apply(entityIn.s_4990_V(), entityIn);
        }

        public e_2866_D n_1700_B(y_2498_m sourceIn) {
            N_4263_v entity = sourceIn.Q_4569_t();
            return entity == null ? sourceIn.P_4830_p() : this.P_1922_E.apply(sourceIn.P_4830_p(), entity);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            u_1723_Y = lightning.product.EntityAnchorArgument$n_1700_B.n_1700_B();
            R_4764_Y = j_3341_s.n_1700_B(Maps.newHashMap(), (T p_209384_0_) -> {
                for (n_1700_B entityanchorargument$type : lightning.product.EntityAnchorArgument$n_1700_B.values()) {
                    p_209384_0_.put(entityanchorargument$type.G_564_y, entityanchorargument$type);
                }
            });
        }
    }
}


