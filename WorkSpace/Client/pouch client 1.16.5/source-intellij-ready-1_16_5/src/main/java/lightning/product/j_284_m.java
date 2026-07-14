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
import java.util.concurrent.CompletableFuture;
import lightning.product.F_2904_S;
import lightning.product.V_4217_p;
import lightning.product.u_530_F;
import lightning.product.v_4839_y;
import lightning.product.y_2498_m;

public class j_284_m
implements ArgumentType<J_1907_R> {
    private static final Collection<String> n_1700_B = Arrays.asList("=", ">", "<");
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("arguments.operation.invalid"));
    private static final SimpleCommandExceptionType R_4764_Y = new SimpleCommandExceptionType((Message)new F_2904_S("arguments.operation.div0"));

    public static j_284_m n_1700_B() {
        return new j_284_m();
    }

    public static J_1907_R n_1700_B(CommandContext<y_2498_m> context, String name) throws CommandSyntaxException {
        return (J_1907_R)context.getArgument(name, J_1907_R.class);
    }

    public J_1907_R n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        if (!p_parse_1_.canRead()) {
            throw J_1907_R.create();
        }
        int i = p_parse_1_.getCursor();
        while (p_parse_1_.canRead() && p_parse_1_.peek() != ' ') {
            p_parse_1_.skip();
        }
        return j_284_m.n_1700_B(p_parse_1_.getString().substring(i, p_parse_1_.getCursor()));
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_listSuggestions_1_, SuggestionsBuilder p_listSuggestions_2_) {
        return V_4217_p.n_1700_B(new String[]{"=", "+=", "-=", "*=", "/=", "%=", "<", ">", "><"}, p_listSuggestions_2_);
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    private static J_1907_R n_1700_B(String name) throws CommandSyntaxException {
        return name.equals("><") ? (p_197175_0_, p_197175_1_) -> {
            int i = p_197175_0_.J_1907_R();
            p_197175_0_.J_1907_R(p_197175_1_.J_1907_R());
            p_197175_1_.J_1907_R(i);
        } : j_284_m.J_1907_R(name);
    }

    private static n_1700_B J_1907_R(String name) throws CommandSyntaxException {
        int b0 = -1;
        switch (name.hashCode()) {
            case 60: {
                if (!name.equals("<")) break;
                b0 = 6;
                break;
            }
            case 61: {
                if (!name.equals("=")) break;
                b0 = 0;
                break;
            }
            case 62: {
                if (!name.equals(">")) break;
                b0 = 7;
                break;
            }
            case 1208: {
                if (!name.equals("%=")) break;
                b0 = 5;
                break;
            }
            case 1363: {
                if (!name.equals("*=")) break;
                b0 = 3;
                break;
            }
            case 1394: {
                if (!name.equals("+=")) break;
                b0 = 1;
                break;
            }
            case 1456: {
                if (!name.equals("-=")) break;
                b0 = 2;
                break;
            }
            case 1518: {
                if (!name.equals("/=")) break;
                b0 = 4;
            }
        }
        switch (b0) {
            case 0: {
                return (p_197174_0_, p_197174_1_) -> p_197174_1_;
            }
            case 1: {
                return (p_197176_0_, p_197176_1_) -> p_197176_0_ + p_197176_1_;
            }
            case 2: {
                return (p_197183_0_, p_197183_1_) -> p_197183_0_ - p_197183_1_;
            }
            case 3: {
                return (p_197173_0_, p_197173_1_) -> p_197173_0_ * p_197173_1_;
            }
            case 4: {
                return (p_197178_0_, p_197178_1_) -> {
                    if (p_197178_1_ == 0) {
                        throw R_4764_Y.create();
                    }
                    return u_530_F.n_1700_B(p_197178_0_, p_197178_1_);
                };
            }
            case 5: {
                return (p_197181_0_, p_197181_1_) -> {
                    if (p_197181_1_ == 0) {
                        throw R_4764_Y.create();
                    }
                    return u_530_F.J_1907_R(p_197181_0_, p_197181_1_);
                };
            }
            case 6: {
                return Math::min;
            }
            case 7: {
                return Math::max;
            }
        }
        throw J_1907_R.create();
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }

    @FunctionalInterface
    public static interface J_1907_R {
        public void apply(v_4839_y var1, v_4839_y var2) throws CommandSyntaxException;
    }

    @FunctionalInterface
    static interface n_1700_B
    extends J_1907_R {
        public int apply(int var1, int var2) throws CommandSyntaxException;

        @Override
        default public void apply(v_4839_y p_apply_1_, v_4839_y p_apply_2_) throws CommandSyntaxException {
            p_apply_1_.J_1907_R(this.apply(p_apply_1_.J_1907_R(), p_apply_2_.J_1907_R()));
        }
    }
}

