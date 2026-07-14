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
import lightning.product.U_2912_j;
import lightning.product.r_4318_c;

public class CompoundTagArgument
implements ArgumentType<U_2912_j> {
    private static final Collection<String> n_1700_B = Arrays.asList("{}", "{foo=bar}");

    private CompoundTagArgument() {
    }

    public static CompoundTagArgument n_1700_B() {
        return new CompoundTagArgument();
    }

    public static <S> U_2912_j n_1700_B(CommandContext<S> context, String name) {
        return (U_2912_j)context.getArgument(name, U_2912_j.class);
    }

    public U_2912_j n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        return new r_4318_c(p_parse_1_).u_1723_Y();
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


