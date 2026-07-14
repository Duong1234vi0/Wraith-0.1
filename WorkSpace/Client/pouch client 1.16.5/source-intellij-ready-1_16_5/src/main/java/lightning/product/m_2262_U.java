/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.d_2427_y;
import lightning.product.x_607_J;
import lombok.Generated;

public class m_2262_U
extends d_2427_y
implements x_607_J {
    private double n_1700_B;
    private double J_1907_R;
    private double R_4764_Y;
    private float G_564_y;
    private float P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N;
    private boolean w_1484_f;

    @Generated
    public double J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public double R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public double G_564_y() {
        return this.R_4764_Y;
    }

    @Generated
    public float P_1922_E() {
        return this.G_564_y;
    }

    @Generated
    public float u_1723_Y() {
        return this.P_1922_E;
    }

    @Generated
    public boolean v_4262_N() {
        return this.u_1723_Y;
    }

    @Generated
    public boolean w_1484_f() {
        return this.v_4262_N;
    }

    @Generated
    public boolean t_148_a() {
        return this.w_1484_f;
    }

    @Generated
    public void n_1700_B(double x) {
        this.n_1700_B = x;
    }

    @Generated
    public void J_1907_R(double y) {
        this.J_1907_R = y;
    }

    @Generated
    public void R_4764_Y(double z) {
        this.R_4764_Y = z;
    }

    @Generated
    public void n_1700_B(float yaw) {
        this.G_564_y = yaw;
    }

    @Generated
    public void J_1907_R(float pitch) {
        this.P_1922_E = pitch;
    }

    @Generated
    public void J_1907_R(boolean onGround) {
        this.u_1723_Y = onGround;
    }

    @Generated
    public void R_4764_Y(boolean isSneaking) {
        this.v_4262_N = isSneaking;
    }

    @Generated
    public void G_564_y(boolean isSprinting) {
        this.w_1484_f = isSprinting;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof m_2262_U)) {
            return false;
        }
        m_2262_U other = (m_2262_U)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Double.compare(this.J_1907_R(), other.J_1907_R()) != 0) {
            return false;
        }
        if (Double.compare(this.R_4764_Y(), other.R_4764_Y()) != 0) {
            return false;
        }
        if (Double.compare(this.G_564_y(), other.G_564_y()) != 0) {
            return false;
        }
        if (Float.compare(this.P_1922_E(), other.P_1922_E()) != 0) {
            return false;
        }
        if (Float.compare(this.u_1723_Y(), other.u_1723_Y()) != 0) {
            return false;
        }
        if (this.v_4262_N() != other.v_4262_N()) {
            return false;
        }
        if (this.w_1484_f() != other.w_1484_f()) {
            return false;
        }
        return this.t_148_a() == other.t_148_a();
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof m_2262_U;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $x = Double.doubleToLongBits(this.J_1907_R());
        result = result * 59 + (int)($x >>> 32 ^ $x);
        long $y = Double.doubleToLongBits(this.R_4764_Y());
        result = result * 59 + (int)($y >>> 32 ^ $y);
        long $z = Double.doubleToLongBits(this.G_564_y());
        result = result * 59 + (int)($z >>> 32 ^ $z);
        result = result * 59 + Float.floatToIntBits(this.P_1922_E());
        result = result * 59 + Float.floatToIntBits(this.u_1723_Y());
        result = result * 59 + (this.v_4262_N() ? 79 : 97);
        result = result * 59 + (this.w_1484_f() ? 79 : 97);
        result = result * 59 + (this.t_148_a() ? 79 : 97);
        return result;
    }

    @Generated
    public String toString() {
        return "EventMotion(x=" + this.J_1907_R() + ", y=" + this.R_4764_Y() + ", z=" + this.G_564_y() + ", yaw=" + this.P_1922_E() + ", pitch=" + this.u_1723_Y() + ", onGround=" + this.v_4262_N() + ", isSneaking=" + this.w_1484_f() + ", isSprinting=" + this.t_148_a() + ")";
    }

    @Generated
    public m_2262_U(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean isSneaking, boolean isSprinting) {
        this.n_1700_B = x;
        this.J_1907_R = y;
        this.R_4764_Y = z;
        this.G_564_y = yaw;
        this.P_1922_E = pitch;
        this.u_1723_Y = onGround;
        this.v_4262_N = isSneaking;
        this.w_1484_f = isSprinting;
    }
}

