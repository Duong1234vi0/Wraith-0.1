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
import java.util.Objects;
import lightning.product.WorldCoordinate;
import lightning.product.P_3504_Q;
import lightning.product.Coordinates;
import lightning.product.e_2866_D;
import lightning.product.u_1579_Y;
import lightning.product.u_530_F;
import lightning.product.y_2498_m;

public class LocalCoordinates
implements Coordinates {
    private final double n_1700_B;
    private final double J_1907_R;
    private final double R_4764_Y;

    public LocalCoordinates(double leftIn, double upIn, double forwardsIn) {
        this.n_1700_B = leftIn;
        this.J_1907_R = upIn;
        this.R_4764_Y = forwardsIn;
    }

    @Override
    public e_2866_D n_1700_B(y_2498_m source) {
        P_3504_Q vector2f = source.multiplayerClientSuggestionProvider();
        e_2866_D vector3d = source.Y_601_j().n_1700_B(source);
        float f = u_530_F.J_1907_R((vector2f.s_956_w + 90.0f) * ((float)Math.PI / 180));
        float f1 = u_530_F.n_1700_B((vector2f.s_956_w + 90.0f) * ((float)Math.PI / 180));
        float f2 = u_530_F.J_1907_R(-vector2f.t_148_a * ((float)Math.PI / 180));
        float f3 = u_530_F.n_1700_B(-vector2f.t_148_a * ((float)Math.PI / 180));
        float f4 = u_530_F.J_1907_R((-vector2f.t_148_a + 90.0f) * ((float)Math.PI / 180));
        float f5 = u_530_F.n_1700_B((-vector2f.t_148_a + 90.0f) * ((float)Math.PI / 180));
        e_2866_D vector3d1 = new e_2866_D(f * f2, f3, f1 * f2);
        e_2866_D vector3d2 = new e_2866_D(f * f4, f5, f1 * f4);
        e_2866_D vector3d3 = vector3d1.R_4764_Y(vector3d2).n_1700_B(-1.0);
        double d0 = vector3d1.J_1907_R * this.R_4764_Y + vector3d2.J_1907_R * this.J_1907_R + vector3d3.J_1907_R * this.n_1700_B;
        double d1 = vector3d1.R_4764_Y * this.R_4764_Y + vector3d2.R_4764_Y * this.J_1907_R + vector3d3.R_4764_Y * this.n_1700_B;
        double d2 = vector3d1.G_564_y * this.R_4764_Y + vector3d2.G_564_y * this.J_1907_R + vector3d3.G_564_y * this.n_1700_B;
        return new e_2866_D(vector3d.J_1907_R + d0, vector3d.R_4764_Y + d1, vector3d.G_564_y + d2);
    }

    @Override
    public P_3504_Q J_1907_R(y_2498_m source) {
        return P_3504_Q.n_1700_B;
    }

    @Override
    public boolean n_1700_B() {
        return true;
    }

    @Override
    public boolean J_1907_R() {
        return true;
    }

    @Override
    public boolean R_4764_Y() {
        return true;
    }

    public static LocalCoordinates n_1700_B(StringReader reader) throws CommandSyntaxException {
        int i = reader.getCursor();
        double d0 = LocalCoordinates.n_1700_B(reader, i);
        if (reader.canRead() && reader.peek() == ' ') {
            reader.skip();
            double d1 = LocalCoordinates.n_1700_B(reader, i);
            if (reader.canRead() && reader.peek() == ' ') {
                reader.skip();
                double d2 = LocalCoordinates.n_1700_B(reader, i);
                return new LocalCoordinates(d0, d1, d2);
            }
            reader.setCursor(i);
            throw u_1579_Y.n_1700_B.createWithContext((ImmutableStringReader)reader);
        }
        reader.setCursor(i);
        throw u_1579_Y.n_1700_B.createWithContext((ImmutableStringReader)reader);
    }

    private static double n_1700_B(StringReader reader, int start) throws CommandSyntaxException {
        if (!reader.canRead()) {
            throw WorldCoordinate.n_1700_B.createWithContext((ImmutableStringReader)reader);
        }
        if (reader.peek() != '^') {
            reader.setCursor(start);
            throw u_1579_Y.J_1907_R.createWithContext((ImmutableStringReader)reader);
        }
        reader.skip();
        return reader.canRead() && reader.peek() != ' ' ? reader.readDouble() : 0.0;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof LocalCoordinates)) {
            return false;
        }
        LocalCoordinates locallocationargument = (LocalCoordinates)p_equals_1_;
        return this.n_1700_B == locallocationargument.n_1700_B && this.J_1907_R == locallocationargument.J_1907_R && this.R_4764_Y == locallocationargument.R_4764_Y;
    }

    public int hashCode() {
        return Objects.hash(this.n_1700_B, this.J_1907_R, this.R_4764_Y);
    }
}


