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

public class I_4481_g
extends d_2427_y
implements x_607_J {
    private double n_1700_B;

    @Generated
    public double J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(double horizontalMove) {
        this.n_1700_B = horizontalMove;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof I_4481_g)) {
            return false;
        }
        I_4481_g other = (I_4481_g)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        return Double.compare(this.J_1907_R(), other.J_1907_R()) == 0;
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof I_4481_g;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $horizontalMove = Double.doubleToLongBits(this.J_1907_R());
        result = result * 59 + (int)($horizontalMove >>> 32 ^ $horizontalMove);
        return result;
    }

    @Generated
    public String toString() {
        return "EventPostMovement(horizontalMove=" + this.J_1907_R() + ")";
    }

    @Generated
    public I_4481_g(double horizontalMove) {
        this.n_1700_B = horizontalMove;
    }
}

