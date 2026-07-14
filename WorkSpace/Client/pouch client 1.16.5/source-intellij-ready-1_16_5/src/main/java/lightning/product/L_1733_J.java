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

public class L_1733_J
extends d_2427_y
implements x_607_J {
    private double n_1700_B;

    @Generated
    public double J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(double distance) {
        this.n_1700_B = distance;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof L_1733_J)) {
            return false;
        }
        L_1733_J other = (L_1733_J)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        return Double.compare(this.J_1907_R(), other.J_1907_R()) == 0;
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof L_1733_J;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $distance = Double.doubleToLongBits(this.J_1907_R());
        result = result * 59 + (int)($distance >>> 32 ^ $distance);
        return result;
    }

    @Generated
    public String toString() {
        return "EventThirdPersonDistance(distance=" + this.J_1907_R() + ")";
    }

    @Generated
    public L_1733_J(double distance) {
        this.n_1700_B = distance;
    }
}

