/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.BuiltInExceptionProvider
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.BuiltInExceptionProvider;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.i_4431_W;

public abstract class MinMaxBounds<T extends Number> {
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.range.empty"));
    public static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("argument.range.swapped"));
    protected final T R_4764_Y;
    protected final T G_564_y;

    protected MinMaxBounds(@Nullable T min, @Nullable T max) {
        this.R_4764_Y = min;
        this.G_564_y = max;
    }

    @Nullable
    public T n_1700_B() {
        return this.R_4764_Y;
    }

    @Nullable
    public T J_1907_R() {
        return this.G_564_y;
    }

    public boolean R_4764_Y() {
        return this.R_4764_Y == null && this.G_564_y == null;
    }

    public JsonElement G_564_y() {
        if (this.R_4764_Y()) {
            return JsonNull.INSTANCE;
        }
        if (this.R_4764_Y != null && this.R_4764_Y.equals(this.G_564_y)) {
            return new JsonPrimitive(this.R_4764_Y);
        }
        JsonObject jsonobject = new JsonObject();
        if (this.R_4764_Y != null) {
            jsonobject.addProperty("min", this.R_4764_Y);
        }
        if (this.G_564_y != null) {
            jsonobject.addProperty("max", this.G_564_y);
        }
        return jsonobject;
    }

    protected static <T extends Number, R extends MinMaxBounds<T>> R n_1700_B(@Nullable JsonElement element, R defaultIn, BiFunction<JsonElement, String, T> biFunction, J_1907_R<T, R> boundedFactory) {
        if (element != null && !element.isJsonNull()) {
            if (i_4431_W.J_1907_R(element)) {
                Number t2 = (Number)biFunction.apply(element, "value");
                return boundedFactory.create(t2, t2);
            }
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "value");
            Number t = jsonobject.has("min") ? (Number)((Number)biFunction.apply(jsonobject.get("min"), "min")) : (Number)null;
            Number t1 = jsonobject.has("max") ? (Number)((Number)biFunction.apply(jsonobject.get("max"), "max")) : (Number)null;
            return boundedFactory.create(t, t1);
        }
        return defaultIn;
    }

    protected static <T extends Number, R extends MinMaxBounds<T>> R n_1700_B(StringReader reader, R_4764_Y<T, R> minMaxReader, Function<String, T> valueFunction, Supplier<DynamicCommandExceptionType> commandExceptionSupplier, Function<T, T> function) throws CommandSyntaxException {
        if (!reader.canRead()) {
            throw n_1700_B.createWithContext((ImmutableStringReader)reader);
        }
        int i = reader.getCursor();
        try {
            Number t1;
            Number t = (Number)MinMaxBounds.n_1700_B(MinMaxBounds.n_1700_B(reader, valueFunction, commandExceptionSupplier), function);
            if (reader.canRead(2) && reader.peek() == '.' && reader.peek(1) == '.') {
                reader.skip();
                reader.skip();
                t1 = (Number)MinMaxBounds.n_1700_B(MinMaxBounds.n_1700_B(reader, valueFunction, commandExceptionSupplier), function);
                if (t == null && t1 == null) {
                    throw n_1700_B.createWithContext((ImmutableStringReader)reader);
                }
            } else {
                t1 = t;
            }
            if (t == null && t1 == null) {
                throw n_1700_B.createWithContext((ImmutableStringReader)reader);
            }
            return minMaxReader.create(reader, t, t1);
        }
        catch (CommandSyntaxException commandsyntaxexception) {
            reader.setCursor(i);
            throw new CommandSyntaxException(commandsyntaxexception.getType(), commandsyntaxexception.getRawMessage(), commandsyntaxexception.getInput(), i);
        }
    }

    @Nullable
    private static <T extends Number> T n_1700_B(StringReader reader, Function<String, T> stringToValueFunction, Supplier<DynamicCommandExceptionType> commandExceptionSupplier) throws CommandSyntaxException {
        int i = reader.getCursor();
        while (reader.canRead() && MinMaxBounds.n_1700_B(reader)) {
            reader.skip();
        }
        String s = reader.getString().substring(i, reader.getCursor());
        if (s.isEmpty()) {
            return (T)((Number)null);
        }
        try {
            return (T)((Number)stringToValueFunction.apply(s));
        }
        catch (NumberFormatException numberformatexception) {
            throw commandExceptionSupplier.get().createWithContext((ImmutableStringReader)reader, (Object)s);
        }
    }

    private static boolean n_1700_B(StringReader reader) {
        char c0 = reader.peek();
        if ((c0 < '0' || c0 > '9') && c0 != '-') {
            if (c0 != '.') {
                return false;
            }
            return !reader.canRead(2) || reader.peek(1) != '.';
        }
        return true;
    }

    @Nullable
    private static <T> T n_1700_B(@Nullable T value, Function<T, T> formatterFunction) {
        return value == null ? null : (T)formatterFunction.apply(value);
    }

    @FunctionalInterface
    public static interface J_1907_R<T extends Number, R extends MinMaxBounds<T>> {
        public R create(@Nullable T var1, @Nullable T var2);
    }

    @FunctionalInterface
    public static interface R_4764_Y<T extends Number, R extends MinMaxBounds<T>> {
        public R create(StringReader var1, @Nullable T var2, @Nullable T var3) throws CommandSyntaxException;
    }

    public static class G_564_y
    extends MinMaxBounds<Integer> {
        public static final G_564_y P_1922_E = new G_564_y((Integer)null, (Integer)null);
        private final Long u_1723_Y;
        private final Long v_4262_N;

        private static G_564_y n_1700_B(StringReader reader, @Nullable Integer min, @Nullable Integer max) throws CommandSyntaxException {
            if (min != null && max != null && min > max) {
                throw J_1907_R.createWithContext((ImmutableStringReader)reader);
            }
            return new G_564_y(min, max);
        }

        @Nullable
        private static Long n_1700_B(@Nullable Integer value) {
            return value == null ? null : Long.valueOf(value.longValue() * value.longValue());
        }

        private G_564_y(@Nullable Integer min, @Nullable Integer max) {
            super(min, max);
            this.u_1723_Y = lightning.product.MinMaxBounds$G_564_y.n_1700_B(min);
            this.v_4262_N = lightning.product.MinMaxBounds$G_564_y.n_1700_B(max);
        }

        public static G_564_y n_1700_B(int value) {
            return new G_564_y(value, value);
        }

        public static G_564_y J_1907_R(int value) {
            return new G_564_y(value, (Integer)null);
        }

        public boolean R_4764_Y(int value) {
            if (this.R_4764_Y != null && (Integer)this.R_4764_Y > value) {
                return false;
            }
            return this.G_564_y == null || (Integer)this.G_564_y >= value;
        }

        public static G_564_y n_1700_B(@Nullable JsonElement element) {
            return lightning.product.MinMaxBounds$G_564_y.n_1700_B(element, P_1922_E, i_4431_W::u_1723_Y, G_564_y::new);
        }

        public static G_564_y n_1700_B(StringReader reader) throws CommandSyntaxException {
            return lightning.product.MinMaxBounds$G_564_y.n_1700_B(reader, (Integer integer) -> integer);
        }

        public static G_564_y n_1700_B(StringReader reader, Function<Integer, Integer> valueFunction) throws CommandSyntaxException {
            return lightning.product.MinMaxBounds$G_564_y.n_1700_B(reader, G_564_y::n_1700_B, Integer::parseInt, () -> ((BuiltInExceptionProvider)CommandSyntaxException.BUILT_IN_EXCEPTIONS).readerInvalidInt(), valueFunction);
        }
    }

    public static class n_1700_B
    extends MinMaxBounds<Float> {
        public static final n_1700_B P_1922_E = new n_1700_B((Float)null, (Float)null);
        private final Double u_1723_Y;
        private final Double v_4262_N;

        private static n_1700_B n_1700_B(StringReader reader, @Nullable Float min, @Nullable Float max) throws CommandSyntaxException {
            if (min != null && max != null && min.floatValue() > max.floatValue()) {
                throw J_1907_R.createWithContext((ImmutableStringReader)reader);
            }
            return new n_1700_B(min, max);
        }

        @Nullable
        private static Double n_1700_B(@Nullable Float value) {
            return value == null ? null : Double.valueOf(value.doubleValue() * value.doubleValue());
        }

        private n_1700_B(@Nullable Float min, @Nullable Float max) {
            super(min, max);
            this.u_1723_Y = lightning.product.MinMaxBounds$n_1700_B.n_1700_B(min);
            this.v_4262_N = lightning.product.MinMaxBounds$n_1700_B.n_1700_B(max);
        }

        public static n_1700_B n_1700_B(float value) {
            return new n_1700_B(Float.valueOf(value), (Float)null);
        }

        public boolean J_1907_R(float value) {
            if (this.R_4764_Y != null && ((Float)this.R_4764_Y).floatValue() > value) {
                return false;
            }
            return this.G_564_y == null || !(((Float)this.G_564_y).floatValue() < value);
        }

        public boolean n_1700_B(double value) {
            if (this.u_1723_Y != null && this.u_1723_Y > value) {
                return false;
            }
            return this.v_4262_N == null || !(this.v_4262_N < value);
        }

        public static n_1700_B n_1700_B(@Nullable JsonElement element) {
            return lightning.product.MinMaxBounds$n_1700_B.n_1700_B(element, P_1922_E, i_4431_W::G_564_y, n_1700_B::new);
        }

        public static n_1700_B n_1700_B(StringReader reader) throws CommandSyntaxException {
            return lightning.product.MinMaxBounds$n_1700_B.n_1700_B(reader, (Float floatValue) -> floatValue);
        }

        public static n_1700_B n_1700_B(StringReader reader, Function<Float, Float> valueFunction) throws CommandSyntaxException {
            return lightning.product.MinMaxBounds$n_1700_B.n_1700_B(reader, n_1700_B::n_1700_B, Float::parseFloat, () -> ((BuiltInExceptionProvider)CommandSyntaxException.BUILT_IN_EXCEPTIONS).readerInvalidFloat(), valueFunction);
        }
    }
}


