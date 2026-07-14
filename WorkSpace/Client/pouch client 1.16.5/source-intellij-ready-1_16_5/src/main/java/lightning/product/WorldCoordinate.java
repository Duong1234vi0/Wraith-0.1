/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 */
package lightning.product;

import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import lightning.product.F_2904_S;
import lightning.product.u_1579_Y;

public class WorldCoordinate {
    public static final SimpleCommandExceptionType n_1700_B = new SimpleCommandExceptionType((Message)new F_2904_S("argument.pos.missing.double"));
    public static final SimpleCommandExceptionType J_1907_R = new SimpleCommandExceptionType((Message)new F_2904_S("argument.pos.missing.int"));
    private final boolean R_4764_Y;
    private final double G_564_y;

    public WorldCoordinate(boolean relativeIn, double valueIn) {
        this.R_4764_Y = relativeIn;
        this.G_564_y = valueIn;
    }

    public double n_1700_B(double coord) {
        return this.R_4764_Y ? this.G_564_y + coord : this.G_564_y;
    }

    public static WorldCoordinate n_1700_B(StringReader reader, boolean centerIntegers) throws CommandSyntaxException {
        if (reader.canRead() && reader.peek() == '^') {
            throw u_1579_Y.J_1907_R.createWithContext((ImmutableStringReader)reader);
        }
        if (!reader.canRead()) {
            throw n_1700_B.createWithContext((ImmutableStringReader)reader);
        }
        boolean flag = WorldCoordinate.J_1907_R(reader);
        int i = reader.getCursor();
        double d0 = reader.canRead() && reader.peek() != ' ' ? reader.readDouble() : 0.0;
        String s = reader.getString().substring(i, reader.getCursor());
        if (flag && s.isEmpty()) {
            return new WorldCoordinate(true, 0.0);
        }
        if (!s.contains(".") && !flag && centerIntegers) {
            d0 += 0.5;
        }
        return new WorldCoordinate(flag, d0);
    }

    public static WorldCoordinate n_1700_B(StringReader reader) throws CommandSyntaxException {
        if (reader.canRead() && reader.peek() == '^') {
            throw u_1579_Y.J_1907_R.createWithContext((ImmutableStringReader)reader);
        }
        if (!reader.canRead()) {
            throw J_1907_R.createWithContext((ImmutableStringReader)reader);
        }
        boolean flag = WorldCoordinate.J_1907_R(reader);
        double d0 = reader.canRead() && reader.peek() != ' ' ? (flag ? reader.readDouble() : (double)reader.readInt()) : 0.0;
        return new WorldCoordinate(flag, d0);
    }

    public static boolean J_1907_R(StringReader reader) {
        boolean flag;
        if (reader.peek() == '~') {
            flag = true;
            reader.skip();
        } else {
            flag = false;
        }
        return flag;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof WorldCoordinate)) {
            return false;
        }
        WorldCoordinate locationpart = (WorldCoordinate)p_equals_1_;
        if (this.R_4764_Y != locationpart.R_4764_Y) {
            return false;
        }
        return Double.compare(locationpart.G_564_y, this.G_564_y) == 0;
    }

    public int hashCode() {
        int i = this.R_4764_Y ? 1 : 0;
        long j = Double.doubleToLongBits(this.G_564_y);
        return 31 * i + (int)(j ^ j >>> 32);
    }

    public boolean n_1700_B() {
        return this.R_4764_Y;
    }
}


