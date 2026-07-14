/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.ImmutableStringReader
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 */
package lightning.product;

import com.mojang.brigadier.ImmutableStringReader;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lightning.product.WorldCoordinate;
import lightning.product.P_3504_Q;
import lightning.product.Coordinates;
import lightning.product.e_2866_D;
import lightning.product.u_1579_Y;
import lightning.product.y_2498_m;

public class WorldCoordinates
implements Coordinates {
    private final WorldCoordinate n_1700_B;
    private final WorldCoordinate J_1907_R;
    private final WorldCoordinate R_4764_Y;

    public WorldCoordinates(WorldCoordinate x, WorldCoordinate y, WorldCoordinate z) {
        this.n_1700_B = x;
        this.J_1907_R = y;
        this.R_4764_Y = z;
    }

    @Override
    public e_2866_D n_1700_B(y_2498_m source) {
        e_2866_D vector3d = source.P_4830_p();
        return new e_2866_D(this.n_1700_B.n_1700_B(vector3d.J_1907_R), this.J_1907_R.n_1700_B(vector3d.R_4764_Y), this.R_4764_Y.n_1700_B(vector3d.G_564_y));
    }

    @Override
    public P_3504_Q J_1907_R(y_2498_m source) {
        P_3504_Q vector2f = source.multiplayerClientSuggestionProvider();
        return new P_3504_Q((float)this.n_1700_B.n_1700_B(vector2f.t_148_a), (float)this.J_1907_R.n_1700_B(vector2f.s_956_w));
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.n_1700_B();
    }

    @Override
    public boolean J_1907_R() {
        return this.J_1907_R.n_1700_B();
    }

    @Override
    public boolean R_4764_Y() {
        return this.R_4764_Y.n_1700_B();
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof WorldCoordinates)) {
            return false;
        }
        WorldCoordinates locationinput = (WorldCoordinates)p_equals_1_;
        if (!this.n_1700_B.equals(locationinput.n_1700_B)) {
            return false;
        }
        return !this.J_1907_R.equals(locationinput.J_1907_R) ? false : this.R_4764_Y.equals(locationinput.R_4764_Y);
    }

    public static WorldCoordinates n_1700_B(StringReader reader) throws CommandSyntaxException {
        int i = reader.getCursor();
        WorldCoordinate locationpart = WorldCoordinate.n_1700_B(reader);
        if (reader.canRead() && reader.peek() == ' ') {
            reader.skip();
            WorldCoordinate locationpart1 = WorldCoordinate.n_1700_B(reader);
            if (reader.canRead() && reader.peek() == ' ') {
                reader.skip();
                WorldCoordinate locationpart2 = WorldCoordinate.n_1700_B(reader);
                return new WorldCoordinates(locationpart, locationpart1, locationpart2);
            }
            reader.setCursor(i);
            throw u_1579_Y.n_1700_B.createWithContext((ImmutableStringReader)reader);
        }
        reader.setCursor(i);
        throw u_1579_Y.n_1700_B.createWithContext((ImmutableStringReader)reader);
    }

    public static WorldCoordinates n_1700_B(StringReader reader, boolean centerIntegers) throws CommandSyntaxException {
        int i = reader.getCursor();
        WorldCoordinate locationpart = WorldCoordinate.n_1700_B(reader, centerIntegers);
        if (reader.canRead() && reader.peek() == ' ') {
            reader.skip();
            WorldCoordinate locationpart1 = WorldCoordinate.n_1700_B(reader, false);
            if (reader.canRead() && reader.peek() == ' ') {
                reader.skip();
                WorldCoordinate locationpart2 = WorldCoordinate.n_1700_B(reader, centerIntegers);
                return new WorldCoordinates(locationpart, locationpart1, locationpart2);
            }
            reader.setCursor(i);
            throw u_1579_Y.n_1700_B.createWithContext((ImmutableStringReader)reader);
        }
        reader.setCursor(i);
        throw u_1579_Y.n_1700_B.createWithContext((ImmutableStringReader)reader);
    }

    public static WorldCoordinates G_564_y() {
        return new WorldCoordinates(new WorldCoordinate(true, 0.0), new WorldCoordinate(true, 0.0), new WorldCoordinate(true, 0.0));
    }

    public int hashCode() {
        int i = this.n_1700_B.hashCode();
        i = 31 * i + this.J_1907_R.hashCode();
        return 31 * i + this.R_4764_Y.hashCode();
    }
}


