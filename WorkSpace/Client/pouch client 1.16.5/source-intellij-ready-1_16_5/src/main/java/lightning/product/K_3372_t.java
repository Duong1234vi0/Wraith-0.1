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

public class K_3372_t
extends d_2427_y
implements x_607_J {
    public double n_1700_B;
    public double J_1907_R;

    @Generated
    public double J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public double R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public void n_1700_B(double yaw) {
        this.n_1700_B = yaw;
    }

    @Generated
    public void J_1907_R(double pitch) {
        this.J_1907_R = pitch;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof K_3372_t)) {
            return false;
        }
        K_3372_t other = (K_3372_t)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        if (Double.compare(this.J_1907_R(), other.J_1907_R()) != 0) {
            return false;
        }
        return Double.compare(this.R_4764_Y(), other.R_4764_Y()) == 0;
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof K_3372_t;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $yaw = Double.doubleToLongBits(this.J_1907_R());
        result = result * 59 + (int)($yaw >>> 32 ^ $yaw);
        long $pitch = Double.doubleToLongBits(this.R_4764_Y());
        result = result * 59 + (int)($pitch >>> 32 ^ $pitch);
        return result;
    }

    @Generated
    public String toString() {
        return "EventLook(yaw=" + this.J_1907_R() + ", pitch=" + this.R_4764_Y() + ")";
    }

    @Generated
    public K_3372_t(double yaw, double pitch) {
        this.n_1700_B = yaw;
        this.J_1907_R = pitch;
    }
}

