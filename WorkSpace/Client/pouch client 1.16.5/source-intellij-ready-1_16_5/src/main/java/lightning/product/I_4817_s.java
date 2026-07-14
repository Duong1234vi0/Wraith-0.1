/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.BlockHitResult;
import lightning.product.BoundingBox;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.u_530_F;

public class I_4817_s {
    public final double minX;
    public final double minY;
    public final double minZ;
    public final double maxX;
    public final double maxY;
    public final double maxZ;

    public I_4817_s(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.minX = Math.min(x1, x2);
        this.minY = Math.min(y1, y2);
        this.minZ = Math.min(z1, z2);
        this.maxX = Math.max(x1, x2);
        this.maxY = Math.max(y1, y2);
        this.maxZ = Math.max(z1, z2);
    }

    public I_4817_s(c_1514_x pos) {
        this(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
    }

    public I_4817_s(c_1514_x pos1, c_1514_x pos2) {
        this(pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
    }

    public I_4817_s(e_2866_D min, e_2866_D max) {
        this(min.J_1907_R, min.R_4764_Y, min.G_564_y, max.J_1907_R, max.R_4764_Y, max.G_564_y);
    }

    public static I_4817_s toImmutable(BoundingBox mutableBox) {
        return new I_4817_s(mutableBox.n_1700_B, mutableBox.J_1907_R, mutableBox.R_4764_Y, mutableBox.G_564_y + 1, mutableBox.P_1922_E + 1, mutableBox.u_1723_Y + 1);
    }

    public static I_4817_s fromVector(e_2866_D vector) {
        return new I_4817_s(vector.J_1907_R, vector.R_4764_Y, vector.G_564_y, vector.J_1907_R + 1.0, vector.R_4764_Y + 1.0, vector.G_564_y + 1.0);
    }

    public double getMin(b_257_Y.n_1700_B axis) {
        return axis.n_1700_B(this.minX, this.minY, this.minZ);
    }

    public double getMax(b_257_Y.n_1700_B axis) {
        return axis.n_1700_B(this.maxX, this.maxY, this.maxZ);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof I_4817_s)) {
            return false;
        }
        I_4817_s axisalignedbb = (I_4817_s)p_equals_1_;
        if (Double.compare(axisalignedbb.minX, this.minX) != 0) {
            return false;
        }
        if (Double.compare(axisalignedbb.minY, this.minY) != 0) {
            return false;
        }
        if (Double.compare(axisalignedbb.minZ, this.minZ) != 0) {
            return false;
        }
        if (Double.compare(axisalignedbb.maxX, this.maxX) != 0) {
            return false;
        }
        if (Double.compare(axisalignedbb.maxY, this.maxY) != 0) {
            return false;
        }
        return Double.compare(axisalignedbb.maxZ, this.maxZ) == 0;
    }

    public int hashCode() {
        long i = Double.doubleToLongBits(this.minX);
        int j = (int)(i ^ i >>> 32);
        i = Double.doubleToLongBits(this.minY);
        j = 31 * j + (int)(i ^ i >>> 32);
        i = Double.doubleToLongBits(this.minZ);
        j = 31 * j + (int)(i ^ i >>> 32);
        i = Double.doubleToLongBits(this.maxX);
        j = 31 * j + (int)(i ^ i >>> 32);
        i = Double.doubleToLongBits(this.maxY);
        j = 31 * j + (int)(i ^ i >>> 32);
        i = Double.doubleToLongBits(this.maxZ);
        return 31 * j + (int)(i ^ i >>> 32);
    }

    public I_4817_s contract(double x, double y, double z) {
        double d0 = this.minX;
        double d1 = this.minY;
        double d2 = this.minZ;
        double d3 = this.maxX;
        double d4 = this.maxY;
        double d5 = this.maxZ;
        if (x < 0.0) {
            d0 -= x;
        } else if (x > 0.0) {
            d3 -= x;
        }
        if (y < 0.0) {
            d1 -= y;
        } else if (y > 0.0) {
            d4 -= y;
        }
        if (z < 0.0) {
            d2 -= z;
        } else if (z > 0.0) {
            d5 -= z;
        }
        return new I_4817_s(d0, d1, d2, d3, d4, d5);
    }

    public I_4817_s expand(e_2866_D vector) {
        return this.expand(vector.J_1907_R, vector.R_4764_Y, vector.G_564_y);
    }

    public I_4817_s expand(double x, double y, double z) {
        double d0 = this.minX;
        double d1 = this.minY;
        double d2 = this.minZ;
        double d3 = this.maxX;
        double d4 = this.maxY;
        double d5 = this.maxZ;
        if (x < 0.0) {
            d0 += x;
        } else if (x > 0.0) {
            d3 += x;
        }
        if (y < 0.0) {
            d1 += y;
        } else if (y > 0.0) {
            d4 += y;
        }
        if (z < 0.0) {
            d2 += z;
        } else if (z > 0.0) {
            d5 += z;
        }
        return new I_4817_s(d0, d1, d2, d3, d4, d5);
    }

    public I_4817_s grow(double x, double y, double z) {
        double d0 = this.minX - x;
        double d1 = this.minY - y;
        double d2 = this.minZ - z;
        double d3 = this.maxX + x;
        double d4 = this.maxY + y;
        double d5 = this.maxZ + z;
        return new I_4817_s(d0, d1, d2, d3, d4, d5);
    }

    public I_4817_s grow(double value) {
        return this.grow(value, value, value);
    }

    public I_4817_s intersect(I_4817_s other) {
        double d0 = Math.max(this.minX, other.minX);
        double d1 = Math.max(this.minY, other.minY);
        double d2 = Math.max(this.minZ, other.minZ);
        double d3 = Math.min(this.maxX, other.maxX);
        double d4 = Math.min(this.maxY, other.maxY);
        double d5 = Math.min(this.maxZ, other.maxZ);
        return new I_4817_s(d0, d1, d2, d3, d4, d5);
    }

    public I_4817_s union(I_4817_s other) {
        double d0 = Math.min(this.minX, other.minX);
        double d1 = Math.min(this.minY, other.minY);
        double d2 = Math.min(this.minZ, other.minZ);
        double d3 = Math.max(this.maxX, other.maxX);
        double d4 = Math.max(this.maxY, other.maxY);
        double d5 = Math.max(this.maxZ, other.maxZ);
        return new I_4817_s(d0, d1, d2, d3, d4, d5);
    }

    public I_4817_s offset(double x, double y, double z) {
        return new I_4817_s(this.minX + x, this.minY + y, this.minZ + z, this.maxX + x, this.maxY + y, this.maxZ + z);
    }

    public I_4817_s offset(c_1514_x pos) {
        return new I_4817_s(this.minX + (double)pos.getX(), this.minY + (double)pos.getY(), this.minZ + (double)pos.getZ(), this.maxX + (double)pos.getX(), this.maxY + (double)pos.getY(), this.maxZ + (double)pos.getZ());
    }

    public I_4817_s offset(e_2866_D vec) {
        return this.offset(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y);
    }

    public boolean intersects(I_4817_s other) {
        return this.intersects(other.minX, other.minY, other.minZ, other.maxX, other.maxY, other.maxZ);
    }

    public boolean intersects(double x1, double y1, double z1, double x2, double y2, double z2) {
        return this.minX < x2 && this.maxX > x1 && this.minY < y2 && this.maxY > y1 && this.minZ < z2 && this.maxZ > z1;
    }

    public boolean intersects(e_2866_D min, e_2866_D max) {
        return this.intersects(Math.min(min.J_1907_R, max.J_1907_R), Math.min(min.R_4764_Y, max.R_4764_Y), Math.min(min.G_564_y, max.G_564_y), Math.max(min.J_1907_R, max.J_1907_R), Math.max(min.R_4764_Y, max.R_4764_Y), Math.max(min.G_564_y, max.G_564_y));
    }

    public boolean contains(e_2866_D vec) {
        return this.contains(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y);
    }

    public boolean contains(double x, double y, double z) {
        return x >= this.minX && x < this.maxX && y >= this.minY && y < this.maxY && z >= this.minZ && z < this.maxZ;
    }

    public double getAverageEdgeLength() {
        double d0 = this.getXSize();
        double d1 = this.getYSize();
        double d2 = this.getZSize();
        return (d0 + d1 + d2) / 3.0;
    }

    public double getXSize() {
        return this.maxX - this.minX;
    }

    public double getYSize() {
        return this.maxY - this.minY;
    }

    public double getZSize() {
        return this.maxZ - this.minZ;
    }

    public I_4817_s shrink(double value) {
        return this.grow(-value);
    }

    public Optional<e_2866_D> rayTrace(e_2866_D from, e_2866_D to) {
        double[] adouble = new double[]{1.0};
        double d0 = to.J_1907_R - from.J_1907_R;
        double d1 = to.R_4764_Y - from.R_4764_Y;
        double d2 = to.G_564_y - from.G_564_y;
        b_257_Y direction = I_4817_s.calcSideHit(this, from, adouble, null, d0, d1, d2);
        if (direction == null) {
            return Optional.empty();
        }
        double d3 = adouble[0];
        return Optional.of(from.J_1907_R(d3 * d0, d3 * d1, d3 * d2));
    }

    @Nullable
    public static BlockHitResult rayTrace(Iterable<I_4817_s> boxes, e_2866_D start, e_2866_D end, c_1514_x pos) {
        double[] adouble = new double[]{1.0};
        b_257_Y direction = null;
        double d0 = end.J_1907_R - start.J_1907_R;
        double d1 = end.R_4764_Y - start.R_4764_Y;
        double d2 = end.G_564_y - start.G_564_y;
        for (I_4817_s axisalignedbb : boxes) {
            direction = I_4817_s.calcSideHit(axisalignedbb.offset(pos), start, adouble, direction, d0, d1, d2);
        }
        if (direction == null) {
            return null;
        }
        double d3 = adouble[0];
        return new BlockHitResult(start.J_1907_R(d3 * d0, d3 * d1, d3 * d2), direction, pos, false);
    }

    @Nullable
    private static b_257_Y calcSideHit(I_4817_s aabb, e_2866_D start, double[] minDistance, @Nullable b_257_Y facing, double deltaX, double deltaY, double deltaZ) {
        if (deltaX > 1.0E-7) {
            facing = I_4817_s.checkSideForHit(minDistance, facing, deltaX, deltaY, deltaZ, aabb.minX, aabb.minY, aabb.maxY, aabb.minZ, aabb.maxZ, b_257_Y.P_1922_E, start.J_1907_R, start.R_4764_Y, start.G_564_y);
        } else if (deltaX < -1.0E-7) {
            facing = I_4817_s.checkSideForHit(minDistance, facing, deltaX, deltaY, deltaZ, aabb.maxX, aabb.minY, aabb.maxY, aabb.minZ, aabb.maxZ, b_257_Y.u_1723_Y, start.J_1907_R, start.R_4764_Y, start.G_564_y);
        }
        if (deltaY > 1.0E-7) {
            facing = I_4817_s.checkSideForHit(minDistance, facing, deltaY, deltaZ, deltaX, aabb.minY, aabb.minZ, aabb.maxZ, aabb.minX, aabb.maxX, b_257_Y.n_1700_B, start.R_4764_Y, start.G_564_y, start.J_1907_R);
        } else if (deltaY < -1.0E-7) {
            facing = I_4817_s.checkSideForHit(minDistance, facing, deltaY, deltaZ, deltaX, aabb.maxY, aabb.minZ, aabb.maxZ, aabb.minX, aabb.maxX, b_257_Y.J_1907_R, start.R_4764_Y, start.G_564_y, start.J_1907_R);
        }
        if (deltaZ > 1.0E-7) {
            facing = I_4817_s.checkSideForHit(minDistance, facing, deltaZ, deltaX, deltaY, aabb.minZ, aabb.minX, aabb.maxX, aabb.minY, aabb.maxY, b_257_Y.R_4764_Y, start.G_564_y, start.J_1907_R, start.R_4764_Y);
        } else if (deltaZ < -1.0E-7) {
            facing = I_4817_s.checkSideForHit(minDistance, facing, deltaZ, deltaX, deltaY, aabb.maxZ, aabb.minX, aabb.maxX, aabb.minY, aabb.maxY, b_257_Y.G_564_y, start.G_564_y, start.J_1907_R, start.R_4764_Y);
        }
        return facing;
    }

    @Nullable
    private static b_257_Y checkSideForHit(double[] minDistance, @Nullable b_257_Y prevDirection, double distanceSide, double distanceOtherA, double distanceOtherB, double minSide, double minOtherA, double maxOtherA, double minOtherB, double maxOtherB, b_257_Y hitSide, double startSide, double startOtherA, double startOtherB) {
        double d0 = (minSide - startSide) / distanceSide;
        double d1 = startOtherA + d0 * distanceOtherA;
        double d2 = startOtherB + d0 * distanceOtherB;
        if (0.0 < d0 && d0 < minDistance[0] && minOtherA - 1.0E-7 < d1 && d1 < maxOtherA + 1.0E-7 && minOtherB - 1.0E-7 < d2 && d2 < maxOtherB + 1.0E-7) {
            minDistance[0] = d0;
            return hitSide;
        }
        return prevDirection;
    }

    public String toString() {
        return "AABB[" + this.minX + ", " + this.minY + ", " + this.minZ + "] -> [" + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
    }

    public boolean hasNaN() {
        return Double.isNaN(this.minX) || Double.isNaN(this.minY) || Double.isNaN(this.minZ) || Double.isNaN(this.maxX) || Double.isNaN(this.maxY) || Double.isNaN(this.maxZ);
    }

    public e_2866_D getCenter() {
        return new e_2866_D(u_530_F.G_564_y(0.5, this.minX, this.maxX), u_530_F.G_564_y(0.5, this.minY, this.maxY), u_530_F.G_564_y(0.5, this.minZ, this.maxZ));
    }

    public static I_4817_s withSizeAtOrigin(double xSize, double ySize, double zSize) {
        return new I_4817_s(-xSize / 2.0, -ySize / 2.0, -zSize / 2.0, xSize / 2.0, ySize / 2.0, zSize / 2.0);
    }

    private e_2866_D[] getCorners(I_4817_s AABB) {
        return new e_2866_D[]{new e_2866_D(AABB.minX, AABB.minY, AABB.minZ), new e_2866_D(AABB.minX, AABB.minY, AABB.maxZ), new e_2866_D(AABB.minX, AABB.maxY, AABB.minZ), new e_2866_D(AABB.minX, AABB.maxY, AABB.maxZ), new e_2866_D(AABB.maxX, AABB.minY, AABB.minZ), new e_2866_D(AABB.maxX, AABB.minY, AABB.maxZ), new e_2866_D(AABB.maxX, AABB.maxY, AABB.minZ), new e_2866_D(AABB.maxX, AABB.maxY, AABB.maxZ)};
    }

    public e_2866_D[] getCorners() {
        return this.getCorners(this);
    }
}


