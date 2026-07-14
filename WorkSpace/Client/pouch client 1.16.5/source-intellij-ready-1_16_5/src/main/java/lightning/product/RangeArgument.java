/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 */
package lightning.product;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Arrays;
import java.util.Collection;
import lightning.product.MinMaxBounds;
import lightning.product.y_2498_m;

public interface RangeArgument<T extends MinMaxBounds<?>>
extends ArgumentType<T> {
    public static J_1907_R n_1700_B() {
        return new J_1907_R();
    }

    public static n_1700_B J_1907_R() {
        return new n_1700_B();
    }

    public static class J_1907_R
    implements RangeArgument<MinMaxBounds.G_564_y> {
        private static final Collection<String> n_1700_B = Arrays.asList("0..5", "0", "-5", "-100..", "..100");

        public static MinMaxBounds.G_564_y n_1700_B(CommandContext<y_2498_m> context, String name) {
            return (MinMaxBounds.G_564_y)context.getArgument(name, MinMaxBounds.G_564_y.class);
        }

        public MinMaxBounds.G_564_y n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
            return MinMaxBounds.G_564_y.n_1700_B(p_parse_1_);
        }

        public Collection<String> getExamples() {
            return n_1700_B;
        }

        public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
            return this.n_1700_B(stringReader);
        }
    }

    public static class n_1700_B
    implements RangeArgument<MinMaxBounds.n_1700_B> {
        private static final Collection<String> n_1700_B = Arrays.asList("0..5.2", "0", "-5.4", "-100.76..", "..100");

        public MinMaxBounds.n_1700_B n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
            return MinMaxBounds.n_1700_B.n_1700_B(p_parse_1_);
        }

        public Collection<String> getExamples() {
            return n_1700_B;
        }

        public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
            return this.n_1700_B(stringReader);
        }
    }
}


