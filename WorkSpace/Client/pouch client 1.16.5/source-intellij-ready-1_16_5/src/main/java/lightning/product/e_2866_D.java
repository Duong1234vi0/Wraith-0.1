/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.M_1336_P;
import lightning.product.P_3504_Q;
import lightning.product.Position;
import lightning.product.b_257_Y;
import lightning.product.u_530_F;
import lightning.product.z_3539_x;

public class e_2866_D
implements Position {
    public static final e_2866_D n_1700_B = new e_2866_D(0.0, 0.0, 0.0);
    public double J_1907_R;
    public double R_4764_Y;
    public double G_564_y;

    public static e_2866_D n_1700_B(int packed) {
        double d0 = (double)(packed >> 16 & 0xFF) / 255.0;
        double d1 = (double)(packed >> 8 & 0xFF) / 255.0;
        double d2 = (double)(packed & 0xFF) / 255.0;
        return new e_2866_D(d0, d1, d2);
    }

    public static e_2866_D n_1700_B(z_3539_x toCopy) {
        return new e_2866_D((double)toCopy.getX() + 0.5, (double)toCopy.getY() + 0.5, (double)toCopy.getZ() + 0.5);
    }

    public static e_2866_D J_1907_R(z_3539_x toCopy) {
        return new e_2866_D(toCopy.getX(), toCopy.getY(), toCopy.getZ());
    }

    public static e_2866_D R_4764_Y(z_3539_x toCopy) {
        return new e_2866_D((double)toCopy.getX() + 0.5, toCopy.getY(), (double)toCopy.getZ() + 0.5);
    }

    public static e_2866_D n_1700_B(z_3539_x toCopy, double verticalOffset) {
        return new e_2866_D((double)toCopy.getX() + 0.5, (double)toCopy.getY() + verticalOffset, (double)toCopy.getZ() + 0.5);
    }

    public e_2866_D(double xIn, double yIn, double zIn) {
        this.J_1907_R = xIn;
        this.R_4764_Y = yIn;
        this.G_564_y = zIn;
    }

    public e_2866_D(M_1336_P vec) {
        this(vec.n_1700_B(), vec.J_1907_R(), vec.R_4764_Y());
    }

    public e_2866_D n_1700_B(e_2866_D vec) {
        return new e_2866_D(vec.J_1907_R - this.J_1907_R, vec.R_4764_Y - this.R_4764_Y, vec.G_564_y - this.G_564_y);
    }

    public e_2866_D G_564_y() {
        double d0 = u_530_F.n_1700_B(this.J_1907_R * this.J_1907_R + this.R_4764_Y * this.R_4764_Y + this.G_564_y * this.G_564_y);
        return d0 < 1.0E-4 ? n_1700_B : new e_2866_D(this.J_1907_R / d0, this.R_4764_Y / d0, this.G_564_y / d0);
    }

    public double J_1907_R(e_2866_D vec) {
        return this.J_1907_R * vec.J_1907_R + this.R_4764_Y * vec.R_4764_Y + this.G_564_y * vec.G_564_y;
    }

    public e_2866_D R_4764_Y(e_2866_D vec) {
        return new e_2866_D(this.R_4764_Y * vec.G_564_y - this.G_564_y * vec.R_4764_Y, this.G_564_y * vec.J_1907_R - this.J_1907_R * vec.G_564_y, this.J_1907_R * vec.R_4764_Y - this.R_4764_Y * vec.J_1907_R);
    }

    public e_2866_D G_564_y(e_2866_D vec) {
        return this.n_1700_B(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y);
    }

    public e_2866_D n_1700_B(double x, double y, double z) {
        return this.J_1907_R(-x, -y, -z);
    }

    public e_2866_D P_1922_E(e_2866_D vec) {
        return this.J_1907_R(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y);
    }

    public e_2866_D J_1907_R(double x, double y, double z) {
        return new e_2866_D(this.J_1907_R + x, this.R_4764_Y + y, this.G_564_y + z);
    }

    public boolean n_1700_B(Position pos, double distance) {
        return this.R_4764_Y(pos.n_1700_B(), pos.J_1907_R(), pos.R_4764_Y()) < distance * distance;
    }

    public double u_1723_Y(e_2866_D vec) {
        double d0 = vec.J_1907_R - this.J_1907_R;
        double d1 = vec.R_4764_Y - this.R_4764_Y;
        double d2 = vec.G_564_y - this.G_564_y;
        return u_530_F.n_1700_B(d0 * d0 + d1 * d1 + d2 * d2);
    }

    public double v_4262_N(e_2866_D vec) {
        double d0 = vec.J_1907_R - this.J_1907_R;
        double d1 = vec.R_4764_Y - this.R_4764_Y;
        double d2 = vec.G_564_y - this.G_564_y;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public double R_4764_Y(double xIn, double yIn, double zIn) {
        double d0 = xIn - this.J_1907_R;
        double d1 = yIn - this.R_4764_Y;
        double d2 = zIn - this.G_564_y;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public e_2866_D n_1700_B(double factor) {
        return this.G_564_y(factor, factor, factor);
    }

    public e_2866_D P_1922_E() {
        return this.n_1700_B(-1.0);
    }

    public e_2866_D w_1484_f(e_2866_D vec) {
        return this.G_564_y(vec.J_1907_R, vec.R_4764_Y, vec.G_564_y);
    }

    public e_2866_D G_564_y(double factorX, double factorY, double factorZ) {
        return new e_2866_D(this.J_1907_R * factorX, this.R_4764_Y * factorY, this.G_564_y * factorZ);
    }

    public double u_1723_Y() {
        return u_530_F.n_1700_B(this.J_1907_R * this.J_1907_R + this.R_4764_Y * this.R_4764_Y + this.G_564_y * this.G_564_y);
    }

    public double v_4262_N() {
        return this.J_1907_R * this.J_1907_R + this.R_4764_Y * this.R_4764_Y + this.G_564_y * this.G_564_y;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof e_2866_D)) {
            return false;
        }
        e_2866_D vector3d = (e_2866_D)p_equals_1_;
        if (Double.compare(vector3d.J_1907_R, this.J_1907_R) != 0) {
            return false;
        }
        if (Double.compare(vector3d.R_4764_Y, this.R_4764_Y) != 0) {
            return false;
        }
        return Double.compare(vector3d.G_564_y, this.G_564_y) == 0;
    }

    public int hashCode() {
        long j = Double.doubleToLongBits(this.J_1907_R);
        int i = (int)(j ^ j >>> 32);
        j = Double.doubleToLongBits(this.R_4764_Y);
        i = 31 * i + (int)(j ^ j >>> 32);
        j = Double.doubleToLongBits(this.G_564_y);
        return 31 * i + (int)(j ^ j >>> 32);
    }

    public String toString() {
        return "(" + this.J_1907_R + ", " + this.R_4764_Y + ", " + this.G_564_y + ")";
    }

    public e_2866_D n_1700_B(float pitch) {
        float f = u_530_F.J_1907_R(pitch);
        float f1 = u_530_F.n_1700_B(pitch);
        double d0 = this.J_1907_R;
        double d1 = this.R_4764_Y * (double)f + this.G_564_y * (double)f1;
        double d2 = this.G_564_y * (double)f - this.R_4764_Y * (double)f1;
        return new e_2866_D(d0, d1, d2);
    }

    public e_2866_D J_1907_R(float yaw) {
        float f = u_530_F.J_1907_R(yaw);
        float f1 = u_530_F.n_1700_B(yaw);
        double d0 = this.J_1907_R * (double)f + this.G_564_y * (double)f1;
        double d1 = this.R_4764_Y;
        double d2 = this.G_564_y * (double)f - this.J_1907_R * (double)f1;
        return new e_2866_D(d0, d1, d2);
    }

    public e_2866_D R_4764_Y(float roll) {
        float f = u_530_F.J_1907_R(roll);
        float f1 = u_530_F.n_1700_B(roll);
        double d0 = this.J_1907_R * (double)f + this.R_4764_Y * (double)f1;
        double d1 = this.R_4764_Y * (double)f - this.J_1907_R * (double)f1;
        double d2 = this.G_564_y;
        return new e_2866_D(d0, d1, d2);
    }

    public static e_2866_D n_1700_B(P_3504_Q vec) {
        return e_2866_D.n_1700_B(vec.t_148_a, vec.s_956_w);
    }

    public static e_2866_D n_1700_B(float pitch, float yaw) {
        float f = u_530_F.J_1907_R(-yaw * ((float)Math.PI / 180) - (float)Math.PI);
        float f1 = u_530_F.n_1700_B(-yaw * ((float)Math.PI / 180) - (float)Math.PI);
        float f2 = -u_530_F.J_1907_R(-pitch * ((float)Math.PI / 180));
        float f3 = u_530_F.n_1700_B(-pitch * ((float)Math.PI / 180));
        return new e_2866_D(f1 * f2, f3, f * f2);
    }

    public e_2866_D n_1700_B(EnumSet<b_257_Y.n_1700_B> axes) {
        double d0 = axes.contains(b_257_Y.n_1700_B.n_1700_B) ? (double)u_530_F.R_4764_Y(this.J_1907_R) : this.J_1907_R;
        double d1 = axes.contains(b_257_Y.n_1700_B.J_1907_R) ? (double)u_530_F.R_4764_Y(this.R_4764_Y) : this.R_4764_Y;
        double d2 = axes.contains(b_257_Y.n_1700_B.R_4764_Y) ? (double)u_530_F.R_4764_Y(this.G_564_y) : this.G_564_y;
        return new e_2866_D(d0, d1, d2);
    }

    public double n_1700_B(b_257_Y.n_1700_B axis) {
        return axis.n_1700_B(this.J_1907_R, this.R_4764_Y, this.G_564_y);
    }

    @Override
    public final double n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public final double J_1907_R() {
        return this.R_4764_Y;
    }

    @Override
    public final double R_4764_Y() {
        return this.G_564_y;
    }

    public e_2866_D n_1700_B(e_2866_D vec, float factor) {
        return new e_2866_D(e_2866_D.P_1922_E(this.n_1700_B(), vec.n_1700_B(), factor), e_2866_D.P_1922_E(this.J_1907_R(), vec.J_1907_R(), factor), e_2866_D.P_1922_E(this.R_4764_Y(), vec.R_4764_Y(), factor));
    }

    public static double P_1922_E(double input, double target, double step) {
        return input + step * (target - input);
    }
}


