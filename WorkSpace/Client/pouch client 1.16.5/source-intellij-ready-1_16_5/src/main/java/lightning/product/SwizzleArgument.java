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
 */
package lightning.product;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import lightning.product.F_2904_S;
import lightning.product.b_257_Y;
import lightning.product.y_2498_m;

public class SwizzleArgument
implements ArgumentType<EnumSet<b_257_Y.n_1700_B>> {
    private static final Collection<String> n_1700_B = Arrays.asList("xyz", "x");
    private static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("arguments.swizzle.invalid"));

    public static SwizzleArgument n_1700_B() {
        return new SwizzleArgument();
    }

    public static EnumSet<b_257_Y.n_1700_B> n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (EnumSet)context.getArgument(name, EnumSet.class);
    }

    public EnumSet<b_257_Y.n_1700_B> n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        EnumSet<b_257_Y.n_1700_B> enumset = EnumSet.noneOf(b_257_Y.n_1700_B.class);
        while (p_parse_1_.canRead() && p_parse_1_.peek() != ' ') {
            char c0 = p_parse_1_.read();
            b_257_Y.n_1700_B direction$axis = switch (c0) {
                case 'x' -> b_257_Y.n_1700_B.n_1700_B;
                case 'y' -> b_257_Y.n_1700_B.J_1907_R;
                case 'z' -> b_257_Y.n_1700_B.R_4764_Y;
                default -> throw J_1907_R.create();
            };
            if (enumset.contains(direction$axis)) {
                throw J_1907_R.create();
            }
            enumset.add(direction$axis);
        }
        return enumset;
    }

    public Collection<String> getExamples() {
        return n_1700_B;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


