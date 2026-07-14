/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonParseException
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 */
package lightning.product;

import com.google.gson.JsonParseException;
import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import java.util.Arrays;
import java.util.Collection;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class ComponentArgument
implements ArgumentType<x_282_a> {
    private static final Collection<String> J_1907_R = Arrays.asList("\"hello world\"", "\"\"", "\"{\"text\":\"hello world\"}", "[\"\"]");
    public static final DynamicCommandExceptionType n_1700_B = new DynamicCommandExceptionType(textComponent -> new F_2904_S("argument.component.invalid", textComponent));

    private ComponentArgument() {
    }

    public static x_282_a n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (x_282_a)context.getArgument(name, x_282_a.class);
    }

    public static ComponentArgument n_1700_B() {
        return new ComponentArgument();
    }

    public x_282_a n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        try {
            MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(p_parse_1_);
            if (itextcomponent == null) {
                throw n_1700_B.createWithContext((ImmutableStringReader)p_parse_1_, (Object)"empty");
            }
            return itextcomponent;
        }
        catch (JsonParseException jsonparseexception) {
            String s = jsonparseexception.getCause() != null ? jsonparseexception.getCause().getMessage() : jsonparseexception.getMessage();
            throw n_1700_B.createWithContext((ImmutableStringReader)p_parse_1_, (Object)s);
        }
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


