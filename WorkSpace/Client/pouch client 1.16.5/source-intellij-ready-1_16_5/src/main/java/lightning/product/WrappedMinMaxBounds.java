/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.MinMaxBounds;

public class WrappedMinMaxBounds {
    public static final WrappedMinMaxBounds n_1700_B = new WrappedMinMaxBounds(null, null);
    public static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("argument.range.ints"));
    private final Float R_4764_Y;
    private final Float G_564_y;

    public WrappedMinMaxBounds(@Nullable Float min, @Nullable Float max) {
        this.R_4764_Y = min;
        this.G_564_y = max;
    }

    @Nullable
    public Float n_1700_B() {
        return this.R_4764_Y;
    }

    @Nullable
    public Float J_1907_R() {
        return this.G_564_y;
    }

    public static WrappedMinMaxBounds n_1700_B(StringReader reader, boolean isFloatingPoint, Function<Float, Float> valueFunction) throws CommandSyntaxException {
        Float f1;
        if (!reader.canRead()) {
            throw MinMaxBounds.n_1700_B.createWithContext((ImmutableStringReader)reader);
        }
        int i = reader.getCursor();
        Float f = WrappedMinMaxBounds.n_1700_B(WrappedMinMaxBounds.n_1700_B(reader, isFloatingPoint), valueFunction);
        if (reader.canRead(2) && reader.peek() == '.' && reader.peek(1) == '.') {
            reader.skip();
            reader.skip();
            f1 = WrappedMinMaxBounds.n_1700_B(WrappedMinMaxBounds.n_1700_B(reader, isFloatingPoint), valueFunction);
            if (f == null && f1 == null) {
                reader.setCursor(i);
                throw MinMaxBounds.n_1700_B.createWithContext((ImmutableStringReader)reader);
            }
        } else {
            if (!isFloatingPoint && reader.canRead() && reader.peek() == '.') {
                reader.setCursor(i);
                throw J_1907_R.createWithContext((ImmutableStringReader)reader);
            }
            f1 = f;
        }
        if (f == null && f1 == null) {
            reader.setCursor(i);
            throw MinMaxBounds.n_1700_B.createWithContext((ImmutableStringReader)reader);
        }
        return new WrappedMinMaxBounds(f, f1);
    }

    @Nullable
    private static Float n_1700_B(StringReader reader, boolean isFloatingPoint) throws CommandSyntaxException {
        int i = reader.getCursor();
        while (reader.canRead() && WrappedMinMaxBounds.J_1907_R(reader, isFloatingPoint)) {
            reader.skip();
        }
        String s = reader.getString().substring(i, reader.getCursor());
        if (s.isEmpty()) {
            return null;
        }
        try {
            return Float.valueOf(Float.parseFloat(s));
        }
        catch (NumberFormatException numberformatexception) {
            if (isFloatingPoint) {
                throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.readerInvalidDouble().createWithContext((ImmutableStringReader)reader, (Object)s);
            }
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.readerInvalidInt().createWithContext((ImmutableStringReader)reader, (Object)s);
        }
    }

    private static boolean J_1907_R(StringReader reader, boolean isFloatingPoint) {
        char c0 = reader.peek();
        if ((c0 < '0' || c0 > '9') && c0 != '-') {
            if (isFloatingPoint && c0 == '.') {
                return !reader.canRead(2) || reader.peek(1) != '.';
            }
            return false;
        }
        return true;
    }

    @Nullable
    private static Float n_1700_B(@Nullable Float value, Function<Float, Float> valueFunction) {
        return value == null ? null : valueFunction.apply(value);
    }
}


