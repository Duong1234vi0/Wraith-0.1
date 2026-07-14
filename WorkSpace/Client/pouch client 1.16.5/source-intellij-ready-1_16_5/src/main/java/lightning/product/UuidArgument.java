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
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.F_2904_S;
import lightning.product.y_2498_m;

public class UuidArgument
implements ArgumentType<UUID> {
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.uuid.invalid"));
    private static final Collection<String> J_1907_R = Arrays.asList("dd12be42-52a9-4a91-a8a1-11c01849e498");
    private static final Pattern R_4764_Y = Pattern.compile("^([-A-Fa-f0-9]+)");

    public static UUID n_1700_B(CommandContext<y_2498_m> p_239195_0_, String p_239195_1_) {
        return (UUID)p_239195_0_.getArgument(p_239195_1_, UUID.class);
    }

    public static UuidArgument n_1700_B() {
        return new UuidArgument();
    }

    public UUID n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        String s = p_parse_1_.getRemaining();
        Matcher matcher = R_4764_Y.matcher(s);
        if (matcher.find()) {
            String s1 = matcher.group(1);
            try {
                UUID uuid = UUID.fromString(s1);
                p_parse_1_.setCursor(p_parse_1_.getCursor() + s1.length());
                return uuid;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                // empty catch block
            }
        }
        throw n_1700_B.create();
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


