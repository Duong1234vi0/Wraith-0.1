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

public class r_2478_U
extends d_2427_y
implements x_607_J {
    private final double n_1700_B;
    private final double J_1907_R;

    @Generated
    public double J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public double R_4764_Y() {
        return this.J_1907_R;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof r_2478_U)) {
            return false;
        }
        r_2478_U other = (r_2478_U)o;
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
        return other instanceof r_2478_U;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $mouseX = Double.doubleToLongBits(this.J_1907_R());
        result = result * 59 + (int)($mouseX >>> 32 ^ $mouseX);
        long $mouseY = Double.doubleToLongBits(this.R_4764_Y());
        result = result * 59 + (int)($mouseY >>> 32 ^ $mouseY);
        return result;
    }

    @Generated
    public String toString() {
        return "EventDragging(mouseX=" + this.J_1907_R() + ", mouseY=" + this.R_4764_Y() + ")";
    }

    @Generated
    public r_2478_U(double mouseX, double mouseY) {
        this.n_1700_B = mouseX;
        this.J_1907_R = mouseY;
    }
}

