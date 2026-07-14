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
import lightning.product.Tag;
import lightning.product.r_4318_c;

public class NbtTagArgument
implements ArgumentType<Tag> {
    private static final Collection<String> n_1700_B = Arrays.asList("0", "0b", "0l", "0.0", "\"foo\"", "{foo=bar}", "[0]");

    private NbtTagArgument() {
    }

    public static NbtTagArgument n_1700_B() {
        return new NbtTagArgument();
    }

    public static <S> Tag n_1700_B(CommandContext<S> p_218086_0_, String p_218086_1_) {
        return (Tag)p_218086_0_.getArgument(p_218086_1_, Tag.class);
    }

    public Tag n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        return new r_4318_c(p_parse_1_).G_564_y();
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


