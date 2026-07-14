/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.Arrays;
import java.util.Collection;
import lightning.product.F_2904_S;
import lightning.product.WorldCoordinate;
import lightning.product.WorldCoordinates;
import lightning.product.Coordinates;
import lightning.product.y_2498_m;

public class RotationArgument
implements ArgumentType<Coordinates> {
    private static final Collection<String> J_1907_R = Arrays.asList("0 0", "~ ~", "~-5 ~5");
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.rotation.incomplete"));

    public static RotationArgument n_1700_B() {
        return new RotationArgument();
    }

    public static Coordinates n_1700_B(CommandContext<y_2498_m> context, String name) {
        return (Coordinates)context.getArgument(name, Coordinates.class);
    }

    public Coordinates n_1700_B(StringReader p_parse_1_) throws CommandSyntaxException {
        int i = p_parse_1_.getCursor();
        if (!p_parse_1_.canRead()) {
            throw n_1700_B.createWithContext((ImmutableStringReader)p_parse_1_);
        }
        WorldCoordinate locationpart = WorldCoordinate.n_1700_B(p_parse_1_, false);
        if (p_parse_1_.canRead() && p_parse_1_.peek() == ' ') {
            p_parse_1_.skip();
            WorldCoordinate locationpart1 = WorldCoordinate.n_1700_B(p_parse_1_, false);
            return new WorldCoordinates(locationpart1, locationpart, new WorldCoordinate(true, 0.0));
        }
        p_parse_1_.setCursor(i);
        throw n_1700_B.createWithContext((ImmutableStringReader)p_parse_1_);
    }

    public Collection<String> getExamples() {
        return J_1907_R;
    }

    public /* synthetic */ Object parse(StringReader stringReader) throws CommandSyntaxException {
        return this.n_1700_B(stringReader);
    }
}


