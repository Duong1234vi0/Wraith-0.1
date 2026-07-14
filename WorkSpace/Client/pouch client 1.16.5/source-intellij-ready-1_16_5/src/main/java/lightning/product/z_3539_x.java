/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.mojang.serialization.Codec
 *  javax.annotation.concurrent.Immutable
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import com.mojang.serialization.Codec;
import java.util.stream.IntStream;
import javax.annotation.concurrent.Immutable;
import lightning.product.Position;
import lightning.product.b_257_Y;
import lightning.product.j_3341_s;
import lightning.product.u_530_F;

@Immutable
public class z_3539_x
implements Comparable<z_3539_x> {
    public static final Codec<z_3539_x> CODEC = Codec.INT_STREAM.comapFlatMap(stream -> j_3341_s.n_1700_B(stream, 3).map(componentArray -> new z_3539_x(componentArray[0], componentArray[1], componentArray[2])), vector -> IntStream.of(vector.getX(), vector.getY(), vector.getZ()));
    public static final z_3539_x NULL_VECTOR = new z_3539_x(0, 0, 0);
    public int x;
    public int y;
    public int z;

    public z_3539_x(int xIn, int yIn, int zIn) {
        this.x = xIn;
        this.y = yIn;
        this.z = zIn;
    }

    public z_3539_x(double xIn, double yIn, double zIn) {
        this(u_530_F.R_4764_Y(xIn), u_530_F.R_4764_Y(yIn), u_530_F.R_4764_Y(zIn));
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof z_3539_x)) {
            return false;
        }
        z_3539_x vector3i = (z_3539_x)p_equals_1_;
        if (this.getX() != vector3i.getX()) {
            return false;
        }
        if (this.getY() != vector3i.getY()) {
            return false;
        }
        return this.getZ() == vector3i.getZ();
    }

    public int hashCode() {
        return (this.getY() + this.getZ() * 31) * 31 + this.getX();
    }

    @Override
    public int compareTo(z_3539_x p_compareTo_1_) {
        if (this.getY() == p_compareTo_1_.getY()) {
            return this.getZ() == p_compareTo_1_.getZ() ? this.getX() - p_compareTo_1_.getX() : this.getZ() - p_compareTo_1_.getZ();
        }
        return this.getY() - p_compareTo_1_.getY();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    protected void setX(int xIn) {
        this.x = xIn;
    }

    protected void setY(int yIn) {
        this.y = yIn;
    }

    protected void setZ(int zIn) {
        this.z = zIn;
    }

    public z_3539_x up() {
        return this.up(1);
    }

    public z_3539_x up(int n) {
        return this.offset(b_257_Y.J_1907_R, n);
    }

    public z_3539_x down() {
        return this.down(1);
    }

    public z_3539_x down(int n) {
        return this.offset(b_257_Y.n_1700_B, n);
    }

    public z_3539_x offset(b_257_Y facing, int n) {
        return n == 0 ? this : new z_3539_x(this.getX() + facing.t_148_a() * n, this.getY() + facing.s_956_w() * n, this.getZ() + facing.u_2550_I() * n);
    }

    public z_3539_x crossProduct(z_3539_x vec) {
        return new z_3539_x(this.getY() * vec.getZ() - this.getZ() * vec.getY(), this.getZ() * vec.getX() - this.getX() * vec.getZ(), this.getX() * vec.getY() - this.getY() * vec.getX());
    }

    public boolean withinDistance(z_3539_x vector, double distance) {
        return this.distanceSq(vector.getX(), vector.getY(), vector.getZ(), false) < distance * distance;
    }

    public boolean withinDistance(Position position, double distance) {
        return this.distanceSq(position.n_1700_B(), position.J_1907_R(), position.R_4764_Y(), true) < distance * distance;
    }

    public double distanceSq(z_3539_x to) {
        return this.distanceSq(to.getX(), to.getY(), to.getZ(), true);
    }

    public double distanceSq(Position position, boolean useCenter) {
        return this.distanceSq(position.n_1700_B(), position.J_1907_R(), position.R_4764_Y(), useCenter);
    }

    public double distanceSq(double x, double y, double z, boolean useCenter) {
        double d0 = useCenter ? 0.5 : 0.0;
        double d1 = (double)this.getX() + d0 - x;
        double d2 = (double)this.getY() + d0 - y;
        double d3 = (double)this.getZ() + d0 - z;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public int manhattanDistance(z_3539_x vector) {
        float f = Math.abs(vector.getX() - this.getX());
        float f1 = Math.abs(vector.getY() - this.getY());
        float f2 = Math.abs(vector.getZ() - this.getZ());
        return (int)(f + f1 + f2);
    }

    public int func_243648_a(b_257_Y.n_1700_B p_243648_1_) {
        return p_243648_1_.n_1700_B(this.x, this.y, this.z);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("x", this.getX()).add("y", this.getY()).add("z", this.getZ()).toString();
    }

    public String getCoordinatesAsString() {
        return this.getX() + ", " + this.getY() + ", " + this.getZ();
    }
}


