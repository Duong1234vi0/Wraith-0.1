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

public class h_2848_I
extends d_2427_y
implements x_607_J {
    private int n_1700_B;

    @Generated
    public int J_1907_R() {
        return this.n_1700_B;
    }

    @Generated
    public void n_1700_B(int currentSlot) {
        this.n_1700_B = currentSlot;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof h_2848_I)) {
            return false;
        }
        h_2848_I other = (h_2848_I)o;
        if (!other.n_1700_B(this)) {
            return false;
        }
        return this.J_1907_R() == other.J_1907_R();
    }

    @Generated
    protected boolean n_1700_B(Object other) {
        return other instanceof h_2848_I;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.J_1907_R();
        return result;
    }

    @Generated
    public String toString() {
        return "EventDropItem(currentSlot=" + this.J_1907_R() + ")";
    }

    @Generated
    public h_2848_I(int currentSlot) {
        this.n_1700_B = currentSlot;
    }
}

